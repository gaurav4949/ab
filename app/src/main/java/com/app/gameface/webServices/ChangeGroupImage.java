package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/25/2017.
 */

public class ChangeGroupImage{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private ChangeGroupImageResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChangeGroupImageResult getResult() {
        return result;
    }

    public void setResult(ChangeGroupImageResult result) {
        this.result = result;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
 class ChangeGroupImageResult {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("group_image")
    @Expose
    private String groupImage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

}