package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
import com.dcs.myretailer.app.databinding.FragmentReportProductBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportProductFragment extends Fragment {
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
    public static ArrayList<String> reportProductNameArr = new ArrayList<String>();
    public static ArrayList<String> reportProductQtyArr = new ArrayList<String>();
    public static ArrayList<String> reportProductPriceArr = new ArrayList<String>();
//    Handler mHandler;
    public static String St = "0";
    static String from_date = "0";
    static String to_date = "0";
    public static Context context = null;
    public static FragmentReportProductBinding binding = null;
    public ReportProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportProductFragment newInstance(String param1, String param2) {
        ReportProductFragment fragment = new ReportProductFragment();
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
//        View view = inflater.inflate( R.layout.fragment_report_product, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_report_product, container, false);
        View view = binding.getRoot();

        context = getContext();

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
            FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsReporData.leftMargin = 20;
            binding.ReportProductListView.setLayoutParams(paramsReporData);
        }else if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
            if (device.equals("M2-Max")) {

            }else {
//                FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                paramsReporData.leftMargin = 20;
//                binding.ReportProductListView.setLayoutParams(paramsReporData);
            }
        }
        from_date = new SimpleDateFormat(Constraints.dateYMD).format(new Date());
        to_date = new SimpleDateFormat(Constraints.dateYMD).format(new Date());
//        from_date = Constraints.from_date;
//        to_date = Constraints.to_date;



//        new AsyncTaskGetSalesFun(context,binding).execute();
        //getSalesDetailsByProduct(context,binding);
        getSalesDetailsByProduct();

        ReportProductAdapter customAdapter = new ReportProductAdapter(context, reportProductNameArr, reportProductQtyArr,
                reportProductPriceArr);
        binding.ReportProductListView.setAdapter(customAdapter);

//        mHandler = new Handler();
//        m_Runnable.run();
        return view;
    }

    public static Double reportProductPriceArrVal = 0.0;
    public static Integer reportProductQtyArrVal = 0;
    public static void UpdateProductReport() {
//        new AsyncTaskGetSalesFun(context,binding).execute();
        //getSalesDetailsByProduct(context,binding);
        getSalesDetailsByProduct();



        ReportProductAdapter customAdapter = new ReportProductAdapter(context, reportProductNameArr, reportProductQtyArr,
                reportProductPriceArr);
        binding.ReportProductListView.setAdapter(customAdapter);
    }

    @Override
    public void onResume() {
        context = getContext();
        UpdateProductReport();
        super.onResume();
    }

//    public static class AsyncTaskGetSalesFun extends AsyncTask<Object, ImageView, Void> {
//        Context mcontext = null;
//        FragmentReportProductBinding mbinding = null;
//
//        public AsyncTaskGetSalesFun(Context context, FragmentReportProductBinding binding) {
//            mcontext = context;
//            mbinding = binding;
//        }
//
//        protected Void doInBackground(Object... params) {
//
//
//            getSalesDetailsByProduct(mcontext,mbinding);
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//
//            ReportProductAdapter customAdapter = new ReportProductAdapter(mcontext, reportProductNameArr, reportProductQtyArr,
//                    reportProductPriceArr);
//            mbinding.ReportProductListView.setAdapter(customAdapter);
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//
//        }
//    }

//    private final Runnable m_Runnable = new Runnable() {
//        public void run() {
//            if (getContext() != null){
//                if (St.equals("1")) {
//                    getSalesDetailsByProduct();
////                    ReportProductAdapter customAdapter = new ReportProductAdapter(getContext(), reportProductNameArr, reportProductQtyArr, reportProductPriceArr);
////                    reportProductListView.setAdapter(customAdapter);
//                    St = "0";
//                }
//            }
//            mHandler.postDelayed(m_Runnable,300);
//            //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//    };

    //public static void getSalesDetailsByProduct(Context mcontext, FragmentReportProductBinding mbinding) {
    public static void getSalesDetailsByProduct() {
        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
            from_date = ReportActivity.start;
            to_date = ReportActivity.end;
        }

        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = "";
//            String query = " WHERE (Sales.STATUS = 'SALES'   OR Sales.STATUS = 'REFUND') ";
            String query = " WHERE (Sales.STATUS = 'SALES' ) ";
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

            sql = "select DetailsBillProduct.ProductName,SUM(DetailsBillProduct.ProductQty)," +
                    "DetailsBillProduct.ProductPrice," +
                    "sales.STATUS,sales.BillNo,DetailsBillProduct.ProductID,DetailsBillProduct.Remarks," +
                    "DetailsBillProduct.ItemDiscountAmount from DetailsBillProduct "
                    + " left join sales on sales.BillNo = DetailsBillProduct.BillNo"
//            sql = "select DetailsBillProduct.ProductName,SUM(DetailsBillProduct.ProductQty)," +
//                    "(DetailsBillProduct.ProductPrice - DetailsBillProduct.ItemDiscountAmount)," +
//                    "sales.STATUS,sales.BillNo,DetailsBillProduct.ProductID,DetailsBillProduct.Remarks from DetailsBillProduct "
//                    + " left join sales on sales.BillNo = DetailsBillProduct.BillNo"
//                    + " where sales.STATUS = '" + ENUM.SALES + "'  "
                    + query
                    + " AND sales.BillID IN ("+billnoall+") "
