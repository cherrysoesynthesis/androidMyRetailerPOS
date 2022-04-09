package com.dcs.myretailer.app.Setting;

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
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityTaxConfigurationBinding;

import java.util.ArrayList;
import java.util.List;

public class TaxConfigurationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static List<TaxClass> taxes = new ArrayList<TaxClass>();
    //ListView taxListView;
    ActivityTaxConfigurationBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tax_configuration);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tax_configuration);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Tax Configuration");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getTax();
        TaxAdapter customAdapter = new TaxAdapter(getApplicationContext(),taxes);
        binding.taxListView.setAdapter(customAdapter);
        binding.taxListView.setOnItemClickListener(this);
    }

    private void getTax() {
        Cursor c = DBFunc.Query("SELECT ID, Name, Acronym, Rate, Type, Seq FROM Tax ORDER BY Seq", true);
        if(c!=null){
            taxes.clear();
            while(c.moveToNext()){
                TaxClass tax = new TaxClass(c.getLong(0));
                tax.setTaxID(c.getLong(0));
                tax.setName(c.getString(1));
                tax.setAcronym(c.getString(2));
                tax.setRate(c.getDouble(3));
                tax.setType(c.getInt(4));
                taxes.add(tax);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_tax_configurations_actions, menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //return true;


        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        String chkSubmitSalesOrNot = Query.GetOptions(22);
//        if (chkSubmitSalesOrNot.equals("1")) {
//            menu.findItem(R.id.action_add_tax_configurations).setVisible(false);
//        }else {
//            menu.findItem(R.id.action_add_tax_configurations).setVisible(true);
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_tax_configurations) {
            Intent addTaxConfiguration = new Intent(TaxConfigurationActivity.this,AddTaxConfigurationActivity.class);
            addTaxConfiguration.putExtra("ID","null");
            startActivity(addTaxConfiguration);
            //finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("_testposition",position+"_"+taxes.get(position).getTaxID());
       // Toast.makeText(this, "_testposition"+position+"_"+taxes.get(position).getTaxID(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(TaxConfigurationActivity.this,AddTaxConfigurationActivity.class);
        i.putExtra("ID",taxes.get(position).getTaxID().toString());
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(TaxConfigurationActivity.this);
        Intent i = new Intent(TaxConfigurationActivity.this,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
