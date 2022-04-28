package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.EditProductSheetViewModel;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentEditProductSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatException;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.dcs.myretailer.app.Cashier.RecyclerViewAdapter.sldIDArr;

public class EditProductSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    //private static int _counter = Integer.parseInt(RecyclerViewAdapter.item_countt);
    private String _stringVal;
    public static Integer product_id = 0;
    public static String EditFragmentOpenPrice = "0";
    public static String ID = "0";
    public static String EditFragmentRemarks = "0";
    public static String EditFragmenttotalQty = "0";
    public static String EditOpenPrice = "0";
    String category_id = "";
    String category_name = "";
    public static String qty,old_value,price = "";
    public static String str_edit_product_sheet_fragment = "0";
    public static String productName = "0";
    public static String billNo = "0";
    //public static String productPrice = "0";
    public static String EditFragmentPrice = "0";
    //public static String productQty = "0";
    public static String productID = "0";
    public static String DetailBillID = "0";
    public static String FragmentVar = "0";
    public static String detail_bill_id = "0";
    Double ItemDiscountAmount = 0.0;
    String OpenPriceStatus = "0";
    public static Double ProductPrice = 0.0;
//    RecyclerView chooseButton;
    public static ArrayList<String> modiName = new ArrayList<String>();
    public static ArrayList<String> modiPrice = new ArrayList<String>();
    public static ArrayList<Integer> modiID = new ArrayList<Integer>();
    public static ArrayList<String> ItemID = new ArrayList<String>();
    public static ArrayList<String> UnSelectedItem =  new ArrayList<String>();
    String EPSF_item_id = "0";
    String EPSF_detail_bill_id = "0";
    String EPSF_category_id= "0";
    String EPSF_category_name= "0";
    String EPSF_item_name= "0";
    Double EPSF_item_pricee=  0.0;
    Double EPSF_ItemDiscountAmount= 0.0;
    String EPSF_item_diss= "0";
    public static String EPSF_old_pricee= "0";
    String EPSF_original_item_pricee= "0";
    public static String uuid = "0";
    public static String disVal = "";
    public static String disID = "0";
    public static String disName = "";
    public static String disOptions = "";
    public static String disTypeName = "";
    public static String disType = "";
    public static String disValue = "";
    public static String disIDAmt = "0";
    public static String disNameAmt = "";
    public static String disOptionsAmt = "";
    public static String disTypeNameAmt = "";
    public static String disTypeAmt = "";
    public static String disValueAmt = "";
    public static String str_percentage,str_percentage_value,old_pricee = "";
    public static String str_value = "";
    String DiscountID = "0";
    String DiscountName = "0";
    String DiscountType = "0";
    String DiscountTypeName = "0";
    String DiscountValue = "0";
    String DiscountItemDiscountAmount = "0";
    String DiscountItemDiscountTypeName = "0";
    public static ArrayList<Integer> ModifierID = new ArrayList<Integer>();
    public static ArrayList<String> ModifierName = new ArrayList<String>();
    public static ArrayList<String> ModifierPrice = new ArrayList<String>();
    public static FragmentEditProductSheetDialogBinding binding = null;
    public static EditProductSheetViewModel model = null;
    Context mcontext;
    public static Double ddd = 0.0;
    public static Integer dd1 = 0;
    String name = "";
    String remarks = "";
    String popupProductID = "";
    String name_remarks = "";
    FragmentManager manager = null;
    public EditProductSheetFragment() {

        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_product_sheet_dialog, container, false);
        try {
            manager = ((MainActivity)getContext()).getSupportFragmentManager();
        }catch (ClassCastException e){
            manager = ((CheckOutActivity)getContext()).getSupportFragmentManager();
        }
        model = ViewModelProviders.of(requireActivity()).get(EditProductSheetViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String device = Query.GetDeviceData(Constraints.DEVICE);

        if (device.equals("M2-Max")) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(450,
                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
            binding.LayShowPrice.setLayoutParams(params);
            binding.txtQuantity.setLayoutParams(params);
        }
        binding.linearClose.setOnClickListener(this);
        binding.productEditPrice.setEnabled(false);
        binding.productEditPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE );
        binding.btnAddToBill.setOnClickListener(this);
        binding.btnAddToVoid.setOnClickListener(this);
        binding.addition.setOnClickListener(this);
        binding.subtraction.setOnClickListener(this);
        binding.btnItemDiscount.setOnClickListener(this);
        binding.imgItemRemarks.setOnClickListener(this);

