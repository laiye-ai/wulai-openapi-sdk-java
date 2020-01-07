## 消息路由

消息路由即 Webhook ，该 Web 服务需要开发者自己搭建，并且需要遵从开放平台预先定义好的输入输出。机器人每次响应，吾来会把机器人回复内容、对话解析结果传给消息路由，调用方可以按需使或修改消息体内容达到影响机器人回复的目的。


##### REQUEST BODY SCHEMA: application/json
|类型|字段名称|长度|是否一定存在|
| --- | --- | --- | --- |
|Object[]| suggested_response|-| true |
|String |userId|[1..128]|true|
|String |extra| [0..1024]||
|String |msgId|[1..18]|true|
|Boolean|is_dispatch| - |true|
|String |msg_ts| [1..]|true|
|Object |msgBody|-|true|
|String |nickname|[1..128] |true|

##### RESPONSE BODY SCHEMA: application/json
|类型|字段名称|
| --- | --- | 
|Boolean|is_dispatch|  
|Object[]|suggested_response| 

Example:

```java

public class Common extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream in = request.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] b = new byte[1024];
        for (int n; (n = in.read(b)) != -1; ) {
            sb.append(new String(b, 0, n));
        }
        response.setContentType("application/json");
        OutputStream out = response.getOutputStream();
        byte[] dataByteArr = sb.toString().getBytes("UTF-8");
        String data = sb.toString();
        System.out.println("data: " + data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        Object suggested_obj = jsonObject.get("suggested_response");
        Object[] suggested_response = JSONArray.parseArray(suggested_obj.toString()).toArray();
        
        //业务代码处理
        for (int i = 0; i < suggested_response.length; i++) {
            Object object = suggested_response[i];
            JSONObject json = JSONObject.parseObject(object.toString());
            json.put("is_send", false); //修改is_send 为false，仅为示例，具体代码请结合业务逻辑
            suggested_response[i] = json;
        }
        
        // 生成返回对象
        JSONObject responseJson=new JSONObject();
        responseJson.put("suggested_response",suggested_response);
        responseJson.put("is_dispatch",false);
        
        out.write(responseJson.toJSONString().getBytes());
        }
    }

```

附开发者文档链接：
https://openapi.wul.ai/docs/latest/saas.openapi.v2/openapi.v2.html#operation/MessageRoute

## 错误回调
当吾来向第三方（目前只有微信公众号）发消息失败时，会把这个错误详情投递到该接口。注：该接口的回调地址暂时不能在吾来平台页面上配置，如需使用请联系我们。
                                        
##### REQUEST BODY SCHEMA: application/json
|类型|字段名称|
| --- | --- |
|String|userId|
|String|error_msg|
|String|source_msg_id|

##### RESPONSE SCHEMA: application/json
无

附开发者文档链接：
https://openapi.wul.ai/docs/latest/saas.openapi.v2/openapi.v2.html#operation/CallbackError

## 消息投递
调用方开发。如果机器人对接第三方渠道，机器人做出响应后，会调用消息投递接口，将消息投递到第三方渠道。

注意：
1) 此接口可保证至少投递一次（即服务质量QOS=At least once，不保证100%消息只投递一次）；
2) 此接口不能保证消息100%按照消息发送的顺序投递

因此建议：
1) 接入方根据msg_id做去重
2) 接入方根据msg_ts在客户端做重排序

##### REQUEST BODY SCHEMA: application/json
|类型|字段名称|
| --- | --- |
|String|userId|
|Object|sender_info|
|string|msg_type|
|string|extra|
|string|msgId|
|string|msg_ts|
|string|source|
|Object|bot|
|Object|msgBody|
|Object[]|similarResponses|
|Boolean|enableEvaluate|
|String[]|quickReply|

附开发者文档链接：
https://openapi.wul.ai/docs/latest/saas.openapi.v2/openapi.v2.html#operation/CallbackMessage