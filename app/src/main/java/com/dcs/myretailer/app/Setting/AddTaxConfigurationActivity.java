package com.dcs.myretailer.app.Setting;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityAddTaxConfigurationsBinding;

import java.util.ArrayList;
import java.util.List;

public class AddTaxConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
   // Spinner tax_type_spinner;
    String tax_type;
    static List<TaxType> taxetypes = new ArrayList<TaxType>();
    List<String> tax_names_list = new ArrayList<String>();
    String taxID = "0";
    Button testing;
    public static Handler mHandler;
    String tax_name,tax_acronym,tax_rate = "";
    Integer service_charges = 0;
    String tax_ttype = "0";
    public static String St = "0";
    ActivityAddTaxConfigurationsBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_tax_configurations);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Tax Configuration");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.chkPosService.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 134));
        binding.btnAddTax.setOnClickListener(this);
        binding.btnDeleteTax.setOnClickListener(this);
        binding.imgTaxType.setOnClickListener(this);

        Intent intent = getIntent();
        taxID = intent.getStringExtra("ID");
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (taxID.equals("null")){
            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                binding.btnAddTax.setLayoutParams(new LinearLayout.LayoutParams(520, 90));
            }else if (terminalTypeVal.equals(Constraints.IMIN)) {

                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    binding.btnAddTax.setLayoutParams(new LinearLayout.LayoutParams(720, 90));

                    LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720, 90);
                    linearOverAllParams2.leftMargin = 25;
                    linearOverAllParams2.topMargin = 10;
                    linearOverAllParams2.bottomMargin = 10;
                    binding.LayName.setLayoutParams(linearOverAllParams2);
                    binding.LayRate.setLayoutParams(linearOverAllParams2);
                    binding.Laytaxtype.setLayoutParams(linearOverAllParams2);
                    binding.Layservicecharges.setLayoutParams(linearOverAllParams2);
                } else {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 5;
                    linearOverAllParams.topMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(630,
                            90);
                    //linearOverAllParams1.leftMargin = 40;
                    binding.btnAddTax.setLayoutParams(linearOverAllParams1);
                }

            } else {
                binding.btnAddTax.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            }
            setTitle("Add Tax Configuration");
