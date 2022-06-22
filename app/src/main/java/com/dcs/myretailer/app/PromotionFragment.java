package com.dcs.myretailer.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcs.myretailer.app.Query.Query;

import java.util.ArrayList;
import java.util.List;

public class PromotionFragment extends Fragment implements View.OnClickListener {
    //    List<Book> lstBook ;
    public static List<ProductData> lstProductData = new ArrayList<ProductData>();
    ArrayList<String> maphorizontalname = new ArrayList<String>();
    ArrayList<String> billnamehorizontal = new ArrayList<String>();
    ArrayList<String> mapname = new ArrayList<String>();
    ArrayList<String> billname = new ArrayList<String>();
    private int focusedItem = 0;
    TextView product_category_name;
    ImageView back;
    public static RecyclerView myrv;
    public static RecyclerView myrv2;
    private String BalNo = "0";

    ArrayList<String> promo_arr = new ArrayList<String>();

    public PromotionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate( R.layout.recyclerview_promotion_fragment, container, false);
        myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);

        getPromotion();
//        PromotionRecyclerAdapter adapter = new PromotionRecyclerAdapter(getContext(), lstBillDetails);
//        myrv.setAdapter(adapter);

        return view;
    }

    private void getPromotion() {
        promo_arr.clear();
        Query.GetPromotionInformation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.back:
//                Intent intent = new Intent(getActivity(),MainActivity.class);
//                intent.putExtra("name","Product____"+String.valueOf("0"));
//                getActivity().startActivity(intent);
//                break;
        }
    }
}