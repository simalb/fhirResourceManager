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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID internalId;

    @Column(name = "url", nullable = false, unique = true)
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
