package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/24/2017.
 */

public class DeleteGroupResponse  {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private DeleteGroupResponseResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeleteGroupResponseResult getResult() {
        return result;
    }

    public void setResult(DeleteGroupResponseResult result) {
        this.result = result;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

class DeleteGroupResponseResult {

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