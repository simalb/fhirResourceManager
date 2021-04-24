package frm.bean.utils.json.objects;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class PatientJsonObject {

    private int internalId;

    private String url;

    private Date creationDate;

    private String family;
    private List<String> given = null;
    private String prefix = "";
    private String suffix = "";
    private String gender;
    private String birthDate;
}
