package com.dcs.myretailer.app.Cashier;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.ProductDataViewModel;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.TransactionDetailsActivity;
import com.dcs.myretailer.app.databinding.CardveiwItemBookBinding;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements View.OnKeyListener, View.OnTouchListener {

    public static Context mContext ;
    public static List<ProductData> mData ;
    ArrayList<String> arr = new ArrayList<String>();
    public static HashMap<String,String> hashValues = new HashMap<String, String>();
    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
    ArrayList selectedItems = new ArrayList();
    public static String str ;
    public ProductMainPageFragment productMainPageFragment;
    int[] counter;
    public static String remarkScanVar  = "";
    public static String remarkScanValue  = "";
    public static String edit_fragment_status  = "0";
    public static Integer totalItems  = 0;
    public static Integer count_totalItems  = 0;
    public static Integer count_item_selected  = 0;
    public static Integer count_selected_total  = 0;
    public static Double totalAmount = 0.0;
    public static Double ItemDiscountAmount = 0.0;
    public static Integer St_position = 0;
    public static String openPrice = "0";
    public static String openPriceValue = "0";

//    Handler mHandler;
    public static String St = "0";
    ProgressDialog progressDialog;

    public static int count = 0;
    public static int ccount = 0;
    public static int count_selected = 0;
    public static Double sltPriceTotal = 0.0;
    public static String str_sltPriceTotal = "0";
    public static String old_pricee = "0";
    public static Integer countSelectedArr = 0;
    public static ArrayList<String> slddisID = new ArrayList<String>();
    public static ArrayList<String> slddisName = new ArrayList<String>();
    public static ArrayList<String> slddisTypeName = new ArrayList<String>();
    public static ArrayList<String> slddisType = new ArrayList<String>();
    public static ArrayList<String> slddisValue = new ArrayList<String>();
    public static ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
    public static ArrayList<String> sldIDArr = new ArrayList<String>();
    public static ArrayList<String> sldNameArr = new ArrayList<String>();
    public static ArrayList<String> sldCategoryIDArr = new ArrayList<String>();
    public static ArrayList<String> sldCategoryNameArr = new ArrayList<String>();
    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
    public static ArrayList<String> sltBillDisArr = new ArrayList<String>();
    public static ArrayList<String> sltProductIDArr = new ArrayList<String>();
    public static ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
    public static ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
    public static ArrayList<String> vchQueueNo = new ArrayList<String>();
    public static ArrayList<String> intTableNo = new ArrayList<String>();
    //    public static ArrayList<String> ReceiptOrderStatus = new ArrayList<String>();
    public static ArrayList<String> TestQty = new ArrayList<String>();
    public static String item_id = "0";
    public static String item_name = "0";
    public static String item_countt = "0";
    public static String item_diss = "0";
    public static String item_diss_amt = "0";
    public static String item_pricee = "0";
    public static String original_item_pricee = "0";
//    public static String old_pricee = "0";
    public static String category_id = "0";
    public static String category_name = "0";
    public static MyViewHolder stat_holder = null;
    public static Integer stat_position = -1;
//    public static Integer latestQty = 0;
    int totalqty = 0;
    int exiting_id = 0;
    public static String select_ = "0";
    public static String status_on = "0";
    //String url ="https://www.thecrazyprogrammer.com/wp-content/uploads/2015/07/The-Crazy-Programmer.png";
    //String url ="https://i.imgur.com/tGbaZCY.jpg";

    public RecyclerViewAdapter(Context mContext, List<ProductData> mData, String str) {
        this.mContext = mContext;
        this.mData = mData;
        this.str = str;
    }

//    public RecyclerViewAdapter(ProductMainPageFragment productMainPageFragment, List<ProductData> lstProductData) {
//        this.productMainPageFragment = productMainPageFragment;
//        this.mData = lstProductData;
//    }

//    private final LifecycleRegistry registry = new LifecycleRegistry(mContext);
//
//    @NonNull
//    @Override
//    public Lifecycle getLifecycle() {
//        return null;
//    }

    public static CardveiwItemBookBinding binding = null;
    ProductDataViewModel model = null;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view ;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        String chk_hide_img = Query.GetOptions(20);
//        if (chk_hide_img.equals("1")) {
//            view = mInflater.inflate(R.layout.cardveiwnoimage_item_book,parent,false);
//        }else {
//            view = mInflater.inflate(R.layout.cardveiw_item_book, parent, false);
//        }
//
//        Log.i("View___","Hellofromhere");
//
////        if (SampleActivity.terminal_type.equals(ENUM.PAX_E600M)){
//////            123dp
////        }
//        counter = new int[getItemCount()];
//        mHandler = new Handler();
//        m_Runnable.run();
//        return new MyViewHolder(view);

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cardveiw_item_book, parent, false);

        binding.setLifecycleOwner((LifecycleOwner) mContext);

        //        ProductDataViewModel model = new ViewModelProvider(MainActivity) mContext).get(ProductDataViewModel.class);
        model = ViewModelProviders.of((MainActivity) mContext).get(ProductDataViewModel.class);

//        qrScan = new IntentIntegrator((Activity)mContext);
//        binding.setVariable(BR.vm, viewModel);

        //binding.setLifecycleOwner((LifecycleOwner) mContext);
        //        ProductDataViewModel model = new ViewModelProvider().get(ProductDataViewModel.class);

//        model.getTotalCount().observe((LifecycleOwner) mContext, Observer {
//            //TODO: populate recycler view
//        })


        binding.setViewmodel(model);
//        model.setTotalCount(1);
//        model.getTotalCount().observe((LifecycleOwner) mContext, fruitlist -> {
//            // update UI
////            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
////                    android.R.layout.simple_list_item_1, android.R.id.text1, fruitlist);
////            // Assign adapter to ListView
////            listView.setAdapter(adapter);
////            progressBar.setVisibility(View.GONE);
//            binding.txtItemCcount.setText(fruitlist.toString());
//        });

//        binding.viewmodel = ProductDataViewModel;
//        binding.setLifecycleOwner((LifecycleOwner) mContext);

        String chk_hide_img = Query.GetOptions(20);
        if (chk_hide_img.equals("1")) {

            String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
            if (terminalTypeVal.toUpperCase().equals(Constraints.PAX_E600M)){
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(180,
                        180);
                //params.topMargin = 10;
                binding.productLinearLayoutId.setLayoutParams(params);

                LinearLayout.LayoutParams paramsCardView = new LinearLayout.LayoutParams(160,
                        170);
                paramsCardView.leftMargin = 10;
                paramsCardView.rightMargin = 10;
                //paramsCardView.topMargin = 10;
                paramsCardView.bottomMargin = 10;
                binding.cardviewId.setLayoutParams(paramsCardView);


                binding.bookImgId.setVisibility(View.GONE);

                FrameLayout.LayoutParams paramsTitle = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsTitle.leftMargin = 10;
                paramsTitle.topMargin = 10;
                binding.bookTitleId.setLayoutParams(paramsTitle);

                FrameLayout.LayoutParams paramsPrice = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsPrice.leftMargin = 10;
                paramsPrice.topMargin = 90;
                binding.bookPriceId.setLayoutParams(paramsPrice);

                RelativeLayout.LayoutParams totalCountImg = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                totalCountImg.leftMargin = 70;
                totalCountImg.topMargin = 100;
                binding.txtItemCcount.setLayoutParams(totalCountImg);

            }else {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(234,
                        205);
                binding.productLinearLayoutId.setLayoutParams(params);

                LinearLayout.LayoutParams paramsCardView = new LinearLayout.LayoutParams(224,
                        200);
                paramsCardView.leftMargin = 10;
                binding.cardviewId.setLayoutParams(paramsCardView);


                binding.bookImgId.setVisibility(View.GONE);

                FrameLayout.LayoutParams paramsTitle = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsTitle.leftMargin = 10;
                paramsTitle.topMargin = 10;
                binding.bookTitleId.setLayoutParams(paramsTitle);

                FrameLayout.LayoutParams paramsPrice = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        70);
                paramsPrice.leftMargin = 10;
                paramsPrice.topMargin = 90;
                binding.bookPriceId.setLayoutParams(paramsPrice);

                RelativeLayout.LayoutParams totalCountImg = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                totalCountImg.leftMargin = 80;
                totalCountImg.topMargin = 90;
                binding.txtItemCcount.setLayoutParams(totalCountImg);
            }
        }
        counter = new int[getItemCount()];
