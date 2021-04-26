package frm.application;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

import frm.bean.TransferFhirPatientHandlerBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FhirResourceTransfer extends HttpServlet {

    @Inject
    TransferFhirPatientHandlerBean transferFhirPatientHandlerBean;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("Welcome to Fhir Resource Transfer Servlet");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        String responseJson= transferFhirPatientHandlerBean.createPatientOnPublicFhirServer(readRequestBody(request));
        response.getWriter().write(responseJson);

    }

    protected String readRequestBody(final HttpServletRequest request) {

        final StringBuilder stringBuilder = new StringBuilder();

        try (final Stream<String> stream = request.getReader().lines()) {
            stream.forEach(line -> stringBuilder.append(line.trim()));
        } catch (IOException e) {
            System.out.println("TO BE MANAGED - readRequestBody error occurred: " + e.getMessage());
        }

        return stringBuilder.toString();
    }
}
