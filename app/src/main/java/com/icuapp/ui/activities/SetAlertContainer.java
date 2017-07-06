package com.icuapp.ui.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.icuapp.ui.fragment.CCUPatientList;
import com.icuapp.ui.fragment.CcuList;
import com.icuapp.ui.fragment.ICUPatientList;
import com.icuapp.ui.fragment.IcuList;
import com.icuapp.ui.fragment.NICUPatientList;
import com.icuapp.ui.fragment.NicuList;
import com.icuapp.ui.fragment.PICUPatientList;
import com.icuapp.ui.fragment.PicuList;

/**
 * Created by jeetal on 6/7/17.
 */

public class SetAlertContainer extends FragmentStatePagerAdapter {

    int tabCount;
    public SetAlertContainer(FragmentManager fragMngr, int tabCount, final ViewPager viewPager) {
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
                data = new CcuList();
                break;
            case 1:
                data= new IcuList();
                break;
            case 2:
                data = new PicuList();
                break;
            case 3:
                data = new NicuList();
                break;
        }
        return data;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
