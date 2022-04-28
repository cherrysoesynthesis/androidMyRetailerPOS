package com.dcs.myretailer.app.Report;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FileBrowser;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.StrTextConst;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityGenReport extends Activity {
    static DecimalFormat df;
    static Activity CurrentActivity;
    private SimpleDateFormat fmtt = new SimpleDateFormat(Constraints.dateYMD);
    //private SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat fmt = new SimpleDateFormat(Constraints.dateYMD);
//    private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/YY");
    private SimpleDateFormat fmttime = new SimpleDateFormat("HH:mm:ss");
    private Spinner spn_report;
    private Spinner spn_users;
    private Spinner spn_refers;
    private ArrayAdapter<String> adapt_report;
    private ArrayAdapter<String> adapt_users;
    private ArrayAdapter<String> adapt_refers;
    private List<UserData> userlist = new ArrayList<UserData>();
    private List<ReferData> referlist = new ArrayList<ReferData>();


    private Dialog dlg;

    private float scale = 4.167f;

    private Typeface boldtext = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
    private Typeface normaltext = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

    private Calendar reportgentime = Calendar.getInstance();

    private String regID = null;
    Calendar calfrom = Calendar.getInstance();
    Calendar calto = Calendar.getInstance();

    Button btn_fromdate = null;
    Button btn_todate = null;
    public static String selected_from_date = "";
    public static String selected_to_date = "";
    protected void onCreate(Bundle savedInstanceState) {
        df = new DecimalFormat("#.##");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_report);


        fmttime = ((SimpleDateFormat)android.text.format.DateFormat.getTimeFormat(getApplicationContext()));
        fmt = ((SimpleDateFormat) android.text.format.DateFormat.getDateFormat(getApplicationContext()));

        CurrentActivity = this;


        regID = SerialChecker.GetID(this);
        if(regID==null){
            //int abc = 1/0; //let it crash cuz crack
            int abc = 1/1; //let it crash cuz crack
            System.out.println(abc);
            regID = ""+abc;
        }

        final LinearLayout lay_refer = (LinearLayout)findViewById(R.id.lay_rpt_refer);

        TextView lbl_1 = (TextView)findViewById(R.id.lbl_1);
        TextView lbl_2 = (TextView)findViewById(R.id.lbl_2);
        TextView lbl_3 = (TextView)findViewById(R.id.lbl_3);
        TextView lbl_4 = (TextView)findViewById(R.id.lbl_4);
        TextView lbl_5 = (TextView)findViewById(R.id.lbl_5);

//        final Calendar calfrom = Calendar.getInstance();
//        final Calendar calto = Calendar.getInstance();
        calfrom = Calendar.getInstance();
//        int year = Calendar.getInstance().get(Calendar.YEAR);
//        calfrom.set(Calendar.YEAR, year);
        calfrom.set(Calendar.HOUR_OF_DAY, 0);
        calfrom.set(Calendar.MINUTE, 0);
        calfrom.set(Calendar.SECOND, 0);
        calfrom.set(Calendar.MILLISECOND, 0);

        calto = Calendar.getInstance();
//        calto.set(Calendar.YEAR, year);
        calto.set(Calendar.HOUR_OF_DAY, 23);
        calto.set(Calendar.MINUTE, 59);
        calto.set(Calendar.SECOND, 59);
        calto.set(Calendar.MILLISECOND, 999);

        spn_report = (Spinner)findViewById(R.id.spn_report);
        adapt_report = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_report.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_report.setAdapter(adapt_report);
        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 200));
        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 201));
        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 301));
        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 302));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 303));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 304));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 210));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 202));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 203));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 301));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 302));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 303));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 304));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 305));
//        adapt_report.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 306));


        spn_users = (Spinner)findViewById(R.id.spn_users);
        adapt_users = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_users.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_users.setAdapter(adapt_users);

        spn_refers = (Spinner)findViewById(R.id.spn_refers);
        adapt_refers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapt_refers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_refers.setAdapter(adapt_refers);

        adapt_users.add(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 300));

        try {

            userlist = ListUsers();
            referlist = ListRefers();

            if (userlist.size() > 0) {
                for (UserData u : userlist) {
                    adapt_users.add(u.getName());
                }
            }

            if (referlist.size() > 0) {
                for(ReferData r:referlist){
                    adapt_refers.add(r.getName());
                }
            }

        }catch (RuntimeException e){

        }
        spn_report.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==adapt_report.getCount()-1){
                    lay_refer.setVisibility(LinearLayout.GONE);
                }else{
                    lay_refer.setVisibility(LinearLayout.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });


//        final Button btn_fromdate = (Button)findViewById(R.id.btn_from);
//        final Button btn_todate = (Button)findViewById(R.id.btn_to);
        btn_fromdate = (Button)findViewById(R.id.btn_from);
        btn_todate = (Button)findViewById(R.id.btn_to);
        Button btn_view = (Button)findViewById(R.id.btn_view);
        Button btn_save = (Button)findViewById(R.id.btn_save);



        btn_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(spn_report.getSelectedItemPosition()>=0 && spn_report.getSelectedItemPosition()<5){
                    UserData user = null;
                    ReferData refer = null;
                    if(spn_users.getSelectedItemPosition()>0){
                        user = userlist.get(spn_users.getSelectedItemPosition()-1);
                    }
                    try {

                        refer = referlist.get(spn_refers.getSelectedItemPosition());
                    }catch (ArrayIndexOutOfBoundsException e){

                    }catch (NullPointerException e){

                    }

//                    Calendar cal = Calendar.getInstance();
                    //calfrom.add(Calendar.DATE, 1);

//                    Calendar c = Calendar.getInstance();
//                    try {
//                        Date date = fmtt.parse(ActivityGenReport.selected_from_date);
//                        if (date != null) {
//                            c.setTime(date);
//                            String formatted = fmtt.format(c.getTime());
////                            Log.i("__dffd__","__formatted____");
////                            Log.i("__dffd__",ActivityGenReport.selected_from_date+"__formatted____"+formatted);
////
//                            Date resultdate = new Date(c.getTimeInMillis());
//                            String dateInString = fmtt.format(resultdate);
//                            Log.i("__dffd_dateInString_",ActivityGenReport.selected_from_date+"__formatted____"+dateInString);
//
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    Calendar ct = Calendar.getInstance();
//                    try {
//                        Date date = fmtt.parse(ActivityGenReport.selected_to_date);
//                        if (date != null) {
//                            ct.setTime(date);
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    String formattedt = fmtt.format(ct.getTime());
//                    Log.i("__dff__","__formatted____");
//                    Log.i("__dff__",ActivityGenReport.selected_to_date+"__formattedt____"+formattedt);
////                    Log.i("DFDF____","DFDFD_____"+ActivityGenReport.selected_from_date);
////                    Calendar c = Calendar.getInstance();
////                    Date date1=fmttt.format()
////                    c.setTime();
////                    try {
////                        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////                                .parse(ActivityGenReport.selected_from_date));
////                    } catch (ParseException e) {
////                        e.printStackTrace();
////                    }
////                    Log.i("DFDF____","DFDFD_____"+ActivityGenReport.selected_to_date);
////                    Calendar ct = Calendar.getInstance();
////                    try {
////                        ct.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////                                .parse(ActivityGenReport.selected_to_date));
////                    } catch (ParseException e) {
////                        e.printStackTrace();
////                    }
//
////                    RealDate(c);
////                    RealDate(ct);
////                    RealDate(calfrom);
////                    RealDate(calto);

                    GenerateReport(calfrom,calto, spn_report.getSelectedItemPosition(),user,refer, true);
                    //GenerateReport(c,ct, spn_report.getSelectedItemPosition(),user,refer, true);
                }else{
                    DialogBox dlg = new DialogBox(v.getContext());
                    dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 0));
                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 25));
                    dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg.show();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(spn_report.getSelectedItemPosition()>=0 && spn_report.getSelectedItemPosition()<5){
                    UserData user = null;
                    ReferData refer = null;
                    if(spn_users.getSelectedItemPosition()>0){
                        user = userlist.get(spn_users.getSelectedItemPosition()-1);
                    }

                    try {

                        refer = referlist.get(spn_refers.getSelectedItemPosition());
                    }catch (ArrayIndexOutOfBoundsException e){
                        refer = null;
                    }



                    GenerateReport(calfrom,calto, spn_report.getSelectedItemPosition(), user,refer,false);
                }else{
                    DialogBox dlg = new DialogBox(v.getContext());
                    dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 0));
                    dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 25));
                    dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg.show();
                }
            }
        });


