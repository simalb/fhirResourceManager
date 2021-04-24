package frm.bean;

import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.utils.http.connection.HttpOperationHandler;
import frm.bean.utils.http.connection.ResultHandler;
import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;
import frm.bean.utils.json.objects.Patient;
import frm.bean.utils.json.utils.JsonManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import javax.inject.Inject;


/**
 * The type Json handler tester.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestFhirResourceManagerAgainstPublicTestServer {

    public static final String TEST_URI = "http://hapi.fhir.org/baseR4/Patient";
    public static final String TEST_ID = "/1854776";
    public static final String GET_URI = TEST_URI + TEST_ID;
    public static final String EXPECTED_JSON_STRING = "{\"internalId\":22,\"url\":\"http://hapi.fhir.org/baseR4/Patient/1854776\",\"creationDate\":\"Jan 1, 2016 12:00:00 AM\",\"family\":\"Newman\",\"given\":[\"Simon\",\"Paul\"],\"prefix\":\"\",\"suffix\":\"\",\"gender\":\"male\",\"birthDate\":\"1998-03-17\"}";
    public static final String JSON_OBJECT_EXAMPLE_FILE = "src/test/resources/JsonObjectExample.json";
    public static final String RESPONSE_JSON_OBJECT_EXAMPLE_JSON ="src/test/resources/ResponseJsonObjectExample.json";

    public static HttpOperationHandler httpOperationHandler;

    /*@Inject
    private HttpOperationHandler httpOperationHandler;*/

    @BeforeClass
    public static void setup()  {
        httpOperationHandler  = new HttpOperationHandler();
    }

    @Test
    public void testGetPatientFromTestServerAndConvertMethods() {
        System.out.println("\n*** Starting testGetPatientFromTestServerAndConvertMethods\n");

        try {
            System.out.println("Getting the already present Patient from the public test server - id " + TEST_ID);
            ResultHandler getResultHandler = httpOperationHandler.get(GET_URI);
            assertEquals(200, getResultHandler.getCode());

            System.out.println("Convert provided Patient from json to java class");
            Patient patient = JsonManager.getPatientFromJsonObject(getResultHandler.getResultMessage());
            assertEquals("Newman", patient.getName().get(0).getFamily());

            System.out.println("Convert Patient java class in PatientEntity to store in the DB");
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, TEST_URI+TEST_ID);
            assertEquals(GET_URI, patientEntity.getUrl());

            System.out.println("Complete PatientEntity as provided by DB and convert into JsonObject format");

            patientEntity.setInternalId(22);

            String myDateStr="01/01/16";
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            Date myDate = dateFormat.parse(myDateStr);
            patientEntity.setCreationDate(myDate);

            String jsonObject = JsonManager.getJsonObjectFromPatientEntity(patientEntity);

            assertEquals(EXPECTED_JSON_STRING, jsonObject);

            System.out.println("\n*** Ending testGetPatientFromTestServerAndConvertMethods\n");

        } catch (HttpURLConnectionFailException | ParseException e) {
            System.out.println("*** ERROR to be managed!!! - testGetPatientFromTestServerAndConvertMethods");
            e.printStackTrace();
        }
    }

    @Test
    public void testPostPatientToTestServer() {
        System.out.println("\n*** Starting testPostPatientToTestServer\n");

        try {
            System.out.println("Create a new Patient to the public test server");
            String data = new String(Files.readAllBytes(Paths.get(JSON_OBJECT_EXAMPLE_FILE)));
            ResultHandler postResultHandler = httpOperationHandler.post(TEST_URI, data);
            assertEquals(201, postResultHandler.getCode());


            Patient createdPatient = JsonManager.getPatientFromJsonObject(postResultHandler.getResultMessage());
            String newPatientId = createdPatient.getId();
            System.out.println("Verify Patient creation getting the new Patient from the public test server - id: " + newPatientId);
            ResultHandler checkResultHandler = httpOperationHandler.get(TEST_URI + "/" + newPatientId);
            assertEquals(200, checkResultHandler.getCode());

            System.out.println("\n*** Ending testPostPatientToTestServer\n");

        } catch (HttpURLConnectionFailException | IOException e) {
            System.out.println("*** ERROR to be managed!!! - testPostPatientToTestServer");
            e.printStackTrace();
        }
    }

}