//        searchProductNameDis(product_id,EditFragmentOpenPrice);

        GetModifierData();

        updateDataFun();

        searchProductNameDis(product_id,EditFragmentOpenPrice);

        DiscountSetFunc(binding.itemDiscount,binding.rel);

    }

    private void updateDataFun() {
        ButtonAdapter customAdapter = new ButtonAdapter(getActivity(),ModifierID,ModifierName,ModifierPrice);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        binding.chooseButton.setLayoutManager(gridLayoutManager);
        binding.chooseButton.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        EPSF_item_id = RecyclerViewAdapter.item_id;
        EPSF_category_id = RecyclerViewAdapter.category_id;
        EPSF_category_name = RecyclerViewAdapter.category_name;
        EPSF_item_name = RecyclerViewAdapter.item_name;
        EPSF_item_pricee = Double.valueOf(RecyclerViewAdapter.item_pricee);

//        if (FragmentVar.equals("CheckoutItem")) {
//            EPSF_ItemDiscountAmount = 0.0;
//            EPSF_item_diss = item_diss;
//            EPSF_old_pricee = old_pricee;
//        } else {

            EPSF_ItemDiscountAmount = RecyclerViewAdapter.ItemDiscountAmount;

            EPSF_item_diss = RecyclerViewAdapter.item_diss;
            EPSF_old_pricee = RecyclerViewAdapter.old_pricee;


//        }
        if (productID.equals("0")) {
            product_id = Integer.parseInt(EPSF_item_id);
        }else {
            product_id = Integer.parseInt(productID);
        }

        if (!DetailBillID.equals("0")){
            detail_bill_id = DetailBillID;
        }else {
            detail_bill_id = EPSF_detail_bill_id;
        }
        uuid = Query.GetUUID(product_id);

        Integer totalOnHandQty = Query.GetOnHandQtyByPLU(product_id);

        binding.txtOnHandQty.setText("( "+totalOnHandQty+" )");

        category_id = EPSF_category_id;
        category_name = EPSF_category_name;

//        if (!productName.equals("0")) {
//            EPSF_item_name = productName;
//        }
//        binding.txtHeader.setText(EPSF_item_name);

        searchProductNameDis(product_id,EditFragmentOpenPrice);

        binding.qun.setText(EditFragmenttotalQty);

        if (EditFragmentPrice != null && !EditFragmentPrice.equals("0")) {


            if (EditFragmentPrice != null && !(EditFragmentPrice.equals(""))) {
                EPSF_item_pricee = Double.valueOf(EditFragmentPrice);
            }
//            Double qty_edit_bottom_fragment = 0.0;
//            if (binding.qun.getText().toString() != null && !(binding.qun.getText().toString().equals(""))) {
//                 qty_edit_bottom_fragment = Double.valueOf(binding.qun.getText().toString());
//            }
//                //txt_old_value.setText("$"+String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
            if (EPSF_item_pricee != null && !(EPSF_item_pricee.equals(""))) {
//                EPSF_item_pricee = Double.valueOf(EPSF_item_pricee) * qty_edit_bottom_fragment;
                EPSF_item_pricee = Double.valueOf(EPSF_item_pricee);
            }
        }

        //_counter = Integer.parseInt(EditFragmenttotalQty);
    }

    public static void GetModifierData() {
        String sql = "Select ID, Name,Price from "+ Constraints.ProductModifier;
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            modiName.clear();
            modiPrice.clear();
            modiID.clear();
            ModifierID.clear();
            ModifierName.clear();
            ModifierPrice.clear();
            while (c.moveToNext()) {
                ModifierID.add(c.getInt(0));
                ModifierName.add(c.getString(1));
                ModifierPrice.add(c.getString(2));
            }
            c.close();
        }
    }
    private void DiscountSetFunc(TextView item_discount, RelativeLayout rel) {


        if (!(EPSF_item_diss == null || EPSF_item_diss.equals("0") || EPSF_item_diss.isEmpty() || EPSF_item_diss.equals(""))) {


            if (str_percentage_value != null && str_percentage_value.length() > 0) {
                binding.itemDiscount.setText(EPSF_item_diss + " Off");

                binding.productEditPrice.setText(String.format("%.2f", Double.valueOf(EPSF_old_pricee)));
//                product_edit_price.setText(String.format("%.2f", Double.valueOf(actualamt)));
            }else {

                binding.itemDiscount.setText("$" + EPSF_item_diss+ " Off");

                Double reductamt = Double.valueOf(binding.qun.getText().toString()) * Double.valueOf(EPSF_item_diss);
                Double actualamt = Double.valueOf(EPSF_item_pricee) - reductamt;

                binding.productEditPrice.setText(String.format("%.2f", actualamt));
//                product_edit_price.setText(String.format("%.2f", Double.valueOf(EPSF_old_pricee)));
//                product_edit_price.setText(String.format("%.2f", actualamt));
            }
            rel.setVisibility(View.VISIBLE);
            //erase.setVisibility(View.VISIBLE);
            binding.txtOldValue.setVisibility(View.VISIBLE);
            Double qty_edit_bottom_fragment = Double.valueOf(binding.qun.getText().toString());
            //txt_old_value.setText("$"+String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
            //EPSF_item_pricee = Double.valueOf(EPSF_item_pricee) * qty_edit_bottom_fragment;

            if (EPSF_item_pricee > 0.0) {
                binding.txtOldValue.setText(String.format("%.2f", EPSF_item_pricee));
                EPSF_item_pricee = 0.0;
            }
            binding.txtOldValue.setPaintFlags(binding.txtOldValue.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {

            if (ItemDiscountAmount > 0.0) {

                Double priceee = ProductPrice + ItemDiscountAmount; // Before Discount / Original Price


                ShowDisPercentageAmountValue(item_discount);

    //            Double qty_edit_bottom_fragment = Double.valueOf(binding.qun.getText().toString());

                //product_edit_price.setText("$"+String.format("%.2f", ProductPrice));
                //ProductPrice = ProductPrice * qty_edit_bottom_fragment;
                ProductPrice = ProductPrice;

                binding.productEditPrice.setText(String.format("%.2f", ProductPrice));
                rel.setVisibility(View.VISIBLE);
                //erase.setVisibility(View.VISIBLE);
                binding.txtOldValue.setVisibility(View.VISIBLE);
                //txt_old_value.setText("$"+String.format("%.2f", priceee));
               // priceee = priceee * qty_edit_bottom_fragment;
                priceee = priceee;
                binding.txtOldValue.setText(String.format("%.2f", priceee));
                binding.txtOldValue.setPaintFlags(binding.txtOldValue.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
               // Log.i("EPSF_item_diss_elseelse_", String.valueOf(EPSF_item_pricee));
//            //ShowDisPercentageAmountValue(item_discount);
//            //Double qty_edit_bottom_fragment = Double.valueOf(binding.qun.getText().toString());
//            //EPSF_item_pricee = EPSF_item_pricee * qty_edit_bottom_fragment;
//
                if (EPSF_item_pricee != null && !(EPSF_item_pricee.equals("")) ) {
                    binding.productEditPrice.setText(String.format("%.2f", Double.valueOf(EPSF_item_pricee)));
                }
            }
        }
    }

    private void ShowDisPercentageAmountValue(TextView item_discount) {
        try {

            if (DiscountItemDiscountTypeName != null && !(DiscountItemDiscountTypeName.equals("0") || DiscountItemDiscountTypeName.isEmpty() ||
                    DiscountItemDiscountTypeName == null || DiscountItemDiscountTypeName.equals(""))) {
                if (DiscountItemDiscountTypeName.equals("% Percentage")){
                    binding.itemDiscount.setText(DiscountValue + " %Off");
                }else {
                    binding.itemDiscount.setText("$" + DiscountValue+ " Off");
                }
            }
        }catch (Exception e){

        }
    }

    Double beforeItemDisProductAmt = 0.0;
    Double beforeItemDisProductQty = 0.0;
    private void searchProductNameDis(Integer productId, String editFragmentOpenPrice) {
        String sql = "";
        try {
            sql = "Select ProductPrice,ItemDiscountAmount,OpenPriceStatus," +
                    "DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,ItemDiscountAmount,DiscountTypeName," +
                    "ProductName,Remarks,ProductID,ProductQty " +
                    "FROM DetailsBillProduct ";

            if (FragmentVar.equals("CheckoutItem")) {
                sql += " Where ID = " + ID;
            } else {
                sql += "Where BillNo = '" + billNo + "' AND ProductID = '" + productId + "'  ";
                sql += Query.CheckingNullOrNot(editFragmentOpenPrice,"OpenPriceStatus");
                sql += "group by ProductID,OpenPriceStatus";
            }
        } catch (Exception e){

        }
        Cursor Cursor_Dis = DBFunc.Query(sql, false);
        if (Cursor_Dis != null) {
            beforeItemDisProductAmt = 0.0;
            ProductPrice = 0.0;
            OpenPriceStatus = "0";
            ItemDiscountAmount = 0.0;
            remarks = "";
            name = "";
            name_remarks = "";
            popupProductID = "";

            OpenPriceStatus = "0";
            DiscountID = "0";
            DiscountName = "0";
            DiscountType = "0";
            DiscountTypeName = "0";
            DiscountValue = "0";
            DiscountItemDiscountAmount = "0";
            DiscountItemDiscountTypeName = "0";

            name = "";
            remarks = "";

            if (Cursor_Dis.moveToNext()) {

                beforeItemDisProductAmt = Cursor_Dis.getDouble(0);
                beforeItemDisProductQty = Cursor_Dis.getDouble(13);
                ProductPrice = Cursor_Dis.getDouble(0) - Cursor_Dis.getDouble(1);
                ItemDiscountAmount = Cursor_Dis.getDouble(1);
                OpenPriceStatus = Cursor_Dis.getString(2);
                DiscountID = Cursor_Dis.getString(3);
                DiscountName = Cursor_Dis.getString(4);
                DiscountType = Cursor_Dis.getString(5);
                DiscountTypeName = Cursor_Dis.getString(6);
                DiscountValue = Cursor_Dis.getString(7);
                DiscountItemDiscountAmount = Cursor_Dis.getString(8);
                DiscountItemDiscountTypeName = Cursor_Dis.getString(9);
                name = Cursor_Dis.getString(10);
                remarks = Cursor_Dis.getString(11);
                popupProductID = Cursor_Dis.getString(12);

                if (remarks!= null && remarks.trim() != null && remarks.trim().length() > 0 && !remarks.equals("0")){
                    name_remarks = name + " ("+remarks+") ";
                }else {
                    name_remarks = name;
                }
            }
            Cursor_Dis.close();
        }
        //binding.txtHeader.setText(name_remarks);
        binding.txtHeader.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_item_remarks:
                dismiss();

//                MainActivity.binding.container.setAlpha(0.4F);
////                binding.inner.setAlpha(0.4F);
////                binding.inner.setVisibility(View.GONE);
//
//                LayoutInflater inflater = (LayoutInflater) getContext()
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                final View popUpView = inflater.inflate(R.layout.activity_item_remarks_pop_up,
//                        (ViewGroup) v.findViewById(R.id.fadePopup));
//
//                PopupWindow mpopup = new PopupWindow(popUpView, LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT, true); // Creation of popup
//                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
//
//                mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
//                mpopup.setOutsideTouchable(false);
//
//                LinearLayout lvclose = (LinearLayout) popUpView.findViewById(R.id.linear_close) ;
//
//                EditText pop_up_window_item_remarks = (EditText) popUpView.findViewById(R.id.pop_up_window_itemremarks) ;
//                pop_up_window_item_remarks.setText(remarks);
//
//
//                Button btn_add_pop_up_window_itemremarks = (Button) popUpView.findViewById(R.id.btn_add_pop_up_window_itemremarks) ;
//                btn_add_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                       // Toast.makeText(getContext(), "Update Successfully!", Toast.LENGTH_LONG).show();
//                        mpopup.dismiss();
//
//                        updateAndRefreshRemarks(pop_up_window_item_remarks.getText().toString());
//                        //onResume();
//                    }
//                });
//                Button btn_cancel_pop_up_window_itemremarks = (Button) popUpView.findViewById(R.id.btn_cancel_pop_up_window_itemremarks) ;
//                btn_cancel_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mpopup.dismiss();
//                        //onResume();
//                        MainActivity.binding.container.setAlpha(1);
//                    }
//                });
//
//                lvclose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mpopup.dismiss();
//                        //onResume();
//                        MainActivity.binding.container.setAlpha(1);
//                    }
//                });
                break;
            case R.id.btn_add_to_void:
                RecyclerViewAdapter.item_diss = "";
                RecyclerViewAdapter.old_pricee  = "";
                RecyclerViewAdapter.item_diss_amt = "";

                try {
                    String deletesql = "";

                    try {
                        deletesql = "DELETE FROM DetailsBillProduct ";

                        if (FragmentVar.equals("CheckoutItem")) {
                            deletesql += " WHERE ID = " + ID + " ";
                        } else {
                            deletesql += " WHERE BillNo = '" + billNo + "' " +
                                    " AND ProductID = '" + product_id + "' ";
                            deletesql += Query.CheckingNullOrNot(EditFragmentOpenPrice,"OpenPriceStatus");
                        }
                        DBFunc.ExecQuery(deletesql, false);

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("Deleted-EditProductSheetFragment -delete-" + deletesql));

                    } catch (Exception e){
                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("Err-EditProductSheetFragment -delete-" + e.getMessage()));
                    }

                    //DeletePLUPro
                    DeletePluPro(billNo,product_id);

                    updateBillListTotalAmountAndQty(billNo);

                    //Delete PLUModi
                    DeletePLUModi(billNo,uuid,EditFragmentOpenPrice,EditFragmentRemarks,ID);

                    dismiss();
                    billNo = "";
                }catch (IllegalFormatException e){
                    MainActivity.St = "1";
                }finally {
                    MainActivity.St = "1";
                    binding.txtOldValue.setText("");
                    binding.productEditPrice.setText("");
                }
                modiID.clear();
                modiName.clear();
                modiPrice.clear();
                UnSelectedItem.clear();

                break;
            case R.id.btn_add_to_bill:
                //dismiss();
                Query.EditFragmentOpenPrice = EditFragmentOpenPrice;
                Query.EditFragmentRemarks = EditFragmentRemarks;

                String qtychk = binding.qun.getText().toString();
                if (qtychk == null) {
                    qtychk = "0";
                }
                if (qtychk != null && Integer.parseInt(qtychk) < 0){
                    final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            //.setTitleText("Cancelled Bill")
                            .setContentText("Qty must be greater than zero.")
                            .setConfirmText(Constraints.OK)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                }
                            });
                    pRefundDialog.show();
                    pRefundDialog.setCancelable(false);
                }else {

                    RecyclerViewAdapter.item_diss = "";
                    RecyclerViewAdapter.old_pricee  = "";
                    RecyclerViewAdapter.item_diss_amt = "";

                    OpenPriceStatusSaveFun(billNo);

                    ProductSaveToBill(Constraints.SALES,billNo);

                    updateBillListTotalAmountAndQty(billNo);

                    billNo = "";
                    binding.txtOldValue.setText("");
                    binding.productEditPrice.setText("");

                    dismiss();
                }
                break;
            case R.id.linear_close:
                dismiss();
                break;
            case R.id.subtraction:
                Integer c_Sub = Integer.parseInt(binding.qun.getText().toString());
                c_Sub--;
                if (c_Sub < 0 ){
                    c_Sub = 1;
                }
                _stringVal = Integer.toString(c_Sub);
                binding.qun.setText(_stringVal);
                break;
            case R.id.addition:
                Integer c_ADD = Integer.parseInt(binding.qun.getText().toString());
                c_ADD++;
                _stringVal = Integer.toString(c_ADD);
                binding.qun.setText(_stringVal);
                break;
            case R.id.btn_item_discount:
                EPSF_item_diss = "0";
                RecyclerViewAdapter.item_diss = "0";
                CheckOutActivity.item_dis_amt = 0.0;
                qty = binding.qun.getText().toString();
                old_value = binding.txtOldValue.getText().toString();
                price = binding.productEditPrice.getText().toString();
                Intent DiscountActivity = new Intent(getContext(), ItemDiscountActivity.class);
                if (FragmentVar.equals("CheckoutItem")) {
                    //CheckOutActivity.ItemPriceTotal = price;
                    CheckOutActivity.ItemPriceTotal = EditFragmentPrice;//OriginalPrice
                    DiscountActivity.putExtra("name", FragmentVar);
                }else {
                    DiscountActivity.putExtra("name", "EditProductSheetFragment");
                }
                startActivity(DiscountActivity);
                ((Activity)getContext()).finish();
                break;
        }
    }

    private void DeletePluPro(String billNo,Integer product_id) {
        String delete_plupromo = "DELETE From PLUPromo WHERE BillNo = '"+billNo+"' AND PLUID = '"+product_id+"'";
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Deleted-Plupro" + delete_plupromo));
        DBFunc.ExecQuery(delete_plupromo,false);
    }

    private void DeletePLUModi(String billNo,String uuid,String EditFragmentOpenPrice,String EditFragmentRemarks,String ID) {
//        String sqlunselected = "DELETE FROM PLUModi WHERE BillNo = '" + billNo + "' " +
//                "AND PLU_UUID = '" + uuid + "' " ;
////        " AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' " +
////        " AND Remarks = '"+EditFragmentRemarks+"' " ;
//
//        sqlunselected += Query.CheckingNullOrNot(EditFragmentOpenPrice,"OpenPriceStatus");
//        sqlunselected += Query.CheckingNullOrNot(EditFragmentRemarks,"Remarks");

        String sqlunselected = "DELETE FROM PLUModi WHERE BillNo = '"+billNo+"' AND DetailsBillProductID = '" + ID + "' ";


        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Deleted-PLUModi" + sqlunselected));

        DBFunc.ExecQuery(sqlunselected, false);
    }

    private void OpenPriceStatusSaveFun(String billno_BDProduct) {

        if (EditFragmentOpenPrice != null && EditFragmentOpenPrice.length() > 1) {

            Integer bill_plu_id = Query.findLatestID("ID", "DetailsBillProduct", false);
            String sql = "Select BillNo,SUM(ProductQty) from DetailsBillProduct " +
                    "Where BillNo = '"+billno_BDProduct+"' " +
                    "AND ProductId = '"+productID+"' " ;

            sql += Query.CheckingNullOrNot(EditFragmentOpenPrice,"OpenPriceStatus");

            sql += "group by BillNo,ProductID,OpenPriceStatus,Remarks";

            Cursor c = DBFunc.Query(sql,false);
            if (c != null){
                if (c.getCount() == 0){
                    bill_plu_id = bill_plu_id + 1;
                    RecyclerViewAdapter.openPrice = "_" + bill_plu_id;
                }else {
                    if (c.moveToNext()) {
                        RecyclerViewAdapter.openPrice = EditFragmentOpenPrice;
                    }
                }
                c.close();
            }
        }else {
            RecyclerViewAdapter.openPrice = "0";
        }
    }

    private void updateBillListTotalAmountAndQty(String billNo) {
        String queray1 = "Select ProductPrice from DetailsBillProduct where BillNo = '"+billNo+"'";
        Cursor cc1 = DBFunc.Query(queray1,false);
        if (cc1 != null) {
            if (cc1.getCount() == 0){
                String queryd = "UPDATE BillList SET ";
                queryd += "TotalAmount = '0', ";
                queryd += "TotalItems = '0' ";
                queryd += "WHERE BillNo = '"+billNo+"' ";


                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("UpdatedBillList-EditProductSFragment-Zero" + queryd));

                DBFunc.ExecQuery(queryd,false);
            }
            cc1.close();
        }

        String queray = "Select (ProductPrice),(ProductQty),(ItemDiscountAmount) from DetailsBillProduct " +
                "where BillNo = '"+billNo+"' group by ProductID";
