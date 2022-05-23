package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PreviousReportFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText edit_text_product_name1,price;
    ImageView addition,subtraction;
    private static int _counter = 45;
    private String _stringVal;
    TextView qun;
    List<Long> previousList = new ArrayList<Long>();
    List<String> tax_names_list = new ArrayList<String>();
    List<Integer> tax_ID_list = new ArrayList<Integer>();
    public static Integer selected_tax_id = 0;
    public static String selected_tax_name = "0";
    public static long selected_previousname = 0;
    public static String previous_start_d = "";
    public static String previous_end_d = "";
    public PreviousReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_report_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_tax_list);

        String device = Query.GetDeviceData(Constraints.DEVICE);

//        if (device.equals("M2-Max")) {
//
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//            params.gravity = Gravity.CENTER;
//            LinearLayout layoverall = (LinearLayout) view.findViewById(R.id.Layoverall);
//            layoverall.setLayoutParams(params);
//        }


        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close_previous_report);
        linear_close.setOnClickListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        tax_ID_list.add(1);
//        tax_ID_list.add(2);
//        tax_names_list.add("Discount");
//        tax_names_list.add("Voucher");

        //Cursor c = DBFunc.Query("SELECT ID, ClosingTime FROM ClosingPeriod WHERE ClosingTime BETWEEN " + _tmpfrom.getTimeInMillis() + " AND " + _tmpto.getTimeInMillis() + " ORDER BY ID ASC", false);
        //Cursor c = DBFunc.Query("SELECT ID, ClosingTime FROM ClosingPeriod ORDER BY ID ASC", false);
        //Cursor c = DBFunc.Query("SELECT ID, ClosingTime FROM ZClosing ORDER BY ID ASC", false);
//        String sqldatepreviousql = "SELECT ID,ClosingTime,strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch') FROM ZClosing " +
//                " WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch') BETWEEN '"+
//                ReportDateSheetFragment.start_date +"' AND '"+ReportDateSheetFragment.end_date+"' "+
//        String sqldatepreviousql = "SELECT ID,ClosingTime,strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch') FROM ZClosing " +
//                " WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch') BETWEEN '"+
//                ReportDateSheetFragment.edit_text_starting.getText().toString() +"' AND '"+
//                ReportDateSheetFragment.edit_text_ending.getText().toString()+"' "+
//                "ORDER BY ID ASC";
//        String sqldatepreviousql = "SELECT ID,ClosingTime,strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch') FROM ZClosing " +
//                " WHERE strftime('"+Constraints.sqldateformat+"', OpeningTime / 1000, 'unixepoch') = '"+
//                ReportDateSheetFragment.edit_text_starting.getText().toString() +"' " +
//                "AND strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+
//                ReportDateSheetFragment.edit_text_ending.getText().toString() +"' " +
////                "AND '"+
////                ReportDateSheetFragment.edit_text_ending.getText().toString()+"' "+
//                "ORDER BY ID ASC";
        String sqldatepreviousql = "SELECT ID,ClosingTime,strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch')," +
                "OpeningTime FROM ZClosing " +
//                "WHERE strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+ReportDateSheetFragment.edit_text_ending.getText().toString()+"' " +
                "WHERE " +
//                "strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+ReportDateSheetFragment.edit_text_starting.getText().toString()+"' " +
//                "AND " +
                "strftime('"+Constraints.sqldateformat+"', " +
