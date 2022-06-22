package com.dcs.myretailer.app.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.CharacterSheetFragment;
import com.dcs.myretailer.app.Setting.ReceiptPrinterNameSheetFragment;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.Setting.USBHelper;
import com.dcs.myretailer.app.databinding.ActivityHardwareBinding;

import java.util.ArrayList;
import java.util.List;

public class HardwareActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    public static int selected_receipt_printer_id = -1;
    public static String selected_receipt_printer_name = "0";
    public static int selected_character_id = 0;
    public static String selected_character_name = "0";
    Handler mHandler = null;
    //ImageView btn_receipt_printer,btn_character;
    //EditText edit_receipt_printer,edit_character;
    //LinearLayout lay_usb = null;
    //Spinner spn_usb_list = null;
    ArrayAdapter<String> adapt_usb = null;
//    LinearLayout lay_net = null;
//    LinearLayout laymac = null;
//     EditText et_net_ip = null;
//     EditText et_net_port = null;

//     LinearLayout lay_bt = null;;
//     TextView tv_bt_mac = null;
//     ImageView btn_bt_select = null;
//     EditText edit_bt_select = null;
     String taxID = "0";
//    EditText et_name = null;
//    CheckBox chk_singlesize = null;
//    EditText et_feed = null;
//    EditText et_width = null;
    ActivityHardwareBinding binding = null;
    @SuppressWarnings("rawtypes")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hardware);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_hardware);
        final Activity CurrentActivity = this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Intent intent = getIntent();
        taxID = intent.getStringExtra("ID");


//        this.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//        this.getActionBar().setIcon(R.drawable.ic_print_grey_500);
        //TextView lbl_1 = (TextView)findViewById(R.id.lbl_1);
        //TextView lbl_2 = (TextView)findViewById(R.id.lbl_2);
        //TextView lbl_3 = (TextView)findViewById(R.id.lbl_3);
        //TextView lbl_4 = (TextView)findViewById(R.id.lbl_4);
        //TextView lbl_5 = (TextView)findViewById(R.id.lbl_5);
        //TextView lbl_6 = (TextView)findViewById(R.id.lbl_6);
        //TextView lbl_7 = (TextView)findViewById(R.id.lbl_7);
        //TextView lbl_8 = (TextView) findViewById(R.id.lbl_8);
        //TextView lbl_9 = (TextView) findViewById(R.id.lbl_9);
        //TextView lbl_10 = (TextView) findViewById(R.id.lbl_10);


        final List<Integer> ID = new ArrayList<Integer>();
        //final Spinner spn_list = (Spinner)findViewById(R.id.spn_list);
//        final ArrayAdapter<String> adapt_list = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//        adapt_list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_list.setAdapter(adapt_list);
        //et_name = (EditText) findViewById(R.id.et_name);

        //final Spinner spn_type = (Spinner)findViewById(R.id.spn_printer_type);
//        final ArrayAdapter<String> adapt_type = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//        adapt_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_type.setAdapter(adapt_type);
//        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 30));
//        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 31));
//        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 32));
//        adapt_type.add(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 33));

