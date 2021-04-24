package frm.rest;

import javax.ws.rs.*;

@Path("/fhir-resource/")
public class FhirResourceTransferResource {

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getFhirResource(@PathParam("id") String id) {
        return "Hello, World!";
    }

    @POST
    @Consumes("application/json")
    public String createFhirResource() {
        return "Hello, World!";
    }
}
