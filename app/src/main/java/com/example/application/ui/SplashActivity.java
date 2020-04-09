package com.example.application.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.application.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity = null;
    private AppCompatTextView textviewSignUp,textviewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity= SplashActivity.this;
        setContentView(R.layout.activity_splash);

        textviewLogin=(AppCompatTextView)findViewById(R.id.textviewLogin);
        textviewLogin.setOnClickListener(this);
        textviewSignUp=(AppCompatTextView)findViewById(R.id.textviewSignUp);
        textviewSignUp.setOnClickListener(this);
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
            case R.id.textviewLogin:
                startActivity(new Intent(activity, LoginActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.textviewSignUp:
                startActivity(new Intent(activity, SignUpActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }
    }
}
