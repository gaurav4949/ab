package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/5/2017.
 */

public class ResendOtpResponse

 {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private ResendOtpResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResendOtpResult getResult() {
        return result;
    }

    public void setResult(ResendOtpResult result) {
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


 class ResendOtpResult {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("verify_code")
    @Expose
    private Integer verifyCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
    }

}