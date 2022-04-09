package com.dcs.myretailer.app;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dcs.myretailer.app.Cashier.BillListMainPageFragment;
import com.dcs.myretailer.app.Cashier.CategoryMainPageFragment;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.Query.Query;

public class MainPagePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public static String OnlineOrderBillStatusOn = "0";
    public static String str_newText = "";
    public static Integer onPageSelectedSt = 0;
    public static Integer product_ID = 0;
//    public static String product_Name = "0";
//    public static String str_tab_fragment_2 = "0";
    //private final List<Fragment> mFragmentList = new ArrayList<Fragment>();

    //private final List<String> mFragmentTitleList = new ArrayList<String>();

    public MainPagePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                if (!OnlineOrderBillStatusOn.equals("1")) {
                    ProductMainPageFragment tab11 = new ProductMainPageFragment();
                    return tab11;
                }
            case 1:
                if (!OnlineOrderBillStatusOn.equals("1")) {
                    CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
                    return tab2;
                }
            case 2:
                if (!OnlineOrderBillStatusOn.equals("1")) {
                    BillListMainPageFragment.str_newText = str_newText;
                    if (!MainActivity.str_tab_fragment_2.equals("1")) {
                        MainActivity.str_tab_fragment_3 = "0";
                    }
                    BillListMainPageFragment tab3 = new BillListMainPageFragment();
                    return tab3;
                }
            case 3:
//                OnlineOrderListMainPageFragment.str_newText = str_newText;
//                OnlineOrderListMainPageFragment tab5 = new OnlineOrderListMainPageFragment();

                String optionImage = Query.GetOptions(19);
                if (optionImage.equals("1")) {
                    OnlineOrderListMainPageFragment tab5 = new OnlineOrderListMainPageFragment();
                    return tab5;
                }else {
                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
                    return tab1;
                }

            case 4:
                String optionImage1 = Query.GetOptions(19);
                if (optionImage1.equals("1")) {
                    if (!OnlineOrderBillStatusOn.equals("1")) {
                        OnlineOrderClosedBillListMainPageFragment tab6 = new OnlineOrderClosedBillListMainPageFragment();
                        return tab6;
                    }
                }else {
                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
                    return tab1;
                }
//            case 5:
//                MainActivity.str_chk_map_layout = "0";
//                MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
//                return tab4;
            default:
                ProductMainPageFragment tab1 = new ProductMainPageFragment();
                return tab1;
        }
    }
//    public PagerAdapter(FragmentManager manager) {
//        super(manager);
//    }

