package com.dcs.myretailer.app.Setting;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.DataNoteImformation;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityCategoryManagementBinding;
import com.dcs.myretailer.app.databinding.RecyclerviewItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagementActivity extends AppCompatActivity {
    static List<DataNoteImformation> lstBook = new ArrayList<DataNoteImformation>();
    private static ListAdapter mListadapter;
    public static ActivityCategoryManagementBinding binding = null;
    public static RecyclerviewItemBinding adapterBinding = null;
    public static Context mcontext;
    public static String str_newText = "";
    SearchView searchView;
    SearchView.OnQueryTextListener queryTextListener;
    String searchValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_category_management);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Category Management");

        mcontext = this;

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        final LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.recyclerViewCategoriesList.setLayoutManager(layoutManager);

        getCategoryAll("");
//        if (lstBook.size() > 0) {
            mListadapter = new ListAdapter(mcontext, lstBook);
            binding.recyclerViewCategoriesList.setAdapter(mListadapter);
            binding.recyclerViewCategoriesList.setLayoutManager(new LinearLayoutManager(mcontext));
            binding.recyclerViewCategoriesList.addItemDecoration(new
                    DividerItemDecoration(mcontext,
                    DividerItemDecoration.VERTICAL));
//        }

    }

    public static void categoryFun(String searchValue) {
        getCategoryAll(searchValue);
//        if (lstBook.size() > 0) {
            mListadapter = new ListAdapter(mcontext, lstBook);
            binding.recyclerViewCategoriesList.setAdapter(mListadapter);
            //getBillAll();
            //        recyclerView.setAdapter(adapter);
            binding.recyclerViewCategoriesList.setLayoutManager(new LinearLayoutManager(mcontext));
            binding.recyclerViewCategoriesList.addItemDecoration(new
                    DividerItemDecoration(mcontext,
                    DividerItemDecoration.VERTICAL));
//        }
    }


    @Override
    protected void onResume() {
        mcontext = this;
        categoryFun(searchValue);
        super.onResume();
    }

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        public static List<DataNoteImformation> dataList;
        //public static Context mContext ;
        public ListAdapter(Context mContext,List<DataNoteImformation> data) {
            //mContext = mContext;
            dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerviewItemBinding adapterBinding;

            public ViewHolder(RecyclerviewItemBinding adapterBinding) {
                super(adapterBinding.getRoot());
                this.adapterBinding = (RecyclerviewItemBinding) adapterBinding;
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            adapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item,
                    parent, false);
            return new ViewHolder(adapterBinding);

//            View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontentstorechat,parent,false);
//            ViewHolder viewHolder = new ViewHolder(rowView);
//
//            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
            String hideImage = Query.GetOptions(20);
            if (hideImage.equals("1")){
                holder.adapterBinding.categoryImage.setVisibility(View.GONE);
            }else {
                holder.adapterBinding.categoryImage.setVisibility(View.VISIBLE);
                if (dataList.get(position).getImage() == null || dataList.get(position).getImage().equals("0") || dataList.get(position).getImage().isEmpty()){

                    Picasso.with(mcontext).load(R.drawable.default_no_image).into(holder.adapterBinding.categoryImage);
                }else {
                    Picasso.with(mcontext).load(dataList.get(position).getImage()).into(holder.adapterBinding.categoryImage);

                }
            }

            holder.adapterBinding.categoryText.setText(dataList.get(position).getName());

            String chkSubmitSalesOrNot = Query.GetOptions(22);
            if (chkSubmitSalesOrNot.equals("0")) {
                holder.adapterBinding.dropdownmark.setVisibility(View.VISIBLE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();
                        Integer selectedID = dataList.get(position).getID();

                        Intent intent = new Intent(mcontext, AddNewCategoryActivity.class);
                        intent.putExtra("ID", String.valueOf(selectedID));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);
                        ((Activity)mcontext).finish();
                    }
                });
            }else {
                holder.adapterBinding.dropdownmark.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    private static void getCategoryAll(String searchValue) {
        String chk = "";
        if (searchValue != null && searchValue.length() > 0 ){
            chk = " WHERE Name LIKE '%" + searchValue.toUpperCase() + "%'";
        }
        String sql = "SELECT ID,Name,Image FROM Category " + chk;
        Log.i("sql___","sql___"+sql);
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            lstBook.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    lstBook.add(new DataNoteImformation(c.getInt(0),c.getString(1),c.getString(2)));
                 }
            }
            c.close();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String chkSubmitSalesOrNot = Query.GetOptions(22);
        if (chkSubmitSalesOrNot.equals("1")) {
            menu.findItem(R.id.action_add_new_category).setVisible(false);
        }else {
            menu.findItem(R.id.action_add_new_category).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_new_category_actions, menu);
        //inflater.inflate(R.menu.activity_add_new_product_actions, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_category_management_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    searchValue = newText;
                    categoryFun(searchValue);
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
                    //Toast t = Toast.makeText(this, "close", Toast.LENGTH_SHORT);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_category) {
            Intent addNewCategory = new Intent(getApplicationContext(),AddNewCategoryActivity.class);
            addNewCategory.putExtra("ID","null");
            startActivity(addNewCategory);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(CategoryManagementActivity.this);
        Intent i = new Intent(CategoryManagementActivity.this,SettingActivity.class);
        startActivity(i);
        finish();
    }
}
