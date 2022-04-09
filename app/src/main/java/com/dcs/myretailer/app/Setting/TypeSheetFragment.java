package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
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

import com.dcs.myretailer.app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class TypeSheetFragment extends BottomSheetDialogFragment {
    EditText edit_text_product_name1,price;
    ImageView addition,subtraction;
    private static int _counter = 45;
    private String _stringVal;
    TextView qun;
    public static Integer disID = 0;
    List<String> tax_names_list = new ArrayList<String>();
    List<Integer> tax_ID_list = new ArrayList<Integer>();
    Integer selected_tax_id = 0;
    String selected_tax_name = "0";
    public TypeSheetFragment() {
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
        return inflater.inflate(R.layout.fragment_type_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_tax_list);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        tax_ID_list.add(1);
        tax_ID_list.add(2);
        tax_names_list.add("Discount");
        tax_names_list.add("Voucher");
        RecyclerView.Adapter mListadapter = new ListAdapter(getContext(), tax_names_list,tax_ID_list);
        mRecyclerView.setAdapter(mListadapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<TypeSheetFragment.ListAdapter.ViewHolder> {
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

                        break;
                }
            }
        }

        @Override
        public TypeSheetFragment.ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_item, parent, false);

            TypeSheetFragment.ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tax_type_name.setText(dataList.get(position));
            holder.tax_type_layout.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    selected_tax_id = tax_id_list.get(position);
                    selected_tax_name = tax_names_list.get(position);
                    dismiss();
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        onResume();

        AddNewVouchersAndDiscountActivity.updateTypeValue(disID,selected_tax_id,selected_tax_name);

        super.onDismiss(dialog);

    }
}
