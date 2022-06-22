////package com.dcs.myretailer.app.Cashier;
////
////import android.content.Context;
////import android.content.Intent;
////import android.content.res.Resources;
////import android.database.Cursor;
////import android.graphics.Bitmap;
////import android.graphics.BitmapFactory;
////import android.graphics.Color;
////import android.graphics.drawable.BitmapDrawable;
////import android.os.Handler;
////import android.support.v4.app.FragmentManager;
////import android.support.v4.content.ContextCompat;
////import android.support.v7.app.AppCompatActivity;
////import android.support.v7.widget.CardView;
////import android.support.v7.widget.RecyclerView;
////import android.text.Html;
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.ImageView;
////import android.widget.LinearLayout;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import com.dcs.myretailer.app.Book;
////import com.dcs.myretailer.app.CheckOutActivity;
////import com.dcs.myretailer.app.Database.DBFunc;
////import com.dcs.myretailer.app.R;
////import com.dcs.myretailer.app.Activity.AddNewProductActivity;
////import com.dcs.myretailer.app.Activity.AddTaxConfigurationActivity;
////import com.dcs.myretailer.app.Setting.ProductManagementActivity;
////import com.newland.ndk.h.ST_RSA_PRIVATE_KEY;
////
////import java.util.ArrayList;
////import java.util.Date;
////import java.util.HashMap;
////import java.util.List;
////
////public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
////
////    private Context mContext ;
////    private List<Book> mData ;
////    ArrayList<String> arr = new ArrayList<String>();
////    HashMap<String,String> hashValues = new HashMap<String, String>();
////    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
////    ArrayList selectedItems = new ArrayList();
////    private String str ;
////    private ProductMainPageFragment productMainPageFragment;
////    int[] counter;
////    public static String edit_fragment_status  = "0";
////    public static Integer totalItems  = 0;
////    public static Integer count_totalItems  = 0;
////    public static Integer count_item_selected  = 0;
////    public static Integer count_selected_total  = 0;
////    public static Double totalAmount = 0.0;
////    public static Double ItemDiscountAmount = 0.0;
////    Handler mHandler;
////    public RecyclerViewAdapter(Context mContext, List<Book> mData, String str) {
////        this.mContext = mContext;
////        this.mData = mData;
////        this.str = str;
////    }
////
////    public RecyclerViewAdapter(ProductMainPageFragment productMainPageFragment, List<Book> lstBook) {
////        this.productMainPageFragment = productMainPageFragment;
////        this.mData = lstBook;
////    }
////
////    @Override
////    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////
////        View view ;
////        LayoutInflater mInflater = LayoutInflater.from(mContext);
////        view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);
////        counter = new int[getItemCount()];
////        return new MyViewHolder(view);
////    }
////    public static int count = 0;
////    public static int ccount = 0;
////    public static int count_selected = 0;
////    public static Double sltPriceTotal = 0.0;
////    public static String str_sltPriceTotal = "0";
////    public static Integer countSelectedArr = 0;
////    public static ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
////    public static ArrayList<String> sldIDArr = new ArrayList<String>();
////    public static ArrayList<String> sldNameArr = new ArrayList<String>();
////    public static ArrayList<String> sldCategoryIDArr = new ArrayList<String>();
////    public static ArrayList<String> sldCategoryNameArr = new ArrayList<String>();
////    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
////    public static ArrayList<String> sltBillDisArr = new ArrayList<String>();
////    public static ArrayList<String> sltProductIDArr = new ArrayList<String>();
////    public static ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
////    public static ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
////    public static ArrayList<String> TestQty = new ArrayList<String>();
////    public static String item_id = "0";
////    public static String item_name = "0";
////    public static String item_countt = "0";
////    public static String item_diss = "0";
////    public static String item_diss_amt = "0";
////    public static String item_pricee = "0";
////    public static String original_item_pricee = "0";
////    public static String old_pricee = "0";
////    public static String category_id = "0";
////    public static String category_name = "0";
////    int totalqty = 0;
////    int exiting_id = 0;
////    public static String select_ = "0";
////    public static String status_on = "0";
////    @Override
////    public void onBindViewHolder(final MyViewHolder holder, final int position) {
////
////        holder.tv_book_title.setText(mData.get(position).getTitle());
////
////        //holder.book_price_id.setText("$"+mData.get(position).getPrice());
////        holder.book_price_id.setText("$"+String.format("%.2f", Double.valueOf(mData.get(position).getPrice())));
////        //holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
////
////        BitmapDrawable drawable = null;
////
//////        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
//////                R.drawable.black_photo);
//////        drawable = new BitmapDrawable(mContext.getResources(), icon);
//////        Bitmap bitmap = drawable.getBitmap();
//////        AddNewProductActivity.BitMapToString(bitmap);
////
////        //if (mData.get(position).getThumbnail() == null){
////            holder.img_book_thumbnail.setImageBitmap(mData.get(position).getThumbnail());
////            holder.tstid.setVisibility(View.VISIBLE);
////            holder.tstid.setText(mData.get(position).getTitle());
////       // }
//////        else {
//////
//////            holder.img_book_thumbnail.setImageBitmap(mData.get(position).getThumbnail());
//////        }
////
////        if (str.equals("product")) {
////            getBillByProductID();
////            if (hashValues.containsKey(mData.get(position).getTitle())){
////            //if (arr.contains(mData.get(position).getTitle())) {
////                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
////                holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
////                holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
////
////                holder.txt_item_ccount.setVisibility(View.VISIBLE);
////                holder.txt_item_ccount.setText(hashValues.get(mData.get(position).getTitle().toString()));
////                //ProductMainPageFragment.status_on = "0";
////            }
////            //sldNameArr.clear();
////            holder.itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    mHandler = new Handler();
////                    m_Runnable.run();
////                    MainActivity.St = "0";
////                    ProductMainPageFragment.status_on = "1";
////                    int position = holder.getAdapterPosition();
////                    select_ = "1";
////                    count++;
////                    countSelectedArr = count;
////                    sldNameArr.clear();
////                     sldIDArr.clear();
////                     sltPriceTotalArr.clear();
////                     countSelectedArr = 0;
////
////                    sldNameArr.add(mData.get(position).getTitle());
////                    sldQtyArr.add(Integer.parseInt(mData.get(position).getProductID()));
////                    //sldQtyArr.add(1);
////                    sldIDArr.add(mData.get(position).getProductID());
////                    sltPriceTotal += Double.parseDouble(mData.get(position).getPrice());
////                    str_sltPriceTotal = String.valueOf(sltPriceTotal);
////                    //sltPriceTotalArr.add(Integer.parseInt(selectedPrice))
////                    sltPriceTotalArr.add(mData.get(position).getPrice());
////                    //chksldQtyArr.add(Integer.parseInt(mData.get(position).getProductID()));
////                    sldCategoryIDArr.add(mData.get(position).getProductCategoryID());
////                    sldCategoryNameArr.add(mData.get(position).getProductCategoryName());
////                    if(Double.valueOf(item_diss_amt) > 0.0) {
////                        sltBillDisArr.add(item_diss_amt);
////
////                        String sql = "INSERT INTO BillDisc (DiscID,BillNo,BillPLU_idx,Name,Value,Option,DateTime) VALUES (";
////                        sql += "'" + RecyclerViewItemDiscountAdapter.disID + "', ";
////                        sql += "'" + MainActivity.strbillNo  + "', ";
////                        sql += "'" + DBFunc.PurifyString(String.valueOf(mData.get(position).getProductCategoryID())) + "', ";
////                        sql += "'" + DBFunc.PurifyString(String.valueOf(RecyclerViewItemDiscountAdapter.disName)) + "', ";
////                        sql += "'" + DBFunc.PurifyString(String.valueOf(RecyclerViewItemDiscountAdapter.disVal)) + "', ";
////                        sql += "'" + DBFunc.PurifyString(String.valueOf(RecyclerViewItemDiscountAdapter.disOptions)) + "', ";
////                        sql += System.currentTimeMillis() + ")";
////
////                        DBFunc.ExecQuery(sql, false);
////                        RecyclerViewItemDiscountAdapter.disID = 0;
////                        RecyclerViewItemDiscountAdapter.disName = "";
////                        RecyclerViewItemDiscountAdapter.disVal = "";
////                        RecyclerViewItemDiscountAdapter.disOptions = "";
////                        item_diss_amt = "";
////                    }else{
////                        sltBillDisArr.add("0.0");
////                    }
////                    sltProductIDArr.add(mData.get(position).getProductID());
////                    sltCategoryIDArr.add(mData.get(position).getPrice());
////                    sltCategoryNameArr.add(mData.get(position).getPrice());
////
////                    count_totalItems = count + totalItems;
////
////                    if (count_totalItems > 0) {
////                        count_item_selected = Integer.parseInt(holder.txt_item_ccount.getText().toString()) + 1;
////
////                        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
////                        holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
////                        holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
////
////                        holder.txt_item_ccount.setVisibility(View.VISIBLE);
////
////                        holder.txt_item_ccount.setText(count_item_selected.toString());
////                        count_selected_total += Integer.parseInt(holder.txt_item_ccount.getText().toString());
////                    }
////
////                    String bill_details_ID = "";
////                    String sql = "Select ID,ProductName FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
////                    Cursor c = DBFunc.Query(sql, false);
////                    if (c.moveToNext()) {
////                        bill_details_ID = c.getString(0);
////                    }
////                    c.close();
////                    Log.i("fdgf__",bill_details_ID);
////                    if (bill_details_ID.length() > 0){
////
////                        CheckOutActivity.UpdateBill(MainActivity.strbillNo,bill_details_ID);
////                    }else{
////                        CheckOutActivity.SaveBill(MainActivity.strbillNo);
////                    }
////
////                    getBillByBillNo();
////                    getBillByProductID();
//////
////                    MainActivity.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
////                            "<small>" + totalItems + " Products " + "</small>" +
////                            "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
////                            String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
////                }
////            });
////
////
////            holder.itemView.setLongClickable(true);
////            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
////                @Override
////                public boolean onLongClick(View v) {
////                    mHandler = new Handler();
////                    m_Runnable.run();
////                    ProductMainPageFragment.status_on = "1";
////                    EditProductSheetFragment.str_edit_product_sheet_fragment = "1";
////                    item_id =  mData.get(position).getProductID();
////                    item_name =  mData.get(position).getTitle().toString();
////                    item_countt =  holder.txt_item_ccount.getText().toString();
////                    Double amt = Double.valueOf(item_countt) * Double.valueOf(mData.get(position).getPrice().toString());
////                    item_pricee = String.valueOf(amt);
////                    original_item_pricee = mData.get(position).getPrice().toString();
////                    item_diss = "";
////                    old_pricee = "";
////                    category_id = mData.get(position).getProductCategoryID();
////                    category_name = mData.get(position).getProductCategoryName();
////                    FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
////                    EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
////                    //editProductSheetFragment.show(getSupportFragmentManager(), editProductSheetFragment.getTag());
////                    editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
////                    return true;
////                }
////            });
////
////        }else if (str.equals("fragment4")) {
////            Intent intent = new Intent(mContext, MainActivity.class);
////            intent.putExtra("name", "Fragment____0");
////            mContext.startActivity(intent);
////        }else{
////            holder.itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    int position = holder.getAdapterPosition();
////                    String selectedID = mData.get(position).getProductID();
////                    String selectedCatID = mData.get(position).getProductCategoryID();
////                    String selectedCatName = mData.get(position).getProductCategoryName();
////                    Log.i("_selectedCatID",selectedCatID);
////                    Log.i("_selectedCatName",selectedCatName);
////                    Log.i("selectedID",selectedID);
////                    //Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
////                    //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
////                    Intent intent = new Intent(mContext, AddNewProductActivity.class);
////                    intent.putExtra("ID", String.valueOf(selectedID));
////                    mContext.startActivity(intent);
////                }
////            });
////
////            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
////                @Override
////                public boolean onLongClick(View v) {
////                    int position = holder.getAdapterPosition();
////                    count_selected ++;
////                    selectedItems.add(count_selected);
////                    Log.i("dsfdsf_selectedItems", String.valueOf(selectedItems));
////                    //Toast.makeText(v.getContext(), "Position is " + position, Toast.LENGTH_SHORT).show();
////                    holder.itemView.setBackgroundColor(Color.parseColor("#567845"));
////                    holder.product_linear_layout_id.setBackgroundColor(Color.parseColor("#337ab7"));
////                    return false;
////                }
////            });
////        }
//////        new Thread(new Runnable() {
//////            @Override
//////            public void run () {
//////                functionReload(holder, holder, position);
//////            }
//////        }).start();
////    }
//////
//////    private void functionReload(MyViewHolder holder, MyViewHolder holder1, int position) {
//////        if (hashValues.containsKey(mData.get(position).getTitle())){
//////            //if (arr.contains(mData.get(position).getTitle())) {
//////            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//////            holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//////            holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//////
//////            holder.txt_item_ccount.setVisibility(View.VISIBLE);
//////            holder.txt_item_ccount.setText(hashValues.get(mData.get(position).getTitle().toString()));
//////            //ProductMainPageFragment.status_on = "0";
//////        }
//////    }
////
////    private void getBillByProductID() {
////        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
////        String sql = " SELECT count(ProductQty),SUM(ProductPrice),ProductName,ItemDiscountAmount FROM " +
////                "Details_BillProduct Where BillNo = '" + MainActivity.strbillNo + "' "+
////                "  group by ProductName";
////        Cursor c = DBFunc.Query(sql, false);
////        if (c != null) {
//////            totalItems = 0;
//////            totalAmount = 0.0;
//////            ItemDiscountAmount = 0.0;
////            hashValues.clear();
////            while (c.moveToNext()) {
////                //if (c.moveToNext()) {
////                if (!c.isNull(0)) {
////////                    totalItems += c.getInt(0);
////////                    totalAmount += Double.valueOf(c.getString(1));
//////                    totalItems = c.getInt(0);
//////                    totalAmount = Double.valueOf(c.getString(1));
//////                    ItemDiscountAmount = Double.valueOf(c.getString(1));
//////                    //arr.add(c.getString(2));
////                    hashValues.put(c.getString(2),c.getString(0));
////                }
////            }
////            c.close();
////        }
////
////        String str_product = "Product";
////        if (totalItems > 0) {
////            str_product = "Products";
////        }
////    }
////    private void getBillByBillNo() {
////        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
////        String sql = " SELECT count(ProductQty),SUM(ProductPrice) FROM Details_BillProduct " +
////                "Where BillNo = '" + MainActivity.strbillNo + "' "+
////                "  group by BillNo";
////        Cursor c = DBFunc.Query(sql, false);
////        if (c != null) {
////            totalItems = 0;
////            totalAmount = 0.0;
////            while (c.moveToNext()) {
////                //if (c.moveToNext()) {
////                if (!c.isNull(0)) {
////                    totalItems += c.getInt(0);
////                    totalAmount += Double.valueOf(c.getString(1));
////                }
////            }
////            c.close();
////        }
////
////        String str_product = "Product";
////        if (totalItems > 0) {
////            str_product = "Products";
////        }
////    }
////        @Override
////    public int getItemCount() {
////        return mData.size();
////    }
////    public int getSelectedItemCount() {
////        if (selectedItems.size() > 0){
////
////            return selectedItems.size();
////        }else {
////
////            return 0;
////        }
////    }
////
////    public class MyViewHolder extends RecyclerView.ViewHolder {
////        public LinearLayout product_linear_layout_id;
////        public TextView tv_book_title,book_price_id,txt_item_ccount,tstid;
////        public ImageView img_book_thumbnail;
////        CardView cardView ;
////        public MyViewHolder(View itemView) {
////            super(itemView);
////            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
////            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
////            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
////            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
////            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
////            txt_item_ccount = (TextView) itemView.findViewById(R.id.txt_item_ccount);
////            tstid = (TextView) itemView.findViewById(R.id.tstid);
////
////            getBillByBillNo();
////            getBillByProductID();
////
////            MainActivity.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
////                    "<small>" + totalItems + " Products " + "</small>" +
////                    "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
////                    String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
////
////            mHandler = new Handler();
////            m_Runnable.run();
////        }
////    }
////    private final Runnable m_Runnable = new Runnable()
////    {
////        public void run()
////
////        {
////            //oast.makeText(mContext,"in runnablaaae",Toast.LENGTH_SHORT).show();
//////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
////
////            mHandler.postDelayed(m_Runnable, 300);
////
////           if (status_on.equals("1") || edit_fragment_status.equals("1")) {
////                //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
////                //Log.i("DSFDSFDS_FSDFDS_F",EditProductSheetFragment.str_edit_product_sheet_fragment);
////                //if (EditProductSheetFragment.str_edit_product_sheet_fragment.equals("1")) {
////                    MainActivity.St = "0";
////
////                    getBillByBillNo();
////                    getBillByProductID();
////
////
////                    MainActivity.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
////                            "<small>" + totalItems + " Products " + "</small>" +
////                            "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
////                            String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
////
////                    //EditProductSheetFragment.str_edit_product_sheet_fragment = "0";
////                //}
////                status_on = "0";
////                //ProductMainPageFragment.status_on = "1";
////               edit_fragment_status = "0";
////            }else {
////
////               mHandler.removeCallbacks(m_Runnable);
////               mHandler.removeCallbacksAndMessages(null);
////           }
////
////        }
////
////    };
////
////}
//package com.dcs.myretailer.app.Cashier;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.net.Uri;
//import android.os.Handler;
//import android.provider.MediaStore;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.Html;
//import android.text.InputType;
//import android.text.method.KeyListener;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.dcs.myretailer.app.Book;
//import com.dcs.myretailer.app.CheckOutActivity;
//import com.dcs.myretailer.app.Database.DBFunc;
//import com.dcs.myretailer.app.DialogBox;
//import com.dcs.myretailer.app.ENUM.ENUM;
//import com.dcs.myretailer.app.Query.Query;
//import com.dcs.myretailer.app.R;
//import com.dcs.myretailer.app.Setting.StrTextConst;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.IllegalFormatException;
//import java.util.List;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
////import com.squareup.picasso.Picasso;
//
//
//public class RecyclerViewNoImageAdapter extends RecyclerView.Adapter<RecyclerViewNoImageAdapter.MyViewHolder> {
//
//    public Context mContext ;
//    public List<Book> mData ;
//    ArrayList<String> arr = new ArrayList<String>();
//    public static HashMap<String,String> hashValues = new HashMap<String, String>();
//    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
//    ArrayList selectedItems = new ArrayList();
//    public String str ;
//    public ProductMainPageFragment productMainPageFragment;
//    int[] counter;
//    public static String edit_fragment_status  = "0";
//    public static Integer totalItems  = 0;
//    public static Integer count_totalItems  = 0;
//    public static Integer count_item_selected  = 0;
//    public static Integer count_selected_total  = 0;
//    public static Double totalAmount = 0.0;
//    public static Double ItemDiscountAmount = 0.0;
//    public static Integer St_position = 0;
//    public static String openPrice = "0";
//    public static String openPriceValue = "0";
//
//    Handler mHandler;
//    public static String St = "0";
//    ProgressDialog progressDialog;
//
//    public static int count = 0;
//    public static int ccount = 0;
//    public static int count_selected = 0;
//    public static Double sltPriceTotal = 0.0;
//    public static String str_sltPriceTotal = "0";
//    public static Integer countSelectedArr = 0;
//    public static ArrayList<String> slddisID = new ArrayList<String>();
//    public static ArrayList<String> slddisName = new ArrayList<String>();
//    public static ArrayList<String> slddisTypeName = new ArrayList<String>();
//    public static ArrayList<String> slddisType = new ArrayList<String>();
//    public static ArrayList<String> slddisValue = new ArrayList<String>();
//    public static ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
//    public static ArrayList<String> sldIDArr = new ArrayList<String>();
//    public static ArrayList<String> sldNameArr = new ArrayList<String>();
//    public static ArrayList<String> sldCategoryIDArr = new ArrayList<String>();
//    public static ArrayList<String> sldCategoryNameArr = new ArrayList<String>();
//    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
//    public static ArrayList<String> sltBillDisArr = new ArrayList<String>();
//    public static ArrayList<String> sltProductIDArr = new ArrayList<String>();
//    public static ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
//    public static ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
//    public static ArrayList<String> vchQueueNo = new ArrayList<String>();
//    public static ArrayList<String> intTableNo = new ArrayList<String>();
//    //    public static ArrayList<String> ReceiptOrderStatus = new ArrayList<String>();
//    public static ArrayList<String> TestQty = new ArrayList<String>();
//    public static String item_id = "0";
//    public static String item_name = "0";
//    public static String item_countt = "0";
//    public static String item_diss = "0";
//    public static String item_diss_amt = "0";
//    public static String item_pricee = "0";
//    public static String original_item_pricee = "0";
//    public static String old_pricee = "0";
//    public static String category_id = "0";
//    public static String category_name = "0";
//    public static RecyclerViewNoImageAdapter.MyViewHolder stat_holder = null;
//    public static Integer stat_position = -1;
//    int totalqty = 0;
//    int exiting_id = 0;
//    public static String select_ = "0";
//    public static String status_on = "0";
//    //String url ="https://www.thecrazyprogrammer.com/wp-content/uploads/2015/07/The-Crazy-Programmer.png";
//    //String url ="https://i.imgur.com/tGbaZCY.jpg";
//
//    public RecyclerViewNoImageAdapter(Context mContext, List<Book> mData, String str) {
//        this.mContext = mContext;
//        this.mData = mData;
//        this.str = str;
//    }
//
//    public RecyclerViewNoImageAdapter(ProductMainPageFragment productMainPageFragment, List<Book> lstBook) {
//        this.productMainPageFragment = productMainPageFragment;
//        this.mData = lstBook;
//    }
//
//    @Override
//    public RecyclerViewNoImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view ;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.cardveiwnoimage_item_book,parent,false);
//        counter = new int[getItemCount()];
//        mHandler = new Handler();
//        m_Runnable.run();
//        return new RecyclerViewNoImageAdapter.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerViewNoImageAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position_val) {
//
//        final String openPriceVar = mData.get(position_val).getOpenPrice();
//        final String imgVar = mData.get(position_val).getThumbnail();
//        final String priceVar = mData.get(position_val).getPrice();
//        final String prodIDVar = mData.get(position_val).getProductID();
//        final String titleVar = mData.get(position_val).getTitle();
//
//        holder.tv_book_title.setText(titleVar);
//
//        holder.cardView.setTag(position_val);
//
//        if (priceVar.length() > 0) {
//            holder.book_price_id.setText("$" + String.format("%.2f", Double.valueOf(priceVar)));
//        }else {
//
//
//            holder.book_price_id.setText("$0.00");
//        }
//
////        if ( imgVar == null || imgVar.equals("0")  || imgVar.isEmpty()){
////            Picasso.with(mContext).load(R.drawable.default_no_image).into(holder.img_book_thumbnail);
////        }else {
////            Picasso.with(mContext).load(imgVar).into(holder.img_book_thumbnail);
////        }
//
//        if (str.equals("product")) {
//
//            hashValues = Query.GetProductByID(MainActivity.strbillNo);
//            //Query.GetProductByID(MainActivity.strbillNo);
//
//            setColor(holder,position_val);
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if (openPriceVar != null && openPriceVar.equals("1")) {
//                        OpenPriceDialog(mContext, prodIDVar, titleVar ,holder,position_val);
//                    }else {
//                        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//                        //holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.test1));
//                        holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//                        holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//
//                        holder.txt_item_ccount.setVisibility(View.VISIBLE);
//
//                        NormalFun(holder,position_val);
//                    }
//                }
//            });
//
//
//            holder.itemView.setLongClickable(true);
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
////                public boolean onLongClick(View v) {
////                    EditProductSheetFragment.productID = "0";
////                    EditProductSheetFragment.productName = "0";
////                    EditProductSheetFragment.EditFragmentPrice = "0";
////                    EditProductSheetFragment.productID = "0";
////                    EditProductSheetFragment.DetailBillID = "0";
////                    EditProductSheetFragment.FragmentVar = "0";
////                    if (openPriceVar == null || openPriceVar.equals("0")){
////                        EditFragmentFun(holder, position_val);
////                    }else {
////                        Toast.makeText(mContext, "Open Price. \n Please edit at checkout page. ", Toast.LENGTH_SHORT).show();
////                    }
////                    return true;
////                }
//                public boolean onLongClick(View v) {
//
//                    EditProductSheetFragment.productID = "0";
//                    EditProductSheetFragment.productName = "0";
//                    EditProductSheetFragment.EditFragmentPrice = "0";
//                    EditProductSheetFragment.productID = "0";
//                    EditProductSheetFragment.DetailBillID = "0";
//                    EditProductSheetFragment.FragmentVar = "0";
//                    String sqll = "Select BillNo from DetailsBillProduct " +
//                            "Where BillNo = '"+MainActivity.strbillNo+"' " +
//                            "AND ProductId = '"+prodIDVar+"' " +
//                            "AND OpenPriceStatus = '"+openPriceVar+"'";
//                    Log.i("DFDFDF____","sqllffff__"+sqll);
//                    Cursor ccc = DBFunc.Query(sqll,false);
//                    if (ccc != null) {
//                        if (ccc.getCount() == 0){
//                            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
//                                    //.setTitleText("Cancelled Bill")
//                                    .setContentText(ENUM.SelectProduct)
//                                    .setConfirmText(ENUM.OK)
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            sDialog.dismissWithAnimation();
//                                        }
//                                    })
//                                    .show();
//                        }else {
//                            EditProductSheetFragment.productID = "0";
//                            EditProductSheetFragment.productName = "0";
//                            EditProductSheetFragment.EditFragmentPrice = "0";
//                            EditProductSheetFragment.productID = "0";
//                            EditProductSheetFragment.DetailBillID = "0";
//                            EditProductSheetFragment.FragmentVar = "0";
//                            if (openPriceVar == null || openPriceVar.equals("0")){
//                                EditFragmentFun(holder, position_val);
//                            }else {
//                                Toast.makeText(mContext, "Open Price. \n Please edit at checkout page. ", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        ccc.close();
//                    }
//                    return true;
//                }
//            });
//
//        }else if (str.equals("fragment4")) {
//            Intent intent = new Intent(mContext, MainActivity.class);
//            intent.putExtra("name", "Fragment____0");
//            mContext.startActivity(intent);
//            ((Activity)mContext).finish();
//        }else{
////            holder.itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    int position = holder.getAdapterPosition();
////                    String selectedID = mData.get(position).getProductID();
////                    String selectedCatID = mData.get(position).getProductCategoryID();
////                    String selectedCatName = mData.get(position).getProductCategoryName();
////                    Log.i("_selectedCatID",selectedCatID);
////                    Log.i("_selectedCatName",selectedCatName);
////                    Log.i("selectedID",selectedID);
////                    //Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
////                    //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
////                    Intent intent = new Intent(mContext, AddNewProductActivity.class);
////                    intent.putExtra("ID", String.valueOf(selectedID));
////                    mContext.startActivity(intent);
////                }
////            });
////
////            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
////                @Override
////                public boolean onLongClick(View v) {
////                    int position = holder.getAdapterPosition();
////                    count_selected ++;
////                    selectedItems.add(count_selected);
////                    Log.i("dsfdsf_selectedItems", String.valueOf(selectedItems));
////                    //Toast.makeText(v.getContext(), "Position is " + position, Toast.LENGTH_SHORT).show();
////                    holder.itemView.setBackgroundColor(Color.parseColor("#567845"));
////                    holder.product_linear_layout_id.setBackgroundColor(Color.parseColor("#337ab7"));
////                    return false;
////                }
////            });
//        }
//
//
////        new Thread(new Runnable() {
////            @Override
////            public void run () {
////                functionReload(holder, holder, position);
////            }
////        }).start();
//    }
//
//    private void NormalFun(RecyclerViewNoImageAdapter.MyViewHolder holder, int position) {
//        openPrice = "0";
//
//        AddNewDetailsBillProduct(holder, position);
//
//        getBillByBillNo();
//
//        hashValues = Query.GetProductByID(MainActivity.strbillNo);
//        //Query.GetProductByID(MainActivity.strbillNo);
//
//        CheckoutButtonValues(totalItems,totalAmount);
//
//    }
//
//    private void setColor(RecyclerViewNoImageAdapter.MyViewHolder holder, int position) {
//        // if (mData.get(position).getOpenPrice() == null || mData.get(position).getOpenPrice().equals("0")) {
//        if (hashValues.containsKey(mData.get(position).getProductID())) {
//            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//
//            holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//            holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//
//            holder.txt_item_ccount.setVisibility(View.VISIBLE);
//
//            holder.txt_item_ccount.setText(hashValues.get(mData.get(position).getProductID()));
//        } else {
//            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
//
//            holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));
//            holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));
//
//            holder.txt_item_ccount.setVisibility(View.GONE);
//        }
//        // }
//        stat_holder = null;
//        stat_position = -1;
//    }
//
//    private void AddNewDetailsBillProduct(RecyclerViewNoImageAdapter.MyViewHolder holder, int position) {
//        ProductMainPageFragment.status_on = "1";
//        //int position = holder.getAdapterPosition();
//        //St_position =  v.getVerticalScrollbarPosition();
//        St_position =  holder.getAdapterPosition();
//        select_ = "1";
//        //count++;
//
////        slddisID.clear();
////        slddisName.clear();;
////        slddisTypeName.clear();;
////        slddisType.clear();;
////        slddisValue.clear();
//
//        getBillByBillNo();
//        //countSelectedArr = count;
//        countSelectedArr = totalItems;
////        sldNameArr.clear();
////        sldIDArr.clear();
////        sltPriceTotalArr.clear();
////        countSelectedArr = 0;
//
//        sldNameArr.add(mData.get(position).getTitle());
//        sldQtyArr.add(Integer.parseInt(mData.get(position).getProductID()));
//        String DiscountID = "0";
//        String DiscountName = "0";
//        String DiscountType = "0";
//        String DiscountTypeName = "0";
//        String DiscountValue = "0";
//        String DiscountItemDiscountAmount = "0";
//        Cursor Cursor_Dis = Query.SearchDiscountFromDetailsBillProductBy(MainActivity.strbillNo,mData.get(position).getProductID());
//        if (Cursor_Dis != null){
//            if (Cursor_Dis.moveToNext()){
//                DiscountID = Cursor_Dis.getString(0);
//                DiscountName = Cursor_Dis.getString(1);
//                DiscountType = Cursor_Dis.getString(2);
//                DiscountTypeName = Cursor_Dis.getString(3);
//                DiscountValue = Cursor_Dis.getString(4);
//                DiscountItemDiscountAmount = Cursor_Dis.getString(5);
//            }
//            Cursor_Dis.close();
//        }
//        slddisID.add(DiscountID);
//        slddisName.add(DiscountName);
//        slddisTypeName.add(DiscountTypeName);
//        slddisType.add(DiscountType);
//        slddisValue.add(DiscountValue);
//        sldIDArr.add(mData.get(position).getProductID());
//        sltPriceTotal += Double.parseDouble(mData.get(position).getPrice());
//        str_sltPriceTotal = String.valueOf(sltPriceTotal);
//        sltPriceTotalArr.add(mData.get(position).getPrice());
//        sldCategoryIDArr.add(mData.get(position).getProductCategoryID());
//        sldCategoryNameArr.add(mData.get(position).getProductCategoryName());
//
//        if (!(item_diss_amt == null || item_diss_amt.equals("0") ||
//                item_diss_amt.equals("null") || item_diss_amt.isEmpty())){
//            if (Double.valueOf(item_diss_amt) > 0.0) {
//                sltBillDisArr.add(item_diss_amt);
//
//                String sql = "INSERT INTO BillDisc (DiscID,BillNo,BillPLU_idx,Name,Value,Option,DateTime) VALUES (";
//                sql += "'" + slddisID + "', ";
//                sql += "'" + MainActivity.strbillNo + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf(mData.get(position).getProductCategoryID())) + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf(slddisName)) + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf(slddisValue)) + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf("000")) + "', ";
//                sql += System.currentTimeMillis() + ")";
//
//                DBFunc.ExecQuery(sql, false);
//                item_diss_amt = "";
//
//            } else {
////                sltBillDisArr.add("0.0"); //20082020
//                sltBillDisArr.add(DiscountItemDiscountAmount);
//            }
//        } else {
//            //sltBillDisArr.add("0.0");//20082020
//            sltBillDisArr.add(DiscountItemDiscountAmount);
//        }
//        sltProductIDArr.add(mData.get(position).getProductID());
//        sltCategoryIDArr.add(mData.get(position).getPrice());
//        sltCategoryNameArr.add(mData.get(position).getPrice());
//
//        //count_totalItems = count + totalItems;
//        count_totalItems = totalItems;
//
//
//        // if (count_totalItems > 0) {
//        if (str.equals("product")) {
//            hashValues = Query.GetProductByID(MainActivity.strbillNo);
//            //Query.GetProductByID(MainActivity.strbillNo);
//        }
//        //count_item_selected = Integer.parseInt(holder.txt_item_ccount.getText().toString()) + 1;
//        if (hashValues.get(mData.get(position).getProductID()) != null && Integer.parseInt(hashValues.get(mData.get(position).getProductID())) > 0){
//            count_item_selected = Integer.parseInt(hashValues.get(mData.get(position).getProductID()))+ 1;
//        }else {
//            count_item_selected = 1;
//        }
//
//
//
//        holder.txt_item_ccount.setText(count_item_selected.toString());
//        count_selected_total += Integer.parseInt(holder.txt_item_ccount.getText().toString());
//
//        //new LongOperation5(mContext).execute(holder,position);
//        String bill_details_ID = "";
//        String sql = "Select ID FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
//        Cursor c = DBFunc.Query(sql, false);
//        if (c.moveToNext()) {
//            bill_details_ID = c.getString(0);
//        }
//        c.close();
//        if (bill_details_ID.length() > 0) {
//            if (!MainActivity.tbl_name.equals("0")){
//                intTableNo.add(MainActivity.tbl_name);
//            }else {
//                intTableNo.add("0");
//            }
//            Integer billIDDD = Query.getBillID(MainActivity.strbillNo);
//            CheckOutActivity.UpdateBill(mContext,billIDDD,MainActivity.strbillNo,bill_details_ID, ENUM.SALES);
//        } else {
//            String strbillNo = "";
//            strbillNo = MainActivity.strbillNo;
//            Integer qty = 1;
//            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//            String paymenttype = "Cash";
//            String status = ENUM.SALES;
//            String Itemstatus = ENUM.SALES;
//            Double sub_total = 0.0;
//            Double amount = 0.0;
//            ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;
//
//
//            if (!MainActivity.tbl_name.equals("0")){
//                intTableNo.add(MainActivity.tbl_name);
//            }else {
//                intTableNo.add("0");
//            }
//
//            Integer billIDDD = Query.getBillID(strbillNo);
//            CheckOutActivity.SaveBill(billIDDD,strbillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                    qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                    sub_total, amount, paymenttype, Modifier_ID, vchQueueNo, intTableNo, Itemstatus,slddisID,slddisName,slddisTypeName,
//                    slddisType,slddisValue);
//            //CheckOutActivity.SaveBill(MainActivity.strbillNo);
////                    }
//        }
//    }
//
//    private void OpenPriceDialog(final Context mContext, final String pluID, final String pluName,
//                                 final RecyclerViewNoImageAdapter.MyViewHolder holder, final int position) {
//        final DialogBox dlg = new DialogBox(mContext);
//        if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Window window = dlg.getWindow();
//            WindowManager.LayoutParams wlp = window.getAttributes();
//            wlp.gravity = Gravity.RIGHT;
//            window.setAttributes(wlp);
//        }
//        //not support pax
//        //dlg.setTitle(StrTextConst.GetText(TextType.POS, 9, "Open Price", tv_total.getText()));
//        dlg.setTitle("Open Price");
//        LayoutInflater inflater = LayoutInflater.from(dlg.getContext());
//        LinearLayout lay_numpad = (LinearLayout) inflater.inflate(R.layout.dlg_numpad, null, false);
//
//        TextView txt_info = (TextView) lay_numpad.findViewById(R.id.txt_info);
//        txt_info.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 428));
//
//        LinearLayout lay = (LinearLayout) lay_numpad.findViewById(R.id.lay_val);
//
//        final StyleTextView et_value = new StyleTextView(mContext);
//        lay.addView(et_value, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        et_value.setTypeface(Typeface.MONOSPACE);
//        //rgb(127,255,0)
//        //50,205,50
//        et_value.setBackgroundColor(Color.argb(255, 50,205,50));
//        et_value.setTextColor(Color.WHITE);
//        et_value.setTextSize(50);
//        et_value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        String val = mData.get(position).getPrice();
////        String val = "0";
//        //String val = "1";
//
//        // et_value.setTag(tag)
////        et_value.setText(String.format("%.2f", 0.0));
//        et_value.setText(String.format("%.2f", Double.valueOf(val)));
//        et_value.setFocusable(true);
//        //et_value.setTag(val);
//        et_value.setTag("0");
//
//        final MetallicButton btn_bkspc = (MetallicButton) lay_numpad.findViewById(R.id.btn_bkspc);
//        final MetallicButton[] btn_num = new MetallicButton[11];
//        btn_num[0] = (MetallicButton) lay_numpad.findViewById(R.id.btn_0);
//        btn_num[0].setTag("0");
//        btn_num[1] = (MetallicButton) lay_numpad.findViewById(R.id.btn_1);
//        btn_num[1].setTag("1");
//        btn_num[2] = (MetallicButton) lay_numpad.findViewById(R.id.btn_2);
//        btn_num[2].setTag("2");
//        btn_num[3] = (MetallicButton) lay_numpad.findViewById(R.id.btn_3);
//        btn_num[3].setTag("3");
//        btn_num[4] = (MetallicButton) lay_numpad.findViewById(R.id.btn_4);
//        btn_num[4].setTag("4");
//        btn_num[5] = (MetallicButton) lay_numpad.findViewById(R.id.btn_5);
//        btn_num[5].setTag("5");
//        btn_num[6] = (MetallicButton) lay_numpad.findViewById(R.id.btn_6);
//        btn_num[6].setTag("6");
//        btn_num[7] = (MetallicButton) lay_numpad.findViewById(R.id.btn_7);
//        btn_num[7].setTag("7");
//        btn_num[8] = (MetallicButton) lay_numpad.findViewById(R.id.btn_8);
//        btn_num[8].setTag("8");
//        btn_num[9] = (MetallicButton) lay_numpad.findViewById(R.id.btn_9);
//        btn_num[9].setTag("9");
//        btn_num[10] = (MetallicButton) lay_numpad.findViewById(R.id.btn_00);
//        btn_num[10].setTag("00");
//
//        btn_bkspc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("cfirst", "ten");
//                String _val = (String) et_value.getTag();
//                if (_val.length() > 0) {
//                    _val = _val.substring(0, _val.length() - 1);
//                }
//                if (_val.length() == 0) {
//                    _val = "0";
//                }
//                et_value.setTag(_val);
//                et_value.setText(String.format("%.2f", Double.parseDouble(_val) / 100.0));
//                et_value.requestFocus();
//            }
//        });
//
//        for (MetallicButton btn : btn_num) {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("cfirst", "5");
//                    String num = (String) v.getTag();
//                    String _val = (String) et_value.getTag();
//                    _val += num;
//                    int val = 0;
//                    try {
//                        val = Integer.parseInt(_val);
//                        if (val > 999999999) {
//                            val = 999999999;
//                        }
//                        et_value.setTag("" + val);
//                        et_value.setText(String.format("%.2f", Double.parseDouble("" + val) / 100.0));
//                    } catch (NumberFormatException e) {
//                    }
//                    et_value.requestFocus();
//                }
//            });
//        }
//
//        final MetallicButton btn_ok = (MetallicButton) lay_numpad.findViewById(R.id.btn_ok);
////        final int finalPluid = pluid;
////        final String finalPluname = pluname;
////
////        final int finalDeptID = deptID;
////        final String finalDeptName = deptName;
////        final double finalPluprice = pluprice;
//        btn_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Dff___", "___"+String.valueOf(et_value.getText().toString()));
//                Log.i("pluID_", "___"+"_pluID"+pluID);
//                Log.i("ppluName_", "___"+"_pluName"+pluName);
//                try {
//                    String typeValue = et_value.getText().toString();
//                    Log.i("typeValue", "___"+"_typeValue"+typeValue);
//                    dlg.dismiss();
//                    EditPLUQty(Integer.parseInt(pluID),pluName,typeValue,holder,position);
//                } catch (NumberFormatException e) {
//                    ShowErrorDialog(mContext,"ONE"+StrTextConst.GetText(StrTextConst.TextType.POS, 125));
//                } catch (IllegalFormatException e) {
//                    ShowErrorDialog(mContext,"two"+StrTextConst.GetText(StrTextConst.TextType.POS, 125));
//                }
//            }
//        });
//
//        MetallicButton btn_close = (MetallicButton) lay_numpad.findViewById(R.id.btn_cancel);
//        btn_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dlg.dismiss();
//            }
//        });
//
//        et_value.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View v) {
//                String _val = "0";
//
//                et_value.setTag(_val);
//                et_value.setText(String.format("%.2f", Double.parseDouble(_val) / 100.0));
//                return true;
//            }
//
//        });
//
//        et_value.setKeyListener(new KeyListener() {
//
//            @Override
//            public int getInputType() {
//                return 0;
//            }
//
//            @Override
//            public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
//
//                // String num = "";
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_0:
//                    case KeyEvent.KEYCODE_NUMPAD_0:
//                        // num = "0";
//                        btn_num[0].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_1:
//                    case KeyEvent.KEYCODE_NUMPAD_1:
//                        // num = "1";
//                        btn_num[1].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_2:
//                    case KeyEvent.KEYCODE_NUMPAD_2:
//                        // num = "2";
//                        btn_num[2].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_3:
//                    case KeyEvent.KEYCODE_NUMPAD_3:
//                        // num = "3";
//                        btn_num[3].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_4:
//                    case KeyEvent.KEYCODE_NUMPAD_4:
//                        // num = "4";
//                        btn_num[4].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_5:
//                    case KeyEvent.KEYCODE_NUMPAD_5:
//                        // num = "5";
//                        Log.i("cfirst", "tenn");
//                        btn_num[5].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_6:
//                    case KeyEvent.KEYCODE_NUMPAD_6:
//                        // num = "6";
//                        btn_num[6].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_7:
//                    case KeyEvent.KEYCODE_NUMPAD_7:
//                        // num = "7";
//                        btn_num[7].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_8:
//                    case KeyEvent.KEYCODE_NUMPAD_8:
//                        // num = "8";
//                        btn_num[8].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_9:
//                    case KeyEvent.KEYCODE_NUMPAD_9:
//                        // num = "9";
//                        btn_num[9].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_DEL:
//                    case KeyEvent.KEYCODE_FORWARD_DEL:
//                        btn_bkspc.performClick();
//                        break;
//                    case KeyEvent.KEYCODE_ENTER:
//                    case KeyEvent.KEYCODE_NUMPAD_ENTER:
//                        btn_ok.performClick();
//                        break;
//                    case KeyEvent.KEYCODE_ESCAPE:
//                        et_value.performLongClick();
//                        break;
//                }
//
//                et_value.requestFocus();
//
//                return true;
//            }
//
//            @Override
//            public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
//                return false;
//            }
//
//            @Override
//            public boolean onKeyOther(View view, Editable text, KeyEvent event) {
//                return false;
//            }
//
//            @Override
//            public void clearMetaKeyState(View view, Editable content, int states) {
//            }
//
//        });
//
//        dlg.addView(lay_numpad);
//        lay_numpad.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        dlg.setWindowSize(0.8f, 0.9f);
//        dlg.show();
//
//        et_value.requestFocus();
//        return;
//    }
//    //show error dialog
//    private void ShowErrorDialog(Context context,String errmsg) {
//        final DialogBox dlg = new DialogBox(context);
//        dlg.setMessage(errmsg);
//        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
//        dlg.setDialogIconType(DialogBox.IconType.ERROR);
//        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//        dlg.show();
//
//    }
//
//    private void EditPLUQty(final int billINDEX, final String pluName, final String typeValue, final RecyclerViewNoImageAdapter.MyViewHolder holder, final int position) {
//        Context CurrentActivity = mContext;
//
//        final DialogBox dlg = new DialogBox(CurrentActivity);
//        if (CurrentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Window window = dlg.getWindow();
//            WindowManager.LayoutParams wlp = window.getAttributes();
//            wlp.gravity = Gravity.RIGHT;
//            window.setAttributes(wlp);
//        }
//
////                final long _qty = plu.getInt(0);
////                final String _name = plu.getString(3);
//
//        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 8, "Quantity"));
//        LayoutInflater inflater = LayoutInflater.from(dlg.getContext());
//        LinearLayout lay_numpad = (LinearLayout) inflater.inflate(R.layout.dlg_numpad, null, false);
//
//        TextView txt_info = (TextView) lay_numpad.findViewById(R.id.txt_info);
//        txt_info.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 424));
//
//        LinearLayout lay = (LinearLayout) lay_numpad.findViewById(R.id.lay_val);
//
//        final StyleTextView et_value = new StyleTextView(mContext);
//        et_value.setTypeface(Typeface.MONOSPACE);
//
//        lay.addView(et_value, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        //et_value.setBackgroundColor(Color.argb(255, 65, 78, 92));
//        et_value.setBackgroundColor(Color.argb(255, 50,205,50));
//        et_value.setTextColor(Color.WHITE);
//        et_value.setTextSize(50);
//        et_value.setInputType(InputType.TYPE_CLASS_NUMBER);
//        et_value.setFocusable(true);
//        //String val = "" + plu.getInt(0);
//
//        // et_value.setTag(tag)
//
//        //et_value.setText("" + _qty);
//        et_value.setText("" + 1);
//        et_value.setTag(""+1);
//        //et_value.setTag(val);
//
//        Log.i("ttyet_valuee", "___"+"_et_valuelue"+et_value.getText());
//        final MetallicButton btn_bkspc = (MetallicButton) lay_numpad.findViewById(R.id.btn_bkspc);
//        final MetallicButton[] btn_num = new MetallicButton[11];
//        btn_num[0] = (MetallicButton) lay_numpad.findViewById(R.id.btn_0);
//        btn_num[0].setTag("0");
//        btn_num[1] = (MetallicButton) lay_numpad.findViewById(R.id.btn_1);
//        btn_num[1].setTag("1");
//        btn_num[2] = (MetallicButton) lay_numpad.findViewById(R.id.btn_2);
//        btn_num[2].setTag("2");
//        btn_num[3] = (MetallicButton) lay_numpad.findViewById(R.id.btn_3);
//        btn_num[3].setTag("3");
//        btn_num[4] = (MetallicButton) lay_numpad.findViewById(R.id.btn_4);
//        btn_num[4].setTag("4");
//        btn_num[5] = (MetallicButton) lay_numpad.findViewById(R.id.btn_5);
//        btn_num[5].setTag("5");
//        btn_num[6] = (MetallicButton) lay_numpad.findViewById(R.id.btn_6);
//        btn_num[6].setTag("6");
//        btn_num[7] = (MetallicButton) lay_numpad.findViewById(R.id.btn_7);
//        btn_num[7].setTag("7");
//        btn_num[8] = (MetallicButton) lay_numpad.findViewById(R.id.btn_8);
//        btn_num[8].setTag("8");
//        btn_num[9] = (MetallicButton) lay_numpad.findViewById(R.id.btn_9);
//        btn_num[9].setTag("9");
//        btn_num[10] = (MetallicButton) lay_numpad.findViewById(R.id.btn_00);
//        btn_num[10].setTag("00");
//
//        btn_bkspc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("cfirst", "seven");
//                String _val = (String) et_value.getTag();
//                if (_val.length() > 0) {
//                    _val = _val.substring(0, _val.length() - 1);
//                }
//                if (_val.length() == 0) {
//                    _val = "0";
//                }
//                et_value.setTag(_val);
//                et_value.setText(_val);
//                et_value.requestFocus();
//            }
//        });
//        final boolean[] numchk = {true};
//        for (MetallicButton btn : btn_num) {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("cfirst", "3");
//                    String num = (String) v.getTag();
//                    String _val = (String) et_value.getTag();
////							if (_val.equals("0")) {
////								_val = num;
////							} else {
////								_val += num;
////							}
//                    if (numchk[0]) {
//                        if (_val.equals("0")) {
//                            _val = num;
//                        } else {
//                            _val = num;
//                        }
//                        numchk[0] = false;
//                    } else {
//                        if (_val.equals("0")) {
//                            _val = num;
//                        } else {
//                            _val += num;
//                        }
//                    }
//                    et_value.setTag(_val);
//                    et_value.setText(_val);
//                    et_value.requestFocus();
//                }
//            });
//        }
//
//        final MetallicButton btn_ok = (MetallicButton) lay_numpad.findViewById(R.id.btn_ok);
//        btn_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(ActivityPosCashier.this, "mcsbtn_test1", Toast.LENGTH_LONG).show();
//                try {
//                    int q = Integer.parseInt(et_value.getText().toString());
//                    if (q < 1) {
//                        //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 124));
//                        // Toast.makeText(CurrentActivity,"Invalid Quantity Number! Minimum quantity is 1!",Toast.LENGTH_SHORT).show();
//                        // return;
//                        q = 1;
//                    }
//
//
//                    //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                    String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                    try {
//                        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
//                        Integer bill_plu_id = Query.findLatestID("ID", "DetailsBillProduct", false);
//                        //openPrice = timeStamp+"_"+bill_plu_id;
//                        bill_plu_id = bill_plu_id +1 ;
//                        openPrice = "_"+ bill_plu_id;
//                        for (int i = 0; i < q; i ++) {
//                            Log.i("___openPrice","__openPrice"+openPrice);
//                            Query.saveDetailsBillProduct_AssignValue(MainActivity.BillID, MainActivity.strbillNo, "OFF",
//                                    billINDEX, pluName, String.valueOf(typeValue), "0", String.valueOf(billINDEX), "0", "0",
//                                    ENUM.SALES, "0", "0", "0", "0", "0");
//                        }
//                        Query.SaveBillPLU(MainActivity.BillID,MainActivity.strbillNo,
//                                String.valueOf(billINDEX),pluName,String.valueOf(typeValue),q,"","");
//
//                        MainCheckoutButtonUpdate(holder,position);
////                                Intent i_main = new Intent(mContext,MainActivity.class);
////                                i_main.putExtra("name","");
////                                mContext.startActivity(i_main);
////                                MainActivity.St = "1";
//                    }catch (IllegalFormatException e){
//                        Log.i("__Errr","EEr"+e.getMessage());
//                    }
//                    dlg.dismiss();
//                    //MainActivity.St = "1";
//                } catch (NumberFormatException e) {
//                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 124));
//                    // Toast.makeText(CurrentActivity,"Invalid Quantity Number! Minimum quantity is 1!",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        MetallicButton btn_close = (MetallicButton) lay_numpad.findViewById(R.id.btn_cancel);
//        btn_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dlg.dismiss();
//            }
//        });
//
//        et_value.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View v) {
//                et_value.setTag("0");
//                et_value.setText("0");
////						et_value.setTag("1");
////						et_value.setText("1");
//                return true;
//            }
//
//        });
//
//        et_value.setKeyListener(new KeyListener() {
//
//            @Override
//            public int getInputType() {
//                return 0;
//            }
//
//            @Override
//            public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
//
//                // String num = "";
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_0:
//                    case KeyEvent.KEYCODE_NUMPAD_0:
//                        // num = "0";
//                        btn_num[0].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_1:
//                    case KeyEvent.KEYCODE_NUMPAD_1:
//                        // num = "1";
//                        btn_num[1].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_2:
//                    case KeyEvent.KEYCODE_NUMPAD_2:
//                        // num = "2";
//                        btn_num[2].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_3:
//                    case KeyEvent.KEYCODE_NUMPAD_3:
//                        // num = "3";
//                        btn_num[3].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_4:
//                    case KeyEvent.KEYCODE_NUMPAD_4:
//                        // num = "4";
//                        btn_num[4].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_5:
//                    case KeyEvent.KEYCODE_NUMPAD_5:
//                        // num = "5";
//                        Log.i("cfirst", "eigth");
//                        btn_num[5].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_6:
//                    case KeyEvent.KEYCODE_NUMPAD_6:
//                        // num = "6";
//                        btn_num[6].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_7:
//                    case KeyEvent.KEYCODE_NUMPAD_7:
//                        // num = "7";
//                        btn_num[7].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_8:
//                    case KeyEvent.KEYCODE_NUMPAD_8:
//                        // num = "8";
//                        btn_num[8].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_9:
//                    case KeyEvent.KEYCODE_NUMPAD_9:
//                        // num = "9";
//                        btn_num[9].performClick();
//                        break;
//                    case KeyEvent.KEYCODE_DEL:
//                    case KeyEvent.KEYCODE_FORWARD_DEL:
//                        btn_bkspc.performClick();
//                        break;
//                    case KeyEvent.KEYCODE_ENTER:
//                    case KeyEvent.KEYCODE_NUMPAD_ENTER:
//                        btn_ok.performClick();
//                        break;
//                    case KeyEvent.KEYCODE_ESCAPE:
//                        et_value.performLongClick();
//                        break;
//                }
//
//                et_value.requestFocus();
//
//                return true;
//            }
//
//            @Override
//            public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
//                return false;
//            }
//
//            @Override
//            public boolean onKeyOther(View view, Editable text, KeyEvent event) {
//                return false;
//            }
//
//            @Override
//            public void clearMetaKeyState(View view, Editable content, int states) {
//            }
//
//        });
//
//        dlg.addView(lay_numpad);
//        lay_numpad.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        dlg.setWindowSize(0.8f, 0.9f);
//        dlg.show();
//        et_value.requestFocus();
//
//        //plu.close();
////            } else {
////                // System.out.println("Something wrong with the DB or out of sync IDX!");
////                DialogBox dlg1 = new DialogBox(CurrentActivity);
////                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
////                dlg1.setDialogIconType(DialogBox.IconType.ERROR);
////                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 312));
////                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                dlg1.show();
////                // Toast.makeText(CurrentActivity,
////                // "Something wrong with the DB or out of sync IDX!",
////                // Toast.LENGTH_SHORT).show();
////            }
////        } else {
////            // System.out.println("CANT QUERY! DB ERROR!");
////            DialogBox dlg1 = new DialogBox(CurrentActivity);
////            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
////            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
////            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
////            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////            dlg1.show();
////            // Toast.makeText(CurrentActivity,
////            // "CANT QUERY! DB ERROR!",Toast.LENGTH_SHORT).show();
////        }
//    }
//
//    private void MainCheckoutButtonUpdate(RecyclerViewNoImageAdapter.MyViewHolder holder, int position) {
//        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(MainActivity.strbillNo,ENUM.GroupBy);
//        if (C_DetailsBillProduct != null) {
//            totalItems = 0;
//            totalAmount = 0.0;
//            while (C_DetailsBillProduct.moveToNext()) {
//                if (!C_DetailsBillProduct.isNull(0)) {
//                    if (C_DetailsBillProduct.getInt(2) != -1){
//                        totalItems += C_DetailsBillProduct.getInt(0);
//                        totalAmount += Double.valueOf(C_DetailsBillProduct.getString(1));
//                    }
//                }
//            }
//            C_DetailsBillProduct.close();
//        }
//
//        MainActivity.totalItems = totalItems;
//        MainActivity.totalAmount = totalAmount;
//
//        Log.i("___","DDD_totalItems__"+ MainActivity.totalItems);
//
//        getBillByBillNo();
//        if (str.equals("product")) {
//            hashValues = Query.GetProductByID(MainActivity.strbillNo);
//            //Query.GetProductByID(MainActivity.strbillNo);
//        }
//        Log.i("TEStingTwo","TTTTTT");
//        //CheckoutButtonValues(RecyclerViewAdapter.totalItems,RecyclerViewAdapter.totalAmount);
//        CheckoutButtonValues(totalItems,totalAmount);
//        setColor(holder,position);
//        //EditProductSheetFragment.str_edit_product_sheet_fragment = "0";
//        //}
//        status_on = "0";
//        //ProductMainPageFragment.status_on = "1";
//        edit_fragment_status = "0";
//    }
//
//    private void EditFragmentFun(RecyclerViewNoImageAdapter.MyViewHolder holder, int position) {
//        item_id = "0";
//        category_id = "0";
//        category_name = "0";
//        item_name = "0";
//        item_pricee = "0";
//        ItemDiscountAmount = 0.0;
//        item_diss = "0";
//        old_pricee = "0";
//        stat_holder = holder;
//        stat_position = position;
//
//        EditProductSheetFragment.str_percentage = "";
//        EditProductSheetFragment.str_percentage_value = "";
//        EditProductSheetFragment.disID = "";
//        EditProductSheetFragment.disName = "";
//        EditProductSheetFragment.disTypeName = "";
//        EditProductSheetFragment.disType = "";
//        EditProductSheetFragment.disValue = "";
//        CheckOutActivity.str_percentage = "";
//        CheckOutActivity.str_percentage_value = "";
//        CheckOutActivity.disID = "";
//        CheckOutActivity.disName = "";
//        CheckOutActivity.disTypeName = "";
//        CheckOutActivity.disType = "";
//        CheckOutActivity.disValue = "";
////        TabFragmentAmount.str_percentage = "";
////        TabFragmentAmount.str_percentage_value = "";
//        St_position =  holder.getAdapterPosition();
//        EditProductSheetFragment.str_edit_product_sheet_fragment = "0";
//        item_id =  mData.get(position).getProductID();
//        item_name =  mData.get(position).getTitle().toString();
//        item_countt =  holder.txt_item_ccount.getText().toString();
//        Double amt = Double.valueOf(item_countt) * Double.valueOf(mData.get(position).getPrice().toString());
//        item_pricee = String.valueOf(amt);
//        original_item_pricee = mData.get(position).getPrice().toString();
//        item_diss = "";
//        old_pricee = "";
//        category_id = mData.get(position).getProductCategoryID();
//        category_name = mData.get(position).getProductCategoryName();
//
//        EditProductSheetFragment.billNo = MainActivity.strbillNo;
//
//        EditProductSheetFragment.EditFragmenttotalQty = item_countt;
//
//        FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
//        FragmentTransaction ft = manager.beginTransaction();
//        //commitAllowingStateLoss();
//        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//        try {
//            //EditProductCheckoutSheetFragment editProductSheetFragment = new EditProductCheckoutSheetFragment();
//            editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
//        }catch (IllegalStateException e){
//            //editProductSheetFragment.onStop();
//            //MainActivity.St = "1";
//            Intent i_main = new Intent(mContext,MainActivity.class);
//            i_main.putExtra("name","");
//            mContext.startActivity(i_main);
//            ((Activity)mContext).finish();
//        }
////                    }
//
//        //}else {
//        //MainActivity.St = "1";
//        //Toast.makeText(mContext, "Null", Toast.LENGTH_SHORT).show();
//        // }
//    }
//
//    //
////    private void functionReload(MyViewHolder holder, MyViewHolder holder1, int position) {
////        if (hashValues.containsKey(mData.get(position).getTitle())){
////            //if (arr.contains(mData.get(position).getTitle())) {
////            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
////            holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
////            holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
////
////            holder.txt_item_ccount.setVisibility(View.VISIBLE);
////            holder.txt_item_ccount.setText(hashValues.get(mData.get(position).getTitle().toString()));
////            //ProductMainPageFragment.status_on = "0";
////        }
////    }
//    private String getPathFromURI(Context context, Uri contentUri) {
//        if ( contentUri.toString().indexOf("file:///") > -1 ){
//            return contentUri.getPath();
//        }
//
//        Cursor cursor = null;
//        try {
//            String[] proj = { MediaStore.Images.Media.DATA };
//            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        }finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }
//    //    private void getBillByProductID() {
////        hashValues = Query.GetProductByID(MainActivity.strbillNo);
////    }
//    private void getBillByBillNo() {
//        //new LongOperation6(mContext).execute();
//        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
//        String sql = " SELECT SUM(ProductQty),SUM(ProductPrice),ProductQty FROM DetailsBillProduct " +
//                "Where ProductQty != -1 AND BillNo = '" + MainActivity.strbillNo + "' "+
//                "   AND Cancel = 'SALES'  group by BillNo";
//
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            totalItems = 0;
//            totalAmount = 0.0;
//            while (c.moveToNext()) {
//                //if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    if (c.getInt(2) != -1){
//                        totalItems += c.getInt(0);
//                        totalAmount += Double.valueOf(c.getString(1));
//                    }else {
//                        totalItems = 0;
//                        totalAmount = 0.0;
//                    }
//                }
//            }
//            c.close();
//        }
//
//        String str_product = "Product";
//        if (totalItems > 0) {
//            str_product = "Products";
//        }
//
//    }
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//    private int getSelectedItemCount() {
//        if (selectedItems.size() > 0){
//
//            return selectedItems.size();
//        }else {
//
//            return 0;
//        }
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public LinearLayout product_linear_layout_id;
//        public TextView tv_book_title,book_price_id,txt_item_ccount,tstid;
//        CardView cardView ;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
//            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
//            tv_book_title.setTextSize(MainActivity.billFontSize);
//            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
//            book_price_id.setTextSize(MainActivity.billFontSize);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
//            txt_item_ccount = (TextView) itemView.findViewById(R.id.txt_item_ccount);
//            tstid = (TextView) itemView.findViewById(R.id.tstid);
//
//            getBillByBillNo();
//            if (str.equals("product")) {
//                hashValues = Query.GetProductByID(MainActivity.strbillNo);
//                //Query.GetProductByID(MainActivity.strbillNo);
//            }
//
//
//            CheckoutButtonValues(totalItems,totalAmount);
//
//        }
//    }
//    private final Runnable m_Runnable = new Runnable() {
//        public void run()  {
//            mHandler.postDelayed(m_Runnable, 300);
//
//            if (St.equals("1")) {
//                //new LongOperation9(mContext).execute();
//                getBillByBillNo();
//                if (str.equals("product")) {
//                    hashValues = Query.GetProductByID(MainActivity.strbillNo);
//                    //Query.GetProductByID(MainActivity.strbillNo);
//                }
//
//                if (stat_position > -1) {
//                    setColor(stat_holder, stat_position);
//                }
//
//                CheckoutButtonValues(RecyclerViewNoImageAdapter.totalItems,RecyclerViewNoImageAdapter.totalAmount);
//
//                //MainActivity.edittext_barcode.requestFocus();
//
//                status_on = "0";
//                edit_fragment_status = "0";
//                St = "0";
//            }
//
//        }
//
//    };
//
//    private void CheckoutButtonValues(Integer totalItems, Double totalAmount) {
//        MainActivity.totalItems = totalItems;
//        MainActivity.totalAmount = totalAmount;
//        if (totalItems.toString().length() > 2){
//            MainActivity.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
//                    "<small>" + totalItems + " Products " + "</small>" +
//                    "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
//                    String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
//        }else {
//            MainActivity.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
//                    "<small>" + totalItems + " Products " + "</small>" +
//                    "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
//                    String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
//        }
//
//    }
////viewHolder.deleteButton.setOnClickListener(view -> delete(viewHolder.getAdapterPosition()));
//}
