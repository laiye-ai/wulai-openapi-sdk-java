package exceptions;

public class Server_Exception extends Exception {
    static String http_code;
    static String error_code;
    static String error_message;
    public Server_Exception(String http_code, String error_code, String error_message) {
        this.http_code=http_code;
        this.error_code=http_code;
        this.error_message=error_message;
    }

    private Server_Exception() {
    }
}
