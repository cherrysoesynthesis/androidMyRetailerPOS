package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.ProductMainPageViewModel;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.ProductManagementActivity;
import com.dcs.myretailer.app.databinding.ProductMainPageListBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProductMainPageFragment extends Fragment implements View.OnClickListener {
    public static List<ProductData> lstProductData = new ArrayList<ProductData>();
    private int focusedItem = 0;
    Boolean flag_loading = false;
    public static Handler mHandler;
    public static String str_newText = "";
    public static String tet = "0";
    public static Integer product_ID = 0;
    public static String product_Name = "0";
    public static String status_on = "0";
    public static RecyclerViewAdapter myAdapter;
    static GridLayoutManager gridLayoutManager;
    public static String St = "0";
    public static ProductMainPageListBinding binding = null;
    public static ProductMainPageViewModel model = null;
    public ProductMainPageFragment() {
        // Required empty public constructor
    }
    public static Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.product_main_page_list, container, false);

        model = ViewModelProviders.of(this).get(ProductMainPageViewModel.class);
        View view = binding.getRoot();

        BackButtonShowOff();

        binding.back.setOnClickListener(this);

        RecyclerViewDataShow(mContext);

        return view;
    }

    @Override
    public void onResume() {
        mContext = getContext();
        RunAuto();
        super.onResume();
    }

    public static void RunAuto() {

            BackButtonShowOff();

            if (str_newText.length() > 0) {
                if (str_newText.length() == 1) {
                    str_newText = "";
                }
                RecyclerViewDataShow(mContext);
            }else {
                RecyclerViewDataShow(mContext);
            }
            if (RecyclerViewAdapter.select_.equals("1")) {
                RecyclerViewDataShow(mContext);
                RecyclerViewAdapter.select_ = "0";
            }

//            RecyclerViewDataShow(mContext);
            ProductManagementActivity.str_product_management = "0";
            status_on = "0";
            St = "0";
            RecyclerViewAdapter.status_on = "1";

    }

    private static void BackButtonShowOff() {
        //product_ID = CategoryMainPageFragment.productID;
//        product_Name = CategoryMainPageFragment.productName;
        if (product_ID > 0){
            binding.backLayoutId.setVisibility(View.VISIBLE);
            binding.categoryName.setText(product_Name);
            binding.back.setVisibility(View.VISIBLE);
            //product_ID = 0;
        }else {
            binding.backLayoutId.setVisibility(View.GONE);
            binding.back.setVisibility(View.GONE);
        }
    }

    private static void RecyclerViewDataShow(Context context) {
        AtomicReference<Integer> position = new AtomicReference<>(0);
        getProductAll();
//        model = ViewModelProviders.of((MainActivity)context).get(ProductMainPageViewModel.class);
//        model.getProduct().observe((LifecycleOwner) context, lstProductData -> {
            position.set(RecyclerViewAdapter.St_position);
            String device = Query.GetDeviceData(Constraints.DEVICE);
            Integer showPicCount = 3;
            if (device.equals("M2-Max")) {
                showPicCount = 4;
            }
            gridLayoutManager = new GridLayoutManager(context,showPicCount);
            binding.recyclerviewId.setLayoutManager(gridLayoutManager);

//            if (lstProductData.size() > 0) {

                myAdapter = new RecyclerViewAdapter(context, lstProductData, "product");
                binding.recyclerviewId.setAdapter(myAdapter);
                //myAdapter.notifyDataSetChanged();
                RecyclerViewAdapter.St = "1";
                binding.recyclerviewId.getLayoutManager().scrollToPosition(position.get());

//            }
            //myrv.notifyItemChanged(position);
            ////myAdapter.notifyDataSetChanged();
            //myAdapter.notifyItemChanged(position);
//        }
//        });


    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            m_Runnable = new Runnable() {
//                @Override
//                public void run() {
//                    //do something
//                }
//            };
//            getHandler().postDelayed(runnable, 2000);
//        } else if (ev.getAction() == MotionEvent.ACTION_UP
//                || ev.getAction() == MotionEvent.ACTION_CANCEL) {
//            getHandler().removeCallbacks(runnable);
//        }
//        return super.dispatchTouchEvent(ev);
//    }

//    public final Runnable m_Runnable = new Runnable() {
//        public void run()
//        {
//            mHandler.postDelayed(m_Runnable, 300);
//            if (St.equals("1")) {
//                RunAuto();
//            }
//        }
//
//
//
//    };
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;

        int stretch_width = Math.round((float)width / (float)reqWidth);
        int stretch_height = Math.round((float)height / (float)reqHeight);

        if (stretch_width <= stretch_height)
            return stretch_height;
        else
            return stretch_width;
    }

    public static void getProductAll() {
        Query.ClearRecyclerViewValues();
        Cursor c = null;

        if (str_newText.length() > 0){
            c = Query.GetPLU(-1);
        }else {
            if (product_ID > 0) {
                c = Query.GetPLU(product_ID);
            }else{
                binding.backLayoutId.setVisibility(View.GONE);
                c = Query.GetPLU(0);
            }
        }
        if (c != null) {
            lstProductData.clear();
            while (c.moveToNext()) {
                lstProductData = Query.GetBillDetailsProduct(c, lstProductData);
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
//        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        CategoryMainPageFragment fragment = new CategoryMainPageFragment();
//        fragmentTransaction.replace(R.id.container, fragment);
//        fragmentTransaction.commit();
        switch (v.getId()){
            case R.id.back:
//                Intent intent= new Intent(v.getContext(), MainActivity.class);
                Intent intent= new Intent(mContext, MainActivity.class);
                intent.putExtra("name", "CategoryMainPageFragment");
                //((Activity)getContext()).finish();
                v.getContext().startActivity(intent);
                ((Activity)mContext).finish();
//                PagerAdapter.str_tab_fragment_2 = "1";
//
//                MainActivity.viewPager.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ProductMainPageFragment.St = "1";
//                        CategoryMainPageFragment.productID = 0;
//                        CategoryMainPageFragment.productName = "";
//                        MainActivity.viewPager.setCurrentItem(1);
//                    }
//                });
                //MainActivity.St = "1";
                //MainActivity.CategorySt = "1";
                product_ID = 0;
                ProductMainPageFragment.status_on = "0";
                CategoryMainPageFragment.status_on = "1";
                //MainActivity.str_tab_fragment_2 = "0";
                //ProductMainPageFragment.status_on = "0";
                //CategoryMainPageFragment.status_on = "1";
                break;
        }
    }


//    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
//        int tryFocusItem = focusedItem + direction;
//
//        // If still within valid bounds, move the selection, notify to redraw, and scroll
//        if (tryFocusItem >= 0 && tryFocusItem < getItemCount()) {
//            //notifyItemChanged(focusedItem);
//            focusedItem = tryFocusItem;
//            //notifyItemChanged(focusedItem);
//            lm.scrollToPosition(focusedItem);
//            return true;
//        }
//
//        return false;
//    }
//
//    public int getItemCount() {
//        return lstBook.size();
//    }
//
//    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        // Set selected state; use a state list drawable to style the view
//        viewHolder.itemView.setSelected(focusedItem == i);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            // Handle item click and set the selection
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Redraw the old selection and the new
//                    //notifyItemChanged(focusedItem);
//                    focusedItem = getLayoutPosition();
//                    Log.i("QQQ___", String.valueOf(focusedItem));
//                    //notifyItemChanged(focusedItem);
//                }
//            });
//        }
//    }


}