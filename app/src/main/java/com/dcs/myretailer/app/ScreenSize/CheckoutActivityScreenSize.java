package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityCheckOutBinding;

public class CheckoutActivityScreenSize {
    public CheckoutActivityScreenSize(Context context, ActivityCheckOutBinding binding) {
        int widthSZ = Query.screenSize(context,"W");
        int heigthSZ = Query.screenSize(context,"H");
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
            binding.checkOutScrollView.setLayoutParams(new FrameLayout.LayoutParams(750,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT));

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(750,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.layCheckoutOverAll.setLayoutParams(params);

            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
            //FrameLayout.LayoutParams paramslayCardView = new FrameLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramslayCardView.leftMargin = 10;
            binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView);
//
            binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
//
            FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
//
//            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(580,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            paramslaySubCardViewDetails.leftMargin = 10;
            binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);
//
            LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(580,80);
            //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
            binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//            binding.checkoutOrderSummary.tota;..setLayoutParams(paramslaySubCardViewDetailsTotalAmt);

            binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView);
            binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView);
        }else if (terminalTypeVal.equals(Constraints.IMIN)) {
            String device = Query.GetDeviceData(Constraints.DEVICE);

            if (device.equals("M2-Max")) {

                LinearLayout.LayoutParams paramscheckOutScrollView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        700);

                binding.checkOutScrollView.setLayoutParams(paramscheckOutScrollView);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.layCheckoutOverAll.setLayoutParams(params);

                FrameLayout.LayoutParams paramsLayBillInformation = new FrameLayout.LayoutParams(720,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.LayBillInformation.setLayoutParams(paramsLayBillInformation);


                LinearLayout.LayoutParams paramsHeaderDescription = new LinearLayout.LayoutParams((int) ((widthSZ/10)*6.1),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                paramsHeaderDescription.leftMargin = 10;
                binding.checkoutInfo.HeaderDescription.setLayoutParams(paramsHeaderDescription);
//                binding.checkoutInfo.HeaderDescription.setBackgroundColor(getResources().getColor(R.color.color_red));

                LinearLayout.LayoutParams paramscheckOutListView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.checkOutListView.setLayoutParams(paramscheckOutListView);

                LinearLayout.LayoutParams paramslayCardView1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView1);

                LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                paramslayCardView.leftMargin = 10;
//
                binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
//
                FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
//
//            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//                LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(720,
//                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//                //paramslaySubCardViewDetails.leftMargin = 10;
//                binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);


                LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                //paramslaySubCardViewDetails.leftMargin = 10;
                // binding.checkoutOrderSummary.laySubCardViewDetails.setBackgroundColor(getResources().getColor(R.color.main_green_color));
                binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);

//                LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(330,
//                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);



                LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams((int) ((widthSZ/10)*4.5),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                //paramslayHCheckout.leftMargin = 10;


                binding.checkoutOrderSummary.totitmHeaderCheckout.setLayoutParams(paramslayHCheckout);
//                binding.checkoutOrderSummary.totitmHeaderCheckout
//                        .setBackgroundColor(getResources().getColor(R.color.white));



//                binding.checkoutOrderSummary.checkoutTotalItem
//                        .setBackgroundColor(getResources().getColor(R.color.color_red));

                binding.checkoutOrderSummary.textViewNamehgjhgj.setLayoutParams(paramslayHCheckout);
                //binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramslayHCheckout);
                binding.checkoutOrderSummary.textPaymentCash.setLayoutParams(paramslayHCheckout);
                binding.checkoutOrderSummary.totalNettTitle.setLayoutParams(paramslayHCheckout);


                //binding.paymentTypeNameCheckout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                binding.checkoutOrderSummary.totitmHeaderCheckout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.textViewNamehgjhgj.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.checkoutSubtotal.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                LinearLayout.LayoutParams billitemdiscountheader_width = new LinearLayout.LayoutParams((int) ((widthSZ/10)*6.6),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.billitemdiscountheader.setLayoutParams(billitemdiscountheader_width);

                binding.checkoutOrderSummary.billitemdiscountheader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.txtItemDiscountCheckout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);


                LinearLayout.LayoutParams billDiscountName_width = new LinearLayout.LayoutParams((int) ((widthSZ/10)*6.6),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.billDiscountName.setLayoutParams(billDiscountName_width);

                //binding.checkoutOrderSummary.txtDiscountCheckout.setBackgroundColor(getResources().getColor(R.color.color_red));

                binding.checkoutOrderSummary.billDiscountName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                LinearLayout.LayoutParams txtDiscountCheckout_width = new LinearLayout.LayoutParams((int) ((widthSZ/10)*4.8),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                txtDiscountCheckout_width.gravity = Gravity.RIGHT;
                binding.checkoutOrderSummary.txtDiscountCheckout.setLayoutParams(txtDiscountCheckout_width);

                binding.checkoutOrderSummary.txtDiscountCheckout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);


                binding.checkoutOrderSummary.serviceChargesname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.incTaxname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.taxname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.taxValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.roundAdjHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.roundAdjValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.textPaymentCash.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.textPaymentCashAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.checkoutOrderSummary.totalNettTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                binding.checkoutOrderSummary.checkoutTotal.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

                binding.checkoutOrderSummary.checkoutTotalItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                binding.checkoutOrderSummary.checkoutTotalItem.setPadding(100,0,0,0);

                LinearLayout.LayoutParams exctaxname_width = new LinearLayout.LayoutParams((int) ((widthSZ/10)*6.6),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.taxname.setLayoutParams(exctaxname_width);


                LinearLayout.LayoutParams paramsLayDis = new LinearLayout.LayoutParams(720,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.LayDis.setLayoutParams(paramsLayDis);

                LinearLayout.LayoutParams paramsOrderSummary = new LinearLayout.LayoutParams(230,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramsOrderSummary);

                LinearLayout.LayoutParams paramslayy = new LinearLayout.LayoutParams(70,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.btnAddDiscountCheckout.setLayoutParams(paramslayy);

//                LinearLayout.LayoutParams paramsDisCheckout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                paramsDisCheckout.gravity = Gravity.RIGHT;
//                binding.checkoutOrderSummary.txtDiscountCheckout.setLayoutParams(paramsDisCheckout);
//
                LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        80);
                //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
                binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//
                LinearLayout.LayoutParams paramsLaySubTotal = new LinearLayout.LayoutParams(720,
                        80);
                binding.checkoutOrderSummary.LaySubTotal.setLayoutParams(paramsLaySubTotal);

                LinearLayout.LayoutParams paramslayCardView2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        410);
                binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView2);

                LinearLayout.LayoutParams paramslayCardView3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView3);

                FrameLayout.LayoutParams paramscheckoutPaymentList = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                FrameLayout.LayoutParams paramscheckoutPaymentList = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        500);
                paramscheckoutPaymentList.leftMargin = 5;
                binding.checkoutPaymentList.layPaymentMethod.setLayoutParams(paramscheckoutPaymentList);

            } else {
                LinearLayout.LayoutParams paramscheckOutScrollView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        700);

                binding.checkOutScrollView.setLayoutParams(paramscheckOutScrollView);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.layCheckoutOverAll.setLayoutParams(params);

                FrameLayout.LayoutParams paramsLayBillInformation = new FrameLayout.LayoutParams(720,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.LayBillInformation.setLayoutParams(paramsLayBillInformation);


                LinearLayout.LayoutParams paramsHeaderDescription = new LinearLayout.LayoutParams(350,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.HeaderDescription.setLayoutParams(paramsHeaderDescription);

                LinearLayout.LayoutParams paramscheckOutListView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.checkOutListView.setLayoutParams(paramscheckOutListView);

                LinearLayout.LayoutParams paramslayCardView1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView1);

                LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                paramslayCardView.leftMargin = 10;
//
                binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
//
                FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
//
//            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                //paramslaySubCardViewDetails.leftMargin = 10;
                binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);

