package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.R;

import java.util.ArrayList;
import java.util.List;

public class TaxTypeSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText edit_text_product_name1,price;
    ImageView addition,subtraction;
    private static int _counter = 45;
    private String _stringVal;
    TextView qun;
    List<String> tax_names_list = new ArrayList<String>();
    List<Integer> tax_ID_list = new ArrayList<Integer>();
    public static Integer selected_tax_id = 0;
    public static String selected_tax_name = "0";
    public TaxTypeSheetFragment() {
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
        return inflater.inflate(R.layout.fragment_tax_type_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
        linear_close.setOnClickListener(this);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_tax_list);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        getTaxTypes();
        RecyclerView.Adapter mListadapter = new ListAdapter(getContext(), tax_names_list,tax_ID_list);
        mRecyclerView.setAdapter(mListadapter);
    }
    private void getTaxTypes() {
        Cursor c = DBFunc.Query("SELECT ID,Name FROM TaxType ", true);
        if (c != null) {
            tax_ID_list.clear();
            tax_names_list.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    //taxetypes.add(new TaxType(c.getInt(0),c.getString(1),c.getInt(2)));
                    tax_ID_list.add(c.getInt(0));
                    tax_names_list.add(c.getString(1));
                }
            }
            c.close();
        }

    }
    public class ListAdapter extends RecyclerView.Adapter<TaxTypeSheetFragment.ListAdapter.ViewHolder> {
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
                this.tax_type_name = (TextView) itemView.findViewById(R.id.tax_type_name);
                this.tax_type_layout = (LinearLayout) itemView.findViewById(R.id.tax_type_layout);
                //tax_type_layout.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tax_type_layout:

                        break;
                }
            }
        }

        @Override
        public TaxTypeSheetFragment.ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taxtype_item, parent, false);

            TaxTypeSheetFragment.ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tax_type_name.setText(dataList.get(position));
            holder.tax_type_layout.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();

                    selected_tax_id = tax_id_list.get(position);
                    selected_tax_name = tax_names_list.get(position);

                    AddTaxConfigurationActivity.St = "1";
                    //Toast.makeText(v.getContext(), "selectedID " + String.valueOf(tax_names_list.get(position)), Toast.LENGTH_SHORT).show();
                    dismiss();
                    //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
//                    mContext.finish();
//                    Intent intent = new Intent(mContext, AddNewCategoryActivity.class);
//                    intent.putExtra("ID", String.valueOf(selectedID));
//                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_close:
                dismiss();
                break;
            case R.id.btn_add:
//                Bitmap bitmap = drawable.getBitmap();
//
//                DBFunc.ExecQuery("INSERT INTO PLU (Name,DeptID,Price,Option,Code,Image,ProductVariant,ProductModifiers,AllowNameQuickEdit,AllowPriceQuickEdit,DateTime,Condi_Seq) " +
//                        "VALUES ('" + DBFunc.PurifyString(edit_text_product_name1.getText().toString()) + "'," +
//                        "" + 0 + "," +
//                        price.getText().toString() + ",'" +
//                        00000 + "',''," +
//                        "'" + DBFunc.PurifyString(BitMapToString(bitmap)) + "'," +
//                        "'" + DBFunc.PurifyString(String.valueOf(product_variant_id)) + "'," +
//                        "'" + DBFunc.PurifyString(String.valueOf(product_modifier_id)) + "'," +
//                        "" + Integer.parseInt(str_checked_name_quick_edit) + "," +
//                        "" + Integer.parseInt(str_checked_price_quick_edit) + "," +
//                        "" + System.currentTimeMillis() + "," +
//                        000 + ")", true);

                break;
            case R.id.addition:
                _counter++;
                _stringVal = Integer.toString(_counter);
                qun.setText(_stringVal);
                break;
            case R.id.subtraction:
                _counter--;
                if (_counter > 0 ){
                    _counter = 1;
                }
                _stringVal = Integer.toString(_counter);
                qun.setText(_stringVal);
                break;
        }
    }
}
