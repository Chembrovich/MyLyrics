package com.epam.androidlab.mylyrics.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.androidlab.mylyrics.R;
import com.epam.androidlab.mylyrics.api.Network;
import com.epam.androidlab.mylyrics.models.Constants;

public class TrackInfoFragment extends Fragment {

    private int trackId;
    private int albumId;
    private String trackName;
    private String artistName;
    private String genreName;
    private String albumName;
    private int hasLyrics;

    private Network network = new Network();

    private TextView lyricsView;

    private OnSearchListener onSearchCallback;

    public TrackInfoFragment() {
        // Required empty public constructor
    }

    interface OnSearchListener {
        void onSearch(String searchRequest, String type);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onSearchCallback = (OnSearchListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSearchListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            trackId = getArguments().getInt(Constants.TRACK_ID);
            albumId = getArguments().getInt(Constants.ALBUM_ID);
            trackName = getArguments().getString(Constants.TRACK_NAME);
            artistName = getArguments().getString(Constants.ARTIST_NAME);
            genreName = getArguments().getString(Constants.GENRE_NAME);
            albumName = getArguments().getString(Constants.ALBUM_NAME);
            hasLyrics = getArguments().getInt(Constants.HAS_LYRICS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_info, container, false);
        TextView trackNameView = view.findViewById(R.id.track_name_info);
        TextView artistNameView = view.findViewById(R.id.artist_name_info);
        TextView albumNameView = view.findViewById(R.id.album_name_info);
        TextView genreView = view.findViewById(R.id.genre_name);
        lyricsView = view.findViewById(R.id.lyrics_text_view);

        trackNameView.setText(trackName);
        artistNameView.setText(artistName);
        albumNameView.setText(getString(R.string.album_name, albumName));
        genreView.setText(getString(R.string.genre, genreName));

        albumNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchCallback.onSearch(String.valueOf(albumId), Constants.ALBUM_SEARCH);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (hasLyrics == 1) {
            Network.LyricsCallBack lyricsCallBack = new Network.LyricsCallBack() {
                @Override
                public void onSuccess(String lyrics) {
                    lyricsView.setText(lyrics);
                }

                @Override
                public void onFailure() {
                    Toast.makeText(getContext(), R.string.no_connection, Toast.LENGTH_SHORT).show();
                }
            };
            network.getLyrics(String.valueOf(trackId), lyricsCallBack);
        } else {
            lyricsView.setText(getString(R.string.no_lyrics));
        }
    }
}
