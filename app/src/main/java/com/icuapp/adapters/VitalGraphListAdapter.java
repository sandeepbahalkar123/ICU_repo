package com.icuapp.adapters;

/**
 * Created by riteshpandhurkar on 3/7/17.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.model.Patients;
import com.icuapp.model.VitalList;
import com.icuapp.ui.activities.PatientDetailsMain;
import com.icuapp.util.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class VitalGraphListAdapter extends
        RecyclerView.Adapter<VitalGraphListAdapter.ViewHolder> {

    private final String TAG = getClass().getName();
    Context mContext;
    int layoutResourceId;
    ArrayList<VitalList> mDataList;

    public VitalGraphListAdapter(Context context, int layoutResourceId, ArrayList<VitalList> dataList) {
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        mDataList = dataList;
    }

    // Create new views
    @Override
    public VitalGraphListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                layoutResourceId, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        VitalList vitals = mDataList.get(position);
        viewHolder.checkBox.setChecked(vitals.isChecked());
        viewHolder.vitalName.setText(vitals.getVitalName());

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDataList.get(position).setChecked(viewHolder.checkBox.isChecked());
                notifyItemChanged(position);
            }
        });
    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView vitalName;
        CheckBox checkBox;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            vitalName = (TextView) itemLayoutView.findViewById(R.id.ctvVitalName);
            checkBox = (CheckBox) itemLayoutView.findViewById(R.id.checkBoxVital);
        }

    }
}
