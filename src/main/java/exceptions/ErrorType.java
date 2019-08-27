package exceptions;

public enum ErrorType {
    // 客户端异常
    Client,

    // 服务端异常
    Server,

    // 限流
    Throttling,

    // default
    Unknown,
}

