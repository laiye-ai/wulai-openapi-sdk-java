package com.exceptions;

public class ClientException extends Exception {

    private String errCode;
    private ErrorType errorType;

    public ClientException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.setErrorType(ErrorType.Client);
    }

    public String getErrCode() {
        return errCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    private void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }


}
