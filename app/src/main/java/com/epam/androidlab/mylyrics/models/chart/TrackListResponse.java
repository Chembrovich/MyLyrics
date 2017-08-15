
package com.epam.androidlab.mylyrics.models.chart;

import com.epam.androidlab.mylyrics.models.network.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackListResponse {

    @SerializedName("message")
    @Expose
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
