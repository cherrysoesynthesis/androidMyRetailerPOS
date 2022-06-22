package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityManualFtpmallInterfaceBinding;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ManualFTPMallInterfaceActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityManualFtpmallInterfaceBinding binding;
    FTPFileRecyclerViewAdapter myAdapter;
    List<String> idLst = new ArrayList<String>();
    List<String> chkLst = new ArrayList<String>();
    List<String> dateLst = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_manual_ftpmall_interface);

        setSupportActionBar(binding.myToolbar);

//        binding.myToolbar.setTitle(Constraints.MallFTPMallInterface);
//        binding.myToolbar.setTitle("Mall FTP Mall Interface");
        binding.myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        binding.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getAllFTPDatetoSend(fdate,tdate);


        myAdapter = new FTPFileRecyclerViewAdapter(getSupportFragmentManager(),idLst,chkLst,dateLst);
        binding.RecyclerView.setAdapter(myAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.RecyclerView.setLayoutManager(layoutManager);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfYMD = new SimpleDateFormat("yyyyMMdd");
        String formatted = df.format(new Date());
        String formatteddfYMD = dfYMD.format(new Date());
        fdate = formatteddfYMD;
        tdate = formatteddfYMD;
        binding.fromDate.setText(formatted);
        binding.toDate.setText(formatted);

        binding.fromDate.setOnClickListener(this);
        binding.toDate.setOnClickListener(this);
        binding.manualFTPSend.setOnClickListener(this);
        binding.generateFile.setOnClickListener(this);
        binding.searchBtn.setOnClickListener(this);
    }

    private void getAllFTPDatetoSend(String fdate, String tdate) {
//        if (fdate == null || fdate.equals("")){
//            fdate = new SimpleDateFormat(Constraints.dateDMY_FTP).format(new Date());
//            binding.fromDate.setText(fdate);
//        }
//        if (tdate == null || tdate.equals("")){
//            tdate = new SimpleDateFormat(Constraints.dateDMY_FTP).format(new Date());
//            binding.toDate.setText(tdate);
//        }
        String query = "SELECT salesdtstr,status,ID FROM Schedular ";
        query += " WHERE strftime('%Y%m%d', salesdt / 1000 + (3600*8), 'unixepoch') " +
//        query += " WHERE strftime('%d%m%Y', salesdt / 1000 + (3600*8), 'unixepoch') " +
                " BETWEEN '"+fdate+"' AND '"+tdate+"'"+
                " GROUP BY strftime('%Y%m%d', salesdt / 1000 + (3600*8), 'unixepoch')";
        Log.i("Sdf__query","query____"+query);
        Cursor c = DBFunc.Query(query,false);
        String dt = "";
        String status = "";
        String id = "";
        if (c != null) {
            idLst.clear();
            chkLst.clear();
            dateLst.clear();
            while (c.moveToNext()){
                dt = c.getString(0);
                status = c.getString(1);
                id = c.getString(2);
                chkLst.add(status);
                dateLst.add(dt);
                idLst.add(id);
            }
            c.close();
        }

        myAdapter = new FTPFileRecyclerViewAdapter(getSupportFragmentManager(),idLst,chkLst,dateLst);
        binding.RecyclerView.setAdapter(myAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.RecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SettingActivity.class);
        startActivity(i);
        finish();
    }
    String fdate = "";
    String tdate = "";
    public static List<String> listIdValue = new ArrayList<String>();
    public static List<String> listDateValue = new ArrayList<String>();
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fromDate:
                fromDateSelectFun();
                break;
            case R.id.toDate:
                toDateSelectFun();
                break;
            case R.id.searchBtn:
                 getAllFTPDatetoSend(fdate,tdate);
//                 getAllFTPDatetoSend(binding.fromDate.getText().toString(),binding.toDate.getText().toString());
                break;
            case R.id.generateFile:
//                SendSavedFileToFTP(listIdValue);
                try {
                    MallInterfaceActivity.doFTPSync(this, "",fdate,tdate,"generate");
                } catch (Exception e){
                    Log.i("ftpexception_d_","exception___"+e.getMessage());
                }
                break;
            case R.id.manualFTPSend:
//                SendSavedFileToFTP(listIdValue);
                SendSavedFileToFTP(this,listDateValue);
                break;
        }
    }

    private void fromDateSelectFun() {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int datePickerThemeResId = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            datePickerThemeResId = R.style.my_dialog_theme;
        }

//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog mDatePicker = new DatePickerDialog(this, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
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
                Log.i("Sfd__selected_from_date","selected_from_date____"+selected_from_date);
//                sendFTPToDateDialogFun(selectedyear,month,day);

                    fdate = selectedyear+month+day;
                    binding.fromDate.setText(selected_from_date);
            }
        },mYear, mMonth, mDay);
        mDatePicker.show();
    }

