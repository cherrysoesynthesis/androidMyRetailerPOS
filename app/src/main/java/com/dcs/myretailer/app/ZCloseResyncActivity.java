package com.dcs.myretailer.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.Setting.SettingActivity;
import com.dcs.myretailer.app.ZClose.ZClose;
import com.dcs.myretailer.app.databinding.ActivityZCloseResyncBinding;

import java.util.ArrayList;
import java.util.List;

public class ZCloseResyncActivity extends AppCompatActivity implements View.OnClickListener {
    public static ActivityZCloseResyncBinding binding;
//    public static String uuid;
    Context context;
    public static List<String> arr = new ArrayList<>();
    public static List<String> arrValues = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this,R.layout.activity_z_close_resync);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle(Constraints.ZCloseTitle);

        context = getApplicationContext();

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//
//        try {
//            if (binding.txtUuidZcloseHidden.getText().toString() != null && binding.txtUuidZcloseHidden.getText().toString().length() > 0) {
//                getZCloseResyncByUUId(binding.txtUuidZcloseHidden.getText().toString());
//            }
//        }catch (Exception e){
//
//        }

//        RecyclerViewZCloseResyncAdapter myAdapter = new RecyclerViewZCloseResyncAdapter(
//                getApplicationContext(),arr,
//                arrValues);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//        binding.zcloseResyncRecyclerviewId.setLayoutManager(gridLayoutManager);
//        binding.zcloseResyncRecyclerviewId.setAdapter(myAdapter);
//
        binding.imgZclose.setOnClickListener(this);
        binding.btnShowResyncZclose.setOnClickListener(this);
        binding.btnResyncZclose.setOnClickListener(this);
        binding.btnResyncZclose.setOnClickListener(this);

//        initUI();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

    private void getZCloseResyncByUUId(String uuid) {

        try {

            if (binding.LayAll.getVisibility() == View.VISIBLE) {

                try {
                    if (uuid != null && uuid.length() > 0) {

                        ZClose zcloseObj = Query.getZClose(context, uuid , "show");

                        binding.editTextZreadno.setText(zcloseObj.getZReadNo().toString());

                        binding.editTextTransFrom.setText(zcloseObj.getTransNoFrom().toString());
                        binding.editTextTransTo.setText(zcloseObj.getTransNoFrom().toString());

                        String opdt = Query.changeLongtoDateFormat(zcloseObj.getOpeningTime());
                        String czdt = Query.changeLongtoDateFormat(zcloseObj.getClosingTime());

                        binding.editTextOpening.setText(String.valueOf(opdt));
                        binding.editTextClosing.setText(String.valueOf(czdt));


                        binding.editTextSyncStatus.setText(zcloseObj.getResponseStatus());
                    }
                }catch (Exception e){

                }


            }
        } catch (Exception e){

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_zclose:
//                ProductCategorySheetFragment pcSSheetFragment = new ProductCategorySheetFragment();
                ZSheetFragment pcSSheetFragment = new ZSheetFragment();
                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
            break;
            case R.id.btn_show_resync_zclose:
                if (binding.btnShowResyncZclose.getText().toString().equals("SHOW")) {
                    binding.LayAll.setVisibility(View.VISIBLE);
                    binding.btnShowResyncZclose.setText("HIDE");
                } else {
                    binding.LayAll.setVisibility(View.GONE);
                    binding.btnShowResyncZclose.setText("SHOW");
                }

                Log.i("DSfsfds___","uuid___"+binding.txtUuidZcloseHidden.getText().toString());
                getZCloseResyncByUUId(binding.txtUuidZcloseHidden.getText().toString());
            break;
            case R.id.btn_resync_zclose:
                    Query.getZClose(context,binding.txtUuidZcloseHidden.getText().toString(),"resync");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(ProductManagementActivity.this);
        Intent i = new Intent(ZCloseResyncActivity.this, SettingActivity.class);
        startActivity(i);
        finish();//add 09112020
    }
//
//    private void initUI() {
//        //UI reference of textView
//        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);
//
//        // create list of customer
//        ArrayList<String> customerList = getCustomerList();
//
//        //Create adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, customerList);
//
//        //Set adapter
//        customerAutoTV.setAdapter(adapter);
//
//        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(ZCloseResyncActivity.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    private ArrayList<String> getCustomerList()
//    {
//        ArrayList<String> customers = new ArrayList<>();
//        customers.add("James");
//        customers.add("Mary");
//        customers.add("Paul");
//        customers.add("Michael");
//        customers.add("William");
//        customers.add("Daniel");
//        customers.add("Thomas");
//        customers.add("Sarah");
//        customers.add("Sophia");
//        return customers;
//    }
}