package com.example.application.service;

import com.example.application.model.GallaryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAllmageDataService {

    /*End point for get All Country With all data*/
    @GET("images/latest")
    Call<GallaryModel> getAllImages();
}