//                LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(330,
//                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(170,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                //paramslayHCheckout.leftMargin = 10;
                binding.checkoutOrderSummary.totitmHeaderCheckout.setLayoutParams(paramslayHCheckout);
                binding.checkoutOrderSummary.textViewNamehgjhgj.setLayoutParams(paramslayHCheckout);
                //binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramslayHCheckout);
                binding.checkoutOrderSummary.textPaymentCash.setLayoutParams(paramslayHCheckout);
                binding.checkoutOrderSummary.totalNettTitle.setLayoutParams(paramslayHCheckout);

                LinearLayout.LayoutParams paramsLayDis = new LinearLayout.LayoutParams(600,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.LayDis.setLayoutParams(paramsLayDis);

                LinearLayout.LayoutParams paramsOrderSummary = new LinearLayout.LayoutParams(230,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramsOrderSummary);

                LinearLayout.LayoutParams paramslayy = new LinearLayout.LayoutParams(70,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkoutOrderSummary.btnAddDiscountCheckout.setLayoutParams(paramslayy);

                LinearLayout.LayoutParams paramsDisCheckout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                paramsDisCheckout.gravity = Gravity.RIGHT;
                binding.checkoutOrderSummary.txtDiscountCheckout.setLayoutParams(paramsDisCheckout);
//
                LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        80);
                //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
                binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//            binding.checkoutOrderSummary.tota;..setLayoutParams(paramslaySubCardViewDetailsTotalAmt);

                LinearLayout.LayoutParams paramslayCardView2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        410);
                binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView2);

                LinearLayout.LayoutParams paramslayCardView3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView3);

                FrameLayout.LayoutParams paramscheckoutPaymentList = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                paramscheckoutPaymentList.leftMargin = 5;
                binding.checkoutPaymentList.layPaymentMethod.setLayoutParams(paramscheckoutPaymentList);
            }
        }else if (terminalTypeVal.equals(Constraints.PAX)) {
            LinearLayout.LayoutParams paramscheckOutScrollView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    770);

            binding.checkOutScrollView.setLayoutParams(paramscheckOutScrollView);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.layCheckoutOverAll.setLayoutParams(params);

            FrameLayout.LayoutParams paramsLayBillInformation = new FrameLayout.LayoutParams(720,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.LayBillInformation.setLayoutParams(paramsLayBillInformation);


            LinearLayout.LayoutParams paramsHeaderDescription = new LinearLayout.LayoutParams(350,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.HeaderDescription.setLayoutParams(paramsHeaderDescription);

            LinearLayout.LayoutParams paramscheckOutListView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.checkOutListView.setLayoutParams(paramscheckOutListView);

            LinearLayout.LayoutParams paramslayCardView1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView1);

            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramslayCardView.leftMargin = 10;
//
            binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
//
            FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
//
//            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            //paramslaySubCardViewDetails.leftMargin = 10;
            binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);

