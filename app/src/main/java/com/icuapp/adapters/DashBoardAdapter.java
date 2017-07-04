package com.icuapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.icuapp.R;
import com.icuapp.model.Patients;
import com.icuapp.model.vitals.VitalDetails;
import com.icuapp.util.AppConstants;
import com.icuapp.util.CommonMethods;
import java.util.ArrayList;


import static android.support.v7.recyclerview.R.styleable.RecyclerView;


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
        final Patients patientObject = mSelectedPatients.get(position);
        ArrayList<VitalDetails> vitalInfo = AppConstants.getVitalInfo(CommonMethods.generateEvenNumber());

        viewHolder.patientName.setText(patientObject.getPatientName());
        viewHolder.bedNo.setText("Bed No.:" + patientObject.getBedNo());

        for (VitalDetails dataObject :
                vitalInfo) {
            String name = dataObject.getName();
            String value = dataObject.getValue();
            if (name.equalsIgnoreCase("Pleth")) {
                viewHolder.countPleth.setText(value);
            } else if (name.equalsIgnoreCase("SPO2")) {
                viewHolder.countResp.setText(value);

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
            } else if (name.equalsIgnoreCase("Systolic Pressure")) {
                viewHolder.countSystolicPressure.setText(value);
            } else if (name.equalsIgnoreCase("T1")) {
                viewHolder.countT1.setText(value);
            } else if (name.equalsIgnoreCase("T2")) {
                viewHolder.countT2.setText(value);
            }
        }

        /*
        if (patientObject.isAnimated()) {
            holder.mcountOfSPO2.setBackgroundColor(Color.YELLOW);
            holder.mcountOfSPO2.setTextColor(Color.BLACK);
        } else {
            holder.mcountOfSPO2.setBackgroundColor(Color.BLACK);
            holder.mcountOfSPO2.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (patientObject.isAnimated()) {
                    movie.setAnimated(false);
                } else {
                    movie.setAnimated(true);
                }
                notifyItemChanged(position);
                //  drawable.start();
            }
        }, 500);*/
    }

/*
    private void setAnimation(DashBoardViewHolder holder, final VitalDetails dataObject, String vitalName, final int position, String range) {
      if(vitalName.equals("Pulse")&&range.equals(NORMAL)) {
          if (dataObject.isAnimated()) {
              holder.countPulse.setBackgroundColor(Color.BLUE);
              holder.countPulse.setTextColor(Color.WHITE);
          } else {
              holder.countPulse.setBackgroundColor(Color.BLACK);
              holder.countPulse.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
          }
         handler = new Handler();
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  if (dataObject.isAnimated()) {
                      dataObject.setAnimated(false);
                  } else {
                      dataObject.setAnimated(true);
                  }
                  notifyItemChanged(position);
                  //  drawable.start();
              }
          }, 500);

      }else if(vitalName.equals("Pleth")&&range.equals(MEDIUM)){
          if (dataObject.isAnimated()) {
              holder.countPulse.setBackgroundColor(Color.YELLOW);
              holder.countPulse.setTextColor(Color.BLACK);
          } else {
              holder.countPulse.setBackgroundColor(Color.BLACK);
              holder.countPulse.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
          }
          handler = new Handler();
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  if (dataObject.isAnimated()) {
                      dataObject.setAnimated(false);
                  } else {
                      dataObject.setAnimated(true);
                  }
                  notifyItemChanged(position);
                  //  drawable.start();
              }
          }, 500);
      }
      else if(vitalName.equals("Pleth")&&range.equals(HIGH)){
          if (dataObject.isAnimated()) {
              holder.countPulse.setBackgroundColor(Color.RED);
              holder.countPulse.setTextColor(Color.BLACK);
          } else {
              holder.countPulse.setBackgroundColor(Color.BLACK);
              holder.countPulse.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
          }
          handler = new Handler();
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  if (dataObject.isAnimated()) {
                      dataObject.setAnimated(false);
                  } else {
                      dataObject.setAnimated(true);
                  }
                  notifyItemChanged(position);
                  //  drawable.start();
              }
          }, 500);
      }
    }
*/

    @Override
    public int getItemCount() {
        return mSelectedPatients.size();
    }

    public class DashBoardViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName, countPleth, countResp, countCvp, countIcp, countPap, countSystolicPressure, countT1, countT2;
        public TextView countPulse, bedNo;

        public DashBoardViewHolder(View view) {
            super(view);
            patientName = (TextView) view.findViewById(R.id.patientName);
            bedNo = (TextView) view.findViewById(R.id.bedNo);
            countPleth = (TextView) view.findViewById(R.id.countPleth);
            countResp = (TextView) view.findViewById(R.id.countResp);
            countCvp = (TextView) view.findViewById(R.id.countCvp);
            countIcp = (TextView) view.findViewById(R.id.countIcp);
            countPap = (TextView) view.findViewById(R.id.countPap);
            countSystolicPressure = (TextView) view.findViewById(R.id.countSystolicPressure);
            countT1 = (TextView) view.findViewById(R.id.countt1);
            countT2 = (TextView) view.findViewById(R.id.countt2);
            countPulse = (TextView) view.findViewById(R.id.countPulse);
        }
    }
}