//        btn_fromdate.setText(fmt.format(calfrom.getTime()));
//        btn_todate.setText(fmt.format(calto.getTime()));
        SimpleDateFormat fmt1 = new SimpleDateFormat(Constraints.dateYMD);
        btn_fromdate.setText(fmt1.format(calfrom.getTime()));
        btn_todate.setText(fmt1.format(calto.getTime()));

        btn_fromdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                SynFromDateDialogFun();
                final Dialog dlgdate = new Dialog(CurrentActivity);
                dlgdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
                LinearLayout lay = new LinearLayout(dlgdate.getContext());
                lay.setOrientation(LinearLayout.VERTICAL);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dlgdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dlgdate.addContentView(lay, lp);

                final DatePicker dp = new DatePicker(dlgdate.getContext());
                dp.setCalendarViewShown(false);
                dp.updateDate(calfrom.get(Calendar.YEAR), calfrom.get(Calendar.MONTH), calfrom.get(Calendar.DATE));
                //dp.setTheme(R.style.DialogTheme);
                lay.addView(dp);

                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                LinearLayout lay3 = new LinearLayout(dlgdate.getContext());
                lay3.setOrientation(LinearLayout.HORIZONTAL);
                lay.addView(lay3);

                Button btn1 = new Button(dlgdate.getContext());
                btn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
                btn1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 110));
                btn1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        calfrom.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),0,0,0);
                        calfrom.set(Calendar.MILLISECOND, 0);
                        btn_fromdate.setText(fmt.format(calfrom.getTime()));
                        if(calfrom.after(calto)){
                            calto.set(dp.getYear(), dp.getMonth(),dp.getDayOfMonth(),23,59,59);
                            calto.set(Calendar.MILLISECOND, 999);
                            btn_todate.setText(fmt.format(calto.getTime()));
                        }
                        dlgdate.dismiss();


                    }
                });
                lay3.addView(btn1);


                Button btn2 = new Button(dlgdate.getContext());
                btn2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
                btn2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 111));
                btn2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        dp.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                    }
                });
                lay3.addView(btn2);

                Button btn3 = new Button(dlgdate.getContext());
                btn3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
                btn3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
                btn3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dlgdate.dismiss();
                    }
                });
                lay3.addView(btn3);
                dlgdate.show();
            }
        });

        btn_todate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                SynToDateDialogFun();
                final Dialog dlgdate = new Dialog(CurrentActivity);
                dlgdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
                LinearLayout lay = new LinearLayout(dlgdate.getContext());
                lay.setOrientation(LinearLayout.VERTICAL);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dlgdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dlgdate.addContentView(lay, lp);

                final DatePicker dp = new DatePicker(dlgdate.getContext());
                dp.setCalendarViewShown(false);
                dp.updateDate(calto.get(Calendar.YEAR), calto.get(Calendar.MONTH), calto.get(Calendar.DATE));
                lay.addView(dp);

                LinearLayout lay3 = new LinearLayout(dlgdate.getContext());
                lay3.setOrientation(LinearLayout.HORIZONTAL);
                lay.addView(lay3);

                Button btn1 = new Button(dlgdate.getContext());
                btn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
                btn1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 110));
                btn1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        calto.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),23,59,59);
                        calto.set(Calendar.MILLISECOND, 999);
                        btn_todate.setText(fmt.format(calto.getTime()));
                        if(calto.before(calfrom)){
                            calfrom.set(dp.getYear(), dp.getMonth(),dp.getDayOfMonth(),0,0,0);
                            calfrom.set(Calendar.MILLISECOND, 0);
                            btn_fromdate.setText(fmt.format(calfrom.getTime()));
                        }
                        dlgdate.dismiss();


                    }
                });
                lay3.addView(btn1);


                Button btn2 = new Button(dlgdate.getContext());
                btn2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
                btn2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 111));
                btn2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        dp.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                    }
                });
                lay3.addView(btn2);

                Button btn3 = new Button(dlgdate.getContext());
                btn3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
                btn3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
                btn3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dlgdate.dismiss();
                    }
                });
                lay3.addView(btn3);
                dlgdate.show();
            }
        });



        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CurrentActivity.finish();
            }
        });


        this.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 0));
        lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 108));
        lbl_2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 109));
        lbl_3.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 102));
        lbl_4.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 103));
        lbl_5.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 104));

        btn_view.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 106));
        btn_save.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 2));
    }

    private void RealDate(Calendar cal) {
        //cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE),
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),
                cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
        SimpleDateFormat format1 = new SimpleDateFormat(Constraints.dateYMD);
        Log.i("__dff__","___dfdfdd____"+cal.getTime());

        String formatted = format1.format(cal.getTime());
        Log.i("__dff__","__formatted____"+formatted);
    }

    private void SynFromDateDialogFun() {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int datePickerThemeResId = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            datePickerThemeResId = R.style.my_dialog_theme;
        }
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog mDatePicker = new DatePickerDialog(CurrentActivity, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
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

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD);
                Date convertedCurrentDate = null;
                    String aa = selectedyear+"-"+selectedmonth+"-"+selectedday;
                try {
                    convertedCurrentDate = sdf.parse(aa);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calfrom.setTime(convertedCurrentDate);
                SimpleDateFormat sdf_from = new SimpleDateFormat("MM/dd/YY");
//                btn_fromdate.setText(fmt.format(calfrom.getTime()));
//                btn_fromdate.setText(convertedCurrentDate.toString());
                //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                  selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                //btn_fromdate.setText(selected_from_date);
                //btn_fromdate.setText(sdf_from.format(convertedCurrentDate));
                //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;

                //String selected_from_date = month + "/" + day + "/" + sdf_from.format(selectedyear);
                btn_fromdate.setText(selected_from_date);

                //SynToDateDialogFun(selectedyear,month,day);
            }
        },mYear, mMonth, mDay);
        mDatePicker.show();
    }

//    private void SynToDateDialogFun(final int fselectedyear, final String fmonth, final String fday) {
    private void SynToDateDialogFun() {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int datePickerThemeResId = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            datePickerThemeResId = R.style.my_dialog_theme;
        }
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog mDatePicker = new DatePickerDialog(CurrentActivity, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
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
                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD);
                Date convertedCurrentDate = null;
                String aa = selectedyear+"-"+selectedmonth+"-"+selectedday;
                try {
                    convertedCurrentDate = sdf.parse(aa);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calto.setTime(convertedCurrentDate);
                SimpleDateFormat sdf_to = new SimpleDateFormat("YY");
                //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                selected_to_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                //String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;
                //String selected_from_date = month + "/" + day + "/" + sdf_to.format(selectedyear);
                //btn_todate.setText(convertedCurrentDate.toString());
                //btn_todate.setText(sdf_to.format(convertedCurrentDate));
                btn_todate.setText(selected_to_date);

//                passwordResendSalesDialog(SyncActivity.this,"Resend Sale",fselectedyear, fmonth, fday,
//                        selectedyear, month, day,"Normal");
            }
        },mYear, mMonth, mDay);
        mDatePicker.show();
    }

    void GenerateReport(Calendar calfrom, Calendar calto, int reportType, UserData user, ReferData refer, boolean viewonly){
        if(reportType<0 || reportType >5){
            return;
        }

        dlg = new Dialog(this);
        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 1));
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        LinearLayout lay = new LinearLayout(this);
        dlg.addContentView(lay, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ProgressBar pb = new ProgressBar(this);
        pb.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        pb.setIndeterminate(true);
        lay.addView(pb);

        reportgentime = Calendar.getInstance();

        switch(reportType){
            case 0:
//                String stringdate = ActivityGenReport.selected_from_date;
//                String pattern = "YYYY-MM-dd";
//                Date date = null;
//                try {
//                    date = new SimpleDateFormat(pattern).parse(stringdate);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//                String stringdatet = ActivityGenReport.selected_to_date;
//
//
//
//                Calendar calf = Calendar.getInstance();
//                Calendar calt = Calendar.getInstance();
//
//                calf.setTime();
//                calt.setTime(datet);
//                Log.i("DFDF____","__calf_"+calf);
//                Log.i("DFDF____","__calt_"+calt);
//                new GenerateSalesSumReport().execute(calf,calt,user,refer,viewonly);
                new GenerateSalesSumReport().execute(calfrom,calto,user,refer,viewonly);
                break;
            case 1:
                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
                break;
            case 2:
                new GenerateDetailsBillPLUReport().execute(calfrom,calto,user,refer,viewonly);
                break;
            case 3:
                new GenerateDetailsBillPLUReportRefund().execute(calfrom,calto,user,refer,viewonly);
                break;
//            case 4:
//                Log.i("Sdfherer","herrrr");
//                new GenerateDetailsBillReceiptNoSummary().execute(calfrom,calto,user,refer,viewonly);
//                break;
//            case 5:
//                Log.i("Sdfherer","herrrr");
//                new GeneratePaymentSummary().execute(calfrom,calto,user,refer,viewonly);
//                break;
//            case 3:
//                new GenerateUserLogReport().execute(calfrom,calto,user,viewonly);
//                break;
//            case 3:
//                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
//                new GenerateStockInReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 4:
//                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 5:
//                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 6:
//                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 7:
//                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 8:
//                //new GenerateSalesSumReport1().execute(calfrom,calto,user,refer,viewonly);
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 2:
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,true);
//                break;
//            case 3:
//                new GenerateDeptReport().execute(calfrom,calto,user,refer,viewonly);
//                break;
//            case 4:
//                new GenerateUserLogReport().execute(calfrom,calto,user,viewonly);
//                break;

        }

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    void ReportResult(Object result){
        if(result == null){
            DialogBox dlg = new DialogBox(this);
            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
            dlg.setDialogIconType(DialogBox.IconType.ERROR);
            dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 26));
            dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
            dlg.show();
            return;
        }

        if(dlg!=null){
            if(dlg.isShowing()){
                dlg.dismiss();
            }
        }
        if(result instanceof PdfDocument){
            final PdfDocument fdoc = (PdfDocument)result;

            FileBrowser fb = new FileBrowser(CurrentActivity);

            fb.SetFileExtension("PDF File|*.pdf");
            fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
                @Override
                public void onSelected(View v, File file) {
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            fdoc.writeTo(fos);
                        }
                        fos.close();
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

                        DialogBox dlg = new DialogBox(v.getContext());
                        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 12));
                        dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 28 ,file.getAbsolutePath()));
                        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg.show();
                    } catch (IOException e) {
                        Logger.WriteLog("ActivityGenReport",e.toString());
                        DialogBox dlg = new DialogBox(v.getContext());
                        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                        dlg.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 27));
                        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg.show();
                    }
                }
            });
            fb.ShowSaveDialog();

        }else{
            @SuppressWarnings("unchecked")
            final ArrayList<Bitmap> doclist = (ArrayList<Bitmap>)result;
            final Dialog dlg = new Dialog(CurrentActivity);
            dlg.setCancelable(false);
            dlg.setCanceledOnTouchOutside(false);
            dlg.setContentView(R.layout.dlg_reportviewer);
            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 0));

            TextView lbl_1 = (TextView)dlg.findViewById(R.id.lbl_1);

            final Button btn_next = (Button)dlg.findViewById(R.id.btn_next);
            final Button btn_prev = (Button)dlg.findViewById(R.id.btn_prev);
            Button btn_zoom_in = (Button)dlg.findViewById(R.id.btn_zoom_in);
            Button btn_zoom_out = (Button)dlg.findViewById(R.id.btn_zoom_out);
            Button btn_zoom_fit = (Button)dlg.findViewById(R.id.btn_zoom_fit);
            Button btn_zoom_exact = (Button)dlg.findViewById(R.id.btn_zoom_exact);
            Button btn_close = (Button)dlg.findViewById(R.id.btn_close);

            LinearLayout lay = (LinearLayout)dlg.findViewById(R.id.lay_canvas);
            final CanvasDrawer cd = new CanvasDrawer(dlg.getContext());
            lay.addView(cd, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            final TextView txt_page = (TextView)dlg.findViewById(R.id.txt_pgnum);
            txt_page.setTag(0);
            txt_page.setText((((Integer)txt_page.getTag())+1)+"/"+doclist.size());


            btn_zoom_in.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomIn();
                }
            });

            btn_zoom_out.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomOut();
                }
            });

            btn_zoom_fit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomFit();
                }
            });

            btn_zoom_exact.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomExact();
                }
            });

            btn_prev.setEnabled(false);
            if(((Integer)txt_page.getTag())>= doclist.size()-1){
                btn_next.setEnabled(false);
            }

            btn_close.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    doclist.clear();
                    dlg.dismiss();
                }
            });




            btn_prev.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //previous page
                    int count = (Integer)txt_page.getTag();
                    if(count<=0){
                        count = 0;
                        btn_prev.setEnabled(false);
                        txt_page.setTag(count);
                        return;
                    }
                    count--;

                    if(count<=0){
                        btn_prev.setEnabled(false);
                    }
                    txt_page.setTag(count);
                    if(count<doclist.size()-1)btn_next.setEnabled(true);
                    txt_page.setText((count+1)+"/"+doclist.size());
                    cd.DrawBitmap(doclist.get(count));
                }
            });

            btn_next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //next page
                    int count = (Integer)txt_page.getTag();
                    if(count>=doclist.size()-1){
                        count = doclist.size()-1;
                        btn_next.setEnabled(false);
                        txt_page.setTag(count);
                        return;
                    }
                    count++;



                    if(count>=doclist.size()-1){
                        btn_next.setEnabled(false);
                    }


                    txt_page.setTag(count);
                    if(count>0)btn_prev.setEnabled(true);
                    txt_page.setText((count+1)+"/"+doclist.size());
                    cd.DrawBitmap(doclist.get(count));

                }
            });


            if(doclist.size()>0){
                cd.DrawBitmap(doclist.get(0));
            }

            lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 10));
            btn_zoom_fit.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 100));
            btn_zoom_exact.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 101));
            btn_close.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
            dlg.show();

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dlg.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);



        }
    }


    private class CanvasDrawer extends View{
        private ScaleGestureDetector sg;
        private GestureDetector gd;
        private Bitmap b = null;
        private float posX=0,posY=0;
        private float scale = 1f;
        private RectF size = new RectF(0,0,1,1);
        private float scalemin = 1f;

        public CanvasDrawer(Context context) {
            super(context);
            sg = new ScaleGestureDetector(context,new ScaleListener());
            gd = new GestureDetector(context,new PanListener());
            //this.setOnClickListener(doubleclicklistener);
        }

        public CanvasDrawer(Context context, AttributeSet attrs){
            super(context,attrs);
            sg = new ScaleGestureDetector(context,new ScaleListener());
            gd = new GestureDetector(context,new PanListener());
            //this.setOnClickListener(doubleclicklistener);

        }



        @Override
        public boolean onTouchEvent(MotionEvent e){
            if(e.getPointerCount()>=2){
                if(sg!=null){
                    sg.onTouchEvent(e);
                }
            }else if(e.getPointerCount() == 1){
                if(gd!=null){
                    gd.onTouchEvent(e);
                }
            }

            return true;
        }

        public void DrawBitmap(Bitmap b){
            this.b = b;
            scale = -1f;
            posX = 0;
            posY = 0;
            if(this.b==null){
                return;
            }
            invalidate();
        }

        @Override
        protected void onDraw(Canvas c){
            if(b==null){
                return;
            }
            if(c.getWidth()<=0 || c.getHeight() <=0){
                return;
            }

            size.set(0, 0, c.getWidth(), c.getHeight());
            if(scale==-1){
                if(b.getWidth()>b.getHeight()){
                    scale = (float)c.getWidth()/(float)b.getWidth();
                }else{
                    scale = (float)c.getHeight()/(float)b.getHeight();
                }
                scalemin = scale;
                if(scalemin<0.1f)scalemin=0.1f;
            }

            c.save();
            c.scale(scale, scale);
            c.translate(posX, posY);
            c.drawBitmap(b, 0, 0, null);

            c.restore();
        }

        private class PanListener extends GestureDetector.SimpleOnGestureListener{
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float disX, float disY){
                if(b!=null){

                    float x = disX/scale;
                    float y = disY/scale;

                    posX-=x;
                    posY-=y;
                    if(posX<-b.getWidth())posX = -b.getWidth();
                    if(b!=null){
                        if(posX>b.getWidth()-size.width()) posX = b.getWidth()-size.width();
                    }else{
                        posX = 0;
                    }

                    if(posY<-b.getHeight())posY = -b.getHeight();
                    if(b!=null){
                        if(posY>b.getHeight()-size.height()) posY = b.getHeight()-size.height();
                    }else{
                        posY = 0;
                    }

                }

                invalidate();

                return true;
            }
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();
                scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
                invalidate();
                return true;
            }
        }

        public void ZoomIn(){
            scale *=2f;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }

        public void ZoomOut(){
            scale /=2f;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }

        public void ZoomExact(){
            scale = 1f;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }

        public void ZoomFit(){
            scale = scalemin;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }
    }

    private class GeneratePaymentSummary extends AsyncTask<Object, String, Object>{

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            Log.i("dsf___","__"+params[3]);
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
//            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH+1), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;


                String billPaymentDate = "";
                Integer receiptQty = 0;
                String billPaymentBillNo = "";
                Double billPaymentAmount = 0.0;
                String billPaymentStatus = "";
                String billPaymentName = "";
                Double billPaymentChangeAmount = 0.0;
                Double billPaymentNett = 0.0;
                //Total Sales//
                Cursor c = Query.GeneratePaymentSummary(daycount.getTimeInMillis(),dayend.getTimeInMillis(),"Report");
//                Integer cc = 0;
                if (c != null) {
                    while (c.moveToNext()) {

//                        return "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')," +
//                                "BillNo,Name,STATUS,Amount,ChangeAmount" +
//                                " FROM BillPayment " ;

                        billPaymentDate = c.getString(0);
                        billPaymentBillNo = c.getString(1);
                        billPaymentStatus = c.getString(2);
                        billPaymentName = c.getString(3);
                        billPaymentAmount = c.getDouble(4) + c.getDouble(5);
                        billPaymentChangeAmount = c.getDouble(5);
                        billPaymentNett = c.getDouble(4);

                        data = new Object[]{billPaymentDate,billPaymentBillNo, billPaymentStatus, billPaymentName, billPaymentAmount, billPaymentChangeAmount,billPaymentNett};

                        daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
                    }
                    c.close();
                }
//                data = CommonReport.CountDetailsBillTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


//                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;
            String[] header = new String[]{
                    "Date           ",
                    "       Bill No",
                    "       STATUS",
                    "  Payment Name",
                    "    Payment Amount",
                    "    Change Amount ",
                    "    Total Nett "};

            String totaldate = "";
            String totalreceipt = "";
            String totalstatus = "";
            long totalbill = 0;
            long totalqty = 0;
            long totalbillcancel = 0;
            double totalamtnett = 0;
            double totalamt = 0;
            double totalamtgross = 0;
            double totaltax = 0;
            double totaldisc = 0;
            double totalround = 0;
            double totalsurcharge = 0;
            long totalqtycancel = 0;
            double totalamtgrosscancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            for(int i=0;i<tmp.length-2;i++){
                String[] data = new String[header.length];
                Object[] summaryday = daysumdata.get(i);


                data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[0] = String.format("%1$"+header[0].length()+"s", "0");
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", "0");
                    data[5] = String.format("%1$"+header[5].length()+"s", "0");
                    data[6] = String.format("%1$"+header[6].length()+"s", "0");

                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
//                    totalbill+=(Integer)salesdata[0];
                    totaldate+=(String)salesdata[0];
                    totalreceipt+=(String)salesdata[1];
                    totalstatus +=(String)salesdata[2];


                    String tmpstr = String.format("%1$"+header[0].length()+"s", ""+ salesdata[0]);
                    data[0] = tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                    tmpstr = String.format("%1$"+header[1].length()+"s", ""+ salesdata[1]);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+salesdata[2]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    tmpstr = String.format("%1$"+header[3].length()+"s", ""+salesdata[3]);
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",salesdata[4]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));


                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));


                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",salesdata[6]));
                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));


                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

