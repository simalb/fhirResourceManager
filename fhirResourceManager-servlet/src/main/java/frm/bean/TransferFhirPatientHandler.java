package frm.bean;

public interface TransferFhirPatientHandler {

    String PUBLIC_TEST_SERVER_URI = "http://hapi.fhir.org/baseR4/Patient/";

    //JAVA TEST - 1 READ AND TRANSFER A FHIR RESOURCE
    boolean transferFhirPatient(String fhirUrl);

    //JAVA TEST - 2 GET THE COPY
    String transferredPatient(String fhirUrl);

    //JAVA TEST - 4 FHIR TEST SERVER
    String createPatientOnPublicFhirServer(String fhirPatientJson);
}
