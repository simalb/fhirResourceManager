package frm.bean;

import frm.bean.persistence.PatientPersistenceManager;
import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.utils.http.connection.HttpOperationHandler;
import frm.bean.utils.json.utils.JsonManager;

import frm.bean.utils.json.objects.Patient;
import frm.bean.utils.http.connection.ResultHandler;
import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TransferFhirPatientHandlerBean implements TransferFhirPatientHandler {

    @Inject
    HttpOperationHandler httpOperationHandler;

    @Inject
    PatientPersistenceManager patientPersistenceManager;

    @PostConstruct
    public void init() {
        System.out.println("*** Starting fhirResourceManager execution.");
    }

    @PreDestroy
    public void stop() {
        System.out.println("*** Ending  fhirResourceManager execution.");
    }

    @Override
    public void transferFhirPatientFromFhirServerToDB(String fhirUrl) {

        try {
            ResultHandler resultHandler = httpOperationHandler.get(fhirUrl);
            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());

            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, fhirUrl);
            patientPersistenceManager.createPatient(patientEntity);

        } catch (HttpURLConnectionFailException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getJsonFhirPatientFromDB(String fhirUrl) {
        return JsonManager.getJsonObjectFromPatientEntity(patientPersistenceManager.getPatientFromUrl(fhirUrl));
    }

}
