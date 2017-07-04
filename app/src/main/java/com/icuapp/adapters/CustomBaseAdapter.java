package com.icuapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.model.RowItem;
import com.icuapp.ui.activities.DialogVitalDetailView;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;
     
    public CustomBaseAdapter(Context context, List<RowItem> items) {
        this.context = context;
        this.rowItems = items;
    }
     
    /*private view holder class*/
    private class ViewHolder {

        TextView txtTitle;

    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
         
        LayoutInflater mInflater = (LayoutInflater)
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dialog_item_layout, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
         
        RowItem rowItem = (RowItem) getItem(position);
        holder.txtTitle.setText(rowItem.getTitle());
     holder.txtTitle.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, DialogVitalDetailView.class);
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