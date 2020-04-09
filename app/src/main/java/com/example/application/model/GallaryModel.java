package com.example.application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GallaryModel implements Serializable
{
    @SerializedName("images")
    @Expose
    private ArrayList<Image> images = null;
    private final static long serialVersionUID = -6775598957536320093L;

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }


public class Image implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("large_url")
    @Expose
    private String largeUrl;
    @SerializedName("source_id")
    @Expose
    private Object sourceId;
    private final static long serialVersionUID = 1627061005746904444L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public Object getSourceId() {
        return sourceId;
    }

    public void setSourceId(Object sourceId) {
        this.sourceId = sourceId;
    }
}
}