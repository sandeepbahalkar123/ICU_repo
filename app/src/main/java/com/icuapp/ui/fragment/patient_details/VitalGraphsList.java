package com.icuapp.ui.fragment.patient_details;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icuapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VitalGraphsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VitalGraphsList extends Fragment {

    public static final double MAX_X = 100;

    private static final double MAX_X_NEXT = 280;

    private final Handler mHandler = new Handler();
    private Runnable mTimer;

    private LineGraphSeries<DataPoint> mSeries_1_I = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_I = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_II = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_II = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_III = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_III = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_AVR = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_AVR = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_AVL = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_AVL = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_AVF = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_AVF = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_V1 = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_V1 = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Pleth = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_Pleth = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Resp = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_Resp = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Art = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_Art = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_PAP = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_PAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_CVP = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_CVP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_ICP = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_ICP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Ao = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_Ao = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_UAP = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_UAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_BAP = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_BAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_FAP = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_FAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_CO2 = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_CO2 = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_O2 = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_O2 = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_N2O = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_N2O = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_AA = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mSeries_2_AA = new LineGraphSeries<>();

    private double series_1_XValue_I = 0d;
    private double series_2_XValue_I = (MAX_X - MAX_X * 2);

    private double series_1_XValue_II = 0d;
    private double series_2_XValue_II = (MAX_X - MAX_X * 2);

    private double series_1_XValue_III = 0d;
    private double series_2_XValue_III = (MAX_X - MAX_X * 2);

    private double series_1_XValue_AVR = 0d;
    private double series_2_XValue_AVR = (MAX_X - MAX_X * 2);

    private double series_1_XValue_AVL = 0d;
    private double series_2_XValue_AVL = (MAX_X - MAX_X * 2);

    private double series_1_XValue_AVF = 0d;
    private double series_2_XValue_AVF = (MAX_X - MAX_X * 2);

    private double series_1_XValue_V1 = 0d;
    private double series_2_XValue_V1 = (MAX_X - MAX_X * 2);

    private double series_1_XValue_Pleth = 0d;
    private double series_2_XValue_Pleth = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_Resp = 0d;
    private double series_2_XValue_Resp = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_Art = 0d;
    private double series_2_XValue_Art = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_PAP = 0d;
    private double series_2_XValue_PAP = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_CVP = 0d;
    private double series_2_XValue_CVP = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_ICP = 0d;
    private double series_2_XValue_ICP = (MAX_X_NEXT - MAX_X_NEXT * 2);

    // All same.

    private double series_1_XValue_Ao = 0d;
    private double series_2_XValue_Ao = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_UAP = 0d;
    private double series_2_XValue_UAP = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_BAP = 0d;
    private double series_2_XValue_BAP = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_FAP = 0d;
    private double series_2_XValue_FAP = (MAX_X_NEXT - MAX_X_NEXT * 2);

    // All same end.

    // next same

    private double series_1_XValue_CO2 = 0d;
    private double series_2_XValue_CO2 = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_O2 = 0d;
    private double series_2_XValue_O2 = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_N2O = 0d;
    private double series_2_XValue_N2O = (MAX_X_NEXT - MAX_X_NEXT * 2);

    private double series_1_XValue_AA = 0d;
    private double series_2_XValue_AA = (MAX_X_NEXT - MAX_X_NEXT * 2);

    public VitalGraphsList() {
        // Required empty public constructor
    }

    public static VitalGraphsList newInstance() {
        VitalGraphsList fragment = new VitalGraphsList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_vital_graphs_list, container, false);
        // init

        GraphView mGraph_I = (GraphView) rootView.findViewById(R.id.graph_I);
        GraphView mGraph_II = (GraphView) rootView.findViewById(R.id.graph_II);
        GraphView mGraph_III = (GraphView) rootView.findViewById(R.id.graph_III);
        GraphView mGraph_AVR = (GraphView) rootView.findViewById(R.id.graph_AVR);
        GraphView mGraph_AVL = (GraphView) rootView.findViewById(R.id.graph_AVL);
        GraphView mGraph_AVF = (GraphView) rootView.findViewById(R.id.graph_AVF);
        GraphView mGraph_V1 = (GraphView) rootView.findViewById(R.id.graph_V1);
        GraphView mGraph_Pleth = (GraphView) rootView.findViewById(R.id.graph_Pleth);
        GraphView mGraph_Resp = (GraphView) rootView.findViewById(R.id.graph_Resp);
        GraphView mGraph_Art = (GraphView) rootView.findViewById(R.id.graph_Art);
        GraphView mGraph_PAP = (GraphView) rootView.findViewById(R.id.graph_PAP);
        GraphView mGraph_CVP = (GraphView) rootView.findViewById(R.id.graph_CVP);
        GraphView mGraph_ICP = (GraphView) rootView.findViewById(R.id.graph_ICP);
        GraphView mGraph_Ao = (GraphView) rootView.findViewById(R.id.graph_Ao);
        GraphView mGraph_UAP = (GraphView) rootView.findViewById(R.id.graph_UAP);
        GraphView mGraph_BAP = (GraphView) rootView.findViewById(R.id.graph_BAP);
        GraphView mGraph_FAP = (GraphView) rootView.findViewById(R.id.graph_FAP);
        GraphView mGraph_CO2 = (GraphView) rootView.findViewById(R.id.graph_CO2);
        GraphView mGraph_O2 = (GraphView) rootView.findViewById(R.id.graph_O2);
        GraphView mGraph_N2O = (GraphView) rootView.findViewById(R.id.graph_N2O);
        GraphView mGraph_AA = (GraphView) rootView.findViewById(R.id.graph_AA);

        // set Common Properties
        setGraphProperties(mGraph_I);
        setGraphProperties(mGraph_II);
        setGraphProperties(mGraph_III);
        setGraphProperties(mGraph_AVR);
        setGraphProperties(mGraph_AVL);
        setGraphProperties(mGraph_AVF);
        setGraphProperties(mGraph_V1);
        // set Common Next Properties
        setGraphPropertiesNext(mGraph_Pleth);
        setGraphPropertiesNext(mGraph_Resp);
        setGraphPropertiesNext(mGraph_Art);
        setGraphPropertiesNext(mGraph_PAP);
        setGraphPropertiesNext(mGraph_CVP);
        setGraphPropertiesNext(mGraph_ICP);

        // All Same

        setGraphPropertiesNext(mGraph_Ao);
        setGraphPropertiesNext(mGraph_UAP);
        setGraphPropertiesNext(mGraph_BAP);
        setGraphPropertiesNext(mGraph_FAP);

        // next

        setGraphPropertiesNext(mGraph_CO2);
        setGraphPropertiesNext(mGraph_O2);
        setGraphPropertiesNext(mGraph_N2O);
        setGraphPropertiesNext(mGraph_AA);

        // I

        mGraph_I.getViewport().setMinY(-1.5d);
        mGraph_I.getViewport().setMaxY(1.5d);
        mGraph_I.addSeries(mSeries_2_I);
        mGraph_I.addSeries(mSeries_1_I);
        mSeries_1_I.setThickness(2);
        mSeries_2_I.setThickness(2);
        mSeries_1_I.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_I.setColor(getResources().getColor(R.color.parrot));

        // II

        mGraph_II.getViewport().setMinY(-1.5d);
        mGraph_II.getViewport().setMaxY(1.5d);
        mGraph_II.addSeries(mSeries_2_II);
        mGraph_II.addSeries(mSeries_1_II);
        mSeries_1_II.setThickness(2);
        mSeries_2_II.setThickness(2);
        mSeries_1_II.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_II.setColor(getResources().getColor(R.color.parrot));

        // III

        mGraph_III.getViewport().setMinY(-1.5d);
        mGraph_III.getViewport().setMaxY(1.5d);
        mGraph_III.addSeries(mSeries_2_III);
        mGraph_III.addSeries(mSeries_1_III);
        mSeries_1_III.setThickness(2);
        mSeries_2_III.setThickness(2);
        mSeries_1_III.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_III.setColor(getResources().getColor(R.color.parrot));

        // AVR

        mGraph_AVR.getViewport().setMinY(-1.5d);
        mGraph_AVR.getViewport().setMaxY(1.5d);
        mGraph_AVR.addSeries(mSeries_2_AVR);
        mGraph_AVR.addSeries(mSeries_1_AVR);
        mSeries_1_AVR.setThickness(2);
        mSeries_2_AVR.setThickness(2);
        mSeries_1_AVR.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_AVR.setColor(getResources().getColor(R.color.parrot));

        // AVL

        mGraph_AVL.getViewport().setMinY(-1.5d);
        mGraph_AVL.getViewport().setMaxY(1.5d);
        mGraph_AVL.addSeries(mSeries_2_AVL);
        mGraph_AVL.addSeries(mSeries_1_AVL);
        mSeries_1_AVL.setThickness(2);
        mSeries_2_AVL.setThickness(2);
        mSeries_1_AVL.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_AVL.setColor(getResources().getColor(R.color.parrot));

        // AVF

        mGraph_AVF.getViewport().setMinY(-1.5d);
        mGraph_AVF.getViewport().setMaxY(1.5d);
        mGraph_AVF.addSeries(mSeries_2_AVF);
        mGraph_AVF.addSeries(mSeries_1_AVF);
        mSeries_1_AVF.setThickness(2);
        mSeries_2_AVF.setThickness(2);
        mSeries_1_AVF.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_AVF.setColor(getResources().getColor(R.color.parrot));

        // V1

        mGraph_V1.getViewport().setMinY(-1.5d);
        mGraph_V1.getViewport().setMaxY(1.5d);
        mGraph_V1.addSeries(mSeries_2_V1);
        mGraph_V1.addSeries(mSeries_1_V1);
        mSeries_1_V1.setThickness(2);
        mSeries_2_V1.setThickness(2);
        mSeries_1_V1.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_V1.setColor(getResources().getColor(R.color.parrot));

        // Pleth

        mGraph_Pleth.getViewport().setMinY(-.3d);
        mGraph_Pleth.getViewport().setMaxY(.3d);
        mGraph_Pleth.addSeries(mSeries_2_Pleth);
        mGraph_Pleth.addSeries(mSeries_1_Pleth);
        mSeries_1_Pleth.setThickness(2);
        mSeries_2_Pleth.setThickness(2);
        mSeries_1_Pleth.setColor(getResources().getColor(R.color.pleth));
        mSeries_2_Pleth.setColor(getResources().getColor(R.color.pleth));

        // Resp

        mGraph_Resp.getViewport().setMinY(-1);  // change
        mGraph_Resp.getViewport().setMaxY(2); // change
        mGraph_Resp.addSeries(mSeries_2_Resp);
        mGraph_Resp.addSeries(mSeries_1_Resp);
        mSeries_1_Resp.setThickness(2);
        mSeries_2_Resp.setThickness(2);
        mSeries_1_Resp.setColor(getResources().getColor(R.color.yellow));
        mSeries_2_Resp.setColor(getResources().getColor(R.color.yellow));

        // Art

        mGraph_Art.getViewport().setMinY(-50); // change
        mGraph_Art.getViewport().setMaxY(200); // change
        mGraph_Art.addSeries(mSeries_2_Art);
        mGraph_Art.addSeries(mSeries_1_Art);
        mSeries_1_Art.setThickness(2);
        mSeries_2_Art.setThickness(2);
        mSeries_1_Art.setColor(Color.RED);
        mSeries_2_Art.setColor(Color.RED);

        // PAP

        mGraph_PAP.getViewport().setMinY(0);
        mGraph_PAP.getViewport().setMaxY(70);
        mGraph_PAP.addSeries(mSeries_2_PAP);
        mGraph_PAP.addSeries(mSeries_1_PAP);
        mSeries_1_PAP.setThickness(2);
        mSeries_2_PAP.setThickness(2);
        mSeries_1_PAP.setColor(getResources().getColor(R.color.yellow));
        mSeries_2_PAP.setColor(getResources().getColor(R.color.yellow));

        // CVP

        mGraph_CVP.getViewport().setMinY(-6);
        mGraph_CVP.getViewport().setMaxY(19);
        mGraph_CVP.addSeries(mSeries_2_CVP);
        mGraph_CVP.addSeries(mSeries_1_CVP);
        mSeries_1_CVP.setThickness(2);
        mSeries_2_CVP.setThickness(2);
        mSeries_1_CVP.setColor(getResources().getColor(R.color.cvp));
        mSeries_2_CVP.setColor(getResources().getColor(R.color.cvp));

        // ICP

        mGraph_ICP.getViewport().setMinY(-7);
        mGraph_ICP.getViewport().setMaxY(18);
        mGraph_ICP.addSeries(mSeries_2_ICP);
        mGraph_ICP.addSeries(mSeries_1_ICP);
        mSeries_1_ICP.setThickness(2);
        mSeries_2_ICP.setThickness(2);
        mSeries_1_ICP.setColor(getResources().getColor(R.color.cvp));
        mSeries_2_ICP.setColor(getResources().getColor(R.color.cvp));

        //////////

        // Ao

        mGraph_Ao.getViewport().setMinY(10);
        mGraph_Ao.getViewport().setMaxY(150);
        mGraph_Ao.addSeries(mSeries_2_Ao);
        mGraph_Ao.addSeries(mSeries_1_Ao);
        mSeries_1_Ao.setThickness(2);
        mSeries_2_Ao.setThickness(2);
        mSeries_1_Ao.setColor(Color.RED);
        mSeries_2_Ao.setColor(Color.RED);

        // UAP

        mGraph_UAP.getViewport().setMinY(10);
        mGraph_UAP.getViewport().setMaxY(150);
        mGraph_UAP.addSeries(mSeries_2_UAP);
        mGraph_UAP.addSeries(mSeries_1_UAP);
        mSeries_1_UAP.setThickness(2);
        mSeries_2_UAP.setThickness(2);
        mSeries_1_UAP.setColor(Color.RED);
        mSeries_2_UAP.setColor(Color.RED);

        // BAP

        mGraph_BAP.getViewport().setMinY(10);
        mGraph_BAP.getViewport().setMaxY(150);
        mGraph_BAP.addSeries(mSeries_2_BAP);
        mGraph_BAP.addSeries(mSeries_1_BAP);
        mSeries_1_BAP.setThickness(2);
        mSeries_2_BAP.setThickness(2);
        mSeries_1_BAP.setColor(Color.RED);
        mSeries_2_BAP.setColor(Color.RED);

        // FAP

        mGraph_FAP.getViewport().setMinY(10);
        mGraph_FAP.getViewport().setMaxY(150);
        mGraph_FAP.addSeries(mSeries_2_FAP);
        mGraph_FAP.addSeries(mSeries_1_FAP);
        mSeries_1_FAP.setThickness(2);
        mSeries_2_FAP.setThickness(2);
        mSeries_1_FAP.setColor(Color.RED);
        mSeries_2_FAP.setColor(Color.RED);

        // next same

        // CO2

        mGraph_CO2.getViewport().setMinY(-6);
        mGraph_CO2.getViewport().setMaxY(63);
        mGraph_CO2.addSeries(mSeries_2_CO2);
        mGraph_CO2.addSeries(mSeries_1_CO2);
        mSeries_1_CO2.setThickness(2);
        mSeries_2_CO2.setThickness(2);
        mSeries_1_CO2.setColor(getResources().getColor(R.color.yellow));
        mSeries_2_CO2.setColor(getResources().getColor(R.color.yellow));

        // O2

        mGraph_O2.getViewport().setMinY(-45);
        mGraph_O2.getViewport().setMaxY(110);
        mGraph_O2.addSeries(mSeries_2_O2);
        mGraph_O2.addSeries(mSeries_1_O2);
        mSeries_1_O2.setThickness(2);
        mSeries_2_O2.setThickness(2);
        mSeries_1_O2.setColor(getResources().getColor(R.color.parrot));
        mSeries_2_O2.setColor(getResources().getColor(R.color.parrot));

        // N2O

        mGraph_N2O.getViewport().setMinY(-140);
        mGraph_N2O.getViewport().setMaxY(70);
        mGraph_N2O.addSeries(mSeries_2_N2O);
        mGraph_N2O.addSeries(mSeries_1_N2O);
        mSeries_1_N2O.setThickness(2);
        mSeries_2_N2O.setThickness(2);
        mSeries_1_N2O.setColor(getResources().getColor(R.color.cvp));
        mSeries_2_N2O.setColor(getResources().getColor(R.color.cvp));

        // AA

        mGraph_AA.getViewport().setMinY(-150);
        mGraph_AA.getViewport().setMaxY(650);
        mGraph_AA.addSeries(mSeries_2_AA);
        mGraph_AA.addSeries(mSeries_1_AA);
        mSeries_1_AA.setThickness(2);
        mSeries_2_AA.setThickness(2);
        mSeries_1_AA.setColor(getResources().getColor(R.color.yellow));
        mSeries_2_AA.setColor(getResources().getColor(R.color.yellow));
        return rootView;
    }

    private void setGraphProperties(GraphView graphView) {
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(MAX_X);
    }

    private void setGraphPropertiesNext(GraphView graphView) {
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(MAX_X_NEXT);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromAssets(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(filename)));

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

    private void init() throws IOException {

        String[] arrayPleth = readFromAssets("pleth").split(","); // 5
        String[] arrayResp = readFromAssets("Resp").split(","); // 5
        String[] arrayArt = readFromAssets("Art").split(","); // 5
//        String[] arrayCVP = readFromAssets( "CVP").split(","); // 5
        String[] arrayPAP = readFromAssets("PAP").split(","); // 5
        String[] arrayCO2 = readFromAssets("CO2").split(",");  // 20

        final ArrayList<Double> seriesArrayPleth = new ArrayList<>();
        final ArrayList<Double> seriesArrayResp = new ArrayList<>();
        final ArrayList<Double> seriesArrayArt = new ArrayList<>();
        final ArrayList<Double> seriesArrayPAP = new ArrayList<>();
//        final ArrayList<Double> seriesArrayCVP = new ArrayList<>();
        final ArrayList<Double> seriesArrayCO2 = new ArrayList<>();

        for (int i = 0; i < arrayPleth.length; i += 7) {
            seriesArrayPleth.add(Double.parseDouble(arrayPleth[i]));
            seriesArrayPAP.add(Double.parseDouble(arrayPAP[i]));
//            seriesArrayCVP.add(Double.parseDouble(arrayCVP[i]));
        }

        for (int i = 0; i < arrayResp.length; i += 7)
            seriesArrayResp.add(Double.parseDouble(arrayResp[i]));

        for (int i = 0; i < arrayArt.length; i += 7)
            seriesArrayArt.add(Double.parseDouble(arrayArt[i]));

        for (int i = 0; i < arrayCO2.length; i += 20)
            seriesArrayCO2.add(Double.parseDouble(arrayCO2[i]));

//        final double[] seriesArray = {94d, 92d, 89d, 94d, 90d, 90d, 94d, 92d, 88d, 90d, 92d, 94d, 86d, 92d, 90d, 94d, 82d, 80d, 92d, 96d, 82d, 84d, 92d, 94d, 86d, 90d, 92d, 88d, 92d, 94d, 90d};
        final double[] seriesArrayI = {0.2d, 0d, 0d, -0.2d, 1.3d, -0.3d, 0d, 0d, 0d, 0.2d, 0.4d, 0.2d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 1
        final double[] seriesArrayII = {0.4d, 0d, 0d, -0.4d, 1.5d, -0.5d, 0d, 0d, 0d, 0.3d, 0.6d, 0.3d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 2
        final double[] seriesArrayIII = {0.1d, 0d, 0d, -0.1d, 0.6d, -0.2d, 0d, 0d, 0d, 0.3d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 3
        final double[] seriesArrayAVR = {-0.3d, 0d, 0d, 0.3d, -1.4d, 0.4d, 0d, 0d, 0d, -0.5d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 4
        final double[] seriesArrayAVL = {0.04d, 0d, 0d, -0.05d, 0.3d, -0.07d, 0d, 0d, 0d, 0.07d, 0.1d, 0.07d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 5
        final double[] seriesArrayAVF = {0.2d, 0d, 0d, -0.2d, 1.3d, -0.3d, 0d, 0d, 0d, 0.2d, 0.4d, 0.2d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 6
        final double[] seriesArrayV1 = {0.1d, 0d, 0d, 0.2d, -0.6d, 0.1d, 0d, 0d, 0d, 0d, 0.05d, 0.07d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d}; // 7

        final double[] seriesArrayCVP = {5, 5, 5, 5.5, 5.5, 5.5, 6, 6, 6, 6.5, 6.5, 6.5, 7, 7, 7, 7.5, 7.5, 7.5, 8, 8, 8, 8.5, 8.5, 8.5, 9, 9, 9, 9.5, 9.5, 9.5, 10, 10, 10, 9.5, 9.5, 9.5, 9, 9, 9, 8.5, 8.5, 8.5, 8, 8, 8, 7.5, 7.5, 7.5, 7, 7, 7, 6.5, 6.5, 6.5, 6, 6, 6, 5.5, 5.5, 5.5, 5, 5, 5, 5, 5, 5.5, 5.5, 5.5, 6, 6, 6, 6.5, 6.5, 6.5, 7, 7, 7, 6.5, 6.5, 6.5, 6, 6, 6, 5.5, 5.5, 5.5, 5, 5, 5, 5, 5, 5.5, 5.5, 5.5, 6, 6, 6, 6.5, 6.5, 6.5, 7, 7, 7, 7.5, 7.5, 7.5, 8, 8, 8, 8.5, 8.5, 8.5, 9, 9, 9, 9.5, 9.5, 9.5, 10, 10, 10, 9.5, 9.5, 9.5, 9, 9, 9, 8.5, 8.5, 8.5, 8, 8, 8, 7.5, 7.5, 7.5, 7, 7, 7, 6.5, 6.5, 6.5, 6, 6, 6, 5.5, 5.5, 5.5, 5, 5, 5, 5, 5, 5.5, 5.5, 5.5, 6, 6, 6, 6.5, 6.5, 6.5, 7, 7, 7, 6.5, 6.5, 6.5, 6, 6, 6, 5.5, 5.5, 5.5, 5, 5, 5, 5, 5, 5.5, 5.5, 5.5};

//        final double[] seriesArrayCVP = {5,5,5,5,5,6,6,6,7,7,7,8,8,8,9,9,9,10,10,10,11,11,11,12,12,12,13,13,13,14,14,14,15,15,15,16,16,16,17,17,17,18,18,18,17,17,17,16,16,16,15,15,15,14,14,14,13,13,13,12,12,12,11,11,11,10,10,10,9,9,9,8,8,8,7,7,7,6,6,6,5,5,5,5,5,6,6,6,7,7,7,8,8,8,9,9,9,10,10,10,11,11,11,12,12,12,13,13,13,14,14,14,13,13,13,12,12,12,11,11,11,10,10,10,9,9,9,8,8,8,7,7,7,6,6,6,5,5,5,5,5};

        final double[] seriesArrayAo = {91, 87, 83, 81, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 81, 86, 92, 98, 104, 110, 116, 122, 122, 122, 116, 115, 114, 114, 114, 111, 107, 103, 99, 95, 91, 87, 83, 81, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 81, 86, 92, 98, 104, 110, 116, 122, 122, 122, 116, 115, 114, 114, 114, 111, 107, 103, 99, 95, 91, 87, 83, 81, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 81, 86, 92, 98, 104, 110, 116, 122, 122, 122, 116, 115, 114, 114, 114, 111, 107, 103, 99, 95, 91, 87, 83};

//    double[] seriesArray = {0d, 0d, 0d, 0.2d, 0.2d, 0d, 0d, -0.2d, -0.2d,-0.2d, -0.2d, 0d, 0d, 0.2d, 0.2d, 0.2d, 0.2d, 0d, 0d, 0d,0d, 0d, 0d,}; // CVP

//    double[] seriesArray = {-1.4d, -1.38d, -1.38d, -1.39d, -1.4d, -1.4d, -1.4d, -1.44d, 1d, 1.01d, 1.02d, 1.03d, 1.04d, 1.05d,1.06d, 1.07d, 1.09d}; // CO2

        mTimer = new Runnable() {
            @Override
            public void run() {

                // I

                double random = getRandomI(seriesArrayI);

                if (series_1_XValue_I == (MAX_X * 2)) {
                    series_1_XValue_I = 0d;
                    mSeries_1_I.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_I, random)
                    });
                }

                series_1_XValue_I += 1d;
                mSeries_1_I.appendData(new DataPoint(series_1_XValue_I, random), false, (int) MAX_X - 2);

                if (series_2_XValue_I == (MAX_X * 2)) {
                    series_2_XValue_I = 0d;
                    mSeries_2_I.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_I, random)
                    });
                }

                series_2_XValue_I += 1d;
                mSeries_2_I.appendData(new DataPoint(series_2_XValue_I, random), false, (int) MAX_X - 2);

                // II

                random = getRandomII(seriesArrayII);

                if (series_1_XValue_II == (MAX_X * 2)) {
                    series_1_XValue_II = 0d;
                    mSeries_1_II.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_II, random)
                    });
                }

                series_1_XValue_II += 1d;
                mSeries_1_II.appendData(new DataPoint(series_1_XValue_II, random), false, (int) MAX_X - 2);

                if (series_2_XValue_II == (MAX_X * 2)) {
                    series_2_XValue_II = 0d;
                    mSeries_2_II.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_II, random)
                    });
                }

                series_2_XValue_II += 1d;
                mSeries_2_II.appendData(new DataPoint(series_2_XValue_II, random), false, (int) MAX_X - 2);

                // III

                random = getRandomIII(seriesArrayIII);

                if (series_1_XValue_III == (MAX_X * 2)) {
                    series_1_XValue_III = 0d;
                    mSeries_1_III.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_III, random)
                    });
                }

                series_1_XValue_III += 1d;
                mSeries_1_III.appendData(new DataPoint(series_1_XValue_III, random), false, (int) MAX_X - 2);

                if (series_2_XValue_III == (MAX_X * 2)) {
                    series_2_XValue_III = 0d;
                    mSeries_2_III.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_III, random)
                    });
                }

                series_2_XValue_III += 1d;
                mSeries_2_III.appendData(new DataPoint(series_2_XValue_III, random), false, (int) MAX_X - 2);

                // AVR

                random = getRandomAVR(seriesArrayAVR);

                if (series_1_XValue_AVR == (MAX_X * 2)) {
                    series_1_XValue_AVR = 0d;
                    mSeries_1_AVR.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_AVR, random)
                    });
                }

                series_1_XValue_AVR += 1d;
                mSeries_1_AVR.appendData(new DataPoint(series_1_XValue_AVR, random), false, (int) MAX_X - 2);

                if (series_2_XValue_AVR == (MAX_X * 2)) {
                    series_2_XValue_AVR = 0d;
                    mSeries_2_AVR.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_AVR, random)
                    });
                }

                series_2_XValue_AVR += 1d;
                mSeries_2_AVR.appendData(new DataPoint(series_2_XValue_AVR, random), false, (int) MAX_X - 2);

                // AVL

                random = getRandomAVL(seriesArrayAVL);

                if (series_1_XValue_AVL == (MAX_X * 2)) {
                    series_1_XValue_AVL = 0d;
                    mSeries_1_AVL.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_AVL, random)
                    });
                }

                series_1_XValue_AVL += 1d;
                mSeries_1_AVL.appendData(new DataPoint(series_1_XValue_AVL, random), false, (int) MAX_X - 2);

                if (series_2_XValue_AVL == (MAX_X * 2)) {
                    series_2_XValue_AVL = 0d;
                    mSeries_2_AVL.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_AVL, random)
                    });
                }

                series_2_XValue_AVL += 1d;
                mSeries_2_AVL.appendData(new DataPoint(series_2_XValue_AVL, random), false, (int) MAX_X - 2);

                // AVF

                random = getRandomAVF(seriesArrayAVF);

                if (series_1_XValue_AVF == (MAX_X * 2)) {
                    series_1_XValue_AVF = 0d;
                    mSeries_1_AVF.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_AVF, random)
                    });
                }

                series_1_XValue_AVF += 1d;
                mSeries_1_AVF.appendData(new DataPoint(series_1_XValue_AVF, random), false, (int) MAX_X - 2);

                if (series_2_XValue_AVF == (MAX_X * 2)) {
                    series_2_XValue_AVF = 0d;
                    mSeries_2_AVF.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_AVF, random)
                    });
                }

                series_2_XValue_AVF += 1d;
                mSeries_2_AVF.appendData(new DataPoint(series_2_XValue_AVF, random), false, (int) MAX_X - 2);

                // V1

                random = getRandomV1(seriesArrayV1);

                if (series_1_XValue_V1 == (MAX_X * 2)) {
                    series_1_XValue_V1 = 0d;
                    mSeries_1_V1.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_V1, random)
                    });
                }

                series_1_XValue_V1 += 1d;
                mSeries_1_V1.appendData(new DataPoint(series_1_XValue_V1, random), false, (int) MAX_X - 2);

                if (series_2_XValue_V1 == (MAX_X * 2)) {
                    series_2_XValue_V1 = 0d;
                    mSeries_2_V1.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_V1, random)
                    });
                }

                series_2_XValue_V1 += 1d;
                mSeries_2_V1.appendData(new DataPoint(series_2_XValue_V1, random), false, (int) MAX_X - 2);

                // Pleth

                random = getRandomPleth(seriesArrayPleth);

                if (series_1_XValue_Pleth == (MAX_X_NEXT * 2)) {
                    series_1_XValue_Pleth = 0d;
                    mSeries_1_Pleth.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_Pleth, random)
                    });
                }

                series_1_XValue_Pleth += 1d;
                mSeries_1_Pleth.appendData(new DataPoint(series_1_XValue_Pleth, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_Pleth == (MAX_X_NEXT * 2)) {
                    series_2_XValue_Pleth = 0d;
                    mSeries_2_Pleth.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_Pleth, random)
                    });
                }

                series_2_XValue_Pleth += 1d;
                mSeries_2_Pleth.appendData(new DataPoint(series_2_XValue_Pleth, random), false, (int) MAX_X_NEXT - 2);

                // Resp

                random = getRandomResp(seriesArrayResp); // change

                if (series_1_XValue_Resp == (MAX_X_NEXT * 2)) {
                    series_1_XValue_Resp = 0d;
                    mSeries_1_Resp.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_Resp, random)
                    });
                }

                series_1_XValue_Resp += 1d;
                mSeries_1_Resp.appendData(new DataPoint(series_1_XValue_Resp, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_Resp == (MAX_X_NEXT * 2)) {
                    series_2_XValue_Resp = 0d;
                    mSeries_2_Resp.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_Resp, random)
                    });
                }

                series_2_XValue_Resp += 1d;
                mSeries_2_Resp.appendData(new DataPoint(series_2_XValue_Resp, random), false, (int) MAX_X_NEXT - 2);

                // Art

                random = getRandomArt(seriesArrayArt); // change

                if (series_1_XValue_Art == (MAX_X_NEXT * 2)) {
                    series_1_XValue_Art = 0d;
                    mSeries_1_Art.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_Art, random)
                    });
                }

                series_1_XValue_Art += 1d;
                mSeries_1_Art.appendData(new DataPoint(series_1_XValue_Art, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_Art == (MAX_X_NEXT * 2)) {
                    series_2_XValue_Art = 0d;
                    mSeries_2_Art.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_Art, random)
                    });
                }

                series_2_XValue_Art += 1d;
                mSeries_2_Art.appendData(new DataPoint(series_2_XValue_Art, random), false, (int) MAX_X_NEXT - 2);

                // PAP

                random = getRandomPAP(seriesArrayPAP);

                if (series_1_XValue_PAP == (MAX_X_NEXT * 2)) {
                    series_1_XValue_PAP = 0d;
                    mSeries_1_PAP.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_PAP, random)
                    });
                }

                series_1_XValue_PAP += 1d;
                mSeries_1_PAP.appendData(new DataPoint(series_1_XValue_PAP, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_PAP == (MAX_X_NEXT * 2)) {
                    series_2_XValue_PAP = 0d;
                    mSeries_2_PAP.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_PAP, random)
                    });
                }

                series_2_XValue_PAP += 1d;
                mSeries_2_PAP.appendData(new DataPoint(series_2_XValue_PAP, random), false, (int) MAX_X_NEXT - 2);

                // CVP

                random = getRandomCVP(seriesArrayCVP);  // may change

                if (series_1_XValue_CVP == (MAX_X_NEXT * 2)) {
                    series_1_XValue_CVP = 0d;
                    mSeries_1_CVP.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_CVP, random)
                    });
                }

                series_1_XValue_CVP += 1d;
                mSeries_1_CVP.appendData(new DataPoint(series_1_XValue_CVP, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_CVP == (MAX_X_NEXT * 2)) {
                    series_2_XValue_CVP = 0d;
                    mSeries_2_CVP.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_CVP, random)
                    });
                }

                series_2_XValue_CVP += 1d;
                mSeries_2_CVP.appendData(new DataPoint(series_2_XValue_CVP, random), false, (int) MAX_X_NEXT - 2);

                // ICP

                random = getRandomICP(seriesArrayCVP);  // may change

                if (series_1_XValue_ICP == (MAX_X_NEXT * 2)) {
                    series_1_XValue_ICP = 0d;
                    mSeries_1_ICP.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_ICP, random)
                    });
                }

                series_1_XValue_ICP += 1d;
                mSeries_1_ICP.appendData(new DataPoint(series_1_XValue_ICP, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_ICP == (MAX_X_NEXT * 2)) {
                    series_2_XValue_ICP = 0d;
                    mSeries_2_ICP.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_ICP, random)
                    });
                }

                series_2_XValue_ICP += 1d;
                mSeries_2_ICP.appendData(new DataPoint(series_2_XValue_ICP, random), false, (int) MAX_X_NEXT - 2);

                // Ao

                random = getRandomAo(seriesArrayAo);

                if (series_1_XValue_Ao == (MAX_X_NEXT * 2)) {
                    series_1_XValue_Ao = 0d;
                    mSeries_1_Ao.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_Ao, random)
                    });
                }

                series_1_XValue_Ao += 1d;
                mSeries_1_Ao.appendData(new DataPoint(series_1_XValue_Ao, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_Ao == (MAX_X_NEXT * 2)) {
                    series_2_XValue_Ao = 0d;
                    mSeries_2_Ao.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_Ao, random)
                    });
                }

                series_2_XValue_Ao += 1d;
                mSeries_2_Ao.appendData(new DataPoint(series_2_XValue_Ao, random), false, (int) MAX_X_NEXT - 2);

                // UAP

                random = getRandomUAP(seriesArrayAo);

                if (series_1_XValue_UAP == (MAX_X_NEXT * 2)) {
                    series_1_XValue_UAP = 0d;
                    mSeries_1_UAP.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_UAP, random)
                    });
                }

                series_1_XValue_UAP += 1d;
                mSeries_1_UAP.appendData(new DataPoint(series_1_XValue_UAP, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_UAP == (MAX_X_NEXT * 2)) {
                    series_2_XValue_UAP = 0d;
                    mSeries_2_UAP.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_UAP, random)
                    });
                }

                series_2_XValue_UAP += 1d;
                mSeries_2_UAP.appendData(new DataPoint(series_2_XValue_UAP, random), false, (int) MAX_X_NEXT - 2);

                // BAP

                random = getRandomBAP(seriesArrayAo);

                if (series_1_XValue_BAP == (MAX_X_NEXT * 2)) {
                    series_1_XValue_BAP = 0d;
                    mSeries_1_BAP.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_BAP, random)
                    });
                }

                series_1_XValue_BAP += 1d;
                mSeries_1_BAP.appendData(new DataPoint(series_1_XValue_BAP, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_BAP == (MAX_X_NEXT * 2)) {
                    series_2_XValue_BAP = 0d;
                    mSeries_2_BAP.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_BAP, random)
                    });
                }

                series_2_XValue_BAP += 1d;
                mSeries_2_BAP.appendData(new DataPoint(series_2_XValue_BAP, random), false, (int) MAX_X_NEXT - 2);

                // FAP

                random = getRandomFAP(seriesArrayAo);

                if (series_1_XValue_FAP == (MAX_X_NEXT * 2)) {
                    series_1_XValue_FAP = 0d;
                    mSeries_1_FAP.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_FAP, random)
                    });
                }

                series_1_XValue_FAP += 1d;
                mSeries_1_FAP.appendData(new DataPoint(series_1_XValue_FAP, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_FAP == (MAX_X_NEXT * 2)) {
                    series_2_XValue_FAP = 0d;
                    mSeries_2_FAP.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_FAP, random)
                    });
                }

                series_2_XValue_FAP += 1d;
                mSeries_2_FAP.appendData(new DataPoint(series_2_XValue_FAP, random), false, (int) MAX_X_NEXT - 2);

                // CO2

                random = getRandomCO2(seriesArrayCO2);

                if (series_1_XValue_CO2 == (MAX_X_NEXT * 2)) {
                    series_1_XValue_CO2 = 0d;
                    mSeries_1_CO2.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_CO2, random)
                    });
                }

                series_1_XValue_CO2 += 1d;
                mSeries_1_CO2.appendData(new DataPoint(series_1_XValue_CO2, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_CO2 == (MAX_X_NEXT * 2)) {
                    series_2_XValue_CO2 = 0d;
                    mSeries_2_CO2.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_CO2, random)
                    });
                }

                series_2_XValue_CO2 += 1d;
                mSeries_2_CO2.appendData(new DataPoint(series_2_XValue_CO2, random), false, (int) MAX_X_NEXT - 2);

                // O2

                random = getRandomO2(seriesArrayCO2);

                if (series_1_XValue_O2 == (MAX_X_NEXT * 2)) {
                    series_1_XValue_O2 = 0d;
                    mSeries_1_O2.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_O2, random)
                    });
                }

                series_1_XValue_O2 += 1d;
                mSeries_1_O2.appendData(new DataPoint(series_1_XValue_O2, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_O2 == (MAX_X_NEXT * 2)) {
                    series_2_XValue_O2 = 0d;
                    mSeries_2_O2.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_O2, random)
                    });
                }

                series_2_XValue_O2 += 1d;
                mSeries_2_O2.appendData(new DataPoint(series_2_XValue_O2, random), false, (int) MAX_X_NEXT - 2);

                // N2O

                random = getRandomN2O(seriesArrayCO2);

                if (series_1_XValue_N2O == (MAX_X_NEXT * 2)) {
                    series_1_XValue_N2O = 0d;
                    mSeries_1_N2O.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_N2O, random)
                    });
                }

                series_1_XValue_N2O += 1d;
                mSeries_1_N2O.appendData(new DataPoint(series_1_XValue_N2O, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_N2O == (MAX_X_NEXT * 2)) {
                    series_2_XValue_N2O = 0d;
                    mSeries_2_N2O.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_N2O, random)
                    });
                }

                series_2_XValue_N2O += 1d;
                mSeries_2_N2O.appendData(new DataPoint(series_2_XValue_N2O, random), false, (int) MAX_X_NEXT - 2);

                // AA

                random = getRandomAA(seriesArrayCO2);

                if (series_1_XValue_AA == (MAX_X_NEXT * 2)) {
                    series_1_XValue_AA = 0d;
                    mSeries_1_AA.resetData(new DataPoint[]{
                            new DataPoint(series_1_XValue_AA, random)
                    });
                }

                series_1_XValue_AA += 1d;
                mSeries_1_AA.appendData(new DataPoint(series_1_XValue_AA, random), false, (int) MAX_X_NEXT - 2);

                if (series_2_XValue_AA == (MAX_X_NEXT * 2)) {
                    series_2_XValue_AA = 0d;
                    mSeries_2_AA.resetData(new DataPoint[]{
                            new DataPoint(series_2_XValue_AA, random)
                    });
                }

                series_2_XValue_AA += 1d;
                mSeries_2_AA.appendData(new DataPoint(series_2_XValue_AA, random), false, (int) MAX_X_NEXT - 2);

                mHandler.postDelayed(this, 20);
            }
        };
        mHandler.postDelayed(mTimer, 1000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }

    int arrayIndexI = 0;
    int arrayIndexII = 0;
    int arrayIndexIII = 0;
    int arrayIndexAVR = 0;
    int arrayIndexAVL = 0;
    int arrayIndexAVF = 0;
    int arrayIndexV1 = 0;
    int arrayIndexPleth = 0;
    int arrayIndexResp = 0;
    int arrayIndexArt = 0;
    int arrayIndexPAP = 0;
    int arrayIndexCVP = 0;
    int arrayIndexICP = 0;

    int arrayIndexAo = 0;
    int arrayIndexUAP = 0;
    int arrayIndexBAP = 0;
    int arrayIndexFAP = 0;

    int arrayIndexCO2 = 0;
    int arrayIndexO2 = 0;
    int arrayIndexN2O = 0;
    int arrayIndexAA = 0;

    private double getRandom(ArrayList<Double> seriesArray) {
        if (arrayIndexI < seriesArray.size() - 1)
            arrayIndexI++;
        else arrayIndexI = 0;
        return seriesArray.get(arrayIndexI);
    }

    private double getRandomI(double[] seriesArray) {
        if (arrayIndexI < seriesArray.length - 1)
            arrayIndexI++;
        else arrayIndexI = 0;
        return seriesArray[arrayIndexI] > 9 ? seriesArray[arrayIndexI] / 10 : seriesArray[arrayIndexI];
    }

    private double getRandomII(double[] seriesArray) {
        if (arrayIndexII < seriesArray.length - 1)
            arrayIndexII++;
        else arrayIndexII = 0;
        return seriesArray[arrayIndexII] > 9 ? seriesArray[arrayIndexII] / 10 : seriesArray[arrayIndexII];
    }

    private double getRandomIII(double[] seriesArray) {
        if (arrayIndexIII < seriesArray.length - 1)
            arrayIndexIII++;
        else arrayIndexIII = 0;
        return seriesArray[arrayIndexIII] > 9 ? seriesArray[arrayIndexIII] / 10 : seriesArray[arrayIndexIII];
    }

    // next

    private double getRandomAVR(double[] seriesArray) {
        if (arrayIndexAVR < seriesArray.length - 1)
            arrayIndexAVR++;
        else arrayIndexAVR = 0;
        return seriesArray[arrayIndexAVR] > 9 ? seriesArray[arrayIndexAVR] / 10 : seriesArray[arrayIndexAVR];
    }

    private double getRandomAVL(double[] seriesArray) {
        if (arrayIndexAVL < seriesArray.length - 1)
            arrayIndexAVL++;
        else arrayIndexAVL = 0;
        return seriesArray[arrayIndexAVL] > 9 ? seriesArray[arrayIndexAVL] / 10 : seriesArray[arrayIndexAVL];
    }

    private double getRandomAVF(double[] seriesArray) {
        if (arrayIndexAVF < seriesArray.length - 1)
            arrayIndexAVF++;
        else arrayIndexAVF = 0;
        return seriesArray[arrayIndexAVF] > 9 ? seriesArray[arrayIndexAVF] / 10 : seriesArray[arrayIndexAVF];
    }

    private double getRandomV1(double[] seriesArray) {
        if (arrayIndexV1 < seriesArray.length - 1)
            arrayIndexV1++;
        else arrayIndexV1 = 0;
        return seriesArray[arrayIndexV1] > 9 ? seriesArray[arrayIndexV1] / 10 : seriesArray[arrayIndexV1];
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

    private double getRandomPAP(ArrayList<Double> seriesArray) {
        if (arrayIndexPAP < seriesArray.size() - 1)
            arrayIndexPAP++;
        else arrayIndexPAP = 0;
        return seriesArray.get(arrayIndexPAP);
    }

    private double getRandomCVP(/*ArrayList<Double> seriesArray*/double[] seriesArray) {
        /*if (arrayIndexCVP < seriesArray.size() - 1)
            arrayIndexCVP++;
        else arrayIndexCVP = 0;
        return seriesArray.get(arrayIndexCVP);*/

        if (arrayIndexCVP < seriesArray.length - 1)
            arrayIndexCVP++;
        else arrayIndexCVP = 0;
        return seriesArray[arrayIndexCVP]/* > 9 ? seriesArray[arrayIndexCVP] / 10 : seriesArray[arrayIndexCVP]*/;
    }

    private double getRandomICP(/*ArrayList<Double> seriesArray*/double[] seriesArray) {
        /*if (arrayIndexICP < seriesArray.size() - 1)
            arrayIndexICP++;
        else arrayIndexICP = 0;
        return seriesArray.get(arrayIndexICP);*/

        if (arrayIndexICP < seriesArray.length - 1)
            arrayIndexICP++;
        else arrayIndexICP = 0;
        return seriesArray[arrayIndexICP]/* > 9 ? seriesArray[arrayIndexICP] / 10 : seriesArray[arrayIndexICP]*/;
    }

    private double getRandomAo(/*ArrayList<Double> seriesArray*/double[] seriesArray) {
        /*if (arrayIndexAo < seriesArray.size() - 1)
            arrayIndexAo++;
        else arrayIndexAo = 0;
        return seriesArray.get(arrayIndexAo);*/

        if (arrayIndexAo < seriesArray.length - 1)
            arrayIndexAo++;
        else arrayIndexAo = 0;
        return seriesArray[arrayIndexAo]/* > 9 ? seriesArray[arrayIndexAo] / 10 : seriesArray[arrayIndexAo]*/;
    }

    private double getRandomUAP(/*ArrayList<Double> seriesArray*/double[] seriesArray) {
        /*if (arrayIndexUAP < seriesArray.size() - 1)
            arrayIndexUAP++;
        else arrayIndexUAP = 0;
        return seriesArray.get(arrayIndexUAP);*/

        if (arrayIndexUAP < seriesArray.length - 1)
            arrayIndexUAP++;
        else arrayIndexUAP = 0;
        return seriesArray[arrayIndexUAP]/* > 9 ? seriesArray[arrayIndexUAP] / 10 : seriesArray[arrayIndexUAP]*/;
    }

    private double getRandomBAP(/*ArrayList<Double> seriesArray*/double[] seriesArray) {
        /*if (arrayIndexBAP < seriesArray.size() - 1)
            arrayIndexBAP++;
        else arrayIndexBAP = 0;
        return seriesArray.get(arrayIndexBAP);*/

        if (arrayIndexBAP < seriesArray.length - 1)
            arrayIndexBAP++;
        else arrayIndexBAP = 0;
        return seriesArray[arrayIndexBAP]/* > 9 ? seriesArray[arrayIndexBAP] / 10 : seriesArray[arrayIndexBAP]*/;
    }

    private double getRandomFAP(/*ArrayList<Double> seriesArray*/double[] seriesArray) {
        /*if (arrayIndexFAP < seriesArray.size() - 1)
            arrayIndexFAP++;
        else arrayIndexFAP = 0;
        return seriesArray.get(arrayIndexFAP);*/

        if (arrayIndexFAP < seriesArray.length - 1)
            arrayIndexFAP++;
        else arrayIndexFAP = 0;
        return seriesArray[arrayIndexFAP]/* > 9 ? seriesArray[arrayIndexFAP] / 10 : seriesArray[arrayIndexFAP]*/;
    }

    private double getRandomCO2(ArrayList<Double> seriesArray) {
        if (arrayIndexCO2 < seriesArray.size() - 1)
            arrayIndexCO2++;
        else arrayIndexCO2 = 0;
        return seriesArray.get(arrayIndexCO2);
    }

    private double getRandomO2(ArrayList<Double> seriesArray) {
        if (arrayIndexO2 < seriesArray.size() - 1)
            arrayIndexO2++;
        else arrayIndexO2 = 0;
        return seriesArray.get(arrayIndexO2);
    }

    private double getRandomN2O(ArrayList<Double> seriesArray) {
        if (arrayIndexN2O < seriesArray.size() - 1)
            arrayIndexN2O++;
        else arrayIndexN2O = 0;
        return seriesArray.get(arrayIndexN2O);
    }

    private double getRandomAA(ArrayList<Double> seriesArray) {
        if (arrayIndexAA < seriesArray.size() - 1)
            arrayIndexAA++;
        else arrayIndexAA = 0;
        return seriesArray.get(arrayIndexAA);
    }
    
}
