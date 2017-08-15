package com.epam.androidlab.mylyrics.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.androidlab.mylyrics.R;
import com.squareup.picasso.Picasso;

class CountrySpinnerAdapter extends BaseAdapter {

    private Context context;
    private int countriesCount;
    private String[] countriesNames;
    private static final int[] countriesFlags={R.drawable.country_australia, R.drawable.country_canada,
            R.drawable.country_germany, R.drawable.country_great_britain,
            R.drawable.country_italy, R.drawable.country_russia,
            R.drawable.country_spain, R.drawable.country_united_states};

    CountrySpinnerAdapter(Context context) {
        this.context = context;
        countriesNames = context.getResources().getStringArray(R.array.countries_names_array);
        countriesCount = context.getResources().getInteger(R.integer.countries_count);
    }

    @Override
    public int getCount() {
        return countriesCount;
    }

    @Override
    public Object getItem(int i) {
        return countriesNames[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context context = viewGroup.getContext();
            view = LayoutInflater.from(context).inflate(R.layout.item_country_spinner, viewGroup, false);
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.countryNameView.setText(countriesNames[i]);
        Picasso.with(context).load(countriesFlags[i]).into(holder.countryFlagImageView);

        return view;
    }

    private static class ViewHolder {
        private final TextView countryNameView;
        private final ImageView countryFlagImageView;

        ViewHolder(View view) {
            this.countryNameView = view.findViewById(R.id.country_name_spinner);
            this.countryFlagImageView = view.findViewById(R.id.country_flag_spinner_image_view);
        }
    }
}
