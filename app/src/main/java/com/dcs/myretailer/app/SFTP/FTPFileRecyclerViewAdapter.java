package com.dcs.myretailer.app.SFTP;

import android.app.TimePickerDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.ActivityFtpRecyclerviewBinding;
import com.dcs.myretailer.app.databinding.ActivityFtpfileRecyclerviewBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class FTPFileRecyclerViewAdapter extends
        RecyclerView.Adapter<FTPFileRecyclerViewAdapter.ViewHolder>  {
    static ActivityFtpfileRecyclerviewBinding binding;
    public static MallInterfaceActivity context;
    public static FragmentManager manager;
    List<String> idList;
    List<String> chkList;
    List<String> dateList;
//    List<String> ftpDropdownList;
//    InputMethodManager inputMgr;
//    Integer positionVal = 0;


    public FTPFileRecyclerViewAdapter(FragmentManager supportFragmentManager,
                                      List<String> idLst,List<String> chkLst,List<String> dateLst) {
        idList = idLst;
        chkList = chkLst;
        dateList = dateLst;
    }
    @NonNull
    @Override
    public FTPFileRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_ftpfile_recyclerview, parent, false);
        return new FTPFileRecyclerViewAdapter.ViewHolder(binding);
    }
    Integer count = 0;

    @Override
    public void onBindViewHolder(@NonNull FTPFileRecyclerViewAdapter.ViewHolder holder, int position) {
        if(chkList.get(position).equals("1")){
            binding.chkShowFTPFileDate.setChecked(true);
        } else {
            binding.chkShowFTPFileDate.setChecked(false);
        }
        binding.chkShowFTPFileDate.setText(dateList.get(position));

        binding.chkShowFTPFileDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //do your toast programming here.

                Log.i("DL____","DonCheckedChanged___"+dateList.get(position));
                if (binding.chkShowFTPFileDate.isChecked()) {
                    Log.i("DL____","ischecked__"+idList.get(position));
                    ManualFTPMallInterfaceActivity.listIdValue.add(idList.get(position));
                    ManualFTPMallInterfaceActivity.listDateValue.add(dateList.get(position));
                } else {
                    Log.i("DL____","unchecked__"+idList.get(position));
                    ManualFTPMallInterfaceActivity.listIdValue.remove(idList.get(position));
                    ManualFTPMallInterfaceActivity.listDateValue.remove(dateList.get(position));
                }
                //notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ActivityFtpfileRecyclerviewBinding binding;
        public ViewHolder(ActivityFtpfileRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = (ActivityFtpfileRecyclerviewBinding) binding;

        }
    }
}