//            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
//            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
            data[1] = String.format("%1$-"+(header[1].length())+"s", "");

            data[2] = String.format("%1$-"+(header[2].length())+"s", "");

            String tmpstr = String.format("%1$"+header[3].length()+"s", ""+totalqty);
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",totalamtnett));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

            tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",totaltax));
            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));

            tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",totaltax));
            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));

            tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",totaltax));
            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

            tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",totaltax));
            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));

//            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
//            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
//            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
//            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
//            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
//            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
//            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
//            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
//            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
//            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Receipt No Summary Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Receipt No Summary Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private class GenerateDetailsBillReceiptNoSummary extends AsyncTask<Object, String, Object>{

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            Log.i("dsf___","__"+params[3]);
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
//            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH+1), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;


                String receiptDate = "";
                Integer receiptQty = 0;
                String receiptBillNo = "";
                Double receiptPrice = 0.0;
                String receiptStatus = "";
                Double receiptTax = 0.0;
                //Total Sales//
                Cursor c = Query.DetailsBillProductReportReceiptNoSummary(daycount.getTimeInMillis(),dayend.getTimeInMillis(),"Report");
//                Integer cc = 0;
                if (c != null) {
                    while (c.moveToNext()) {
//                        cc ++;
//                        DetailsBillProduct.BillNo,SALES.STATUS,SUM(DetailsBillProduct.ProductQty)," +
//                        "SUM(DetailsBillProduct.ProductPrice),SUM(SALES.TotalTax) " +
//                                " FROM DetailsBillProduct inner join SALES ON DetailsBillProduct.BillNo = SALES.BillNo

                        receiptDate = c.getString(0);
                        receiptBillNo = c.getString(1);
                        receiptStatus = c.getString(2);
                        receiptQty = c.getInt(3);
                        receiptPrice = c.getDouble(4);
                        receiptTax = c.getDouble(5);

//                        if (receiptStatus.equals("VOID")){
//                            receiptQty = (-1) * receiptQty;
//                            receiptPrice = (-1) * receiptPrice;
//                            if (receiptTax != 0.0) {
//                                receiptTax = (-1) * receiptTax;
//                            }
//                        }

                        data = new Object[]{receiptDate,receiptBillNo, receiptStatus, receiptQty, receiptPrice, receiptTax};

                        daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
                    }
                    c.close();
                }
//                data = CommonReport.CountDetailsBillTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


//                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;
            String[] header = new String[]{
                    "Date           ",
                    "       Receipt No",
                    "       STATUS",
                    "  TotalQty",
                    "    Total Nett",
                    "    TotalTax (Exclusive) ",
                    "    TotalTax (Inclusive) ",
                    "    Payment Type ",
                    "    Invoice No ",};
            //TotalQty, productName, productPrice, productRemarks

            String totaldate = "";
            String totalreceipt = "";
            String totalstatus = "";
            long totalbill = 0;
            long totalqty = 0;
            long totalbillcancel = 0;
            double totalamtnett = 0;
            double totalamt = 0;
            double totalamtgross = 0;
            double totaltax = 0;
            double totaldisc = 0;
            double totalround = 0;
            double totalsurcharge = 0;
            long totalqtycancel = 0;
            double totalamtgrosscancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            for(int i=0;i<tmp.length-2;i++){
                String[] data = new String[header.length];
                Object[] summaryday = daysumdata.get(i);


                data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[0] = String.format("%1$"+header[0].length()+"s", "0");
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", "0");
                    data[5] = String.format("%1$"+header[5].length()+"s", "0");
                    data[6] = String.format("%1$"+header[6].length()+"s", "0");
                    data[7] = String.format("%1$"+header[7].length()+"s", "0");
                    data[8] = String.format("%1$"+header[8].length()+"s", "0");

                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
//                    totalbill+=(Integer)salesdata[0];
                    totaldate+=(String)salesdata[0];
                    totalreceipt+=(String)salesdata[1];
                    totalstatus +=(String)salesdata[2];

                    if (!(((String)salesdata[2]).equals("VOID"))) {
                        totalqty+=(Integer)salesdata[3];
                        totalamtnett+=(Double)salesdata[4];
                        totaltax+=(Double)salesdata[5];
                    }


                    String tmpstr = String.format("%1$"+header[0].length()+"s", ""+ salesdata[0]);
                    data[0] = tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                    tmpstr = String.format("%1$"+header[1].length()+"s", ""+ salesdata[1]);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+salesdata[2]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    tmpstr = String.format("%1$"+header[3].length()+"s", ""+salesdata[3]);
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",salesdata[4]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));


                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));


                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));


                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));

                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

//            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
//            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
            data[1] = String.format("%1$-"+(header[1].length())+"s", "");

            data[2] = String.format("%1$-"+(header[2].length())+"s", "");

            String tmpstr = String.format("%1$"+header[3].length()+"s", ""+totalqty);
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",totalamtnett));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

            tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",totaltax));
            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));

            tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",totaltax));
            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));

            tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",totaltax));
            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

            tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",totaltax));
            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));

