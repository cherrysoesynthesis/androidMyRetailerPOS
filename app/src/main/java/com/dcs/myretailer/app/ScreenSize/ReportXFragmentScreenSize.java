package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.FragmentReportxProductBinding;

public class ReportXFragmentScreenSize {
    public ReportXFragmentScreenSize(Context context, FragmentReportxProductBinding binding) {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
            FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsReporData.leftMargin = 20;
            binding.ReportProductListView.setLayoutParams(paramsReporData);
        } else if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
            String device = Query.GetDeviceData(Constraints.DEVICE);
            if (device.equals("M2-Max")) {
                FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                paramsReporData.leftMargin = 30;
                binding.ReportProductListView.setLayoutParams(paramsReporData);
            } else {
//                FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
                FrameLayout.LayoutParams paramsReporData1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        900);
                //paramsReporData.leftMargin = 20;
                binding.productLinearLayoutId.setLayoutParams(paramsReporData1);
                FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //paramsReporData.leftMargin = 20;
                binding.ReportProductListView.setLayoutParams(paramsReporData);
            }
        }
    }
}