//        String queray = "Select SUM(ProductPrice),SUM(ProductQty),SUM(ItemDiscountAmount) from DetailsBillProduct " +
//                "where BillNo = '"+billNo+"' group by ProductID";
        Cursor cc = DBFunc.Query(queray,false);

        if (cc != null) {
            ddd = 0.0;
            dd1 = 0;
            while (cc.moveToNext()){
                //ddd += ( cc.getDouble(0) - cc.getDouble(2) )* cc.getDouble(1);
                ddd +=  cc.getDouble(0) -( cc.getDouble(2) * cc.getDouble(1));
                dd1 += cc.getInt(1);
            }
            cc.close();
        }
        String query = "UPDATE BillList SET ";
        query += "TotalAmount = '"+ddd+"', ";
        query += "TotalItems = '"+dd1+"' ";
        query += "WHERE BillNo = '"+billNo+"' ";

        DBFunc.ExecQuery(query,false);


        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("UpdatedBillList-EditProductSFragment" + query));

    }

    private void CallCheckAdapterFun() {
        if (productID.equals("0")) {

        }else {
//            Intent intent = new Intent(getContext(), CheckOutActivity.class);
//            intent.putExtra("BillNo", billNo);
//            intent.putExtra("Status", "EditProductSheetFragment");
//            intent.putExtra("StatusSALES", "");
//            startActivity(intent);
//            ((Activity) getContext()).finish();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        //onResume();

        if (productID.equals("0")){
//            updateMainFunu(getContext());
            //dismiss();

            ProductMainPageFragment.RunAuto();
        }else {
            //dismiss();

            CheckOutActivity.updateMediaButtons();
        }
        super.onDismiss(dialog);

    }

//    @Override
//    public void onResume() {
//
////        searchProductNameDis(product_id,EditFragmentOpenPrice);
//        GetModifierData();
//        updateDataFun();
//        searchProductNameDis(product_id,EditFragmentOpenPrice);
//
//        Log.i("Sdfsd____","dsfdsf______11__"+str_value);
//        DiscountSetFunc(binding.itemDiscount,binding.rel);
//        super.onResume();
//    }

    private void ProductSaveToBill(String itemStatus, String strbillNo) {

        if (UnSelectedItem.size() > 0) {
            for (int i = 0; i < UnSelectedItem.size(); i++) {

                try {

                    // String sqlunselected = "DELETE FROM PLUModi WHERE DetailsBillProductID = '" + ID + "' " ;
                    String sqlunselected = "DELETE FROM PLUModi  " +
                            " WHERE  BillNo = '"+MainActivity.strbillNo+"' AND DetailsBillProductID = '" + ID + "' " ;

                    DBFunc.ExecQuery(sqlunselected, false);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Delete-EditProductSheetFragment -ProductSaveToBill-" +
                                    sqlunselected));

                } catch (Exception e){
                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Err-EditProductSheetFragment -ProductSaveToBill-" + e.getMessage()));

                }
            }
        }

        String sql = "";
        if (modiID.size() > 0) {
            for (int i = 0; i < modiID.size(); i++) {
                String sqlunselected = "DELETE FROM PLUModi  " +
                        " WHERE  BillNo = '"+MainActivity.strbillNo+"' AND DetailsBillProductID = '" + ID + "' " ;

                DBFunc.ExecQuery(sqlunselected, false);

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Delete-EditProductSheetFragment -ProductSaveToBill-" +
                                sqlunselected));
