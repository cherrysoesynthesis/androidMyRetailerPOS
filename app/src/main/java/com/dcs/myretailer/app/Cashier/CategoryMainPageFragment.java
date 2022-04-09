package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.BillList.BillListModel;
import com.dcs.myretailer.app.DataNoteImformation;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.CategoryMainPageListBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryMainPageFragment extends Fragment {
    private static final String TAG = "Tab2";
    static List<DataNoteImformation> lstBook = new ArrayList<DataNoteImformation>();
    public static List<ProductData> lstProductData2 = new ArrayList<ProductData>();
    public static String str_newText = "";
    private static ListAdapter mListadapter;
    public static Integer productID = 0;
//    public static String productName = "0";
    public static String St = "0";
    public CategoryMainPageFragment() {}
    public static String category_name = "0";
    Handler mHandler;
    public static String status_on = "0";
    static CategoryMainPageListBinding binding = null;
    public static Context mcontext;
    static String terminalTypeVal = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.category_main_page_list, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.category_main_page_list, container, false);
        View view = binding.getRoot();

        mcontext = getContext();

       terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);

        MainPageCategoryRefreshFun();

        return view;
    }

    public static void MainPageCategoryRefreshFun() {

        new AsyncTaskupdateCategoryMainFun(mcontext,binding).execute(terminalTypeVal);
    }


    public static class AsyncTaskupdateCategoryMainFun extends AsyncTask<Object, ImageView, Void> {
        Context mcontext = null;
        CategoryMainPageListBinding mbinding = null;
        List<BillListModel> lstBill = new ArrayList<BillListModel>();
        public AsyncTaskupdateCategoryMainFun(Context appContext, CategoryMainPageListBinding binding) {
            mcontext = appContext;
            mbinding = binding;
        }

        protected Void doInBackground(Object... params) {

            getCategoryAll();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


            final LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mbinding.recyclerViewCategoriesList.setLayoutManager(layoutManager);

//        if (lstBook.size() > 0) {
            mListadapter = new ListAdapter(mcontext, lstBook);
            mbinding.recyclerViewCategoriesList.setAdapter(mListadapter);
//        }
        }

        @Override
        protected void onPreExecute() {

            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(540,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams.leftMargin = 10;
//            linearOverAllParams.topMargin = 10;
//            linearOverAllParams.bottomMargin = 10;
                mbinding.layOverAll.setLayoutParams(linearOverAllParams);

//            LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(520,100);
//            linearOverAllParams1.leftMargin = 25;
//            binding.Lay1.setLayoutParams(linearOverAllParams1);
//
//            LinearLayout.LayoutParams linearOverAllParams2 = new LinearLayout.LayoutParams(520,90);
//            linearOverAllParams2.leftMargin = 25;
//            linearOverAllParams2.topMargin = 10;
//            linearOverAllParams2.bottomMargin = 10;
//            binding.Lay2.setLayoutParams(linearOverAllParams2);
//            binding.Lay3.setLayoutParams(linearOverAllParams2);
//            binding.Lay4.setLayoutParams(linearOverAllParams2);
//            binding.Lay5.setLayoutParams(linearOverAllParams2);
//            binding.Lay6.setLayoutParams(linearOverAllParams2);

            }

        }
    }

