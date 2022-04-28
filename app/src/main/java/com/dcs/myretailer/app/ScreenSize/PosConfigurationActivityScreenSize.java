package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.databinding.ActivityPosConfigurationIminBinding;

public class PosConfigurationActivityScreenSize {
    public PosConfigurationActivityScreenSize(Context context, ActivityPosConfigurationIminBinding binding_imin) {
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(750,
                android.widget.Toolbar.LayoutParams.MATCH_PARENT);
        params2.leftMargin = 30;
        binding_imin.layKitchenPrinter.setLayoutParams(params2);
    }
}
