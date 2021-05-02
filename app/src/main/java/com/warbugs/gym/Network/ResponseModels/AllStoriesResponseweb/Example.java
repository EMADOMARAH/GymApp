
package com.warbugs.gym.Network.ResponseModels.AllStoriesResponseweb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Example implements Serializable
{

    @SerializedName("message")
    @Expose
    private Message message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    private final static long serialVersionUID = 100434239178745253L;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
