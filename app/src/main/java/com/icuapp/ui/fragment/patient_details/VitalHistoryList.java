package com.icuapp.ui.fragment.patient_details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.util.Log;
import android.widget.AdapterView;

import com.icuapp.R;
import com.icuapp.adapters.PatientListAdapter;
import com.icuapp.adapters.VitalListAdapter;
import com.icuapp.model.VitalList;
import com.icuapp.util.AppConstants;


import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardikj on 03/07/17.
 */

public class VitalHistoryList extends Fragment {

    ArrayList<VitalList> vitalListData;
    private String[] spinnerDays = {"1 Days", "2 Days", "3 Days", "4 Days", "5 Days", "6 Days", "7 Days"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.vital_history_list, container, false);

        vitalListData = AppConstants.getAllVitalList();

        ArrayAdapter adapter = new VitalListAdapter(getActivity(),
                R.layout.vital_history_list_row, vitalListData);

        final ListView listView = (ListView) view.findViewById(R.id.vitalListView);
        listView.setAdapter(adapter);

        final LinearLayout linearLayoutList = (LinearLayout) view.findViewById(R.id.vitalHistoryListLinearLayout);
        linearLayoutList.setVisibility(View.VISIBLE);

        final LinearLayout linearLayoutDetail = (LinearLayout) view.findViewById(R.id.vitalHistoryDetailLayout);
        linearLayoutDetail.setVisibility(view.GONE);

        final Button backButton = (Button) view.findViewById(R.id.backBtnVital);
        Button btnOk = (Button) view.findViewById(R.id.buttonOkVital);

//        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerDays);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(),
//                R.layout.vital_history_list, spinnerDays);
//
//        spinner.setVisibility(view.GONE);

        // Drop down layout style - list view with radio button
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
//        spinner.setAdapter(spinnerAdapter);

        // Spinner click listener

        Button btnDays = (Button) view.findViewById(R.id.btnDays);

        btnOk.setOnClickListener(new OnClickListener() {
            //Run when button is clicked
            @Override
            public void onClick(View v) {
                ArrayList<VitalList> allVitalList = AppConstants.getAllVitalList();
                int count = 0;
                for (VitalList data :
                        allVitalList) {
                    if (data.isChecked()) {
                        count++;
                    }
                }
                if (count != 0) {
                    linearLayoutDetail.setVisibility(view.VISIBLE);
                    linearLayoutList.setVisibility(view.GONE);
                } else {
                    Toast.makeText(v.getContext(), "No patient selected", Toast.LENGTH_LONG).show();
                }
            }
        });

        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutDetail.setVisibility(view.GONE);
                linearLayoutList.setVisibility(view.VISIBLE);
            }
        });

//        btnDays.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               spinner.setVisibility(view.VISIBLE);
//            }
//        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
