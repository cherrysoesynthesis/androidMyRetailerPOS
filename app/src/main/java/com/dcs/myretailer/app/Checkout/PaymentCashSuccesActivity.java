package com.dcs.myretailer.app.Checkout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Activity.SyncActivity;
import com.dcs.myretailer.app.databinding.ActivityPaymentCashSuccesBinding;


public class PaymentCashSuccesActivity extends AppCompatActivity implements View.OnClickListener {
    String billno = "";
    Double paymentAmt = 0.0;
    Double totalAmt = 0.0;
    Double changeAmt = 0.0;
    String paymentName = "";
    String paymentAmountStr = "";
    public static ActivityPaymentCashSuccesBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment_cash_succes);

        Intent getIntent = getIntent();
        Bundle bundleName = getIntent.getExtras();
        if(bundleName != null) {
            billno = (String) bundleName.get("billno");
        }

        binding.btnPaymentCash.setOnClickListener(this);

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 30;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        } else if (terminalTypeVal.equals(Constraints.IMIN)){
            String device = Query.GetDeviceData(Constraints.DEVICE);

            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(600,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //linearOverAllParams.leftMargin = 30;
                linearOverAllParams.gravity = Gravity.CENTER;
                binding.layOverAll.setLayoutParams(linearOverAllParams);
            }
        }

        String sql_sales = "SELECT STATUS,isZ FROM Sales WHERE BillNo = '"+ CheckOutActivity.BillNo +"' ";
        Cursor Cursor = DBFunc.Query(sql_sales,false);
        if (Cursor != null){
            while (Cursor.moveToNext()){
                String sstatus = Cursor.getString(0);
                String sisZZ = Cursor.getString(1);
//                        if (sisZZ.equals("null")){
//                            sisZZ = "";
//                        }
                String updatesql = "UPDATE BillPayment SET STATUS = '"+sstatus+"' , isZ = '"+sisZZ+"' WHERE BillNo = '"+CheckOutActivity.BillNo+"' ";

                DBFunc.ExecQuery(updatesql,false);
            }
            Cursor.close();
        }

        String ddd = "UPDATE BillPayment " +
                    "SET EwalletIssueBanker = '' " +
                    "WHERE EwalletIssueBanker IS NULL AND BillNo = '"+CheckOutActivity.BillNo+"'";

        DBFunc.ExecQuery(ddd,false);

        String str = "SELECT Amount,ChangeAmount,Name FROM BillPayment WHERE BillNo = '"+ CheckOutActivity.BillNo+"' " +
                " ORDER BY idx DESC";
        Cursor c = DBFunc.Query(str,false);
        if ( c != null){
            paymentAmt = 0.0;
            totalAmt = 0.0;
            changeAmt = 0.0;
            paymentName = "";
            paymentAmountStr = "";
            while (c.moveToNext()){
                totalAmt += c.getDouble(0);
                changeAmt += c.getDouble(1);

                paymentAmt = totalAmt + changeAmt;

                paymentName += c.getString(2).toUpperCase() + "\n";
                paymentAmountStr += "$"+String.format("%.2f", Double.valueOf(c.getString(0))) + "\n";

                binding.btnPaymentTypeName.setText(paymentName);
                binding.btnPaymentTypeValue.setText(paymentAmountStr);
            }
            c.close();
        }

        binding.paymentSuccessPaymentAmount.setText("$"+String.format("%.2f", Double.valueOf(paymentAmt)));
        binding.paymentSuccessTotalAmount.setText("$"+String.format("%.2f", Double.valueOf(totalAmt)));

        if (changeAmt > 0.0) {
            binding.changeAmtLinearlayout.setVisibility(View.VISIBLE);
            binding.paymentSuccessChangeAmount.setText("$"+String.format("%.2f", Double.valueOf(changeAmt)));
        }




        String queray = "Select TotalQty,TotalNettSales,SalesDateTime,STATUS from Sales " +
                "where BillNo = '"+CheckOutActivity.BillNo+"' ";
        Cursor ccc = DBFunc.Query(queray,false);
        Double ddPrice = 0.0;
        Integer ddQty = 0;
        String ddDt = "";
        String ddstatus = "";
        if (ccc != null) {
            while (ccc.moveToNext()){
                ddQty = ccc.getInt(0);
                ddPrice = ccc.getDouble(1);
                ddDt = ccc.getString(2);
                ddstatus = ccc.getString(3);
            }
            ccc.close();
        }
        String query = "UPDATE BillList SET ";
        query += "Date = '"+ddDt+"', ";
        query += "TotalAmount = '"+ddPrice+"', ";
        query += "TotalItems = '"+ddQty+"', ";
        query += "STATUS = '"+ddstatus+"' ";
        query += "WHERE BillNo = '"+CheckOutActivity.BillNo+"' ";

        DBFunc.ExecQuery(query,false);

        Query.findOnHandQtyByBillNo(CheckOutActivity.BillNo);

        Query.DeleteProductQty(CheckOutActivity.BillNo);


        //Sync to Backend Function
        Cursor config_values_pro_item = null;
        String url = "";
        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
        if (config_values_pro_item != null) {
            while (config_values_pro_item.moveToNext()) {
                url = config_values_pro_item.getString(2);
            }
        }
        if (url != null) {
            SyncActivity.ResyncOrNormal(getApplicationContext(),CheckOutActivity.BillNo,"","Normal","");
        }
    }

    @Override
    public void onClick(View v) {

        String option = "";
        Cursor config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
        if (config_values_pro_item != null) {
            while (config_values_pro_item.moveToNext()) {
                option = config_values_pro_item.getString(3);
            }
        }
        String chkk = "0";
        if (option.length() > 0) {
            if (option.charAt(19) == '1') {
                chkk = "1";
            }else {
                chkk = "0";
            }
        }else {
            chkk = "0";
        }

        if (chkk.equals("1")) {
            Query.CreateNewBill("OFF","");

            Intent intent = new Intent(PaymentCashSuccesActivity.this, MainActivity.class);
            intent.putExtra("name", "OnlineOrderListMainPageFragment");
            startActivity(intent);
            finish();
        }else {
            Intent i = new Intent(PaymentCashSuccesActivity.this, MainActivity.class);
            i.putExtra("name", "PrintingActivity");
            this.startActivity(i);
            finish();
        }
        MainActivity.St = "1";
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PaymentCashSuccesActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
