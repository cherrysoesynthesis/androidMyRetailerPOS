package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityFtpmallInterfaceBinding;

import java.util.ArrayList;
import java.util.List;

public class FTPMallInterfaceActivity extends AppCompatActivity implements View.OnClickListener {
    public static ActivityFtpmallInterfaceBinding binding;

    public static String ftpTypeSelectedValue = "0";
    public static String fileFormatSelectedValue = "0";
    String imageHideShowVar = "1";
    Drawable visibleImg;
    Drawable inVisibleImg;
    public static String ipValue = "";
    public static String typeValue = "";
    public static String portNoValue = "";
    public static String userValue = "";
    public static String passwordValue = "";
    public static String formatValue = "";
    public static String mallCodeValue = "";
    public static String machineIdValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ftpmall_interface);

        visibleImg = getResources().getDrawable(R.drawable.baseline_visibility_black_24);
        inVisibleImg = getResources().getDrawable(R.drawable.baseline_visibility_off_black_24);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        myToolbar.setTitle("FTP Mall Management");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.editTextMallPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.editTextMallPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        binding.btnSaveFTP.setOnClickListener(this);
        binding.dropDownFTPType.setOnClickListener(this);
        binding.dropDownFTPFileFormat.setOnClickListener(this);
        binding.btnTestConnection.setOnClickListener(this);
        binding.imagVisibleInvisible.setOnClickListener(this);

        ftpTypeSelectedValue = "0";
        fileFormatSelectedValue = "0";
        doFun();
    }

    public static void doFun() {
        Log.i("selectedNamipValuee_doFun_", ipValue);
        FTPSync ftpSync = Query.getFTPSync();
        binding.editTextMallFTPIP.setText(funchecking(ipValue,ftpSync.getIp()));
        if (ftpTypeSelectedValue != null && ftpTypeSelectedValue.length() > 0  && !ftpTypeSelectedValue.equals("0")){
            if (ftpTypeSelectedValue.equals("1")){
                binding.editTextFTPType.setText(Constraints.FTP_SELECTED_FTP);
            }else {
                binding.editTextFTPType.setText(Constraints.FTP_SELECTED_SFTP);
            }
        }else {

            binding.editTextFTPType.setText(ftpSync.getType());
        }

        binding.editTextMallPortNo.setText(funchecking(portNoValue,ftpSync.getPort()));
        binding.editTextMallUsername.setText(funchecking(userValue,ftpSync.getUser()));
        binding.editTextMallPassword.setText(funchecking(passwordValue,ftpSync.getPassword()));

        if (fileFormatSelectedValue != null && fileFormatSelectedValue.length() > 0 && !fileFormatSelectedValue.equals("0")){
            if (fileFormatSelectedValue.equals("1")){
                binding.editTextFTPFileFormat.setText("1");
            }else {
                binding.editTextFTPFileFormat.setText("24");
            }
        }else {

            binding.editTextFTPFileFormat.setText(ftpSync.getFileFormat());
        }
        binding.editTextMallCode.setText(funchecking(mallCodeValue,ftpSync.getMallcode()));
        binding.editTextMallMachineId.setText(funchecking(machineIdValue,ftpSync.getMachineID()));

    }

    private static String funchecking(String typeValue, String existingValue) {
        String val = "";
        if (typeValue != null && !(typeValue.equals("null")) && typeValue.length() > 0){
            Log.i("chk___ing___","typValif___"+typeValue+"_"+existingValue);
            //ftpSyncList.add(typeValue);
            val = typeValue;
        }else {
            Log.i("chk___ing___","typValelse___"+typeValue+"_"+existingValue);
            //ftpSyncList.add(existingValue);
            val = existingValue;
        }
        //return ftpSyncList;
        return val;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(FTPMallInterfaceActivity.this, SettingActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagVisibleInvisible:

                if (imageHideShowVar.equals("1")){
                    binding.editTextMallPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    binding.imagVisibleInvisible.setImageDrawable(inVisibleImg);

                    binding.imagVisibleInvisible.setImageResource(R.drawable.baseline_visibility_off_black_24);
                    imageHideShowVar = "2";
                }else {
//                    binding.editTextMallPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.editTextMallPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.editTextMallPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.editTextMallPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.imagVisibleInvisible.setImageDrawable(visibleImg);
                    binding.imagVisibleInvisible.setImageResource(R.drawable.baseline_visibility_black_24);
                    imageHideShowVar = "1";
                }

                break;
            case R.id.dropDownFTPType:
                FTPTypeSheetFragment.ipValue = binding.editTextMallFTPIP.getText().toString();
                FTPTypeSheetFragment.typeValue = binding.editTextFTPType.getText().toString();
                FTPTypeSheetFragment.portNoValue = binding.editTextMallPortNo.getText().toString();
                FTPTypeSheetFragment.userValue = binding.editTextMallUsername.getText().toString();
                FTPTypeSheetFragment.passwordValue = binding.editTextMallPassword.getText().toString();
                FTPTypeSheetFragment.formatValue = binding.editTextFTPFileFormat.getText().toString();
                FTPTypeSheetFragment.mallCodeValue = binding.editTextMallCode.getText().toString();
                FTPTypeSheetFragment.machineIdValue = binding.editTextMallMachineId.getText().toString();
                Log.i("Sdf___","Dfdfdf___"+FTPTypeSheetFragment.ipValue);
                FTPTypeSheetFragment pcSSheetFragment = new FTPTypeSheetFragment();
                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                break;
            case R.id.dropDownFTPFileFormat:
                FTPFormatTypeSheetFragment.ipValue = ipValue;
                FTPFormatTypeSheetFragment.typeValue = typeValue;
                FTPFormatTypeSheetFragment.portNoValue = portNoValue;
                FTPFormatTypeSheetFragment.userValue = userValue;
                FTPFormatTypeSheetFragment.passwordValue = passwordValue;
                FTPFormatTypeSheetFragment.formatValue = formatValue;
                FTPFormatTypeSheetFragment.mallCodeValue = mallCodeValue;
                FTPFormatTypeSheetFragment.machineIdValue = machineIdValue;
                FTPFormatTypeSheetFragment pcSSheetFragment1 = new FTPFormatTypeSheetFragment();
                pcSSheetFragment1.show(getSupportFragmentManager(), pcSSheetFragment1.getTag());

                break;
            case R.id.btnSaveFTP:
                saveFtp();
                Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, FTPMallInterfaceActivity.class);
                startActivity(i);
                finish();
            case R.id.btnTestConnection:
                Log.i("testConnn___","tec__"+"here");
                try {
                    String type = Query.findfieldNameByTableName("ftptype","FTPSync", true);
                    Log.i("testCtype__","type___"+type);
                    if (type.equals(Constraints.FTP_SELECTED_FTP)) {
                        new FTPInterfaceTestConnection(this);
                    } else {
                        new SFTPInterfaceTestConnection(this);
                    }
//                    /new FTPInterfaceTestConnection(this);
//                    Log.i("testConnn___","tec__"+"heretry");
                } catch (Exception e){

                    Log.i("testConExceptionnn___","tec__"+"heree_"+e.getMessage());
                }
            break;
        }
    }

    private void saveFtp() {
        FTPSync ftpSync = new FTPSync();
        String ip = binding.editTextMallFTPIP.getText().toString();
        String type = binding.editTextFTPType.getText().toString();
        String port = binding.editTextMallPortNo.getText().toString();
        String user = binding.editTextMallUsername.getText().toString();
        String password = binding.editTextMallPassword.getText().toString();
        String fileFormat = binding.editTextFTPFileFormat.getText().toString();
        //String ftptype = "";
        String mallcode = binding.editTextMallCode.getText().toString();
        String machineID = binding.editTextMallMachineId.getText().toString();
        ftpSync.setIp(ip);
        ftpSync.setType(type);
        ftpSync.setPort(port);
        ftpSync.setUser(user);
        ftpSync.setPassword(password);
        ftpSync.setFileFormat(fileFormat);
        ftpSync.setFtptype(type);
        ftpSync.setMallcode(mallcode);
        ftpSync.setMachineID(machineID);
//        ftpSync.setHours(fromDateValue);
//        ftpSync.setMinutes(toDateValue);
        ftpSync.setHours("");
        ftpSync.setMinutes("");
        ftpSync.setSeconds("00");
        try {
            Query.saveFTPSync(this, ftpSync);


        } catch (Exception e){
            Log.i("ExceptionSaveFTP_","exception__"+e.getMessage());
        }
    }
}