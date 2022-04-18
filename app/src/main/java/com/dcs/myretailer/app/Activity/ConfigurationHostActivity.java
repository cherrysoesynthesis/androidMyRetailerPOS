package com.dcs.myretailer.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.ConfigurationsAdapter;
import com.dcs.myretailer.app.databinding.ActivityConfigurationHostBinding;

import java.util.ArrayList;

public class ConfigurationHostActivity extends AppCompatActivity {
    public static ArrayList<Integer> ID = new ArrayList<Integer>();
    public static ArrayList<String> NAME = new ArrayList<String>();
    public static ArrayList<String> ACTIVE = new ArrayList<String>();
    Handler mHandler;
    Context context;
    ActivityConfigurationHostBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_configuration_host);

        context = ConfigurationHostActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Configuration Host");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getAllConfiguration();

        //configurationListView.setOnItemClickListener(this);

        //PaymentTypesAdapter customAdapter = new PaymentTypesAdapter(getApplicationContext(), PaymentTypeName, PaymentTypeOnOff);
        ConfigurationsAdapter customAdapter = new ConfigurationsAdapter(ConfigurationHostActivity.this, ID, NAME,ACTIVE);
//        PaymentTypesAdapter customAdapter = new PaymentTypesAdapter(getApplicationContext(), lstPaymentType);
        binding.configurationListView.setAdapter(customAdapter);
        binding.configurationListView.setClickable(true);
        //
        binding.configurationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
               // Toast.makeText(ConfigurationHostActivity.this, String.valueOf(ID.get(position)), Toast.LENGTH_SHORT).show();
                Log.i("dfa__", String.valueOf(ID.get(position)));
                Intent addNewProduct = new Intent(getApplicationContext(),AddNewConfigurationActivity.class);
                addNewProduct.putExtra("ID",String.valueOf(ID.get(position)));
                startActivity(addNewProduct);
                finish();
            }
        });

        this.mHandler = new Handler();
        m_Runnable.run();
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {

            getAllConfiguration();

            //configurationListView.setOnItemClickListener(this);

            //PaymentTypesAdapter customAdapter = new PaymentTypesAdapter(getApplicationContext(), PaymentTypeName, PaymentTypeOnOff);
            ConfigurationsAdapter customAdapter = new ConfigurationsAdapter(ConfigurationHostActivity.this, ID, NAME,ACTIVE);
//        PaymentTypesAdapter customAdapter = new PaymentTypesAdapter(getApplicationContext(), lstPaymentType);
            binding.configurationListView.setAdapter(customAdapter);
            binding.configurationListView.setClickable(true);
            binding.configurationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view, int position, long id) {
                    //Toast.makeText(ConfigurationHostActivity.this, String.valueOf(ID.get(position)), Toast.LENGTH_SHORT).show();
                    Intent addNewProduct = new Intent(getApplicationContext(),AddNewConfigurationActivity.class);
                    addNewProduct.putExtra("ID",String.valueOf(ID.get(position)));
                    startActivity(addNewProduct);
                    finish();
                }
            });
        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_new_configuration_actions, menu);
//        Log.i("dsdfdfdd___", String.valueOf(myAdapter.getSelectedItemCount()));
//        if (myAdapter.getSelectedItemCount() == 0) {
//            menu.findItem(R.id.action_delete_product).setVisible(false);
//        } else {
//            menu.findItem(R.id.action_delete_product).setVisible(true);
//        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //return true;


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            menu.findItem(R.id.action_add_configuration).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_configuration).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_configuration) {
            //int latestID = findLatestID("PLU");
            Intent addNewProduct = new Intent(getApplicationContext(),AddNewConfigurationActivity.class);
            addNewProduct.putExtra("ID","null");
            startActivity(addNewProduct);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllConfiguration() {
        Cursor data = DBFunc.Query("SELECT * FROM "+ Constraints.ConfigPaymentHost +" ORDER BY ID ASC", true);
        if(data!=null){
            ID.clear();
            NAME.clear();
            ACTIVE.clear();
            while(data.moveToNext()){
                ID.add(data.getInt(0));
                NAME.add(data.getString(3));
                ACTIVE.add(data.getString(4));
            }
            data.close();
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "_testposition"+position, Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(ConfigurationHostActivity.this,AddNewConfigurationActivity.class);
//        i.putExtra("ID",ID.get(position));
//        startActivity(i);
//    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(ConfigurationHostActivity.this);
        Intent i = new Intent(ConfigurationHostActivity.this,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
