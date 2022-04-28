package com.dcs.myretailer.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     OpenDiscountFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class OpenDiscountFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";

    // TODO: Customize parameters
    public static OpenDiscountFragment newInstance(int itemCount) {
        final OpenDiscountFragment fragment = new OpenDiscountFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    List<OpenDiscountModel> aa = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_open_discount_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



//        new LongOperation(getContext(),recyclerView).execute();

        if (aa != null && aa.size() > 0) {
            try {
                recyclerView.setAdapter(new ItemAdapter(aa));
            } catch (java.lang.NullPointerException e) {
                Log.i("DFDFDF__", "e__" + e.getMessage());
            }
        }

    }
//
//    private final class LongOperation extends AsyncTask<Void, Void, String> {
//        Context context;
//        RecyclerView rv;
//        public LongOperation(Context context, RecyclerView recyclerView) {
//            context = context;
//            rv = recyclerView;
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
////            for (int i = 0; i < 5; i++) {
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    // We were cancelled; stop sleeping!
////                }
////            }
//            return "Executed";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            AppExecutors.getInstance().diskIO().execute(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//
//                            OpenDiscountDatabase openDisDB = OpenDiscountDatabase.getInstance(context);
//
//                            List<OpenDiscountModel> getdismodel = openDisDB.openDiscountDao().GetOpenDiscount();
//
//                            aa = getdismodel;
//                        }
//                    }
//            );
//        }
//    }
    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_open_discount_list_dialog_item, parent, false));
            text = itemView.findViewById(R.id.text);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

//        private final int mItemCount;
//
//        ItemAdapter(int itemCount) {
//            mItemCount = itemCount;
//        }
        List<OpenDiscountModel> dismodel;
        public ItemAdapter(List<OpenDiscountModel> getdismodel) {
            this.dismodel = getdismodel;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.text.setText(dismodel.get(position).openDiscountName);
        }

        @Override
        public int getItemCount() {
            return dismodel.size();
        }

    }

}