//                "OpeningTime / 1000, 'unixepoch') Between '"+
                "DateTime / 1000 + (3600*8), 'unixepoch') Between '"+
                ReportDateSheetFragment.binding.editTextStarting.getText().toString()+"' " +
                "AND '"+ReportDateSheetFragment.binding.editTextEnding.getText().toString()+"'  " +
                "ORDER BY ID DESC";

        Cursor c = DBFunc.Query(sqldatepreviousql, false);
        if (c != null) {

            //String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
            final Calendar myCalendar2 = Calendar.getInstance();
            String todayd = sdf.format(myCalendar2.getTime());
            Log.i("todayd__","todayd___"+todayd+"__"+ReportDateSheetFragment.start_date);
            if (c.getCount() != 0) {
//                if (c.moveToNext()) {
                    //if (todayd.equals(ReportDateSheetFragment.start_date)) {
//                    if (todayd.equals(ReportActivity.start)) {
//                        tax_ID_list.add(-1);
//                        tax_names_list.add("Now");
//                        previousList.add((long) -1);
//                    }else {
                        tax_ID_list.add(-2);
                        tax_names_list.add("All");
                        previousList.add((long) -2);

                        tax_ID_list.add(-1);
                        tax_names_list.add("Now");
                        previousList.add((long) -1);
//                    }
//                }
            }else {
                if (todayd.equals(ReportDateSheetFragment.start_date)) {
                    tax_ID_list.add(-1);
                    tax_names_list.add("Now");
                    previousList.add((long) -1);
                }
            }
            while (c.moveToNext()) {
//                rpt_periodID.add(c.getInt(0));
                previousList.add(c.getLong(1));
//
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(c.getLong(1));
                final SimpleDateFormat fmttime = new SimpleDateFormat(Constraints.dateYMD+" HH:mm.ss");
                //final SimpleDateFormat fmttime = new SimpleDateFormat("yyyy/MM/dd HH:mm.ss");
                Calendar cal2 = Calendar.getInstance();
                cal2.setTimeInMillis(c.getLong(3));
                final SimpleDateFormat fmttime2 = new SimpleDateFormat(Constraints.dateYMD+" HH:mm.ss");
                //final SimpleDateFormat fmttime2 = new SimpleDateFormat("yyyy/MM/dd HH:mm.ss");

                tax_ID_list.add(c.getInt(0));
                tax_names_list.add(fmttime2.format(cal2.getTime())+"  -  "+fmttime.format(cal.getTime()));
            }
            c.close();
        }
        RecyclerView.Adapter mListadapter = new PreviousReportFragment.ListAdapter(getContext(), tax_names_list,tax_ID_list);
        mRecyclerView.setAdapter(mListadapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.linear_close_previous_report:
                dismiss();

////                        ReportDateSheetFragment rdSheetFragment = new ReportDateSheetFragment();
////                        rdSheetFragment.show(getSupportFragmentManager(), rdSheetFragment.getTag());
//
//                ReportDateSheetFragment fragment1 = new ReportDateSheetFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(android.R.id.content, fragment1);
//                fragmentTransaction.commit();

//                previous_start_d = ReportDateSheetFragment.start_date;
//                previous_end_d = ReportDateSheetFragment.end_date;

//                ReportDateSheetFragment.edit_text_starting.setText(ReportDateSheetFragment.start_date);
//                ReportDateSheetFragment.edit_text_ending.setText(ReportDateSheetFragment.end_date);

                ReportDateSheetFragment rdSheetFragment = new ReportDateSheetFragment();
                rdSheetFragment.show(getFragmentManager(), rdSheetFragment.getTag());
                break;
        }
    }

    public class ListAdapter extends RecyclerView.Adapter<PreviousReportFragment.ListAdapter.ViewHolder> {
        private List<String> dataList;
        private List<Integer> tax_id_list;
        private Context mContext ;
        public ListAdapter(Context mContext,List<String> data, List<Integer> tax_id_list) {
            this.mContext = mContext;
            this.dataList = data;
            this.tax_id_list = tax_id_list;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tax_type_name;
            LinearLayout tax_type_layout;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tax_type_name = (TextView) itemView.findViewById(R.id.type_name);
                this.tax_type_layout = (LinearLayout) itemView.findViewById(R.id.tax_type_layout);
                //tax_type_layout.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tax_type_layout:
//                        ReportDateSheetFragment rdSheetFragment = new ReportDateSheetFragment();
//                        rdSheetFragment.show(getFragmentManager(), rdSheetFragment.getTag());
                        break;
                }
            }
        }

        @Override
        public PreviousReportFragment.ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_item, parent, false);

            PreviousReportFragment.ListAdapter.ViewHolder viewHolder = new PreviousReportFragment.ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final PreviousReportFragment.ListAdapter.ViewHolder holder, int position) {
            holder.tax_type_name.setText(dataList.get(position));
            holder.tax_type_layout.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    selected_tax_id = tax_id_list.get(position);
                    selected_tax_name = tax_names_list.get(position);
                    selected_previousname = previousList.get(position);
//                    dismiss();
                    previous_start_d = ReportDateSheetFragment.binding.editTextStarting.getText().toString();
                    previous_end_d = ReportDateSheetFragment.binding.editTextEnding.getText().toString();

//                    previous_start_d = ReportDateSheetFragment.start_date;
//                    previous_end_d = ReportDateSheetFragment.end_date;

                    ReportActivity.start = previous_start_d;
                    ReportActivity.end = previous_end_d;

//                    Log.i("DF__","DDstart_date111___"+ReportDateSheetFragment.start_date);
//                    Log.i("DF__","DDend_date211___"+ReportDateSheetFragment.end_date);
//                    ReportDateSheetFragment.edit_text_starting.setText(ReportDateSheetFragment.start_date);
//                    ReportDateSheetFragment.edit_text_ending.setText(ReportDateSheetFragment.end_date);

                    ReportDateSheetFragment.binding.editTextStarting.setText(previous_start_d);
                    ReportDateSheetFragment.binding.editTextEnding.setText(previous_end_d);


                    dismiss();
                    ReportDateSheetFragment rdSheetFragment = new ReportDateSheetFragment();
                    rdSheetFragment.show(getFragmentManager(), rdSheetFragment.getTag());

                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }
}
