package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.FragmentReportDateSheetDialogBinding;

public class ReportDateSheetFragmentScreenSize {
    public ReportDateSheetFragmentScreenSize(Context context, FragmentReportDateSheetDialogBinding binding) {
        String device = Query.GetDeviceData(Constraints.DEVICE);

        if (device.equals("M2-Max")) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            params.leftMargin = 30;
            params.topMargin = 30;
            params.gravity = Gravity.CENTER;
            binding.Lay1.setLayoutParams(params);
            binding.LayStarting.setLayoutParams(params);
            binding.LayEnding.setLayoutParams(params);
            binding.LayPrevious.setLayoutParams(params);
            binding.btnApplyDateRange.setLayoutParams(params);
        }
    }
}