//            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
//            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
//            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
//            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
//            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
//            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
//            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
//            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
//            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
//            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Receipt No Summary Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Receipt No Summary Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private class GenerateDetailsBillPLUReportRefund extends AsyncTask<Object, String, Object>{

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            Log.i("dsf___","__"+params[3]);
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
//            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH+1), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;


                Integer TotalQty = 0;
                String productName = "";
                Double productPrice = 0.0;
                String productRemarks = "";
                Double productTotalAmount = 0.0;
                Double productItemDis = 0.0;
                String productBillNo = "";
                //Total Sales//
                Cursor c = Query.DetailsBillProductReportRefund(daycount.getTimeInMillis(),dayend.getTimeInMillis(),"Report");
                Integer cc = 0;
                if (c != null) {
                     TotalQty = 0;
                     productName = "";
                     productPrice = 0.0;
                     productRemarks = "";
                     productTotalAmount = 0.0;
                     productItemDis = 0.0;
                     productBillNo = "";
                    while (c.moveToNext()) {
                        cc ++;
                        TotalQty = c.getInt(0);
                        productName = c.getString(1);
//                        productPrice = c.getDouble(2) / TotalQty;
//                        String plunamesql = "SELECT Price FROM PLU WHERE ID = "+c.getInt(6);
//                        Log.i("plunamesql___","plunamesql_"+plunamesql);
//                        Cursor cplunamesql = DBFunc.Query(plunamesql,true);
//                        if (cplunamesql != null){
//                            if  (cplunamesql.moveToNext()){
//                                productPrice = cplunamesql.getDouble(0);
//                            }
//                            cplunamesql.close();
//                        }
                        //productPrice = c.getDouble(2) / c.getDouble(0);
                        productPrice = c.getDouble(2);
                        productRemarks = c.getString(3);
                        //productTotalAmount = productPrice * c.getDouble(0);
                        productItemDis = ( c.getDouble(0) *  c.getDouble(5));
                        productTotalAmount = productPrice -  productItemDis;

                        TotalQty = (-1) * TotalQty;
                        productTotalAmount = (-1) * productTotalAmount;
                        productItemDis = (-1) * productItemDis;

                        productBillNo = c.getString(4);

//                        String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
//                                "WHERE ReferenceBillNo = '" + productBillNo + "' ";
//                        Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo, false);
//                        if (cSearchReferenceBillNo != null) {
//                            if (cSearchReferenceBillNo.getCount() == 0) {
                                String chkicno_on = Query.GetOptions(26);
                                if (chkicno_on.equals("1")) {
                                    //HPB
                                    if (productRemarks != null && productRemarks.length() == 9) {
                                        productRemarks = Constraints.PASSFirstDigits + productRemarks.substring(5, 9);
                                    }
                                } else {
                                    productRemarks = productRemarks;
                                }
                                if (productRemarks == null || productRemarks.equals("null") || productRemarks.equals("")) {
                                    productRemarks = "";
                                }
//                        return new Object[]{TotalQty, productName, productRemarks , productPrice};
                                Log.i("dsf__productTotalAmount", "productTotalAmount_" + productTotalAmount + "_" + productBillNo+"_"+cc);
                                data = new Object[]{TotalQty, productName, productRemarks, productPrice, productTotalAmount,productItemDis};

                                //daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
//                            } else {
//                                //daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
//                                data = new Object[]{0, "", "", 0.0, 0.0};
//                            }

//                        String productBillNo_ = "SELECT STATUS FROM SALES WHERE BillNo = '"+productBillNo+"' ";
//                        String checking_status = "";
//                        Cursor cproductBillNo_ = DBFunc.Query(productBillNo_,false);
//                        if (cproductBillNo_ != null) {
//                            if (cproductBillNo_.moveToNext()) {
//                                checking_status = cproductBillNo_.getString(0);
//                            }
//                            cproductBillNo_.close();
//                        }
//
//                        if (checking_status.equals("REFUND")) {
//                            productTotalAmount = (-1) * productTotalAmount;
//                            TotalQty = (-1) * TotalQty;
//                        }

//                        if (productRemarks != null && productRemarks.length() == 9) {
//                            productRemarks = Constraints.PASSFirstDigits+productRemarks.substring(5,9);
//                        }
//
//                        if (productRemarks == null || productRemarks.equals("null") || productRemarks.equals("")) {
//                            productRemarks = "";
//                        }
////                        return new Object[]{TotalQty, productName, productRemarks , productPrice};
//                        data = new Object[]{TotalQty, productName, productRemarks , productPrice , productTotalAmount};
//
//                        daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
//                            cSearchReferenceBillNo.close();
//                        }

                        daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
                    }
                    c.close();
                }
//                data = CommonReport.CountDetailsBillTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


//                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;

            String[] header = new String[]{
                    //"ProductName    ",
                    "ProductName                ",
                    "ProductRemarks                ",
                    "       TotalQty",
                    "       TotalPrice",
                    "       ItemDiscount"};

            //TotalQty, productName, productPrice, productRemarks


            long totalbill = 0;
            long totalqty = 0;
            long totalbillcancel = 0;
            double totalamtnett = 0;
            double totalamt = 0;
            double totalitemdis = 0;
            double totalamtgross = 0;
            double totaltax = 0;
            double totaldisc = 0;
            double totalround = 0;
            double totalsurcharge = 0;
            long totalqtycancel = 0;
            double totalamtgrosscancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            for(int i=0;i<tmp.length-2;i++){
                String[] data = new String[header.length];
                Object[] summaryday = daysumdata.get(i);


                data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[0] = String.format("%1$"+header[0].length()+"s", "0");
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", "0");

                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
//                    totalbill+=(Integer)salesdata[0];
                    totalqty+=(Integer)salesdata[0];
                    totalamtnett+=(Double)salesdata[3];
                    totalamt+=(Double)salesdata[4];
                    totalitemdis+=(Double)salesdata[5];
//                    totaltax+=(Double)salesdata[3];
//                    totalround+=(Double)salesdata[4];
//                    totalamtgross+=(Double)salesdata[5];
//                    totaldisc+=(Double)salesdata[6];
//                    totalsurcharge+=(Double)salesdata[7];
//                    totalbillcancel+=(Integer)salesdata[8];
//                    totalqtycancel+=(Integer)salesdata[9];
//                    totalamtgrosscancel+=(Double)salesdata[11];


                    String tmpstr = String.format("%1$-"+header[0].length()+"s", salesdata[1]);
                    data[0] = tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                    tmpstr = String.format("%1$-"+(header[1].length())+"s ", salesdata[2]);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));


//                    tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",salesdata[3]));
//                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+(Integer)salesdata[0]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",salesdata[4]));
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

//            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
//            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
            data[1] = String.format("%1$-"+(header[1].length())+"s", "");

//            String tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",totalamtnett));
//            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            String tmpstr = String.format("%1$"+header[2].length()+"s", ""+totalqty);
            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",totalamt));
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",totalitemdis));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

//            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
//            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
//            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
//            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
//            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
//            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
//            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
//            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
//            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
//            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Item Refund Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Item Refund Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private class GenerateDetailsBillPLUReport extends AsyncTask<Object, String, Object>{

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            Log.i("dsf___","__"+params[3]);
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
//            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH+1), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;


                Integer TotalQty = 0;
                String productName = "";
                Double productPrice = 0.0;
                String productRemarks = "";
                Double productTotalAmount = 0.0;
                Double productItemDis = 0.0;
                String productBillNo = "";
                String productStatus = "";
                //Total Sales//
                Cursor c = Query.DetailsBillProductReport(daycount.getTimeInMillis(),dayend.getTimeInMillis(),"Report");
                Integer cc = 0;
                if (c != null) {
                     TotalQty = 0;
                     productName = "";
                     productPrice = 0.0;
                     productRemarks = "";
                     productTotalAmount = 0.0;
                     productItemDis = 0.0;
                     productBillNo = "";
                     productStatus = "";
                    while (c.moveToNext()) {
                        cc ++;
                        TotalQty = c.getInt(0);
                        productName = c.getString(1);
                        productPrice = c.getDouble(2) / TotalQty;
//                        productPrice = c.getDouble(2);
                        productRemarks = c.getString(3);

                       // productItemDis =  ( c.getDouble(0) * c.getDouble(5));
                        productItemDis =  c.getDouble(5);

                        //productTotalAmount = c.getDouble(2) - productItemDis;
                        productTotalAmount = c.getDouble(2);

                        productBillNo = c.getString(4);
                        productStatus = c.getString(20);

                        String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                                "WHERE ReferenceBillNo = '" + productBillNo + "' ";
                        Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo, false);
                        if (cSearchReferenceBillNo != null) {
                            if (cSearchReferenceBillNo.getCount() == 0) {
//                            Log.i("dfdf___","888___GGGG___"+cSearchReferenceBillNo.getCount());
//                            if (cSearchReferenceBillNo.getCount() > 0) {
                                String chkicno_on = Query.GetOptions(26);
                                if (chkicno_on.equals("1")) {
                                    //HPB
                                    if (productRemarks != null && productRemarks.length() == 9) {
                                        productRemarks = Constraints.PASSFirstDigits + productRemarks.substring(5, 9);
                                    }
                                } else {
                                    productRemarks = productRemarks;
                                }

                                if (productRemarks == null || productRemarks.equals("null") || productRemarks.equals("")) {
                                    productRemarks = "";
                                }

                                if (productStatus.equals("REFUND")) {
                                    TotalQty = (-1) * TotalQty;
                                    productTotalAmount = (-1) * productTotalAmount;
                                    productItemDis = (-1) * productItemDis;
                                }
//                        return new Object[]{TotalQty, productName, productRemarks , productPrice};
                                Log.i("dsf_otalAmounty", "productTotalAmount_" + productTotalAmount + "_" + productBillNo+"_"+cc);
                                data = new Object[]{TotalQty, productName, productRemarks, productPrice, productTotalAmount,productItemDis};

                                //daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
                            } else {
                                //daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
//                                data = new Object[]{0, "", "", 0.0, 0.0, 0.0};
                                data = new Object[]{TotalQty, productName, productRemarks, productPrice, productTotalAmount,productItemDis};

                            }
                            cSearchReferenceBillNo.close();
                        }

                        daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
                    }
                    c.close();
                }
//                data = CommonReport.CountDetailsBillTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


