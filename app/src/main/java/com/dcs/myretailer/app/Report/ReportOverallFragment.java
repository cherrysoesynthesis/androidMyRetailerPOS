package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentReportOverallBinding;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportOverallFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportOverallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportOverallFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String BillNo,Totalamount;
    private static String DateTime = "0";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static BarData barData;
    public static BarDataSet barDataSet;
    public static ArrayList<BarEntry> barEntries;
    public static String St = "0";
    public static String GrossTotal,ServiceCharges;
    public static Integer Qty = 0;
    public static Double TotalItemDisount,TotalBillDisount,GrossSales,TotalNettSales,TotalTaxes = 0.0;
    public static ArrayList<String> reportOverallTransactionBillNoListView = new ArrayList<String>();
    public static ArrayList<String> reportOverallTransactionPriceListView = new ArrayList<String>();
    public static ArrayList<String> reportOverallTransactionDateTimeListView = new ArrayList<String>();
    public static ArrayList<String> reportOverallTransactionCashierNameListView = new ArrayList<String>();
    private OnFragmentInteractionListener mListener;
    public static List<Sales> lstSales = new ArrayList<Sales>();
    public static String from_date = new SimpleDateFormat(Constraints.dateYMD).format(new Date());
    public static String to_date = new SimpleDateFormat(Constraints.dateYMD).format(new Date());
    public static ArrayList<String> mylist = new ArrayList<String>();
//    Handler mHandler;
    //String reportOverallSaleSummaryName[] = {"Gross Sales", "Total Itemized Discount", "Total Bill Discount", "Gross Total", "Service Charge", "Taxes"};
    public static String reportOverallSaleSummaryName[] = {"Nett Amount", "Total Item Discount", "Total Bill Discount", "Total Tax", "Service Charges"};
    //    public static String start_date = "";
