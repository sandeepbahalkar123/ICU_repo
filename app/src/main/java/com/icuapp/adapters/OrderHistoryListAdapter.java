package com.icuapp.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.order_history.OrderHistoryData;
import com.icuapp.ui.activities.DialogVitalDetailView;

import java.util.ArrayList;

public class OrderHistoryListAdapter extends ArrayAdapter<OrderHistoryData> {
    private final String TAG = getClass().getName();
    Context mContext;
    int layoutResourceId;
    ArrayList<OrderHistoryData> mDataList;

    public OrderHistoryListAdapter(Context context, int layoutResourceId, ArrayList<OrderHistoryData> dataList) {
        super(context, layoutResourceId, dataList);
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        mDataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHolder holder = null;

        if (row == null) {
            holder = new DataHolder();
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder.orderName = (CustomTextView) row.findViewById(R.id.orderName);
            holder.orderInstruction = (TextView) row.findViewById(R.id.orderInstruction);
            holder.orderTimestamp = (CustomTextView) row.findViewById(R.id.orderTimestamp);

            row.setTag(holder);
        } else {
            holder = (DataHolder) row.getTag();
        }

        OrderHistoryData dataObject = mDataList.get(position);

        holder.orderName.setText(dataObject.getName());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.orderInstruction.setText(Html.fromHtml("<b>Instruction</b>: <small>" + dataObject.getInstruction() + "</small>", Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.orderInstruction.setText(Html.fromHtml("<b>Instruction</b>: <small>" + dataObject.getInstruction() + "</small>"));
        }
        holder.orderTimestamp.setText(dataObject.getTimeStamp());

        return row;
    }

    static class DataHolder {

        TextView orderName, orderInstruction, orderTimestamp;
    }

}