//                String name = "0";
//                String billid = Query.findBillIDByBillNo(billNo);
//                Query.SavePLUModi(uuid,modiID.get(i),name,modiName.get(i),modiPrice.get(i),0,EditFragmentOpenPrice,
//                        EditFragmentRemarks,billNo,billid,ID);

            }
        }
        if (modiID.size() > 0) {
            for (int i = 0; i < modiID.size(); i++) {
                String name = "0";
                String billid = Query.findBillIDByBillNo(billNo);
                Query.SavePLUModi(uuid,modiID.get(i),name,modiName.get(i),modiPrice.get(i),0,EditFragmentOpenPrice,
                        EditFragmentRemarks,billNo,billid,ID);

            }
        }


        Query.UpdatePLUModi(getContext(),popupProductID,MainActivity.strbillNo,EditFragmentRemarks,EditFragmentOpenPrice,ID);

        str_edit_product_sheet_fragment = "0";
        qty = binding.qun.getText().toString();
        old_value = binding.txtOldValue.getText().toString();
        price = binding.productEditPrice.getText().toString();

        searchProductNameDis(product_id,EditFragmentOpenPrice);

        RecyclerViewAdapter.item_diss = str_percentage;
       // EPSF_original_item_pricee = RecyclerViewAdapter.original_item_pricee;
        EPSF_original_item_pricee = String.valueOf(beforeItemDisProductAmt / beforeItemDisProductQty);


        //Double dis_amt = (Double.valueOf(TabFragmentPercentage.str_percentage_value) * Double.valueOf(RecyclerViewAdapter.item_pricee)) / 100d;
        Double dis_amt = 0.0;
        Double new_amt = 0.0;
        try {
            if (str_percentage_value != null && str_percentage_value.length() > 0 &&
                    EPSF_original_item_pricee != null && EPSF_original_item_pricee.length() > 0) {
                //dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(EPSF_original_item_pricee)) / 100d;
                //Double new_amt = Double.valueOf(RecyclerViewAdapter.item_pricee) - dis_amt;
                //new_amt = Double.valueOf(EPSF_original_item_pricee) - dis_amt;
                dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(EPSF_original_item_pricee)) / 100d;
            }
        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-EditProductSheetFragment -ProductSaveToBill-" + e.getMessage()));
        }
        Log.i("str_value__","str_value____"+str_value);
        if (CheckOutActivity.str_value != null && !(CheckOutActivity.str_value.isEmpty())) {
            if (CheckOutActivity.str_value != null && CheckOutActivity.str_value.length() > 0) {
                //dis_amt = Double.valueOf(EditProductSheetFragment.str_value);
                dis_amt = Double.valueOf(CheckOutActivity.str_value);
            }
        }else if (str_value != null && !(str_value.isEmpty())) {
            if (str_value != null && str_value.length() > 0) {
                //dis_amt = Double.valueOf(EditProductSheetFragment.str_value);
                dis_amt = Double.valueOf(str_value);
            }
        }

