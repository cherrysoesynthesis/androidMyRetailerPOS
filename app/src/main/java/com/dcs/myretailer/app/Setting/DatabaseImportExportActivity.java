package com.dcs.myretailer.app.Setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FileBrowser;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.StrTextConst.TextType;
import com.dcs.myretailer.app.databinding.ActivityDatabaseImportExportBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//import com.filebrowser.FileBrowser;

public class DatabaseImportExportActivity extends AppCompatActivity {
    private static final String SAMPLE_DB_NAME = "TEXT";
    static Activity CurrentActivity;
    Context context;
    ActivityDatabaseImportExportBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_database_import_export);

        context = DatabaseImportExportActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Database Import/Export");

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            linearOverAllParams.leftMargin = 30;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }else if (terminalTypeVal.equals(Constraints.PAX)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.gravity = Gravity.CENTER;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        master_db_size = (TextView) findViewById(R.id.master_db_size);
//        master_db_size.setTextSize(MainActivity.billFontSize);
//        master_txt_header = (TextView) findViewById(R.id.txt_mas_header);
//        master_txt_header.setTextSize(MainActivity.billFontSize);
//        transaction_db_size = (TextView) findViewById(R.id.transaction_db_size);
//        transaction_db_size.setTextSize(MainActivity.billFontSize);
//        transaction_txt_header = (TextView) findViewById(R.id.transaction_txt_header);
//        transaction_txt_header.setTextSize(MainActivity.billFontSize);

        ListView simpleList,transactionSimpleList;
        String countryList[] = {"Import", "Export", "Repair", "Reset"};
        int flags[] = {R.drawable.ic_import, R.drawable.ic_export_export, R.drawable.ic_repair, R.drawable.ic_reset};

        simpleList = (ListView) findViewById(R.id.master_list);
        //SettingAdapter settingAdapter = new SettingAdapter(DatabaseImportExportActivity.this, countryList, flags);
        MasterDatabaseAdapter masterDatabaseAdapter = new MasterDatabaseAdapter(DatabaseImportExportActivity.this, countryList, flags);
        simpleList.setAdapter(masterDatabaseAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

//                Toast.makeText(DatabaseImportExportActivity.this,
//                        "You click on position:"+position, Toast.LENGTH_SHORT).show();
                if (position == 0){
                    final Context context = view.getContext();
                    FileBrowser fb = new FileBrowser(context);

                    fb.setDefaultDir(Environment.getExternalStorageDirectory()+"/posdata/");
                    fb.setTitle(StrTextConst.GetText(TextType.CFGDB, 2));
                    try {
                        fb.SetFileExtension("SQLite Database(*.sqlite;*.db;*.sqlite3;*.db3)|*.sqlite;*.db;*.sqlite3;*.db3|All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
                            @Override
                            public void onSelected(View v, File file) {
                                final File dbfile = file;
                                final Context context = v.getContext();
                                final DialogBox dlg = new DialogBox(context);
                                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                                dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 6));
                                dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 11));


                                dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 6), new View.OnClickListener(){

                                    @Override
                                    public void onClick(View v) {
                                        dlg.dismiss();
                                        if(dbfile.exists() && dbfile.canRead()){
                                            //new CheckRepair(context,dbfile.getAbsolutePath()).execute(true,false);
                                            new CheckRepair(DatabaseImportExportActivity.this,dbfile.getAbsolutePath()).execute(true,false);

                                        }else{
                                            DialogBox dlg = new DialogBox(v.getContext());
                                            dlg.setDialogIconType(DialogBox.IconType.ERROR);
                                            dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                                            dlg.setMessage(StrTextConst.GetText(TextType.GENERAL, 19));
                                            dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                            dlg.show();
                                        }
                                    }
                                });

                                dlg.setButton2OnClickListener(StrTextConst.GetText(TextType.GENERAL, 7), null);
                                dlg.show();


                            }
                        });
                        fb.ShowOpenDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityDatabase",e.toString());
                    }

