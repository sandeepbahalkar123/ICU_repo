package com.icuapp.adapters;

import android.app.Activity;
import android.widget.CheckBox;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.icuapp.R;
import com.icuapp.model.Patients;
import com.icuapp.model.VitalList;
import com.icuapp.util.AppConstants;

import java.util.ArrayList;
import android.widget.ArrayAdapter;

/**
 * Created by hardikj on 06/07/17.
 */

public class VitalListAdapter extends ArrayAdapter<VitalList> {
    private final String TAG = getClass().getName();
    Context mContext;
    int layoutResourceId;
    ArrayList<VitalList> mDataList;

    public VitalListAdapter(Context context, int layoutResourceId, ArrayList<VitalList> dataList) {
        super(context, layoutResourceId, dataList);
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        mDataList = dataList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHolder holder = null;

        if (row == null) {
            holder = new DataHolder();
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder.vitalName = (TextView) row.findViewById(R.id.ctvVitalName);
            holder.checkBox = (CheckBox) row.findViewById(R.id.checkBoxVital);

            VitalList vitals = mDataList.get(position);

            holder.checkBox.setChecked(vitals.isChecked());

            holder.checkBox.setTag(vitals);

            row.setTag(holder);

        } else {
            holder = (DataHolder) row.getTag();
        }

        VitalList dataObject = mDataList.get(position);

        holder.vitalName.setText(dataObject.getVitalName());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                VitalList contact = (VitalList) cb.getTag();

                contact.setChecked(cb.isChecked());
                mDataList.get(position).setChecked(cb.isChecked());
                AppConstants.getAllVitalList().get(position).setChecked(cb.isChecked());
            }
        });

        return row;
    }

    static class DataHolder {
        TextView vitalName;
        CheckBox checkBox;
    }

}