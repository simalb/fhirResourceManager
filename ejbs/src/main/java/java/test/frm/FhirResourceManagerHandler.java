package java.test.frm;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import java.test.frm.exceptions.HttpURLConnectionFailException;
import java.test.frm.utils.prova.HttpOperationHandler;

@Startup
@Singleton
public class FhirResourceManagerHandler {

        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        public static final String TEST_URI= "http://hapi.fhir.org/baseR4/Patient/1854776";

        @PostConstruct
        public void init() {
                System.out.println("*** Starting fhirResourceManager execution.");
                try {
                        httpOperationHandler.post(TEST_URI, null);
                } catch (HttpURLConnectionFailException e) {
                        e.printStackTrace();
                }

        }

        @PreDestroy
        public void stop() {
                System.out.println("*** Ending  fhirResourceManager execution.");
        }

}
