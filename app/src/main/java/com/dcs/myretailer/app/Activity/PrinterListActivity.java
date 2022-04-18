package com.dcs.myretailer.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.PrinterAdapter;
import com.dcs.myretailer.app.Setting.PrinterList;
import com.dcs.myretailer.app.databinding.ActivityPrinterListBinding;

import java.util.ArrayList;
import java.util.List;

public class PrinterListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static List<PrinterList> printerlistt = new ArrayList<PrinterList>();
    Context context;
    ActivityPrinterListBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_printer_list);

        context = PrinterListActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Hardware Configuration");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getPrinterList();

//        screenDisplay();

        PrinterAdapter customAdapter = new PrinterAdapter(getApplicationContext(),printerlistt);
        binding.taxListView.setAdapter(customAdapter);
        binding.taxListView.setOnItemClickListener(this);
    }

//    private void screenDisplay() {
//        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//     if (terminalTypeVal.equals(Constraints.IMIN)){
//        String device = Query.GetDeviceData(Constraints.DEVICE);
//        Log.i("sdf__device","device___"+device);
//        if (device.equals("M2-Max")) {
//            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams.leftMargin = 10;
//            binding.layOverAll.setLayoutParams(linearOverAllParams);
//
//        }
//
//    }
//    }

    private void getPrinterList() {
        Cursor c = DBFunc.Query("SELECT id,name FROM Printer ORDER BY ID ASC", true);
        if(c!=null){
            printerlistt.clear();
            while(c.moveToNext()){
                PrinterList tax = new PrinterList(c.getInt(0),c.getString(1));
                printerlistt.add(tax);
            }
            c.close();
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            menu.findItem(R.id.action_add_printer).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_printer).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_printer_actions, menu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //return true;


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_printer) {
            //Intent addTaxConfiguration = new Intent(getApplicationContext(),AddTaxConfigurationsActivity.class);
            //Intent addTaxConfiguration = new Intent(TaxConfigurationActivity.this,AddTaxConfigurationsActivity.class);
            Intent addTaxConfiguration = new Intent(PrinterListActivity.this,HardwareActivity.class);
            addTaxConfiguration.putExtra("ID","null");
            startActivity(addTaxConfiguration);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("_testposition",position+"_"+printerlistt.get(position).getId());
        // Toast.makeText(this, "_testposition"+position+"_"+taxes.get(position).getTaxID(), Toast.LENGTH_SHORT).show();
         Intent i = new Intent(PrinterListActivity.this,HardwareActivity.class);
        i.putExtra("ID",printerlistt.get(position).getId().toString());
        startActivity(i);
        finish();

    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(PrinterListActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
