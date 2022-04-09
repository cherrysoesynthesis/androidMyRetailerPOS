package com.dcs.myretailer.app.Report;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentReportDateSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ReportDateSheetFragment extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
    public static String start_date = "";
    public static String end_date = "";
    public static Integer currenttabnumber = 0;
    public static FragmentReportDateSheetDialogBinding binding = null;
    public ReportDateSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_report_date_sheet_dialog, container, false);
//        this.mHandler = new Handler();
//        m_Runnable.run();
        return binding.getRoot();
    }

//    private final Runnable m_Runnable = new Runnable() {
//        public void run() {
//            ReportDateSheetFragment.this.mHandler.postDelayed(m_Runnable,500);
//        }
//    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String device = Query.GetDeviceData(Constraints.DEVICE);

        if (device.equals("M2-Max")) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            params.leftMargin = 30;
            params.topMargin = 30;
            params.gravity = Gravity.CENTER;
            binding.Lay1.setLayoutParams(params);
            binding.LayStarting.setLayoutParams(params);
            binding.LayEnding.setLayoutParams(params);
            binding.LayPrevious.setLayoutParams(params);
            binding.btnApplyDateRange.setLayoutParams(params);
        }
        binding.linearClose.setOnClickListener(this);

        binding.editTextStarting.setInputType(InputType.TYPE_NULL);
        binding.editTextEnding.setInputType(InputType.TYPE_NULL);
        binding.typeName.setInputType(InputType.TYPE_NULL);
        binding.editTextDatecount.setInputType(InputType.TYPE_NULL);

        if (PreviousReportFragment.previous_start_d.length() > 0){
            binding.editTextStarting.setText(PreviousReportFragment.previous_start_d);
        }else {
            if (ReportActivity.start.length() > 0) {
                binding.editTextStarting.setText(ReportActivity.start);
                binding.typeName.setText("");
            }else {
                binding.editTextStarting.setText(sdf.format(myCalendar.getTime()));
                binding.typeName.setText("");
            }
        }
        if (PreviousReportFragment.previous_end_d.length() > 0){
            binding.editTextEnding.setText(PreviousReportFragment.previous_end_d);
        }else{
            if (ReportActivity.end.length() > 0) {
                binding.editTextEnding.setText(ReportActivity.end);
                binding.typeName.setText("");
            }
            else {
                binding.editTextEnding.setText(sdf.format(myCalendar2.getTime()));
                binding.typeName.setText("");
            }
        }
//        binding.editTextStarting.setOnClickListener(new View.OnClickListener() {
        binding.LayStarting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);


                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD);
                int existingY = 0;
                int existingM = 0;
                int existingD = 0;
                Date convertedCurrentDate = null;

                try {
                    convertedCurrentDate = sdf.parse(binding.editTextStarting.getText().toString());
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(convertedCurrentDate);
//                    final SimpleDateFormat fmttime2 = new SimpleDateFormat("yyyy");
//                    final SimpleDateFormat fmttime3 = new SimpleDateFormat("MM");
//                    final SimpleDateFormat fmttime4 = new SimpleDateFormat("dd");
                    final SimpleDateFormat fmttime2 = new SimpleDateFormat("yyyy");
                    final SimpleDateFormat fmttime3 = new SimpleDateFormat("MM");
                    final SimpleDateFormat fmttime4 = new SimpleDateFormat("dd");
                    existingY = Integer.parseInt(fmttime2.format(cal2.getTime()));
                    existingM = Integer.parseInt(fmttime3.format(cal2.getTime()));
                    existingD = Integer.parseInt(fmttime4.format(cal2.getTime()));
                    //String date = sdf.format(convertedCurrentDate );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (existingY > 0 ) {
                    mYear = existingY;
                }
                if (existingM > 0 ) {
                    mMonth = existingM - 1;
                }
                if (existingD > 0 ) {
                    mDay = existingD;
                }

                int datePickerThemeResId = 0;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
//                    datePickerThemeResId = android.R.style.Theme_Material_Light_Dialog;
//                    datePickerThemeResId = android.R.color.holo_green_dark;
                    //datePickerThemeResId = android.R.style.Theme_Holo_Dialog;
                    datePickerThemeResId = R.style.my_dialog_theme;
                }
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        String month = "";
                        Integer monthh = selectedmonth + 1;
                        if (monthh < 10){
                            month = "0" + String.valueOf(monthh);
                        }else{
                            month = String.valueOf(monthh);
                        }
                        String day = "";
                        Integer dayy = selectedday;
                        if (dayy < 10){
                            day = "0" + String.valueOf(dayy);
                        }else{
                            day = String.valueOf(dayy);
                        }
                        SimpleDateFormat yyy = new SimpleDateFormat(Constraints.dateY);
                        //String selected_from_date = month + "/" + day + "/" + yyy.format(selectedyear) ;
                        //String selected_from_date = month + "/" + day + "/" + selectedyear ;
                        //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;

                        //String changeStr = String.valueOf(selectedyear).substring(2,4);
                        //String selected_from_date = month + "/" + day + "/" + changeStr ;
                        String selected_from_date = month + "/" + day + "/" + selectedyear ;
                        start_date = selected_from_date;
                        binding.editTextStarting.setText(selected_from_date);
                        binding.typeName.setText("");

