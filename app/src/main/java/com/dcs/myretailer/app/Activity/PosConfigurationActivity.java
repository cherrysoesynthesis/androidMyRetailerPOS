package com.dcs.myretailer.app.Activity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FileBrowser;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.ScreenSize.PosConfigurationActivityScreenSize;
import com.dcs.myretailer.app.Setting.BalanceTypeSheetFragment;
import com.dcs.myretailer.app.Setting.BluetoothSheetFragment;
import com.dcs.myretailer.app.Setting.DBConfig;
import com.dcs.myretailer.app.Setting.FontSizeSheetFragment;
import com.dcs.myretailer.app.Setting.ReceiptPrinterSheetFragment;
import com.dcs.myretailer.app.Setting.RoundMethodSheetFragment;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.ActivityPosConfigurationBinding;
import com.dcs.myretailer.app.databinding.ActivityPosConfigurationIminBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PosConfigurationActivity extends AppCompatActivity {
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    public static String macAddress = "";
    public static String devicename = "";
    public static DBConfig db_config;
    String saveCxPrinterReceipt = "0";
    String saveCxCashDrawer = "0";
    String saveCxCustomerDisplay = "0";
    String saveCxBarCodeScanner = "0";
    public static Integer selected_product_cateogry_id = -1;
    public static String selected_product_cateogry_name = "0";
    public static Integer selected_balance_type_id = -1;
    public static String selected_balance_type_name = "0";
    public static Integer selected_receipt_printer_id = -1;
    public static String selected_receipt_printer_name = "0";
    public static Integer selected_font_size_id = -1;
    public static String selected_font_size_name = "0";
    public static Integer selected_bluetooth_id = -1;
    public static String selected_bluetooth_name = "0";
    Handler mHandler;
    //EditText et_name = null;
    //EditText edit_terminal_id = null;
    //EditText edit_storeno_id = null;
    //EditText edit_server_image_upload_url = null;
    Context context;
    ActivityPosConfigurationBinding binding = null;
    ActivityPosConfigurationIminBinding binding_imin = null;
    String terminalTypeVal = "";
    String device = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pos_configuration);

//        binding = DataBindingUtil.setContentView(this,R.layout.activity_pos_configuration);
//        binding_imin = DataBindingUtil.setContentView(this,R.layout.activity_pos_configuration_imin);

        context = PosConfigurationActivity.this;
        terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        //device = Query.GetDeviceData(Constraints.DEVICE);
        device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            binding_imin = DataBindingUtil.setContentView(this,R.layout.activity_pos_configuration_imin);
            new PosConfigurationActivityScreenSize(context,binding_imin);
        }else {
            binding = DataBindingUtil.setContentView(this,R.layout.activity_pos_configuration);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //screenDisplay();


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final Activity CurrentActivity = this;

        db_config = new DBConfig(this,"",null,1);

        Log.i("device___","device____"+device);

        if (device.equals("M2-Max")) {
            binding_imin.btnRoundMethod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoundMethodSheetFragment pcSSheetFragment = new RoundMethodSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        }else {
            binding.btnRoundMethod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoundMethodSheetFragment pcSSheetFragment = new RoundMethodSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });

        }
        if (device.equals("M2-Max")) {
            binding_imin.btnBalanceType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BalanceTypeSheetFragment pcSSheetFragment = new BalanceTypeSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        } else {
            binding.btnBalanceType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BalanceTypeSheetFragment pcSSheetFragment = new BalanceTypeSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        }
        if (device.equals("M2-Max")) {
            binding_imin.btnBillListFontSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FontSizeSheetFragment pcSSheetFragment = new FontSizeSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
         } else {
            binding.btnBillListFontSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FontSizeSheetFragment pcSSheetFragment = new FontSizeSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        }
        if (device.equals("M2-Max")) {
            binding_imin.btnBluetooth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BluetoothSheetFragment pcSSheetFragment = new BluetoothSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        } else {
            binding.btnBluetooth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BluetoothSheetFragment pcSSheetFragment = new BluetoothSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        }
        if (device.equals("M2-Max")) {
            binding_imin.btnReceiptPrinter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReceiptPrinterSheetFragment pcSSheetFragment = new ReceiptPrinterSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        }else {
            binding.btnReceiptPrinter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReceiptPrinterSheetFragment pcSSheetFragment = new ReceiptPrinterSheetFragment();
                    pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
                }
            });
        }

        final List<Integer> printerid = new ArrayList<Integer>();
        //final ArrayAdapter<String> adapt_printer = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        final ArrayAdapter<String> adapt_printer = new ArrayAdapter<String>(this, R.layout.spinner_pos_configuration);
        //adapt_printer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapt_printer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (device.equals("M2-Max")) {
            binding_imin.spnPrinter.setAdapter(adapt_printer);
        } else {
            binding.spnPrinter.setAdapter(adapt_printer);
        }

        final ArrayList list = new ArrayList();

        if(bAdapter==null){
            Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
        }
        else{
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();

            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    devicename = device.getName();
                    macAddress = device.getAddress();
                    //list.add("Name: "+devicename+"MAC Address: "+macAddress);
                    list.add(devicename);
                }
                //Log.i("devicename:::",String.valueOf(devicename));
                //Log.i("macAddress:::",String.valueOf(macAddress));
                //Log.i("list_:::",String.valueOf(list));
//                 lstvw = (ListView) findViewById(R.id.deviceList);
//                 aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
//                 lstvw.setAdapter(aAdapter);
            }
        }
