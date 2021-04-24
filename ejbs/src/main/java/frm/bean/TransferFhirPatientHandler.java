package frm.bean;

import frm.bean.utils.json.objects.Patient;

public interface TransferFhirPatientHandler {

    public static final String PUBLIC_TEST_SERVER_URI = "http://hapi.fhir.org/baseR4/Patient/";

    public void transferFhirPatientFromFhirServerToDB(String fhirUrl);

    public String getJsonFhirPatientFromDB(String fhirUrl);

    public String getFhirPatientFromFhirServer(String id);

    public String createFhirPatientOnFhirServer(String fhirPatientJson);
}
