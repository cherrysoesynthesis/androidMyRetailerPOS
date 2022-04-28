package com.dcs.myretailer.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import com.dcs.myretailer.app.R;


public class AddTaxConfigurationsActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_tax_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tax_configurations);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Tax Configuration");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        btn_tax_type = (Button) findViewById(R.id.btn_tax_type);
//        btn_tax_type.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btn_tax_type:
//                    Log.i("dsaf_dsf","sdafdsf");
//                TaxTypeSheetFragment rdSheetFragment = new TaxTypeSheetFragment();
//                rdSheetFragment.show(getSupportFragmentManager(), rdSheetFragment.getTag());
//                break;
        }
    }
}
