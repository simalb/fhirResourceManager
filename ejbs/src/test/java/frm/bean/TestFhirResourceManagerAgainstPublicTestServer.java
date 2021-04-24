package frm.bean;


import com.google.gson.Gson;
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

import javax.xml.crypto.Data;

import java.io.IOException;
import java.io.Reader;
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
    public static final String EXPECTED_JSON_STRING = "{\"internalId\":22,\"url\":\"http://hapi.fhir.org/baseR4/Patient/1854776\",\"creationDate\":\"Jan 1, 2016 12:00:00 AM\",\"family\":\"Newman\",\"given\":[\"Simon\",\"Paul\"],\"prefix\":\"\",\"suffix\":\"\",\"gender\":\"male\",\"birthDate\":\"1998-03-17\"}";
    public static final String JSON_OBJECT_EXAMPLE_FILE = "src/test/resources/JsonObjectExample.json";
    public static final String RESPONSE_JSON_OBJECT_EXAMPLE_JSON ="src/test/resources/ResponseJsonObjectExample.json";


    /*@Inject
    private HttpOperationHandler httpOperationHandler;*/

    @Test
    public void testGet() {
        System.out.println("*** Starting testGet");
        try {
            HttpOperationHandler httpOperationHandler = new HttpOperationHandler();
            ResultHandler getResultHandler = httpOperationHandler.get(TEST_URI+TEST_ID);
            assertEquals(200, getResultHandler.getCode());

            Patient patient = JsonManager.getPatientFromJsonObject(getResultHandler.getResultMessage());
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, TEST_URI+TEST_ID);

            assertEquals(TEST_URI+TEST_ID, patientEntity.getUrl());

            patientEntity.setInternalId(22);
            String myDateStr="01/01/16";
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            Date myDate = dateFormat.parse(myDateStr);

            patientEntity.setCreationDate(myDate);

            String jsonObject = new Gson().toJson(ConverterUtility.convertPatientEntityToPatientJsonObject(patientEntity));
            //String jsonObject = JsonManager.getJsonObjectFromPatientEntity(patientEntity);

            assertEquals(EXPECTED_JSON_STRING, jsonObject);

            // create a new patient
            String data = new String(Files.readAllBytes(Paths.get(JSON_OBJECT_EXAMPLE_FILE)));
            ResultHandler postResultHandler = httpOperationHandler.post(TEST_URI, data);
            assertEquals(201, postResultHandler.getCode());


            Patient createdPatient = JsonManager.getPatientFromJsonObject(postResultHandler.getResultMessage());
            ResultHandler checkResultHandler = httpOperationHandler.get(TEST_URI + "/" + createdPatient.getId());
            assertEquals(200, checkResultHandler.getCode());

            System.out.println("*** Ending testGet");

        } catch (HttpURLConnectionFailException | ParseException | IOException e) {
            System.out.println("*** ERROR to be managed!!!");
            e.printStackTrace();
        }
    }

}
