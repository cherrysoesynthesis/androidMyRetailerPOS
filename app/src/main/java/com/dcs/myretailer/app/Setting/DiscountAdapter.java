package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.List;

class DiscountAdapter extends BaseAdapter {
    Context context;
    List<DiscountClass> discounts;
    int flags[];
    LayoutInflater inflter;

    public DiscountAdapter(Context applicationContext, List<DiscountClass> discounts) {
        this.context = context;
        this.discounts = discounts;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return discounts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_discount_listview, null);
        TextView tax_name = (TextView) view.findViewById(R.id.discount_name);
        if (discounts.get(i).getDiscountType() != null) {
            if (discounts.get(i).getDiscountType().equals("$ Dollar Value")) {
                try {
                    tax_name.setText(discounts.get(i).getDiscount_Name() + "(" + String.format("%.2f", Double.valueOf(String.valueOf(discounts.get(i).getDiscount_Value()))) + ")");
                }catch (NumberFormatException e){
                    tax_name.setText(discounts.get(i).getDiscount_Name() + "( 0.00 )");

                }
            } else {
                tax_name.setText(discounts.get(i).getDiscount_Name() + "(" + discounts.get(i).getDiscount_Value() + "%)");
            }

            if (discounts.get(i).getOpenDiscountStatus() != null && discounts.get(i).getOpenDiscountStatus().equals("1")){
                tax_name.setText(tax_name.getText().toString() + "( OpenDiscount )");
            }
        }
        return view;
    }
}
