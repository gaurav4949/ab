package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TeamSportSchduleResponse  {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<TeamSportSchduleResponseResult> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TeamSportSchduleResponseResult> getResult() {
        return result;
    }

    public void setResult(List<TeamSportSchduleResponseResult> result) {
        this.result = result;
    }

}
 class InSchedule {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_image")
    @Expose
    private String userImage;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}

class OutSchedule {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_image")
    @Expose
    private String userImage;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}
 class TeamSportSchduleResponseResult {

    @SerializedName("schedule_id")
    @Expose
    private String scheduleId;
    @SerializedName("game_name")
    @Expose
    private String gameName;
    @SerializedName("opponent_name")
    @Expose
    private String opponentName;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("snacks")
    @Expose
    private String snacks;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("is_in")
    @Expose
    private String isIn;
    @SerializedName("is_out")
    @Expose
    private String isOut;
    @SerializedName("in_schedule")
    @Expose
    private List<InSchedule> inSchedule = null;
    @SerializedName("out_schedule")
    @Expose
    private List<OutSchedule> outSchedule = null;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
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

    public String getIsIn() {
        return isIn;
    }

    public void setIsIn(String isIn) {
        this.isIn = isIn;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public List<InSchedule> getInSchedule() {
        return inSchedule;
    }

    public void setInSchedule(List<InSchedule> inSchedule) {
        this.inSchedule = inSchedule;
    }

    public List<OutSchedule> getOutSchedule() {
        return outSchedule;
    }

    public void setOutSchedule(List<OutSchedule> outSchedule) {
        this.outSchedule = outSchedule;
    }

}