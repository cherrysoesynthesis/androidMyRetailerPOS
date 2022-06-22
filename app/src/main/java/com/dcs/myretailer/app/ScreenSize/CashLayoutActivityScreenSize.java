package com.dcs.myretailer.app.ScreenSize;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityCashLayoutBinding;

public class CashLayoutActivityScreenSize {

    public static String terminalTypeVal = "PAX";

    public CashLayoutActivityScreenSize(ActivityCashLayoutBinding binding){
        terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 30;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }

        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams.leftMargin = 50;
//            linearOverAllParams.gravity = Gravity.CENTER;
            binding.layOverAll.setLayoutParams(linearOverAllParams);

            LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams1.leftMargin = 50;
//            linearOverAllParams1.gravity = Gravity.CENTER;
//            linearOverAllParams1.gravity = Gravity.CENTER;
            binding.Layshowamt.setLayoutParams(linearOverAllParams1);

            LinearLayout.LayoutParams linearlayshowall1 = new LinearLayout.LayoutParams(600,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearlayshowall1.leftMargin = 50;
            linearlayshowall1.gravity = Gravity.CENTER;
            binding.layshowall.setLayoutParams(linearlayshowall1);

            LinearLayout.LayoutParams linearLaycollected = new LinearLayout.LayoutParams(300,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLaycollected.leftMargin = 50;
            binding.laycollected.setLayoutParams(linearLaycollected);

            LinearLayout.LayoutParams linearOverAllParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.layLine.setLayoutParams(linearOverAllParams3);

            LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.layshowcollected.setLayoutParams(linearOverAllParams2);

            LinearLayout.LayoutParams linearOverAllParams5 = new LinearLayout.LayoutParams(500,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams5.gravity = Gravity.CENTER;
            binding.Laybtnaccept.setLayoutParams(linearOverAllParams5);
        }
    }
}
