package com.epam.androidlab.mylyrics.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.androidlab.mylyrics.R;
import com.epam.androidlab.mylyrics.models.track.Track;
import com.epam.androidlab.mylyrics.models.chart.TrackList;
import com.squareup.picasso.Picasso;

import java.util.List;

class TrackListAdapter extends BaseAdapter {

    private Context context;
    private List<TrackList> trackList;

    TrackListAdapter(Context context, List<TrackList> trackList) {
        this.context = context;
        this.trackList = trackList;
    }

    Track getTrack(int number) {
        return trackList.get(number).getTrack();
    }

    @Override
    public int getCount() {
        return trackList.size();
    }

    @Override
    public Object getItem(int i) {
        return trackList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context context = viewGroup.getContext();
            view = LayoutInflater.from(context).inflate(R.layout.item_track_list, viewGroup, false);
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.trackNameView.setText(trackList.get(i).getTrack().getTrackName());
        holder.artistNameView.setText(trackList.get(i).getTrack().getArtistName());
        Picasso.with(context).load(trackList.get(i).getTrack().getAlbumCoverart100x100()).into(holder.albumImageView);

        return view;
    }

    private static class ViewHolder {
        private final TextView trackNameView;
        private final TextView artistNameView;
        private final ImageView albumImageView;

        ViewHolder(View view) {
            this.trackNameView = view.findViewById(R.id.track_name_list_view);
            this.artistNameView = view.findViewById(R.id.artist_name_list_view);
            this.albumImageView = view.findViewById(R.id.album_image_list_view);
        }
    }
}
