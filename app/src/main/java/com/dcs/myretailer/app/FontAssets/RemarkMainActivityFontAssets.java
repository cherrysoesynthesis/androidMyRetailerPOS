package com.dcs.myretailer.app.FontAssets;

import android.util.TypedValue;

import com.dcs.myretailer.app.databinding.ActivityRemarkMainBinding;

public class RemarkMainActivityFontAssets {
    public RemarkMainActivityFontAssets(ActivityRemarkMainBinding binding) {
        binding.txtVer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
        binding.txtLicense.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f);

        binding.KeyLicense.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
    }
}