//                LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(330,
//                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(170,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            //paramslayHCheckout.leftMargin = 10;
            binding.checkoutOrderSummary.totitmHeaderCheckout.setLayoutParams(paramslayHCheckout);
            binding.checkoutOrderSummary.textViewNamehgjhgj.setLayoutParams(paramslayHCheckout);
            //binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramslayHCheckout);
            binding.checkoutOrderSummary.textPaymentCash.setLayoutParams(paramslayHCheckout);
            binding.checkoutOrderSummary.totalNettTitle.setLayoutParams(paramslayHCheckout);

            LinearLayout.LayoutParams paramsLayDis = new LinearLayout.LayoutParams(600,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.LayDis.setLayoutParams(paramsLayDis);

            LinearLayout.LayoutParams paramsOrderSummary = new LinearLayout.LayoutParams(230,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramsOrderSummary);

            LinearLayout.LayoutParams paramslayy = new LinearLayout.LayoutParams(70,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.btnAddDiscountCheckout.setLayoutParams(paramslayy);

            LinearLayout.LayoutParams paramsDisCheckout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            paramsDisCheckout.gravity = Gravity.RIGHT;
            binding.checkoutOrderSummary.txtDiscountCheckout.setLayoutParams(paramsDisCheckout);
//
            LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    80);
            //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
            binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//            binding.checkoutOrderSummary.tota;..setLayoutParams(paramslaySubCardViewDetailsTotalAmt);


            // binding.checkoutOrderSummary.taxLayout,binding.checkoutOrderSummary.taxname,
            // binding.checkoutOrderSummary.taxValue);

//            binding.checkoutOrderSummary.taxLayout.setBackgroundColor(getResources().getColor(
//                    R.color.light_green));

            LinearLayout.LayoutParams exctaxname_width = new LinearLayout.LayoutParams((int) ((widthSZ/10)*5.58),
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.taxname.setLayoutParams(exctaxname_width);
//            binding.checkoutOrderSummary.taxname.setBackgroundColor(getResources().getColor(
//                                    R.color.color_red));
//            binding.checkoutOrderSummary.taxValue.setBackgroundColor(getResources().getColor(
//                    R.color.white));

            LinearLayout.LayoutParams paramslayCardView2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    410);
            binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView2);

            LinearLayout.LayoutParams paramslayCardView3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView3);

            FrameLayout.LayoutParams paramscheckoutPaymentList = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramscheckoutPaymentList.leftMargin = 5;
            binding.checkoutPaymentList.layPaymentMethod.setLayoutParams(paramscheckoutPaymentList);

        }else if (terminalTypeVal.equals(Constraints.INGENICO)) {
//            checkOutScrollView.setMinimumWidth(ENUM.checkOutScrollViewWidthIngenico);
//            checkOutScrollView.setMinimumHeight(ENUM.checkOutScrollViewWidthIngenico);

//            LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT);
//            checkOutScrollView.setLayoutParams(scrollParams);
//            LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
//            checkOutScrollView.setLayoutParams(scrollParams);

//            checkOutScrollView.setLayoutParams(new RelativeLayout.LayoutParams(
//                    LayoutParams.FILL_PARENT, 350));


            LinearLayout.LayoutParams paramscheckOutScrollView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    770);

            binding.checkOutScrollView.setLayoutParams(paramscheckOutScrollView);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.layCheckoutOverAll.setLayoutParams(params);

            FrameLayout.LayoutParams paramsLayBillInformation = new FrameLayout.LayoutParams(720,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.LayBillInformation.setLayoutParams(paramsLayBillInformation);


            LinearLayout.LayoutParams paramsHeaderDescription = new LinearLayout.LayoutParams(350,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.HeaderDescription.setLayoutParams(paramsHeaderDescription);

            LinearLayout.LayoutParams paramscheckOutListView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.checkOutListView.setLayoutParams(paramscheckOutListView);

            LinearLayout.LayoutParams paramslayCardView1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView1);

            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramslayCardView.leftMargin = 10;
//
            binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
//
            FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
//
//            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            //paramslaySubCardViewDetails.leftMargin = 10;
            binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);