//        mHandler = new Handler();
//        m_Runnable.run();
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position_val) {

        final String openPriceVar = mData.get(position_val).getOpenPrice();
        final String imgVar = mData.get(position_val).getThumbnail();
        final String priceVar = mData.get(position_val).getPrice();
        final String prodIDVar = mData.get(position_val).getProductID();
        final String titleVar = mData.get(position_val).getTitle();
        final String remarksVar = mData.get(position_val).getRemarks();

        holder.binding.bookTitleId.setText(titleVar);

        holder.binding.cardviewId.setTag(position_val);

        if (priceVar.length() > 0) {
            holder.binding.bookPriceId.setText("$" + String.format("%.2f", Double.valueOf(priceVar)));
        }else {
            holder.binding.bookPriceId.setText("$0.00");
        }

        String chk_hide_img = Query.GetOptions(20);
        if (chk_hide_img.equals("0")) {
            //content://media/external/images/media/41
            if (imgVar == null || imgVar.equals("0") || imgVar.isEmpty()) {
                Picasso.with(mContext).load(R.drawable.default_no_image).into(holder.binding.bookImgId);
            } else {
                String url_name = Query.GetImageURLForPLU(mData.get(position_val).getUUID());
                if (url_name != null && url_name.length() > 23){
                        Picasso.with(mContext).load(url_name).into(holder.binding.bookImgId);
                }else {
                    try {
                        Picasso.with(mContext).load(imgVar).into(holder.binding.bookImgId);
                    } catch (IllegalStateException e) {
                        Picasso.with(mContext).load(R.drawable.default_no_image).into(holder.binding.bookImgId);
                    }
                }
            }
        }
        if (str.equals("product")) {

            hashValues = Query.GetProductByID(MainActivity.strbillNo);


            setColor(holder,position_val);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditProductSheetFragment.productID = "0";
                    EditProductSheetFragment.productName = "0";
                    EditProductSheetFragment.EditFragmentPrice = "0";
                    EditProductSheetFragment.productID = "0";
                    EditProductSheetFragment.DetailBillID = "0";
                    EditProductSheetFragment.FragmentVar = "0";

                    RecyclerViewAdapter.slddisID.clear();
                    RecyclerViewAdapter.slddisName.clear();
                    RecyclerViewAdapter.slddisTypeName.clear();
                    RecyclerViewAdapter.slddisType.clear();
                    RecyclerViewAdapter.slddisValue.clear();
                    RecyclerViewAdapter.sltBillDisArr.clear();

                    Log.i("remarksVar___","remarksVar___"+remarksVar);
                    Log.i("openPriceVar___","openPriceVar___"+openPriceVar);
                    if (openPriceVar != null && openPriceVar.equals("1")) {
                        OpenPriceDialog(mContext, prodIDVar, titleVar ,holder,position_val,remarksVar);
                    }else {
                        Log.i("remarksVar___","remarksVar___"+remarksVar);
                        Log.i("openPriceVar___","openPriceVarelse___"+openPriceVar);
                        if (remarksVar != null && remarksVar.equals("1")) {
                            Log.i("rprodIDVar__","rprodIDVar__"+prodIDVar);
                            remarksDialog(mContext, holder , position_val , MainActivity.strbillNo, prodIDVar , openPriceVar,
                                    titleVar,"normal",remarksVar,priceVar);
                        }else {
                            NormalFun(holder,position_val,"normal",remarksVar);
                        }
                    }
                }
            });


            holder.itemView.setLongClickable(true);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    EditProductSheetFragment.productID = "0";
                    EditProductSheetFragment.productName = "0";
                    EditProductSheetFragment.EditFragmentPrice = "0";
                    EditProductSheetFragment.productID = "0";
                    EditProductSheetFragment.DetailBillID = "0";
                    EditProductSheetFragment.FragmentVar = "0";

                    Integer billDetailsPID = 0;
                    String sqll = "Select BillNo,ID from DetailsBillProduct " +
                            "Where BillNo = '"+MainActivity.strbillNo+"' " +
                            "AND ProductId = '"+prodIDVar+"' " ;
                    if (openPriceVar != null && !(openPriceVar.equals("0"))){
                        sqll += "AND OpenPriceStatus = '" + openPriceVar + "'";
                    }
                    Log.i("sqll___","longclock_sqll"+sqll);
                    Cursor ccc = DBFunc.Query(sqll,false);
                    if (ccc != null) {
                        billDetailsPID = 0;
                        if (ccc.getCount() == 0){
                            Log.i("sqll___","longclock_openPriceVarl"+openPriceVar);
                            if (openPriceVar != null && !(openPriceVar.equals("0"))){//not open price
                                Toast.makeText(mContext, "Open Price. \n Please edit at checkout page. ", Toast.LENGTH_SHORT).show();
                            }else {
                                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                        //.setTitleText("Cancelled Bill")
                                        .setContentText(Constraints.SelectProduct)
                                        .setConfirmText(Constraints.OK)
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        }else {
                            if (ccc.moveToNext()) {
                                EditProductSheetFragment.productID = "0";
                                EditProductSheetFragment.productName = "0";
                                EditProductSheetFragment.EditFragmentPrice = "0";
                                EditProductSheetFragment.productID = "0";
                                EditProductSheetFragment.DetailBillID = "0";
                                EditProductSheetFragment.FragmentVar = "0";
                                Log.i("sqll___","longclock_openPriceVarl"+openPriceVar);
                                if (openPriceVar == null || openPriceVar.equals("0")) {
                                    Log.i("sqll___","lremarksVarnPriceVarl"+remarksVar);
                                    if (remarksVar.equals("0")) {
                                        Log.i("sqll___","lremarksVarnPriceValll"+remarksVar);

                                        billDetailsPID = ccc.getInt(1);

                                        EditFragmentFun(holder, position_val,billDetailsPID);
                                    } else {
                                        Toast.makeText(mContext, "Item Remarks. \n Please edit at checkout page. ", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mContext, "Open Price. \n Please edit at checkout page. ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        ccc.close();
                    }
                    return true;
                }
            });

        }else if (str.equals("fragment4")) {
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("name", "Fragment____0");
            mContext.startActivity(intent);
            ((Activity)mContext).finish();
        }else{
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getAdapterPosition();
//                    String selectedID = mData.get(position).getProductID();
//                    String selectedCatID = mData.get(position).getProductCategoryID();
//                    String selectedCatName = mData.get(position).getProductCategoryName();
//                    Log.i("_selectedCatID",selectedCatID);
//                    Log.i("_selectedCatName",selectedCatName);
//                    Log.i("selectedID",selectedID);
//                    //Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
//                    //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
//                    Intent intent = new Intent(mContext, AddNewProductActivity.class);
//                    intent.putExtra("ID", String.valueOf(selectedID));
//                    mContext.startActivity(intent);
//                }
//            });
//
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


//        new Thread(new Runnable() {
//            @Override
//            public void run () {
//                functionReload(holder, holder, position);
//            }
//        }).start();
    }
//    public static EditText pop_up_window_item_remarks;
//    public static Button btn_cancel_pop_up_window_itemremarks;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("CheckStartActivity","onActivityResult and resultCode = "+resultCode);
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//        this.finish();
//    }

    public static MyViewHolder _holder;
    public static Integer _position_val;
    EditText pop_up_window_item_remarks;
    private void remarksDialog(Context context, MyViewHolder holder, int position_val,String billno, String productID, String openPriceVariable,
                               String titleVar,String status,String remarksVar,String priceVar) {

        Log.i("openPriceVar___","oremarksDialog___"+openPriceVariable);

        MainActivity.binding.container.setAlpha(0.4F);

//
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.activity_item_remarks_pop_up, null);



        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        LinearLayout lvclose = (LinearLayout) popupView.findViewById(R.id.linear_close) ;


        pop_up_window_item_remarks = (EditText) popupView.findViewById(R.id.pop_up_window_itemremarks) ;
        pop_up_window_item_remarks.setOnKeyListener(this);

//        pop_up_window_item_remarks.setText();
//
//        Log.i("dsf___","remarkScanValue____"+remarkScanValue);
//        if (remarkScanValue != null && remarkScanValue.trim().length() > 0){
//            if (remarkScanValue.length() > 9) {
//                String remarkScanValueSubstring = remarkScanValue.substring(4, 8);
//                pop_up_window_item_remarks.setText(remarkScanValueSubstring);
//            }
//            remarkScanValue = "";
//        }
//        //pop_up_window_item_remarks.setText(remarks);
////        pop_up_window_item_remarks.setText("testing3");

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.update();
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        popupWindow.showAsDropDown(popupView, 0, 0);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        Button btn_add_pop_up_window_itemremarks = (Button) popupView.findViewById(R.id.btn_add_pop_up_window_itemremarks);
        btn_add_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String Name = pop_up_window_item_remarks.getText().toString();
//                if (!Name.matches("[a-zA-Z ]+")) {
                    // Toast.makeText(getContext(), "Update Successfully!", Toast.LENGTH_LONG).show();

                Log.i("barcodeee____","barcodeee____onClickkkkk");
                Log.i("barcodeee____","barcodeee____onClickkkkk1"+pop_up_window_item_remarks.getText().toString());
                Log.i("barcodeee____","barcoopenPriceVariable"+openPriceVariable);
                    updateAndRefreshRemarks(pop_up_window_item_remarks.getText().toString(),billno,productID,openPriceVariable,titleVar,
                            holder,position_val,status,remarksVar,priceVar);
                    //onResume();
                Log.i("barcodeee____","barcodeee____onClickkkkk");
                    holder.binding.cardviewId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
                    //holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.test1));
                    holder.binding.bookTitleId.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.binding.bookPriceId.setTextColor(ContextCompat.getColor(mContext, R.color.white));

                    holder.binding.txtItemCcount.setVisibility(View.VISIBLE);

                        popupWindow.dismiss();

                    MainActivity.binding.container.setAlpha(1);

//                } else {
//                    pop_up_window_item_remarks.setError("ENTER ONLY ALPHABETICAL CHARACTER");
//                }



                //NormalFun(holder,position_val);
            }
        });
        //Button btn_cancel_pop_up_window_itemremarks = (Button) popupView.findViewById(R.id.btn_cancel_pop_up_window_itemremarks) ;
        Button btn_cancel_pop_up_window_itemremarks = (Button) popupView.findViewById(R.id.btn_cancel_pop_up_window_itemremarks) ;
        btn_cancel_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popupWindow.dismiss();
//                //onResume();
//                MainActivity.binding.container.setAlpha(1);
//
//                getBillByBillNo();
//
//                hashValues = Query.GetProductByID(MainActivity.strbillNo);
//                //Query.GetProductByID(MainActivity.strbillNo);
//                setColor(holder,position_val);
//
//                CheckoutButtonValues(totalItems, totalAmount);

                updateAndRefreshRemarks(pop_up_window_item_remarks.getText().toString(),billno,productID,openPriceVariable,titleVar,
                        holder,position_val,status,remarksVar,priceVar);
                //onResume();
                Log.i("barcodeee____","barcodeee____onClickkkkk");
                holder.binding.cardviewId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
                //holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.test1));
                holder.binding.bookTitleId.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                holder.binding.bookPriceId.setTextColor(ContextCompat.getColor(mContext, R.color.white));

                holder.binding.txtItemCcount.setVisibility(View.VISIBLE);

                popupWindow.dismiss();

                MainActivity.binding.container.setAlpha(1);
            }
        });
        ImageView remarks_clear_itemremarks = (ImageView) popupView.findViewById(R.id.remarks_clear) ;
        remarks_clear_itemremarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popupWindow.dismiss();
                //onResume();
//                MainActivity.binding.container.setAlpha(1);
                pop_up_window_item_remarks.setText("");
            }
        });

        ImageView btn_cancel_pop_up_window_itemremarks_scan = (ImageView) popupView.findViewById(R.id.remarks_scan) ;
        btn_cancel_pop_up_window_itemremarks_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // popupWindow.dismiss();
                //onResume();
                Intent intent_scan = new Intent(context, RemarkScanActivity.class);
//            intent_scan.putExtra("scan_value","search");
                ((Activity)context).startActivity(intent_scan);
                ((Activity)context).finish();
            }
        });

        lvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                //onResume();
                MainActivity.binding.container.setAlpha(1);
            }
        });

//        popupView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("jererererer__","onClickkk");
//                MainActivity.binding.container.setAlpha(1);
//            }
//        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.i("dsfsdf___","___onDismiss");
                //Do Something here
                MainActivity.binding.container.setAlpha(1);
            }
        });

//        popupWindow.setTouchInterceptor(new View.OnTouchListener()
//        {
//
//
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                Log.i("dsfsdf___","___"+event.getAction());
////                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
//                if (event.getAction() == 1)
//                {
//                    popupWindow.dismiss();
//                    MainActivity.binding.container.setAlpha(1);
//                    return true;
//                }
//
//                return false;
//            }
//        });

//        MainActivity.binding.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("jererererer__","onClick");
//                MainActivity.binding.container.setAlpha(1);
//            }
//        });

//        Button btnDismiss = (Button) popupView.findViewById(R.id.btn_dismiss);
//        btnDismiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });


//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        LinearLayout lay = (LinearLayout) inflater.inflate(R.layout.activity_item_remarks_pop_up, null, false);
//
//        final View popUpView = inflater.inflate(R.layout.activity_item_remarks_pop_up,
//                (ViewGroup) lay.findViewById(R.id.fadePopup));
//
//
//        PopupWindow mpopup = new PopupWindow(popUpView, LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT, true); // Creation of popup
//        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
//
//        mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
//        mpopup.setOutsideTouchable(false);

    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        Log.i("dsfsdf_____","sdf_____"+motionEvent.toString());
//        Log.i("event___","event____"+pop_up_window_item_remarks.getText().toString());
//        return false;
//    }
//    String barcode = "";
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        char pressedKey = (char) keyEvent.getUnicodeChar();
//        barcode += pressedKey;
//        Log.i("dsfsdf_____","sdf_barcode____"+barcode);
        Log.i("dsfsdf_____","sdf_Eve___"+keyEvent.getCharacters());
        Log.i("event___","event_te___"+pop_up_window_item_remarks.getText().toString());