//            binding.btnAddTax.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            binding.btnAddTax.setText("Add");
            binding.btnDeleteTax.setVisibility(View.GONE);
        }else{
            setTitle("Edit Tax Configuration");
            binding.btnAddTax.setText("Update");
            binding.btnDeleteTax.setVisibility(View.VISIBLE);

            if (terminalTypeVal.equals(Constraints.IMIN)) {

                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 5;
                    linearOverAllParams.topMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720, 90);
                    linearOverAllParams2.leftMargin = 25;
                    linearOverAllParams2.topMargin = 10;
                    linearOverAllParams2.bottomMargin = 10;
                    binding.LayName.setLayoutParams(linearOverAllParams2);
                    binding.LayRate.setLayoutParams(linearOverAllParams2);
                    binding.Laytaxtype.setLayoutParams(linearOverAllParams2);
                    binding.Layservicecharges.setLayoutParams(linearOverAllParams2);

                    LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(310,
                            90);
                    binding.btnAddTax.setLayoutParams(linearOverAllParams1);

                    LinearLayout.LayoutParams linearOverAllParams3 = new LinearLayout.LayoutParams(310,
                            90);
                    linearOverAllParams3.leftMargin = 5;
                    binding.btnDeleteTax.setLayoutParams(linearOverAllParams3);
                } else {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 5;
                    linearOverAllParams.topMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(310,
                            90);
                    binding.btnAddTax.setLayoutParams(linearOverAllParams1);

                    LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(310,
                            90);
                    linearOverAllParams2.leftMargin = 5;
                    binding.btnDeleteTax.setLayoutParams(linearOverAllParams2);
                }
            }

            getTaxById(Integer.parseInt(taxID));
        }
        mHandler = new Handler();
        m_Runnable.run();

        St = "1";
    }

    private void getTaxById(int taxID) {
            Integer tax_type_id = 0;
            Cursor c = DBFunc.Query("SELECT Name, Acronym, Rate, Type, Seq, ServiceCharges FROM Tax where ID = "+taxID, true);

            if(c!=null){
                if(c.moveToNext()){
                    tax_name = c.getString(0);
                    tax_acronym = c.getString(1);
                    tax_rate = c.getString(2);
                    tax_type_id = c.getInt(3);
//                    tax_type_id = c.getInt(4);
                    service_charges = c.getInt(5);

                }
                c.close();
            }

        String sql = "Select Name FROM TaxType Where ID = "+tax_type_id+" ";
        c = DBFunc.Query(sql, true);
        if(c!=null){
            if(c.moveToNext()){
                tax_ttype = c.getString(0);
            }
            c.close();
        }

        binding.editTextTaxName.setText(tax_name);
        binding.editTextTaxActonym.setText(tax_acronym);
        binding.editTextTaxRate.setText(tax_rate);
        binding.editTaxType.setText(tax_ttype);
        if (service_charges.equals(1)){
            binding.chkPosService.setChecked(true);
        }else {

            binding.chkPosService.setChecked(false);
        }
    }

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            if (St.equals("1")) {
//            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable"+TaxTypeSheetFragment.selected_tax_id ,Toast.LENGTH_SHORT).show();
//            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();

//            if (TaxTypeSheetFragment.selected_tax_id > 0) {
//                if (TaxTypeSheetFragment.selected_tax_id > -1) {
//                    if (TaxTypeSheetFragment.selected_tax_id == 1){
//
//                        edit_tax_type.setText("Inclusive");
//                    }else if (TaxTypeSheetFragment.selected_tax_id == 2){
//                        edit_tax_type.setText("Exclusive");
//                    }else if (TaxTypeSheetFragment.selected_tax_id == 3){
//                        edit_tax_type.setText("None");
//                    }
                    if (TaxTypeSheetFragment.selected_tax_id == 1){
                        binding.editTaxType.setText("None");
                    }else if (TaxTypeSheetFragment.selected_tax_id == 2){
                        binding.editTaxType.setText("Inclusive");
                    }else if (TaxTypeSheetFragment.selected_tax_id == 3){
                        binding.editTaxType.setText("Exclusive");
                    }
                    //edit_tax_type.setText(TaxTypeSheetFragment.selected_tax_name);
//                }

                St = "0";
            }
            mHandler.postDelayed(m_Runnable,300);

            //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
        }

    };

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String value = tax_type_spinner.getSelectedItem().toString();
//        if (!value.equals("Product Category")) {
//            tax_type = tax_names_list.get(Integer.parseInt(value));
//
//            Log.v("id", "" + tax_type);
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_tax:
                //mHandler.removeCallbacks(m_Runnable);
                //mHandler.removeCallbacksAndMessages(null);
                Integer tax_type_id = 0;
                String sql = "Select ID FROM TaxType Where Name = '"+binding.editTaxType.getText().toString()+"' ";
                Cursor c = DBFunc.Query(sql, true);
                if (c != null) {
                    if (c.moveToNext()) {
                        //if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            tax_type_id = c.getInt(0);
                        }
                    }
                    c.close();
                }
                Integer Seq = 0;
                if (tax_type_id == 0){
                    Seq = 0;
                }else if (tax_type_id == 1){
                    Seq = 1;
                }else if (tax_type_id == 2){
                    Seq = 2;
                }else{
                    Seq = 3;
                }
                if (binding.chkPosService.isChecked()){
                    service_charges = 1;
                }else {
                    service_charges = 0;
                }
                if (taxID.equals("null")){
                    saveTax(tax_type_id,Seq);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                            "Tax -> Save -> Name: "+DBFunc.PurifyString(binding.editTextTaxName.getText().toString()));
                    Intent i = new Intent(AddTaxConfigurationActivity.this,TaxConfigurationActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    updateTax(tax_type_id,Seq);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                            "Tax -> Update -> Name: "+binding.editTextTaxName.getText().toString());
                    Intent i = new Intent(AddTaxConfigurationActivity.this,TaxConfigurationActivity.class);
                    startActivity(i);
                    finish();
                }

                break;
            case R.id.btn_delete_tax:
                    deleteTax(taxID);
                break;
            case R.id.img_tax_type:

                //Toast.makeText(this, "afdsfd", Toast.LENGTH_SHORT).show();
                TaxTypeSheetFragment rdSheetFragment = new TaxTypeSheetFragment();
                rdSheetFragment.show(getSupportFragmentManager(), rdSheetFragment.getTag());
                break;
        }
    }

    private void deleteTax(String taxID) {
        DBFunc.ExecQuery("DELETE FROM Tax WHERE ID = "+Integer.parseInt(taxID), true);
        Intent i = new Intent(AddTaxConfigurationActivity.this,TaxConfigurationActivity.class);
        startActivity(i);
        finish();
    }

    private void updateTax(Integer tax_type_id, Integer seq) {
        String sql = "UPDATE Tax SET Name = '"+binding.editTextTaxName.getText().toString()+"', " +
                "Acronym = '"+binding.editTextTaxActonym.getText().toString()
                +"', ServiceCharges = "+service_charges+", " +
                "Rate = "+binding.editTextTaxRate.getText().toString()+", " +
                "Type = "+tax_type_id+", Seq = "+seq+" WHERE ID = "+taxID;

        DBFunc.ExecQuery(sql, true);

        TaxTypeSheetFragment.selected_tax_id = 0;
        TaxTypeSheetFragment.selected_tax_name = "";
    }

    private void saveTax(Integer tax_type_id, Integer seq) {
        String sql = "INSERT INTO Tax (Name,Acronym,ServiceCharges,Rate,Type,Seq,DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(binding.editTextTaxName.getText().toString()) + "','" +
                binding.editTextTaxActonym.getText().toString() + "'," +
                service_charges + "," +
                binding.editTextTaxRate.getText().toString() + "," +
                tax_type_id + "," +
                seq + "," +
                System.currentTimeMillis() + ")";

        try {

            DBFunc.ExecQuery(sql, true);
        }catch (Exception e){
            Log.i("TAG","error_"+e.getMessage());
        }

        TaxTypeSheetFragment.selected_tax_id = 0;
        TaxTypeSheetFragment.selected_tax_name = "";
    }

    @Override
    public void onBackPressed() {
//        mHandler.removeCallbacks(m_Runnable);
//        mHandler.removeCallbacksAndMessages(null);
        //ActivityCompat.finishAffinity(AddTaxConfigurationActivity.this);
        Intent i = new Intent(AddTaxConfigurationActivity.this,TaxConfigurationActivity.class);
        startActivity(i);
        finish();
    }
}
