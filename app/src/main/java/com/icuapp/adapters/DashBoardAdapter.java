package com.icuapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.icuapp.customesViews.CustomTextView;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.Patients;
import com.icuapp.model.vitals.VitalCriticalDataOfPatient;
import com.icuapp.model.vitals.VitalDetails;
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

    Handler handler;
    Context mContext;

    public DashBoardAdapter(Context context, ArrayList<Patients> mSelectedPatients) {
        this.mContext = context;
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

                    viewHolder.vitalsMainTagCount.setBackgroundResource(R.drawable.curve_fill_red_bg);
                    viewHolder.vitalsMainTagCount.setText("***SPO2 <80 " + CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm"));
                    loadAnimation(viewHolder.countPleth, dataObject);
                    //------------
                    VitalCriticalDataOfPatient vitalCriticalDataOfPatient = AppConstants.vitalCriticalDataReportOfPatient.get(patientObject.getBedNo());
                    if (vitalCriticalDataOfPatient != null) {
                        vitalCriticalDataOfPatient.setPatient(patientObject);
                        vitalCriticalDataOfPatient.setVitalInfo(vitalInfo);
                        vitalCriticalDataOfPatient.setPelthOrSPO2CriticalTimeStamp(CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss"));
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(), vitalCriticalDataOfPatient);
                    } else {
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(),
                                new VitalCriticalDataOfPatient(patientObject, vitalInfo, CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss"), null));
                    }
                    //---------------

                    viewHolder.mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mVitalsLinearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.curve_fill_red_bg));
                    viewHolder.vitalsMainTagCount.setText("***SPO2 <80");
                    loadAnimation(viewHolder.countPleth, dataObject);
                    dialogList.add("***SPO2 <80");

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
                    viewHolder.mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mVitalsLinearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.curve_fill_yellow_bg));
                    viewHolder.vitalsMainTagCount.setTextColor(ContextCompat.getColor(mContext, R.color.black));


                    //------------------
                    VitalCriticalDataOfPatient vitalCriticalDataOfPatient = AppConstants.vitalCriticalDataReportOfPatient.get(patientObject.getBedNo());

                    if (vitalCriticalDataOfPatient != null) {
                        vitalCriticalDataOfPatient.setPatient(patientObject);
                        vitalCriticalDataOfPatient.setVitalInfo(vitalInfo);
                        vitalCriticalDataOfPatient.setHRCriticalTimeStamp(CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss"));
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(), vitalCriticalDataOfPatient);
                    } else {
                        AppConstants.vitalCriticalDataReportOfPatient.put(patientObject.getBedNo(),
                                new VitalCriticalDataOfPatient(patientObject, vitalInfo, null, CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "HH:mm:ss")));
                    }
                    //------------------

                    viewHolder.vitalsMainTagCount.setText("***Pulse > 120");
                    dialogList.add("***Pulse > 120");

                }
            } else if (name.equalsIgnoreCase("Systolic Pressure")) {
                viewHolder.countSystolicPressure.setText(value);
            } else if (name.equalsIgnoreCase("T1")) {
                viewHolder.countT1.setText(value);
                if (formattedValue > 38) {
                    viewHolder.mVitalsLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mVitalsLinearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.curve_fill_blue_bg));
                    viewHolder.vitalsMainTagCount.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    viewHolder.vitalsMainTagCount.setText("* T Rect High > 38.0");
                    dialogList.add("* T Rect High > 38.0");
                }
            } else if (name.equalsIgnoreCase("T2")) {
                viewHolder.countT2.setText(value);
            }
        }
        viewHolder.vitalsMainTagCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.showAlertDialog(mContext, "Bed No." + patientObject.getBedNo() + " " + patientObject.getPatientName(), dialogList);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mSelectedPatients.size();
    }

    public class DashBoardViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName, countPleth, countResp, countCvp, countIcp, countPap, countSystolicPressure, countT1, countT2;
        public TextView countPulse, bedNo;
        public CustomTextView vitalsMainTagCount;
        public LinearLayout mVitalsLinearLayout;


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
            vitalsMainTagCount = (CustomTextView) view.findViewById(R.id.vitalsMainTagCount);
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
                    //  view.setTextColor(Color.WHITE);
                } else {
                    view.setBackgroundColor(Color.BLACK);
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
}
