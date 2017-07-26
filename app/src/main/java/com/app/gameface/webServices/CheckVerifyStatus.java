package com.app.gameface.webServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajit on 7/5/2017.
 */

public class CheckVerifyStatus {



        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("result")
        @Expose
        private VerifyResult result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public VerifyResult getResult() {
            return result;
        }

        public void setResult(VerifyResult result) {
            this.result = result;
        }

    }



class VerifyResult {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("verify_status")
        @Expose
        private String verifyStatus;

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

    }

