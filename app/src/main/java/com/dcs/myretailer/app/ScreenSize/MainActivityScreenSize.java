package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityMainBinding;

public class MainActivityScreenSize {
    public MainActivityScreenSize(Context context, ActivityMainBinding binding) {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String modelVal = Query.GetDeviceData(Constraints.MODEL);

        Log.i("Teerminail_","terminalTypeVal_"+terminalTypeVal);
        if (terminalTypeVal.equals(Constraints.INGENICO)){
            if (modelVal.equals(Constraints.INGENICO_MODEL_APOS_A8OVS)) {

//                DisplayMetrics displaymetrics = new DisplayMetrics();
//                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//                int screenHeight = displaymetrics.heightPixels;
//                int screenWidth = displaymetrics.widthPixels;
//                int screendensity = displaymetrics.densityDpi;

                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 760));
//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

                binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(750, 120));
                binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 100));

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 120));

            }else if (modelVal.equals(Constraints.INGENICO_MODEL_DX8000)){
                //            navView.setLayoutParams(new LinearLayout.LayoutParams(650, 90));

//                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 820));
////            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
////            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));
//
//                binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(750, 110));
//                binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 100));
//
//                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 120));


                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 950));
//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

                binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(750, 110));
                binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 100));

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 120));

            }else {
                //            navView.setLayoutParams(new LinearLayout.LayoutParams(650, 90));

                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 850));
//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

                binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(750, 120));
                binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 110));
                //binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 100));

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 120));

            }
        }else if (terminalTypeVal.equals(Constraints.PAX_E600M)) {

//            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(750, 560);
            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(750, 570);
//            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(750, 500);
            linearparams.leftMargin = 20;

            binding.pager.setLayoutParams(linearparams);

//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

            LinearLayout.LayoutParams linearParamsLayBtnCheckout = new LinearLayout.LayoutParams(610, 90);
            linearParamsLayBtnCheckout.leftMargin = 15;

            binding.linearLayCheckoutBtn.setLayoutParams(linearParamsLayBtnCheckout);
            binding.linearLayCheckoutBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

//            LinearLayout.LayoutParams linearParamsBtnCheckout = new LinearLayout.LayoutParams(540, 70);
            LinearLayout.LayoutParams linearParamsBtnCheckout = new LinearLayout.LayoutParams(540, 80);
            //linearParamsBtnCheckout.leftMargin = 30;

            binding.btnCheckout.setLayoutParams(linearParamsBtnCheckout);

//            binding.navView.setLayoutParams(new LinearLayout.LayoutParams(620, 80));
            binding.navView.setLayoutParams(new LinearLayout.LayoutParams(620, 90));
        }else if (terminalTypeVal.equals(Constraints.IMIN)) {

            String device = Query.GetDeviceData(Constraints.DEVICE);

            if (device.equals("M2-Max")) {

                //binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 900));
                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 890));
//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

                binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(850, 100));
                binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(780, 90));

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(820, 100));

            } else {

//                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 750));
                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 825));
                //binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 700));
//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

                binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(750, 100));
                binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 90));

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 105));
            }
        }else if (terminalTypeVal.equals(Constraints.PAX)) {

            binding.pager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 920));
//            viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 850));
//            viewPager.setBackgroundColor(Color.parseColor("#E3E1E1"));

            binding.linearLayCheckoutBtn.setLayoutParams(new LinearLayout.LayoutParams(750, 100));
            binding.btnCheckout.setLayoutParams(new LinearLayout.LayoutParams(680, 90));

            binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 90));

        }
    }
}