//        Double reductamt = Double.valueOf(qun.getText().toString()) * Double.valueOf(EPSF_item_diss);
//        Double actualamt = Double.valueOf(EPSF_item_pricee) - reductamt;

        //Integer loop = Integer.parseInt(qty);
        Integer qty = Integer.valueOf(binding.qun.getText().toString());
//         Log.i("DSFDFloop__","loopp__"+loop);
//        ClearExistingValue(chk_hide_img);
        try {
//            for (int i = 0; i < loop; i++) {

            RecyclerViewAdapter.old_pricee = String.valueOf(EPSF_original_item_pricee);
            RecyclerViewAdapter.item_diss_amt = String.valueOf(dis_amt);
            sldIDArr.add(String.valueOf(product_id));
//                    RecyclerViewAdapter.sldQtyArr.add(product_id);
            RecyclerViewAdapter.sldQtyArr.add(qty);
            //RecyclerViewAdapter.sldNameArr.add(binding.txtHeader.getText().toString());
            RecyclerViewAdapter.sldNameArr.add(name);

            RecyclerViewAdapter.original_item_pricee = EPSF_original_item_pricee;
            RecyclerViewAdapter.sltPriceTotalArr.add(RecyclerViewAdapter.original_item_pricee);

            RecyclerViewAdapter.sltProductIDArr.add(String.valueOf(product_id));
            RecyclerViewAdapter.sltCategoryIDArr.add(category_id);
            RecyclerViewAdapter.sltCategoryNameArr.add(category_name);

            try {
                if (str_percentage_value != null && str_percentage_value.length() > 0) {
                    Log.i("Sdfdsf___","dsfsDiscountValue6__"+disValue+"-"+dis_amt);

                    RecyclerViewAdapter.slddisID.add(disID);
                    RecyclerViewAdapter.slddisName.add(disName);
                    RecyclerViewAdapter.slddisTypeName.add(disTypeName);
                    RecyclerViewAdapter.slddisType.add(disType);
                    RecyclerViewAdapter.slddisValue.add(disValue);
                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
                }
//                else if (str_value.length() > 0) {
//                    RecyclerViewAdapter.slddisID.add(disIDAmt);
//                    RecyclerViewAdapter.slddisName.add(disNameAmt);
//                    RecyclerViewAdapter.slddisTypeName.add(disTypeNameAmt);
//                    RecyclerViewAdapter.slddisType.add(disTypeAmt);
//                    RecyclerViewAdapter.slddisValue.add(disValueAmt);
//                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));
//                }
                else if (CheckOutActivity.str_value != null && CheckOutActivity.str_value.length() > 0) {

                    disID = CheckOutActivity.disIDAmt;
                    disName = CheckOutActivity.disNameAmt;
                    disTypeName = disTypeNameAmt;
                    disType = disTypeAmt;
                    disValue = disValueAmt;
                    Log.i("Sdfdsf___","dsfsDiscountValue5__"+disValue+"-"+dis_amt);
                    RecyclerViewAdapter.slddisID.add(CheckOutActivity.disIDAmt);
                    RecyclerViewAdapter.slddisName.add(CheckOutActivity.disNameAmt);
                    RecyclerViewAdapter.slddisTypeName.add(CheckOutActivity.disTypeNameAmt);
                    RecyclerViewAdapter.slddisType.add(CheckOutActivity.disTypeAmt);
                    RecyclerViewAdapter.slddisValue.add(CheckOutActivity.disValueAmt);
                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));

                    CheckOutActivity.str_value = "";
                    CheckOutActivity.disIDAmt = "0";
                    CheckOutActivity.disNameAmt = "";
                    CheckOutActivity.disTypeNameAmt = "";
                    CheckOutActivity.disTypeAmt = "";
                    CheckOutActivity.disValueAmt = "";

                }  else if (str_value != null && str_value.length() > 0) {

                    disID = disIDAmt;
                    disName = disNameAmt;
                    disTypeName = disTypeNameAmt;
                    disType = disTypeAmt;
                    disValue = disValueAmt;
                    Log.i("Sdfdsf___","dsfsDiscountValue5__"+disValue+"-"+dis_amt);
                    RecyclerViewAdapter.slddisID.add(disIDAmt);
                    RecyclerViewAdapter.slddisName.add(disNameAmt);
                    RecyclerViewAdapter.slddisTypeName.add(disTypeNameAmt);
                    RecyclerViewAdapter.slddisType.add(disTypeAmt);
                    RecyclerViewAdapter.slddisValue.add(disValueAmt);
                    RecyclerViewAdapter.sltBillDisArr.add(String.valueOf(dis_amt));

                    str_value = "";
                    disIDAmt = "0";
                    disNameAmt = "";
                    disTypeNameAmt = "";
                    disTypeAmt = "";
                    disValueAmt = "";

                }
                else {

//                    searchProductNameDis(product_id,EditFragmentOpenPrice);
                    disID = DiscountID;
                    disName = DiscountName;
                    disTypeName = DiscountTypeName;
                    disType = DiscountType;
                    disValue = DiscountValue;
                    dis_amt = Double.valueOf(DiscountItemDiscountAmount);//Just

                    Log.i("Sdfdsf___","dsfsDiscountValue4__"+disValue+"-"+dis_amt);

                    RecyclerViewAdapter.slddisID.add(DiscountID);
                    RecyclerViewAdapter.slddisName.add(DiscountName);
                    RecyclerViewAdapter.slddisTypeName.add(DiscountTypeName);
                    RecyclerViewAdapter.slddisType.add(DiscountType);
                    RecyclerViewAdapter.slddisValue.add(DiscountValue);
                    RecyclerViewAdapter.sltBillDisArr.add(DiscountItemDiscountAmount);
                }
            }catch (NullPointerException e){
                Log.i("ErrorNull__",e.getMessage());
            }

            String BillNo = String.valueOf(MainActivity.BillID);
            //Integer qty = 1;
            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
            String paymenttype = "Cash";
            String status = Constraints.SALES;
            Double sub_total = 0.0;
            Double amount = 0.0;
            ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;

            Integer billIDDD = Query.getBillID(strbillNo);

            Log.i("SDf__CHecking_","Checking___"+ID);
            Log.i("Sdfdsf___","dsfsDiscountValue3__"+disValue+"-"+remarks);
            CheckOutActivity.saveDetailsBillProduct(billIDDD, strbillNo, name, RecyclerViewAdapter.original_item_pricee,
                    String.valueOf(dis_amt), category_name,
                    qty, String.valueOf(product_id), category_id, dateFormat3,   Modifier_ID,
                    MainActivity.inv_table, MainActivity.tbl_name,
                    status,itemStatus, disID, disName, disTypeName,
                    disType, disValue,Integer.parseInt(ID),EditFragmentOpenPrice,remarks);

            sql = " SELECT (ProductQty),(ProductPrice),(ItemDiscountAmount) FROM DetailsBillProduct " +
                    "Where BillNo = '" + billNo + "' " +
                    "  group by BillNo";