//                    //final Context context = getApplicationContext();
//                    //FileBrowser fb = new FileBrowser(context);
//                    FileBrowser fb = new FileBrowser(DatabaseImportExportActivity.this);
//
//                    fb.setDefaultDir(Environment.getExternalStorageDirectory()+"/posdata/");
//                    fb.setTitle("DFSFDSFADF");
//                    Log.i("FDSFDSFDSF","one");
////                    try {
//                        fb.SetFileExtension("SQLite Database(*.sqlite;*.db;*.sqlite3;*.db3)|*.sqlite;*.db;*.sqlite3;*.db3|All Files(*.*)|*.*");
//                        Log.i("FDSFDSFDSF","one1");
//                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
//                            @Override
//                            public void onSelected(View v, File file) {
//                                final File dbfile = file;
//                                Log.i("FDSFDSFDSF","one2"+file);
//                                final Context context = v.getContext();
//                                final DialogBox dlg = new DialogBox(context);
//                                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
//                                dlg.setTitle("DSFSFDSAF");
//                                dlg.setMessage("EWResfds");
//
//                                Log.i("FDSFDSFDSF","one3");
////                                dlg.setButton1OnClickListener("RERDSFDSF", new View.OnClickListener(){
////
////                                    @Override
////                                    public void onClick(View v) {
////                                        dlg.dismiss();
////                                        if(dbfile.exists() && dbfile.canRead()){
////                                            new CheckRepair(context,dbfile.getAbsolutePath()).execute(true,false);
////
////                                        }else{
////                                            DialogBox dlg = new DialogBox(v.getContext());
////                                            dlg.setDialogIconType(DialogBox.IconType.ERROR);
////                                            dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
////                                            dlg.setMessage(StrTextConst.GetText(TextType.GENERAL, 19));
////                                            dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
////                                            dlg.show();
////                                        }
////                                    }
////                                });
//                                Log.i("FDSFDSFDSF","one4");
//                                dlg.setButton2OnClickListener("RESFDSFD", null);
//                                dlg.show();
//                                Log.i("FDSFDSFDSF","one5");
//
//
//                            }
//                        });
//                        fb.ShowOpenDialog();
////                    } catch (Exception e) {
////                        Logger.WriteLog("ActivityDatabase",e.toString());
////                        Log.i("FDSFDSFDSF","one6"+e.getMessage());
////                        Log.i("FDSFDSFDSF","one7"+e.toString());
////                    }
//                    //writeFileExternalStorage();
//                    //exportDB();
////                    btn_master_import.setOnClickLismatener(new View.OnClickListener(){
////                        @Override
////                        public void onClick(View v){
//                            //final Context context = getApplicationContext();
////                            FileBrowser fb = new FileBrowser(context);
////
////                            fb.setDefaultDir(Environment.getExternalStorageDirectory()+"/posdata/");
////                            fb.setTitle("TEXT");
////                            try {
////                                fb.SetFileExtension("SQLite Database(*.sqlite;*.db;*.sqlite3;*.db3)|*.sqlite;*.db;*.sqlite3;*.db3|All Files(*.*)|*.*");
////                                fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
////                                    @Override
////                                    public void onSelected(View v, File file) {
////                                        final File dbfile = file;
////                                        final Context context = v.getContext();
////                                        final DialogBox dlg = new DialogBox(getApplicationContext());
////                                        dlg.setTitle("TEXT2");
////                                        dlg.setMessage("TEXT3");
////
////
////                                        dlg.setButton1OnClickListener("TEXT4", new View.OnClickListener(){
////
////                                            @Override
////                                            public void onClick(View v) {
////                                                dlg.dismiss();
////                                                if(dbfile.exists() && dbfile.canRead()){
////                                                    //new CheckRepair(context,dbfile.getAbsolutePath()).execute(true,false);
////
////                                                }else{
////                                                    DialogBox dlg = new DialogBox(v.getContext());
////                                                    dlg.setDialogIconType(DialogBox.IconType.ERROR);
////                                                    dlg.setTitle("TEXT23");
////                                                    dlg.setMessage("TEXT24");
////                                                    dlg.setButton1OnClickListener("TEXT25", null);
////                                                    dlg.show();
////                                                }
////                                            }
////                                        });
////
////                                        dlg.setButton2OnClickListener("TEXT26", null);
////                                        dlg.show();
////
////
////                                    }
////                                });
////                                fb.ShowOpenDialog();
////                            } catch (Exception e) {
////                                Logger.WriteLog("ActivityDatabase",e.toString());
////                            }
//
////                        }
////                    });
                }else if (position == 1){
                    //final Context context = getApplicationContext();
                    //FileBrowser fb = new FileBrowser(context);
                    FileBrowser fb = new FileBrowser(DatabaseImportExportActivity.this);

                    fb.setDefaultDir(Environment.getExternalStorageDirectory()+"/posdata/");
                    fb.setTitle(StrTextConst.GetText(TextType.CFGDB, 4));
                    try {
                        fb.SetFileExtension("SQLite Database(*.sqlite;*.db;*.sqlite3;*.db3)|*.sqlite;*.db;*.sqlite3;*.db3|All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
                            @Override
                            public void onSelected(View v, File file) {
                                File dbpath = getApplicationContext().getDatabasePath("master.db");

                                if(dbpath.exists() && dbpath.canRead()){
                                    try{
                                        File f = file;

                                        DBFunc.SaveDBToDisk(f.getAbsolutePath(), dbpath.getAbsolutePath());
                                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f)));

                                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Database -> Export -> Master -> "+f.getAbsolutePath());
                                        DialogBox dlg = new DialogBox(v.getContext());
                                        dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                                        dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 7));
                                        dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 13,f.getName()));
                                        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                        dlg.show();
                                    }catch(IOException e){
                                        Logger.WriteLog("ActivityDatabase",e.toString());
                                        DialogBox dlg = new DialogBox(v.getContext());
                                        dlg.setDialogIconType(DialogBox.IconType.ERROR);
                                        dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                                        dlg.setMessage(e.getMessage());
                                        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                        dlg.show();
                                    }
                                }else{
                                    DialogBox dlg = new DialogBox(v.getContext());
                                    dlg.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 12));
                                    dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                    dlg.show();
                                }
                            }
                        });
                        fb.ShowSaveDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityDatabase",e.toString());
                    }
                }else if (position == 2){
                    final Context context = DatabaseImportExportActivity.this;
                    final DialogBox dlg = new DialogBox(context);
                    dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                    dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 0));
                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 10));

                    final CheckBox chk = new CheckBox(context);
                    chk.setText(StrTextConst.GetText(TextType.CFGDB, 1));
                    dlg.addView(chk);

                    dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 6), new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            dlg.dismiss();
                            //new CheckRepair(context).execute(true,chk.isChecked());
                            new CheckRepair(DatabaseImportExportActivity.this).execute(true,chk.isChecked());
                        }
                    });

                    dlg.setButton2OnClickListener(StrTextConst.GetText(TextType.GENERAL, 7), null);
                    dlg.show();
                }else{
                    ShowResetDB(getApplicationContext(),true);
                }
            }
        });

        transactionSimpleList = (ListView) findViewById(R.id.transaction_list);
        TransactionDatabaseAdapter transactionDatabaseAdapter = new TransactionDatabaseAdapter(DatabaseImportExportActivity.this, countryList, flags);
        transactionSimpleList.setAdapter(transactionDatabaseAdapter);
        transactionSimpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