//        //final ArrayAdapter<String> adapt_bluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
//        final ArrayAdapter<String> adapt_bluetooth = new ArrayAdapter<String>(this, R.layout.spinner_pos_configuration,list);
//        adapt_bluetooth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_bluetooth.setAdapter(adapt_bluetooth);
//
//
//        spn_bluetooth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//                // TODO Auto-generated method stub
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//            }
//        });

        final ArrayAdapter<String> adapt_col = new ArrayAdapter<String>(this,R.layout.spinner_pos_configuration);
        adapt_col.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (device.equals("M2-Max")) {
            binding_imin.spnCol.setAdapter(adapt_col);
        }else {
            binding.spnCol.setAdapter(adapt_col);
        }

        //final ArrayAdapter<String> adapt_row = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        final ArrayAdapter<String> adapt_row = new ArrayAdapter<String>(this,R.layout.spinner_pos_configuration);
        adapt_row.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (device.equals("M2-Max")) {
            binding_imin.spnRow.setAdapter(adapt_row);
        }else {
            binding.spnRow.setAdapter(adapt_row);
        }

        for (int i = 0; i < 9; i++) {
            adapt_col.add("" + (i + 1));
            adapt_row.add("" + (i + 1));
        }
        if (device.equals("M2-Max")) {
            binding_imin.spnCol.setSelection(3);
            binding_imin.spnRow.setSelection(3);
        }else {
            binding.spnCol.setSelection(3);
            binding.spnRow.setSelection(3);
        }

        // final ArrayAdapter<String> adapt_baltype = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        final ArrayAdapter<String> adapt_baltype = new ArrayAdapter<String>(this,R.layout.spinner_pos_configuration);
        adapt_baltype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (device.equals("M2-Max")) {
            binding_imin.spnBaltype.setAdapter(adapt_baltype);
        } else {
            binding.spnBaltype.setAdapter(adapt_baltype);
        }
        adapt_baltype.add(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 30));
        adapt_baltype.add(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 31));
        adapt_baltype.add(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 32));

        final List<Integer> mapid = new ArrayList<Integer>();
        //final ArrayAdapter<String> adapt_maplayout = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        final ArrayAdapter<String> adapt_maplayout = new ArrayAdapter<String>(this,R.layout.spinner_pos_configuration);
        adapt_maplayout.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (device.equals("M2-Max")) {
            binding_imin.spnMapLay.setAdapter(adapt_maplayout);
        } else {
            binding.spnMapLay.setAdapter(adapt_maplayout);
        }

        if (device.equals("M2-Max")) {
            binding_imin.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 33));
            binding_imin.etBalTitle.setEnabled(false);

            binding_imin.spnBaltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    binding_imin.spnMapLay.setEnabled(false);
                    if (position == 0) {
                        binding_imin.etBalTitle.setEnabled(false);
                    } else {
                        binding_imin.etBalTitle.setEnabled(true);
                        if (position == 2) {
                            binding_imin.spnMapLay.setEnabled(true);
                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }

            });
        } else {
            binding.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 33));
            binding.etBalTitle.setEnabled(false);

            binding.spnBaltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    binding.spnMapLay.setEnabled(false);
                    if (position == 0) {
                        binding.etBalTitle.setEnabled(false);
                    } else {
                        binding.etBalTitle.setEnabled(true);
                        if (position == 2) {
                            binding.spnMapLay.setEnabled(true);
                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }

            });
        }


        //final ArrayAdapter<String> adapt_fontsize = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        final ArrayAdapter<String> adapt_fontsize = new ArrayAdapter<String>(this, R.layout.spinner_pos_configuration);
        adapt_fontsize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (device.equals("M2-Max")) {
            binding_imin.spnFontsize.setAdapter(adapt_fontsize);
        } else {
            binding.spnFontsize.setAdapter(adapt_fontsize);
        }

        for (int i = 0; i < 31; i++) {
            adapt_fontsize.add("" + (10 + i));
        }

        if (device.equals("M2-Max")) {
            binding_imin.spnFontsize.setSelection(8);
        } else {
            binding.spnFontsize.setSelection(8);
        }

        if (device.equals("M2-Max")) {
            if (binding_imin.chkPosIntegrateShoptima.isChecked()) {
                binding_imin.linearlayshoptimaurl.setVisibility(View.VISIBLE);
                binding_imin.linearlayshoptima.setVisibility(View.VISIBLE);
                binding_imin.linearlayshoptimapassword.setVisibility(View.VISIBLE);
                binding_imin.linearlayshoptimachineid.setVisibility(View.VISIBLE);
                binding_imin.linearlayshoptimallcode.setVisibility(View.VISIBLE);
            } else {
                binding_imin.linearlayshoptimaurl.setVisibility(View.GONE);
                binding_imin.linearlayshoptima.setVisibility(View.GONE);
                binding_imin.linearlayshoptimapassword.setVisibility(View.GONE);
                binding_imin.linearlayshoptimachineid.setVisibility(View.GONE);
                binding_imin.linearlayshoptimallcode.setVisibility(View.GONE);
            }
        } else {
            if (binding.chkPosIntegrateShoptima.isChecked()) {
                binding.linearlayshoptimaurl.setVisibility(View.VISIBLE);
                binding.linearlayshoptima.setVisibility(View.VISIBLE);
                binding.linearlayshoptimapassword.setVisibility(View.VISIBLE);
                binding.linearlayshoptimachineid.setVisibility(View.VISIBLE);
                binding.linearlayshoptimallcode.setVisibility(View.VISIBLE);
            } else {
                binding.linearlayshoptimaurl.setVisibility(View.GONE);
                binding.linearlayshoptima.setVisibility(View.GONE);
                binding.linearlayshoptimapassword.setVisibility(View.GONE);
                binding.linearlayshoptimachineid.setVisibility(View.GONE);
                binding.linearlayshoptimallcode.setVisibility(View.GONE);
            }
        }

        final List<Integer> langID = new ArrayList<Integer>();

        //final ArrayAdapter<String> adapt_lang = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        final ArrayAdapter<String> adapt_lang = new ArrayAdapter<String>(this, R.layout.spinner_pos_configuration);
        adapt_lang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (device.equals("M2-Max")) {
            binding_imin.spnLang.setAdapter(adapt_lang);
        } else {
            binding.spnLang.setAdapter(adapt_lang);
        }

        langID.add(0);
        adapt_lang.add(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 200));

        if (device.equals("M2-Max")) {
            binding_imin.spnLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        binding_imin.btnLangDel.setEnabled(false);
                        binding_imin.btnLangImport.setEnabled(false);
                    } else {
                        binding_imin.btnLangDel.setEnabled(true);
                        binding_imin.btnLangImport.setEnabled(true);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else {
            binding.spnLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        binding.btnLangDel.setEnabled(false);
                        binding.btnLangImport.setEnabled(false);
                    } else {
                        binding.btnLangDel.setEnabled(true);
                        binding.btnLangImport.setEnabled(true);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }


        if (device.equals("M2-Max")) {
            binding_imin.btnLangNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 3));


                    LinearLayout lay1 = new LinearLayout(v.getContext());
                    lay1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TextView tv2 = new TextView(v.getContext());
                    tv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    tv2.setGravity(Gravity.CENTER_VERTICAL);
                    tv2.setMaxWidth(80);
                    tv2.setMinWidth(80);
                    tv2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 100));

                    lay1.addView(tv2);

                    final EditText et_langname = new EditText(v.getContext());
                    et_langname.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                    et_langname.setLines(1);
                    et_langname.setMaxLines(1);
                    et_langname.setSingleLine(true);
                    et_langname.setText("");

                    lay1.addView(et_langname);

                    dlg1.addView(lay1);

                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            String name = et_langname.getText().toString();
                            if (name.length() == 0) {
                                DialogBox dlg2 = new DialogBox(v.getContext());
                                dlg2.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                                dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg2.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 20));
                                dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg2.show();
                                return;
                            }

                            Cursor c = DBFunc.Query("SELECT COUNT(*) FROM LangList WHERE name = '" + DBFunc.PurifyString(name) + "' COLLATE NOCASE", true);
                            boolean isexist = false;
                            if (c != null) {
                                if (c.moveToNext()) {
                                    if (c.getInt(0) > 0) {
                                        isexist = true;
                                    }
                                }
                                c.close();
                            }
                            if (isexist) {
                                DialogBox dlg2 = new DialogBox(v.getContext());
                                dlg2.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                                dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg2.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 29));
                                dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg2.show();
                                return;
                            }

                            DBFunc.ExecQuery("INSERT INTO LangList(name) VALUES('" + DBFunc.PurifyString(name) + "')", true);

                            c = DBFunc.Query("SELECT seq FROM sqlite_sequence WHERE name = 'LangList'", true);
                            int lastinsertID = 0;
                            if (c != null) {
                                if (c.moveToNext()) {
                                    lastinsertID = c.getInt(0);
                                }
                                c.close();
                            }

                            langID.add(lastinsertID);
                            adapt_lang.add(name);
                            binding_imin.spnLang.setSelection(adapt_lang.getCount() - 1);
                            dlg1.dismiss();
                        }

                    });

                    dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1), null);

                    dlg1.show();

                }

            });
        } else {
            binding.btnLangNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 3));


                    LinearLayout lay1 = new LinearLayout(v.getContext());
                    lay1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TextView tv2 = new TextView(v.getContext());
                    tv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    tv2.setGravity(Gravity.CENTER_VERTICAL);
                    tv2.setMaxWidth(80);
                    tv2.setMinWidth(80);
                    tv2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 100));

                    lay1.addView(tv2);

                    final EditText et_langname = new EditText(v.getContext());
                    et_langname.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                    et_langname.setLines(1);
                    et_langname.setMaxLines(1);
                    et_langname.setSingleLine(true);
                    et_langname.setText("");

                    lay1.addView(et_langname);

                    dlg1.addView(lay1);

                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            String name = et_langname.getText().toString();
                            if (name.length() == 0) {
                                DialogBox dlg2 = new DialogBox(v.getContext());
                                dlg2.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                                dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg2.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 20));
                                dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg2.show();
                                return;
                            }

                            Cursor c = DBFunc.Query("SELECT COUNT(*) FROM LangList WHERE name = '" + DBFunc.PurifyString(name) + "' COLLATE NOCASE", true);
                            boolean isexist = false;
                            if (c != null) {
                                if (c.moveToNext()) {
                                    if (c.getInt(0) > 0) {
                                        isexist = true;
                                    }
                                }
                                c.close();
                            }
                            if (isexist) {
                                DialogBox dlg2 = new DialogBox(v.getContext());
                                dlg2.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                                dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg2.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 29));
                                dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg2.show();
                                return;
                            }

                            DBFunc.ExecQuery("INSERT INTO LangList(name) VALUES('" + DBFunc.PurifyString(name) + "')", true);

                            c = DBFunc.Query("SELECT seq FROM sqlite_sequence WHERE name = 'LangList'", true);
                            int lastinsertID = 0;
                            if (c != null) {
                                if (c.moveToNext()) {
                                    lastinsertID = c.getInt(0);
                                }
                                c.close();
                            }

                            langID.add(lastinsertID);
                            adapt_lang.add(name);
                            binding.spnLang.setSelection(adapt_lang.getCount() - 1);
                            dlg1.dismiss();
                        }

                    });

                    dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1), null);

                    dlg1.show();

                }

            });
        }
        if (device.equals("M2-Max")) {
            binding_imin.btnLangDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = binding_imin.spnLang.getSelectedItemPosition();
                    if (pos == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    final int id = langID.get(pos);
                    if (id == 0) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 13));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }

                    final DialogBox dlg = new DialogBox(v.getContext());
                    dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 11, adapt_lang.getItem(pos)));
                    dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dlg.dismiss();
                            DBFunc.ExecQuery("DELETE FROM LangDetails FROM langlist_id = " + id, true);
                            DBFunc.ExecQuery("DELETE FROM LangList FROM id = " + id, true);
                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Language -> Delete -> Name: " + adapt_lang.getItem(pos));

                            adapt_lang.clear();
                            langID.clear();

                            Cursor data = DBFunc.Query("SELECT id,name FROM LangList ORDER BY id ASC", true);
                            if (data != null) {
                                while (data.moveToNext()) {
                                    langID.add(data.getInt(0));
                                    adapt_lang.add(data.getString(1));
                                }
                                data.close();
                                if (pos < langID.size()) {
                                    binding_imin.spnLang.setSelection(pos);
                                } else {
                                    if (langID.size() > 0) {
                                        binding_imin.spnLang.setSelection(langID.size() - 1);
                                    }
                                }
                            }

                            DialogBox dlg1 = new DialogBox(v.getContext());
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 12));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                        }
                    });
                    dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                    dlg.show();
                }
            });
        } else {

            binding.btnLangDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = binding.spnLang.getSelectedItemPosition();
                    if (pos == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    final int id = langID.get(pos);
                    if (id == 0) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 13));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }

                    final DialogBox dlg = new DialogBox(v.getContext());
                    dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 11, adapt_lang.getItem(pos)));
                    dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dlg.dismiss();
                            DBFunc.ExecQuery("DELETE FROM LangDetails FROM langlist_id = " + id, true);
                            DBFunc.ExecQuery("DELETE FROM LangList FROM id = " + id, true);
                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Language -> Delete -> Name: " + adapt_lang.getItem(pos));

                            adapt_lang.clear();
                            langID.clear();

                            Cursor data = DBFunc.Query("SELECT id,name FROM LangList ORDER BY id ASC", true);
                            if (data != null) {
                                while (data.moveToNext()) {
                                    langID.add(data.getInt(0));
                                    adapt_lang.add(data.getString(1));
                                }
                                data.close();
                                if (pos < langID.size()) {
                                    binding.spnLang.setSelection(pos);
                                } else {
                                    if (langID.size() > 0) {
                                        binding.spnLang.setSelection(langID.size() - 1);
                                    }
                                }
                            }

                            DialogBox dlg1 = new DialogBox(v.getContext());
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 12));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                        }
                    });
                    dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                    dlg.show();
                }
            });
        }

        if (device.equals("M2-Max")) {
            binding_imin.btnLangImport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = binding_imin.spnLang.getSelectedItemPosition();
                    if (pos == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    final int id = langID.get(pos);

                    FileBrowser fb = new FileBrowser(v.getContext());

                    fb.setDefaultDir(Environment.getExternalStorageDirectory() + "/posdata/");
                    fb.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 4));
                    try {
                        fb.SetFileExtension("All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener() {
                            @Override
                            public void onSelected(View v, File file) {
                                if (!StrTextConst.LoadLanguageSQLFile(file.getAbsolutePath(), id)) {
                                    DialogBox dlg1 = new DialogBox(v.getContext());
                                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 24));
                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                    dlg1.show();
                                    return;
                                }

                                DialogBox dlg1 = new DialogBox(v.getContext());
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
                                dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 15));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                            }
                        });
                        fb.ShowOpenDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityPOSConfig", e.toString());
                    }
                }
            });
        } else {
            binding.btnLangImport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = binding.spnLang.getSelectedItemPosition();
                    if (pos == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    final int id = langID.get(pos);

                    FileBrowser fb = new FileBrowser(v.getContext());

                    fb.setDefaultDir(Environment.getExternalStorageDirectory() + "/posdata/");
                    fb.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 4));
                    try {
                        fb.SetFileExtension("All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener() {
                            @Override
                            public void onSelected(View v, File file) {
                                if (!StrTextConst.LoadLanguageSQLFile(file.getAbsolutePath(), id)) {
                                    DialogBox dlg1 = new DialogBox(v.getContext());
                                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 24));
                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                    dlg1.show();
                                    return;
                                }

                                DialogBox dlg1 = new DialogBox(v.getContext());
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
                                dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 15));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                            }
                        });
                        fb.ShowOpenDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityPOSConfig", e.toString());
                    }
                }
            });
        }
        if (device.equals("M2-Max")) {
            binding_imin.btnLangExport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = binding_imin.spnLang.getSelectedItemPosition();
                    if (pos == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    final int id = langID.get(pos);

                    FileBrowser fb = new FileBrowser(v.getContext());

                    fb.setDefaultDir(Environment.getExternalStorageDirectory() + "/posdata/");
                    fb.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 5));
                    try {
                        fb.SetFileExtension("All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener() {
                            @Override
                            public void onSelected(View v, File file) {
                                boolean retstat = false;
                                if (id == 0) {
                                    retstat = StrTextConst.ExportTempLanguageFile(file.getAbsolutePath());
                                } else {
                                    retstat = StrTextConst.ExportLanguageSQLFile(file.getAbsolutePath(), id);
                                }

                                if (!retstat) {
                                    DialogBox dlg1 = new DialogBox(v.getContext());
                                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 24));
                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                    dlg1.show();
                                    return;
                                }

                                DialogBox dlg1 = new DialogBox(v.getContext());
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
                                dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 16));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                            }
                        });
                        fb.ShowSaveDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityPOSConfig", e.toString());
                    }
                }
            });
        } else {
            binding.btnLangExport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = binding.spnLang.getSelectedItemPosition();
                    if (pos == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 23));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    final int id = langID.get(pos);

                    FileBrowser fb = new FileBrowser(v.getContext());

                    fb.setDefaultDir(Environment.getExternalStorageDirectory() + "/posdata/");
                    fb.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 5));
                    try {
                        fb.SetFileExtension("All Files(*.*)|*.*");
                        fb.setOnFileOKListener(new FileBrowser.OnFileOKListener() {
                            @Override
                            public void onSelected(View v, File file) {
                                boolean retstat = false;
                                if (id == 0) {
                                    retstat = StrTextConst.ExportTempLanguageFile(file.getAbsolutePath());
                                } else {
                                    retstat = StrTextConst.ExportLanguageSQLFile(file.getAbsolutePath(), id);
                                }

                                if (!retstat) {
                                    DialogBox dlg1 = new DialogBox(v.getContext());
                                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 24));
                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                    dlg1.show();
                                    return;
                                }

                                DialogBox dlg1 = new DialogBox(v.getContext());
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
                                dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 16));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                            }
                        });
                        fb.ShowSaveDialog();
                    } catch (Exception e) {
                        Logger.WriteLog("ActivityPOSConfig", e.toString());
                    }
                }
            });
        }

        if (device.equals("M2-Max")) {
            binding_imin.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //CurrentActivity.finish();

                    Intent i = new Intent(context, SettingActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        } else {

            binding.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //CurrentActivity.finish();

                    Intent i = new Intent(context, SettingActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        }
        if (device.equals("M2-Max")) {
            binding_imin.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding_imin.etName.getText().toString().isEmpty()) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 20));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }


                    int printcount = -1;
                    try {
                        printcount = Integer.parseInt(binding_imin.etPrintcount.getText().toString());
                    } catch (NumberFormatException e) {
                    }

                    if (printcount < 0) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 26));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }


