package frm.bean.utils.json.utils;

import com.google.gson.Gson;
import frm.bean.persistence.entity.PatientEntity;
import frm.bean.utils.json.objects.Patient;

public class JsonManager {

    public static Patient getPatientFromJsonObject(String json) {
        Patient patient = new Gson().fromJson(json, Patient.class);
        return patient;
    }

    public static String getJsonObjectFromPatientEntity(PatientEntity patientEntity) {
        String jsonObject = new Gson().toJson(patientEntity);
        return jsonObject;
    }
}
