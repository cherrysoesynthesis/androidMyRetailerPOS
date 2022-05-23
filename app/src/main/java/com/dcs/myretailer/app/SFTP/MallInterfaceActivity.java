package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dcs.myretailer.app.Activity.AddNewProductActivity;
import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.Activity.SyncActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.ProductManagementActivity;
import com.dcs.myretailer.app.databinding.ActivityMallInterfaceBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MallInterfaceActivity extends AppCompatActivity {
    public static ActivityMallInterfaceBinding binding;
    public static String ipValue = "";
    public static String typeValue = "";
    public static String portNoValue = "";
    public static String userValue = "";
    public static String passwordValue = "";
    public static String formatValue = "";
    public static String mallCodeValue = "";
    public static String machineIdValue = "";
    public static String dateValue = "";
    public static String fromDateValue = "";
    public static String toDateValue = "";
    public static String ftpTypeSelectedValue = "0";
    public static String fileFormatSelectedValue = "0";
    public static String redColor = "0";
    static MallInterfaceRecyclerViewAdapter myAdapter;
    public static MallInterfaceActivity act;
    public static InputMethodManager inputMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mall_interface);

        act = MallInterfaceActivity.this;

        inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

//        setSupportActionBar(binding.myToolbar);

        setSupportActionBar(binding.myToolbar);

//        binding.myToolbar.setTitle(Constraints.FTPSyncManagement);
        binding.myToolbar.setTitle("FTP Mall Management");
        binding.myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        binding.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int widthSZ = Query.screenSize(this,"W");
        int heightSZ = Query.screenSize(this,"H");
//        ConstraintLayout.LayoutParams paramsHeaderDescription = new ConstraintLayout.LayoutParams(widthSZ,
//                (int) ((heightSZ/5)*4.3));
//        ConstraintLayout.LayoutParams paramsHeaderDescription = new ConstraintLayout.LayoutParams(widthSZ,
//                 heightSZ);
//        FrameLayout.LayoutParams paramsHeaderDescription = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                 ViewGroup.LayoutParams.WRAP_CONTENT);
//        binding.constraintLayoutOverall.setLayoutParams(paramsHeaderDescription);
//
////        ConstraintLayout.LayoutParams paramsHeaderDescription1 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
////                 ViewGroup.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams paramsHeaderDescription1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                 ViewGroup.LayoutParams.WRAP_CONTENT);
//        paramsHeaderDescription1.topMargin = 100;
//        binding.recyclerviewId.setLayoutParams(paramsHeaderDescription1);
////        binding.constraintLayout.setBackgroundColor(getResources().getColor(R.color.color_red));
////
//        ConstraintLayout.LayoutParams paramsHeaderDescription2 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                 ViewGroup.LayoutParams.WRAP_CONTENT);
//        paramsHeaderDescription2.topMargin = 300;
//        binding.constraintLayoutBottom.setLayoutParams(paramsHeaderDescription2);
//        binding.constraintLayout.setBackgroundColor(getResources().getColor(R.color.color_red));

//        ConstraintLayout.LayoutParams paramsHeaderDescription3 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                 ViewGroup.LayoutParams.WRAP_CONTENT);
//        paramsHeaderDescription2.topMargin = 100;
//        binding.saveFtpSync.setLayoutParams(paramsHeaderDescription3);

//        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.bottomMargin = 10;
//        binding.ScrollView01.setLayoutParams(params);

        //List<FTP> ftpList = new ArrayList<>();

        doFun(getApplicationContext(),getSupportFragmentManager());


//        binding.saveFtpSync.setOnClickListener(this);
//        binding.cancelFtpSync.setOnClickListener(this);
//
//        myAdapter = new MallInterfaceRecyclerViewAdapter(context, lstProductData, "product");
//        binding.recyclerviewId.setAdapter(myAdapter);

//        binding.ftpSyncBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_mall_interface_actions, menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_mall_interface_settings) {
            try {
//                if (redColor.equals("1")){
//                    Toast.makeText(this, "Save Failed!", Toast.LENGTH_SHORT).show();
//                } else {
                    saveFtp();
                    Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, MallInterfaceActivity.class);
                    startActivity(i);
                    finish();
