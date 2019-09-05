
<p align="center">
	<a href="https://www.laiye.com"><img src="https://www.laiye.com/static/official-website/logo.png"></a>
</p>

<h1 align="center">Wulai Openapi SDK for Java</h1>

<p align="center">

[![build status][travis-image]][travis-url]
[![codecov][cov-image]][cov-url]

[travis-image]: https://travis-ci.org/laiye-ai/wulai-openapi-sdk-java.svg?branch=develop
[travis-url]: https://travis-ci.org/laiye-ai/wulai-openapi-sdk-java
[cov-image]: https://codecov.io/gh/zuiyuqingfeng/wulai-openapi-sdk-java/branch/master/graph/badge.svg
[cov-url]: https://codecov.io/gh/zuiyuqingfeng/wulai-openapi-sdk-java
</p>

欢迎使用 Wulai Openapi SDK for Java。

## 环境要求
- 您的系统需要安装JDK 1.8 的运行环境 

## 安装
使用 maven 添加依赖

## 快速使用
在您开始之前，您需要注册帐户并获取您的[凭证](https://openapi.wul.ai/docs/latest/saas.openapi.v2/openapi.v2.html#section/%E9%89%B4%E6%9D%83%E8%AE%A4%E8%AF%81)。

### 创建 WulaiClient 客户端
```
//为避免泄漏公钥密钥等信息，建议将相关配置写到环境变量后使用System类的getenv方法获取环境变量。
WulaiCLient wulaiClient=new WulaiClient(System.getenv("pubkey"),
System.getenv("secret"), "v2", true);
```

### 使用通用方法processCommonRequest发送请求
```
String name="Tom";
//设置创建用户请求参数
String data="{\"user_id\":\"%s\",name}";
//设置对话内容参数
String data2=String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");

//发起创建用户
wulaiClient.processCommonRequest("/user/create",data);

//发起对话机器人请求
wulaiClient.processCommonRequest("/msg/bot-response",data2);

```
### 使用Wulai Java API发送请求
```
// 创建requestBean 对象
UserCreateRequest userCreateRequest = new UserCreateRequest("wulai@test");
userCreateRequest.setNickname("Tom");
userCreateRequest.setAvatar_url("https://www.laiye.com/static/official-website/logo.png");

// 调用Java API发送请求
int result=wulaiClient.userCreate(userCreateRequest);
System.out.println(result); //httpCode=200


// 创建requestBean对象
BotResponseRequest botResponseRequest = new BotResponseRequest("wulai@test","你是谁""); //创建对象时传入必选参数
botResponseRequest.setExtra("readme"); //set可选参数
BotResponse botResponse = wulaiClient.getBotResponse(botResponseRequest); //得到responseBean

// 获取回复内容
System.out.println(botResponse.getMsgId()); 
System.out.println(botResponse.isDispatch());
for (Object object : botResponse.getSuggestedResponse()) {
    System.out.println(object.toString());
}
```      

### 协议说明
```text
网络协议: HTTP

请求方式: POST

请求、响应数据格式: JSON
```