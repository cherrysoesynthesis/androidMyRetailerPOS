package com.dcs.myretailer.app.Cashier;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Model.BillListModel;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.BilllistMainpageListBinding;
import com.pax.dal.IDAL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillListMainPageFragment extends Fragment{
//    private RecyclerView recyclerView;
    private static RecyclerAdapter adapter;
    //private List<BillDetails> lstBillDetails = new ArrayList<BillDetails>();
    private static List<BillListModel> lstBill = new ArrayList<BillListModel>();
    public static String str_newText = "";
    public static String balance_title = "AAAAAAAAAAAAAAAAAAAA";
//    Handler mHandler;
//static RequestQueue queue ;
//    Handler mHandler_online_order;
    public static String status_on = "0";
    String ItemName = "0";
    public static String name = "";
    String ItemQty = "0";
    String ItemPrice = "0";
    String IttemID = "0";
    String ParentItemID_ADDON = "0";
    String LastUpdate = "0";
    String TransNo = "0";
    String CreateTime = "0";
    String CashierID = "0";
    String vchQueueNo = "0";
    String intTableNo = "0";
    public static String status = "1";
    public static String St = "0";
//    static String retail_ID = "";
//    static String terminal_id = "";
//    static String company_code = "";
//    static String url = "";
    String SIGNATURE = "0";
    String obj_RECEIPTFLAG = "0";
    String obj_QueueNo = "0";
    String obj_TableNo = "0";
    String obj_SALESID = "0";
    String obj_SALESNO = "0";
    String obj_SALESDATE = "0";
    String obj_CREATETIME = "0";
    String obj_SALESAMOUNT = "0";
    String TransID = "0";
    String CompanyID = "0";
    String RetailerID = "0";
    String TotalDue = "0";
    String TotalGST = "0";
    String TotalDisc = "0";
    String TransDate = "0";
    String TotalQty = "0";
    String MemberID = "0";
    String isNewCust = "0";
    String SalesPersonID = "0";
    String CommID = "0";
    String CommPerc = "0";
    String MacAddress = "0";
    String ZReadNo = "0";
    String SalesID = "0";
    String RetailID = "0";
    String DOID = "0";
    String Bill_ID = "0";
    String SalesNo = "0";
    String SalesTax = "0";
    String SalesTaxVal = "0";
    String SalesDate = "0";
    String CloseRetailID = "0";
    String CloseDate = "0";
    String CloseTime = "0";
    String CloseSalesID = "0";
    String Sales_DetailID = "0";
    String ItemIDD = "0";
    String SupBarCode = "0";
    String ItemUOMDesc = "0";
    String ItemQtyAct = "0";
    String ItemUnitPrice = "0";
    String ItemUnitCost = "0";
    String ItemAveCost = "0";
    String ItemSSPx = "0";

    String ItemTaxType = "0";
    String SalesPaymentID = "0";
    String PaymentID = "0";
    String PaymentReference = "0";
    String ReceiptRemarks = "0";
    String OthersPayment = "0";
    String OthersPaymentRef = "0";
    String Close_SalesID = "0";
    String Close_TerminalID = "0";
    String CardDisc = "0";
    String SalesPayTtl = "0";
    String SalesBalTtl = "0";
    String SalesDeposit = "0";
    String ChangeAmount = "0";
    String TipsAmount = "0";
    String PaymentStatus = "0";
    String DepositStatus = "0";
    String Close_RetailID = "0";
    String CardAmt = "0";
    String ZReadDate = "0";
    private static IDAL dal;
    Cursor config_values = null;
    String check = "";
    String ItemTotal = "0";
    String ItemGST = "0";
    String ItemDiscType = "0";
    String ItemDisc1 = "0";
    String ItemDisc2 = "0";
    String ItemDisc3 = "0";
    String ItemBarcode = "0";
    String ItemUOM = "0";
    String ItemGSTInEx = "0";
    String ItemCost = "0";
    String ItemActQty = "0";
    String ItemUOMID = "0";
    String ItemGroupDisc = "0";
    String ItemSKU = "0";
    String SalesCommTypeID = "0";
    String SalesCommPerc = "0";
    String ItemCommPerc = "0";
    String ItemCommAmt = "0";
    String ItemSerialNo = "0";
    String DISCID = "0";
    String ItemIMEINo = "0";
    String ItemBatchNo = "0";
    String ItemStatus = "0";
    String OpenPriceRemark = "0";
    String ItemRemark = "0";
    String ExpireDate = "0";
    String ExpiryDay = "0";
    String RedeemPoint = "0";
    String bitAddOnItem = "0";
    String ParentDetailID_ADDON = "0";
    String MemDOBDiscPerc = "0";
    String MemDOBDiscAmount = "0";
    String ReceiptOrderStatus = "0";
    String TerminalID = "0";
    String RFID = "0";
    String PendingSync = "0";
    String SPBI01 = "0";
    String SPBI02 = "0";
    String SPBI03 = "0";
    String SPBI04 = "0";
    String SPBI05 = "0";
    String SPD01 = "0";
    String SPD02 = "0";
    String SPD03 = "0";
    String SPD04 = "0";
    String SPD05 = "0";
    String SPI01 = "0";
    String SPI02 = "0";
    String SPI03 = "0";
    String SPI04 = "0";
    String SPI05 = "0";
    String SPC01 = "0";
    String SPC02 = "0";
    String SPC03 = "0";
    String SPC04 = "0";
    String SPC05 = "0";
    String SPV01 = "0";
    String SPV02 = "0";
    String SPV03 = "0";
    String SPV04 = "0";
    String SPV05 = "0";
    String SPT01 = "0";
    String SPT02 = "0";
    String SPT03 = "0";
    String SPT04 = "0";
    String SPT05 = "0";
    String SDT01 = "0";
    String SDT02 = "0";
    String SDT03 = "0";
    String SDT04 = "0";
    String SDT05 = "0";
    String LastUser = "0";
    String LockUser = "0";
    String LockUpdate = "0";
    String LockStatus = "0";
    String RecordStatus = "0";
    String RecordUpdate = "0";
    String QueueStatus = "0";
    String UpdateStatusForOnlineOrderResponse = "";
    String SupplierID = "0";
    String GetDetailsForOrderResult = "";
    String GetSalesForOrderingResponse = "0";
    String RecordNo = "0";
    String LineNo = "0";
    public static Context mcontext;
    public static BilllistMainpageListBinding binding = null;
    static String terminalTypeVal = "";
//    BitmapDrawable drawable = null;
//    Bitmap bitmap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.billlist_mainpage_list, container, false);
        View view = binding.getRoot();

        mcontext = getContext();

        updateBillListFun();

        screenDisplay();


//        mHandler = new Handler();
//        m_Runnable.run();
//        mHandler_online_order = new Handler();
        return view;
    }

    private void screenDisplay() {
        String device = Query.GetDeviceData(Constraints.DEVICE);
        Log.i("sdf__device","device___"+device);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.ScrollView01.setLayoutParams(params);
////            binding.cardviewId.setLayoutParams(new LinearLayout.LayoutParams(850, ViewGroup.LayoutParams.WRAP_CONTENT));
////            FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(850,
////                    ViewGroup.LayoutParams.WRAP_CONTENT);
////            binding.recyclerView.setLayoutParams(linearOverAllParams);
            binding.Lay1.setLayoutParams(new FrameLayout.LayoutParams(800, ViewGroup.LayoutParams.WRAP_CONTENT));
            //binding.cardviewId.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(800,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.cardviewId.setLayoutParams(params1);
            FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(800,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.recyclerView.setLayoutParams(linearOverAllParams);
        }
    }

    public static void updateBillListFun() {

        Query.UpdateNaNItemBillDiscount();

//        new AsyncTaskUpdateBillListFun(mcontext,binding).execute(terminalTypeVal);

        lstBill = getBillAll();

        RecyclerViewAdapter.count = 0;

        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(560,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.cardviewId.setLayoutParams(linearOverAllParams);
//            binding.cardviewId.setLayoutParams(new LinearLayout.LayoutParams(750, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if (lstBill.size() > 0) {
            adapter = new RecyclerAdapter(mcontext, lstBill);
            binding.recyclerView.setAdapter(adapter);
            //getBillAll();
            //        recyclerView.setAdapter(adapter);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
            binding.recyclerView.addItemDecoration(new
                    DividerItemDecoration(mcontext,
                    DividerItemDecoration.VERTICAL));
        }
    }

    public static class AsyncTaskUpdateBillListFun extends AsyncTask<Object, ImageView, Void> {
        Context mcontext = null;
        BilllistMainpageListBinding mbinding = null;
        List<BillListModel> lstBill = new ArrayList<BillListModel>();
        public AsyncTaskUpdateBillListFun(Context appContext, BilllistMainpageListBinding binding) {
            mcontext = appContext;
            mbinding = binding;
        }

        protected Void doInBackground(Object... params) {

            lstBill = getBillAll();

            RecyclerViewAdapter.count = 0;

            //Query.CheckDateUpdate();


//            Cursor config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,terminal_id FROM POSSys", true);
//            if (config_values_pro_item != null) {
//                while (config_values_pro_item.moveToNext()) {
//                    retail_ID = config_values_pro_item.getString(0);
//                    company_code = config_values_pro_item.getString(1);
//                    url = config_values_pro_item.getString(2);
//                    terminal_id = config_values_pro_item.getString(3);
//                }
//                config_values_pro_item.close();
//            }
//            queue = Volley.newRequestQueue(mcontext);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(560,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                mbinding.cardviewId.setLayoutParams(linearOverAllParams);
//            binding.cardviewId.setLayoutParams(new LinearLayout.LayoutParams(750, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            if (lstBill.size() > 0) {
                adapter = new RecyclerAdapter(mcontext, lstBill);
                mbinding.recyclerView.setAdapter(adapter);
                //getBillAll();
                //        recyclerView.setAdapter(adapter);
                mbinding.recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
                mbinding.recyclerView.addItemDecoration(new
                        DividerItemDecoration(mcontext,
                        DividerItemDecoration.VERTICAL));
            }

        }

        @Override
        protected void onPreExecute() {
        }
    }

    public static List<BillListModel> getBillAll() {
        List<BillListModel> lslbill = new ArrayList<BillListModel>();
        if (str_newText.length() > 0){
            String queryStrBill = " AND BillNo LIKE '%"+str_newText+"%'";
            queryStrBill += " OR STATUS = '"+str_newText.toUpperCase()+"' ";
            lslbill = BillListSearchAllData(queryStrBill);
        }else{
            lslbill = BillListSearchAllData("");
        }
        return lslbill;
    }

    private static List<BillListModel> BillListSearchAllData(String queryStrBill) {
        List<BillListModel> lstBill = Query.getBillList("OFF",queryStrBill);
        return lstBill;
    }

    @Override
    public void onResume() {
        mcontext = getContext();
        updateBillListFun();
        super.onResume();
    }

//    public static void updateBillListFun() {
//        getBillAll();
//        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
//            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(560,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            binding.cardviewId.setLayoutParams(linearOverAllParams);
////            binding.cardviewId.setLayoutParams(new LinearLayout.LayoutParams(750, ViewGroup.LayoutParams.MATCH_PARENT));
//        }
//        if (lstBill.size() > 0) {
//            adapter = new RecyclerAdapter(mcontext, lstBill);
//            binding.recyclerView.setAdapter(adapter);
//            //getBillAll();
//            //        recyclerView.setAdapter(adapter);
//            binding.recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
//            binding.recyclerView.addItemDecoration(new
//                    DividerItemDecoration(mcontext,
//                    DividerItemDecoration.VERTICAL));
//        }
//
//
//
//    }

    //    private final Runnable m_Runnable = new Runnable() {
//        public void run() {
//
//            mHandler.postDelayed(m_Runnable, 500);
//             //if (str_newText.length() > 1){
//            if (getContext() != null) {
//                if (St.equals("1")) {
//                    RecyclerViewAdapter.count = 0;
//                    getBillAll();
//                    if (lstBill.size() > 0) {
//                        adapter = new RecyclerAdapter(getContext(), lstBill);
//                        binding.recyclerView.setAdapter(adapter);
//                        //getBillAll();
//                        //        recyclerView.setAdapter(adapter);
//                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        binding.recyclerView.addItemDecoration(new
//                                DividerItemDecoration(getContext(),
//                                DividerItemDecoration.VERTICAL));
//                    }
//                    St = "0";
//                }
//            }
//            }
//       // }
//
//
//
//    };
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // Only if you need to restore open/close state when
//        // the orientation is changed
//        if (adapter != null) {
//            adapter.saveStates(outState);
//        }
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // Only if you need to restore open/close state when
//        // the orientation is changed
//        if (adapter != null) {
//            adapter.restoreStates(savedInstanceState);
//        }
//    }


    private List<String> createList(int n) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            list.add("View " + i);
        }

        return list;
    }