//    @Override
//    public Fragment getItem(int position) {
//        //ProductMainPageFragment.St = "1";
//        Log.i("__p0osition", String.valueOf(position));
//        Fragment fragment = null;
//        //position = onPageSelectedSt;
//        switch (position) {
////        if (position > 0 ) {
////            if (position == 0) {
//            case 0:
//                Log.i("__aftp0osition", String.valueOf(position));
////                Log.i("__astr_tab_fragment_2", String.valueOf(MainActivity.str_tab_fragment_2));
////                Log.i("__astrstatus_on_2", String.valueOf(status_on));
////                //category_status = "0";
////                //if (!MainActivity.str_tab_fragment_3.equals("1")) {
////                ProductMainPageFragment.str_newText = str_newText;
////                if (!MainActivity.str_tab_fragment_2.equals("1")) {
//////                        ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                        return tab1;
//////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                    return tab1;
////                } else if (status_on.equals("1")) {
////                    status_on = "0";
////                    ProductMainPageFragment.status_on = "1";
//////                        ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                        return tab1;
////                }
//                ProductMainPageFragment tab11 = new ProductMainPageFragment();
//                return tab11;
////                else {
//////                    Log.i("DDFDF", String.valueOf(MainActivity.viewPager.getCurrentItem()));
//////                    MainActivity.viewPager.setCurrentItem(MainActivity.viewPager.getCurrentItem()+1);
////                    CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
//////                    // return tab2;
////                    return tab2;
////                }
////                ProductMainPageFragment.product_ID = product_ID;
////                ProductMainPageFragment.product_Name = product_Name;
////                Log.i("Dfdf___", String.valueOf(product_ID));
////                //Log.i("Dfdfproduct_Name", String.valueOf(product_Name));
////                ProductMainPageFragment tab1 = new ProductMainPageFragment();
////                fragment = tab1;
////                }else {
//////                    MainActivity.St = "1";
//////                    ProductMainPageFragment.St = "1";
//////                    RecyclerViewAdapter.St = "1";
////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
////                    return tab1;
////                }
////                else {
////                    Log.i("DSF__DSfposition", String.valueOf(position));
////                    //ProductMainPageFragment.St = "1";
////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
////                    return tab1;
////                }
////            }
//                //if (position == 1) {
//            case 1:
////                CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
////                return tab2;
//////                if (!MainActivity.str_tab_fragment_3.equals("1")) {
//////                    CategoryMainPageFragment.str_newText = str_newText;
////                Log.i("Dfdf__yyyytyyyuuiu", MainActivity.str_tab_fragment_2);
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
////                    MainActivity.tab_2_ = "1";
////                    MainActivity.St = "1";
////                    CategoryMainPageFragment.productID = 0;
////                    MainActivity.category_status = "0";
////                    MainActivity.str_tab_fragment_2 = "0";
////                    ProductMainPageFragment.product_ID = 0;
////                    MainActivity.CategorySt = "0";
////                    //MainActivity.St = "1";
////                    //MainActivity.viewPager.setCurrentItem(1);
////                }
//////                }
//////                MainActivity.name = "";
//////                CategoryMainPageFragment.productID = 0;
//////                MainActivity.category_status = "0";
//////                MainActivity.str_tab_fragment_2 = "0";
//////                ProductMainPageFragment.product_ID = 0;
//////                MainActivity.CategorySt = "0";
////                Log.i("imhere", "Dfdfdfdfd" + position);
//                CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
//                return tab2;
////                return tab2;
//////                else {
//////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                    return tab1;
//////                }
//////            }
//////            if (position == 2) {
//            case 2:
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
//                BillListMainPageFragment.str_newText = str_newText;
//                if (!MainActivity.str_tab_fragment_2.equals("1")) {
////                        Log.i("dfdf_map_layout", MainActivity.str_chk_map_layout);
////                        //                    if (MainActivity.str_chk_map_layout.equals("1")){
////                        //                        MainActivity.str_chk_map_layout = "0";
////                        //                        MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
////                        //                        return tab4;
////                        //                    }else {
//                    MainActivity.str_tab_fragment_3 = "0";
////                        BillListMainPageFragment tab3 = new BillListMainPageFragment();
////                        return tab3;
//                    //                    }
//
//                }
//                BillListMainPageFragment tab3 = new BillListMainPageFragment();
//                //return tab3;
//                return tab3;
////                }
////            }
////            if (position == 3) {
//            case 3:
//                //Online Bill
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
//                //if (!MainActivity.str_tab_fragment_3.equals("1")) {
//                OnlineOrderListMainPageFragment.str_newText = str_newText;
//                //if (!MainActivity.str_tab_fragment_2.equals("1")) {
//                OnlineOrderListMainPageFragment tab5 = new OnlineOrderListMainPageFragment();
//                // return tab5;
//                //}
//                // }
////                }
//                return tab5;
////            }
////            if (position == 4) {
//            case 4:
//                Log.i("HERERE", "DFDFD");
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
//                // if (!MainActivity.str_tab_fragment_3.equals("1")) {
//                //OnlineOrderClosedBillListMainPageFragment.str_newText = str_newText;
//                //if (!MainActivity.str_tab_fragment_2.equals("1")) {
//                //return new OnlineOrderClosedBillListMainPageFragment();
////                MainActivity.viewPager.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        //ProductMainPageFragment.St = "1";
////                        MainActivity.viewPager.setCurrentItem(4);
////                    }
////                });
//                //MainActivity.viewPager.setCurrentItem(4);
//                OnlineOrderClosedBillListMainPageFragment tab6 = new OnlineOrderClosedBillListMainPageFragment();
//                //return tab6;
//                return tab6;
//            case 5:
//                MainActivity.str_chk_map_layout = "0";
//                MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
//                //return tab4;
//                return tab4;
//
//            default:
//                ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                return tab1;
//        }
//}

    //beforelatest22082020
