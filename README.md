
<p align="center">
	<a href="https://www.laiye.com"><img src="https://www.laiye.com/static/official-website/logo.png"></a>
</p>

<h1 align="center">Wulai Openapi SDK for Java</h1>

<p align="center">

[![Maven Central](https://img.shields.io/maven-central/v/com.laiye.wulai.javasdk/wulaiSDK.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.laiye.wulai.javasdk%22%20AND%20a:%22wulaiSDK%22)
[![build status][travis-image]][travis-url]
[![codecov][cov-image]][cov-url]
[![Codacy Badge][codacy-image]][codacy-url]

[travis-image]: https://travis-ci.org/laiye-ai/wulai-openapi-sdk-java.svg?branch=develop
[travis-url]: https://travis-ci.org/laiye-ai/wulai-openapi-sdk-java
[cov-image]: https://codecov.io/gh/zuiyuqingfeng/wulai-openapi-sdk-java/branch/master/graph/badge.svg
[cov-url]: https://codecov.io/gh/zuiyuqingfeng/wulai-openapi-sdk-java
[codacy-image]: https://api.codacy.com/project/badge/Grade/877660309403463dbab6a3393d7291d5
[codacy-url]: https://www.codacy.com/app/zuiyuqingfeng/wulai-openapi-sdk-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zuiyuqingfeng/wulai-openapi-sdk-java&amp;utm_campaign=Badge_Grade
</p>

欢迎使用 Wulai Openapi SDK for Java。

## 环境要求
- 您的系统需要安装JDK 1.8 的运行环境 

## 安装
添加依赖到 pom.xml 中
```xml
<dependencies>
    ...
    <dependency>
        <groupId>com.wulai.laiye.javasdk</groupId>
        <artifactId>wulaiSDK</artifactId>
        <version>1.0.3</version>
    </dependency>
</dependencies>
```

## 快速使用
在您开始之前，您需要注册帐户并获取您的[凭证](https://openapi.wul.ai/docs/latest/saas.openapi.v2/openapi.v2.html#section/%E9%89%B4%E6%9D%83%E8%AE%A4%E8%AF%81)。

### 创建 WulaiClient 客户端
```java

public class Test {

    public static void main(String[] args) throws ClientException, ServerException {

        //为避免泄漏公钥密钥等信息，建议将相关配置写到环境变量后使用System类的getenv方法获取环境变量。
        WulaiClient wulaiClient=new WulaiClient(System.getenv("pubkey"),System.getenv("secret"),"v2");
        //设置域名
        wulaiClient.setEndpoint(URI.create("https://openapi.wul.ai/"));
        
    }
}

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
UserCreateRequest userCreateRequest = new UserCreateRequest("laiye@test");
userCreateRequest.setNickname("laiye");
userCreateRequest.setAvatar_url("https://www.laiye.com/static/official-website/logo.png");

// 调用Java API发送请求
int result=wulaiClient.userCreate(userCreateRequest);
System.out.println(result); //httpCode=200


// 创建requestBean对象
Text text =new Text("你是谁");
MsgBody msgBody =new MsgBody(text);
BotResponseRequest botResponseRequest = new BotResponseRequest("laiye@test",msgBody); //创建对象时传入必选参数
botResponseRequest.setExtra("readme"); //set可选参数
BotResponse botResponse = wulaiClient.getBotResponse(botResponseRequest); //得到responseBean

// 获取回复内容
System.out.println(botResponse.getMsgId()); 
System.out.println(botResponse.isDispatch());
for (Object object : botResponse.getSuggestedResponse()) {
    System.out.println(object.toString());
}
```      
### 启用日志功能
SDK默认提供了slf4j对象，在maven中添加相关依赖即可实现日志功能，例如
```xml
       
<dependencies>
    ...
    <dependency>
       <groupId>org.slf4j</groupId>
       <artifactId>slf4j-api</artifactId>
       <version>1.7.28</version>
    </dependency>
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.17</version>
   </dependency>
   <dependency>
       <groupId>org.slf4j</groupId>
       <artifactId>slf4j-log4j12</artifactId>
       <version> 1.7.25</version>
   </dependency>
</dependencies>
```


### 协议说明
```text
网络协议: HTTP

请求方式: POST

请求、响应数据格式: JSON
```