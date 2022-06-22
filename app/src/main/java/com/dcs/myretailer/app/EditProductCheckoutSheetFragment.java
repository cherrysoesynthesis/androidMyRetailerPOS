//package com.dcs.myretailer.app;
//
//import android.os.Bundle;
//import android.support.design.widget.BottomSheetDialogFragment;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//public class EditProductCheckoutSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
//    ImageView addition,subtraction;
//    //private static int _counter = 45;
//    private String _stringVal;
//    TextView qun,txt_old_value;
//    ImageView btn_item_discount;
//    EditText product_edit_price;
//    TextView txt_header_;
//    public static Integer product_id = 0;
//    String category_id = "";
//    String category_name = "";
//    public static String qty,old_value,price = "";
//    public static String str_edit_product_sheet_fragment = "0";
//    public static Integer totalQty = 0;
//    //public static Double ItemDiscountAmount = 0.0;
//    Double ItemDiscountAmount = 0.0;
//    public static Double ProductPrice = 0.0;
//    android.support.v7.widget.RecyclerView chooseButton;
//    public static ArrayList<String> modiName = new ArrayList<String>();
//    public static ArrayList<Integer> modiID = new ArrayList<Integer>();
//    public static ArrayList<String> ItemID = new ArrayList<String>();
//    public static ArrayList<String> UnSelectedItem =  new ArrayList<String>();
//    String EPSF_item_id = "0";
//    String EPSF_category_id= "0";
//    String EPSF_category_name= "0";
//    String EPSF_item_name= "0";
//    String EPSF_item_pricee= "0";
//    Double EPSF_ItemDiscountAmount= 0.0;
//    String EPSF_item_diss= "0";
//    String EPSF_old_pricee= "0";
//    String EPSF_original_item_pricee= "0";
//    public static String uuid = "0";
//    public static String disVal = "";
//    public static String disID = "0";
//    public static String disName = "";
//    public static String disOptions = "";
//    public static String disTypeName = "";
//    public static String disType = "";
//    public static String disValue = "";
//    public static String disIDAmt = "0";
//    public static String disNameAmt = "";
//    public static String disOptionsAmt = "";
//    public static String disTypeNameAmt = "";
//    public static String disTypeAmt = "";
//    public static String disValueAmt = "";
//    public static String str_percentage,str_percentage_value = "";
//    public static String str_value = "";  String DiscountID = "0";
//    String DiscountName = "0";
//    String DiscountType = "0";
//    String DiscountTypeName = "0";
//    String DiscountValue = "0";
//    String DiscountItemDiscountAmount = "0";
//
//    public EditProductCheckoutSheetFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View mView = inflater.inflate(R.layout.fragment_edit_product_checkout_sheet_dialog, container, false);
//        return mView;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        ImageView close_cashier_float_amt = (ImageView) view.findViewById(R.id.close_cashier_float_amt);
//        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
//        linear_close.setOnClickListener(this);
//
//        RelativeLayout erase = (RelativeLayout) view.findViewById(R.id.erase);
//        RelativeLayout rel = (RelativeLayout) view.findViewById(R.id.rel);
//        Button btn_add_to_bill = (Button) view.findViewById(R.id.btn_add_to_bill);
//        Button btn_add_to_void = (Button) view.findViewById(R.id.btn_add_to_void);
//        product_edit_price = (EditText) view.findViewById(R.id.product_edit_price);
//        TextView item_discount = (TextView) view.findViewById(R.id.item_discount);
//        txt_header_ = (TextView) view.findViewById(R.id.txt_header_);
//        btn_add_to_bill.setOnClickListener(this);
//        btn_add_to_void.setOnClickListener(this);
//
//
//        Log.i("_1111_", String.valueOf(str_value.length()));
//
//        Cursor Cursor_Dis = Query.SearchDiscountFromDetailsBillProductBy(MainActivity.strbillNo,String.valueOf(product_id));
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
//
//        qun = (TextView) view.findViewById(R.id.qun);
//        addition = (ImageView) view.findViewById(R.id.addition);
//        subtraction = (ImageView) view.findViewById(R.id.subtraction);
//        btn_item_discount = (ImageView) view.findViewById(R.id.btn_item_discount);
//        txt_old_value = (TextView) view.findViewById(R.id.txt_old_value);
//        chooseButton = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.chooseButton);
//
//        String sql = "Select ID, Name,Price from "+ENUM.ProductModifier;
//
//        Cursor c = DBFunc.Query(sql, true);
//        ArrayList<Integer> ModifierID = new ArrayList<Integer>();
//        ArrayList<String> ModifierName = new ArrayList<String>();
//        ArrayList<String> ModifierPrice = new ArrayList<String>();
//        modiName.clear();
//        modiID.clear();
//        if (c != null) {
//            while (c.moveToNext()) {
//                ModifierID.add(c.getInt(0));
//                ModifierName.add(c.getString(1));
//                ModifierPrice.add(c.getString(2));
//            }
//            c.close();
//        }
//        ButtonAdapter customAdapter = new ButtonAdapter(getActivity(),ModifierID,ModifierName,ModifierPrice);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
//        chooseButton.setLayoutManager(gridLayoutManager);
//        chooseButton.setAdapter(customAdapter);
//        customAdapter.notifyDataSetChanged();
//
//        String chk_hide_img = Query.GetOptions(20);
//        if (chk_hide_img.equals("1")){
//            EPSF_item_id = RecyclerViewNoImageAdapter.item_id;
//            EPSF_category_id = RecyclerViewNoImageAdapter.category_id;
//            EPSF_category_name = RecyclerViewNoImageAdapter.category_name;
//            EPSF_item_name = RecyclerViewNoImageAdapter.item_name;
//            EPSF_item_pricee = RecyclerViewNoImageAdapter.item_pricee;
//            EPSF_ItemDiscountAmount = RecyclerViewNoImageAdapter.ItemDiscountAmount;
//            EPSF_item_diss = RecyclerViewNoImageAdapter.item_diss;
//            EPSF_old_pricee = RecyclerViewNoImageAdapter.old_pricee;
//        }else {
//            EPSF_item_id = RecyclerViewAdapter.item_id;
//            EPSF_category_id = RecyclerViewAdapter.category_id;
//            EPSF_category_name = RecyclerViewAdapter.category_name;
//            EPSF_item_name = RecyclerViewAdapter.item_name;
//            EPSF_item_pricee = RecyclerViewAdapter.item_pricee;
//            EPSF_ItemDiscountAmount = RecyclerViewAdapter.ItemDiscountAmount;
//            EPSF_item_diss = RecyclerViewAdapter.item_diss;
//            EPSF_old_pricee = RecyclerViewAdapter.old_pricee;
//        }
//
//        product_id = Integer.parseInt(EPSF_item_id);
//
//        String uuidsql = " SELECT UUID " +
//                "FROM PLU WHERE ID = "+ product_id;
//        c = DBFunc.Query(uuidsql, true);
//        if (c!=null){
//            if (c.moveToNext()){
//                uuid = c.getString(0);
//            }
//            c.close();
//        }
//        category_id = EPSF_category_id;
//        category_name = EPSF_category_name;
//        txt_header_.setText(EPSF_item_name);
//
//        Log.i("EPSF_item_pricee",EPSF_item_pricee);
//        //product_edit_price.setText("$"+String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
//        product_edit_price.setText(String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
//
//        searchProductNameDis();
//        Log.i("EPSF_item_diss",EPSF_item_diss);
//        Log.i("ItemDiscountAmount", String.valueOf(ItemDiscountAmount));
//        if (EPSF_item_diss.length() > 0) {
//            Log.i("Log_first","Hi");
//            item_discount.setText(EPSF_item_diss + " Off");
//            //product_edit_price.setText("$"+String.format("%.2f", Double.valueOf(EPSF_old_pricee)));
//            product_edit_price.setText(String.format("%.2f", Double.valueOf(EPSF_old_pricee)));
//            rel.setVisibility(View.VISIBLE);
//            //erase.setVisibility(View.VISIBLE);
//            txt_old_value.setVisibility(View.VISIBLE);
//            //txt_old_value.setText("$"+String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
//            txt_old_value.setText(String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
//            txt_old_value.setPaintFlags(txt_old_value.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//        }else if (ItemDiscountAmount > 0.0){
//            Log.i("Log_second","Hi");
//            Double priceee = ProductPrice + ItemDiscountAmount;
//
//            //product_edit_price.setText("$"+String.format("%.2f", ProductPrice));
//            product_edit_price.setText(String.format("%.2f", ProductPrice));
//            rel.setVisibility(View.VISIBLE);
//            //erase.setVisibility(View.VISIBLE);
//            txt_old_value.setVisibility(View.VISIBLE);
//            //txt_old_value.setText("$"+String.format("%.2f", priceee));
//            txt_old_value.setText(String.format("%.2f", priceee));
//            txt_old_value.setPaintFlags(txt_old_value.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//        }
//
//        getDetailsBillProduct(MainActivity.strbillNo);
//        //qun.setText(RecyclerViewAdapter.item_countt);
//        qun.setText(totalQty.toString());
//        _counter = totalQty;
//        addition.setOnClickListener(this);
//        subtraction.setOnClickListener(this);
//        btn_item_discount.setOnClickListener(this);
//    }
//
//    private void searchProductNameDis() {
//        String sql = "Select SUM(ProductPrice),SUM(ItemDiscountAmount) FROM Details_BillProduct " +
//                "Where BillNo = '"+MainActivity.strbillNo+"' AND ProductID = '" + EPSF_item_id +
//                "' group by ProductID";
//        Log.i("DFDSQL___",sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            ProductPrice = 0.0;
//            ItemDiscountAmount = 0.0;
//            if (c.moveToNext()) {
//                Log.i("_dd", c.getString(0));
//                ProductPrice = c.getDouble(0);
//                ItemDiscountAmount = c.getDouble(1);
//            }
//            c.close();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_add_to_void:
//                RecyclerViewAdapter.item_diss = "";
//                RecyclerViewAdapter.old_pricee  = "";
//                RecyclerViewAdapter.item_diss_amt = "";
//                //ProductSaveToBill(ENUM.CANCEL);
//                // ProductSaveToBill("REMOVE");
//                try {
//                    DBFunc.ExecQuery("DELETE FROM Details_BillProduct WHERE BillNo = '" + MainActivity.strbillNo + "' " +
//                            " AND ProductID = '"+product_id+"'", false);
//                    MainActivity.St = "1";
//                    dismiss();
//                }catch (IllegalFormatException e){
//                    Log.i("Err",e.getMessage());
//                    MainActivity.St = "1";
//                }finally {
//                    MainActivity.St = "1";
//                }
//                break;
//            case R.id.btn_add_to_bill:
//                RecyclerViewAdapter.item_diss = "";
//                RecyclerViewAdapter.old_pricee  = "";
//                RecyclerViewAdapter.item_diss_amt = "";
//                String bill_details_ID = "";
//                String sql = "Select ID FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
//
//                Cursor c = DBFunc.Query(sql, false);
//                if (c != null) {
//                    if (c.moveToNext()) {
//                        bill_details_ID = c.getString(0);
//                    }
//                    c.close();
//                }
//                if (bill_details_ID.length() > 0) {
//                    Query.DeleteForUpdate(MainActivity.strbillNo, txt_header_.getText().toString(), EPSF_item_id);
//                }
//
//                ProductSaveToBill(ENUM.SALES,bill_details_ID);
//                break;
//            case R.id.linear_close:
//                dismiss();
//                break;
//            case R.id.subtraction:
////                _counter++;
////                _stringVal = Integer.toString(_counter);
////                qun.setText(_stringVal);
//                Integer c_Sub = Integer.parseInt(qun.getText().toString());
////                _counter--;
////                if (_counter > 0 ){
////                    _counter = 1;
////                }
////                _stringVal = Integer.toString(_counter);
//                c_Sub--;
////                if (c_Sub > 0 ){
////                    c_Sub = 1;
////                }
//                if (c_Sub < 0 ){
//                    c_Sub = 1;
//                }
//                _stringVal = Integer.toString(c_Sub);
//                qun.setText(_stringVal);
//                break;
//            case R.id.addition:
////                _counter--;
////                if (_counter > 0 ){
////                    _counter = 1;
////                }
////                _stringVal = Integer.toString(_counter);
////                qun.setText(_stringVal);
//                Integer c_ADD = Integer.parseInt(qun.getText().toString());
//                c_ADD++;
//                _stringVal = Integer.toString(c_ADD);
//                qun.setText(_stringVal);
//                break;
//            case R.id.btn_item_discount:
//                qty = qun.getText().toString();
//                old_value = txt_old_value.getText().toString();
//                price = product_edit_price.getText().toString();
//                Intent DiscountActivity = new Intent(getContext(), ItemDiscountActivity.class);
//                DiscountActivity.putExtra("name","EditProductSheetFragment");
////                getActivity().finish();
//                startActivity(DiscountActivity);
//                break;
//        }
//    }
//
//    private void ProductSaveToBill(String itemStatus, String bill_details_ID) {
//        //MainActivity.St = "1";
//        //str_edit_product_sheet_fragment = "1";
//        str_edit_product_sheet_fragment = "0";
//        //qty = qun.getText().toString();
//        qty = qun.getText().toString();
//        old_value = txt_old_value.getText().toString();
//        price = product_edit_price.getText().toString();
////                //product_id
//
////        Log.i("___EPSF_item_pricee",EPSF_item_pricee); // original
////        Log.i("___EPSF_item_diss",EPSF_item_diss); // dis value
////        Log.i("___EPSF_old_pricee",EPSF_old_pricee); //after dis
//
////        String percentage_ = TabFragmentPercentage.str_percentage;
////        String dis_str = "";
////        if (!(percentage_.equals("0") || percentage_.isEmpty() || percentage_ == null || percentage_.equals("null"))){
////            dis_str = percentage_;
////        }
////        if (!(percentage_.equals("0") || percentage_.isEmpty() || percentage_ == null || percentage_.equals("null"))){
////            dis_str = percentage_;
////        }
//////
//        String chk_hide_img = Query.GetOptions(20);
//        if (chk_hide_img.equals("1")){
//            RecyclerViewNoImageAdapter.item_diss = str_percentage;
//            EPSF_original_item_pricee = RecyclerViewNoImageAdapter.original_item_pricee;
//        }else {
//            RecyclerViewAdapter.item_diss = str_percentage;
//            EPSF_original_item_pricee = RecyclerViewAdapter.original_item_pricee;
//        }
//        //Double dis_amt = (Double.valueOf(TabFragmentPercentage.str_percentage_value) * Double.valueOf(RecyclerViewAdapter.item_pricee)) / 100d;
//        Double dis_amt = 0.0;
//        Double new_amt = 0.0;
//        if (str_percentage_value.length() > 0) {
//            dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(EPSF_original_item_pricee)) / 100d;
//            //Double new_amt = Double.valueOf(RecyclerViewAdapter.item_pricee) - dis_amt;
//            new_amt = Double.valueOf(EPSF_original_item_pricee) - dis_amt;
//            dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(EPSF_original_item_pricee)) / 100d;
//
//        }
//        if (str_value.length() > 0){
//            dis_amt = Double.valueOf(EditProductSheetFragment.str_value);
//        }
////        Integer loop = Integer.parseInt(qty) - totalQty ;
//        Integer loop = Integer.parseInt(qty);
////
////        Integer loop_size = 0;
////        if (loop > 0){
////            loop_size = loop;
////        }else{
////            loop_size = Integer.parseInt(qty);
////        }
//        ClearExistingValue(chk_hide_img);
//
//        //for (int i = 0 ; i < loop_size ; i ++) {
////        int counttosave = 0;
//        try {
//            for (int i = 0; i < loop; i++) {
////            counttosave ++;
//                //str_edit_product_sheet_fragment = "0";
//                if (chk_hide_img.equals("1")) {
//                    //RecyclerViewNoImageAdapter.old_pricee = String.valueOf(new_amt);
//                    RecyclerViewNoImageAdapter.old_pricee = String.valueOf(EPSF_original_item_pricee);
//                    //RecyclerViewNoImageAdapter.old_pricee = EPSF_item_pricee;
//                    //RecyclerViewNoImageAdapter.old_pricee = EPSF_old_pricee;
//                    //RecyclerViewNoImageAdapter.old_pricee = txt_old_value.getText().toString();
//                    RecyclerViewNoImageAdapter.item_diss_amt = String.valueOf(dis_amt);
//                    // RecyclerViewNoImageAdapter.item_diss_amt = EPSF_item_diss;
//
//                    // RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(qty));
//                    RecyclerViewNoImageAdapter.sldIDArr.add(String.valueOf(product_id));
//                    RecyclerViewNoImageAdapter.sldQtyArr.add(product_id);
//                    RecyclerViewNoImageAdapter.sldNameArr.add(txt_header_.getText().toString());
//
//                    //RecyclerViewAdapter.original_item_pricee = product_edit_price.getText().toString();
//                    RecyclerViewAdapter.original_item_pricee = EPSF_original_item_pricee;
//                    RecyclerViewNoImageAdapter.sltPriceTotalArr.add(RecyclerViewNoImageAdapter.original_item_pricee);
//                    //RecyclerViewNoImageAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
//                    RecyclerViewNoImageAdapter.sltProductIDArr.add(String.valueOf(product_id));
//                    RecyclerViewNoImageAdapter.sltCategoryIDArr.add(category_id);
//                    RecyclerViewNoImageAdapter.sltCategoryNameArr.add(category_name);
//
//                    if (str_percentage_value.length() > 0) {
//                        RecyclerViewNoImageAdapter.slddisID.add(disID);
//                        RecyclerViewNoImageAdapter.slddisName.add(disName);
//                        RecyclerViewNoImageAdapter.slddisTypeName.add(disTypeName);
//                        RecyclerViewNoImageAdapter.slddisType.add(disType);
//                        RecyclerViewNoImageAdapter.slddisValue.add(disValue);
//                        RecyclerViewNoImageAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
//                    } else if (str_value.length() > 0) {
//                        RecyclerViewNoImageAdapter.slddisID.add(disIDAmt);
//                        RecyclerViewNoImageAdapter.slddisName.add(disNameAmt);
//                        RecyclerViewNoImageAdapter.slddisTypeName.add(disTypeNameAmt);
//                        RecyclerViewNoImageAdapter.slddisType.add(disTypeAmt);
//                        RecyclerViewNoImageAdapter.slddisValue.add(disValueAmt);
//                        RecyclerViewNoImageAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
//                    } else {
//                        RecyclerViewNoImageAdapter.slddisID.add(DiscountID);
//                        RecyclerViewNoImageAdapter.slddisName.add(DiscountName);
//                        RecyclerViewNoImageAdapter.slddisTypeName.add(DiscountTypeName);
//                        RecyclerViewNoImageAdapter.slddisType.add(DiscountType);
//                        RecyclerViewNoImageAdapter.slddisValue.add(DiscountValue);
//                        RecyclerViewNoImageAdapter.sltBillDisArr.add(DiscountItemDiscountAmount);
//                    }
//
//                    //    public static Integer disID = 0;
////    public static String disName = "";
////    public static String disOptions = "";
////    public static String disTypeName = "";
////    public static String disType = "";
//                } else {
//                    //RecyclerViewAdapter.old_pricee = txt_old_value.getText().toString();
//                    // RecyclerViewAdapter.old_pricee = String.valueOf(EPSF_old_pricee);
////                RecyclerViewAdapter.item_diss_amt = String.valueOf(EPSF_item_diss);
//                    //RecyclerViewAdapter.old_pricee = String.valueOf(new_amt);
//                    RecyclerViewAdapter.old_pricee = String.valueOf(EPSF_original_item_pricee);
//                    RecyclerViewAdapter.item_diss_amt = String.valueOf(dis_amt);
//
//                    // RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(qty));
//                    RecyclerViewAdapter.sldIDArr.add(String.valueOf(product_id));
//                    RecyclerViewAdapter.sldQtyArr.add(product_id);
//                    RecyclerViewAdapter.sldNameArr.add(txt_header_.getText().toString());
//
//                    //RecyclerViewAdapter.original_item_pricee = product_edit_price.getText().toString();
//                    RecyclerViewAdapter.original_item_pricee = EPSF_original_item_pricee;
//                    RecyclerViewAdapter.sltPriceTotalArr.add(RecyclerViewAdapter.original_item_pricee);
////                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
////                if (EPSF_item_diss.equals("")) {
////                    RecyclerViewAdapter.sltBillDisArr.add("0");
////                }else {
////                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(EPSF_item_diss));
////                }
////                Log.i("dfd___", "dfd_old_pricee_"+String.valueOf(RecyclerViewAdapter.old_pricee));
////                Log.i("dfd___", "dfd_original_item_pricee__"+String.valueOf(RecyclerViewAdapter.original_item_pricee));
////                Double disAmtt = 0.0;
////                disAmtt = Double.valueOf(RecyclerViewAdapter.old_pricee) - Double.valueOf(RecyclerViewAdapter.original_item_pricee);
////                if (disAmtt != 0.0) {
////                    RecyclerViewAdapter.sltBillDisArr.add("0");
////                    RecyclerViewAdapter.item_diss_amt = "0";
////                }else {
////                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(disAmtt));
////                    RecyclerViewAdapter.item_diss_amt = String.valueOf(disAmtt);
////                }
//                    RecyclerViewAdapter.sltProductIDArr.add(String.valueOf(product_id));
//                    RecyclerViewAdapter.sltCategoryIDArr.add(category_id);
//                    RecyclerViewAdapter.sltCategoryNameArr.add(category_name);
//                    Log.i("_11_", String.valueOf(str_percentage_value.length()));
//                    Log.i("_111_", String.valueOf(str_value.length()));
//                    if (str_percentage_value.length() > 0) {
//                        RecyclerViewAdapter.slddisID.add(disID);
//                        RecyclerViewAdapter.slddisName.add(disName);
//                        RecyclerViewAdapter.slddisTypeName.add(disTypeName);
//                        RecyclerViewAdapter.slddisType.add(disType);
//                        RecyclerViewAdapter.slddisValue.add(disValue);
//                        RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
//                    } else if (str_value.length() > 0) {
//                        RecyclerViewAdapter.slddisID.add(disIDAmt);
//                        RecyclerViewAdapter.slddisName.add(disNameAmt);
//                        RecyclerViewAdapter.slddisTypeName.add(disTypeNameAmt);
//                        RecyclerViewAdapter.slddisType.add(disTypeAmt);
//                        RecyclerViewAdapter.slddisValue.add(disValueAmt);
//                        RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
//                    } else {
//                        Log.i("_111111_", String.valueOf(DiscountItemDiscountAmount));
//                        RecyclerViewAdapter.slddisID.add(DiscountID);
//                        RecyclerViewAdapter.slddisName.add(DiscountName);
//                        RecyclerViewAdapter.slddisTypeName.add(DiscountTypeName);
//                        ;
//                        RecyclerViewAdapter.slddisType.add(DiscountType);
//                        RecyclerViewAdapter.slddisValue.add(DiscountValue);
//                        RecyclerViewAdapter.sltBillDisArr.add(DiscountItemDiscountAmount);
//                    }
//
//                    // RecyclerViewAdapter.status_on = "1";
////                MainActivity.St = "1";
////
////                    Intent i = new Intent(getContext(), MainActivity.class);
////                    i.putExtra("name", "EditProductSheetFragment");
////                    startActivity(i);
//                }
////
//            }
//        }catch (IllegalFormatException e){
//            Log.i("ERr",e.getMessage());
//        }finally {
//
//            Log.i("___finnally", String.valueOf(RecyclerViewAdapter.sltBillDisArr));
//            Log.i("___dfdfddfdfd__", String.valueOf(RecyclerViewAdapter.sltBillDisArr));
////        if (loop == counttosave) {
////            Log.i("_qqq__", String.valueOf(RecyclerViewAdapter.sltBillDisArr)+"_____"+counttosave);
//            if (bill_details_ID.length() > 0) {
////            if (loop > 0){
////
////            }else{
//                // Query.DeleteForUpdate(MainActivity.strbillNo, txt_header_.getText().toString(), EPSF_item_id);
//                //            }
//                String sql = " SELECT ModiName,ItemID,ID FROM PLUModi " +
//                        "WHERE BillNo = '" + MainActivity.strbillNo + "' and ItemID = '" + EditProductSheetFragment.uuid + "'";
//
//                Cursor c = DBFunc.Query(sql, false);
//                if (c != null) {
//                    while (c.moveToNext()) {
//                        DBFunc.ExecQuery("DELETE FROM PLUModi WHERE BillNo = '" + MainActivity.strbillNo + "' " +
//                                "AND ItemID = '" + c.getString(1) + "'", false);
//                    }
//                    c.close();
//                }
//                Integer billIDDD = Query.getBillID(MainActivity.strbillNo);
//                CheckOutActivity.UpdateBill(getContext(), billIDDD, MainActivity.strbillNo, bill_details_ID, itemStatus);
////            MainActivity.St = "1";
////            ProductMainPageFragment.St = "1";
////            RecyclerViewAdapter.St = "1";
//            } else {
//                Log.i("fgsfd_dsf__", "ghgy");
//                String strbillNo = "";
//                strbillNo = MainActivity.billNo(MainActivity.BillID + 1, strbillNo);
//                Integer totalItems = 0;
//                ArrayList<String> slddisID = new ArrayList<String>();
//                ArrayList<String> slddisName = new ArrayList<String>();
//                ArrayList<String> slddisTypeName = new ArrayList<String>();
//                ArrayList<String> slddisType = new ArrayList<String>();
//                ArrayList<String> slddisValue = new ArrayList<String>();
//                ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
//                ArrayList<String> sldIDArr = new ArrayList<String>();
//                ArrayList<String> sldNameArr = new ArrayList<String>();
//                ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
//                ArrayList<String> sltBillDisArr = new ArrayList<String>();
//                ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
//                ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
//                ArrayList<String> intTableNo = new ArrayList<String>();
//                ArrayList<String> vchQueueNo = new ArrayList<String>();
//                Integer countSelectedArr = 0;
//                if (chk_hide_img.equals("1")) {
//                    totalItems = RecyclerViewNoImageAdapter.totalItems;
//                    sldIDArr = RecyclerViewNoImageAdapter.sldIDArr;
//                    sldQtyArr = RecyclerViewNoImageAdapter.sldQtyArr;
//                    sldNameArr = RecyclerViewNoImageAdapter.sldNameArr;
//                    sltPriceTotalArr = RecyclerViewNoImageAdapter.sltPriceTotalArr;
//                    sltBillDisArr = RecyclerViewNoImageAdapter.sltBillDisArr;
//                    sltCategoryIDArr = RecyclerViewNoImageAdapter.sltCategoryIDArr;
//                    sltCategoryNameArr = RecyclerViewNoImageAdapter.sltCategoryNameArr;
//                    intTableNo = RecyclerViewNoImageAdapter.intTableNo;
//                    vchQueueNo = RecyclerViewNoImageAdapter.vchQueueNo;
//                    countSelectedArr = RecyclerViewNoImageAdapter.countSelectedArr;
//
//                    slddisID = RecyclerViewNoImageAdapter.slddisID;
//                    slddisName = RecyclerViewNoImageAdapter.slddisName;
//                    slddisTypeName = RecyclerViewNoImageAdapter.slddisTypeName;
//                    slddisType = RecyclerViewNoImageAdapter.slddisType;
//                    slddisValue = RecyclerViewNoImageAdapter.slddisValue;
//                } else {
//                    totalItems = RecyclerViewAdapter.totalItems;
//                    sldIDArr = RecyclerViewAdapter.sldIDArr;
//                    sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                    sldNameArr = RecyclerViewAdapter.sldNameArr;
//                    sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                    sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
//                    sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
//                    sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
//                    intTableNo = RecyclerViewAdapter.intTableNo;
//                    vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                    countSelectedArr = RecyclerViewAdapter.countSelectedArr;
//
//                    slddisID = RecyclerViewAdapter.slddisID;
//                    slddisName = RecyclerViewAdapter.slddisName;
//                    slddisTypeName = RecyclerViewAdapter.slddisTypeName;
//                    slddisType = RecyclerViewAdapter.slddisType;
//                    slddisValue = RecyclerViewAdapter.slddisValue;
//                }
//                String BillNo = String.valueOf(MainActivity.BillID);
//                Integer qty = 1;
//                String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                String paymenttype = "Cash";
//                String status = "SALES";
//                Double sub_total = 0.0;
//                Double amount = 0.0;
//                ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;
//
//                Log.i("Dfdfd_sltPriceTotalArr_", String.valueOf(sltPriceTotalArr));
//
////            DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ MainActivity.strbillNo + "' AND ProductName = '"+txt_header_.getText().toString()+"'", false);
////            //DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ MainActivity.strbillNo + "'", false);
////            DBFunc.ExecQuery("DELETE FROM Details_BillProduct WHERE BillNo = '"+ MainActivity.strbillNo + "' AND ProductName = '"+txt_header_.getText().toString()+"'", false);
////            DBFunc.ExecQuery("DELETE FROM BillPLU WHERE BillNo = '"+ MainActivity.strbillNo + "' AND Name = '"+txt_header_.getText().toString()+"'", false);
//
//                Integer billIDDD = Query.getBillID(strbillNo);
//                CheckOutActivity.SaveBill(billIDDD, strbillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                        qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                        sub_total, amount, paymenttype, Modifier_ID, intTableNo, vchQueueNo, itemStatus, slddisID, slddisName, slddisTypeName,
//                        slddisType, slddisValue);
////            MainActivity.St = "1";
////            ProductMainPageFragment.St = "1";
////            RecyclerViewAdapter.St = "1";
//                //CheckOutActivity.SaveBill(MainActivity.strbillNo);
//            }
//            //startActivity(itemDiscountActivity);
//
//////                Intent i = new Intent(getContext(), MainActivity.class);
//////                i.putExtra("name", "EditProductSheetFragment");
//////                startActivity(i);
////                startActivity(new Intent(getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
////                ((Activity)getContext()).finish();
//            String sql = " SELECT count(ProductQty),SUM(ProductPrice) FROM Details_BillProduct Where BillNo = '" + MainActivity.strbillNo + "' " +
//                    "  group by BillNo";
//            Cursor c = DBFunc.Query(sql, false);
//            if (c != null) {
//                if (chk_hide_img.equals("1")) {
//                    RecyclerViewNoImageAdapter.totalItems = 0;
//                    RecyclerViewNoImageAdapter.totalAmount = 0.0;
//                } else {
//                    RecyclerViewAdapter.totalItems = 0;
//                    RecyclerViewAdapter.totalAmount = 0.0;
//                }
//                while (c.moveToNext()) {
//                    //if (c.moveToNext()) {
//                    if (!c.isNull(0)) {
//                        if (chk_hide_img.equals("1")) {
//                            RecyclerViewNoImageAdapter.totalItems += c.getInt(0);
//                            RecyclerViewNoImageAdapter.totalAmount += Double.valueOf(c.getString(1));
//                        } else {
//                            RecyclerViewAdapter.totalItems += c.getInt(0);
//                            RecyclerViewAdapter.totalAmount += Double.valueOf(c.getString(1));
//                        }
//                    }
//                }
//                c.close();
//                if (chk_hide_img.equals("1")) {
//                    RecyclerViewNoImageAdapter.edit_fragment_status = "1";
//                } else {
//                    RecyclerViewAdapter.edit_fragment_status = "1";
//                }
//                ProductMainPageFragment.tet = "1";
//                //MainActivity.str_edit_product_sheetfragment = "1";
//                dismiss();
//            }
//
////                String str_product = "Product";
////                if (RecyclerViewAdapter.totalItems > 0) {
////                    str_product = "Products";
////                }
////                ing.valueOf(product_id));
////                RecyclerViewAdapter.sldQtyArr.add(product_id);
////                RecyclerViewAdapter.sldNameArr.add(txt_header_.getText().toString()
//
//            if (modiID.size() > 0) {
//                for (int i = 0; i < modiID.size(); i++) {
//                    DBFunc.ExecQuery("DELETE FROM PLUModi WHERE BillNo = '" + MainActivity.strbillNo + "' " +
//                            "AND ItemID = '" + modiID.get(i) + "'", false);
//                }
//                for (int i = 0; i < modiID.size(); i++) {
//                    sql = "INSERT INTO PLUModi (ItemID,ItemName,ModiName,BillNo,BillID) VALUES (";
//                    sql += "'" + uuid + "', ";
//                    sql += "'0',";
//                    sql += "'" + modiName.get(i) + "',";
//                    sql += "'" + MainActivity.strbillNo + "',";
//                    sql += "'0')";
//                    Log.i("Sf_PLUModi", sql);
//                    DBFunc.ExecQuery(sql, false);
//
//                }
//            }
//            if (UnSelectedItem.size() > 0) {
//                for (int i = 0; i < UnSelectedItem.size(); i++) {
////                sql = "DELETE FROM PLUModi WHERE ID = " + UnSelectedItem.get(i);
////                Log.i("ffgsql",sql);
////                DBFunc.ExecQuery(sql, false);
//                    DBFunc.ExecQuery("DELETE FROM PLUModi WHERE BillNo = '" + MainActivity.strbillNo + "' " +
//                            "AND ItemID = '" + UnSelectedItem.get(i) + "'", false);
//
//                }
//            }
////        MainActivity.St = "1";
////        //qun.setText("0");
////        RecyclerViewAdapter.St = "1";
////        ProductMainPageFragment.St = "1";
//            str_percentage = "";
//            str_percentage_value = "";
//            str_value = "";
//            disID = "";
//            disName = "";
//            disTypeName = "";
//            disType = "";
//            disValue = "";
//
////        TabFragmentAmount.str_percentage = "";
////        TabFragmentAmount.str_percentage_value = "";
//            MainActivity.name = "";
//            RecyclerViewAdapter.old_pricee = "";
//            RecyclerViewAdapter.item_diss_amt = "";
//            RecyclerViewAdapter.item_diss = "";
////        }
//
//        }
//    }
//
//    private void ClearExistingValue(String chk_hide_img) {
//        if (chk_hide_img.equals("1")){
//            RecyclerViewNoImageAdapter.old_pricee = "0";
//            RecyclerViewNoImageAdapter.item_diss_amt = "0";
//
//            RecyclerViewNoImageAdapter.sldIDArr.clear();
//            RecyclerViewNoImageAdapter.sldQtyArr.clear();
//            RecyclerViewNoImageAdapter.sldNameArr.clear();
//            RecyclerViewNoImageAdapter.sltPriceTotalArr.clear();
//            RecyclerViewNoImageAdapter.sltBillDisArr.clear();
//            RecyclerViewNoImageAdapter.sltProductIDArr.clear();
//            RecyclerViewNoImageAdapter.sltCategoryIDArr.clear();
//            RecyclerViewNoImageAdapter.sltCategoryNameArr.clear();
//
//            RecyclerViewNoImageAdapter.slddisID.clear();
//            RecyclerViewNoImageAdapter.slddisName.clear();
//            RecyclerViewNoImageAdapter.slddisTypeName.clear();
//            RecyclerViewNoImageAdapter.slddisType.clear();
//            RecyclerViewNoImageAdapter.slddisValue.clear();
//        }else {
//            RecyclerViewAdapter.old_pricee = "0";
//            RecyclerViewAdapter.item_diss_amt = "0";
//
//            // RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(qty));
//            RecyclerViewAdapter.sldIDArr.clear();
//            RecyclerViewAdapter.sldQtyArr.clear();
//            RecyclerViewAdapter.sldNameArr.clear();
//            RecyclerViewAdapter.sltPriceTotalArr.clear();
//            RecyclerViewAdapter.sltBillDisArr.clear();
//            RecyclerViewAdapter.sltProductIDArr.clear();
//            RecyclerViewAdapter.sltCategoryIDArr.clear();
//            RecyclerViewAdapter.sltCategoryNameArr.clear();
//            RecyclerViewAdapter.slddisID.clear();
//            RecyclerViewAdapter.slddisName.clear();
//            RecyclerViewAdapter.slddisTypeName.clear();
//            RecyclerViewAdapter.slddisType.clear();
//            RecyclerViewAdapter.slddisValue.clear();
//
//            RecyclerViewAdapter.slddisID.clear();
//            RecyclerViewAdapter.slddisName.clear();
//            RecyclerViewAdapter.slddisTypeName.clear();
//            RecyclerViewAdapter.slddisType.clear();
//            RecyclerViewAdapter.slddisValue.clear();
//
//        }
//    }
//
//    private void getDetailsBillProduct(String billNo) {
//        String chk_hide_img = Query.GetOptions(20);
//        String sql = " SELECT BillNo,count(ProductQty) FROM Details_BillProduct " +
//                " Where BillNo = '"+ billNo +"' AND ProductID = '"+Integer.parseInt(EPSF_item_id)+"'"+
//                "  AND Cancel = '"+ENUM.SALES+"' group by ProductID";
//        //Log.i("_sql__",sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            totalQty = 0;
//            while (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    //totalQty += c.getInt(1);
//                    totalQty = c.getInt(1);
//                }
//            }
//            c.close();
//        }
//    }
////    private void updateBill() {
////        String sql = "UPDATE Details_BillProduct SET ProductQty = '"+ qty +"' WHERE BillNo = '"+ MainActivity.strbillNo + "' and " +
////                " ProductName = '"+ txt_header_.getText().toString() +"' ";
////        Log.i("__sql",sql);
////        DBFunc.ExecQuery(sql, false);
////
//////        Intent intent = new Intent(getContext(), MainActivity.class);
//////        intent.putExtra("name", "CashierFloatAmountSheetFragment");
//////        getActivity().finish();
//////        startActivity(intent);
////
////    }
//}
//
//
