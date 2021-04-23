package frm.bean.utils.json.utils;

import com.google.gson.Gson;
import frm.bean.utils.json.objects.Patient;

public class JsonManager {

    public static Patient getPatientFromJson(String json) {
        Patient patient = new Gson().fromJson(json, Patient.class);
        return patient;
    }
}
