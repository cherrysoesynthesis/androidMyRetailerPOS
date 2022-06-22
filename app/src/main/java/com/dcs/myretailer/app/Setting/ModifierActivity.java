package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Activity.AddModifierActivity;
import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityModifierBinding;

import java.util.ArrayList;

public class ModifierActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<String> modiName = new ArrayList<String>();
    ArrayList<String> modiPrice = new ArrayList<String>();
    ArrayList<String> modiID = new ArrayList<String>();
    Context context;
    ActivityModifierBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_modifier);
        context = ModifierActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Modifier Management");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //str_product_management = "1";
                onBackPressed();
            }
        });
        getModifier();

        ModifierAdapter customAdapter = new ModifierAdapter(getApplicationContext(), modiName,modiPrice,modiID);
        binding.modifierListView.setAdapter(customAdapter);
        binding.modifierListView.setOnItemClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_new_modifier_actions, menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //return true;


        return super.onCreateOptionsMenu(menu);
    }
    private void getModifier() {
        Cursor c = DBFunc.Query("SELECT ID,Name,Price FROM Product_Modifier ORDER BY ID", true);
        if (c != null) {
            modiName.clear();
            modiPrice.clear();
            modiID.clear();
            while (c.moveToNext()) {
                //PermissionClass per_Group = new PermissionClass(c.getInt(0),c.getString(1));
                modiName.add(c.getString(1));
                modiPrice.add(c.getString(2));
                modiID.add(c.getString(0));
            }
            c.close();
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            menu.findItem(R.id.action_add_new_modifier).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_new_modifier).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_modifier) {
            //int latestID = findLatestID("PLU");

            Intent addNewProduct = new Intent(getApplicationContext(), AddModifierActivity.class);
            addNewProduct.putExtra("ID","null");
            startActivity(addNewProduct);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(this, "_testposition"+position+"_"+taxes.get(position).getTaxID(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, AddModifierActivity.class);
        i.putExtra("ID", String.valueOf(modiID.get(position)));
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(ModifierActivity.this);
        Intent i = new Intent(context, SettingActivity.class);
        startActivity(i);
        finish();
    }
}
