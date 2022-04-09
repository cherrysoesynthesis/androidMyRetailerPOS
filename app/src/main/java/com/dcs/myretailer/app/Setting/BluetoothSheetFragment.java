package com.dcs.myretailer.app.Setting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
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
import android.widget.Toast;

import com.dcs.myretailer.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText edit_text_product_name1,price;
    ImageView addition,subtraction;
    private static int _counter = 45;
    private String _stringVal;
    TextView qun;
    List<String> product_cateogry_names_list = new ArrayList<String>();
    List<Integer>  product_cateogry_ID_list = new ArrayList<Integer>();
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    public BluetoothSheetFragment() {
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
        return inflater.inflate(R.layout.fragment_bluetooth_sheet_dialog, container, false);
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

        getTaxTypes();
        RecyclerView.Adapter mListadapter = new ListAdapter(getContext(),  product_cateogry_names_list, product_cateogry_ID_list);
        mRecyclerView.setAdapter(mListadapter);
    }
    private void getTaxTypes() {
//        Cursor c = DBFunc.Query("SELECT ID,Name FROM Category ", true);
//        if (c != null) {
        product_cateogry_ID_list.clear();
        product_cateogry_names_list.clear();
        if(bAdapter==null){
            Toast.makeText(getContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
        }
        else{
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
            int count = 0;
            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    count ++;
//                    devicename = device.getName();
//                    macAddress = device.getAddress();
                    //list.add("Name: "+devicename+"MAC Address: "+macAddress);
                    product_cateogry_ID_list.add(count);
                    product_cateogry_names_list.add(device.getName());
                }
                //Log.i("devicename:::",String.valueOf(devicename));
                //Log.i("macAddress:::",String.valueOf(macAddress));
                //Log.i("list_:::",String.valueOf(list));
//                 lstvw = (ListView) findViewById(R.id.deviceList);
//                 aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
//                 lstvw.setAdapter(aAdapter);
            }
        }
    }
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private List<String> product_cateogry_names;
        private List<Integer> product_cateogry_id;
        private Context mContext ;
        public ListAdapter(Context mContext,List<String> product_cateogry_names, List<Integer> product_cateogry_id)
        {
            this.mContext = mContext;
            this.product_cateogry_names = product_cateogry_names;
            this.product_cateogry_id = product_cateogry_id;
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
            holder.tax_type_name.setText(product_cateogry_names_list.get(position));
            holder.tax_type_layout.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Log.i("selectedID", String.valueOf(product_cateogry_id.get(position)));
                    PosConfigurationActivity.selected_bluetooth_id = product_cateogry_id.get(position);
                    PosConfigurationActivity.selected_bluetooth_name =  product_cateogry_names.get(position);
                    Log.i("selectedID__", String.valueOf( product_cateogry_id.get(position)));
                    Log.i("selectedName__", String.valueOf( product_cateogry_names.get(position)));

                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return product_cateogry_id.size();
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
