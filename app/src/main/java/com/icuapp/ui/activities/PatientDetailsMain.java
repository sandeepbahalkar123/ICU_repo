package com.icuapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.icuapp.R;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.vitals.VitalCriticalDataOfPatient;
import com.icuapp.model.vitals.VitalDetails;
import com.icuapp.util.AppConstants;
import com.icuapp.util.CommonMethods;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardikj on 03/07/17.
 */

public class PatientDetailsMain extends AppCompatActivity implements TabLayout.OnTabSelectedListener,View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView mBackArrow;

    @BindView(R.id.vitalsMainTagCount)
    CustomTextView mVitalsMainTagCount;

    @BindView(R.id.bedNo)
    CustomTextView mBedNo;

    @BindView(R.id.patientName)
    CustomTextView mPatienName;

    @BindView(R.id.countPulse)
    CustomTextView mPulseCount;

    @BindView(R.id.countPleth)
    CustomTextView mSpo2Count;

    @BindView(R.id.countResp)
    CustomTextView mRespCount;

    @BindView(R.id.countCvp)
    CustomTextView mCvpCount;

    @BindView(R.id.countIcp)
    CustomTextView mIcpCount;

    @BindView(R.id.countPap)
    CustomTextView mPapCount;

    @BindView(R.id.countSystolicPressure)
    CustomTextView mSysPressureCount;

    @BindView(R.id.countt1)
    CustomTextView mT1Count;

    @BindView(R.id.countt2)
    CustomTextView mT2Count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icu_ecg_reports_layout);
        ButterKnife.bind(this);
        setupHomeView();
    }

    private void setupHomeView() {
        tabLayout = (TabLayout) findViewById(R.id.tabFragmentDetailView);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Vital Graphs"));
        tabLayout.addTab(tabLayout.newTab().setText("ECG Reports"));
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position,0f,true);
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position,0f,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Setup patient vitals datacounts.
        setupPatientVitals();
    }

    private void setupPatientVitals() {
        ArrayList<VitalDetails> vitalInfo = AppConstants.getVitalInfo(CommonMethods.generateRandomEvenNumber());
        for (VitalDetails dataObject :
                vitalInfo) {
            String name = dataObject.getName();
            String value = dataObject.getValue();
            double formattedValue = Double.parseDouble(dataObject.getValue());
            if (name.equalsIgnoreCase("Pleth") || name.equalsIgnoreCase("SPO2")) {
                mSpo2Count.setText(value);
            } else if (name.equalsIgnoreCase("Resp")) {
                mRespCount.setText(value);
            } else if (name.equalsIgnoreCase("CVP")) {
                mCvpCount.setText(value);
            } else if (name.equalsIgnoreCase("ICP")) {
                mIcpCount.setText(value);
            } else if (name.equalsIgnoreCase("PAP")) {
                mPapCount.setText(value);
            } else if (name.equalsIgnoreCase("Pulse")) {
                mPulseCount.setText(value);
            } else if (name.equalsIgnoreCase("Systolic Pressure")) {
                mSysPressureCount.setText(value);
            } else if (name.equalsIgnoreCase("T1")) {
                mT1Count.setText(value);
            } else if (name.equalsIgnoreCase("T2")) {
                mT2Count.setText(value);
            }
        }

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
