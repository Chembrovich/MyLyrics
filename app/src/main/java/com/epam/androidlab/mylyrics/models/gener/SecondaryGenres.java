
package com.epam.androidlab.mylyrics.models.gener;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondaryGenres {

    @SerializedName("music_genre_list")
    @Expose
    private List<Object> musicGenreList = new ArrayList<Object>();

    public List<Object> getMusicGenreList() {
        return musicGenreList;
    }

    public void setMusicGenreList(List<Object> musicGenreList) {
        this.musicGenreList = musicGenreList;
    }

}
