package com.icuapp.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icuapp.R;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.vitals.VitalDetails;
import com.icuapp.util.AppConstants;
import com.icuapp.util.CommonMethods;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static final double MAX_X = 110;
    private static final double MAX_X_NEXT = 320;

    private LineGraphSeries<DataPoint> mSeries_1_II = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_1_Pleth = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_1_Resp = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_1_Art = new LineGraphSeries<>();

    private double series_1_XValue_II = 0d;
    private double series_1_XValue_Pleth = 0d;
    private double series_1_XValue_Resp = 0d;
    private double series_1_XValue_Art = 0d;

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

    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;

    @BindView(R.id.respFixedLayout)
    RelativeLayout respFixedLayout;
    @BindView(R.id.artFixLayout)
    RelativeLayout artFixLayout;

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
        String vitalType = intent.getStringExtra("VitalType");
        mTextViewTimeDate.setText(currentDate + " " + currentTime + "  " + vitalType);
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

        // Ganesh Added

        for (int i = -3; i < 4; i++) {
            final View view = LayoutInflater.from(this)
                    .inflate(R.layout.timetextview, timeLayout, false);
            TextView timeView = (TextView) view.findViewById(R.id.timeTextView);
            String time = CommonMethods.getCalculatedSeconds("HH:mm:ss", i);
            timeView.setText(time);
            if (currentTime.equals(time))
                timeView.setTextColor(Color.RED);
            timeLayout.addView(view);
        }

        GraphView mGraph_II = (GraphView) findViewById(R.id.graph_II);
        GraphView mGraph_Pleth = (GraphView) findViewById(R.id.graph_Pleth);
        GraphView mGraph_Resp = (GraphView) findViewById(R.id.graph_Resp);
        GraphView mGraph_Art = (GraphView) findViewById(R.id.graph_Art);

        if (vitalType.contains("SPO2")) {
            mGraph_Art.setVisibility(View.VISIBLE);
            artFixLayout.setVisibility(View.VISIBLE);
            mGraph_Resp.setVisibility(View.GONE);
            respFixedLayout.setVisibility(View.GONE);

        } else {
            mGraph_Art.setVisibility(View.GONE);
            artFixLayout.setVisibility(View.GONE);
            mGraph_Resp.setVisibility(View.VISIBLE);
            respFixedLayout.setVisibility(View.VISIBLE);
        }

        setGraphProperties(mGraph_II);
        setGraphPropertiesNext(mGraph_Pleth);
        setGraphPropertiesNext(mGraph_Resp);
        setGraphPropertiesNext(mGraph_Art);

        // II

        mGraph_II.getViewport().setMinY(-1.5d);
        mGraph_II.getViewport().setMaxY(1.5d);
        mGraph_II.addSeries(mSeries_1_II);
        mSeries_1_II.setThickness(2);
        mSeries_1_II.setColor(getResources().getColor(R.color.parrot));


        // Pleth

        mGraph_Pleth.getViewport().setMinY(-.3d);
        mGraph_Pleth.getViewport().setMaxY(.3d);
        mGraph_Pleth.addSeries(mSeries_1_Pleth);
        mSeries_1_Pleth.setThickness(2);
        mSeries_1_Pleth.setColor(getResources().getColor(R.color.pleth));

        // Resp

        mGraph_Resp.getViewport().setMinY(-1);  // change
        mGraph_Resp.getViewport().setMaxY(2); // change
        mGraph_Resp.addSeries(mSeries_1_Resp);
        mSeries_1_Resp.setThickness(2);
        mSeries_1_Resp.setColor(getResources().getColor(R.color.yellow));

        // Art

        mGraph_Art.getViewport().setMinY(-50); // change
        mGraph_Art.getViewport().setMaxY(200); // change
        mGraph_Art.addSeries(mSeries_1_Art);
        mSeries_1_Art.setThickness(2);
        mSeries_1_Art.setColor(Color.RED);

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // End

    }

    private void init() throws IOException {
        String[] arrayPleth = readFromAssets("pleth").split(","); // 5
        String[] arrayResp = readFromAssets("Resp").split(","); // 5
        String[] arrayArt = readFromAssets("Art").split(","); // 5

        final double[] seriesArrayII = {0.4d, 0d, 0d, -0.4d, 1.5d, -0.5d, 0d, 0d, 0d, 0.3d, 0.6d, 0.3d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 2

        final ArrayList<Double> seriesArrayPleth = new ArrayList<>();
        final ArrayList<Double> seriesArrayResp = new ArrayList<>();
        final ArrayList<Double> seriesArrayArt = new ArrayList<>();

        for (int i = 0; i < arrayPleth.length; i += 7)
            seriesArrayPleth.add(Double.parseDouble(arrayPleth[i]));

        for (int i = 0; i < arrayResp.length; i += 7)
            seriesArrayResp.add(Double.parseDouble(arrayResp[i]));

        for (int i = 0; i < arrayArt.length; i += 7)
            seriesArrayArt.add(Double.parseDouble(arrayArt[i]));

        // II

        for (int i = 0; i < MAX_X; i++) {
            double random = getRandomII(seriesArrayII);
            series_1_XValue_II += 1d;
            mSeries_1_II.appendData(new DataPoint(series_1_XValue_II, random), false, (int) MAX_X);
        }

        for (int i = 0; i < MAX_X_NEXT; i++) {
            // Pleth

            double random = getRandomPleth(seriesArrayPleth);
            series_1_XValue_Pleth += 1d;
            mSeries_1_Pleth.appendData(new DataPoint(series_1_XValue_Pleth, random), false, (int) MAX_X_NEXT);

            // Resp

            random = getRandomResp(seriesArrayResp); // change
            series_1_XValue_Resp += 1d;
            mSeries_1_Resp.appendData(new DataPoint(series_1_XValue_Resp, random), false, (int) MAX_X_NEXT);

            // Art

            random = getRandomArt(seriesArrayArt); // change
            series_1_XValue_Art += 1d;
            mSeries_1_Art.appendData(new DataPoint(series_1_XValue_Art, random), false, (int) MAX_X_NEXT);
        }

    }

    int arrayIndexII = 0;
    int arrayIndexPleth = 0;
    int arrayIndexResp = 0;
    int arrayIndexArt = 0;

    private double getRandomII(double[] seriesArray) {
        if (arrayIndexII < seriesArray.length - 1)
            arrayIndexII++;
        else arrayIndexII = 0;
        return seriesArray[arrayIndexII] > 9 ? seriesArray[arrayIndexII] / 10 : seriesArray[arrayIndexII];
    }

    private double getRandomPleth(ArrayList<Double> seriesArray) {
        if (arrayIndexPleth < seriesArray.size() - 1)
            arrayIndexPleth++;
        else arrayIndexPleth = 0;
        return seriesArray.get(arrayIndexPleth);
    }

    private double getRandomResp(ArrayList<Double> seriesArray) {
        if (arrayIndexResp < seriesArray.size() - 1)
            arrayIndexResp++;
        else arrayIndexResp = 0;
        return seriesArray.get(arrayIndexResp);
    }

    private double getRandomArt(ArrayList<Double> seriesArray) {
        if (arrayIndexArt < seriesArray.size() - 1)
            arrayIndexArt++;
        else arrayIndexArt = 0;
        return seriesArray.get(arrayIndexArt);
    }

    private String readFromAssets(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

    private void setGraphProperties(GraphView graphView) {
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(MAX_X);
    }

    private void setGraphPropertiesNext(GraphView graphView) {
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(MAX_X_NEXT);
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
