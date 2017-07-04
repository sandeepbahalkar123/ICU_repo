package com.icuapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


import com.icuapp.R;
import com.icuapp.util.CommonMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();
    Context mContext;
    String mServerPath;
    @BindView(R.id.userName)
    EditText mUserName;

    @BindView(R.id.password)
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }


    @OnClick(R.id.loginButton)
    public void doLogin() {
        if (!validate()) {
            Intent intentObj = new Intent(LoginActivity.this, DashboardActivity.class);
            intentObj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentObj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentObj.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentObj);
        }
    }

    /**
     * Return true if fields empty/validation failed, else false.
     *
     * @return
     */
    private boolean validate() {
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        String message = null;
        if (userName.isEmpty() || password.isEmpty()) {
            message = getString(R.string.error_empty_fields);
        }
        if (message != null) {
            CommonMethods.showSnack(mUserName, message);
            return true;
        } else {
            return false;
        }
    }

}

