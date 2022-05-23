package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentReportxProductBinding;

import java.util.ArrayList;

public class ReportXFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<String> ProductQty = new ArrayList<String>();
    public static ArrayList<String> ProductName = new ArrayList<String>();
    public static ArrayList<String> ProductPrice = new ArrayList<String>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public static ArrayList<String> reportProductHeaderArr = new ArrayList<String>();
    public static ArrayList<String> reportProductNameArr = new ArrayList<String>();
    public static ArrayList<String> reportProductQtyArr = new ArrayList<String>();
    public static ArrayList<String> reportProductPriceArr = new ArrayList<String>();
    Handler mHandler;
    public static String St = "0";
    public static String str_header = "";
    public static String str_name = "";
    public static String str_price = "";
    public static String str_header1 = "";
    public static String str_name1 = "";
    public static String str_price1 = "";
    public static String str_header2 = "";
    public static String str_name2 = "";
    public static String str_price2 = "";
    public static String str_header3 = "";
    public static String str_name3 = "";
    public static String str_price3 = "";
    public static String str_header4 = "";
    public static String str_name4 = "";
    public static String str_price4 = "";
    public static String str_header5 = "";
    public static String str_name5 = "";
    public static String str_price5 = "";
    public static String str_header6 = "";
    public static String str_name6 = "";
    public static String str_price6 = "";
    String str_header7 = "";
    String str_name7 = "";
    String str_price7 = "";
    String str = "";
    String str2 = "";
    String str3 = "";
    String str4 = "";
    String str5 = "";
    String str6 = "";
    public static String str7 = "";
    public static Double TotalNettSales = 0.0;
    public static Integer BillCancelTotalQty = 0;
    public static Integer TotalQty = 0;
    public static Double BillCancel = 0.0;
    String BillTax = "0";
    public static Double TotalBillDisountTax = 0.0;
    public static Double TotalBillDisountAmountCancel = 0.0;
    public static Double TotalBillServiceChargesCancel = 0.0;
    public static Double GrossSales = 0.0;
    public static Double TaxTotal = 0.0;
    public static Double TotalBillDisount = 0.0;
    public static Double TotalRoundAdjSales = 0.0;
    public static Double ServiceChargesSales = 0.0;
    public static Double TotalTaxSales = 0.0;
    public static Integer TotalQtyProduct = 0;
    public static Double TotalNettSalesProduct = 0.0;
    public static String GrossSalesProduct = "0";
    String TaxTotalProduct = "0";
    public static String TotalBillDisountProduct = "0";
    String BillCancelTotalQtyProduct = "";
    String BillCancelProduct = "";
    public static String BillTaxProduct = "";
    public static Double TotalBillDisountTaxProduct = 0.0;
    public static Double TotalNettSalesProductCancel = 0.0;
    public static Double TotalProductServiceCharges = 0.0;
    public static Integer PaymentTypeCount = 0;
    public static String PaymentTypeAmount = "0";
    public static String PaymentTypeName = "0";
//    Integer DicountCount = 0;
    public static String DicountName = "";
    public static String DicountAmount ="";
    public static String CancelDicountName = "";
    public static String CancelDicountAmount ="";
    public static Integer TotalTaxesCount = 0;
    public static Double TotalTaxesAmount = 0.0;
    public static Integer TotalQtyCategory = 0;
    public static Double TotalNettSalesCategory = 0.0;
    public static Double GrossSalesCategory = 0.0;
    String TaxTotalCategory = "0";
    public static Double TotalBillDisountCategory = 0.0;
    public static Integer BillCancelTotalQtyCategory = 0;
    String BillTaxCategory = "";
    public static Double TotalBillDisountTaxCategory = 0.0;
    public static Integer TotalQtyCategoryCancel = 0;
    public static Double TotalNettSalesCategoryCancel = 0.0;
    public static Integer CategoryCancelItemDiscount_Qty = 0;
    public static Double CategoryCancelItemDiscount_Amount = 0.0;
    public static Integer CategoryCancelTotalBillDiscount_Qty = 0;
    public static Double CategoryCancelTotalBillDiscount_Amount = 0.0;
    public static Double TotalNettSalesProductRefund = 0.0;
    public static Integer BillRefundTotalQty = 0;
    public static Double BillRefund = 0.0;
    public static Double TotalBillDisountAmountRefund = 0.0;
    public static Double TotalBillServiceChargesRefund = 0.0;
    public static Double TotalBillDisountTaxRefund = 0.0;
    public static ArrayList<Integer> ClosingPeriodBillIDArr = new ArrayList<Integer>();
    public static FragmentReportxProductBinding binding = null;
    public static Context mcontext;
    public static ReportXAdapter customAdapter;
    public ReportXFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment 
     */
//    // TODO: Rename and change types and number of parameters
//    public static ReportProductFragment newInstance(String param1, String param2) {
//        ReportProductFragment fragment = new ReportProductFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        mcontext = context;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate( R.layout.fragment_reportx_product, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_reportx_product, container, false);


        mcontext = getContext();

        //ReportActivity.LaySearchPrint.setVisibility(View.INVISIBLE);

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

        updateReportFragmentXFun();

        View view = binding.getRoot();

//        mHandler = new Handler();
//        m_Runnable.run();
        return view;
    }




//    onres
//
//    @Override
//    protected void onResume() {
//        Log.i("onResumeRActivity_","Onresume");
//        updateReportDateAndDataFun();
//        super.onResume();
//    }

