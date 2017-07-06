package com.icuapp.model.vitals;

import com.icuapp.model.Patients;

import java.util.ArrayList;

/**
 * Created by riteshpandhurkar on 5/7/17.
 */

public class VitalCriticalDataOfPatient {

    private Patients patient;
    private ArrayList<VitalDetails> vitalInfo;
    private String pelthOrSpO2CriticalTimeStamp;
    private String HRCriticalTimeStamp;

    public VitalCriticalDataOfPatient(Patients patient, ArrayList<VitalDetails> vitalInfo, String pelthOrSpO2CriticalTimeStamp, String HRCriticalTimeStamp) {
        this.patient = patient;
        this.vitalInfo = vitalInfo;
        this.pelthOrSpO2CriticalTimeStamp = pelthOrSpO2CriticalTimeStamp;
        this.HRCriticalTimeStamp = HRCriticalTimeStamp;
    }


    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public ArrayList<VitalDetails> getVitalInfo() {
        return vitalInfo;
    }

    public void setVitalInfo(ArrayList<VitalDetails> vitalInfo) {
        this.vitalInfo = vitalInfo;
    }

    public String getPelthOrSpO2CriticalTimeStamp() {
        return pelthOrSpO2CriticalTimeStamp;
    }

    public void setPelthOrSpO2CriticalTimeStamp(String pelthOrSpO2CriticalTimeStamp) {
        this.pelthOrSpO2CriticalTimeStamp = pelthOrSpO2CriticalTimeStamp;
    }

    public String getHRCriticalTimeStamp() {
        return HRCriticalTimeStamp;
    }

    public void setHRCriticalTimeStamp(String HRCriticalTimeStamp) {
        this.HRCriticalTimeStamp = HRCriticalTimeStamp;
    }

    @Override
    public String toString() {
        return "VitalCriticalDataOfPatient{" +
                "patient=" + patient +
                ", vitalInfo=" + vitalInfo +
                ", pelthOrSpO2CriticalTimeStamp='" + pelthOrSpO2CriticalTimeStamp + '\'' +
                ", HRCriticalTimeStamp='" + HRCriticalTimeStamp + '\'' +
                '}';
    }
}
