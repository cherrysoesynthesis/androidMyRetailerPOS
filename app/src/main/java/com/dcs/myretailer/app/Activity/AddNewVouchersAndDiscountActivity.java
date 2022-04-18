package com.dcs.myretailer.app.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.DiscountTypeSheetFragment;
import com.dcs.myretailer.app.Setting.TypeSheetFragment;
import com.dcs.myretailer.app.Setting.VouchersAndDiscountsActivity;
import com.dcs.myretailer.app.databinding.ActivityAddNewVouchersAndDiscountBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewVouchersAndDiscountActivity extends AppCompatActivity implements View.OnClickListener {
//    EditText edit_text_discount_name, edit_text_discount_rate,edit_text_voucher_code,type_name,btn_discount_type;
//    Button btn_add_discount,btn_delete_discount;
//    LinearLayout linear_voucher_name,linear_voucher_code,linear_discount_name,linear_discount_type,linear_discount_rate,linear_discount_value_amount,linear_discount_value_expiry_date;
//    ImageView scan_vooucher_code,voucher_expiry_date,btn_type,img_discount_type;
    Integer discountID = 0;
    Handler mHandler;
    final Calendar myCalendar = Calendar.getInstance();
    public static String scan_qr_code = "0";
//    androidx.appcompat.widget.AppCompatCheckBox chk_allow_bill_discount,chk_allow_amt_discount,chk_allow_service_charge_discount;
    AddNewVouchersAndDiscountActivity context;
    public static String discount_name,discount_type,discount_discount_type,discount_value,discount_options = "null";
    public static String chk_bill_dis,chk_bill_amt,chk_service_charges,chk_open_discount,bill_dis,bill_amt,service_charges,discountDate,open_discount = "0";
//    EditText edit_text_expiry_date;
    public static String St = "1";
    public static ActivityAddNewVouchersAndDiscountBinding binding = null;
    public static String selected_type_name = "";
    public static String selected_discount_type_name = "";
    public static void updateTypeValue(Integer disID, Integer selectedID, String selectedName) {
        if (disID != null && disID > 0) {
            getDiscountById(disID);
        }
        selected_type_name = selectedName;
        binding.typeName.setText(selectedName);

        if (selected_discount_type_name != null && selected_discount_type_name.length() > 0) {
            binding.btnDiscountType.setText(selected_discount_type_name);
        }else {
            binding.btnDiscountType.setText("0");
        }

//        String sql = "UPDATE Disc SET Type = '"+ selectedName +"'" +
//                " WHERE ID = "+disID;
//        Log.i("__sql",sql);
//        DBFunc.ExecQuery(sql, true);
    }
    public static void updateDisTypeValue(Integer disID, Integer selectedID, String selectedName) {

        if (disID != null && disID > 0) {
            getDiscountById(disID);
        }
        selected_discount_type_name = selectedName;
        binding.btnDiscountType.setText(selectedName);
//
//        String sql = "UPDATE Disc SET DiscountType = '"+ selectedName +"'" +
//                " WHERE ID = "+disID;
//        Log.i("__sql",sql);
//        DBFunc.ExecQuery(sql, true);

        if (selected_type_name != null && selected_type_name.length() > 0) {
            binding.typeName.setText(selected_type_name);
        }else {
            binding.typeName.setText("0");
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_vouchers_and_discount);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Voucher/Discount");

        context = AddNewVouchersAndDiscountActivity.this;

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.linearVoucherName.setVisibility(View.GONE);
        binding.linearVoucherCode.setVisibility(View.GONE);
        binding.linearDiscountValueExpiryDate.setVisibility(View.GONE);
        binding.linearVoucherCode.setVisibility(View.GONE);
        binding.linearDiscountValueAmount.setVisibility(View.GONE);

        binding.btnType.setOnClickListener(this);
        binding.imgDiscountType.setOnClickListener(this);
        binding.scanVooucherCode.setOnClickListener(this);
        binding.btnAddDiscount.setOnClickListener(this);
        binding.btnDeleteDiscount.setOnClickListener(this);
        binding.voucherExpiryDate.setOnClickListener(this);
        binding.chkOpenDiscount.setOnClickListener(this);

        final java.text.SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
        final Calendar myCalendar2 = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel2();
                binding.editTextExpiryDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        Intent intent = getIntent();
        if (intent.hasExtra("ID")) {
            discountID = Integer.parseInt(intent.getStringExtra("ID"));
        }

        showDisFun();
//        this.mHandler = new Handler();
//        m_Runnable.run();
    }

    private void showDisFun() {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//        if (discountID.equals("null")){
        String device = Query.GetDeviceData(Constraints.DEVICE);

        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);

            binding.btnAddDiscount.setLayoutParams(new LinearLayout.LayoutParams(720, 90));

            LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720, 90);
            linearOverAllParams2.leftMargin = 25;
            linearOverAllParams2.topMargin = 10;
            linearOverAllParams2.bottomMargin = 10;
            binding.linearDiscountName.setLayoutParams(linearOverAllParams2);
            binding.linearDiscountRate.setLayoutParams(linearOverAllParams2);
            binding.linearDiscountType.setLayoutParams(linearOverAllParams2);
            binding.lineartype.setLayoutParams(linearOverAllParams2);
            binding.linearopendiscount.setLayoutParams(linearOverAllParams2);
        } else {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 5;
            linearOverAllParams.topMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }
        binding.LayBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (discountID == 0){
            setTitle("Add Voucher/Discount");
            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                binding.btnAddDiscount.setLayoutParams(new LinearLayout.LayoutParams(520, 90));
            } else if (terminalTypeVal.equals(Constraints.IMIN)) {

                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearOverAllParamsbtnadd = new LinearLayout.LayoutParams(720,
                            90);
                    linearOverAllParamsbtnadd.leftMargin = 20;
                    linearOverAllParamsbtnadd.topMargin = 20;
                    binding.btnAddDiscount.setLayoutParams(linearOverAllParamsbtnadd);

                }else {
                    LinearLayout.LayoutParams linearOverAllParamsbtnadd = new LinearLayout.LayoutParams(630,
                            90);
                    linearOverAllParamsbtnadd.leftMargin = 40;
                    linearOverAllParamsbtnadd.topMargin = 20;
                    binding.btnAddDiscount.setLayoutParams(linearOverAllParamsbtnadd);
                }



            }else {
                binding.btnAddDiscount.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            }
            binding.btnAddDiscount.setText("Add");
            binding.btnDeleteDiscount.setVisibility(View.GONE);
        }else{
            setTitle("Edit Voucher/Discount");
            binding.btnAddDiscount.setText("Update");
            binding.btnDeleteDiscount.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams linearOverAllParamsbtnadd = new LinearLayout.LayoutParams(320,
                    90);
            linearOverAllParamsbtnadd.leftMargin =30;
            linearOverAllParamsbtnadd.topMargin = 20;
            binding.btnAddDiscount.setLayoutParams(linearOverAllParamsbtnadd);
            binding.btnDeleteDiscount.setLayoutParams(linearOverAllParamsbtnadd);

            if (terminalTypeVal.equals(Constraints.IMIN)) {

                LinearLayout.LayoutParams linearOverAllParamsbtnadd1 = new LinearLayout.LayoutParams(310,
                        90);
                linearOverAllParamsbtnadd1.leftMargin = 40;
                linearOverAllParamsbtnadd1.topMargin = 20;
                binding.btnAddDiscount.setLayoutParams(linearOverAllParamsbtnadd1);
                binding.btnDeleteDiscount.setLayoutParams(linearOverAllParamsbtnadd1);

            }

            getDiscountById(discountID);

            binding.editTextDiscountName.setText(discount_name);
            binding.editTextDiscountRate.setText(discount_value);

            selected_type_name = discount_type;
            binding.typeName.setText(discount_type);

            selected_discount_type_name = discount_discount_type;
            binding.btnDiscountType.setText(discount_discount_type);

//            chkValueCheckingFun(binding.chkAllowBillDiscount,bill_dis);
//            chkValueCheckingFun(binding.chkAllowAmtDiscount,bill_amt);
//            chkValueCheckingFun(binding.chkAllowServiceChargeDiscount,service_charges);
            chkValueCheckingFun(binding.chkOpenDiscount,open_discount);

            binding.editTextExpiryDate.setText(discountDate);
        }
    }

    private void chkValueCheckingFun(AppCompatCheckBox bindingChk, String chkStringVal) {
        Log.i("chkStringVal___","chkStringVal_____"+chkStringVal);
        if (chkStringVal !=null && chkStringVal.length() > 0 &&  chkStringVal.equals("1")){
            bindingChk.setChecked(true);
        }else{
            bindingChk.setChecked(false);
        }
    }

    @Override
    protected void onResume() {
        Log.i("_Onresumee","onreusme");
        showDisFun();
        super.onResume();
    }

    //    private final Runnable m_Runnable = new Runnable(){
