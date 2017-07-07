package com.icuapp.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.icuapp.model.Patients;
import com.icuapp.model.VitalList;
import com.icuapp.model.order_history.OrderHistoryData;
import com.icuapp.model.order_history.OrderHistoryModel;
import com.icuapp.model.vitals.VitalCriticalDataOfPatient;
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
    private static ArrayList<OrderHistoryData> allOrderHistoryDataList = null;
    private static VitalsMainModel allVitals = null;
    private static ArrayList<VitalList> allVitalsList = null;

    private static HashMap<String, String> definedVitalsConstants = new HashMap<>();
    public static HashMap<String, VitalCriticalDataOfPatient> vitalCriticalDataReportOfPatient = new HashMap<>();

    public AppConstants(Context context) {
    }

    public static ArrayList<Patients> getAllPatientList() {
        if (allPatients == null) {
            allPatients = new ArrayList<>();
            ArrayList<String> arrayPatientName = new ArrayList<>();
            arrayPatientName.add("Anant Singh ");
            arrayPatientName.add("Ashutosh Pareek");
            arrayPatientName.add("Atul Bhalerao");
            arrayPatientName.add("Avnish Mall");
            arrayPatientName.add("Datta Patil");
            arrayPatientName.add("Deepak Sharma");
            arrayPatientName.add("Dhiren Lokwani");
            arrayPatientName.add("Dinesh Jagtap");
            arrayPatientName.add("Kumar Ankit");
            arrayPatientName.add("Kuldeep Jain");
            arrayPatientName.add("Lomesh Pansuriya");
            arrayPatientName.add("Mahesh Saraf");
            arrayPatientName.add("Shiv Kumar Swami");
            arrayPatientName.add("Shivam Dubey");
            arrayPatientName.add("Yogesh Dhamal");
            for (int i = 0; i <= 14; i++) {
                Patients st = new Patients(arrayPatientName.get(i), "" + i, false);
                allPatients.add(st);
            }
        }
        return allPatients;
    }

    public static ArrayList<VitalList> getAllVitalList() {
        if (allVitalsList == null) {
            allVitalsList = new ArrayList<>();
            ArrayList<String> arrayVitalList = new ArrayList<>();
            arrayVitalList.add("Pleth");
            arrayVitalList.add("Resp");
            arrayVitalList.add("Art");
            arrayVitalList.add("PAP");
            arrayVitalList.add("CVP");
            arrayVitalList.add("ICP");
            arrayVitalList.add("Ao");
            arrayVitalList.add("UAP");
            arrayVitalList.add("BAP");
            arrayVitalList.add("FAP");
            arrayVitalList.add("CO2");
            arrayVitalList.add("O2");
            arrayVitalList.add("N2O");
            arrayVitalList.add("AA");

            for (int i = 0; i < arrayVitalList.size(); i++) {
                VitalList vt = new VitalList(arrayVitalList.get(i), false);
                allVitalsList.add(vt);
            }
        }
        return allVitalsList;
    }
    public static ArrayList<Patients> getSelectedPatientList() {
        ArrayList<Patients> allPatientList = getAllPatientList();
        ArrayList<Patients> mSelectedPatients = new ArrayList<>();

        for (Patients data :
                allPatientList) {
            if (data.isChecked())
                mSelectedPatients.add(data);
        }

        return mSelectedPatients;

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

    public static ArrayList<OrderHistoryData> getAllOrderHistoryDataList(Context mContext) {
        if (allOrderHistoryDataList == null) {
            // TODO : HARDCODED JSON STRING PARSING FROM assets foler
            try {
                InputStream is = mContext.getAssets().open("order_history.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, "UTF-8");
                Log.e("OrderHistoryList", "json" + json);

                OrderHistoryModel orderHistoryModel = new Gson().fromJson(json, OrderHistoryModel.class);
                allOrderHistoryDataList = orderHistoryModel.getOrderHistoryDataList();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return allOrderHistoryDataList;
    }

    private static void setDefinedVitalsConstants() {
        AppConstants.definedVitalsConstants.put("SpO2", "SpO2");
        AppConstants.definedVitalsConstants.put("Resp", "Resp");
        AppConstants.definedVitalsConstants.put("CVP", "CVP");
        AppConstants.definedVitalsConstants.put("ICP", "ICP");
        AppConstants.definedVitalsConstants.put("PAP", "PAP");
        AppConstants.definedVitalsConstants.put("Pulse", "Pulse");
        AppConstants.definedVitalsConstants.put("Systolic Pressure", "Systolic Pressure");
        AppConstants.definedVitalsConstants.put("T1", "T1");
        AppConstants.definedVitalsConstants.put("T2", "T2");
    }

    public static ArrayList<VitalDetails> getVitalInfo(int timeValue) {
        HashMap<Integer, ArrayList<VitalDetails>> vitalList = allVitals.getVitalList();
        ArrayList<VitalDetails> vitalDetailses = vitalList.get(timeValue);
        for (VitalDetails data :
                vitalDetailses) {
            data.setAnimated(false);
        }
        return vitalDetailses;
    }
}
