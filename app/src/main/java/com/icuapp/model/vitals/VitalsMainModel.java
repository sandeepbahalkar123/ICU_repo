
package com.icuapp.model.vitals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VitalsMainModel {

    @SerializedName("data")
    @Expose
    private HashMap<Integer, ArrayList<VitalDetails>> vitalList = null;

    public HashMap<Integer, ArrayList<VitalDetails>> getVitalList() {
        return vitalList;
    }

    public void setVitalList(HashMap<Integer, ArrayList<VitalDetails>> vitalList) {
        this.vitalList = vitalList;
    }
}
