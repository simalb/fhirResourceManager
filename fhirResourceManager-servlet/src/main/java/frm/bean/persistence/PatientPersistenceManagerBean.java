package frm.bean.persistence;

import frm.bean.persistence.entity.PatientEntity;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Startup
@Singleton
@Local
public class PatientPersistenceManagerBean {

    @PersistenceContext
    private static EntityManager em;

    //@PersistenceUnit
    //private EntityManagerFactory emf;

    //private EntityManager em;

    @PostConstruct
    public void init() {
        System.out.println("*** Starting  PatientTransferManager execution.");
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("PatientTransferService");
        //em = factory.createEntityManager();

        //h2 native query to show tables and columns
        runNativeQuery("SHOW TABLES");
        runNativeQuery("SHOW COLUMNS from Patients");
    }

    @PreDestroy
    public void stop() {
        em.close();

        System.out.println("*** Ending  PatientTransferManager execution.");
    }

    public void createPatient(PatientEntity patient)  {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();

        System.out.println("New patient created: " + patient.toString());
    }

    public PatientEntity getPatient(int internalId)  {
        return em.getReference(PatientEntity.class, internalId);
    }

    public PatientEntity getPatientFromDbTableByUrl(@NotNull final String url) {
        System.out.println("Requested patient from DB: " + url);
        TypedQuery<PatientEntity> q = em.createQuery("select p from PatientEntity p where p.url=:url", PatientEntity.class);
        q.setParameter("url", url);

        try {
            PatientEntity patientEntity = q.getSingleResult();
            System.out.println("Requested patient found: " + patientEntity.toString());
            return patientEntity;
        } catch (final NoResultException nre) {
            System.out.println("ERROR TO MANAGE - Requested patient not found DB: " + url);
            return null;
        }
    }

    private static void runNativeQuery(String s) {
        System.out.println("--------\n" + s);
        Query query = em.createNativeQuery(s);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(Arrays.toString((Object[]) o));
        }
    }
}
