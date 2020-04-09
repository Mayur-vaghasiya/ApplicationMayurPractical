package com.example.application.ui;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.adapter.GallaryAdapter;
import com.example.application.custom_views.CustomProgressDialog;
import com.example.application.model.GallaryModel;
import com.example.application.service.GetAllmageDataService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity = null;
    private RecyclerView recyclerViewImageGallary;
    private ArrayList<GallaryModel.Image> imageList = null;
    private GallaryAdapter gallaryAdapter = null;
    private GetAllmageDataService service = null;
    private LinearLayoutManager layoutManager = null;
    private CustomProgressDialog progress = null;
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private AppCompatTextView textViewone, textViewtwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;


        textViewone= findViewById(R.id.textviewTaskone);
        textViewone.setOnClickListener(this);
        textViewtwo= findViewById(R.id.textviewTaskTwo);
        textViewtwo.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textviewTaskone:
                startActivity(new Intent(activity, SplashActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.textviewTaskTwo:
                startActivity(new Intent(activity, GallaryActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }
    }
}
