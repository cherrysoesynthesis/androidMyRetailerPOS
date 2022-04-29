package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.ActivityFtpRecyclerviewBinding;
import com.dcs.myretailer.app.databinding.ActivityMallInterfaceBinding;
import com.dcs.myretailer.app.databinding.CardveiwItemBookBinding;

import java.util.List;

public class MallInterfaceRecyclerViewAdapter extends RecyclerView.Adapter<MallInterfaceRecyclerViewAdapter.MallInterfaceViewHolder> {
    ActivityFtpRecyclerviewBinding binding;
    Context context;
    List<FTP> ftp;
    public MallInterfaceRecyclerViewAdapter(Context ctx, List<FTP> ftpe) {
        context = ctx;
        ftp = ftpe;
   }

    @NonNull
    @Override
    public MallInterfaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_ftp_recyclerview, parent, false);
        return new MallInterfaceRecyclerViewAdapter.MallInterfaceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MallInterfaceViewHolder holder, int position) {

        binding.ftpTextValue.setText(ftp.get(position).getTextView());
        binding.ftpEditTextValue.setText(ftp.get(position).getEditView());
    }

    @Override
    public int getItemCount() {
        return ftp.size();
    }
    public class MallInterfaceViewHolder extends RecyclerView.ViewHolder{
        ActivityFtpRecyclerviewBinding binding;
        public MallInterfaceViewHolder(ActivityFtpRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = (ActivityFtpRecyclerviewBinding) binding;

        }
    }
}
