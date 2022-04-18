package com.dcs.myretailer.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;

public class AddPermissionGroupActivity extends AppCompatActivity  implements View.OnClickListener {
    Button btn_add_staff;
    Button btn_delete_permission_group;
    EditText edit_text_permission_group_name;
    String ID = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_permission_group);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Permission Group Management");

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        edit_text_permission_group_name = (EditText) findViewById(R.id.edit_text_permission_group_name);

        btn_add_staff = (Button) findViewById(R.id.btn_add_staff);
        btn_delete_permission_group = (Button) findViewById(R.id.btn_delete_permission_group);

        if (ID.equals("null")){
            setTitle("Add Permission Group");
            btn_add_staff.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            btn_add_staff.setText("Add");
            btn_delete_permission_group.setVisibility(View.GONE);
        }else{
            setTitle("Edit Permission Group");
            btn_add_staff.setText("Update");
            btn_delete_permission_group.setVisibility(View.VISIBLE);

            String chkSubmitSalesOrNot = Query.GetOptions(22);
            if (chkSubmitSalesOrNot.equals("1")) {
                edit_text_permission_group_name.setEnabled(false);
                edit_text_permission_group_name.setInputType(InputType.TYPE_NULL);
                btn_delete_permission_group.setVisibility(View.GONE);
                btn_add_staff.setVisibility(View.GONE);
            }
        }
        btn_add_staff.setOnClickListener(this);
        btn_delete_permission_group.setOnClickListener(this);
        if (!ID.equals("null")) {
            ShowEditPermissionGroup(Integer.parseInt(ID));
        }
    }

    private void ShowEditPermissionGroup(int id) {
        Log.i("Edit", String.valueOf(id));
        String sql = "SELECT Name FROM PermissionGroup WHERE ID = " + id;
        Log.i("Edit__s", String.valueOf(id));
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    edit_text_permission_group_name.setText(c.getString(0));
                }
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.edit_text_permission_group_name:
            case R.id.btn_add_staff:
                if (ID.equals("null")) {
                    String name = edit_text_permission_group_name.getText().toString();
                    if (name.isEmpty()){
                        Query.ErrorDialog(this, Constraints.EmptyName);
                    }
                    Query.SavePermissionGroup(name);
//                    String sql = " SELECT name " +
//                            "FROM PermissionGroup Where name = '" + name + "' ";
//                    Log.i("Dfdf_Permisp_",sql);
//                    Cursor c = DBFunc.Query(sql,true);
//                    if (c == null){
//                        //Log.i("Dfdf_Pc.getCount()up_", String.valueOf(c.getCount()));
//                        //if (c.getCount() == 0){
//                        sql = "INSERT INTO PermissionGroup (name,DateTime) " +
//                                "VALUES ('" + DBFunc.PurifyString(name) + "'," +
//                                "" + System.currentTimeMillis() + ")";
//                        Log.i("Dfdf_PermissionGroup_",sql);
//                        DBFunc.ExecQuery(sql, true);
//                        //}
//                        // c.close();
//                    }
                    finish();
                    Intent i = new Intent(AddPermissionGroupActivity.this,PermissionGroupActivity.class);
                    startActivity(i);
                }else{
                    UpdatePermissionGroup(ID);
                }
                break;
            case R.id.btn_delete_permission_group:
                    deletePermissionGroup(ID);
                break;
        }
    }



    private void UpdatePermissionGroup(String ID) {
        try{
            String query = "UPDATE PermissionGroup SET ";
            query += "Name = '"+DBFunc.PurifyString(edit_text_permission_group_name.getText().toString())+"',";
            query += "DateTime = "+System.currentTimeMillis()+" ";
            query += "WHERE ID = "+Integer.parseInt(ID);
            Log.i("_query",query);

            DBFunc.ExecQuery(query, true);
            finish();
            Intent i = new Intent(AddPermissionGroupActivity.this,PermissionGroupActivity.class);
            startActivity(i);
        }catch (Exception e){
            Log.i("Error",e.toString());
        }

    }


    private void deletePermissionGroup(String ID) {
        try{
            DBFunc.ExecQuery("DELETE FROM PermissionGroup WHERE ID = "+Integer.parseInt(ID), true);
            finish();
            Intent i = new Intent(AddPermissionGroupActivity.this,PermissionGroupActivity.class);
            startActivity(i);
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

}
