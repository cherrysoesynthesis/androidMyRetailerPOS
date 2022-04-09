package com.dcs.myretailer.app;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dcs.myretailer.app.BillList.BillListModel;
import com.dcs.myretailer.app.Cashier.BillData;
import com.dcs.myretailer.app.Cashier.ClearBillSheetFragment;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.RecyclerAdapter;
import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.Query.Query;
import com.android.volley.RequestQueue;
import com.jmedeisis.draglinearlayout.DragLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OnlineOrderClosedBillListMainPageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    //private List<BillDetails> lstBillDetails = new ArrayList<BillDetails>();
    private List<BillListModel> lstBill = new ArrayList<BillListModel>();
    public static String str_newText = "";
    Handler mHandler;
    Handler mHandler_online_order;
    public static String status_on = "0";
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
    String TransNo = "0";
    String TotalDue = "0";
    String TotalGST = "0";
    String TotalDisc = "0";
    String TransDate = "0";
    String CreateTime = "0";
    String TotalQty = "0";
    String CashierID = "0";
    String MemberID = "0";
    String isNewCust = "0";
    String SalesPersonID = "0";
    String CommID = "0";
    String CommPerc = "0";
    String vchQueueNo = "0";
    String intTableNo = "0";
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
    String ItemName = "0";
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

    String PostSalesResponse = "";
    String GetDetailsForOrderResult = "";
    String GetSalesForOrderingResponse = "0";
    String UpdateStatusForOnlineOrderResponse = "";
    String RecordNo = "0";
    String LineNo = "0";
    String ItemQty = "0";
    String ItemPrice = "0";
    String ItemTotal = "0";
    String ItemGST = "0";
    String ItemDiscType = "0";
    String ItemDisc1 = "0";
    String ItemDisc2 = "0";
    String ItemDisc3 = "0";
    String IttemID = "0";
    String ItemBarcode = "0";
    String ItemUOM = "0";
    String ItemGSTInEx = "0";
    String ItemCost = "0";
    String ItemActQty = "0";
    String ItemUOMID = "0";
    String ItemGroupDisc = "0";
    String ItemSKU = "0";
    String SupplierID = "0";
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
    String ParentItemID_ADDON = "";
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
    String LastUpdate = "0";
    String LockUser = "0";
    String LockUpdate = "0";
    String LockStatus = "0";
    String RecordStatus = "0";
    String RecordUpdate = "0";
    String QueueStatus = "0";
    String retail_ID = "";
    String terminal_id = "";
    private String url = "";
    String company_code = "";
    BitmapDrawable drawable = null;
    TextView txtemptyMsg;
    Bitmap bitmap;
    RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.billlist_mainpage_list, container, false);

        getBillAll();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtemptyMsg = (TextView) view.findViewById(R.id.txtemptyMsg);

        //RecyclerViewAdapter.count = 0;

        if (lstBill.size() > 0) {
            txtemptyMsg.setVisibility(View.GONE);
            adapter = new RecyclerAdapter(getContext(), lstBill);
            recyclerView.setAdapter(adapter);
         }else {
            txtemptyMsg.setVisibility(View.VISIBLE);
            txtemptyMsg.setText("Empty Data");
            txtemptyMsg.setTextSize(MainActivity.billFontSize);
        }

