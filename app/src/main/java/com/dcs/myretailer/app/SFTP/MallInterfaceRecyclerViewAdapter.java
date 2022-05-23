package com.dcs.myretailer.app.SFTP;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.ProductCategorySheetFragment;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.ActivityFtpRecyclerviewBinding;
import com.dcs.myretailer.app.databinding.ActivityMallInterfaceBinding;
import com.dcs.myretailer.app.databinding.CardveiwItemBookBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class MallInterfaceRecyclerViewAdapter extends
        RecyclerView.Adapter<MallInterfaceRecyclerViewAdapter.MallInterfaceViewHolder>  {
    static ActivityFtpRecyclerviewBinding binding;
    public static MallInterfaceActivity context;
    public static FragmentManager manager;
    List<String> ftp;
    List<String> ftpSync;
    List<String> ftpDropdownList;
    InputMethodManager inputMgr;
//    Integer positionVal = 0;

    public MallInterfaceRecyclerViewAdapter(MallInterfaceActivity ctx,  FragmentManager supportFragmentManager,
                                            InputMethodManager inputMethodManager, List<String> ftpe, List<String> ftpSyncObj, List<String> ftpDropdownlstt) {
        context = ctx;
        manager = supportFragmentManager;
        inputMgr = inputMethodManager;
        ftp = ftpe;
        ftpSync = ftpSyncObj;
        ftpDropdownList = ftpDropdownlstt;
   }
    @NonNull
    @Override
    public MallInterfaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_ftp_recyclerview, parent, false);
        return new MallInterfaceRecyclerViewAdapter.MallInterfaceViewHolder(binding);
    }
    Integer count = 0;

    @Override
    public void onBindViewHolder(@NonNull MallInterfaceViewHolder holder, int position) {

        count +=1;
        if (count < 10) {
            Log.i("chk___", "post___" + position + "___" + ftp.get(position));


            binding.ftpTextValue.setText(ftp.get(position));
            binding.ftpEditTextValue.setText(ftpSync.get(position));
//
            if (ftpDropdownList.get(position).equals("1")){
                binding.dropDownSync.setVisibility(View.VISIBLE);
            }
            if (position == 1 || position == 5 || position == 8){//ftptype and ftpformat
                binding.ftpEditTextValue.setEnabled(false);
    //            binding.dropDownSync.setEnabled(true);
               // binding.dropDownSync.setBackgroundColor(ContextCompat.getColor(context, R.color.nasty_green));
    //            binding.ftpEditTextValue.setOnClickListener(this);
                binding.dropDownSync.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == 1 ) {
                            FTPTypeSheetFragment pcSSheetFragment = new FTPTypeSheetFragment();
                            pcSSheetFragment.show(manager, pcSSheetFragment.getTag());
                        } else if (position == 5){
                            FTPFormatTypeSheetFragment pcSSheetFragment = new FTPFormatTypeSheetFragment();
                            pcSSheetFragment.show(manager, pcSSheetFragment.getTag());
                        } else {

                            SendFPTFromDateDialogFun();
                        }
                    }
                });
            }
            if (position == 4){
                //binding.ftpEditTextValue.setInputType(InputType.PASSw);
                binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                binding.ftpEditTextValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
    //                    binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }

            Log.i("postion_____","post___"+binding.ftpEditTextValue.getText());
            Log.i("postion_____","post_length__"+binding.ftpEditTextValue.getText().length());
            if (position == 7) {
                Log.i("Sfddsf___","Dfdf___"+binding.ftpEditTextValue.getText());
                if (binding.ftpEditTextValue.getText().length() > 8) {
                    MallInterfaceActivity.redColor = "1";
                    Toast.makeText(context, "Max allowed 8 digts!", Toast.LENGTH_SHORT).show();
                    //binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.color_red));
                }
                MallInterfaceActivity.redColor = "0";
                MallInterfaceActivity.machineIdValue = binding.ftpEditTextValue.getText().toString();
                binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));

            }

            doFun(position,binding.ftpEditTextValue.getEditableText());


            //inputMgr.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN, InputMethodManager.RESULT_UNCHANGED_SHOWN);


            //binding.ftpEditTextValue.addTextChangedListener();
            binding.ftpEditTextValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (position == 4){
                        //binding.ftpEditTextValue.setInputType(InputType.PASSw);
                        binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        binding.ftpEditTextValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
    //                    binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    //                //using this boolean because sometime when user enter value in edittxt
    //                //afterTextchanged runs twice to prevent this, i m making use of this variable.
    //                isOnTextChanged = true;
                    if (position == 4){
    //                    binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        //binding.ftpEditTextValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        binding.ftpEditTextValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
                @Override
                public void afterTextChanged(Editable editable) {

    //                for (int i = 0; i <= position; i++) {
                       doFun(position,editable);
    //                }
                }
            });
        }
    }

    private void positionFun(int position, TextView ftpTextValue, TextInputEditText ftpEditTextValue,Integer posAct) {
        if (position == posAct){
            Log.i("DSFvdsf___","ASASAS____"+ftp.get(posAct));
            Log.i("DSFvdsf___","ASASASe____"+ftpSync.get(posAct));
            ftpTextValue.setText(ftp.get(posAct));
            ftpEditTextValue.setText(ftpSync.get(posAct));
        }
    }

    private void SendFPTFromDateDialogFun() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);

                    Log.i("Sdfdsf___","hourOfDay____"+hourOfDay);
                    Log.i("Sdfdsf___","minute____"+minute);


                    MallInterfaceActivity.dateValue = hourOfDay + "-" + minute;
                    MallInterfaceActivity.fromDateValue = String.valueOf(hourOfDay);
                    MallInterfaceActivity.toDateValue = String.valueOf(minute);

                    MallInterfaceActivity.doFun(context,MallInterfaceRecyclerViewAdapter.manager);

                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(MallInterfaceActivity.act, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
//        final Calendar myCalender = Calendar.getInstance();
//        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
//        int minute = myCalender.get(Calendar.MINUTE);
//
//
//        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//
//                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                    myCalender.set(Calendar.MINUTE, minute);
//
//
//            }
//        };
//        TimePickerDialog timePickerDialog = new TimePickerDialog(MallInterfaceActivity.act, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
//        timePickerDialog.setTitle("Choose hour:");
//        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        timePickerDialog.show();

//        Calendar mcurrentDate=Calendar.getInstance();
//        int mYear = mcurrentDate.get(Calendar.YEAR);
//        int mMonth = mcurrentDate.get(Calendar.MONTH);
//        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//        int datePickerThemeResId = 0;
//        if (android.os.Build.VERSION.SDK_INT >= 21) {
//            datePickerThemeResId = R.style.my_dialog_theme;
//        }
//
////                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
////                        new DatePickerDialog.OnDateSetListener() {
//        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
//        TimePickerDialog mDatePicker = new TimePickerDialog(context, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                // TODO Auto-generated method stub
//                /*      Your code   to get date and time    */
//                String month = "";
//                Integer monthh = selectedmonth + 1;
//                if (monthh < 10){
//                    month = "0" + String.valueOf(monthh);
//                }else{
//                    month = String.valueOf(monthh);
//                }
//                String day = "";
//                Integer dayy = selectedday;
//                if (dayy < 10){
//                    day = "0" + String.valueOf(dayy);
//                }else{
//                    day = String.valueOf(dayy);
//                }
//                String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
//                Log.i("Sfd__selected_from_date","selected_from_date____"+selected_from_date);
//                sendFTPToDateDialogFun(selectedyear,month,day);
//            }
//        },mYear, mMonth, mDay);
//        mDatePicker.show();
    }

