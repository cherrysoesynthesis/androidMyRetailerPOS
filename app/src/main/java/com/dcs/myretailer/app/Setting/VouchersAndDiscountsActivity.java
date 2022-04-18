package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Activity.AddNewVouchersAndDiscountActivity;
import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityVouchersAndDiscountsBinding;

import java.util.ArrayList;
import java.util.List;

public class VouchersAndDiscountsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static List<DiscountClass> discounts = new ArrayList<DiscountClass>();
    //ListView discountListView;
    Context context;
    ActivityVouchersAndDiscountsBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vouchers_and_discounts);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_vouchers_and_discounts);

        context = VouchersAndDiscountsActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Vouchers & Discounts");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        discounts = Query.getDiscountAll("DiscountAll");

        if (discounts.size() > 0) {
            DiscountAdapter customAdapter = new DiscountAdapter(this, discounts);
            binding.discountListView.setAdapter(customAdapter);
            binding.discountListView.setOnItemClickListener(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_discount_configurations_actions, menu);
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

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        String chkSubmitSalesOrNot = Query.GetOptions(22);
//        if (chkSubmitSalesOrNot.equals("1")) {
//            menu.findItem(R.id.action_add_discount).setVisible(false);
//        }else {
//            menu.findItem(R.id.action_add_discount).setVisible(true);
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_discount) {
            Intent addTaxConfiguration = new Intent(VouchersAndDiscountsActivity.this, AddNewVouchersAndDiscountActivity.class);
            //addTaxConfiguration.putExtra("ID","null");
            addTaxConfiguration.putExtra("ID","0");
            startActivity(addTaxConfiguration);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.i("_testposition",position+"_"+discounts.get(position).getDiscount_ID());
        //Toast.makeText(this, "_testposition"+position+"_"+discounts.get(position).getDiscount_ID(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(VouchersAndDiscountsActivity.this,AddNewVouchersAndDiscountActivity.class);
        i.putExtra("ID",discounts.get(position).getDiscount_ID().toString());
        startActivity(i);
        finish();

    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(VouchersAndDiscountsActivity.this);
        Intent i = new Intent(context, SettingActivity.class);
        startActivity(i);
        finish();
    }
}