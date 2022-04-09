package com.dcs.myretailer.app.Setting;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityProductManagementBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductManagementActivity extends AppCompatActivity {
//    RecyclerView myrv = null;
    public static List<ProductData> lstProductData = new ArrayList<ProductData>();
    public static  RecyclerViewProductManagementAdapter myAdapter = null;
    //RecyclerViewNoImageAdapter myNoImageAdapter = null;
    //RecyclerNoImageViewProductManagementAdapter myNoImageAdapter = null;
    public static String str_product_management = "0";
//    private ActionModeCallback actionModeCallback = new ActionModeCallback();
//    private ActionMode actionMode;
    public static ActivityProductManagementBinding binding = null;
    SearchView searchView;
    SearchView.OnQueryTextListener queryTextListener;
    String searchValue = "";
    private ActionMode mActionMode;
    public static Context context;
    private static ProductManagementActivity xcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_management);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle(Constraints.ProductManagement);

        context = getApplicationContext();

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_product_management = "1";
                onBackPressed();
            }
        });

        updateProductManagementFun(searchValue);

//        lstProductData = Query.getProductAll("");
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
//        binding.productManagementRecyclerviewId.setLayoutManager(gridLayoutManager);
//
//        myAdapter = new RecyclerViewProductManagementAdapter(this,getApplicationContext(), lstProductData, "product_management");
//        binding.productManagementRecyclerviewId.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

//        @Override
//        public boolean onItemLongClicked(int position) {
//            if (actionMode != null) {
//                ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
//            }
//
//            toggleSelection(position);
//
//            return true;
//        }

//
//        myrv.setOnLongClickListener(new View.OnLongClickListener() {
//            // Called when the user long-clicks on someView
//            public boolean onLongClick(View view) {
//                if (actionMode != null) {
//                    return false;
//                }
//
//                // Start the CAB using the ActionMode.Callback defined above
////                actionMode = getActivity().startActionMode(ActionModeCallback);
//                actionMode =  startActionMode(actionModeCallback);
//                view.setSelected(true);
//                return true;
//            }
//        });

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 30;
            binding.productManagementRecyclerviewId.setLayoutParams(linearOverAllParams);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_new_product_actions, menu);
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


//        return super.onCreateOptionsMenu(menu);

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_product_management_actions, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_product_management_search);
        SearchManager searchManager = (SearchManager) ProductManagementActivity.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ProductManagementActivity.this.getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("__newText__","newText_produman__"+newText);
                    searchValue = newText;
                    updateProductManagementFun(searchValue);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //Toast t = Toast.makeText(ProductManagementActivity.this, "close", Toast.LENGTH_SHORT);
                    //t.show();
                    return false;
                }
            });
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean newViewFocus) {
                    if (!newViewFocus) {
                        //Collapse the action item.
                        searchItem.collapseActionView();
                        //Clear the filter/search query.
                    }
                }
            });

        }
        return super.onCreateOptionsMenu(menu);
    }

//    private class ActionModeCallback implements ActionMode.Callback {
//        @SuppressWarnings("unused")
//        private final String TAG = ActionModeCallback.class.getSimpleName();
//
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.getMenuInflater().inflate (R.menu.menu_cam, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.favorite:
//                    // TODO: actually remove items
//                    Log.d(TAG, "menu_remove");
//                    mode.finish();
//                    return true;
//
//                default:
//                    return false;
//            }
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            myAdapter.clearSelection();
//            actionMode = null;
//        }

//    }


//
//    //Contextual Action Bar
//    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
//
//        // Called when the action mode is created; startActionMode() was called
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            // Inflate a menu resource providing context menu items
//            MenuInflater inflater = mode.getMenuInflater();
//            inflater.inflate(R.menu.context_menu, menu);
//            return true;
//        }
//
//        // Called each time the action mode is shown. Always called after onCreateActionMode, but
//        // may be called multiple times if the mode is invalidated.
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false; // Return false if nothing is done
//        }
//
//        // Called when the user selects a contextual menu item
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.menu_share:
//                    shareCurrentItem();
//                    mode.finish(); // Action picked, so close the CAB
//                    return true;
//                default:
//                    return false;
//            }
//        }
//
//        // Called when the user exits the action mode
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            actionMode = null;
//        }
//    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            menu.findItem(R.id.action_add_new_product).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_new_product).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_add_new_product_actions, menu);
////        Log.i("dsdfdfdd___", String.valueOf(myAdapter.getSelectedItemCount()));
////        if (myAdapter.getSelectedItemCount() == 0) {
////            menu.findItem(R.id.action_delete_product).setVisible(false);
////        } else {
////            menu.findItem(R.id.action_delete_product).setVisible(true);
////        }
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        //return true;
//
//
//        return super.onCreateOptionsMenu(menu);
//    }

    public static ProductManagementActivity getContext() {
        return xcontext;
    }

    public void updateProductManagementFun(String searchValue) {
        lstProductData = Query.getProductAll(searchValue);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        Integer showPicCount = 3;
        if (device.equals("M2-Max")) {
            showPicCount = 4;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, showPicCount);
        binding.productManagementRecyclerviewId.setLayoutManager(gridLayoutManager);

//        if (lstProductData.size() > 0) {
//        myAdapter = new RecyclerViewProductManagementAdapter(getContext(),context, lstProductData, "product_management");
            myAdapter = new RecyclerViewProductManagementAdapter(ProductManagementActivity.this, context, lstProductData, "product_management");
            binding.productManagementRecyclerviewId.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_product) {
            //int latestID = findLatestID("PLU");
            String chkSubmitSalesOrNot = Query.GetOptions(22);
            if (chkSubmitSalesOrNot.equals("1")) {

            }else {
                Intent addNewProduct = new Intent(getApplicationContext(), AddNewProductActivity.class);
                addNewProduct.putExtra("ID", "null");
                startActivity(addNewProduct);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    static public int findLatestID(String tablename,Boolean flag) {
//        int latestID = 0;
//        Cursor c = DBFunc.Query("SELECT MAX(ID) FROM "+tablename, flag);
//        if (c != null) {
//            if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    latestID = c.getInt(0);
//                }
//            }
//            c.close();
//        }
//        return latestID;
//    }


    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(ProductManagementActivity.this);
        Intent i = new Intent(ProductManagementActivity.this,SettingActivity.class);
        startActivity(i);
        finish();//add 09112020
    }

//    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.getMenuInflater().inflate(R.menu.example_menu, menu);
//            mode.setTitle("Choose your option");
//            return true;
//        }
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.option_1:
//                    Toast.makeText(ProductManagementActivity.this, "Option 1 selected", Toast.LENGTH_SHORT).show();
//                    mode.finish();
//                    return true;
//                case R.id.option_2:
//                    Toast.makeText(ProductManagementActivity.this, "Option 2 selected", Toast.LENGTH_SHORT).show();
//                    mode.finish();
//                    return true;
//                default:
//                    return false;
//            }
//        }
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            mActionMode = null;
//        }
//    };
}
