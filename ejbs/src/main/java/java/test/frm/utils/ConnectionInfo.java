package java.test.frm.utils;

import static java.test.frm.utils.Constants.ACCEPT;
import static java.test.frm.utils.Constants.AUTHORIZATION_BASIC;
import static java.test.frm.utils.Constants.CONTENT_TYPE_VALUE;
import static java.test.frm.utils.Constants.PASSWORD;
import static java.test.frm.utils.Constants.USER_NAME;

public class ConnectionInfo {
    private String user = USER_NAME;
    private String password = PASSWORD;
    private String accept = ACCEPT;
    private String contentTypeValue = CONTENT_TYPE_VALUE;
    private String authorization = AUTHORIZATION_BASIC;

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
