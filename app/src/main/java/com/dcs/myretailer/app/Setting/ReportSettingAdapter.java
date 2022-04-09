package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

class ReportSettingAdapter extends BaseAdapter {
    Context context;
    String reportSettingList[];
    LayoutInflater inflter;
    androidx.appcompat.widget.AppCompatCheckBox chk_allow_report_setting;
    public ReportSettingAdapter(Context applicationContext, String[] reportSettingList) {
        this.context = context;
        this.reportSettingList = reportSettingList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return reportSettingList.length;
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
        view = inflter.inflate(R.layout.activity_report_setting_listview, null);
        TextView report_setting_text =  view.findViewById(R.id.report_setting_text);
        chk_allow_report_setting =  view.findViewById(R.id.report_setting_text);
        report_setting_text.setText(reportSettingList[i]);
        return view;
    }
}