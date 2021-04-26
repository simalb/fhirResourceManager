package frm.bean.http.connection;

import frm.bean.utils.exception.HttpURLConnectionFailException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpOperationHandler {

    public synchronized ResultHandler post(final String uri, final String data) throws HttpURLConnectionFailException {

        return doOperation(uri, "POST", data);
    }

    public synchronized ResultHandler get(final String uri) throws HttpURLConnectionFailException {

        return doOperation(uri, "GET", null);
    }

    public synchronized ResultHandler doOperation(final String uri, final String httpCommand, final String data) throws HttpURLConnectionFailException {
        ResultHandler resultHandler = new ResultHandler();

        try {
            HttpURLConnection conn = prepareConnection(uri, httpCommand);

            if (data != null) {
                System.out.println("Request body " + data);

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
            System.out.println("Response body " + resultHandler.getResultMessage());

            br.close();
            conn.disconnect();

        } catch (final MalformedURLException e) {
            System.out.println("HttpOperationHandler MalformedURLException exception");
            throw new HttpURLConnectionFailException(e.getMessage());
        } catch (final IOException e) {
            System.out.println("HttpOperationHandler IOException exception");
            throw new HttpURLConnectionFailException(e.getMessage());
        }

        return resultHandler;
    }

    public HttpURLConnection prepareConnection (final String uri,
                                                final String httpCommand) throws IOException{

        final URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(httpCommand);

        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-type", "application/json");

        return conn;
    }

}
