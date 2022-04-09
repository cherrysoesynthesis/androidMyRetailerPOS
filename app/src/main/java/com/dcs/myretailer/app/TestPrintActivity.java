//package com.dcs.myretailer.app;
//
//
//import android.Manifest;
//import android.annotation.TargetApi;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.ColorMatrix;
//import android.graphics.ColorMatrixColorFilter;
//import android.graphics.Paint;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.imin.library.SystemPropManager;
//import com.imin.printer.js.JsActivity;
//import com.imin.printerlib.Callback;
//import com.imin.printerlib.IminPrintUtils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class TestPrintActivity extends AppCompatActivity {
//
//    private RecyclerView rvView;
//    private List<String> data;
//    private EditText edit_bar_width, edit_bar_height, edit_bar_position, edit_qr_size, edit_qr_left, edit_qr_error_lev;
//    private int barWidth, barHeight, barTextPos, qrCodeSize, qrCodeErrorLev, barAndQrLeftSize;
//    private static final String TAG = "TestPrintActivity";
//    private IminPrintUtils mIminPrintUtils;
//    private int orientation;
//    private GridLayoutManager settingLayoutManager;
//    private BluetoothStateReceiver mBluetoothStateReceiver;
//    private Spinner spin_one;
//    private ListView mLvPairedDevices;
//    private int bluetoothPosition = -1;
//    private DeviceListAdapter mAdapter;
//    private int connectType;
//    private Button jsPrintBtn;
//    private List<String> connectTypeList;
//    private int spinnerPosition;
//    private TextView tv_tips;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_print);
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, 0);
//        }
//        initView();
//        initReceiver();
//        mIminPrintUtils = IminPrintUtils.getInstance(TestPrintActivity.this);
//
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        fillAdapter();
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        unregisterReceiver(mBluetoothStateReceiver);
//        mIminPrintUtils.release();
//        super.onDestroy();
//    }
//
//
//    private void initView() {
//        edit_bar_width = findViewById(R.id.edit_bar_width);
//        edit_bar_height = findViewById(R.id.edit_bar_height);
//        edit_bar_position = findViewById(R.id.edit_bar_position);
//        edit_qr_size = findViewById(R.id.edit_qr_size);
//        edit_qr_left = findViewById(R.id.edit_qr_left);
//        edit_qr_error_lev = findViewById(R.id.edit_qr_error_lev);
//        jsPrintBtn = findViewById(R.id.js_print);
//        tv_tips = findViewById(R.id.tv_tips);
//        rvView = findViewById(R.id.rv_list);
//        spin_one = findViewById(R.id.spin_one);
//
//        String deviceModel = SystemPropManager.getModel();
//
//        Log.i("xgh", "deviceModel:" + deviceModel);
//
//        connectTypeList = new ArrayList<>();
//        if (TextUtils.equals("M2-202", deviceModel) || TextUtils.equals("M2-203", deviceModel)) {
//            connectTypeList.add("SPI");
//            connectTypeList.add("Bluetooth");
//        } else if (TextUtils.equals("S1-701", deviceModel) || TextUtils.equals("S1-702", deviceModel)) {
//            connectTypeList.add("USB");
//            connectTypeList.add("Bluetooth");
//        } else if (TextUtils.equals("D1p-601", deviceModel) || TextUtils.equals("D1p-602", deviceModel)
//                || TextUtils.equals("D1p-603", deviceModel) || TextUtils.equals("D1p-604", deviceModel)
//                || TextUtils.equals("D1w-701", deviceModel) || TextUtils.equals("D1w-702", deviceModel)
//                || TextUtils.equals("D1w-703", deviceModel) || TextUtils.equals("D1w-704", deviceModel)
//                || TextUtils.equals("D4-501", deviceModel) || TextUtils.equals("D4-502", deviceModel)
//                || TextUtils.equals("D4-503", deviceModel) || TextUtils.equals("D4-504", deviceModel)
//                || TextUtils.equals("D4-505", deviceModel)) {
//            connectTypeList.add("USB");
//            connectTypeList.add("Bluetooth");
//        } else {
//            tv_tips.setVisibility(View.VISIBLE);
//            rvView.setVisibility(View.GONE);
//            tv_tips.setText("暂不支持当前设备");
//        }
//
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, connectTypeList);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin_one.setAdapter(spinnerAdapter);
//
//        mLvPairedDevices = (ListView) findViewById(R.id.lv_paired_devices);
//        mAdapter = new DeviceListAdapter(this);
//        mLvPairedDevices.setAdapter(mAdapter);
//        mLvPairedDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                bluetoothPosition = position;
//                mAdapter.notifyDataSetChanged();
//            }
//        });
//        jsPrintBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), JsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        spin_one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                if (R.id.spin_one == parent.getId()) {
//                    spinnerPosition = position;
//
//                    if (TextUtils.equals("USB", connectTypeList.get(position))) {
//                        connectType = 1;
//                        mLvPairedDevices.setVisibility(View.INVISIBLE);
//                    } else if (TextUtils.equals("Bluetooth", connectTypeList.get(position))) {
//                        connectType = 2;
//                        if (BluetoothUtil.isBluetoothOn()) {
//                            fillAdapter();
//                        } else {
//                            BluetoothUtil.openBluetooth(com.imin.printer.TestPrintActivity.this);
//                        }
//                    } else if (TextUtils.equals("SPI", connectTypeList.get(position))) {
//                        connectType = 3;
//                        if (mIminPrintUtils.isSPIPrint()) {
//                            mLvPairedDevices.setVisibility(View.INVISIBLE);
//                        } else {
//                            Toast.makeText(com.imin.printer.TestPrintActivity.this, "当前设备不支持SPI", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        if (data == null) {
//            data = new ArrayList<>();
//            data.add("init printer");
//            data.add("get printer status");
//            data.add("feed paper");
//            data.add("cut paper");
//            data.add("print text");
//
//            data.add("print a column of the table");
//            data.add("print single image");
//            data.add("print multiple images");
//
//            data.add("print barcode");
//            data.add("set barcode width");
//            data.add("set barcode height");
//            data.add("set barcode text position");
//
//            data.add("print QR code");
//            data.add("set QR code size");
//            data.add("set QrCode error correction Lev");
//            data.add("set barcode and QR coed left margin");
//            data.add("start print service");
//            data.add("print nv bitmap");
//
//        }
//
//
//        orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            settingLayoutManager = new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false);
//        } else {
//            settingLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
//
//        }
//        rvView.setHasFixedSize(true);
//        rvView.setNestedScrollingEnabled(false);
//        rvView.setLayoutManager(settingLayoutManager);
//        ButtonAdapter adapter = new ButtonAdapter(data, this);
//        adapter.setOnClickListener(new OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view, int pos, Object o) {
//                final int position = pos;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        synchronized (com.imin.printer.TestPrintActivity.class) {
//
//                            switch (position) {
//                                case 0:
//                                    Log.i("xgh", " :" + spinnerPosition + " :" + connectTypeList.get(spinnerPosition));
//                                    if (TextUtils.equals("USB", connectTypeList.get(spinnerPosition))) {
//                                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.USB);
//                                    } else if (TextUtils.equals("Bluetooth", connectTypeList.get(spinnerPosition))) {
//                                        Log.i("XGH", "bluetoothPosition:" + bluetoothPosition);
//                                        if (bluetoothPosition >= 0) {
//                                            BluetoothDevice device = mAdapter.getItem(bluetoothPosition);
//                                            Log.i("XGH", "device:" + device);
//                                            try {
//                                                mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, device);
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    } else if (TextUtils.equals("SPI", connectTypeList.get(spinnerPosition))) {
//                                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.SPI);
//                                    }
//
//                                    break;
//                                case 1:
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            if (TextUtils.equals("USB", connectTypeList.get(spinnerPosition))) {
//                                                int status = mIminPrintUtils.getPrinterStatus(IminPrintUtils.PrintConnectType.USB);
//                                                Log.d("XGH", " print USB status:" + status);
//                                                Toast.makeText(com.imin.printer.TestPrintActivity.this, " " + status, Toast.LENGTH_SHORT).show();
//                                            } else if (TextUtils.equals("Bluetooth", connectTypeList.get(spinnerPosition))) {
//                                                Toast.makeText(com.imin.printer.TestPrintActivity.this, "Not support", Toast.LENGTH_SHORT).show();
//                                            } else if (TextUtils.equals("SPI", connectTypeList.get(spinnerPosition))) {
//                                                mIminPrintUtils.getPrinterStatus(IminPrintUtils.PrintConnectType.SPI, new Callback() {
//                                                    @Override
//                                                    public void callback(int status) {
//                                                        Log.d("XGH", " print SPI status:" + status);
//                                                        Toast.makeText(com.imin.printer.TestPrintActivity.this, " " + status, Toast.LENGTH_SHORT).show();
//                                                    }
//
//                                                });
//                                            }
//
//                                        }
//                                    });
//
//                                    break;
//                                case 2:
//                                    mIminPrintUtils.printAndLineFeed();
//                                    mIminPrintUtils.printAndFeedPaper(50);
//
//                                    break;
//                                case 3:
//                                    mIminPrintUtils.partialCut();
//                                    break;
//                                case 4:
//
////                                    mIminPrintUtils.printText("iMin致力于使用先进的技术来帮助合作伙伴实现业务数字化。" +
////                                            "我们致力于成为东盟国家领先的智能商务设备提供商，帮助合作伙伴有效地连接\n");
//
//                                    mIminPrintUtils.printText("iMin committed to use advanced technologies to help our business partners digitize their business.We are dedicated in becoming a leading provider of smart business equipment " +
//                                            "in ASEAN countries,assisting our partners to connect, create and utilize data effectively.\n");
//
////                                    mIminPrintUtils.setPageFormat(0);//80mm
////                                    mIminPrintUtils.printText("iMin committed to use advanced technologies to help our business partners digitize their business.We are dedicated in becoming a leading provider of smart business equipment " +
////                                            "in ASEAN countries,assisting our partners to connect, create and utilize data effectively.");
////                                    mIminPrintUtils.setPageFormat(1);//58mm
////                                    mIminPrintUtils.printText("iMin committed to use advanced technologies to help our business partners digitize their business.We are dedicated in becoming a leading provider of smart business equipment " +
////                                            "in ASEAN countries,assisting our partners to connect, create and utilize data effectively.");
////                                    mIminPrintUtils.setPageFormat(0);//80mm
////
////                                    mIminPrintUtils.setAlignment(1);
////                                    mIminPrintUtils.printText(text);
////                                    mIminPrintUtils.setAlignment(0);
////                                    mIminPrintUtils.setTextSize(16);
////                                    mIminPrintUtils.printText(text);
////                                    mIminPrintUtils.setTextSize(26);
////                                    mIminPrintUtils.setTextWidth(300);
////                                    mIminPrintUtils.printText(text);
////                                    mIminPrintUtils.setTextWidth(576);
////                                    mIminPrintUtils.setTextLineSpacing(1.5f);
////                                    mIminPrintUtils.printText(text);
////                                    mIminPrintUtils.setTextLineSpacing(1.0f);
////                                    mIminPrintUtils.setTextStyle(Typeface.BOLD_ITALIC);
////                                    mIminPrintUtils.printText(text);
////                                    mIminPrintUtils.setTextStyle(Typeface.NORMAL);
////                                    mIminPrintUtils.setTextTypeface(Typeface.MONOSPACE);
////                                    mIminPrintUtils.printText(text);
////                                    mIminPrintUtils.setTextTypeface(Typeface.DEFAULT);
////
////                                    mIminPrintUtils.printText("----------------------------------------------------------------");
////                                    mIminPrintUtils.printText("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
//
//                                    mIminPrintUtils.printAndFeedPaper(100);
//
//                                    break;
//                                case 5:
//                                    String[] strings3 = new String[]{"Test", "Description Description Description@48", "192.00"};
//                                    int[] colsWidthArr3 = new int[]{2, 6, 2};
//                                    int[] colsAlign3 = new int[]{0, 0, 2};
//                                    int[] colsSize3 = new int[]{26, 26, 26};
//                                    mIminPrintUtils.printColumnsText(strings3, colsWidthArr3,
//                                            colsAlign3, colsSize3);
//
//                                    //                        mIminPrintUtils.setPageFormat(0);//80mm
//                                    //                        mIminPrintUtils.printColumnsText(strings3, colsWidthArr3,
//                                    //                                colsAlign3, colsSize3);
//                                    //                        mIminPrintUtils.setPageFormat(1);//58mm
//                                    //                        mIminPrintUtils.printColumnsText(strings3, colsWidthArr3,
//                                    //                                colsAlign3, colsSize3);
//                                    //                        mIminPrintUtils.setPageFormat(0);//80mm
//
//                                    mIminPrintUtils.printAndFeedPaper(100);
//                                    byte[] bytes = new byte[2];
//                                    bytes[0] = 0x1b;
//                                    bytes[1] = 0x40;
//                                    mIminPrintUtils = IminPrintUtils.getInstance(com.imin.printer.TestPrintActivity.this);
//                                    mIminPrintUtils.sendRAWData(bytes);
//
//                                    break;
//                                case 6:
//
////                                    Bitmap bitmap_red = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
////                                    bitmap_red = getBlackWhiteBitmap(bitmap_red);
////                                    bitmap_red = Bitmap.createScaledBitmap(bitmap_red, 400, 400, true);
////                                    mIminPrintUtils.printSingleBitmap(bitmap_red);
//
//                                    Bitmap bitmap_black = BitmapFactory.decodeResource(getResources(), R.drawable.icon1);
//                                    bitmap_black = getBlackWhiteBitmap(bitmap_black);
//                                    mIminPrintUtils.printSingleBitmap(bitmap_black);
//
////                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rabit);
////                                    mIminPrintUtils.printSingleBitmap(bitmap);
////                                    mIminPrintUtils.printSingleBitmap(bitmap, 1);
////                                    mIminPrintUtils.printSingleBitmap(bitmap, 2);
////                                    mIminPrintUtils.setPageFormat(0);//80mm
////                                    mIminPrintUtils.printSingleBitmap(bitmap);
////                                    mIminPrintUtils.setPageFormat(1);//58mm
////                                    mIminPrintUtils.printSingleBitmap(bitmap);
////                                    mIminPrintUtils.setPageFormat(0);//80mm
//
//                                    mIminPrintUtils.printAndFeedPaper(100);
//
//                                    break;
//                                case 7:
//                                    List<Bitmap> mBitmapList = new ArrayList<>();
//                                    Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rabit);
//                                    mBitmapList.add(bitmap1);
//                                    mBitmapList.add(bitmap1);
//                                    mBitmapList.add(bitmap1);
//                                    mIminPrintUtils.printMultiBitmap(mBitmapList);
//                                    mIminPrintUtils.printMultiBitmap(mBitmapList, 1);
//                                    mIminPrintUtils.printAndFeedPaper(100);
//                                    break;
//                                case 8:
//                                    try {
//                                        mIminPrintUtils.printBarCode(4, "123456");
//                                        mIminPrintUtils.printText("Test1\n");
//                                        mIminPrintUtils.printBarCode(4, "123456789");
//                                        mIminPrintUtils.printText("Test2\n");
//
//                                    } catch (UnsupportedEncodingException e) {
//                                        e.printStackTrace();
//                                    }
//                                    mIminPrintUtils.printAndFeedPaper(100);
//                                    break;
//                                case 9:
//                                    //2-6
//                                    if (TextUtils.isEmpty(edit_bar_width.getText().toString())) {
//                                        barWidth = 3;
//                                    } else {
//                                        barWidth = Integer.valueOf(edit_bar_width.getText().toString());
//                                    }
//                                    mIminPrintUtils.setBarCodeWidth(barWidth);
//                                    break;
//                                case 10://0-255
//                                    if (TextUtils.isEmpty(edit_bar_height.getText().toString())) {
//                                        barHeight = 100;
//                                    } else {
//                                        barHeight = Integer.valueOf(edit_bar_height.getText().toString());
//                                    }
//                                    mIminPrintUtils.setBarCodeHeight(barHeight);
//                                    break;
//                                case 11:
//                                    //0 none  1 up  2 down  3 up and down
//                                    if (TextUtils.isEmpty(edit_bar_position.getText().toString())) {
//                                        barTextPos = 0;
//                                    } else {
//                                        barTextPos = Integer.valueOf(edit_bar_position.getText().toString());
//                                    }
//                                    mIminPrintUtils.setBarCodeContentPrintPos(barTextPos);
//                                    break;
//                                case 12:
//                                    mIminPrintUtils.printQrCode("123456");
//                                    mIminPrintUtils.printQrCode("123456", 1);
//
//                                    mIminPrintUtils.setLeftMargin(100);
//                                    mIminPrintUtils.printQrCode("123456");
//                                    mIminPrintUtils.setLeftMargin(0);
//
//                                    mIminPrintUtils.printAndFeedPaper(100);
//                                    break;
//                                case 13:
//                                    //1-13
//                                    if (TextUtils.isEmpty(edit_qr_size.getText().toString())) {
//                                        qrCodeSize = 6;
//                                    } else {
//                                        qrCodeSize = Integer.valueOf(edit_qr_size.getText().toString());
//
//                                    }
//                                    mIminPrintUtils.setQrCodeSize(qrCodeSize);
//
//                                    break;
//                                case 14:
//                                    //48-51
//                                    if (TextUtils.isEmpty(edit_qr_error_lev.getText().toString())) {
//                                        qrCodeErrorLev = 48;
//                                    } else {
//                                        qrCodeErrorLev = Integer.valueOf(edit_qr_error_lev.getText().toString());
//                                    }
//                                    mIminPrintUtils.setQrCodeErrorCorrectionLev(qrCodeErrorLev);
//                                    break;
//                                case 15:
//                                    //0-576
//                                    if (TextUtils.isEmpty(edit_qr_left.getText().toString())) {
//                                        barAndQrLeftSize = 0;
//                                    } else {
//                                        barAndQrLeftSize = Integer.valueOf(edit_qr_left.getText().toString());
//                                    }
//                                    mIminPrintUtils.setLeftMargin(barAndQrLeftSize);
//                                    break;
//
//                                case 16:
//                                    startService(new Intent(com.imin.printer.TestPrintActivity.this, TestService.class));
//                                    break;
//                                case 17:
//                                    startActivity(new Intent(com.imin.printer.TestPrintActivity.this, NvBitmapActivity.class));
//                                    break;
//                            }
//                        }
//                    }
//                }).start();
//
//            }
//        });
//        rvView.setAdapter(adapter);
//
//    }
//
//
//    private void initReceiver() {
//        mBluetoothStateReceiver = new BluetoothStateReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
//        registerReceiver(mBluetoothStateReceiver, filter);
//    }
//
//
//    class BluetoothStateReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
//            switch (state) {
//                case BluetoothAdapter.STATE_TURNING_ON:
//                    toast("Bluetooth ON");
//                    break;
//
//                case BluetoothAdapter.STATE_TURNING_OFF:
//                    toast("Bluetooth OFF");
//                    break;
//            }
//        }
//    }
//
//    protected void toast(String message) {
//        Toast.makeText(com.imin.printer.TestPrintActivity.this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {
//
//        public DeviceListAdapter(Context context) {
//            super(context, 0);
//        }
//
//        @TargetApi(Build.VERSION_CODES.ECLAIR)
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            BluetoothDevice device = getItem(position);
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bluetooth_device, parent, false);
//            }
//
//            TextView tvDeviceName = (TextView) convertView.findViewById(R.id.tv_device_name);
//            CheckBox cbDevice = (CheckBox) convertView.findViewById(R.id.cb_device);
//
//            tvDeviceName.setText(device.getName());
//
//            cbDevice.setChecked(position == bluetoothPosition);
//
//            return convertView;
//        }
//    }
//
//
//    private void fillAdapter() {
//        List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();
//        mAdapter.clear();
//        mAdapter.addAll(printerDevices);
//        refreshButtonText(printerDevices);
//    }
//
//    private void refreshButtonText(List<BluetoothDevice> printerDevices) {
//        if (printerDevices.size() > 0 && connectType == 2) {
//            mLvPairedDevices.setVisibility(View.VISIBLE);
//        }
//    }
//
//
//    public static Bitmap getBlackWhiteBitmap(Bitmap bitmap) {
//        int w = bitmap.getWidth();
//        int h = bitmap.getHeight();
//
//        Bitmap resultBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
//        int color = 0;
//        int a, r, g, b, r1, g1, b1;
//        int[] oldPx = new int[w * h];
//        int[] newPx = new int[w * h];
//
//        bitmap.getPixels(oldPx, 0, w, 0, 0, w, h);
//        for (int i = 0; i < w * h; i++) {
//            color = oldPx[i];
//            r = Color.red(color);
//            g = Color.green(color);
//            b = Color.blue(color);
//            a = Color.alpha(color);
//            //黑白矩阵
//            r1 = (int) (0.33 * r + 0.59 * g + 0.11 * b);
//            g1 = (int) (0.33 * r + 0.59 * g + 0.11 * b);
//            b1 = (int) (0.33 * r + 0.59 * g + 0.11 * b);
//            //检查各像素值是否超出范围
//            if (r1 > 255) {
//                r1 = 255;
//            }
//
//            if (g1 > 255) {
//                g1 = 255;
//            }
//
//            if (b1 > 255) {
//                b1 = 255;
//            }
//
//            newPx[i] = Color.argb(a, r1, g1, b1);
//        }
//        resultBitmap.setPixels(newPx, 0, w, 0, 0, w, h);
//        return getGreyBitmap(resultBitmap);
//    }
//
//    public static Bitmap getGreyBitmap(Bitmap bitmap) {
//        if (bitmap == null) {
//            return null;
//        } else {
//            int width = bitmap.getWidth();
//            int height = bitmap.getHeight();
//            int[] pixels = new int[width * height];
//            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
//            int[] gray = new int[height * width];
//
//            int e;
//            int i;
//            int j;
//            int g;
//            for (e = 0; e < height; ++e) {
//                for (i = 0; i < width; ++i) {
//                    j = pixels[width * e + i];
//                    g = (j & 16711680) >> 16;
//                    gray[width * e + i] = g;
//                }
//            }
//
//            for (i = 0; i < height; ++i) {
//                for (j = 0; j < width; ++j) {
//                    g = gray[width * i + j];
//                    if (g >= 128) {
//                        pixels[width * i + j] = -1;
//                        e = g - 255;
//                    } else {
//                        pixels[width * i + j] = -16777216;
//                        e = g - 0;
//                    }
//
//                    if (j < width - 1 && i < height - 1) {
//                        gray[width * i + j + 1] += 3 * e / 8;
//                        gray[width * (i + 1) + j] += 3 * e / 8;
//                        gray[width * (i + 1) + j + 1] += e / 4;
//                    } else if (j == width - 1 && i < height - 1) {
//                        gray[width * (i + 1) + j] += 3 * e / 8;
//                    } else if (j < width - 1 && i == height - 1) {
//                        gray[width * i + j + 1] += e / 4;
//                    }
//                }
//            }
//
//            Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//            return mBitmap;
//        }
//    }
//
//    private static Bitmap toGrayscale(Bitmap bmpOriginal) {
//        int height = bmpOriginal.getHeight();
//        int width = bmpOriginal.getWidth();
//        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//        Canvas c = new Canvas(bmpGrayscale);
//        Paint paint = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0F);
//        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
//        paint.setColorFilter(f);
//        c.drawBitmap(bmpOriginal, 0.0F, 0.0F, paint);
//        return bmpGrayscale;
//    }
//
//
//}