//    @Nullable
//    @Override
//    public Context getContext() {
//        mcontext = getContext();
//        return super.getContext();
//    }

    public static void updateReportFragmentXFun() {

        getPrintSales(ReportActivity.start, ReportActivity.end);

        ClearArrs();

        reportProductHeaderArr.add(str_header);
        reportProductHeaderArr.add(str_header1);
        reportProductHeaderArr.add(str_header2);
        reportProductHeaderArr.add(str_header3);
        reportProductHeaderArr.add(str_header4);
        if (refund.equals("1")) {
            reportProductHeaderArr.add(str_header5);
        }
        reportProductHeaderArr.add(str_header6);

        reportProductNameArr.add(str_name);
        reportProductNameArr.add(str_name1);
        reportProductNameArr.add(str_name2);
        reportProductNameArr.add(str_name3);
        reportProductNameArr.add(str_name4);
        if (refund.equals("1")) {
            reportProductNameArr.add(str_name5);
        }
        reportProductNameArr.add(str_name6);

        reportProductPriceArr.add(str_price);
        reportProductPriceArr.add(str_price1);
        reportProductPriceArr.add(str_price2);
        reportProductPriceArr.add(str_price3);
        reportProductPriceArr.add(str_price4);
        if (refund.equals("1")) {
            reportProductPriceArr.add(str_price5);
        }
        reportProductPriceArr.add(str_price6);

        //binding.ReportProductListView.removeAllViewsInLayout();
        ReportXAdapter customAdapter = new ReportXAdapter(mcontext, reportProductNameArr, reportProductPriceArr, reportProductHeaderArr);

        binding.ReportProductListView.setAdapter(customAdapter);
//            customAdapter.notifyDataSetInvalidated();
        customAdapter.notifyDataSetChanged();

        //new AsyncTaskupdateReportFragmentXFun(mcontext,binding).execute(ReportActivity.start, ReportActivity.end);
    }

    private static void ClearArrs() {
        reportProductHeaderArr.clear();
        reportProductHeaderArr.clear();
        reportProductHeaderArr.clear();
        reportProductHeaderArr.clear();
        reportProductHeaderArr.clear();
        reportProductHeaderArr.clear();
        reportProductHeaderArr.clear();

        reportProductNameArr.clear();
        reportProductNameArr.clear();
        reportProductNameArr.clear();
        reportProductNameArr.clear();
        reportProductNameArr.clear();
        reportProductNameArr.clear();
        reportProductNameArr.clear();

        reportProductPriceArr.clear();
        reportProductPriceArr.clear();
        reportProductPriceArr.clear();
        reportProductPriceArr.clear();
        reportProductPriceArr.clear();
        reportProductPriceArr.clear();
        reportProductPriceArr.clear();

    }

//    public static class AsyncTaskupdateReportFragmentXFun extends AsyncTask<Object, ImageView, Void> {
//        FragmentReportxProductBinding mbinding = null;
//        Context mcontext = null;
//        ArrayList<String> reportProductHeaderArr = new ArrayList<String>();
//        ArrayList<String> reportProductNameArr = new ArrayList<String>();
//        ArrayList<String> reportProductQtyArr = new ArrayList<String>();
//        ArrayList<String> reportProductPriceArr = new ArrayList<String>();
//
//        public AsyncTaskupdateReportFragmentXFun(Context context, FragmentReportxProductBinding binding) {
//            mcontext = context;
//            mbinding = binding;
//        }
//
//        protected Void doInBackground(Object... params) {
//            String start = (String) params[0];
//            String end = (String) params[1];
//
////        if (!checkingZClose.equals(Constraints.ZClose)) {
//            getPrintSales(start,end);
//
////            reportProductHeaderArr.add(str_header);
////            reportProductHeaderArr.add(str_header1);
////            reportProductHeaderArr.add(str_header2);
////            reportProductHeaderArr.add(str_header3);
////            reportProductHeaderArr.add(str_header4);
////            reportProductHeaderArr.add(str_header5);
////            reportProductHeaderArr.add(str_header6);
////
////            reportProductNameArr.add(str_name);
////            reportProductNameArr.add(str_name1);
////            reportProductNameArr.add(str_name2);
////            reportProductNameArr.add(str_name3);
////            reportProductNameArr.add(str_name4);
////            reportProductNameArr.add(str_name5);
////            reportProductNameArr.add(str_name6);
////
////            reportProductPriceArr.add(str_price);
////            reportProductPriceArr.add(str_price1);
////            reportProductPriceArr.add(str_price2);
////            reportProductPriceArr.add(str_price3);
////            reportProductPriceArr.add(str_price4);
////            reportProductPriceArr.add(str_price5);
////            reportProductPriceArr.add(str_price6);
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//
//            reportProductHeaderArr.add(str_header);
//            reportProductHeaderArr.add(str_header1);
//            reportProductHeaderArr.add(str_header2);
//            reportProductHeaderArr.add(str_header3);
//            reportProductHeaderArr.add(str_header4);
//            if (refund.equals("1")) {
//                reportProductHeaderArr.add(str_header5);
//            }
//            reportProductHeaderArr.add(str_header6);
//
//            reportProductNameArr.add(str_name);
//            reportProductNameArr.add(str_name1);
//            reportProductNameArr.add(str_name2);
//            reportProductNameArr.add(str_name3);
//            reportProductNameArr.add(str_name4);
//            if (refund.equals("1")) {
//                reportProductNameArr.add(str_name5);
//            }
//            reportProductNameArr.add(str_name6);
//
//            reportProductPriceArr.add(str_price);
//            reportProductPriceArr.add(str_price1);
//            reportProductPriceArr.add(str_price2);
//            reportProductPriceArr.add(str_price3);
//            reportProductPriceArr.add(str_price4);
//            if (refund.equals("1")) {
//                reportProductPriceArr.add(str_price5);
//            }
//            reportProductPriceArr.add(str_price6);
//
//            //binding.ReportProductListView.removeAllViewsInLayout();
//            ReportXAdapter customAdapter = new ReportXAdapter(mcontext, reportProductNameArr, reportProductPriceArr, reportProductHeaderArr);
//
//            mbinding.ReportProductListView.setAdapter(customAdapter);
////            customAdapter.notifyDataSetInvalidated();
//            customAdapter.notifyDataSetChanged();
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            reportProductHeaderArr.clear();
//            reportProductHeaderArr.clear();
//            reportProductHeaderArr.clear();
//            reportProductHeaderArr.clear();
//            reportProductHeaderArr.clear();
//            reportProductHeaderArr.clear();
//            reportProductHeaderArr.clear();
//
//            reportProductNameArr.clear();
//            reportProductNameArr.clear();
//            reportProductNameArr.clear();
//            reportProductNameArr.clear();
//            reportProductNameArr.clear();
//            reportProductNameArr.clear();
//            reportProductNameArr.clear();
//
//            reportProductPriceArr.clear();
//            reportProductPriceArr.clear();
//            reportProductPriceArr.clear();
//            reportProductPriceArr.clear();
//            reportProductPriceArr.clear();
//            reportProductPriceArr.clear();
//            reportProductPriceArr.clear();
//        }
//    }

