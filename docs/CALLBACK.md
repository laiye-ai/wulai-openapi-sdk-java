##消息路由

消息路由即 Webhook ，该 Web 服务需要开发者自己搭建，并且需要遵从开放平台预先定义好的输入输出。机器人每次响应，吾来会把机器人回复内容、对话解析结果传给消息路由，调用方可以按需使或修改消息体内容达到影响机器人回复的目的。


##### REQUEST BODY SCHEMA: application/json
|类型|字段名称|长度|是否必须|
| --- | --- | --- | --- |
|Object[]| suggested_response|-| true |
|String |user_id|[1..128]|true|
|String |extra| [0..1024]||
|String |msg_id|[1..18]|true|
|Boolean| is_dispatch| - |true|
|String |msg_ts| [1..]|true|
|Object |msg_body|-|true|
|String |nickname|[1..128] |true|

##### RESPONSE BODY SCHEMA: application/json
|类型|字段名称|
| --- | --- | 
|Boolean|is_dispatch|  
|Object[]|suggested_response| 
