##消息路由

消息路由即 Webhook ，该 Web 服务需要开发者自己搭建，并且需要遵从开放平台预先定义好的输入输出。机器人每次响应，吾来会把机器人回复内容、对话解析结果传给消息路由，调用方可以按需使或修改消息体内容达到影响机器人回复的目的。


##### REQUEST BODY SCHEMA: application/json
|类型|字段名称|长度|是否一定存在|
| --- | --- | --- | --- |
|Object[]| suggested_response|-| true |
|String |user_id|[1..128]|true|
|String |extra| [0..1024]||
|String |msg_id|[1..18]|true|
|Boolean|is_dispatch| - |true|
|String |msg_ts| [1..]|true|
|Object |msg_body|-|true|
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
        for (int i = 0; i < suggested_response.length; i++) {
            Object object = suggested_response[i];
            JSONObject json = JSONObject.parseObject(object.toString());
            json.put("is_send", false); //修改is_send 为false，仅为实例，具体代码请结合业务逻辑
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

附开发者文档：
https://openapi.wul.ai/docs/latest/saas.openapi.v2/openapi.v2.html#operation/MessageRoute
