package com.dcs.myretailer.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.PaymentTypeListRecyclerViewAdapter;
import com.dcs.myretailer.app.Setting.PaymentTypes;
import com.dcs.myretailer.app.databinding.ActivityPaymentListBinding;

import java.util.ArrayList;
import java.util.List;

public class PaymentListActivity extends AppCompatActivity implements PaymentTypeListRecyclerViewAdapter.ItemClickListener {
    ListView paymentListView;
    PaymentListActivity context;
    public static ArrayList<Integer> PaymentTypeID = new ArrayList<Integer>();
    public static ArrayList<String> PaymentTypeName = new ArrayList<String>();
    public static ArrayList<Integer> PaymentTypeOnOff = new ArrayList<Integer>();
    List<PaymentTypes> lstPaymentType= new ArrayList<PaymentTypes>();
    ListView reportProductListView;
    ListView simpleList;
    PaymentTypeListRecyclerViewAdapter padapter;
    ActivityPaymentListBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_payment_list);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Payment Type");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        context = PaymentListActivity.this;

        ScreenDisplayFun();

        getPaymentAll();

//        //simpleList = (ListView) findViewById(R.id.simpleListView);
//
//        PaymentTypesAdapter customAdapter = new PaymentTypesAdapter(getApplicationContext(), PaymentTypeName, PaymentTypeOnOff);
//        binding.simpleListView.setAdapter(customAdapter);
////        CustomAdapterPaymentType customAdapter = new CustomAdapterPaymentType(getApplicationContext(), PaymentTypeName, PaymentTypeOnOff,PaymentTypeID);
////        listView.setAdapter(customAdapter);


//        binding.simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(context, "dsfafdf"+PaymentTypeID.get(position).toString(), Toast.LENGTH_SHORT).show();
//
//                //finish();
//                Intent addTaxConfiguration = new Intent(context,AddPaymentTypeActivity.class);
//                addTaxConfiguration.putExtra("ID",PaymentTypeID.get(position).toString());
//                startActivity(addTaxConfiguration);
//                }
//            });
//        ArrayList<String> animalNames = new ArrayList<String>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");

        // set up the RecyclerView
        //RecyclerView recyclerView = findViewById(R.id.rvPaymentType);
        binding.rvPaymentType.setLayoutManager(new LinearLayoutManager(this));
        padapter = new PaymentTypeListRecyclerViewAdapter(getApplicationContext(), PaymentTypeName, PaymentTypeOnOff);
        padapter.setClickListener(this);
        binding.rvPaymentType.setAdapter(padapter);

        binding.rvPaymentType.addItemDecoration(new
                DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        paymentListView = (ListView) findViewById(R.id.paymentListView);
//        //DiscountAdapter customAdapter = new DiscountAdapter(this, discounts);
//        PaymentTypesAdapter customAdapter = new PaymentTypesAdapter(this, PaymentTypeName, PaymentTypeOnOff,PaymentTypeID);
//        paymentListView.setAdapter(customAdapter);
//        paymentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "DSAFDSF"+position, Toast.LENGTH_SHORT).show();
////                Intent i = new Intent(getActivity(), DiscussAddValu.class);
////                startActivity(i);
//            }
//        });
        //paymentListView.setItemsCanFocus(false);
//        paymentListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
//                 Toast.makeText(context, "_testposition ", Toast.LENGTH_SHORT).show();
//             }
//            });
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, PaymentTypeName);
//        paymentListView.setAdapter(arrayAdapter);
//        paymentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                                         @Override
//                                                         public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
//                                                             // TODO Auto-generated method stub
//                                                             //TextView v = (TextView) view.findViewById(R.id.txtLstItem);
//                                                             Toast.makeText(getApplicationContext(), "selected Item Name is " + "dsfdsaf", Toast.LENGTH_LONG).show();
//                                                         }
//                                                     }
//        );
//////        paymentListView.setOnClickListener(this);
//////        paymentListView.setOnItemClickListener(this);
//        ListView listView = (ListView) findViewById(R.id.listview);
//        String[] players = new String[] {"CR7", "Messi", "Hazard", "Neymar"};
//        List<String> Players_list = new ArrayList<String>(Arrays.asList(players));
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Players_list);
//        listView.setAdapter(arrayAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = (String) parent.getItemAtPosition(position);
//                Toast.makeText(context, "The best football player is : " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void ScreenDisplayFun() {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//        if (terminalTypeVal.equals(Constraints.PAX)) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(750,
//                    android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//
//            binding.paymentLay.setLayoutParams(params);
//        }
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + padapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//        Log.i("ffd_Paymedf__)", String.valueOf(PaymentTypeID.get(position).toString()));
//        //finish();
        Intent addTaxConfiguration = new Intent(context,AddPaymentTypeActivity.class);
        addTaxConfiguration.putExtra("ID",PaymentTypeID.get(position).toString());
        startActivity(addTaxConfiguration);
    }
    private void getPaymentAll() {
        Cursor c = DBFunc.Query("SELECT ID,Name,Amount,Option,SwitchSTATUS FROM Payment ", true);
        if (c != null) {
            PaymentTypeID.clear();
            PaymentTypeName.clear();
            PaymentTypeOnOff.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    PaymentTypeID.add(c.getInt(0));
                    PaymentTypeName.add(c.getString(1));
                    PaymentTypeOnOff.add(c.getInt(4));
//                    lstPaymentType.add(new PaymentTypes(c.getString(1),c.getInt(4)));
                }
            }
            c.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_new_payment_types_actions, menu);