//        final Spinner spn_charset = (Spinner)findViewById(R.id.spn_print_charset);
//        final ArrayAdapter<String> adapt_charset = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//        adapt_charset.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_charset.setAdapter(adapt_charset);
//
//        SortedMap charsets = Charset.availableCharsets();
//
//        for (Iterator e = charsets.keySet().iterator(); e.hasNext();) {
//            Charset charset = (Charset) charsets.get(e.next());
//
//            if(Charset.isSupported(charset.name())){
//                adapt_charset.add(charset.name());
//                //for (Iterator ee = charset.aliases().iterator(); ee.hasNext();) {
//                //	adapt_charset.add(""+ee.next());
//                //}
//            }
//        }
//
//        adapt_charset.sort(new Comparator<String>() {
//            @Override
//            public int compare(String lhs, String rhs) {
//                return lhs.toUpperCase().compareTo(rhs.toUpperCase());
//            }
//        });
//
//        spn_charset.setSelection(adapt_charset.getPosition("US-ASCII"));

        //et_width = (EditText) findViewById(R.id.et_length);

        //et_feed = (EditText) findViewById(R.id.et_print_feedline);
        //edit_receipt_printer = (EditText) findViewById(R.id.edit_receipt_printer);
        //edit_character = (EditText) findViewById(R.id.edit_character);
        binding.editCharacter.setText("US-ASCII");
        //binding.etPrintFeedline.setText(""+Allocator.PrintFeed);
        //final CheckBox chk_default = (CheckBox)findViewById(R.id.chk_print_default);

        //chk_singlesize = (CheckBox) findViewById(R.id.chk_print_singlesize);
        //binding.chkPrintSinglesize.setChecked(Allocator.PrintSingleSize);

        //final LinearLayout lay = (LinearLayout)findViewById(R.id.lay_print_extra);
        //lay_net = (LinearLayout) findViewById(R.id.lay_print_net);
        //laymac = (LinearLayout) findViewById(R.id.laymac);
        //et_net_ip = (EditText) findViewById(R.id.et_net_ip);
        //et_net_port = (EditText) findViewById(R.id.et_net_port);

        //lay_bt = (LinearLayout) findViewById(R.id.lay_print_bt);
        //tv_bt_mac = (TextView) findViewById(R.id.tv_bt_mac);
        //edit_bt_select = (EditText) findViewById(R.id.edit_bt_select);
        //btn_bt_select = (ImageView) findViewById(R.id.btn_bt_select);


         binding.btnBtSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
                if (bt == null) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 26));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                if (bt.getState() == BluetoothAdapter.STATE_TURNING_OFF || bt.getState() == BluetoothAdapter.STATE_TURNING_ON) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 27));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                if (bt.getState() == BluetoothAdapter.STATE_OFF) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    return;
                }

                final Dialog dlg = new Dialog(CurrentActivity);
                dlg.setContentView(R.layout.dlg_btselect);
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 1));
                ListView lst_bt = (ListView) dlg.findViewById(R.id.lst_btselect_list);
                List<String> btdevicelist = new ArrayList<String>();
                lst_bt.setAdapter(new ArrayAdapter<String>(dlg.getContext(), android.R.layout.simple_list_item_1, btdevicelist));
                List<String> btdevicemaclist = new ArrayList<String>();
                final BluetoothDevice[] btbondlist = bt.getBondedDevices().toArray(new BluetoothDevice[bt.getBondedDevices().size()]);

                for (BluetoothDevice device : btbondlist) {
                    btdevicelist.add(device.getName() + "\n" + device.getAddress());
                    btdevicemaclist.add(device.getAddress());
                }

                lst_bt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        binding.tvBtMac.setText(btbondlist[position].getAddress());
                        binding.editBtSelect.setText(btbondlist[position].getName());
                        dlg.dismiss();
                    }

                });

                Button btn_bt_openbtscreen = (Button) dlg.findViewById(R.id.btn_btselect_btsetting);
                btn_bt_openbtscreen.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent btintent = new Intent(Intent.ACTION_MAIN, null);
                        btintent.addCategory(Intent.CATEGORY_LAUNCHER);
                        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.bluetooth.BluetoothSettings");
                        btintent.setComponent(cn);
                        btintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(btintent);
                        dlg.dismiss();
                    }

                });

                Button btn_bt_cancel = (Button) dlg.findViewById(R.id.btn_btselect_cancel);
                btn_bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });

                btn_bt_openbtscreen.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 113));
                btn_bt_cancel.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1));
                dlg.show();
            }

        });


        //lay_usb = (LinearLayout) findViewById(R.id.lay_print_usb);
        //spn_usb_list = (Spinner) findViewById(R.id.spn_usb_list);
        adapt_usb = new ArrayAdapter<String>(CurrentActivity, android.R.layout.simple_spinner_item);
        final Button btn_usb_refresh = (Button) findViewById(R.id.btn_usb_refresh);
        final Button btn_usb_check = (Button) findViewById(R.id.btn_usb_check);
        adapt_usb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnUsbList.setAdapter(adapt_usb);

        final int PID = 0;
        final int VID = 0;


        btn_usb_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapt_usb.clear();
                List<UsbDevice> devices = USBHelper.ListUSBDevice(CurrentActivity, PID, VID);
                for (int i = 0; i < devices.size(); i++) {
                    adapt_usb.add(devices.get(i).getDeviceName());
                }
            }
        });

        btn_usb_refresh.callOnClick();

        btn_usb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.spnUsbList.getSelectedItem() == null) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 16));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }
                UsbDevice device = USBHelper.getUSBDevice(CurrentActivity, "" + binding.spnUsbList.getSelectedItem());
                if (device == null) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 17));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                if (USBHelper.isUSBHasPermission(CurrentActivity, device)) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 18, device.getDeviceName()));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }
                USBHelper.getUSBPermission(CurrentActivity, device);
            }
        });

        //final Button btn_save = (Button) findViewById(R.id.btn_print_save);
        //final Button btn_del = (Button) findViewById(R.id.btn_print_del);
        //final Button btn_close = (Button) findViewById(R.id.btn_print_close);

        //btn_receipt_printer = (ImageView) findViewById(R.id.btn_receipt_printer);
        binding.btnReceiptPrinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceiptPrinterNameSheetFragment pcSSheetFragment = new ReceiptPrinterNameSheetFragment();
                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());

                m_Runnable.run();
            }
        });

        //btn_character = (ImageView) findViewById(R.id.btn_character);
        binding.btnCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterSheetFragment pcSSheetFragment = new CharacterSheetFragment();
                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());

                m_Runnable.run();
            }
        });
        Log.i("ddf__taxID", taxID);
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        Log.i("ddf__tadeviceD", device);
        if (device.equals("M2-Max")) {
//            LinearLayout.LayoutParams Layproductstockinbtn1 = new LinearLayout.LayoutParams(820,
//                    90);
//            //Layproductstockinbtn1.leftMargin = 40;
//            binding.btnPrintSave.setLayoutParams(Layproductstockinbtn1);
        }

        if (taxID.equals("null")) {
            setTitle("Add Hardware Configuration");

            LinearLayout.LayoutParams Layproductstockinbtn = new LinearLayout.LayoutParams(720,
                    90);
            Layproductstockinbtn.leftMargin = 20;
            binding.btnPrintSave.setLayoutParams(Layproductstockinbtn);
            binding.btnPrintSave.setText("Add");
            binding.btnPrintDel.setVisibility(View.GONE);
            if (terminalTypeVal.equals(Constraints.IMIN)){
                if (device.equals("M2-Max")) {
//                    LinearLayout.LayoutParams Layproductstockinbtn1 = new LinearLayout.LayoutParams(820,
//                            90);
//                    //Layproductstockinbtn1.leftMargin = 40;
//                    binding.btnPrintSave.setLayoutParams(Layproductstockinbtn1);

                    LinearLayout.LayoutParams LaybtnPrintSave = new LinearLayout.LayoutParams(720,
                            90);
//                    Layproductstockinbtn.leftMargin = 20;
                    binding.btnPrintSave.setLayoutParams(LaybtnPrintSave);
                } else {

                    LinearLayout.LayoutParams Layproductstockinbtn1 = new LinearLayout.LayoutParams(640,
                            90);
                    //Layproductstockinbtn1.leftMargin = 40;
                    binding.btnPrintSave.setLayoutParams(Layproductstockinbtn1);


                }

            }
        } else {
            setTitle("Edit Hardware Configuration");
//            LinearLayout.LayoutParams Layproductstockinebtn = new LinearLayout.LayoutParams(320,
//                    90);
//            Layproductstockinebtn.leftMargin = 30;
//            Layproductstockinebtn.topMargin = 20;
//            binding.btnPrintSave.setLayoutParams(Layproductstockinebtn);

            binding.btnPrintSave.setText("Update");
            binding.btnPrintDel.setVisibility(View.VISIBLE);

            if (terminalTypeVal.equals(Constraints.IMIN)){
                if (device.equals("M2-Max")) {
//                    LinearLayout.LayoutParams Layproductstockinebtn1 = new LinearLayout.LayoutParams(200,
//                            90);
//                    Layproductstockinebtn1.topMargin = 20;
//                    binding.btnPrintSave.setLayoutParams(Layproductstockinebtn1);
//
//                    LinearLayout.LayoutParams Layproductstockinebtn2 = new LinearLayout.LayoutParams(200,
//                            90);
//                    Layproductstockinebtn2.leftMargin = 30;
//                    Layproductstockinebtn2.topMargin = 20;
//                    binding.btnPrintDel.setLayoutParams(Layproductstockinebtn2);
                } else {
                    LinearLayout.LayoutParams Layproductstockinebtn1 = new LinearLayout.LayoutParams(310,
                            90);
                    Layproductstockinebtn1.topMargin = 20;
                    binding.btnPrintSave.setLayoutParams(Layproductstockinebtn1);
                    LinearLayout.LayoutParams Layproductstockinebtn2 = new LinearLayout.LayoutParams(310,
                            90);
                    Layproductstockinebtn2.leftMargin = 30;
                    Layproductstockinebtn2.topMargin = 20;
                    binding.btnPrintDel.setLayoutParams(Layproductstockinebtn2);



                }

            }

            getTaxById(Integer.parseInt(taxID));
//            binding.etName.setText(tax_name);
//            et_length.setText(tax_acronym);
//            et_print_feedline.setText(tax_rate);
//            btn_tax_type.setText(tax_ttype);
        }


        binding.btnPrintSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (binding.etName.getText().length() == 0) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 20));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                int len = 0;
                int line = 0;
                int port = 0;

                //Log.i("yryt_", binding.editReceiptPrinter.getText().toString());
                int type = 0;
                if (binding.editReceiptPrinter.getText().toString().equals(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 30))) {
                    type = 0;
                } else if (binding.editReceiptPrinter.getText().toString().equals(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 31))) {
                    type = 1;
                } else if (binding.editReceiptPrinter.getText().toString().equals(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 32))) {
                    type = 2;
                } else if (binding.editReceiptPrinter.getText().toString().equals(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 33))) {
                    type = 3;
                }

                try {
                    len = Integer.parseInt(binding.etLength.getText().toString());
                    if (len < 32 || len > 100) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 12));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 12));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                try {
                    line = Integer.parseInt(binding.etPrintFeedline.getText().toString());
                    if (line <= 0 || line > 100) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 13));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 13));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }

                //if(selected_receipt_printer_id == 1){
                if (type == 1) {
                    if (binding.etNetIp.length() == 0) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 14));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }

                    try {
                        port = Integer.parseInt(binding.etNetPort.getText().toString());
                        if (port <= 0 || port > 65535) {
                            DialogBox dlg1 = new DialogBox(v.getContext());
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 15));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 15));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                    //}else if(selected_receipt_printer_id==3){
                } else if (type == 3) {
                    if (binding.spnUsbList.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
                        DialogBox dlg1 = new DialogBox(v.getContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 16));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

                final int fline = line;
                final int flen = len;
                final int fport = port;
                //final String fcharset = ""+spn_charset.getSelectedItem();
                final String fcharset = "" + selected_character_id;


                String param = "";
                //switch(selected_receipt_printer_id){
                switch (type) {
                    case 1://net
                        param = "NET:" + binding.etNetIp.getText().toString() + ":" + fport;
                        break;
                    case 2://bt
                        param = "BT:" + binding.tvBtMac.getText().toString();
                        break;
                    case 3://usb
                        param = "USB:" + adapt_usb.getItem(binding.spnUsbList.getSelectedItemPosition());
                        break;
                }
                final String fparam = param;

//                if(adapt_list.getCount()==0){
                //add new Printer
                //insert into DB
//                    Log.i("yryt_","etu_");
//                    DBFunc.ExecQuery("INSERT INTO Printer " +
//                            "(name,dflt,type,lfeed,lwidth,nodouble,param,charset) " +
//                            "VALUES ('"+DBFunc.PurifyString(binding.etName.getText().toString())+"',"+
//                            (0)+","+selected_receipt_printer_id+","+fline+","+flen+","+(binding.chkPrintSinglesize.isChecked()?1:0)
//                            +",'"+DBFunc.PurifyString(fparam)+"','"+DBFunc.PurifyString(fcharset)+"')", true);
//
//                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Add -> Name: "+binding.etName.getText());

                //adapt_list.clear();
//                    ID.clear();
//                    Cursor data = DBFunc.Query("SELECT id,name FROM Printer ORDER BY ID ASC", true);
//                    if(data!=null){
//                        while(data.moveToNext()){
//                            ID.add(data.getInt(0));
//                            adapt_list.add(data.getString(1));
//                        }
//                        data.close();
//                        spn_list.setSelection(ID.size()-1);
//                    }

//                    DialogBox dlg1 = new DialogBox(v.getContext());
//                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 22));
//                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                    dlg1.show();
//                    return;
                // }


//                final DialogBox dlg = new DialogBox(v.getContext());
//                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
//                dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 21));
//                dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 4), new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dlg.dismiss();
                //add new Printer
                //insert into DB

                //if(chk_default.isChecked()){
                //	DBFunc.ExecQuery("UPDATE Printer SET dflt = 0", true);
                //}

                if (taxID.equals("null")) {
                    DBFunc.ExecQuery("INSERT INTO Printer (name,dflt,type,lfeed,lwidth,nodouble,param,charset) VALUES ('" +
                            DBFunc.PurifyString(binding.etName.getText().toString()) + "'," + (0) + "," + type +
                            "," + fline + "," + flen + "," + (binding.chkPrintSinglesize.isChecked() ? 1 : 0) + ",'" + DBFunc.PurifyString(fparam) + "','" +
                            DBFunc.PurifyString(fcharset) + "')", true);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Add -> Name: " + binding.etName.getText());

                    Intent i = new Intent(HardwareActivity.this, PrinterListActivity.class);
                    startActivity(i);
                } else {
                    String query = "UPDATE Printer SET ";
                    query += "name = '" + DBFunc.PurifyString(binding.etName.getText().toString()) + "', ";
                    //query += "dflt = "+(chk_default.isChecked()?1:0)+", ";
                    query += "type = " + type + ", ";
                    query += "lfeed = " + fline + ", ";
                    query += "lwidth = " + flen + ", ";
                    query += "nodouble = " + (binding.chkPrintSinglesize.isChecked() ? 1 : 0) + ", ";
                    query += "param = '" + DBFunc.PurifyString(fparam) + "', ";
                    query += "charset = '" + DBFunc.PurifyString(fcharset) + "' ";

                    query += "WHERE ID = " + taxID;
                    Log.i("__query_", query);
                    DBFunc.ExecQuery(query, true);

                    //UPDATE Printer SET name = 'receipt_setting', type = 0, lfeed = 5, lwidth = 40, nodouble = 0, param = '',
                    // charset = '0' WHERE ID = 1


                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Update -> Name: " + binding.etName.getText());
                    Intent i = new Intent(HardwareActivity.this, PrinterListActivity.class);
                    startActivity(i);
                }
                //adapt_list.clear();
//                        ID.clear();
//                        Cursor data = DBFunc.Query("SELECT id,name FROM Printer ORDER BY ID ASC", true);
//                        if(data!=null){
//                            while(data.moveToNext()){
//                                ID.add(data.getInt(0));
//                                adapt_list.add(data.getString(1));
//                            }
//                            data.close();
//                            spn_list.setSelection(ID.size()-1);
//                        }
//
//                        DialogBox dlg1 = new DialogBox(v.getContext());
//                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 22));
//                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                        dlg1.show();

//                    }
//                });
//                dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 5), new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dlg.dismiss();
//                        //if(chk_default.isChecked()){
//                        //	DBFunc.ExecQuery("UPDATE Printer SET dflt = 0", true);
//                        //}
//
//                        int pos = spn_list.getSelectedItemPosition();
//                        int printerID = ID.get(pos);
//
//                        String query = "UPDATE Printer SET ";
//                        query += "name = '"+DBFunc.PurifyString(binding.etName.getText().toString())+"', ";
//                        //query += "dflt = "+(chk_default.isChecked()?1:0)+", ";
//                        query += "type = "+selected_receipt_printer_id+", ";
//                        query += "lfeed = "+fline+", ";
//                        query += "lwidth = "+flen+", ";
//                        query += "nodouble = "+(binding.chkPrintSinglesize.isChecked()?1:0)+", ";
//                        query += "param = '"+DBFunc.PurifyString(fparam)+"', ";
//                        query += "charset = '"+DBFunc.PurifyString(fcharset)+"' ";
//
//                        query += "WHERE ID = "+printerID;
//
//                        DBFunc.ExecQuery(query, true);
//
//                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Update -> Name: "+binding.etName.getText());
//
//
//                        adapt_list.clear();
//                        ID.clear();
//                        Cursor data = DBFunc.Query("SELECT id,name FROM Printer ORDER BY ID ASC", true);
//                        if(data!=null){
//                            while(data.moveToNext()){
//                                ID.add(data.getInt(0));
//                                adapt_list.add(data.getString(1));
//                            }
//                            data.close();
//                            spn_list.setSelection(pos);
//                        }
//
//                        DialogBox dlg1 = new DialogBox(v.getContext());
//                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 23));
//                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                        dlg1.show();
//
//                    }
//                });
                //dlg.setButton3OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 1), null);

                //dlg.show();

            }

        });

        binding.btnPrintDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HardwareActivity.this, R.style.AlertDialogStyle)
                        .setMessage(Constraints.Exit)
                        .setCancelable(false)
                        .setPositiveButton(Constraints.YES, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DBFunc.ExecQuery("DELETE FROM Printer WHERE ID = " + taxID, true);

                                Intent i = new Intent(HardwareActivity.this, PrinterListActivity.class);
                                startActivity(i);
                                finish();
                                //ActivityCompat.finishAffinity(MainActivity.this);
                            }
                        })
                        .setNegativeButton(Constraints.No, null)
                        .show();
            }
        });