//            sql = " SELECT SUM(ProductQty),SUM(ProductPrice),SUM(ItemDiscountAmount) FROM DetailsBillProduct " +
//                    "Where BillNo = '" + billNo + "' " +
//                    "  group by BillNo";
            Cursor c = DBFunc.Query(sql, false);
            if (c != null) {
                RecyclerViewAdapter.totalItems = 0;
                RecyclerViewAdapter.totalAmount = 0.0;
                while (c.moveToNext()) {
                    if (!c.isNull(0)) {
                        RecyclerViewAdapter.totalItems += c.getInt(0);
                        //RecyclerViewAdapter.totalAmount += c.getInt(0) * (c.getDouble(1) - c.getDouble(2));
                        RecyclerViewAdapter.totalAmount +=  (c.getDouble(1) - ( c.getInt(0) * c.getDouble(2)));

                    }
                }
                c.close();
                RecyclerViewAdapter.edit_fragment_status = "1";
                ProductMainPageFragment.tet = "1";
                dismiss();
            }
            str_percentage = "";
            str_percentage_value = "";
            str_value = "";
            disID = "";
            disName = "";
            disTypeName = "";
            disType = "";
            disValue = "";

            MainActivity.name = "";
            RecyclerViewAdapter.old_pricee = "";
            RecyclerViewAdapter.item_diss_amt = "";
            RecyclerViewAdapter.item_diss = "";
            EditProductSheetFragment.EditFragmentOpenPrice = "0"; // 02092020 (add because of increase double in main page's editbottomfragement add new qty)

            EditProductSheetFragment.modiID.clear();
            EditProductSheetFragment.modiName.clear();
            EditProductSheetFragment.UnSelectedItem.clear();

           dismiss();

        }catch (IllegalFormatException e){
            Log.i("ERr",e.getMessage());
        }finally {
            //MainActivity.St = "1";
            ClearExistingValue();
        }
        //dismiss();
    }

    public static void ClearExistingValue() {
        RecyclerViewAdapter.old_pricee = "0";
        RecyclerViewAdapter.item_diss_amt = "0";

        sldIDArr.clear();
        RecyclerViewAdapter.sldQtyArr.clear();
        RecyclerViewAdapter.sldNameArr.clear();
        RecyclerViewAdapter.sltPriceTotalArr.clear();
        RecyclerViewAdapter.sltBillDisArr.clear();
        RecyclerViewAdapter.sltProductIDArr.clear();
        RecyclerViewAdapter.sltCategoryIDArr.clear();
        RecyclerViewAdapter.sltCategoryNameArr.clear();
        RecyclerViewAdapter.slddisID.clear();
        RecyclerViewAdapter.slddisName.clear();
        RecyclerViewAdapter.slddisTypeName.clear();
        RecyclerViewAdapter.slddisType.clear();
        RecyclerViewAdapter.slddisValue.clear();

        RecyclerViewAdapter.slddisID.clear();
        RecyclerViewAdapter.slddisName.clear();
        RecyclerViewAdapter.slddisTypeName.clear();
        RecyclerViewAdapter.slddisType.clear();
        RecyclerViewAdapter.slddisValue.clear();

        RecyclerViewAdapter.old_pricee = "";

         disID = "0";
         disName = "";
         disTypeName = "";
         disType = "";
        disValue = "";

        disIDAmt = "0";
        disNameAmt = "";
        disTypeNameAmt = "";
        disTypeAmt = "";
        disValueAmt = "";



        RecyclerViewAdapter.item_id = "0";
        RecyclerViewAdapter.category_id = "0";
        RecyclerViewAdapter.category_name = "0";
        RecyclerViewAdapter.item_name = "0";
        RecyclerViewAdapter.item_pricee = "0";
        RecyclerViewAdapter.ItemDiscountAmount = 0.0;
        RecyclerViewAdapter.item_diss = "0";
        RecyclerViewAdapter.old_pricee = "0";


        str_percentage = "";
        str_value = "";

//        }
    }
}


