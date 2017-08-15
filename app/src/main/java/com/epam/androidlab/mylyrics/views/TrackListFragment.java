package com.epam.androidlab.mylyrics.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.epam.androidlab.mylyrics.R;
import com.epam.androidlab.mylyrics.api.Network;
import com.epam.androidlab.mylyrics.models.Constants;
import com.epam.androidlab.mylyrics.models.track.Track;
import com.epam.androidlab.mylyrics.models.chart.TrackList;

import java.util.List;

public class TrackListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ListView trackListView;
    private TrackListAdapter trackListAdapter;
    private Network network = new Network();
    private Network.TrackListCallBack trackListCallBack;
    private String[] countriesCodes;
    private int tracksCount;
    private String searchRequest;
    private String searchType;
    private OnTrackListSelectedListener onListItemClickCallback;
    private boolean isSearch;

    interface OnTrackListSelectedListener {
        void onTrackSelected(Track track);
    }

    public TrackListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onListItemClickCallback = (OnTrackListSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTrackListSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            searchRequest = getArguments().getString(Constants.SEARCH_REQUEST);
            searchType = getArguments().getString(Constants.SEARCH_TYPE);
            isSearch = true;
        }

        countriesCodes = getResources().getStringArray(R.array.countries_codes_array);
        tracksCount = getResources().getInteger((R.integer.tracks_count));

        trackListCallBack = new Network.TrackListCallBack() {
            @Override
            public void onSuccess(List<TrackList> trackList) {
                onSuccessCallback(trackList);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), R.string.no_connection, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_list, container, false);

        if (trackListView == null) {
            trackListView = view.findViewById(R.id.track_list_view);

            trackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    onListItemClickCallback.onTrackSelected(trackListAdapter.getTrack(i));
                }
            });
        }

        Spinner countrySpinner = view.findViewById(R.id.country_spinner);

        if (isSearch) {
            countrySpinner.setVisibility(View.INVISIBLE);
        } else {
            countrySpinner.setOnItemSelectedListener(this);
            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext());
            countrySpinner.setAdapter(countrySpinnerAdapter);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getArguments() != null){
            searchRequest = getArguments().getString(Constants.SEARCH_REQUEST);
        }

        if (isSearch) {
            switch (searchType) {
                case Constants.ARTIST_TITLE_SEARCH:
                    network.searchTrack(searchRequest, tracksCount, trackListCallBack);
                    break;
                case Constants.ALBUM_SEARCH:
                    network.getTracksFromAlbum(searchRequest, tracksCount, trackListCallBack);
            }
        }else{
            network.getChart(countriesCodes[0], tracksCount, trackListCallBack);
        }
    }

    private void onSuccessCallback(List<TrackList> trackList) {
        if (trackList.size() > 0) {
            trackListAdapter = new TrackListAdapter(getContext(), trackList);
            trackListView.setAdapter(trackListAdapter);
        } else {
            Toast.makeText(getContext(), R.string.no_tracks, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (!isSearch) {
            network.getChart(countriesCodes[i], tracksCount, trackListCallBack);
        } else {
            isSearch = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getContext(), R.string.no_connection, Toast.LENGTH_SHORT).show();
    }
}
