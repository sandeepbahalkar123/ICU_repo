package com.icuapp.ui.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.icuapp.ui.fragment.CCUPatientList;
import com.icuapp.ui.fragment.ICUPatientList;
import com.icuapp.ui.fragment.NICUPatientList;
import com.icuapp.ui.fragment.PICUPatientList;

/**
 * Created by hardikj on 04/07/17.
 */

public class PatientListContainerViewPager extends FragmentStatePagerAdapter {

    int tabCount;
    public PatientListContainerViewPager(FragmentManager fragMngr, int tabCount, final ViewPager viewPager) {
        super(fragMngr);
        this.tabCount = tabCount;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(1);
            }
        }, 100);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment  data=null;
        switch (position) {
            case 0:
                data = new CCUPatientList();
                break;
            case 1:
                data= new ICUPatientList();
                break;
            case 2:
                data = new PICUPatientList();
                break;
            case 3:
               data = new NICUPatientList();
                break;
        }
        return data;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
