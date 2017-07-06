package com.icuapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.icuapp.R;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.vitals.VitalDetails;
import com.icuapp.util.AppConstants;
import com.icuapp.util.CommonMethods;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeetal on 4/7/17.
 */

public class DialogVitalDetailView extends AppCompatActivity {

//    @BindView(R.id.vitalsMainTagCount)
//    CustomTextView mVitalsMainTagCount;
//
//    @BindView(R.id.bedNo)
//    CustomTextView mBedNo;
//
//    @BindView(R.id.patientName)
//    CustomTextView mPatienName;

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

    @BindView(R.id.textViewTimeDate)
    CustomTextView mTextViewTimeDate;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_vital_detail_view_layout);
        ButterKnife.bind(this);
        intent = getIntent();
        String currentDate = CommonMethods.getCurrentDateTime();
        String currentTime = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTextViewTimeDate.setText(currentDate +" "+ currentTime+"  " + intent.getStringExtra("VitalType"));
        Intent intent = getIntent();
        String mToolBarTitle = intent.getStringExtra("TITLE");
        getSupportActionBar().setTitle(mToolBarTitle);

        toolbar.setNavigationIcon(VectorDrawableCompat.create(getResources(), R.drawable.ic_arrow_back_white_24dp, null));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });
        setupPatientVitals();

    }

    private void setupPatientVitals() {
        ArrayList<VitalDetails> vitalInfo = AppConstants.getVitalInfo(CommonMethods.generateRandomEvenNumber());
        for (VitalDetails dataObject :
                vitalInfo) {
            String name = dataObject.getName();
            String value = dataObject.getValue();
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
}
