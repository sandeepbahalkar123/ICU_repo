package com.icuapp.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.ui.activities.DialogVitalDetailView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> rowItems;
    private String mToolBarTitle;
    private  Dialog mDialog;
     
    public CustomBaseAdapter(Context context, ArrayList<String> items, String toolBarTitle, Dialog dialog) {
        this.context = context;
        this.rowItems = items;
        this.mToolBarTitle = toolBarTitle;
        this.mDialog = dialog;
    }
     
    /*private view holder class*/
    private class ViewHolder {

        TextView mTxtTitle;
        LinearLayout mDialogLinearLayout;

    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
         
        LayoutInflater mInflater = (LayoutInflater)
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dialog_item_layout, null);
            holder = new ViewHolder();
            holder.mTxtTitle = (TextView) convertView.findViewById(R.id.vitalTypeName);
            holder.mDialogLinearLayout = (LinearLayout)convertView.findViewById(R.id.dialogLinearLayout) ;
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        String vitalName = rowItems.get(position);
        if(vitalName.equals("***SPO2 <80")){
            holder.mDialogLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.Red));

        }else if(vitalName.equals("**HR High > 120")){
            holder.mDialogLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));

        }else if(vitalName.equals("* T Rect High > 38.0")){
            holder.mDialogLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.notification_header_text));

        }


        holder.mTxtTitle.setText(rowItems.get(position));
     holder.mTxtTitle.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             mDialog.dismiss();
             Intent intent = new Intent(context, DialogVitalDetailView.class);
             intent.putExtra("TITLE",mToolBarTitle);
             context.startActivity(intent);


         }
     });
         
        return convertView;
    }
 
    @Override
    public int getCount() {     
        return rowItems.size();
    }
 
    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}