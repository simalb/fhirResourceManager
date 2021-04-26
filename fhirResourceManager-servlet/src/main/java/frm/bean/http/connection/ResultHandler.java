package frm.bean.http.connection;

public class ResultHandler {
    private String resultMessage;
    private int code = 0;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    //HTTP_CREATED = 201
    //HTTP_OK = 200
    public boolean isResultOk() {
        return (code == 200) || (code == 201);
    }

    //HTTP_NOT_FOUND = 404
    public boolean isResultNotFound() {
        return code == 404;
    }

    public String toString(){
        return resultMessage + " - response code: " + code;
    }
}
