package frm.bean.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Patients")
public class PatientEntity {

    @Id
    /*@GeneratedValue(generator = “UUID”)
    @GenericGenerator(
            name = “UUID”,
            strategy = “org.hibernate.id.UUIDGenerator”,
    )*/
    @GeneratedValue
    //@Column(name = “id”, updatable = false, nullable = false)
    private UUID internalId;

    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private String family;
    private List<String> given = null;
    private String prefix = "";
    private String suffix = "";
    private String gender;
    private String birthDate;
}