//        G3825221L05122018#
//        barcode = pop_up_window_item_remarks.getText().toString();
//        if (barcode != null && barcode.trim().length() > 17){
//        if (barcode != null && barcode.trim().length() > 9){
//        if (barcode != null && barcode.trim().length() > 7){
//            pop_up_window_item_remarks.setText("");
//            String barcodeRead = barcode.substring(0,1);
//            Log.i("aaaa___","aaaa______"+barcodeRead);
//                if (aaaa.startsWith("G")) {
//            if (barcodeRead.startsWith("A") || barcodeRead.startsWith("B") || barcodeRead.startsWith("C") || barcodeRead.startsWith("D") || barcodeRead.startsWith("E") ||
//                    barcodeRead.startsWith("F") || barcodeRead.startsWith("G") || barcodeRead.startsWith("H") || barcodeRead.startsWith("I") || barcodeRead.startsWith("J") ||
//                    barcodeRead.startsWith("K") || barcodeRead.startsWith("L") || barcodeRead.startsWith("M") || barcodeRead.startsWith("N") || barcodeRead.startsWith("O") || barcodeRead.startsWith("P") ||
//                    barcodeRead.startsWith("Q") || barcodeRead.startsWith("R") || barcodeRead.startsWith("S") || barcodeRead.startsWith("T") || barcodeRead.startsWith("U") ||
//                    barcodeRead.startsWith("V") || barcodeRead.startsWith("W") || barcodeRead.startsWith("X") || barcodeRead.startsWith("Y") || barcodeRead.startsWith("Z")) {
//                Log.i("aaaa___","aaaa______if"+barcodeRead);
//                Log.i("aaaa___","aaaa______if"+barcodeRead);
//                pop_up_window_item_remarks.setText(barcode.substring(5, 9));
//                pop_up_window_item_remarks.setText(barcode);


         String barcodescanner = pop_up_window_item_remarks.getText().toString();
        Log.i("__actual", "barcode__origin" + barcodescanner);

       //barcode = barcodescanner;
        if (barcodescanner.endsWith("#")) {
            Log.i("__ac#", "barcode__#_" + barcodescanner);
            Log.i("__acsubstring", "barcode__substring_" + barcodescanner.substring(0,9));

            pop_up_window_item_remarks.setText(barcodescanner.substring(0,9));

            return false;
        }

//        String barcode = pop_up_window_item_remarks.getText().toString();
//        Log.i("barcode___","barcodeoverall____"+barcode);
//        if (barcode != null && barcode.length() == 8) {
//
//            Log.i("barcode___","barcodemorethan8____"+barcode);
//            pop_up_window_item_remarks.setText(barcode);
//
////            if (barcode.length() == 9){
////                Log.i("barcode___","barcodemequal9____"+barcode);
////                pop_up_window_item_remarks.setText(barcode);
////                //                    }else if (barcode.length() > 9){
////            }else if (barcode.length() == 16){
////                Log.i("barcode___","barcodemequal16____"+barcode);
////                //81022050G7483438N
////                //81022150G3825221L
////                //                        pop_up_window_item_remarks.setText(barcode.substring(8,16));
////                pop_up_window_item_remarks.setText(barcode.substring(8,16));
////                //                        pop_up_window_item_remarks.setText(barcode);
////                Log.i("barcode___","barcode____"+barcode);
////            }
//            return false;
//
//        }

//                String barcode = pop_up_window_item_remarks.getText().toString();
//                Log.i("barcode___","barcodeoverall____"+barcode);
//                if (barcode != null && barcode.length() > 8) {
//                    Log.i("barcode___","barcodemorethan8____"+barcode);
//                    if (barcode.length() == 9){
//                        Log.i("barcode___","barcodemequal9____"+barcode);
//                        pop_up_window_item_remarks.setText(barcode);
//        //                    }else if (barcode.length() > 9){
//                    }else if (barcode.length() == 16){
//                        Log.i("barcode___","barcodemequal16____"+barcode);
//                        //81022050G7483438N
//                        //81022150G3825221L
//        //                        pop_up_window_item_remarks.setText(barcode.substring(8,16));
//                        pop_up_window_item_remarks.setText(barcode.substring(8,16));
//        //                        pop_up_window_item_remarks.setText(barcode);
//                        Log.i("barcode___","barcode____"+barcode);
//                    }
//                }
//                if (barcode != null && barcode.length() > 8) {
//                    if (barcode.length() == 9){
//                        pop_up_window_item_remarks.setText(barcode);
////                    }else if (barcode.length() > 9){
//                    }else if (barcode.length() == 16){
//                        //81022050G7483438N
//                        //81022150G3825221L
////                        pop_up_window_item_remarks.setText(barcode.substring(8,16));
//                        pop_up_window_item_remarks.setText(barcode.substring(8,16));
////                        pop_up_window_item_remarks.setText(barcode);
//                        Log.i("barcode___","barcode____"+barcode);
//                    }
//
//                }
                pop_up_window_item_remarks.setTextColor(Color.BLACK);
//            } else {
//                Log.i("aaaa___","aaaa______else"+barcodeRead);
//            }
//        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("DSfdsf___","rer___"+motionEvent.toString());
        MainActivity.binding.container.setAlpha(1);
        return false;
    }

    public static class CustomPopUp extends PopupWindow {
        EditText pop_up_window_item_remarks;

        private final View.OnTouchListener customPopUpTouchListenr = new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View vv, final MotionEvent event) {


                Log.i("event___","event____"+pop_up_window_item_remarks.getText().toString());
                Log.i("event___","event____"+event.toString());

                return false;
            }
        };

        public CustomPopUp(final View theView) {
            super(theView);

            pop_up_window_item_remarks = (EditText) theView.findViewById(R.id.pop_up_window_itemremarks) ;
            initView();

            setTouchInterceptor(customPopUpTouchListenr);

        }

        private void initView() {
            setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

            setTouchable(true);
            setOutsideTouchable(true);

            setBackgroundDrawable(new ColorDrawable());

            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            update();
//            showAsDropDown(popupView, 0, 0);
//            showAtLocation(popupView, Gravity.CENTER, 0, 0);;

//            /        popupWindow.update();
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
////        popupWindow.showAsDropDown(popupView, 0, 0);
//        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);;
//
//            new CustomPopUp(popupView).showAtLocation(popupView, Gravity.CENTER, 0, 0);
//            new CustomPopUp(popupView).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        }

    }