//                    + "group by DetailsBillProduct.ProductID order by sales.BillNo ";
//                    +  " group by DetailsBillProduct.ProductID,sales.STATUS  " +
                    +  " group by DetailsBillProduct.ProductID,sales.STATUS,sales.BillNo,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks " +
//                    +  " group by DetailsBillProduct.ProductID,sales.STATUS,sales.BillNo " +
                    " order by sales.BillNo";

        }else {

            sql =  "select DetailsBillProduct.ProductName,SUM(DetailsBillProduct.ProductQty)," +
                    "DetailsBillProduct.ProductPrice ," +
                    "sales.STATUS,sales.BillNo,DetailsBillProduct.ProductID,DetailsBillProduct.Remarks," +
                    "DetailsBillProduct.ItemDiscountAmount from DetailsBillProduct "
                    + " left join sales on sales.BillNo = DetailsBillProduct.BillNo "
                    + " where ( sales.STATUS = 'SALES' ) ";

            //String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
            final Calendar myCalendar2 = Calendar.getInstance();
            String todayd = sdf.format(myCalendar2.getTime());

//                if (todayd.equals(from_date)) {
            if (ReportActivity.previous_report_shift_name.equals("Now")){
                sql += "AND IsZ IS NULL " ;
            }else {
                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch')" +
                        " from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }

//                    if (isnulldatestr.equals(from_date)) {
                if (ReportActivity.previous_report_shift_name.equals("Now")){
                    sql += "AND Sales.IsZ IS NULL " ;

                }
            }
            sql += " AND strftime('"+Constraints.sqldateformat+"', sales.DateTime / 1000 + (3600*8), 'unixepoch') "
                    + "BETWEEN '" + from_date + "' AND '" + to_date + "' "
//                    + "group by DetailsBillProduct.ProductID order by sales.BillNo " ;
//                    + " group by DetailsBillProduct.ProductID,sales.STATUS " +
                    + " group by DetailsBillProduct.ProductID,sales.STATUS,sales.BillNo,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks " +
//                    + " group by DetailsBillProduct.ProductID,sales.STATUS,sales.BillNo " +
                    " order by sales.BillNo";
        }
        Log.i("sql_sql___","sql__ffff____"+sql);
        Cursor c_s = DBFunc.Query(sql, false);
        if (c_s != null) {
            reportProductNameArr.clear();
            reportProductQtyArr.clear();
            reportProductPriceArr.clear();

            reportProductPriceArrVal = 0.0;
            reportProductQtyArrVal = 0;

            ArrayList<Integer> sameTotalQty = new ArrayList<Integer>();
            Integer one = 0;
            while (c_s.moveToNext()) {
                if (c_s.getString(3).toUpperCase().equals("SALES")) {

                    String bnoo = c_s.getString(4);
                    Integer bid = c_s.getInt(5);
                    String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                            "WHERE ReferenceBillNo = '"+bnoo+"'";
                    Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo,false);
                    if (cSearchReferenceBillNo != null){
                        if (cSearchReferenceBillNo.getCount() == 0){
//                        while (cSearchReferenceBillNo.getCount() == 0){

//                            if(sameTotalQty.contains(bid)){
//
//                            }else {
//                                sameTotalQty.add(bid);
                            String remarks = c_s.getString(6);
                            String remarks_ = "";
                            if (remarks != null && !(remarks.equals("")) && remarks.trim().length() > 0 && !(remarks.equals("0"))) {
                                String chkicno_on = Query.GetOptions(26);
                                if (chkicno_on.equals("1")) {
                                    //HPB
                                    if (remarks.length() == 9) {
                                        remarks_ = "("+ Constraints.PASSFirstDigits + remarks.substring(5, 9)+")";

                                    } else {
                                        remarks_ = "("+  remarks +")";
                                    }
                                } else {
                                    remarks_ = "(" + remarks + ")";
                                }

                            }
                                reportProductNameArr.add(c_s.getString(0)+remarks_);

                                reportProductQtyArr.add(c_s.getString(1)+" (BillNo - "+bnoo+")");

                                //String aa = String.valueOf(c_s.getDouble(2) - c_s.getDouble(7));
                                String aa = String.valueOf(c_s.getDouble(2) - (c_s.getDouble(1) * c_s.getDouble(7)));

                                reportProductPriceArr.add(aa);

                            reportProductPriceArrVal += Double.valueOf(aa);
                            reportProductQtyArrVal += c_s.getInt(1);
                                //reportProductPriceArr.add(c_s.getString(2));
//                            }

                        }
                        cSearchReferenceBillNo.close();
                    }

                }
            }
            c_s.close();
            //ReportActivity.updateQtyAndAmtFun();
            Log.i("DSFDSF____","result_____"+"herere1");
            ShowTotalItemsAndAmt(reportProductPriceArrVal,reportProductQtyArrVal);
        }else {
            Log.i("DSFDSF____","result_____"+"herere2");
            ShowTotalItemsAndAmt(0.0,0);
        }

    }

    private static void ShowTotalItemsAndAmt(Double amt, Integer qty) {
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
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
