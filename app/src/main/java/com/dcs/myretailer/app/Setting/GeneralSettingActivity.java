package com.dcs.myretailer.app.Setting;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.BuildConfig;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityGeneralSettingBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GeneralSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 001;
    private static final int CAMERA_REQUEST_CODE = 002;
    private String cameraFilePath;
    Context context;
    ActivityGeneralSettingBinding binding = null;
    String path_ = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_general_setting);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_general_setting);

        context = GeneralSettingActivity.this;

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 20;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearLayName = new LinearLayout.LayoutParams(700,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayName.leftMargin = 50;
            linearLayName.topMargin = 30;
            binding.LayName.setLayoutParams(linearLayName);
            binding.LayAddress.setLayoutParams(linearLayName);

            LinearLayout.LayoutParams linearLayNamebtn = new LinearLayout.LayoutParams(720,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayNamebtn.leftMargin = 30;
            linearLayNamebtn.topMargin = 20;
            binding.btnAddGeneralSettings.setLayoutParams(linearLayNamebtn);

            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.Laybtnsave.setLayoutParams(linearOverAllParams);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("General Settings");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.choosePhotoId.setOnClickListener(this);
        binding.takeCameraId.setOnClickListener(this);
        binding.btnAddGeneralSettings.setOnClickListener(this);

        getGeneralSetings();
    }

    private void getGeneralSetings() {
        Cursor c = DBFunc.Query("SELECT Logo,Name,Password,Address from General_Settings", true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
//                    byte[] decodedString = Base64.decode(c.getString(0), Base64.DEFAULT);
//                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                    img_choose_folder.setImageBitmap(decodedByte);
                    String img = c.getString(0);
                    if (img == null || img.equals("0")){
                        Picasso.with(getApplicationContext()).load(R.drawable.myretailerlogo_ize1).into(binding.imgChooseFolder);
                    }else {
                        Picasso.with(getApplicationContext()).load(img).into(binding.imgChooseFolder);
                    }
                    binding.editTextName.setText(c.getString(1));
                    binding.editTextPassword.setText(c.getString(2));
                    binding.editTextAddress.setText(c.getString(3));
                }
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_photo_id:
                pickFromGallery();
                break;
            case R.id.take_camera_id:
                captureFromCamera();
                break;
            case R.id.btn_add_general_settings:
                  saveGeneralSettings();
                break;
        }
    }

    private void saveGeneralSettings() {
        String str_img = "";
        if (path_.length() > 0) {
//            str_img = Query.getImageUrl(getApplicationContext(), binding.imgChooseFolder);
            str_img = String.valueOf(Uri.parse(path_));
        }
        DBFunc.ExecQuery("DELETE FROM General_Settings", true);

        String sql = "INSERT INTO General_Settings (Logo,Name,Password,Address,DateTime) " +
                "VALUES (" +
                "'" + DBFunc.PurifyString(str_img) + "'," +
                "'" + DBFunc.PurifyString(binding.editTextName.getText().toString()) + "'," +
                "'" + DBFunc.PurifyString(binding.editTextPassword.getText().toString()) + "'," +
                "'" + DBFunc.PurifyString(binding.editTextAddress.getText().toString()) + "'," +
                "" + System.currentTimeMillis() + ")";

        DBFunc.ExecQuery(sql, true);
        Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
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
    @SuppressLint("MissingSuperCall")
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
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
        //ActivityCompat.finishAffinity(GeneralSettingActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
