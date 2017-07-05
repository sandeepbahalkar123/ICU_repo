package com.icuapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.icuapp.R;

/**
 * Created by hardikj on 03/07/17.
 */

public class PatientDetailsMain extends AppCompatActivity implements TabLayout.OnTabSelectedListener,View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView mBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icu_ecg_reports_layout);
        setupHomeView();
    }

    private void setupHomeView() {
        //setup tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabFragmentDetailView);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Vital Graphs"));
        tabLayout.addTab(tabLayout.newTab().setText("ECG Graphs"));
        tabLayout.addTab(tabLayout.newTab().setText("Vital History"));
        tabLayout.addTab(tabLayout.newTab().setText("Order History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mBackArrow = (ImageView)findViewById(R.id.backArrow);
        mBackArrow.setOnClickListener(this);
        //Setup ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpagerDetailView);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.backArrow:
                finish();
                break;

        }

    }
}
