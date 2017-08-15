
package com.epam.androidlab.mylyrics.models.gener;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrimaryGenres {

    @SerializedName("music_genre_list")
    @Expose
    private List<MusicGenreList> musicGenreList = new ArrayList<MusicGenreList>();

    public List<MusicGenreList> getMusicGenreList() {
        return musicGenreList;
    }

    public void setMusicGenreList(List<MusicGenreList> musicGenreList) {
        this.musicGenreList = musicGenreList;
    }

    public String getGenreName(){
        if (musicGenreList.size() > 0) {
            return musicGenreList.get(0).getMusicGenre().getMusicGenreName();
        } else {
            return "No genre";
        }
    }

}
