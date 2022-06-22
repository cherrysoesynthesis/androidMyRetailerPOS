package com.dcs.myretailer.app.Setting;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.List;

class SpinnerCategoryAdapter extends ArrayAdapter<String> {
    private List<String> objects;

    public SpinnerCategoryAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        this.objects=objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(final int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner, parent, false);
        final TextView label=(TextView)row.findViewById(R.id.tv_spinnervalue);
        label.setText(objects.get(position));
        return row;
    }
}