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

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentReportCategoryBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ArrayList<String> reportCategoryNameArr = new ArrayList<String>();
    public static ArrayList<String> reportCategoryQtyArr = new ArrayList<String>();
    public static ArrayList<String> reportCategoryPriceArr = new ArrayList<String>();
    public static ArrayList<String> reportCategoryBillNoArrVal = new ArrayList<String>();
    static String from_date = new SimpleDateFormat(Constraints.dateYMD).format(new Date());
    static String to_date = new SimpleDateFormat(Constraints.dateYMD).format(new Date());
    public static String St = "0";
    private OnFragmentInteractionListener mListener;
    public static ReportCategoryAdapter customAdapter;
    public static FragmentReportCategoryBinding binding = null;
    public static Context context = null;
    public ReportCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportCategoryFragment newInstance(String param1, String param2) {
        ReportCategoryFragment fragment = new ReportCategoryFragment();
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
        //return inflater.inflate(R.layout.fragment_report_category, container, false);
//        View view = inflater.inflate( R.layout.fragment_report_category, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_report_category, container, false);
        View view = binding.getRoot();

        context = getContext();

        //ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
            FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsReporData.leftMargin = 20;
            binding.ReportCategoryListView.setLayoutParams(paramsReporData);
        }else if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
            if (device.equals("M2-Max")) {

            } else {
//                FrameLayout.LayoutParams paramsReporData = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                paramsReporData.leftMargin = 20;
//                binding.ReportCategoryListView.setLayoutParams(paramsReporData);
            }
        }
//        getSalesDetailsByCategory();
//        customAdapter = new ReportCategoryAdapter(getActivity(), reportCategoryNameArr, reportCategoryPriceArr, reportCategoryQtyArr);
//        binding.ReportCategoryListView.setAdapter(customAdapter);

        UpdateCategoryFun(context);

        return view;
    }

    @Override
    public void onResume() {
        context = getContext();
        UpdateCategoryFun(context);
        super.onResume();
    }

    public static void UpdateCategoryFun(Context context) {
        Integer id = Query.CategoryCheck();
        if (id > 0) {
            getSalesDetailsByCategory(context);
        }
    }

    public static Double reportCategoryPriceArrVal = 0.0;
    public static Integer reportCategoryQtyArrVal = 0;
    private static void getSalesDetailsByCategory(Context context) {
        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
            from_date = ReportActivity.start;

            to_date = ReportActivity.end;
        }
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = "";
            String query = " WHERE (Sales.STATUS = 'SALES'  ) ";
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



            sql = "select DetailsBillProduct.CategoryName," +
                    "SUM(DetailsBillProduct.ProductPrice)," +
                    "sales.STATUS," +
                    "DetailsBillProduct.CategoryID," +
                    "sales.BillNo,SUM(DetailsBillProduct.ProductQty),SUM(DetailsBillProduct.ItemDiscountAmount)," +
                    "SUM(DetailsBillProduct.ProductPrice - (DetailsBillProduct.ProductQty * DetailsBillProduct.ItemDiscountAmount))" +
                    "from DetailsBillProduct "
                    + " left join sales on sales.BillNo = DetailsBillProduct.BillNo"
//                    + " where sales.STATUS = '" + ENUM.SALES + "' "
                    + query
                    + " AND sales.BillID IN ("+billnoall+") "
                    //+ " AND strftime('"+Constraints.sqldateformat+"', sales.DateTime / 1000, 'unixepoch') and DetailsBillProduct.CategoryID > 0  "
                    + " AND strftime('"+Constraints.sqldateformat+"', sales.DateTime / 1000, 'unixepoch')   "
                    + "BETWEEN '" + from_date + "' AND '" + to_date + "' "
//                    + " group by DetailsBillProduct.CategoryID";
                    + " group by DetailsBillProduct.CategoryID,DetailsBillProduct.BillNo";
