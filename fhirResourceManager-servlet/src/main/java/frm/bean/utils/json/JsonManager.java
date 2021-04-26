package frm.bean.utils.json;

import com.google.gson.Gson;
import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.utils.json.objects.Patient;

public class JsonManager {

    private JsonManager() {
    }

    public static Patient getPatientFromJsonObject(String json) {
        return new Gson().fromJson(json, Patient.class);
    }

    public static String getJsonObjectFromPatientEntity(PatientEntity patientEntity) {
        return new Gson().toJson(ConverterUtility.convertPatientEntityToPatientJsonObject(patientEntity));
    }
}
