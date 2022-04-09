package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivitySupportBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    ActivitySupportBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_support);
        context = SupportActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Support");

        String device = Query.GetDeviceData(Constraints.DEVICE);
        Log.i("ddf__tadeviceD", device);
        if (device.equals("M2-Max")) {

            LinearLayout.LayoutParams LaybtnPrintSave = new LinearLayout.LayoutParams(720,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            LaybtnPrintSave.leftMargin = 30;
            LaybtnPrintSave.topMargin = 10;
            binding.LayEmail.setLayoutParams(LaybtnPrintSave);
            binding.LayContactNo.setLayoutParams(LaybtnPrintSave);
            binding.LaySubject.setLayoutParams(LaybtnPrintSave);
            binding.LayMessage.setLayoutParams(LaybtnPrintSave);
            binding.btnSubmit.setLayoutParams(LaybtnPrintSave);
        }

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isEmailValid(binding.editTextEmail.getText().toString())){
            binding.editTextEmail.setError("Your Email Id is Invalid.");
            return;
        }
        if (binding.editTextContactNo.getText().toString().isEmpty()){
            binding.editTextContactNo.setError("Please fill Contact No.");
            return;
        }
        if (binding.editTextSubject.getText().toString().isEmpty()){
            binding.editTextSubject.setError("Please fill Subject.");
            return;
        }
        if (binding.editTextMessage.getText().toString().isEmpty()){
            binding.editTextMessage.setError("Please fill Message.");
            return;
        }
        String sql = "INSERT INTO Support (Email,ContactNumber,Subject,Message,DateTime) " +
                "VALUES (" +
                "'" + DBFunc.PurifyString(binding.editTextEmail.getText().toString().toUpperCase()) + "'," +
                "'" + DBFunc.PurifyString(binding.editTextContactNo.getText().toString().toUpperCase()) + "'," +
                "'" + DBFunc.PurifyString(binding.editTextSubject.getText().toString().toUpperCase()) + "'," +
                "'" + DBFunc.PurifyString(binding.editTextMessage.getText().toString().toUpperCase()) + "'," +
                System.currentTimeMillis() + ")";

        DBFunc.ExecQuery(sql, true);
        binding.editTextEmail.setText("");
        binding.editTextContactNo.setText("");
        binding.editTextSubject.setText("");
        binding.editTextMessage.setText("");
        //Toast.makeText(this, "Send Message Successfully!", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setMessage("Send Message Successfully!")
                .setCancelable(false)
                //.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //SupportActivity.this.finish();
//
//                    }
//                })
                .setNegativeButton(Constraints.OK, null)
                .show();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(SupportActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
