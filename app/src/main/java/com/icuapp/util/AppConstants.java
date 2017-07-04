package com.icuapp.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.icuapp.model.Patients;
import com.icuapp.model.vitals.VitalDetails;
import com.icuapp.model.vitals.VitalsMainModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by riteshpandhurkar on 4/7/17.
 */

public class AppConstants {

    private static ArrayList<Patients> allPatients = null;
    private static VitalsMainModel allVitals = null;

    private static HashMap<String, String> definedVitalsConstants;

    public AppConstants(Context context) {
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

    public static VitalsMainModel getAllVitalList(Context mContext) {
        if (allVitals == null) {
            setDefinedVitalsConstants();
            // TODO : HARDCODED JSON STRING PARSING FROM assets foler
            try {
                InputStream is = mContext.getAssets().open("dashboard.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, "UTF-8");
                Log.e("getAllVitalList", "doGetHistory" + json);

                allVitals = new Gson().fromJson(json, VitalsMainModel.class);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return allVitals;
    }

    private static void setDefinedVitalsConstants() {
        AppConstants.definedVitalsConstants.put("SPO2", "SPO2");
        AppConstants.definedVitalsConstants.put("Resp", "Resp");
        AppConstants.definedVitalsConstants.put("CVP", "CVP");
        AppConstants.definedVitalsConstants.put("ICP", "ICP");
        AppConstants.definedVitalsConstants.put("PAP", "PAP");
        AppConstants.definedVitalsConstants.put("Pulse", "Pulse");
        AppConstants.definedVitalsConstants.put("Systolic Pressure", "Systolic Pressure");
        AppConstants.definedVitalsConstants.put("T1", "T1");
        AppConstants.definedVitalsConstants.put("T2", "T2");
    }
}