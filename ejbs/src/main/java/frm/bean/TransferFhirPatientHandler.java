package frm.bean;

public interface TransferFhirPatientHandler {

    public static final String PUBLIC_TEST_SERVER_URI = "http://hapi.fhir.org/baseR4/Patient/";

    public boolean transferFhirPatientFromFhirServerToDB(String fhirUrl);

    public String getJsonFhirPatientFromDB(String fhirUrl);

    public String getFhirPatientFromFhirServer(String id);

    public String createFhirPatientOnFhirServer(String fhirPatientJson);
}