//    public static String end_date = "";
    public static String start_date = "";
    public static String end_date = "";
    public static FragmentReportOverallBinding binding = null;
    public static Context mcontext;
    public ReportOverallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportOverallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportOverallFragment newInstance(String param1, String param2) {
        ReportOverallFragment fragment = new ReportOverallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate( R.layout.fragment_report_overall, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_report_overall, container, false);
        View view = binding.getRoot();

        mcontext = getContext();

        updateMediaButtons();

        updateSalesSummaryFun();

        ReportOverallTransactionAdapter reportOverallTransactionAdapter = new ReportOverallTransactionAdapter(getActivity(), reportOverallTransactionBillNoListView, reportOverallTransactionPriceListView, reportOverallTransactionDateTimeListView, reportOverallTransactionCashierNameListView);
        binding.ReportOverallTransactionListView.setAdapter(reportOverallTransactionAdapter);

        return view;
    }

    public static void updateSalesSummaryFun() {
        ReportOverallSaleSummaryAdapter reportOverallSaleSummaryAdapter = new ReportOverallSaleSummaryAdapter(mcontext,
                reportOverallSaleSummaryName, mylist);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        300);
                binding.ReportOverallSaleSummaryListView.setLayoutParams(params);

                LinearLayout.LayoutParams LinearLayoutparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        80);
                LinearLayoutparams.topMargin = 5;
                binding.LayTotalSales.setLayoutParams(LinearLayoutparams);

                LinearLayout.LayoutParams LineartxttotalQty = new LinearLayout.LayoutParams(300,
                        80);
                LineartxttotalQty.leftMargin = 20;
                binding.txttotalQty.setLayoutParams(LineartxttotalQty);

                LinearLayout.LayoutParams LineartxttotalPriceAmount= new LinearLayout.LayoutParams(200,
                        80);
                binding.txttotalPriceAmount.setLayoutParams(LineartxttotalPriceAmount);
            } else {
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        300);
//                binding.ReportOverallSaleSummaryListView.setLayoutParams(params);
            }
        }
        binding.ReportOverallSaleSummaryListView.setAdapter(reportOverallSaleSummaryAdapter);
        LinearLayout.LayoutParams paramsreport_product_name = new LinearLayout.LayoutParams(430,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        binding.totalNetSales.setLayoutParams(paramsreport_product_name);


        if (TotalNettSales != null) {
            if (TotalNettSales > 0.0) {
                binding.totalNetSales.setText("$" + String.format("%.2f", TotalNettSales));
            }else {
                binding.totalNetSales.setText("$0.00");
            }
        }else {
            binding.totalNetSales.setText("$0.00");
        }
    }

    public static void ShowBarChart() {
        barEntries = new ArrayList<>();
        getEntries();
        updateBarChartFun();
    }

    public static void updateBarChartFun() {
        if (barEntries != null && barEntries.size() > 0) {

            YAxis rightYAxis = binding.BarChart.getAxisRight();
            rightYAxis.setEnabled(false);
            rightYAxis.setDrawGridLines(false);
            YAxis yAxis = binding.BarChart.getAxisLeft(); // Show left y-axis line
            yAxis.setDrawGridLines(false); // Hide y-axis (horizontal) grid lines

            barDataSet = new BarDataSet(barEntries, "");
            barDataSet.setColors(ColorTemplate.getHoloBlue());
            //barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            //barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(5f);
            barDataSet.setDrawValues(false);
            barData = new BarData(barDataSet);
            binding.BarChart.setData(barData);
            binding.BarChart.notifyDataSetChanged();
            binding.BarChart.invalidate();
        } else {
            binding.BarChart.clear();
//            binding.BarChart.notifyDataSetChanged();
//            binding.BarChart.invalidate();
        }
    }

    public class LabelValueFormatter implements IAxisValueFormatter {
        private final DataSet<com.github.mikephil.charting.data.Entry> mData;

        public LabelValueFormatter(DataSet<com.github.mikephil.charting.data.Entry> data) {
            mData = data;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // return the entry's data which represents the label
            //return (String) mData.getEntryForXPos(value, DataSet.Rounding.CLOSEST).getData();
            //return (String) mData.getEntryForXValue(value, DataSet.Rounding.CLOSEST).getData();
            return (String) mData.getEntryForXValue(value, value).getData();
        }
    }

    @Override
    public void onResume() {
        mcontext = getContext();
        updateMediaButtons();
        super.onResume();
    }

    public static void updateMediaButtons() {
        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
            start_date = ReportActivity.start;
            end_date = ReportActivity.end;
        }

        ShowBarChart();
        getSalesAll();
        updateSalesSummaryFun();


    }


    //    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable"+TaxTypeSheetFragment.selected_tax_id ,Toast.LENGTH_SHORT).show();
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
//
//            if (St.equals("1")) {
//
////                if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
////                    start_date = ReportDateSheetFragment.start_date;
////                    end_date = ReportDateSheetFragment.end_date;
////                }
//
//                if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
//                    start_date = ReportActivity.start;
//                    end_date = ReportActivity.end;
//                }
//
//                ShowBarChart();
//                getSalesAll();
////                if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
////                    start_date = ReportDateSheetFragment.start_date;
////                    end_date = ReportDateSheetFragment.end_date;
//////               // ReportOverallSaleSummaryAdapter reportOverallSaleSummaryAdapter = new ReportOverallSaleSummaryAdapter(getContext(), reportOverallSaleSummaryName, mylist);
//////                 ReportOverallSaleSummaryAdapter reportOverallSaleSummaryAdapter = new ReportOverallSaleSummaryAdapter(getActivity(), reportOverallSaleSummaryName, mylist);
//////                reportOverallSaleSummaryListView.setAdapter(reportOverallSaleSummaryAdapter);
////
//////                //ReportOverallTransactionAdapter reportOverallTransactionAdapter = new ReportOverallTransactionAdapter(getContext(), reportOverallTransactionBillNoListView, reportOverallTransactionPriceListView, reportOverallTransactionDateTimeListView, reportOverallTransactionCashierNameListView);
//////                 ReportOverallTransactionAdapter reportOverallTransactionAdapter = new ReportOverallTransactionAdapter(getActivity(), reportOverallTransactionBillNoListView, reportOverallTransactionPriceListView, reportOverallTransactionDateTimeListView, reportOverallTransactionCashierNameListView);
//////                reportOverallTransactionListView.setAdapter(reportOverallTransactionAdapter);
////                }
//            }
//
//            mHandler.postDelayed(m_Runnable,500);
//            //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//
//    };
    private static void getSalesAll() {
//        ring> reportOverallTransactionBillNoListView = new ArrayList<String>();
//        public static ArrayList<String> reportOverallTransactionPriceListView = new ArrayList<String>();
//        public static ArrayList<String> reportOverallTransactionDateTimeListView

//        if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
//            from_date = ReportDateSheetFragment.start_date;
//
//            to_date = ReportDateSheetFragment.end_date;
//        }
        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
            from_date = ReportActivity.start;

            to_date = ReportActivity.end;
        }
