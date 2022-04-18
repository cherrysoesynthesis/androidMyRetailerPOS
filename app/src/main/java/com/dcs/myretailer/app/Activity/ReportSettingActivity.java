package com.dcs.myretailer.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityReportSettingBinding;

public class ReportSettingActivity extends AppCompatActivity implements View.OnClickListener {
//    str_chk_refer_info
    String str_chk_sales,str_chk_category,str_chk_product_sales,str_chk_payment,str_chk_discount,str_chk_tax,str_chk_cancellation,str_chk_refund;
    Context context;
    ActivityReportSettingBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_report_setting);
        context = ReportSettingActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Report Settings");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSaveReportSettings.setOnClickListener(this);

        String check = CheckingValueInDB();
        if (check.length() > 0) {
            getAllReportSettings();
        }

        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearLayName = new LinearLayout.LayoutParams(700,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayName.leftMargin = 50;
            linearLayName.topMargin = 30;
            binding.LaySales.setLayoutParams(linearLayName);
            binding.layCategory.setLayoutParams(linearLayName);
            binding.LayProductSales.setLayoutParams(linearLayName);
            binding.LayPayment.setLayoutParams(linearLayName);
            binding.LayDiscount.setLayoutParams(linearLayName);
            binding.LayCancellation.setLayoutParams(linearLayName);
            binding.LayReferInfoSales.setLayoutParams(linearLayName);
            binding.LayRefund.setLayoutParams(linearLayName);
            binding.LayBtnSave.setLayoutParams(linearLayName);

            LinearLayout.LayoutParams linearLayNamebtn = new LinearLayout.LayoutParams(700,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.btnSaveReportSettings.setLayoutParams(linearLayNamebtn);

        }
    }

    private String CheckingValueInDB() {
        String check = "";
        Cursor config_values_pay = DBFunc.Query("SELECT * FROM ReportSettings", true);
        if (config_values_pay != null) {
            while (config_values_pay.moveToNext()) {
                check = config_values_pay.getString(0);

            }
        }
        return check;
    }

    private void getAllReportSettings() {
        Cursor c = DBFunc.Query("SELECT Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales,Refund FROM ReportSettings ", true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    str_chk_sales = c.getString(0);
                    str_chk_category = c.getString(1);
                    str_chk_product_sales = c.getString(2);
                    str_chk_payment = c.getString(3);
                    str_chk_discount = c.getString(4);
                    str_chk_tax = c.getString(5);
                    str_chk_cancellation = c.getString(6);
//                    str_chk_refer_info = c.getString(7);
                    str_chk_refund = c.getString(8);
                }
            }
            c.close();
        }
        checkedTickUnTickFun(str_chk_sales,binding.chkSales);
        checkedTickUnTickFun(str_chk_category,binding.chkCategory);
        checkedTickUnTickFun(str_chk_product_sales,binding.chkProductSales);
        checkedTickUnTickFun(str_chk_payment,binding.chkPayment);
        checkedTickUnTickFun(str_chk_discount,binding.chkDiscount);
        checkedTickUnTickFun(str_chk_tax,binding.chkTax);
        checkedTickUnTickFun(str_chk_cancellation,binding.chkCancellation);
//        checkedTickUnTickFun(str_chk_refer_info,binding.chkReferInfo);
        checkedTickUnTickFun(str_chk_refund,binding.chkRefund);
    }

    private void checkedTickUnTickFun(String status,AppCompatCheckBox chkValue) {
        try {
            if (status.equals("1")){
                chkValue.setChecked(true);
            }else{
                chkValue.setChecked(false);
            }
        }catch (NullPointerException e){
            chkValue.setChecked(false);
        }
    }


    private String checkedStatusFun(String status, AppCompatCheckBox chkValue) {
        if (chkValue.isChecked()){
            status = "1";
        }else{
            status = "0";
        }
        return status;
    }

    @Override
    public void onClick(View v) {
        str_chk_sales = checkedStatusFun(str_chk_sales,binding.chkSales);
        str_chk_product_sales = checkedStatusFun(str_chk_product_sales,binding.chkProductSales);
        str_chk_payment = checkedStatusFun(str_chk_payment,binding.chkPayment);
        str_chk_discount = checkedStatusFun(str_chk_discount,binding.chkDiscount);
        str_chk_tax = checkedStatusFun(str_chk_tax,binding.chkTax);
        str_chk_cancellation = checkedStatusFun(str_chk_cancellation,binding.chkCancellation);
        //str_chk_refer_info = checkedStatusFun(str_chk_refer_info,binding.chkReferInfo);
        str_chk_refund = checkedStatusFun(str_chk_refund,binding.chkRefund);

        saveReportSettings();
    }

    private void saveReportSettings() {
        String dbValue = CheckingValueInDB();
        if (dbValue.length() > 0){
            DBFunc.ExecQuery("DELETE FROM ReportSettings", true);
        }
        Query.SaveReportSettings(str_chk_sales,str_chk_category,str_chk_product_sales,str_chk_payment,str_chk_discount,
                str_chk_tax,str_chk_cancellation,"0",str_chk_refund);
        Toast.makeText(this, "Report Settings Saved Succesfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
