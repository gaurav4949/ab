package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/25/2017.
 */

public class ChangeAgeGroup {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private ChangeAgeGroupResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChangeAgeGroupResult getResult() {
        return result;
    }

    public void setResult(ChangeAgeGroupResult result) {
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
 class ChangeAgeGroupResult {

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