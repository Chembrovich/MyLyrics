
package com.epam.androidlab.mylyrics.models.network;

import java.util.ArrayList;
import java.util.List;

import com.epam.androidlab.mylyrics.models.chart.TrackList;
import com.epam.androidlab.mylyrics.models.lyrics.Lyrics;
import com.epam.androidlab.mylyrics.models.track.Track;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("track")
    @Expose
    private Track track;

    @SerializedName("track_list")
    @Expose
    private List<TrackList> trackList = new ArrayList<TrackList>();

    @SerializedName("lyrics")
    @Expose
    private Lyrics lyrics;

    public Lyrics getLyrics() {
        return lyrics;
    }

    public void setLyrics(Lyrics lyrics) {
        this.lyrics = lyrics;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public List<TrackList> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackList> trackList) {
        this.trackList = trackList;
    }

}
