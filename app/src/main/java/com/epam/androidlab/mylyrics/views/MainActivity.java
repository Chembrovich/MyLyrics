package com.epam.androidlab.mylyrics.views;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.epam.androidlab.mylyrics.R;
import com.epam.androidlab.mylyrics.models.Constants;
import com.epam.androidlab.mylyrics.models.track.Track;

public class MainActivity extends FragmentActivity implements TrackListFragment.OnTrackListSelectedListener,
                                                                SearchFragment.OnSearchListener,
                                                                TrackInfoFragment.OnSearchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.track_list_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            TrackListFragment trackListFragment = new TrackListFragment();
            trackListFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.track_list_fragment, trackListFragment).commit();
        }
    }

    @Override
    public void onTrackSelected(Track track) {
        TrackInfoFragment newFragment = new TrackInfoFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.TRACK_ID, track.getTrackId());
        args.putInt(Constants.ALBUM_ID, track.getAlbumId());
        args.putString(Constants.TRACK_NAME, track.getTrackName());
        args.putString(Constants.ARTIST_NAME, track.getArtistName());
        args.putString(Constants.GENRE_NAME, track.getPrimaryGenres().getGenreName());
        args.putString(Constants.ALBUM_NAME, track.getAlbumName());
        args.putInt(Constants.HAS_LYRICS, track.getHasLyrics());

        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.track_list_fragment, newFragment);
        transaction.addToBackStack(null).commit();
    }

    @Override
    public void onSearch(String searchRequest) {
        if (findViewById(R.id.track_list_fragment) != null) {
            Bundle args = new Bundle();
            args.putString(Constants.SEARCH_REQUEST, searchRequest);
            args.putString(Constants.SEARCH_TYPE, Constants.ARTIST_TITLE_SEARCH);
            TrackListFragment trackListFragment = new TrackListFragment();
            trackListFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.track_list_fragment, trackListFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onSearch(String searchRequest, String type) {
        if (findViewById(R.id.track_list_fragment) != null) {
            Bundle args = new Bundle();
            args.putString(Constants.SEARCH_REQUEST, searchRequest);
            args.putString(Constants.SEARCH_TYPE, type);
            TrackListFragment trackListFragment = new TrackListFragment();
            trackListFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.track_list_fragment, trackListFragment).addToBackStack(null).commit();
        }

    }
}

