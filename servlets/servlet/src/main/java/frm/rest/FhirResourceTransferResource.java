package frm.rest;

import javax.inject.Inject;
import javax.ws.rs.*;

import frm.bean.TransferFhirPatientHandlerBean;

@Path("/fhir-resource/")
public class FhirResourceTransferResource {

    @Inject
    TransferFhirPatientHandlerBean transferFhirPatientHandlerBean;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getFhirResource(@PathParam("id") String id) {
        return transferFhirPatientHandlerBean.getFhirPatientFromFhirServer(id);
    }

    @POST
    @Consumes("application/json")
    public String createFhirResource() {
        return "Hello, World!";
    }
}
