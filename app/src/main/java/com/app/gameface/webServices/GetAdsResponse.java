package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ajit on 7/11/2017.
 */

public class GetAdsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<GetAdsResponseResult> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetAdsResponseResult> getResult() {
        return result;
    }

    public void setResult(List<GetAdsResponseResult> result) {
        this.result = result;
    }

}

class GetAdsResponseResult {

    @SerializedName("web_link")
    @Expose
    private String webLink;
    @SerializedName("ad_image")
    @Expose
    private String adImage;

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

}