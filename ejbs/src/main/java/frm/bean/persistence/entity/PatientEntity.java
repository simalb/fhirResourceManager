package frm.bean.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Data
public class PatientEntity {

    @Id
    @GeneratedValue
    private int internalId;

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