//    @Override
//    public void onStart() {
//        Log.i("report__","onresume___");
//        mcontext = getContext();
//        updateReportFragmentXFun(Constraints.NotZClose);
//        super.onStart();
//    }

    @Override
    public void onResume() {
        mcontext = getContext();
        updateReportFragmentXFun();
        super.onResume();
    }
        public static String sales = "0";
    public static String category = "0";
    public static String product_sales = "0";
    public static String payment = "0";
    public static String disocunt = "0";
    public static String tax = "0";
    public static String cancellation = "0";
    public static String refund = "0";
    //public static refer_info_sales = "0";

    public static void getPrintSales(String start, String end) {

        getSalesALLTotalSales(start,end);
        //getSalesALLTotalOnlineOrderSales(start,end);

        String sql = "SELECT Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales,Refund FROM ReportSettings";
        //Log.i("_sql__",sql);
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {

                    sales = c.getString(0);
                    category = c.getString(1);
                    product_sales = c.getString(2);
                    payment = c.getString(3);
                    disocunt = c.getString(4);
                    tax = c.getString(5);
                    cancellation = c.getString(6);
                    refund = c.getString(8);
                    //refer_info_sales = c.getString(7);
                }
            }
            c.close();
        }
        String device = Query.GetDeviceData(Constraints.DEVICE);
        //TotalSales
        if (sales.equals("1")){
           // str = Query.XZDataReportSalesShow(TotalNettSales,BillCancelTotalQty,TotalQty,TotalNettSales,GrossSales,TaxTotal,TotalBillDisount,TotalNettSales,BillCancel,BillCancelTotalQty);




            if (device.equals("M2-Max")) {
                str_header = Constraints.LINEXM2MAX;
                str_header += "\n" + "*TOTAL SALES*";
                str_header += "\n"  + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
            }else {
                str_header = Constraints.LINEX;
                str_header += "\n" + "*TOTAL SALES*";
                str_header += "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
            }

            str_name =  Constraints.BillPaid;
            str_name += "\n" +Constraints.BillCancel;
            str_name += "\n" + Constraints.BillRefund;
            str_name += "\n" +Constraints.QtySold;
            str_name += "\n" + Constraints.AMTNett;
            str_name += "\n" + Constraints.TaxTotal;
            str_name += "\n" + Constraints.AMTDiscount;
            str_name += "\n" + Constraints.AMTSurcharge;
            str_name += "\n" + Constraints.RoundAdj;
//            str_name +="\n" +ENUM.AMTCollected;
            str_name += "\n" + Constraints.QtyCancel;
//            str_name += "\n" + Constraints.AMTCancel;//12
            str_name += "\n" + Constraints.QtyRefund;//12
            if (TotalNettSales != null) {
                str_price = "$" + String.format("%.2f", Double.valueOf(TotalNettSales));
            }
            if (BillCancel != null) {
                str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
            }else {
                str_price += "\n" + "$0.00";
            }
            if (BillRefund != null) {
                str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillRefund));
            }else {
                str_price += "\n" + "$0.00";
            }
            str_price += "\n" +TotalQty;
            if (TotalNettSales != null) {
                str_price += "\n" + "$" + String.format("%.2f", Double.valueOf(TotalNettSales));
            }else {
                str_price += "\n" + "$0.00";
            }
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(GrossSales));
            //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TaxTotal));

//            TotalTaxSales = GetInclusive(TotalTaxSales);
            if (TotalTaxSales != null) {
                str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
            }else {
                str_price += "\n" + "$0.00";
            }
            if (TotalBillDisount != null) {
                str_price += "\n" + "$" + String.format("%.2f", Double.valueOf(TotalBillDisount));
            }else {
                str_price += "\n" + "$0.00";
            }
            if (ServiceChargesSales != null) {
                str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(ServiceChargesSales));
            }else {
                str_price += "\n" + "$0.00";
            }
            if (TotalRoundAdjSales != null) {
                str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalRoundAdjSales));
            }else {
                str_price += "\n" + "$0.00";
            }
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSales));
            if (BillCancelTotalQty != null) {
                str_price += "\n" + Integer.valueOf(BillCancelTotalQty);
            }else {
                str_price += "\n" + "$0.00";
            }
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
            if (BillRefundTotalQty != null) {
                str_price += "\n" + Integer.valueOf(BillRefundTotalQty);
            }else {
                str_price += "\n" + "$0.00";
            }

        }
        if (category.equals("1")){
           // str2 = Query.XZDataReportCategoryShow(TotalQtyCategory,TotalNettSalesCategory,TaxTotalCategory,TotalBillDisountTaxCategory,TotalQtyCategoryCancel,TotalNettSalesCategory);



            if (device.equals("M2-Max")) {
                str_header1 = Constraints.LINEXM2MAX;
                str_header1 += "\n" + "*Category*";
                str_header1 += "\n"  + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
            } else {
                str_header1 = Constraints.LINEX;
                str_header1 += "\n" + "*Category*";
                str_header1 += "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
            }

            str_name1 =  Constraints.QtySold;
//            str_name1 += "\n" + Constraints.AMTNett;
            str_name1 += "\n" + Constraints.Amount;
//            //str_name1 += "\n" +ENUM.TaxTotal;
//            //str_name1 += "\n" +ENUM.AMTDiscount;
//            //str_name1 += "\n" +ENUM.AMTSurcharge;
//            str_name1 += "\n" + Constraints.QtyCancel;
//            str_name1 += "\n" + Constraints.AMTCancel;//7
//            str_name1 += "\n" +Constraints.ItemDiscountQty;
//            str_name1 += "\n" +Constraints.ItemDiscountAmount;//7
//            str_name1 += "\n" +Constraints.BillDiscountQty;
//            str_name1 += "\n" +Constraints.BillDiscountAmount;//7

            str_price1 = String.valueOf(TotalQtyCategory);
            //TotalNettSalesCategory = TotalNettSalesCategory - CategoryCancelTotalBillDiscount_Amount;
            //TotalNettSalesCategory = TotalNettSalesCategory ;
            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesCategory));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesCategory));
