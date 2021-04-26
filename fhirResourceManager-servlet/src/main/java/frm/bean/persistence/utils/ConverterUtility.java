package frm.bean.persistence.utils;

import frm.bean.persistence.entity.PatientEntity;
import frm.bean.utils.json.objects.Patient;
import frm.bean.utils.json.objects.PatientJsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterUtility {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    public static PatientJsonObject convertPatientEntityToPatientJsonObject(PatientEntity patientEntity) {
        PatientJsonObject patientJsonObject = new PatientJsonObject();
        patientJsonObject.setInternalId(patientEntity.getInternalId());
        patientJsonObject.setUrl(patientEntity.getUrl());
        patientJsonObject.setCreationDate(patientEntity.getCreationDate());
        patientJsonObject.setFamily(patientEntity.getFamily());
        patientJsonObject.setGiven(patientEntity.getGiven());
        patientJsonObject.setPrefix(patientEntity.getPrefix());
        patientJsonObject.setSuffix(patientEntity.getSuffix());
        patientJsonObject.setGender(patientEntity.getGender());
        patientJsonObject.setBirthDate(patientEntity.getBirthDate());

        return patientJsonObject;
    }

    private Date parseTimestamp(String timestamp) {
        try {
            return simpleDateFormat.parse(timestamp);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}