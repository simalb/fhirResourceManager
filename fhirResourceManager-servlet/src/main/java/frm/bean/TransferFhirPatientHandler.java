package frm.bean;

public interface TransferFhirPatientHandler {

    public static final String PUBLIC_TEST_SERVER_URI = "http://hapi.fhir.org/baseR4/Patient/";

    //JAVA TEST - 1 READ AND TRANSFER A FHIR RESOURCE
    public boolean transferFhirPatient(String fhirUrl);

    //2 GET THE COPY
    public String transferedPatient(String fhirUrl);

    //4 FHIR TEST SERVER
    public String createPatientOnPublicFhirServer(String fhirPatientJson);
}
