package com.dcs.myretailer.app.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.BuildConfig;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FileTransfer;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.ScreenSize.AddNewProductActivityScreenSize;
import com.dcs.myretailer.app.Model.StockAdjustment;
import com.dcs.myretailer.app.Model.StockIn;
import com.dcs.myretailer.app.Setting.CondimentSheetFragment;
import com.dcs.myretailer.app.Setting.ProductCategorySheetFragment;
import com.dcs.myretailer.app.Setting.ProductManagementActivity;
import com.dcs.myretailer.app.Setting.ProductTaxSheetFragment;
import com.dcs.myretailer.app.Setting.ReceiptPrinterProductSheetFragment;
import com.dcs.myretailer.app.databinding.ActivityAddNewProductBinding;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

//import com.dcs.myretailer.app.databinding.ActivityAddNewProductBinding;

public class AddNewProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 001;
    private static final int CAMERA_REQUEST_CODE = 002;
    private String cameraFilePath;
    String ID ,product_name,product_name2,product_price,product_code,product_image,product_category = null;
    String str_checked_name_quick_edit,str_checked_price_quick_edit,str_checked_open_price_edit;
    Integer category_IDD = 0;
    String category_Namee = "0";
    String str_img = "0";
    String server_image_url = "";
    String str_imgItemId = "";
    String str_imgUrl = "";
    String str_imgType = "";
    String str_imgfileName = "";
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendarStockAdj = Calendar.getInstance();
    public static Integer selected_receipt_printer_id = -1;
    public static String selected_receipt_printer_name = "0";
    Integer AllowNameQuickEdit = 0;
    Integer AllowPriceQuickEdit = 0;
    Integer AllowOpenPrice = 0;
    Integer AllowRemarks = 0;
    String ProductCategoryName = "0";
    String category_name = "";
    Integer OnHandQty = 0;
    Handler mHandler;
    Handler mHandler1;
    public static Integer selected_product_cateogry_id = 0;
    public static String selected_product_cateogry_name = "0";
    public static Integer selected_tax_id = 0;
    public static String selected_tax_name = "0";
    public static String scan_qr_code = "0";
    String uniqueId = "";
    String path_ = "";
    public static String St = "0";
    DatePickerDialog.OnDateSetListener date = null;
    DatePickerDialog.OnDateSetListener dateStockAdj = null;
    String imgType = "";
    String imgName = "";
    String base64imgValue = "";
    public static ActivityAddNewProductBinding binding = null;
    String terminalTypeVal = "";
    static CompositeDisposable disposable = new CompositeDisposable();
