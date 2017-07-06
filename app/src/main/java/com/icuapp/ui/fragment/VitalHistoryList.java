package com.icuapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.ui.activities.VitalHistoryDetail;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hardikj on 03/07/17.
 */

public class VitalHistoryList extends Fragment {

    String[] arrayDate = {"Pleth","Resp", "CVP", "ICP","PAP","02","CO2","N20"};
    private TextView textViewName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get Subtract Time from current time with no of counts.

//        textViewName = (TextView) getView().findViewById(R.id.tvName);
//        textViewName.setText("Rhythm");

        getDate(8,-15);
        View view = inflater.inflate(R.layout.vital_history_list, container, false);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.vital_history_list_row,R.id.tvVitalName, arrayDate);

        ListView listView = (ListView) view.findViewById(R.id.ecgListView);
        listView.setAdapter(adapter);

        //Set Click Listener for Listview row.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), VitalHistoryDetail.class));
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