//        btn_del.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                final int pos = spn_list.getSelectedItemPosition();
//                if(pos==Spinner.INVALID_POSITION){
//                    DialogBox dlg1 = new DialogBox(v.getContext());
//                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 25));
//                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                    dlg1.show();
//                    return;
//                }
//
//                Cursor c = DBFunc.Query("SELECT COUNT(*) FROM Printer_Kitchen WHERE printer_id = "+ID.get(pos), true);
//                c.moveToNext();
//                int count = c.getInt(0);
//                if(count>0){
//                    c.close();
//                    DialogBox dlg1 = new DialogBox(v.getContext());
//                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
//                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 10, count));
//                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                    dlg1.show();
//                    return;
//                }
//                c.close();
////
////
////                final DialogBox dlg = new DialogBox(v.getContext());
////                dlg.setDialogIconType(DialogBox.IconType.QUESTION);
////                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
////                dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 11, adapt_list.getItem(pos)));
////
////                dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dlg.dismiss();
////                        int id = ID.get(pos);
////                        DBFunc.ExecQuery("DELETE FROM Printer WHERE ID = "+id, true);
////
////                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Delete -> Name: "+adapt_list.getItem(pos));
////
////                        adapt_list.clear();
////                        ID.clear();
////                        Cursor data = DBFunc.Query("SELECT id,name FROM Printer ORDER BY id ASC", true);
////                        if(data!=null){
////                            while(data.moveToNext()){
////                                ID.add(data.getInt(0));
////                                adapt_list.add(data.getString(1));
////                            }
////                            data.close();
////                            if(pos<ID.size()){
////                                spn_list.setSelection(pos);
////                            }else{
////                                if(ID.size()>0){
////                                    spn_list.setSelection(ID.size()-1);
////                                }
////                            }
////                        }
////                        DialogBox dlg1 = new DialogBox(v.getContext());
////                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 0));
////                        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
////                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 24));
////                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                        dlg1.show();
////                    }
////                });
////
////                dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                dlg.show();
//            }
//
//        });


        binding.btnPrintClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CurrentActivity.finish();
            }

        });

