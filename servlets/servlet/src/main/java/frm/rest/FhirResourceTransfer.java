package frm.rest;

import javax.inject.Inject;
import javax.ws.rs.*;

import frm.bean.TransferFhirPatientHandlerBean;

@Path(value="/fhir-resource")
public class FhirResourceTransfer {

    //@Inject
    //TransferFhirPatientHandlerBean transferFhirPatientHandlerBean;

    @GET
    public String getFhirResource() {

        return "Hello by FhirResourceTransfer";
    }

    @GET
    @Path(value="{id}")
    @Produces(value="application/json")
    public void getFhirResource(@PathParam("id") String id) {
        TransferFhirPatientHandlerBean transferFhirPatientHandlerBean = new TransferFhirPatientHandlerBean();
        transferFhirPatientHandlerBean.getFhirPatientFromFhirServer(id);
    }

    @POST
    @Consumes(value="application/json")
    public String createFhirResource(String json) {
        TransferFhirPatientHandlerBean transferFhirPatientHandlerBean = new TransferFhirPatientHandlerBean();
        return transferFhirPatientHandlerBean.createFhirPatientOnFhirServer(json);
    }
}
