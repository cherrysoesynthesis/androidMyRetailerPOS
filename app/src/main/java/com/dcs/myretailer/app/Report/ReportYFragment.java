//package com.dcs.myretailer.app.Report;
//
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import com.dcs.myretailer.app.Database.DBFunc;
//import com.dcs.myretailer.app.ENUM.ENUM;
//import com.dcs.myretailer.app.Query.Query;
//import com.dcs.myretailer.app.R;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class ReportYFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    public static ArrayList<String> ProductQty = new ArrayList<String>();
//    public static ArrayList<String> ProductName = new ArrayList<String>();
//    public static ArrayList<String> ProductPrice = new ArrayList<String>();
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//    private ReportXFragment.OnFragmentInteractionListener mListener;
//    public static ArrayList<String> reportProductHeaderArr = new ArrayList<String>();
//    public static ArrayList<String> reportProductNameArr = new ArrayList<String>();
//    public static ArrayList<String> reportProductQtyArr = new ArrayList<String>();
//    public static ArrayList<String> reportProductPriceArr = new ArrayList<String>();
//    String from_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//    String to_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//    ListView reportProductListView;
//    Handler mHandler;
//    public static String St = "0";
//    String str_header = "";
//    String str_name = "";
//    String str_price = "";
//    String str_header1 = "";
//    String str_name1 = "";
//    String str_price1 = "";
//    String str_header2 = "";
//    String str_name2 = "";
//    String str_price2 = "";
//    String str_header3 = "";
//    String str_name3 = "";
//    String str_price3 = "";
//    String str_header4 = "";
//    String str_name4 = "";
//    String str_price4 = "";
//    String str_header5 = "";
//    String str_name5 = "";
//    String str_price5 = "";
//    String str_header6 = "";
//    String str_name6 = "";
//    String str_price6 = "";
//    String str_header7 = "";
//    String str_name7 = "";
//    String str_price7 = "";
//    String str = "";
//    String str2 = "";
//    String str3 = "";
//    String str4 = "";
//    String str5 = "";
//    String str6 = "";
//    String str7 = "";
//    Double TotalNettSales = 0.0;
//    Integer BillCancelTotalQty = 0;
//    Integer TotalQty = 0;
//    Double BillCancel = 0.0;
//    String BillTax = "0";
//    Double TotalBillDisountTax = 0.0;
//    Double GrossSales = 0.0;
//    Double TaxTotal = 0.0;
//    Double TotalBillDisount = 0.0;
//    Integer TotalQtyProduct = 0;
//    Double TotalNettSalesProduct = 0.0;
//    String GrossSalesProduct = "0";
//    String TaxTotalProduct = "0";
//    String TotalBillDisountProduct = "0";
//    String BillCancelTotalQtyProduct = "";
//    String BillCancelProduct = "";
//    String BillTaxProduct = "";
//    Double TotalBillDisountTaxProduct = 0.0;
//    Double TotalNettSalesProductCancel = 0.0;
//    Integer PaymentTypeCount = 0;
//    Double PaymentTypeAmount = 0.0;
//    Integer DicountCount = 0;
//    Double DicountAmount = 0.0;
//    String TotalTaxesCount = "0";
//    String TotalTaxesAmount = "0";
//    Integer TotalQtyCategory = 0;
//    Double TotalNettSalesCategory = 0.0;
//    Double GrossSalesCategory = 0.0;
//    ArrayList<Integer> Arr = new ArrayList<Integer>();
//    String TaxTotalCategory = "0";
//    Double TotalBillDisountCategory = 0.0;
//    Integer BillCancelTotalQtyCategory = 0;
//    String BillTaxCategory = "";
//    Double TotalBillDisountTaxCategory = 0.0;
//    Integer TotalQtyCategoryCancel = 0;
//    Double TotalNettSalesCategoryCancel = 0.0;
//    public ReportYFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment
//     */
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
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate( R.layout.fragment_reportx_product, container, false);
//        reportProductListView = (ListView) view.findViewById(R.id.ReportProductListView);
//        //ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
//        getPrintSales(ReportActivity.start,ReportActivity.end);
//
//        reportProductHeaderArr.add(str_header);
//        reportProductHeaderArr.add(str_header1);
//        reportProductHeaderArr.add(str_header2);
//        reportProductHeaderArr.add(str_header3);
//        reportProductHeaderArr.add(str_header4);
//        reportProductHeaderArr.add(str_header5);
//        reportProductHeaderArr.add(str_header6);
//
//        reportProductNameArr.add(str_name);
//        reportProductNameArr.add(str_name1);
//        reportProductNameArr.add(str_name2);
//        reportProductNameArr.add(str_name3);
//        reportProductNameArr.add(str_name4);
//        reportProductNameArr.add(str_name5);
//        reportProductNameArr.add(str_name6);
//
//        reportProductPriceArr.add(str_price);
//        reportProductPriceArr.add(str_price1);
//        reportProductPriceArr.add(str_price2);
//        reportProductPriceArr.add(str_price3);
//        reportProductPriceArr.add(str_price4);
//        reportProductPriceArr.add(str_price5);
//        reportProductPriceArr.add(str_price6);
//
//        ReportXAdapter customAdapter = new ReportXAdapter(getActivity(), reportProductNameArr, reportProductPriceArr , reportProductHeaderArr);
//        reportProductListView.setAdapter(customAdapter);
//
//        mHandler = new Handler();
//        m_Runnable.run();
//        return view;
//    }
//
//    private void getPrintSales(String start, String end) {
//        String sales = "0";
//        String category = "0";
//        String product_sales = "0";
//        String payment = "0";
//        String disocunt = "0";
//        String tax = "0";
//        String cancellation = "0";
//        String refer_info_sales = "0";
//
//        getSalesALLTotalSales(start,end);
//        //getSalesALLTotalOnlineOrderSales(start,end);
//
//        String sql = "SELECT Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales FROM ReportSettings";
//        //Log.i("_sql__",sql);
//        Cursor c = DBFunc.Query(sql, true);
//        if (c != null) {
//            if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    Log.i("DSF_Integer_", String.valueOf(c.getInt(0)));
//                    sales = c.getString(0);
//                    category = c.getString(1);
//                    product_sales = c.getString(2);
//                    payment = c.getString(3);
//                    disocunt = c.getString(4);
//                    tax = c.getString(5);
//                    cancellation = c.getString(6);
//                    //refer_info_sales = c.getString(7);
//                }
//            }
//            c.close();
//        }
//        if (sales.equals("1")){
//            // str = Query.XZDataReportSalesShow(TotalNettSales,BillCancelTotalQty,TotalQty,TotalNettSales,GrossSales,TaxTotal,TotalBillDisount,TotalNettSales,BillCancel,BillCancelTotalQty);
//
//            str_header = ENUM.LINE;
//            str_header += "\n" + "*TOTAL SALES*";
//            str_header += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name =  ENUM.BillPaid;
//            str_name += "\n" +"Bill Cancel";
//            str_name += "\n" +"Qty Sold";
//            str_name += "\n" +ENUM.AMTNett;
//            str_name += "\n" +ENUM.AMTGross;
//            str_name += "\n" +ENUM.TaxTotal;
//            str_name += "\n" +ENUM.AMTDiscount;
//            str_name += "\n" +ENUM.AMTSurcharge;
//            str_name += "\n" +ENUM.RoundAdj;
//            str_name +="\n" +ENUM.AMTCollected;
//            str_name += "\n" +ENUM.QtyCancel;
//            str_name += "\n" +ENUM.AMTCancel;//12
//
//            str_price = "$"+ String.format("%.2f", Double.valueOf(TotalNettSales));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancelTotalQty));
//            str_price += "\n" +TotalQty;
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSales));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(GrossSales));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TaxTotal));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisount));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf("0.00"));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf("0.00"));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSales));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSales));
//            Log.i("dfd_str_price",str_price);
//        }
//        if (category.equals("1")){
//            // str2 = Query.XZDataReportCategoryShow(TotalQtyCategory,TotalNettSalesCategory,TaxTotalCategory,TotalBillDisountTaxCategory,TotalQtyCategoryCancel,TotalNettSalesCategory);
//            str_header1 = ENUM.LINE;
//            str_header1 += "\n" + "*Category*";
//            str_header1 += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name1 =  ENUM.QtySold;
//            str_name1 += "\n" +ENUM.AMTNett;
//            str_name1 += "\n" +ENUM.Amount;
//            str_name1 += "\n" +ENUM.TaxTotal;
//            str_name1 += "\n" +ENUM.AMTDiscount;
//            str_name1 += "\n" +ENUM.AMTSurcharge;
//            str_name1 += "\n" +ENUM.QtyCancel;
//            str_name1 += "\n" +ENUM.AMTCancel;//7
//
//            str_price1 = String.valueOf(TotalQtyCategory);
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesCategory));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesCategory));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TaxTotalCategory));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTaxCategory));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf("0.00"));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalQtyCategoryCancel));
//            str_price1 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesCategory));
//        }
//        if (product_sales.equals("1")){
//            //str3 = Query.XZDataReportProductShow(TotalQtyProduct,TotalNettSalesProduct,TaxTotalProduct,TotalBillDisountTaxProduct,TotalNettSalesProductCancel,TotalNettSalesProduct);
//            str_header2 = ENUM.LINE;
//            str_header2 += "\n" + "*Product SALES*";
//            str_header2 += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name2 = ENUM.QtySold;
//            str_name2 += "\n" +ENUM.AMTNett;
//            str_name2 += "\n" +ENUM.Amount;
//            str_name2 += "\n" +ENUM.TaxTotal;
//            str_name2 += "\n" +ENUM.AMTDiscount;
//            str_name2 += "\n" +ENUM.AMTSurcharge;
//            str_name2 += "\n" +ENUM.AMTCancel;//7
//
//            str_price2 = String.valueOf(TotalQtyProduct);
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesProduct));
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesProduct));
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TaxTotalProduct));
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTaxProduct));
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf("0.00"));
//            str_price2 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalNettSalesProductCancel));
//        }
//        if (payment.equals("1")){
//            //str4 = Query.XZDataReportPaymentShow(PaymentTypeCount,PaymentTypeAmount);
//            str_header3 = ENUM.LINE;
//            str_header3 += "\n" + "*Payment Count*";
//            str_header3 += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name3 =  ENUM.Count;
//            str_name3 += "\n" +ENUM.Amount;//2
//
//            str_price3 =  String.valueOf(PaymentTypeCount);
//            str_price3 += "\n" +"$"+ String.format("%.2f", Double.valueOf(PaymentTypeAmount));
//        }
//        if (disocunt.equals("1")){
//            //str5 = Query.XZDataReportDiscountShow(DicountCount,DicountAmount);
//            str_header4 = ENUM.LINE;
//            str_header4 += "\n" + "*Discount Surcharge*";
//            str_header4 += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name4 =  ENUM.Count;
//            str_name4 += "\n" +ENUM.Amount;//2
//
//            str_price4 += String.valueOf(DicountCount);
//            str_price4 += "\n" +"$"+ String.format("%.2f", Double.valueOf(DicountAmount));
//        }
//        if (tax.equals("1")){
//            //str6 = Query.XZDataReportTaxShow(TotalTaxesCount,TotalTaxesAmount);
//            str_header5 = ENUM.LINE;
//            str_header5 += "\n" + "*Tax Sales*";
//            str_header5 += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name5 =  ENUM.Count;
//            str_name5 += "\n" +ENUM.Amount;//2
//
//            str_price5 = String.valueOf(TotalTaxesCount);
//            str_price5 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxesAmount));
//        }
//        if (cancellation.equals("1")){
//            //str6 += Query.XZDataReportCancellationShow(BillCancel,BillCancelTotalQty,BillTax,TotalBillDisountTax);
//
//            str_header6 = ENUM.LINE;
//            str_header6 += "\n" + "*Cancellation*";
//            str_header6 += "\n"  + ENUM.DOTLINE+ ENUM.DOTLINE;
//
//            str_name6 = ENUM.BillCancelled;
//            str_name6 = "\n" +ENUM.Quantity;
//            str_name6 += "\n" +ENUM.AMTNett;
//            str_name6 += "\n" +ENUM.Amount;
//            str_name6 += "\n" +ENUM.TaxTotal;
//            str_name6 += "\n" +ENUM.AMTDiscount;
//            str_name6 += "\n" +ENUM.AMTSurcharge;
//            str_name6 += "\n" +ENUM.AMTNettCancel;
//            str_name6 += "\n" +ENUM.AMTCancel;
//            str_name6 += "\n" +ENUM.TaxCancel;//10
//
//            str_price6 = "$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price6 += "\n" + BillCancelTotalQty;
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillTax));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalBillDisountTax));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf("0.00"));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillCancel));
//            str_price6 += "\n" +"$"+ String.format("%.2f", Double.valueOf(BillTax));
//        }
//
//        if (refer_info_sales.equals("1")){
//            str7 = Query.XZDataReportReforeInfoShow();
//        }
//        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
//        String dateString2 = dateFormat2.format(new Date()).toString();
//        str7 += "\n" + "====================================";
//        str7 += "\n" + dateString2;
//        str7 += "\n" + "====================================";
//        str7 += "\n" + "\n" + "\n" + "\n" + "\n" ;
//
//
////            }
////        }).start();
//    }
//
//    private void getSalesALLTotalSales(String start, String end) {
////        if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
////            start = ReportDateSheetFragment.start_date;
////            end = ReportDateSheetFragment.end_date;
////        }
//        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
//            start = ReportActivity.start;
//            end = ReportActivity.end;
//        }
//        Cursor c = Query.XZDataReportSales(start,end,Arr);
//        if (c != null) {
//            TotalQty = 0;
//            TotalNettSales = 0.0;
//            GrossSales = 0.0;
//            TotalBillDisount = 0.0;
//            while (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    TotalQty += c.getInt(0);
//                    TotalNettSales += c.getDouble(1);
//                    GrossSales += c.getDouble(2);
//                    TotalBillDisount += c.getDouble(3);
//                }else {
//                    TotalQty = 0;
//                    TotalNettSales = 0.0;
//                    GrossSales = 0.0;
//                    TotalBillDisount = 0.0;
//                }
//            }
//            c.close();
//        }
//        Cursor c_payment = Query.XZDataReportPayment(start,end,Arr);
//        if (c_payment != null) {
//            while (c_payment.moveToNext()) {
//                if (!c_payment.isNull(0)) {
//                    PaymentTypeCount += c_payment.getInt(0);
//                    PaymentTypeAmount += c_payment.getDouble(1);
//                }
//            }
//            c_payment.close();
//        }
//        Cursor c_discount = Query.XZDataReportDiscount(start,end,Arr);
//        if (c_discount != null) {
//            while (c_discount.moveToNext()) {
//                if (!c_discount.isNull(0)) {
//                    DicountCount += c_discount.getInt(0);
//                    DicountAmount += c_discount.getDouble(1) + c_discount.getDouble(2);
//                }
//            }
//            c_discount.close();
//        }
//        Cursor c_tax = Query.XZDataReportTax(start,end,Arr);
//        if (c_tax != null) {
//            while (c_tax.moveToNext()) {
//                if (!c_tax.isNull(0)) {
//                    //TotalTaxesCount = c.getString(0);
//                    //TotalTaxesAmount = c.getString(1);
//                }
//            }
//            c_tax.close();
//        }
//
//        Cursor c_product = Query.XZDataReportProduct(start,end,Arr);
//
//        if (c_product != null) {
//            while (c_product.moveToNext()) {
//                if (!c_product.isNull(0)) {
//                    TotalQtyProduct += c_product.getInt(2);
//                    TotalNettSalesProduct = c_product.getDouble(13);
//                    GrossSalesProduct = c_product.getString(8);
//                    //TaxTotalProduct = c.getString(14);
//                    TotalBillDisountProduct = c_product.getString(9);
//                    BillTaxProduct = c_product.getString(14);
//                    TotalBillDisountTaxProduct = c_product.getDouble(9);
//                }
//            }
//            c_product.close();
//        }
//
//        Cursor c_category = Query.XZDataReportCategory(start,end,Arr);
//
//        if (c_category != null) {
//            while (c_category.moveToNext()) {
//                if (!c_category.isNull(0)) {
//                    TotalQtyCategory += c_category.getInt(2);
//                    TotalNettSalesCategory += c_category.getDouble(13);
//                    GrossSalesCategory += c_category.getDouble(8);
//                    //TaxTotalCategory = c.getString(14);
//                    TotalBillDisountCategory += c_category.getDouble(9);
//                    BillCancelTotalQtyCategory += c_category.getInt(2);
//                    //BillTaxCategory = c.getString(14);
//                    TotalBillDisountTaxCategory += c_category.getDouble(9);
//                }
//            }
//            c_category.close();
//        }
//
//        Cursor c_cancel = Query.XZDataReportCancel(start,end,Arr);
//        if (c_cancel != null) {
//            while (c_cancel.moveToNext()) {
//                if (!c_cancel.isNull(0)) {
////                    if (c.getString(2).equals(null)){
////                        TotalQtyCategoryCancel = "0";
////                    }else {
//                    TotalQtyCategoryCancel += c_cancel.getInt(2);
////                    }
//                    TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
//                }
//            }
//            c_cancel.close();
//        }
//
//        Cursor c_productcancel = Query.XZDataReportProductCancel(start,end,Arr);
//
//        if (c_productcancel != null) {
//            while (c.moveToNext()) {
//                if (!c_productcancel.isNull(0)) {
//                    TotalNettSalesProductCancel = c_productcancel.getDouble(13);
//                    BillCancelTotalQty += c_productcancel.getInt(2);
//                    BillCancel += c_productcancel.getDouble(13);
//                    //BillTax = c.getString(14);
//                    TotalBillDisountTax += c_productcancel.getDouble(9);
//                }
//            }
//            c_productcancel.close();
//        }
//    }
//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable"+TaxTypeSheetFragment.selected_tax_id ,Toast.LENGTH_SHORT).show();
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
//
//            if (getContext() != null){
//                if (St.equals("1")) {
//                    getPrintSales(ReportActivity.start,ReportActivity.end);
//
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//                    reportProductHeaderArr.add(str_header);
//
//                    reportProductNameArr.add(str);
//                    reportProductNameArr.add(str2);
//                    reportProductNameArr.add(str3);
//                    reportProductNameArr.add(str4);
//                    reportProductNameArr.add(str5);
//                    reportProductNameArr.add(str6);
//                    reportProductNameArr.add(str7);
//
//                    reportProductPriceArr.add(str);
//                    reportProductPriceArr.add(str2);
//                    reportProductPriceArr.add(str3);
//                    reportProductPriceArr.add(str4);
//                    reportProductPriceArr.add(str5);
//                    reportProductPriceArr.add(str6);
//                    reportProductPriceArr.add(str7);
//                    ReportXAdapter customAdapter = new ReportXAdapter(getActivity(), reportProductNameArr,reportProductPriceArr,reportProductHeaderArr);
//                    reportProductListView.setAdapter(customAdapter);
//                    St = "0";
//                }
//            }
//            mHandler.postDelayed(m_Runnable,300);
//            //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//
//    };
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
////    @Override
////    public void onAttach(Context context) {
////        super.onAttach(context);
////        if (context instanceof OnFragmentInteractionListener) {
////            mListener = (OnFragmentInteractionListener) context;
////        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
////        }
////    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}
//
