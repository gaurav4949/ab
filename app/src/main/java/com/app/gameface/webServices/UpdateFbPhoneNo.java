package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/7/2017.
 */

public class UpdateFbPhoneNo  {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private UpdateFbPhoneResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdateFbPhoneResult getResult() {
        return result;
    }

    public void setResult(UpdateFbPhoneResult result) {
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


 class UpdateFbPhoneResult {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("verify_status")
    @Expose
    private String verifyStatus;
    @SerializedName("verify_code")
    @Expose
    private String verifyCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