//@Override
//public Fragment getItem(int position) {
//    Log.i("__p0osition", String.valueOf(position));
//    Fragment fragment = null;
//    switch (position) {
//        case 0:
//            Log.i("__aftp0osition", String.valueOf(position));
////                Log.i("__astr_tab_fragment_2", String.valueOf(MainActivity.str_tab_fragment_2));
////                Log.i("__astrstatus_on_2", String.valueOf(status_on));
////                //category_status = "0";
////                //if (!MainActivity.str_tab_fragment_3.equals("1")) {
////                ProductMainPageFragment.str_newText = str_newText;
////                if (!MainActivity.str_tab_fragment_2.equals("1")) {
//////                        ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                        return tab1;
//////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                    return tab1;
////                } else if (status_on.equals("1")) {
////                    status_on = "0";
////                    ProductMainPageFragment.status_on = "1";
//////                        ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                        return tab1;
////                }
//            ProductMainPageFragment tab11 = new ProductMainPageFragment();
//            return tab11;
////                else {
//////                    Log.i("DDFDF", String.valueOf(MainActivity.viewPager.getCurrentItem()));
//////                    MainActivity.viewPager.setCurrentItem(MainActivity.viewPager.getCurrentItem()+1);
////                    CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
//////                    // return tab2;
////                    return tab2;
////                }
////                ProductMainPageFragment.product_ID = product_ID;
////                ProductMainPageFragment.product_Name = product_Name;
////                Log.i("Dfdf___", String.valueOf(product_ID));
////                //Log.i("Dfdfproduct_Name", String.valueOf(product_Name));
////                ProductMainPageFragment tab1 = new ProductMainPageFragment();
////                fragment = tab1;
////                }else {
//////                    MainActivity.St = "1";
//////                    ProductMainPageFragment.St = "1";
//////                    RecyclerViewAdapter.St = "1";
////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
////                    return tab1;
////                }
////                else {
////                    Log.i("DSF__DSfposition", String.valueOf(position));
////                    //ProductMainPageFragment.St = "1";
////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
////                    return tab1;
////                }
////            }
//        //if (position == 1) {
//        case 1:
////                CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
////                return tab2;
//////                if (!MainActivity.str_tab_fragment_3.equals("1")) {
//////                    CategoryMainPageFragment.str_newText = str_newText;
////                Log.i("Dfdf__yyyytyyyuuiu", MainActivity.str_tab_fragment_2);
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
////                    MainActivity.tab_2_ = "1";
////                    MainActivity.St = "1";
////                    CategoryMainPageFragment.productID = 0;
////                    MainActivity.category_status = "0";
////                    MainActivity.str_tab_fragment_2 = "0";
////                    ProductMainPageFragment.product_ID = 0;
////                    MainActivity.CategorySt = "0";
////                    //MainActivity.St = "1";
////                    //MainActivity.viewPager.setCurrentItem(1);
////                }
//////                }
//////                MainActivity.name = "";
//////                CategoryMainPageFragment.productID = 0;
//////                MainActivity.category_status = "0";
//////                MainActivity.str_tab_fragment_2 = "0";
//////                ProductMainPageFragment.product_ID = 0;
//////                MainActivity.CategorySt = "0";
////                Log.i("imhere", "Dfdfdfdfd" + position);
//            CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
//            return tab2;
////                return tab2;
//////                else {
//////                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
//////                    return tab1;
//////                }
//////            }
//////            if (position == 2) {
//        case 2:
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
//            BillListMainPageFragment.str_newText = str_newText;
//            if (!MainActivity.str_tab_fragment_2.equals("1")) {
////                        Log.i("dfdf_map_layout", MainActivity.str_chk_map_layout);
////                        //                    if (MainActivity.str_chk_map_layout.equals("1")){
////                        //                        MainActivity.str_chk_map_layout = "0";
////                        //                        MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
////                        //                        return tab4;
////                        //                    }else {
//                MainActivity.str_tab_fragment_3 = "0";
////                        BillListMainPageFragment tab3 = new BillListMainPageFragment();
////                        return tab3;
//                //                    }
//
//            }
//            BillListMainPageFragment tab3 = new BillListMainPageFragment();
//            //return tab3;
//            return tab3;
////                }
////            }
////            if (position == 3) {
//        case 3:
//            //Online Bill
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
//            //if (!MainActivity.str_tab_fragment_3.equals("1")) {
//            OnlineOrderListMainPageFragment.str_newText = str_newText;
//            //if (!MainActivity.str_tab_fragment_2.equals("1")) {
//            OnlineOrderListMainPageFragment tab5 = new OnlineOrderListMainPageFragment();
//            // return tab5;
//            //}
//            // }
////                }
//            return tab5;
////            }
////            if (position == 4) {
//        case 4:
//            Log.i("HERERE", "DFDFD");
////                if (MainActivity.str_tab_fragment_2.equals("1")) {
//            // if (!MainActivity.str_tab_fragment_3.equals("1")) {
//            //OnlineOrderClosedBillListMainPageFragment.str_newText = str_newText;
//            //if (!MainActivity.str_tab_fragment_2.equals("1")) {
//            //return new OnlineOrderClosedBillListMainPageFragment();
////                MainActivity.viewPager.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        //ProductMainPageFragment.St = "1";
////                        MainActivity.viewPager.setCurrentItem(4);
////                    }
////                });
//            //MainActivity.viewPager.setCurrentItem(4);
//            OnlineOrderClosedBillListMainPageFragment tab6 = new OnlineOrderClosedBillListMainPageFragment();
//            //return tab6;
//            return tab6;
//        case 5:
//            MainActivity.str_chk_map_layout = "0";
//            MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
//            //return tab4;
//            return tab4;
//
//        default:
//            ProductMainPageFragment tab1 = new ProductMainPageFragment();
//            return tab1;
//    }
//}


    //public static Integer view_pager_position = 0;
