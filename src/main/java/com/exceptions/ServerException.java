package com.exceptions;

public class ServerException extends Exception {

    private static final long serialVersionUID = 7025739208436927199L;
    private int http_code;
    private String errCode;

    private ErrorType errorType;

    public ServerException(String errCode, String errorMsg, int http_code) {
        super(errorMsg);
        this.errCode = errCode;
        this.http_code = http_code;
        this.setErrorType(ErrorType.Server);
    }

    private void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrCode() {
        return errCode;
    }

    public int getHttp_code() {
        return http_code;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
