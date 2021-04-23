package frm.bean.persistence;

import frm.bean.persistence.entity.PatientEntity;
import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Startup
@Singleton
public class PatientTransferManager {

    private static final String PERSISTENCE_UNIT_NAME = "PatientTransferService";
    private static EntityManagerFactory factory;

    private EntityManager em;

    @PostConstruct
    public void init() {
        System.out.println("*** Starting  PatientTransferManager execution.");

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    @PreDestroy
    public void stop() {
        em.close();

        System.out.println("*** Ending  PatientTransferManager execution.");
    }

    public void createPatient(PatientEntity patient)  {
        // create new patient
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    public PatientEntity getPatient(int internalId)  {
        return em.getReference(PatientEntity.class, internalId);
    }

    public List<PatientEntity> getAllPatients()  {
        // read the existing entries and write to console
        Query q = em.createQuery("SELECT p FROM PatientEntity p");
        List<PatientEntity> patientList = q.getResultList();
        for (PatientEntity patient : patientList) {
            System.out.println(patient);
        }
        System.out.println("Size: " + patientList.size());
        return patientList;
    }

}
