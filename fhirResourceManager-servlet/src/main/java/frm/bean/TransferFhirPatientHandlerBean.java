package frm.bean;

import frm.bean.persistence.PatientPersistenceManagerBean;
import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.http.connection.HttpOperationHandlerBean;
import frm.bean.utils.json.JsonManager;

import frm.bean.utils.json.objects.Patient;
import frm.bean.http.connection.ResultHandler;
import frm.bean.utils.exception.HttpURLConnectionFailException;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TransferFhirPatientHandlerBean implements TransferFhirPatientHandler {

    @Inject
    HttpOperationHandlerBean httpOperationHandlerBean;

    @Inject
    PatientPersistenceManagerBean patientPersistenceManagerBean;

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

        try {
            ResultHandler resultHandler = httpOperationHandlerBean.get(fhirUrl);
            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());

            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, fhirUrl);
            patientPersistenceManagerBean.createPatient(patientEntity);

            return true;

        } catch (HttpURLConnectionFailException e) {
            System.out.println("TO BE MANAGED - Error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String transferedPatient(String fhirUrl) {
        return JsonManager.getJsonObjectFromPatientEntity(patientPersistenceManagerBean.getPatientFromDbTableByUrl(fhirUrl));
    }

    @Override
    public String createPatientOnPublicFhirServer(String fhirPatientJson) {
        try {
            return httpOperationHandlerBean.post(PUBLIC_TEST_SERVER_URI, fhirPatientJson).getResultMessage();
        } catch (HttpURLConnectionFailException e) {
            System.out.println("TO BE MANAGED - Error occurred: " + e.getMessage());
            return "{}";
        }
    }

}
