package frm.bean.persistence;

import frm.bean.persistence.entity.PatientEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Startup
@Singleton
@Local
public class PatientPersistenceManager {

    private static final String PERSISTENCE_UNIT_NAME = "PatientTransferService";
    private static EntityManagerFactory factory;

    private EntityManager em;

    @PersistenceContext
    private EntityManager containerEm;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        System.out.println("*** Starting  PatientTransferManager execution.");

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();

        //em = emf.createEntityManager();
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

    public PatientEntity getPatientFromUrl(@NotNull final String url) {

        PatientEntity patientEntity;

        /*Criteria criteria = em.createCriteria(PatientEntity.class);
        PatientEntity patientEntity = criteria.add(Restrictions.eq("url", url))
                .uniqueResult();*/


        /*CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> criteria = builder.createQuery(PatientEntity.class);
        Root<PatientEntity> from = criteria.from(PatientEntity.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(PatientEntity_.url), url));
        TypedQuery<PatientEntity> typed = em.createQuery(criteria);*/


        TypedQuery<PatientEntity> q = em.createQuery("select p from PatientEntity p where p.url=:url", PatientEntity.class);
        q.setParameter("url", url);
        try {
            return q.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }

        /*CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> criteria = builder.createQuery(PatientEntity.class );
        Root<PatientEntity> personRoot = criteria.from( PatientEntity.class );
        criteria.select( personRoot );
        criteria.where( builder.equal( personRoot.get( PatientEntity_.url ), url ) );
        List<PatientEntity> people = em.createQuery( criteria ).getResultList();*/

        /*PatientEntity patientEntity = (PatientEntity)em.createQuery("SELECT x FROM PatientEntity x WHERE x.id_patientEntity  =   :url")
                .setParameter("url", url)
                .getSingleResult();
        */

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
