package com.dcs.myretailer.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityAddPaymentTypeBinding;


public class AddPaymentTypeActivity extends AppCompatActivity implements View.OnClickListener {
    String switch_payment_type_on_off = "0";
    AddPaymentTypeActivity context;
    String DisallowEmptyCash,LinktoPaymentApp,IntegratetoShoptima,RoundingActivate,Remarks,Ezlink,
            fullAmount,amount,Option,ThirdPartyPaymentID,SwitchSTATUS,PaymentName,ID = "";
    String Name = "";
    String Amount = "";
    ActivityAddPaymentTypeBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_payment_type);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Payment Types");

        context = AddPaymentTypeActivity.this;

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        binding.paymenttypeOnoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    // do something when check is selected
                    switch_payment_type_on_off = "1";
                } else {
                    //do something when unchecked
                    switch_payment_type_on_off = "0";
                }
            }
        });
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        Log.i("sdf__device","device___"+device);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 10;
            linearOverAllParams.topMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);

            LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(520,100);
            linearOverAllParams1.leftMargin = 25;
            binding.Lay1.setLayoutParams(linearOverAllParams1);

            LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(520,90);
            linearOverAllParams2.leftMargin = 25;
            linearOverAllParams2.topMargin = 10;
            linearOverAllParams2.bottomMargin = 10;
            binding.Lay2.setLayoutParams(linearOverAllParams2);
            binding.Lay3.setLayoutParams(linearOverAllParams2);
            binding.Lay4.setLayoutParams(linearOverAllParams2);
            binding.Lay5.setLayoutParams(linearOverAllParams2);
            binding.Lay6.setLayoutParams(linearOverAllParams2);
            binding.Lay7.setLayoutParams(linearOverAllParams2);
            binding.Lay8.setLayoutParams(linearOverAllParams2);

        }else if (terminalTypeVal.equals(Constraints.IMIN)){

            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 10;
                linearOverAllParams.topMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(720,100);
                linearOverAllParams1.leftMargin = 25;
                binding.Lay1.setLayoutParams(linearOverAllParams1);

                LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720,90);
                linearOverAllParams2.leftMargin = 25;
                linearOverAllParams2.topMargin = 10;
                linearOverAllParams2.bottomMargin = 10;
                binding.Lay2.setLayoutParams(linearOverAllParams2);
                binding.Lay3.setLayoutParams(linearOverAllParams2);
                binding.Lay4.setLayoutParams(linearOverAllParams2);
                binding.Lay5.setLayoutParams(linearOverAllParams2);
                binding.Lay6.setLayoutParams(linearOverAllParams2);
                binding.Lay7.setLayoutParams(linearOverAllParams2);
                binding.Lay8.setLayoutParams(linearOverAllParams2);
            }else {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 5;
                linearOverAllParams.topMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);
            }

        }else if (terminalTypeVal.equals(Constraints.PAX)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 5;
            linearOverAllParams.topMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);

        }
