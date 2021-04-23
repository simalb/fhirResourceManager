package frm.bean.utils.http.connection;

import com.google.gson.Gson;

public class JsonManager {

    public static Patient getPatientFromJson(String json) {
        Patient patient = new Gson().fromJson(json, Patient.class);
        return patient;
    }
}