//    public static void MainPageCategoryRefreshFun() {
//        getCategoryAll();
////        if (lstBook.size() > 0) {
//            mListadapter = new ListAdapter(mcontext, lstBook);
//            binding.recyclerViewCategoriesList.setAdapter(mListadapter);
////        }
//    }
    @Override
    public void onResume() {
        mcontext = getContext();
        MainPageCategoryRefreshFun();
        super.onResume();
    }

    public static void getCategoryAll() {
        Cursor c = null;
        if (str_newText.length() > 1){
            c = DBFunc.Query("SELECT ID,Name,Image FROM Category WHERE Name LIKE '%"+str_newText.toUpperCase()+"%'", true);
        }else{
            c = DBFunc.Query("SELECT ID,Name,Image FROM Category ", true);
        }

        if (c != null) {
            lstBook.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
//                    byte[] decodedString = Base64.decode(c.getString(2), Base64.DEFAULT);
//                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    lstBook.add(new DataNoteImformation(c.getInt(0),c.getString(1),c.getString(2)));
                }
            }
            c.close();
        }
    }
    private final Runnable m_Runnable = new Runnable() {
        public void run() {
             //Toast.makeText(getContext(),"in runnable 2",Toast.LENGTH_SHORT).show();
//            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
            //mHandler = new Handler();
//           if (status_on.equals("1")) {
                //mHandler.postDelayed(m_Runnable, 300);
                mHandler.postDelayed(m_Runnable, 1000);
//                if (St.equals("1")) {
//
//                    MainActivity.str_tab_fragment_2 = "0";
//                    MainActivity.St = "1";
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    binding.recyclerViewCategoriesList.setLayoutManager(layoutManager);
                    getCategoryAll();
                    mListadapter = new ListAdapter(getContext(), lstBook);
                    binding.recyclerViewCategoriesList.setAdapter(mListadapter);

                    status_on = "0";
                    //St = "0";
                }
//           }
        //}



    };
    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private List<DataNoteImformation> dataList;
        private Context mContext ;
        public ListAdapter(Context mContext,List<DataNoteImformation> data) {
            this.mContext = mContext;
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView category_text;
            ImageView category_image;
            ImageView dropdown_mark;
            FrameLayout layout_fragment_container;
            CardView layout_card_view;
            LinearLayout lay1;

            public ViewHolder(View itemView) {
                super(itemView);
                this.category_text = (TextView) itemView.findViewById(R.id.category_text);
                this.category_text.setTextSize(MainActivity.billFontSize);
                this.layout_fragment_container = (FrameLayout) itemView.findViewById(R.id.fragment_container);
                this.layout_card_view = (CardView) itemView.findViewById(R.id.layout_card_view);
                this.category_image = (ImageView) itemView.findViewById(R.id.category_image);
                this.dropdown_mark = (ImageView) itemView.findViewById(R.id.dropdownmark);
                this.lay1 = (LinearLayout) itemView.findViewById(R.id.Lay1);

                String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);

                if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
                    FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(540,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.leftMargin = 10;
//                    linearOverAllParams.topMargin = 10;
//                    linearOverAllParams.bottomMargin = 10;
                    lay1.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(535,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams1.topMargin = 3;
                    linearOverAllParams1.bottomMargin = 3;
                    linearOverAllParams1.leftMargin = 3;
                    linearOverAllParams1.rightMargin = 3;
                    layout_card_view.setLayoutParams(linearOverAllParams1);
                }
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
            String HideImage = Query.GetOptions(20);
            if (HideImage.equals("1")) {
                holder.category_image.setVisibility(View.GONE);
            }else {
                holder.category_image.setVisibility(View.VISIBLE);
                if (dataList.get(position).getImage() == null || dataList.get(position).getImage().equals("0")
                        || dataList.get(position).getImage().isEmpty()){
//
                    Picasso.with(mContext).load(R.drawable.default_no_image).into(holder.category_image);
                }else {
                    Picasso.with(mContext).load(dataList.get(position).getImage()).into(holder.category_image);
                }
                //Picasso.with(mContext).load(dataList.get(position).getImage()).into(holder.category_image);

//            holder.category_image.setImageBitmap(dataList.get(position).getImage());
            }
            holder.category_text.setText(dataList.get(position).getName());
            category_name = dataList.get(position).getName();
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    //getProductByCategory(productID);
                    productID = 0;
                    status_on = "1";
                    //searchProductWithCategoryId(dataList.get(position).getID());
                   // category_name = dataList.get(position).getName();
                    productID = dataList.get(position).getID();
//                    productName = dataList.get(position).getName();
                    ProductMainPageFragment.product_ID = productID;
                    ProductMainPageFragment.product_Name = dataList.get(position).getName();
                    //MainActivity.product_ID = productID;
                    //MainActivity.product_Name = dataList.get(position).getName();
                    if (productID > 0){
                        //MainActivity.CategoryproductID = productID;
                        Intent intent = new Intent(mcontext,MainActivity.class);
                        MainActivity.category_status = "1";
                        MainActivity.str_tab_fragment_2 = "0";
                        intent.putExtra("name","Category____"+String.valueOf(productID));
                        mcontext.startActivity(intent);
                        ((Activity)mcontext).finish();


                       // MainActivity.viewPager.setCurrentItem(0);
//                        MainActivity.viewPager.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                ProductMainPageFragment.St = "1";
//                                MainActivity.viewPager.setCurrentItem(0);
//                            }
//                        });
                    }

//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.hide(oldFragment);
//                    ft.show(newFragment);
//                    ft.commit();
//                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//                    ProductMainPageFragment fragment = new ProductMainPageFragment();
//                    ft.replace(R.id.fragment_container, fragment);
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                    ft.addToBackStack(CategoryMainPageFragment.this.getClass().getSimpleName());
//                    ft.commit();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

    private void searchProductWithCategoryId(Integer id) {
        String sql = "SELECT ProductCategoryID FROM PLU WHERE " +
                " ID = '"+String.valueOf(id)+"'";
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            productID =  0;
            if (c.moveToNext()) {
                productID = c.getInt(0);
            }
            c.close();
        }
    }

//    private void getProductByCategory(Integer id) {
////        Cursor c = DBFunc.Query("SELECT Name,Price,Code,Image,ID FROM PLU Where ID = "+id, true);
//        Cursor c = DBFunc.Query("SELECT Name,Price,Code,Image,ID,ProductCategoryID,ProductCategoryName,UUID FROM PLU Where ID IS NULL ", true);
//        if (c != null) {
//            lstBook.clear();
//            while (c.moveToNext()) {
//                if (!c.isNull(0)) {
////                    byte[] decodedString = Base64.decode(c.getString(3), Base64.DEFAULT);
////                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                    lstBook2.add(new Book(c.getString(0),c.getString(1),"","", c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8)));
//                }
//            }
//            c.close();
//        }
//    }

//    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
//    {
//        private ArrayList<DataNote> dataList;
//
//        public ListAdapter(ArrayList<DataNote> data)
//        {
//            this.dataList = data;
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder
//        {
//            ImageView textViewText;
//            TextView textViewComment;
//            ImageView textViewDate;
//            FrameLayout layout_fragment_container;
//            CardView layout_card_view;
//
//            public ViewHolder(View itemView)
//            {
//                super(itemView);
//                this.textViewText = (ImageView) itemView.findViewById(R.id.text);
//                this.layout_fragment_container = (FrameLayout) itemView.findViewById(R.id.fragment_container);
//                this.layout_card_view = (CardView) itemView.findViewById(R.id.layout_card_view);
//                this.textViewComment = (TextView) itemView.findViewById(R.id.textcomment);
//                this.textViewDate = (ImageView) itemView.findViewById(R.id.date);
//            }
//        }
//
//        @Override
//        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
//        {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
//
//            ViewHolder viewHolder = new ViewHolder(view);
//            return viewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
//        {
//            //holder.textViewText.setText(dataList.get(position).getText());
//            holder.textViewText.setImageResource(dataList.get(position).getMasarImg());
//            holder.textViewComment.setText(dataList.get(position).getComment());
//            //holder.textViewDate.setText(dataList.get(position).getDate());
//            holder.textViewDate.setImageResource(dataList.get(position).getArrowImg());
//
//            holder.itemView.setOnClickListener(new View.OnClickListener()
//            {
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public void onClick(View v) {
//                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//                    ProductMainPageFragment fragment = new ProductMainPageFragment();
//                    ft.replace(R.id.fragment_container, fragment);
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                    ft.addToBackStack(CategoryMainPageFragment.this.getClass().getSimpleName());
//                    ft.commit();
////                    ProductMainPageFragment fragment2 = new ProductMainPageFragment();
////                    FragmentManager fragmentManager = getFragmentManager();
////                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                    fragmentTransaction.replace(R.id.dddd, fragment2);
////                    //fragmentTransaction.replace(((ViewGroup)(Objects.requireNonNull(getView()).getParent())).getId(), fragment);
////                    fragmentTransaction.addToBackStack(CategoryMainPageFragment.this.getClass().getSimpleName());
////                    fragmentTransaction.commit();
//
//
//////                    getFragmentManager().beginTransaction()
//////                            .replace(R.id.fragment_container, new ProductMainPageFragment())
//////                            .commit();
////
//////                    ProductMainPageFragment fragment = new ProductMainPageFragment();
//////                    FragmentManager fragmentManager = getFragmentManager();
//////
//////                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//////
//////                    fragmentTransaction.replace(R.layout.activity_mypartner_view,fragment);
//////
//////                    fragmentTransaction.commit();
////
////
//////                    Bundle args = new Bundle();
//////                    args.putString("category_id", String.valueOf(dataList.get(position)));
//////                    //set Fragmentclass Arguments
////                    ProductMainPageFragment fragobj = new ProductMainPageFragment();
////                    //fragobj.setArguments(args);
////                    FragmentManager fragmentManager = (FragmentManager) CategoryMainPageFragment.this.getFragmentManager();
////                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                    fragmentTransaction.remove(CategoryMainPageFragment.this);
////                   // holder.layout_card_view.setVisibility(View.GONE);
////
////                    fragmentManager = (FragmentManager) CategoryMainPageFragment.this.getFragmentManager();
////                    fragmentTransaction = fragmentManager.beginTransaction();
////                    fragmentTransaction.add(R.id.fragment_container, fragobj);
////                    //fragmentTransaction.hide(CategoryMainPageFragment.this);
////                    fragmentTransaction.addToBackStack(CategoryMainPageFragment.this.getClass().getSimpleName());
////                    fragmentTransaction.commit();
////
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount()
//        {
//            return dataList.size();
//        }
//    }
}