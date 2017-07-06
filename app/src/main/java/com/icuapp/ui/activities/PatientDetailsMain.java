package com.icuapp.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.adapters.DashBoardAdapter;
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

    @BindView(R.id.order)
    TextView mOrder;


    @BindView(R.id.countPulse)
    CustomTextView mPulseCount;

    @BindView(R.id.vitalsLinearLayout)
    LinearLayout mVitalsLinearLayout;

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

    @BindView(R.id.bedNo)
    CustomTextView mBedNo;

    @BindView(R.id.patientName)
    CustomTextView mPatientName;

    @BindView(R.id.countt1)
    CustomTextView mT1Count;

    @BindView(R.id.countt2)
    CustomTextView mT2Count;
    Intent intent;
    private Handler mHandler;
    private String currentDate;
    private String currentTime;
    private ArrayList<String> dialogList;


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
        intent = getIntent();
        mPatientName.setText(intent.getStringExtra("PatientName"));
        mBedNo.setText("Bed No. "+ "  " + intent.getStringExtra("PatientBedNo"));
        mBackArrow = (ImageView)findViewById(R.id.backArrow);
        mBackArrow.setOnClickListener(this);
        //Setup ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpagerDetailView);
        PatientDetailViewPager adapter = new PatientDetailViewPager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(this);
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientDetailsMain.this,OrderHistoryContainerActivity.class);
                startActivity(intent);
            }
        });
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
        mHandler = new Handler();
        Runnable updateUI = new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                setupPatientVitals();
                mHandler.postDelayed(this, 2000);

            }
        };

        mHandler.postDelayed(updateUI, 2000);
        mVitalsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeDetails = currentDate + currentTime;
                CommonMethods.showAlertDialog(PatientDetailsMain.this, "Bed No. "+intent.getStringExtra("PatientBedNo")+"  "+intent.getStringExtra("PatientName"), dialogList,timeDetails);
            }
        });
        //Setup patient vitals datacounts.

    }

    private void setupPatientVitals() {
        dialogList = new ArrayList<>();
        ArrayList<VitalDetails> vitalInfo = AppConstants.getVitalInfo(CommonMethods.generateRandomEvenNumber());
        for (VitalDetails dataObject :
                vitalInfo) {
            String name = dataObject.getName();
            String value = dataObject.getValue();
            double formattedValue = Double.parseDouble(dataObject.getValue());
            if (name.equalsIgnoreCase("Pleth") || name.equalsIgnoreCase("SPO2")) {
                mSpo2Count.setText(value);
                if (formattedValue > Double.parseDouble("" + 90)) {
                    currentDate = CommonMethods.getCurrentDateTime();
                    currentTime = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");
                    mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    mVitalsMainTagCount.setText("***SPO2 <80 " +currentTime.substring(0,5));
                    mVitalsLinearLayout.setBackground(getResources().getDrawable(R.drawable.curve_fill_red_bg));
                   // loadAnimation(mSpo2Count, dataObject);
                    dialogList.add("***SPO2 <80 " +currentTime.substring(0,5));
                }

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
                if (formattedValue < 75) {
                    String currentTimeHr = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");
                    mVitalsLinearLayout.setVisibility(View.VISIBLE);
                   mVitalsLinearLayout.setBackground(getResources().getDrawable(R.drawable.curve_fill_yellow_bg));
                  mVitalsMainTagCount.setTextColor(ContextCompat.getColor(PatientDetailsMain.this, R.color.black));
                    mVitalsMainTagCount.setText("**HR High > 120"+ " "+currentTimeHr.substring(0,5));
                   // loadAnimationHr(mPulseCount, dataObject);
                    dialogList.add("**HR High > 120"+ " "+currentTimeHr.substring(0,5));
                }
            } else if (name.equalsIgnoreCase("Systolic Pressure")) {
                mSysPressureCount.setText(value);
            } else if (name.equalsIgnoreCase("T1")) {
                mT1Count.setText(value);
                //TODO
               if (formattedValue <= 38.2) {
                    String currentTimeHr = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");
                    mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    mVitalsLinearLayout.setBackground(getResources().getDrawable(R.drawable.curve_fill_blue_bg));
                    mVitalsMainTagCount.setTextColor(ContextCompat.getColor(PatientDetailsMain.this, R.color.black));
                    mVitalsMainTagCount.setText("**T1 High> 38.0"+ " "+currentTimeHr.substring(0,5));
                    // loadAnimationHr(mPulseCount, dataObject);
                    dialogList.add("**T1 High> 38.0"+ " "+currentTimeHr.substring(0,5));
                }
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
    private void loadAnimation(final View view, final VitalDetails dataObject) {
        /*Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);*/

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataObject.isAnimated()) {
                    view.setBackgroundColor(ContextCompat.getColor(PatientDetailsMain.this, R.color.Red));
                    TextView textView = (TextView) view.findViewById(R.id.countPleth);
                    textView.setTextColor(ContextCompat.getColor(PatientDetailsMain.this,R.color.white));
                    //  view.setTextColor(Color.WHITE);
                } else {
                    view.setBackgroundColor(Color.BLACK);
                    TextView textView = (TextView) view.findViewById(R.id.countPleth);
                    textView.setTextColor(ContextCompat.getColor(PatientDetailsMain.this,R.color.parrot_green_color));
                    //    view.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
                }

                if (dataObject.isAnimated()) {
                    dataObject.setAnimated(false);
                } else {
                    dataObject.setAnimated(true);
                }
                handler.postDelayed(this, 300);
                //  drawable.start();
            }
        }, 300);
    }
    private void loadAnimationHr(final View view, final VitalDetails dataObject) {
        /*Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);*/

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataObject.isAnimated()) {
                    view.setBackgroundColor(ContextCompat.getColor(PatientDetailsMain.this, R.color.yellow));
                    TextView textView = (TextView) view.findViewById(R.id.countPulse);
                    textView.setTextColor(ContextCompat.getColor(PatientDetailsMain.this,R.color.black));

                } else {
                    view.setBackgroundColor(Color.BLACK);
                    TextView textView = (TextView) view.findViewById(R.id.countPulse);
                    textView.setTextColor(ContextCompat.getColor(PatientDetailsMain.this,R.color.parrot_green_color));
                    //    view.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
                }

                if (dataObject.isAnimated()) {
                    dataObject.setAnimated(false);
                } else {
                    dataObject.setAnimated(true);
                }
                handler.postDelayed(this, 300);
                //  drawable.start();
            }
        }, 300);
    }

}
