package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ajit on 7/24/2017.
 */

public class GetGroupMembersResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<GetGroupMembersResponseResult> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetGroupMembersResponseResult> getResult() {
        return result;
    }

    public void setResult(List<GetGroupMembersResponseResult> result) {
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


class GetGroupMembersResponseResult {

    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("member_image")
    @Expose
    private String memberImage;
    @SerializedName("admin_status")
    @Expose
    private String adminStatus;
    @SerializedName("coach_status")
    @Expose
    private String coachStatus;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getCoachStatus() {
        return coachStatus;
    }

    public void setCoachStatus(String coachStatus) {
        this.coachStatus = coachStatus;
    }

}