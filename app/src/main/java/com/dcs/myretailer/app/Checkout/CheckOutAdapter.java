package com.dcs.myretailer.app.Checkout;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.TransactionDetailsActivity;
import com.dcs.myretailer.app.databinding.ActivityCheckOutListviewBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckOutAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> ReportProductNameList;
    ArrayList<String> ReportProductPriceList;
    ArrayList<String> ReportProductQuantityList;
    ArrayList<String> ReportProductIDList;
    ArrayList<String> ReportDiscountList;
    ArrayList<String> SldDiscountName;
    ArrayList<String> SldDiscountType;
    ArrayList<String> SldDiscountValue;
    ArrayList<String> sldOpenPriceStatus;
    ArrayList<String> sldRemarks;
    ArrayList<Integer> billdetailsPID;
    String billNo;
    int flags[];
    LayoutInflater inflter;
    ActivityCheckOutListviewBinding binding = null;
    public CheckOutAdapter(Context context, String billNo,ArrayList<String> reportProductQuantityList, ArrayList<String> reportProductNameList, ArrayList<String> reportProductPriceList,
                           ArrayList<String> reportProductIDList, ArrayList<String> reportDiscountList,
                           ArrayList<String> sldDiscountName, ArrayList<String> sldDiscountType, ArrayList<String> sldDiscountValue,
                           ArrayList<String> sldOpenPriceStatus,ArrayList<String> sldRemarks,ArrayList<Integer> billdetailsPID) {
        this.context = context;
        this.ReportProductNameList = reportProductNameList;
        this.ReportProductPriceList = reportProductPriceList;
        this.ReportProductQuantityList = reportProductQuantityList;
        this.ReportProductIDList = reportProductIDList;
        this.ReportDiscountList = reportDiscountList;
        this.SldDiscountName = sldDiscountName;
        this.SldDiscountType = sldDiscountType;
        this.SldDiscountValue = sldDiscountValue;
        this.sldOpenPriceStatus = sldOpenPriceStatus;
        this.sldRemarks = sldRemarks;
        this.billNo = billNo;
        this.billdetailsPID = billdetailsPID;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return ReportProductQuantityList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    String dis_str = "";
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        //view = inflter.inflate(R.layout.activity_check_out_listview, null);

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.activity_check_out_listview, viewGroup, false);
        view = binding.getRoot();



        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 15;
            binding.layCheckoutAdapter.setLayoutParams(params);
            //binding..setLayoutParams(paramslayCardView);
        }else if (terminalTypeVal.equals(Constraints.IMIN)) {
            String device = Query.GetDeviceData(Constraints.DEVICE);
            if (device.equals("M2-Max")) {

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 5;
                binding.layCheckoutAdapter.setLayoutParams(params);

                LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                paramslayCardView.leftMargin = 5;
                binding.checkoutProductListview.setLayoutParams(paramslayCardView);

                LinearLayout.LayoutParams paramsqty= new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.reportProductQty.setLayoutParams(paramsqty);

                LinearLayout.LayoutParams paramsname= new LinearLayout.LayoutParams(250, ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.LayProductName.setLayoutParams(paramsname);
                binding.reportProductName1.setLayoutParams(paramsname);

                LinearLayout.LayoutParams paramsprice= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.LayProductPrice.setLayoutParams(paramsprice);
            }
        }

        binding.reportProductQty.setText(ReportProductQuantityList.get(i) + " X ");
        String text_promo_name = "0";
        String text_promo_value = "0";
        String PromoValue = "0";
        //GetPromo
        if (ReportProductIDList.size() > 0){

            Cursor CursorPromo = Query.GetPromo(billNo, ReportProductIDList.get(i));
            //PLUID,PromoName,PromoValue from PLUPromo
            text_promo_name = "0";
            text_promo_value = "0";
            PromoValue = "0";
            if (CursorPromo != null) {
                while (CursorPromo.moveToNext()) {
                    String PromoName = CursorPromo.getString(1);
                    PromoValue = CursorPromo.getString(2);
                    text_promo_name = "<br/><font color='#a9aaad'>" + PromoName + "</font>";
                    text_promo_value = "<br/><font color='#a9aaad'>" + PromoValue + "</font>";
                }
                CursorPromo.close();
            }
        }
        try {
            if (ReportProductIDList.size() > 0) {
                ArrayList ModiN = ModiFun(billNo, ReportProductIDList.get(i),sldOpenPriceStatus.get(i),sldRemarks.get(i),
                        ReportProductQuantityList.get(i),billdetailsPID.get(i));
                String text = "";

                if (ModiN.size() > 0) {
                    for (int modiVar = 0; modiVar < ModiN.size(); modiVar++) {
                        text += "<br/><font color='#a9aaad'>(" + ModiN.get(modiVar) + ")</font>";
                    }
                }
                String st = "0";
                    st = ReportProductNameList.get(i);
                String query = "SELECT ModifierGroup FROM DetailsBillProduct WHERE UPPER(ProductID) = '"+ReportProductIDList.get(i)+"' " +
                        "AND BillNo = '"+TransactionDetailsActivity.BillNo+"'";

                Cursor cursor = DBFunc.Query(query,false);
                String modifierGroup_jerifood = "";
                if (cursor != null){
                    if (cursor.moveToNext()){
                        modifierGroup_jerifood = cursor.getString(0);
                    }
                }

                if (modifierGroup_jerifood != null && modifierGroup_jerifood.length() > 0) {
                    st += " - " + modifierGroup_jerifood;
                }

                    st += text;

                if (!text_promo_name.equals("0") && text_promo_name.length() > 1) {
                    st = st + text_promo_name; //Add Promo Name
                }
                binding.reportProductName.setText(Html.fromHtml(st));
            }
        }catch (IndexOutOfBoundsException e){
            Log.i("Exception_e","e");
        }
//        }
        if (ReportProductPriceList.size() > 0) {
            if (ReportProductPriceList.get(i).equals("0")) {
                binding.reportProductPrice.setText("");
            } else {
                //report_product_price.setText("$" + String.format("%.2f", Double.valueOf(String.valueOf(reportProductPriceList.get(i))))+str);
                binding.reportProductPrice.setText("$" + String.format("%.2f", Double.valueOf(String.valueOf(ReportProductPriceList.get(i)))));
            }
        }
        if (!text_promo_value.equals("0") && text_promo_value.length() > 1) {
            binding.reportProductPrice.setText(Html.fromHtml(binding.reportProductPrice.getText().toString() + text_promo_value));
        }
        if (ReportDiscountList != null && ReportDiscountList.size() > 0) {
            Double disamt = Double.valueOf(ReportDiscountList.get(i));

            if (disamt > 0.0) {
                binding.reportProductName1.setVisibility(View.VISIBLE);
                binding.reportProductPriceOld.setVisibility(View.VISIBLE);

                if (SldDiscountValue != null && SldDiscountValue.size() > 0) {

                    try {
                        if (SldDiscountType != null && SldDiscountType.get(i).equals("% Percentage")) {
                            dis_str = SldDiscountName.get(i) + " ( " + SldDiscountValue.get(i) + " % " + " ) ";
                        } else if (SldDiscountType != null && SldDiscountType.get(i).equals("$ Dollar Value")) {
                            dis_str = SldDiscountName.get(i) + " ( $ " + SldDiscountValue.get(i) + " " + " ) ";
                        }
                    } catch (Exception e){
                        dis_str = "";
                    }
//                    else {
//                        dis_str = SldDiscountName.get(i) + " ( $ " + SldDiscountValue.get(i) + " " + " ) ";
//                    }
                    binding.reportProductName1.setText(dis_str);
                    binding.reportProductPriceOld.setText("- $" + String.format("%.2f", disamt));
                }
            }

        }

        return view;
    }

    public static SimpleDateFormat sdf;
    public static String tbl_name = "0";

    public static ArrayList ModiFun(String billNo,String ID,String openPriceStatus,String remarks,String qty,Integer billdetailsPID) {
        String ItmID = "0";
//        String sql = " SELECT UUID " +
//                "FROM PLU " +
//                "Where Name = '"+ Name +"'";
        String sql = " SELECT UUID " +
                "FROM PLU " +
                "Where ID = '"+ ID +"'";
//                "Where ID = '"+ ID +"'";



        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {
                ItmID = c.getString(0);
            }
            c.close();
        }

        ArrayList<String> ModiN = new ArrayList<String>();