//                        int dateDifference = (int) getDateDiff(new SimpleDateFormat("yyyy-MM-dd"),
//                                binding.editTextStarting.getText().toString(), binding.editTextEnding.getText().toString());

                        DateRangeCountFun();
                    }
                },mYear, mMonth, mDay);
//                mDatePicker.setTitle("Select date");
//                ((ViewGroup) mDatePicker.getDatePicker()).findViewById(
//                        Resources.getSystem().getIdentifier("day", "id", "android")).
//                        setVisibility(View.GONE);

                mDatePicker.show();
            }

        });
//        binding.imgTextEnding.setOnClickListener(new View.OnClickListener() {
        binding.LayEnding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD);
                int existingY = 0;
                int existingM = 0;
                int existingD = 0;
                Date convertedCurrentDate = null;
                try {
                    convertedCurrentDate = sdf.parse(binding.editTextEnding.getText().toString());
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(convertedCurrentDate);
                    final SimpleDateFormat fmttime2 = new SimpleDateFormat("yyyy");
                    final SimpleDateFormat fmttime3 = new SimpleDateFormat("MM");
                    final SimpleDateFormat fmttime4 = new SimpleDateFormat("dd");
                    existingY = Integer.parseInt(fmttime2.format(cal2.getTime()));
                    existingM = Integer.parseInt(fmttime3.format(cal2.getTime()));
                    existingD = Integer.parseInt(fmttime4.format(cal2.getTime()));
                    //String date = sdf.format(convertedCurrentDate );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (existingY > 0 ) {
                    mYear = existingY;
                }
                if (existingM > 0 ) {
                    mMonth = existingM - 1;
                }
                if (existingD > 0 ) {
                    mDay = existingD;
                }
                int datePickerThemeResId = 0;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    //datePickerThemeResId = android.R.style.Theme_Material_Light_Dialog;
//                    datePickerThemeResId = android.R.color.holo_green_dark;
                    //datePickerThemeResId = android.R.style.Theme_Holo_Dialog;
                    datePickerThemeResId = R.style.my_dialog_theme;
                }

                DatePickerDialog mDatePicker=new DatePickerDialog(getContext(), datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        String month = "";
                        Integer monthh = selectedmonth + 1;
                        if (monthh < 10){
                            month = "0" + String.valueOf(monthh);
                        }else{
                            month = String.valueOf(monthh);
                        }
                        String day = "";
                        Integer dayy = selectedday;
                        if (dayy < 10){
                            day = "0" + String.valueOf(dayy);
                        }else{
                            day = String.valueOf(dayy);
                        }
                        //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                        //String changeStr = String.valueOf(selectedyear).substring(2,4);
                        String selected_from_date = month + "/" + day + "/" + selectedyear ;
                        end_date = selected_from_date;
                        binding.editTextEnding.setText(selected_from_date);
                        binding.typeName.setText("");

//                        int dateDifference = (int) getDateDiff(new SimpleDateFormat("yyyy-MM-dd"),
//                                binding.editTextStarting.getText().toString(), binding.editTextEnding.getText().toString());

                        DateRangeCountFun();
                    }
                },mYear, mMonth, mDay);