//    public static void updateAndRefreshRemarks(String strRemarks, String billNo, String popupProductID,
//                                               String EditFragmentOpenPrice, String titleVar, MyViewHolder holder, int position_val,
//                                               String status,String remarksVar,String priceVar) {
//
//        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//        String dateFormat4 = CheckOutActivity.dateFormat555.format(new Date()).toString();
//
//        String chkExisting = TransactionDetailsActivity.ValidAndGetValue("BillNo", "DetailsBillProduct", "BillNo",
//                MainActivity.strbillNo, false);
//
//        //Bill No Empty at DetailsBillProduct //Need to save
//        if (chkExisting.equals("0")) {
//            //NormalFun(holder,position_val);Remarks Details
//            //If not OpenPrice
//            if (!status.equals("openPrice")) {
//                //Save wih openprice status
//                AddNewDetailsBillProduct(holder, position_val,status,remarksVar);
//            }
//
//            //Update Remarks for OpenPrice
//           Query.UpdateDetailsBillProduct("RecyVAdapExBZero",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,"0");
//
//            //Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,"0");
//        } else {
//
//           Query.UpdateDetailsBillProduct("Update_Details_BillProduct_with_OpenPrice_Status",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,"0");
//
////             String chkExistingRemarks = TransactionDetailsActivity.ValidAndGetValue("Remarks", "DetailsBillProduct", "Remarks",
////                strRemarks, false);
////            Log.i("chkExistingRemarks_____","chkExistingRemarks_____"+chkExistingRemarks);
//
////            //If Bill No already has at Details Bill Product
////            //If Open Price
////            if (status.equals("openPrice")) {
////                //Update_Details_BillProduct_with_OpenPrice_Status
////                Query.UpdateDetailsBillProduct("Update_Details_BillProduct_with_OpenPrice_Status",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,"0");
////
////               //Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,"0");
////            } else {
////                //If Bill No already has at Details Bill Product
////                //Checking Reamrks
////                String chkExistingRemarks = "";
////                String searchRemarkQty = "SELECT SUM(ProductQty),Remarks FROM DetailsBillProduct WHERE BillNo = '" + billNo + "' " +
////                        " AND ProductID = '"+popupProductID+"' " ;
////                if (status.equals("openPrice")) {
////                    searchRemarkQty += " AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
////                }
////
////                if (strRemarks != null && strRemarks.trim().length() > 0 && !(strRemarks.equals("0")) && !(strRemarks.trim() == null)
////                        && !(strRemarks.trim().equals("null"))) {
////                    searchRemarkQty += " AND Remarks = '"+strRemarks+"' " ;
////                }else {
////                    searchRemarkQty += " AND ( Remarks IS NULL OR Remarks = '' OR Remarks = 'null' OR Remarks = '0' ) ";
////                }
////                Log.i("searchRemarkQty__","searchRemarkQty_____"+searchRemarkQty);
////                Integer existingsearchRemarkQty =  0;
////                Cursor csearchRemarkQty = DBFunc.Query(searchRemarkQty,false);
////                if (csearchRemarkQty != null) {
////                    if (csearchRemarkQty.moveToNext()) {
////                        existingsearchRemarkQty = csearchRemarkQty.getInt(0);
////                        chkExistingRemarks = csearchRemarkQty.getString(1);
////                    }
////                    csearchRemarkQty.close();
////                }
////                Log.i("update___","existingsearchRemarkQty___"+existingsearchRemarkQty);//0
////                Log.i("update___","uchkExistingRemarks___"+chkExistingRemarks);//null
//////                if (chkExistingRemarks.equals("0")) {
////
////                //If Bill No already has at Details Bill Product
////                //If Existing Remarks , not allow duplicate remarks
////                if (chkExistingRemarks != null && chkExistingRemarks.trim().length() > 0 && !(chkExistingRemarks.equals("0")) && !(chkExistingRemarks.trim() == null) && !(chkExistingRemarks.trim() == "null")) {
////
////
////                } else {
////                    //If Bill No already has at Details Bill Product
////                    //If Not Existing Remarks , get Existing Qty
////                    // If Qty empty => then need to save new record
////                    if (existingsearchRemarkQty != null && existingsearchRemarkQty == 0) {
////
////                        String detailsBillProductsql = "SELECT OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID, " +
////                                "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel, " +
////                                "TaxID,TaxType,TaxName,TaxAmount " +
////                                "FROM DetailsBillProduct " +
//////                            "where BillNo = '" + billNo + "'";
////                                "WHERE BillNo = '" + billNo + "' " +
////                                " AND ProductID = '" + popupProductID + "' ";
////                        if (status.equals("openPrice")) {
////                            detailsBillProductsql += "AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
////                        }
//////                            " AND Remarks = '"+strRemarks+"' " ;
////                        Log.i("__sqgctsql", detailsBillProductsql);
////
////                        Cursor c = DBFunc.Query(detailsBillProductsql, false);
////                        String OnlineOrderBill = "";
////                        //String ProductQty = c.getString(1);
////                        String ProductQty = "";
////                        String ProductName = "";
////                        String ProductPrice = "";
////                        String BillDetailsID = "";
//////                        String ItemDiscountAmount = "";
////                        String ProductID = "";
////                        String CategoryID = "";
////                        String CategoryName = "";
////                        String vchQueueNo = "";
////                        String intTableNo = "";
////                        String Cancel = "";
////                        String TaxID = "";
////                        String TaxType = "";
////                        String TaxName = "";
////                        String TaxAmount = "";
////                        String DisID = "";
////                        String DisName = "";
////                        String DisType = "";
////                        String DisTypeName = "";
////                        String DisValue = "";
////                        String AfterDisAmount = "";
////                        ;
////                        String OpenPriceStatus = "";
////                        String BeforeOpenPrice = "";
////                        if (c != null) {
////                            while (c.moveToNext()) {
////
////                                OnlineOrderBill = c.getString(0);
//////                            ProductQty = c.getString(1);
////                                ProductQty = "1";
////                                ProductName = c.getString(2);
////                                ProductPrice = c.getString(3);
////                                BillDetailsID = c.getString(4);
//////                                ItemDiscountAmount = c.getString(5);
////                                ProductID = c.getString(6);
////                                CategoryID = c.getString(7);
////                                CategoryName = c.getString(8);
////                                vchQueueNo = c.getString(9);
////                                intTableNo = c.getString(10);
////                                Cancel = c.getString(11);
////                                TaxID = c.getString(12);
////                                TaxType = c.getString(13);
////                                TaxName = c.getString(14);
////                                TaxAmount = c.getString(15);
//////                                DisID = c.getString(16);
//////                                DisName = c.getString(17);
//////                                DisType = c.getString(18);
//////                                DisTypeName = c.getString(19);
//////                                DisValue = c.getString(20);
////                                //AfterDisAmount = c.getString(21);
////                                //OpenPriceStatus = c.getString(22);
////                                //BeforeOpenPrice = c.getString(23);
////                            }
////                            c.close();
////                        }
////
////
////                        if (ProductID != null && ProductID.trim().length() > 0 && !(ProductID.trim() == null) && !(ProductID.trim() == "null")) {
////
////
////                            String sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
////                                    "ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel," +
////                                    "BillID,TaxID,TaxType,TaxName,TaxAmount,Remarks,DateTime) VALUES (";
//////                            ,DiscountID,DiscountName,DiscountType,DiscountTypeName," +
//////                            "DiscountValue,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,
////                            sql += "'" + UUID.randomUUID().toString() + "', ";
////                            sql += "'" +  billNo + "', ";
////                            sql += "'" + OnlineOrderBill + "', ";
//////                        sql += "'" + (Integer.parseInt(ProductQty) + 1) + "', ";
////                            sql += "'" + ProductQty + "', ";
////                            sql += "'" + ProductName + "', ";
////                            try {
////                                if (ProductPrice != null && ProductPrice.length() > 0) {
//////                                    sql += "'" + String.format("%.2f", (Double.valueOf(ProductPrice) )) + "', ";
////                                    sql += "'" + String.format("%.2f", (Double.valueOf(priceVar))) + "', ";
////                                } else {
////                                    sql += "'0.0', ";
////                                }
////                            } catch (Exception e) {
////                                sql += "'0.0', ";
////                            }
////                            sql += "'" + BillDetailsID + "', ";
////                            sql += "'" + dateFormat3 + "', ";
////                            sql += "'" + ProductID + "', ";
////                            sql += "'" + CategoryID + "', ";
////                            sql += "'" + CategoryName + "', ";
////                            sql += "'" + vchQueueNo + "', ";
////                            sql += "'" + intTableNo + "', ";
////                            sql += "'" + Cancel + "', ";
////                            sql += "'" + MainActivity.BillID + "', ";
////                            sql += "'" + TaxID + "', ";
////                            sql += "'" + TaxType + "', ";
////                            sql += "'" + TaxName + "', ";
//////                    try {
////                            if (TaxAmount != null && TaxAmount.length() > 0) {
////                                sql += "'" + String.format("%.2f", Double.valueOf(TaxAmount)) + "', ";
////                            } else {
////                                sql += "'0.0', ";
////                            }
////
////                            sql += "'" + strRemarks + "', ";
////                            sql += System.currentTimeMillis() + ")";
////
////                            Log.i("sql__", "sql_dddfd___" + sql);dd
////                            DBFunc.ExecQuery(sql, false);
////
////                            //Integer detailsBillPId = Query.findLatestID("DetailsBillProduct","ID",false);
////
////
////                          // Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,String.valueOf(detailsBillPId));
////                        } else {
////                            AddNewDetailsBillProduct(holder, position_val,status,remarksVar);
////                        }
////                    }
////                }
//
//            //}
//        }
//
////        String updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
////                "SET Remarks = '"+strRemarks+"' AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' " +
////                "WHERE BillNo = '" + billNo + "' " +
////                " AND ProductID = '"+popupProductID+"' AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' ";
////        Log.i("update___","updupdateRemarksDetatilsBilll___"+updateRemarksDetatilsBill);
////        DBFunc.ExecQuery(updateRemarksDetatilsBill, false);
//
//        getBillByBillNo();
//
//        hashValues = Query.GetProductByID(MainActivity.strbillNo);
//        //Query.GetProductByID(MainActivity.strbillNo);
//
//
////        CheckoutButtonValues(totalItems, totalAmount);
//
//
//        MainCheckoutButtonUpdate(holder,position_val);
//
//
////        FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
////        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
////        editProductSheetFragment.setCancelable(false);
////        editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
//
//
//
////        Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice);
//
//    }

     public static void updateAndRefreshRemarks(String strRemarks, String billNo, String popupProductID,
                                               String EditFragmentOpenPrice, String titleVar, MyViewHolder holder, int position_val,
                                               String status,String remarksVar,String priceVar) {
        Log.i("productId_","productid_____"+popupProductID);
        Log.i("productId_","EditFragmentOpenPrice_____"+EditFragmentOpenPrice);
//        MainActivity.binding.container.setAlpha(0.0F);
//        MainActivity.binding.container.setAlpha(1);
//        String updateItemRemarksPLU = "UPDATE PLU SET Remarks = '"+strRemarks+
//                "' WHERE ID = "+ popupProductID;
//        Log.i("update___","update___"+updateItemRemarksPLU);
//        DBFunc.ExecQuery(updateItemRemarksPLU,true);

        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
        String dateFormat4 = CheckOutActivity.dateFormat555.format(new Date()).toString();

        String chkExisting = TransactionDetailsActivity.ValidAndGetValue("BillNo", "DetailsBillProduct", "BillNo",
                MainActivity.strbillNo, false);

        if (chkExisting.equals("0")) {
            //NormalFun(holder,position_val);
            if (!status.equals("openPrice")) {
                AddNewDetailsBillProduct(holder, position_val,status,remarksVar,strRemarks);
            }

            String updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
                    "SET Remarks = '"+strRemarks+"' " ;
            if (status.equals("openPrice")) {
                updateRemarksDetatilsBill += " AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
            }
            updateRemarksDetatilsBill += "WHERE BillNo = '" + billNo + "' " +
                    " AND ProductID = '"+popupProductID+"' " ;
            if (status.equals("openPrice")) {
                //if (EditFragmentOpenPrice.equals("0")) {
                    updateRemarksDetatilsBill += "AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
                //}
            }
//            if (EditFragmentOpenPrice.equals("0")) {
//                    updateRemarksDetatilsBill += "AND ( OpenPriceStatus = '" + EditFragmentOpenPrice + "' OR " +
//                            "OpenPriceStatus IS NULL OR  OpenPriceStatus = '' )";
//                }

            DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

            //Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,"0");
        } else {
//             String chkExistingRemarks = TransactionDetailsActivity.ValidAndGetValue("Remarks", "DetailsBillProduct", "Remarks",
//                strRemarks, false);
//            Log.i("chkExistingRemarks_____","chkExistingRemarks_____"+chkExistingRemarks);


            if (status.equals("openPrice")) {
                String updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
                        "SET Remarks = '"+strRemarks+"' " +
                        "WHERE BillNo = '" + billNo + "' " +
                        " AND ProductID = '"+popupProductID+"' AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' ";

                DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

               //Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,"0");
            } else {
                String chkExistingRemarks = "";
                String searchRemarkQty = "SELECT SUM(ProductQty),Remarks FROM DetailsBillProduct WHERE BillNo = '" + billNo + "' " +
                        " AND ProductID = '"+popupProductID+"' " ;
                if (status.equals("openPrice")) {
                    searchRemarkQty += " AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
                }

                if (strRemarks != null && strRemarks.trim().length() > 0 && !(strRemarks.equals("0")) && !(strRemarks.trim() == null)
                        && !(strRemarks.trim().equals("null"))) {
                    searchRemarkQty += " AND Remarks = '"+strRemarks+"' " ;
                }else {
                    searchRemarkQty += " AND ( Remarks IS NULL OR Remarks = '' OR Remarks = 'null' OR Remarks = '0' ) ";
                }
                Log.i("searchRemarkQty__","searchRemarkQty_____"+searchRemarkQty);
                Integer existingsearchRemarkQty =  0;
                Cursor csearchRemarkQty = DBFunc.Query(searchRemarkQty,false);
                if (csearchRemarkQty != null) {
                    if (csearchRemarkQty.moveToNext()) {
                        existingsearchRemarkQty = csearchRemarkQty.getInt(0);
                        chkExistingRemarks = csearchRemarkQty.getString(1);
                    }
                    csearchRemarkQty.close();
                }

//                if (chkExistingRemarks.equals("0")) {
                if (chkExistingRemarks != null && chkExistingRemarks.trim().length() > 0 && !(chkExistingRemarks.equals("0")) && !(chkExistingRemarks.trim() == null) && !(chkExistingRemarks.trim() == "null")) {
//                    new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
//                            //.setTitleText("Cancelled Bill")
//                            .setContentText("Duplicate!")
//                            .setConfirmText(Constraints.OK)
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                }
//                            })
//                            .show();

//                    String updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
//                            "SET ProductQty = '" + ( existingsearchRemarkQty + 1 ) + "' " +
//                            "WHERE BillNo = '" + billNo + "' " +
//                            " AND ProductID = '" + popupProductID + "' AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' " +
//                            " AND Remarks = '" + chkExistingRemarks + "' ";
//                    Log.i("update___", "updupdateRemarksDetatilsBill___" + updateRemarksDetatilsBill);
//                    DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

                } else {

                    if (existingsearchRemarkQty != null && existingsearchRemarkQty == 0) {

                        String detailsBillProductsql = "SELECT OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID, " +
                                "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel, " +
                                "TaxID,TaxType,TaxName,TaxAmount " +
                                "FROM DetailsBillProduct " +
//                            "where BillNo = '" + billNo + "'";
                                "WHERE BillNo = '" + billNo + "' " +
                                " AND ProductID = '" + popupProductID + "' ";
                        if (status.equals("openPrice")) {
                            detailsBillProductsql += "AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
                        }
//                            " AND Remarks = '"+strRemarks+"' " ;
                        Log.i("__sqgctsql", detailsBillProductsql);

                        Cursor c = DBFunc.Query(detailsBillProductsql, false);
                        String OnlineOrderBill = "";
                        //String ProductQty = c.getString(1);
                        String ProductQty = "";
                        String ProductName = "";
                        String ProductPrice = "";
                        String BillDetailsID = "";
//                        String ItemDiscountAmount = "";
                        String ProductID = "";
                        String CategoryID = "";
                        String CategoryName = "";
                        String vchQueueNo = "";
                        String intTableNo = "";
                        String Cancel = "";
                        String TaxID = "";
                        String TaxType = "";
                        String TaxName = "";
                        String TaxAmount = "";
                        String DisID = "";
                        String DisName = "";
                        String DisType = "";
                        String DisTypeName = "";
                        String DisValue = "";
                        String AfterDisAmount = "";
                        ;
                        String OpenPriceStatus = "";
                        String BeforeOpenPrice = "";
                        if (c != null) {
                            while (c.moveToNext()) {

                                OnlineOrderBill = c.getString(0);
//                            ProductQty = c.getString(1);
                                ProductQty = "1";
                                ProductName = c.getString(2);
                                ProductPrice = c.getString(3);
                                BillDetailsID = c.getString(4);
//                                ItemDiscountAmount = c.getString(5);
                                ProductID = c.getString(6);
                                CategoryID = c.getString(7);
                                CategoryName = c.getString(8);
                                vchQueueNo = c.getString(9);
                                intTableNo = c.getString(10);
                                Cancel = c.getString(11);
                                TaxID = c.getString(12);
                                TaxType = c.getString(13);
                                TaxName = c.getString(14);
                                TaxAmount = c.getString(15);
//                                DisID = c.getString(16);
//                                DisName = c.getString(17);
//                                DisType = c.getString(18);
//                                DisTypeName = c.getString(19);
//                                DisValue = c.getString(20);
                                //AfterDisAmount = c.getString(21);
                                //OpenPriceStatus = c.getString(22);
                                //BeforeOpenPrice = c.getString(23);
                            }
                            c.close();
                        }


                        if (ProductID != null && ProductID.trim().length() > 0 && !(ProductID.trim() == null) && !(ProductID.trim() == "null")) {

                            String sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
                                    "ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel," +
                                    "BillID,TaxID,TaxType,TaxName,TaxAmount,Remarks,DateTime) VALUES (";
//                            ,DiscountID,DiscountName,DiscountType,DiscountTypeName," +
//                            "DiscountValue,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,
                            sql += "'" + UUID.randomUUID().toString() + "', ";
                            sql += "'" + billNo + "', ";
                            sql += "'" + OnlineOrderBill + "', ";
//                        sql += "'" + (Integer.parseInt(ProductQty) + 1) + "', ";
                            sql += "'" + ProductQty + "', ";
                            sql += "'" + ProductName + "', ";
                            try {
                                if (ProductPrice != null && ProductPrice.length() > 0) {
//                                    sql += "'" + String.format("%.2f", (Double.valueOf(ProductPrice) )) + "', ";
                                    sql += "'" + String.format("%.2f", (Double.valueOf(priceVar))) + "', ";
                                } else {
                                    sql += "'0.0', ";
                                }
                            } catch (Exception e) {
                                sql += "'0.0', ";
                            }
                            sql += "'" + BillDetailsID + "', ";
                            sql += "'" + dateFormat3 + "', ";
                            sql += "'" + ProductID + "', ";
                            sql += "'" + CategoryID + "', ";
                            sql += "'" + CategoryName + "', ";
                            sql += "'" + vchQueueNo + "', ";
                            sql += "'" + intTableNo + "', ";
                            sql += "'" + Cancel + "', ";
                            sql += "'" + MainActivity.BillID + "', ";
                            sql += "'" + TaxID + "', ";
                            sql += "'" + TaxType + "', ";
                            sql += "'" + TaxName + "', ";
//                    try {
                            if (TaxAmount != null && TaxAmount.length() > 0) {
                                sql += "'" + String.format("%.2f", Double.valueOf(TaxAmount)) + "', ";
                            } else {
                                sql += "'0.0', ";
                            }

                            sql += "'" + strRemarks + "', ";
                            sql += System.currentTimeMillis() + ")";


                            DBFunc.ExecQuery(sql, false);

                            //Integer detailsBillPId = Query.findLatestID("DetailsBillProduct","ID",false);


                          // Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,String.valueOf(detailsBillPId));
                        } else {
                            AddNewDetailsBillProduct(holder, position_val,status,remarksVar,strRemarks);
                        }
                    }
                }

            }
        }

