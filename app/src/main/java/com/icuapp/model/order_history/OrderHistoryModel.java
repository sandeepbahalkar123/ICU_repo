package com.icuapp.model.order_history;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderHistoryModel {

    @SerializedName("data")
    @Expose
    private ArrayList<OrderHistoryData> orderHistoryDataList = null;


    public ArrayList<OrderHistoryData> getOrderHistoryDataList() {
        return orderHistoryDataList;
    }

    public void setOrderHistoryDataList(ArrayList<OrderHistoryData> orderHistoryDataList) {
        this.orderHistoryDataList = orderHistoryDataList;
    }

    @Override
    public String toString() {
        return "OrderHistoryModel{" +
                "orderHistoryDataList=" + orderHistoryDataList +
                '}';
    }
}