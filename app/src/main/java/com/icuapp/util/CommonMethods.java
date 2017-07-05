package com.icuapp.util;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * Created by riteshpandhurkar on 3/7/17.
 */
public class CommonMethods {
    static String TAG = "CommonMethods";

    public static void showSnack(View mViewById, String msg) {
        if (mViewById != null) {
            Snackbar.make(mViewById, msg, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Log.d(TAG, "null snacbar view" + msg);
        }
    }

    public static int generateRandomEvenNumber() {
        int min = 0, max = 60;
        Random rand = new Random();
        min = min % 2 == 1 ? min + 1 : min; // If min is odd, add one to make sure the integer division can´t create a number smaller min;
        max = max % 2 == 1 ? max - 1 : max; // If max is odd, subtract one to make sure the integer division can´t create a number greater max;
        int randomNum = ((rand.nextInt((max - min)) + min) + 1) / 2; // Divide both by 2 to ensure the range
        int i = randomNum * 2;
        Log.e(TAG, "generateEvenNumber : " + i);

        return i; // multiply by 2 to make the number even
    }

    public static String convertMilliSecondsToDate(long miliSeconds, String dateFormat) {

        //creating Date from millisecond
        Date currentDate = new Date(miliSeconds);

        //printing value of Date
        System.out.println("current Date: " + currentDate);

        DateFormat df = new SimpleDateFormat(dateFormat);//"HH:mm:ss"

        //formatted value of current Date
        System.out.println("Milliseconds to Date: " + df.format(currentDate));

        return df.format(currentDate);

    }

    public static void printLog(String tag, String data) {
        Log.e(tag, "" + data);
    }
}
