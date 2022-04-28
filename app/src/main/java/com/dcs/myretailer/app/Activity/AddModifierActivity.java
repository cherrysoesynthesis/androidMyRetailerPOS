package com.dcs.myretailer.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.ModifierData;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.ModifierActivity;
import com.dcs.myretailer.app.databinding.ActivityAddModifierBinding;

public class AddModifierActivity extends AppCompatActivity  implements View.OnClickListener {
    String ID = "0";
    Context context;
    ActivityAddModifierBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_modifier);
        binding.setLifecycleOwner(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Permission Group Management");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        context = AddModifierActivity.this;
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);

        LinearLayout.LayoutParams linearLaymodiname = new LinearLayout.LayoutParams(720,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLaymodiname.leftMargin = 10;
        linearLaymodiname.topMargin = 10;
        binding.LayModiName.setLayoutParams(linearLaymodiname);
        binding.LayModiPrice.setLayoutParams(linearLaymodiname);

        binding.LayBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (ID.equals("null")){
            setTitle(Constraints.Add_Modifier);
            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                binding.btnAddModifier.setLayoutParams(new LinearLayout.LayoutParams(520, 90));
            }  else if (terminalTypeVal.equals(Constraints.IMIN)){
                if (device.equals("M2-Max")) {

                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearbtnAdd = new LinearLayout.LayoutParams(720,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearbtnAdd.leftMargin = 30;
                    linearbtnAdd.leftMargin = 20;
                    binding.btnAddModifier.setLayoutParams(linearbtnAdd);


                    LinearLayout.LayoutParams linearLaymodiname1 = new LinearLayout.LayoutParams(720,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLaymodiname1.leftMargin = 20;
                    linearLaymodiname1.topMargin = 10;
                    binding.LayModiName.setLayoutParams(linearLaymodiname1);
                    binding.LayModiPrice.setLayoutParams(linearLaymodiname1);
                } else {

                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearbtnAdd = new LinearLayout.LayoutParams(630,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearbtnAdd.leftMargin = 20;
                    linearbtnAdd.topMargin = 20;
                    binding.btnAddModifier.setLayoutParams(linearbtnAdd);


                    LinearLayout.LayoutParams linearLaymodiname1 = new LinearLayout.LayoutParams(630,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLaymodiname1.leftMargin = 20;
                    linearLaymodiname1.topMargin = 10;
                    binding.LayModiName.setLayoutParams(linearLaymodiname1);
                    binding.LayModiPrice.setLayoutParams(linearLaymodiname1);
                }
            } else {
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 20;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                LinearLayout.LayoutParams linearbtnAdd = new LinearLayout.LayoutParams(630,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearbtnAdd.leftMargin = 20;
                linearbtnAdd.topMargin = 20;
                binding.btnAddModifier.setLayoutParams(linearbtnAdd);


                LinearLayout.LayoutParams linearLaymodiname1 = new LinearLayout.LayoutParams(630,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLaymodiname1.leftMargin = 20;
                linearLaymodiname1.topMargin = 10;
                binding.LayModiName.setLayoutParams(linearLaymodiname1);
                binding.LayModiPrice.setLayoutParams(linearLaymodiname1);
            }
            binding.btnAddModifier.setText(Constraints.ADD);
            binding.btnDeleteModifier.setVisibility(View.GONE);
        }else{
            setTitle(Constraints.EDIT_Modifier);
            binding.btnAddModifier.setText(Constraints.UPDATE);
            binding.btnDeleteModifier.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams linearbtnAdd = new LinearLayout.LayoutParams(320,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearbtnAdd.leftMargin = 30;
            linearbtnAdd.leftMargin = 20;
            binding.btnAddModifier.setLayoutParams(linearbtnAdd);
            binding.btnDeleteModifier.setLayoutParams(linearbtnAdd);

            if (terminalTypeVal.equals(Constraints.IMIN)) {
                Log.i("sdf__device", "device___" + device);
                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearLaymodiname1 = new LinearLayout.LayoutParams(630,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLaymodiname1.leftMargin = 20;
                    linearLaymodiname1.topMargin = 10;
                    binding.LayModiName.setLayoutParams(linearLaymodiname1);
                    binding.LayModiPrice.setLayoutParams(linearLaymodiname1);

                    LinearLayout.LayoutParams linearbtnAdd1 = new LinearLayout.LayoutParams(310,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearbtnAdd1.leftMargin = 20;
                    //linearbtnAdd1.topMargin = 20;
                    binding.btnAddModifier.setLayoutParams(linearbtnAdd1);
                    binding.btnDeleteModifier.setLayoutParams(linearbtnAdd1);
                } else {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearLaymodiname1 = new LinearLayout.LayoutParams(630,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLaymodiname1.leftMargin = 20;
                    linearLaymodiname1.topMargin = 10;
                    binding.LayModiName.setLayoutParams(linearLaymodiname1);
                    binding.LayModiPrice.setLayoutParams(linearLaymodiname1);

                    LinearLayout.LayoutParams linearbtnAdd1 = new LinearLayout.LayoutParams(310,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearbtnAdd1.leftMargin = 20;
                    //linearbtnAdd1.topMargin = 20;
                    binding.btnAddModifier.setLayoutParams(linearbtnAdd1);
                    binding.btnDeleteModifier.setLayoutParams(linearbtnAdd1);
                }
            }
        }
        binding.btnAddModifier.setOnClickListener(this);
        binding.btnDeleteModifier.setOnClickListener(this);
        if (!ID.equals("null")) {
            ShowModifier(Integer.parseInt(ID));
        }
    }

    private void ShowModifier(int id) {
        String sql = "SELECT Name,Price,DateTime FROM Product_Modifier WHERE ID = " + id;
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
//                    binding.editTextPermissionModifierName.setText(c.getString(0));
                    binding.setModifierData(new ModifierData(id,c.getString(0),c.getString(1),c.getLong(2)));
                }
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.edit_text_permission_group_name:
            case R.id.btn_add_modifier:
                String name = binding.editTextPermissionModifierName.getText().toString();
                String price = binding.editTextPermissionModifierPrice.getText().toString();
                if (ID.equals("null")) {
                    if (name.isEmpty()){
                        Query.ErrorDialog(this, Constraints.EmptyName);
                    }
                    //Query.SavePermissionGroup(name);

                    if (price.isEmpty()){
                        price = "0";
                    }
                    Query.SaveModifier(name,price);

                    Intent i = new Intent(context, ModifierActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    UpdateModifier(name,price,ID);
                }
                break;
            case R.id.btn_delete_modifier:
                deleteModifier(ID);
                break;
        }
    }



    private void UpdateModifier(String Name, String  Price, String ID) {
        try{
            String query = "UPDATE Product_Modifier SET ";
            query += "Name = '"+DBFunc.PurifyString(Name)+"',";
            query += "Price = '"+DBFunc.PurifyString(Price)+"',";
            query += "DateTime = "+System.currentTimeMillis()+" ";
            query += "WHERE ID = "+Integer.parseInt(ID);
            Log.i("_query",query);

            DBFunc.ExecQuery(query, true);
            Intent i = new Intent(context,ModifierActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }

    }

    private void deleteModifier(String ID) {
        try{
            DBFunc.ExecQuery("DELETE FROM Product_Modifier WHERE ID = "+Integer.parseInt(ID), true);

            Toast.makeText(this, "Deleted Successfully.", Toast.LENGTH_SHORT).show();
            Log.i("dellete","Delectedsucc");

            Intent i = new Intent(context,ModifierActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(CategoryManagementActivity.this);
        Intent i = new Intent(AddModifierActivity.this, ModifierActivity.class);
        startActivity(i);
        finish();
    }
}
