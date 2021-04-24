package frm.bean;

import frm.bean.utils.http.connection.HttpOperationHandler;
import frm.bean.utils.json.utils.JsonManager;

import frm.bean.utils.json.objects.Patient;
import frm.bean.utils.http.connection.ResultHandler;
import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.Stateless;

@Stateless
public class FhirResourceManagerHandler {

        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        public static final String TEST_URI= "http://hapi.fhir.org/baseR4/Patient/1854776";

        @PostConstruct
        public void init() {
                System.out.println("*** Starting fhirResourceManager execution.");
                try {
                        ResultHandler resultHandler = httpOperationHandler.get(TEST_URI);

                        Patient patient = JsonManager.getPatientFromJson(resultHandler.getResultMessage());
                } catch (HttpURLConnectionFailException e) {
                        e.printStackTrace();
                }

        }

        @PreDestroy
        public void stop() {
                System.out.println("*** Ending  fhirResourceManager execution.");
        }

}