//        spn_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
//                switch(position){
//                    case 0://internal
//                        binding.layPrintNet.setVisibility(View.GONE);
//                        binding.layPrintBt.setVisibility(View.GONE);
//                        binding.layPrintUsb.setVisibility(View.GONE);
//                        break;
//                    case 1://net
//                        binding.layPrintNet.setVisibility(View.VISIBLE);
//                        binding.layPrintBt.setVisibility(View.GONE);
//                        binding.layPrintUsb.setVisibility(View.GONE);
//                        break;
//                    case 2://bt
//                        binding.layPrintNet.setVisibility(View.GONE);
//                        binding.layPrintBt.setVisibility(View.VISIBLE);
//                        binding.layPrintUsb.setVisibility(View.GONE);
//                        break;
//                    case 3://usb
//                        binding.layPrintNet.setVisibility(View.GONE);
//                        binding.layPrintBt.setVisibility(View.GONE);
//                        binding.layPrintUsb.setVisibility(View.VISIBLE);
//                        final int PID = 0;
//                        final int VID = 0;
//                        List<UsbDevice> devices = USBHelper.ListUSBDevice(CurrentActivity, PID, VID);
//                        for(int i=0;i<devices.size();i++){
//                            adapt_usb.add(devices.get(i).getDeviceName());
//                        }
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });

