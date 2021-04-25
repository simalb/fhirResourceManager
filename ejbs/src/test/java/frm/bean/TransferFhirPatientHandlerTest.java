package frm.bean;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

//import javax.inject.Inject;


/**
 * The type Json handler tester.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferFhirPatientHandlerTest {

    public static final String TEST_URI= "http://hapi.fhir.org/baseR4/Patient/1854776";

    /*@Inject
    private HttpOperationHandler httpOperationHandler;*/


    @Test
    public void testTransferFhirPatientFromFhirServerToDB() {
        /*System.out.println("*** Starting testGet");
        TransferFhirPatientHandlerBean transferFhirPatientHandlerBean = new TransferFhirPatientHandlerBean();
            transferFhirPatientHandlerBean.transferFhirPatientFromFhirServerToDB(TEST_URI);
            /*HttpOperationHandler httpOperationHandler = new HttpOperationHandler();
            ResultHandler resultHandler = httpOperationHandler.get(TEST_URI);

            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, TEST_URI);*/

            /*System.out.println("*** Ending testGet");*/
    }
}
