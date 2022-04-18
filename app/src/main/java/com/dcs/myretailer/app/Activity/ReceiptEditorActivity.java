package com.dcs.myretailer.app.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.BuildConfig;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityReceiptEditorBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ReceiptEditorActivity extends AppCompatActivity implements View.OnClickListener {
    String str_chk_print_logo = "0";
    String str_image_status = "0";
    private static final int GALLERY_REQUEST_CODE = 001;
    private static final int CAMERA_REQUEST_CODE = 002;
    private String cameraFilePath;
    Integer count = 1;
    String strings = "";
    String strings_footer = "";
    Context context;
    List<EditText> allEds = new ArrayList<EditText>();
    List<EditText> allEds_footer = new ArrayList<EditText>();
    ActivityReceiptEditorBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_receipt_editor);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_receipt_editor);
        context = ReceiptEditorActivity.this;


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Receipt Editor");

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            linearOverAllParams.leftMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }else if (terminalTypeVal.equals(Constraints.IMIN)) {
            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams2.leftMargin = 25;
                linearOverAllParams2.topMargin = 10;
                linearOverAllParams2.bottomMargin = 10;
                binding.linearLayoutAddHeader.setLayoutParams(linearOverAllParams2);

                binding.linearFooter.setLayoutParams(linearOverAllParams2);
                binding.Layprintlogo.setLayoutParams(linearOverAllParams2);
//
                LinearLayout.LayoutParams linearbtn = new LinearLayout.LayoutParams(320, 50);
                linearbtn.leftMargin = 25;
                linearbtn.topMargin = 10;
                binding.btnAdd.setLayoutParams(linearbtn);
                binding.btnDelete.setLayoutParams(linearbtn);
                binding.btnAddFooter.setLayoutParams(linearbtn);
                binding.btnDeleteFooter.setLayoutParams(linearbtn);

                LinearLayout.LayoutParams Laybtn = new LinearLayout.LayoutParams(720, 90);
                Laybtn.leftMargin = 30;
                Laybtn.topMargin = 20;
                binding.Laybtn.setLayoutParams(Laybtn);

                LinearLayout.LayoutParams paramsbtn_add_receipt_editor = new LinearLayout.LayoutParams(720, 90);
                //paramsbtn_add_receipt_editor.leftMargin = 30;
                binding.btnAddReceiptEditor.setLayoutParams(paramsbtn_add_receipt_editor);
            } else {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(680,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 2;
                //linearOverAllParams.topMargin = 2;
                //linearOverAllParams.rightMargin = 2;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(680,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                binding.generalSettingLayout.setLayoutParams(linearOverAllParams1);

                LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(200,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams2.leftMargin = 20;
                linearOverAllParams2.topMargin = 30;
                binding.choosePhotoId.setLayoutParams(linearOverAllParams2);
//                binding.takeCameraId.setLayoutParams(linearOverAllParams2);


                LinearLayout.LayoutParams Laybtn = new LinearLayout.LayoutParams(680, 110);
                Laybtn.leftMargin = 10;
                //Laybtn.topMargin = 20;
                binding.Laybtn.setLayoutParams(Laybtn);

                LinearLayout.LayoutParams Laybtn1 = new LinearLayout.LayoutParams(650, 90);
                Laybtn1.bottomMargin = 30;
                binding.btnAddReceiptEditor.setLayoutParams(Laybtn1);
            }
//            if (device.equals("M2-Max")) {
//                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT);
//                linearOverAllParams.leftMargin = 10;
//                binding.layOverAll.setLayoutParams(linearOverAllParams);
//
//                LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720, 90);
//                linearOverAllParams2.leftMargin = 25;
//                linearOverAllParams2.topMargin = 10;
//                linearOverAllParams2.bottomMargin = 10;
//                binding.linearLayoutAddHeader.setLayoutParams(linearOverAllParams2);
//                binding.linearFooter.setLayoutParams(linearOverAllParams2);
//                binding.Layprintlogo.setLayoutParams(linearOverAllParams2);
//
//                LinearLayout.LayoutParams linearbtn = new LinearLayout.LayoutParams(320, 90);
//                linearbtn.leftMargin = 25;
//                linearbtn.topMargin = 10;
//                binding.btnAdd.setLayoutParams(linearbtn);
//                binding.btnDelete.setLayoutParams(linearbtn);
//            } else {
//                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                linearOverAllParams.leftMargin = 5;
//                linearOverAllParams.topMargin = 10;
//                binding.layOverAll.setLayoutParams(linearOverAllParams);
//            }

        }

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.btnAddReceiptEditor.setOnClickListener(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
        binding.btnAddFooter.setOnClickListener(this);
        binding.btnDeleteFooter.setOnClickListener(this);
        binding.choosePhotoId.setOnClickListener(this);
        binding.takeCameraId.setOnClickListener(this);
        binding.imgChooseFolder.setOnClickListener(this);
        binding.imgZclose.setOnClickListener(this);

        getAllReceiptEditor();
    }

    private void getAllReceiptEditor() {
        Cursor c = DBFunc.Query("SELECT HeaderValue,FooterValue,Options,ImageStatus,DateTime,Logo FROM ReceiptEditor ", true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    if (!c.getString(0).equals("_&ABCD_EF_G&")) {
                        binding.editTextReceiptEditorHeader.setText(c.getString(0).split("_&ABCD_EF_G&")[0].replace("_&ABCD_EF_G&", ""));
                    }
                    if (!c.getString(1).equals("_&ABCD_EF_G&")) {
                        binding.editTextReceiptEditorFooter.setText(c.getString(1).split("_&ABCD_EF_G&")[0].replace("_&ABCD_EF_G&", ""));
                    }
                        List<String> items = Arrays.asList(c.getString(0).split("_&ABCD_EF_G&"));
                        allEds.clear();
                        binding.lay1.removeAllViews();
                        for (int i = 1; i < items.size(); i++) {
                            EditText valueTV = new EditText(getApplicationContext());
                            valueTV.setWidth(1);
                            valueTV.setTextSize(14);
                            valueTV.setId(i);
                            valueTV.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mine_shaft));
                            valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.FILL_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            valueTV.setText(items.get(i).toString());
                            binding.lay1.addView(valueTV);
                            allEds.add(valueTV);
                        }
                        List<String> items_footer = Arrays.asList(c.getString(1).split("_&ABCD_EF_G&"));
                        allEds_footer.clear();
                    binding.lay2.removeAllViews();
                        for (int i = 1; i < items_footer.size(); i++) {
                            EditText valueTV = new EditText(getApplicationContext());
                            valueTV.setWidth(1);
                            valueTV.setTextSize(14);
                            valueTV.setId(i);
                            valueTV.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mine_shaft));
                            valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.FILL_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            valueTV.setText(items_footer.get(i).toString());
                            binding.lay2.addView(valueTV);
                            allEds_footer.add(valueTV);
                        }
                        if (c.getInt(2) == 1) {
                            binding.chkPrintLogo.setChecked(true);
                        } else {
                            binding.chkPrintLogo.setChecked(false);
                        }
                         if (c.getString(5) != null) {
                            byte[] decodedString = Base64.decode(c.getString(5), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                             binding.imgChooseFolder.setImageBitmap(decodedByte);
                        }else {
                            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.default_no_image);
                            binding.imgChooseFolder.setImageBitmap(bitmap);
                        }
                    }
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_receipt_editor:
                strings = "";
                for(int i=0; i < allEds.size(); i++){
                    strings += allEds.get(i).getText().toString().concat("_&ABCD_EF_G&");
                }
                strings_footer = "";
                for(int i=0; i < allEds_footer.size(); i++){
                    strings_footer += allEds_footer.get(i).getText().toString().concat("_&ABCD_EF_G&");
                }
                if (binding.chkPrintLogo.isChecked() == true){
                    str_chk_print_logo = "1";
                    str_image_status = "1";
                }else{
                    str_chk_print_logo = "0";
                    str_image_status = "0";
                }
                SaveReceiptEditor();
            break;
            case R.id.btn_delete:
                if(allEds.size() > 0){
                    binding.lay1.removeView(allEds.get(allEds.size() - 1));

                    allEds.remove(allEds.size()- 1);

                    DBFunc.ExecQuery("DELETE FROM ReceiptHeader WHERE ID=(SELECT MAX(id) FROM ReceiptHeader)", true);
                }
                break;
            case R.id.btn_delete_footer:
                if(allEds_footer.size() > 0){
                    binding.lay2.removeView(allEds_footer.get(allEds_footer.size() - 1));

                    allEds_footer.remove(allEds_footer.size()- 1);

                    DBFunc.ExecQuery("DELETE FROM ReceiptFooter WHERE ID=(SELECT MAX(id) FROM ReceiptFooter)", true);
                }
                break;
            case R.id.btn_add:
                Cursor c = DBFunc.Query("SELECT * FROM ReceiptHeader ", true);
                if (c != null) {
                    count = 0;
                    if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            count = c.getCount();
                        }
                    }
                    c.close();
                }

                if (count < 17) {
                    DBFunc.ExecQuery("INSERT INTO ReceiptHeader (Seq,Type,PrintSize,Data) VALUES (0,0,0,'')", true);
                }

                c = DBFunc.Query("SELECT * FROM ReceiptHeader ", true);
                if (c != null) {
                    count = 0;
                    if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            count = c.getCount();
                        }
                    }
                    c.close();
                }
                List<String> items = new ArrayList<String>();
                c = DBFunc.Query("SELECT HeaderValue,FooterValue,Options,ImageStatus,DateTime FROM ReceiptEditor ", true);
                if (c != null) {
                    if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            items = Arrays.asList(c.getString(0).split("_&ABCD_EF_G&"));
                            count -= 1;
                        }
                    }
                    c.close();
                }

                binding.lay1.removeAllViews();
                allEds.clear();

                if (items.size() > 0) {
                    for (int i = 1; i < items.size(); i++) {
                        EditText valueTV = new EditText(getApplicationContext());
                        valueTV.setWidth(1);
                        valueTV.setId(i);
                        valueTV.setTextSize(14);
                        valueTV.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mine_shaft));
                        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        valueTV.setText(items.get(i).toString());
                        binding.lay1.addView(valueTV);
                        allEds.add(valueTV);
                    }
                }
                for (int i = 0 ; i < count ; i ++){
                    EditText valueTV = new EditText(getApplicationContext());
                    valueTV.setWidth(1);
                    valueTV.setTextSize(14);
                    valueTV.setId(i);
                    valueTV.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mine_shaft));
                    valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.FILL_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    binding.lay1.addView(valueTV);
                    allEds.add(valueTV);
                }

                //count ++;
                break;
            case R.id.btn_add_footer:
                c = DBFunc.Query("SELECT * FROM ReceiptFooter ", true);
                if (c != null) {
                    count = 0;
                    if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            count = c.getCount();
                        }
                    }
                    c.close();
                }

                if (count < 17) {
                    DBFunc.ExecQuery("INSERT INTO ReceiptFooter (Seq,Type,PrintSize,Data) VALUES (0,0,0,'')", true);
                }

                c = DBFunc.Query("SELECT * FROM ReceiptFooter ", true);
                if (c != null) {
                    count = 0;
                    if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            count = c.getCount();
                        }
                    }
                    c.close();
                }
                items = new ArrayList<String>();
                c = DBFunc.Query("SELECT HeaderValue,FooterValue,Options,ImageStatus,DateTime FROM ReceiptEditor ", true);
                if (c != null) {
                    if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            items = Arrays.asList(c.getString(1).split("_&ABCD_EF_G&"));
                            count -= 1;
                        }
                    }
                    c.close();
                }

                binding.lay2.removeAllViews();
                allEds_footer.clear();

                if (items.size() > 0) {
                    for (int i = 1; i < items.size(); i++) {
                        EditText valueTV = new EditText(getApplicationContext());
                        valueTV.setWidth(1);
                        valueTV.setId(i);
                        valueTV.setTextSize(14);
                        valueTV.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mine_shaft));
                        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        valueTV.setText(items.get(i).toString());
                        binding.lay2.addView(valueTV);
                        allEds_footer.add(valueTV);
                    }
                }
                for (int i = 0 ; i < count ; i ++){
                    EditText valueTV = new EditText(getApplicationContext());
                    valueTV.setWidth(1);
                    valueTV.setTextSize(14);
                    valueTV.setId(i);
                    valueTV.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mine_shaft));
                    valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.FILL_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    binding.lay2.addView(valueTV);
                    allEds_footer.add(valueTV);
                }

                //count ++;
                break;
            case R.id.choose_photo_id:
                pickFromGallery();
                break;
            case R.id.take_camera_id:
                captureFromCamera();
                break;
            case R.id.img_choose_folder:
                deleteImage();
                break;
            case R.id.img_zclose:
//                FontAssetsSheetFragment pcSSheetFragment = new FontAssetsSheetFragment();
//                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                break;
        }
    }

    private void deleteImage() {
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("Delete Image");
//        alert.setMessage("Are you sure you want to delete?");
//        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//            //Database_TT db = new Database_TT(DeleteTT.this);
//
//            public void onClick(DialogInterface dialog, int which) {
//                // continue with delete
//                //Database_TT db = new Database_TT(DeleteTT.this);
//                //db.deleteEntry(getID);
//                Query.DeleteImageLogoReceiptEditor(getApplicationContext(),"ReceiptEditor","Logo","");
//                getAllReceiptEditor();
//            }
//        });
//        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // close dialog
//                dialog.cancel();
//            }
//        });
//        alert.show();
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Delete Image")
                .setTitleText(Constraints.DeleteQuestion)
                .setConfirmText(Constraints.YES)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //sDialog.dismissWithAnimation();
                        Query.DeleteImageLogoReceiptEditor(getApplicationContext(),"ReceiptEditor","Logo","");
                        getAllReceiptEditor();
                        sDialog.dismissWithAnimation();
                    }
                })
                .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    binding.imgChooseFolder.setImageURI(selectedImage);
                    break;
                case CAMERA_REQUEST_CODE:
                    binding.imgChooseFolder.setImageURI(Uri.parse(cameraFilePath));
                    break;
            }
    }
    private void captureFromCamera() {
        Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()));
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }

    private void SaveReceiptEditor() {
        BitmapDrawable drawable = null;
        Bitmap bitmap = null;
        if (binding.imgChooseFolder.getDrawable() == null){
            bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.default_no_image);
        }else {
            drawable = (BitmapDrawable) binding.imgChooseFolder.getDrawable();
            bitmap = drawable.getBitmap();
        }

        DBFunc.ExecQuery("DELETE FROM ReceiptEditor", true);
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), "ReceiptEditor -> Delete ");

        String header = binding.editTextReceiptEditorHeader.getText().toString()+"_&ABCD_EF_G&"+strings;
        String footer = binding.editTextReceiptEditorFooter.getText().toString()+"_&ABCD_EF_G&"+strings_footer;
        Integer print_status = Integer.parseInt(str_chk_print_logo);
        Integer img_status = Integer.parseInt(str_image_status);

        Query.SaveReceiptEditor(getApplicationContext(),bitmap,header,footer,print_status,img_status);


        getAllReceiptEditor();
        Toast.makeText(this, "Receipt Editor Save Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(ReceiptEditorActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