//        public void run(){
//            if (St.equals("1")) {
//                if (TypeSheetFragment.selected_tax_id > 0) {
//                    binding.typeName.setText(TypeSheetFragment.selected_tax_name);
//                }
//                binding.linearVoucherName.setVisibility(View.GONE);
//                binding.linearVoucherCode.setVisibility(View.GONE);
//                binding.linearDiscountValueExpiryDate.setVisibility(View.VISIBLE);
//                binding.linearDiscountValueAmount.setVisibility(View.GONE);
//                binding.linearDiscountName.setVisibility(View.VISIBLE);
//                binding.linearDiscountType.setVisibility(View.VISIBLE);
//                binding.linearDiscountRate.setVisibility(View.VISIBLE);
//
//                if (DiscountTypeSheetFragment.selected_tax_id > 0) {
//                    binding.btnDiscountType.setText(DiscountTypeSheetFragment.selected_tax_name);
//                }
//                if (!scan_qr_code.equals("0")) {
//                    binding.editTextVoucherCode.setText(scan_qr_code);
//                }
//            }
//            context.mHandler.postDelayed(m_Runnable,500);
//            //AddTaxConfigurationActivity.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//
//    };
    public static void getDiscountById(int discountID) {
        String sql = "SELECT Name, Type, DiscountType, Value, Option , BillDiscount , AmountDiscount , " +
                "ServiceChargeDiscount , " +
                "DiscountDate, OpenDiscountStatus FROM Disc where ID = "+discountID;
        Log.i("SQLLL__","dql__"+sql);
        Cursor c = DBFunc.Query(sql, true);
        if(c!=null){
            if(c.moveToNext()){
                discount_name = c.getString(0);
                discount_type = c.getString(1);
                discount_discount_type = c.getString(2);
                discount_value = c.getString(3);
                discount_options = c.getString(4);
                bill_dis = c.getString(5);
                bill_amt = c.getString(6);
                service_charges = c.getString(7);
                discountDate = c.getString(8);
                open_discount = c.getString(9);
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_discount:
//                 mHandler.removeCallbacks(m_Runnable);
//                 mHandler.removeCallbacksAndMessages(null);
                 DiscountFun();
                break;
            case R.id.btn_delete_discount:
                deleteDiscount(discountID);
                break;
            case R.id.btn_type:
//                mHandler.removeCallbacks(m_Runnable);
//                mHandler.removeCallbacksAndMessages(null);
//                this.mHandler = new Handler();
//                m_Runnable.run();
                TypeSheetFragment rdSheetFragment = new TypeSheetFragment();
                TypeSheetFragment.disID = discountID;

                rdSheetFragment.show(getSupportFragmentManager(), rdSheetFragment.getTag());
                break;
            case R.id.img_discount_type:
//                mHandler.removeCallbacks(m_Runnable);
//                mHandler.removeCallbacksAndMessages(null);
//                this.mHandler = new Handler();
//                m_Runnable.run();
                DiscountTypeSheetFragment dtSheetFragment = new DiscountTypeSheetFragment();
                DiscountTypeSheetFragment.disID = discountID;
                dtSheetFragment.show(getSupportFragmentManager(), dtSheetFragment.getTag());
                break;
            case R.id.scan_vooucher_code:
                Intent intent_scan = new Intent(getApplicationContext(), ScanVoucherActivity.class);
                startActivity(intent_scan);
                break;
            case R.id.voucher_expiry_date:
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker=new DatePickerDialog(AddNewVouchersAndDiscountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        Log.i("selectedyear", String.valueOf(selectedyear));
                        Log.i("selectedmonth", String.valueOf(selectedmonth));
                        String month = "";
                        Integer monthh = selectedmonth + 1;
                        if (monthh < 10){
                            month = "0" + String.valueOf(monthh);
                        }else{
                            month = String.valueOf(monthh);
                        }
                        String day = "";
                        Integer dayy = selectedday;
                        if (dayy < 10){
                            day = "0" + String.valueOf(dayy);
                        }else{
                            day = String.valueOf(dayy);
                        }

                        String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                        binding.editTextExpiryDate.setText(selected_from_date);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
                break;
        }
    }
    String options = "";
    private void DiscountFun() {
        if(TextUtils.isEmpty(binding.editTextDiscountName.getText().toString())) {
            binding.editTextDiscountName.setError("Discount Name Empty!");
            return;
        }
        if(TextUtils.isEmpty(binding.editTextDiscountRate.getText().toString())) {
            binding.editTextDiscountRate.setError("Discount Rate Empty!");
            return;
        }

        chk_bill_dis = getChkBoxValueFun(binding.chkAllowBillDiscount,chk_bill_dis);
        chk_bill_amt = getChkBoxValueFun(binding.chkAllowAmtDiscount,chk_bill_amt);
        chk_service_charges = getChkBoxValueFun(binding.chkAllowServiceChargeDiscount,chk_service_charges);
        chk_open_discount = getChkBoxValueFun(binding.chkOpenDiscount,chk_open_discount);

        Integer Seq = 0;
        String discount_date = binding.editTextExpiryDate.getText().toString();
//        if (discountID.equals("null")){
        if (discountID == 0){
            saveDiscount(selected_type_name,selected_discount_type_name,Seq,options,discount_date,chk_open_discount);
        }else{
            updateDiscount(selected_type_name,selected_discount_type_name,Seq,options,discount_date,chk_open_discount);
        }
    }

    private String getChkBoxValueFun(AppCompatCheckBox bindingChkBoxVal, String chkStringValue) {
        String chkValue = "";
        if (bindingChkBoxVal.isChecked()){
            options = options + "1";
            chkStringValue = "1";
        }else{
            options = options + "0";
            chkStringValue = "0";
        }
        chkValue = chkStringValue;
        return chkValue;
    }

    private void deleteDiscount(Integer discountID) {
        DBFunc.ExecQuery("DELETE FROM Disc WHERE ID = "+discountID, true);
        Intent i = new Intent(context, VouchersAndDiscountsActivity.class);
        startActivity(i);
        finish();
    }

    private void updateDiscount(String type_name, String discount_type_name, Integer seq, String options,
                                String discountDateTime, String chk_open_discount) {
        String sql = "UPDATE Disc SET Name = '"+binding.editTextDiscountName.getText().toString()+"', " +
                "Value = '"+binding.editTextDiscountRate.getText().toString()
                +"', Option = '"+ options +"', Seq = "+ seq +", PromoTypeID = '1' " +", Type = '"+ type_name +"', DiscountType = '"+ discount_type_name +
                "', BillDiscount = '"+ chk_bill_dis +"', AmountDiscount = '"+ chk_bill_amt +"', ServiceChargeDiscount = '"+ chk_service_charges +
                "',OpenDiscountStatus = '"+ chk_open_discount +
                "', DiscountDate = '"+discountDateTime+"' , DateTime = " + System.currentTimeMillis() +
                " WHERE ID = "+discountID;
        Log.i("__sql",sql);
        DBFunc.ExecQuery(sql, true);
        //DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Tax -> Update -> Name: "+edit_text_tax_name.getText().toString());
        Intent i = new Intent(context,VouchersAndDiscountsActivity.class);
        startActivity(i);
        finish();

    }

    private void saveDiscount(String type_name, String discount_type_name, Integer seq, String options , String discountDateTime, String chk_open_discount) {
        String sql = "INSERT INTO Disc (Name,Value,Option,Seq,PromoTypeID,Type,DiscountType,BillDiscount,AmountDiscount," +
                "ServiceChargeDiscount,DiscountDate,OpenDiscountStatus,DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(binding.editTextDiscountName.getText().toString()) + "','" +
                binding.editTextDiscountRate.getText().toString() + "','" +
                options + "'," +
                seq + ",'" +
                "1" + "','" +
                type_name + "','" +
                discount_type_name + "','" +
                chk_bill_dis + "','" +
                chk_bill_amt + "','" +
                chk_service_charges + "','" +
                chk_open_discount + "','" +
                discountDateTime + "'," +
                System.currentTimeMillis() + ")";
        Log.i("__sql",sql);
        DBFunc.ExecQuery(sql, true);
        //DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Tax -> Save -> Name: "+DBFunc.PurifyString(edit_text_tax_name.getText().toString()));
        Intent i = new Intent(AddNewVouchersAndDiscountActivity.this,VouchersAndDiscountsActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(PaymentListActivity.this);
        Intent i = new Intent(context,VouchersAndDiscountsActivity.class);
        startActivity(i);
        finish();
    }
}