//                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;
            String[] header = new String[]{
                    "ProductName                ",
                    "ProductRemarks                ",
                    "       TotalQty",
                    "       TotalPrice",
                    "       ItemDiscount"};



            long totalbill = 0;
            long totalqty = 0;
            long totalbillcancel = 0;
            double totalamtnett = 0;
            double totalamt = 0;
            double totalitemdismt = 0;
            double totalamtgross = 0;
            double totaltax = 0;
            double totaldisc = 0;
            double totalround = 0;
            double totalsurcharge = 0;
            long totalqtycancel = 0;
            double totalamtgrosscancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            for(int i=0;i<tmp.length-2;i++){
                String[] data = new String[header.length];
                Object[] summaryday = daysumdata.get(i);


                data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[0] = String.format("%1$"+header[0].length()+"s", "0");
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", "0");

                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
//                    totalbill+=(Integer)salesdata[0];
                    totalqty+=(Integer)salesdata[0];
                    totalamtnett+=(Double)salesdata[3];
                    totalamt+=(Double)salesdata[4];
                    totalitemdismt+=(Double)salesdata[5];
//                    totaltax+=(Double)salesdata[3];
//                    totalround+=(Double)salesdata[4];
//                    totalamtgross+=(Double)salesdata[5];
//                    totaldisc+=(Double)salesdata[6];
//                    totalsurcharge+=(Double)salesdata[7];
//                    totalbillcancel+=(Integer)salesdata[8];
//                    totalqtycancel+=(Integer)salesdata[9];
//                    totalamtgrosscancel+=(Double)salesdata[11];



                    //String tmpstr = String.format("%1$-"+header[0].length()+"s", salesdata[1]);
                    //tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                    // tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.name);
                    //tmpstr = String.format("%1$-"+(header[1].length())+"s ", data.name);
                   // tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
                    //TotalQty, productName, productPrice, productRemarks


                    //String tmpstr = String.format("%1$-"+(header[0].length())+"s ", salesdata[1]);
                    String tmpstr = String.format("%1$-"+header[0].length()+"s", salesdata[1]);
                    data[0] = tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                    tmpstr = String.format("%1$-"+(header[1].length())+"s ", salesdata[2]);
                    //tmpstr = String.format("%1$-"+(header[1].length())+"s ", data.name);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+(Integer)salesdata[0]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",salesdata[4]));
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",salesdata[5]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

//            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
//            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
            data[1] = String.format("%1$-"+(header[1].length())+"s", "");

//            String tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",totalamtnett));
//            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            String tmpstr = String.format("%1$"+header[2].length()+"s", ""+totalqty);
            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",totalamt));
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));


            tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",totalitemdismt));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

//            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
//            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
//            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
//            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
//            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
//            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
//            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
//            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
//            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
//            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Item Remarks Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Item Remarks Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private class GeneratePLUReport extends AsyncTask<Object, String, Object> {


        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {

            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            if(params[3]!=null){
                refer = (ReferData)params[3];
            }
            boolean viewonly = (Boolean)params[4];
            boolean groupdept = (Boolean)params[5];



            List<PLUSalesData> plu = null;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }


            plu = CommonReport.CountPLUSalesTotal(fromdate.getTimeInMillis(),todate.getTimeInMillis(), groupdept, uid, rid);


            if(plu==null){
                return null;
            }

            if(groupdept){//group by department
                Collections.sort(plu, new Comparator<PLUSalesData>(){
                    @Override
                    public int compare(PLUSalesData a, PLUSalesData b) {
                        if(a.pluid==b.pluid){
                            return 0;
                        }
                        return a.pluid<b.pluid? -1:1;
                    }
                });
                Collections.sort(plu, new Comparator<PLUSalesData>(){
                    @Override
                    public int compare(PLUSalesData a, PLUSalesData b) {
                        if(a.deptid==b.deptid){
                            return 0;
                        }
                        return a.deptid<b.deptid? -1:1;
                    }
                });

            }else{
                Collections.sort(plu, new Comparator<PLUSalesData>(){
                    @Override
                    public int compare(PLUSalesData a, PLUSalesData b) {
                        if(a.pluid==b.pluid){
                            return 0;

                        }
                        return a.pluid<b.pluid? -1:1;
                    }
                });
            }






            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
            }
            int height = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
            }
            int width = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            String[] header = new String[]{
                    "PLU ID    ",
                    "Name                ",
                    "       Qty Sold",
                    "          Amt Nett",
                    "               Tax",
                    "     Item Disc",
                    "   Item Surchg",
                    "         Cancel",
                    "        Amt Cancel"};


            List<String> datastr = new ArrayList<String>();

            int deptID = -2;
            String deptName = "";

            if(groupdept){

            }else{
                //double qtysold = 0;
                int qtysold = 0;
                double amtsold = 0;
                double amtnett = 0;
                double amtunitprice = 0;
                double tax = 0;
                double disc = 0;
                double srchg = 0;
                double cancel = 0;
                double amtcancel = 0;

                for(PLUSalesData data:plu){
                    String tmp = "";

                    String tmpstr = String.format("%1$-"+header[0].length()+"s", data.pluid);
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                   // tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.name);
                    tmpstr = String.format("%1$-"+(header[1].length())+"s ", data.name);
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));


////                    tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.paidamounttax));
//                    tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.unitprice));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    //tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.paidqty));
                    tmpstr = String.format("%1$"+header[2].length()+"s", data.paidqty);
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

//                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f", ( data.paidamountnotax * data.paidqty )));
//                    //tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f", ( 10.00 * data.paidqty )));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f", (( data.paidamountnotax * data.paidqty ) - data.discamount)));


                    Log.i("data.nett___","__"+data.paidqty+"__ddddd__"+data.unitprice);
                    tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f", data.unitprice));
                    //tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f", ( 10.00 * data.paidqty )));
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",data.paidtax));
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",data.discamount));
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));

                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",data.surchgamt));
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));

                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",data.cancelqty));
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",data.cancelamtnotax));
                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
                    datastr.add(tmp);


                    qtysold += data.paidqty;
                    amtsold += data.paidamounttax;
                    //amtnett += data.paidamountnotax;
                    //amtnett += ( data.paidamountnotax * data.paidqty );
                    //amtnett += data.nett;
                    amtunitprice += data.unitprice;
                    tax += data.paidtax;
                    disc += data.discamount;
                    srchg += data.surchgamt;
                    cancel += data.cancelqty;
                    amtcancel += data.cancelamtnotax;
                }

                datastr.add("");

                String tmp = "";

                String tmpstr = String.format("%1$-"+header[0].length()+"s", "Total");
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                tmpstr = String.format("%1$-"+header[1].length()+"s", " ");
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

//                //tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",amtsold));
//                tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",amtunitprice));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                //tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",qtysold));
                tmpstr = String.format("%1$"+header[2].length()+"s", qtysold);
                //tmpstr = String.format("%1$"+header[2].length()+"s", qtysold);
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                //tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",amtnett));
//                tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f", ( amtnett * qtysold )));
                //tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f", (amtnett - disc)));
                tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f", amtunitprice));
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",tax));
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

                tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",disc));
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));

                tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",srchg));
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));

                tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",cancel));
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

                tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",amtcancel));
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
                datastr.add(tmp);
            }


            String[] tmp = datastr.toArray(new String[0]);

            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    if(groupdept){
                        DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"PLU Group By Dept", user,refer);
                    }else{
                        DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Summary PLU Report", user,refer);
                    }
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        if(groupdept){
                            DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"PLU Group By Dept", user,refer);
                        }else{
                            DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Summary PLU Report", user,refer);
                        }
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private class GenerateStockInReport extends AsyncTask<Object, String, Object> {


        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {

            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            if(params[3]!=null){
                refer = (ReferData)params[3];
            }
            boolean viewonly = (Boolean)params[4];
//            boolean groupdept = (Boolean)params[5];



            List<StockInRef> stockInData = null;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }


            stockInData = CommonReport.CountStockInData(fromdate.getTimeInMillis(),todate.getTimeInMillis(), uid, rid);


            if(stockInData==null){
                return null;
            }

            Log.i("stockInData___","stockInData____"+stockInData);
            Collections.sort(stockInData, new Comparator<StockInRef>(){
                @Override
                public int compare(StockInRef a, StockInRef b) {
                    if(a.pluid==b.pluid){
                        return 0;

                    }
                    return Integer.parseInt(a.pluid)<Integer.parseInt(b.pluid)? -1:1;
                }
            });

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
            }
            int height = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
            }
            int width = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            String[] header = new String[]{
                    "PLU ID    ",
                    "Name                ",
                    "       Qty Sold",
                    "        Datetime"};


            List<String> datastr = new ArrayList<String>();

            int deptID = -2;
            String deptName = "";


            double qtysold = 0;
            //int qtysold = 0;
            double amtsold = 0;
            double amtnett = 0;
            double tax = 0;
            double disc = 0;
            double srchg = 0;
            double cancel = 0;
            double amtcancel = 0;

            Log.i("stockInData___","stockInData____1"+stockInData);
            for(StockInRef data:stockInData){
                String tmp = "";

                Log.i("stockInData___","stockInData____data.pluid"+data.pluid);
                String tmpstr = String.format("%1$-"+header[0].length()+"s", data.pluid);
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.pluname);
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.pluname);
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.pluname);
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.pluname);
                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

//                tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.pluprice));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",data.transno));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",data.dt));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));


                qtysold += data.qty;
                Log.i("stockInData___","stockInData____qtysold"+qtysold);
//                    amtsold += data.paidamounttax;
//                    amtnett += data.paidamountnotax;
//                    tax += data.paidtax;
//                    disc += data.discamount;
//                    srchg += data.surchgamt;
//                    cancel += data.cancelqty;
//                    amtcancel += data.cancelamtnotax;
            }

            datastr.add("");

            String tmp1 = "";

            String tmpstr = String.format("%1$-"+header[0].length()+"s", "Total");
            tmp1 += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

            tmpstr = String.format("%1$-"+header[1].length()+"s", " ");
            tmp1 += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

            tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",qtysold));
            //tmpstr = String.format("%1$"+header[2].length()+"s", qtysold);
            tmp1 += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",amtsold));
            tmp1 += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            datastr.add(tmp1);


            String[] tmp = datastr.toArray(new String[0]);

            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);

                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Stock In Report", user,refer);

                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);

                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Stock In Report", user,refer);

                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }


    private class GenerateDeptReport extends AsyncTask<Object, String, Object>{


        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {

            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            if(params[3]!=null){
                refer = (ReferData)params[3];
            }
            boolean viewonly = (Boolean)params[4];

			/*
			Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

			if(bill==null){//DB Error
				return null;
			}

			List<DeptSalesData> dept = null;

			if(bill.moveToNext()){
				int billstart = bill.getInt(0);
				int billend = bill.getInt(1);
				if(user==null){
					dept = CommonReport.CountDeptSalesTotal(billstart, billend, -1);
				}else{
					dept = CommonReport.CountDeptSalesTotal(billstart, billend, user.getID());
				}
			}
			*/

            //List<DeptSalesData> dept = null;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }


           // dept = CommonReport.CountDeptSalesTotal(fromdate.getTimeInMillis(), todate.getTimeInMillis(), uid, rid);


//            if(dept==null){
//                return null;
//            }


//            Collections.sort(dept, new Comparator<DeptSalesData>(){
//                @Override
//                public int compare(DeptSalesData a, DeptSalesData b) {
//                    if(a.deptID==b.deptID){
//                        return 0;
//
//                    }
//                    return a.deptID<b.deptID? -1:1;
//                }
//            });







            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //	"           Sold",
            String[] header = new String[]{
                    "Dept ID   ",
                    "Name                ",
                    "       Qty Sold",
                    "            Amount",
                    "          Amt Nett",
                    "               Tax",
                    "     Item Disc",
                    "   Item Surchg",
                    "         Cancel",
                    "        Amt Cancel"};


            List<String> datastr = new ArrayList<String>();

            double qtysold = 0;
            double amtsold = 0;
            double amtnett = 0;
            double tax = 0;
            double disc = 0;
            double srchg = 0;
            double cancel = 0;
            double amtcancel = 0;

//            for(DeptSalesData data:dept){
//                String tmp = "";
//                String tmpstr = "";
//                if(data.deptID==-1){
//                    tmpstr = String.format("%1$-"+header[0].length()+"s", "---");
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));
//
//                    tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", "(No Dept)");
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//                }else{
//                    tmpstr = String.format("%1$-"+header[0].length()+"s", data.deptID);
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));
//
//                    tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.name);
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//                }
//
//                tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.deptPaidQty));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",data.deptTaxSales));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",data.deptPaidSales));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",data.deptTax));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",data.discamount));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",data.surchgamt));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",data.deptCancelQty));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",data.deptCancelSales));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                datastr.add(tmp);
//
//
//                qtysold += data.deptPaidQty;
//                amtsold += data.deptTaxSales;
//                amtnett += data.deptPaidSales;
//                tax += data.deptTax;
//                disc += data.discamount;
//                srchg += data.surchgamt;
//                cancel += data.deptCancelQty;
//                amtcancel += data.deptCancelSales;
//            }

            datastr.add("");

            String _tmp = "";

            String tmpstr = String.format("%1$-"+header[0].length()+"s", "Total");
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

            tmpstr = String.format("%1$-"+header[1].length()+"s", " ");
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

            tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",qtysold));
            //tmpstr = String.format("%1$"+header[2].length()+"s", qtysold);
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",amtsold));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",amtnett));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

            tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",tax));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));

            tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",disc));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));

            tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",srchg));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

            tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",cancel));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));

            tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",amtcancel));
            _tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
            datastr.add(_tmp);



            String[] tmp = datastr.toArray(new String[0]);

            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);

                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata, "Department Summary", user,refer);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata, "Department Summary", user,refer);
                        //DrawReportPLUSubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata, groupdept);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private class GenerateSalesSumReport extends AsyncTask<Object, String, Object>{

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
//            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH+1), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;



                data = CommonReport.CountSalesTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;
            String[] header = new String[]{
                    "Date         ",
                    "Bill Paid",
                    "    Bill Cancel",
                    "    Qty Sold",
                    "    Amt Nett",
                    "    Amt Gross",
//                    "     Total Amt",
                    "    Tax Total",
                    "    Discount",
                    "    Round",
                    "    Surcharge",
                    "    Qty Cancel",
                    "    Amt Cancel"};

            long totalbill = 0;
            long totalqty = 0;
            long totalbillcancel = 0;
            double totalamtnett = 0;
            double totalamtgross = 0;
            double totaltax = 0;
