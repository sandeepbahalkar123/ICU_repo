package com.icuapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.LinearLayout;
import com.icuapp.R;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.icuapp.ui.activities.ECGGraphDetail;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Created by hardikj on 03/07/17.
 */

public class ECGGraphsList extends Fragment {

    String[] arrayDate = new String[8];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get Subtract Time from current time with no of counts.
        getDate(8,-15);
        final View view = inflater.inflate(R.layout.ecg_graphs, container, false);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.ecg_graphs_row,R.id.tvDateAndTime, arrayDate);

        final ListView listView = (ListView) view.findViewById(R.id.ecgListView);
        listView.setAdapter(adapter);
        listView.setVisibility(view.VISIBLE);


        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ecgGraphDetailLayout);
        linearLayout.setVisibility(view.GONE);

        final TextView tvAnalysisTime = (TextView) view.findViewById(R.id.analysisTime);

        final Button backButton = (Button) view.findViewById(R.id.backBtn);
        //Set Click Listener for Listview row.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(view.GONE);
                linearLayout.setVisibility(view.VISIBLE);
                tvAnalysisTime.setText(arrayDate[position]);
            }
        });

        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(view.VISIBLE);
                linearLayout.setVisibility(view.GONE);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //Methods for getting subtracted time from current time.
    private void getDate(Integer noOfCell, Integer subTime) {
        Calendar calendar = Calendar.getInstance();
        for (int day = 0; day < noOfCell; day++) {
            calendar.add(Calendar.MINUTE, subTime);
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            String formattedDate = df.format(calendar.getTime());
            arrayDate[day] = formattedDate;
            System.out.println("Today List :" + arrayDate);
        }
    }
}