//    void sendFTPToDateDialogFun(final int fselectedyear, final String fmonth, final String fday) {
    void toDateSelectFun() {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int datePickerThemeResId = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            datePickerThemeResId = R.style.my_dialog_theme;
        }
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog mDatePicker = new DatePickerDialog(this, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
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
//                //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
//                String fromdate = fselectedyear + "-" + fmonth + "-" + fday;
                String todate = selectedyear + "-" + month + "-" + day;
//
//                MallInterfaceActivity.dateValue = fromdate + "-" + todate;
//                MallInterfaceActivity.fromDateValue = fselectedyear+fmonth+fday;
//                MallInterfaceActivity.toDateValue = selectedyear+month+day;

                binding.toDate.setText(todate);
//                Log.i("Sfd__selected_from_date","selefromdate____"+fromdate);
//                Log.i("Sfd__selected_from_date","seletodatee____"+todate);
//                String fdate = fselectedyear+fmonth+fday;
//                String tdate = selectedyear+month+day;
//                SendSavedFileToFTP(fdate,tdate);
                tdate = selectedyear+month+day;
            }
        },mYear, mMonth, mDay);
        mDatePicker.show();
    }

//    public static void SendSavedFileToFTPByDate(String fdate, String tdate) {
//        String type = Query.findfieldNameByTableName("type","FTPSync", true);
//        String sql = "SELECT uuid,filePath,fileName,setTime,status,DateTime " +
//                "FROM Schedular WHERE " +
//                "  strftime('%Y%m%d', salesdt / 1000 + (3600*8), 'unixepoch') " +
//                "BETWEEN '"+fdate+"' AND '"+tdate+"' ";
//
////        (status IS NULL OR status == NULL) AND
////                " fileFormat,ftptype,mallcode,machineID,DateTime FROM FTPSync WHERE UUID = '"+uuid+"' ";
//        Log.i("SAVESchedular_","Schedular_"+sql);
//
//        Cursor c = DBFunc.Query(sql, false);
//
//        if (c != null) {
//            while (c.moveToNext()) {
//                String uuid = c.getString(0);
//                String filePath = c.getString(1);
//                String fileName = c.getString(2);
//                String setTime = c.getString(3);
//                String status = c.getString(4);
//                long dt = c.getLong(5);
//                File file = new File(filePath);
//                Log.i("Sdf___","Dfile__"+file);
//                Log.i("Sdf___","DffileName_"+fileName);
//                Log.i("Sdf___","type_"+type);
//                if (type.equals(Constraints.FTP_SELECTED_FTP)) {
//                    new FTPInterface(file, fileName);
//                } else {
//                    new SFTPInterface(file, fileName);
//                }
//            }
//            c.close();
//        }
//    }

    public static void SendSavedFileToFTP(Context context, List<String> idlist) {
        Log.i("idlist____","idlist_"+idlist);
        for (int i = 0 ; i < idlist.size(); i++) {

            String type = Query.findfieldNameByTableName("type","FTPSync", true);
            String sql = "SELECT uuid,filePath,fileName,setTime,status,DateTime " +
                    "FROM Schedular WHERE " +
//                    " ID = "+idlist.get(i);
//                 "  strftime('%Y%m%d', salesdt / 1000 + (3600*8), 'unixepoch') " +
                 "  salesdtstr = '"+idlist.get(i)+"' " ;
//                "BETWEEN '"+fdate+"' AND '"+tdate+"' ";

//        (status IS NULL OR status == NULL) AND
//                " fileFormat,ftptype,mallcode,machineID,DateTime FROM FTPSync WHERE UUID = '"+uuid+"' ";
            Log.i("SAVESchedular_","Schedular_"+sql);

            Cursor c = DBFunc.Query(sql, false);

            if (c != null) {
                while (c.moveToNext()) {
                    String uuid = c.getString(0);
                    String filePath = c.getString(1);
                    String fileName = c.getString(2);
                    String setTime = c.getString(3);
                    String status = c.getString(4);
                    long dt = c.getLong(5);
                    File file = new File(filePath);
//                    File file = new File(fileName);
//                    File file = new File("/storage/emulated/0/2022_05_21/D674838.009.txt");
                    Log.i("TESTING__________d","ManualFTPMallinterface__"+file
                            +"\n_fileName_"+fileName
                            +"\n_type_"+type);
                    if (type.equals(Constraints.FTP_SELECTED_FTP)) {
                        //new FTPInterfaceTestConnection(context);
                        new FTPInterface(context,file, fileName,"send");
//                        new FTPInterface(file, "tst");
                    } else {
                        //ew SFTPInterface(file, fileName);
                        new SFTPInterface(context,file, fileName,"send");
                        //new SFTPInterfaceTestConnection(context);
                    }
                }
                c.close();
            }
        }
    }
}