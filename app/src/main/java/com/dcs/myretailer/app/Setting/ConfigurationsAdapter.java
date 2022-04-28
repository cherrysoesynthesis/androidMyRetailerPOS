package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;

import java.util.ArrayList;

public class ConfigurationsAdapter extends BaseAdapter   {
    Context context;
    ArrayList<Integer> IDList;
    ArrayList<String> NameList;
    ArrayList<String> ActiveList;
    int flags[];
    LayoutInflater inflter;
    String ID = "0";
    String device = "";
    public ConfigurationsAdapter(Context context, ArrayList<Integer> IDList, ArrayList<String> NameList, ArrayList<String> ActiveList) {
        this.context = context;
        this.IDList = IDList;
        this.NameList = NameList;
        this.ActiveList = ActiveList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return IDList.size();
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
        view = inflter.inflate(R.layout.activity_configuration_listview, null);
        LinearLayout config_host_listview = (LinearLayout) view.findViewById(R.id.config_host_listview);
        TextView bank_name = (TextView) view.findViewById(R.id.bank_name);

        device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(700,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 30;
            linearOverAllParams.topMargin = 20;
            config_host_listview.setLayoutParams(linearOverAllParams);
        }
//        Switch active_onoff = (Switch) view.findViewById(R.id.active_onoff);
        bank_name.setText(NameList.get(i).toUpperCase());
        ID = IDList.get(i).toString();
//        Log.i("_IDsdsdsdsd",ID);
//        if (ActiveList.get(i).equals("1")) {
//            active_onoff.setChecked(true);
//        }else{
//            active_onoff.setChecked(false);
//        }
        return view;
    }

}