//    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
//        private ArrayList<BillData> dataList;
//
//        public ListAdapter(ArrayList<BillData> data)
//        {
//            this.dataList = data;
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//            TextView table_name,table_name1,table_name2,table_name3,table_name4;
//            TextView occupied_name,occupied_name1,occupied_name2,occupied_name3,occupied_name4;
//            TextView pax_name,pax_name1,pax_name2,pax_name3,pax_name4;
//            DragLinearLayout dragLinearLayout;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//            }
//        }
//
//        @Override
//        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_recyclerview_item, parent, false);
//
//           ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
//            return viewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
//            holder.table_name.setText(dataList.get(position).getTableName());
//            holder.occupied_name.setText(dataList.get(position).getOccupiedName());
//            holder.pax_name.setText(dataList.get(position).getPaxName());
//            holder.table_name1.setText(dataList.get(position).getTableName1());
//            holder.occupied_name1.setText(dataList.get(position).getOccupiedName2());
//            holder.pax_name1.setText(dataList.get(position).getPaxName3());
//            holder.table_name2.setText(dataList.get(position).getTableName());
//            holder.occupied_name2.setText(dataList.get(position).getOccupiedName());
//            holder.pax_name2.setText(dataList.get(position).getPaxName());
//            holder.table_name3.setText(dataList.get(position).getTableName1());
//            holder.occupied_name3.setText(dataList.get(position).getOccupiedName2());
//            holder.pax_name3.setText(dataList.get(position).getPaxName3());
//            holder.table_name4.setText(dataList.get(position).getTableName1());
//            holder.occupied_name4.setText(dataList.get(position).getOccupiedName2());
//            holder.pax_name4.setText(dataList.get(position).getPaxName3());
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public void onClick(View v) {
//                    ClearBillSheetFragment clearBillSheetFragment = new ClearBillSheetFragment();
//                    clearBillSheetFragment.show(getActivity().getSupportFragmentManager(), clearBillSheetFragment.getTag());
//                }
//            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    ShowBalanceMergeList(2,getContext());
//                    return true;
//                }
//            });
////            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
////                @Override
////                public boolean onLongClick(View v) {
////                    ShowBalanceMergeList(2,getContext());
////                    return true;
////                }
////            });
//        }
//
//        @Override
//        public int getItemCount()
//        {
//            return dataList.size();
//        }
//    }

//    private final Runnable m_Runnable_online_order = new Runnable()
//    {
//        public void run() {
//            new LongOperation().execute();
//            //Hold();
////            Map<String, String> postData = new HashMap<>();
////            postData.put("param1", param1);
////            postData.put("anotherParam", anotherParam);
////            PostTask task = new HttpPostAsyncTask(postData);
////            task.execute(baseUrl + "/some/path/goes/here");
//
//            //new YourDownload().execute();
//            //10000 10sec
////            Intent startServiceIntent = new Intent(context, HelloService.class);
////            context.startService(startServiceIntent);
//             mHandler_online_order.postDelayed(m_Runnable_online_order, 10000);
////            if (!url.isEmpty()) {
//               //GetSalesForOrdering();
////                // final double leftover = MathUtil.Truncate((itemincludetax2 + round) - payment, decimalpoint, 0);
////                //Cursor data = DBFunc.Query("SELECT OpenDateTime,CloseDateTime,Cashier,CashierID,Refer,ReferInfo1,ReferInfo2,ReferInfo3 FROM BILL WHERE BillNo = " + BillID, false);
//////            Date printTime = null;
//////            if (!(data == null)) {
//////                printTime = new Date(data.getLong(0));
//////            }
//////            Date datetime = new Date(System.currentTimeMillis());
//////            PrintKP(BillID,datetime);
////                //SoundAlert();
////                GetSalesForOrdering();
////
////                ////DeliveryInfoJsonDatatoLalava();
////                //LalaMoveDelivery();
//////                if (obj_RECEIPTFLAG.equals("O")){
//////                    UpdateStatusForOnlineOrder();
//////                }
////            }
//
//        }
//
//    };

//    private void GetSalesForOrdering(final Context context) {
//
////                String sql = "SELECT TransNo,ItemID,ItemName,ItemPrice,ItemQty,QueueStatus,ItemSKU" +
////                        " FROM OnlineBill_HOLD_ITEMS";

////                Cursor c = DBFunc.Query(sql, false);
////                if (c != null) {
////                    assert c != null;
////                    while (c.moveToNext()) {
////                        TransNo = c.getString(0);
////                        IttemID = c.getString(1);
////                        ItemName = c.getString(2);
////                        ItemPrice = c.getString(3);
////                        ItemQty = c.getString(4);
////                        QueueStatus = c.getString(5);
////                        intTableNo = c.getString(5);
////                        ItemSKU = c.getString(6);
////                        sql = "SELECT idx FROM BillPLU WHERE BillNo = '"+TransNo+"'";

////                        c = DBFunc.Query(sql, false);
////                        if (c != null) {
////                            if (c.getCount() == 0) {
////                                //sql = "INSERT INTO BillPLU (BillNo,PLUID,Name,Amount,Qty,OnlineOrdering,QueueNo,TableNo,DateTime,DeptID,DeptName,PLUCode) VALUES (";
////                                sql = "INSERT INTO BillPLU (BillNo,PLUID,Name,Amount,Qty,DateTime,DeptID,DeptName,PLUCode) VALUES (";
////                                sql += "'" + TransNo + " ', ";
////                                sql += "'" + IttemID + "', ";
////                                sql += "'" + ItemName + "', ";
////                                sql += "'" + ItemPrice + "', ";
////                                sql += "'" + ItemQty + "', ";
////                                //sql += RecyclerViewAdapter.countSelectedArr + ", ";
////
////                                sql += System.currentTimeMillis() + ", ";
////                                sql += 0 + ", ";
////                                sql += "'" + DBFunc.PurifyString("") + "', ";
////
////                                sql += "'" + ItemSKU + "')";

////                                DBFunc.ExecQuery(sql, false);
////                            }
////
////                        }
////
////                    }
////                }
//        //RequestQueue queue = Volley.newRequestQueue(getContext());
////        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        final String finalCompany_code = company_code;
//        final String finalRetails_ID = retail_ID;
//        final JSONObject jsonObject = new JSONObject();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//                        String xmlString = response;

//                        Document xmlparse  = null;
//                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        for (int i=0;i< parse.getElementsByTagName("GetSalesForOrderingResponse").getLength();i++) {
//                            GetSalesForOrderingResponse = (parse.getElementsByTagName("GetSalesForOrderingResponse").getLength() > 0)
//                                    ? parse.getElementsByTagName("GetSalesForOrderingResponse").item(i).getTextContent() : " ";
//                        }
//                        if (!GetSalesForOrderingResponse.equals("0")) try {
//                            JSONObject obj = new JSONObject(GetSalesForOrderingResponse);
//                            String RETAIL_SALES = obj.getString("RETAIL_SALES");
//                            JSONArray mJsonArray_RETAIL_SALES = new JSONArray(RETAIL_SALES);
//                            for (int i = 0; i < mJsonArray_RETAIL_SALES.length(); i++) {
//                                JSONObject retail_sales_arr_Values = mJsonArray_RETAIL_SALES.getJSONObject(i);
//                                obj_SALESID = retail_sales_arr_Values.getString("SALESID");
//                                obj_SALESNO = retail_sales_arr_Values.getString("SALESNO");
//                                obj_SALESDATE = retail_sales_arr_Values.getString("SALESDATE");
//                                //obj_CREATETIME = retail_sales_arr_Values.getString("CREATETIME");
//                                obj_SALESAMOUNT = retail_sales_arr_Values.getString("SALESAMOUNT");
//                                obj_RECEIPTFLAG = retail_sales_arr_Values.getString("RECEIPTFLAG");
//                                obj_QueueNo = retail_sales_arr_Values.getString("QueueNo");
//                                obj_TableNo = retail_sales_arr_Values.getString("TableNo");
//

//                                GetDetailsForOrder(obj_RECEIPTFLAG, context);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//
//                        // Now you can use any deserializer to make sense of data
////                                JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
//                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
//                        "  <soap:Body>\n" +
//                        "    <GetSalesForOrdering xmlns=\"http://tempuri.org/\">\n" +
//                        "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
//                        "      <retailid>"+finalRetails_ID+"</retailid>\n" +
//                        "    </GetSalesForOrdering>\n" +
//                        "  </soap:Body>\n" +
//                        "</soap:Envelope>";

//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };

//        queue.add(stringRequest);
//        // }
//    }
//    private void GetDetailsForOrder(final String obj_RECEIPTFLAG, Context context) {
//        //RequestQueue queue = Volley.newRequestQueue(context);
//        final String finalRetail_ID = retail_ID;
//        String finalSalesStatus = "";

//        if (obj_RECEIPTFLAG.equals("H")){
//            finalSalesStatus = "HOLD";
//        }else {
//            finalSalesStatus = "PAYMENT";
//        }
//        final String finalCompany_code = company_code;
//        final String finalSaleNo = obj_SALESNO;
//        final JSONObject jsonObject = new JSONObject();
//        final String finalSalesStatus1 = finalSalesStatus;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//                        String xmlString = response;

//                        Document xmlparse  = null;
//                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        for (int i=0;i< parse.getElementsByTagName("GetDetailsForOrderResult").getLength();i++) {
//                            GetDetailsForOrderResult = (parse.getElementsByTagName("GetDetailsForOrderResult").getLength() > 0)
//                                    ? parse.getElementsByTagName("GetDetailsForOrderResult").item(i).getTextContent() : " ";
//                        }


//                        RETAIL_SALES(GetDetailsForOrderResult, obj_RECEIPTFLAG);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//
//                        // Now you can use any deserializer to make sense of data
////                                JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
//                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
//                        "  <soap:Body>\n" +
//                        "    <GetDetailsForOrder xmlns=\"http://tempuri.org/\">\n" +
//                        "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
//                        "      <salesno>"+finalSaleNo+"</salesno>\n" +
//                        "      <retailid>"+finalRetail_ID+"</retailid>\n" +
//                        "      <salesstatus>"+ finalSalesStatus1 +"</salesstatus>\n" +
//                        "    </GetDetailsForOrder>\n" +
//                        "  </soap:Body>\n" +
//                        "</soap:Envelope>";

//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };

//        queue.add(stringRequest);
//        // }
//    }
//    ArrayList<String> modVal = new ArrayList<String>();
//    ArrayList<String> modParentItemVal = new ArrayList<String>();
//    private void RETAIL_SALES(String ResponseObj, String obj_receiptflag) {
//        try {
//            JSONObject obj = new JSONObject(ResponseObj);

//            if (obj_receiptflag.equals("H")){
//                String RETAIL_SALES_HOLDHDR = obj.getString("RETAIL_SALES_HOLDHDR");
//                JSONArray mJsonArray_HOLDHDR = new JSONArray(RETAIL_SALES_HOLDHDR);
//                String RETAIL_SALES_HOLDDTL = obj.getString("RETAIL_SALES_HOLDDTL");
//                JSONArray mJsonArray_HOLDDTL = new JSONArray(RETAIL_SALES_HOLDDTL);
//                for (int i =0; i < mJsonArray_HOLDHDR.length(); i++) {
//                    JSONObject retail_sales_arr_Values = mJsonArray_HOLDHDR.getJSONObject(i);
//                    TransID = retail_sales_arr_Values.getString("TransID");
//                    CompanyID = retail_sales_arr_Values.getString("CompanyID");
//                    RetailerID = retail_sales_arr_Values.getString("RetailerID");
//                    TransNo = retail_sales_arr_Values.getString("TransNo");
//                    TotalDue = retail_sales_arr_Values.getString("TotalDue");
//                    TotalGST = retail_sales_arr_Values.getString("TotalGST");
//                    TotalDisc = retail_sales_arr_Values.getString("TotalDisc");
//                    TransDate = retail_sales_arr_Values.getString("TransDate");
//                    CreateTime = retail_sales_arr_Values.getString("CreateTime");
//                    TotalQty = retail_sales_arr_Values.getString("TotalQty");
//                    CashierID = retail_sales_arr_Values.getString("CashierID");
//                    MemberID = retail_sales_arr_Values.getString("MemberID");
//                    isNewCust = retail_sales_arr_Values.getString("isNewCust");
//                    SalesPersonID = retail_sales_arr_Values.getString("SalesPersonID");
//                    CommID = retail_sales_arr_Values.getString("CommPerc");
//                    CommPerc = retail_sales_arr_Values.getString("CommPerc");
//                    ReceiptOrderStatus = retail_sales_arr_Values.getString("ReceiptOrderStatus");
//                    vchQueueNo = retail_sales_arr_Values.getString("vchQueueNo");
//                    intTableNo = retail_sales_arr_Values.getString("intTableNo");
//                    MacAddress = retail_sales_arr_Values.getString("MacAddress");
//                    TerminalID = retail_sales_arr_Values.getString("TerminalID");
//                    PendingSync = retail_sales_arr_Values.getString("PendingSync");
//                    SPBI01 = retail_sales_arr_Values.getString("SPBI01");
//                    SPBI02 = retail_sales_arr_Values.getString("SPBI02");
//                    SPBI03 = retail_sales_arr_Values.getString("SPBI03");
//                    SPBI04 = retail_sales_arr_Values.getString("SPBI04");
//                    SPBI05 = retail_sales_arr_Values.getString("SPBI05");
//                    SPD01 = retail_sales_arr_Values.getString("SPD01");
//                    SPD02 = retail_sales_arr_Values.getString("SPD02");
//                    SPD03 = retail_sales_arr_Values.getString("SPD03");
//                    SPD04 = retail_sales_arr_Values.getString("SPD04");
//                    SPD05 = retail_sales_arr_Values.getString("SPD05");
//                    SPI01 = retail_sales_arr_Values.getString("SPI01");
//                    SPI02 = retail_sales_arr_Values.getString("SPI02");
//                    SPI03 = retail_sales_arr_Values.getString("SPI03");
//                    SPI04 = retail_sales_arr_Values.getString("SPI04");
//                    SPI05 = retail_sales_arr_Values.getString("SPI05");
//                    SPC01 = retail_sales_arr_Values.getString("SPC01");
//                    SPC02 = retail_sales_arr_Values.getString("SPC02");
//                    SPC03 = retail_sales_arr_Values.getString("SPC03");
//                    SPC04 = retail_sales_arr_Values.getString("SPC04");
//                    SPC05 = retail_sales_arr_Values.getString("SPC05");
//                    SPV01 = retail_sales_arr_Values.getString("SPV01");
//                    SPV02 = retail_sales_arr_Values.getString("SPV02");
//                    SPV03 = retail_sales_arr_Values.getString("SPV03");
//                    SPV04 = retail_sales_arr_Values.getString("SPV04");
//                    SPV05 = retail_sales_arr_Values.getString("SPV05");
//                    SPT01 = retail_sales_arr_Values.getString("SPT01");
//                    SPT02 = retail_sales_arr_Values.getString("SPT02");
//                    SPT03 = retail_sales_arr_Values.getString("SPT03");
//                    SPT04 = retail_sales_arr_Values.getString("SPT04");
//                    SPT05 = retail_sales_arr_Values.getString("SPT05");
//                    SDT01 = retail_sales_arr_Values.getString("SDT01");
//                    LastUser = retail_sales_arr_Values.getString("LastUser");
//                    LastUpdate = retail_sales_arr_Values.getString("LastUpdate");
//                    LockUser = retail_sales_arr_Values.getString("LockUser");
//                    LockUpdate = retail_sales_arr_Values.getString("LockUpdate");
//                    LockStatus = retail_sales_arr_Values.getString("LockStatus");
//                    RecordStatus = retail_sales_arr_Values.getString("RecordStatus");
//                    RecordUpdate = retail_sales_arr_Values.getString("RecordUpdate");
//                    QueueStatus = retail_sales_arr_Values.getString("QueueStatus");
//
//                    String sql = "SELECT ID FROM OnlineBill_HOLD WHERE TransID = '"+TransID+"'";

//
//                    Cursor c = DBFunc.Query(sql, false);
//                    if (c != null) {

//                        if (c.getCount() == 0) {
//
//                            sql = "SELECT BillNo FROM Bill WHERE TransNo = '" + TransNo + "'";

//                            c = DBFunc.Query(sql, false);
//                            if (c != null) {
//                                if (c.getCount() == 0) {
//
//                                    int latestID = 0;
//                                    c = DBFunc.Query("SELECT MAX(BillNo) FROM Bill", false);
//                                    if (c != null) {
//                                        if (c.moveToNext()) {
//                                            if (!c.isNull(0)) {
//                                                latestID = c.getInt(0);
//                                            }
//                                        }
//                                        c.close();
//                                    }
//                                    DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, OnlineOrderBill,TransNo) VALUES " +
//                                            "(" + System.currentTimeMillis() + ",'" + CashierID + "', " +
//                                            Allocator.cashierID + ", '" + latestID + 1 + "','ON','" + TransNo + "')", false);
//
//                                }
//                                c.close();
//                            }
//
//                            DBFunc.ExecQuery("INSERT INTO OnlineBill_HOLD (TransID, CompanyID, RetailerID, TransNo, TotalDue ," +
//                                    "TotalGST,TotalDisc,TransDate,CreateTime,TotalQty ," +
//                                    "CashierID,MemberID,isNewCust,SalesPersonID,CommID ," +
//                                    "CommPerc,ReceiptOrderStatus,vchQueueNo,intTableNo,MacAddress ," +
//                                    "TerminalID,PendingSync,LastUser,LastUpdate,LockUser ," +
//                                    "LockUpdate,LockStatus,RecordStatus,RecordUpdate,QueueStatus ,DateTime) VALUES " +
//                                    "(" +
//                                    "'"+TransID+"',"+
//                                    "'"+CompanyID+"',"+
//                                    "'"+RetailerID+"',"+
//                                    "'"+TransNo+"',"+
//                                    "'"+TotalDue+"',"+
//                                    "'"+TotalGST+"',"+
//                                    "'"+TotalDisc+"',"+
//                                    "'"+TransDate+"',"+
//                                    "'"+CreateTime+"',"+
//                                    "'"+TotalQty+"',"+
//                                    "'"+CashierID+"',"+
//                                    "'"+MemberID+"',"+
//                                    "'"+isNewCust+"',"+
//                                    "'"+SalesPersonID+"',"+
//                                    "'"+CommID+"',"+
//                                    "'"+CommPerc+"',"+
//                                    "'"+ReceiptOrderStatus+"',"+
//                                    "'"+vchQueueNo+"',"+
//                                    "'"+intTableNo+"',"+
//                                    "'"+MacAddress+"',"+
//                                    "'"+TerminalID+"',"+
//                                    "'"+PendingSync+"',"+
//                                    "'"+LastUser+"',"+
//                                    "'"+LastUpdate+"',"+
//                                    "'"+LockUpdate+"',"+
//                                    "'"+CommPerc+"',"+
//                                    "'"+LockStatus+"',"+
//                                    "'"+RecordStatus+"',"+
//                                    "'"+RecordUpdate+"',"+
//                                    "'"+QueueStatus+"',"+
//                                    "" + System.currentTimeMillis() + ")", false);
//                        }
//                        c.close();
//                    }
//
//
//                }
//
//
//
//                Double total_price = 0.0;
//                Integer total_ItemQty = 0;
//                for (int i =0; i < mJsonArray_HOLDDTL.length(); i++) {
//                    JSONObject retail_sales_arr_Valuesh = mJsonArray_HOLDDTL.getJSONObject(i);
//                    TransID = retail_sales_arr_Valuesh.getString("TransID");
//                    RecordNo = retail_sales_arr_Valuesh.getString("RecordNo");
//                    LineNo = retail_sales_arr_Valuesh.getString("LineNo");
//                    ItemQty = retail_sales_arr_Valuesh.getString("ItemQty");
//                    ItemPrice = retail_sales_arr_Valuesh.getString("ItemPrice");
//                    ItemTotal = retail_sales_arr_Valuesh.getString("ItemTotal");
//                    ItemGST = retail_sales_arr_Valuesh.getString("ItemGST");
//                    ItemDiscType = retail_sales_arr_Valuesh.getString("ItemDiscType");
//                    ItemDisc1 = retail_sales_arr_Valuesh.getString("ItemDisc1");
//                    ItemDisc2 = retail_sales_arr_Valuesh.getString("ItemDisc2");
//                    ItemDisc3 = retail_sales_arr_Valuesh.getString("ItemDisc3");
//                    IttemID = retail_sales_arr_Valuesh.getString("ItemID");
//                    //ItemName = retail_sales_arr_Valuesh.getString("ItemName");
//                    ItemName = retail_sales_arr_Valuesh.getString("itemname");
//                    ItemBarcode = retail_sales_arr_Valuesh.getString("ItemBarcode");
//                    ItemUOM = retail_sales_arr_Valuesh.getString("ItemUOM");
//                    ItemGSTInEx = retail_sales_arr_Valuesh.getString("ItemGSTInEx");
//                    ItemCost = retail_sales_arr_Valuesh.getString("ItemCost");
//                    ItemActQty = retail_sales_arr_Valuesh.getString("ItemActQty");
//                    ItemUOMID = retail_sales_arr_Valuesh.getString("ItemUOMID");
//                    ItemGroupDisc = retail_sales_arr_Valuesh.getString("ItemGroupDisc");
//                    ItemSKU = retail_sales_arr_Valuesh.getString("ItemSKU");
//                    SupplierID = retail_sales_arr_Valuesh.getString("SupplierID");
//                    SalesPersonID = retail_sales_arr_Valuesh.getString("SalesPersonID");
//                    SalesCommTypeID = retail_sales_arr_Valuesh.getString("SalesCommTypeID");
//                    SalesCommPerc = retail_sales_arr_Valuesh.getString("SalesCommPerc");
//                    ItemCommPerc = retail_sales_arr_Valuesh.getString("ItemCommPerc");
//                    ItemCommAmt = retail_sales_arr_Valuesh.getString("ItemCommAmt");
//                    ItemSerialNo = retail_sales_arr_Valuesh.getString("ItemSerialNo");
//                    DISCID = retail_sales_arr_Valuesh.getString("DISCID");
//                    ItemIMEINo = retail_sales_arr_Valuesh.getString("ItemIMEINo");
//                    ItemBatchNo = retail_sales_arr_Valuesh.getString("ItemBatchNo");
//                    ItemStatus = retail_sales_arr_Valuesh.getString("ItemStatus");
//                    OpenPriceRemark = retail_sales_arr_Valuesh.getString("OpenPriceRemark");
//                    ItemRemark = retail_sales_arr_Valuesh.getString("ItemRemark");
//                    ExpireDate = retail_sales_arr_Valuesh.getString("ExpireDate");
//                    ExpiryDay = retail_sales_arr_Valuesh.getString("ExpiryDay");
//                    RedeemPoint = retail_sales_arr_Valuesh.getString("RedeemPoint");
//                    ParentItemID_ADDON = retail_sales_arr_Valuesh.getString("ParentItemID_ADDON");
//                    bitAddOnItem = retail_sales_arr_Valuesh.getString("bitAddOnItem");
//                    ParentDetailID_ADDON = retail_sales_arr_Valuesh.getString("ParentDetailID_ADDON");
//                    MemDOBDiscPerc = retail_sales_arr_Valuesh.getString("MemDOBDiscPerc");
//                    MemDOBDiscAmount = retail_sales_arr_Valuesh.getString("MemDOBDiscAmount");
//                    ReceiptOrderStatus = retail_sales_arr_Valuesh.getString("ReceiptOrderStatus");
//                    TerminalID = retail_sales_arr_Valuesh.getString("TerminalID");
//                    RFID = retail_sales_arr_Valuesh.getString("RFID");
//                    PendingSync = retail_sales_arr_Valuesh.getString("PendingSync");
//                    SPBI01 = retail_sales_arr_Valuesh.getString("SPBI01");
//                    SPBI02 = retail_sales_arr_Valuesh.getString("SPBI02");
//                    SPBI03 = retail_sales_arr_Valuesh.getString("SPBI03");
//                    SPBI04 = retail_sales_arr_Valuesh.getString("SPBI04");
//                    SPBI05 = retail_sales_arr_Valuesh.getString("SPBI05");
//                    SPD01 = retail_sales_arr_Valuesh.getString("SPD01");
//                    SPD02 = retail_sales_arr_Valuesh.getString("SPD02");
//
//                    String sql = "SELECT ID FROM OnlineBill_HOLD_ITEMS WHERE ItemID = '"+IttemID+"'";

//                    Cursor c = DBFunc.Query(sql, false);
//                    if (c != null) {
//                        if (c.getCount() == 0) {

//                            sql = "INSERT INTO OnlineBill_HOLD_ITEMS " +
//                                    "(TransID,TransNo,RecordNo,LineNo,ItemQty,ItemPrice ," +
//                                    "ItemTotal,ItemGST,ItemDiscType,ItemDisc1,ItemDisc2 ," +
//                                    "ItemDisc3,ItemID,ItemName,ItemBarcode,ItemUOM,ItemGSTInEx ," +
//                                    "ItemCost,ItemActQty,ItemUOMID,ItemGroupDisc,ItemSKU ," +
//                                    "SupplierID,SalesPersonID,SalesCommTypeID,SalesCommPerc,ItemCommPerc ," +
//                                    "ItemCommAmt,ItemSerialNo,DISCID,ItemIMEINo,ItemBatchNo," +
//                                    "ItemStatus,OpenPriceRemark,ItemRemark,ExpireDate,ExpiryDay," +
//                                    "RedeemPoint,ParentItemID_ADDON,bitAddOnItem,ParentDetailID_ADDON,MemDOBDiscPerc," +
//                                    "MemDOBDiscAmount,ReceiptOrderStatus,TerminalID,RFID,PendingSync," +
//                                    "SPBI01,SPBI02,SPBI03,SPBI04,SPBI05," +
//                                    "SPD01,SPD02,SPD03,SPD04,SPD05," +
//                                    "SPI01,SPI02,SPI03,SPI04,SPI05," +
//                                    "SPC01,SPC02,SPC03,SPC04,SPC05," +
//                                    "SPV01,SPV02,SPV03,SPV04,SPV05," +
//                                    "SPT01,SPT02,SPT03,SPT04,SPT05," +
//                                    "SDT01,SDT02,SDT03,SDT04,SDT05," +
//                                    "LastUser,LockUser,LastUpdate,LockUpdate,LockStatus," +
//                                    "RecordStatus,RecordUpdate,QueueStatus,DateTime) VALUES " +
//                                    "(" +
//                                    "'"+TransID+"','"+TransNo+"','"+RecordNo+"','"+LineNo+"','"+ItemQty+"','"+ItemPrice+"',"+
//                                    "'"+ItemTotal+"','"+ItemGST+"','"+ItemDiscType+"','"+ItemDisc1+"','"+ItemDisc2+"',"+
//                                    "'"+ItemDisc3+"','"+IttemID+"','"+ItemName+"','"+ItemBarcode+"','"+ItemUOM+"','"+ItemGSTInEx+"',"+
//                                    "'"+ItemCost+"','"+ItemActQty+"',"+
//                                    "'"+ItemUOMID+"',"+
//                                    "'"+ItemGroupDisc+"',"+
//                                    "'"+ItemSKU+"',"+
//                                    "'"+SupplierID+"',"+
//                                    "'"+SalesPersonID+"',"+
//                                    "'"+SalesCommTypeID+"',"+
//                                    "'"+SalesCommPerc+"',"+
//                                    "'"+ItemCommPerc+"','"+
//                                    ItemCommAmt+"','"+ItemSerialNo+"','"+DISCID+"','"+ItemIMEINo+"','"+ItemBatchNo+"','"+
//                                    ItemStatus+"','"+OpenPriceRemark+"','"+ItemRemark+"','"+ExpireDate+"','"+ExpiryDay+"','"+
//                                    RedeemPoint+"','"+ParentItemID_ADDON+"','"+bitAddOnItem+"','"+ParentDetailID_ADDON+"','"+MemDOBDiscPerc+"','"+
//                                    MemDOBDiscAmount+"','"+ReceiptOrderStatus+"','"+TerminalID+"','"+RFID+"','"+PendingSync+"','"+
//                                    SPBI01+"','"+SPBI02+"','"+SPBI03+"','"+SPBI04+"','"+SPBI05+"','"+
//                                    SPD01+"','"+SPD02+"','"+SPD03+"','"+SPD04+"','"+SPD05+"','"+
//                                    SPI01+"','"+SPI02+"','"+SPI03+"','"+SPI04+"','"+SPI05+"','"+
//                                    SPC01+"','"+SPC02+"','"+SPC03+"','"+SPC04+"','"+SPC05+"','"+
//                                    SPV01+"','"+SPV02+"','"+SPV03+"','"+SPV04+"','"+SPV05+"','"+
//                                    SPT01+"','"+SPT02+"','"+SPT03+"','"+SPT04+"','"+SPT05+"','"+
//                                    SDT01+"','"+SDT02+"','"+SDT03+"','"+SDT04+"','"+SDT05+"','"+
//                                    LastUser+"','"+LockUser+"','"+LastUpdate+"','"+LockUpdate+"','"+LockStatus+"','"+
//                                    RecordStatus+"','"+RecordUpdate+"','"+QueueStatus+"',"+
//                                    "" + System.currentTimeMillis() + ")";

//                            DBFunc.ExecQuery(sql, false);
//                            //Hold

//                            if (ItemQty.equals("0")) {
//                                AddNewProductActivity.SaveModifier(ItemName,ItemPrice);
//                                modVal.add(ItemName);
//                                modParentItemVal.add(ParentItemID_ADDON);
//                                sql = "INSERT INTO PLUModi (ItemID,ItemName,ModiName,BillNo,BillID) VALUES (";
//                                sql += "'" + ParentItemID_ADDON + "', ";
//                                sql += "'0',";
//                                sql += "'" + ItemName + "',";
//                                sql += "'" + TransNo + "',";
//                                sql += "'0')";

//                                DBFunc.ExecQuery(sql, false);
//
//                            }else {
////                        BitmapDrawable drawable = null;
////
////                        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
////                                R.drawable.black_photo);
////                        drawable = new BitmapDrawable(this.getResources(), icon);
////                        Bitmap bitmap = drawable.getBitmap();
//                                sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";

//                                c = DBFunc.Query(sql, true);
//                                if (c != null) {
//                                    if (c.getCount() == 0) {
//                                        sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,ProductVariant,ProductModifiers," +
//                                                "AllowNameQuickEdit,AllowPriceQuickEdit,ProductCategoryID,ProductCategoryName,DateTime,Condi_Seq) " +
//                                                "VALUES ('" + DBFunc.PurifyString(ItemName) + "'," +
//                                                "'" + IttemID + "'," +
//                                                "''," +
//                                                "" + 0 + "," +
//                                                ItemPrice + ",'" +
//                                                00000 + "','" + DBFunc.PurifyString(vchQueueNo) + "'," +
//                                                "'" + DBFunc.PurifyString(String.valueOf(AddNewProductActivity.BitMapToString(bitmap))) + "'," +
//                                                "'" + DBFunc.PurifyString("0") + "'," +
//                                                "'" + DBFunc.PurifyString("0") + "'," +
//                                                "" + Integer.parseInt("0") + "," +
//                                                "" + Integer.parseInt("0") + "," +
//                                                "'0'," +
//                                                "'0'," +
//                                                "" + System.currentTimeMillis() + "," +
//                                                000 + ")";
//                                        DBFunc.ExecQuery(sql, true);
//                                    }
//                                    c.close();
//                                }
//                            }
//
//                            sql = " SELECT ID FROM PLU Where UUID = '" + IttemID + "' ";
//                            //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
//                            c = DBFunc.Query(sql, true);
//                            if (c != null) {
//                                if (c.moveToNext()) {
//                                    total_price += Double.valueOf(ItemPrice);
//                                    RecyclerViewAdapter.totalItems = 1;
//                                    RecyclerViewAdapter.countSelectedArr = 1;
//                                    RecyclerViewAdapter.sldNameArr.add(ItemName);
//                                    RecyclerViewAdapter.sltPriceTotalArr.add(ItemPrice);
//                                    RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(ItemQty));
//                                    total_ItemQty += Integer.parseInt(ItemQty);
//                                    RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(total_price));
//                                    RecyclerViewAdapter.sltBillDisArr.add("0");
//                                    RecyclerViewAdapter.sldIDArr.add(c.getString(0));
//                                    RecyclerViewAdapter.sltCategoryIDArr.add("0");
//                                    RecyclerViewAdapter.sltCategoryNameArr.add("0");
////                                    RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);

////                                    RecyclerViewAdapter.intTableNo.add(intTableNo);
//                                }
//                            }
//                        }
//
//                        if (RecyclerViewAdapter.sldIDArr.size() > 0) {
//                            Integer totalItems = RecyclerViewAdapter.totalItems;
//                            ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
//                            ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                            ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
//                            ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                            ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
//                            ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
//                            ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
//                            Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
//                            //String BillNo = String.valueOf(MainActivity.BillID);
//                            String BillNo = TransNo;
//                            Integer qty = total_ItemQty;
//                            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                            String paymenttype = "Cash";
//                            String status = "SALES";
//                            Double sub_total = total_price;
//                            Double amount = total_price;
//                            ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;
//
//                            RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);
//                            RecyclerViewAdapter.intTableNo.add(intTableNo);
//                            ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                            ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
////                    CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                            qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                            sub_total, amount, paymenttype,Modifier_ID,vchQueueNo,intTableNo);
//

//                            //hold
//                            CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                                    qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                                    sub_total, amount, paymenttype,MID,vchQueueNo,intTableNo);
//
//                        }
//
//                        }
//                        c.close();
//                    }
//
////                    if (ItemQty.equals("0")) {
////                        AddNewProductActivity.SaveModifier(ItemName,ItemPrice);
////                    }else {
//////                        BitmapDrawable drawable = null;
//////
//////                        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//////                                R.drawable.black_photo);
//////                        drawable = new BitmapDrawable(this.getResources(), icon);
//////                        Bitmap bitmap = drawable.getBitmap();
////                        sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";

////                        c = DBFunc.Query(sql, true);
////                        if (c != null) {
////                            if (c.getCount() == 0) {
////                                sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,ProductVariant,ProductModifiers," +
////                                        "AllowNameQuickEdit,AllowPriceQuickEdit,ProductCategoryID,ProductCategoryName,DateTime,Condi_Seq) " +
////                                        "VALUES ('" + DBFunc.PurifyString(ItemName) + "'," +
////                                        "'" + IttemID + "'," +
////                                        "''," +
////                                        "" + 0 + "," +
////                                        ItemPrice + ",'" +
////                                        00000 + "','" + DBFunc.PurifyString(vchQueueNo) + "'," +
////                                        "'" + DBFunc.PurifyString(String.valueOf(AddNewProductActivity.BitMapToString(bitmap))) + "'," +
////                                        "'" + DBFunc.PurifyString("0") + "'," +
////                                        "'" + DBFunc.PurifyString("0") + "'," +
////                                        "" + Integer.parseInt("0") + "," +
////                                        "" + Integer.parseInt("0") + "," +
////                                        "'0'," +
////                                        "'0'," +
////                                        "" + System.currentTimeMillis() + "," +
////                                        000 + ")";
////                                DBFunc.ExecQuery(sql, true);
////                            }
////                            c.close();
////                        }
////                    }
////
////                    sql = " SELECT ID FROM PLU Where UUID = '" + IttemID + "' ";
////                    //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
////                    c = DBFunc.Query(sql, true);
////                    if (c != null) {
////                        if (c.moveToNext()) {
////                            total_price += Double.valueOf(ItemPrice);
////                            RecyclerViewAdapter.totalItems = 1;
////                            RecyclerViewAdapter.countSelectedArr = 1;
////                            RecyclerViewAdapter.sldNameArr.add(ItemName);
////                            RecyclerViewAdapter.sltPriceTotalArr.add(ItemPrice);
////                            RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(ItemQty));
////                            RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(total_price));
////                            RecyclerViewAdapter.sltBillDisArr.add("0");
////                            RecyclerViewAdapter.sldIDArr.add(c.getString(0));
////                            RecyclerViewAdapter.sltCategoryIDArr.add("0");
////                            RecyclerViewAdapter.sltCategoryNameArr.add("0");
////                            RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);

////                            RecyclerViewAdapter.intTableNo.add(intTableNo);
////                        }
////                    }
////                }
////
////                if (RecyclerViewAdapter.sldIDArr.size() > 0) {
////                    Integer totalItems = RecyclerViewAdapter.totalItems;
////                    ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
////                    ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
////                    ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
////                    ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
////                    ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
////                    ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
////                    ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
////                    Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
////                    String BillNo = String.valueOf(MainActivity.BillID);
////                    Integer qty = 1;
////                    String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
////                    String paymenttype = "Cash";
////                    String status = "SALES";
////                    Double sub_total = 0.0;
////                    Double amount = 0.0;
////                    ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;
////
////                    ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
////                    ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
//////                    CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//////                            qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//////                            sub_total, amount, paymenttype,Modifier_ID,vchQueueNo,intTableNo);
////
////                    CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                            qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                            sub_total, amount, paymenttype,MID,vchQueueNo,intTableNo);
////
////                }
//
//            }else {
//                String RETAIL_SALES = obj.getString("RETAIL_SALES");
//                JSONArray mJsonArray_RETAIL_SALES = new JSONArray(RETAIL_SALES);
//                String RETAIL_SALES_DETAIL = obj.getString("RETAIL_SALES_DETAIL");
//                JSONArray mJsonArray_RETAIL_SALES_DETAIL = new JSONArray(RETAIL_SALES_DETAIL);
//
//                String RETAIL_SALES_PAYMENT = obj.getString("RETAIL_SALES_PAYMENT");
//                JSONArray mJsonArray_RETAIL_SALES_PAYMENT = new JSONArray(RETAIL_SALES_PAYMENT);
//                for (int i = 0; i < mJsonArray_RETAIL_SALES.length(); i++) {
//                    JSONObject retail_sales_arr_Values = mJsonArray_RETAIL_SALES.getJSONObject(i);
//                    SalesID = retail_sales_arr_Values.getString("SalesID");
//                    RetailID = retail_sales_arr_Values.getString("RetailID");
//                    DOID = retail_sales_arr_Values.getString("DOID");
//                    Bill_ID = retail_sales_arr_Values.getString("BillID");
//                    vchQueueNo = retail_sales_arr_Values.getString("vchQueueNo");
//                    intTableNo = retail_sales_arr_Values.getString("intTableNo");
//                    SalesNo = retail_sales_arr_Values.getString("SalesNo");
//                    SalesTax = retail_sales_arr_Values.getString("SalesTax");
//                    SalesTaxVal = retail_sales_arr_Values.getString("SalesTaxVal");
//                    SalesDate = retail_sales_arr_Values.getString("SalesDate");
//                    CloseRetailID = retail_sales_arr_Values.getString("CloseRetailID");
//                    CloseDate = retail_sales_arr_Values.getString("CloseDate");
//                    CloseTime = retail_sales_arr_Values.getString("CloseTime");
//                    CloseSalesID = retail_sales_arr_Values.getString("CloseSalesID");
//
//                    Cursor c = DBFunc.Query("SELECT ID FROM OnlineBill_PAYMENT WHERE SalesID = '" + SalesID + "'", false);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//
//                            String sql = "SELECT BillNo FROM Bill WHERE TransNo = '" + TransNo + "'";
//
//                            c = DBFunc.Query(sql, false);
//                            if (c != null) {
//                                if (c.getCount() == 0) {
//
//                                    int latestID = 0;
//                                    c = DBFunc.Query("SELECT MAX(BillNo) FROM Bill", false);
//                                    if (c != null) {
//                                        if (c.moveToNext()) {
//                                            if (!c.isNull(0)) {
//                                                latestID = c.getInt(0);
//                                            }
//                                        }
//                                        c.close();
//                                    }
//                                    DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, OnlineOrderBill,TransNo) VALUES " +
//                                            "(" + System.currentTimeMillis() + ",'" + CashierID + "', " +
//                                            Allocator.cashierID + ", '" + latestID + 1 + "','ON','" + TransNo + "')", false);
//
//                                }
//                                c.close();
//                            }
//                            sql = "INSERT INTO OnlineBill_PAYMENT " +
//                                    "(SalesID,RetailID,DOID,Bill_ID,SalesNo,SalesTax,SalesTaxVal,SalesDate,CloseRetailID,CloseDate,CloseTime,CloseSalesID,DateTime) VALUES " +
//                                    "('" + SalesID + "','" + RetailID + "','" + DOID + "','" + Bill_ID + "','" + SalesNo + "','" + SalesTax + "','" + SalesTaxVal + "','" + SalesDate + "','" + CloseRetailID + "','" + CloseDate + "','" + CloseTime + "','" + CloseSalesID + "'," +
//                                    "" + System.currentTimeMillis() + ")";

//                            DBFunc.ExecQuery(sql, false);
//                        }
//                        c.close();
//                    }
//                }
//
//                //SoundAlert();
//                Double total_price = 0.0;
//                Integer total_ItemQty = 0;
//                for (int i = 0; i < mJsonArray_RETAIL_SALES_DETAIL.length(); i++) {
//                    JSONObject retail_sales_arr_Values = mJsonArray_RETAIL_SALES_DETAIL.getJSONObject(i);
//                    Sales_DetailID = retail_sales_arr_Values.getString("Sales_DetailID");
//                    SalesID = retail_sales_arr_Values.getString("SalesID");
//                    RetailID = retail_sales_arr_Values.getString("RetailID");
//                    ItemIDD = retail_sales_arr_Values.getString("ItemID");
//                    //ItemName = retail_sales_arr_Values.getString("ItemName");
//                    //ItemName = retail_sales_arr_Values.getString("itemname");
//                    ItemName = retail_sales_arr_Values.getString("ItemName");
//                    SupBarCode = retail_sales_arr_Values.getString("SupBarCode");
//                    ItemQty = retail_sales_arr_Values.getString("ItemQty");
//                    total_ItemQty += Integer.parseInt(ItemQty);
//                    ItemUOM = retail_sales_arr_Values.getString("ItemUOM");
//                    ItemUOMDesc = retail_sales_arr_Values.getString("ItemUOMDesc");
//                    ItemQtyAct = retail_sales_arr_Values.getString("ItemQtyAct");
//                    ItemUnitPrice = retail_sales_arr_Values.getString("ItemUnitPrice");
//                    ItemUnitCost = retail_sales_arr_Values.getString("ItemUnitCost");
//                    ItemAveCost = retail_sales_arr_Values.getString("ItemAveCost");
//                    ItemSSPx = retail_sales_arr_Values.getString("ItemSSPx");
//
//                    ItemTaxType = retail_sales_arr_Values.getString("ItemTaxType");
//                    SupplierID = retail_sales_arr_Values.getString("SupplierID");
//
//                    Cursor c = DBFunc.Query("SELECT ID FROM OnlineBill_PAYMENT_ITEMS WHERE Sales_DetailID = '" + Sales_DetailID + "'", false);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//                            String sql = "INSERT INTO OnlineBill_PAYMENT_ITEMS " +
//                                    "(Sales_DetailID,SalesID,RetailID,ItemID,ItemName,SupBarCode,ItemQty,ItemUOM,ItemUOMDesc,ItemQtyAct,ItemUnitPrice,ItemUnitCost,ItemAveCost,ItemSSPx,DateTime) VALUES " +
//                                    "('" + Sales_DetailID + "','" + SalesID + "','" + RetailID + "','" + ItemIDD + "','" + ItemName + "','" + SupBarCode + "','" + ItemQty + "','" + ItemUOM + "','" + ItemUOMDesc + "','" + ItemQtyAct + "','" + ItemUnitPrice + "','" + ItemUnitCost + "','" + ItemAveCost + "','" + ItemSSPx + "'," +
//                                    "" + System.currentTimeMillis() + ")";

//                            DBFunc.ExecQuery(sql, false);
//
//                            if (ItemQty.equals("0")) {
//                                AddNewProductActivity.SaveModifier(ItemName, ItemPrice);
//                            } else {
////                        BitmapDrawable drawable = null;
////
////                        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
////                                R.drawable.black_photo);
////                        drawable = new BitmapDrawable(this.getResources(), icon);
////                        Bitmap bitmap = drawable.getBitmap();
//                                sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";

//                                c = DBFunc.Query(sql, true);
//                                if (c != null) {
//                                    if (c.getCount() == 0) {
//                                        sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,ProductVariant,ProductModifiers," +
//                                                "AllowNameQuickEdit,AllowPriceQuickEdit,ProductCategoryID,ProductCategoryName,DateTime,Condi_Seq) " +
//                                                "VALUES ('" + DBFunc.PurifyString(ItemName) + "'," +
//                                                "'" + IttemID + "'," +
//                                                "''," +
//                                                "" + 0 + "," +
//                                                ItemPrice + ",'" +
//                                                00000 + "','" + DBFunc.PurifyString(vchQueueNo) + "'," +
//                                                "'" + DBFunc.PurifyString(String.valueOf(AddNewProductActivity.BitMapToString(bitmap))) + "'," +
//                                                "'" + DBFunc.PurifyString("0") + "'," +
//                                                "'" + DBFunc.PurifyString("0") + "'," +
//                                                "" + Integer.parseInt("0") + "," +
//                                                "" + Integer.parseInt("0") + "," +
//                                                "'0'," +
//                                                "'0'," +
//                                                "" + System.currentTimeMillis() + "," +
//                                                000 + ")";
//                                        DBFunc.ExecQuery(sql, true);
//                                    }
//                                    c.close();
//                                }
//                            }
//
//                            sql = " SELECT ID FROM PLU Where UUID = '" + IttemID + "' ";
//                            //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
//                            c = DBFunc.Query(sql, true);
//                            if (c != null) {
//                                if (c.moveToNext()) {
//                                    total_price += Double.valueOf(ItemPrice);
//                                    RecyclerViewAdapter.totalItems = 1;
//                                    RecyclerViewAdapter.countSelectedArr = 1;
//                                    RecyclerViewAdapter.sldNameArr.add(ItemName);
//                                    RecyclerViewAdapter.sltPriceTotalArr.add(ItemPrice);
//                                    RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(ItemQty));
//                                    RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(total_price));
//                                    RecyclerViewAdapter.sltBillDisArr.add("0");
//                                    RecyclerViewAdapter.sldIDArr.add(c.getString(0));
//                                    RecyclerViewAdapter.sltCategoryIDArr.add("0");
//                                    RecyclerViewAdapter.sltCategoryNameArr.add("0");
//                                    RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);

//                                    RecyclerViewAdapter.intTableNo.add(intTableNo);
//                                }
//                            }
//                        }
//
//                        if (RecyclerViewAdapter.sldIDArr.size() > 0) {
//                            Integer totalItems = RecyclerViewAdapter.totalItems;
//                            ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
//                            ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                            ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
//                            ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                            ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
//                            ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
//                            ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
//                            Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
//                            //String BillNo = String.valueOf(MainActivity.BillID);
//                            String BillNo = TransNo;
//                            Integer qty = total_ItemQty;
//                            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                            String paymenttype = "Cash";
//                            String status = "SALES";
////                            Double sub_total = 0.0;
////                            Double amount = 0.0;
//                            Double sub_total =total_price;
//                            Double amount =total_price;
//                            ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;
//
//                            ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                            ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
////                    CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                            qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                            sub_total, amount, paymenttype,Modifier_ID,vchQueueNo,intTableNo);
//
//                            String sql = "SELECT ID FROM ProductModifier WHERE Name = '" + ItemName + "'";

//                            c = DBFunc.Query(sql, true);
//                            if (c != null) {
//                                MID.clear();
//                                while (c.moveToNext()) {
//                                    MID.add(c.getInt(0));
//                                }
//                                c.close();
//                            }
//
//                            CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                                    qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                                    sub_total, amount, paymenttype, MID, vchQueueNo, intTableNo);
//
//                        }
//                        }
//                        c.close();
//                    }
//
//                for (int i =0; i < mJsonArray_RETAIL_SALES_PAYMENT.length(); i++) {
//                    JSONObject retail_sales_arr_Values = mJsonArray_RETAIL_SALES_PAYMENT.getJSONObject(i);
//                    SalesPaymentID = retail_sales_arr_Values.getString("SalesPaymentID");
//                    SalesID = retail_sales_arr_Values.getString("SalesID");
//                    RetailID = retail_sales_arr_Values.getString("RetailID");
//                    PaymentID = retail_sales_arr_Values.getString("PaymentID");
//                    PaymentReference = retail_sales_arr_Values.getString("PaymentReference");
//                    ReceiptRemarks = retail_sales_arr_Values.getString("ReceiptRemarks");
//                    OthersPayment = retail_sales_arr_Values.getString("OthersPayment");
//                    OthersPaymentRef = retail_sales_arr_Values.getString("OthersPaymentRef");
//                    Close_SalesID = retail_sales_arr_Values.getString("Close_SalesID");
//                    Close_TerminalID = retail_sales_arr_Values.getString("Close_TerminalID");
//                    CardDisc = retail_sales_arr_Values.getString("CardDisc");
//                    SPBI01 = retail_sales_arr_Values.getString("SPBI01");
//                    SPBI02 = retail_sales_arr_Values.getString("SPBI02");
//                    SPBI03 = retail_sales_arr_Values.getString("SPBI03");
//                    SPBI04 = retail_sales_arr_Values.getString("SPBI04");
//                    SPBI05 = retail_sales_arr_Values.getString("SPBI05");
//                    SPD01 = retail_sales_arr_Values.getString("SPD01");
//                    SPD02 = retail_sales_arr_Values.getString("SPD02");
//                    SPD03 = retail_sales_arr_Values.getString("SPD03");
//                    SPD04 = retail_sales_arr_Values.getString("SPD04");
//                    SPD05 = retail_sales_arr_Values.getString("SPD05");
//                    SPI01 = retail_sales_arr_Values.getString("SPI01");
//                    SPI02 = retail_sales_arr_Values.getString("SPI02");
//                    SPI03 = retail_sales_arr_Values.getString("SPI03");
//                    SPI04 = retail_sales_arr_Values.getString("SPI04");
//                    SPI05 = retail_sales_arr_Values.getString("SPI05");
//                    SPC01 = retail_sales_arr_Values.getString("SPC01");
//                    SPC02 = retail_sales_arr_Values.getString("SPC02");
//                    SPC03 = retail_sales_arr_Values.getString("SPC03");
//                    SPC04 = retail_sales_arr_Values.getString("SPC04");
//                    SPC05 = retail_sales_arr_Values.getString("SPC05");
//                    SPV01 = retail_sales_arr_Values.getString("SPV01");
//                    SPV02 = retail_sales_arr_Values.getString("SPV02");
//                    SPV03 = retail_sales_arr_Values.getString("SPV03");
//                    SPV04 = retail_sales_arr_Values.getString("SPV04");
//                    SPV05 = retail_sales_arr_Values.getString("SPV05");
//                    SPT01 = retail_sales_arr_Values.getString("SPT01");
//                    SPT02 = retail_sales_arr_Values.getString("SPT02");
//                    SPT03 = retail_sales_arr_Values.getString("SPT03");
//                    SPT04 = retail_sales_arr_Values.getString("SPT04");
//                    SPT05 = retail_sales_arr_Values.getString("SPT05");
//                    SDT01 = retail_sales_arr_Values.getString("SDT01");
//                    SDT02 = retail_sales_arr_Values.getString("SDT02");
//                    SDT03 = retail_sales_arr_Values.getString("SDT03");
//                    SDT04 = retail_sales_arr_Values.getString("SDT04");
//                    SDT05 = retail_sales_arr_Values.getString("SDT05");
//                    LastUser = retail_sales_arr_Values.getString("LastUser");
//                    LastUpdate = retail_sales_arr_Values.getString("LastUpdate");
//                    LockUser = retail_sales_arr_Values.getString("LockUser");
//                    LockUpdate = retail_sales_arr_Values.getString("LockUpdate");
//                    LockStatus = retail_sales_arr_Values.getString("LockStatus");
//                    RecordUpdate = retail_sales_arr_Values.getString("RecordUpdate");
//                    QueueStatus = retail_sales_arr_Values.getString("QueueStatus");
//                    SalesPayTtl = retail_sales_arr_Values.getString("SalesPayTtl");
//                    SalesBalTtl = retail_sales_arr_Values.getString("SalesBalTtl");
//                    SalesDeposit = retail_sales_arr_Values.getString("SalesDeposit");
//                    ChangeAmount = retail_sales_arr_Values.getString("ChangeAmount");
//                    TipsAmount = retail_sales_arr_Values.getString("TipsAmount");
//                    PaymentStatus = retail_sales_arr_Values.getString("PaymentStatus");
//                    DepositStatus = retail_sales_arr_Values.getString("DepositStatus");
//                    Close_RetailID = retail_sales_arr_Values.getString("Close_RetailID");
//                    CardAmt = retail_sales_arr_Values.getString("CardAmt");
//                    ZReadDate = retail_sales_arr_Values.getString("ZReadDate");
//                    TerminalID = retail_sales_arr_Values.getString("TerminalID");
//                    PendingSync = retail_sales_arr_Values.getString("PendingSync");
//                    RecordStatus = retail_sales_arr_Values.getString("RecordStatus");
//
//                    ZReadNo = retail_sales_arr_Values.getString("ZReadNo");
//                    Cursor c = DBFunc.Query("SELECT ID FROM OnlineBill_PAYMENT_PAYMENT WHERE SalesPaymentID = '"+SalesPaymentID+"'", false);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//                            String sql = "INSERT INTO OnlineBill_PAYMENT_PAYMENT " +
//                                    "(SalesPaymentID,SalesID,RetailID,PaymentID,PaymentReference,ReceiptRemarks,OthersPayment,OthersPaymentRef,Close_SalesID," +
//                                    "Close_TerminalID,CardDisc,SPBI01,SPBI02,SPBI03,SPBI04,SPBI05," +
//                                    "SPD01,SPD02,SPD03,SPD04,SPD05,SPI01,SPI02,SPI03,SPI04,SPI05,SPC01,SPC02,SPC03," +
//                                    " SPC04,SPC05,SPV01,SPV02,SPV03,SPV04,SPV05,SPT01,SPT02,SPT03,SPT04,SPT05,SDT01,SDT02,SDT03,SDT04,SDT05,LastUser,LastUpdate,LockUser,LockUpdate,LockStatus,RecordUpdate,QueueStatus,SalesPayTtl,SalesBalTtl,SalesDeposit,ChangeAmount,TipsAmount,PaymentStatus,DepositStatus,Close_RetailID,CardAmt,ZReadDate,TerminalID,RecordStatus,ZReadNo,DateTime) VALUES " +
//                                    "('"+SalesPaymentID+"','"+SalesID+"','"+RetailID+"','"+PaymentID+"','"+PaymentReference+"','"+ReceiptRemarks+"','"+OthersPayment+"','"+OthersPaymentRef+"','"+Close_SalesID+"','"+Close_TerminalID+"','"+CardDisc+"','"+SPBI01+"','"+SPBI02+"','"+SPBI03+"','"+SPBI04+"','"+SPBI05+"','"+SPD01+"','"+SPD02+"','"+SPD03+"','"+SPD04+"','"+SPD05+"','"+SPI01+"','"+SPI02+"','"+SPI03+"','"+SPI04+"','"+SPI05+"','"+SPC01+"','"+SPC02+"','"+SPC03+"','"+SPC04+"','"+SPC05+"','"+SPV01+"','"+SPV02+"','"+SPV03+"','"+SPV04+"','"+SPV05+"','"+SPT01+"','"+SPT02+"','"+SPT03+"','"+SPT04+"','"+SPT05+"','"+SDT01+"','"+SDT02+"','"+SDT03+"','"+SDT04+"','"+SDT05+"','"+LastUser+"','"+LastUpdate+"','"+LockUser+"','"+LockUpdate+"','"+LockStatus+"','"+RecordUpdate+"','"+QueueStatus+"','"+SalesPayTtl+"','"+SalesBalTtl+"','"+SalesDeposit+"','"+ChangeAmount+"','"+TipsAmount+"','"+PaymentStatus+"','"+DepositStatus+"','"+Close_RetailID+"','"+CardAmt+"','"+ZReadDate+"','"+TerminalID+"','"+RecordStatus+"','"+ZReadNo+"',"+
//                                    "" + System.currentTimeMillis() + ")";

//
//
//                            DBFunc.ExecQuery(sql, false);
//
//                            CashLayoutActivity.SaveBillPayment(ZReadNo,Double.valueOf(SalesBalTtl),
//                                    total_ItemQty,
//                                    Double.valueOf(SalesPayTtl),
//                                    0.0,
//                                    PaymentID,
//                                    "Cash","0.00",
//                                    String.valueOf(SalesPayTtl),getContext());
//                        }
//                        c.close();
//                    }
//                }
//                UpdateStatusForOnlineOrder();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private void UpdateStatusForOnlineOrder() {
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//        final String finalCompany_code = company_code;
//        final String finalRetails_ID = retail_ID;
//        final String finalterminal_ID = terminal_id;
//        final String finalsaleno = SalesNo;
//        final JSONObject jsonObject = new JSONObject();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//                        String xmlString = response;
//                        Document xmlparse = null;
//                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        for (int i = 0; i < parse.getElementsByTagName("UpdateStatusForOnlineOrderResponse").getLength(); i++) {
//                            UpdateStatusForOnlineOrderResponse = (parse.getElementsByTagName("UpdateStatusForOnlineOrderResponse").getLength() > 0)
//                                    ? parse.getElementsByTagName("UpdateStatusForOnlineOrderResponse").item(i).getTextContent() : " ";
//                        }
//
//                        JSONObject obj = null;
//                        try {
//                            obj = new JSONObject(UpdateStatusForOnlineOrderResponse);
//                            //"Status":"Success"
//                            String Status = obj.getString("Status");
//                            if (Status.equals("Success")){
//                                Date datetime = new Date(System.currentTimeMillis());
//                                CashLayoutActivity.PrintKP(SalesNo,SalesID,SalesDate);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//
//                        // Now you can use any deserializer to make sense of data
////                                JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
//                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
//                        "  <soap:Body>\n" +
//                        "    <UpdateStatusForOnlineOrder xmlns=\"http://tempuri.org/\">\n" +
//                        "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
//                        "      <retailid>"+finalRetails_ID+"</retailid>\n" +
//                        "      <terminalid>"+finalterminal_ID+"</terminalid>\n" +
//                        "      <salesno>"+finalsaleno+"</salesno>\n" +
//                        "    </UpdateStatusForOnlineOrder>\n" +
//                        "  </soap:Body>\n" +
//                        "</soap:Envelope>";
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue.add(stringRequest);
//        // }
//    }
//    private class LongOperation extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            // getDataFromSqlite();
//            GetSalesForOrdering(getContext());
//            //Hold();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            //Toast.makeText(getContext(), "DOne", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
//        @Override
//        protected void onProgressUpdate(Void... values) {}
//    }
//
//    ArrayList<Integer> MID = new ArrayList<Integer>();
//    private void Hold() {
//        Double total_price = 0.0;
//        Cursor config_values_pro_item = DBFunc.Query("SELECT ItemName,ItemID,ItemPrice,TransNo,ItemQty,ParentItemID_ADDON" +
//                " FROM OnlineBill_HOLD_ITEMS", false);
//        Integer Bill__ID = 0;
//        if (config_values_pro_item != null) {
//            while (config_values_pro_item.moveToNext()) {
//                ItemName = config_values_pro_item.getString(0);
//                IttemID = config_values_pro_item.getString(1);
//                ItemPrice = config_values_pro_item.getString(2);
//                TransNo = config_values_pro_item.getString(3);
//                ItemQty = config_values_pro_item.getString(4);
//                ParentItemID_ADDON = config_values_pro_item.getString(5);
//
//                Integer ID = 0;
//                String ModifiersID = "0";
//                if (ParentItemID_ADDON.length() > 0){
//                    Cursor c = DBFunc.Query("SELECT ID " +
//                            " FROM PLU where UUID = '"+IttemID+"'", true);
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            ID = c.getInt(0);
//                        }
//                        c.close();
//                    }
//
//                    c = DBFunc.Query("SELECT Modifiers " +
//                            " FROM BillPLU where PLUID = '"+ID+"'", false);
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            ModifiersID = c.getString(0);
//                        }
//                        c.close();
//                    }
//                    Integer modiID = 0;
//                    c = DBFunc.Query("SELECT ID FROM ProductModifier Where Name = '"+ItemName+"'",true);
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            if (!c.isNull(0)) {
//                                modiID = c.getInt(0);
//                            }
//                        }
//                        c.close();
//                    }
//                    if (ModifiersID.split(":")[0].equals(String.valueOf(modiID))){
//
//                    }else {
//                        String str_modiID = ModifiersID + ":" +modiID;
//                        String sql = "SELECT BillNo FROM Bill WHERE TransNo = '" + TransNo + "'";

//
//                        c = DBFunc.Query(sql, false);
//                        if (c != null) {
//                            if (c.moveToNext()) {
//                                Bill__ID = c.getInt(0);
//                            }
//                            c.close();
//                        }
//                        sql = "UPDATE BillPLU SET Modifiers = '" + str_modiID + "' WHERE PLUID = " + ID + " and BillNo = "+Bill__ID;

//                        DBFunc.ExecQuery(sql, false);
//                    }
//
//                }
//                Cursor c = DBFunc.Query("SELECT vchQueueNo,CreateTime,LastUpdate,CashierID,intTableNo " +
//                        " FROM OnlineBill_HOLD where TransNo = '"+TransNo+"'", false);

//                if (c != null) {
//                    if (c.moveToNext()) {
//                        vchQueueNo = c.getString(0);
//                        CreateTime = c.getString(1);
//                        LastUpdate = c.getString(2);
//                        CashierID = c.getString(3);
//                        intTableNo = c.getString(4);
//                    }
//                    c.close();
//                }else {
//                    vchQueueNo = "0";
//                    CreateTime = "0";
//                    LastUpdate = "0";
//                    CashierID = "0";
//                    intTableNo = "0";
//                }
//                if (ItemQty.equals("0") && ItemPrice.equals("0")) {
//                    AddNewProductActivity.SaveModifier(ItemName,ItemPrice);
//
//
//                }else {
//                    String sql = "SELECT BillNo FROM Bill WHERE TransNo = '" + TransNo + "'";
//                    c = DBFunc.Query(sql, false);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//
//                            int latestID = 0;
//                            c = DBFunc.Query("SELECT MAX(BillNo) FROM Bill", false);
//                            if (c != null) {
//                                if (c.moveToNext()) {
//                                    if (!c.isNull(0)) {
//                                        latestID = c.getInt(0);
//                                    }
//                                }
//                                c.close();
//                            }
//                            DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, OnlineOrderBill,TransNo) VALUES " +
//                                    "(" + System.currentTimeMillis() + ",'" + CashierID + "', " +
//                                    Allocator.cashierID + ", '" + latestID + 1 + "','ON','" + TransNo + "')", false);
//
//                        }
//                        c.close();
//                    }
//                    BitmapDrawable drawable = null;
//
//                    Bitmap icon = BitmapFactory.decodeResource(getResources(),
//                            R.drawable.black_photo);
//                    drawable = new BitmapDrawable(getResources(), icon);
//                    Bitmap bitmap = drawable.getBitmap();
//                    sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";

//                    c = DBFunc.Query(sql, true);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//                            sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,ProductVariant,ProductModifiers," +
//                                    "AllowNameQuickEdit,AllowPriceQuickEdit,ProductCategoryID,ProductCategoryName,DateTime,Condi_Seq) " +
//                                    "VALUES ('" + DBFunc.PurifyString(ItemName) + "'," +
//                                    "'" + IttemID + "'," +
//                                    "''," +
//                                    "" + 0 + "," +
//                                    ItemPrice + ",'" +
//                                    00000 + "','" + DBFunc.PurifyString(vchQueueNo) + "'," +
//                                    "'" + DBFunc.PurifyString(String.valueOf(AddNewProductActivity.BitMapToString(bitmap))) + "'," +
//                                    "'" + DBFunc.PurifyString("0") + "'," +
//                                    "'" + DBFunc.PurifyString("0") + "'," +
//                                    "" + Integer.parseInt("0") + "," +
//                                    "" + Integer.parseInt("0") + "," +
//                                    "'0'," +
//                                    "'0'," +
//                                    "" + System.currentTimeMillis() + "," +
//                                    000 + ")";

//                            DBFunc.ExecQuery(sql, true);
//                        }
//                        c.close();
//                        sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";

//                        c = DBFunc.Query(sql, true);
//                        if (c != null) {
//                            if (c.moveToNext()){
//                                total_price += Double.valueOf(ItemPrice);
//                                RecyclerViewAdapter.totalItems = 1;
//                                RecyclerViewAdapter.countSelectedArr = 1;
//                                RecyclerViewAdapter.sldNameArr.add(ItemName);
//                                RecyclerViewAdapter.sltPriceTotalArr.add(ItemPrice);
//                                RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(ItemQty));
//                                RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(total_price));
//                                RecyclerViewAdapter.sltBillDisArr.add("0");
//                                RecyclerViewAdapter.sldIDArr.add(c.getString(0));
//                                RecyclerViewAdapter.sltCategoryIDArr.add("0");
//                                RecyclerViewAdapter.sltCategoryNameArr.add("0");
////                                RecyclerViewAdapter.vchQueueNo.clear();
////                                RecyclerViewAdapter.intTableNo.clear();
//
//                                RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);

//                                RecyclerViewAdapter.intTableNo.add(intTableNo);
//                            }
//                        }
//                    }
//
//                    if (RecyclerViewAdapter.sldIDArr.size() > 0) {
//                        Integer totalItems = RecyclerViewAdapter.totalItems;
//                        ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
//                        ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                        ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
//                        ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                        ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
//                        ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
//                        ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
//                        ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                        ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
//                        Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
//                        String BillNo = TransNo;
//                        Integer qty = 1;
//                        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                        String paymenttype = "Cash";
//                        String status = "SALES";
//                        Double sub_total = 0.0;
//                        Double amount = 0.0;
//                        sql = "SELECT ID FROM ProductModifier WHERE Name = '" + ItemName + "'";

//                        c = DBFunc.Query(sql, true);
//                        if (c != null) {
//                            MID.clear();
//                            while (c.moveToNext()) {
//                                MID.add(c.getInt(0));
//                            }
//                        }
//                        //ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;


//                        sql = "SELECT idx FROM BillPLU WHERE Name = '"+ItemName+"' and BillNo = "+Bill__ID;

//                        c = DBFunc.Query(sql, false);
//                        if (c != null) {
//                            if (c.getCount() == 0) {
//                                CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                                        qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                                        sub_total, amount, paymenttype,MID,vchQueueNo,intTableNo);
//                            }
//                        }
//                    }
//                }
//
//            }
//            config_values_pro_item.close();
////            String sql = "SELECT idx FROM BillPLU WHERE Name = '" + ItemName + "'";
////            Log.i("sql__ddd", sql);
////            Cursor c = DBFunc.Query(sql, false);
////            if (c != null) {
////                if (c.getCount() == 0) {
////                    if (RecyclerViewAdapter.sldIDArr.size() > 0) {
////                        Integer totalItems = RecyclerViewAdapter.totalItems;
////                        ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
////                        ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
////                        ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
////                        ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
////                        ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
////                        ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
////                        ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
////                        Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
////                        String BillNo = TransNo;
////                        Integer qty = 1;
////                        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
////                        String paymenttype = "Cash";
////                        String status = "SALES";
////                        Double sub_total = 0.0;
////                        Double amount = 0.0;
////                        sql = "SELECT ID FROM ProductModifier WHERE Name = '" + ItemName + "'";
////                        Log.i("sql__ddd", sql);
////                        c = DBFunc.Query(sql, true);
////                        if (c != null) {
////                            MID.clear();
////                            while (c.moveToNext()) {
////                                MID.add(c.getInt(0));
////                            }
////                        }
////                        //ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;
////                        CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                                qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                                sub_total, amount, paymenttype,MID);
////                    }
////                }
////            }
//
//        }
//    }

    //show balance split dialog
    void ShowBalanceSplitDlg(final Integer BillID, final Context CurrentActivity, final String BalanceNo) {
//        if (BalanceNo == null) {// if balance not null
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 106, balance_title));
//            return;
//        }
//
//        if (BillCancel) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 140));
//            return;
//        }
//
//        if (BillPaid) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 141));
//            return;
//        }

        final List<Object[]> lst_main = new ArrayList<Object[]>();
        final List<List<Object[]>> lst_split = new ArrayList<List<Object[]>>();
        Cursor c = DBFunc.Query("SELECT idx, Name, Qty FROM BillPLU WHERE BillNo = " + BillID + " AND Cancel = 0 AND CondiPLUID IS NULL ORDER BY idx", false);

        int count = 0;

        while (c.moveToNext()) {
            lst_main.add(new Object[]{c.getInt(0), c.getString(1), c.getInt(2), null});

            // get list of condiment item
            Cursor condi = DBFunc.Query("SELECT idx, Name, Qty FROM BillPLU WHERE BillNo = " + BillID + " AND Cancel = 0 AND CondiPLUID = " + c.getInt(0) + " ORDER BY idx", false);
            while (condi.moveToNext()) {
                lst_main.add(new Object[]{condi.getInt(0), condi.getString(1), condi.getInt(2), c.getInt(0)});
            }
            condi.close();

            count++;

        }
        c.close();

        if (count < 2) {
            //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 144));
            lst_main.clear();
            return;
        }

        final Dialog dlg = new Dialog(CurrentActivity);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (CurrentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window window = dlg.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.RIGHT;
            window.setAttributes(wlp);
        }
        dlg.setContentView(R.layout.dlg_billsplit);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);

        final TableLayout tbl_main = (TableLayout) dlg.findViewById(R.id.tbl_main_itemlist);
        tbl_main.setTag(-1);
        final TableLayout tbl_split = (TableLayout) dlg.findViewById(R.id.tbl_split_itemlist);
        tbl_split.setTag(-1);

        final TextView lbl_1 = (TextView) dlg.findViewById(R.id.lbl_1);

        final Button btn_add = (Button) dlg.findViewById(R.id.btn_add);
        final Button btn_remove = (Button) dlg.findViewById(R.id.btn_remove);
        final Button btn_bill_prev = (Button) dlg.findViewById(R.id.btn_bill_prev);
        final Button btn_bill_next = (Button) dlg.findViewById(R.id.btn_bill_next);
        final Button btn_bill_add = (Button) dlg.findViewById(R.id.btn_bill_add);
        final Button btn_bill_del = (Button) dlg.findViewById(R.id.btn_bill_del);
        final Button btn_ok = (Button) dlg.findViewById(R.id.btn_ok);
        final Button btn_cancel = (Button) dlg.findViewById(R.id.btn_cancel);

        lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 222));
        btn_bill_add.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 223));
        btn_bill_del.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 224));
        btn_ok.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0));

        final TextView txt_billsplit_no = (TextView) dlg.findViewById(R.id.txt_billsplit_no);
        txt_billsplit_no.setTag(0);
        txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 414));
        btn_bill_next.setEnabled(false);
        btn_bill_prev.setEnabled(false);

        lst_split.add(new ArrayList<Object[]>());

        btn_bill_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int splitpos = (Integer) txt_billsplit_no.getTag();
                if (splitpos < lst_split.size() - 1) {
                    splitpos++;
                    txt_billsplit_no.setTag(splitpos);
                    txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (splitpos + 1), lst_split.size()));
                }

                if (splitpos == lst_split.size() - 1) {
                    btn_bill_next.setEnabled(false);
                }

                if (splitpos > 0) {
                    btn_bill_prev.setEnabled(true);
                } else {
                    btn_bill_prev.setEnabled(false);
                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                // refresh split table
                for (int i = 0; i < lst_split.get(splitpos).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    String txt = ((Integer) lst_split.get(splitpos).get(i)[2]) + "x " + ((String) lst_split.get(splitpos).get(i)[1]);
                    if (lst_split.get(splitpos).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }

                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }

            }
        });

        btn_bill_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int splitpos = (Integer) txt_billsplit_no.getTag();
                if (splitpos > 0) {
                    splitpos--;
                    txt_billsplit_no.setTag(splitpos);
                    txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (splitpos + 1), lst_split.size()));
                }

                if (splitpos == 0) {
                    btn_bill_prev.setEnabled(false);
                }

                if (splitpos < lst_split.size() - 1) {
                    btn_bill_next.setEnabled(true);
                } else {
                    btn_bill_next.setEnabled(false);
                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                // refresh split table
                for (int i = 0; i < lst_split.get(splitpos).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    String txt = ((Integer) lst_split.get(splitpos).get(i)[2]) + "x " + ((String) lst_split.get(splitpos).get(i)[1]);
                    if (lst_split.get(splitpos).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }

                    // tv.setText(((Integer)lst_split.get(splitpos).get(i)[2]) +
                    // "x " + ((String)lst_split.get(splitpos).get(i)[1]));
                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }
            }
        });

        btn_bill_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogBox dlg1 = new DialogBox(CurrentActivity);
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 416));

                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();
                        lst_split.add(new ArrayList<Object[]>());
                        txt_billsplit_no.setTag(lst_split.size() - 1);
                        txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (lst_split.size()), lst_split.size()));

                        btn_bill_prev.setEnabled(true);
                        tbl_split.setTag(-1);
                        tbl_split.removeAllViews();

                        if (tbl_split.getChildCount() == 0) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tv.setTextSize(MainActivity.billFontSize);
                            tv.setText(" ");
                            tr.addView(tv);
                            tbl_split.addView(tr);
                        }

                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();

            }
        });

        btn_bill_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lst_split.size() <= 1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 146));
                    return;
                }

                final DialogBox dlg1 = new DialogBox(CurrentActivity);
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 417));

                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();

                        int splitpos = (Integer) txt_billsplit_no.getTag();
                        for (Object[] obj : lst_split.get(splitpos)) {
                            lst_main.add(obj);
                        }

                        lst_split.remove(splitpos);
                        if (splitpos >= lst_split.size()) {
                            splitpos = lst_split.size() - 1;
                        }

                        txt_billsplit_no.setTag(splitpos);
                        txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (splitpos + 1), lst_split.size()));
                        if (splitpos > 0) {
                            btn_bill_prev.setEnabled(true);
                        } else {
                            btn_bill_prev.setEnabled(false);
                        }
                        if (splitpos < lst_split.size() - 1) {
                            btn_bill_next.setEnabled(true);
                        } else {
                            btn_bill_next.setEnabled(false);
                        }

                        tbl_main.setTag(-1);
                        tbl_main.removeAllViews();

                        for (int i = 0; i < lst_main.size(); i++) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tr.setTag(i);

                            String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
                            if (lst_main.get(i)[3] != null) {
                                tv.setText("  ++" + txt);
                            } else {
                                tv.setText(txt);
                            }
                            // tv.setText(((Integer)lst_main.get(i)[2])
                            // + "x " +
                            // ((String)lst_main.get(i)[1]));
                            tv.setPadding(15, 0, 0, 0);
                            tv.setTextSize(MainActivity.billFontSize);
                            tr.addView(tv);
                            tr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < tbl_main.getChildCount(); j++) {
                                        ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                                    }
                                    v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                                    tbl_main.setTag((Integer) v.getTag());
                                }
                            });

                            tbl_main.addView(tr);

                        }

                        tbl_split.setTag(-1);
                        tbl_split.removeAllViews();

                        // refresh split table
                        for (int i = 0; i < lst_split.get(splitpos).size(); i++) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tr.setTag(i);
                            String txt = ((Integer) lst_split.get(splitpos).get(i)[2]) + "x " + ((String) lst_split.get(splitpos).get(i)[1]);
                            if (lst_split.get(splitpos).get(i)[3] != null) {
                                tv.setText("  ++" + txt);
                            } else {
                                tv.setText(txt);
                            }
                            // tv.setText(((Integer)lst_split.get(splitpos).get(i)[2])
                            // + "x " +
                            // ((String)lst_split.get(splitpos).get(i)[1]));
                            tv.setPadding(15, 0, 0, 0);
                            tv.setTextSize(MainActivity.billFontSize);
                            tr.addView(tv);
                            tr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                        ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                                    }
                                    v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                                    tbl_split.setTag((Integer) v.getTag());
                                }
                            });

                            tbl_split.addView(tr);
                        }

                        if (tbl_main.getChildCount() == 0) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tv.setTextSize(MainActivity.billFontSize);
                            tv.setText(" ");
                            tr.addView(tv);
                            tbl_main.addView(tr);
                        }

                        if (tbl_split.getChildCount() == 0) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tv.setTextSize(MainActivity.billFontSize);
                            tv.setText(" ");
                            tr.addView(tv);
                            tbl_split.addView(tr);
                        }

                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add into bill

                if (lst_main.size() <= 1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 147));
                    return;
                }

                int selected_item_main = (Integer) tbl_main.getTag();

                if (selected_item_main == -1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 117));
                    return;
                }

                int selectedsplitbill = (Integer) txt_billsplit_no.getTag();

                int pluidx = 0;

                if (lst_main.get(selected_item_main)[3] == null) {
                    pluidx = (Integer) lst_main.get(selected_item_main)[0];
                } else {// if this is condiment item then we will change the
                    // selected index to main plu item index
                    pluidx = (Integer) lst_main.get(selected_item_main)[3];
                    boolean exist = false;
                    for (int i = 0; i < lst_main.size(); i++) {
                        if ((Integer) lst_main.get(i)[0] == pluidx) {
                            selected_item_main = i;
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        DialogBox dlg1 = new DialogBox(CurrentActivity);
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 149));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

                lst_split.get(selectedsplitbill).add(lst_main.get(selected_item_main));
                lst_main.remove(selected_item_main);

                // check for any condiment item that belongs to main pluidx
                while (true) {
                    boolean hasCondi = false;
                    for (int i = 0; i < lst_main.size(); i++) {
                        if (lst_main.get(i)[3] != null) {
                            if ((Integer) lst_main.get(i)[3] == pluidx) {
                                lst_split.get(selectedsplitbill).add(lst_main.get(i));
                                lst_main.remove(i);
                                hasCondi = true;
                                break;
                            }
                        }
                    }
                    if (!hasCondi) {
                        break;
                    }
                }

                tbl_main.setTag(-1);
                tbl_main.removeAllViews();

                for (int i = 0; i < lst_main.size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_main.get(i)[2]) + "x " +
                    // ((String)lst_main.get(i)[1]));
                    String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
                    if (lst_main.get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }
                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_main.getChildCount(); j++) {
                                ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_main.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_main.addView(tr);

                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_split.get(selectedsplitbill).get(i)[2])
                    // + "x " +
                    // ((String)lst_split.get(selectedsplitbill).get(i)[1]));
                    String txt = ((Integer) lst_split.get(selectedsplitbill).get(i)[2]) + "x " + ((String) lst_split.get(selectedsplitbill).get(i)[1]);
                    if (lst_split.get(selectedsplitbill).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }

                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_main.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_main.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }

            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_item_split = (Integer) tbl_split.getTag();
                if (selected_item_split == -1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 117));
                    return;
                }
                int selectedsplitbill = (Integer) txt_billsplit_no.getTag();

                int pluidx = 0;

                if (lst_split.get(selectedsplitbill).get(selected_item_split)[3] == null) {
                    pluidx = (Integer) lst_split.get(selectedsplitbill).get(selected_item_split)[0];
                } else {// if this is condiment item then we will change the
                    // selected index to main plu item index
                    pluidx = (Integer) lst_split.get(selectedsplitbill).get(selected_item_split)[3];
                    boolean exist = false;

                    for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                        if ((Integer) lst_split.get(selectedsplitbill).get(i)[0] == pluidx) {
                            selected_item_split = i;
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        DialogBox dlg1 = new DialogBox(CurrentActivity);
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 149));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

                lst_main.add(lst_split.get(selectedsplitbill).get(selected_item_split));
                lst_split.get(selectedsplitbill).remove(selected_item_split);

                while (true) {
                    boolean hasCondi = false;
                    for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                        if (lst_split.get(selectedsplitbill).get(i)[3] != null) {
                            if ((Integer) lst_split.get(selectedsplitbill).get(i)[3] == pluidx) {
                                lst_main.add(lst_split.get(selectedsplitbill).get(i));
                                lst_split.get(selectedsplitbill).remove(i);
                                // lst_split.get(selectedsplitbill).add(lst_main.get(i));
                                // lst_main.remove(i);
                                hasCondi = true;
                                break;
                            }
                        }
                    }
                    if (!hasCondi) {
                        break;
                    }
                }

                tbl_main.setTag(-1);
                tbl_main.removeAllViews();

                for (int i = 0; i < lst_main.size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_main.get(i)[2]) + "x " +
                    // ((String)lst_main.get(i)[1]));
                    String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
                    if (lst_main.get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }
                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_main.getChildCount(); j++) {
                                ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_main.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_main.addView(tr);

                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_split.get(selectedsplitbill).get(i)[2])
                    // + "x " +
                    // ((String)lst_split.get(selectedsplitbill).get(i)[1]));
                    String txt = ((Integer) lst_split.get(selectedsplitbill).get(i)[2]) + "x " + ((String) lst_split.get(selectedsplitbill).get(i)[1]);
                    if (lst_split.get(selectedsplitbill).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }
                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_main.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_main.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityPosCashier.this, "mcsbtn_test8", Toast.LENGTH_LONG).show();
                boolean splitok = false;
                for (List<Object[]> obj : lst_split) {
                    if (obj.size() > 0) {
                        splitok = true;
                        break;
                    }
                }

                if (!splitok) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 150));
                    return;
                }

                final DialogBox dlg1 = new DialogBox(v.getContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 418));
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();

                        // TODO: cashier name
                        // String cashiername = Allocator.cashierName;
                        long time = System.currentTimeMillis();

                        for (List<Object[]> obj : lst_split) {
                            if (obj.size() > 0) {
                                DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo) VALUES (" + time + ",'" + DBFunc.PurifyString(Allocator.cashierName) + "', " + Allocator.cashierID + ", '" + DBFunc.PurifyString(BalanceNo) + "')", false);
                                Cursor c = DBFunc.Query("SELECT seq FROM sqlite_sequence WHERE name = 'Bill'", false);
                                if (c.moveToNext()) {
                                    int billid = c.getInt(0);
                                    for (Object[] plu : obj) {
                                        DBFunc.ExecQuery("UPDATE BillPLU SET BillNo = " + billid + " WHERE idx = " + (Integer) plu[0], false);
                                    }

                                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), DBFunc.PurifyString("POS -> Balance Split -> Bill: " + BillID + "/" + billid));

                                } else {
                                    DialogBox dlg1 = new DialogBox(CurrentActivity);
                                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 312));
                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                    dlg1.show();
                                    // Toast.makeText(CurrentActivity,
                                    // "DB Error! Failed to split bill!",
                                    // Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                c.close();
                            }
                        }
                        lst_split.clear();
                        lst_main.clear();
                        dlg.dismiss();
