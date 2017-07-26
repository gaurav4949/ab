package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/21/2017.
 */

public class AddGroupResponse {
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("result")
        @Expose
        private AddGroupResponseResult result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public AddGroupResponseResult getResult() {
            return result;
        }

        public void setResult(AddGroupResponseResult result) {
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


 class AddGroupResponseResult {

        @SerializedName("group_id")
        @Expose
        private String groupId;
        @SerializedName("group_name")
        @Expose
        private String groupName;
        @SerializedName("group_image")
        @Expose
        private String groupImage;
        @SerializedName("clipboard")
        @Expose
        private String clipboard;
        @SerializedName("group_date")
        @Expose
        private String groupDate;
        @SerializedName("sport_name")
        @Expose
        private String sportName;
        @SerializedName("games")
        @Expose
        private String games;
        @SerializedName("age_group")
        @Expose
        private String ageGroup;
        @SerializedName("count")
        @Expose
        private String count;
        @SerializedName("message")
        @Expose
        private String message;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupImage() {
            return groupImage;
        }

        public void setGroupImage(String groupImage) {
            this.groupImage = groupImage;
        }

        public String getClipboard() {
            return clipboard;
        }

        public void setClipboard(String clipboard) {
            this.clipboard = clipboard;
        }

        public String getGroupDate() {
            return groupDate;
        }

        public void setGroupDate(String groupDate) {
            this.groupDate = groupDate;
        }

        public String getSportName() {
            return sportName;
        }

        public void setSportName(String sportName) {
            this.sportName = sportName;
        }

        public String getGames() {
            return games;
        }

        public void setGames(String games) {
            this.games = games;
        }

        public String getAgeGroup() {
            return ageGroup;
        }

        public void setAgeGroup(String ageGroup) {
            this.ageGroup = ageGroup;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }