package frm.bean.utils.exception;


public class HttpURLConnectionFailException extends Exception {

    public HttpURLConnectionFailException(String errorMessage) {
        super(errorMessage);
    }

    public String toString() {
        return getMessage();
    }
}
