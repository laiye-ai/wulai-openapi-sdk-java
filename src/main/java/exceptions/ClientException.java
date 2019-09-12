package exceptions;

public class ClientException extends Exception {

    private static final long serialVersionUID = -7312075709491291437L;

    private String errCode;
    private ErrorType errorType;

    public ClientException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.setErrorType(ErrorType.Client);
    }

    public ClientException(String errMsg) {
    }

    public String getErrCode() {
        return errCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    protected void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }


}