//            //str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TaxTotalCategory));
//            //str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTaxCategory));
//            //str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf("0.00"));
//            //str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalQtyCategoryCancel));
//            str_price1 += "\n" + String.valueOf(TotalQtyCategoryCancel);
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesCategoryCancel));
//            str_price1 += "\n" + String.valueOf(CategoryCancelItemDiscount_Qty);
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(CategoryCancelItemDiscount_Amount));
//            str_price1 += "\n" + String.valueOf(CategoryCancelTotalBillDiscount_Qty);
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(CategoryCancelTotalBillDiscount_Amount));
        }

        //ProductSales
        if (product_sales.equals("1")){
            //str3 = Query.XZDataReportProductShow(TotalQtyProduct,TotalNettSalesProduct,TaxTotalProduct,TotalBillDisountTaxProduct,TotalNettSalesProductCancel,TotalNettSalesProduct);


            if (device.equals("M2-Max")) {
                str_header2 = Constraints.LINEXM2MAX;
                str_header2 += "\n" + "*Product SALES*";
                str_header2 += "\n"  + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
            } else {
                str_header2 = Constraints.LINEX;
                str_header2 += "\n" + "*Product SALES*";
                str_header2 += "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
            }

            str_name2 = Constraints.QtySold;
//            str_name2 += "\n" + Constraints.AMTNett;
            str_name2 += "\n" + Constraints.Amount;
            //str_name2 += "\n" +ENUM.TaxTotal;
            //str_name2 += "\n" +ENUM.AMTDiscount;
            //str_name2 += "\n" +ENUM.AMTSurcharge;
            //str_name2 += "\n" + Constraints.QtyCancel;//7
            //str_name2 += "\n" + Constraints.AMTCancel;//7

            str_price2 = String.valueOf(TotalQtyProduct);
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesProduct));
            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesProduct));
            //str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TaxTotalProduct));
            //str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTaxProduct));
            //str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalProductServiceCharges));
            //str_price2 += "\n" + BillCancelTotalQty;
            //str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesProductCancel));
        }
        if (payment.equals("1")){
            //str4 = Query.XZDataReportPaymentShow(PaymentTypeCount,PaymentTypeAmount);
            if (device.equals("M2-Max")) {
                str_header3 = Constraints.LINEXM2MAX;
                str_header3 += "\n" + "*Payment*";
                str_header3 += "\n" + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
            } else {
                str_header3 = Constraints.LINEX;
                str_header3 += "\n" + "*Payment*";
                str_header3 += "\n" + Constraints.DOTLINEX + Constraints.DOTLINEX;
            }
            //str_name3 =  ENUM.Count;
            //str_name3 += "\n" +ENUM.Amount;//2
            //str_name3 += PaymentTypeName.toUpperCase();//2
            str_name3 = PaymentTypeName.toUpperCase();//2

            //str_price3 =  String.valueOf(PaymentTypeCount);
            //str_price3 += "\n" +"$"+ String.format("%.2f", Double.valueOf(PaymentTypeAmount));
            //str_price3 +=  PaymentTypeAmount;
            str_price3 =  PaymentTypeAmount;
            //str_price3 += "\n" +"$"+ String.format("%.2f", Double.valueOf(PaymentTypeName));
        }
        if (disocunt.equals("1")){
            //str5 = Query.XZDataReportDiscountShow(DicountCount,DicountAmount);

            if (device.equals("M2-Max")) {
                str_header4 = Constraints.LINEXM2MAX;
                str_header4 += "\n" + "*Discount*";
                str_header4 += "\n"  + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
            } else {
                str_header4 = Constraints.LINEX;
                str_header4 += "\n" + "*Discount*";
                str_header4 += "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
            }
//            str_name4 =  ENUM.Count;
//            str_name4 += "\n" +ENUM.Amount;//2
            str_name4 =  DicountName;
            str_name4 +=  CancelDicountName;

            //str_price4 += String.valueOf(DicountName);
            str_price4 = DicountAmount;
            str_price4 += CancelDicountAmount;
        }
