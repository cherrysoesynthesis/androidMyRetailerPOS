package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityDatabaseImportExportBinding;

public class DatabaseImportExportActivityScreenSize {
    public DatabaseImportExportActivityScreenSize(Context context, ActivityDatabaseImportExportBinding binding) {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            linearOverAllParams.leftMargin = 30;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }else if (terminalTypeVal.equals(Constraints.PAX)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.gravity = Gravity.CENTER;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }
    }
}
