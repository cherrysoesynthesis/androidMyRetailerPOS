package com.dcs.myretailer.app.Setting.StockManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.SettingActivity;
import com.dcs.myretailer.app.Stock.StockAgent;
import com.dcs.myretailer.app.databinding.ActivityStockManagementBinding;

import java.util.List;

//import com.dcs.myretailer.app.Cashier.RecyclerViewNoImageAdapter;

public class StockManagementActivity extends AppCompatActivity {
    List<StockAgent> stockAgent = null;
    ActivityStockManagementBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stock_management);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Stock Management");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 30;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }else if (terminalTypeVal.equals(Constraints.IMIN)) {

            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 30;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                FrameLayout.LayoutParams LayproductheaderParams = new FrameLayout.LayoutParams(350,
                        60);
                LayproductheaderParams.leftMargin = 30;
                binding.Layproductheader.setLayoutParams(LayproductheaderParams);
            }

        }

        stockAgent = Query.getStockAgent();
        if (stockAgent.size() > 0) {
            StockManagementAdapter adapter = new StockManagementAdapter(this, stockAgent);
            binding.recyclerViewStockManagement.setAdapter(adapter);
            binding.recyclerViewStockManagement.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            binding.recyclerViewStockManagement.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(StockManagementActivity.this, SettingActivity.class);
        startActivity(i);
        finish();
    }
}