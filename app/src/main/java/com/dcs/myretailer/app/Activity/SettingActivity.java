package com.dcs.myretailer.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Model.KitchenPrinterActivity;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.SFTP.FPTFileCreateActivity;
import com.dcs.myretailer.app.SFTP.MallInterfaceActivity;
import com.dcs.myretailer.app.SFTP.ManualFTPMallInterfaceActivity;
import com.dcs.myretailer.app.Setting.ActivityMapSetup;
import com.dcs.myretailer.app.Setting.ModifierActivity;
import com.dcs.myretailer.app.Setting.ProductManagementActivity;
import com.dcs.myretailer.app.Setting.SettingAdapter;
import com.dcs.myretailer.app.Setting.VouchersAndDiscountsActivity;
import com.dcs.myretailer.app.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_setting);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        setTitle(Html.fromHtml("Settings&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small>EN</small>"));
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainPage();

            }
        });

//
       String[] countryList = {"Product Management", "Category Management", "Hardware","Kitchen Printer", "Payment Types", "Vouchers & Discounts", "Tax Configuration","Receipt Editor", "POS Configuration", "General Settings", "Database Import / Export", "Config Payment Host", "Report Setting", "Sync", "ZClose Resync" , "Support", "Floor Map", "Modifiers", "Stock Management", "FTP Mall Management",  "FTP Mall Interface", "Version : "+Constraints.VERSION_NAME};
        int[] flags = {R.drawable.ic_productmanagement, R.drawable.ic_category, R.drawable.ic_hardware, R.drawable.ic_print_grey_grey_500 , R.drawable.ic_payment, R.drawable.ic_voucher_setting, R.drawable.ic_tax,R.drawable.ic_receipteditor, R.drawable.ic_pos, R.drawable.setting_setting, R.drawable.ic_database,R.drawable.setting_setting ,R.drawable.setting_setting, R.drawable.baseline_sync_alt_black_18, R.drawable.ic_sync, R.drawable.ic_support, R.drawable.ic_floormap, R.drawable.ic_floormap, R.drawable.setting, R.drawable.ic_cloud_check_black_48dp,  R.drawable.ic_cloud_check_white_48dp, R.drawable.setting};

        SettingAdapter settingAdapter = new SettingAdapter(getApplicationContext(), countryList, flags);
        binding.simpleListView.setAdapter(settingAdapter);
        binding.simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                renderPageFun(getApplicationContext(), position);
            }
        });
    }

    private void renderPageFun(Context context, Integer position) {
        if (position != 13) {
            Intent i = new Intent(context, SettingActivity.class);
            if (position == 0) {
                i = new Intent(context, ProductManagementActivity.class);
            } else if (position == 1) {
                i = new Intent(context, CategoryManagementActivity.class);
            } else if (position == 2) {
                i = new Intent(context, PrinterListActivity.class);
            } else if (position == 3) {
                i = new Intent(context, KitchenPrinterActivity.class);
            } else if (position == 4) {
                i = new Intent(context, PaymentListActivity.class);
            } else if (position == 5) {
                i = new Intent(context, VouchersAndDiscountsActivity.class);
            } else if (position == 6) {
                i = new Intent(context, TaxConfigurationActivity.class);
            } else if (position == 7) {
                i = new Intent(context, ReceiptEditorActivity.class);
            } else if (position == 8) {
                i = new Intent(context, PosConfigurationActivity.class);
            } else if (position == 9) {
                i = new Intent(context, GeneralSettingActivity.class);
            }
//            else if (position == 9) {
//                i = new Intent(context, PermissionGroupActivity.class);
//            }
//            else if (position == 10) {
//                i = new Intent(context, StaffManagementActivity.class);
//            }
            else if (position == 10) {
                i = new Intent(context, DatabaseImportExportActivity.class);
            } else if (position == 11) {
                i = new Intent(context, ConfigurationHostActivity.class);
            } else if (position == 12) {
                i = new Intent(context, ReportSettingActivity.class);
            } else if (position == 14) {
                i = new Intent(context, ZCloseResyncActivity.class);
            } else if (position == 15) {
                i = new Intent(context, SupportActivity.class);
            } else if (position == 16) {
                //i = new Intent(context, FloorMapActivity.class);
                i = new Intent(context, ActivityMapSetup.class);
            } else if (position == 17) {
                i = new Intent(context, ModifierActivity.class);
            } else if (position == 18) {
                i = new Intent(context, StockManagementActivity.class);
            }else if (position == 19) {
                i = new Intent(context, MallInterfaceActivity.class);
            }else if (position == 20) {
                i = new Intent(context, ManualFTPMallInterfaceActivity.class);
            }
            startActivity(i);
            finish();
        }else {
            passwordDialog();
        }
    }

    public void passwordDialog() {

        LayoutInflater li = LayoutInflater.from(SettingActivity.this);
        View promptsView = li.inflate(R.layout.searchprompt, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingActivity.this,R.style.AlertDialogStyle);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton(Constraints.OK,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                /** DO THE METHOD HERE WHEN PROCEED IS CLICKED*/
                                String user_text = (userInput.getText()).toString();

                                /** CHECK FOR USER'S INPUT **/
                                if (user_text.equals(Constraints.SyncPassword)){
                                    Intent i = new Intent(getApplicationContext(), SyncActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Log.d(user_text,"string is empty");
                                    String message = "The password you have entered is incorrect." + " \n \n" + "Please try again!";
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this,R.style.AlertDialogStyle);
                                    builder.setTitle("Error");
                                    builder.setMessage(message);
                                    builder.setPositiveButton("Cancel", null);
                                    builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            passwordDialog();
                                        }
                                    });
                                    builder.create().show();

                                }
                            }
                        })
                .setPositiveButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }

                        }

                );

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MainPage();
    }

    private void MainPage() {
//        Intent i = new Intent(SettingActivity.this,MainActivity.class);
//        startActivity(i);
//        finish();//add 09112020

        String syncRetailID = "";
        String syncCompanyCode = "";
        String syncUrl = "";
        String download_retail_ID = "";
        String download_company_code = "";
        String download_url = "";
        String download_userlogin = "";
        String download_userpassword = "";
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
                download_userlogin = Cursor_Possys.getString(13);
                download_userpassword = Cursor_Possys.getString(14);
            }
            Cursor_Possys.close();
        }
//        SyncActivity.volleyCheckPermission(this, Constraints.ORDERING,Constraints.Accessable,syncRetailID,
//                syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);

        //String accessable = SyncActivity.volleyCheckPermission(this, Constraints.ORDERING,Constraints.Accessable);
        Log.i("sdsfsf_","dfdfd___RemarkMainActivity.loginSuccess"+RemarkMainActivity.userid);
        if (RemarkMainActivity.userid != null && RemarkMainActivity.userid.length() > 0) {
            Intent i = new Intent(SettingActivity.this, MainActivity.class);
            i.putExtra("name","Setting");
            startActivity(i);
            finish();//add 09112020
        } else {


                // Intent i = new Intent(SettingActivity.this, SampleActivity.class);
                Intent i = new Intent(SettingActivity.this, RemarkMainActivity.class);
                startActivity(i);
                finish();//add 02092021

//           // Intent i = new Intent(SettingActivity.this, SampleActivity.class);
//            Intent i = new Intent(SettingActivity.this, RemarkMainActivity.class);
//            startActivity(i);
//            finish();//add 02092021
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_setting_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_language) {
//            LanguageSheetFragment languageSheetFragment = new LanguageSheetFragment();
//            languageSheetFragment.show(getSupportFragmentManager(), languageSheetFragment.getTag());
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
