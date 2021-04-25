package frm.application;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;

import frm.bean.TransferFhirPatientHandlerBean;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Path("/fhir-resource")
public class FhirResourceTransfer extends HttpServlet {

    //@Inject
    //TransferFhirPatientHandlerBean transferFhirPatientHandlerBean;

    @GET
    public String getFhirResource() {

        return "Hello by FhirResourceTransfer";
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public void getFhirResource(@PathParam("id") String id) {
        TransferFhirPatientHandlerBean transferFhirPatientHandlerBean = new TransferFhirPatientHandlerBean();
        transferFhirPatientHandlerBean.getFhirPatientFromFhirServer(id);
    }

    @POST
    @Consumes("application/json")
    public String createFhirResource(String json) {
        TransferFhirPatientHandlerBean transferFhirPatientHandlerBean = new TransferFhirPatientHandlerBean();
        return transferFhirPatientHandlerBean.createFhirPatientOnFhirServer(json);
    }

    /*public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Sample Application Servlet Page</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=white>");

        writer.println("<table border=\"0\" cellpadding=\"10\">");
        writer.println("<tr>");
        writer.println("<td>");
        writer.println("<img src=\"images/Pivotal_Logo.png\">");
        writer.println("</td>");
        writer.println("<td>");
        writer.println("<h1>Sample Application Servlet</h1>");
        writer.println("</td>");
        writer.println("</tr>");
        writer.println("</table>");

        writer.println("This is the output of a servlet that is part of");
        writer.println("the Hello, World application.");

        writer.println("</body>");
        writer.println("</html>");
    }*/
}
