package frm.bean;

import frm.bean.utils.json.objects.Patient;

public interface TransferFhirPatientHandler {

    public void transferFhirPatientFromFhirServerToDB(String fhirUrl);

    public String getJsonFhirPatientFromDB(String fhirUrl);
}
