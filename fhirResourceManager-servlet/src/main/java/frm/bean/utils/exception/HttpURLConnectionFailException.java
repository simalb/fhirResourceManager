package frm.bean.utils.exception;

public class HttpURLConnectionFailException extends Exception {

    public HttpURLConnectionFailException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
