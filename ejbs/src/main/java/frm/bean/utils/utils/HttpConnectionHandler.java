package frm.bean.utils.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionHandler {

    public static final String CONTENT_TYPE_VALUE = "application/json";
    public static final String CONTENT_TYPE = "Content-type";
    public static final String AUTHORIZATION_BASIC = "Basic";
    public static final String AUTHORIZATION = "Authorization";

    public HttpURLConnection prepareConnection (final String uri,
                                               final String httpCommand) throws IOException{
        HttpURLConnection conn = null;

        final URL url = new URL(uri);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(httpCommand);

        ConnectionInfo connectionInfo = new ConnectionInfo();

        String accept = connectionInfo.getAccept();
        conn.setRequestProperty(accept, connectionInfo.getContentTypeValue());

        String contentType = connectionInfo.getContentTypeValue();
        conn.setRequestProperty(CONTENT_TYPE, contentType);

        //final String username = connectionInfo.getUser();
        //final String password = connectionInfo.getPassword();

        //final String auth = username.concat(":").concat(password);
        //final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        //final String authorization = connectionInfo.getAuthorization();
        //final String authHeader = authorization.concat(" ").concat(new String(encodedAuth));

        //conn.setRequestProperty(AUTHORIZATION, authHeader);

        /*logger.trace("HttpConnectionHandler prepareConnection uri {}", uri);
        logger.trace("HttpConnectionHandler prepareConnection accept {}", accept);
        logger.trace("HttpConnectionHandler prepareConnection contentType {}", contentType);
        logger.trace("HttpConnectionHandler prepareConnection username {}", username);
        logger.trace("HttpConnectionHandler prepareConnection password {}", password);
        logger.trace("HttpConnectionHandler prepareConnection authorization {}", authorization);
        logger.trace("HttpConnectionHandler prepareConnection authHeader {}", authHeader);*/

        return conn;
    }
}
