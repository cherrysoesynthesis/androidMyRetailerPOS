package com.dcs.myretailer.app.Cashier;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityItemDiscountBinding;
import com.google.android.material.tabs.TabLayout;

public class ItemDiscountActivity extends AppCompatActivity {
    public static String name = "";
    ActivityItemDiscountBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_item_discount);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_item_discount);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Item Discount");

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        TabFragmentAmount.ChekingCheckoutOrEditProductFragment = name;
        TabFragmentPercentage.ChekingCheckoutOrEditProductFragment = name;

//        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
//        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        binding.tabLayoutItemDiscount.addTab(binding.tabLayoutItemDiscount.newTab().setText("Percentage(%)"));
        binding.tabLayoutItemDiscount.addTab(binding.tabLayoutItemDiscount.newTab().setText("Value($)"));
        binding.tabLayoutItemDiscount.setTabTextColors(getResources().getColor(R.color.mine_shaft),
                getResources().getColor(R.color.mine_shaft));
        binding.tabLayoutItemDiscount.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayoutItemDiscount.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayoutItemDiscount.setTabTextColors(Color.parseColor("#a9aaad"), Color.parseColor("#000000"));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_item_discount);
        final PagerItemDiscountAdapter adapter = new PagerItemDiscountAdapter
                (getSupportFragmentManager(), binding.tabLayoutItemDiscount.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutItemDiscount));
        binding.tabLayoutItemDiscount.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        //tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ItemDiscountActivity.this,MainActivity.class);
        startActivity(i);
        finish();

        if (name.equals("Checkout")) {
            Log.i("btn_apply__","btn_apply____112");
            //CheckOutActivity.str_percentage = binding.editTextDiscountAmount.getText().toString();
            //CheckOutActivity.str_percentage_value = binding.editTextDiscountAmount.getText().toString().replace(" % ","");

            Intent intent = new Intent(this, CheckOutActivity.class);
            intent.putExtra("BillNo",CheckOutActivity.BillNo);
            intent.putExtra("Status","TabFragmentPercentage");
            intent.putExtra("StatusSALES","");
            startActivity(intent);
            finish();
        }else if (name.equals("CheckoutItem")) {
            Log.i("btn_apply__","btn_apply____12");
            //CheckOutActivity.str_percentage = binding.editTextDiscountAmount.getText().toString();
            //CheckOutActivity.str_percentage_value = binding.editTextDiscountAmount.getText().toString().replace(" % ","");

            Intent intent = new Intent(this, CheckOutActivity.class);
            intent.putExtra("BillNo",CheckOutActivity.BillNo);
            intent.putExtra("Status","TabFragmentPercentage_CheckoutForItem");
            intent.putExtra("StatusSALES","");
            startActivity(intent);
            finish();
        }else{
            Log.i("btn_apply__","btn_apply____13");
            //EditProductSheetFragment.str_percentage = binding.editTextDiscountAmount.getText().toString();
            //EditProductSheetFragment.str_percentage_value = binding.editTextDiscountAmount.getText().toString().replace(" % ","");

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", "TabFragmentPercentage");
            startActivity(intent);
            finish();

//                        FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
//                        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//                        editProductSheetFragment.show(manager, editProductSheetFragment.getTag());

        }
    }
}
