package com.dcs.myretailer.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.PermissionGroupSheetFragment;

public class AddStaffManagementActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_permission_group;
    Button btn_add_staff,btn_delete_staff;
    EditText edit_permission_group;
    EditText edit_text_staff_name;
    EditText edit_text_staff_pin;
    EditText edit_text_staff_commission;
    public static Integer selected_permission_group_id = 0;
    public static String selected_permission_group_name = "0";
    String ID = "0";
    Handler mHandler;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_management);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Staff Management");

        context = AddStaffManagementActivity.this;

        edit_text_staff_name = (EditText) findViewById(R.id.edit_text_staff_name);
        edit_text_staff_pin = (EditText) findViewById(R.id.edit_text_staff_pin);
        edit_text_staff_commission = (EditText) findViewById(R.id.edit_text_staff_commission);

        btn_add_staff = (Button) findViewById(R.id.btn_add_staff);

        img_permission_group = (ImageView) findViewById(R.id.img_permission_group);
        edit_permission_group = (EditText) findViewById(R.id.edit_permission_group);
        btn_delete_staff = (Button) findViewById(R.id.btn_delete_staff);

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        if (ID.equals("null")){
            setTitle("Add Staff");
            btn_add_staff.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            btn_add_staff.setText("Add");
            btn_delete_staff.setVisibility(View.GONE);
        }else{
            setTitle("Edit Staff");
            btn_add_staff.setText("Update");
            btn_delete_staff.setVisibility(View.VISIBLE);
        }
        if (!ID.equals("null")) {
            ShowEditStaff(Integer.parseInt(ID));
        }
        img_permission_group.setOnClickListener(this);
        btn_add_staff.setOnClickListener(this);
        btn_delete_staff.setOnClickListener(this);

        this.mHandler = new Handler();
        m_Runnable.run();
    }


    private void ShowEditStaff(int id) {

        String sql = "SELECT name,pin,commission,permission_group_id,permission_group_name " +
                "FROM Staff WHERE ID = " + id;

        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    edit_text_staff_name.setText(c.getString(0));
                    edit_text_staff_pin.setText(c.getString(1));
                    edit_text_staff_commission.setText(c.getString(2));
                    selected_permission_group_id = c.getInt(3);
                    selected_permission_group_name = c.getString(4);
                }
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_permission_group:
                PermissionGroupSheetFragment pcSSheetFragment = new PermissionGroupSheetFragment();
                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
            break;
            case R.id.btn_add_staff:
                String password = edit_text_staff_pin.getText().toString();
                if (password.length() != 4){
                    //Query.ErrorDialog(getApplicationContext(), ENUM.PasswordLength);
//                    new AlertDialog.Builder(c, R.style.AlertDialogStyle)
//                            .setMessage(msg)
//                            .setCancelable(false)
//                            .setNegativeButton(ENUM.OK, null)
//                            .show();
                }
                if (ID.equals("null")) {
                    SaveStaff();
                }else{
                    UpdateStaff(ID);
                }
                break;
            case R.id.btn_delete_staff:
                deleteStaff(ID);
                break;
        }
    }

    private void SaveStaff() {
        Integer permissionGroup_IDD = 0;
        String permissionGroup_Namee = "0";
        try {
            if (selected_permission_group_id > 0) {
                permissionGroup_IDD = selected_permission_group_id;
            }else{
                permissionGroup_IDD = 0;
            }
            if (selected_permission_group_name.equals("0")) {
                permissionGroup_Namee = "0";
            }else{
                permissionGroup_Namee = selected_permission_group_name;
            }
            String sql = "INSERT INTO Staff (name,pin,commission,permission_group_id," +
                    "permission_group_name,DateTime) " +
                    "VALUES ('" + DBFunc.PurifyString(edit_text_staff_name.getText().toString()) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(edit_text_staff_pin.getText().toString())) + "','" +
                     DBFunc.PurifyString(edit_text_staff_commission.getText().toString()) + "','" +
                     DBFunc.PurifyString(permissionGroup_IDD.toString()) + "','" +
                     DBFunc.PurifyString(permissionGroup_Namee) + "'," +
                    "" + System.currentTimeMillis() + ")";

            DBFunc.ExecQuery(sql, true);
            selected_permission_group_id = 0;
            selected_permission_group_name = "0";
            Intent i = new Intent(AddStaffManagementActivity.this,StaffManagementActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run(){

            if (selected_permission_group_id > 0) {
               edit_permission_group.setText(selected_permission_group_name);
            }
            AddStaffManagementActivity.this.mHandler.postDelayed(m_Runnable,300);
        }
    };

    private void UpdateStaff(String ID) {
        try{
            String query = "UPDATE Staff SET ";
            query += "name = '"+DBFunc.PurifyString(edit_text_staff_name.getText().toString())+"',";
            query += "pin = '"+DBFunc.PurifyString(edit_text_staff_pin.getText().toString())+"',";
            query += "commission = '"+DBFunc.PurifyString(edit_text_staff_commission.getText().toString())+"',";
            if (selected_permission_group_id > 0) {
                query += "permission_group_id = '" + selected_permission_group_id + "', ";
            }else{
                query += "permission_group_id = '0', ";
            }
            if (selected_permission_group_name.equals("0")) {
                query += "permission_group_name = '0', ";
            }else{
                query += "permission_group_name = '"+selected_permission_group_name+"', ";
            }
            query += "DateTime = "+System.currentTimeMillis()+" ";
            query += "WHERE ID = "+Integer.parseInt(ID);


            DBFunc.ExecQuery(query, true);

            selected_permission_group_id = 0;
            selected_permission_group_name = "0";

            Intent i = new Intent(AddStaffManagementActivity.this,StaffManagementActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }

    }

    private void deleteStaff(String ID) {
        try{
            selected_permission_group_id = 0;
            selected_permission_group_name = "0";
            DBFunc.ExecQuery("DELETE FROM Staff WHERE ID = "+Integer.parseInt(ID), true);

            Intent i = new Intent(AddStaffManagementActivity.this,StaffManagementActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }
}
