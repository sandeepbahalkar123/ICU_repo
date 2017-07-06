package com.icuapp.ui.fragment.patient_details;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.adapters.VitalGraphListAdapter;
import com.icuapp.model.VitalList;
import com.icuapp.util.AppConstants;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by hardikj on 03/07/17.
 */

public class VitalHistoryList extends Fragment {

    private static final String Pleth = "Pleth";
    private static final String Resp = "Resp";
    private static final String Art = "Art";
    private static final String PAP = "PAP";
    private static final String CVP = "CVP";
    private static final String ICP = "ICP";
    private static final String Ao = "Ao";
    private static final String UAP = "UAP";
    private static final String BAP = "BAP";
    private static final String FAP = "FAP";
    private static final String CO2 = "CO2";
    private static final String O2 = "O2";
    private static final String N2O = "N2O";
    private static final String AA = "AA";

    private static final double MAX_X_NEXT = 280;

    private LineGraphSeries<DataPoint> mSeries_1_Pleth = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Resp = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Art = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_PAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_CVP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_ICP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_Ao = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_UAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_BAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_FAP = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_CO2 = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_O2 = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_N2O = new LineGraphSeries<>();

    private LineGraphSeries<DataPoint> mSeries_1_AA = new LineGraphSeries<>();

    private double series_1_XValue_Pleth = 0d;

    private double series_1_XValue_Resp = 0d;

    private double series_1_XValue_Art = 0d;

    private double series_1_XValue_PAP = 0d;

    private double series_1_XValue_CVP = 0d;

    private double series_1_XValue_ICP = 0d;

    // All same.

    private double series_1_XValue_Ao = 0d;

    private double series_1_XValue_UAP = 0d;

    private double series_1_XValue_BAP = 0d;

    private double series_1_XValue_FAP = 0d;

    // All same end.

    // next same

    private double series_1_XValue_CO2 = 0d;

    private double series_1_XValue_O2 = 0d;

    private double series_1_XValue_N2O = 0d;

    private double series_1_XValue_AA = 0d;

    // Content View Elements

    private LinearLayout mGraphPlethLayout;
    private GraphView mGraph_Pleth;
    private LinearLayout mGraphRespLayout;
    private GraphView mGraph_Resp;
    private LinearLayout mGraphArtLayout;
    private GraphView mGraph_Art;
    private LinearLayout mGraphPAPLayout;
    private GraphView mGraph_PAP;
    private LinearLayout mGraphCVPLayout;
    private GraphView mGraph_CVP;
    private LinearLayout mGraphICPLayout;
    private GraphView mGraph_ICP;
    private LinearLayout mGraphAoLayout;
    private GraphView mGraph_Ao;
    private LinearLayout mGraphUAPLayout;
    private GraphView mGraph_UAP;
    private LinearLayout mGraphBAPLayout;
    private GraphView mGraph_BAP;
    private LinearLayout mGraphFAPLayout;
    private GraphView mGraph_FAP;
    private LinearLayout mGraphCO2Layout;
    private GraphView mGraph_CO2;
    private LinearLayout mGraphO2Layout;
    private GraphView mGraph_O2;
    private LinearLayout mGraphN2OLayout;
    private GraphView mGraph_N2O;
    private LinearLayout mGraphAALayout;
    private GraphView mGraph_AA;

    // End Of Content View Elements

