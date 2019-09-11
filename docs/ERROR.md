在使用wulaiSDK for Java时，当服务端或者SDK端出错时，SDK会返回相应的异常信息。这些异常信息中会包含具体的错误信息，包括错误码（Error Code）和错误消息（Error Message）。

一般情况下，您不需要对wulaiSDK for Java返回的错误进行处理。您只需要根据服务端的错误提示处理服务端错误即可。

* ServerException 是服务端错误
* ClientException 是客户端错误