//    @Override
//    public int getItemPosition(Object object) {
//        Log.i("DFD",object.toString());
//        return super.getItemPosition(object);
//    }

   /* //int count = 0;
    @Override
    public Fragment getItem(int position) {
//        count++;
//        Log.i("DFDFDF_Success", String.valueOf(String.valueOf(view_pager_position)+"__"+count));
//        Fragment fragment = null;
//        //position = view_pager_position;
//            if (view_pager_position == 0) {
//                ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                fragment = tab1;
//            }
//            if (view_pager_position == 1) {
//                    if (str_tab_fragment_2.equals("1")) {
//                        CategoryMainPageFragment.productID = 0;
//                        MainActivity.category_status = "0";
//                        str_tab_fragment_2 = "0";
//                        ProductMainPageFragment.product_ID = 0;
//                        MainActivity.CategorySt = "0";
//                    }
//                CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
//                fragment = tab2;
//            }
//            if (view_pager_position == 2) {
//                Log.i("BILL_", String.valueOf(view_pager_position));
//                BillListMainPageFragment.str_newText = str_newText;
//                if (!MainActivity.str_tab_fragment_2.equals("1")) {
//                    MainActivity.str_tab_fragment_3 = "0";
//                }
//                BillListMainPageFragment tab3 = new BillListMainPageFragment();
//                fragment = tab3;
//            }
//            if (view_pager_position == 3) {
//                OnlineOrderListMainPageFragment.str_newText = str_newText;
//                OnlineOrderListMainPageFragment tab5 = new OnlineOrderListMainPageFragment();
//                fragment = tab5;
//            }
//            if (view_pager_position == 4) {
//                //MainActivity.viewPager.setCurrentItem(4);
//                OnlineOrderClosedBillListMainPageFragment tab6 = new OnlineOrderClosedBillListMainPageFragment();
//                fragment = tab6;
//            }
//            if (view_pager_position == 5) {
//                MainActivity.str_chk_map_layout = "0";
//                MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
//                fragment = tab4;
//            }
//            return fragment;
        Log.i("DFDLFDFLDF__", String.valueOf(position));
        switch (position) {
            case 0:
//                ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                return tab1;
            //category_status = "0";
                //if (!MainActivity.str_tab_fragment_3.equals("1")) {
                    ProductMainPageFragment.str_newText = str_newText;
                    if (!MainActivity.str_tab_fragment_2.equals("1")) {
//                        ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                        return tab1;
                    } else if (status_on.equals("1")) {
                        status_on = "0";
                        ProductMainPageFragment.status_on = "1";
//                        ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                        return tab1;
                    }
                ProductMainPageFragment tab1 = new ProductMainPageFragment();
                return tab1;
//                }else {
////                    MainActivity.St = "1";
////                    ProductMainPageFragment.St = "1";
////                    RecyclerViewAdapter.St = "1";
//                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                    return tab1;
//                }
//                else {
//                    Log.i("DSF__DSfposition", String.valueOf(position));
//                    //ProductMainPageFragment.St = "1";
//                    ProductMainPageFragment tab1 = new ProductMainPageFragment();
//                    return tab1;
//                }
            case 1:
                if (str_tab_fragment_2.equals("1")) {
                    CategoryMainPageFragment.productID = 0;
                    MainActivity.category_status = "0";
                    str_tab_fragment_2 = "0";
                    ProductMainPageFragment.product_ID = 0;
                    MainActivity.CategorySt = "0";
                }
                CategoryMainPageFragment tab2 = new CategoryMainPageFragment();
                return tab2;
            case 2:
                BillListMainPageFragment.str_newText = str_newText;
                if (!MainActivity.str_tab_fragment_2.equals("1")) {
                    MainActivity.str_tab_fragment_3 = "0";
                }
                BillListMainPageFragment tab3 = new BillListMainPageFragment();
                return tab3;
            case 3:
                OnlineOrderListMainPageFragment.str_newText = str_newText;
                OnlineOrderListMainPageFragment tab5 = new OnlineOrderListMainPageFragment();
                return tab5;
            case 4:
                OnlineOrderClosedBillListMainPageFragment tab6 = new OnlineOrderClosedBillListMainPageFragment();
                return tab6;
            case 5:
                MainActivity.str_chk_map_layout = "0";
                MapLayoutMainPageFragment tab4 = new MapLayoutMainPageFragment();
                return tab4;
            default:
//                ReportActivity.LaySearchPrint.setVisibility(View.GONE);
//                ReportActivity.LaySearchPrint.setVisibility(View.VISIBLE);
//                ReportOtherFragment tab4 = new ReportOtherFragment();
//                return tab4;
                return null;
        }
    }
*/
//    @Override
//    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
//    }
//    @Override
//    public int getCount() {
//        return mFragmentList.size();
//    }
//
//    public void addFragment(Fragment fragment, String title, int position) {
//        mFragmentList.add(position, fragment);
//        mFragmentTitleList.add(position, title);
//    }
//
//
//    public void removeFragment(Fragment fragment, int position) {
//        mFragmentList.remove(position);
//        mFragmentTitleList.remove(position);
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mFragmentTitleList.get(position);
//    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}