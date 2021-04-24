package frm.bean;


import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.utils.http.connection.HttpOperationHandler;
import frm.bean.utils.http.connection.ResultHandler;
import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;
import frm.bean.utils.json.objects.Patient;
import frm.bean.utils.json.utils.JsonManager;
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
