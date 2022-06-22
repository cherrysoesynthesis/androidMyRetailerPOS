package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.FragmentReportCategoryBinding;

public class ReportCategoryFragmentScreenSize {
    public ReportCategoryFragmentScreenSize(Context context, FragmentReportCategoryBinding binding) {
        //ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
            FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsReporData.leftMargin = 20;
            binding.ReportCategoryListView.setLayoutParams(paramsReporData);
        }else if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
            if (device.equals("M2-Max")) {

            } else {
//                FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                paramsReporData.leftMargin = 20;
//                binding.ReportCategoryListView.setLayoutParams(paramsReporData);
            }
        }
    }
}
