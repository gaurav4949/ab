package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FootballFantasyMatchupsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<FootballFantasyMatchupsResult> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FootballFantasyMatchupsResult> getResult() {
        return result;
    }

    public void setResult(List<FootballFantasyMatchupsResult> result) {
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


class FootballFantasyMatchupsResult {

    @SerializedName("matchup_id")
    @Expose
    private String matchupId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact_name")
    @Expose
    private String contactName;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("image")
    @Expose
    private String image;

    public String getMatchupId() {
        return matchupId;
    }

    public void setMatchupId(String matchupId) {
        this.matchupId = matchupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