//            double totalamt = 0;
            double totaldisc = 0;
            double totalround = 0;
            double totalsurcharge = 0;
            long totalqtycancel = 0;
            double totalamtgrosscancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            for(int i=0;i<tmp.length-2;i++){
                String[] data = new String[header.length];
                Object[] summaryday = daysumdata.get(i);


                data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",0d));
                    data[5] = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",0d));
                    data[6] = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",0d));
                    data[7] = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",0d));
                    data[8] = String.format("%1$"+header[10].length()+"s", String.format("%,.2f",0d));
                    data[9] = String.format("%1$"+header[11].length()+"s", "0");
                    data[10] = String.format("%1$"+header[12].length()+"s", String.format("%,.2f",0d));
                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
                    totalbill+=(Integer)salesdata[0];
                    totalqty+=(Integer)salesdata[1];
                    totalamtnett+=(Double)salesdata[2];
                    totaltax+=(Double)salesdata[3];
//                    totalamt+=totalamtnett+totaltax;
                    totalround+=(Double)salesdata[4];
                    totalamtgross+=(Double)salesdata[5];
                    totaldisc+=(Double)salesdata[6];
                    totalsurcharge+=(Double)salesdata[7];
                    totalbillcancel+=(Integer)salesdata[8];
                    totalqtycancel+=(Integer)salesdata[9];
                    totalamtgrosscancel+=(Double)salesdata[11];

                    String tmpstr = String.format("%1$"+header[1].length()+"s", ""+(Integer)salesdata[0]);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+(Integer)salesdata[8]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
                    tmpstr = String.format("%1$"+header[3].length()+"s", ""+(Integer)salesdata[1]);
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",(Double)salesdata[2]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",(Double)salesdata[5]));
                    data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",((Double)salesdata[2])+(Double)salesdata[3]));
//                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",(Double)salesdata[3]));
                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",(Double)salesdata[6]));
                    data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));;
                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",(Double)salesdata[4]));
                    data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));;
                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",(Double)salesdata[7]));
                    data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
                    tmpstr = String.format("%1$"+header[10].length()+"s", ""+(Integer)salesdata[9]);
                    data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
                    tmpstr = String.format("%1$"+header[11].length()+"s", String.format("%,.2f",(Double)salesdata[11]));
                    data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

            tmpstr = String.format("%1$"+header[2].length()+"s", ""+totalbillcancel);
            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", ""+totalqty);
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totalamt));
//            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }
//    private class GenerateSalesSumReport extends AsyncTask<Object, String, Object>{
//
//        @Override
//        protected void onPreExecute(){
//            if(dlg!=null)dlg.show();
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        protected Object doInBackground(Object... params) {
//
//
//            Calendar fromdate = (Calendar)params[0];
//            Calendar todate = (Calendar)params[1];
//            UserData user = null;
//            if(params[2]!=null){
//                user = (UserData)params[2];
//            }
//            ReferData refer = null;
//            if(params[3]!=null){
//                refer = (ReferData)params[3];
//                Log.e("ASDAD",refer.getName());
//
//            }
//            boolean viewonly = (Boolean)params[4];
//
//            List<Object[]> daysumdata = new ArrayList<Object[]>();
//            long from_tick = (fromdate.getTimeInMillis()/86400000L);
//            long to_tick = todate.getTimeInMillis()/86400000L;
//
//            Calendar daycount = Calendar.getInstance();
//            //daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
//            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
//            daycount.set(Calendar.MILLISECOND, 0);
//            int day =(int) (to_tick - from_tick);
//            if(day<=0)day=1;
//
//            int uid = -1;
//            int rid = -2;
//            if(user!=null){
//                uid = user.getID();
//            }
//            if(refer!=null){
//                rid = refer.getID();
//            }
//
//            for(int i=0;i<day;i++){
//                Calendar dayend = Calendar.getInstance();
//                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
//                dayend.set(Calendar.MILLISECOND, 999);
//                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);
//
//                Object[] data = null;
//
//
//                Log.i("DFDF___","DFDF_aa__"+daycount);
//
//                ReportActivity.previousReport = 00;
//                data = CommonReport.CountSalesTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);
////                data = CommonReport.CountSalesTotal1(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);
//
////                daycount.add(Calendar.DATE, 1);
//
//                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});
//
//
//                daycount.add(Calendar.DATE, 1);
//
//                Log.i("DFDF___","DFDF_bb__"+daycount);
//            }
//
//            Object doc = null;
//
//            if(viewonly){
//                doc = new ArrayList<Bitmap>();
//            }else{
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    doc = new PdfDocument();
//                }
//            }
//            int height = 0;
//            int width = 0;
//            PrintAttributes.MediaSize mediasize = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                mediasize = PrintAttributes.MediaSize.ISO_A4;
//                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
//                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
//            }
//
//            Paint p = new Paint();
//            p.setTextSize(8*scale);
//            p.setTypeface(normaltext);
//            float txtheight = 9*scale;
//
//            float pagefillheight = height-(70*scale);
//            //float pagefillwidth = width - 40;
//            String[] header = new String[]{
//                    "Date         ",
//                    " Bill Paid",
//                    "  Bill Cancel",
//                    "   Qty Sold",
//                    "      Amt Nett",
//                    "     Amt Gross",
//                    "     Tax Total",
//                    "      Discount",
//                    "      Round",
//                    "     Surcharge",
//                    "  Qty Cancel",
//                    "    Amt Cancel"};
//
//            long totalbill = 0;
//            long totalqty = 0;
//            long totalbillcancel = 0;
//            double totalamtnett = 0;
//            double totalamtgross = 0;
//            double totaltax = 0;
//            double totaldisc = 0;
//            double totalround = 0;
//            double totalsurcharge = 0;
//            long totalqtycancel = 0;
//            double totalamtgrosscancel = 0;
//            String[] tmp = new String[2+daysumdata.size()];
//            for(int i=0;i<tmp.length-2;i++){
//                String[] data = new String[header.length];
//                Object[] summaryday = daysumdata.get(i);
//                data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
//                if(summaryday[1]==null){
//                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
//                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
//                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
//                    data[4] = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",0d));
//                    data[5] = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",0d));
//                    data[6] = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",0d));
//                    data[7] = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",0d));
//                    data[8] = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",0d));
//                    data[9] = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",0d));
//                    data[10] = String.format("%1$"+header[10].length()+"s", "0");
//                    data[11] = String.format("%1$"+header[11].length()+"s", String.format("%,.2f",0d));
//                }else{
//                    Object[] salesdata = (Object[])summaryday[1];
////                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
////                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
////                    Log.i("DTATE___","DFDFDFdate___"+fmtt.format(new Date((Long)summaryday[0])));
////
//////                    yyyy/MM/dd
////                    String str_report_query = "  AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') = '"+
////                            fmtt.format(new Date((Long)summaryday[0]))+"'";
////                    String query = " WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND') ";
////                    String sql = "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(GrossTotal),SUM(TotalBillDisount)," +
////                            "SUM(TotalTaxes),SUM(RoundAdj)," +
////                            "SUM(ServiceCharges),count(ID),SUM(TotalItemDisount),ReferenceBillNo,BillNo" +
////                            " FROM Sales " +
////                            query +
////                            str_report_query +
//////                    " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '"+ ReportActivity.start +"' AND '"+ ReportActivity.end +"' " +
////                            " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" ;
////
////                    sql += " order by BillNo DESC ";
////                    Log.i("DFDF_____","DFDF_____"+sql);
////                    Cursor cccc = DBFunc.Query(sql,false);
////                    if (cccc != null){
////                        if (cccc.moveToNext()){
////                            totalbill = cccc.getInt(0);
////                            totalqty = cccc.getInt(0);
////                            totalamtnett = cccc.getDouble(1);
////                            totaltax = cccc.getDouble(1);
////                            totalround+= cccc.getDouble(1);
////                            totalamtgross+= cccc.getDouble(1);
////                            totaldisc=cccc.getDouble(1);
////                            totalsurcharge=cccc.getDouble(1);
////                            totalbillcancel=cccc.getInt(0);
////                            totalqtycancel=cccc.getInt(0);
////                            totalamtgrosscancel=cccc.getDouble(1);
////                        }
////                        cccc.close();
////                    }
////                    totalbill+=(Integer)salesdata[0];
////                    totalqty+=(Integer)salesdata[1];
////                    totalamtnett+=(Double)salesdata[2];
////                    totaltax+=(Double)salesdata[3];
////                    totalround+=(Double)salesdata[4];
////                    totalamtgross+=(Double)salesdata[5];
////                    totaldisc+=(Double)salesdata[6];
////                    totalsurcharge+=(Double)salesdata[7];
////                    totalbillcancel+=(Integer)salesdata[8];
////                    totalqtycancel+=(Integer)salesdata[9];
////                    totalamtgrosscancel+=(Double)salesdata[11];
//
//                    String tmpstr = String.format("%1$"+header[1].length()+"s", ""+(Integer)salesdata[0]);
//                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+(Integer)salesdata[8]);
//                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//                    tmpstr = String.format("%1$"+header[3].length()+"s", ""+(Integer)salesdata[1]);
//                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",(Double)salesdata[2]));
//                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",(Double)salesdata[5]));
//                    data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",(Double)salesdata[3]));
//                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",(Double)salesdata[6]));
//                    data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));;
//                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",(Double)salesdata[4]));
//                    data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));;
//                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",(Double)salesdata[7]));
//                    data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                    tmpstr = String.format("%1$"+header[10].length()+"s", ""+(Integer)salesdata[9]);
//                    data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
//                    tmpstr = String.format("%1$"+header[11].length()+"s", String.format("%,.2f",(Double)salesdata[11]));
//                    data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
//                }
//
//                tmp[i] = "";
//                for(int j=0;j<data.length;j++){
//                    tmp[i]+=data[j];
//                }
//            }
//
//            tmp[tmp.length-2] = "";
//            tmp[tmp.length-1] = "";
//            String[] data = new String[header.length];
//            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");
//
//            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
//            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//
//            tmpstr = String.format("%1$"+header[2].length()+"s", ""+totalbillcancel);
//            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//            tmpstr = String.format("%1$"+header[3].length()+"s", ""+totalqty);
//            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
//            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
//            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
//            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
//            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
//            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
//            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
//            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
//            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
//            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
//            for(int j=0;j<data.length;j++){
//                tmp[tmp.length-1]+=data[j];
//            }
//            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
//            int itemperpage = (int)(pagefillheight/txtheight);
//            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
//                pagemax++;
//            }
//
//            for(int i=0;i<pagemax;i++){
//                int nextcount = itemperpage;
//                if(tmp.length-(i)*itemperpage <itemperpage){
//                    nextcount = tmp.length-(i)*itemperpage;
//                }
//                String[] tmpdata = new String[nextcount];
//                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
//                if(viewonly){
//                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
//                    Canvas c = new Canvas(b);
//                    Paint paint = new Paint();
//                    paint.setColor(Color.WHITE);
//                    c.drawRect(0, 0, width, height, paint);
//                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
//                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
//                    ((ArrayList<Bitmap>)doc).add(b);
//                }else{
//                    PdfDocument.PageInfo pi = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
//                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
//                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
//                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
//                        ((PdfDocument)doc).finishPage(page);
//                    }
//                }
//            }
//
//            return doc;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... params){}
//
//        @Override
//        protected void onPostExecute(Object output){
//            ReportResult(output);
//
//        }
//    }


    private class GenerateSalesSumReport1 extends AsyncTask<Object, String, Object>{

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;



                data = CommonReport.CountSalesPLUTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;
//            String[] header = new String[]{
////                    "Date         ",
////                    " Bill Paid",
////                    "  Bill Cancel",
////                    "   Qty Sold",
////                    "      Amt Nett",
////                    "     Amt Gross",
////                    "     Tax Total",
////                    "      Discount",
////                    "      Round",
////                    "     Surcharge",
////                    "  Qty Cancel",
////                    "    Amt Cancel"};//12
            String[] header = new String[]{
                    "PLU ID    ",
                    "Name                ",
                    "       Qty Sold",
                    "            Amount",
                    "          Amt Nett",
                    "               Tax",
                    "     Item Disc",
                    "   Item Surchg",
                    "         Cancel",
                    "        Amt Cancel"};//10

            long pluid = 0;
            //long totalqty = 0;
            String pluname = "";
            long qtysold = 0;
            double nett = 0;
            double tax = 0;
            double dis = 0;
            double servicecharges = 0;
            double cancel = 0;
            double amtcancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            Log.i("___daysumdata", String.valueOf(daysumdata.size()));
            Log.i("___tmp", String.valueOf(tmp));
            Log.i("___tmplength", String.valueOf(tmp.length));
            //for(int i=0;i<tmp.length-2;i++){
            for(int i=0;i<tmp.length-2;i++){
                Log.i("___header.length", String.valueOf(header.length));
                String[] data = new String[header.length];
                Log.i("__daysumdata.get(i)", String.valueOf(daysumdata));
                Log.i("__daysumdata.get(i)", String.valueOf(daysumdata.get(i)));
                Object[] summaryday = daysumdata.get(i);


                //data[0] = String.format("%1$-"+(header[0].length())+"s", fmt.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[0] = String.format("%1$"+header[0].length()+"s", "0");
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",0d));
                    data[5] = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",0d));
                    data[6] = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",0d));
                    data[7] = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",0d));
                    data[8] = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",0d));
                    data[9] = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",0d));
                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    Log.i("Dfd___salesdata", String.valueOf(salesdata.length));

                    pluid = (Integer)salesdata[0];
                    pluname  =(String)salesdata[1];
                    qtysold +=(Integer)salesdata[2];
                    nett +=(Double)salesdata[3];
                    nett +=(Double)salesdata[4];
                    tax +=(Double)salesdata[5];
                    dis +=(Double)salesdata[6];
                    servicecharges +=(Double)salesdata[7];
                    cancel +=(Double)salesdata[8];
                    amtcancel +=(Double)salesdata[9];

                    //String tmpstr = String.format("%1$"+header[0].length()+"s", ""+(Integer)salesdata[0]);
                    String tmpstr = String.format("%1$-"+(header[0].length())+"s", (Integer)salesdata[0]);
                    data[0] = tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));

                    tmpstr = String.format("%1$-"+(header[1].length())+"s", (String)salesdata[1]);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

                    tmpstr = String.format("%1$"+header[2].length()+"s", salesdata[2]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

                    tmpstr = String.format("%1$"+header[3].length()+"s", ""+ String.format("%,.2f",(Double)salesdata[3]));
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

                    tmpstr = String.format("%1$"+header[4].length()+"s",  String.format("%,.2f",(Double)salesdata[4]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",(Double)salesdata[5]));
                    data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));

                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",(Double)salesdata[6]));
                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));

                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",(Double)salesdata[7]));
                    data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));

                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",(Double)salesdata[8]));
                    data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));

                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",(Double)salesdata[9]));
                    data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));

                    Log.i("Dfd___sdataa", String.valueOf(data));

