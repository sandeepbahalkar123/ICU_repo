package com.icuapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.icuapp.model.Movie;
import com.icuapp.R;
import com.icuapp.util.CommonMethods;

import java.util.List;
import java.util.Timer;



public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.MyViewHolder> {

    private Activity activity;
    private List<Movie> moviesList;
    private  int i;
    Context mContext;

    final AnimationDrawable drawable = new AnimationDrawable();
    final Handler handler = new Handler();
   private Timer myTimer = new Timer();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView mcountOfSPO2;
        public TextView mVitalsCount;

 
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.patientName);
            mcountOfSPO2 = (TextView) view.findViewById(R.id.countPulse);
            mVitalsCount = (TextView) view.findViewById(R.id.vitalsCount);
        }
    }

    public DashBoardAdapter(List<Movie> moviesList, Activity activity, Context context) {
        this.mContext = context;
        this.activity = activity;
        this.moviesList = moviesList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_item_layout, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Movie movie = moviesList.get(position);

        if(movie.isAnimated()){
       holder.mcountOfSPO2.setBackgroundColor(Color.YELLOW);
            holder.mcountOfSPO2.setTextColor(Color.BLACK);
        }
        else {
            holder.mcountOfSPO2.setBackgroundColor(Color.BLACK);
            holder.mcountOfSPO2.setTextColor(mContext.getResources().getColor(R.color.parrot_green_color));
       }
        holder.mVitalsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.showAlertDialog(mContext,"Bed No,1");
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(movie.isAnimated()){
                    movie.setAnimated(false);
                }
                else {
                    movie.setAnimated(true);
                }
                notifyItemChanged(position);
              //  drawable.start();
            }
        }, 500);
    }
 
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}