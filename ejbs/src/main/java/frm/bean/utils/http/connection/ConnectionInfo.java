package frm.bean.utils.http.connection;

public class ConnectionInfo {
    private String user = Constants.USER_NAME;
    private String password = Constants.PASSWORD;
    private String accept = Constants.ACCEPT;
    private String contentTypeValue = Constants.CONTENT_TYPE_VALUE;
    private String authorization = Constants.AUTHORIZATION_BASIC;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getContentTypeValue() {
        return contentTypeValue;
    }

    public void setContentTypeValue(String contentTypeValue) {
        this.contentTypeValue = contentTypeValue;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
