package com.icuapp.model;

import java.util.ArrayList;

/**
 * Created by riteshpandhurkar on 3/7/17.
 */

public class Patients {


    private static ArrayList<Patients> allPatients = null;

    private String patientName;
    private String bedNo;
    private boolean isChecked;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Patients(String patientName, String bedNo, boolean isChecked) {
        this.patientName = patientName;
        this.bedNo = bedNo;
        this.isChecked = isChecked;
    }

    public static ArrayList<Patients> getPatientList() {
        if (allPatients == null) {
            allPatients = new ArrayList<>();
            for (int i = 1; i <= 15; i++) {
                Patients st = new Patients("Patient " + i, "" + i, false);
                allPatients.add(st);
            }
        }
        return allPatients;
    }
}
