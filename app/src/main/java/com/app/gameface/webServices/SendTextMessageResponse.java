package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/24/2017.
 */

public class SendTextMessageResponse{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private SendTextMessageResponseResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SendTextMessageResponseResult getResult() {
        return result;
    }

    public void setResult(SendTextMessageResponseResult result) {
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

class SendTextMessageResponseResult {

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