//        Cursor data = DBFunc.Query("SELECT id,name FROM Printer ORDER BY id ASC", true);
//        if(data!=null){
//            while(data.moveToNext()){
//                ID.add(data.getInt(0));
//                adapt_list.add(data.getString(1));
//            }
//            data.close();
//        }

//        spn_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long lid) {
//                int id = ID.get(position);
//                Cursor data = DBFunc.Query("SELECT id,name,dflt,type,lfeed,lwidth,nodouble,param,charset FROM Printer WHERE id = "+id, true);
//                if(data!=null){
//                    if(data.moveToNext()){
//                        //binding.etName.setText(data.getString(1));
//
//                        //if(data.getInt(2)==1){
//                        //chk_default.setChecked(true);
//                        //}else{
//                        //chk_default.setChecked(false);
//                        //}
//
//
//                        int type = data.getInt(3);
//                        //if(type>)
//                        if(type>=adapt_type.getCount())type=0;
//                        if(type<0)type=0;
//                        spn_type.setSelection(type);
//
//                        int line = data.getInt(4);
//                        if(line<0)line=0;
//                        if(line>100)line=100;
//                        binding.etPrintFeedline.setText(""+line);
//
//                        int len = data.getInt(5);
//                        if(len<32)len=32;
//                        if(len>100)len=100;
//                        binding.etLength.setText(""+len);
//
//                        if(data.getInt(6)==1){
//                            binding.chkPrintSinglesize.setChecked(true);
//                        }else{
//                            binding.chkPrintSinglesize.setChecked(false);
//                        }
//
//                        binding.etNetIp.setText("");
//                        binding.etNetPort.setText("9100");
//                        binding.tvBtMac.setText("00:00:00:00:00:00");
//                        if(!data.isNull(7)){
//                            String[] param = data.getString(7).split(":",2);
//                            if(param.length==2){
//                                if(type==1){//net
//                                    if(param[0].equalsIgnoreCase("NET")){
//                                        String[] netparam = param[1].split(":");
//                                        if(netparam.length==2){
//                                            try{
//                                                int port = Integer.parseInt(netparam[1]);
//                                                if(port>0 && port<=65535){
//                                                    binding.etNetIp.setText(netparam[0]);
//                                                    binding.etNetPort.setText(""+port);
//                                                }
//                                            }catch(NumberFormatException e){}
//                                        }
//                                    }
//                                }else if(type==2){//bt
//                                    if(param[0].equalsIgnoreCase("BT")){
//                                        if(param[1].length()==17){
//                                            binding.tvBtMac.setText(param[1]);
//                                        }
//                                    }
//                                }else if(type==3){//usb
//                                    if(param[0].equalsIgnoreCase("USB")){
//                                        for(int i=0;i<adapt_usb.getCount();i++){
//                                            if(adapt_usb.getItem(i).equals(param[1])){
//                                                binding.spnUsbList.setSelection(i);
//                                                break;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//                        String charset = "US-ASCII";
//                        if(!data.isNull(8)){
//                            charset = data.getString(8);
//                            try{
//                                spn_charset.setSelection(adapt_charset.getPosition(charset));
//                            }catch(Exception e){
//                                Logger.WriteLog("ActivityPrinterSetup",e.toString());
//                                //Log.e("GetCharSetError", e.toString());
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//
//        });


        //lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 100));
        //lbl_2.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 101));
        //lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 102));
        //lbl_4.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 103));
