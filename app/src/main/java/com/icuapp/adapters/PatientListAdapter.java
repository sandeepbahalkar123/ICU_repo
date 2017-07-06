package com.icuapp.adapters;

/**
 * Created by riteshpandhurkar on 3/7/17.
 */


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.model.Patients;
import com.icuapp.ui.activities.PatientDetailsMain;
import com.icuapp.util.AppConstants;

public class PatientListAdapter extends
        RecyclerView.Adapter<PatientListAdapter.ViewHolder> {

    private List<Patients> mPatientList;
    private Activity mParentActivity;

    public PatientListAdapter() {
        this.mPatientList = AppConstants.getAllPatientList();
    }

    // Create new views
    @Override
    public PatientListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_patient, parent, false);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;
        Patients patients = mPatientList.get(position);

        viewHolder.tvPatientName.setText(patients.getPatientName());
        viewHolder.tvBedNo.setText("Bed No:" + patients.getBedNo());

        viewHolder.chkSelected.setChecked(patients.isChecked());

        viewHolder.chkSelected.setTag(patients);

        viewHolder.mainContainer.setTag(patients);

        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Patients contact = (Patients) cb.getTag();

                contact.setChecked(cb.isChecked());
                mPatientList.get(pos).setChecked(cb.isChecked());
                AppConstants.getAllPatientList().get(pos).setChecked(cb.isChecked());

            }
        });


        viewHolder.mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ADASD", "onBindViewHolder:" + (Patients) v.getTag());
                Patients data = (Patients) v.getTag();
                Intent intent = new Intent(getmParentActivity(), PatientDetailsMain.class);
                intent.putExtra("PatientBedNo",data.getBedNo());
                intent.putExtra("PatientName",data.getPatientName());
                getmParentActivity().startActivity(intent);
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
        public TextView tvBedNo;
        public LinearLayout mainContainer;

        public Patients singlePatient;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvPatientName = (TextView) itemLayoutView.findViewById(R.id.tvPatientName);
            mainContainer = (LinearLayout) itemLayoutView.findViewById(R.id.mainContainer);
            tvBedNo = (TextView) itemLayoutView.findViewById(R.id.tvBedNo);

            chkSelected = (CheckBox) itemLayoutView
                    .findViewById(R.id.chkSelected);
        }

    }

    public Activity getmParentActivity() {
        return mParentActivity;
    }

    public void setmParentActivity(Activity mParentActivity) {
        this.mParentActivity = mParentActivity;
    }
}