//    Integer latestID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_product);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        MasterDB openMasterDB = MasterDB.getInstance(getApplicationContext());
//        AppExecutors.getInstance().diskIO().execute(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("TAGG", "onNext");
//                        List<ProductModel> aa = openMasterDB.productDao().getLatestProductID();
//
//                        for (int i = 0 ; i < aa.size() ; i ++) {
//                           ProductModel aaa =  aa.get(i);
//                            latestID = aaa.getId();
//                        }
//
//
//                    }
//                }
//        );
//        latestID = latestID +1;
//        Log.i("Ltest_","checkinngggg____dd"+latestID);

        binding.stockIn.btnAddStock.setOnClickListener(this);
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            binding.stockIn.btnAddStock.setVisibility(View.GONE);
        }

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        binding.imgProductCategory.setOnClickListener(this);
        binding.imgProductTax.setOnClickListener(this);
        binding.choosePhotoId.setOnClickListener(this);
        binding.takeCameraId.setOnClickListener(this);
        binding.imgChooseFolder.setOnClickListener(this);
        binding.imgCodiment.setOnClickListener(this);
        binding.imgReceiptPrinter.setOnClickListener(this);
        binding.editTextPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE );

        binding.stockIn.imgTockIn.setOnClickListener(this);
        binding.stockIn.layStockin.setOnClickListener(this);
        binding.stockIn.LayStockInDate.setOnClickListener(this);
        binding.stockIn.editTextInputLayProductStockDate.setOnClickListener(this);

        binding.stockAdj.btnAddAdjStock.setOnClickListener(this);
        binding.stockAdj.imgTockAdjust.setOnClickListener(this);
        binding.stockAdj.layStockadjust.setOnClickListener(this);
        binding.stockAdj.editTextInputLayProductStockAdjDate.setOnClickListener(this);


        String chk_hide_img = Query.GetOptions(20);
        if (chk_hide_img.equals("1")){
            binding.linearlayImageHide.setVisibility(View.GONE);
        }else {
            binding.linearlayImageHide.setVisibility(View.VISIBLE);
        }

        terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        binding.Laybtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //ScreenSize
        new AddNewProductActivityScreenSize(this,binding,ID);

        if (ID.equals("null")){
            setTitle(Constraints.AddProduct);

            binding.btnAddProduct.setText(Constraints.Add);
            binding.btnAddVariant.setText(Constraints.Add);
            binding.btnAddModifier.setText(Constraints.Add);
            binding.btnDeleteProduct.setVisibility(View.GONE);
        }else{
            setTitle(Constraints.EditProduct);

            ShowEditProduct(Integer.parseInt(ID));

            binding.btnAddProduct.setText(Constraints.Update);
            binding.btnAddVariant.setText(Constraints.Update);
            binding.btnAddModifier.setText(Constraints.Update);
            binding.btnDeleteProduct.setVisibility(View.VISIBLE);


            if (chkSubmitSalesOrNot.equals("1")) {
                binding.editTextProductName1.setEnabled(false);
                binding.editTextPrice.setEnabled(false);
                binding.editProductCategory.setEnabled(false);
                binding.imgProductCategory.setEnabled(false);
                binding.editTextProductCode.setEnabled(false);
                binding.scanProdcutCode.setEnabled(false);
                binding.editProductTax.setEnabled(false);
                binding.imgProductTax.setEnabled(false);
                binding.editTextProductOnhandqty.setEnabled(false);
                binding.stockIn.editTextProductStockQty.setEnabled(false);
                binding.stockIn.editTextProductStockDate.setEnabled(false);
                //edit_text_product_stock_adj_qty.setEnabled(false);
                //edit_text_product_stock_adj_date.setEnabled(false);
                binding.stockIn.editTextInputLayProductStockDate.setEnabled(false);
                //edit_text_input_lay_product_stock_adj_date.setEnabled(false);
                binding.btnDeleteProduct.setVisibility(View.GONE);
                binding.btnAddProduct.setVisibility(View.GONE);

                binding.editTextProductName1.setInputType(InputType.TYPE_NULL);
                binding.editTextPrice.setInputType(InputType.TYPE_NULL);
                binding.editTextProductCode.setInputType(InputType.TYPE_NULL);
                binding.editProductCategory.setInputType(InputType.TYPE_NULL);
                binding.editProductTax.setInputType(InputType.TYPE_NULL);
                binding.editTextProductOnhandqty.setInputType(InputType.TYPE_NULL);
                binding.stockIn.editTextProductStockDate.setInputType(InputType.TYPE_NULL);
                //edit_text_product_stock_adj_qty.setInputType(InputType.TYPE_NULL);
            }
        }


        binding.btnAddProduct.setOnClickListener(this);
        binding.btnDeleteProduct.setOnClickListener(this);
        binding.btnAddVariant.setOnClickListener(this);
        binding.btnAddModifier.setOnClickListener(this);
        binding.scanProdcutCode.setOnClickListener(this);
        binding.editTextProductCode.setOnClickListener(this);
        binding.stockIn.editTextProductStockDate.setOnClickListener(this);
        binding.stockIn.editTextProductStockDate.setOnClickListener(this);

        updateLabel();
        updateLabelAdjustment();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };
        dateStockAdj = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarStockAdj.set(Calendar.YEAR, year);
                myCalendarStockAdj.set(Calendar.MONTH, monthOfYear);
                myCalendarStockAdj.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelAdjustment();
            }

        };

        CustomerDisplayFun();

        this.mHandler = new Handler();
        m_Runnable.run();
    }

    private void CustomerDisplayFun() {
        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {

            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(540,
                    100);
//            linearparams.topMargin = 30;
            linearparams.leftMargin = 30;
            linearparams.bottomMargin = 30;

            binding.layProductName.setLayoutParams(linearparams);
            binding.layPrice.setLayoutParams(linearparams);
            binding.layProductCode.setLayoutParams(linearparams);
            binding.layProductCategory.setLayoutParams(linearparams);
            binding.layTax.setLayoutParams(linearparams);
            binding.layOnHandQty.setLayoutParams(linearparams);
//            binding.layAllowOpenPrice.setLayoutParams(linearparams);
//            binding.layKitchenPrinter.setLayoutParams(linearparams);

            LinearLayout.LayoutParams linearParamsStockIn = new LinearLayout.LayoutParams(250,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearparams.topMargin = 30;
            linearParamsStockIn.leftMargin = 30;
            linearParamsStockIn.bottomMargin = 10;
            binding.stockIn.layStockQty.setLayoutParams(linearParamsStockIn);

            binding.stockIn.LayStockInDate.setLayoutParams(linearParamsStockIn);

            LinearLayout.LayoutParams linearParamsStockInOverAll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.stockIn.layStockInOverAll.setLayoutParams(linearParamsStockInOverAll);

            LinearLayout.LayoutParams linearLayPStockInBtn = new LinearLayout.LayoutParams(540,
                    70);
//            linearparams.topMargin = 30;
//            linearPStockInBtn.leftMargin = 30;
//            linearPStockInBtn.bottomMargin = 30;

            binding.stockIn.layStockInBtnAdd.setLayoutParams(linearLayPStockInBtn);


            LinearLayout.LayoutParams linearParamsStockAdj = new LinearLayout.LayoutParams(250,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearparams.topMargin = 30;
            linearParamsStockAdj.leftMargin = 30;
            linearParamsStockAdj.bottomMargin = 10;
            binding.stockAdj.layStockQty.setLayoutParams(linearParamsStockAdj);

            binding.stockAdj.LayStockInDate.setLayoutParams(linearParamsStockAdj);

            LinearLayout.LayoutParams linearParamsStockAdjOverAll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.stockAdj.layStockInOverAll.setLayoutParams(linearParamsStockAdjOverAll);

            LinearLayout.LayoutParams linearLayPStockAdjBtn = new LinearLayout.LayoutParams(540,
                    70);
//            linearparams.topMargin = 30;
//            linearPStockInBtn.leftMargin = 30;
//            linearPStockInBtn.bottomMargin = 30;

            binding.stockAdj.layStockAdjBtnAdd.setLayoutParams(linearLayPStockInBtn);


            LinearLayout.LayoutParams linearPStockInBtn = new LinearLayout.LayoutParams(500,
                    70);
            binding.stockIn.btnAddStock.setLayoutParams(linearPStockInBtn);
        }
    }

    private void updateLabelAdjustment() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (myCalendarStockAdj.getTime() != null) {
            binding.stockAdj.editTextProductStockAdjDate.setText(sdf.format(myCalendarStockAdj.getTime()));
        }else {
            Date currentDate = new Date();
            // convert date to calendar
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);

            // manipulate date
            c.add(Calendar.YEAR, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.HOUR, 1);
            c.add(Calendar.MINUTE, 1);
            c.add(Calendar.SECOND, 1);

            // convert calendar to date
            Date currentDatePlusOne = c.getTime();

            myCalendarStockAdj.set(Calendar.YEAR, c.YEAR);
            myCalendarStockAdj.set(Calendar.MONTH, c.MONTH);
            myCalendarStockAdj.set(Calendar.DAY_OF_MONTH, c.DAY_OF_MONTH);

            binding.stockAdj.editTextProductStockAdjDate.setText(sdf.format(myCalendarStockAdj.getTime()));
        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (myCalendar.getTime() != null) {
            binding.stockIn.editTextProductStockDate.setText(sdf.format(myCalendar.getTime()));
        }else {
            Date currentDate = new Date();
            // convert date to calendar
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);

            // manipulate date
            c.add(Calendar.YEAR, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.HOUR, 1);
            c.add(Calendar.MINUTE, 1);
            c.add(Calendar.SECOND, 1);

            // convert calendar to date
            Date currentDatePlusOne = c.getTime();

            myCalendar.set(Calendar.YEAR, c.YEAR);
            myCalendar.set(Calendar.MONTH, c.MONTH);
            myCalendar.set(Calendar.DAY_OF_MONTH, c.DAY_OF_MONTH);

            binding.stockIn.editTextProductStockDate.setText(sdf.format(myCalendar.getTime()));
        }
    }

    private void ShowEditProduct(final int id) {

        String sql = "SELECT Name,Name2,Price,Code,Image,ProductCategoryID,AllowNameQuickEdit," +
                "AllowPriceQuickEdit,ProductCategoryName,ProductTaxID,ProductTaxName,AllowOpenPrice,OnHandQty,AllowRemarks FROM PLU WHERE ID = " + id;

        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            product_name = "";
            product_name2 = "";
            product_price = "";
            product_code = "";
            product_image = "";
            product_category = "";
            AllowNameQuickEdit = 0;
            AllowPriceQuickEdit = 0;
            AllowRemarks = 0;
            ProductCategoryName = "";

            selected_product_cateogry_id = 0;
            selected_product_cateogry_name = "0";
            selected_tax_id = 0;
            selected_tax_name = "0";
            AllowOpenPrice = 0;
            OnHandQty = 0;
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    product_name = c.getString(0);
                    product_name2 = c.getString(1);
                    product_price = c.getString(2);
                    product_code = c.getString(3);
                    product_image = c.getString(4);
                    product_category = c.getString(5);
                    AllowNameQuickEdit = c.getInt(6);
                    AllowPriceQuickEdit = c.getInt(7);
                    AllowRemarks = c.getInt(13);
                    ProductCategoryName = c.getString(8);

                    selected_product_cateogry_id = c.getInt(5);
                    selected_product_cateogry_name = c.getString(8);
                    selected_tax_id = c.getInt(9);
                    selected_tax_name = c.getString(10);
                    AllowOpenPrice = c.getInt(11);
                    OnHandQty = c.getInt(12);
                }
            }
            c.close();
        }

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {


        if (product_image != null || !(product_image.equals("0"))) {
//            byte[] decodedString = Base64.decode(product_image, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            img_choose_folder.setImageBitmap(decodedByte);

                    if (product_image.length() > 1) {
//                        Picasso.with(AddNewProductActivity.this).load(product_image).into(img_choose_folder);
                        String url_name = "";
                        String searchUUId = "SELECT ImageUrl,ImageFileName,ImageType FROM PLU WHERE ID = "+id;

                        Cursor c4 = DBFunc.Query(searchUUId,true);
                        if (c4 != null) {
                            if (c4.moveToNext()) {
                                if (c4.getString(0) != null && c4.getString(0).length() > 2) {
                                    url_name = "http://" + c4.getString(0) + c4.getString(1) + "." + c4.getString(2);
                                }
                            }
                            c4.close();
                        }

//                        if (url_name != null && url_name.length() > 23){
//                            Picasso.with(AddNewProductActivity.this).load(url_name).into(binding.imgChooseFolder);
//                        }else {
//                            Log.i("Dfd___","product_image___2"+product_image);
//                            Picasso.with(AddNewProductActivity.this).load(product_image).into(binding.imgChooseFolder);
//                        }


                        if (url_name != null && url_name.length() > 23){
                            Picasso.with(AddNewProductActivity.this).load(url_name).into(binding.imgChooseFolder);
                        }else {
                            try {
                                Picasso.with(AddNewProductActivity.this).load(product_image).into(binding.imgChooseFolder);
                            }catch (IllegalStateException e){
                                Picasso.with(AddNewProductActivity.this).load(R.drawable.default_no_image).into(binding.imgChooseFolder);
                            }
                        }
                    }else {

                        Picasso.with(AddNewProductActivity.this).load(R.drawable.default_no_image).into(binding.imgChooseFolder);

                    }
                }else {
                    //Picasso.with(getApplicationContext()).load(R.drawable.default_1).into(img_choose_folder);
                    Picasso.with(AddNewProductActivity.this).load(R.drawable.default_no_image).into(binding.imgChooseFolder);
                }
                //img_choose_folder.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, 120, 120, false));

                binding.editTextProductName1.setText(product_name);
                //edit_text_product_name2.setText(product_name);

                if (product_category.length() > 0){
                    sql = "Select NAME FROM Category Where ID = "+Integer.parseInt(product_category)+"";
                    Cursor ccate = DBFunc.Query(sql, true);
                    if (ccate != null) {
                        if (ccate.moveToNext()) {
                            //if (c.moveToNext()) {
                            if (!ccate.isNull(0)) {
                                category_name = ccate.getString(0);
                            }
                        }
                        ccate.close();
                    }
                }

                chkedUnchkedFun(AllowNameQuickEdit,binding.chkAllowNameQuickEdit);
                chkedUnchkedFun(AllowPriceQuickEdit,binding.chkAllowPriceQuickEdit);
                chkedUnchkedFun(AllowOpenPrice,binding.chkAllowOpenPrice);
                chkedUnchkedFun(AllowRemarks,binding.chkAllowRemarks);

                binding.editTextPrice.setText(product_price);
                binding.editTextProductCode.setText(product_code);
                binding.editTextProductOnhandqty.setText(OnHandQty.toString());

                String stockInValue = Query.SearchStockInValueByPLUID(id,"Qty");
                String stockInDateTime = Query.SearchStockInValueByPLUID(id,"strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')");
                binding.stockIn.editTextProductStockQty.setText(stockInValue);
                if (stockInDateTime.length() > 0) {
                    binding.stockIn.editTextProductStockDate.setText(stockInDateTime);
                }

                String stockAdjValue = Query.SearchStockAdjValueByPLUID(id,"VarianceQty");
                String stockAdjDateTime = Query.SearchStockInValueByPLUID(id,"strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')");

                binding.stockAdj.editTextProductStockAdjQty.setText(stockAdjValue);
                if (stockAdjDateTime.length() > 0) {
                    binding.stockAdj.editTextProductStockAdjDate.setText(stockAdjDateTime);
                }

                if (ProductCategoryName.equals("0")) {
                    binding.editProductCategory.setText("Select Product Category");
                    //btn_product_category.setText(category_name);
                }else{
                    binding.editProductCategory.setText(ProductCategoryName);
                }
                if (selected_tax_name != null) {
                    if (selected_tax_name.equals("0")) {
                        binding.editProductTax.setText("Select Tax");
                        //btn_product_category.setText(category_name);
                    } else {
                        binding.editProductTax.setText(selected_tax_name);
                    }
                }else {
                    binding.editProductTax.setText("Select Tax");
                }
//
//            }});


    }

    public static void chkedUnchkedFun (Integer statusVal,androidx.appcompat.widget.AppCompatCheckBox chkVal){
        if (statusVal == 1){
            chkVal.setChecked(true);
        }else{
            chkVal.setChecked(false);
        }
    }
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        //String[] mimeTypes = {"image/jpeg", "image/png"};
//        String[] mimeTypes = {"image/jpeg", "image/png"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }
    Integer taxID = 0;
    String taxName = "0";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_text_product_code:
                binding.editTextProductCode.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case R.id.choose_photo_id:
                pickFromGallery();
                break;
            case R.id.take_camera_id:
                captureFromCamera();
                break;
            case R.id.btn_add_product:

//                RoomDBSaveProduct(getApplicationContext(),latestID);

                String pluName = binding.editTextProductName1.getText().toString();
                String pluPrice = binding.editTextPrice.getText().toString();
                String pluCode= binding.editTextProductCode.getText().toString();
                String tax_Name = binding.editProductTax.getText().toString();


                if (path_.length() > 0) {
                    str_img = String.valueOf(Uri.parse(path_));
                }else {
                    str_img = Query.getImageUrl(AddNewProductActivity.this, binding.imgChooseFolder);
                }
                if (selected_product_cateogry_id > 0) {
                    category_IDD = selected_product_cateogry_id;
                }
                if (selected_product_cateogry_name.equals("0")) {
                    category_Namee = "0";
                }else{
                    category_Namee = selected_product_cateogry_name;
                }

                binding.btnAddProduct.setEnabled(false);
                getDataValues(AddNewProductActivity.this, tax_Name);

                try {

                    Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
                    if (Cursor_Possys != null) {
                        while (Cursor_Possys.moveToNext()) {
                            server_image_url = Cursor_Possys.getString(8);
                            str_imgItemId = UUID.randomUUID().toString();
                            str_imgUrl = server_image_url;
                            str_imgType = imgType;
                            str_imgfileName = imgName;
                        }
                    }
                }catch (java.lang.RuntimeException e){
                    Log.i("err","err___"+e.getMessage());
                }

                new AsyncTaskAddUpdateProduct(AddNewProductActivity.this).execute(ID,tax_Name,pluName,pluPrice,pluCode,
                        str_img,category_IDD,category_Namee,str_imgItemId,str_imgUrl,str_imgType,str_imgfileName,base64imgValue);

                break;
            case R.id.btn_delete_product:
                    deleteProduct(ID);
                break;
            case R.id.btn_add_stock:

                pluName = binding.editTextProductName1.getText().toString();
                pluPrice = binding.editTextPrice.getText().toString();
                pluCode= binding.editTextProductCode.getText().toString();
                tax_Name = binding.editProductTax.getText().toString();
                if (path_.length() > 0) {
                    str_img = String.valueOf(Uri.parse(path_));
                }else {
                    str_img = Query.getImageUrl(AddNewProductActivity.this, binding.imgChooseFolder);
                }

                if (selected_product_cateogry_id > 0) {
                    category_IDD = selected_product_cateogry_id;
                }
                if (selected_product_cateogry_name.equals("0")) {
                    category_Namee = "0";
                }else{
                    category_Namee = selected_product_cateogry_name;
                }

                getDataValues(AddNewProductActivity.this, tax_Name);

                new AsyncTaskForSaveStock(AddNewProductActivity.this).execute(ID,tax_Name,pluName,pluPrice,pluCode,
                        str_img,category_IDD,category_Namee,str_imgItemId,str_imgUrl,str_imgType,str_imgfileName,base64imgValue);

                break;
            case R.id.btn_add_adj_stock:
                new AsynTaskStockAdjustment(getApplicationContext()).execute();
                break;
            case R.id.scan_prodcut_code:
                Intent intent_scan = new Intent(getApplicationContext(), ScanProductActivity.class);
                startActivity(intent_scan);
                //finish();
                break;
            case R.id.img_product_category:
                ProductCategorySheetFragment pcSSheetFragment = new ProductCategorySheetFragment();
                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());

                break;
            case R.id.img_product_tax:
                ProductTaxSheetFragment pctaxSheetFragment = new ProductTaxSheetFragment();
                pctaxSheetFragment.show(getSupportFragmentManager(), pctaxSheetFragment.getTag());

                break;
            case R.id.img_codiment:
                CondimentSheetFragment dtSheetFragment = new CondimentSheetFragment();
                dtSheetFragment.show(getSupportFragmentManager(), dtSheetFragment.getTag());
                break;
            case R.id.img_receipt_printer:
                ReceiptPrinterProductSheetFragment rpSheetFragment = new ReceiptPrinterProductSheetFragment();
                rpSheetFragment.show(getSupportFragmentManager(), rpSheetFragment.getTag());
                break;
            case R.id.img_tock_in:
                LayStockInVisibleFun(binding.stockIn.layStockin);
                break;
            case R.id.img_tock_adjust:
                LayStockAdjustVisibleFun(binding.stockAdj.layStockadjust);
                break;
            case R.id.edit_text_input_lay_product_stock_date:
                new DatePickerDialog(AddNewProductActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.edit_text_input_lay_product_stock_adj_date:
                new DatePickerDialog(AddNewProductActivity.this, date, myCalendarStockAdj
                        .get(Calendar.YEAR), myCalendarStockAdj.get(Calendar.MONTH),
                        myCalendarStockAdj.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.edit_text_product_stock_date:
                new DatePickerDialog(AddNewProductActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.edit_text_product_stock_adj_date:
                new DatePickerDialog(AddNewProductActivity.this, date, myCalendarStockAdj
                        .get(Calendar.YEAR), myCalendarStockAdj.get(Calendar.MONTH),
                        myCalendarStockAdj.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.img_choose_folder:
                deleteImage(ID);
                break;
        }
    }

    private void ProgressSweetAlert() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                pDialog.dismiss();
            }
        }, 3000); // 3000 milliseconds delay
    }

    private void LayStockAdjustVisibleFun(LinearLayout lay_stockadjust) {

        if (lay_stockadjust.getVisibility() == View.VISIBLE){
            lay_stockadjust.setVisibility(View.GONE);
        }else {
            lay_stockadjust.setVisibility(View.VISIBLE);
        }
    }

    private void LayStockInVisibleFun(LinearLayout lay_stockin) {
        if (lay_stockin.getVisibility() == View.VISIBLE){
            lay_stockin.setVisibility(View.GONE);
        }else {
            lay_stockin.setVisibility(View.VISIBLE);
        }
    }

    private void deleteProduct(String ID) {
        try{
            selected_product_cateogry_id = 0;
            selected_product_cateogry_name = "0";
            selected_tax_id = 0;
            selected_tax_name = "0";
            DBFunc.ExecQuery("DELETE FROM PLU WHERE ID = "+Integer.parseInt(ID), true);
            DBFunc.ExecQuery("DELETE FROM StockAgent WHERE PLUID = '"+ID+"'", true);

            CallProductManagementActivityFun();

        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

    private void UpdateProduct(Context context,String ID,String status_StockIn,String pluName,String pluPrice,
                               String pluCode,String tax_Name,String str_img,
                               String str_imgItemId, String str_imgUrl, String str_imgType,String str_imgfileName,String base64imgValue,
                               String cateid,String catename) {

        String allowremarks = "0";
        if (binding.chkAllowRemarks.isChecked()){
            allowremarks = "1";
        }

//        if (!status_StockIn.equals("StockIn")) {
            try{

                String query = "UPDATE PLU SET ";
                query += "Name = '"+DBFunc.PurifyString(pluName)+"', ";
                query += "DeptID = "+0000+", ";
                query += "Price = "+pluPrice+", ";
                query += "Image = "+ "'" + DBFunc.PurifyString(str_img) + "'," ;
                query += "base64imgValue = "+ "'" + base64imgValue + "'," ;
                query += "ImageItemID = "+ "'" + str_imgItemId + "'," ;
                query += "ImageUrl = "+ "'" + str_imgUrl + "'," ;
                query += "ImageType = "+ "'" + str_imgType + "'," ;
                query += "ImageFileName = "+ "'" + str_imgfileName.replace(".","") + "'," ;
                query += "Option = '"+""+"', ";
                query += "Code = '"+DBFunc.PurifyString(pluCode)+"', ";
                query += "ProductTaxID = '"+taxID+"', ";
                query += "ProductTaxName = '"+tax_Name+"', ";
                query += "AllowOpenPrice = "+ str_checked_open_price_edit+", ";
                query += "AllowRemarks = "+ allowremarks+", ";
                query += "ProductCategoryID = '" + cateid + "', ";
                query += "ProductCategoryName = '"+catename+"', ";
                query += "ProductTaxID = '" + taxID + "', ";
                query += "ProductTaxName = '"+ taxName +"', ";
                query += "Condi_Seq = "+00000+", ";
                query += "DateTime = "+System.currentTimeMillis()+" ";
                query += "WHERE ID = "+Integer.parseInt(ID);

                Log.i("query______","query__"+query);
                DBFunc.ExecQuery(query,true);

                //Query.UpdatPLU(ID,pluName,pluPrice,pluCode);

                //StockInFun(AddNewProductActivity.this,Integer.parseInt(ID));

                try {

                    str_imgUrl = str_imgUrl + imgName +"."+imgType;
                    SyncActivity.SyncImageUpload(context,base64imgValue,str_imgfileName,str_imgType,str_imgItemId,str_imgUrl);
                }catch (java.lang.RuntimeException e){
                    Log.i("err","err__"+e.getMessage());
                }



            }catch (Exception e){
                Log.i("Error",e.toString());
            }

            CallProductManagementActivityFun();
//        }else {
//            StockInFun(AddNewProductActivity.this,Integer.parseInt(ID));
//        }


    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run(){
            if (St.equals("1")) {

                String chk_hide_img = Query.GetOptions(20);
                if (chk_hide_img.equals("1")){
                    binding.linearlayImageHide.setVisibility(View.GONE);
                }else {
                    binding.linearlayImageHide.setVisibility(View.VISIBLE);
                }

                if (selected_product_cateogry_id > 0) {
                    binding.editProductCategory.setText(selected_product_cateogry_name);
                }

                if (selected_tax_id > 0) {
                    binding.editProductTax.setText(selected_tax_name);
                }

                if (!scan_qr_code.equals("0")) {
                    binding.editTextProductCode.setText(scan_qr_code);
                }
                if (selected_receipt_printer_id > -1) {
                    binding.editReceiptPrinter.setText(selected_receipt_printer_name);
                }
                St = "0";
            }
            AddNewProductActivity.this.mHandler.postDelayed(m_Runnable,300);

        }

    };

    private final Runnable m_Runnable1 = new Runnable()
    {
        public void run(){
            String sql = "UPDATE PLU SET Image = '0' WHERE ID = "+ID;

            DBFunc.ExecQuery(sql,true);

            Intent addNewProduct = new Intent(getApplicationContext(), AddNewProductActivity.class);
            addNewProduct.putExtra("ID", ID);
            startActivity(addNewProduct);
            finish();


            mHandler1.removeCallbacks(m_Runnable1);
            mHandler1.removeCallbacksAndMessages(null);
           // AddNewProductActivity.this.mHandler1.postDelayed(m_Runnable1,300);

        }

    };

    private void SaveProduct(Context context,String status_StockIn,String pluName,String pluPrice,String pluCode,
                             String tax_Name,String str_img,
                             String str_imgItemId, String str_imgUrl, String str_imgType,String str_imgfileName,String base64imgValue,
                             String cateid,String catename) {

        try {

                uniqueId = UUID.randomUUID().toString();

                String allowremarks = "0";
                if (binding.chkAllowRemarks.isChecked()){
                    allowremarks = "1";
                }
                Query.SavePLU(pluName, uniqueId, pluPrice, pluCode, str_img, str_imgItemId, str_imgUrl, str_imgType,
                        str_imgfileName, base64imgValue,str_checked_name_quick_edit,
                        str_checked_price_quick_edit,str_checked_open_price_edit,allowremarks, Integer.parseInt(cateid), catename, taxID, taxName,
                        "0","Other");

                try {

                    str_imgUrl = str_imgUrl + imgName +"."+imgType;
                    SyncActivity.SyncImageUpload(context,base64imgValue,str_imgfileName,str_imgType,str_imgItemId,str_imgUrl);
                }catch (java.lang.RuntimeException e){
                    Log.i("err","err__"+e.getMessage());
                }


                Integer lstpluid = Query.findLatestID("ID","PLU",true);
                Integer onhandQtyLstpluid= Query.findOnHandQtyByPLUID(lstpluid);

                Query.UpdateStockAgent(lstpluid,onhandQtyLstpluid);

                CallProductManagementActivityFun();

            if (status_StockIn.equals("StockIn")) {

                SavedProductAndCallStockInFun(status_StockIn);

            }



        } catch (Exception e) {
            Log.i("Error", e.toString());
            ErrorSweetAlert(context, "Save Failed.");
        }

    }

    private void SavedProductAndCallStockInFun(String status_stockIn) {
        selected_product_cateogry_id = 0;
        selected_product_cateogry_name = "0";
        selected_tax_id = 0;
        selected_tax_name = "0";

        if (!status_stockIn.equals("StockIn")) {

           CallProductManagementActivityFun();

        }else {
            final Integer plu_id = Query.findLatestID("ID", "PLU", true);
            StockInFun(AddNewProductActivity.this,plu_id);
        }
    }

    private void CallProductManagementActivityFun() {
        Intent i = new Intent(AddNewProductActivity.this, ProductManagementActivity.class);
        startActivity(i);
        finish();
    }

    private void getDataValues(Context context, String tax_Name) {
        str_checked_name_quick_edit = "0";
        str_checked_price_quick_edit = "0";
        str_checked_open_price_edit = "0";

        getTaxValues(tax_Name);

        if (binding.chkAllowNameQuickEdit.isChecked()){
            str_checked_name_quick_edit = "1";
        }
        if (binding.chkAllowPriceQuickEdit.isChecked()){
            str_checked_price_quick_edit = "1";
        }
        if (binding.chkAllowOpenPrice.isChecked()){
            str_checked_open_price_edit = "1";
        } else {
            str_checked_open_price_edit = "0";
        }


        if (str_checked_name_quick_edit == null){
            str_checked_name_quick_edit = "0";
        }
        if (str_checked_price_quick_edit == null){
            str_checked_price_quick_edit = "0";
        }
        if (str_checked_open_price_edit == null){
            str_checked_open_price_edit = "0";
        }
    }

    private void getTaxValues(String tax_name) {
        Cursor CursorTax = Query.getTaxByName(tax_name);
        if (CursorTax != null) {
            if (CursorTax.moveToNext()){
                taxID = CursorTax.getInt(0);
                taxName = CursorTax.getString(1);
            }
            CursorTax.close();
        }
        if (taxID > 0){
            taxID = taxID;
        }else  {
            taxID = 0;
        }
        if (taxName.length() > 0){
            taxName = taxName;
        }else {
            taxName = "0";
        }
    }

    private void StockInFun(Context context,final Integer plu_id) {

        String stockInQty = binding.stockIn.editTextProductStockQty.getText().toString();
        String stockInDateTime = binding.stockIn.editTextProductStockDate.getText().toString();

        if (stockInQty.length() == 0){
            ErrorSweetAlert(context, Constraints.InputQtyEmpty);
            return;
        }else if (Integer.parseInt(stockInQty) == 0 ){
            ErrorSweetAlert(context, Constraints.InputQtyZero);
            return;
        }else if (Integer.parseInt(stockInQty) < 0 ){
            ErrorSweetAlert(context, Constraints.QtyLessThanZero);
            return;
        }


        String old_stockInQty = Query.SearchStockInValueByPLUID(plu_id,"Qty");
        Integer totalStockInQty = Integer.parseInt(stockInQty);

        StockIn stockIn = new StockIn(plu_id, totalStockInQty , "0000", stockInDateTime);
        Query.SaveStockIn(stockIn);


        String stockInOnHandQty = Query.SearchStockInValueByPLUID(plu_id,"Qty");
        Integer old_onHandQty = Query.findOnHandQtyByPLUID(plu_id);

        Integer NewonHandQty = 0;
        if (old_onHandQty != 0){
            NewonHandQty = old_onHandQty + Integer.parseInt(binding.stockIn.editTextProductStockQty.getText().toString());
        }else {
            if (old_onHandQty == 0){
                NewonHandQty = Integer.parseInt(binding.stockIn.editTextProductStockQty.getText().toString());
            }else {
                NewonHandQty = Integer.parseInt(stockInOnHandQty);
            }
        }

        Query.UpdateOnHandQtyByPLUID(plu_id,NewonHandQty);

        Query.UpdateStockAgent(plu_id,NewonHandQty);

        DialogFun(context,plu_id, Constraints.SavedSuccess);

    }

    private void DialogFun(final Context context, final Integer plu_id , final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new SweetAlertDialog(AddNewProductActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText(str)
                        //.setContentText("Won't be able to recover this file!")
                        //.setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                //sDialog.dismissWithAnimation();


                                Intent intent = new Intent(getApplicationContext(), AddNewProductActivity.class);
                                intent.putExtra("ID", String.valueOf(plu_id));
                                startActivity(intent);
                                finish();
                            }
                        })
//                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
                        .show();
            } });
    }

    private void SaveStockAdjustment(Context context) {

        if (!ID.equals("null") && ID.length() > 0 ) {
            //Stock Adjustment
            String stockAdjQty = binding.stockAdj.editTextProductStockAdjQty.getText().toString();
            String stockAdjDateTime = binding.stockAdj.editTextProductStockAdjDate.getText().toString();
//                String stockInQty = edit_text_product_stock_qty.getText().toString();
            //Integer PLUID, Integer qty, Integer varianceQty, String dateTime, String transNo
            if (!stockAdjQty.isEmpty()) {
                if (!stockAdjDateTime.isEmpty()) {
                    String uniqueId  = UUID.randomUUID().toString();
//                    "STKADJ/100023/2020"
                    //StockAdjustment stockAdj = new StockAdjustment(Integer.parseInt(ID), Integer.parseInt(stockAdjQty), Integer.parseInt(stockAdjQty), stockAdjDateTime, "0000");
//                    SimpleDateFormat YFormat = new SimpleDateFormat("YYYY");
//                    SimpleDateFormat AdjDtFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                    SimpleDateFormat YFormat = new SimpleDateFormat("yyyy");
                    SimpleDateFormat AdjDtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();
                    String YValue = YFormat.format(c.getTimeInMillis());
                    String stockAdjDtValueValue = AdjDtFormat.format(c.getTimeInMillis());
                    Integer lstStockAdjValue = Query.findLatestID("ID", "StockAdjustment", true);
                    String transValue = Query.billNo(lstStockAdjValue + 1);
                    String IdrefValue = "STKADJ/"+transValue+"/"+YValue;
                    String stockAdjTypeValue = "1";
                    String StkAdj_RemarkValue = "";
                    String StkAdj_RemarkDValue = "";
                    String transStatusValue = "FINISH";
                    Integer old_onHandQty = Query.findOnHandQtyByPLUID(Integer.parseInt(ID));
                    StockAdjustment stockAdj = new StockAdjustment(Integer.parseInt(ID), old_onHandQty,
                            Integer.parseInt(stockAdjQty), System.currentTimeMillis(), transValue,
                            uniqueId,IdrefValue,stockAdjTypeValue,stockAdjDtValueValue,StkAdj_RemarkValue,
                            StkAdj_RemarkDValue,transStatusValue);

                    Query.SaveStockAdjustment(stockAdj);

                    Integer NewonHandQty = old_onHandQty + Integer.parseInt(stockAdjQty);

                    Query.UpdateOnHandQtyByPLUID(Integer.parseInt(ID),NewonHandQty);

                    Query.UpdateStockAgent(Integer.parseInt(ID),NewonHandQty);

                    DialogFun(context,Integer.parseInt(ID), Constraints.SavedSuccess);
                }else {
                    ErrorSweetAlert(context, Constraints.AdjDateEmpty);
                }
            }else {
                ErrorSweetAlert(context, Constraints.AdjQtyEmpty);
            }
        }else {
            ErrorSweetAlert(context, Constraints.FillStockIn);
        }
    }
    private void SuccessSweetAlert(String msg) {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(msg)
                .show();
    }

    private void ErrorSweetAlert(final Context context, final String name) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new SweetAlertDialog(AddNewProductActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(name)
                        .show();
            } });
    }

    @Override
    public void onBackPressed() {

        CallProductManagementActivityFun();

    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,50, baos);
