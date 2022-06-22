package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityMallInterfaceBinding;

public class MallInterfaceActivityScreenSize {
    public MallInterfaceActivityScreenSize(Context context, ActivityMallInterfaceBinding binding) {
        String device = Query.GetDeviceData(Constraints.DEVICE);
        String  terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);

        if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO)) {
            ConstraintLayout.LayoutParams constraintLayoutAllParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    1500);
            //constraintLayoutAllParams.leftMargin = 10;
            binding.constraintLayout.setLayoutParams(constraintLayoutAllParams);

            FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    1110);

            //165
            //175
            //185

            linearOverAllParams.topMargin = 20;
            //linearOverAllParams.leftMargin = 10;
            binding.recyclerViewLinearLay.setLayoutParams(linearOverAllParams);

            LinearLayout.LayoutParams recyclerviewIdOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    1110);
            //linearOverAllParams.leftMargin = 10;
            binding.recyclerviewId.setLayoutParams(recyclerviewIdOverAllParams);
        }
    }
}