//                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",(Double)salesdata[9]));
//                    data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+pluname);
            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

            tmpstr = String.format("%1$"+header[2].length()+"s", ""+qtysold);
            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", ""+String.format("%,.2f",nett));
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",nett));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",tax));
            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",dis));
            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",servicecharges));
            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",cancel));
            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",amtcancel));
            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));

            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }


    private class GenerateUserLogReport extends AsyncTask<Object, String, Object>{


        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {

            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            boolean viewonly = (Boolean)params[3];


            String sql = "SELECT DISTINCT(user_id) FROM userlog WHERE time BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis();

            if(user!=null){
                sql += " AND user_id = "+user.id+" AND name = '"+DBFunc.PurifyString(user.name)+"'";
            }

            sql += " ORDER BY user_id";


            Cursor c_user = DBFunc.Query(sql, false);
            if(c_user==null){//DB Error
                return null;
            }

            List<Integer> _tmpuserid = new ArrayList<Integer>();

            while(c_user.moveToNext()){
                _tmpuserid.add(c_user.getInt(0));
            }

            c_user.close();

            //Map<Integer,List<String>> data = new HashMap<Integer,List<String>>();
            List<UserLogData> data = new ArrayList<UserLogData>();
            for(int id : _tmpuserid){
                sql = "SELECT DISTINCT(name) FROM userlog WHERE user_id = "+id +" AND time BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis();

                if(user!=null){
                    sql += " AND name = '"+DBFunc.PurifyString(user.name)+"'";
                }

                Cursor _tmp = DBFunc.Query(sql, false);//get list of name





                if(_tmp==null){
                    return null;
                }

                while(_tmp.moveToNext()){
                    data.add(new UserLogData(id,_tmp.getString(0)));
                }

                _tmp.close();
            }

            for(UserLogData l : data){
                Cursor _tmp = DBFunc.Query("SELECT time,info FROM userlog WHERE time BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis()+" AND user_id = "+l.getID()+" AND name = '"+DBFunc.PurifyString(l.getName())+"' ORDER BY time ASC", false);

                if(_tmp==null){
                    return null;
                }

                while(_tmp.moveToNext()){
                    l.getLog().put(new Date(_tmp.getLong(0)), _tmp.getString(1));
                }
                _tmp.close();
            }



            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            PrintAttributes.MediaSize mediasize = null;
            int height = 0;
            int width = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            String[] header = new String[]{
                    //yyyy/mm/dd hh:mm.ss
                    "Date Time               ",
                    "Information"};



            List<String> datastr = new ArrayList<String>();

            for(UserLogData l : data){
                datastr.add("");
                datastr.add(l.getName());
                datastr.add("");

                Date[] adate = l.getLog().keySet().toArray(new Date[0]);
                Arrays.sort(adate);

                for(Date date : adate){
                    //String tmp = fmt.format(date)+" "+fmttime.format(date);
                    String tmp = String.format("%-20s", fmt.format(date) + "  " + fmttime.format(date));
                    if(tmp.length()>24){
                        tmp = tmp.substring(0, 24);
                    }
                    datastr.add(MessageFormat.format("{0}    {1}", tmp, l.getLog().get(date)));
                }
            }

            String[] tmp = datastr.toArray(new String[0]);

            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);

                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata, "User Log", user,null);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata, "User Log", user,null);
                        //DrawReportPLUSubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata, groupdept);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    static ArrayList aa = new ArrayList();
    private void DrawReportPage(Canvas c, Calendar fromdate, Calendar todate, int pagenum, int pagemax,
                                 String[] header,  String[] item, String title, UserData user, ReferData refer){

        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setTextSize(10*scale);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

//        String tmpstr = MessageFormat.format("Date: {0} \u2192 {1}",fmt.format(fromdate.getTime()), fmt.format(todate.getTime()));
//        c.drawText(tmpstr, 25*scale, 50*scale, paint);
        String tmpstr = MessageFormat.format("Date: {0} \u2192 {1}",fmt.format(fromdate.getTime()), fmt.format(todate.getTime()));
        //c.drawText(tmpstr, 30*scale, 50*scale, paint);
        c.drawText(tmpstr, 30*scale, 75*scale, paint);


        getHeaderFooterValues();
        //List<String> items = Arrays.asList(HeaderValue.split("_&ABCD_EF_G&"));
        //for (int i = 0; i < items.size(); i ++) {

        //Header = "";
        //Header = HeaderValue.replace("_&ABCD_EF_G&", "\n")+"\n";
        Header = HeaderValue;
        //aa.add(HeaderValue.replace("_&ABCD_EF_G&", "\n"));
        //aa.add(HeaderValue);

        //}

        Footer = "";
        Footer += FooterValue.replace("_&ABCD_EF_G&", "\n");

        Log.i("sdf___","Header___"+Header);



        if(user!=null){

            tmpstr = user.getName();
            if(tmpstr.length()>32){
                tmpstr = tmpstr.substring(0, 32)+"...";
            }
            tmpstr = MessageFormat.format("User: {0}", tmpstr);
            c.drawText(tmpstr, 300*scale, 50*scale, paint);
        }

        if(refer!=null){
            if(refer.getID()!=-2){
                tmpstr = refer.getName();
                if(tmpstr.length()>32){
                    tmpstr = tmpstr.substring(0, 32)+"...";
                }
                tmpstr = MessageFormat.format("Refer: {0}", tmpstr);
                c.drawText(tmpstr, 500*scale, 50*scale, paint);
            }
        }

        tmpstr = "Page: "+pagenum+"/"+pagemax;
        c.drawText(tmpstr, c.getWidth()-(20*scale)-paint.measureText(tmpstr), 50*scale, paint);


        //paint.setColor(Color.BLACK);
        //paint.setTextSize(10*scale);
        paint.setTextSize(8*scale);

        //int widthcount1 = (int)(10*scale);

        //c.drawLine(10*scale, 10*scale, c.getWidth()-(10*scale), 10*scale, paint);
        //int widthcount1 = (int)(10*scale);
        //int widthcount1 = (int)(10*scale);

        //c.drawText("tmpstr", widthcount1 ,widthcount1  , paint);
        //c.drawText("    ", 10*scale ,10*scale  , paint);
        for (int i = 0 ; i < list1.size(); i++) {

//            if (i == 0){
//                c.drawText("testing", 10*scale ,15*scale * 1 , paint);
//            }
            //tmpstr = Header;
            tmpstr = (String) list1.get(i) ;
            Log.i("sdf___","tmpstr_"+tmpstr);
            //c.drawText(tmpstr, c.getWidth() - paint.measureText(tmpstr),paint.getTextSize(), paint);
            //c.drawText(tmpstr, (15 + 1 + i)* scale, (15 + 1 + i) * scale, paint);

            //c.drawText(tmpstr, 10*scale ,15*scale * (i+2) , paint);
            c.drawText(tmpstr, 10*scale ,15*scale * (i+1) , paint);
//            c.drawText(tmpstr, 10*scale ,(30*scale) + (i * 2), paint);
            //widthcount1 += paint.measureText(tmpstr);
        }
        //paint.setStrokeWidth(2);


        //c.drawLine(10*scale, 10*scale, c.getWidth(), 10*scale, paint);
        //c.drawText(tmpstr, 10*scale,c.getHeight(), paint);
        paint.setTypeface(boldtext);


        paint.setColor(Color.BLACK);
        paint.setTextSize(30*scale);
        tmpstr = title;
        c.drawText(tmpstr, c.getWidth()/2 - paint.measureText(tmpstr)/2,paint.getTextSize(), paint);
        paint.setStrokeWidth(2);


        //c.drawLine(10*scale, 35*scale, c.getWidth()-(10*scale), 35*scale, paint);
        c.drawLine(10*scale, 62*scale, c.getWidth()-(10*scale), 62*scale, paint);
        paint.setTypeface(boldtext);

        paint.setTextSize(8*scale);
        int widthcount = (int)(10*scale);
        for(int i=0;i<header.length;i++){
            tmpstr = header[i];
            //c.drawText(tmpstr, widthcount, 70*scale, paint);
            c.drawText(tmpstr, widthcount, 90*scale, paint);
            widthcount += paint.measureText(tmpstr);
        }

        paint.setStrokeWidth(2);

//        c.drawLine(10*scale, 57*scale, c.getWidth()-(10*scale), 57*scale, paint);
//        c.drawLine(10*scale, 77*scale, c.getWidth()-(10*scale), 77*scale, paint);
        c.drawLine(10*scale, 77*scale, c.getWidth()-(10*scale), 77*scale, paint);
        c.drawLine(10*scale, 97*scale, c.getWidth()-(10*scale), 97*scale, paint);



        for(int i=0;i<item.length;i++){
            //if(i==item.length-2 && pagenum == pagemax){
            if(item[i].isEmpty()){
                paint.setStrokeWidth(1);
                //c.drawLine(10*scale, 87*scale+(i*(paint.getTextSize()+1))-5, c.getWidth()-(10*scale), 87*scale+(i*(paint.getTextSize()+1))-5, paint);
                c.drawLine(10*scale, 107*scale+(i*(paint.getTextSize()+1))-5, c.getWidth()-(10*scale), 107*scale+(i*(paint.getTextSize()+1))-5, paint);
            }
            if((i-1)>=0 && i+1<item.length-1){
                if(item[i-1].isEmpty() && item[i+1].isEmpty()){
                    paint.setTypeface(boldtext);
                }else{
                    paint.setTypeface(normaltext);
                }
            }else{
                paint.setTypeface(normaltext);
            }

            if(i==item.length-1 && pagenum == pagemax){
                paint.setTypeface(boldtext);
            }
            //c.drawText(item[i], 10*scale, 88*scale+(i*(paint.getTextSize()+1)), paint);
            c.drawText(item[i], 10*scale, 108*scale+(i*(paint.getTextSize()+1)), paint);
        }

        paint.setTypeface(Typeface.DEFAULT);

        paint.setStrokeWidth(2);
        c.drawLine(10*scale, c.getHeight()-22*scale, c.getWidth()-10*scale, c.getHeight()-22*scale, paint);
        paint.setStrokeWidth(1);

        paint.setTypeface(Typeface.DEFAULT);

        paint.setTextSize(8*scale);

        tmpstr = Allocator.cashierName;
        if(tmpstr.length()>32){
            tmpstr = Allocator.cashierName.substring(0, 20) + "...";
        }

        tmpstr = MessageFormat.format("Report Generated On: {0}  {1}  (By: {2})", fmt.format(reportgentime.getTime()), fmttime.format(reportgentime.getTime()), tmpstr);
        //tmpstr = "Report Generated On: "+fmt.format(reportgentime.getTime())+"  "+fmttime.format(reportgentime.getTime());
        c.drawText(tmpstr, 20*scale,c.getHeight()-10*scale, paint);

        tmpstr = regID;
        c.drawText(regID, c.getWidth() - (20*scale + paint.measureText(tmpstr)) ,c.getHeight()-10*scale, paint);

        paint.setTextSize(10*scale);
        tmpstr = "Powered By MyRetailer POS";
        c.drawText(tmpstr, c.getWidth()/2 - paint.measureText(tmpstr)/2,c.getHeight()-10*scale, paint);
    }
    public static String HeaderValue = "";
    public static String FooterValue = "";
    public static String Options= "";
    public static String ImageStatus= "";
    public static String ImageLogo = "";
    public static String Header = "";
    public static String Footer = "";
    public static ArrayList<String> list = null;
    static List<String> list1 = new ArrayList<>();
    public static void getHeaderFooterValues() {
        Cursor c = DBFunc.Query("SELECT HeaderValue,FooterValue,Options,ImageStatus,DateTime,Logo FROM ReceiptEditor", true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
//                    HeaderValue = c.getString(0);
                   // if (c.getString(0).contains("_&ABCD_EF_G&")) {
                        aa.add(c.getString(0).split("_&ABCD_EF_G&"));
                   // }
                    HeaderValue = c.getString(0).replace("_&ABCD_EF_G&", "\n");
//                    list = (ArrayList<String>) Arrays.asList(c.getString(0).split("_&ABCD_EF_G&"));
//
//                    ArrayList<String> list = new ArrayList<>(Arrays.asList(c.getString(0).split("_&ABCD_EF_G&")));
//                    //ArrayList<String> list= Arrays.asList(Your_String.split("delimiter"));

                    list1 = (Arrays.asList(c.getString(0).split("_&ABCD_EF_G&")));

                    Log.i("list___","___"+list1);
                    Log.i("list___","___"+list);
                    Log.i("sdf___","___"+aa);
                    FooterValue = c.getString(1);
                    Options = String.valueOf(c.getInt(2));
                    ImageStatus = String.valueOf(c.getInt(3));
                    ImageLogo = String.valueOf(c.getInt(5));
                }
            }
            c.close();
        }
    }

    private List<ReferData> ListRefers(){
        List<ReferData> data = new ArrayList<ReferData>();
        Cursor c = DBFunc.Query("SELECT DISTINCT(ReferID) FROM Bill ORDER BY ReferID", false);
        if(c==null){//DB Error
            return null;
        }

        List<Integer> _tmpreferid = new ArrayList<Integer>();

        while(c.moveToNext()){
            if(c.isNull(0)){
                _tmpreferid.add(-1);
            }else{
                _tmpreferid.add(c.getInt(0));
            }
        }
        c.close();



        for(int id : _tmpreferid){
            if(id == -1){
                continue;
            }
            Cursor _tmp = DBFunc.Query("SELECT DISTINCT(Refer) FROM Bill WHERE ReferID = "+id, false);//get list of name

            if(_tmp==null){
                return null;
            }

            while(_tmp.moveToNext()){
                data.add(new ReferData(id,_tmp.getString(0)));
            }

            _tmp.close();
        }

        Collections.sort(data, new Comparator<ReferData> (){
            @Override
            public int compare(ReferData lhs, ReferData rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });

        data.add(0,new ReferData(-1,"(None)"));
        data.add(0,new ReferData(-2,"All"));
        return data;
    }

    private List<UserData> ListUsers(){
        List<UserData> data = new ArrayList<UserData>();


        Cursor c_user = DBFunc.Query("SELECT DISTINCT(user_id) FROM userlog ORDER BY user_id", false);
        if(c_user==null){//DB Error
            return null;
        }

        List<Integer> _tmpuserid = new ArrayList<Integer>();

        while(c_user.moveToNext()){
            _tmpuserid.add(c_user.getInt(0));
        }

        c_user.close();

        //Map<Integer,List<String>> data = new HashMap<Integer,List<String>>();
        for(int id : _tmpuserid){
            if(id == -1 && Allocator.cashierID != -1){
                continue;
            }
            Cursor _tmp = DBFunc.Query("SELECT DISTINCT(name) FROM userlog WHERE user_id = "+id, false);//get list of name

            if(_tmp==null){
                return null;
            }

            while(_tmp.moveToNext()){
                data.add(new UserData(id,_tmp.getString(0)));
            }

            _tmp.close();
        }

        _tmpuserid.clear();


        c_user = DBFunc.Query("SELECT DISTINCT(CashierID) FROM Bill ORDER BY CashierID", false);
        if(c_user==null){//DB Error
            return null;
        }

        while(c_user.moveToNext()){
            _tmpuserid.add(c_user.getInt(0));
        }

        c_user.close();

        for(int id : _tmpuserid){
            Cursor _tmp = DBFunc.Query("SELECT DISTINCT(Cashier) FROM Bill WHERE CashierID = "+id, false);//get list of name

            if(_tmp==null){
                return null;
            }

            while(_tmp.moveToNext()){
                boolean exist = false;

                for(UserData u:data){
                    if(u.id==id && u.getName().equals(_tmp.getString(0))){
                        exist = true;
                        break;
                    }
                }

                if(!exist){
                    data.add(new UserData(id,_tmp.getString(0)));
                }

            }

            _tmp.close();
        }

        _tmpuserid.clear();

        Collections.sort(data, new Comparator<UserData> (){
            @Override
            public int compare(UserData lhs, UserData rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });


        return data;
    }

    class ReferData{
        private int id = -1;
        private String name = "";
        public ReferData(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getID(){
            return id;
        }

        public String getName(){
            return name;
        }

        public String toString(){
            return name;
        }
    }

    class UserData{
        private int id = -1;
        private String name = "";
        public UserData(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getID(){
            return id;
        }

        public String getName(){
            return name;
        }

        public String toString(){
            return name;
        }
    }

    class UserLogData{
        private int id = -1;
        private String name = "";
        private Map<Date, String> log = new HashMap<Date, String>();
        public UserLogData(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getID(){
            return id;
        }

        public String getName(){
            return name;
        }

        public Map<Date, String> getLog(){
            return log;
        }

    }

}

