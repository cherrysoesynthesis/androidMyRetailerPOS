package com.dcs.myretailer.app.Setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Stock.StockAgent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RecyclerViewProductManagementAdapter extends RecyclerView.Adapter<RecyclerViewProductManagementAdapter.MyViewHolder> {

    private Context mContext ;
    private List<ProductData> mData ;
    ArrayList<String> arr = new ArrayList<String>();
    HashMap<String,String> hashValues = new HashMap<String, String>();
    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
    ArrayList selectedItems = new ArrayList();
    private String str ;
    private ProductMainPageFragment productMainPageFragment;
    int[] counter;
    public static String edit_fragment_status  = "0";
    public static Integer totalItems  = 0;
    public static Integer count_totalItems  = 0;
    public static Integer count_item_selected  = 0;
    public static Integer count_selected_total  = 0;
    public static Double totalAmount = 0.0;
    public static Double ItemDiscountAmount = 0.0;
    Handler mHandler;
    ActionMode mActionMode;
    private Activity mActivity;
    ArrayList<String> multi_selectedID = new ArrayList<>();
//    public RecyclerViewProductManagementAdapter(Activity mActivity, Context mContext, List<ProductData> mData, String str) {
    public RecyclerViewProductManagementAdapter(Activity mActivity, Context mContext, List<ProductData> mData, String str) {
        this.mActivity=mActivity;
        this.mContext = mContext;
        this.mData = mData;
        this.str = str;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        //view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);

        String chk_hide_img = Query.GetOptions(20);
        if (chk_hide_img.equals("1")) {
            view = mInflater.inflate(R.layout.cardveiwnoimage_item_book,parent,false);
        }else {
            view = mInflater.inflate(R.layout.cardveiw_item_book, parent, false);
        }

        counter = new int[getItemCount()];
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());

        holder.book_price_id.setText("$"+String.format("%.2f", Double.valueOf(mData.get(position).getPrice())));

        String chk_hide_img = Query.GetOptions(20);
        if (chk_hide_img.equals("0")) {

            if (mData.get(position).getThumbnail() == null || mData.get(position).getThumbnail().equals("0") || mData.get(position).getThumbnail().isEmpty()){
                Picasso.with(mContext).load(R.drawable.default_no_image).into(holder.img_book_thumbnail);
            }else {
                try {

                    String url_name = Query.GetImageURLForPLU(mData.get(position).getUUID());

                    if (url_name != null && url_name.length() > 23){
                        Picasso.with(mContext).load(url_name).into(holder.img_book_thumbnail);
                    }else {
                        try {
                            Picasso.with(mContext).load(mData.get(position).getThumbnail()).into(holder.img_book_thumbnail);
                        }catch (IllegalStateException e){
                            Picasso.with(mContext).load(R.drawable.default_no_image).into(holder.img_book_thumbnail);
                        }
                    }
                }catch (java.lang.IllegalStateException e){
                    Log.i(" e__"," e "+ e.getMessage());
                }
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String selectedID = mData.get(position).getProductID();
                if (multi_selectedID.contains(selectedID)) {
                    multi_selectedID.remove(selectedID);

                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));
                    holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));
                }else {
                    multi_selectedID.add(selectedID);

                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
                    holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                }
                String selectedCatID = mData.get(position).getProductCategoryID();
                String selectedCatName = mData.get(position).getProductCategoryName();


                if (mActionMode != null) {
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
                    holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                }else {
                    //Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
                    //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
                    Intent intent = new Intent(mContext, AddNewProductActivity.class);
                    intent.putExtra("ID", String.valueOf(selectedID));
                    //mContext.startActivity(intent);
                    mActivity.startActivity(intent);
//                    ((Activity)mContext).startActivity(intent);

//                String selectedTitle = mData.get(position).getTitle();
//
//                Log.i("DFDF___","DFDFFD__selectedID"+selectedID);
//                Log.i("DFDF___","DFDFFD__selectedCatID"+selectedCatID);
//                Log.i("DFDF___","DFDFFD__selectedCatName"+selectedCatName);
//                Log.i("DFDF___","DFDFFD__selectedTitle"+selectedTitle);
//
////                mActionMode = mActivity.startActionMode(new MyActionModeCallback());
//
//                mActionMode = mActivity.startActionMode(new  MyActionModeCallback());
                }

            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mActionMode != null) {
//                    return false;
//                }
////                selectedID = mData.get(position).getProductID();
//                multi_selectedID.add(mData.get(position).getProductID());
////                ActionBar actionBar = getActionBar();
////                actionBar.hide();
////                MenuInflater inflater = getMenuInflater();
////                inflater.inflate(R.menu.activity_add_new_product_actions, menu);
//////                MenuItem menuitem = mActionMode.getMenu().findItem(R.menu.activity_add_new_product_actions);
//////                if (myList.size() > 0) {
//////                    menuitem.setVisible(true);
//////                } else {
////                    menuitem.setVisible(false);
//////                }
//
////                MenuInflater inflater = (Activity)mContext.getMenuInflater();
//
////                MenuInflater inflater = getMenuInflater();
////                inflater.inflate(R.menu.activity_add_new_product_actions, menu);
////
////                MenuItem item = (MenuItem) findViewById(R.id.prod);
////                item.setVisible(false);
////                this.invalidateOptionsMenu();
//
//                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//
////                mActionMode = startSupportActionMode(mActionModeCallback);
//                mActionMode = mActivity.startActionMode(mActionModeCallback);
//                return true;
//            }
//        });


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//
//                               @Override
//                               public boolean onLongClick(View v) {
//
//                                   int position = holder.getAdapterPosition();
//                                   String selectedID = mData.get(position).getProductID();
//                                   String selectedCatID = mData.get(position).getProductCategoryID();
//                                   String selectedCatName = mData.get(position).getProductCategoryName();
//                                   String selectedTitle = mData.get(position).getTitle();
//
//                                   Log.i("DFDF___","DFDFFD__selectedID"+selectedID);
//                                   Log.i("DFDF___","DFDFFD__selectedCatID"+selectedCatID);
//                                   Log.i("DFDF___","DFDFFD__selectedCatName"+selectedCatName);
//                                   Log.i("DFDF___","DFDFFD__selectedTitle"+selectedTitle);
//
//                                   holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//
//                                   mActionMode = mActivity.startActionMode(new MyActionModeCallback());
//
////                                   _actionMode = startSupportActionMode(this);
//
//                                   //mActionMode=((Activity)mContext).startActionMode(new MyActionModeCallback());
//                                   //mActionMode =  startActionMode(new MyActionModeCallback());
////                                   mActionMode = ((ProductManagementActivity)mContext).startActionMode(new MyActionModeCallback());
//                                   return true;
//                               }
//
//
//        });


//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = holder.getAdapterPosition();
//                    count_selected ++;
//                    selectedItems.add(count_selected);
//                    Log.i("dsfdsf_selectedItems", String.valueOf(selectedItems));
//                    //Toast.makeText(v.getContext(), "Position is " + position, Toast.LENGTH_SHORT).show();
//                    holder.itemView.setBackgroundColor(Color.parseColor("#567845"));
//                    holder.product_linear_layout_id.setBackgroundColor(Color.parseColor("#337ab7"));
//                    return false;
//                }
//            });
        }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_grid_thumb, menu);