//        lbl_5.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 104));
//        lbl_6.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 105));
        //lbl_7.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 106));
        binding.lbl8.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 107));
        binding.lbl9.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 108));
        binding.lbl10.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 114));

        binding.chkPrintSinglesize.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 109));
        binding.editBtSelect.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 110));
        btn_usb_refresh.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 111));
        btn_usb_check.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 112));

        binding.btnPrintSave.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
        binding.btnPrintDel.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 10));
        binding.btnPrintClose.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 100));

        this.mHandler = new Handler();


//        if (device.equals("M2-Max")) {
//            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(700,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams.leftMargin = 50;
//            linearOverAllParams.topMargin = 30;
//            binding.LayName.setLayoutParams(linearOverAllParams);
//            binding.LayPrintReceipt.setLayoutParams(linearOverAllParams);
//            binding.LayLengthPerLine.setLayoutParams(linearOverAllParams);
//            binding.LayLineFeed.setLayoutParams(linearOverAllParams);
//            binding.LayCharacterEncoding.setLayoutParams(linearOverAllParams);
//            binding.LaySingleSizeFont.setLayoutParams(linearOverAllParams);
//            binding.laymac.setLayoutParams(linearOverAllParams);
//            binding.layPrintBt.setLayoutParams(linearOverAllParams);
//
//            LinearLayout.LayoutParams linearSavve = new LinearLayout.LayoutParams(500,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearSavve.leftMargin = 30;
//            binding.btnPrintSave.setLayoutParams(linearSavve);
//        }
    }

    private void getTaxById(int taxID) {
        //Cursor c = DBFunc.Query("SELECT Name, Acronym, Rate, Type, Seq FROM Tax where ID = "+taxID, true);
        String sql = "SELECT id,name,dflt,type,lfeed,lwidth,nodouble,param,charset " +
                "FROM Printer WHERE id = "+taxID;
        Log.i("jitjrt",sql);
        Cursor data = DBFunc.Query(sql,true);

        if(data!=null){
            if(data.moveToNext()){

                binding.etName.setText(data.getString(1)); 

                //if(data.getInt(2)==1){
                //chk_default.setChecked(true);
                //}else{
                //chk_default.setChecked(false);
                //}


                int type = data.getInt(3);
                Log.i("nfngf__typee", String.valueOf(type));
                if (type == 0){
                    binding.editReceiptPrinter.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 30));
                }else if (type == 1){
                    binding.editReceiptPrinter.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 31));
                }else if (type == 2){
                    binding.editReceiptPrinter.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 32));
                }else if (type == 3){
                    binding.editReceiptPrinter.setText(StrTextConst.GetText(StrTextConst.TextType.CFGPRINTER, 33));
                }

                int line = data.getInt(4);
                if(line<0)line=0;
                if(line>100)line=100;
                binding.etPrintFeedline.setText(""+line);

                int len = data.getInt(5);
                if(len<32)len=32;
                if(len>100)len=100;
                binding.etLength.setText(""+len);

                if(data.getInt(6)==1){
                    binding.chkPrintSinglesize.setChecked(true);
                }else{
                    binding.chkPrintSinglesize.setChecked(false);
                }

                if (type == 0) {
                    //internal
                    binding.layPrintNet.setVisibility(View.GONE);
                    binding.layPrintBt.setVisibility(View.GONE);
                    binding.laymac.setVisibility(View.GONE);
                    binding.layPrintUsb.setVisibility(View.GONE);
                }else if (type == 1) {
                    //net
                    binding.layPrintNet.setVisibility(View.VISIBLE);
                    binding.layPrintBt.setVisibility(View.GONE);
                    binding.laymac.setVisibility(View.GONE);
                    binding.layPrintUsb.setVisibility(View.GONE);
                }else if (type == 2) {
                    //bt
                    binding.layPrintNet.setVisibility(View.GONE);
                    binding.layPrintBt.setVisibility(View.VISIBLE);
                    binding.laymac.setVisibility(View.VISIBLE);
                    binding.layPrintUsb.setVisibility(View.GONE);
                }else if (type == 3) {
                    //usb
                    binding.layPrintNet.setVisibility(View.GONE);
                    binding.layPrintBt.setVisibility(View.GONE);
                    binding.laymac.setVisibility(View.GONE);
                    binding.layPrintUsb.setVisibility(View.VISIBLE);
                    final int PID = 0;
                    final int VID = 0;
                    List<UsbDevice> devices = USBHelper.ListUSBDevice(HardwareActivity.this, PID, VID);
                    for(int i=0;i<devices.size();i++){
                        adapt_usb.add(devices.get(i).getDeviceName());
                    }
                }

                binding.etNetIp.setText(""); 
                binding.etNetPort.setText("9100"); 
                binding.tvBtMac.setText("00:00:00:00:00:00"); 
                if(!data.isNull(7)){
                    Log.i("retretre",data.getString(7));
                    String[] param = data.getString(7).split(":",2);
                    if(param.length==2){
                        if(type==1){//net
                            if(param[0].equalsIgnoreCase("NET")){
                                String[] netparam = param[1].split(":");
                                if(netparam.length==2){
                                    try{
                                        int port = Integer.parseInt(netparam[1]);
                                        if(port>0 && port<=65535){
                                            binding.etNetIp.setText(netparam[0]);
                                            binding.etNetPort.setText(""+port);
                                        }
                                    }catch(NumberFormatException e){}
                                }
                            }
                        }else if(type==2){//bt
                            if(param[0].equalsIgnoreCase("BT")){
                                if(param[1].length()==17){
                                    binding.tvBtMac.setText(param[1]);
                                }
                            }
                        }else if(type==3){//usb
                            if(param[0].equalsIgnoreCase("USB")){
                                for(int i=0;i<adapt_usb.getCount();i++){
                                    if(adapt_usb.getItem(i).equals(param[1])){
                                        binding.spnUsbList.setSelection(i); 
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }else {
                }

                String charset = "US-ASCII";
                if(!data.isNull(8)){
                    charset = data.getString(8);
                    try{
                        //spn_charset.setSelection(adapt_charset.getPosition(charset));
                        //btn_character.setText(charset);
                    }catch(Exception e){
                        Logger.WriteLog("ActivityPrinterSetup",e.toString());
                        //Log.e("GetCharSetError", e.toString());
                    }
                }
            }
            data.close();
        }
//        c.close();
//        String sql = "Select Name FROM TaxType Where ID = "+tax_type_id+" ";
//        Log.i("dsffds__",sql);
//        c = DBFunc.Query(sql, true);
//        if(c!=null){
//            if(c.moveToNext()){
//                tax_ttype = c.getString(0);
//            }
//        }
//        Log.i("tax_ttype__",tax_ttype);
//        c.close();
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run(){
            Log.i("dsfd_", String.valueOf(selected_receipt_printer_id));
            if (selected_receipt_printer_id >= 0) {
                binding.editReceiptPrinter.setText(selected_receipt_printer_name);
                if (selected_receipt_printer_id == 0) {
                    //internal
                    binding.layPrintNet.setVisibility(View.GONE);
                    binding.layPrintBt.setVisibility(View.GONE);
                    binding.laymac.setVisibility(View.GONE);
                    binding.layPrintUsb.setVisibility(View.GONE);
                }else if (selected_receipt_printer_id == 1) {
                    //net
                    binding.layPrintNet.setVisibility(View.VISIBLE);
                    binding.layPrintBt.setVisibility(View.GONE);
                    binding.laymac.setVisibility(View.GONE);
                    binding.layPrintUsb.setVisibility(View.GONE);
                }else if (selected_receipt_printer_id == 2) {
                    //bt
                    binding.layPrintNet.setVisibility(View.GONE);
                    binding.layPrintBt.setVisibility(View.VISIBLE);
                    binding.laymac.setVisibility(View.VISIBLE);
                    binding.layPrintUsb.setVisibility(View.GONE);
                }else if (selected_receipt_printer_id == 3) {
                    //usb
                    binding.layPrintNet.setVisibility(View.GONE);
                    binding.layPrintBt.setVisibility(View.GONE);
                    binding.laymac.setVisibility(View.GONE);
                    binding.layPrintUsb.setVisibility(View.VISIBLE);
                    final int PID = 0;
                    final int VID = 0;
                    List<UsbDevice> devices = USBHelper.ListUSBDevice(HardwareActivity.this, PID, VID);
                    for(int i=0;i<devices.size();i++){
                        adapt_usb.add(devices.get(i).getDeviceName());
                    }
                }

            }else {
                MainActivity.St = "1";
            }
            if (selected_character_id >= 0) {
                binding.editCharacter.setText(selected_character_name);
            }
//            if (selected_receipt_printer_id > -1) {
//                btn_receipt_printer.setText(selected_receipt_printer_name);
//            }
//            if (selected_font_size_id > -1) {
//                btn_font_size.setText(selected_font_size_name);
//            }
//            if (selected_bluetooth_id > -1) {
//                btn_bluetooth.setText(selected_bluetooth_name);
//            }
            mHandler.removeCallbacks(m_Runnable);
            mHandler.removeCallbacksAndMessages(null);
            HardwareActivity.this.mHandler.postDelayed(m_Runnable,300);

        }

    };

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(CategoryManagementActivity.this);
        Intent i = new Intent(HardwareActivity.this,PrinterListActivity.class);
        startActivity(i);
        finish();
    }

}