    private void bindViews(View view) {

        mGraphPlethLayout = (LinearLayout) view.findViewById(R.id.graphPlethLayout);
        mGraph_Pleth = (GraphView) view.findViewById(R.id.graph_Pleth);
        mGraphRespLayout = (LinearLayout) view.findViewById(R.id.graphRespLayout);
        mGraph_Resp = (GraphView) view.findViewById(R.id.graph_Resp);
        mGraphArtLayout = (LinearLayout) view.findViewById(R.id.graphArtLayout);
        mGraph_Art = (GraphView) view.findViewById(R.id.graph_Art);
        mGraphPAPLayout = (LinearLayout) view.findViewById(R.id.graphPAPLayout);
        mGraph_PAP = (GraphView) view.findViewById(R.id.graph_PAP);
        mGraphCVPLayout = (LinearLayout) view.findViewById(R.id.graphCVPLayout);
        mGraph_CVP = (GraphView) view.findViewById(R.id.graph_CVP);
        mGraphICPLayout = (LinearLayout) view.findViewById(R.id.graphICPLayout);
        mGraph_ICP = (GraphView) view.findViewById(R.id.graph_ICP);
        mGraphAoLayout = (LinearLayout) view.findViewById(R.id.graphAoLayout);
        mGraph_Ao = (GraphView) view.findViewById(R.id.graph_Ao);
        mGraphUAPLayout = (LinearLayout) view.findViewById(R.id.graphUAPLayout);
        mGraph_UAP = (GraphView) view.findViewById(R.id.graph_UAP);
        mGraphBAPLayout = (LinearLayout) view.findViewById(R.id.graphBAPLayout);
        mGraph_BAP = (GraphView) view.findViewById(R.id.graph_BAP);
        mGraphFAPLayout = (LinearLayout) view.findViewById(R.id.graphFAPLayout);
        mGraph_FAP = (GraphView) view.findViewById(R.id.graph_FAP);
        mGraphCO2Layout = (LinearLayout) view.findViewById(R.id.graphCO2Layout);
        mGraph_CO2 = (GraphView) view.findViewById(R.id.graph_CO2);
        mGraphO2Layout = (LinearLayout) view.findViewById(R.id.graphO2Layout);
        mGraph_O2 = (GraphView) view.findViewById(R.id.graph_O2);
        mGraphN2OLayout = (LinearLayout) view.findViewById(R.id.graphN2OLayout);
        mGraph_N2O = (GraphView) view.findViewById(R.id.graph_N2O);
        mGraphAALayout = (LinearLayout) view.findViewById(R.id.graphAALayout);
        mGraph_AA = (GraphView) view.findViewById(R.id.graph_AA);

        // next

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

        // set Properties

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

        // PAP

        mGraph_PAP.getViewport().setMinY(0);
        mGraph_PAP.getViewport().setMaxY(70);
        mGraph_PAP.addSeries(mSeries_1_PAP);
        mSeries_1_PAP.setThickness(2);
        mSeries_1_PAP.setColor(getResources().getColor(R.color.yellow));

        // CVP

        mGraph_CVP.getViewport().setMinY(-6);
        mGraph_CVP.getViewport().setMaxY(19);
        mGraph_CVP.addSeries(mSeries_1_CVP);
        mSeries_1_CVP.setThickness(2);
        mSeries_1_CVP.setColor(getResources().getColor(R.color.cvp));

        // ICP

        mGraph_ICP.getViewport().setMinY(-7);
        mGraph_ICP.getViewport().setMaxY(18);
        mGraph_ICP.addSeries(mSeries_1_ICP);
        mSeries_1_ICP.setThickness(2);
        mSeries_1_ICP.setColor(getResources().getColor(R.color.cvp));

        //////////

        // Ao

        mGraph_Ao.getViewport().setMinY(10);
        mGraph_Ao.getViewport().setMaxY(150);
        mGraph_Ao.addSeries(mSeries_1_Ao);
        mSeries_1_Ao.setThickness(2);
        mSeries_1_Ao.setColor(Color.RED);

        // UAP

        mGraph_UAP.getViewport().setMinY(10);
        mGraph_UAP.getViewport().setMaxY(150);
        mGraph_UAP.addSeries(mSeries_1_UAP);
        mSeries_1_UAP.setThickness(2);
        mSeries_1_UAP.setColor(Color.RED);

        // BAP

        mGraph_BAP.getViewport().setMinY(10);
        mGraph_BAP.getViewport().setMaxY(150);
        mGraph_BAP.addSeries(mSeries_1_BAP);
        mSeries_1_BAP.setThickness(2);
        mSeries_1_BAP.setColor(Color.RED);

        // FAP

        mGraph_FAP.getViewport().setMinY(10);
        mGraph_FAP.getViewport().setMaxY(150);
        mGraph_FAP.addSeries(mSeries_1_FAP);
        mSeries_1_FAP.setThickness(2);
        mSeries_1_FAP.setColor(Color.RED);

        // next same

        // CO2

        mGraph_CO2.getViewport().setMinY(-6);
        mGraph_CO2.getViewport().setMaxY(63);
        mGraph_CO2.addSeries(mSeries_1_CO2);
        mSeries_1_CO2.setThickness(2);
        mSeries_1_CO2.setColor(getResources().getColor(R.color.yellow));

        // O2

        mGraph_O2.getViewport().setMinY(-45);
        mGraph_O2.getViewport().setMaxY(110);
        mGraph_O2.addSeries(mSeries_1_O2);
        mSeries_1_O2.setThickness(2);
        mSeries_1_O2.setColor(getResources().getColor(R.color.parrot));

        // N2O

        mGraph_N2O.getViewport().setMinY(-140);
        mGraph_N2O.getViewport().setMaxY(70);
        mGraph_N2O.addSeries(mSeries_1_N2O);
        mSeries_1_N2O.setThickness(2);
        mSeries_1_N2O.setColor(getResources().getColor(R.color.cvp));

        // AA

        mGraph_AA.getViewport().setMinY(-150);
        mGraph_AA.getViewport().setMaxY(650);
        mGraph_AA.addSeries(mSeries_1_AA);
        mSeries_1_AA.setThickness(2);
        mSeries_1_AA.setColor(getResources().getColor(R.color.yellow));

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

    private void setGraphPropertiesNext(GraphView graphView) {
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(MAX_X_NEXT);
    }


    ArrayList<VitalList> vitalListData;
    private String[] spinnerDays = {"1 Days", "2 Days", "3 Days", "4 Days", "5 Days", "6 Days", "7 Days"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.vital_history_list, container, false);

        vitalListData = AppConstants.getAllVitalList();

        VitalGraphListAdapter adapter = new VitalGraphListAdapter(getActivity(),
                R.layout.vital_history_list_row, vitalListData);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.vitalListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        final RelativeLayout linearLayoutList = (RelativeLayout) view.findViewById(R.id.vitalHistoryListLinearLayout);
        linearLayoutList.setVisibility(View.VISIBLE);

        final LinearLayout linearLayoutDetail = (LinearLayout) view.findViewById(R.id.vitalHistoryDetailLayout);
        linearLayoutDetail.setVisibility(view.GONE);

        final Button backButton = (Button) view.findViewById(R.id.backBtnVital);
        Button btnOk = (Button) view.findViewById(R.id.buttonOkVital);

        // Ganesh Added
        bindViews(view);
        //

//        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerDays);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(),
//                R.layout.vital_history_list, spinnerDays);
//
//        spinner.setGraphVisibility(view.GONE);

        // Drop down layout style - list view with radio button
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
//        spinner.setAdapter(spinnerAdapter);

        // Spinner click listener

//        Button btnDays = (Button) view.findViewById(R.id.btnDays);

        btnOk.setOnClickListener(new OnClickListener() {
            //Run when button is clicked
            @Override
            public void onClick(View v) {
                setGraphInvisible();
                ArrayList<VitalList> allVitalList = AppConstants.getAllVitalList();
                int count = 0;
                for (VitalList data : allVitalList) {
                    if (data.isChecked()) {
                        count++;
                        Log.d("CheckedGraph", data.getVitalName());
                        setGraphVisibility(data.getVitalName());
                    }
                }
                if (count != 0) {
                    linearLayoutDetail.setVisibility(view.VISIBLE);
                    linearLayoutList.setVisibility(view.GONE);
                } else {
                    Toast.makeText(v.getContext(), "No patient selected", Toast.LENGTH_LONG).show();
                }
            }
        });

        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutDetail.setVisibility(view.GONE);
                linearLayoutList.setVisibility(view.VISIBLE);
            }
        });

