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
public class HttpOperationHandlerTest {

    public static final String TEST_URI = "http://hapi.fhir.org/baseR4/Patient/1854776";
    public static final String EXPECTED_JSON_STRING = "{\"internalId\":22,\"url\":\"http://hapi.fhir.org/baseR4/Patient/1854776\",\"creationDate\":\"Jan 1, 2016 12:00:00 AM\",\"family\":\"Newman\",\"given\":[\"Simon\",\"Paul\"],\"prefix\":\"\",\"suffix\":\"\",\"gender\":\"male\",\"birthDate\":\"1998-03-17\"}";

    /*@Inject
    private HttpOperationHandler httpOperationHandler;*/

    @Test
    public void testGet() {
        System.out.println("*** Starting testGet");
        try {
            HttpOperationHandler httpOperationHandler = new HttpOperationHandler();
            ResultHandler resultHandler = httpOperationHandler.get(TEST_URI);

            Patient patient = JsonManager.getPatientFromJsonObject(resultHandler.getResultMessage());
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, TEST_URI);

            assertEquals(TEST_URI, patientEntity.getUrl());

            patientEntity.setInternalId(22);
            String myDateStr="01/01/16";
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            Date myDate = dateFormat.parse(myDateStr);

            patientEntity.setCreationDate(myDate);

            String jsonObject = new Gson().toJson(ConverterUtility.convertPatientEntityToPatientJsonObject(patientEntity));
            //String jsonObject = JsonManager.getJsonObjectFromPatientEntity(patientEntity);
            assertEquals(EXPECTED_JSON_STRING, jsonObject);



            System.out.println("*** Ending testGet");

        } catch (HttpURLConnectionFailException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
