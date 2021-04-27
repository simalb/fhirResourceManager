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
    //JAVA TEST - 1 READ AND TRANSFER A FHIR RESOURCE
    public boolean transferFhirPatient(String fhirUrl) {

        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        try {
            //Read out patient from fhir server
            ResultHandler resultHandler = httpOperationHandler.get(fhirUrl);
            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());

            System.out.println("getCompletePatientEntity - patient: " + patient.toString());
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, fhirUrl);

            //Create a copy in sql table
            // TODO- STILL NOT WORKING
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
    //JAVA TEST - 2 GET THE COPY
    public String transferredPatient(String fhirUrl) {

        //Get copied patient from sql table - NOT WORKING
        // TODO- STILL NOT WORKING
        PatientPersistenceManager patientPersistenceManager = new PatientPersistenceManager();

        //Convert to Json Object
        String jsonObjectPatient = JsonManager.getJsonObjectFromPatientEntity(patientPersistenceManager.getPatientFromDbTableByUrl(fhirUrl));
        System.out.println("transferedPatient: " + jsonObjectPatient);

        return jsonObjectPatient;
    }

    @Override
    //JAVA TEST - 4 FHIR TEST SERVER
    public String createPatientOnPublicFhirServer(String fhirPatientJson) {
        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        try {
            //Create patient to public fhir test server
            String resultMessage = httpOperationHandler.post(PUBLIC_TEST_SERVER_URI, fhirPatientJson).getResultMessage();
            System.out.println("createPatientOnPublicFhirServer: " + resultMessage);
            return resultMessage;

        } catch (HttpURLConnectionFailException e) {
            System.out.println("TO BE MANAGED - Error occurred: " + e.getMessage());
            return "{}";
        }
    }

}
