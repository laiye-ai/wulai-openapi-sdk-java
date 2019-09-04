package exceptions;

public class ServerException extends Exception {

    private static final long serialVersionUID = 7025739208436927199L;
    private int http_code;
    private String errCode;
    private ErrorType errorType;

    public ServerException(String errCode, String errorMsg, int http_code) {
        super(errCode+errorMsg);
        this.errCode=errCode;
        this.http_code=http_code;
        this.setErrorType(ErrorType.Server);
    }

    private void setErrorType(ErrorType errorType) {
        this.errorType=errorType;
    }

}
