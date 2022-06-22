package com.dcs.myretailer.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.PermissionAdapter;
import com.dcs.myretailer.app.Setting.PermissionClass;
import com.dcs.myretailer.app.databinding.ActivityPermissionGroupBinding;

import java.util.ArrayList;
import java.util.List;

public class PermissionGroupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    static List<PermissionClass> permissionGroup = new ArrayList<PermissionClass>();
    //ListView taxListView;
    Context context;
    ActivityPermissionGroupBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_permission_group);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_permission_group);

        context = PermissionGroupActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Permission Group Management");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getPermissionGroup();

        PermissionAdapter customAdapter = new PermissionAdapter(getApplicationContext(), permissionGroup);
        binding.taxListView.setAdapter(customAdapter);
        binding.taxListView.setOnItemClickListener(this);
    }
    private void getPermissionGroup() {
        Cursor c = DBFunc.Query("SELECT ID, Name FROM PermissionGroup ORDER BY ID", true);
        if (c != null) {
            permissionGroup.clear();
            while (c.moveToNext()) {
                PermissionClass per_Group = new PermissionClass(c.getInt(0),c.getString(1));
                permissionGroup.add(per_Group);
            }
            c.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_staff_configurations_actions, menu);
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
            menu.findItem(R.id.action_add_staff_configurations).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_staff_configurations).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_staff_configurations) {
            //Intent addTaxConfiguration = new Intent(getApplicationContext(),AddTaxConfigurationsActivity.class);
            //Intent addTaxConfiguration = new Intent(TaxConfigurationActivity.this,AddTaxConfigurationsActivity.class);
            Intent addTaxConfiguration = new Intent(PermissionGroupActivity.this, AddPermissionGroupActivity.class);
            addTaxConfiguration.putExtra("ID", "null");
            startActivity(addTaxConfiguration);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Toast.makeText(this, "_testposition"+position+"_"+taxes.get(position).getTaxID(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(PermissionGroupActivity.this, AddPermissionGroupActivity.class);
        i.putExtra("ID", String.valueOf(permissionGroup.get(position).getPermission_group_id()));
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(PermissionGroupActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}

