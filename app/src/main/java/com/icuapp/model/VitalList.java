package com.icuapp.model;

/**
 * Created by hardikj on 06/07/17.
 */

public class VitalList {
    private String vitalName;
    private boolean isChecked;

    public String getVitalName() {
        return vitalName;
    }

    public void setVitalName(String vitalName) {
        this.vitalName = vitalName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public VitalList(String patientName, boolean isChecked) {
        this.vitalName = patientName;
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "VitalList{" +
                "vitalName='" + vitalName + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
