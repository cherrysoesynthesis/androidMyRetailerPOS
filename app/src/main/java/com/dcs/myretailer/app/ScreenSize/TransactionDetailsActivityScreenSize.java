package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityTransactionDetailsBinding;

public class TransactionDetailsActivityScreenSize {
    public TransactionDetailsActivityScreenSize(Context context, ActivityTransactionDetailsBinding binding) {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);


        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.ScrollView01.setLayoutParams(params);

            FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.transactiondetaillinerarlayout.setLayoutParams(params1);

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(750,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            params2.leftMargin = 30;
            binding.layCheckoutOverAll.setLayoutParams(params2);

            LinearLayout.LayoutParams LayHeaderp = new LinearLayout.LayoutParams(400,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.LayHeader.setLayoutParams(LayHeaderp);
//
//            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
//            //FrameLayout.LayoutParams paramslayCardView = new FrameLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
//            paramslayCardView.leftMargin = 10;
//            binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView);
////
//            binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
////
//            FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//            binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
////
////            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
////                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//            LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//            paramslaySubCardViewDetails.leftMargin = 10;
//            binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);
////
//            LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(580,80);
//            //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
//            binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
////            binding.checkoutOrderSummary.tota;..setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//
//            binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView);
//            binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView);
        }else if (terminalTypeVal.equals(Constraints.IMIN)){
            String device = Query.GetDeviceData(Constraints.DEVICE);

            if (device.equals("M2-Max")) {
                ScrollView.LayoutParams params = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                binding.ScrollView01.setLayoutParams(params);

                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.transactiondetaillinerarlayout.setLayoutParams(params1);

                FrameLayout.LayoutParams paramtransactionheaderlay = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.headerlay.setLayoutParams(paramtransactionheaderlay);

                LinearLayout.LayoutParams paramtransactionheaderlay1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.headerlay1.setLayoutParams(paramtransactionheaderlay1);


                LinearLayout.LayoutParams paramLinearDt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.LinearDt.setLayoutParams(paramLinearDt);


                LinearLayout.LayoutParams paramtransactionbillno = new LinearLayout.LayoutParams(500,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.laytransactionbillno.setLayoutParams(paramtransactionbillno);


                LinearLayout.LayoutParams transactiondatetime = new LinearLayout.LayoutParams(470,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.transactionDatetime.setLayoutParams(transactiondatetime);


                LinearLayout.LayoutParams paramscard = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.cardLay.setLayoutParams(paramscard);
//
                LinearLayout.LayoutParams paramscard1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.cardView111.setLayoutParams(paramscard1);
//
                LinearLayout.LayoutParams paramscard11 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.cardView111111.setLayoutParams(paramscard11);
//
                LinearLayout.LayoutParams paramscheckOutListView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkOutListView.setLayoutParams(paramscheckOutListView);
//
                LinearLayout.LayoutParams paramstransCard = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.transactionCard.setLayoutParams(paramstransCard);

                FrameLayout.LayoutParams paramscardsale = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.laySaleSummaryDetails.setLayoutParams(paramscardsale);
//
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                params2.leftMargin = 30;
                params2.rightMargin = 30;
                binding.layCheckoutOverAll.setLayoutParams(params2);

                LinearLayout.LayoutParams paramstamt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                paramstamt.leftMargin = 30;
                paramstamt.rightMargin = 30;
                binding.layTotalAmt.setLayoutParams(params2);


//                LinearLayout.LayoutParams paramsimgReprint = new LinearLayout.LayoutParams((int) ((widthSZ)/10 *9),
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                paramsimgReprint.leftMargin = (int) ((widthSZ)/10 * 0.5); ;
//                binding.imgReprint.setLayoutParams(paramsimgReprint);

//                LinearLayout.LayoutParams LayHeaderp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                binding.LayHeader.setLayoutParams(LayHeaderp);
//
//                LinearLayout.LayoutParams paramsLayDescription = new LinearLayout.LayoutParams(350,
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                paramsLayDescription.leftMargin = 10;
//                binding.LayDescription.setLayoutParams(paramsLayDescription);


                binding.totalitmHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTotalItems.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.subtotaltransactiondetails.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionGrosssales.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.txttotalitemdis.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTotalitemdis.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.totalBillDiscount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTotalbilldis.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.serviceChargesname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.serviceChargesValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.incTaxNamee.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.TaxNamee.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTaxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.txtRoundAdj.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.roundAdjValues.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.txtTotalAmt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                binding.transactionTotalamount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

                int widthSZ = Query.screenSize(context,"W");
                int heigthSZ = Query.screenSize(context,"H");

                LinearLayout.LayoutParams LayHeader_width = new LinearLayout.LayoutParams((int) ((widthSZ)),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.LayHeader.setLayoutParams(LayHeader_width);
                //binding.LayHeader.setBackgroundColor(getResources().getColor(R.color.light_green));

                LinearLayout.LayoutParams LayDescription_width = new LinearLayout.LayoutParams((int) ((widthSZ/10) * 6.4),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.LayDescription.setLayoutParams(LayDescription_width);
                // binding.LayDescription.setBackgroundColor(getResources().getColor(R.color.dark_blue));

//        LinearLayout.LayoutParams billDiscountName_width = new LinearLayout.LayoutParams((int) ((widthSZ/10) * 3.5),
//                android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//        binding.txtdescription.setLayoutParams(billDiscountName_width);

                //  binding.txtdescription.setBackgroundColor(getResources().getColor(R.color.color_red));
            }
        }
    }
}
