package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

public class SyncAdapter extends BaseAdapter {
    Context context;
    String syncList[];
    LayoutInflater inflter;

    public SyncAdapter(Context context, String[] syncList) {
        this.context = context;
        this.syncList = syncList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return syncList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_sync_listview, null);
        Button syn_name = (Button) view.findViewById(R.id.syn_name);
        syn_name.setText(syncList[i]);
        return view;
    }
}