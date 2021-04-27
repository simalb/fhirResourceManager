package frm.bean;

import frm.bean.persistence.PatientPersistenceManager;
import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.http.connection.HttpOperationHandler;
import frm.bean.utils.json.JsonManager;

import frm.bean.utils.json.objects.Patient;
import frm.bean.http.connection.ResultHandler;
import frm.bean.utils.exception.HttpURLConnectionFailException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless
public class TransferFhirPatientHandlerBean implements TransferFhirPatientHandler {

    @PostConstruct
    public void init() {
        System.out.println("*** Starting fhirResourceManager execution.");

    }

    @PreDestroy
    public void stop() {
        System.out.println("*** Ending  fhirResourceManager execution.");
    }

    @Override
    public boolean transferFhirPatient(String fhirUrl) {

        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        try {
            ResultHandler resultHandler = httpOperationHandler.get(fhirUrl);
            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());

            System.out.println("getCompletePatientEntity - patient: " + patient.toString());
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, fhirUrl);

            System.out.println("\n *** STILL NOT WORKING - persist patient on DB *** \n");
            PatientPersistenceManager patientPersistenceManager = new PatientPersistenceManager();
            patientPersistenceManager.createPatient(patientEntity);

            return true;

        } catch (HttpURLConnectionFailException e) {
            System.out.println("TO BE MANAGED - Error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String transferedPatient(String fhirUrl) {

        PatientPersistenceManager patientPersistenceManager = new PatientPersistenceManager();
        String jsonObjectPatient = JsonManager.getJsonObjectFromPatientEntity(patientPersistenceManager.getPatientFromDbTableByUrl(fhirUrl));
        System.out.println("transferedPatient: " + jsonObjectPatient);

        return jsonObjectPatient;
    }

    @Override
    public String createPatientOnPublicFhirServer(String fhirPatientJson) {
        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        try {
            String resultMessage = httpOperationHandler.post(PUBLIC_TEST_SERVER_URI, fhirPatientJson).getResultMessage();
            System.out.println("createPatientOnPublicFhirServer: " + resultMessage);
            return resultMessage;

        } catch (HttpURLConnectionFailException e) {
            System.out.println("TO BE MANAGED - Error occurred: " + e.getMessage());
            return "{}";
        }
    }

}