//                if (spn_printer.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
//                    DialogBox dlg1 = new DialogBox(v.getContext());
//                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
//                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 25));
//                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                    dlg1.show();
//                    return;
//                }

                    String bal_title = binding_imin.etBalTitle.getText().toString();
                    if (binding_imin.spnBaltype.getSelectedItemPosition() > 0) {
                        if (bal_title.isEmpty()) {
                            DialogBox dlg1 = new DialogBox(v.getContext());
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 27));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                            return;
                        }

                        if (binding_imin.spnBaltype.getSelectedItemPosition() == 2) {
                            if (binding_imin.spnMapLay.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
                                DialogBox dlg1 = new DialogBox(v.getContext());
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                                dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 28));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                                return;
                            }
                        }

                    } else {
                        bal_title = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 33);
                    }

                    if (bal_title.length() > 10) {
                        bal_title = bal_title.substring(0, 10);
                    }

                    String option = "";
                    if (binding_imin.chkPosSelectlast.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosNoprintcondiqty.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosPaymentonlykp.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosHideNaviBar.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosUsermode.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosCheckupdate.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosReferfunction.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosRefercompulsory.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosReferinfo1print.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosReferinfo2print.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosReferinfo3print.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosUserovertake.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosNoprintunpaid.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosPrinterReceipt.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosCashDrawer.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosCustomerDisplay.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding_imin.chkPosBarcodeScanner.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosIntegrateShoptima.isChecked()) {
                        option += "1";

                        layoutShoptimaFun(View.VISIBLE, "Normal");
                    } else {
                        option += "0";
                        layoutShoptimaFun(View.GONE, "Normal");
                    }
                    if (binding_imin.chkPosQrCodeShoptima.isChecked()) {
                        option += "1";

                        layoutShoptimaFun(View.VISIBLE, "QRCode");

                    } else {
                        option += "0";

                        layoutShoptimaFun(View.GONE, "QRCode");
                    }

                    if (binding_imin.chkPosOnlineOrder.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosImageUrl.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosServiceCharges.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosRetail.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosReceiptPrintPaper.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosJerifood.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosBarcodeOnReceipt.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPos4DigitICNO.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosZclose.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding_imin.chkPosKitchenPrinter.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    String info1 = binding_imin.etInfo1.getText().toString();
                    if (info1.length() > 10) {
                        info1 = info1.substring(0, 10);
                    }

                    String info2 = binding_imin.etInfo2.getText().toString();
                    if (info2.length() > 10) {
                        info2 = info2.substring(0, 10);
                    }

                    String info3 = binding_imin.etInfo3.getText().toString();
                    if (info3.length() > 10) {
                        info3 = info3.substring(0, 10);
                    }


                    DBFunc.ExecQuery("DELETE FROM POSSys", true);

                    String rep_printer = "0";
                    if (selected_receipt_printer_name.equals("0")) {
                        rep_printer = binding_imin.editReceiptPrinter.getText().toString();
                    } else {
                        rep_printer = selected_receipt_printer_name;
                    }
                    String rep_p_id = "0";
                    Cursor c1 = DBFunc.Query("SELECT id,name FROM Printer where name = '" + rep_printer + "'", true);
                    if (c1 != null) {
                        if (c1.moveToNext()) {
                            rep_p_id = c1.getString(0);
                        }
                        c1.close();
                    }
                    String round_type = "0";
                    if (selected_product_cateogry_name.equals("0")) {
                        round_type = binding_imin.editRoundMethod.getText().toString();
                    } else {
                        round_type = selected_product_cateogry_name;
                    }
                    String round_type_id = "0";
                    if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 34))) {
                        round_type_id = "0";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 35))) {
                        round_type_id = "1";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 36))) {
                        round_type_id = "2";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 37))) {
                        round_type_id = "3";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 38))) {
                        round_type_id = "4";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 39))) {
                        round_type_id = "5";
                    }
                    String bfont_size_ = "0";
                    if (selected_font_size_name.equals("0")) {
                        bfont_size_ = binding_imin.editBillListFontSize.getText().toString();
                    } else {
                        bfont_size_ = selected_font_size_name;
                        //bfont_size_ = "14";
                    }
//                if (Integer.parseInt(bfont_size_) == 0){
//                    bfont_size_ = "12";
//                }
                    String sql = "INSERT INTO POSSys (name,terminal_id,storeno,receipt_no_reference,server_image_upload_url,receipt_printer,retails_id,company_code," +
                            "company_url,download_retails_id,download_company_code,download_company_url,download_userlogin,download_userpassword," +
                            "download_edit_eunoia_storeId,download_edit_eunoia_partnerId," +
                            "download_edit_eunoia_url,download_edit_eunoia_partnerEmail,shoptima_url,shoptima_user_id,shoptima_password,shoptima_machine_id,shoptima_mall_code," +
                            "qrcode_shoptima_url,qrcode_shoptima_user_id,qrcode_shoptima_password,qrcode_shoptima_machine_id,qrcode_shoptima_mall_code," +
                            "qrcode_shoptima_brand_code,qrcode_shoptima_outlet_code," +
                            "receipt_count,hotkey_col,hotkey_row,bal_mode,bal_title, bill_fontsize, option,maplayout, refer_info1_name, " +
                            "refer_info2_name, refer_info3_name, " +
                            "roundtype, language) VALUES ('"
                            + DBFunc.PurifyString(binding_imin.etName.getText()
                            .toString())
                            + "','"
                            + binding_imin.editTerminalId.getText().toString()
                            + "','"
                            + binding_imin.editStorenoId.getText().toString()
                            + "','"
                            + binding_imin.etReceiptNoReference.getText().toString()
                            + "','"
                            + binding_imin.editServerImageUploadUrl.getText().toString()
                            + "','"

                            //+ printerid.get(spn_printer.getSelectedItemPosition())
                            + rep_p_id
                            + "','"

                            + binding_imin.editRetailId.getText().toString()
                            + "','"

                            + binding_imin.editCompanyCode.getText().toString()
                            + "','"

                            + binding_imin.editCompanyUrl.getText().toString()
                            + "','"

                            + binding_imin.downloadEditRetailId.getText().toString()
                            + "','"

                            + binding_imin.downloadEditCompanyCode.getText().toString()
                            + "','"

                            + binding_imin.downloadEditCompanyUrl.getText().toString()
                            + "','"

                            + binding_imin.downloadUserLogin.getText().toString()
                            + "','"

                            + binding_imin.downloadUserPassword.getText().toString()
                            + "','"

                            + binding_imin.downloadEditEunoiaStoreId.getText().toString()
                            + "','"

                            + binding_imin.downloadEditEunoiaPartnerId.getText().toString()
                            + "','"

                            + binding_imin.downloadEditEunoiaUrl.getText().toString()
                            + "','"

                            + binding_imin.downloadEditEunoiaPartnerEmail.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaUrl.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaUserId.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaUserPassword.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaMachineId.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaMallCode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaUrlQrcode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaUserIdQrcode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaUserPasswordQrcode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaMallcodeQrcode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaMahcineidQrcode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaBrandQrcode.getText().toString()
                            + "','"

                            + binding_imin.editShoptimaOutletQrcode.getText().toString()
                            + "',"

                            + printcount
                            + ","

                            + (binding_imin.spnCol.getSelectedItemPosition())
                            + ","

                            + (binding_imin.spnRow.getSelectedItemPosition())
                            + ","

                            + (binding_imin.spnBaltype.getSelectedItemPosition())
                            + ",'"

                            + DBFunc.PurifyString(bal_title)
                            + "',"

                            //+ (spn_fontsize.getSelectedItemPosition() + 10)
                            + (bfont_size_)
                            + ",'" + DBFunc.PurifyString(option) + "'";

                    if (binding_imin.spnBaltype.getSelectedItemPosition() == 2) {
                        sql += "," + mapid.get(binding_imin.spnMapLay.getSelectedItemPosition());
                    } else {
                        sql += ",NULL";
                    }

                    if (info1.length() == 0) {
                        sql += ",NULL";
                    } else {
                        sql += ",'" + DBFunc.PurifyString(info1) + "'";
                    }

                    if (info2.length() == 0) {
                        sql += ",NULL";
                    } else {
                        sql += ",'" + DBFunc.PurifyString(info2) + "'";
                    }

                    if (info3.length() == 0) {
                        sql += ",NULL";
                    } else {
                        sql += ",'" + DBFunc.PurifyString(info3) + "'";
                    }


                    //sql += ","+spn_roundtype.getSelectedItemPosition();
                    sql += "," + round_type_id;

                    sql += ", " + langID.get(binding_imin.spnLang.getSelectedItemPosition());

                    sql += ")";

                    DBFunc.ExecQuery(sql, true);

                    selected_product_cateogry_id = -1;
                    selected_product_cateogry_name = "0";
                    selected_receipt_printer_id = -1;
                    selected_receipt_printer_name = "0";
                    selected_font_size_id = -1;
                    selected_font_size_name = "0";
                    selected_bluetooth_id = -1;
                    selected_bluetooth_name = "0";

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "POSConfig -> Update");

                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 22));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                }
            });
        } else {
            Log.i("device___","device__btnSave__"+device);
            binding.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("device___","device__btnSave__"+binding.etName.getText().toString());
                    if (binding.etName.getText().toString().isEmpty()) {
                        Log.i("device___","device__btnSave__"+binding.etName.getText().toString());
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 20));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }


                    int printcount = -1;
                    try {
                        printcount = Integer.parseInt(binding.etPrintcount.getText().toString());
                    } catch (NumberFormatException e) {
                    }
                    Log.i("device___","device__btnSave__"+printcount);
                    if (printcount < 0) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 26));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }


//                if (spn_printer.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
//                    DialogBox dlg1 = new DialogBox(v.getContext());
//                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
//                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 25));
//                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                    dlg1.show();
//                    return;
//                }

                    String bal_title = binding.etBalTitle.getText().toString();

                    Log.i("device___","device__bal_title___"+bal_title);

                    if (binding.spnBaltype.getSelectedItemPosition() > 0) {
                        if (bal_title.isEmpty()) {
                            DialogBox dlg1 = new DialogBox(v.getContext());
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 27));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                            return;
                        }

                        if (binding.spnBaltype.getSelectedItemPosition() == 2) {
                            if (binding.spnMapLay.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
                                DialogBox dlg1 = new DialogBox(v.getContext());
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                                dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 28));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                                return;
                            }
                        }

                    } else {
                        bal_title = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 33);
                    }

                    if (bal_title.length() > 10) {
                        bal_title = bal_title.substring(0, 10);
                    }

                    String option = "";
                    if (binding.chkPosSelectlast.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    Log.i("device___","device__boption___"+option);
                    if (binding.chkPosNoprintcondiqty.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosPaymentonlykp.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosHideNaviBar.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosUsermode.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosCheckupdate.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosReferfunction.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosRefercompulsory.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosReferinfo1print.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosReferinfo2print.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosReferinfo3print.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosUserovertake.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosNoprintunpaid.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosPrinterReceipt.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosCashDrawer.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosCustomerDisplay.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }

                    if (binding.chkPosBarcodeScanner.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosIntegrateShoptima.isChecked()) {
                        option += "1";

                        layoutShoptimaFun(View.VISIBLE, "Normal");
                    } else {
                        option += "0";
                        layoutShoptimaFun(View.GONE, "Normal");
                    }
                    if (binding.chkPosQrCodeShoptima.isChecked()) {
                        option += "1";

                        layoutShoptimaFun(View.VISIBLE, "QRCode");

                    } else {
                        option += "0";

                        layoutShoptimaFun(View.GONE, "QRCode");
                    }

                    if (binding.chkPosOnlineOrder.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosImageUrl.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosServiceCharges.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosRetail.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosReceiptPrintPaper.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosJerifood.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosBarcodeOnReceipt.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPos4DigitICNO.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosZclose.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    if (binding.chkPosKitchenPrinter.isChecked()) {
                        option += "1";
                    } else {
                        option += "0";
                    }
                    String info1 = binding.etInfo1.getText().toString();
                    if (info1.length() > 10) {
                        info1 = info1.substring(0, 10);
                    }

                    String info2 = binding.etInfo2.getText().toString();
                    if (info2.length() > 10) {
                        info2 = info2.substring(0, 10);
                    }

                    String info3 = binding.etInfo3.getText().toString();
                    if (info3.length() > 10) {
                        info3 = info3.substring(0, 10);
                    }


                    DBFunc.ExecQuery("DELETE FROM POSSys", true);

                    String rep_printer = "0";
                    if (selected_receipt_printer_name.equals("0")) {
                        rep_printer = binding.editReceiptPrinter.getText().toString();
                    } else {
                        rep_printer = selected_receipt_printer_name;
                    }
                    String rep_p_id = "0";
                    Cursor c1 = DBFunc.Query("SELECT id,name FROM Printer where name = '" + rep_printer + "'", true);
                    if (c1 != null) {
                        if (c1.moveToNext()) {
                            rep_p_id = c1.getString(0);
                        }
                        c1.close();
                    }
                    String round_type = "0";
                    if (selected_product_cateogry_name.equals("0")) {
                        round_type = binding.editRoundMethod.getText().toString();
                    } else {
                        round_type = selected_product_cateogry_name;
                    }
                    String round_type_id = "0";
                    if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 34))) {
                        round_type_id = "0";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 35))) {
                        round_type_id = "1";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 36))) {
                        round_type_id = "2";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 37))) {
                        round_type_id = "3";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 38))) {
                        round_type_id = "4";
                    } else if (round_type.equals(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 39))) {
                        round_type_id = "5";
                    }
                    String bfont_size_ = "0";
                    if (selected_font_size_name.equals("0")) {
                        bfont_size_ = binding.editBillListFontSize.getText().toString();
                    } else {
                        bfont_size_ = selected_font_size_name;
                        //bfont_size_ = "14";
                    }

                    Log.i("device___","device__boptionss___"+option);
