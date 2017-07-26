package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ajit on 7/24/2017.
 */

public class GetChatHistoryResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total_page")
    @Expose
    private Integer totalPage;
    @SerializedName("result")
    @Expose
    private List<GetChatHistoryResponseResult> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<GetChatHistoryResponseResult> getResult() {
        return result;
    }

    public void setResult(List<GetChatHistoryResponseResult> result) {
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    @SerializedName("message")
    @Expose
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }
}

class GetChatHistoryResponseResult {

    @SerializedName("message_id")
    @Expose
    private String message_id;

    @SerializedName("sender_id")
    @Expose
    private String senderId;

    @SerializedName("sender_name")
    @Expose
    private String senderName;
    @SerializedName("sender_image")
    @Expose
    private String senderImage;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }



}