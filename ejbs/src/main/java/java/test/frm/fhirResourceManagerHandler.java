package java.test.frm;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import java.test.frm.utils.HttpOperationHandler;

@Startup
@Singleton
public class fhirResourceManagerHandler {

        HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

        public static final String TEST_URI= "http://hapi.fhir.org/baseR4/Patient/1854776";

        @PostConstruct
        public void init() {
                System.out.println("*** Starting fhirResourceManager execution.");
                httpOperationHandler.post(TEST_URI, null);

        }

        @PreDestroy
        public void stop() {
                System.out.println("*** Ending  fhirResourceManager execution.");
        }

}
