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

    public static final String TEST_URI = "http://hapi.fhir.org/baseR4/Patient";

    @Inject
    TransferFhirPatientHandlerBean transferFhirPatientHandlerBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("FhirResourceTransfer - doGet");

        try {
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("Welcome to Fhir Resource Transfer Servlet");

            String patientId = request.getPathInfo();
            if(patientId != null) {
                System.out.println("FhirResourceTransfer - patient id to find in the fhir test server: " + patientId);
                boolean success = transferFhirPatientHandlerBean.transferFhirPatient(TEST_URI + patientId);

                if(!success) {
                    response.sendError(404, "Operation failure!");
                }

            }

        } catch (IOException e) {
            System.out.println("TO BE MANAGED - IOException occurred: " + e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("FhirResourceTransfer - doPost");

        response.setContentType("application/json");
        final String requestBody = readRequestBody(request);
        String responseJson= transferFhirPatientHandlerBean.createPatientOnPublicFhirServer(requestBody);
        try {
            response.getWriter().write(responseJson);
        } catch (IOException e) {
            System.out.println("TO BE MANAGED - IOException occurred: " + e.getMessage());
        }
    }

    protected String readRequestBody(final HttpServletRequest request) {

        final StringBuilder stringBuilder = new StringBuilder();

        try (final Stream<String> stream = request.getReader().lines()) {
            stream.forEach(line -> stringBuilder.append(line.trim()));
        } catch (IOException e) {
            System.out.println("TO BE MANAGED - readRequestBody IOException occurred: " + e.getMessage());
        }

        return stringBuilder.toString();
    }
}