//                Toast.makeText(DatabaseImportExportActivity.this,
//                        "You click on position:"+position, Toast.LENGTH_SHORT).show();
                if (position == 0){
                    final Context context = getApplicationContext();
                    FileBrowser fb = new FileBrowser(DatabaseImportExportActivity.this);
                    fb.setDefaultDir(Environment.getExternalStorageDirectory()+"/posdata/");
                    fb.setTitle(StrTextConst.GetText(TextType.CFGDB, 3));
                    try {
                        fb.SetFileExtension("SQLite Database(*.sqlite;*.db;*.sqlite3;*.db3)|*.sqlite;*.db;*.sqlite3;*.db3|All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
                            @Override
                            public void onSelected(View v, File file) {
                                final File dbfile = file;
                                final Context context = v.getContext();
                                final DialogBox dlg = new DialogBox(context);
                                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                                dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 6));
                                dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 11));


                                dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 6), new View.OnClickListener(){

                                    @Override
                                    public void onClick(View v) {
                                        dlg.dismiss();
                                        if(dbfile.exists() && dbfile.canRead()){
                                            //new CheckRepair(context,dbfile.getAbsolutePath()).execute(false,false);
                                            new CheckRepair(DatabaseImportExportActivity.this,dbfile.getAbsolutePath()).execute(false,false);
                                        }else{
                                            DialogBox dlg = new DialogBox(v.getContext());
                                            dlg.setDialogIconType(DialogBox.IconType.ERROR);
                                            dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                                            dlg.setMessage(StrTextConst.GetText(TextType.GENERAL, 19));
                                            dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                            dlg.show();

                                        }
                                    }
                                });

                                dlg.setButton2OnClickListener(StrTextConst.GetText(TextType.GENERAL, 7), null);
                                dlg.show();

                            }
                        });
                        fb.ShowOpenDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityDatabase",e.toString());
                    }

                }else if (position == 1){
                    final Context context = getApplicationContext();
                    FileBrowser fb = new FileBrowser(DatabaseImportExportActivity.this);
                    fb.setDefaultDir(Environment.getExternalStorageDirectory()+"/posdata/");
                    fb.setTitle(StrTextConst.GetText(TextType.CFGDB, 5));
                    try {
                        fb.SetFileExtension("SQLite Database(*.sqlite;*.db;*.sqlite3;*.db3)|*.sqlite;*.db;*.sqlite3;*.db3|All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
                            @Override
                            public void onSelected(View v, File file) {
                                File dbpath = context.getDatabasePath("transact.db");
                                if(dbpath.exists() && dbpath.canRead()){
                                    try{
                                        File f = file;

                                        DBFunc.SaveDBToDisk(f.getAbsolutePath(), dbpath.getAbsolutePath());
                                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f)));

                                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Database -> Export -> Transact -> "+f.getAbsolutePath());

                                        DialogBox dlg = new DialogBox(v.getContext());
                                        dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                                        dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 7));
                                        dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 13,f.getName()));
                                        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                        dlg.show();
                                    }catch(IOException e){
                                        Logger.WriteLog("ActivityDatabase",e.toString());
                                        DialogBox dlg = new DialogBox(v.getContext());
                                        dlg.setDialogIconType(DialogBox.IconType.ERROR);
                                        dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                                        dlg.setMessage(e.getMessage());
                                        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                        dlg.show();
                                    }
                                }else{
                                    DialogBox dlg = new DialogBox(v.getContext());
                                    dlg.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 12));
                                    dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                                    dlg.show();
                                }
                            }
                        });
                        fb.ShowSaveDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityDatabase",e.toString());
                    }
                }else if (position == 2){
                    //final Context context = v.getContext();
                    final Context context = getApplicationContext();
                    final DialogBox dlg = new DialogBox(DatabaseImportExportActivity.this);
                    dlg.setDialogIconType(DialogBox.IconType.QUESTION);
                    dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 0));
                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 10));

                    //final CheckBox chk = new CheckBox(v.getContext());
                    final CheckBox chk = new CheckBox(DatabaseImportExportActivity.this);
                    chk.setText(StrTextConst.GetText(TextType.CFGDB, 1));
                    dlg.addView(chk);

                    dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 6), new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            dlg.dismiss();
                            // CheckRepair(context).execute(false,chk.isChecked());
                            new CheckRepair(DatabaseImportExportActivity.this).execute(false,chk.isChecked());
                        }
                    });

                    dlg.setButton2OnClickListener(StrTextConst.GetText(TextType.GENERAL, 7), null);
                    dlg.show();
                }else{
                    ShowResetDB(DatabaseImportExportActivity.this,false);
                }
            }
        });
    }
    void ShowResetDB(final Context c, final boolean isMaster){
        Random rand = new Random();
        final String code = String.format("%06d", rand.nextInt(999999));
        //final DialogBox dlg = new DialogBox(c);
        final DialogBox dlg = new DialogBox(DatabaseImportExportActivity.this);
        dlg.setDialogIconType(DialogBox.IconType.WARNING);
        dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 8));
        dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 14));
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            dlg.setWindowSize(1f, 0.8f);
        }else{
            dlg.setWindowSize(1f, 0.5f);
        }


        LinearLayout lay = new LinearLayout(c);
        lay.setOrientation(LinearLayout.VERTICAL);
        TextView tv = new TextView(c);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(30f);
        tv.setTypeface(Typeface.MONOSPACE);
        tv.setTextColor(ContextCompat.getColor(DatabaseImportExportActivity.this, R.color.mine_shaft));
        tv.setText(code);


        lay.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        final EditText et = new EditText(c);
        et.setGravity(Gravity.CENTER_HORIZONTAL);
        et.setLines(1);
        et.setMaxLines(1);
        et.setTextColor(ContextCompat.getColor(DatabaseImportExportActivity.this, R.color.mine_shaft));
        et.setFilters(new InputFilter[] {new InputFilter.LengthFilter(6)});
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        lay.addView(et, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        dlg.addView(lay);
        //dlgbox.setView(lay);

        dlg.setButton2OnClickListener(StrTextConst.GetText(TextType.GENERAL, 1), null);
        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 14), new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String key = et.getText().toString();
                dlg.dismiss();
                if(code.equalsIgnoreCase(key)){

                    if(DBFunc.CreateResetDB(c, isMaster)){
                        if(isMaster){
                            long size = DBFunc.GetMasterDBSize(c);
                            if(size==-1){
                                binding.masterDbSize.setText(StrTextConst.GetText(TextType.CFGDB, 21));
                                binding.masterDbSize.setTextSize(MainActivity.billFontSize);
                            }
                            binding.masterDbSize.setText(StrTextConst.GetText(TextType.CFGDB, 20, size));
                            binding.masterDbSize.setTextSize(MainActivity.billFontSize);
                        }else{
                            long size = DBFunc.GetTransactDBSize(c);
                            if(size==-1){
                                binding.transactionDbSize.setText(StrTextConst.GetText(TextType.CFGDB, 21));
                                binding.transactionDbSize.setTextSize(MainActivity.billFontSize);
                            }
                            binding.transactionDbSize.setText(StrTextConst.GetText(TextType.CFGDB, 20, size));
                            binding.transactionDbSize.setTextSize(MainActivity.billFontSize);
                        }

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Database -> Reset -> "+(isMaster?"Master":"Transact"));

                        DialogBox dlg = new DialogBox(v.getContext());
                        dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 0));
                        dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 15));
                        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                        dlg.show();
                    }else{
                        DialogBox dlg = new DialogBox(v.getContext());
                        dlg.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
                        dlg.setMessage(StrTextConst.GetText(TextType.GENERAL, 19));
                        dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                        dlg.show();

                    }
                }else{
                    DialogBox dlg = new DialogBox(v.getContext());

                    dlg.setDialogIconType(DialogBox.IconType.ERROR);
                    dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 0));
                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 16));
                    dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
                    dlg.show();
                }
            }

        });



        dlg.show();


    }

    private class CheckRepair extends AsyncTask<Object, Object, Integer> {

        Dialog dlg;
        TextView txt_stat;
        ProgressBar pb_1;
        ProgressBar pb_2;
        Context context;
        CheckRepair at;
        String importdb = "";

        public CheckRepair(Context context){
            importdb = "";
            this.context = context;
            at = this;
        }

        public CheckRepair(Context context, String dbpath){
            importdb = dbpath;
            this.context = context;
            at = this;
        }


        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            dlg = new Dialog(context);

            dlg.setContentView(R.layout.dlg_dbstat);
            if(importdb.isEmpty()){
                dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 25));
            }else{
                dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 26));
            }

            TextView lbl_1 = (TextView)dlg.findViewById(R.id.lbl_1);
            Button btn = (Button)dlg.findViewById(R.id.btn_db);

            lbl_1.setText(StrTextConst.GetText(TextType.CFGDB, 107));

            btn.setText(StrTextConst.GetText(TextType.GENERAL, 1));
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    at.cancel(true);
                }
            });
            txt_stat = (TextView)dlg.findViewById(R.id.txt_db1);
            pb_1 = (ProgressBar)dlg.findViewById(R.id.pb_db1);
            pb_2 = (ProgressBar)dlg.findViewById(R.id.pb_db2);
            pb_1.setScaleY(2f);
            pb_2.setScaleY(2f);
            dlg.setCancelable(false);
            dlg.setCanceledOnTouchOutside(false);

            if(dlg!=null){
                dlg.show();
            }
        }

        @Override
        protected Integer doInBackground(Object... params) {

            this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 22),-1,-1,-1,-1});
            if(isCancelled()){
                return -2;
            }


            boolean isMaster = (Boolean)params[0];
            boolean forcerepair = (Boolean)params[1];
            context.deleteDatabase("tmp_dest.db");
            context.deleteDatabase("tmp_src.db");
            context.deleteDatabase("tmp_check.db");


            String srcdbpath = "";
            if(importdb.isEmpty()){
                if(isMaster){
                    srcdbpath = context.getDatabasePath("master.db").getAbsolutePath();
                }else{
                    srcdbpath = context.getDatabasePath("transact.db").getAbsolutePath();
                }
            }else{
                srcdbpath = importdb;
            }

            try {
                Map<String,String> tblmap = DBFunc.LoadTableHeader(context,isMaster);
                Log.i("Fgfg__", String.valueOf(tblmap));
                List<String> scripts = DBFunc.LoadScriptUpdate(context, isMaster);
                //check database
                DBFunc.OpenDBFromDisk(srcdbpath, "tmp_check.db", context.getApplicationContext());

                boolean badversion = false;

                if(importdb.isEmpty()){
                    SQLiteDatabase dbcheck = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("tmp_check.db").getAbsolutePath(), null);
                    for(String tbl : tblmap.keySet()){
                        if(isCancelled()){
                            dbcheck.close();
                            return -2;
                        }
                        //Cursor c = dbcheck.rawQuery("SELECT COUNT(*) FROM sqlite_master where name = ?", null);
                        SQLiteStatement sql = dbcheck.compileStatement("SELECT COUNT(*) FROM sqlite_master where name = ?");
                        sql.bindString(1, tbl);
                        long count = sql.simpleQueryForLong();
                        //c.close();
                        if(count==0){
                            badversion = true;
                            break;
                        }

                        sql = dbcheck.compileStatement("SELECT COUNT(*) FROM sqlite_master where sql = ?");
                        sql.bindString(1, tblmap.get(tbl));
                        sql.simpleQueryForLong();

                        long count1 = sql.simpleQueryForLong();
                        //c.close();
                        if(count1==0){
                            badversion = true;
                            break;
                        }
                    }
                    dbcheck.close();
                }else{
                    badversion = true;
                }
                if(forcerepair){
                    badversion = true;
                }


                if(!badversion){//the database is good so we delete the temporary
                    context.getDatabasePath("tmp_check.db").delete();
                    return 1;
                }else{//the database is bad or wrong version, so we going to do restructure the database
                    if(isCancelled()){
                        return -2;
                    }
                    if(importdb.isEmpty()){
                        this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 23),-1,-1,-1,-1});
                    }else{
                        this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 24),-1,-1,-1,-1});
                    }

                    SQLiteDatabase dbsrc = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("tmp_check.db").getAbsolutePath(), null);
                    Cursor c = dbsrc.rawQuery("PRAGMA journal_mode=OFF", null);
                    c.close();
                    c = dbsrc.rawQuery("PRAGMA locking_mode=EXCLUSIVE", null);
                    c.close();



                    //create new database for restructuring
                    SQLiteDatabase dbdest = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("tmp_dest.db").getAbsolutePath(), null);
                    c = dbdest.rawQuery("PRAGMA journal_mode=OFF", null);
                    c.close();
                    c = dbdest.rawQuery("PRAGMA locking_mode=EXCLUSIVE", null);
                    c.close();
                    c = dbdest.rawQuery("PRAGMA ignore_check_constraints=ON", null);
                    c.close();
                    c = dbdest.rawQuery("PRAGMA foreign_keys=OFF", null);
                    c.close();
                    c = dbdest.rawQuery("PRAGMA synchronous=OFF", null);
                    c.close();


                    //create tables and columns
                    for(String tbl : tblmap.keySet()){
                        Log.i("Ffdg_tbl",tbl);
                        dbdest.execSQL(tblmap.get(tbl));
                    }


                    int tblcount = 0;
                    for(String tbl : tblmap.keySet()){
                        if(isCancelled()){
                            dbdest.close();
                            dbsrc.close();
                            return -2;
                        }
                        tblcount++;
                        if(importdb.isEmpty()){
                            Log.i("importdb1",importdb);
                            this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 25),tblmap.keySet().size(),tblcount,100,0});
                        }else{
                            this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 26),tblmap.keySet().size(),tblcount,100,0});
                        }

                        String sql2 = "SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND tbl_name = '"+tbl+"'";

                        Cursor read = dbsrc.rawQuery(sql2, null);
                        read.moveToNext();
                        boolean hastable = false;
                        if(read.getInt(0)>0)hastable = true;

                        read.close();
                        if(hastable){

                            Map<String,Object[]> tblstruct = new HashMap<String, Object[]>();
                            String sql1 = "PRAGMA table_info('"+tbl+"')";

                            read = dbdest.rawQuery(sql1, null);

                            while(read.moveToNext()){
                                if(isCancelled()){
                                    read.close();
                                    dbdest.close();
                                    dbsrc.close();
                                    return -2;
                                }
                                String name = read.getString(1);
                                boolean notnull = false;
                                if(read.getInt(3)==1){
                                    notnull = true;
                                }
                                Object dflt = null;
                                int type = read.getType(2);
                                switch(type){
                                    case Cursor.FIELD_TYPE_NULL:
                                        dflt = null;
                                        break;
                                    case Cursor.FIELD_TYPE_STRING:
                                        dflt = read.getString(4);
                                        break;
                                    case Cursor.FIELD_TYPE_INTEGER:
                                        dflt = read.getInt(4);
                                        break;
                                    case Cursor.FIELD_TYPE_FLOAT:
                                        dflt = read.getDouble(4);
                                        break;
                                    case Cursor.FIELD_TYPE_BLOB:
                                        dflt = read.getBlob(4);
                                        break;
                                }

                                //Log.e("ASDASD1", "colname="+name+" type="+type);
                                tblstruct.put(name,new Object[]{notnull,type,dflt});
                            }
                            read.close();
                            String[] colname = new String[tblstruct.keySet().size()];
                            tblstruct.keySet().toArray(colname);

                            read = dbsrc.rawQuery("SELECT * FROM "+tbl, null);

                            String script = "INSERT INTO "+tbl+" (";
                            for(int i=0;i<colname.length;i++){
                                if(i<colname.length-1){
                                    script+=colname[i].replace(" ","")+",";

                                }else{
                                    script+=colname[i]+")";
                                }
                            }
                            script += "VALUES(";
                            for(int i=0;i<colname.length;i++){
                                if(i<colname.length-1){
                                    script+="?,";
                                }else{
                                    script+="?)";
                                }
                            }


                            int count = 0;
                            int totalcount = read.getCount();

                            SQLiteStatement sql = dbdest.compileStatement(script);


                            while(read.moveToNext()){
                                if(isCancelled()){
                                    read.close();
                                    dbdest.close();
                                    dbsrc.close();
                                    return -2;
                                }
                                count++;
                                if(importdb.isEmpty()){
                                    this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 25),tblmap.keySet().size(),tblcount,totalcount,count});
                                }else{
                                    this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 26),tblmap.keySet().size(),tblcount,totalcount,count});
                                }

                                sql.clearBindings();
                                for(int i=0;i<colname.length;i++){
                                    int colloc = read.getColumnIndex(colname[i]);

                                    if(colloc==-1){//no such column exist
                                        if((Boolean)tblstruct.get(colname[i])[0]){//is not null
                                            switch((Integer)tblstruct.get(colname[i])[1]){
                                                case Cursor.FIELD_TYPE_NULL:
                                                    sql.bindNull(i+1);
                                                    break;
                                                case Cursor.FIELD_TYPE_STRING:
                                                    if ((String)tblstruct.get(colname[i])[2] != null) {
                                                        sql.bindString(i + 1, (String) tblstruct.get(colname[i])[2]);
                                                    }
                                                    break;
                                                case Cursor.FIELD_TYPE_INTEGER:
                                                    sql.bindLong(i+1, (Integer)tblstruct.get(colname[i])[2]);
                                                    break;
                                                case Cursor.FIELD_TYPE_FLOAT:
                                                    sql.bindDouble(i+1, (Double)tblstruct.get(colname[i])[2]);
                                                    break;
                                                case Cursor.FIELD_TYPE_BLOB:
                                                    sql.bindBlob(i+1, (byte[])tblstruct.get(colname[i])[2]);
                                                    break;
                                            }
                                        }else{
                                            sql.bindNull(i+1);
                                        }

                                    }else{
                                        if(read.isNull(colloc)){
                                            sql.bindNull(i+1);
                                        }else{
                                            switch((Integer)tblstruct.get(colname[i])[1]){
                                                case Cursor.FIELD_TYPE_NULL:
                                                    sql.bindNull(i+1);
                                                    break;
                                                case Cursor.FIELD_TYPE_STRING:
                                                    sql.bindString(i+1, read.getString(colloc));
                                                    break;
                                                case Cursor.FIELD_TYPE_INTEGER:
                                                    sql.bindLong(i+1, read.getLong(colloc));
                                                    break;
                                                case Cursor.FIELD_TYPE_FLOAT:
                                                    sql.bindDouble(i+1, read.getDouble(colloc));
                                                    break;
                                                case Cursor.FIELD_TYPE_BLOB:
                                                    sql.bindBlob(i+1, read.getBlob(colloc));
                                                    break;
                                            }
                                        }
                                    }


                                }
                                //Log.w("ASDASD", sql);
                                try {
                                    sql.executeInsert();
                                }catch (SQLiteConstraintException e){
                                    Log.i("TAG","error_"+e.getMessage());
                                }
                                sql.clearBindings();

                            }



                            read.close();
                            tblstruct.clear();

                        }
                    }

                    if(isCancelled()){
                        dbdest.close();
                        dbsrc.close();
                        return -2;
                    }



                    if(importdb.isEmpty()){
                        this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 25),-1,-1,scripts.size(),0});
                    }else{
                        this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 26),-1,-1,scripts.size(),0});
                    }

                    for(int i=0;i<scripts.size();i++){
                        if(isCancelled()){
                            dbdest.close();
                            dbsrc.close();
                            return -2;
                        }
                        if(importdb.isEmpty()){
                            this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 25),-1,-1,scripts.size(),i+1});
                        }else{
                            this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 26),-1,-1,scripts.size(),i+1});
                        }
                        dbdest.execSQL(scripts.get(i));
                    }



                    this.publishProgress(new Object[]{StrTextConst.GetText(TextType.CFGDB, 27),-1,-1,-1,-1});
                    dbdest.execSQL("VACUUM");
                    String f = dbdest.getPath();
                    dbdest.close();
                    dbsrc.close();
                    if(isMaster){
                        DBFunc.CloseDBFromInternal();

                        context.deleteDatabase("master.db");//delete the old one
                        //DBFunc.OpenDBFromDisk(f, "master.db", CurrentActivity);//copy the tmp_check.db into original filename
                        DBFunc.OpenDBFromDisk(f, "master.db", DatabaseImportExportActivity.this);//copy the tmp_check.db into original filename
                        DBFunc.LoadDBFromInternal(context);
                    }else{
                        DBFunc.CloseTransactDBFromInternal();

                        context.deleteDatabase("transact.db");//delete the old one
                        //DBFunc.OpenDBFromDisk(f, "transact.db", CurrentActivity);//copy the tmp_check.db into original filename
                        DBFunc.OpenDBFromDisk(f, "transact.db", DatabaseImportExportActivity.this);//copy the tmp_check.db into original filename
                        DBFunc.LoadTransactDBFromInternal(context);
                    }

                    context.deleteDatabase("tmp_check.db");//delete the tmp_check.db
                    return 2;//checked and repaired
                }


            } catch (IOException e) {
                Logger.WriteLog("ActivityDatabase",e.toString());
                return -1;
            }
        }

        @Override
        protected void onProgressUpdate(Object... params){
            if(params!=null){
                txt_stat.setText((String)params[0]);
                int val1_max = (Integer)params[1];
                int val1 = (Integer)params[2];
                int val2_max = (Integer)params[3];
                int val2 = (Integer)params[4];

                if(val1_max==-1){
                    pb_1.setIndeterminate(true);
                }else{
                    pb_1.setIndeterminate(false);
                    pb_1.setMax(val1_max);
                    pb_1.setProgress(val1);
                }

                if(val2_max==-1){
                    pb_2.setIndeterminate(true);
                }else{
                    pb_2.setIndeterminate(false);
                    pb_2.setMax(val2_max);
                    pb_2.setProgress(val2);
                }
            }
        }

        @Override
        protected void onCancelled(Integer output){
            if(dlg.isShowing()){
                dlg.dismiss();
                dlg=null;
            }
            context.deleteDatabase("tmp_dest.db");
            context.deleteDatabase("tmp_src.db");
            context.deleteDatabase("tmp_check.db");
        }

        @Override
        protected void onPostExecute(Integer output){
            if(dlg.isShowing()){
                dlg.dismiss();
                dlg=null;
            }

            if(output==-2){
                //clean up
                context.deleteDatabase("tmp_dest.db");
                context.deleteDatabase("tmp_src.db");
                context.deleteDatabase("tmp_check.db");
                return;
            }

            long size = DBFunc.GetMasterDBSize(context);
            if(size==-1){
                //txt_db_master_size.setText(StrTextConst.GetText(TextType.CFGDB, 21));
            }
            //txt_db_master_size.setText(StrTextConst.GetText(TextType.CFGDB, 20, size));
            size = DBFunc.GetTransactDBSize(context);
            if(size==-1){
                //txt_db_trans_size.setText(StrTextConst.GetText(TextType.CFGDB, 21));
            }
            //txt_db_trans_size.setText(StrTextConst.GetText(TextType.CFGDB, 20, size));

            DialogBox dlg = new DialogBox(context);

            if(importdb.isEmpty()){
                dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 25));
            }else{
                dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 26));
            }




            switch(output){
                case -1:
                    dlg.setDialogIconType(DialogBox.IconType.ERROR);
                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 18));
                    break;
                case 1:
                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 28));
                    break;
                case 2:
                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                    if(importdb.isEmpty()){
                        dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 29));
                    }else{
                        dlg.setMessage(StrTextConst.GetText(TextType.CFGDB, 30));
                    }
                    break;
            }

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Database -> Import/Repair");

            dlg.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
            dlg.show();


        }

    }


    public void writeFileExternalStorage() {

        //Text of the Document
        String textToWrite = "bla bla bla";

        //Checking the availability state of the External Storage.
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {

            //If it isn't mounted - we can't write into it.
            return;
        }

        //Create a new file that points to the root directory, with the given name:
        File file = new File(getExternalFilesDir(null), "TEXTSDDSF");

        //This point and below is responsible for the write operation
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            //second argument of FileOutputStream constructor indicates whether
            //to append or create new file if one exists
            outputStream = new FileOutputStream(file, true);

            outputStream.write(textToWrite.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.authorwjf.sqliteexport" +"/databases/"+SAMPLE_DB_NAME;
        String backupDBPath = SAMPLE_DB_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
//    private class CheckRepair extends AsyncTask<Object, Object, Integer> {
//
//        Dialog dlg;
//        TextView txt_stat;
//        ProgressBar pb_1;
//        ProgressBar pb_2;
//        Context context;
//        CheckRepair at;
//        String importdb = "";
//
//        public CheckRepair(Context context){
//            importdb = "";
//            this.context = context;
//            at = this;
//        }
//
//        public CheckRepair(Context context, String dbpath){
//            importdb = dbpath;
//            this.context = context;
//            at = this;
//        }
//
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//
//            dlg = new Dialog(context);
//
//            dlg.setContentView(R.layout.dlg_dbstat);
//            if(importdb.isEmpty()){
//                //dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 25));
//                dlg.setTitle("TEXT5");
//            }else{
//                //dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 26));
//                dlg.setTitle("TEXT6");
//            }
//
//            TextView lbl_1 = (TextView)dlg.findViewById(R.id.lbl_1);
//            Button btn = (Button)dlg.findViewById(R.id.btn_db);
//
//            //lbl_1.setText(StrTextConst.GetText(TextType.CFGDB, 107));
//            lbl_1.setText("TEXT7");
//
//            //btn.setText(StrTextConst.GetText(TextType.GENERAL, 1));
//            btn.setText("TEXT8");
//            btn.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//
//                    at.cancel(true);
//                }
//            });
//            txt_stat = (TextView)dlg.findViewById(R.id.txt_db1);
//            pb_1 = (ProgressBar)dlg.findViewById(R.id.pb_db1);
//            pb_2 = (ProgressBar)dlg.findViewById(R.id.pb_db2);
//            pb_1.setScaleY(2f);
//            pb_2.setScaleY(2f);
//            dlg.setCancelable(false);
//            dlg.setCanceledOnTouchOutside(false);
//
//            if(dlg!=null){
//                dlg.show();
//            }
//        }
//
//        @Override
//        protected Integer doInBackground(Object... params) {
//
////            this.publishProgress(new Object[]{
////                    StrTextConst.GetText(TextType.CFGDB, 22),-1,-1,-1,-1});
//            this.publishProgress(new Object[]{
//                    "TEXT8",-1,-1,-1,-1});
//            if(isCancelled()){
//                return -2;
//            }
//
//
//            boolean isMaster = (Boolean)params[0];
//            boolean forcerepair = (Boolean)params[1];
//            context.deleteDatabase("tmp_dest.db");
//            context.deleteDatabase("tmp_src.db");
//            context.deleteDatabase("tmp_check.db");
//
//
//            String srcdbpath = "";
//            if(importdb.isEmpty()){
//                if(isMaster){
//                    srcdbpath = context.getDatabasePath("master.db").getAbsolutePath();
//                }else{
//                    srcdbpath = context.getDatabasePath("transact.db").getAbsolutePath();
//                }
//            }else{
//                srcdbpath = importdb;
//            }
//
//
//            try {
//                Map<String,String> tblmap = DBFunc.LoadTableHeader(context,isMaster);
//                List<String> scripts = DBFunc.LoadScriptUpdate(context, isMaster);
//                //check database
//                DBFunc.OpenDBFromDisk(srcdbpath, "tmp_check.db", context.getApplicationContext());
//
//                boolean badversion = false;
//
//                if(importdb.isEmpty()){
//                    SQLiteDatabase dbcheck = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("tmp_check.db").getAbsolutePath(), null);
//                    for(String tbl : tblmap.keySet()){
//                        if(isCancelled()){
//                            dbcheck.close();
//                            return -2;
//                        }
//                        //Cursor c = dbcheck.rawQuery("SELECT COUNT(*) FROM sqlite_master where name = ?", null);
//                        SQLiteStatement sql = dbcheck.compileStatement("SELECT COUNT(*) FROM sqlite_master where name = ?");
//                        sql.bindString(1, tbl);
//                        long count = sql.simpleQueryForLong();
//                        //c.close();
//                        if(count==0){
//                            badversion = true;
//                            break;
//                        }
//
//                        sql = dbcheck.compileStatement("SELECT COUNT(*) FROM sqlite_master where sql = ?");
//                        sql.bindString(1, tblmap.get(tbl));
//                        sql.simpleQueryForLong();
//
//                        long count1 = sql.simpleQueryForLong();
//                        //c.close();
//                        if(count1==0){
//                            badversion = true;
//                            break;
//                        }
//                    }
//                    dbcheck.close();
//                }else{
//                    badversion = true;
//                }
//
//                if(forcerepair){
//                    badversion = true;
//                }
//
//
//                if(!badversion){//the database is good so we delete the temporary
//                    context.getDatabasePath("tmp_check.db").delete();
//                    return 1;
//                }else{//the database is bad or wrong version, so we going to do restructure the database
//                    if(isCancelled()){
//                        return -2;
//                    }
//                    if(importdb.isEmpty()){
//                        this.publishProgress(new Object[]{"TEXT9",-1,-1,-1,-1});
//                    }else{
//                        this.publishProgress(new Object[]{"TEXT10",-1,-1,-1,-1});
//                    }
//
//                    SQLiteDatabase dbsrc = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("tmp_check.db").getAbsolutePath(), null);
//                    Cursor c = dbsrc.rawQuery("PRAGMA journal_mode=OFF", null);
//                    c.close();
//                    c = dbsrc.rawQuery("PRAGMA locking_mode=EXCLUSIVE", null);
//                    c.close();
//
//
//
//                    //create new database for restructuring
//                    SQLiteDatabase dbdest = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath("tmp_dest.db").getAbsolutePath(), null);
//                    c = dbdest.rawQuery("PRAGMA journal_mode=OFF", null);
//                    c.close();
//                    c = dbdest.rawQuery("PRAGMA locking_mode=EXCLUSIVE", null);
//                    c.close();
//                    c = dbdest.rawQuery("PRAGMA ignore_check_constraints=ON", null);
//                    c.close();
//                    c = dbdest.rawQuery("PRAGMA foreign_keys=OFF", null);
//                    c.close();
//                    c = dbdest.rawQuery("PRAGMA synchronous=OFF", null);
//                    c.close();
//
//
//                    //create tables and columns
//                    for(String tbl : tblmap.keySet()){
//                        dbdest.execSQL(tblmap.get(tbl));
//                    }
//
//
//                    int tblcount = 0;
//                    for(String tbl : tblmap.keySet()){
//                        if(isCancelled()){
//                            dbdest.close();
//                            dbsrc.close();
//                            return -2;
//                        }
//                        tblcount++;
//                        if(importdb.isEmpty()){
//                            this.publishProgress(new Object[]{"TEXT11",tblmap.keySet().size(),tblcount,100,0});
//                        }else{
//                            this.publishProgress(new Object[]{"TEXT12",tblmap.keySet().size(),tblcount,100,0});
//                        }
//
//                        Cursor read = dbsrc.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND tbl_name = '"+tbl+"'", null);
//                        read.moveToNext();
//                        boolean hastable = false;
//                        if(read.getInt(0)>0)hastable = true;
//                        read.close();
//                        if(hastable){
//
//                            Map<String,Object[]> tblstruct = new HashMap<String, Object[]>();
//                            read = dbdest.rawQuery("PRAGMA table_info('"+tbl+"')", null);
//
//
//
//                            while(read.moveToNext()){
//                                if(isCancelled()){
//                                    read.close();
//                                    dbdest.close();
//                                    dbsrc.close();
//                                    return -2;
//                                }
//                                String name = read.getString(1);
//                                boolean notnull = false;
//                                if(read.getInt(3)==1){
//                                    notnull = true;
//                                }
//                                Object dflt = null;
//                                int type = read.getType(2);
//                                switch(type){
//                                    case Cursor.FIELD_TYPE_NULL:
//                                        dflt = null;
//                                        break;
//                                    case Cursor.FIELD_TYPE_STRING:
//                                        dflt = read.getString(4);
//                                        break;
//                                    case Cursor.FIELD_TYPE_INTEGER:
//                                        dflt = read.getInt(4);
//                                        break;
//                                    case Cursor.FIELD_TYPE_FLOAT:
//                                        dflt = read.getDouble(4);
//                                        break;
//                                    case Cursor.FIELD_TYPE_BLOB:
//                                        dflt = read.getBlob(4);
//                                        break;
//                                }
//
//                                //Log.e("ASDASD1", "colname="+name+" type="+type);
//                                tblstruct.put(name,new Object[]{notnull,type,dflt});
//                            }
//                            read.close();
//
//                            String[] colname = new String[tblstruct.keySet().size()];
//                            tblstruct.keySet().toArray(colname);
//
//
//
//
//                            read = dbsrc.rawQuery("SELECT * FROM "+tbl, null);
//
//                            String script = "INSERT INTO "+tbl+" (";
//                            for(int i=0;i<colname.length;i++){
//                                if(i<colname.length-1){
//                                    script+=colname[i]+",";
//
//                                }else{
//                                    script+=colname[i]+")";
//                                }
//                            }
//                            script += "VALUES(";
//                            for(int i=0;i<colname.length;i++){
//                                if(i<colname.length-1){
//                                    script+="?,";
//                                }else{
//                                    script+="?)";
//                                }
//                            }
//
//
//                            int count = 0;
//                            int totalcount = read.getCount();
//
//                            SQLiteStatement sql = dbdest.compileStatement(script);
//
//
//
//
//                            while(read.moveToNext()){
//                                if(isCancelled()){
//                                    read.close();
//                                    dbdest.close();
//                                    dbsrc.close();
//                                    return -2;
//                                }
//                                count++;
//                                if(importdb.isEmpty()){
//                                    this.publishProgress(new Object[]{"TEXT13",tblmap.keySet().size(),tblcount,totalcount,count});
//                                }else{
//                                    this.publishProgress(new Object[]{"TEXT14",tblmap.keySet().size(),tblcount,totalcount,count});
//                                }
//
//                                sql.clearBindings();
//                                for(int i=0;i<colname.length;i++){
//                                    int colloc = read.getColumnIndex(colname[i]);
//
//                                    //Log.e("ASDASD", "colloc="+colloc+",colname="+colname[i]+" type="+(Integer)tblstruct.get(colname[i])[1]);
//                                    if(colloc==-1){//no such column exist
//                                        if((Boolean)tblstruct.get(colname[i])[0]){//is not null
//                                            switch((Integer)tblstruct.get(colname[i])[1]){
//                                                case Cursor.FIELD_TYPE_NULL:
//                                                    sql.bindNull(i+1);
//                                                    break;
//                                                case Cursor.FIELD_TYPE_STRING:
//                                                    if ((String)tblstruct.get(colname[i])[2] != null) {
//                                                        sql.bindString(i + 1, (String) tblstruct.get(colname[i])[2]);
//                                                    }
//                                                    break;
//                                                case Cursor.FIELD_TYPE_INTEGER:
//                                                    sql.bindLong(i+1, (Integer)tblstruct.get(colname[i])[2]);
//                                                    break;
//                                                case Cursor.FIELD_TYPE_FLOAT:
//                                                    sql.bindDouble(i+1, (Double)tblstruct.get(colname[i])[2]);
//                                                    break;
//                                                case Cursor.FIELD_TYPE_BLOB:
//                                                    sql.bindBlob(i+1, (byte[])tblstruct.get(colname[i])[2]);
//                                                    break;
//                                            }
//                                        }else{
//                                            sql.bindNull(i+1);
//                                        }
//
//                                    }else{
//                                        if(read.isNull(colloc)){
//                                            sql.bindNull(i+1);
//                                        }else{
//                                            switch((Integer)tblstruct.get(colname[i])[1]){
//                                                case Cursor.FIELD_TYPE_NULL:
//                                                    sql.bindNull(i+1);
//                                                    break;
//                                                case Cursor.FIELD_TYPE_STRING:
//                                                    //Log.w("ASDSADA", ""+read.getString(colloc));
//                                                    //Log.e("ASDASD", "Table="+tbl+ ", colloc="+colloc+",colname="+colname[i]+" type="+(Integer)tblstruct.get(colname[i])[1] + ", val="+read.getString(colloc));
//                                                    sql.bindString(i+1, read.getString(colloc));
//                                                    break;
//                                                case Cursor.FIELD_TYPE_INTEGER:
//                                                    sql.bindLong(i+1, read.getLong(colloc));
//                                                    break;
//                                                case Cursor.FIELD_TYPE_FLOAT:
//                                                    sql.bindDouble(i+1, read.getDouble(colloc));
//                                                    break;
//                                                case Cursor.FIELD_TYPE_BLOB:
//                                                    sql.bindBlob(i+1, read.getBlob(colloc));
//                                                    break;
//                                            }
//                                        }
//                                    }
//
//
//                                }
//                                //Log.w("ASDASD", sql);
//                                sql.executeInsert();
//                                sql.clearBindings();
//
//                            }
//
//
//
//                            read.close();
//                            tblstruct.clear();
//
//                        }
//                    }
//                    if(isCancelled()){
//                        dbdest.close();
//                        dbsrc.close();
//                        return -2;
//                    }
//
//
//
//                    if(importdb.isEmpty()){
//                        this.publishProgress(new Object[]{"TEXT15",-1,-1,scripts.size(),0});
//                    }else{
//                        this.publishProgress(new Object[]{"TEXT16",-1,-1,scripts.size(),0});
//                    }
//
//                    for(int i=0;i<scripts.size();i++){
//                        if(isCancelled()){
//                            dbdest.close();
//                            dbsrc.close();
//                            return -2;
//                        }
//                        if(importdb.isEmpty()){
//                            this.publishProgress(new Object[]{"TEXT17",-1,-1,scripts.size(),i+1});
//                        }else{
//                            this.publishProgress(new Object[]{"TEXT18",-1,-1,scripts.size(),i+1});
//                        }
//                        dbdest.execSQL(scripts.get(i));
//                    }
//
//
//
//                    this.publishProgress(new Object[]{"TEXT19",-1,-1,-1,-1});
//                    dbdest.execSQL("VACUUM");
//                    String f = dbdest.getPath();
//                    dbdest.close();
//                    dbsrc.close();
//                    if(isMaster){
//                        DBFunc.CloseDBFromInternal();
//
//                        context.deleteDatabase("master.db");//delete the old one
//                        DBFunc.OpenDBFromDisk(f, "master.db", CurrentActivity);//copy the tmp_check.db into original filename
//                        DBFunc.LoadDBFromInternal(context);
//                    }else{
//                        DBFunc.CloseTransactDBFromInternal();
//
//                        context.deleteDatabase("transact.db");//delete the old one
//                        DBFunc.OpenDBFromDisk(f, "transact.db", CurrentActivity);//copy the tmp_check.db into original filename
//                        DBFunc.LoadTransactDBFromInternal(context);
//                    }
//
//                    context.deleteDatabase("tmp_check.db");//delete the tmp_check.db
//                    return 2;//checked and repaired
//                }
//
//
//            } catch (IOException e) {
//                Logger.WriteLog("ActivityDatabase",e.toString());
//                return -1;
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(Object... params){
//            if(params!=null){
//                txt_stat.setText((String)params[0]);
//                int val1_max = (Integer)params[1];
//                int val1 = (Integer)params[2];
//                int val2_max = (Integer)params[3];
//                int val2 = (Integer)params[4];
//
//                if(val1_max==-1){
//                    pb_1.setIndeterminate(true);
//                }else{
//                    pb_1.setIndeterminate(false);
//                    pb_1.setMax(val1_max);
//                    pb_1.setProgress(val1);
//                }
//
//                if(val2_max==-1){
//                    pb_2.setIndeterminate(true);
//                }else{
//                    pb_2.setIndeterminate(false);
//                    pb_2.setMax(val2_max);
//                    pb_2.setProgress(val2);
//                }
//            }
//        }
//
//        @Override
//        protected void onCancelled(Integer output){
//            if(dlg.isShowing()){
//                dlg.dismiss();
//                dlg=null;
//            }
//            context.deleteDatabase("tmp_dest.db");
//            context.deleteDatabase("tmp_src.db");
//            context.deleteDatabase("tmp_check.db");
//        }
//
//        @Override
//        protected void onPostExecute(Integer output){
//            if(dlg.isShowing()){
//                dlg.dismiss();
//                dlg=null;
//            }
//
//            if(output==-2){
//                //clean up
//                context.deleteDatabase("tmp_dest.db");
//                context.deleteDatabase("tmp_src.db");
//                context.deleteDatabase("tmp_check.db");
//                return;
//            }
//
//            long size = DBFunc.GetMasterDBSize(context);
//            if(size==-1){
//                //txt_db_master_size.setText(StrTextConst.GetText(TextType.CFGDB, 21));
//            }
//            //txt_db_master_size.setText(StrTextConst.GetText(TextType.CFGDB, 20, size));
//            size = DBFunc.GetTransactDBSize(context);
//            if(size==-1){
//               // txt_db_trans_size.setText(StrTextConst.GetText(TextType.CFGDB, 21));
//            }
//            //txt_db_trans_size.setText(StrTextConst.GetText(TextType.CFGDB, 20, size));
//
//            DialogBox dlg = new DialogBox(context);
//
//            if(importdb.isEmpty()){
//                dlg.setTitle("TEXT20");
//                //dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 25));
//            }else{
//                dlg.setTitle("TEXT21");
//                //dlg.setTitle(StrTextConst.GetText(TextType.CFGDB, 26));
//            }
//
//
//
//
//            switch(output){
//                case -1:
//                    dlg.setDialogIconType(DialogBox.IconType.ERROR);
//                    dlg.setMessage("TEXT27");
//                    break;
//                case 1:
//                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    dlg.setMessage("TEXT28");
//                    break;
//                case 2:
//                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    if(importdb.isEmpty()){
//                        dlg.setMessage("TEXT29");
//                    }else{
//                        dlg.setMessage("TEXT30");
//                    }
//                    break;
//            }
//
//            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Database -> Import/Repair");
//
//            dlg.setButton1OnClickListener("TEXT31", null);
//            dlg.show();
//
//
//        }
//
//    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(DatabaseImportExportActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
