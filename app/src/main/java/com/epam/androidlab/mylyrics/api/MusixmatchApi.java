package com.epam.androidlab.mylyrics.api;

import com.epam.androidlab.mylyrics.models.chart.TrackListResponse;
import com.epam.androidlab.mylyrics.models.lyrics.LyricsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MusixmatchApi {

    @GET("chart.tracks.get")
    Call<TrackListResponse> getChart(@Query("apikey") String apikey,
                                     @Query("country") String countryId,
                                     @Query("page_size") int trackCount);

    @GET("track.lyrics.get")
    Call<LyricsResponse> getLyrics(@Query("apikey") String apikey,
                                   @Query("track_id") String trackId);


    @GET("album.tracks.get")
    Call<TrackListResponse> getTracksFromAlbum(@Query("apikey") String apikey,
                                               @Query("album_id") String albumId,
                                               @Query("page_size") int trackCount);

    @GET("track.search")
    Call<TrackListResponse> searchTrack(@Query("apikey") String apikey,
                                        @Query("q_track_artist") String trackArtist,
                                        @Query("page_size") int trackCount,
                                        @Query("s_track_rating") String sortOrder);
}