//
//        menu.setGroupVisible(R.id.menu_grid_thumb, true);//this line to show menu of Fragment
//        menu.setGroupVisible(R.id.menu_group_main, false);// this line to hide menu of Activity
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {



//            mode.getMenuInflater().inflate(R.menu.activity_add_new_product_actions, menu);
            mode.getMenuInflater().inflate(R.menu.example_menu, menu);
//            mode.setTitle("Choose your option");
            mode.setTitle("Choose your option");
            return true;
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.option_1:
////                    Toast.makeText(MainActivity.this, "Option 1 selected", Toast.LENGTH_SHORT).show();
//                    Log.i("onetwo1__","Option 1 selected");

                    final SweetAlertDialog pDialog =  new SweetAlertDialog(mActivity, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(Constraints.Delete)
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    Log.i("multi_selectedID__","multi_selectedID_"+multi_selectedID);
                                    for (int i = 0; i < multi_selectedID.size(); i ++) {
                                        String sql = "DELETE FROM PLU WHERE ID = " + Integer.parseInt(multi_selectedID.get(i));
                                        Log.i("SQLLL_","sql_"+sql);
                                        DBFunc.ExecQuery(sql, true);

                                        String stockSql = "DELETE FROM StockAgent WHERE PLUID = '" + multi_selectedID.get(i) + "'";
                                        Log.i("SQLLLLL_","sql_"+stockSql);
                                        DBFunc.ExecQuery(stockSql, true);
                                    }

                                    List<ProductData> lstProductData = Query.getProductAll("");
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                                    ProductManagementActivity.binding.productManagementRecyclerviewId.setLayoutManager(gridLayoutManager);

                                    RecyclerViewProductManagementAdapter myAdapter = new RecyclerViewProductManagementAdapter(mActivity, mContext, lstProductData, "product_management");
                                    ProductManagementActivity.binding.productManagementRecyclerviewId.setAdapter(myAdapter);
                                    myAdapter.notifyDataSetChanged();
                                    mode.finish();

                                }
                            })
                            .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();


                                    mode.finish();


                                }
                            });
                    pDialog.show();
                    pDialog.setCancelable(false);
                    return true;
                case R.id.option_2:
                    String str_imgUrl = "";
                    String str_imgType = "";
                    String str_imgfileName = "";
                    String base64imgValue = "";
                    String str_imgItemId = "";
                    Log.i("onetwo__","Option_1_selected"+multi_selectedID);

                    for (int i = 0; i < multi_selectedID.size(); i ++) {

                        String sql = "SELECT Name,UUID,Name2,DeptID,Price,Option,Code,Image,base64imgValue,ImageItemID,ImageUrl," +
                                "ImageType,ImageFileName,ProductVariant,ProductModifiers," +
                                "AllowNameQuickEdit,AllowPriceQuickEdit,AllowOpenPrice,ProductCategoryID,ProductCategoryName,ProductTaxID," +
                                "ProductTaxName,OnHandQty,DateTime,Condi_Seq " +
                                "FROM PLU WHERE ID = " + multi_selectedID.get(i);

                        Log.i("onetwo__", "sql__" + sql);

                        Cursor c = DBFunc.Query(sql, true);
                        if (c != null) {
                            if (c.moveToNext()) {

                                UUID uniqueId = UUID.randomUUID();
                                String pluName = c.getString(0);
                                String pluPrice = c.getString(4);
                                String pluCode = c.getString(6);
                                String str_img = c.getString(7);
                                base64imgValue = c.getString(8);
                                str_imgItemId = c.getString(9);
                                str_imgUrl = c.getString(10);
                                str_imgType = c.getString(11);
                                str_imgfileName = c.getString(12);
                                String str_checked_name_quick_edit = c.getString(15);
                                String str_checked_price_quick_edit = c.getString(16);
                                String str_checked_open_price_edit = c.getString(17);
                                String category_IDD = c.getString(18);
                                String category_Namee = c.getString(19);
                                String taxID = c.getString(20);
                                String taxName = c.getString(21);
                                String OnHandQty = c.getString(22);

                                String insert_sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,base64imgValue,ImageItemID,ImageUrl,ImageType,ImageFileName,ProductVariant,ProductModifiers," +
                                        "AllowNameQuickEdit,AllowPriceQuickEdit,AllowOpenPrice,ProductCategoryID,ProductCategoryName,ProductTaxID," +
                                        "ProductTaxName,OnHandQty,DateTime,Condi_Seq) " +
                                        "VALUES ('" + DBFunc.PurifyString(pluName) + "'," +
                                        "'" + uniqueId + "'," +
                                        "''," +
                                        "" + 0 + "," +
                                        pluPrice + ",'" +
                                        000000 + "','" + DBFunc.PurifyString(pluCode) + "'," +
                                        "'" + DBFunc.PurifyString(String.valueOf(str_img)) + "'," +
                                        "'" + DBFunc.PurifyString(String.valueOf(base64imgValue)) + "'," +
                                        "'" + DBFunc.PurifyString(String.valueOf(str_imgItemId)) + "'," +
                                        "'" + DBFunc.PurifyString(String.valueOf(str_imgUrl)) + "'," +
                                        "'" + DBFunc.PurifyString(String.valueOf(str_imgType)) + "'," +
                                        "'" + DBFunc.PurifyString(str_imgfileName) + "'," +
                                        "''," +
                                        "''," +
                                        "" + Integer.parseInt(str_checked_name_quick_edit) + "," +
                                        "" + Integer.parseInt(str_checked_price_quick_edit) + "," +
                                        "" + Integer.parseInt(str_checked_open_price_edit) + "," +
                                        "'" + category_IDD + "'," +
                                        "'" + category_Namee + "'," +
                                        "'" + taxID + "'," +
                                        "'" + taxName + "'," +
                                        "'" + OnHandQty + "'," +
                                        "" + System.currentTimeMillis() + "," +
                                        000 + ")";
                                Log.i("Dfsf___", "sql__" + insert_sql);
                                DBFunc.ExecQuery(insert_sql, true);

                            }
                            c.close();
                        }
//                    ProductManagementActivity.updateProductManagementFun("");

                        List<ProductData> lstProductData = Query.getProductAll("");
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                        ProductManagementActivity.binding.productManagementRecyclerviewId.setLayoutManager(gridLayoutManager);

                        RecyclerViewProductManagementAdapter myAdapter = new RecyclerViewProductManagementAdapter(mActivity, mContext, lstProductData, "product_management");
                        ProductManagementActivity.binding.productManagementRecyclerviewId.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();



                        Integer lstpluid = Query.findLatestID("ID", "PLU", true);

                        Integer QtySold = 0;
                        Integer QtyAdjustment = 0;
                        Integer QtyReturn = 0;
                        Integer QtyBalance = 0;
                        Integer QtyActual = 0;
                        Integer PLUID = lstpluid;
                        String DateTime = Query.GetDateFormart55();

//                        StockAgent stockAgent = new StockAgent(QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime);
//                        Query.SaveStockAgent(stockAgent);

                        String searchPLUStockAgent = "SELECT PLUID FROM StockAgent WHERE PLUID = "+PLUID;
                        Cursor csearchPLUStockAgent = DBFunc.Query(searchPLUStockAgent,true);
                        if (csearchPLUStockAgent != null) {
                            if (csearchPLUStockAgent.getCount() == 0){
                                //String stockAgentDateTime = CheckOutActivity.dateFormat55.format(new Date());

                                StockAgent stockAgent = new StockAgent(QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime);
                                Query.SaveStockAgent(stockAgent);

                            } else {
                                Query.UpdateStockAgent(PLUID,QtyActual);
                            }
                            csearchPLUStockAgent.close();
                        }


                        try {

                            String str_imgUrl_sync = str_imgUrl + str_imgfileName + "." + str_imgType;
                            SyncActivity.SyncImageUpload(mContext,base64imgValue,str_imgfileName,str_imgType,str_imgItemId,str_imgUrl_sync);
                        }catch (java.lang.RuntimeException e){
                            Log.i("err","err__"+e.getMessage());
                        }


//                        Integer lstpluid = Query.findLatestID("ID","PLU",true);
                        Integer onhandQtyLstpluid= Query.findOnHandQtyByPLUID(lstpluid);

                        Query.UpdateStockAgent(lstpluid,onhandQtyLstpluid);

                    }
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public int getSelectedItemCount() {
        if (selectedItems.size() > 0){

            return selectedItems.size();
        }else {

            return 0;
        }
    }

    private class MyActionModeCallback implements ActionMode.Callback {
        @SuppressWarnings("unused")
        private final String TAG = MyActionModeCallback.class.getSimpleName();

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            mode.getMenuInflater().inflate (R.menu.menu_cam, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.favorite:
                    // TODO: actually remove items
                    Log.d(TAG, "menu_remove");
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
//            adapter.clearSelection();
            mActionMode = null;
        }
    }
