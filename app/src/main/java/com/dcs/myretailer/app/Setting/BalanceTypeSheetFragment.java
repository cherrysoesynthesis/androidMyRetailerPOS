package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dcs.myretailer.app.Activity.PosConfigurationActivity;
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

import com.dcs.myretailer.app.R;

import java.util.ArrayList;
import java.util.List;

public class BalanceTypeSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText edit_text_product_name1,price;
    ImageView addition,subtraction;
    TextView qun;
    List<String> balance_type_names_list = new ArrayList<String>();
    List<Integer>  balance_type_ID_list = new ArrayList<Integer>();
    public BalanceTypeSheetFragment() {
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
        return inflater.inflate(R.layout.fragment_balance_type_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
        linear_close.setOnClickListener(this);


        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_product_cateogry_list);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        balance_type_names_list.add(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 30));
        balance_type_names_list.add(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 31));
        balance_type_names_list.add(StrTextConst.GetText(StrTextConst.TextType.CFGPOS, 32));
        balance_type_ID_list.add(0);
        balance_type_ID_list.add(1);
        balance_type_ID_list.add(2);
        RecyclerView.Adapter mListadapter = new ListAdapter(getContext(),  balance_type_names_list, balance_type_ID_list);
        mRecyclerView.setAdapter(mListadapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private List<String> balance_type_names;
        private List<Integer> balance_type_id;
        private Context mContext ;
        public ListAdapter(Context mContext,List<String> balance_type_names, List<Integer> balance_type_id)
        {
            this.mContext = mContext;
            this.balance_type_names = balance_type_names;
            this.balance_type_id = balance_type_id;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tax_type_name;
            LinearLayout tax_type_layout;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.tax_type_name = (TextView) itemView.findViewById(R.id.tax_type_name);
                this.tax_type_layout = (LinearLayout) itemView.findViewById(R.id.tax_type_layout);
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
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taxtype_item, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, int position) {
            holder.tax_type_name.setText(balance_type_names.get(position));
            holder.tax_type_layout.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Log.i("selectedID", String.valueOf(balance_type_id.get(position)));
                    PosConfigurationActivity.selected_balance_type_id = balance_type_id.get(position);
                    PosConfigurationActivity.selected_balance_type_name =  balance_type_names.get(position);
                    Log.i("selectedID__", String.valueOf( balance_type_id.get(position)));
                    Log.i("selectedName__", String.valueOf( balance_type_id.get(position)));

                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return balance_type_id.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_close:
                dismiss();
                break;
        }
    }
}