//        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(bitmapdata, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    path_ = data.getData().toString();

                    Uri selectedImage = data.getData();
                    // Let's read picked image path using content resolver
                    String[] filePath = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage, filePath, null, null, null);
                    cursor.moveToFirst();
                    String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

                    String picturePath = getPath(getApplicationContext( ), selectedImage);

                    String imageFileName = getRealPathFromNAME(selectedImage);

                    base64imgValue = BitMapToString(bitmap);
                    String uppperCaseImgName = imageFileName.toUpperCase();
                    try {
                        if(uppperCaseImgName.endsWith("png".toUpperCase())) {
                            String[] split = uppperCaseImgName.split(".png".toUpperCase());
                            imgType = "PNG";
                            imgName = split[0];
                        } else if(uppperCaseImgName.endsWith("JPG")) {
                            String[] split = uppperCaseImgName.split("JPG");
                            imgType = "JPG";
                            imgName = split[0];
                        } else if(uppperCaseImgName.endsWith("JPEG")) {
                            String[] split = uppperCaseImgName.split("JPG");
                            imgType = "JPEG";
                            imgName = split[0];
                        } else if(uppperCaseImgName.endsWith("BMP")) {
                            String[] split = uppperCaseImgName.split("BMP");
                            imgType = "BMP";
                            imgName = split[0];
                        } else {
                            String[] split = uppperCaseImgName.split("JPG");
                            imgType = "JPG";
                            imgName = split[0];
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        Log.i("splitedValueType", "Error_" + e.getMessage());
                    }

                    binding.imgChooseFolder.setImageURI(selectedImage);
                    break;
                case CAMERA_REQUEST_CODE:

                    path_ = cameraFilePath;
                    //content://media/external/images/media/127
                    //file:///storage/emulated/0/DCIM/Camera/JPEG_20210412_181619_2253848667930554039.jpg

                    //String imggg = getRealPathFromUri(AddNewProductActivity.this,Uri.parse(cameraFilePath));
                    //Log.i("imggg____","imggg___"+imggg);
                    binding.imgChooseFolder.setImageURI(Uri.parse(cameraFilePath));
                    break;
            }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String getRealPathFromNAME(Uri contentNAME) {
        String result;
        Cursor cursor = getContentResolver().query(contentNAME, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentNAME.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public static String getPath( Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    private void sendtoServer(String img) {
        try{
            FileTransfer fr =new FileTransfer();
                /*Toast.makeText(getApplicationContext(),
                        "ftp connected",
                        Toast.LENGTH_LONG).show();*/
            //String server = "ftp://49.128.60.174/";
            String server = "ftp://49.128.60.174";
            int port = 21;
            String user = "1111111";
            String pass = "Myret@123";
            //fr.ftpConnect(server,user,pass,8080);
            fr.ftpConnect(server,user,pass,port);

            Toast.makeText(getApplicationContext(),
                    img,
                    Toast.LENGTH_LONG).show();
            try{
                fr.ftpUpload(img,"test.jpg","sumati");
                fr.ftpUpload(img,"test.jpg","ftp://1111111@49.128.60.174/sumati/");
                //fr.ftpUpload(img,"test.jpg","sumati");
               // fr.ftpUpload();

                Toast.makeText(getApplicationContext(),
                        "image uploaded to the server",
                        Toast.LENGTH_LONG).show();

            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(),
                        "exception for upload",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception es){
            Toast.makeText(getApplicationContext(),
                    "server",
                    Toast.LENGTH_LONG).show();
        }
    }

    //public void onActivityResult(int requestCode,int resultCode,Intent data){
//    // Result code is RESULT_OK only if the user selects an Image
//    if (resultCode == Activity.RESULT_OK)
//        switch (requestCode){
//            case GALLERY_REQUEST_CODE:
//                //data.getData return the content URI for the selected Image
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                // Get the cursor
//                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                // Move to first row
//                cursor.moveToFirst();
//                //Get the column index of MediaStore.Images.Media.DATA
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                //Gets the String value in the column
//                String imgDecodableString = cursor.getString(columnIndex);
//                cursor.close();
//                // Set the Image in ImageView after decoding the String
//                img_choose_folder.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
//                break;
//
//        }
//}
    Uri imageUri = null;
    private void captureFromCamera() {
        Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()));
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
//        // the intent is my camera
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        //getting uri of the file
//        Uri file = Uri.fromFile(getFile());
//
//        //Setting the file Uri to my photo
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,file);
//
//        if(intent.resolveActivity(getPackageManager())!=null)
//        {
//            startActivityForResult(intent, PICTURE_RESULT);
//        }
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "fname_" +
//                String.valueOf(System.currentTimeMillis()) + ".jpg"));
//        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    //this method will create and return the path to the image file
    private File getFile() {
        File folder = Environment.getExternalStoragePublicDirectory("/From_camera/imagens");// the file path

        //if it doesn't exist the folder will be created
        if(!folder.exists())
        {folder.mkdir();}

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+ timeStamp + "_";
        File image_file = null;

        try {
            image_file = File.createTempFile(imageFileName,".jpg",folder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cameraFilePath = image_file.getAbsolutePath();
        return image_file;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");

        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (Exception e) {
           Log.i("errorwhiletakecamera__","e_"+e.getMessage());
        }
        // Save a file: path for using again

        if (image != null) {
            cameraFilePath = "file://" + image.getAbsolutePath();
        } else  {
            cameraFilePath = "";
        }
        return image;
    }

//    private void SocketFactorySolved(){
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        try {
//            // Create a trust manager that does not validate certificate chains
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//
//            // Create an ssl socket factory with our all-trusting manager
//            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
//            client.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            //client.hostnameVerifier((hostname, session) -> true);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void doFileUpload(String file_str) {
//        String path =
//                Environment.getExternalStorageDirectory() + File.separator  + "Images";
//        // Create the folder.
//        File folder = new File(path);
//
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//
//        // Create the file.
//        File file_path = new File(folder, file_str);
//
//        String ServerUploadPath = "http://49.128.60.174:44343/test.php";
//
//        Log.i("Uri", "Do file path" + file_path);
//
//        try {
//
//            HttpClient client = new DefaultHttpClient();
//            //use your server path of php file
//            HttpPost post = new HttpPost(ServerUploadPath);
//
//            Log.d("ServerPath", "Path" + ServerUploadPath);
//
//            FileBody bin1 = new FileBody(file_path);
//            Log.d("Enter", "Filebody complete " + bin1);
//
//            MultipartEntity reqEntity = new MultipartEntity();
//            reqEntity.addPart("uploaded_file", bin1);
//
//            post.setEntity(reqEntity);
//            Log.d("Enter", "Image send complete");
//
//            HttpResponse response = client.execute(post);
//            HttpEntity resEntity = response.getEntity();
//            Log.d("Enter", "Get Response");
//            try {
//
//                final String response_str = EntityUtils.toString(resEntity);
//                if (resEntity != null) {
//                    Log.i("RESPONSE", response_str);
//                    JSONObject jobj = new JSONObject(response_str);
//                    String result = jobj.getString("ResponseCode");
//                    Log.e("Result", "...." + result);
//
//                }
//            } catch (Exception ex) {
//                Log.e("Debug", "error: " + ex.getMessage(), ex);
//            }
//        } catch (Exception e) {
//            Log.e("Upload Exception", "");
//            e.printStackTrace();
//        }
//    }

//    public void uploadProfileImage(final String fileName){
//        byte[] imageBytes = getBytesImage(bitmap);
//        httpclient = new DefaultHttpClient();
//        httpPost = new HttpPost("URL to upload image to...");
//        String boundary = "-------------" + System.currentTimeMillis();
//        httpPost.setHeader("Content-type", "multipart/form-data;
//                boundary="+boundary);
//                ByteArrayBody bab = new ByteArrayBody(imageBytes,fileName);
//        StringBody userId = new StringBody(mPrefs.getUser().getId(),
//                ContentType.TEXT_PLAIN);
//        StringBody type = new StringBody("baby",ContentType.TEXT_PLAIN);
//        HttpEntity entity = MultipartEntityBuilder.create()
//                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
//                .setBoundary(boundary)
//                .addPart("imageUpload",bab)
//                .addPart("userid",userId)
//                .addPart("type",type)
//                .build();
//        httpPost.setEntity(entity);
//        class UploadImage extends AsyncTask<Void,Void,String> {
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getActivity(),"Please
//                        wait...","uploading picture",false,false);
//            }
//            @Override
//            protected void onPostExecute(String s){
//                super.onPostExecute(s);
//                loading.dismiss();
//                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            protected String doInBackground(Void...param){
//                String res = "";
//                HttpResponse response;
//                try{
//                    response = httpclient.execute(httpPost);
//                    res = response.getStatusLine().toString();
//                    User user = mPrefs.getUser();
//                    user.setProfileImageUrl(PICTURE_URL+fileName);
//                    mPrefs.saveUser(user);
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//                return "Profile image upload successful"
//            }
//        }
//        UploadImage u = new UploadImage();
//        u.execute();
//    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String value = product_category_spinner.getSelectedItem().toString();
//
//        String sql = "Select ID FROM Category Where Name = '"+value+"' ";
//        Cursor c = DBFunc.Query(sql, true);
//        if (c != null) {
//            if (c.moveToNext()) {
//                //if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    category_id = c.getInt(0);
//                }
//            }
//        }
//        c.close();
//
////            if (!value.equals("Product Category")) {
////                category_id = product_category_list.get(Integer.parseInt(value));
////
////                Log.v("id", "" + category_id);
////            }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }


//    private class FTPUploader extends AsyncTask<String, Void, String> {
//
//        protected String doInBackground(String... params) {
//
//            FTPClient con = new FTPClient();
//            try {
//                con.setPassive(true);
//                con.setType(FTPClient.TYPE_BINARY);
//                con.connect("ftp.server.it");
//                con.login("user", "password");
//                con.changeDirectory("changeFTPUploadDirectory");
//                con.upload(new java.io.File("/path/local/image"), new MyTransferListener());
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
////            try {
//            try {
//                con.disconnect(true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (FTPIllegalReplyException e) {
//                e.printStackTrace();
//            } catch (FTPException e) {
//                e.printStackTrace();
//            }
////            }
//
////            catch (IOException | FTPException | FTPIllegalReplyException e) {
////                e.printStackTrace();
////            }
//
//            return null;
//        }
//
//
//    }

//    private void doFileUpload(File file_path) {
//
//        Log.d("Uri", "Do file path" + file_path);
//
//        try {
//
//            HttpClient client = new DefaultHttpClient();
//            //use your server path of php file
//            HttpPost post = new HttpPost(ServerUploadPath);
//
//            Log.d("ServerPath", "Path" + ServerUploadPath);
//
//            FileBody bin1 = new FileBody(file_path);
//            Log.d("Enter", "Filebody complete " + bin1);
//
//            MultipartEntity reqEntity = new MultipartEntity();
//            reqEntity.addPart("uploaded_file", bin1);
//            reqEntity.addPart("email", new StringBody(useremail));
//
//            post.setEntity(reqEntity);
//            Log.d("Enter", "Image send complete");
//
//            HttpResponse response = client.execute(post);
//            resEntity = response.getEntity();
//            Log.d("Enter", "Get Response");
//            try {
//
//                final String response_str = EntityUtils.toString(resEntity);
//                if (resEntity != null) {
//                    Log.i("RESPONSE", response_str);
//                    JSONObject jobj = new JSONObject(response_str);
//                    result = jobj.getString("ResponseCode");
//                    Log.e("Result", "...." + result);
//
//                }
//            } catch (Exception ex) {
//                Log.e("Debug", "error: " + ex.getMessage(), ex);
//            }
//        } catch (Exception e) {
//            Log.e("Upload Exception", "");
//            e.printStackTrace();
//        }
//    }

//    public void FileUploadCloudServer(){
////        String name = editText.getText().toString().trim();
////        String path = getPath(filePath);
//        //String UPLOAD_URL = "http://49.128.60.174:44343/";
//        String UPLOAD_URL = "http://49.128.60.174:44343/";
//        String upload_id = UUID.randomUUID().toString();
//        Log.i("dsfdsfpath____",path_);
//        try {
//            new MultipartUploadRequest(this, upload_id, UPLOAD_URL)
//                    .addFileToUpload(path_, "image")
//                    .addParameter("name","saveImgCloud")
//                    .setNotificationConfig(new UploadNotificationConfig())
//                    .setMaxRetries(2)
//                    .startUpload();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//    }

//    private class AsyncTaskForShowProduct extends AsyncTask<Object, ImageView, Void> {
//        Context context;
//        //AddNewProductActivity anpa;
////        ProgressDialog dialog;
//
//        public AsyncTaskForShowProduct(Context context) {
//            this.context = context;
//            //anpa = (AddNewProductActivity) ((Activity) context);
//        }
//
//        @Override
//        protected Void doInBackground(Object... params) {
//            //int img = params[0];
//            int img = (Integer) params[0];
//            ImageView img_choose_folder = (ImageView) params[1];
//            EditText edit_text_product_name1 = (EditText) params[2];
//            Picasso picasso = (Picasso) params[3];
//
//            //ShowEditProduct(context,Integer.parseInt(ID),img,img_choose_folder,edit_text_product_name1,picasso);
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//
//    }

    private class AsyncTaskAddUpdateProduct extends AsyncTask<Object, Void, String> {
        Context context;
        //AddNewProductActivity anpa;
//        ProgressDialog dialog;
        String ID = "0";
        String pluName = "";
        String pluPrice = "";
        String pluCode = "";
        String tax_Name = "";
        String str_img = "";
        Integer category_IDD = 0;
        String category_Namee = "";
        String str_imgItemId = "";
        String str_imgUrl = "";
        String str_imgType = "";
        String str_imgfileName = "";
        String base64imgValue = "";
        public AsyncTaskAddUpdateProduct(Context context) {
            this.context = context;
           //anpa = (AddNewProductActivity) ((Activity) context);
        }

        @Override
        public String doInBackground(Object... params) {
            ID = (String) params[0];
            tax_Name = (String) params[1];
            pluName = (String) params[2];
            pluPrice = (String) params[3];
            pluCode = (String) params[4];
            str_img = (String) params[5];
            category_IDD = (Integer) params[6];
            category_Namee = (String) params[7];
            str_imgItemId = (String) params[8];
            str_imgUrl = (String) params[9];
            str_imgType = (String) params[10];
            str_imgfileName = (String) params[11];
            base64imgValue = (String) params[12];

            AsyncTaskGetDataForSaveProduct("addApdate",context,ID,"",pluName,pluPrice,pluCode,tax_Name,str_img,
                    str_imgItemId,str_imgUrl,str_imgType,str_imgfileName,base64imgValue,
                    String.valueOf(category_IDD),category_Namee);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            if (binding.editTextProductName1.getText().length() > 0 && binding.editTextPrice.getText().length() > 0){
                String str = "Update ";
                if (ID.equals("null")) {
                    str = "Save ";
                }
                Toast.makeText(AddNewProductActivity.this, str + "Successfully.", Toast.LENGTH_SHORT).show();
                binding.btnAddProduct.setEnabled(true);
            }else {
                binding.btnAddProduct.setEnabled(true);
            }


            selected_product_cateogry_id = 0;
            selected_product_cateogry_name = "";
            selected_tax_id = 0;
            selected_tax_name = "0";
            scan_qr_code = "";
        }

        @Override
        protected void onPreExecute() {
            //ErrorSweetAlert(context,"Please fill StockIn First.");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private class AsyncTaskForSaveStock extends AsyncTask<Object, Void, String> {
        Context context;
        //AddNewProductActivity anpa;
        String ID = "0";
        String pluName = "";
        String pluPrice = "";
        String pluCode = "";
        String tax_Name = "";
        String str_img = "";
        Integer category_IDD = 0;
        String category_Namee = "";
        String str_imgItemId = "";
        String str_imgUrl = "";
        String str_imgType = "";
        String str_imgfileName = "";
        String base64imgValue = "";

        public AsyncTaskForSaveStock(Context context) {
            this.context = context;
            //anpa = (AddNewProductActivity) ((Activity) context);
        }

        @Override
        protected String doInBackground(Object... params) {
            ID = (String) params[0];
            tax_Name = (String) params[1];
            pluName = (String) params[2];
            pluPrice = (String) params[3];
            pluCode = (String) params[4];
            str_img = (String) params[5];
            category_IDD = (Integer) params[6];
            category_Namee = (String) params[7];
            str_imgItemId = (String) params[8];
            str_imgUrl = (String) params[9];
            str_imgType = (String) params[10];
            str_imgfileName = (String) params[11];
            base64imgValue = (String) params[12];

            AsyncTaskGetDataForSaveProduct("stock",context,ID,"StockIn",pluName,pluPrice,pluCode,tax_Name,str_img,
                    str_imgItemId,str_imgUrl,str_imgType,str_imgfileName,base64imgValue,
                    String.valueOf(category_IDD),category_Namee);

            return ID;
        }

        @Override
        protected void onPostExecute(String result) {
        }


        @Override
        protected void onPreExecute() {
//            if (pluName.length() == 0){
//                //edit_text_price.setError("Empty Price!");
//                //return;
//                ErrorSweetAlert(context,"Name value is empty!");
//            }
//            if (pluPrice.length() == 0){
//                //edit_text_price.setError("Empty Price!");
//                //return;
//                ErrorSweetAlert(context,"Price value is empty!");
//            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    private class AsynTaskStockAdjustment extends AsyncTask<Void, Void, Void> {
        Context context;
        //AddNewProductActivity anpa;
        public AsynTaskStockAdjustment(Context context) {
            this.context = context;
            //anpa = (AddNewProductActivity) ((Activity) context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            getDataFromSqlite2(context);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            String str = "Update ";
//            if (ID.equals("null")) {
//                str = "Save ";
//            }
//            Toast.makeText(AddNewProductActivity.this, str + "Successfully.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private void AsyncTaskGetDataForSaveProduct(String str, Context context, String ID, String status, String pluName, String pluPrice,
                                                String pluCode, String tax_Name, String str_img,
                                                String str_imgItemId, String str_imgUrl, String str_imgType,String str_imgfileName,String base64imgValue,
                                                String cateid, String cateName) {
//        validate(context,pluName,"Name");
//        validate(context,pluPrice,"Price");

        if (pluName.length() > 0 && pluPrice.length() >0) {
//            if (Double.parseDouble(pluPrice) > 0.0 ){

//                if (str.equals("stock")){
//                    if (ID.equals("null")){
//                        final Integer plu_id = Query.findLatestID("ID", "PLU", true);
//                        StockInFun(AddNewProductActivity.this,plu_id);
//                    }else {
//                        StockInFun(AddNewProductActivity.this,Integer.parseInt(ID));
//                    }
//                }else {
                    if (ID.equals("null")) {
                        SaveProduct(context,status,pluName,pluPrice,pluCode,tax_Name,str_img,
                                str_imgItemId, str_imgUrl, str_imgType,
                                str_imgfileName,base64imgValue,cateid,cateName);

                    }else {
                        if (str.equals("stock")){
                            StockInFun(AddNewProductActivity.this,Integer.parseInt(ID));
                        }else {
                            UpdateProduct(context, ID, status, pluName, pluPrice, pluCode, tax_Name, str_img, str_imgItemId, str_imgUrl, str_imgType,
                                    str_imgfileName,base64imgValue, cateid, cateName);
                        }
                    }
//                }

//            }else {
//                ErrorSweetAlert(context,ENUM.PriceValueZero);
//            }
        }else {
            ErrorSweetAlert(context, Constraints.NamePriceValueEmpty);
        }
    }

    public void getDataFromSqlite2(Context context) {
         SaveStockAdjustment(context);

    }

//    public void validate(Context context, String name , String value){
//        if (name.length() == 0){
//            ErrorSweetAlert(context,value+" value is empty!");
//            return;
//        }
//    }

    private void deleteImage(final String ID) {
        final String[] strDeletCheck = {"0"};
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Delete Image")
                .setTitleText(Constraints.DeleteQuestion)
                .setConfirmText(Constraints.YES)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        strDeletCheck[0] = "1";
                        Log.i("Dfd__","strDeletCheck_1"+ strDeletCheck[0]);
                        sDialog.dismissWithAnimation();
                        //sDialog.dismissWithAnimation();
                        //if (ID.length() > 0){

//                            Query.DeleteImageLogoReceiptEditor(getApplicationContext(),"PLU","Image",squery);
                            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_no_image);
                            //String sql = "UPDATE PLU SET Image = '"+ DBFunc.PurifyString(AddNewProductActivity.BitMapToString(bitmap)) + "' "+squery;

//                            DBFunc.ExecQuery(sql,true);
//////                            int img = R.drawable.default_no_image;
//////                            Picasso picasso = Picasso.with(AddNewProductActivity.this);
//////                            new AsyncTaskForShowProduct(AddNewProductActivity.this).execute(img,img_choose_folder,edit_text_product_name1,picasso);
////                            try {
////                                //Picasso.with(AddNewProductActivity.this).load(R.drawable.default_no_image).into(binding.imgChooseFolder);
////                                ShowEditProduct(Integer.parseInt(ID));
////                            }catch (NumberFormatException e) {
////                                Log.i("DERR","ERROR"+e.getMessage());
////                            }
//                        Intent addNewProduct = new Intent(getApplicationContext(), AddNewProductActivity.class);
//                        addNewProduct.putExtra("ID", ID);
//                        startActivity(addNewProduct);
//                        finish();

                        //}
                        mHandler1 = new Handler();
                        m_Runnable1.run();
                    }
                })
                .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        strDeletCheck[0] = "0";
                    }
                })
                .show();

//            String sql = "UPDATE PLU SET Image = '' WHERE ID = "+ID;
//            DBFunc.ExecQuery(sql,true);



    }
//
//    public static void RoomDBSaveProduct(Context context,Integer i) {
//        //Observable
//        Observable<ProductModel> tstObservablePlu = gettstObservableProduct(context,i);
//
//        //Observer
//        //Observer
//        Observer<ProductModel> tstObserverplu = gettstObserverProduct(context);
//
//        tstObservablePlu.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(tstObserverplu);
//    }

//    public static Observer<ProductModel> gettstObserverProduct(Context c) {
//        return new Observer<ProductModel>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.i("TAGG", "pluOnsubscribe");
//               // disposable = (CompositeDisposable) d;
//            }
//
//            @Override
//            public void onNext(@NonNull ProductModel productModel) {
//                MasterDB openMasterDB = MasterDB.getInstance(c);
//                AppExecutors.getInstance().diskIO().execute(
//                        new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.i("TAGG", "onNext");
//                                openMasterDB.productDao().SaveProduct(productModel);
//
//                                ProductModel result = openMasterDB.productDao().getProduct();
//                                if (result != null) {
//                                    Log.i("DSFDF__Ressulttt", result.toString());
//                                }
//                            }
//                        }
//                );
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.i("TAGG", "plu__e" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i("TAGG", "onComplete");
//            }
//        };
//    }
//
//    private static Observable<ProductModel> gettstObservableProduct(Context c,Integer ii) {
//
////        final String price = mData.get(position).getPrice();
////        final String uuid = mData.get(position).getUUID();
////        final String name = mData.get(position).getProductCategoryName();
//
//        String name = binding.editTextProductName1.getText().toString();
//        String price = binding.editTextPrice.getText().toString();
//        String pluCode = binding.editTextProductCode.getText().toString();
//        String tax_Name = binding.editProductTax.getText().toString();
//        String uuid = UUID.randomUUID().toString();
//
//
//        Log.i("LatestID__","lst__idddddd"+ii);
//        ProductModel openModel = new ProductModel(
//                uuid,
//                name,
//                "",
//                "",
//                "",
//                0,
//                Double.valueOf(price),
//                "00000",
//                pluCode,
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                0,
//                0,
//                0,
//                0,
//                0,
//                0L
//        );
//        Observable<ProductModel> dataObservable = Observable.just(openModel);
//        return dataObservable;
//    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
