package com.dcs.myretailer.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.BuildConfig;
import com.dcs.myretailer.app.Model.CategoryModel;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.ScreenSize.AddNewCategoryActivityScreenSize;
import com.dcs.myretailer.app.databinding.ActivityAddNewCategoryBinding;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AddNewCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 001;
    private static final int CAMERA_REQUEST_CODE = 002;
    private String cameraFilePath;
    String ID,category_name,category_image;
    ActivityAddNewCategoryBinding binding = null;
    String path_ = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_category);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        binding.choosePhotoId.setOnClickListener(this);
        binding.takeCameraId.setOnClickListener(this);
        String hideImage = Query.GetOptions(20);
        if (hideImage.equals("1")){
            binding.laycategoryId.setVisibility(View.GONE);
        }else {
            binding.laycategoryId.setVisibility(View.VISIBLE);
        }
        if (ID.equals("null")){
            setTitle("Add Product Category");
            binding.btnAddCategory.setText("Add");
            binding.btnDeleteCategory.setVisibility(View.GONE);
        }else{
            setTitle("Edit Product Category");
            binding.btnAddCategory.setText("Update");
            binding.btnDeleteCategory.setVisibility(View.VISIBLE);

            String chkSubmitSalesOrNot = Query.GetOptions(22);
            if (chkSubmitSalesOrNot.equals("1")) {
                binding.editTextCategoryName.setEnabled(false);
                binding.editTextCategoryName.setInputType(InputType.TYPE_NULL);
                binding.btnAddCategory.setVisibility(View.GONE);
                binding.btnDeleteCategory.setVisibility(View.GONE);

            }
        }
        //ScreenSize
        new AddNewCategoryActivityScreenSize(this,binding);

        binding.btnAddCategory.setOnClickListener(this);
        binding.btnDeleteCategory.setOnClickListener(this);
        if (!ID.equals("null")) {
            ShowEditCategory(Integer.parseInt(ID));
        }
    }
    public String BitMapToString(Bitmap bitmap){
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
//        byte [] b=baos.toByteArray();
//        String temp= Base64.encodeToString(b, Base64.DEFAULT);
//        return temp;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,50, baos);
//        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(bitmapdata, Base64.DEFAULT);
        return temp;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    path_ = data.getData().toString();
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    binding.imgChooseFolder.setImageURI(selectedImage);
                    break;
                case CAMERA_REQUEST_CODE:
                    path_ = cameraFilePath;
                    binding.imgChooseFolder.setImageURI(Uri.parse(cameraFilePath));
                    break;
            }
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_photo_id:
                pickFromGallery();
                break;
            case R.id.take_camera_id:
                captureFromCamera();
                break;
            case R.id.btn_add_category:

                if (ID.equals("null")) {
                    SaveCategory();
                }else{
                    UpdateCategory(ID);
                }
                break;
            case R.id.btn_delete_category:

                deleteCategory(ID);
                break;
        }
    }
    private void SaveCategory() {
//        BitmapDrawable drawable = null;
//        if (img_choose_folder.getDrawable() == null){
//            Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                    R.drawable.ic_image);
//            drawable = new BitmapDrawable(this.getResources(), icon);
//        }else{
//            drawable = (BitmapDrawable) img_choose_folder.getDrawable();
//        }
//
//        Bitmap bitmap = drawable.getBitmap();
        String str_img = "";
        if (path_.length() > 0) {
//            str_img = Query.getImageUrl(getApplicationContext(), binding.imgChooseFolder);
            str_img = String.valueOf(Uri.parse(path_));
        }
        try {
//            String sql = "INSERT INTO Category (Name,Image,DateTime) " +
//                    "VALUES ('" + DBFunc.PurifyString(edit_text_category_name.getText().toString().toUpperCase()) + "'," +
//                    "'" + DBFunc.PurifyString(BitMapToString(bitmap)) + "'," +
//                    System.currentTimeMillis() + ")";
//            Log.i("sQl_category",sql);
//            DBFunc.ExecQuery(sql, true);
            String name = binding.editTextCategoryName.getText().toString();
            //Query.saveCategory(name,str_img,getApplicationContext());

            String uniqueId  = UUID.randomUUID().toString();
            CategoryModel ctM = new CategoryModel(0,uniqueId,name,name,str_img,0);
            Query.saveCategory(ctM);

            Intent i = new Intent(AddNewCategoryActivity.this,CategoryManagementActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }
    private void ShowEditCategory(int id) {

        Cursor c = DBFunc.Query("SELECT Name,Image FROM Category WHERE ID = " + id, true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    category_name = c.getString(0);
                    category_image = c.getString(1);
                }
            }
            c.close();
        }
//        byte[] decodedString = Base64.decode(category_image, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        img_choose_folder.setImageBitmap(decodedByte);
        if (category_image == null || category_image.equals("0") || category_image.length() == 0){
            Picasso.with(getApplicationContext()).load(R.drawable.default_no_image).into(binding.imgChooseFolder);
        }else {
            Picasso.with(getApplicationContext()).load(category_image).into(binding.imgChooseFolder);
        }

        binding.editTextCategoryName.setText(category_name);
    }
    private void UpdateCategory(String ID) {
        try{
////            BitmapDrawable drawable = (BitmapDrawable) img_choose_folder.getDrawable();
////            Bitmap bitmap = drawable.getBitmap();
//            BitmapDrawable drawable = null;
//            if (img_choose_folder.getDrawable() == null){
//                Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                        R.drawable.ic_image);
//                drawable = new BitmapDrawable(this.getResources(), icon);
//            }else{
//                drawable = (BitmapDrawable) img_choose_folder.getDrawable();
//            }
//
//            Bitmap bitmap = drawable.getBitmap();
            String str_img = "";
            if (path_.length() > 0 ) {
                //str_img = Query.getImageUrl(getApplicationContext(),binding.imgChooseFolder);
                str_img = String.valueOf(Uri.parse(path_));
            }

            String query = "UPDATE Category SET ";

            query += "Name = '"+DBFunc.PurifyString(binding.editTextCategoryName.getText().toString().toUpperCase())+"', ";

            query += "Image = '"+DBFunc.PurifyString(str_img)+"', ";

            query += "DateTime = "+System.currentTimeMillis()+" ";

            query += "WHERE ID = "+Integer.parseInt(ID);

            DBFunc.ExecQuery(query, true);


            Intent i = new Intent(AddNewCategoryActivity.this,CategoryManagementActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }

    }


//    { "categories": [
//        { "CategoryID": "fca99d56-2f21-11eb-be45-00155d01ca02",
//                "CategoryCode": "ALICE MARTHA",
//                "CategoryName": "ALICE MARTHA",
//                "CategoryImage": null, "OtherLanguage": "" },
        private void deleteCategory(String ID) {
        try{
            DBFunc.ExecQuery("DELETE FROM Category WHERE ID = "+Integer.parseInt(ID), true);
            Intent i = new Intent(AddNewCategoryActivity.this,CategoryManagementActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
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

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(CategoryManagementActivity.this);
        Intent i = new Intent(AddNewCategoryActivity.this,CategoryManagementActivity.class);
        startActivity(i);
        finish();
    }
}