//        if (tax.equals("1")){
//            //str6 = Query.XZDataReportTaxShow(TotalTaxesCount,TotalTaxesAmount);
//            str_header5 = ENUM.LINEX;
//            str_header5 += "\n" + "*Tax Sales*";
//            str_header5 += "\n"  + ENUM.DOTLINEX + ENUM.DOTLINEX;
////
////            str_name5 =  ENUM.Count;
////            str_name5 += "\n" +ENUM.Amount;//2
//
//            str_name5 =  "TotalTax";
//
//            //str_price5 = String.valueOf(TotalTaxesCount);
//            //str_price5 += "$"+ String.format("%.2f", Double.valueOf(TotalTaxesAmount)) +"\n";
////            Query.GetTotalTaxForItem();
//            String taxType = "0";
//            Cursor Cursor_tax = Query.GetTax();
//            if (Cursor_tax != null) {
//                if (Cursor_tax.moveToNext()) {
//                    taxType = Cursor_tax.getString(1);
//                }
//                Cursor_tax.close();
//            }
////            if (taxType.equals("1")) {
////                str_price5 += "$" + String.format("%.2f", Double.valueOf(TotalTaxSales)) + "\n";
////            }else {
//                str_price5 += "$" + String.format("%.2f", Double.valueOf(TotalTaxSales)) + "\n";
////            }
//        }
        //*Refund
        try {
            if (refund.equals("1")) {
                //str6 += Query.XZDataReportCancellationShow(BillCancel,BillCancelTotalQty,BillTax,TotalBillDisountTax);
                if (device.equals("M2-Max")) {
                    str_header5 = Constraints.LINEXM2MAX;
                    str_header5 += "\n" + "*Refund*";
                    str_header5 += "\n" + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
                } else {
                    str_header5 = Constraints.LINEX;
                    str_header5 += "\n" + "*Refund*";
                    str_header5 += "\n" + Constraints.DOTLINEX + Constraints.DOTLINEX;
                }
                str_name5 = Constraints.BillRefund;
                str_name5 += "\n" + Constraints.Quantity;
                str_name5 += "\n" + Constraints.AMTNett;
//            str_name5 += "\n" +ENUM.Amount;
                //str_name5 += "\n" +ENUM.TaxTotal;
//            str_name5 += "\n" +ENUM.AMTDiscount;
                str_name5 += "\n" + Constraints.AMTSurcharge;
//            str_name5 += "\n" +ENUM.AMTNettCancel;
//            str_name5 += "\n" +ENUM.AMTCancel;
                //str_name6 += "\n" +ENUM.TaxCancel;//10

                str_price5 = "$" + String.format("%.2f", Double.valueOf(BillRefund));
                str_price5 += "\n" + BillRefundTotalQty;
                if (BillRefund != null) {
                    str_price5 += "\n" + "$" + String.format("%.2f", Double.valueOf(BillRefund));
                } else {
                    str_price5 += "\n" + "$0.00";
                }
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
                //str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillTax));
                //str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTaxRefund));
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountAmountRefund));
                if (TotalBillServiceChargesRefund != null) {
                    str_price5 += "\n" + "$" + String.format("%.2f", Double.valueOf(TotalBillServiceChargesRefund));
                } else {
                    str_price5 += "\n" + "$0.00";
                }
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
                // str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillTax));
            }
        } catch (NullPointerException e){

        }
        //*Cancellation
        if (cancellation.equals("1")){
            //str6 += Query.XZDataReportCancellationShow(BillCancel,BillCancelTotalQty,BillTax,TotalBillDisountTax);
            if (device.equals("M2-Max")) {

                str_header6 = Constraints.LINEXM2MAX;
                str_header6 += "\n" + "*Cancellation*";
                str_header6 += "\n"  + Constraints.DOTLINEXM2MAX + Constraints.DOTLINEXM2MAX;
            } else {

                str_header6 = Constraints.LINEX;
                str_header6 += "\n" + "*Cancellation*";
                str_header6 += "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
            }

//            str_name6 = "Bill Cancelled";
            str_name6 = "\n" + Constraints.Quantity;
            str_name6 += "\n" + Constraints.AMTNett;
//            str_name5 += "\n" +ENUM.Amount;
            str_name6 += "\n" + Constraints.TaxTotal;
//            str_name6 += "\n" + Constraints.AMTDiscount;
            str_name6 += "\n" + Constraints.AMTSurcharge;
//            str_name5 += "\n" +ENUM.AMTNettCancel;
//            str_name5 += "\n" +ENUM.AMTCancel;
            //str_name6 += "\n" +ENUM.TaxCancel;//10

//            str_price6 = "$"+ String.format("%.2f", Double.valueOf(BillCancel));
            str_price6 = "\n" + BillCancelTotalQty;
            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
            //str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillTax));
            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTax));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountAmountCancel));
            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillServiceChargesCancel));
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
           // str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillTax));
        }
//
////        if (refer_info_sales.equals("1")){
////            str7 = Query.XZDataReportReforeInfoShow();
////        }
//        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
//        String dateString2 = dateFormat2.format(new Date()).toString();
//        str7 +=  "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
//        str7 += "\n" + dateString2;
//        str7 +=  "\n"  + Constraints.DOTLINEX + Constraints.DOTLINEX;
//        str7 += "\n" + "\n" + "\n" + "\n" + "\n" ;


//            }
//        }).start();
    }

