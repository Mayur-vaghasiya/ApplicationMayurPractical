package com.example.application.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.R;
import com.example.application.util.CommonUtility;
import com.example.application.util.NetworkStatus;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity = null;
    private AppCompatTextView textviewNewSignup, textviewForgotPassword, textviewLogin;
    private AppCompatEditText edittextEmail, edittextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;
        textviewNewSignup = (AppCompatTextView) findViewById(R.id.textviewNewSignup);
        textviewNewSignup.setOnClickListener(this);
        textviewLogin = (AppCompatTextView) findViewById(R.id.textviewLogin);
        textviewLogin.setOnClickListener(this);

        edittextEmail = (AppCompatEditText) findViewById(R.id.edittextEmail);
        edittextPassword = (AppCompatEditText) findViewById(R.id.edittextPassword);

        edittextPassword.setOnEditorActionListener(new AppCompatEditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    textviewLogin.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(activity, MainActivity.class));
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.textviewLogin:
                if (NetworkStatus.getConnectivityStatusString(activity)) {

                    if (!checkForValidations()) {
                        Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(activity, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textviewForgotPassword:

                break;

            case R.id.textviewNewSignup:
                finish();
                startActivity(new Intent(activity, SignUpActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.textviewTermsCondition:

                break;
        }
    }

    private boolean checkForValidations() {
        if (TextUtils.isEmpty(edittextEmail.getText().toString().trim())) {

            edittextEmail.setError(getString(R.string.email_hint_error));

            return true;

        } else if (!CommonUtility.isValidEmailId(edittextEmail.getText().toString().trim())) {

            edittextEmail.setError(getString(R.string.email_validation));

            return true;

        } else if (TextUtils.isEmpty(edittextPassword.getText().toString().trim())) {

            edittextPassword.setError(getString(R.string.empty_password));

            return true;

        } else {

            return false;

        }
    }
}
