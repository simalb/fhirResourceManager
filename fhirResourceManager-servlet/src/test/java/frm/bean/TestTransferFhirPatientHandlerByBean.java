package frm.bean;

import frm.bean.http.connection.HttpOperationHandler;
import frm.bean.http.connection.ResultHandler;
import frm.bean.utils.exception.HttpURLConnectionFailException;
import frm.bean.utils.json.JsonManager;
import frm.bean.utils.json.objects.Patient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Json handler tester.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestTransferFhirPatientHandlerByBean {

    public static final String TEST_URI = "http://hapi.fhir.org/baseR4/Patient";
    public static final String JSON_OBJECT_EXAMPLE_FILE = "src/test/resources/JsonObjectExample.json";

    public static HttpOperationHandler httpOperationHandlerBean;

    @BeforeClass
    public static void setup()  {
        httpOperationHandlerBean = new HttpOperationHandler();
    }

    @Test
    public void testCreatePatientOnPublicFhirServer() {
        System.out.println("\n*** Starting testCreatePatientOnPublicFhirServer\n");

        try {
            System.out.println("Create a new Patient to the public test server");
            String data = new String(Files.readAllBytes(Paths.get(JSON_OBJECT_EXAMPLE_FILE)));
            TransferFhirPatientHandlerBean transferFhirPatientHandlerBean =  new TransferFhirPatientHandlerBean();
            String responseJson = transferFhirPatientHandlerBean.createPatientOnPublicFhirServer(data);

            Patient createdPatient = JsonManager.getPatientFromJsonObject(responseJson);
            String newPatientId = createdPatient.getId();
            System.out.println("\nVerify Patient creation getting the new Patient from the public test server - id: " + newPatientId);
            ResultHandler checkResultHandler = httpOperationHandlerBean.get(TEST_URI + "/" + newPatientId);
            assertEquals(200, checkResultHandler.getCode());

            System.out.println("\n*** Ending testCreatePatientOnPublicFhirServer\n");

        } catch (HttpURLConnectionFailException e) {
            System.out.println("*** ERROR to be managed!!! - HttpURLConnectionFailException");
        } catch (IOException e) {
            System.out.println("*** ERROR to be managed!!! - IOException");
        }
    }

}