//                if (Integer.parseInt(bfont_size_) == 0){
//                    bfont_size_ = "12";
//                }
                    String sql = "INSERT INTO POSSys (name,terminal_id,storeno,receipt_no_reference,server_image_upload_url,receipt_printer,retails_id,company_code," +
                            "company_url,download_retails_id,download_company_code,download_company_url,download_userlogin,download_userpassword," +
                            "download_edit_eunoia_storeId,download_edit_eunoia_partnerId," +
                            "download_edit_eunoia_url,download_edit_eunoia_partnerEmail,shoptima_url,shoptima_user_id,shoptima_password,shoptima_machine_id,shoptima_mall_code," +
                            "qrcode_shoptima_url,qrcode_shoptima_user_id,qrcode_shoptima_password,qrcode_shoptima_machine_id,qrcode_shoptima_mall_code," +
                            "qrcode_shoptima_brand_code,qrcode_shoptima_outlet_code," +
                            "receipt_count,hotkey_col,hotkey_row,bal_mode,bal_title, bill_fontsize, option,maplayout, refer_info1_name, " +
                            "refer_info2_name, refer_info3_name, " +
                            "roundtype, language) VALUES ('"
                            + DBFunc.PurifyString(binding.etName.getText()
                            .toString())
                            + "','"
                            + binding.editTerminalId.getText().toString()
                            + "','"
                            + binding.editStorenoId.getText().toString()
                            + "','"
                            + binding.etReceiptNoReference.getText().toString()
                            + "','"
                            + binding.editServerImageUploadUrl.getText().toString()
                            + "','"

                            //+ printerid.get(spn_printer.getSelectedItemPosition())
                            + rep_p_id
                            + "','"

                            + binding.editRetailId.getText().toString()
                            + "','"

                            + binding.editCompanyCode.getText().toString()
                            + "','"

                            + binding.editCompanyUrl.getText().toString()
                            + "','"

                            + binding.downloadEditRetailId.getText().toString()
                            + "','"

                            + binding.downloadEditCompanyCode.getText().toString()
                            + "','"

                            + binding.downloadEditCompanyUrl.getText().toString()
                            + "','"

                            + binding.downloadUserLogin.getText().toString()
                            + "','"

                            + binding.downloadUserPassword.getText().toString()
                            + "','"

                            + binding.downloadEditEunoiaStoreId.getText().toString()
                            + "','"

                            + binding.downloadEditEunoiaPartnerId.getText().toString()
                            + "','"

                            + binding.downloadEditEunoiaUrl.getText().toString()
                            + "','"

                            + binding.downloadEditEunoiaPartnerEmail.getText().toString()
                            + "','"

                            + binding.editShoptimaUrl.getText().toString()
                            + "','"

                            + binding.editShoptimaUserId.getText().toString()
                            + "','"

                            + binding.editShoptimaUserPassword.getText().toString()
                            + "','"

                            + binding.editShoptimaMachineId.getText().toString()
                            + "','"

                            + binding.editShoptimaMallCode.getText().toString()
                            + "','"

                            + binding.editShoptimaUrlQrcode.getText().toString()
                            + "','"

                            + binding.editShoptimaUserIdQrcode.getText().toString()
                            + "','"

                            + binding.editShoptimaUserPasswordQrcode.getText().toString()
                            + "','"

                            + binding.editShoptimaMallcodeQrcode.getText().toString()
                            + "','"

                            + binding.editShoptimaMahcineidQrcode.getText().toString()
                            + "','"

                            + binding.editShoptimaBrandQrcode.getText().toString()
                            + "','"

                            + binding.editShoptimaOutletQrcode.getText().toString()
                            + "',"

                            + printcount
                            + ","

                            + (binding.spnCol.getSelectedItemPosition())
                            + ","

                            + (binding.spnRow.getSelectedItemPosition())
                            + ","

                            + (binding.spnBaltype.getSelectedItemPosition())
                            + ",'"

                            + DBFunc.PurifyString(bal_title)
                            + "',"

                            //+ (spn_fontsize.getSelectedItemPosition() + 10)
                            + (bfont_size_)
                            + ",'" + DBFunc.PurifyString(option) + "'";

                    if (binding.spnBaltype.getSelectedItemPosition() == 2) {
                        sql += "," + mapid.get(binding.spnMapLay.getSelectedItemPosition());
                    } else {
                        sql += ",NULL";
                    }

                    if (info1.length() == 0) {
                        sql += ",NULL";
                    } else {
                        sql += ",'" + DBFunc.PurifyString(info1) + "'";
                    }

                    if (info2.length() == 0) {
                        sql += ",NULL";
                    } else {
                        sql += ",'" + DBFunc.PurifyString(info2) + "'";
                    }

                    if (info3.length() == 0) {
                        sql += ",NULL";
                    } else {
                        sql += ",'" + DBFunc.PurifyString(info3) + "'";
                    }


                    //sql += ","+spn_roundtype.getSelectedItemPosition();
                    sql += "," + round_type_id;

                    sql += ", " + langID.get(binding.spnLang.getSelectedItemPosition());

                    sql += ")";

                    Log.i("device___","dsqle__btnSave__"+sql);
                    DBFunc.ExecQuery(sql, true);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Saved-POSConfiguration- " + sql));

                    selected_product_cateogry_id = -1;
                    selected_product_cateogry_name = "0";
                    selected_receipt_printer_id = -1;
                    selected_receipt_printer_name = "0";
                    selected_font_size_id = -1;
                    selected_font_size_name = "0";
                    selected_bluetooth_id = -1;
                    selected_bluetooth_name = "0";

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "POSConfig -> Update");

                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 22));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                }
            });
        }

        Cursor c = DBFunc.Query("SELECT id,name FROM Printer ORDER BY id ASC",true);
        if (c != null) {
            while (c.moveToNext()) {
                printerid.add(c.getInt(0));
                adapt_printer.add(c.getString(1));
            }
            c.close();
        }

        c = DBFunc.Query("SELECT id,name FROM MapLayout ORDER BY id ASC",true);
        if (c != null) {
            while (c.moveToNext()) {
                mapid.add(c.getInt(0));
                adapt_maplayout.add(c.getString(1));
            }
            c.close();
        }

        c = DBFunc.Query("SELECT id,name FROM LangList ORDER BY id ASC",true);
        if (c != null) {
            while (c.moveToNext()) {
                langID.add(c.getInt(0));
                adapt_lang.add(c.getString(1));
            }
            c.close();
        }

        c = DBFunc.Query("SELECT name,receipt_printer,receipt_count,hotkey_col,hotkey_row,bal_mode," +
                "bal_title,bill_fontsize,option,maplayout,refer_info1_name," +
                "refer_info2_name,refer_info3_name,roundtype,language,retails_id," +
                "company_code,company_url,shoptima_url,shoptima_user_id,shoptima_password," +
                "shoptima_machine_id,shoptima_mall_code,qrcode_shoptima_url,qrcode_shoptima_user_id,qrcode_shoptima_password," +
                "qrcode_shoptima_machine_id,qrcode_shoptima_mall_code,qrcode_shoptima_brand_code,qrcode_shoptima_outlet_code,terminal_id," +
                "download_retails_id,download_company_code,download_company_url,storeno,server_image_upload_url," +
                "download_edit_eunoia_storeId,download_edit_eunoia_partnerId,download_edit_eunoia_url,download_edit_eunoia_partnerEmail," +
                "download_userlogin,download_userpassword,receipt_no_reference  " +
                " FROM POSSys LIMIT 1",true);
        if (c != null) {
            if (c.moveToNext()) {
                if (device.equals("M2-Max")) {
                    binding_imin.etName.setText(c.getString(0));
                    binding_imin.etReceiptNoReference.setText(c.getString(42));
                    binding_imin.editTerminalId.setText(c.getString(30));
                    binding_imin.editStorenoId.setText(c.getString(34));
                    binding_imin.editServerImageUploadUrl.setText(c.getString(35));
                } else {
                    binding.etName.setText(c.getString(0));
                    binding.etReceiptNoReference.setText(c.getString(42));
                    binding.editTerminalId.setText(c.getString(30));
                    binding.editStorenoId.setText(c.getString(34));
                    binding.editServerImageUploadUrl.setText(c.getString(35));
                }

                Cursor c1 = DBFunc.Query("SELECT id,name FROM Printer where id = "+c.getInt(1),true);
                if (c1 != null) {
                    if (c1.moveToNext()) {
                        if (device.equals("M2-Max")) {
                            binding_imin.editReceiptPrinter.setText(c1.getString(1));
                        } else {
                            binding.editReceiptPrinter.setText(c1.getString(1));
                        }
                    }
                    c1.close();
                }
                String round_type_id = c.getString(13);
                String round_type_name = "0";
                if (round_type_id.equals("0")){
                    round_type_name = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 34);
                }else if (round_type_id.equals("1")){
                    round_type_name = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 35);
                }else if (round_type_id.equals("2")){
                    round_type_name = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 36);
                }else if (round_type_id.equals("3")){
                    round_type_name = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 37);
                }else if (round_type_id.equals("4")){
                    round_type_name = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 38);
                }else if (round_type_id.equals("5")){
                    round_type_name = StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 39);
                }
                Log.i("device____","device_round_type_name_"+device);
                if (device.equals("M2-Max")) {
                    binding_imin.editRoundMethod.setText(round_type_name);
                    binding_imin.editBillListFontSize.setText(c.getString(7));

                    binding_imin.editRetailId.setText(c.getString(15));
                    //binding_imin.editRetailId.setText("1");
                    //edit_retail_id.setText("4");
                    //edit_retail_id.setText("2");
                    //edit_retail_id.setText("1");
                    //binding.editCompanyCode.setText(c.getString(16));
                    binding_imin.editCompanyCode.setText(c.getString(16));
                    //binding_imin.editCompanyCode.setText("kiddy");
//                binding.editCompanyCode.setText("yuxuanBG113");
//                edit_company_code.setText("TASTY");
                    //edit_company_code.setText("salon");
                    //edit_company_code.setText("MACLINK");
                    binding_imin.editCompanyUrl.setText(c.getString(17));
                    //binding_imin.editCompanyUrl.setText("http://129.126.111.216:44343/myRetailerAPI.asmx");
//                binding.editCompanyUrl.setText("http://myretailer360.dyndns.biz:44308/myRetailerAPI.asmx");

                    //edit_retail_id.setText("2");
                    //edit_retail_id.setText("1");
                    //edit_company_code.setText("salon");
                    //edit_company_code.setText("salon");
                    //edit_company_code.setText("MACLINK");
                    //edit_company_url.setText("http://49.128.60.174:44343/myRetailerAPI.asmx");

                    binding_imin.downloadEditRetailId.setText(c.getString(31));
                    binding_imin.downloadEditCompanyCode.setText(c.getString(32));
//                binding.downloadEditCompanyCode.setText("KIDDY");
//                binding.downloadEditCompanyCode.setText("TESTHPB");
                    binding_imin.downloadEditCompanyUrl.setText(c.getString(33));
//                binding.downloadEditCompanyUrl.setText("http://129.126.111.216:44343/myRetailerAPI.asmx");
//                binding.downloadEditCompanyUrl.setText("https://myretailer360.synthesis.bz:44309/myRetailerAPI.asmx");
                    binding_imin.downloadUserLogin.setText(c.getString(40));
//                binding.downloadUserLogin.setText("0001");
                    binding_imin.downloadUserPassword.setText(c.getString(41));
//                binding.downloadUserPassword.setText("001001");


//                //Log.i("dfdf___","jfodfdijfdf35___"+c.getString(35));
//                Log.i("dfdf___","jfodfdijfdf36___"+c.getString(36));
//                Log.i("dfdf___","jfodfdijfdf37___"+c.getString(37));
                    binding_imin.downloadEditEunoiaStoreId.setText(c.getString(36));
                    binding_imin.downloadEditEunoiaPartnerId.setText(c.getString(37));
                    binding_imin.downloadEditEunoiaUrl.setText(c.getString(38));
                    binding_imin.downloadEditEunoiaPartnerEmail.setText(c.getString(39));


//                binding.downloadEditEunoiaStoreId.setText("632");
//                binding.downloadEditEunoiaPartnerId.setText("WEEBO");
//                binding.downloadEditEunoiaUrl.setText("http://jerifoodeunoiastage.jeripay.com/api");
//                binding.downloadEditEunoiaPartnerEmail.setText("test@weebo.com");

                    //download_edit_retail_id.setText("4");
                    //download_edit_retail_id.setText("1");
                    //download_edit_retail_id.setText("1");
                    //download_edit_retail_id.setText("2");
                    //download_edit_company_code.setText("MACLINK");
                    //download_edit_company_code.setText("KIDDY");
                    //download_edit_company_code.setText("CafeDemo");
                    //download_edit_company_url.setText("http://49.128.60.174:44343/myRetailerAPI.asmx");

//                http://llposmgr.ddns.net/POSManager.asmx
//                POS
//                dcsadmin@123
//                5000440
//                65100001

                    binding_imin.editShoptimaUrl.setText(c.getString(18));
                    binding_imin.editShoptimaUserId.setText(c.getString(19));
                    binding_imin.editShoptimaUserPassword.setText(c.getString(20));
                    binding_imin.editShoptimaMachineId.setText(c.getString(21));
                    binding_imin.editShoptimaMallCode.setText(c.getString(22));
//                edit_shoptima_url.setText("http://llposmgr.ddns.net/POSManager.asmx");
//                edit_shoptima_user_id.setText("POS");
//                edit_shoptima_user_password.setText("dcsadmin@123");
//                edit_shoptima_machine_id.setText("5000440");
//                edit_shoptima_mall_code.setText("65100001");
//
                    binding_imin.editShoptimaUrlQrcode.setText(c.getString(23));
                    binding_imin.editShoptimaUserIdQrcode.setText(c.getString(24));
                    binding_imin.editShoptimaUserPasswordQrcode.setText(c.getString(25));
                    binding_imin.editShoptimaMallcodeQrcode.setText(c.getString(26));
                    binding_imin.editShoptimaMahcineidQrcode.setText(c.getString(27));
                    binding_imin.editShoptimaBrandQrcode.setText(c.getString(28));
                    binding_imin.editShoptimaOutletQrcode.setText(c.getString(29));
//
//                edit_shoptima_url_qrcode.setText("http://llposmgr.ddns.net:8085/Service.asmx");
//                edit_shoptima_user_id_qrcode.setText("dcssupport");
//                edit_shoptima_user_password_qrcode.setText("dcsadmin@12345");
//                edit_shoptima_mallcode_qrcode.setText("30200055");
//                edit_shoptima_mahcineid_qrcode.setText("65100004");
//                edit_shoptima_brand_qrcode.setText("Keisuke");
//                edit_shoptima_outlet_qrcode.setText("Parkway");

//                if (!c.isNull(1)) {
//                    for (int i = 0; i < printerid.size(); i++) {
//                        if (c.getInt(1) == printerid.get(i)) {
//                            spn_printer.setSelection(i);
//                            break;
//                        }
//                    }
//                }
                } else {
                    Log.i("device____","device_round_type_name_"+round_type_name);

                    binding.editRoundMethod.setText(round_type_name);
                    Log.i("device____","device_round_type_name1_"+binding.editRoundMethod.getText().toString());
                    binding.editBillListFontSize.setText(c.getString(7));

                    binding.editRetailId.setText(c.getString(15));
//                    binding.editRetailId.setText("1");
                    //edit_retail_id.setText("4");
                    //edit_retail_id.setText("2");
                    //edit_retail_id.setText("1");
                    //binding.editCompanyCode.setText(c.getString(16));
                    binding.editCompanyCode.setText(c.getString(16));
//                    binding.editCompanyCode.setText("kiddy");
//                binding.editCompanyCode.setText("yuxuanBG113");
//                edit_company_code.setText("TASTY");
                    //edit_company_code.setText("salon");
                    //edit_company_code.setText("MACLINK");
                    binding.editCompanyUrl.setText(c.getString(17));
//                    binding.editCompanyUrl.setText("http://129.126.111.216:44343/myRetailerAPI.asmx");
//                binding.editCompanyUrl.setText("http://myretailer360.dyndns.biz:44308/myRetailerAPI.asmx");

                    //edit_retail_id.setText("2");
                    //edit_retail_id.setText("1");
                    //edit_company_code.setText("salon");
                    //edit_company_code.setText("salon");
                    //edit_company_code.setText("MACLINK");
                    //edit_company_url.setText("http://49.128.60.174:44343/myRetailerAPI.asmx");

                    binding.downloadEditRetailId.setText(c.getString(31));
                    binding.downloadEditCompanyCode.setText(c.getString(32));
//                binding.downloadEditCompanyCode.setText("KIDDY");
//                binding.downloadEditCompanyCode.setText("TESTHPB");
                    binding.downloadEditCompanyUrl.setText(c.getString(33));
//                binding.downloadEditCompanyUrl.setText("http://129.126.111.216:44343/myRetailerAPI.asmx");
//                binding.downloadEditCompanyUrl.setText("https://myretailer360.synthesis.bz:44309/myRetailerAPI.asmx");
                    binding.downloadUserLogin.setText(c.getString(40));
//                binding.downloadUserLogin.setText("0001");
                    binding.downloadUserPassword.setText(c.getString(41));
//                binding.downloadUserPassword.setText("001001");


//                //Log.i("dfdf___","jfodfdijfdf35___"+c.getString(35));
//                Log.i("dfdf___","jfodfdijfdf36___"+c.getString(36));
//                Log.i("dfdf___","jfodfdijfdf37___"+c.getString(37));
                    binding.downloadEditEunoiaStoreId.setText(c.getString(36));
                    binding.downloadEditEunoiaPartnerId.setText(c.getString(37));
                    binding.downloadEditEunoiaUrl.setText(c.getString(38));
                    binding.downloadEditEunoiaPartnerEmail.setText(c.getString(39));


//                binding.downloadEditEunoiaStoreId.setText("632");
//                binding.downloadEditEunoiaPartnerId.setText("WEEBO");
//                binding.downloadEditEunoiaUrl.setText("http://jerifoodeunoiastage.jeripay.com/api");
//                binding.downloadEditEunoiaPartnerEmail.setText("test@weebo.com");

                    //download_edit_retail_id.setText("4");
                    //download_edit_retail_id.setText("1");
                    //download_edit_retail_id.setText("1");
                    //download_edit_retail_id.setText("2");
                    //download_edit_company_code.setText("MACLINK");
                    //download_edit_company_code.setText("KIDDY");
                    //download_edit_company_code.setText("CafeDemo");
                    //download_edit_company_url.setText("http://49.128.60.174:44343/myRetailerAPI.asmx");

//                http://llposmgr.ddns.net/POSManager.asmx
//                POS
//                dcsadmin@123
//                5000440
//                65100001

                    binding.editShoptimaUrl.setText(c.getString(18));
                    binding.editShoptimaUserId.setText(c.getString(19));
                    binding.editShoptimaUserPassword.setText(c.getString(20));
                    binding.editShoptimaMachineId.setText(c.getString(21));
                    binding.editShoptimaMallCode.setText(c.getString(22));
//                edit_shoptima_url.setText("http://llposmgr.ddns.net/POSManager.asmx");
//                edit_shoptima_user_id.setText("POS");
//                edit_shoptima_user_password.setText("dcsadmin@123");
//                edit_shoptima_machine_id.setText("5000440");
//                edit_shoptima_mall_code.setText("65100001");
//
                    binding.editShoptimaUrlQrcode.setText(c.getString(23));
                    binding.editShoptimaUserIdQrcode.setText(c.getString(24));
                    binding.editShoptimaUserPasswordQrcode.setText(c.getString(25));
                    binding.editShoptimaMallcodeQrcode.setText(c.getString(26));
                    binding.editShoptimaMahcineidQrcode.setText(c.getString(27));
                    binding.editShoptimaBrandQrcode.setText(c.getString(28));
                    binding.editShoptimaOutletQrcode.setText(c.getString(29));
//
//                edit_shoptima_url_qrcode.setText("http://llposmgr.ddns.net:8085/Service.asmx");
//                edit_shoptima_user_id_qrcode.setText("dcssupport");
//                edit_shoptima_user_password_qrcode.setText("dcsadmin@12345");
//                edit_shoptima_mallcode_qrcode.setText("30200055");
//                edit_shoptima_mahcineid_qrcode.setText("65100004");
//                edit_shoptima_brand_qrcode.setText("Keisuke");
//                edit_shoptima_outlet_qrcode.setText("Parkway");

//                if (!c.isNull(1)) {
//                    for (int i = 0; i < printerid.size(); i++) {
//                        if (c.getInt(1) == printerid.get(i)) {
//                            spn_printer.setSelection(i);
//                            break;
//                        }
//                    }
//                }
                }

                int count = c.getInt(2);
                if(count<0)count = 1;
                if (device.equals("M2-Max")) {
                    binding_imin.etPrintcount.setText("" + count);
                } else {
                    binding.etPrintcount.setText("" + count);
                }

                int col = c.getInt(3);
                int row = c.getInt(4);

                if (col < 0)
                    col = 0;
                if (col >= adapt_col.getCount())
                    col = adapt_col.getCount() - 1;

                if (row < 0)
                    row = 0;
                if (row >= adapt_row.getCount())
                    row = adapt_row.getCount() - 1;

                if (device.equals("M2-Max")) {
                    binding_imin.spnCol.setSelection(col);
                    binding_imin.spnRow.setSelection(row);
                } else {
                    binding.spnCol.setSelection(col);
                    binding.spnRow.setSelection(row);
                }


                int val = c.getInt(5);
                if(val<0)val = 0;
                if(val>2)val = 2;

                if (device.equals("M2-Max")) {
                    binding_imin.spnBaltype.setSelection(val);
                    binding_imin.spnMapLay.setEnabled(false);
                    if (binding_imin.spnBaltype.getSelectedItemPosition() == 0) {
                        binding_imin.editBalanceTitle.setEnabled(false);
                    } else {
                        binding_imin.editBalanceTitle.setEnabled(true);
                        if (binding_imin.spnBaltype.getSelectedItemPosition() == 2) {
                            binding_imin.spnMapLay.setEnabled(true);
                            binding_imin.spnMapLay.setSelection(0);
                            if (!c.isNull(9)) {

                                for (int i = 0; i < mapid.size(); i++) {
                                    if (c.getInt(9) == mapid.get(i)) {
                                        binding_imin.spnMapLay.setSelection(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {

                    binding.spnBaltype.setSelection(val);
                    binding.spnMapLay.setEnabled(false);
                    if (binding.spnBaltype.getSelectedItemPosition() == 0) {
                        binding.editBalanceTitle.setEnabled(false);
                    } else {
                        binding.editBalanceTitle.setEnabled(true);
                        if (binding.spnBaltype.getSelectedItemPosition() == 2) {
                            binding.spnMapLay.setEnabled(true);
                            binding.spnMapLay.setSelection(0);
                            if (!c.isNull(9)) {

                                for (int i = 0; i < mapid.size(); i++) {
                                    if (c.getInt(9) == mapid.get(i)) {
                                        binding.spnMapLay.setSelection(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if (c.isNull(6)) {
                    if (device.equals("M2-Max")) {
                        binding_imin.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 32));
                    } else {
                        binding.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 32));
                    }
                } else {
                    if (c.getString(6).isEmpty()) {
                        if (device.equals("M2-Max")) {
                            binding_imin.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 32));
                        } else {
                            binding.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 32));
                        }
                    } else {
                        if (device.equals("M2-Max")) {
                            binding_imin.editBalanceTitle.setText(c.getString(6));
                        } else {
                            binding.editBalanceTitle.setText(c.getString(6));
                        }
                    }
                }

                int fontsize = c.getInt(7);
                if (fontsize < 10)
                    fontsize = 10;
                if (fontsize > 40)
                    fontsize = 40;

                //spn_fontsize.setSelection(fontsize - 10);
                //edit_font_size.setText(selected_font_size_name);

                if (device.equals("M2-Max")) {
                    binding_imin.chkPosSelectlast.setChecked(false);
                    binding_imin.chkPosNoprintcondiqty.setChecked(false);
                    binding_imin.chkPosPaymentonlykp.setChecked(false);
                    binding_imin.chkPosHideNaviBar.setChecked(false);
                    binding_imin.chkPosUsermode.setChecked(false);
                    binding_imin.chkPosCheckupdate.setChecked(false);
                    binding_imin.chkPosReferfunction.setChecked(false);
                    binding_imin.chkPosReferinfo1print.setChecked(false);
                    binding_imin.chkPosReferinfo2print.setChecked(false);
                    binding_imin.chkPosReferinfo3print.setChecked(false);
                    binding_imin.chkPosUserovertake.setChecked(false);
                    binding_imin.chkPosNoprintunpaid.setChecked(false);
                    binding_imin.chkPosPrinterReceipt.setChecked(false);
                    binding_imin.chkPosCashDrawer.setChecked(false);
                    binding_imin.chkPosCustomerDisplay.setChecked(false);
                    binding_imin.chkPosBarcodeScanner.setChecked(false);
                    binding_imin.chkPosIntegrateShoptima.setChecked(false);
                    binding_imin.chkPosQrCodeShoptima.setChecked(false);
                    binding_imin.chkPosOnlineOrder.setChecked(false);
                    binding_imin.chkPosImageUrl.setChecked(false);
                    binding_imin.chkPosServiceCharges.setChecked(false);
                    binding_imin.chkPosRetail.setChecked(false);
                    binding_imin.chkPosReceiptPrintPaper.setChecked(false);
                    binding_imin.chkPosJerifood.setChecked(false);
                    binding_imin.chkPosBarcodeOnReceipt.setChecked(false);
                    binding_imin.chkPos4DigitICNO.setChecked(false);
                    binding_imin.chkPosZclose.setChecked(false);
                    binding_imin.chkPosKitchenPrinter.setChecked(false);
                } else {
                    binding.chkPosSelectlast.setChecked(false);
                    binding.chkPosNoprintcondiqty.setChecked(false);
                    binding.chkPosPaymentonlykp.setChecked(false);
                    binding.chkPosHideNaviBar.setChecked(false);
                    binding.chkPosUsermode.setChecked(false);
                    binding.chkPosCheckupdate.setChecked(false);
                    binding.chkPosReferfunction.setChecked(false);
                    binding.chkPosReferinfo1print.setChecked(false);
                    binding.chkPosReferinfo2print.setChecked(false);
                    binding.chkPosReferinfo3print.setChecked(false);
                    binding.chkPosUserovertake.setChecked(false);
                    binding.chkPosNoprintunpaid.setChecked(false);
                    binding.chkPosPrinterReceipt.setChecked(false);
                    binding.chkPosCashDrawer.setChecked(false);
                    binding.chkPosCustomerDisplay.setChecked(false);
                    binding.chkPosBarcodeScanner.setChecked(false);
                    binding.chkPosIntegrateShoptima.setChecked(false);
                    binding.chkPosQrCodeShoptima.setChecked(false);
                    binding.chkPosOnlineOrder.setChecked(false);
                    binding.chkPosImageUrl.setChecked(false);
                    binding.chkPosServiceCharges.setChecked(false);
                    binding.chkPosRetail.setChecked(false);
                    binding.chkPosReceiptPrintPaper.setChecked(false);
                    binding.chkPosJerifood.setChecked(false);
                    binding.chkPosBarcodeOnReceipt.setChecked(false);
                    binding.chkPos4DigitICNO.setChecked(false);
                    binding.chkPosZclose.setChecked(false);
                    binding.chkPosKitchenPrinter.setChecked(false);
                }
                saveCxPrinterReceipt = "0";
                saveCxCashDrawer = "0";
                saveCxCustomerDisplay = "0";
                saveCxBarCodeScanner = "0";

                String option = c.getString(8);
                if (option.length() > 0) {
                    if (option.charAt(0) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosSelectlast.setChecked(true);
                        } else {
                            binding.chkPosSelectlast.setChecked(true);
                        }
                    }
                }
                if (option.length() > 1) {
                    if (option.charAt(1) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosNoprintcondiqty.setChecked(true);
                        }else {
                            binding.chkPosNoprintcondiqty.setChecked(true);
                        }
                    }
                }
                if (option.length() > 2) {
                    if (option.charAt(2) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosPaymentonlykp.setChecked(true);
                        }else {
                            binding.chkPosPaymentonlykp.setChecked(true);
                        }
                    }
                }
                if (option.length() > 3) {
                    if (option.charAt(3) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosHideNaviBar.setChecked(true);
                        }else {
                            binding.chkPosHideNaviBar.setChecked(true);
                        }
                    }
                }
                if (option.length() > 4) {
                    if (option.charAt(4) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosUsermode.setChecked(true);
                        }else {
                            binding.chkPosUsermode.setChecked(true);
                        }
                    }
                }
                if (option.length() > 5) {
                    if (option.charAt(5) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosCheckupdate.setChecked(true);
                        }else {
                            binding.chkPosCheckupdate.setChecked(true);
                        }
                    }
                }
                if (option.length() > 6) {
                    if (option.charAt(6) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosReferfunction.setChecked(true);
                        }else {
                            binding.chkPosReferfunction.setChecked(true);
                        }
                    }
                }
                if (option.length() > 7) {
                    if (option.charAt(7) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosRefercompulsory.setChecked(true);
                        }else {
                            binding.chkPosRefercompulsory.setChecked(true);
                        }
                    }
                }
                if (option.length() > 8) {
                    if (option.charAt(8) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosReferinfo1print.setChecked(true);
                        } else {
                            binding.chkPosReferinfo1print.setChecked(true);
                        }
                    }
                }
                if (option.length() > 9) {
                    if (option.charAt(9) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosReferinfo2print.setChecked(true);
                        }else {
                            binding.chkPosReferinfo2print.setChecked(true);
                        }
                    }
                }
                if (option.length() > 10) {
                    if (option.charAt(10) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosReferinfo3print.setChecked(true);
                        }else {
                            binding.chkPosReferinfo3print.setChecked(true);
                        }
                    }
                }
                if (option.length() > 11) {
                    if (option.charAt(11) == '1') {
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosUserovertake.setChecked(true);
                        }else {
                            binding.chkPosUserovertake.setChecked(true);
                        }
                    }
                }
                if (option.length() > 12) {
                    if (option.charAt(12) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosNoprintunpaid.setChecked(true);
                        }else {
                            binding.chkPosNoprintunpaid.setChecked(true);
                        }
                    }
                }
                if (option.length() > 13) {
                    if (option.charAt(13) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosPrinterReceipt.setChecked(true);
                        }else {
                            binding.chkPosPrinterReceipt.setChecked(true);
                        }
                        saveCxPrinterReceipt = "1";
                    }
                }
                if (option.length() > 14) {
                    if (option.charAt(14) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosCashDrawer.setChecked(true);
                        }else {
                            binding.chkPosCashDrawer.setChecked(true);
                        }
                        saveCxCashDrawer = "1";
                    }
                }
                if (option.length() > 15) {
                    if (option.charAt(15) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosCustomerDisplay.setChecked(true);
                        } else {
                            binding.chkPosCustomerDisplay.setChecked(true);
                        }
                        saveCxCustomerDisplay = "1";
                    }
                }
                if (option.length() > 16) {
                    if (option.charAt(16) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosBarcodeScanner.setChecked(true);
                        } else {
                            binding.chkPosBarcodeScanner.setChecked(true);
                        }
                        saveCxBarCodeScanner = "1";
                    }
                }
                if (option.length() > 17) {
                    if (option.charAt(17) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosIntegrateShoptima.setChecked(true);
                            if (binding_imin.chkPosIntegrateShoptima.isChecked()) {
                                layoutShoptimaFun(View.VISIBLE, "Normal");
                            }
                        } else {
                            binding.chkPosIntegrateShoptima.setChecked(true);
                            if (binding.chkPosIntegrateShoptima.isChecked()) {
                                layoutShoptimaFun(View.VISIBLE, "Normal");
                            }
                        }

                    }
                }
                if (option.length() > 18) {
                    if (option.charAt(18) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosQrCodeShoptima.setChecked(true);

                            if (binding_imin.chkPosQrCodeShoptima.isChecked()) {
                                layoutShoptimaFun(View.VISIBLE, "QRCode");
                            }
                        }else {
                            binding.chkPosQrCodeShoptima.setChecked(true);

                            if (binding.chkPosQrCodeShoptima.isChecked()) {
                                layoutShoptimaFun(View.VISIBLE, "QRCode");
                            }
                        }
                    }
                }
                if (option.length() > 19) {
                    if (option.charAt(19) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosOnlineOrder.setChecked(true);
                        }else {
                            binding.chkPosOnlineOrder.setChecked(true);
                        }
                    }
                }
                if (option.length() > 20) {
                    if (option.charAt(20) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosImageUrl.setChecked(true);
                        } else {
                            binding.chkPosImageUrl.setChecked(true);
                        }
                    }
                }
                if (option.length() > 21) {
                    if (option.charAt(21) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosServiceCharges.setChecked(true);
                        } else {
                            binding.chkPosServiceCharges.setChecked(true);
                        }
                    }
                }
                if (option.length() > 22) {
                    if (option.charAt(22) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosRetail.setChecked(true);
                        } else {
                            binding.chkPosRetail.setChecked(true);
                        }
                    }
                }
                if (option.length() > 23) {
                    if (option.charAt(23) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosReceiptPrintPaper.setChecked(true);
                        }else {
                            binding.chkPosReceiptPrintPaper.setChecked(true);
                        }
                    }
                }
                if (option.length() > 24) {
                    if (option.charAt(24) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosJerifood.setChecked(true);
                        }else {
                            binding.chkPosJerifood.setChecked(true);
                        }
                    }
                }
                if (option.length() > 25) {
                    if (option.charAt(25) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosBarcodeOnReceipt.setChecked(true);
                        }else {
                            binding.chkPosBarcodeOnReceipt.setChecked(true);
                        }
                    }
                }
                if (option.length() > 26) {
                    if (option.charAt(26) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPos4DigitICNO.setChecked(true);
                        }else {
                            binding.chkPos4DigitICNO.setChecked(true);
                        }
                    }
                }
                if (option.length() > 27) {
                    if (option.charAt(27) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosZclose.setChecked(true);
                        }else {
                            binding.chkPosZclose.setChecked(true);
                        }
                    }
                }
                if (option.length() > 28) {
                    if (option.charAt(28) == '1'){
                        if (device.equals("M2-Max")) {
                            binding_imin.chkPosKitchenPrinter.setChecked(true);
                        }else {
                            binding.chkPosKitchenPrinter.setChecked(true);
                        }
                    }
                }
                db_config.deleteCx();
                db_config.saveCx("Cx400",saveCxPrinterReceipt,saveCxCashDrawer,saveCxCustomerDisplay,saveCxBarCodeScanner);

                if (device.equals("M2-Max")) {
                    if (c.isNull(10)) {
                        binding_imin.etInfo1.setText("");
                    } else {
                        binding_imin.etInfo1.setText(c.getString(10));
                    }
                    if(c.isNull(11)){
                        binding_imin.etInfo2.setText("");
                    }else{
                        binding_imin.etInfo2.setText(c.getString(11));
                    }

                    if(c.isNull(12)){
                        binding_imin.etInfo3.setText("");
                    }else{
                        binding_imin.etInfo3.setText(c.getString(12));
                    }
                }else {
                    if (c.isNull(10)) {
                        binding.etInfo1.setText("");
                    } else {
                        binding.etInfo1.setText(c.getString(10));
                    }
                    if(c.isNull(11)){
                        binding.etInfo2.setText("");
                    }else{
                        binding.etInfo2.setText(c.getString(11));
                    }

                    if(c.isNull(12)){
                        binding.etInfo3.setText("");
                    }else{
                        binding.etInfo3.setText(c.getString(12));
                    }
                }



