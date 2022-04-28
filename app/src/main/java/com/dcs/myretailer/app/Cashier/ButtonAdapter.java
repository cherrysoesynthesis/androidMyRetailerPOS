package com.dcs.myretailer.app.Cashier;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ActivityButtonListviewBinding;

import java.util.ArrayList;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.MyViewHolder> {
        private Context mContext ;
        ArrayList<Integer> ModifierID;
        ArrayList<String> ModifierName;
        ArrayList<String> ModifierPrice;
        ArrayList<String> ModiName = new ArrayList<String>();
        ArrayList<String> ItemID = new ArrayList<String>();
        ArrayList<Integer> ModiID = new ArrayList<Integer>();
        public static ArrayList<Integer> ID = new ArrayList<Integer>();
        ActivityButtonListviewBinding binding = null;
        public ButtonAdapter(Context mContext, ArrayList<Integer> ModifierID, ArrayList<String> ModifierName, ArrayList<String> ModifierPrice) {
            this.mContext = mContext;
            this.ModifierID = ModifierID;
            this.ModifierName = ModifierName;
            this.ModifierPrice = ModifierPrice;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            binding = DataBindingUtil.inflate(mInflater,R.layout.activity_button_listview,parent,false);
            return new MyViewHolder(binding);
        }
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.binding.btnModifier.setText(ModifierName.get(position)+"\n"+ModifierPrice.get((position)));


            String sql = " SELECT ModiName,ItemID,ID FROM PLUModi " +
                    " WHERE  BillNo = '"+MainActivity.strbillNo+"' AND DetailsBillProductID = '" + EditProductSheetFragment.ID + "' " ;
//            String sql = " SELECT ModiName,ItemID,ID FROM PLUModi " +
//                    " WHERE  BillNo = '"+MainActivity.strbillNo+"' " ;


            //DBFunc.ExecQuery(sql, false);

            Cursor c = DBFunc.Query(sql, false);
            if (c != null) {
                ModiName.clear();
                ItemID.clear();
                ModiID.clear();
                while (c.moveToNext()){
                    ModiName.add(c.getString(0));
                    ItemID.add(c.getString(1));
                    ModiID.add(c.getInt(2));
                    //ID.add(Integer.parseInt(ItemID.get(position)));
                }
                c.close();
            }
            if (ModiName.contains((ModifierName.get(position)))) {
                //EditProductSheetFragment.modiName.add(ModifierName.get(position));
                holder.binding.btnModifier.setBackgroundColor(ContextCompat.getColor(mContext, R.color.nasty_green));
                holder.binding.btnModifier.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                ID.add(ModifierID.get(position));

                EditProductSheetFragment.modiID.add(ModifierID.get(position));
                EditProductSheetFragment.modiName.add(ModifierName.get(position));
                EditProductSheetFragment.modiPrice.add(ModifierPrice.get((position)));
            }

            holder.binding.btnModifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ID.contains(ModifierID.get(position))){
                        ID.remove(ModifierID.get(position));
                        EditProductSheetFragment.modiID.remove(ModifierID.get(position));
                        //EditProductSheetFragment.ItemID.remove(String.valueOf(ItemID.get(position)));
                        EditProductSheetFragment.modiName.remove(ModifierName.get(position));
                        EditProductSheetFragment.modiPrice.add(ModifierPrice.get((position)));
                        //EditProductSheetFragment.UnSelectedmodiID.add(ModifierID.get(position));
                        //if (ItemID.size() > 0) {
                           // EditProductSheetFragment.UnSelectedItem.add(ItemID.get(position));
                            EditProductSheetFragment.UnSelectedItem.add(String.valueOf(ModifierID.get(position)));
                        //}
                        holder.binding.btnModifier.setBackgroundColor(ContextCompat.getColor(mContext, R.color.pinlocktextcolor));
                        holder.binding.btnModifier.setTextColor(ContextCompat.getColor(mContext, R.color.mine_shaft));
                    }else {
                        EditProductSheetFragment.modiID.add(ModifierID.get(position));
                        EditProductSheetFragment.modiName.add(ModifierName.get(position));
                        EditProductSheetFragment.modiPrice.add(ModifierPrice.get((position)));
                        //EditProductSheetFragment.ItemID.add(String.valueOf(ItemID.get(position)));
                        EditProductSheetFragment.UnSelectedItem.remove(String.valueOf(ModifierID.get(position)));
                        //if (ItemID.size() > 0) {
                            //EditProductSheetFragment.UnSelectedItem.remove(ItemID.get(position));
                        //}
                        holder.binding.btnModifier.setBackgroundColor(ContextCompat.getColor(mContext, R.color.nasty_green));
                        holder.binding.btnModifier.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                        ID.add(ModifierID.get(position));
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            return ModifierID.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ActivityButtonListviewBinding binding;

            public MyViewHolder(ActivityButtonListviewBinding binding) {
                super(binding.getRoot());
                this.binding = (ActivityButtonListviewBinding) binding;
            }
        }


    }
