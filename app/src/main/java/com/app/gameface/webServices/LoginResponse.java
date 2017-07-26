package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("result")
@Expose
private Result result;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Result getResult() {
return result;
}

public void setResult(Result result) {
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


class Result {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("email")
@Expose
private String email;
@SerializedName("name")
@Expose
private String name;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("token")
@Expose
private String token;
@SerializedName("country_code")
@Expose
private String countryCode;
@SerializedName("verify_code")
@Expose
private String verifyCode;
@SerializedName("verify_status")
@Expose
private String verifyStatus;
@SerializedName("user_image")
@Expose
private String userImage;
@SerializedName("message")
@Expose
private String message;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public String getCountryCode() {
return countryCode;
}

public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

public String getVerifyCode() {
return verifyCode;
}

public void setVerifyCode(String verifyCode) {
this.verifyCode = verifyCode;
}

public String getVerifyStatus() {
return verifyStatus;
}

public void setVerifyStatus(String verifyStatus) {
this.verifyStatus = verifyStatus;
}

public String getUserImage() {
return userImage;
}

public void setUserImage(String userImage) {
this.userImage = userImage;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}