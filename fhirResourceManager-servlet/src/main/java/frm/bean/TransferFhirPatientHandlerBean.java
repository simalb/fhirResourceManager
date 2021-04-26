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
        PatientPersistenceManager patientPersistenceManager = new PatientPersistenceManager();

        try {
            ResultHandler resultHandler = httpOperationHandler.get(fhirUrl);
            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());

            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, fhirUrl);
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

        return JsonManager.getJsonObjectFromPatientEntity(patientPersistenceManager.getPatientFromDbTableByUrl(fhirUrl));
    }

    @Override
    public String createPatientOnPublicFhirServer(String fhirPatientJson) {
        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        try {
            System.out.println("createPatientOnPublicFhirServer");
            return httpOperationHandler.post(PUBLIC_TEST_SERVER_URI, fhirPatientJson).getResultMessage();
        } catch (HttpURLConnectionFailException e) {
            System.out.println("TO BE MANAGED - Error occurred: " + e.getMessage());
            return "{}";
        }
    }

}