//        sql = " SELECT ModiName,Qty,ItemID,ModiPrice " +
//                "FROM PLUModi " +
//                "Where PLU_UUID = '"+ ItmID +"' " +
//                "and BillNo = '"+ billNo +"' " ;
//        sql += Query.CheckingNullOrNot(openPriceStatus,"OpenPriceStatus");
//        sql += Query.CheckingNullOrNot(remarks,"Remarks");
        sql = " SELECT ModiName,Qty,ItemID,ModiPrice " +
                "FROM PLUModi " +
                " WHERE BillNo = '"+billNo+"' AND DetailsBillProductID = '" + billdetailsPID + "' ";
        //sql += Query.CheckingNullOrNot(openPriceStatus,"OpenPriceStatus");
        //sql += Query.CheckingNullOrNot(remarks,"Remarks");

//                "and Remarks = '"+remarks+"' " ;

        c = DBFunc.Query(sql, false);
        if (c != null) {
            while (c.moveToNext()) {
                String jerifood_tax = Query.GetOptions(24);
                if (jerifood_tax.equals("1")) {
                    if (c.getString(3) !=null && c.getDouble(3) > 0.0){
                        ModiN.add(c.getString(0) + " * " + c.getString(1) + " $"+String.format("%.2f", Double.valueOf(qty) * Double.valueOf(String.valueOf(c.getDouble(3)))));
                    }else {
                        ModiN.add(c.getString(0) + " * " + c.getString(1));
                    }
                }else {
                    if (c.getString(3) !=null && c.getDouble(3) > 0.0) {
                        ModiN.add(c.getString(0)+ " $"+String.format("%.2f", Double.valueOf(qty) *  Double.valueOf(String.valueOf(c.getDouble(3)))));
                    }else {
                        ModiN.add(c.getString(0));
                    }
                }
            }
            c.close();
        }

        return ModiN;
    }
}
