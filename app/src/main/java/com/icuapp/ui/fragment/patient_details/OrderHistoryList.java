package com.icuapp.ui.fragment.patient_details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.icuapp.R;
import com.icuapp.adapters.OrderHistoryListAdapter;
import com.icuapp.adapters.PatientListAdapter;
import com.icuapp.model.order_history.OrderHistoryData;
import com.icuapp.util.AppConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardikj on 03/07/17.
 */

public class OrderHistoryList extends Fragment {
    @BindView(R.id.orderHistoryListView)
    ListView mOrderHistoryListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_history_main_layout, container, false);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    private void initialize() {
        ArrayList<OrderHistoryData> allOrderHistoryDataList = AppConstants.getAllOrderHistoryDataList(getActivity());
        OrderHistoryListAdapter orderHistoryListAdapter = new OrderHistoryListAdapter(getActivity(), R.layout.item_order_history, allOrderHistoryDataList);
        mOrderHistoryListView.setAdapter(orderHistoryListAdapter);
    }

}