//                        // if(!paymentOnlyKP){
//                        CloseBalance(false);
//                        // }
                    }
                });
                dlg1.show();
            }

        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final DialogBox dlg1 = new DialogBox(v.getContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 419));
                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();
                        lst_main.clear();
                        lst_split.clear();
                        dlg.dismiss();
                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();

            }

        });

        for (int i = 0; i < lst_main.size(); i++) {
            TableRow tr = new TableRow(dlg.getContext());
            TextView tv = new TextView(dlg.getContext());
            tr.setTag(i);
            // tv.setText(((Integer)lst_main.get(i)[2]) + "x " +
            // ((String)lst_main.get(i)[1]));
            String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
            if (lst_main.get(i)[3] != null) {
                tv.setText("  ++" + txt);
            } else {
                tv.setText(txt);
            }
            tv.setPadding(15, 0, 0, 0);
            tv.setTextSize(MainActivity.billFontSize);
            tr.addView(tv);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < tbl_main.getChildCount(); j++) {
                        ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                    }
                    v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                    tbl_main.setTag((Integer) v.getTag());
                }
            });

            tbl_main.addView(tr);
        }

        if (tbl_main.getChildCount() == 0) {
            TableRow tr = new TableRow(dlg.getContext());
            TextView tv = new TextView(dlg.getContext());
            tv.setTextSize(MainActivity.billFontSize);
            tv.setText(" ");
            tr.addView(tv);
            tbl_main.addView(tr);
        }

        if (tbl_split.getChildCount() == 0) {
            TableRow tr = new TableRow(dlg.getContext());
            TextView tv = new TextView(dlg.getContext());
            tv.setTextSize(MainActivity.billFontSize);
            tv.setText(" ");
            tr.addView(tv);
            tbl_split.addView(tr);
        }

        dlg.show();
        dlg.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


    //show balance merge dialog for user to choose
    @SuppressWarnings("deprecation")
    void ShowBalanceMergeList(final Integer BillID, Context CurrentActivity) {

//        if (BalanceNo == null) {// if balance not null
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 106, balance_title));
//            return;
//        }
//
//        if (BillCancel) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 140));
//            return;
//        }
//
//        if (BillPaid) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 141));
//            return;
//        }

        final Dialog dlg = new Dialog(CurrentActivity);
        if (CurrentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window window = dlg.getWindow();

            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.RIGHT;
            window.setAttributes(wlp);
        }

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.dlg_receipt_viewer);

        dlg.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        final LinearLayout laylist = (LinearLayout) dlg.findViewById(R.id.lay_rcptview_list);

        Button btn_close = (Button) dlg.findViewById(R.id.btn_rcpview_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        Button btn_current = (Button) dlg.findViewById(R.id.btn_rcpview_current);

        btn_current.setVisibility(View.GONE);

        Button btn_searchdate = (Button) dlg.findViewById(R.id.btn_rcpview_datetime);
        btn_searchdate.setVisibility(View.GONE);

        TextView tv1 = (TextView) dlg.findViewById(R.id.txt_hint);
        tv1.setVisibility(View.VISIBLE);
        //tv1.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 151, balance_title));
        tv1.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 151, "AAAAAAAAAAA"));

        Cursor c = DBFunc.Query("SELECT BillNo, OpenDateTime, CloseDateTime, Cashier, Round, Cancel, BalNo FROM Bill Where BalNo IS NOT NULL AND CloseDateTime IS NULL AND Cancel = 0 AND BillNo != " + BillID, false);
        if (c == null) {
            DialogBox dlg1 = new DialogBox(CurrentActivity);
            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
            dlg1.show();
            // Toast.makeText(CurrentActivity,
            // "Failed to connect to DB!",Toast.LENGTH_SHORT).show();
            return;
        }

        while (c.moveToNext()) {
            Button btn = new Button(dlg.getContext());

            Date printTime = new Date(c.getLong(1));
            if (!c.isNull(2)) {
                printTime = new Date(c.getLong(2));
            }

            // String cashier = c.getString(3);

            String billNumber = "";
            if ((c.getInt(0) + "").length() < Constraints.Zeroes.length()) {
                billNumber += Constraints.Zeroes.substring(0, Constraints.Zeroes.length() - (c.getInt(0) + "").length()) + c.getInt(0);
            } else {
                billNumber += (c.getInt(0) + "");
            }

            if (!c.isNull(6)) {
                //billNumber += " (" + balance_title + ":" + c.getString(6) + ")";
                billNumber += " (" + "AAAA" + ":" + c.getString(6) + ")";
            }

            String otime = String.format("%02d-%02d-%4d  ", printTime.getDate(), (printTime.getMonth() + 1), (printTime.getYear() + 1900));
            otime += String.format("%02d:%02d.%02d", printTime.getHours(), printTime.getMinutes(), printTime.getSeconds());

            double totalamt = 0;
            double totaltax = 0;
            Cursor data = DBFunc.Query("SELECT idx,Amount,Qty FROM BillPLU WHERE BillNo = " + c.getInt(0) + " AND Cancel = 0 ORDER BY idx ASC", false);
            if (data != null) {
                while (data.moveToNext()) {
                    double itemprice = data.getInt(2) * data.getDouble(1);
                    Cursor plutax = DBFunc.Query("SELECT TaxID, Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + data.getInt(0) + " ORDER BY Seq ASC", false);
                    if (plutax != null) {
                        double _tax = 0;
                        while (plutax.moveToNext()) {
                            if (plutax.getInt(2) == 0) {// not include(add on
                                // tax)
                                _tax = (itemprice * (plutax.getDouble(1) / 100f));
                                itemprice += _tax;
                            } else if (plutax.getInt(2) == 1) {// is inclusive
                                // (VAT)
                                _tax = (itemprice - (itemprice / (1f + (plutax.getDouble(1) / 100f))));
                                itemprice = (itemprice / (1f + (plutax.getDouble(1) / 100f)));
                            }

                        }
                        totaltax += _tax;
                        plutax.close();
                    }
                    totalamt += itemprice;
                }
                data.close();
            }

            if (c.isNull(2)) {
                if (c.getInt(5) == 1) {
                    btn.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 404, billNumber));
                } else {
                    btn.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 403, billNumber));
                }
            } else {
                if (c.getInt(5) == 1) {
                    btn.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 406, billNumber));
                } else {
                    btn.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 405, billNumber));
                }
            }

            btn.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 407, btn.getText().toString(), otime, String.format("%.2f", totalamt), String.format("%.2f", totaltax)));

            btn.setTag(c.getInt(0));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dlg.dismiss();
                    // SelectBill((Integer)v.getTag());
                    final int oldbillid = (Integer) v.getTag();
                    final DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 420, oldbillid, BillID));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dlg1.dismiss();
                            Cursor c = DBFunc.Query("SELECT BalNo FROM Bill WHERE BillNo = " + oldbillid, false);
                            String oldbal = "";
                            if (c.moveToNext()) {
                                oldbal = c.getString(0);
                            }
                            c.close();

                            long time = System.currentTimeMillis();

                            DBFunc.ExecQuery("UPDATE Bill SET MergeBill = " + BillID + ", CloseDateTime = " + time + " WHERE BillNo = " + oldbillid, false);

                            //PrintMoveKP(oldbillid, new Date(time), oldbal, BalanceNo);
                            DBFunc.ExecQuery("UPDATE BillPLU SET BillNo = " + BillID + " WHERE BillNo = " + oldbillid, false);

                            String newbill = "" + BillID;

//                            // if(!paymentOnlyKP){
//                            CloseBalance(false);
//                            // }

                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), DBFunc.PurifyString("POS -> Balance Merge -> Bill: " + oldbillid + "->" + newbill));
                            // UpdateItemList();
                            // ShowErrorDialog(balance_title+" merged!");
                            DialogBox dlg2 = new DialogBox(v.getContext());
                            dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg2.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                            dlg2.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 152, oldbillid, newbill));
                            dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg2.show();
                            dlg.dismiss();
                        }
                    });

                    dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                    dlg1.show();

                }
            });

            laylist.addView(btn);
        }
        c.close();

        if (laylist.getChildCount() == 0) {
            TextView tv = new TextView(dlg.getContext());
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);
            tv.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 153, balance_title));
            laylist.addView(tv);
        }
        dlg.show();
    }
}