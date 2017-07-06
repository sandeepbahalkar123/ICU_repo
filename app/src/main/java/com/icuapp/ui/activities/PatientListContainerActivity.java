package com.icuapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.icuapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hardikj on 04/07/17.
 */

public class PatientListContainerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_list_container_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Patients");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupHomeView();
    }

    private void setupHomeView() {
        //setup tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("CCU"));
        tabLayout.addTab(tabLayout.newTab().setText("ICU"));
        tabLayout.addTab(tabLayout.newTab().setText("PICU"));
        tabLayout.addTab(tabLayout.newTab().setText("NICU"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Setup ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PatientListContainerViewPager adapter = new PatientListContainerViewPager(getSupportFragmentManager(), tabLayout.getTabCount(),viewPager);
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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
