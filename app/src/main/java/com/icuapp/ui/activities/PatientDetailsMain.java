package com.icuapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.icuapp.R;

/**
 * Created by hardikj on 03/07/17.
 */

public class PatientDetailsMain extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_detail_view_pager);
        setupHomeView();
    }

    private void setupHomeView() {
        //setup tablayout
        tabLayout = (TabLayout) findViewById(R.id.patientDetailTabLayout);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Vital Graphs"));
        tabLayout.addTab(tabLayout.newTab().setText("ECG Graphs"));
        tabLayout.addTab(tabLayout.newTab().setText("Vital History"));
        tabLayout.addTab(tabLayout.newTab().setText("Order History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Setup ViewPager
        viewPager = (ViewPager) findViewById(R.id.patientDetailViewPager);
        PatientDetailViewPager adapter = new PatientDetailViewPager(getSupportFragmentManager(), tabLayout.getTabCount());
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
}
