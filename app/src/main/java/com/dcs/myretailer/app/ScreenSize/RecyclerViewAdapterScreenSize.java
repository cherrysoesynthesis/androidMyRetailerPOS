package com.dcs.myretailer.app.ScreenSize;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.CardveiwItemBookBinding;

public class RecyclerViewAdapterScreenSize {
    public RecyclerViewAdapterScreenSize(CardveiwItemBookBinding binding){
        String chk_hide_img = Query.GetOptions(20);
        if (chk_hide_img.equals("1")) {

            String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
            if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(180,
                        180);
                //params.topMargin = 10;
                binding.productLinearLayoutId.setLayoutParams(params);

                LinearLayout.LayoutParams paramsCardView = new LinearLayout.LayoutParams(160,
                        170);
                paramsCardView.leftMargin = 10;
                paramsCardView.rightMargin = 10;
                //paramsCardView.topMargin = 10;
                paramsCardView.bottomMargin = 10;
                binding.cardviewId.setLayoutParams(paramsCardView);


                binding.bookImgId.setVisibility(View.GONE);

                FrameLayout.LayoutParams paramsTitle = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsTitle.leftMargin = 10;
                paramsTitle.topMargin = 10;
                binding.bookTitleId.setLayoutParams(paramsTitle);

                FrameLayout.LayoutParams paramsPrice = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsPrice.leftMargin = 10;
                paramsPrice.topMargin = 90;
                binding.bookPriceId.setLayoutParams(paramsPrice);

                RelativeLayout.LayoutParams totalCountImg = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                totalCountImg.leftMargin = 70;
                totalCountImg.topMargin = 100;
                binding.txtItemCcount.setLayoutParams(totalCountImg);

            }else {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(234,
                        205);
                binding.productLinearLayoutId.setLayoutParams(params);

                LinearLayout.LayoutParams paramsCardView = new LinearLayout.LayoutParams(224,
                        200);
                paramsCardView.leftMargin = 10;
                binding.cardviewId.setLayoutParams(paramsCardView);


                binding.bookImgId.setVisibility(View.GONE);

                FrameLayout.LayoutParams paramsTitle = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsTitle.leftMargin = 10;
                paramsTitle.topMargin = 10;
                binding.bookTitleId.setLayoutParams(paramsTitle);

                FrameLayout.LayoutParams paramsPrice = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsPrice.leftMargin = 10;
                paramsPrice.topMargin = 90;
                binding.bookPriceId.setLayoutParams(paramsPrice);

                RelativeLayout.LayoutParams totalCountImg = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                totalCountImg.leftMargin = 80;
                totalCountImg.topMargin = 90;
                binding.txtItemCcount.setLayoutParams(totalCountImg);
            }
        }
    }
}