//        else {
//
//        }
        binding.LayBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (ID.equals("null")){

            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                binding.btnAddPayment.setLayoutParams(new LinearLayout.LayoutParams(520, 90));
            } else if (terminalTypeVal.equals(Constraints.IMIN)){
                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearbtnadd = new LinearLayout.LayoutParams(720,
                            90);
                    linearbtnadd.leftMargin =30;
                    linearbtnadd.topMargin = 20;
                    binding.btnAddPayment.setLayoutParams(linearbtnadd);

                    }else {
                    LinearLayout.LayoutParams linearbtnadd = new LinearLayout.LayoutParams(630,
                            90);
                    linearbtnadd.leftMargin = 40;
                    linearbtnadd.topMargin = 20;
                    linearbtnadd.bottomMargin = 20;
                    binding.btnAddPayment.setLayoutParams(linearbtnadd);
                }
            } else {
                binding.btnAddPayment.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            }

            setTitle("Add Payment");
            binding.btnAddPayment.setText("Add");
            binding.btnDeletePayment.setVisibility(View.GONE);
        }else{
            setTitle("Edit Payment");
            binding.btnAddPayment.setText("Update");
            binding.btnDeletePayment.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams linearbtnedit = new LinearLayout.LayoutParams(320,
                    90);
            linearbtnedit.leftMargin =30;
            linearbtnedit.topMargin = 20;
            linearbtnedit.bottomMargin = 10;
            binding.btnAddPayment.setLayoutParams(linearbtnedit);
            binding.btnDeletePayment.setLayoutParams(linearbtnedit);

            GetPaymentTypeByID(ID);
            binding.editTextPaymentName.setText(Name);
            binding.editTextPaymentAmount.setText(Amount);

            chkedUnchkedFun(DisallowEmptyCash,binding.chkAllowEmptyCash);
            chkedUnchkedFun(LinktoPaymentApp,binding.chkAllowLinkToPaymentApp);
            chkedUnchkedFun(IntegratetoShoptima,binding.chkAllowIntegrateToShoptima);
            chkedUnchkedFun(RoundingActivate,binding.chkAllowRoundingActivate);
            chkedUnchkedFun(Remarks,binding.chkAllowRemarks);
            chkedUnchkedFun(Ezlink,binding.chkAllowEzlink);

            if (fullAmount != null && fullAmount.length() > 0) {
                chkedUnchkedFun(fullAmount,binding.chkAllowFullAmount);
            }else {
                binding.chkAllowFullAmount.setChecked(false);
            }
            if (SwitchSTATUS.equals("1")){
                binding.paymenttypeOnoff.setChecked(true);
            }else{
                binding.paymenttypeOnoff.setChecked(false);
            }

            String chkSubmitSalesOrNot = Query.GetOptions(22);
            if (chkSubmitSalesOrNot.equals("1")) {
                binding.editTextPaymentName.setEnabled(false);
                binding.editTextPaymentName.setInputType(InputType.TYPE_NULL);
            }
        }

        binding.btnAddPayment.setOnClickListener(this);
        binding.btnDeletePayment.setOnClickListener(this);
    }

    public static void chkedUnchkedFun(String statusVal,androidx.appcompat.widget.AppCompatCheckBox chkVal){
        try {
            if (statusVal.equals("1")) {
                chkVal.setChecked(true);
            } else {
                chkVal.setChecked(false);
            }
        } catch (Exception e){
            chkVal.setChecked(false);
        }
    }
    private void GetPaymentTypeByID(String id) {
            Cursor c = DBFunc.Query("SELECT Name,DisallowEmptyCash,LinktoPaymentApp,IntegratetoShoptima,RoundingActivate,Amount," +
                    "Option,ThirdPartyPaymentID,SwitchSTATUS,FullAmount,Remarks,Ezlink " +
                    "FROM Payment Where ID = "+Integer.parseInt(id), true);
            if (c != null) {
                if (c.moveToNext()) {
                    if (!c.isNull(0)) {
                        Name = c.getString(0);
                        Amount = c.getString(5);
                        DisallowEmptyCash = c.getString(1);
                        LinktoPaymentApp = c.getString(2);
                        IntegratetoShoptima = c.getString(3);
                        RoundingActivate = c.getString(4);
                        SwitchSTATUS = c.getString(8);
                        fullAmount = c.getString(9);
                        Remarks = c.getString(10);
                        Ezlink = c.getString(11);
                    }
                }
                c.close();
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_payment:
                    if (binding.chkAllowEmptyCash.isChecked()){
                        DisallowEmptyCash = "1";
                        Option = DisallowEmptyCash;
                    }else{
                        DisallowEmptyCash = "0";
                        Option = DisallowEmptyCash;
                    }
                    if (binding.chkAllowLinkToPaymentApp.isChecked()){
                        LinktoPaymentApp = "1";
                        Option = Option + LinktoPaymentApp;
                    }else{
                        LinktoPaymentApp = "0";
                        Option = Option + LinktoPaymentApp;
                    }
                    if (binding.chkAllowIntegrateToShoptima.isChecked()){
                        IntegratetoShoptima = "1";
                        Option = Option + IntegratetoShoptima;
                    }else{
                        IntegratetoShoptima = "0";
                        Option = Option + IntegratetoShoptima;
                    }
                    if (binding.chkAllowRoundingActivate.isChecked()){
                        RoundingActivate = "1";
                        Option = Option + RoundingActivate;
                    }else{
                        RoundingActivate = "0";
                        Option = Option + RoundingActivate;
                    }
                    if (binding.chkAllowFullAmount.isChecked()){
                        fullAmount = "1";
                        Option = Option + fullAmount;
                    }else{
                        fullAmount = "0";
                        Option = Option + fullAmount;
                    }
                    if (binding.chkAllowRemarks.isChecked()){
                        Remarks = "1";
                        Option = Option + Remarks;
                    }else{
                        Remarks = "0";
                        Option = Option + Remarks;
                    }
                    if (binding.chkAllowEzlink.isChecked()){
                        Ezlink = "1";
                        Option = Option + Ezlink;
                    }else{
                        Ezlink = "0";
                        Option = Option + Ezlink;
                    }
                    amount = binding.editTextPaymentAmount.getText().toString();
                    ThirdPartyPaymentID = "0";
                    SwitchSTATUS = String.valueOf(switch_payment_type_on_off);
                    PaymentName = DBFunc.PurifyString(binding.editTextPaymentName.getText().toString());

                    if (amount.isEmpty()){
                        amount = "0";
                    }

                    if (ID.equals("null")) {
                        switch_payment_type_on_off = "1";
                        savePayment();
                    }else{
                        updatePayment();
                    }
                break;
            case R.id.btn_delete_payment:
                    deletePayment();
                break;
        }
    }

    private void deletePayment() {
        DBFunc.ExecQuery("DELETE FROM Payment WHERE ID = "+Integer.parseInt(ID), true);
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Payment -> Delete -> Name: "+
                DBFunc.PurifyString(binding.editTextPaymentName.getText().toString()));

        Intent i = new Intent(context,PaymentListActivity.class);
        startActivity(i);
        finish();
    }

    private void updatePayment() {
        try {

            if ( binding.paymenttypeOnoff.isChecked()) {
                SwitchSTATUS = "1";
            }else {
                SwitchSTATUS = "0";
            }
            String sql = "UPDATE Payment SET ";
            sql += "Name = '" + PaymentName + "', ";
            sql += "DisallowEmptyCash = '" + DisallowEmptyCash + "', ";
            sql += "LinktoPaymentApp = '" + LinktoPaymentApp + "', ";
            sql += "IntegratetoShoptima = '" + IntegratetoShoptima + "', ";
            sql += "RoundingActivate = '" + RoundingActivate + "', ";
            sql += "FullAmount = '" + fullAmount + "', ";
            sql += "Remarks = '" + Remarks + "', ";
            sql += "Ezlink = '" + Ezlink + "', ";
            sql += "Amount = " + amount + ", ";
            //sql += "Option = '"+ Option +"', ";
            sql += "Option = '00000', ";
            sql += "SwitchSTATUS = '" + SwitchSTATUS + "', ";
            sql += "DateTime = " + System.currentTimeMillis() + " ";
            sql += "WHERE ID = " + Integer.parseInt(ID);

            DBFunc.ExecQuery(sql, true);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                    "Updated - updatePayment -> updatePayment: " + sql);

        } catch (Exception e){

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                    "Err - updatePayment -> updatePayment: " + e.getMessage());
        }

        Intent i = new Intent(context,PaymentListActivity.class);
        startActivity(i);
        finish();
    }

    private void savePayment() {
        try {
            //Query.SavePayment(PaymentName,DisallowEmptyCash,LinktoPaymentApp,IntegratetoShoptima,RoundingActivate,amount,ThirdPartyPaymentID,SwitchSTATUS,fullAmount);
            Query.SavePayment(PaymentName,DisallowEmptyCash,LinktoPaymentApp,IntegratetoShoptima,
                    RoundingActivate,amount,ThirdPartyPaymentID,"1",fullAmount,Remarks,Ezlink);
        }catch (SQLException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                "Payment -> Save -> Name: "+DBFunc.PurifyString(binding.editTextPaymentName.getText().toString()));

        Intent i = new Intent(context,PaymentListActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //ActivityCompat.finishAffinity(AddPaymentTypeActivity.this);
        Intent i = new Intent(AddPaymentTypeActivity.this,PaymentListActivity.class);
        startActivity(i);
        finish();
    }
}