//        String updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
//                "SET Remarks = '"+strRemarks+"' AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' " +
//                "WHERE BillNo = '" + billNo + "' " +
//                " AND ProductID = '"+popupProductID+"' AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' ";
//        Log.i("update___","updupdateRemarksDetatilsBilll___"+updateRemarksDetatilsBill);
//        DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

        getBillByBillNo();

        hashValues = Query.GetProductByID(MainActivity.strbillNo);
        //Query.GetProductByID(MainActivity.strbillNo);


//        CheckoutButtonValues(totalItems, totalAmount);


        MainCheckoutButtonUpdate(holder,position_val);


//        FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
//        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//        editProductSheetFragment.setCancelable(false);
//        editProductSheetFragment.show(manager, editProductSheetFragment.getTag());



//        Query.UpdatePLUModi(mContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice);

    }




    private void NormalFun(MyViewHolder holder, int position,String normal_status,String remarksVar) {

        openPrice = "0";

//        RoomDBSaveProduct(mData,position,mContext);

        AddNewDetailsBillProduct(holder, position,normal_status,remarksVar,"");

        getBillByBillNo();

        hashValues = Query.GetProductByID(MainActivity.strbillNo);
        //Query.GetProductByID(MainActivity.strbillNo);
        setColor(holder,position);

//        CheckoutButtonValues(totalItems, totalAmount);

        MainActivity.getBillByBillNo();


    }



    private static void setColor(MyViewHolder holder, int position) {
       // if (mData.get(position).getOpenPrice() == null || mData.get(position).getOpenPrice().equals("0")) {


        if (hashValues.containsKey(mData.get(position).getProductID())) {
                holder.binding.cardviewId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));

                holder.binding.bookTitleId.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                holder.binding.bookPriceId.setTextColor(ContextCompat.getColor(mContext, R.color.white));

                holder.binding.txtItemCcount.setVisibility(View.VISIBLE);

                holder.binding.txtItemCcount.setText(hashValues.get(mData.get(position).getProductID()));
//                int ival = Integer.parseInt(hashValues.get(mData.get(position).getProductID()));
//                model.setTotalCount(ival);
            } else {
                holder.binding.cardviewId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));

                holder.binding.bookTitleId.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));
                holder.binding.bookPriceId.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));

                holder.binding.txtItemCcount.setVisibility(View.GONE);
            }
       // }
        stat_holder = null;
        stat_position = -1;
    }

    private static void AddNewDetailsBillProduct(MyViewHolder holder, int position,String chk_status,String remarksVar,String strRemarks) {
        ProductMainPageFragment.status_on = "1";

        St_position = holder.getAdapterPosition();
        select_ = "1";
        //count++;
        getBillByBillNo();
        //countSelectedArr = count;
        countSelectedArr = totalItems;
        sldNameArr.clear();
        sldIDArr.clear();
        sltPriceTotalArr.clear();
        countSelectedArr = 0;

        sldNameArr.add(mData.get(position).getTitle());

        final String openPriceVar = mData.get(position).getOpenPrice();

//        final String imgVar = mData.get(position_val).getThumbnail();
//        final String priceVar = mData.get(position_val).getPrice();
        final String prodIDVar = mData.get(position).getProductID();
//        sldQtyArr.add(Integer.parseInt(mData.get(position).getProductID()));
        Integer existingQty = 0;
        String sqll = "Select SUM(ProductQty) from DetailsBillProduct " +
                "Where BillNo = '" + MainActivity.strbillNo + "' " +
                "AND ProductId = '" + prodIDVar + "' ";
        if (chk_status.equals("openPrice")) {
            sqll += "AND OpenPriceStatus = '" + openPriceVar + "' ";
        }
        sqll += "group by BillNo,ProductID";
        if (chk_status.equals("openPrice")) {
            sqll += ",OpenPriceStatus ";
        }

        Cursor ccc = DBFunc.Query(sqll,false);
        if (ccc != null) {
            if (ccc.moveToNext()){
                existingQty = ccc.getInt(0);
            }
            ccc.close();
        }

//        latestQty = existingQty+1;
        sldQtyArr.add(existingQty+1);
//        sldQtyArr.add(existingQty+1);

        String DiscountID = "0";
        String DiscountName = "0";
        String DiscountType = "0";
        String DiscountTypeName = "0";
        String DiscountValue = "0";
        String DiscountItemDiscountAmount = "0";


        if (remarksVar.equals("0")) {
            Cursor Cursor_Dis = Query.SearchDiscountFromDetailsBillProductBy(MainActivity.strbillNo, mData.get(position).getProductID());
            if (Cursor_Dis != null) {
                if (Cursor_Dis.moveToNext()) {
                    DiscountID = Cursor_Dis.getString(0);
                    DiscountName = Cursor_Dis.getString(1);
                    DiscountType = Cursor_Dis.getString(2);
                    DiscountTypeName = Cursor_Dis.getString(3);
                    DiscountValue = Cursor_Dis.getString(4);
                    DiscountItemDiscountAmount = Cursor_Dis.getString(5);
                }
                Cursor_Dis.close();
            }
            slddisID.add(DiscountID);
            slddisName.add(DiscountName);
            slddisTypeName.add(DiscountTypeName);
            slddisType.add(DiscountType);
            slddisValue.add(DiscountValue);
        }
        sldIDArr.add(mData.get(position).getProductID());
        sltPriceTotal += Double.parseDouble(mData.get(position).getPrice());
        str_sltPriceTotal = String.valueOf(sltPriceTotal);
        sltPriceTotalArr.add(mData.get(position).getPrice());
        sldCategoryIDArr.add(mData.get(position).getProductCategoryID());
        sldCategoryNameArr.add(mData.get(position).getProductCategoryName());

        if (remarksVar.equals("0")) {
            if (!(item_diss_amt == null || item_diss_amt.equals("0") ||
                    item_diss_amt.equals("null") || item_diss_amt.isEmpty())) {
                if (Double.valueOf(item_diss_amt) > 0.0) {
                    sltBillDisArr.add(item_diss_amt);

                    String sql = "INSERT INTO BillDisc (DiscID,BillNo,BillPLU_idx,Name,Value,Option,DateTime) VALUES (";
                    sql += "'" + slddisID + "', ";
                    sql += "'" + MainActivity.strbillNo + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(mData.get(position).getProductCategoryID())) + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(slddisName)) + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(slddisValue)) + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf("000")) + "', ";
                    sql += System.currentTimeMillis() + ")";

                    DBFunc.ExecQuery(sql, false);
                    item_diss_amt = "";

                } else {
//                sltBillDisArr.add("0.0"); //20082020
                    sltBillDisArr.add(DiscountItemDiscountAmount);
                    item_diss_amt = DiscountItemDiscountAmount;//just
                }
            } else {
                //sltBillDisArr.add("0.0");//20082020
                sltBillDisArr.add(DiscountItemDiscountAmount);
                item_diss_amt = DiscountItemDiscountAmount;//just
            }
        }
        sltProductIDArr.add(mData.get(position).getProductID());
        sltCategoryIDArr.add(mData.get(position).getProductCategoryID());
        sltCategoryNameArr.add(mData.get(position).getProductCategoryName());

        //count_totalItems = count + totalItems;
        count_totalItems = totalItems;


        // if (count_totalItems > 0) {
        if (str.equals("product")) {
            hashValues = Query.GetProductByID(MainActivity.strbillNo);
            //Query.GetProductByID(MainActivity.strbillNo);
        }
        //count_item_selected = Integer.parseInt(holder.txt_item_ccount.getText().toString()) + 1;
        if (hashValues.get(mData.get(position).getProductID()) != null && Integer.parseInt(hashValues.get(mData.get(position).getProductID())) > 0){
            count_item_selected = Integer.parseInt(hashValues.get(mData.get(position).getProductID()))+ 1;
        }else {
            count_item_selected = 1;
        }



        holder.binding.txtItemCcount.setText(count_item_selected.toString());
        count_selected_total += Integer.parseInt(holder.binding.txtItemCcount.getText().toString());

            String strbillNo = "";
            strbillNo = MainActivity.strbillNo;
