package com.example.application.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.R;
import com.example.application.util.CommonUtility;
import com.example.application.util.NetworkStatus;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity = null;
    private AppCompatTextView textviewTermsCondition, textviewAlreadyLogin, textviewSignUp;
    private AppCompatEditText edittextFullName, edittextEmail, edittextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        activity = SignUpActivity.this;
        edittextFullName = (AppCompatEditText) findViewById(R.id.edittextFullName);
        edittextEmail = (AppCompatEditText) findViewById(R.id.edittextEmail);
        edittextPassword = (AppCompatEditText) findViewById(R.id.edittextPassword);
        textviewAlreadyLogin = (AppCompatTextView) findViewById(R.id.textviewAlreadyLogin);
        textviewAlreadyLogin.setOnClickListener(this);
        textviewSignUp = (AppCompatTextView) findViewById(R.id.textviewTermsCondition);
        textviewSignUp.setOnClickListener(this);
        textviewTermsCondition = (AppCompatTextView) findViewById(R.id.textviewTermsCondition);
        String text = "<font color=#A1A4AF>By Signig up you accept the </font>" +
                " <font color=#87AFDE>Terms of Service" +
                "</font><font color=#A1A4AF> and</font>" + "</font><font color=#87AFDE> \nPrivacy Policy</font>";
        textviewTermsCondition.setText(Html.fromHtml(text));

        edittextPassword.setOnEditorActionListener(new AppCompatEditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    textviewSignUp.performClick();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textviewAlreadyLogin:
                finish();
                startActivity(new Intent(activity, LoginActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;

            case R.id.textviewSignUp:

                if (NetworkStatus.getConnectivityStatusString(activity)) {

                    if (!checkForValidations()) {
                       /* finish();
                        startActivity(new Intent(activity, SignUpActivity.class));
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                        Toast.makeText(activity, "Sign Up Success", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(activity, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textviewTermsCondition:

                break;
        }
    }

    private boolean checkForValidations() {

        if (TextUtils.isEmpty(edittextFullName.getText().toString().trim())) {

            edittextFullName.setError(getString(R.string.username_validation));

            return true;

        } else if (TextUtils.isEmpty(edittextEmail.getText().toString().trim())) {

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