//                int round = 0;
//                if(!c.isNull(13)){
//                    round = c.getInt(13);
//                }
//
//                if(round<0)round = 0;
//                if(round>=adapt_roundtype.getCount()){
//                    round = 0;
//                }
//                spn_roundtype.setSelection(round);
//                Log.i("dfds__", String.valueOf(round));
                int round = c.getInt(13);

                if (device.equals("M2-Max")) {
                    if (round == 0){
                        binding_imin.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 34));
                    }else if (round == 1){
                        binding_imin.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 35));
                    }else if (round == 2) {
                        binding_imin.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 36));
                    }else if (round == 3){
                        binding_imin.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 37));
                    }else if (round == 4){
                        binding_imin.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 38));
                    }else if (round == 5){
                        binding_imin.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 39));
                    }
                } else {
                    if (round == 0){
                        binding.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 34));
                    }else if (round == 1){
                        binding.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 35));
                    }else if (round == 2) {
                        binding.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 36));
                    }else if (round == 3){
                        binding.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 37));
                    }else if (round == 4){
                        binding.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 38));
                    }else if (round == 5){
                        binding.editRoundMethod.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 39));
                    }
                }


                if (!c.isNull(14)) {
                    for (int i = 0; i < langID.size(); i++) {
                        if (c.getInt(14) == langID.get(i)) {
                            if (device.equals("M2-Max")) {
                                binding_imin.spnLang.setSelection(i);
                            } else {
                                binding.spnLang.setSelection(i);
                            }
                            break;
                        }
                    }
                }



                c.close();
            }
        }

        if (device.equals("M2-Max")) {
            binding_imin.chkPosUsermode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                    if(isChecked){

                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.WARNING);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 21));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

            });
        } else {
            binding.chkPosUsermode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                    if(isChecked){

                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.WARNING);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 21));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

            });
        }


        this.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 0));
        if (device.equals("M2-Max")) {
            //lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 100));
            binding_imin.lbl2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 101));
            //lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 102));
            binding_imin.lbl4.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 103));
            binding_imin.lbl5.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 104));
            binding_imin.lbl6.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 105));
            binding_imin.lbl7.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 106));
            binding_imin.lbl8.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 107));
            //lbl_9.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 108));
            //lbl_10.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 109));
            //lbl_11.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 110));
            //lbl_12.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 111));
            //lbl_13.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 1));
            //lbl_14.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
            //lbl_15.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 125));

            binding_imin.chkPosSelectlast.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 112));
            binding_imin.chkPosNoprintcondiqty.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 113));
            binding_imin.chkPosPaymentonlykp.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 114));
            binding_imin.chkPosHideNaviBar.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 115));
            binding_imin.chkPosUsermode.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 116));
            binding_imin.chkPosCheckupdate.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 117));
            binding_imin.chkPosReferfunction.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 118));
            binding_imin.chkPosRefercompulsory.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 119));
            binding_imin.chkPosReferinfo1print.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 120));
            binding_imin.chkPosReferinfo2print.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 121));
            binding_imin.chkPosReferinfo3print.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 122));
            binding_imin.chkPosUserovertake.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 123));
            binding_imin.chkPosNoprintunpaid.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 124));
            binding_imin.chkPosPrinterReceipt.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 126));
            binding_imin.chkPosCashDrawer.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 127));
            binding_imin.chkPosCustomerDisplay.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 128));
            binding_imin.chkPosBarcodeScanner.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 129));
            binding_imin.chkPosIntegrateShoptima.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 130));
            binding_imin.chkPosQrCodeShoptima.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 131));
            binding_imin.chkPosOnlineOrder.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 132));
            binding_imin.chkPosImageUrl.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 133));
            binding_imin.chkPosServiceCharges.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 134));
            binding_imin.chkPosRetail.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 135));
            binding_imin.chkPosReceiptPrintPaper.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 136));
            binding_imin.chkPosJerifood.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 137));
            binding_imin.chkPosBarcodeOnReceipt.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 138));
            binding_imin.chkPos4DigitICNO.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 139));
            binding_imin.chkPosZclose.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 140));
            binding_imin.chkPosKitchenPrinter.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 141));

            binding_imin.btnLangNew.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 40));
            binding_imin.btnLangDel.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 41));
            binding_imin.btnLangImport.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 42));
            binding_imin.btnLangExport.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 43));

            binding_imin.btnSave.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 5));
            binding_imin.btnBack.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 100));
        } else {
            //lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 100));
            binding.lbl2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 101));
            //lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 102));
            binding.lbl4.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 103));
            binding.lbl5.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 104));
            binding.lbl6.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 105));
            binding.lbl7.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 106));
            binding.lbl8.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 107));
            //lbl_9.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 108));
            //lbl_10.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 109));
            //lbl_11.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 110));
            //lbl_12.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 111));
            //lbl_13.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 1));
            //lbl_14.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 2));
            //lbl_15.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 125));

            binding.chkPosSelectlast.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 112));
            binding.chkPosNoprintcondiqty.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 113));
            binding.chkPosPaymentonlykp.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 114));
            binding.chkPosHideNaviBar.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 115));
            binding.chkPosUsermode.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 116));
            binding.chkPosCheckupdate.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 117));
            binding.chkPosReferfunction.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 118));
            binding.chkPosRefercompulsory.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 119));
            binding.chkPosReferinfo1print.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 120));
            binding.chkPosReferinfo2print.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 121));
            binding.chkPosReferinfo3print.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 122));
            binding.chkPosUserovertake.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 123));
            binding.chkPosNoprintunpaid.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 124));
            binding.chkPosPrinterReceipt.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 126));
            binding.chkPosCashDrawer.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 127));
            binding.chkPosCustomerDisplay.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 128));
            binding.chkPosBarcodeScanner.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 129));
            binding.chkPosIntegrateShoptima.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 130));
            binding.chkPosQrCodeShoptima.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 131));
            binding.chkPosOnlineOrder.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 132));
            binding.chkPosImageUrl.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 133));
            binding.chkPosServiceCharges.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 134));
            binding.chkPosRetail.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 135));
            binding.chkPosReceiptPrintPaper.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 136));
            binding.chkPosJerifood.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 137));
            binding.chkPosBarcodeOnReceipt.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 138));
            binding.chkPos4DigitICNO.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 139));
            binding.chkPosZclose.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 140));
            binding.chkPosKitchenPrinter.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 141));

            binding.btnLangNew.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 40));
            binding.btnLangDel.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 41));
            binding.btnLangImport.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 42));
            binding.btnLangExport.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 43));

            binding.btnSave.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 5));
            binding.btnBack.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 100));
        }


        this.mHandler = new Handler();
        m_Runnable.run();
    }

    private void screenDisplay() {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.equals(Constraints.IMIN)) {

            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(720, 90);
                linearOverAllParams2.leftMargin = 25;
                linearOverAllParams2.topMargin = 10;
                linearOverAllParams2.bottomMargin = 10;
                binding.Lay1.setLayoutParams(linearOverAllParams2);
                binding.Lay2.setLayoutParams(linearOverAllParams2);
                binding.Lay3.setLayoutParams(linearOverAllParams2);
                binding.linearlaySync.setLayoutParams(linearOverAllParams2);
                binding.linearlayDownload.setLayoutParams(linearOverAllParams2);
                binding.linearlayJeripayEunoia.setLayoutParams(linearOverAllParams2);
                binding.lay4.setLayoutParams(linearOverAllParams2);
                binding.Lay5.setLayoutParams(linearOverAllParams2);
                binding.Lay6.setLayoutParams(linearOverAllParams2);
                binding.Lay7.setLayoutParams(linearOverAllParams2);
                binding.Lay8.setLayoutParams(linearOverAllParams2);
                binding.Lay9.setLayoutParams(linearOverAllParams2);
                binding.lay10.setLayoutParams(linearOverAllParams2);
                binding.lay11.setLayoutParams(linearOverAllParams2);
                binding.lay12.setLayoutParams(linearOverAllParams2);

               // binding.btnAdd.setLayoutParams(new LinearLayout.LayoutParams(720, 90));
            }
        }
    }

    private void layoutShoptimaFun(int viewValue, String status) {
        if (status.equals("QRCode")) {
            if (device.equals("M2-Max")) {
                binding_imin.linearlayshoptimaurlQrcode.setVisibility(viewValue);
                binding_imin.linearlayshoptimapasswordQrcode.setVisibility(viewValue);
                binding_imin.linearlayshoptimallcodeQrcode.setVisibility(viewValue);
                binding_imin.linearlayshoptimachineidQrcode.setVisibility(viewValue);
                binding_imin.linearlayshoptimabrandQrcode.setVisibility(viewValue);
                binding_imin.linearlayshoptimaoutletQrcode.setVisibility(viewValue);
            } else {
                binding.linearlayshoptimaurlQrcode.setVisibility(viewValue);
                binding.linearlayshoptimapasswordQrcode.setVisibility(viewValue);
                binding.linearlayshoptimallcodeQrcode.setVisibility(viewValue);
                binding.linearlayshoptimachineidQrcode.setVisibility(viewValue);
                binding.linearlayshoptimabrandQrcode.setVisibility(viewValue);
                binding.linearlayshoptimaoutletQrcode.setVisibility(viewValue);
            }
        }else if (status.equals("Normal")) {
            if (device.equals("M2-Max")) {
                binding_imin.linearlayshoptimaurl.setVisibility(viewValue);
                binding_imin.linearlayshoptimapassword.setVisibility(viewValue);
                binding_imin.linearlayshoptimachineid.setVisibility(viewValue);
                binding_imin.linearlayshoptimallcode.setVisibility(viewValue);
            } else {
                binding.linearlayshoptimaurl.setVisibility(viewValue);
                binding.linearlayshoptimapassword.setVisibility(viewValue);
                binding.linearlayshoptimachineid.setVisibility(viewValue);
                binding.linearlayshoptimallcode.setVisibility(viewValue);
            }
        }
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run(){

            if (selected_product_cateogry_id > 0) {
                if (device.equals("M2-Max")) {
                    binding_imin.editRoundMethod.setText(selected_product_cateogry_name);
                }else {
                    binding.editRoundMethod.setText(selected_product_cateogry_name);
                }
            }
            if (selected_receipt_printer_id > 0) {
                if (device.equals("M2-Max")) {
                    binding_imin.editReceiptPrinter.setText(selected_receipt_printer_name);
                } else {
                    binding.editReceiptPrinter.setText(selected_receipt_printer_name);
                }
            }
            if (selected_font_size_id > 0) {
                if (device.equals("M2-Max")) {
                    binding_imin.editBillListFontSize.setText(selected_font_size_name);
                } else {
                    binding.editBillListFontSize.setText(selected_font_size_name);
                }
            }
            if (selected_bluetooth_id > 0) {
                if (device.equals("M2-Max")) {
                    binding_imin.editBluetooth.setText(selected_bluetooth_name);
                }else {
                    binding.editBluetooth.setText(selected_bluetooth_name);
                }
                selected_bluetooth_id = -1;
            }
            if (selected_balance_type_id > 0) {
                if (device.equals("M2-Max")) {
                    binding_imin.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 33));
                    binding_imin.txtBalanceType.setText(selected_balance_type_name);
                } else {
                    binding.editBalanceTitle.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 33));
                    binding.txtBalanceType.setText(selected_balance_type_name);
                }
                if (selected_balance_type_id  == 2){


                }
            }
            PosConfigurationActivity.this.mHandler.postDelayed(m_Runnable,300);

        }

    };

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_round_method:
//                RoundMethodSheetFragment pcSSheetFragment = new RoundMethodSheetFragment();
//                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
//            break;
//        }
//    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(PosConfigurationActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_lang_export:
//
//                break;
//        }
//    }
}
