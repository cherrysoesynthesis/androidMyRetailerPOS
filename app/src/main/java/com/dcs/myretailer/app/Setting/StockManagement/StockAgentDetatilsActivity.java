package com.dcs.myretailer.app.Setting.StockManagement;

import android.content.Intent;
import android.database.Cursor;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.R;

public class StockAgentDetatilsActivity extends AppCompatActivity {
    String StockAgent_pluID = "0";
    EditText edit_text_stock_product_name;
    EditText edit_text_system_qty;
    EditText edit_text_variant_qty;
    EditText edit_text_count_qty;
    EditText edit_text_stock_product_price;
   // List<StockAgent> stockAgent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_agent_detatils);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Stock Details");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent getIntent = getIntent();
        Bundle bundleName = getIntent.getExtras();
        if(bundleName!=null) {
            StockAgent_pluID = (String) bundleName.get("StockAgent_pluID");
        }
        //stockAgent = Query.getStockAgent("null");
        edit_text_stock_product_name = (EditText) findViewById(R.id.edit_text_stock_product_name);
        edit_text_system_qty = (EditText) findViewById(R.id.edit_text_system_qty);
        edit_text_variant_qty = (EditText) findViewById(R.id.edit_text_variant_qty);
        edit_text_count_qty = (EditText) findViewById(R.id.edit_text_count_qty);
        edit_text_stock_product_price = (EditText) findViewById(R.id.edit_text_stock_product_price);

        if (Integer.parseInt(StockAgent_pluID) > 0){
            String sql = "Select ID,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID FROM StockAgent " +
                    " WHERE PLUID = " + StockAgent_pluID +
                    " GROUP BY PLUID ORDER BY PLUID DESC";

            Cursor c = DBFunc.Query(sql,true);
            if (c != null){
                //stockAgent.clear();
                if (c.moveToNext()){
                    Integer qtySold = c.getInt(1);
                    Integer qtyAdjustment = c.getInt(1);
                    Integer qtyReturn = c.getInt(2);
                    Integer qtyBalance = c.getInt(3);
                    Integer qtyActual = c.getInt(4);
                    Integer PLUID = c.getInt(5);
                    String dateTime = c.getString(0);

                    //Query.GetPLU()

                    String sql_plu = "SELECT Name,Price,Code,Image,ID,ProductCategoryID,ProductCategoryName,UUID,AllowOpenPrice " +
                            "FROM PLU WHERE ID = " + StockAgent_pluID ;
                    Cursor c_plu = DBFunc.Query(sql_plu,true);
                    if (c_plu != null){
                        if (c_plu.moveToNext()){

                            String name = c_plu.getString(0);
                            String price = c_plu.getString(1);

                            edit_text_stock_product_name.setText(name);
                            edit_text_stock_product_price.setText(price);
                        }
                        c_plu.close();
                    }
                    edit_text_system_qty.setText(qtyActual.toString());
                    edit_text_variant_qty.setText(qtyAdjustment.toString());
                    edit_text_count_qty.setText(qtyBalance.toString());
                }
                c.close();
            }
        }


    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(StockAgentDetatilsActivity.this);
        Intent i = new Intent(StockAgentDetatilsActivity.this, StockManagementActivity.class);
        startActivity(i);
        finish();
    }
}