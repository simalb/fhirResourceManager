package frm.rest;

import javax.inject.Inject;
import javax.ws.rs.*;

import frm.bean.TransferFhirPatientHandlerBean;

@Path("/fhir-resource/")
public class FhirResourceTransfer {

    @Inject
    TransferFhirPatientHandlerBean transferFhirPatientHandlerBean;

    @GET
    public String getFhirResource() {
        return "Hello by FhirResourceTransfer";
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public void getFhirResource(@PathParam("id") String id) {
        transferFhirPatientHandlerBean.getFhirPatientFromFhirServer(id);
    }

    @POST
    @Consumes("application/json")
    public String createFhirResource(String json) {
        return transferFhirPatientHandlerBean.createFhirPatientOnFhirServer(json);
    }
}
