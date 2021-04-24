package frm.bean.utils.http.connection;

import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

@Stateless
public class HttpOperationHandler {
    protected HttpConnectionHandler httpConnectionHandler;

    public HttpOperationHandler() {
        this.httpConnectionHandler = new HttpConnectionHandler();
    }

    public synchronized ResultHandler post(final String uri, final String data) throws HttpURLConnectionFailException {

        return doOperation(uri, "POST", data);
    }

    public synchronized ResultHandler get(final String uri) throws HttpURLConnectionFailException {

        return doOperation(uri, "GET", null);
    }


    public synchronized ResultHandler doOperation(final String uri, final String httpCommand, final String data) throws HttpURLConnectionFailException {
        ResultHandler resultHandler = new ResultHandler();

        try {
            HttpURLConnection conn = httpConnectionHandler.prepareConnection(uri, httpCommand);

            if (data != null) {
                conn.setDoOutput(true);
                conn.getOutputStream().write(data.getBytes());
                conn.getOutputStream().flush();
            }

            int code = conn.getResponseCode();
            resultHandler.setCode(code);

            if(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED){
                System.out.println("HttpOperationHandler doOperation response code OK " + code + " " + httpCommand + " " + uri);
            }
            else {
                if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println("HttpOperationHandler doOperation response code not found " + code + " " + httpCommand + " " + uri);
                    resultHandler.setResultMessage("Not found");
                }
                else {
                    System.out.println("HttpOperationHandler doOperation response code not OK " + code + " " + httpCommand + " " + uri);
                    resultHandler.setResultMessage("Not ok");
                    throw new HttpURLConnectionFailException("HTTP Status-Code" + conn.getResponseMessage());
                }

                return resultHandler;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String response = "";
            while ((output = br.readLine()) != null) {
                response = response.concat(output);
            }
            resultHandler.setResultMessage(response);

            br.close();
            conn.disconnect();

        } catch (final MalformedURLException e) {
            //logger.error("HttpOperationHandler MalformedURLException exception {}", e);
            throw new HttpURLConnectionFailException(e.getMessage());
        } catch (final IOException e) {
            //logger.warn("HttpOperationHandler IOException exception {}", e);
            throw new HttpURLConnectionFailException(e.getMessage());
        }

        //logger.trace("HttpOperationHandler doOperation result {}", resultHandler.toString());

        return resultHandler;
    }

}
