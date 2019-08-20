package exceptions;

public class ServerException extends ClientException {
    private static final long serialVersionUID = 7025739208436927199L;
    private int http_code;

    public ServerException(String error_code, String error_message, int http_code) {
        super(error_code, error_message);
        setHttp_code(http_code);
        this.setErrorType(ErrorType.Server);
    }

    public int getHttp_code() {
        return http_code;
    }

    public void setHttp_code(int http_code) {
        this.http_code = http_code;
    }


}
