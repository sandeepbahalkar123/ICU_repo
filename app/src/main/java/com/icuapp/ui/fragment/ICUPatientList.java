package com.icuapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.adapters.PatientListAdapter;
import com.icuapp.model.Patients;

import java.util.ArrayList;
import java.lang.String;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardikj on 04/07/17.
 */

public class ICUPatientList extends Fragment  {
    private PatientListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recycleListView)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.list_view, container, false);
        final FragmentActivity c = getActivity();
      final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleListView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);

//        setContentView(R.layout.list_view);
        ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(layoutManager);


        // create an Object for Adapter
        mAdapter = new PatientListAdapter();
        mAdapter.setmParentActivity(getActivity());

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
/*
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
//                int pos = mRecyclerView.getChildAdapterPosition(v);
//                System.out.println("Position of clicked :" + pos);
                Toast.makeText(getActivity(),"Cell Clicked ", Toast.LENGTH_LONG)
                        .show();
            }
        });*/


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
//            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
