
package com.epam.androidlab.mylyrics.models.chart;

import com.epam.androidlab.mylyrics.models.track.Track;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrackList implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("track")
    @Expose
    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}