//                }
            }catch (Exception e){
                Log.i("Exception_","e_"+e.getMessage());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save_mall_interface_settings).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }


    public static void doFun(Context context, FragmentManager supportFragmentManager) {
        Log.i("doFun___","dateValue____"+dateValue);
        Log.i("doFun__","fromDateValue____"+fromDateValue);
        Log.i("doFun___","ftoDateValue____"+toDateValue);
        List<String> ftpList = new ArrayList<>();

        FTPSync ftpSync = Query.getFTPSync();
        List<String> ftpSyncList = new ArrayList<>();
        List<String> ftpDropdownList = new ArrayList<>();

        List<String> val = new ArrayList<>();
        val.add(Constraints.FTP_IP);
        ftpList.add(Constraints.FTP_IP);
        val.add(Constraints.FTP_TYPE);
        ftpList.add(Constraints.FTP_TYPE);
        val.add(Constraints.FTP_PortNo);
        ftpList.add(Constraints.FTP_PortNo);
        val.add(Constraints.FTP_User);
        ftpList.add(Constraints.FTP_User);
        val.add(Constraints.FTP_Password);
        ftpList.add(Constraints.FTP_Password);
        val.add(Constraints.FTP_FileFormat);
        ftpList.add(Constraints.FTP_FileFormat);
        val.add(Constraints.FTP_MallCode);
        ftpList.add(Constraints.FTP_MallCode);
        val.add(Constraints.FTP_MachineId);
        ftpList.add(Constraints.FTP_MachineId);
        val.add(Constraints.FTP_Time);
        ftpList.add(Constraints.FTP_Time);

        Log.i("Sdfdsf___","Dfd__"+ipValue+"______"+ftpSync.getIp());
        Log.i("Sdfdsf___","DfdftpList__"+ftpList+"______"+val.size());
        for (int i = 0 ; i < val.size(); i++){
            //FTP ftp = new FTP();
            //ftp.setTextView(val.get(i));
            //ftp.setEditView(String.valueOf(R.string.ftpSyncFTPIP));
            //ftpList.add(ftp);
            //ftpList.add(val.get(i));

            if (i == 0){
                ftpSyncList.add(chkEditFieldValue(ipValue,ftpSync.getIp()));
                ftpDropdownList.add("0");
            }else if (i == 1){
                if (ftpTypeSelectedValue.equals("1")){
                    ftpSyncList.add("FTP");
                } else  if (ftpTypeSelectedValue.equals("2")){
                    ftpSyncList.add("SFTP");
                } else {
                    ftpSyncList.add(ftpSync.getType());
                }
                ftpDropdownList.add("1");
            }else if (i == 2){
                ftpSyncList.add(chkEditFieldValue(portNoValue,ftpSync.getPort()));
                ftpDropdownList.add("0");
            }else if (i == 3){
                ftpSyncList.add(chkEditFieldValue(userValue,ftpSync.getUser()));
                ftpDropdownList.add("0");
            }else if (i == 4){
                ftpSyncList.add(chkEditFieldValue(passwordValue,ftpSync.getPassword()));
                ftpDropdownList.add("0");
            }else if (i == 5){
                if (fileFormatSelectedValue.equals("1")){
                    ftpSyncList.add("1");
                } else  if (fileFormatSelectedValue.equals("2")){
                    ftpSyncList.add("24");
                } else {
                    ftpSyncList.add(ftpSync.getFileFormat());
                }
                ftpDropdownList.add("1");
            }else if (i == 6){
                ftpSyncList.add(chkEditFieldValue(mallCodeValue,ftpSync.getMallcode()));
                ftpDropdownList.add("0");
            }else if (i == 7){
                ftpSyncList.add(chkEditFieldValue(machineIdValue,ftpSync.getMachineID()));
                ftpDropdownList.add("0");
            }else{
                Log.i("DSf__","typeValue___"+dateValue);
                Log.i("DSf__","fromDateValue___"+fromDateValue);
                Log.i("DSf__","toDateValue___"+toDateValue);
                String vale = Constraints.FTP_SELECT_TIME;
                if (ftpSync.getHours() != null &&  ftpSync.getHours() != null && ftpSync.getHours() != "null"
                 && ftpSync.getHours().length() > 0){
                    vale =ftpSync.getHours() +"-"+ftpSync.getMinutes() ;
                }
                Log.i("DSf__","toDvale___"+vale);
                ftpSyncList.add(chkEditFieldValue(dateValue,vale));
                ftpDropdownList.add("1");
            }
        }
        myAdapter = new MallInterfaceRecyclerViewAdapter(act, supportFragmentManager, inputMgr,
                ftpList , ftpSyncList,ftpDropdownList);
        binding.recyclerviewId.setAdapter(myAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerviewId.setLayoutManager(layoutManager);
    }

    private static String chkEditFieldValue(String typeValue,String existingValue) {
        String val = "";
        List<String> ftpSyncList = new ArrayList<>();
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

    public static void doFTPSync(Context context,String billno,String fromDate,String toDate) {
        String ftpSyncMallInterface = Query.GetOptions(30);
        Log.i("Sdfdsf___","SDfdsf___"+ftpSyncMallInterface);
        if (ftpSyncMallInterface.equals("1")) {
            new FTPFileCreate(context, billno, fromDate, toDate);
        }
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.ftp_sync_btn:
//                Log.i("Sftp_sync_btn__","ftpsyncbtn"+fromDateValue+"-"+toDateValue);
//                    try {
//                        doFTPSync(act, "",fromDateValue,toDateValue);
//                    } catch (Exception e){
//                        Log.i("ftpexception_d_","exception___"+e.getMessage());
//                    }
//                break;
//            case R.id.saveFtpSync:
//                try {
//                    if (redColor.equals("1")){
//                        Toast.makeText(this, "Save Failed!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        saveFtp();
//                        Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(this, MallInterfaceActivity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                }catch (Exception e){
//                    Log.i("Exception_","e_"+e.getMessage());
//                }
//                break;
//            case R.id.cancelFtpSync:
//                onBackPressed();
////                String del = "DELETE FROM FTPSync";
////                DBFunc.ExecQuery(del,true);
//                break;
//        }
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        doFun(act,getSupportFragmentManager());
//    }

    private void saveFtp() {
        FTPSync ftpSync = new FTPSync();
        String ip = ipValue;
        String type = typeValue;
        String port = portNoValue;
        String user = userValue;
        String password = passwordValue;
        String fileFormat = formatValue;
        String ftptype = "";
        String mallcode = mallCodeValue;
        String machineID = machineIdValue;
        ftpSync.setIp(ip);
        ftpSync.setType(type);
        ftpSync.setPort(port);
        ftpSync.setUser(user);
        ftpSync.setPassword(password);
        ftpSync.setFileFormat(fileFormat);
        ftpSync.setFtptype(ftptype);
        ftpSync.setMallcode(mallcode);
        ftpSync.setMachineID(machineID);
        ftpSync.setHours(fromDateValue);
        ftpSync.setMinutes(toDateValue);
        ftpSync.setSeconds("00");
        try {
            Query.saveFTPSync(this, ftpSync);


        } catch (Exception e){
            Log.i("ExceptionSaveFTP_","exception__"+e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MallInterfaceActivity.this, SettingActivity.class);
        startActivity(i);
        finish();
    }
}