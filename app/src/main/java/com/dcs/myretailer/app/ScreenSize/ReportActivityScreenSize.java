package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityReportBinding;

public class ReportActivityScreenSize {
    public ReportActivityScreenSize(Context context, ActivityReportBinding binding) {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams paramsReportIcon = new LinearLayout.LayoutParams(82,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            binding.layRprint.setLayoutParams(paramsReportIcon);
            binding.LayReportExport.setLayoutParams(paramsReportIcon);
            binding.LayReportRefresh.setLayoutParams(paramsReportIcon);

//            LinearLayout.LayoutParams paramsLayTotalsales = new LinearLayout.LayoutParams(520,
//                   70);
//            paramsLayTotalsales.leftMargin = 20;
            //binding.LayTotalSales.setLayoutParams(paramsLayTotalsales);

//            LinearLayout.LayoutParams paramsTotalsales = new LinearLayout.LayoutParams(400,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //binding.txttotalQty.setLayoutParams(paramsTotalsales);
//            binding.txttotalPriceAmount.setLayoutParams(paramsTotalsales);


        }  else if (terminalTypeVal.toUpperCase().equals(Constraints.PAX)){
//            LinearLayout.LayoutParams paramsReportIcon = new LinearLayout.LayoutParams(82,
//                    ViewGroup.LayoutParams.MATCH_PARENT);
//            binding.layRprint.setLayoutParams(paramsReportIcon);
//            binding.LayReportExport.setLayoutParams(paramsReportIcon);
//            binding.LayReportRefresh.setLayoutParams(paramsReportIcon);
//
//            LinearLayout.LayoutParams paramsLayTotalsales = new LinearLayout.LayoutParams(520,
//                   70);
//            paramsLayTotalsales.leftMargin = 20;
//            binding.LayTotalSales.setLayoutParams(paramsLayTotalsales);



            LinearLayout.LayoutParams pagerReportLay = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    920);
            binding.pagerReport.setLayoutParams(pagerReportLay);

            binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 100));



        } else if (terminalTypeVal.equals(Constraints.IMIN)) {

            String device = Query.GetDeviceData(Constraints.DEVICE);
            Log.i("sdf__device","device___"+device);
            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams paramsReportIcon = new LinearLayout.LayoutParams(92,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                paramsReportIcon.gravity = Gravity.CENTER;
                binding.layRprint.setLayoutParams(paramsReportIcon);
                binding.LayReportExport.setLayoutParams(paramsReportIcon);
                binding.LayReportRefresh.setLayoutParams(paramsReportIcon);

//                LinearLayout.LayoutParams paramsLayTotalsales = new LinearLayout.LayoutParams(523,
//                        70);
//                paramsLayTotalsales.leftMargin = 20;
//                binding.LayTotalSales.setLayoutParams(paramsLayTotalsales);

                LinearLayout.LayoutParams drangeLay = new LinearLayout.LayoutParams(520,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                binding.daterange.setLayoutParams(drangeLay);

                LinearLayout.LayoutParams pagerReportLay = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        920);
                binding.pagerReport.setLayoutParams(pagerReportLay);

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(820, 100));
            }  else {

                LinearLayout.LayoutParams pagerReportLay = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        825);
                binding.pagerReport.setLayoutParams(pagerReportLay);

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 100));
            }
        }
    }
}
