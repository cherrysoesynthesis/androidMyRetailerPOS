package com.dcs.myretailer.app.Cashier;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentAddQuickProductSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddQuickProductSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText edit_text_quick_edit_name;
    EditText edit_text_quick_edit_price;
    FragmentAddQuickProductSheetDialogBinding binding ;
    public AddQuickProductSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_quick_product_sheet_dialog, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_quick_product_sheet_dialog, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAdd.setOnClickListener(this);
        binding.linearClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_close:
                dismiss();
                break;
            case R.id.btn_add:
                String name = binding.editTextQuickEditName.getText().toString();
                String price = binding.editTextQuickEditPrice.getText().toString();
                //String itemRemarks = binding.quickProductSheetItemremarks.getText().toString();
                if (name.isEmpty()){
                    new AlertDialog.Builder(getContext(), R.style.AlertDialogStyle)
                            .setMessage(Constraints.EmptyProduct)
                            .setCancelable(false)
                            .setNegativeButton(Constraints.OK, null)
                            .show();
                    return;
                }
                if (price.isEmpty()){
                    new AlertDialog.Builder(getContext(), R.style.AlertDialogStyle)
                            .setMessage(Constraints.EmptyPrice)
                            .setCancelable(false)
                            .setNegativeButton(Constraints.OK, null)
                            .show();
                    return;
                }
                //String chk_hide_img = Query.GetOptions(20);
                Bitmap bitmap = null;
//                if (chk_hide_img.equals("1")){
//                    bitmap = null;
//                }else {
//                    BitmapDrawable drawable = null;
////
//                    Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                            R.drawable.black_photo);
//                    drawable = new BitmapDrawable(this.getResources(), icon);
//
//                    bitmap = drawable.getBitmap();
//                }
                Boolean numeric = true;
                try {
                    Double num = Double.parseDouble(price);
                } catch (NumberFormatException e) {
                    numeric = false;
                }

                if(!numeric) {
                    Query.ErrorDialog(getContext(), Constraints.ISNOTNUMBER);
                    return;
                }
                Query.SavePLUQuick(name,"",price,"0","0","1",
                        "1","","","","","",
                        "0","0",getContext());
                Toast.makeText(getContext(), "Quick Product Created Successfully!", Toast.LENGTH_LONG).show();
//                MainActivity.str_quick_product = "1";
//                MainActivity.St = "1";
                dismiss();

                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        onResume();

        ProductMainPageFragment.RunAuto();

        super.onDismiss(dialog);

    }

}

//        extends BottomSheetDialogFragment {
//    public AddQuickProductSheetFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_quick_product_sheet_dialog, container, false);
//    }
//
////    @Override
////    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
////        super.onViewCreated(view, savedInstanceState);
////        LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.addproduct_layout);
////        myLayt.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Log.i("hi","Click1");
//////                AddProuctSheetFragment addProductSheetFragment = new AddProductSheetFragment();
//////                addProductSheetFragment.show(getSupportFragmentManager(), addProductSheetFragment.getTag());
////            }
////        });
////    }
//}