//            Integer qty = 1;
            Integer qty = existingQty+1;
            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
            String paymenttype = "Cash";
            String status = Constraints.SALES;
            String Itemstatus = Constraints.SALES;
            Double sub_total = 0.0;
            Double amount = 0.0;
            ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;


            if (!MainActivity.tbl_name.equals("0")){
                intTableNo.add(MainActivity.tbl_name);
            }else {
                intTableNo.add("0");
            }

            Integer billIDDD = Query.getBillID(strbillNo);

////            RecyclerViewAdapter.latestQty = qty;
//        Log.i("DFDF__","st_sldNameAsldNameArr___"+sldNameArr+"__"+sldQtyArr+qty+sldIDArr);
//        Log.i("DFDF__","st_sldNameAsldNameArr__slddisID_"+slddisID+"_slddisName_"+slddisName+"_slddisType_"+slddisType
//                +"qty_slddisValue_"+slddisValue);
//            CheckOutActivity.SaveBill(billIDDD,strbillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                    qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                    sub_total, amount, paymenttype, Modifier_ID, vchQueueNo, intTableNo, Itemstatus,slddisID,slddisName,slddisTypeName,
//                    slddisType,slddisValue);


        CheckOutActivity.saveDetailsBillProduct(billIDDD, strbillNo, mData.get(position).getTitle(),
                mData.get(position).getPrice(),
                item_diss_amt, mData.get(position).getProductCategoryName(),
                existingQty+1, mData.get(position).getProductID(), mData.get(position).getProductCategoryID(),
                dateFormat3,   Modifier_ID,
                MainActivity.inv_table, MainActivity.tbl_name,
                status,Itemstatus, DiscountID, DiscountName, DiscountTypeName,
                DiscountType, DiscountValue,detailsBillProductID,"0",strRemarks);

//            CheckOutActivity.SaveBill(billIDDD,strbillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                    qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                    sub_total, amount, paymenttype, Modifier_ID, vchQueueNo, intTableNo, Itemstatus,slddisID,slddisName,slddisTypeName,
//                    slddisType,slddisValue);
            //CheckOutActivity.SaveBill(MainActivity.strbillNo);
//                    }
//        }
    }

    private void OpenPriceDialog(final Context mContext, final String pluID, final String pluName,
                                 final MyViewHolder holder, final int position, String remarksVar) {
        final DialogBox dlg = new DialogBox(mContext);
        if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window window = dlg.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.RIGHT;
            window.setAttributes(wlp);
        }
        //not support pax
        //dlg.setTitle(StrTextConst.GetText(TextType.POS, 9, "Open Price", tv_total.getText()));
        dlg.setTitle("Open Price");
        LayoutInflater inflater = LayoutInflater.from(dlg.getContext());
        LinearLayout lay_numpad = (LinearLayout) inflater.inflate(R.layout.dlg_numpad, null, false);

        TextView txt_info = (TextView) lay_numpad.findViewById(R.id.txt_info);
        txt_info.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 428));

        LinearLayout lay = (LinearLayout) lay_numpad.findViewById(R.id.lay_val);

        final StyleTextView et_value = new StyleTextView(mContext);
        lay.addView(et_value, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        et_value.setTypeface(Typeface.MONOSPACE);
        //rgb(127,255,0)
        //50,205,50
        et_value.setBackgroundColor(Color.argb(255, 50,205,50));
        et_value.setTextColor(Color.WHITE);
        et_value.setTextSize(50);
        et_value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        String val = mData.get(position).getPrice();
//        String val = "0";
        //String val = "1";

        // et_value.setTag(tag)
//        et_value.setText(String.format("%.2f", 0.0));
        et_value.setText(String.format("%.2f", Double.valueOf(val)));
        et_value.setFocusable(true);
        //et_value.setTag(val);
        et_value.setTag("0");

        final MetallicButton btn_bkspc = (MetallicButton) lay_numpad.findViewById(R.id.btn_bkspc);
        final MetallicButton[] btn_num = new MetallicButton[11];
        btn_num[0] = (MetallicButton) lay_numpad.findViewById(R.id.btn_0);
        btn_num[0].setTag("0");
        btn_num[1] = (MetallicButton) lay_numpad.findViewById(R.id.btn_1);
        btn_num[1].setTag("1");
        btn_num[2] = (MetallicButton) lay_numpad.findViewById(R.id.btn_2);
        btn_num[2].setTag("2");
        btn_num[3] = (MetallicButton) lay_numpad.findViewById(R.id.btn_3);
        btn_num[3].setTag("3");
        btn_num[4] = (MetallicButton) lay_numpad.findViewById(R.id.btn_4);
        btn_num[4].setTag("4");
        btn_num[5] = (MetallicButton) lay_numpad.findViewById(R.id.btn_5);
        btn_num[5].setTag("5");
        btn_num[6] = (MetallicButton) lay_numpad.findViewById(R.id.btn_6);
        btn_num[6].setTag("6");
        btn_num[7] = (MetallicButton) lay_numpad.findViewById(R.id.btn_7);
        btn_num[7].setTag("7");
        btn_num[8] = (MetallicButton) lay_numpad.findViewById(R.id.btn_8);
        btn_num[8].setTag("8");
        btn_num[9] = (MetallicButton) lay_numpad.findViewById(R.id.btn_9);
        btn_num[9].setTag("9");
        btn_num[10] = (MetallicButton) lay_numpad.findViewById(R.id.btn_00);
        btn_num[10].setTag("00");

        btn_bkspc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("cfirst", "ten");
                String _val = (String) et_value.getTag();
                if (_val.length() > 0) {
                    _val = _val.substring(0, _val.length() - 1);
                }
                if (_val.length() == 0) {
                    _val = "0";
                }
                et_value.setTag(_val);
                et_value.setText(String.format("%.2f", Double.parseDouble(_val) / 100.0));
                et_value.requestFocus();
            }
        });

        for (MetallicButton btn : btn_num) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("cfirst", "5");
                    String num = (String) v.getTag();
                    String _val = (String) et_value.getTag();
                    _val += num;
                    int val = 0;
                    try {
                        val = Integer.parseInt(_val);
                        if (val > 999999999) {
                            val = 999999999;
                        }
                        et_value.setTag("" + val);
                        et_value.setText(String.format("%.2f", Double.parseDouble("" + val) / 100.0));
                    } catch (NumberFormatException e) {
                    }
                    et_value.requestFocus();
                }
            });
        }

        final MetallicButton btn_ok = (MetallicButton) lay_numpad.findViewById(R.id.btn_ok);
