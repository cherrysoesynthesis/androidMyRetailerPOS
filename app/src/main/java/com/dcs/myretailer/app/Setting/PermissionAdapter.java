package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.List;

public class PermissionAdapter extends BaseAdapter {
    Context context;
    List<PermissionClass> permissiongroup;
    int flags[];
    LayoutInflater inflter;

    public PermissionAdapter(Context context, List<PermissionClass> permissiongroup) {
        this.context = context;
        this.permissiongroup = permissiongroup;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return permissiongroup.size();
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
        view = inflter.inflate(R.layout.activity_permission_group_listview, null);
        TextView tax_name = (TextView) view.findViewById(R.id.permission_group_name);
        Log.i("Sdfdsstaff_",permissiongroup.get(i).getPermission_group_name());
        tax_name.setText(permissiongroup.get(i).getPermission_group_name());
        return view;
    }
}
