package com.icuapp.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.icuapp.ui.fragment.patient_details.ECGGraphsList;
import com.icuapp.ui.fragment.order_history.OrderHistoryMedicines;
import com.icuapp.ui.fragment.patient_details.OrderHistoryList;
import com.icuapp.ui.fragment.patient_details.VitalGraphsList;
import com.icuapp.ui.fragment.patient_details.VitalHistoryList;

/**
 * Created by hardikj on 03/07/17.
 */

public class PatientDetailViewPager extends FragmentStatePagerAdapter {
    int tabCount;

    public PatientDetailViewPager(FragmentManager fragMngr, int tabCount) {
        super(fragMngr);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                VitalGraphsList vitalGraphsList = VitalGraphsList.newInstance();
                return vitalGraphsList;
            case 1:
                ECGGraphsList ecgGraphsList = new ECGGraphsList();
                return ecgGraphsList;
            case 2:
                VitalHistoryList vitalHistoryList = new VitalHistoryList();
                return vitalHistoryList;
            case 3:
                OrderHistoryList orderHistory = new OrderHistoryList();
                return orderHistory;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
