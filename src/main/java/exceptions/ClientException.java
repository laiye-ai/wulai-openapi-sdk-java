package exceptions;

public class ClientException extends Exception {

    private static final long serialVersionUID = -7312075709491291437L;

    private String errCode;
    private String errMsg;
    private ErrorType errorType;

    public ClientException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.setErrorType(ErrorType.Client);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    protected void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