//        btnDays.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               spinner.setGraphVisibility(view.VISIBLE);
//            }
//        });
        return view;
    }

    private void setGraphInvisible() {
        mGraphPlethLayout.setVisibility(View.GONE);
        
        mGraphRespLayout.setVisibility(View.GONE);
        
        mGraphArtLayout.setVisibility(View.GONE);
        
        mGraphPAPLayout.setVisibility(View.GONE);
        
        mGraphCVPLayout.setVisibility(View.GONE);
        
        mGraphICPLayout.setVisibility(View.GONE);
        
        mGraphAoLayout.setVisibility(View.GONE);
        
        mGraphUAPLayout.setVisibility(View.GONE);
        
        mGraphBAPLayout.setVisibility(View.GONE);
        
        mGraphFAPLayout.setVisibility(View.GONE);
        
        mGraphCO2Layout.setVisibility(View.GONE);
        
        mGraphO2Layout.setVisibility(View.GONE);
        
        mGraphN2OLayout.setVisibility(View.GONE);
        
        mGraphAALayout.setVisibility(View.GONE);
    }

    private void setGraphVisibility(String vitalName) {
        switch (vitalName) {
            case Pleth:
                mGraphPlethLayout.setVisibility(View.VISIBLE);
                break;
            case Resp:
                mGraphRespLayout.setVisibility(View.VISIBLE);
                break;
            case Art:
                mGraphArtLayout.setVisibility(View.VISIBLE);
                break;
            case PAP:
                mGraphPAPLayout.setVisibility(View.VISIBLE);
                break;
            case CVP:
                mGraphCVPLayout.setVisibility(View.VISIBLE);
                break;
            case ICP:
                mGraphICPLayout.setVisibility(View.VISIBLE);
                break;
            case Ao:
                mGraphAoLayout.setVisibility(View.VISIBLE);
                break;
            case UAP:
                mGraphUAPLayout.setVisibility(View.VISIBLE);
                break;
            case BAP:
                mGraphBAPLayout.setVisibility(View.VISIBLE);
                break;
            case FAP:
                mGraphFAPLayout.setVisibility(View.VISIBLE);
                break;
            case CO2:
                mGraphCO2Layout.setVisibility(View.VISIBLE);
                break;
            case O2:
                mGraphO2Layout.setVisibility(View.VISIBLE);
                break;
            case N2O:
                mGraphN2OLayout.setVisibility(View.VISIBLE);
                break;
            case AA:
                mGraphAALayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Init

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

        for (int i = 0; i < MAX_X_NEXT; i++) {

            // Pleth

            double random = getRandomPleth(seriesArrayPleth);

            series_1_XValue_Pleth += 1d;
            mSeries_1_Pleth.appendData(new DataPoint(series_1_XValue_Pleth, random), false, (int) MAX_X_NEXT - 2);

            // Resp

            random = getRandomResp(seriesArrayResp); // change

            series_1_XValue_Resp += 1d;
            mSeries_1_Resp.appendData(new DataPoint(series_1_XValue_Resp, random), false, (int) MAX_X_NEXT - 2);

            // Art

            random = getRandomArt(seriesArrayArt); // change

            series_1_XValue_Art += 1d;
            mSeries_1_Art.appendData(new DataPoint(series_1_XValue_Art, random), false, (int) MAX_X_NEXT - 2);

            // PAP

            random = getRandomPAP(seriesArrayPAP);

            series_1_XValue_PAP += 1d;
            mSeries_1_PAP.appendData(new DataPoint(series_1_XValue_PAP, random), false, (int) MAX_X_NEXT - 2);

            // CVP

            random = getRandomCVP(seriesArrayCVP);  // may change

            series_1_XValue_CVP += 1d;
            mSeries_1_CVP.appendData(new DataPoint(series_1_XValue_CVP, random), false, (int) MAX_X_NEXT - 2);

            // ICP

            random = getRandomICP(seriesArrayCVP);  // may change

            series_1_XValue_ICP += 1d;
            mSeries_1_ICP.appendData(new DataPoint(series_1_XValue_ICP, random), false, (int) MAX_X_NEXT - 2);

            // Ao

            random = getRandomAo(seriesArrayAo);

            series_1_XValue_Ao += 1d;
            mSeries_1_Ao.appendData(new DataPoint(series_1_XValue_Ao, random), false, (int) MAX_X_NEXT - 2);

            // UAP

            random = getRandomUAP(seriesArrayAo);

            series_1_XValue_UAP += 1d;
            mSeries_1_UAP.appendData(new DataPoint(series_1_XValue_UAP, random), false, (int) MAX_X_NEXT - 2);

            // BAP

            random = getRandomBAP(seriesArrayAo);

            series_1_XValue_BAP += 1d;
            mSeries_1_BAP.appendData(new DataPoint(series_1_XValue_BAP, random), false, (int) MAX_X_NEXT - 2);

            // FAP

            random = getRandomFAP(seriesArrayAo);

            series_1_XValue_FAP += 1d;
            mSeries_1_FAP.appendData(new DataPoint(series_1_XValue_FAP, random), false, (int) MAX_X_NEXT - 2);

            // CO2

            random = getRandomCO2(seriesArrayCO2);

            series_1_XValue_CO2 += 1d;
            mSeries_1_CO2.appendData(new DataPoint(series_1_XValue_CO2, random), false, (int) MAX_X_NEXT - 2);

            // O2

            random = getRandomO2(seriesArrayCO2);

            series_1_XValue_O2 += 1d;
            mSeries_1_O2.appendData(new DataPoint(series_1_XValue_O2, random), false, (int) MAX_X_NEXT - 2);

            // N2O

            random = getRandomN2O(seriesArrayCO2);

            series_1_XValue_N2O += 1d;
            mSeries_1_N2O.appendData(new DataPoint(series_1_XValue_N2O, random), false, (int) MAX_X_NEXT - 2);

            // AA

            random = getRandomAA(seriesArrayCO2);

            series_1_XValue_AA += 1d;
            mSeries_1_AA.appendData(new DataPoint(series_1_XValue_AA, random), false, (int) MAX_X_NEXT - 2);

        }
    }

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
