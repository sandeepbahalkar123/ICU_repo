package com.icuapp.adapters;

/**
 * Created by riteshpandhurkar on 3/7/17.
 */


import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.model.Patients;
import com.icuapp.util.AppConstants;

public class PatientListAdapter extends
        RecyclerView.Adapter<PatientListAdapter.ViewHolder> {

    private List<Patients> mPatientList;

    public PatientListAdapter() {
        this.mPatientList = AppConstants.getAllPatientList();
    }

    // Create new views
    @Override
    public PatientListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_patient, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;
        Patients patients = mPatientList.get(position);

        viewHolder.tvPatientName.setText(patients.getPatientName());

        viewHolder.chkSelected.setChecked(patients.isChecked());

        viewHolder.chkSelected.setTag(patients);

        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Patients contact = (Patients) cb.getTag();

                contact.setChecked(cb.isChecked());
                mPatientList.get(pos).setChecked(cb.isChecked());
                AppConstants.getAllPatientList().get(pos).setChecked(cb.isChecked());
                Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return mPatientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPatientName;

        public CheckBox chkSelected;

        public Patients singlePatient;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvPatientName = (TextView) itemLayoutView.findViewById(R.id.tvPatientName);

            chkSelected = (CheckBox) itemLayoutView
                    .findViewById(R.id.chkSelected);
        }

    }

}
