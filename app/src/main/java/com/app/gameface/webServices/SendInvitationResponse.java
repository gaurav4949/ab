package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/10/2017.
 */

public class SendInvitationResponse{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private SendInvitationResponseResult result;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SendInvitationResponseResult getResult() {
        return result;
    }

    public void setResult(SendInvitationResponseResult result) {
        this.result = result;
    }

}

class SendInvitationResponseResult {

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