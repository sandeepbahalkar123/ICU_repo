package com.icuapp.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.icuapp.R;
import com.icuapp.adapters.CustomBaseAdapter;
import com.icuapp.model.RowItem;

import java.util.ArrayList;
import java.util.List;

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

    public static Dialog showAlertDialog(Context activity, String dialogHeader) {
       final String[] titles = new String[] { "Strawberry",
                "Banana", "Orange", "Mixed" };
        List<RowItem> rowItems;
        final Context mContext = activity;
        final Dialog dialog = new Dialog(activity);
        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(titles[i]);
            rowItems.add(item);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.show_dialog_layout);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        if (dialogHeader != null)
            ((TextView) dialog.findViewById(R.id.patientDetails)).setText(dialogHeader);
        ListView listViewDialogList = (ListView)dialog.findViewById(R.id.lvDialogueList) ;
        CustomBaseAdapter adapter = new CustomBaseAdapter(activity, rowItems);
        listViewDialogList.setAdapter(adapter);

        dialog.findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();

        return dialog;
    }
}