//                LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(330,
//                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams paramslayHCheckout = new LinearLayout.LayoutParams(170,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            //paramslayHCheckout.leftMargin = 10;
            binding.checkoutOrderSummary.totitmHeaderCheckout.setLayoutParams(paramslayHCheckout);
            binding.checkoutOrderSummary.textViewNamehgjhgj.setLayoutParams(paramslayHCheckout);
            //binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramslayHCheckout);
            binding.checkoutOrderSummary.textPaymentCash.setLayoutParams(paramslayHCheckout);
            binding.checkoutOrderSummary.totalNettTitle.setLayoutParams(paramslayHCheckout);

            LinearLayout.LayoutParams paramsLayDis = new LinearLayout.LayoutParams(600,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.LayDis.setLayoutParams(paramsLayDis);

            LinearLayout.LayoutParams paramsOrderSummary = new LinearLayout.LayoutParams(230,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.billDiscountName.setLayoutParams(paramsOrderSummary);

            LinearLayout.LayoutParams paramslayy = new LinearLayout.LayoutParams(70,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.btnAddDiscountCheckout.setLayoutParams(paramslayy);

            LinearLayout.LayoutParams paramsDisCheckout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            paramsDisCheckout.gravity = Gravity.RIGHT;
            binding.checkoutOrderSummary.txtDiscountCheckout.setLayoutParams(paramsDisCheckout);
//
            LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    80);
            //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
            binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//            binding.checkoutOrderSummary.tota;..setLayoutParams(paramslaySubCardViewDetailsTotalAmt);


            // binding.checkoutOrderSummary.taxLayout,binding.checkoutOrderSummary.taxname,
            // binding.checkoutOrderSummary.taxValue);

//            binding.checkoutOrderSummary.taxLayout.setBackgroundColor(getResources().getColor(
//                    R.color.light_green));

            LinearLayout.LayoutParams exctaxname_width = new LinearLayout.LayoutParams((int) ((widthSZ/10)*5.58),
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.checkoutOrderSummary.taxname.setLayoutParams(exctaxname_width);
//            binding.checkoutOrderSummary.taxname.setBackgroundColor(getResources().getColor(
//                                    R.color.color_red));
//            binding.checkoutOrderSummary.taxValue.setBackgroundColor(getResources().getColor(
//                    R.color.white));

            LinearLayout.LayoutParams paramslayCardView2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    410);
            binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView2);

            LinearLayout.LayoutParams paramslayCardView3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView3);

            FrameLayout.LayoutParams paramscheckoutPaymentList = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramscheckoutPaymentList.leftMargin = 5;
            binding.checkoutPaymentList.layPaymentMethod.setLayoutParams(paramscheckoutPaymentList);


        }
    }
}
