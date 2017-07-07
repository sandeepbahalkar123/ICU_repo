package com.icuapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.adapters.PatientListAdapter;
import com.icuapp.adapters.SetAlertAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeetal on 6/7/17.
 */

public class NicuList extends Fragment {

    private SetAlertAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recycleListView)
    RecyclerView mRecyclerView;
    @BindView(R.id.buttonDone)
    Button mButtonDone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.list_view, container, false);
        final FragmentActivity c = getActivity();
//        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);

//        setContentView(R.layout.list_view);
        ButterKnife.bind(this, view);
        mButtonDone.setVisibility(View.VISIBLE);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(layoutManager);


        // create an Object for Adapter
        mAdapter = new SetAlertAdapter();
        mAdapter.setmParentActivity(getActivity());
        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Alert Set Successfully", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
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