//        Log.i("dsdfdfdd___", String.valueOf(myAdapter.getSelectedItemCount()));
//        if (myAdapter.getSelectedItemCount() == 0) {
//            menu.findItem(R.id.action_delete_product).setVisible(false);
//        } else {
//            menu.findItem(R.id.action_delete_product).setVisible(true);
//        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //return true;


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            menu.findItem(R.id.action_add_new_payment).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_new_payment).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_payment) {
            Intent addTaxConfiguration = new Intent(context,AddPaymentTypeActivity.class);
            addTaxConfiguration.putExtra("ID","null");
            startActivity(addTaxConfiguration);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//        //Log.i("_testposition",position+"_"+discounts.get(position).getDiscount_ID());
//        Toast.makeText(this, "_testposition"+position+"_", Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(context,AddNewVouchersAndDiscountActivity.class);
//        i.putExtra("ID","null");
//        startActivity(i);
//    }
//
//    @Override
//    public void onClick(View v) {
//        Toast.makeText(this, "_testposition ", Toast.LENGTH_SHORT).show();
//    }

//    private class CustomAdapterPaymentType extends BaseAdapter {
//        private final Context context;
//        private final ArrayList<String> paymentTypeName;
//        private final ArrayList<Integer> paymentTypeOnOff;
//        private final ArrayList<Integer> paymentTypeID;
//        public CustomAdapterPaymentType(Context context, ArrayList<String> paymentTypeName, ArrayList<Integer> paymentTypeOnOff, ArrayList<Integer> paymentTypeID) {
//            this.context = context;
//            this.paymentTypeName = paymentTypeName;
//            this.paymentTypeOnOff = paymentTypeOnOff;
//            this.paymentTypeID = paymentTypeID;
//        }
//
//        @Override
//        public int getCount() {
//            return paymentTypeName.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup viewGroup) {
////        View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
////        //getting view in row_data
////        TextView name = view1.findViewById(R.id.fruits);
////        ImageView image = view1.findViewById(R.id.images);
////
////        name.setText(fruitNames[i]);
////        image.setImageResource(fruitImages[i]);
//            View view1 = getLayoutInflater().inflate(R.layout.activity_product_types_listview, null);
//            TextView paymenttype_name = view.findViewById(R.id.paymenttype_name);
//            Switch paymenttype_onoff = view.findViewById(R.id.paymenttype_onoff);
//            paymenttype_name.setText(paymentTypeName.get(position));
//            if (paymentTypeOnOff.get(position) == 1){
//                paymenttype_onoff.setChecked(true);
//            }else{
//                paymenttype_onoff.setChecked(false);
//            }
//            return view1;
//        }
//    }


    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(PaymentListActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}