//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new
                DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        mHandler_online_order = new Handler();
        //m_Runnable.run();
        m_Runnable_online_order.run();
        return view;
    }
    private void getBillAll() {
        Cursor c = null;

        if (str_newText.length() > 0){
            c = Query.SearchOnlinePaymentBill(str_newText);
        }else{
            c = Query.SearchOnlinePaymentBill("");
        }
        if (c != null) {
            lstBill.clear();
            while (c.moveToNext()) {
                String billNo = c.getString(0);
                String totalItems = c.getString(1);
                Double totalAmount = c.getDouble(2);
                String dateTime = c.getString(3);
                Integer ID = c.getInt(4);
                vchQueueNo = c.getString(5);
                intTableNo = c.getString(6);
                String status = c.getString(7);
                String billID = c.getString(8);

//                lstBillDetails.add(new BillDetails(billNo,
//                        totalItems,
//                        String.valueOf(totalAmount), dateTime, ID, status,
//                        vchQueueNo, intTableNo));

//                String billID, String billNo, String STATUS, String totalItems, String date,
//                        String tableNo, String queueNo, String onlineOrderBill, String totalAmount, Integer dateTime
                lstBill.add(new BillListModel(billID,billNo,status,
                        totalItems,dateTime,intTableNo,vchQueueNo,"ON",
                        String.valueOf(totalAmount),0));
            }
            c.close();
        }
    }

    private final Runnable m_Runnable_online_order = new Runnable() {
        public void run() {
            if (getContext() != null) {

                    getBillAll();
                    RecyclerViewAdapter.count = 0;
                    adapter = new RecyclerAdapter(getContext(), lstBill);
                    recyclerView.setAdapter(adapter);
                    mHandler_online_order.postDelayed(m_Runnable_online_order, 5000);

            }
        }

    };

    private List<String> createList(int n) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            list.add("View " + i);
        }

        return list;
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<BillData> dataList;

        public ListAdapter(ArrayList<BillData> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView table_name,table_name1,table_name2,table_name3,table_name4;
            TextView occupied_name,occupied_name1,occupied_name2,occupied_name3,occupied_name4;
            TextView pax_name,pax_name1,pax_name2,pax_name3,pax_name4;
            DragLinearLayout dragLinearLayout;

            public ViewHolder(View itemView)
            {
                super(itemView);
//                this.table_name = (TextView) itemView.findViewById(R.id.table_name);
//                this.occupied_name = (TextView) itemView.findViewById(R.id.occupied_name);
//                this.pax_name = (TextView) itemView.findViewById(R.id.pax_name);
//                this.table_name1 = (TextView) itemView.findViewById(R.id.table_name_1);
//                this.occupied_name1 = (TextView) itemView.findViewById(R.id.occupied_name_1);
//                this.pax_name1 = (TextView) itemView.findViewById(R.id.pax_name_1);
//                this.table_name2 = (TextView) itemView.findViewById(R.id.table_name_2);
//                this.occupied_name2 = (TextView) itemView.findViewById(R.id.occupied_name_2);
//                this.pax_name2 = (TextView) itemView.findViewById(R.id.pax_name_2);
//                this.table_name3 = (TextView) itemView.findViewById(R.id.table_name_3);
//                this.occupied_name3 = (TextView) itemView.findViewById(R.id.occupied_name_3);
//                this.pax_name3 = (TextView) itemView.findViewById(R.id.pax_name_3);
//                this.table_name4 = (TextView) itemView.findViewById(R.id.table_name_4);
//                this.occupied_name4 = (TextView) itemView.findViewById(R.id.occupied_name_4);
//                this.pax_name4 = (TextView) itemView.findViewById(R.id.pax_name_4);
//
////                itemView.findViewById(R.id.container).setTag("DRAGGABLE TEXTVIEW");
////                itemView.findViewById(R.id.container).setOnLongClickListener(this);
////                itemView.findViewById(R.id.container).setOnDragListener(this);
//
//                itemView.findViewById(R.id.layout_1).setTag("DRAGGABLE TEXTVIEW");
//                itemView.findViewById(R.id.layout_1).setOnLongClickListener(this);
//                itemView.findViewById(R.id.layout_1).setOnDragListener(this);
//
//                DragLinearLayout dragLinearLayout = (DragLinearLayout) itemView.findViewById(R.id.container);
//                // set all children draggable except the first (the header)
//                for(int i = 0; i < dragLinearLayout.getChildCount(); i++) {
//                    View child = dragLinearLayout.getChildAt(i);
//                    dragLinearLayout.setViewDraggable(child, child); // the child is its own drag handle
//                }
            }
//            @Override
//            public boolean onLongClick(View v) {
//                // Create a new ClipData.Item from the ImageView object's tag
//                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
//                // Create a new ClipData using the tag as a label, the plain text MIME type, and
//                // the already-created item. This will create a new ClipDescription object within the
//                // ClipData, and set its MIME type entry to "text/plain"
//                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//                Log.i("v.getTag()_", String.valueOf(v.getTag()));
//                Log.i("mimeTypes_", Arrays.toString(mimeTypes));
//                Log.i("item_", String.valueOf(item));
//                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
//                // Instantiates the drag shadow builder.
//                View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
//                // Starts the drag
//                v.startDrag(data        // data to be dragged
//                        , dragshadow   // drag shadow builder
//                        , v           // local data about the drag and drop operation
//                        , 0          // flags (not currently used, set to 0)
//                );
//                return true;
//            }
            // This is the method that the system calls when it dispatches a drag event to the listener.
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                Log.i("View__",v.toString());
//                // Defines a variable to store the action type for the incoming event
//                int action = event.getAction();
//                // Handles each of the expected events
//                switch (action) {
//
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        // Determines if this View can accept the dragged data
//                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//                            // if you want to apply color when drag started to your view you can uncomment below lines
//                            // to give any color tint to the View to indicate that it can accept data.
//                            // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
//                            // Invalidate the view to force a redraw in the new tint
//                            //  v.invalidate();
//                            // returns true to indicate that the View can accept the dragged data.
//                            return true;
//                        }
//                        // Returns false. During the current drag and drop operation, this View will
//                        // not receive events again until ACTION_DRAG_ENDED is sent.
//                        return false;
//
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
//                        //v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
//                        // Invalidate the view to force a redraw in the new tint
//                        v.invalidate();
//                        return true;
//
//                    case DragEvent.ACTION_DRAG_LOCATION:
//                        // Ignore the event
//                        return true;
//
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        // Re-sets the color tint to blue. Returns true; the return value is ignored.
//                        // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
//                        //It will clear a color filter .
//                        //v.getBackground().clearColorFilter();
//                        // Invalidate the view to force a redraw in the new tint
//                        v.invalidate();
//                        return true;
//
//                    case DragEvent.ACTION_DROP:
//                        // Gets the item containing the dragged data
//                        ClipData.Item item = event.getClipData().getItemAt(0);
//                        // Gets the text data from the item.
//                        String dragData = item.getText().toString();
//                        // Displays a message containing the dragged data.
//                        Toast.makeText(getContext(), "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
//                        // Turns off any color tints
//                        //v.getBackground().clearColorFilter();
//                        // Invalidates the view to force a redraw
//                        v.invalidate();
//
//                        View vw = (View) event.getLocalState();
//                        ViewGroup owner = (ViewGroup) vw.getParent();
//                        owner.removeView(vw); //remove the dragged view
//                        //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
//                        LinearLayout container = (LinearLayout) v;
//                        container.addView(vw);//Add the dragged view
//                        vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
//                        // Returns true. DragEvent.getResult() will return true.
//                        return true;
//
//                    case DragEvent.ACTION_DRAG_ENDED:
//                        // Turns off any color tinting
//                        //v.getBackground().clearColorFilter();
//                        // Invalidates the view to force a redraw
//                        v.invalidate();
//                        // Does a getResult(), and displays what happened.
//                        if (event.getResult())
//                            Toast.makeText(getContext(), "The drop was handled.", Toast.LENGTH_SHORT).show();
//                        else
//                            Toast.makeText(getContext(), "The drop didn't work.", Toast.LENGTH_SHORT).show();
//                        // returns true; the value is ignored.
//                        return true;
//                    // An unknown action type was received.
//                    default:
//                        Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
//                        break;
//                }
//                return false;
//            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_recyclerview_item, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
        {
            holder.table_name.setText(dataList.get(position).getTableName());
            holder.occupied_name.setText(dataList.get(position).getOccupiedName());
            holder.pax_name.setText(dataList.get(position).getPaxName());
            holder.table_name1.setText(dataList.get(position).getTableName1());
            holder.occupied_name1.setText(dataList.get(position).getOccupiedName2());
            holder.pax_name1.setText(dataList.get(position).getPaxName3());
            holder.table_name2.setText(dataList.get(position).getTableName());
            holder.occupied_name2.setText(dataList.get(position).getOccupiedName());
            holder.pax_name2.setText(dataList.get(position).getPaxName());
            holder.table_name3.setText(dataList.get(position).getTableName1());
            holder.occupied_name3.setText(dataList.get(position).getOccupiedName2());
            holder.pax_name3.setText(dataList.get(position).getPaxName3());
            holder.table_name4.setText(dataList.get(position).getTableName1());
            holder.occupied_name4.setText(dataList.get(position).getOccupiedName2());
            holder.pax_name4.setText(dataList.get(position).getPaxName3());

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
//                    BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
//                    bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());

                    ClearBillSheetFragment clearBillSheetFragment = new ClearBillSheetFragment();
                    clearBillSheetFragment.show(getActivity().getSupportFragmentManager(), clearBillSheetFragment.getTag());
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }
}
