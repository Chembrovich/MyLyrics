package com.epam.androidlab.mylyrics.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.androidlab.mylyrics.R;

public class SearchFragment extends Fragment {

    public SearchView searchView;
    private OnSearchListener onSearchCallback;

    public SearchFragment() {
    }

    interface OnSearchListener {
        void onSearch(String searchRequest);
    }

    @Override
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_view, container, false);

        searchView = view.findViewById(R.id.track_search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearchCallback.onSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }

}