//                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }

        });

        //int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), "29/05/2017", "31/05/2017");
        DateRangeCountFun();

        if (PreviousReportFragment.selected_tax_name != null && PreviousReportFragment.selected_tax_name.length() > 0 && PreviousReportFragment.selected_tax_name != "0") {
            binding.typeName.setText(PreviousReportFragment.selected_tax_name);
        }else {
            //Cursor c = DBFunc.Query("SELECT ID, ClosingTime FROM ClosingPeriod WHERE ClosingTime BETWEEN " + _tmpfrom.getTimeInMillis() + " AND " + _tmpto.getTimeInMillis() + " ORDER BY ID ASC", false);
            Cursor c = DBFunc.Query("SELECT ID, ClosingTime FROM ClosingPeriod WHERE strftime('%Y-%m-%d ', ClosingTime / 1000, 'unixepoch') " +
                    "BETWEEN " + binding.editTextStarting.getText().toString() + " AND " + binding.editTextEnding.getText().toString() +
                    " ORDER BY ID ASC", false);
            if (c != null) {
                if (c.moveToNext()) {
//                rpt_periodID.add(c.getInt(0));
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(c.getLong(1));
                    //final SimpleDateFormat fmttime = new SimpleDateFormat("yyyy/MM/dd HH:mm.ss");
                    final SimpleDateFormat fmttime = new SimpleDateFormat(Constraints.dateYMD+" HH:mm.ss");

                    binding.typeName.setText(fmttime.format(cal.getTime()));
                }else {
                    binding.typeName.setText("");
                }
                c.close();
            }
        }
        binding.btnApplyDateRange.setOnClickListener(this);
        binding.LayPrevious.setOnClickListener(this);
    }

    private void DateRangeCountFun() {
        int dateDifferenceInt = (int) getDateDiff(new SimpleDateFormat(Constraints.dateYMD),
                binding.editTextStarting.getText().toString(), binding.editTextEnding.getText().toString());
        String dateDifference = "";
        if (dateDifferenceInt == 1){
            dateDifference = dateDifferenceInt + " Day";
        }else{
            dateDifference = dateDifferenceInt + " Days";
        }

        if (dateDifferenceInt == 0 ){
            SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
            final Calendar myCalendar2 = Calendar.getInstance();
            String todayd = sdf.format(myCalendar2.getTime());
            if (todayd.equals(binding.editTextStarting.getText().toString()) && todayd.equals(binding.editTextEnding.getText().toString())){
                binding.editTextDatecount.setText("Today");
            }else {
                binding.editTextDatecount.setText(dateDifference);
            }
        }else {
            binding.editTextDatecount.setText(dateDifference);
        }
    }

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
//        long days = Days.daysBetween(oldDate, newDate).getDays();
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        onResume();

        //ReportActivity.updateMediaButtons();

//        if (currenttabnumber == 3) {
//        ReportXFragment.updateReportFragmentXFun();
//        }
        super.onDismiss(dialog);

    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        //TextView textView = (TextView) findViewById(R.id.textView);
        binding.editTextEnding.setText(currentDateString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_apply_date_range:
                ReportActivity.start = binding.editTextStarting.getText().toString();
                ReportActivity.end = binding.editTextEnding.getText().toString();

                if (binding.typeName.getText().toString().length() > 4) {
                    ReportActivity.previousReport = PreviousReportFragment.selected_previousname;
                }else {
                    ReportActivity.previousReport = 00;
                    PreviousReportFragment.selected_tax_name = "";
                }

                ReportActivity.previous_report_shift_name = binding.typeName.getText().toString();
                ReportActivity.binding.pagerReport.setCurrentItem(currenttabnumber);
//                currenttabnumber = ReportActivity.binding.pagerReport.getCurrentItem();

                //ReportActivity.St = "1";
                //ReportProductFragment.St = "1";
                //ReportCategoryFragment.St = "1";
                //ReportXFragment.St = "1";

                Log.i("Sdf_currenttabnumber","____"+currenttabnumber+"_||__"+ReportActivity.binding.pagerReport.getCurrentItem());
                //if (currenttabnumber == 0){

                    ReportActivity.updateQtyAndAmtFun();

               // } else  if (currenttabnumber == 1){

                    ReportProductFragment.UpdateProductReport();
                //} else  if (currenttabnumber == 2){
                    ReportCategoryFragment.UpdateCategoryFun(getContext());
                    ReportActivity.updateMediaButtons();
                    ReportOverallFragment.updateMediaButtons();
                    //ReportActivity.showTotalItemsAndAmt(0.0,0,"0");
                //}



                //if (currenttabnumber == 2 || currenttabnumber == 3) {
                if (currenttabnumber == 3 || currenttabnumber == 4) {
                    ReportAdapter adapter = new ReportAdapter
                            (getFragmentManager(), ReportActivity.binding.tabLayoutReport.getTabCount(),ReportActivity.appContext);
                    ReportActivity.binding.pagerReport.setAdapter(adapter);

                    ReportActivity.binding.pagerReport.setCurrentItem(currenttabnumber);

                    ReportXFragment.updateReportFragmentXFun();

                    //ReportActivity.binding.LayTotalSales.setVisibility(View.GONE);
                }
            case R.id.linear_close:
                dismiss();
                break;

//            case R.id.btn_type:
//                dismiss();
//                PreviousReportFragment fragment1 = new PreviousReportFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(android.R.id.content, fragment1);
//                fragmentTransaction.commit();
//                break;
            case R.id.LayPrevious:
                Log.i("hererre,_","herrrr");
                dismiss();
                Log.i("hererre1_","herrrr");
                PreviousReportFragment fragment1 = new PreviousReportFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, fragment1);
                fragmentTransaction.commit();

                Log.i("hererre,_","herrrr");
                break;
        }
    }
}