//        Log.i("DSfds_",from_date);
//        Log.i("DSfds_",to_date);
//        String sql = "";


        String sql = GetData();

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            GrossSales = 0.0;
            TotalItemDisount = 0.0;
            TotalBillDisount = 0.0;
            GrossTotal = "";
            ServiceCharges = "";
            TotalNettSales = 0.0;
            TotalTaxes = 0.0;
            Qty = 0;
            reportOverallTransactionBillNoListView.clear();
            reportOverallTransactionPriceListView.clear();
            reportOverallTransactionDateTimeListView.clear();
            reportOverallTransactionCashierNameListView.clear();
            //lstSales.add(new Sales(c.getInt(0),BillNo,GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,c.getString(14)));
            lstSales.clear();
            lstSales.clear();
            lstSales.clear();
            lstSales.clear();
            lstSales.clear();
            lstSales.clear();
            mylist.clear();
            mylist.clear();
            //mylist.clear();
            //mylist.clear();
            mylist.clear();
            mylist.clear();
            while (c.moveToNext()) {

                    BillNo = c.getString(1);
                    Qty += c.getInt(2);

                    Totalamount = c.getString(4);
                    //DateTime = c.getInt(7);
                    DateTime = c.getString(15);
                    reportOverallTransactionBillNoListView.add(BillNo);
                    reportOverallTransactionPriceListView.add(Totalamount);
                    reportOverallTransactionDateTimeListView.add(String.valueOf(DateTime));
                    reportOverallTransactionCashierNameListView.add(String.valueOf(BillNo));
                    //GrossSales += c.getString(8);
                    GrossSales += c.getDouble(3);
                    TotalItemDisount += c.getDouble(9);
                    TotalBillDisount += c.getDouble(10);
                    GrossTotal += c.getString(11);
                    ServiceCharges += c.getString(12);
                    TotalNettSales += c.getDouble(13);
                    //lstSales.add(new Sales(c.getInt(0),BillNo,GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,c.getString(14)));
                    //TotalTaxes = c.getDouble(14);
                    TotalTaxes += c.getDouble(14);
                    Cursor Cursor_tax = Query.GetTax();
                    if (Cursor_tax != null){
                        if (Cursor_tax.moveToNext()){
                            String taxRate = Cursor_tax.getString(0);
                            String taxType = Cursor_tax.getString(1);
                            String taxName = Cursor_tax.getString(2);
                            Log.i("Sfsf__","taxTydfdpe___"+taxType);
                            if (taxType.equals("2")){
//                                double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
//                                TotalTaxes = amt_inclusive;

                                //Cursor ctax = Query.XZDataReportSalesTax(0,0,"XReport");
                                String ctaxs = GetData();
                                String TotalTaxSales = "0";

                                Cursor ctax = DBFunc.Query(ctaxs,false);
                                if (ctax != null) {
                                    TotalTaxes = 0.0;
                                    Double TotalNettSalestax = 0.0;
                                    while (ctax.moveToNext()) {
                                        TotalNettSalestax = ctax.getDouble(13);
                                        double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
                                        //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
//                                        TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
                                        TotalTaxes += Double.valueOf(String.format("%.2f", amt_inclusive));

                                    }
                                    ctax.close();
                                }
                            }
                        }
                        Cursor_tax.close();
                    }
                    //ID,BillNo,TotalQty,SubTotal,Totalamount,PaymentType,PaymentAmount,DateTime

            }
            lstSales.add(new Sales(String.valueOf(GrossSales)));
            lstSales.add(new Sales(String.valueOf(TotalItemDisount)));
            lstSales.add(new Sales(String.valueOf(TotalBillDisount)));
            lstSales.add(new Sales(GrossTotal));
            lstSales.add(new Sales(ServiceCharges));
            lstSales.add(new Sales(String.valueOf(TotalTaxes)));

            mylist.add(String.valueOf(TotalNettSales));
            mylist.add(String.valueOf(TotalItemDisount));
            mylist.add(String.valueOf(TotalBillDisount));
            mylist.add(String.valueOf(TotalTaxes));
            mylist.add(String.valueOf(ServiceCharges));
            // mylist.add(GrossTotal);
            // mylist.add(ServiceCharges);
            //mylist.add(String.valueOf(TotalTaxes));
            //mylist.add(String.valueOf(TotalNettSales));
            c.close();
        }
        showTotalItemsAndAmt(TotalNettSales,Qty);
    }

    private static void showTotalItemsAndAmt(Double amt, Integer qty) {
       try {
            String _qty_task = "";
            if (qty > 0) {
                _qty_task = qty + " items";
            } else {
                _qty_task = qty + " item";
            }
            try {
                 binding.LayTotalSales.setVisibility(View.VISIBLE);
                binding.txttotalPriceAmount.setText("$" + String.format("%.2f", Double.valueOf(amt)));
                binding.txttotalQty.setText("Total Sales (" + _qty_task + " sold)");
            } catch (Exception e) {
                binding.LayTotalSales.setVisibility(View.VISIBLE);
                binding.txttotalPriceAmount.setText("$0.00");
                binding.txttotalQty.setText("Total Sales (" + 0 + " sold)");
            }
       }catch (Exception e){

       }
    }

    private static String GetData() {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = "";
            String query = " WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND') ";
            String cpSql = "SELECT ID,AllBillNo,ClosingTime FROM ZClosing " +
                    "WHERE  ClosingTime = '"+ReportActivity.previousReport+"' ";
//                    "WHERE strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+ReportActivity.previousReport+"' ";

            Cursor cpc = DBFunc.Query(cpSql, false);
            if (cpc != null) {
                if (cpc.moveToNext()) {
                    billnoall = cpc.getString(1);
                }
                cpc.close();
            }

//            sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,PaymentTypeID, " +
//                    " PaymentAmount,strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),GrossSales, " +
//                    " TotalItemDisount,TotalBillDisount, " +
//                    " GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,SalesDateTime" +
            sql = "SELECT ID,BillNo, (TotalQty), (SubTotal), (Totalamount),PaymentTypeID, (PaymentAmount), " +
                    " strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'), " +
                    "  (GrossSales), " +
                    "  (TotalItemDisount), (TotalBillDisount), (GrossTotal), (ServiceCharges), (TotalNettSales), " +
                    "  (TotalTaxes), (SalesDateTime)" +
                    " FROM Sales " +
                    query +
                    " AND BillID IN ("+billnoall+") " +
                    " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + from_date + "' AND '" + to_date + "' " +
                    " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch'),BillID  order by BillNo DESC ";


        }else {
//            sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,PaymentTypeID," +
//                "PaymentAmount,strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),GrossSales," +
//                "TotalItemDisount,TotalBillDisount," +
//                "GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,SalesDateTime " +
//                " FROM Sales " +
//                "where strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + from_date + "' AND '" + to_date + "' " +
//                " AND STATUS = 'SALES' " ;


            sql = "SELECT ID,BillNo, (TotalQty), (SubTotal), (Totalamount),PaymentTypeID, (PaymentAmount)," +
                    "strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'), " +
                    " (GrossSales), " +
                    " (TotalItemDisount), (TotalBillDisount), (GrossTotal), (ServiceCharges), (TotalNettSales)," +
                    " (TotalTaxes), (SalesDateTime) " +
                    " FROM Sales " +
                    "where strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + from_date + "' AND '" + to_date + "' " +
                    " AND (STATUS = 'SALES'   OR STATUS = 'REFUND') " ;


            SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
            final Calendar myCalendar2 = Calendar.getInstance();
            String todayd = sdf.format(myCalendar2.getTime());
//                if (todayd.equals(from_date)) {
            if (ReportActivity.previous_report_shift_name.equals("Now")){
                sql += "AND IsZ IS NULL " ;
            }
            sql +=  "group by BillNo order by BillNo DESC ";
        }
        Log.i("Sql__","sql__"+sql);
        return sql;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//
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

    private static void getEntries() {
//        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
//            from_date = ReportActivity.start;
//            to_date = ReportActivity.end;
//        }

        String sql = "";

        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = "";
            String query = " WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND') ";
            String cpSql = "SELECT ID,AllBillNo,ClosingTime FROM ZClosing " +
                    "WHERE  ClosingTime = '"+ReportActivity.previousReport+"' ";
//                    "WHERE strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+ReportActivity.previousReport+"' ";

            Cursor cpc = DBFunc.Query(cpSql, false);
            if (cpc != null) {
                if (cpc.moveToNext()) {
                    billnoall = cpc.getString(1);
                }
                cpc.close();
            }

            sql = "SELECT BillHour,ID,BillNo,SUM(TotalQty),SUM(SubTotal), " +
                    " SUM(Totalamount),PaymentTypeID,PaymentAmount,strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'), " +
                    " SUM(GrossSales),SUM(TotalItemDisount),SUM(TotalBillDisount), " +
                    " GrossTotal,ServiceCharges,SUM(TotalNettSales),TotalTaxes,SalesDateTime" +
                    " FROM Sales " +
                    query +
                    " AND BillID IN ("+billnoall+") " +
                    " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')  order by BillNo DESC ";

        }else {

            sql = "SELECT BillHour,ID,BillNo,SUM(TotalQty),SUM(SubTotal)," +
                    "SUM(Totalamount),PaymentTypeID,PaymentAmount,strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch')," +
                    "SUM(GrossSales),SUM(TotalItemDisount),SUM(TotalBillDisount)," +
                    "GrossTotal,ServiceCharges,SUM(TotalNettSales),TotalTaxes,SalesDateTime FROM Sales " +
                    "where strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '"+ start_date +"' AND '"+ end_date +"' " +
//                    "BETWEEN '"+ from_date +"' AND '"+ to_date +"' " +
//                "AND STATUS = 'SALES' AND isZ IS NULL" +
                    " AND (STATUS = 'SALES'   OR STATUS = 'REFUND') " ;

                    SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                    final Calendar myCalendar2 = Calendar.getInstance();
                    String todayd = sdf.format(myCalendar2.getTime());
//                    if (todayd.equals(from_date)) {
                    if (ReportActivity.previous_report_shift_name.equals("Now")){
                        sql += "AND IsZ IS NULL " ;
                    }
                    sql += " group by BillHour order by BillHour DESC ";
        }



        Cursor c = DBFunc.Query(sql, false);
//        Integer count = 0;
        if (c != null) {

            barEntries.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
//                    count ++;
//                    barEntries.add(new BarEntry(Float.parseFloat(c.getString(0)),
//                            Float.parseFloat(c.getString(14))));
                    barEntries.add(new BarEntry(c.getFloat(0),
                             c.getFloat(14)));
                }
            }
            c.close();
        }
    }
}