//    class MyActionModeCallback implements ActionMode.Callback{
//
//
//        @Override
//        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//            actionMode.getMenuInflater().inflate(R.menu.menu_cam, menu);
//            return false;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//            actionMode.setTitle("hihi");
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//            return false;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode actionMode) {
//
//        }
//    }

//
//    public static class MyActionModeCallback implements ActionMode.Callback{
//
//
//        @Override
//        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//            actionMode.getMenuInflater().inflate(R.menu.menu_cam, menu);
//            return false;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//            Log.i("DFDF___","DFDFFD__firstt"+menu);
//            actionMode.setTitle("hihi");
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//            Log.i("DFDF___","DFDFFD__secondd"+menuItem);
//            return false;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode actionMode) {
//            Log.i("DFDF___","DFDFFD__third"+actionMode);
//        }
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout product_linear_layout_id;
        public TextView tv_book_title,book_price_id,txt_item_ccount,tstid;
        public ImageView img_book_thumbnail;
        CardView cardView ;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
            String chk_hide_img = Query.GetOptions(20);
            if (chk_hide_img.equals("0")) {
                img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            }
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            txt_item_ccount = (TextView) itemView.findViewById(R.id.txt_item_ccount);
            tstid = (TextView) itemView.findViewById(R.id.tstid);

        }
    }


}
