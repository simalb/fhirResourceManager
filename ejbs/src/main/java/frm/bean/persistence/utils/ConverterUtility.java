package frm.bean.persistence.utils;

import frm.bean.persistence.entity.PatientEntity;
import frm.bean.utils.json.objects.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterUtility {

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static PatientEntity getCompletePatientEntity(Patient patient, String url) {
        PatientEntity patientEntity = convertPatientToPatientEntity(patient);
        patientEntity.setUrl(url);

        return patientEntity;
    }

    public static PatientEntity convertPatientToPatientEntity(Patient patient) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFamily(patient.getName().get(0).getFamily());
        patientEntity.setGiven(patient.getName().get(0).getGiven());

        if(patient.getName().get(0).getPrefix() != null) {
            patientEntity.setPrefix(patient.getName().get(0).getPrefix());
        }

        if(patient.getName().get(0).getSuffix() != null) {
            patientEntity.setSuffix(patient.getName().get(0).getSuffix());
        }

        patientEntity.setGender(patient.getGender());
        patientEntity.setBirthDate(patient.getBirthDate());

        return patientEntity;
    }

    private Date parseTimestamp(String timestamp) {
        try {
            return DATE_TIME_FORMAT.parse(timestamp);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}