//                    + " group by DetailsBillProduct.CategoryID,sales.STATUS";


        }else {
            sql = "select DetailsBillProduct.CategoryName," +
                    "SUM(DetailsBillProduct.ProductPrice)," +
                    "sales.STATUS," +
                    "DetailsBillProduct.CategoryID," +
                    "sales.BillNo,SUM(DetailsBillProduct.ProductQty),SUM(DetailsBillProduct.ItemDiscountAmount)," +
                    "SUM(DetailsBillProduct.ProductPrice - (DetailsBillProduct.ProductQty * DetailsBillProduct.ItemDiscountAmount)) " +
                    "from DetailsBillProduct "
                    + " left join sales on sales.BillNo = DetailsBillProduct.BillNo"
                    + " where (sales.STATUS = 'SALES' ) ";

            SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
            final Calendar myCalendar2 = Calendar.getInstance();
            String todayd = sdf.format(myCalendar2.getTime());
//            if (todayd.equals(from_date)) {
            if (ReportActivity.previous_report_shift_name.equals("Now")){
                sql += "AND IsZ IS NULL " ;
            }else {
                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" +
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

//                if (isnulldatestr.equals(from_date)) {
                if (ReportActivity.previous_report_shift_name.equals("Now")){
                    sql += "AND Sales.IsZ IS NULL " ;
                    Log.i("sql",sql);
                }
            }
            sql += " AND strftime('"+Constraints.sqldateformat+"', sales.DateTime / 1000, 'unixepoch') "
                   // + "BETWEEN '" + from_date + "' AND '" + to_date + "'  and DetailsBillProduct.CategoryID > 0 "
                    + "BETWEEN '" + from_date + "' AND '" + to_date + "'   "
                    //+ " group by DetailsBillProduct.CategoryID";
                    + " group by DetailsBillProduct.CategoryID,DetailsBillProduct.BillNo";
//                    + " group by DetailsBillProduct.CategoryID,sales.STATUS";
//                    + " group by SalesItem.CategoryName";
        }
        Log.i("Cate_","sql_"+sql);
//        String sql = "select SalesItem.CategoryName,SUM(SalesItem.Price),sales.STATUS from SalesItem "
//                +" left join sales on sales.ID = SalesItem.SalesID"
//                + " where (sales.STATUS = 'SALES' OR sales.STATUS = 'REFUND') AND IsZ IS NULL "
////                + " AND strftime('"+Constraints.sqldateformat+"', SalesItem.DateTime / 1000, 'unixepoch') "
////                + "BETWEEN '"+ from_date +"' AND '"+ to_date +"' "
//                + " group by SalesItem.CategoryName";

        Cursor c_s = DBFunc.Query(sql, false);
        if (c_s != null) {
            reportCategoryNameArr.clear();
            //reportCategoryQtyArr.clear();
            reportCategoryPriceArr.clear();
            reportCategoryPriceArrVal = 0.0;
            reportCategoryQtyArrVal = 0;
            reportCategoryBillNoArrVal.clear();
            while (c_s.moveToNext()) {
                if (c_s.getString(2).toUpperCase().equals("SALES")) {
//                    if (!(c_s.getString(0).equals("0") || c_s.getString(0).equals("null"))) {
                    String bnoo = c_s.getString(4);
                    Integer bqty = c_s.getInt(5);
                    //Double totalPrice = Double.valueOf(bqty) * c_s.getDouble(1);

                    String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                            "WHERE ReferenceBillNo = '"+bnoo+"'";
                    Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo,false);
                    if (cSearchReferenceBillNo != null){
                        if (cSearchReferenceBillNo.getCount() == 0) {
//                            if (!(c_s.getString(3).equals("0") || c_s.getString(3).equals("null"))) {
//                            if ( (c_s.getString(3) != null && c_s.getInt(3) > 0)) {
                                reportCategoryNameArr.add(c_s.getString(0));
                                //reportCategoryQtyArr.add(c_s.getString(1));
                                //Double exact_price = c_s.getDouble(1) - (c_s.getDouble(5) * c_s.getDouble(6));
                                Double exact_price = c_s.getDouble(7);
                                //reportCategoryPriceArr.add(String.valueOf(c_s.getString(1)));
                                reportCategoryPriceArr.add(String.valueOf(exact_price));
                                reportCategoryPriceArrVal += exact_price;
                                reportCategoryQtyArrVal += c_s.getInt(5);
                                reportCategoryBillNoArrVal.add("(BillNo - "+bnoo+")");
//                                reportCategoryPriceArr.add(String.valueOf(c_s.getString(1)));
//                            }
                        }
                    }
                }
            }
            c_s.close();
        }

        ShowTotalItemsAndAmt(reportCategoryPriceArrVal,reportCategoryQtyArrVal);

        customAdapter = new ReportCategoryAdapter(context, reportCategoryNameArr, reportCategoryPriceArr, reportCategoryQtyArr,
                reportCategoryBillNoArrVal);
        try {

            binding.ReportCategoryListView.setAdapter(customAdapter);
        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("ReportCategoryFragment - binding.ReportCategoryListView" + e.getMessage()));
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