//    private static Double GetInclusive(Double totalTaxSales) {
//        Double TotalTaxSales = totalTaxSales;
//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                String taxType = Cursor_tax.getString(1);
//                String taxName = Cursor_tax.getString(2);
//                if (taxType.equals("2")){
//                    Cursor ctax = Query.XZDataReportSalesTax(0,0,"XReport");
//                    if (ctax != null) {
//                        TotalTaxSales = 0.0;
//                        Double TotalNettSalestax = 0.0;
//                        while (ctax.moveToNext()) {
//                            TotalNettSalestax = ctax.getDouble(1);
//
//
//                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
//                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
//                            TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
//                            Log.i("TotalNettSalestax__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
//                        }
//                        ctax.close();
//                    }
//                    str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
//                    // double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
////                        TotalTaxSales = amt_inclusive;
//                } else {
//                    str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
//                }
//            }
//            Cursor_tax.close();
//        }
//        return TotalTaxSales;
//    }

    public static void getSalesALLTotalSales(String start, String end) {
//        if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
//            start = ReportDateSheetFragment.start_date;
//            end = ReportDateSheetFragment.end_date;
//        }

        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
            start = ReportActivity.start;
            end = ReportActivity.end;
        }
        //Total Sales//
        Cursor c = Query.XZDataReportSales(0,0,"XReport");
        ArrayList<Integer> BillIDArr = new ArrayList<Integer>();
        if (c != null) {
            TotalQty = 0;
            TotalNettSales = 0.0;
            GrossSales = 0.0;
            TotalBillDisount = 0.0;
            TotalTaxSales = 0.0;
            TotalRoundAdjSales = 0.0;
            ServiceChargesSales = 0.0;
            while (c.moveToNext()) {
                TotalQty += c.getInt(0);
                TotalNettSales += c.getDouble(1);
                //GrossSales += c.getDouble(8);
               // GrossSales += c.getDouble(2);
                GrossSales += TotalNettSales - c.getDouble(4);
                //TotalBillDisount += c.getDouble(3);
                TotalBillDisount += c.getDouble(3) + c.getDouble(8);
                TotalTaxSales += c.getDouble(4);
                TotalRoundAdjSales += c.getDouble(5);
                ServiceChargesSales += c.getDouble(6);
            }
            c.close();
        }
        //Inclusive
        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                String taxRate = Cursor_tax.getString(0);
                String taxType = Cursor_tax.getString(1);
                String taxName = Cursor_tax.getString(2);
                if (taxType.equals("2")){
                    Cursor ctax = Query.XZDataReportSalesTax(0,0,"XReport");
                    if (ctax != null) {
                        TotalTaxSales = 0.0;
                        Double TotalNettSalestax = 0.0;
                        while (ctax.moveToNext()) {
                            TotalNettSalestax = ctax.getDouble(1);


                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
                            TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
                            Log.i("TotalNettSalestax__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
                        }
                        ctax.close();
                    }
                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
                    // double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
//                        TotalTaxSales = amt_inclusive;
                } else {
                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
                }
            }
            Cursor_tax.close();
        }

        //Payment Type
        Cursor c_payment = Query.XZDataReportPayment(0,0,"XReport");
        if (c_payment != null) {
            PaymentTypeCount = 0;
            PaymentTypeAmount = "";
            PaymentTypeName = "";
            while (c_payment.moveToNext()) {
                if (c_payment.getDouble(1) > 0.0) {

                    if (c_payment.getString(5) != null && c_payment.getInt(5) != 0 && c_payment.getInt(5) == 1
                            && c_payment.getString(6) != null && c_payment.getString(6).length() > 1) {


//                    if (c_payment.getString(5) != null && c_payment.getInt(5) != 0 && c_payment.getInt(5) == 1
////                            && c_payment.getString(6) != null && c_payment.getString(6).length() > 1) {
//                        PaymentTypeName += c_payment.getString(6) + " ("+c_payment.getString(0)+")" + " \n";
//                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";

                        PaymentTypeName += c_payment.getString(6) + "\n";
                        PaymentTypeName += "("+c_payment.getString(0)+")" + " \n";
                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";
                        PaymentTypeAmount += " \n";

                    }else {
                        PaymentTypeName += c_payment.getString(0) + " \n";
                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";
                    }
                    if (c_payment.getString(8) != null  && c_payment.getString(8) != "null" && c_payment.getString(8).length() > 0) {
                        PaymentTypeName += "("+c_payment.getString(8)+")" + " \n";
                        PaymentTypeAmount += " \n";
                    }
                    //PaymentTypeName += c_payment.getString(3) +" \n";

                }
            }
            c_payment.close();
        }

