package com.icuapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.icuapp.customesViews.CustomTextView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.Patients;
import com.icuapp.model.vitals.VitalCriticalDataOfPatient;
import com.icuapp.model.vitals.VitalDetails;

import com.icuapp.ui.activities.OrderHistoryContainerActivity;

import com.icuapp.ui.activities.PatientDetailsMain;

import com.icuapp.util.AppConstants;
import com.icuapp.util.CommonMethods;

import java.util.ArrayList;


public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder> {

    private Activity activity;
    private ArrayList<Patients> mSelectedPatients;
    private int i;
    public static final String NORMAL = "normal";
    public static final String MEDIUM = "medium";
    public static final String HIGH = "high";

    Context mContext;
    String currentDate;
    String currentTime;

    public DashBoardAdapter(Activity context, ArrayList<Patients> mSelectedPatients) {
        this.mContext = context;
        this.activity = context;
        this.mSelectedPatients = mSelectedPatients;
    }


    @Override
    public DashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_item_layout, parent, false);

        // create ViewHolder

        DashBoardViewHolder viewHolder = new DashBoardViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DashBoardViewHolder viewHolder, final int position) {

        CommonMethods.printLog("DashBoardAdapter", "" + AppConstants.vitalCriticalDataReportOfPatient);

        final Patients patientObject = mSelectedPatients.get(position);

        final ArrayList<String> dialogList = new ArrayList<>();
        ArrayList<VitalDetails> vitalInfo = AppConstants.getVitalInfo(CommonMethods.generateRandomEvenNumber());
        viewHolder.mVitalsLinearLayout.setVisibility(View.INVISIBLE);
        viewHolder.patientName.setText(patientObject.getPatientName());
        viewHolder.bedNo.setText("Bed No." + patientObject.getBedNo());


        for (VitalDetails dataObject :
                vitalInfo) {
            String name = dataObject.getName();
            String value = dataObject.getValue();
            double formattedValue = Double.parseDouble(dataObject.getValue());
            if (name.equalsIgnoreCase("Pleth") || name.equalsIgnoreCase("SPO2")) {
                viewHolder.countPleth.setText(value);
                if (formattedValue < Double.parseDouble("" + 90)) {

                    //------------

                currentDate = CommonMethods.getCurrentDateTime();
                 currentTime = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");
                    VitalCriticalDataOfPatient vitalCriticalDataOfPatient = AppConstants.vitalCriticalDataReportOfPatient.get(patientObject.getBedNo());
                    if (vitalCriticalDataOfPatient != null) {
                        vitalCriticalDataOfPatient.setPatient(patientObject);
                        vitalCriticalDataOfPatient.setVitalInfo(vitalInfo);
                        vitalCriticalDataOfPatient.setPelthOrSPO2CriticalTimeStamp(currentTime);
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(), vitalCriticalDataOfPatient);
                    } else {
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(),
                                new VitalCriticalDataOfPatient(patientObject, vitalInfo, currentTime, null));
                    }
                    //---------------

                    viewHolder.mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.vitalsMainTagCount.setText("***SPO2 <80 " + currentTime.substring(0, 5));
                    viewHolder.mVitalsLinearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.curve_fill_red_bg));
                    loadAnimation(viewHolder.countPleth, dataObject);
                    dialogList.add("***SPO2 <80 " + currentTime.substring(0, 5));

                }
            } else if (name.equalsIgnoreCase("Resp")) {
                viewHolder.countResp.setText(value);
            } else if (name.equalsIgnoreCase("CVP")) {
                viewHolder.countCvp.setText(value);
            } else if (name.equalsIgnoreCase("ICP")) {
                viewHolder.countIcp.setText(value);
            } else if (name.equalsIgnoreCase("PAP")) {
                viewHolder.countPap.setText(value);
            } else if (name.equalsIgnoreCase("Pulse")) {
                viewHolder.countPulse.setText(value);
                if (formattedValue > 80) {
                    String currentTimeHr = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");

                    viewHolder.mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mVitalsLinearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.curve_fill_yellow_bg));
                    viewHolder.vitalsMainTagCount.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                    //------------------
                    VitalCriticalDataOfPatient vitalCriticalDataOfPatient = AppConstants.vitalCriticalDataReportOfPatient.get(patientObject.getBedNo());

                    if (vitalCriticalDataOfPatient != null) {
                        vitalCriticalDataOfPatient.setPatient(patientObject);
                        vitalCriticalDataOfPatient.setVitalInfo(vitalInfo);
                        vitalCriticalDataOfPatient.setHRCriticalTimeStamp(currentTimeHr);
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(), vitalCriticalDataOfPatient);
                    } else {
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(),
                                new VitalCriticalDataOfPatient(patientObject, vitalInfo, null, currentTimeHr));
                    }

                    viewHolder.vitalsMainTagCount.setText("**HR High > 120" + " " + currentTimeHr.substring(0, 5));
                    loadAnimationHr(viewHolder.countPulse, dataObject);
                    dialogList.add("**HR High > 120" + " " + currentTimeHr.substring(0, 5));


                }
            } else if (name.equalsIgnoreCase("Systolic Pressure")) {
                viewHolder.countSystolicPressure.setText(value);
            } else if (name.equalsIgnoreCase("T1")) {
                viewHolder.countT1.setText(value);
                if (formattedValue <= 38.2) {
                    String currentTimeHr = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss");
                    viewHolder.mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mVitalsLinearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.curve_fill_blue_bg));
                    viewHolder.vitalsMainTagCount.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    viewHolder.vitalsMainTagCount.setText("**T1 High> 38.0"+ " "+currentTimeHr.substring(0,5));
                     /*loadAnimationT(viewHolder.countT1, dataObject);*/
                    dialogList.add("**T1 High> 38.0"+ " "+currentTimeHr.substring(0,5));
                }
            } else if (name.equalsIgnoreCase("T2")) {
                viewHolder.countT2.setText(value);
            }
        }

        viewHolder.vitalsMainTagCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeDetails = currentDate + currentTime;
                CommonMethods.showAlertDialog(mContext, "Bed No." + patientObject.getBedNo() + "  " + patientObject.getPatientName(), dialogList,timeDetails);
            }
        });

        viewHolder.mLinearLayoutItemDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PatientDetailsMain.class);
                intent.putExtra("PatientBedNo",patientObject.getBedNo());
                intent.putExtra("PatientName",patientObject.getPatientName());
                mContext.startActivity(intent);
            }
        });

        viewHolder.mHorizontalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PatientDetailsMain.class);
                intent.putExtra("PatientBedNo",patientObject.getBedNo());
                intent.putExtra("PatientName",patientObject.getPatientName());
                mContext.startActivity(intent);
            }
        });

        viewHolder.tvOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, OrderHistoryContainerActivity.class);
                intent.putExtra(mContext.getString(R.string.bed_no), patientObject.getBedNo());
                activity.startActivity(intent);
            }
        });


      /*  viewHolder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "hi", Toast.LENGTH_SHORT).show();
            }
        });
*/
    }


    @Override
    public int getItemCount() {
        return mSelectedPatients.size();
    }

    public class DashBoardViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName, countPleth, countResp, countCvp, countIcp, countPap, countSystolicPressure, countT1, countT2;

        public TextView   tvOrderHistory;

        public TextView countPulse, bedNo,order;

        public CustomTextView vitalsMainTagCount;
        public LinearLayout mVitalsLinearLayout;
        public LinearLayout mLinearLayoutItemDashboard;
        public LinearLayout mHorizontalView;


        public DashBoardViewHolder(View view) {
            super(view);
            mVitalsLinearLayout = (LinearLayout) view.findViewById(R.id.vitalsLinearLayout);
            patientName = (TextView) view.findViewById(R.id.patientName);
            bedNo = (TextView) view.findViewById(R.id.bedNo);
            countPleth = (TextView) view.findViewById(R.id.countPleth); // THIS IS SP02
            countResp = (TextView) view.findViewById(R.id.countResp);
            countCvp = (TextView) view.findViewById(R.id.countCvp);
            countIcp = (TextView) view.findViewById(R.id.countIcp);
            countPap = (TextView) view.findViewById(R.id.countPap);
            countSystolicPressure = (TextView) view.findViewById(R.id.countSystolicPressure);
            countT1 = (TextView) view.findViewById(R.id.countt1);
            countT2 = (TextView) view.findViewById(R.id.countt2);
            countPulse = (TextView) view.findViewById(R.id.countPulse);
            tvOrderHistory = (TextView) view.findViewById(R.id.tvOrderHistory);
            vitalsMainTagCount = (CustomTextView) view.findViewById(R.id.vitalsMainTagCount);
            mLinearLayoutItemDashboard = (LinearLayout) view.findViewById(R.id.linearLayoutItemDashboard);
            order = (TextView)view.findViewById(R.id.order);
            mHorizontalView = (LinearLayout)view.findViewById(R.id.horizontalView);

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
                    view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Red));
                    TextView textView = (TextView) view.findViewById(R.id.countPleth);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    //  view.setTextColor(Color.WHITE);
                } else {
                    view.setBackgroundColor(Color.BLACK);
                    TextView textView = (TextView) view.findViewById(R.id.countPleth);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.parrot_green_color));
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
        }, 200);
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
                    view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));

                    TextView textView = (TextView) view.findViewById(R.id.countPulse);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                } else {
                    view.setBackgroundColor(Color.BLACK);
                    TextView textView = (TextView) view.findViewById(R.id.countPulse);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.parrot_green_color));
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
   /* private void loadAnimationT(final View view, final VitalDetails dataObject) {
        *//*Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);*//*

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataObject.isAnimated()) {
                    view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));

                    TextView textView = (TextView) view.findViewById(R.id.countPulse);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                } else {
                    view.setBackgroundColor(Color.BLACK);
                    TextView textView = (TextView) view.findViewById(R.id.countPulse);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.parrot_green_color));
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
    }*/

}
