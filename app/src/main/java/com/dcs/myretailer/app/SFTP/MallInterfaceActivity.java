package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityMallInterfaceBinding;

import java.util.ArrayList;
import java.util.List;

public class MallInterfaceActivity extends AppCompatActivity {
    public static ActivityMallInterfaceBinding binding;
    MallInterfaceRecyclerViewAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mall_interface);


//            if (lstProductData.size() > 0) {


        List<FTP> ftpList = new ArrayList<>();

        List<String> val = new ArrayList<>();
        val.add("IP");
        val.add("Type");
        val.add("Port");
        val.add("User");
        val.add("Password");
        for (int i = 0 ; i < val.size(); i++){
            FTP ftp = new FTP();
            ftp.setTextView(val.get(i));
            ftp.setEditView(String.valueOf(R.string.ftpSyncFTPIP));
            ftpList.add(ftp);
        }
        Log.i("sdfftpList__","ftpList_"+ftpList);
        myAdapter = new MallInterfaceRecyclerViewAdapter(getApplicationContext(), ftpList);
        binding.recyclerviewId.setAdapter(myAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerviewId.setLayoutManager(layoutManager);
//
//        myAdapter = new MallInterfaceRecyclerViewAdapter(context, lstProductData, "product");
//        binding.recyclerviewId.setAdapter(myAdapter);
    }
}