//        Cursor c_paymentewallet = Query.XZDataReportPaymentEwallet(0,0,"XReport");
//        if (c_paymentewallet != null) {
//            PaymentTypeCount = 0;
//            PaymentTypeAmount = "";
//            PaymentTypeName = "";
//            while (c_paymentewallet.moveToNext()) {
//                if (c_paymentewallet.getDouble(1) > 0.0) {
//                    //PaymentTypeCount += c_payment.getInt(0);
//                    //PaymentTypeAmount += c_payment.getString(1) +" \n";
//                    //PaymentTypeAmount += "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1) / 2)) +" \n";
//                    PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_paymentewallet.getDouble(1))) + " \n";
//                    //PaymentTypeName += c_payment.getString(3) +" \n";
//                    PaymentTypeName += c_paymentewallet.getString(0)  + " " +
//                            "\n" + "JeriPay(EWallet)"+ " \n" ;
//
//                }
//            }
//            c_payment.close();
//        }
////        String sql = "SELECT card_label,card_number,invoice_number,BillNo FROM BillJeripayDetails Group By BillNo,card_label";
//        String sql = "SELECT card_label,card_number,invoice_number,BillNo FROM BillJeripayDetails Group By card_label";
//        Cursor jeripayDetails = DBFunc.Query(sql,false);
//        if (jeripayDetails != null) {
////            while (jeripayDetails.moveToNext()) {
//            if (jeripayDetails.moveToNext()) {
////                    String acquirer_id = jeripayDetails.getString(3);
//                String card_label = jeripayDetails.getString(0);
//                String billno = jeripayDetails.getString(3);
////                    "card_label":"OCBC~VISA"
//                String ewalletName = card_label.split("~")[1];
//                //jsonObject.put("Issuer_bank", card_label.split("~")[0]);
//                String sqlamt = "SELECT SUM(amount) FROM BillJeripay where BillNo = '"+billno+"'";
//                Cursor jeripay = DBFunc.Query(sqlamt,false);
//                if (jeripay != null) {
//                    if (jeripay.moveToNext()) {
//                        String amt = jeripay.getString(0);
//                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(amt)) + " \n";
//                    }
//                }
//                //PaymentTypeName += c_payment.getString(3) +" \n";
//                //PaymentTypeName += ewalletName + " \n";
//                PaymentTypeName += "Jeripay (EWallet)" + " \n";
//
//                PaymentTypeName += ENUM.LINEX;
//                PaymentTypeName += "\n" + "Jeripay (EWallet)";
//                PaymentTypeName += "\n"  + ENUM.DOTLINEX + ENUM.DOTLINEX;
//                PaymentTypeAmount += "\n";
//                PaymentTypeAmount += "\n";
//                PaymentTypeAmount += "\n";
//
//                String sqlq = "SELECT card_label,card_number,invoice_number,BillNo FROM BillJeripayDetails " +
//                        "Group By card_label";
//                Cursor jeripayDetails1 = DBFunc.Query(sqlq,false);
//                if (jeripayDetails1 != null) {
////            while (jeripayDetails.moveToNext()) {
//                    while (jeripayDetails1.moveToNext()) {
////                    String acquirer_id = jeripayDetails.getString(3);
//                        String card_label1 = jeripayDetails1.getString(0);
//
//                        PaymentTypeName += "\n" + card_label.split("~")[1];
//                        PaymentTypeAmount += "\n" + "Jeripay (EWallet)";
//                    }
//                }
//
//                //str_name3 =  ENUM.Count;
//                //str_name3 += "\n" +ENUM.Amount;//2
////                PaymentTypeName += PaymentTypeName;//2
//
//            }
//            jeripayDetails.close();
//        }
        Cursor c_discount = Query.XZDataReportDiscount(start,end,ClosingPeriodBillIDArr);
        if (c_discount != null) {
//            DicountCount = 0;
            DicountName = "";
            DicountAmount = "";
            while (c_discount.moveToNext()) {
                if (c_discount.getString(0) != null) {
                    if (c_discount.getString(0).length() > 0) {
                        DicountAmount += "$" + String.format("%.2f", Double.valueOf(c_discount.getString(0))) + " \n";
                        DicountName += "Total Item Dis" + " \n";
                    }
                }
            }
            c_discount.close();
        }
        Cursor c_discount_bill = Query.XZDataReportDiscountBill(start,end,ClosingPeriodBillIDArr);
        if (c_discount_bill != null) {
            while (c_discount_bill.moveToNext()) {
                if (c_discount_bill.getString(0) != null) {
                    if (c_discount_bill.getString(0).length() > 0) {
                        DicountAmount += "$" + String.format("%.2f", Double.valueOf(c_discount_bill.getString(0))) + " \n";
                        DicountName += "Total Bill Dis" + " \n";
                    }
                }
            }
            c_discount_bill.close();
        }
        Cursor cancel_discount = Query.XZDataReportCancelDiscount(start,end,ClosingPeriodBillIDArr);
        if (cancel_discount != null) {
//            DicountCount = 0;
            CancelDicountName = "";
            CancelDicountAmount = "";
            while (cancel_discount.moveToNext()) {
                if (cancel_discount.getString(0) != null) {
                    if (cancel_discount.getString(0).length() > 0) {
                        CancelDicountAmount += "$" + String.format("%.2f", Double.valueOf(cancel_discount.getString(0))) + " \n";
                        CancelDicountName += "Total Item Cancel Dis" + " \n";
                    }
                }
            }
            cancel_discount.close();
        }
        Cursor cancel_discount_bill = Query.XZDataReportCancelDiscountBill(start,end,ClosingPeriodBillIDArr);
        if (cancel_discount_bill != null) {
            while (cancel_discount_bill.moveToNext()) {
                if (cancel_discount_bill.getString(0) != null) {
                    if (cancel_discount_bill.getString(0).length() > 0) {
                        CancelDicountAmount += "$" + String.format("%.2f", Double.valueOf(cancel_discount_bill.getString(0))) + " \n";
                        CancelDicountName += "Total Bill Cancel Dis" + " \n";
                    }
                }
            }
            cancel_discount_bill.close();
        }
        Cursor c_tax = Query.XZDataReportTax(start,end,ClosingPeriodBillIDArr);
        if (c_tax != null) {
            TotalTaxesCount = 0;
            TotalTaxesAmount = 0.0;
            while (c_tax.moveToNext()) {
                //TotalTaxesCount += c_tax.getInt(0);
                TotalTaxesAmount += c_tax.getDouble(0);
            }
            c_tax.close();
        }
        //ProductSalesQuery
        Cursor c_product = Query.XZDataReportProduct(0,0,"XReport");
        if (c_product != null) {
            TotalQtyProduct = 0;
            TotalNettSalesProduct = 0.0;
            GrossSalesProduct = "0";
            TotalBillDisountProduct = "0";
            BillTaxProduct = "0";
            TotalBillDisountTaxProduct = 0.0;
            TotalProductServiceCharges = 0.0;
            while (c_product.moveToNext()) {
                    TotalQtyProduct += c_product.getInt(0);
                    TotalNettSalesProduct += c_product.getDouble(1) + c_product.getDouble(4);
                    GrossSalesProduct += c_product.getString(2);
                    //TaxTotalProduct = c.getString(14);
                    TotalBillDisountProduct += c_product.getString(3);
                    BillTaxProduct += c_product.getString(4);
                    TotalBillDisountTaxProduct += c_product.getDouble(5);
                    TotalProductServiceCharges += c_product.getDouble(6);
                }
            c_product.close();
        }
        //Category
        Integer id = Query.CategoryCheck();
        if (id > 0) {
            Cursor c_category = Query.XZDataReportCategory(start, end, ClosingPeriodBillIDArr);

            if (c_category != null) {
                TotalQtyCategory = 0;
                TotalNettSalesCategory = 0.0;
                GrossSalesCategory = 0.0;
                TotalBillDisountCategory = 0.0;
                BillCancelTotalQtyCategory = 0;
                TotalBillDisountTaxCategory = 0.0;
                while (c_category.moveToNext()) {
                    String bnoo = c_category.getString(3);
                    String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                            "WHERE ReferenceBillNo = '" + bnoo + "'";
                    Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo, false);
                    if (cSearchReferenceBillNo != null) {
                        if (cSearchReferenceBillNo.getCount() == 0) {

                            TotalQtyCategory += c_category.getInt(0);
                            //TotalNettSalesCategory += c_category.getDouble(0) * c_category.getDouble(1);
                            TotalNettSalesCategory += c_category.getDouble(1);


                            //GrossSalesCategory += c_category.getDouble(2);
                            //TaxTotalCategory = c.getString(14);
                            //TotalBillDisountCategory += c_category.getDouble(3);
//                    BillCancelTotalQtyCategory += c_category.getInt(0);
                            //BillTaxCategory = c.getString(14);
                            //TotalBillDisountTaxCategory += c_category.getDouble(3);
                        }
                        cSearchReferenceBillNo.close();
                    }
                }
                c_category.close();
            }

            Cursor c_cancel = Query.XZDataReportCancel(start, end, ClosingPeriodBillIDArr);
            if (c_cancel != null) {
                TotalQtyCategoryCancel = 0;
                TotalNettSalesCategoryCancel = 0.0;
                while (c_cancel.moveToNext()) {
                    //TotalQtyCategoryCancel += c_cancel.getInt(2);
                    //TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
                    TotalQtyCategoryCancel += c_cancel.getInt(0);
                    //TotalNettSalesCategoryCancel += c_cancel.getDouble(0) * c_cancel.getDouble(1);
                    TotalNettSalesCategoryCancel += c_cancel.getDouble(1);
                }
                c_cancel.close();
            }
            Cursor c_cancel_item_discount = Query.XZDataReportCancelItemDiscount(start, end, ClosingPeriodBillIDArr);
            if (c_cancel_item_discount != null) {
                CategoryCancelItemDiscount_Qty = 0;
                CategoryCancelItemDiscount_Amount = 0.0;
                while (c_cancel_item_discount.moveToNext()) {
                    //TotalQtyCategoryCancel += c_cancel.getInt(2);
                    //TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
                    if (c_cancel_item_discount.getString(2).equals("SALES")) {
                        String bnoo = c_cancel_item_discount.getString(3);
                        String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                                "WHERE ReferenceBillNo = '" + bnoo + "'";
                        Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo, false);
                        if (cSearchReferenceBillNo != null) {
                            if (cSearchReferenceBillNo.getCount() == 0) {
                                CategoryCancelItemDiscount_Qty += c_cancel_item_discount.getInt(0);
                                //TotalNettSalesCategoryCancel += c_cancel.getDouble(0) * c_cancel.getDouble(1);
                                CategoryCancelItemDiscount_Amount += c_cancel_item_discount.getDouble(1);
                            }
                            cSearchReferenceBillNo.close();
                        }
                    }
                }
                c_cancel_item_discount.close();
            }
            Cursor c_cancel_bill_discount = Query.XZDataReportCancelTotalBillDiscount(start, end, ClosingPeriodBillIDArr);
            if (c_cancel_bill_discount != null) {
                CategoryCancelTotalBillDiscount_Qty = 0;
                CategoryCancelTotalBillDiscount_Amount = 0.0;
                while (c_cancel_bill_discount.moveToNext()) {
                    String bnoo = c_cancel_bill_discount.getString(2);
                    String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                            "WHERE ReferenceBillNo = '" + bnoo + "'";
                    Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo, false);
                    if (cSearchReferenceBillNo != null) {
                        if (cSearchReferenceBillNo.getCount() == 0) {
                            CategoryCancelTotalBillDiscount_Qty += c_cancel_bill_discount.getInt(0);
                            //TotalNettSalesCategoryCancel += c_cancel.getDouble(0) * c_cancel.getDouble(1);
                            CategoryCancelTotalBillDiscount_Amount += c_cancel_bill_discount.getDouble(1);
                        }
                        cSearchReferenceBillNo.close();
                    }
                }
                c_cancel_bill_discount.close();
            }
        }
        //Refund
        Cursor c_productrefund = Query.XZDataReportProductRefund(0,0,"XReport");

        if (c_productrefund != null) {
            TotalNettSalesProductRefund = 0.0;
            BillRefundTotalQty = 0;
            BillRefund = 0.0;
            TotalBillDisountTaxRefund = 0.0;
            TotalBillDisountAmountRefund = 0.0;
            TotalBillServiceChargesRefund = 0.0;
            while (c_productrefund.moveToNext()) {
                TotalNettSalesProductRefund += (-1) * c_productrefund.getDouble(13);
                BillRefundTotalQty += (-1) * c_productrefund.getInt(2);
                BillRefund += (-1) * c_productrefund.getDouble(13);
                //BillTax = c.getString(14);
                TotalBillDisountTaxRefund += (-1) * c_productrefund.getDouble(14);
                TotalBillDisountAmountRefund += (-1) * c_productrefund.getDouble(13);
                TotalBillServiceChargesRefund += (-1) * c_productrefund.getDouble(12);
            }
            c_productrefund.close();
        }
        //Cancellation
        Cursor c_productcancel = Query.XZDataReportProductCancel(0,0,"XReport");

        if (c_productcancel != null) {
            TotalNettSalesProductCancel = 0.0;
            BillCancelTotalQty = 0;
            BillCancel = 0.0;
            TotalBillDisountTax = 0.0;
            TotalBillDisountAmountCancel = 0.0;
            TotalBillServiceChargesCancel = 0.0;
            while (c_productcancel.moveToNext()) {
//                String sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
//                        "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000 + (3600*8), 'unixepoch'),
//                        SUM(GrossSales),SUM(TotalItemDisount)," +
//                        "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
//                        "SalesDateTime FROM Sales " +
                //TotalNettSalesProductCancel = c_productcancel.getDouble(13);
                TotalNettSalesProductCancel += c_productcancel.getDouble(13);
                BillCancelTotalQty += c_productcancel.getInt(2);
                BillCancel += c_productcancel.getDouble(13);
                //BillTax = c.getString(14);
                TotalBillDisountTax += c_productcancel.getDouble(14);
                TotalBillDisountAmountCancel += c_productcancel.getDouble(13);
                TotalBillServiceChargesCancel += c_productcancel.getDouble(12);
            }
            c_productcancel.close();
        }

        Cursor Cursor_taxx = Query.GetTax();
        if (Cursor_taxx != null){
            if (Cursor_taxx.moveToNext()){
                String taxRate = Cursor_taxx.getString(0);
                String taxType = Cursor_taxx.getString(1);
                String taxName = Cursor_taxx.getString(2);
                if (taxType.equals("2")){
                    Cursor ctax = Query.XZDataReportProductCancel(0,0,"XReport");
                    if (ctax != null) {
                        TotalBillDisountTax = 0.0;
                        Double TotalNettSalestax = 0.0;
                        while (ctax.moveToNext()) {
                            TotalNettSalestax = ctax.getDouble(13);


                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
                            TotalBillDisountTax += Double.valueOf(String.format("%.2f", amt_inclusive));
                            Log.i("TotalNettSalestax__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
                        }
                        ctax.close();
                    }
                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
                    // double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
//                        TotalTaxSales = amt_inclusive;
                } else {
                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
                }
            }
            Cursor_taxx.close();
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
