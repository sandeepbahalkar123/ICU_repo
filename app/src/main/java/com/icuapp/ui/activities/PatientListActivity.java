package com.icuapp.ui.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.adapters.PatientListAdapter;
import com.icuapp.model.Patients;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class PatientListActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Patients> mPatientsList;

    @BindView(R.id.recycleListView)
    RecyclerView mRecyclerView;

    //@BindView(R.id.btnShow)
    // Button btnSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Patients");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPatientsList = Patients.getPatientList();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new PatientListAdapter(mPatientsList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        /*btnSelection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<Patients> stList = ((PatientListAdapter) mAdapter)
                        .getUpdatedPatientList();
                for (int i = 0; i < stList.size(); i++) {
                    Patients singleStudent = stList.get(i);
                    if (singleStudent.isChecked() == true) {
                        data = data + "\n" + singleStudent.getPatientName().toString();
                    }
                }
                Toast.makeText(PatientListActivity.this,
                        "Selected Patients: \n" + data, Toast.LENGTH_LONG)
                        .show();
            }
        });*/

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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

