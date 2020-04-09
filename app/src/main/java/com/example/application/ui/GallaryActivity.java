package com.example.application.ui;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.adapter.GallaryAdapter;
import com.example.application.custom_views.CustomProgressDialog;
import com.example.application.model.GallaryModel;
import com.example.application.retro.RetrofitInstance;
import com.example.application.service.GetAllmageDataService;
import com.example.application.util.NetworkStatus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GallaryActivity extends AppCompatActivity {
    private Activity activity = null;
    private RecyclerView recyclerViewImageGallary;
    private ArrayList<GallaryModel.Image> imageList = null;
    private GallaryAdapter gallaryAdapter = null;
    private GetAllmageDataService service = null;
    private LinearLayoutManager layoutManager = null;
    private CustomProgressDialog progress = null;
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private AppCompatTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);

        activity = GallaryActivity.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageList =new ArrayList<GallaryModel.Image>();
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.golden), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(activity,MainActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        AppCompatTextView txtHeaderNname = (AppCompatTextView) toolbar.findViewById(R.id.actv_header_name);
        txtHeaderNname.setText(getString(R.string.image_gallary));

        gallaryAdapter = new GallaryAdapter(new WeakReference<Context>(activity), imageList);

        recyclerViewImageGallary = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerViewImageGallary.setLayoutManager(layoutManager);
        recyclerViewImageGallary.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImageGallary.setAdapter(gallaryAdapter);



        recyclerViewImageGallary.addOnItemTouchListener(new GallaryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerViewImageGallary, new GallaryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", imageList);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        if (NetworkStatus.getConnectivityStatusString(activity)) {
            getImageData();
        } else {
            Toast.makeText(activity, "No Internet Connection!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

            startActivity(new Intent(activity,MainActivity.class));
            overridePendingTransition(R.anim.left_in, R.anim.right_out);

    }

    public void getImageData() {
        service = RetrofitInstance.getRetrofitInstance().create(GetAllmageDataService.class);
        Call<GallaryModel> call = service.getAllImages();
        progress = new CustomProgressDialog(activity).
                setStyle(CustomProgressDialog.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .show();
        call.enqueue(new Callback<GallaryModel>() {
            @Override
            public void onResponse(Call<GallaryModel> call, Response<GallaryModel> response) {

                Log.e(TAG,response.toString());
                imageList = (ArrayList<GallaryModel.Image>) response.body().getImages();
                setRecyclerViewData();
                progress.dismiss();
                Toast.makeText(activity, "Sync Data with Server", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GallaryModel> call, Throwable t) {
                progress.dismiss();

                Toast.makeText(GallaryActivity.this, "Sync Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerViewData() {
        gallaryAdapter = new GallaryAdapter(new WeakReference<Context>(activity), imageList);
        recyclerViewImageGallary.setAdapter(gallaryAdapter);
    }
}
