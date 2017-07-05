package com.icuapp.model.vitals;

import com.icuapp.model.Patients;

import java.util.ArrayList;

/**
 * Created by riteshpandhurkar on 5/7/17.
 */

public class VitalCriticalDataOfPatient {

    private Patients patient;
    private ArrayList<VitalDetails> vitalInfo;
    private String pelthOrSPO2CriticalTimeStamp;
    private String HRCriticalTimeStamp;

    public VitalCriticalDataOfPatient(Patients patient, ArrayList<VitalDetails> vitalInfo, String pelthOrSPO2CriticalTimeStamp, String HRCriticalTimeStamp) {
        this.patient = patient;
        this.vitalInfo = vitalInfo;
        this.pelthOrSPO2CriticalTimeStamp = pelthOrSPO2CriticalTimeStamp;
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

    public String getPelthOrSPO2CriticalTimeStamp() {
        return pelthOrSPO2CriticalTimeStamp;
    }

    public void setPelthOrSPO2CriticalTimeStamp(String pelthOrSPO2CriticalTimeStamp) {
        this.pelthOrSPO2CriticalTimeStamp = pelthOrSPO2CriticalTimeStamp;
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
                ", pelthOrSPO2CriticalTimeStamp='" + pelthOrSPO2CriticalTimeStamp + '\'' +
                ", HRCriticalTimeStamp='" + HRCriticalTimeStamp + '\'' +
                '}';
    }
}