//        final int finalPluid = pluid;
//        final String finalPluname = pluname;
//
//        final int finalDeptID = deptID;
//        final String finalDeptName = deptName;
//        final double finalPluprice = pluprice;
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Dff___", "___"+String.valueOf(et_value.getText().toString()));
                Log.i("pluID_", "___"+"_pluID"+pluID);
                Log.i("ppluName_", "___"+"_pluName"+pluName);
                try {
                    String typeValue = et_value.getText().toString();
                    Log.i("typeValue", "___"+"_typeValue"+typeValue);
                    dlg.dismiss();
                    EditPLUQty(Integer.parseInt(pluID),pluName,typeValue,holder,position,remarksVar);
                } catch (NumberFormatException e) {
                    ShowErrorDialog(mContext,"ONE"+StrTextConst.GetText(StrTextConst.TextType.POS, 125));
                } catch (IllegalFormatException e) {
                    ShowErrorDialog(mContext,"two"+StrTextConst.GetText(StrTextConst.TextType.POS, 125));
                }
            }
        });

        MetallicButton btn_close = (MetallicButton) lay_numpad.findViewById(R.id.btn_cancel);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        et_value.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                String _val = "0";

                et_value.setTag(_val);
                et_value.setText(String.format("%.2f", Double.parseDouble(_val) / 100.0));
                return true;
            }

        });

        et_value.setKeyListener(new KeyListener() {

            @Override
            public int getInputType() {
                return 0;
            }

            @Override
            public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {

                // String num = "";
                switch (keyCode) {
                    case KeyEvent.KEYCODE_0:
                    case KeyEvent.KEYCODE_NUMPAD_0:
                        // num = "0";
                        btn_num[0].performClick();
                        break;
                    case KeyEvent.KEYCODE_1:
                    case KeyEvent.KEYCODE_NUMPAD_1:
                        // num = "1";
                        btn_num[1].performClick();
                        break;
                    case KeyEvent.KEYCODE_2:
                    case KeyEvent.KEYCODE_NUMPAD_2:
                        // num = "2";
                        btn_num[2].performClick();
                        break;
                    case KeyEvent.KEYCODE_3:
                    case KeyEvent.KEYCODE_NUMPAD_3:
                        // num = "3";
                        btn_num[3].performClick();
                        break;
                    case KeyEvent.KEYCODE_4:
                    case KeyEvent.KEYCODE_NUMPAD_4:
                        // num = "4";
                        btn_num[4].performClick();
                        break;
                    case KeyEvent.KEYCODE_5:
                    case KeyEvent.KEYCODE_NUMPAD_5:
                        // num = "5";
                        Log.i("cfirst", "tenn");
                        btn_num[5].performClick();
                        break;
                    case KeyEvent.KEYCODE_6:
                    case KeyEvent.KEYCODE_NUMPAD_6:
                        // num = "6";
                        btn_num[6].performClick();
                        break;
                    case KeyEvent.KEYCODE_7:
                    case KeyEvent.KEYCODE_NUMPAD_7:
                        // num = "7";
                        btn_num[7].performClick();
                        break;
                    case KeyEvent.KEYCODE_8:
                    case KeyEvent.KEYCODE_NUMPAD_8:
                        // num = "8";
                        btn_num[8].performClick();
                        break;
                    case KeyEvent.KEYCODE_9:
                    case KeyEvent.KEYCODE_NUMPAD_9:
                        // num = "9";
                        btn_num[9].performClick();
                        break;
                    case KeyEvent.KEYCODE_DEL:
                    case KeyEvent.KEYCODE_FORWARD_DEL:
                        btn_bkspc.performClick();
                        break;
                    case KeyEvent.KEYCODE_ENTER:
                    case KeyEvent.KEYCODE_NUMPAD_ENTER:
                        btn_ok.performClick();
                        break;
                    case KeyEvent.KEYCODE_ESCAPE:
                        et_value.performLongClick();
                        break;
                }

                et_value.requestFocus();

                return true;
            }

            @Override
            public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
                return false;
            }

            @Override
            public boolean onKeyOther(View view, Editable text, KeyEvent event) {
                return false;
            }

            @Override
            public void clearMetaKeyState(View view, Editable content, int states) {
            }

        });

        dlg.addView(lay_numpad);
        lay_numpad.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dlg.setWindowSize(0.8f, 0.9f);
        dlg.show();

        et_value.requestFocus();
        return;
    }
    //show error dialog
    private void ShowErrorDialog(Context context,String errmsg) {
        final DialogBox dlg = new DialogBox(context);
        dlg.setMessage(errmsg);
        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
        dlg.setDialogIconType(DialogBox.IconType.ERROR);
        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
        dlg.show();

    }

    private void EditPLUQty(final int billINDEX, final String pluName, final String typeValue, final MyViewHolder holder,
                            final int position, String remarksVar) {
        Context CurrentActivity = mContext;

                final DialogBox dlg = new DialogBox(CurrentActivity);
                if (CurrentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Window window = dlg.getWindow();
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.RIGHT;
                    window.setAttributes(wlp);
                }

//                final long _qty = plu.getInt(0);
//                final String _name = plu.getString(3);

                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 8, "Quantity"));
                LayoutInflater inflater = LayoutInflater.from(dlg.getContext());
                LinearLayout lay_numpad = (LinearLayout) inflater.inflate(R.layout.dlg_numpad, null, false);

                TextView txt_info = (TextView) lay_numpad.findViewById(R.id.txt_info);
                txt_info.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 424));

                LinearLayout lay = (LinearLayout) lay_numpad.findViewById(R.id.lay_val);

                final StyleTextView et_value = new StyleTextView(mContext);
                et_value.setTypeface(Typeface.MONOSPACE);

                lay.addView(et_value, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //et_value.setBackgroundColor(Color.argb(255, 65, 78, 92));
                et_value.setBackgroundColor(Color.argb(255, 50,205,50));
                et_value.setTextColor(Color.WHITE);
                et_value.setTextSize(50);
                et_value.setInputType(InputType.TYPE_CLASS_NUMBER);
                et_value.setFocusable(true);
                //String val = "" + plu.getInt(0);

                // et_value.setTag(tag)

                //et_value.setText("" + _qty);
                et_value.setText("" + 1);
                et_value.setTag(""+1);
                //et_value.setTag(val);

                Log.i("ttyet_valuee", "___"+"_et_valuelue"+et_value.getText());
                final MetallicButton btn_bkspc = (MetallicButton) lay_numpad.findViewById(R.id.btn_bkspc);
                final MetallicButton[] btn_num = new MetallicButton[11];
                btn_num[0] = (MetallicButton) lay_numpad.findViewById(R.id.btn_0);
                btn_num[0].setTag("0");
                btn_num[1] = (MetallicButton) lay_numpad.findViewById(R.id.btn_1);
                btn_num[1].setTag("1");
                btn_num[2] = (MetallicButton) lay_numpad.findViewById(R.id.btn_2);
                btn_num[2].setTag("2");
                btn_num[3] = (MetallicButton) lay_numpad.findViewById(R.id.btn_3);
                btn_num[3].setTag("3");
                btn_num[4] = (MetallicButton) lay_numpad.findViewById(R.id.btn_4);
                btn_num[4].setTag("4");
                btn_num[5] = (MetallicButton) lay_numpad.findViewById(R.id.btn_5);
                btn_num[5].setTag("5");
                btn_num[6] = (MetallicButton) lay_numpad.findViewById(R.id.btn_6);
                btn_num[6].setTag("6");
                btn_num[7] = (MetallicButton) lay_numpad.findViewById(R.id.btn_7);
                btn_num[7].setTag("7");
                btn_num[8] = (MetallicButton) lay_numpad.findViewById(R.id.btn_8);
                btn_num[8].setTag("8");
                btn_num[9] = (MetallicButton) lay_numpad.findViewById(R.id.btn_9);
                btn_num[9].setTag("9");
                btn_num[10] = (MetallicButton) lay_numpad.findViewById(R.id.btn_00);
                btn_num[10].setTag("00");

                if (remarksVar.equals("0")) {
                    btn_bkspc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("cfirst", "seven");
                            String _val = (String) et_value.getTag();
                            if (_val.length() > 0) {
                                _val = _val.substring(0, _val.length() - 1);
                            }
                            if (_val.length() == 0) {
                                _val = "0";
                            }
                            et_value.setTag(_val);
                            et_value.setText(_val);
                            et_value.requestFocus();
                        }
                    });
                }
                final boolean[] numchk = {true};
                for (MetallicButton btn : btn_num) {
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("cfirst", "3");

                            if (remarksVar.equals("0")) {
                                String num = (String) v.getTag();
                                String _val = (String) et_value.getTag();
//							if (_val.equals("0")) {
//								_val = num;
//							} else {
//								_val += num;
//							}
                                if (numchk[0]) {
                                    if (_val.equals("0")) {
                                        _val = num;
                                    } else {
                                        _val = num;
                                    }
                                    numchk[0] = false;
                                } else {
                                    if (_val.equals("0")) {
                                        _val = num;
                                    } else {
                                        _val += num;
                                    }
                                }
                                et_value.setTag(_val);
                                et_value.setText(_val);
                                et_value.requestFocus();
                            }
                        }
                    });
                }

                final MetallicButton btn_ok = (MetallicButton) lay_numpad.findViewById(R.id.btn_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(ActivityPosCashier.this, "mcsbtn_test1", Toast.LENGTH_LONG).show();
                        try {
                            int q = Integer.parseInt(et_value.getText().toString());
                            if (q < 1) {
                                //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 124));
                                // Toast.makeText(CurrentActivity,"Invalid Quantity Number! Minimum quantity is 1!",Toast.LENGTH_SHORT).show();
                               // return;
                                q = 1;
                            }


                            //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
                            try {
                                String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                                Integer bill_plu_id = Query.findLatestID("ID", "DetailsBillProduct", false);

                                //openPrice = timeStamp+"_"+bill_plu_id;
                                bill_plu_id = bill_plu_id +1 ;
                                openPrice = "_"+ bill_plu_id;
//                                for (int i = 0; i < q; i ++) {
                                    Log.i("___openPrice","__openPrice"+openPrice);

//                                    sldQtyArr.add(existingQty+1);

//                                    Query.saveDetailsBillProduct_AssignValue(MainActivity.BillID, MainActivity.strbillNo, "OFF",
//                                            billINDEX, pluName, String.valueOf(typeValue),
//                                              "0", String.valueOf(billINDEX), "0", "0",
//                                            ENUM.SALES, "0", "0", "0", "0", "0");
                                    Query.saveDetailsBillProduct_AssignValue("recyclerview",MainActivity.BillID, MainActivity.strbillNo, "OFF",
                                            q, pluName, String.valueOf(typeValue),
                                              "0", String.valueOf(billINDEX), "0", "0",
                                            Constraints.SALES, "0", "0", "0", "0",
                                            "0",
                                            0,openPrice,"");

//                                    Integer billId,String billNo, String onlineOrderBill, Integer productQty,
//                                            String productName, String productPrice,
//                                            String itemDiscount, String productID,
//                                            String vchQueueNo, String intTableNo, String str_Cancel,
//                                            String slddisID, String slddisName, String slddisTypeName, String slddisType,
//                                            String DiscountValue
//                                }
                                Query.SaveBillPLU(MainActivity.BillID,MainActivity.strbillNo,
                                        String.valueOf(billINDEX),pluName,String.valueOf(typeValue),q,"","");

                                //MainCheckoutButtonUpdate(holder,position);
                                Log.i("rpluId__","rpluId_"+String.valueOf(billINDEX));
                                Log.i("remarksVar___","remarksVar___remarksVar_"+remarksVar);
                                if (remarksVar != null && remarksVar.equals("1")) {
                                    remarksDialog(mContext, holder, position, MainActivity.strbillNo, String.valueOf(billINDEX), openPrice,
                                            pluName, "openPrice", remarksVar, "0");
                                } else {
                                    getBillByBillNo();

                                    hashValues = Query.GetProductByID(MainActivity.strbillNo);
                                    //Query.GetProductByID(MainActivity.strbillNo);


//                                    CheckoutButtonValues(totalItems, totalAmount);

                                    MainCheckoutButtonUpdate(holder,position);
                                }
//                                Intent i_main = new Intent(mContext,MainActivity.class);
//                                i_main.putExtra("name","");
//                                mContext.startActivity(i_main);
//                                MainActivity.St = "1";
                            }catch (IllegalFormatException e){
                                Log.i("__Errr","EEr"+e.getMessage());
                            }
                             dlg.dismiss();
                            //MainActivity.St = "1";
                        } catch (NumberFormatException e) {
                            //ShowErrorDialog(StrTextConst.GetText(StrTextConst.TextType.POS, 124));
                            // Toast.makeText(CurrentActivity,"Invalid Quantity Number! Minimum quantity is 1!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                MetallicButton btn_close = (MetallicButton) lay_numpad.findViewById(R.id.btn_cancel);
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });

                et_value.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        et_value.setTag("0");
                        et_value.setText("0");
//						et_value.setTag("1");
//						et_value.setText("1");
                        return true;
                    }

                });

                et_value.setKeyListener(new KeyListener() {

                    @Override
                    public int getInputType() {
                        return 0;
                    }

                    @Override
                    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {

                        // String num = "";
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_0:
                            case KeyEvent.KEYCODE_NUMPAD_0:
                                // num = "0";
                                btn_num[0].performClick();
                                break;
                            case KeyEvent.KEYCODE_1:
                            case KeyEvent.KEYCODE_NUMPAD_1:
                                // num = "1";
                                btn_num[1].performClick();
                                break;
                            case KeyEvent.KEYCODE_2:
                            case KeyEvent.KEYCODE_NUMPAD_2:
                                // num = "2";
                                btn_num[2].performClick();
                                break;
                            case KeyEvent.KEYCODE_3:
                            case KeyEvent.KEYCODE_NUMPAD_3:
                                // num = "3";
                                btn_num[3].performClick();
                                break;
                            case KeyEvent.KEYCODE_4:
                            case KeyEvent.KEYCODE_NUMPAD_4:
                                // num = "4";
                                btn_num[4].performClick();
                                break;
                            case KeyEvent.KEYCODE_5:
                            case KeyEvent.KEYCODE_NUMPAD_5:
                                // num = "5";
                                Log.i("cfirst", "eigth");
                                btn_num[5].performClick();
                                break;
                            case KeyEvent.KEYCODE_6:
                            case KeyEvent.KEYCODE_NUMPAD_6:
                                // num = "6";
                                btn_num[6].performClick();
                                break;
                            case KeyEvent.KEYCODE_7:
                            case KeyEvent.KEYCODE_NUMPAD_7:
                                // num = "7";
                                btn_num[7].performClick();
                                break;
                            case KeyEvent.KEYCODE_8:
                            case KeyEvent.KEYCODE_NUMPAD_8:
                                // num = "8";
                                btn_num[8].performClick();
                                break;
                            case KeyEvent.KEYCODE_9:
                            case KeyEvent.KEYCODE_NUMPAD_9:
                                // num = "9";
                                btn_num[9].performClick();
                                break;
                            case KeyEvent.KEYCODE_DEL:
                            case KeyEvent.KEYCODE_FORWARD_DEL:
                                btn_bkspc.performClick();
                                break;
                            case KeyEvent.KEYCODE_ENTER:
                            case KeyEvent.KEYCODE_NUMPAD_ENTER:
                                btn_ok.performClick();
                                break;
                            case KeyEvent.KEYCODE_ESCAPE:
                                et_value.performLongClick();
                                break;
                        }

                        et_value.requestFocus();

                        return true;
                    }

                    @Override
                    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
                        return false;
                    }

                    @Override
                    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
                        return false;
                    }

                    @Override
                    public void clearMetaKeyState(View view, Editable content, int states) {
                    }

                });

                dlg.addView(lay_numpad);
                lay_numpad.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                dlg.setWindowSize(0.8f, 0.9f);
                dlg.show();
                et_value.requestFocus();

                //plu.close();
