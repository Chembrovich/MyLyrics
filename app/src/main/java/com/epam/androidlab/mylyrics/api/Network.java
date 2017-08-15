package com.epam.androidlab.mylyrics.api;

import android.support.annotation.NonNull;

import com.epam.androidlab.mylyrics.models.chart.TrackListResponse;
import com.epam.androidlab.mylyrics.models.lyrics.LyricsResponse;
import com.epam.androidlab.mylyrics.models.network.Message;
import com.epam.androidlab.mylyrics.models.chart.TrackList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private MusixmatchApi musixmatchApi;
    private Message message;
    private static final String apiKey = "1f856d378ada71e59fff8907c1a31ab0";
    private static final String baseUrl = "http://api.musixmatch.com/ws/1.1/";
    private static final String descSortOrder = "desc";

    public interface TrackListCallBack {
        void onSuccess(List<TrackList> trackList);
        void onFailure();
    }
    public interface LyricsCallBack {
        void onSuccess(String lyrics);
        void onFailure();
    }

    public Network() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        musixmatchApi = retrofit.create(MusixmatchApi.class);
    }

    public void getChart(String countryId, int count, final TrackListCallBack trackListCallBack){
        musixmatchApi.getChart(apiKey, countryId, count).enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackListResponse> call, @NonNull Response<TrackListResponse> response) {
                if (response.isSuccessful()) {
                    message = response.body().getMessage();
                    trackListCallBack.onSuccess(message.getBody().getTrackList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackListResponse> call, @NonNull Throwable t) {
                trackListCallBack.onFailure();
            }
        });
    }

    public void getLyrics(String trackId, final LyricsCallBack lyricsCallBack){
        musixmatchApi.getLyrics(apiKey, trackId).enqueue(new Callback<LyricsResponse>() {
            @Override
            public void onResponse(@NonNull Call<LyricsResponse> call, @NonNull Response<LyricsResponse> response) {
                if (response.isSuccessful()) {
                    message = response.body().getMessage();
                    lyricsCallBack.onSuccess(message.getBody().getLyrics().getLyricsBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<LyricsResponse> call, @NonNull Throwable t) {
                lyricsCallBack.onFailure();
            }
        });
    }

    public void getTracksFromAlbum(String albumId,int trackCount, final TrackListCallBack trackListCallBack) {
        musixmatchApi.getTracksFromAlbum(apiKey, albumId, trackCount).enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackListResponse> call, @NonNull Response<TrackListResponse> response) {
                if (response.isSuccessful()) {
                    message = response.body().getMessage();
                    trackListCallBack.onSuccess(message.getBody().getTrackList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackListResponse> call, @NonNull Throwable t) {
                trackListCallBack.onFailure();
            }
        });
    }

    public void searchTrack(String searchRequest,int trackCount, final TrackListCallBack trackListCallBack) {
        musixmatchApi.searchTrack(apiKey, searchRequest, trackCount, descSortOrder).enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackListResponse> call,@NonNull  Response<TrackListResponse> response) {
                if (response.isSuccessful()) {
                    message = response.body().getMessage();
                    trackListCallBack.onSuccess(message.getBody().getTrackList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackListResponse> call,@NonNull  Throwable t) {
                trackListCallBack.onFailure();
            }
        });
    }
}