//    private void sendFTPToDateDialogFun(final int fselectedyear, final String fmonth, final String fday) {
//        Calendar mcurrentDate=Calendar.getInstance();
//        int mYear = mcurrentDate.get(Calendar.YEAR);
//        int mMonth = mcurrentDate.get(Calendar.MONTH);
//        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//        int datePickerThemeResId = 0;
//        if (android.os.Build.VERSION.SDK_INT >= 21) {
//            datePickerThemeResId = R.style.my_dialog_theme;
//        }
////                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
////                        new DatePickerDialog.OnDateSetListener() {
//        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
//        DatePickerDialog mDatePicker = new DatePickerDialog(context, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                // TODO Auto-generated method stub
//                /*      Your code   to get date and time    */
//                String month = "";
//                Integer monthh = selectedmonth + 1;
//                if (monthh < 10){
//                    month = "0" + String.valueOf(monthh);
//                }else{
//                    month = String.valueOf(monthh);
//                }
//                String day = "";
//                Integer dayy = selectedday;
//                if (dayy < 10){
//                    day = "0" + String.valueOf(dayy);
//                }else{
//                    day = String.valueOf(dayy);
//                }
//                //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
//                String fromdate = fselectedyear + "-" + fmonth + "-" + fday;
//                String todate = selectedyear + "-" + month + "-" + day;
//
//                MallInterfaceActivity.dateValue = fromdate + "-" + todate;
//                MallInterfaceActivity.fromDateValue = fselectedyear+fmonth+fday;
//                MallInterfaceActivity.toDateValue = selectedyear+month+day;
//
//
//                Log.i("Sfd__selected_from_date","selefromdate____"+fromdate);
//                Log.i("Sfd__selected_from_date","seletodatee____"+todate);
//
//                MallInterfaceActivity.doFun(context,MallInterfaceRecyclerViewAdapter.manager);
//
//            }
//        },mYear, mMonth, mDay);
//        mDatePicker.show();
//    }

    private void doFun(int position, Editable editable) {
        if (position == 0){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.ipValue = editable.toString();
            binding.ftpEditTextValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE);
        }else if (position == 1){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.typeValue = editable.toString();
        } else if (position == 2){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.portNoValue = editable.toString();
            binding.ftpEditTextValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE);
        } else if (position == 3){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.userValue = editable.toString();
        } else if (position == 4){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.passwordValue = editable.toString();
//                        binding.ftpEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            binding.ftpEditTextValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            binding.ftpEditTextValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }  else if (position == 5){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.formatValue = editable.toString();
        } else if (position == 6){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.mallCodeValue = editable.toString();
        } else if (position == 7){
            Log.i("postion_____","post___"+editable.toString().length());
            if (editable.toString().length() > 8) {
                MallInterfaceActivity.redColor = "1";
                Toast.makeText(context, "Max allowed 8 digts!", Toast.LENGTH_SHORT).show();
                binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.color_red));
            } else {
                MallInterfaceActivity.redColor = "0";
                MallInterfaceActivity.machineIdValue = editable.toString();
                binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            }
        } else if (position == 8){
            binding.ftpEditTextValue.setTextColor(context.getResources().getColor(R.color.mine_shaft));
            MallInterfaceActivity.dateValue = editable.toString();
        }
    }


    @Override
    public int getItemCount() {
        return ftp.size();
    }

    public class MallInterfaceViewHolder extends RecyclerView.ViewHolder{
        ActivityFtpRecyclerviewBinding binding;
        public MallInterfaceViewHolder(ActivityFtpRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = (ActivityFtpRecyclerviewBinding) binding;

        }
    }
}
