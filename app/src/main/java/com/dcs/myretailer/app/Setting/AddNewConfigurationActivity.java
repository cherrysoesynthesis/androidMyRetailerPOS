package com.dcs.myretailer.app.Setting;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityAddNewConfigurationBinding;


public class AddNewConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
    String name,activity,str_activate;
    String ID = "";
    String Name = "";
    String Activate = "";
    ActivityAddNewConfigurationBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_configuration);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add New Configuration");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        binding.btnAdd.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
        binding.editTextName.setOnClickListener(this);
        binding.chkActivate.setOnClickListener(this);

        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearLayBankName = new LinearLayout.LayoutParams(700,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayBankName.leftMargin = 50;
            linearLayBankName.topMargin = 30;
            binding.LayBankName.setLayoutParams(linearLayBankName);
            binding.LayActivate.setLayoutParams(linearLayBankName);
        }
        if (ID.equals("null")){
            setTitle("Add Configuration Host");
            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearLayBtnSave = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.LayBtnSave.setLayoutParams(linearLayBtnSave);
                LinearLayout.LayoutParams linearLayBankName = new LinearLayout.LayoutParams(700,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayBankName.leftMargin = 50;
                linearLayBankName.topMargin = 30;
                binding.btnAdd.setLayoutParams(linearLayBankName);
            } else {
                binding.btnAdd.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            }
            binding.btnAdd.setText("Add");
            binding.btnDelete.setVisibility(View.GONE);
        }else{
            setTitle("Edit Configuration Host");
            binding.btnAdd.setText("Update");
            binding.btnDelete.setVisibility(View.VISIBLE);
            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearLayBtnSave = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.LayBtnSave.setLayoutParams(linearLayBtnSave);
                LinearLayout.LayoutParams linearLayBankName = new LinearLayout.LayoutParams(300,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayBankName.leftMargin = 50;
                linearLayBankName.topMargin = 20;
                binding.btnAdd.setLayoutParams(linearLayBankName);
                binding.btnDelete.setLayoutParams(linearLayBankName);
            }

            GetPaymentTypeByID(ID);
            binding.editTextName.setText(Name);
            if (Activate.equals("1")){
                binding.chkActivate.setChecked(true);
            }else {
                binding.chkActivate.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                if (ID.equals("null")){
                    saveConfiguration();
                }else {
                    updateConfiguration(Integer.parseInt(ID));
                }
                break;
            case R.id.btn_delete:
                    deleteConfiguration();
                break;
        }
    }

    private void deleteConfiguration() {
        DBFunc.ExecQuery("DELETE FROM "+ Constraints.ConfigPaymentHost +" WHERE ID = "+Integer.parseInt(ID), true);
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                "Payment -> Delete -> Name: "+DBFunc.PurifyString(binding.editTextName.getText().toString()));
        finish();
        Intent i = new Intent(AddNewConfigurationActivity.this,ConfigurationHostActivity.class);
        startActivity(i);
    }

    private void GetPaymentTypeByID(String id) {
        Cursor c = DBFunc.Query("SELECT BankName,Activate FROM "+ Constraints.ConfigPaymentHost+" Where ID = "+Integer.parseInt(id), true);
        if (c != null) {
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    Name = c.getString(0);
                    Activate = c.getString(1);
                }
            }
            c.close();
        }
    }

    private void saveConfiguration() {
        final String bankName = binding.editTextName.getText().toString();

        getECRNAme(bankName);

        if (str_activate.equals("1")) {
            DBFunc.ExecQuery("UPDATE "+ Constraints.ConfigPaymentHost+" SET Activate = '0'", true);
        }

        Cursor data = DBFunc.Query("SELECT * FROM "+ Constraints.ConfigPaymentHost+" Where UPPER(BankName) = '"+bankName.toUpperCase()+"' ORDER BY ID ASC", true);
        int count = 0;
        if(data==null) {
            count = 0;
        }else{
            count = data.getCount();
        }
        data.close();

        if(count==0){
            //create new if list is empty
//            Log.i("Name__",name);
//            Log.i("activity__",activity);
//            Log.i("bankName__",bankName.toLowerCase().trim());
//            Log.i("str_activate__",str_activate);
            String sql = "INSERT INTO "+ Constraints.ConfigPaymentHost+" (Name,Activity,BankName,Activate) VALUES ('"
                    +name+"','"+activity+"' , '"+bankName.toLowerCase().trim()+"', "+str_activate+")";

            DBFunc.ExecQuery(sql, true);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "ConfPayHost -> Add -> BankName: "+bankName);

        }
        Intent i = new Intent(AddNewConfigurationActivity.this,ConfigurationHostActivity.class);
        startActivity(i);
        finish();
    }

    private void updateConfiguration(Integer ID) {
        final String bankName = binding.editTextName.getText().toString();

        getECRNAme(bankName);

        if (str_activate.equals("1")) {
            DBFunc.ExecQuery("UPDATE "+ Constraints.ConfigPaymentHost+" SET Activate = '0'", true);
        }

        DBFunc.ExecQuery("UPDATE "+ Constraints.ConfigPaymentHost+" SET BankName = '" + DBFunc.PurifyString(bankName).toLowerCase().trim() + "' , Activate = '" + str_activate + "' WHERE ID = " + ID, true);

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "ConfPayHost -> Update -> BankName: "+bankName+", ID: "+ID);

        Intent i = new Intent(AddNewConfigurationActivity.this,ConfigurationHostActivity.class);
        startActivity(i);
        finish();
    }

    private void getECRNAme(String bankName) {
        if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_OCBC.toUpperCase())) {
//            name = "sg.com.mobileeftpos.paymentapplication";
//            activity = "sg.com.mobileeftpos.paymentapplication.activities.ECRActivity";
            name = DeclarationConf.PACKAGE_NAME_OCBC;
            activity = DeclarationConf.CLASS_NAME_OCBC;
        }else if (bankName.toUpperCase().equals(DeclarationConf.PACKAGE_NAME_DBS.toUpperCase())) {
            name = DeclarationConf.PACKAGE_NAME_DBS;
            activity = DeclarationConf.CLASS_NAME_DBS;
        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_BOC.toUpperCase())) {
            name = DeclarationConf.PACKAGE_NAME_BOC;
            activity = DeclarationConf.CLASS_NAME_BOC;
        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JERIPAY.toUpperCase())) {
            name = DeclarationConf.PACKAGE_NAME_JERIPAY;
            activity = DeclarationConf.CLASS_NAME_JERIPAY;
        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_GLOBAL_PAYMENT.toUpperCase())) {


        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_AMERICAN_EXPRESS.toUpperCase())) {


        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_UPI.toUpperCase())) {


        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JCB.toUpperCase())) {


        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_IPP.toUpperCase())) {


        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_DINERS.toUpperCase())) {


        }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_ASCAN.toUpperCase())) {


        }else{

        }

        if(binding.chkActivate.isChecked()){//Is Activate?
            str_activate = "1";
        }else {
            str_activate = "0";
        }
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(AddNewConfigurationActivity.this);
        Intent i = new Intent(AddNewConfigurationActivity.this,ConfigurationHostActivity.class);
        startActivity(i);
        finish();
    }
}