//            } else {
//                // System.out.println("Something wrong with the DB or out of sync IDX!");
//                DialogBox dlg1 = new DialogBox(CurrentActivity);
//                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
//                dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 312));
//                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                dlg1.show();
//                // Toast.makeText(CurrentActivity,
//                // "Something wrong with the DB or out of sync IDX!",
//                // Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            // System.out.println("CANT QUERY! DB ERROR!");
//            DialogBox dlg1 = new DialogBox(CurrentActivity);
//            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
//            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
//            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//            dlg1.show();
//            // Toast.makeText(CurrentActivity,
//            // "CANT QUERY! DB ERROR!",Toast.LENGTH_SHORT).show();
//        }
    }

    private static void MainCheckoutButtonUpdate(MyViewHolder holder, int position) {
//        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(MainActivity.strbillNo, Constraints.GroupBy);
//        if (C_DetailsBillProduct != null) {
//            totalItems = 0;
//            totalAmount = 0.0;
//            while (C_DetailsBillProduct.moveToNext()) {
//                if (!C_DetailsBillProduct.isNull(0)) {
//                    if (C_DetailsBillProduct.getInt(2) != -1){
//                        totalItems += C_DetailsBillProduct.getInt(0);
//                        //totalAmount += C_DetailsBillProduct.getInt(0) * (C_DetailsBillProduct.getDouble(1) - C_DetailsBillProduct.getDouble(3));
//                        totalAmount += (C_DetailsBillProduct.getDouble(1) - C_DetailsBillProduct.getDouble(3));
//                    }
//                }
//            }
//            C_DetailsBillProduct.close();
//        }
//
//        MainActivity.totalItems = totalItems;
//        MainActivity.totalAmount = totalAmount;

        Log.i("___","DDD_totalItems__"+ MainActivity.totalItems);
        Log.i("Sdfsd___","DCS8888____"+totalAmount);

        getBillByBillNo();
        if (str.equals("product")) {
            hashValues = Query.GetProductByID(MainActivity.strbillNo);
            //Query.GetProductByID(MainActivity.strbillNo);
        }
        Log.i("TEStingTwo","TTTTTT");
        MainActivity.getBillByBillNo();

        //CheckoutButtonValues(RecyclerViewAdapter.totalItems,RecyclerViewAdapter.totalAmount);
//        CheckoutButtonValues(totalItems,totalAmount);
        setColor(holder,position);
        //EditProductSheetFragment.str_edit_product_sheet_fragment = "0";
        //}
        status_on = "0";
        //ProductMainPageFragment.status_on = "1";
        edit_fragment_status = "0";
    }

     private void EditFragmentFun(MyViewHolder holder, int position,int billDetailsPID) {
        item_id = "0";
        category_id = "0";
        category_name = "0";
        item_name = "0";
        item_pricee = "0";
        ItemDiscountAmount = 0.0;
        item_diss = "0";
        old_pricee = "0";
        stat_holder = holder;
        stat_position = position;

        EditProductSheetFragment.str_percentage = "";
        EditProductSheetFragment.str_percentage_value = "";
        EditProductSheetFragment.disID = "";
        EditProductSheetFragment.disName = "";
        EditProductSheetFragment.disTypeName = "";
        EditProductSheetFragment.disType = "";
        EditProductSheetFragment.disValue = "";

        CheckOutActivity.str_percentage = "";
        CheckOutActivity.str_percentage_value = "";
        CheckOutActivity.disID = "";
        CheckOutActivity.disName = "";
        CheckOutActivity.disTypeName = "";
        CheckOutActivity.disType = "";
        CheckOutActivity.disValue = "";
//        TabFragmentAmount.str_percentage = "";
//        TabFragmentAmount.str_percentage_value = "";
        St_position =  holder.getAdapterPosition();
        EditProductSheetFragment.str_edit_product_sheet_fragment = "0";
        item_id =  mData.get(position).getProductID();
        item_name =  mData.get(position).getTitle().toString();
        item_countt =  holder.binding.txtItemCcount.getText().toString();
        Double amt = Double.valueOf(item_countt) * Double.valueOf(mData.get(position).getPrice().toString());
        item_pricee = String.valueOf(amt);
        original_item_pricee = mData.get(position).getPrice().toString();
        Log.i("DF__","original_item_pricee__"+original_item_pricee);
        item_diss = "";
        old_pricee = "";
        category_id = mData.get(position).getProductCategoryID();
        category_name = mData.get(position).getProductCategoryName();

        EditProductSheetFragment.billNo = MainActivity.strbillNo;
        EditProductSheetFragment.ID = String.valueOf(billDetailsPID);

        Log.i("Sdfsfd___","billDetailsPID____"+billDetailsPID);

        EditProductSheetFragment.EditFragmenttotalQty = item_countt;

        FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //commitAllowingStateLoss();
        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
        try {
            //EditProductCheckoutSheetFragment editProductSheetFragment = new EditProductCheckoutSheetFragment();
            editProductSheetFragment.setCancelable(false);
            Log.i("DFDFDFD___","TRTRTR____"+"_____3");
            editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
        }catch (IllegalStateException e){
            //editProductSheetFragment.onStop();
            //MainActivity.St = "1";
            Intent i_main = new Intent(mContext,MainActivity.class);
            i_main.putExtra("name","");
            mContext.startActivity(i_main);
            ((Activity)mContext).finish();
        }
//                    }

        //}else {
        //MainActivity.St = "1";
        //Toast.makeText(mContext, "Null", Toast.LENGTH_SHORT).show();
        // }
    }

    //
//    private void functionReload(MyViewHolder holder, MyViewHolder holder1, int position) {
//        if (hashValues.containsKey(mData.get(position).getTitle())){
//            //if (arr.contains(mData.get(position).getTitle())) {
//            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_blue));
//            holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//            holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//
//            holder.txt_item_ccount.setVisibility(View.VISIBLE);
//            holder.txt_item_ccount.setText(hashValues.get(mData.get(position).getTitle().toString()));
//            //ProductMainPageFragment.status_on = "0";
//        }
//    }
    private String getPathFromURI(Context context, Uri contentUri) {
        if ( contentUri.toString().indexOf("file:///") > -1 ){
            return contentUri.getPath();
        }

        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
//    private void getBillByProductID() {
//        hashValues = Query.GetProductByID(MainActivity.strbillNo);
//    }
 public static Integer detailsBillProductID = 0;
    private static void getBillByBillNo() {
        //new LongOperation6(mContext).execute();
        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
        String sql = " SELECT (ProductQty),(ProductPrice),ProductQty,(ItemDiscountAmount),ID FROM DetailsBillProduct " +
                "Where ProductQty != -1 AND BillNo = '" + MainActivity.strbillNo + "' "+
                "   AND Cancel = 'SALES'  group by BillNo,ProductID,OpenPriceStatus";
//        String sql = " SELECT SUM(ProductQty),SUM(ProductPrice),ProductQty,SUM(ItemDiscountAmount),ID FROM DetailsBillProduct " +
//                "Where ProductQty != -1 AND BillNo = '" + MainActivity.strbillNo + "' "+
//                "   AND Cancel = 'SALES'  group by BillNo,ProductID,OpenPriceStatus";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            totalItems = 0;
            totalAmount = 0.0;
            detailsBillProductID = 0;
            while (c.moveToNext()) {
                //if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    if (c.getInt(2) != -1){
                        totalItems += c.getInt(0);
//                        totalAmount +=  c.getInt(0) *
//                                (c.getDouble(1) - c.getDouble(3));
                        //totalAmount += (c.getDouble(1) - c.getDouble(3));
                        totalAmount +=  c.getDouble(1)  - (c.getDouble(0) *  c.getDouble(3));
                        detailsBillProductID = c.getInt(4);
                    }else {
                        totalItems = 0;
                        totalAmount = 0.0;
                        detailsBillProductID = 0;
                    }
                }
            }
            c.close();
        }

        String str_product = "Product";
        if (totalItems > 0) {
            str_product = "Products";
        }

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    private int getSelectedItemCount() {
        if (selectedItems.size() > 0){

            return selectedItems.size();
        }else {

            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public LinearLayout product_linear_layout_id;
//        public TextView tv_book_title,book_price_id,txt_item_ccount,tstid;
//        public ImageView img_book_thumbnail;
//        CardView cardView ;

        CardveiwItemBookBinding binding;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//        }

        public MyViewHolder(CardveiwItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = (CardveiwItemBookBinding) binding;


//            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
//            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            binding.bookTitleId.setTextSize(MainActivity.billFontSize);
//            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
            binding.bookPriceId.setTextSize(MainActivity.billFontSize);
            //String chk_hide_img = Query.GetOptions(20);
            //if (chk_hide_img.equals("0")) {
                //img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            //}
            //cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            //txt_item_ccount = (TextView) itemView.findViewById(R.id.txt_item_ccount);
            //tstid = (TextView) itemView.findViewById(R.id.tstid);

            getBillByBillNo();
            if (str.equals("product")) {
                hashValues = Query.GetProductByID(MainActivity.strbillNo);
                //Query.GetProductByID(MainActivity.strbillNo);
            }


//            CheckoutButtonValues(totalItems,totalAmount);
            MainActivity.getBillByBillNo();
        }
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            Log.i("View___","Hellofromhere_2");
//            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
//            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
//            tv_book_title.setTextSize(MainActivity.billFontSize);
//            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
//            book_price_id.setTextSize(MainActivity.billFontSize);
//            String chk_hide_img = Query.GetOptions(20);
//            if (chk_hide_img.equals("0")) {
//                img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
//            }
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
    }

//    private static void CheckoutButtonValues(Integer totalItems, Double totalAmount) {
//        MainActivity.totalItems = totalItems;
//        MainActivity.totalAmount = totalAmount;
//        if (totalItems.toString().length() > 2){
//            MainActivity.binding.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
//                    "<small>" + totalItems + " Products " + "</small>" +
//                    "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
//                    String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
//        }else {
//            MainActivity.binding.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
//                    "<small>" + totalItems + " Products " + "</small>" +
//                    "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" +
//                    String.format("%.2f", Double.valueOf(totalAmount)) + "</b>"));
//        }
//
//    }
//viewHolder.deleteButton.setOnClickListener(view -> delete(viewHolder.getAdapterPosition()));
}
