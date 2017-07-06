package com.icuapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.adapters.OrderHistoryListAdapter;
import com.icuapp.customesViews.CustomEditTextView;
import com.icuapp.customesViews.CustomTextView;
import com.icuapp.model.order_history.OrderHistoryData;
import com.icuapp.ui.fragment.order_history.OrderHistoryInvestigations;
import com.icuapp.ui.fragment.order_history.OrderHistoryMedicines;
import com.icuapp.util.AppConstants;
import com.icuapp.util.CommonMethods;
import com.icuapp.util.IOnTimePickerSelection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hardikj on 04/07/17.
 */

public class OrderHistoryContainerActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @BindView(R.id.orderHistoryListView)
    ListView mOrderHistoryListView;
    @BindView(R.id.selectDate)
    CustomTextView mSelectDate;
    @BindView(R.id.tvAddOrder)
    CustomTextView mTvAddOrder;
    @BindView(R.id.instructions)
    CustomEditTextView mInstructions;
    @BindView(R.id.selectedMedicine)
    AutoCompleteTextView mSelectedMedicine;
    @BindView(R.id.radioTypeSelectionGroup)
    RadioGroup mRadioTypeSelectionGroup;
    @BindView(R.id.mainContainerLayout)
    LinearLayout mMainContainerLayout;

    String mMedicineORInvestigationSelected;
    private ArrayList<OrderHistoryData> mAllOrderHistoryDataList;
    private ArrayAdapter<String> mSelectedMedicineAdapter;
    private OrderHistoryListAdapter mOrderHistoryListAdapter;
    private String mSelectedTimeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history_medicine_and_investigation_main_layout);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medicines & Investigations");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize();
    }

    private void initialize() {
        mMainContainerLayout.setVisibility(View.GONE);
        mAllOrderHistoryDataList = AppConstants.getAllOrderHistoryDataList(this);
        mOrderHistoryListAdapter = new OrderHistoryListAdapter(this, R.layout.item_order_history, mAllOrderHistoryDataList);
        mOrderHistoryListView.setAdapter(mOrderHistoryListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.radioMedicine, R.id.radioInvestigations})
    public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.radioMedicine:
                if (checked) {
                    mMedicineORInvestigationSelected = "medicine";
                    filterAllOrderHistoryDataList();
                }
                break;
            case R.id.radioInvestigations:
                if (checked) {
                    mMedicineORInvestigationSelected = "INVESTIGATIONS";
                    filterAllOrderHistoryDataList();
                }
                break;
        }
    }

    @OnClick(R.id.selectDate)
    public void selectDateToAdd() {
        CommonMethods.showTimePickerDialog(this, new IOnTimePickerSelection() {
            @Override
            public void onTimeSelected(String selectedTime, String formattedTime) {
                String s = CommonMethods.convertMilliSecondsToDate(System.currentTimeMillis(), "dd MMMM yyyy");
                mSelectedTimeStamp = formattedTime;
                mSelectDate.setText(s + " - " + formattedTime);
            }
        });
    }

    @OnClick(R.id.tvAddOrder)
    public void addOrder() {
        OrderHistoryData orderHistoryData = new OrderHistoryData();
        orderHistoryData.setTimeStamp("" + mSelectedTimeStamp);
        orderHistoryData.setInstruction("" + mInstructions.getText().toString());
        orderHistoryData.setType("" + mMedicineORInvestigationSelected);
        orderHistoryData.setName("" + mSelectedMedicine.getText().toString());
        AppConstants.getAllOrderHistoryDataList(this).add(orderHistoryData);
        mAllOrderHistoryDataList = AppConstants.getAllOrderHistoryDataList(this);
        mOrderHistoryListAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Order added successfully.", Toast.LENGTH_LONG).show();
    }

    private void filterAllOrderHistoryDataList() {
        ArrayList<String> tempList = new ArrayList<>();
        for (OrderHistoryData data :
                mAllOrderHistoryDataList) {
            if (data.getType().equalsIgnoreCase(mMedicineORInvestigationSelected)) {
                tempList.add(data.getName());
            }
        }
        mSelectedMedicineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, tempList);
        mSelectedMedicine.setAdapter(mSelectedMedicineAdapter);

        mSelectedMedicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
                String text = mSelectedMedicine.getText().toString();
                for (OrderHistoryData data :
                        mAllOrderHistoryDataList) {
                    if (data.getName().equalsIgnoreCase(text)) {
                        mInstructions.setText("" + data.getInstruction());
                    }
                }
            }

        });
    }
}
