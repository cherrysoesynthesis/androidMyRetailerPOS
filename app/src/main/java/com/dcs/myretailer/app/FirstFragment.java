package com.dcs.myretailer.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.IllegalFormatException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ActivityMainBinding binding = null;
    MainPagePagerAdapter adapter;
    private FragmentActivity myContext;
    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_first, container, false);

        tabLayoutFun();
        tabSelectedFun();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void tabLayoutFun() {
        // try {
        binding.tabLayout.removeAllTabs();
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.Products));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.Categories));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.Bill));

        String OnlineStatus = Query.GetOptions(19);
        if (OnlineStatus.equals("1")) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.OnlineOrder));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.OnlineOrderBill));

            binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }else {
//                tabLayout.removeTab(tabLayout.newTab().setText(ENUM.OnlineOrder));
//                tabLayout.removeTab(tabLayout.newTab().setText(ENUM.OnlineOrderBill));
            binding.tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        }
        //tabLayout.addTab(tabLayout.newTab().setText("Layout"));
        //tabLayout.addTab(tabLayout.newTab().setText("Promotion"));
        //tabLayout.setTabTextColors(getResources().getColor(R.color.mine_shaft),getResources().getColor(R.color.mine_shaft));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        binding.tabLayout.setTabTextColors(Color.parseColor("#a9aaad"), Color.parseColor("#000000"));
//            tabLayout.setSelectedTabIndicatorHeight(2);
//            tabLayout.setSelectedTabIndicatorColor(Color.RED);
        FragmentManager fragManager = myContext.getFragmentManager();
        adapter = new MainPagePagerAdapter(getFragmentManager(), binding.tabLayout.getTabCount());
//        adapter = new MainPagePagerAdapter(getSupportFragmentManager(), binding.tabLayout.getTabCount());
        //adapter = new PagerAdapter(getSupportFragmentManager());

        binding.pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        //view_pager_position = viewPager.getCurrentItem();
        //Log.i("DFDF______________", String.valueOf(view_pager_position));
//            adapter.addFragment(new ProductMainPageFragment(), "Product",0);
//            adapter.addFragment(new CategoryMainPageFragment(), "Category", 1);
//            adapter.addFragment(new BillListMainPageFragment(), "Bill", 2);
//            adapter.addFragment(new OnlineOrderListMainPageFragment(), "OnlineOrder", 3);
//            adapter.addFragment(new OnlineOrderClosedBillListMainPageFragment(), "OrderOrderBill", 4);
//            adapter.addFragment(new MapLayoutMainPageFragment(), "Layout", 4);

        //viewPager.setOffscreenPageLimit(3);
        //viewPager.setAdapter(adapter);

//        }catch (IllegalFormatException e){
//            Log.i("EEEE",e.getMessage());
//        }
    }

    private void tabSelectedFun() {
////        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
////        viewPager.setAdapter(adapter);
//
//        //tabLayout.setupWithViewPager(viewPager);
//        //PagerAdapter.view_pager_position = viewPager.getCurrentItem();
//        if (str_tab_fragment_2.equals("1")){
//            //            Log.i("str_tab_fragment_2",str_tab_fragment_2);
//            binding.pager.setCurrentItem(1);
//            str_tab_fragment_2 = "0";
//        }
//
//
////        Log.i("DFDF__","DSD"+String.valueOf(ReportActivity.previousReport).length());
////        if (String.valueOf(ReportActivity.previousReport).length() >  4 ) {
////            viewPager.setCurrentItem(3);
////        }
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
//                    viewPager.setCurrentItem(tab.getPosition());
                    if (binding.pager.getCurrentItem() > -1) {
                        binding.pager.setCurrentItem(tab.getPosition());
                    }
                }catch (IllegalFormatException e){
                    Log.e("IllegalFormatException",e.getMessage());
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}