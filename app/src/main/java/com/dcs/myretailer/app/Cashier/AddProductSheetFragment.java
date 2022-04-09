package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.CheckOutActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.FragmentAddProductSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Date;

//import android.support.design.widget.BottomSheetDialogFragment;

public class AddProductSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
//    EditText edit_text_product_name1,price;
//    ImageView addition,subtraction;
    private static final int GALLERY_REQUEST_CODE = 001;
    //private static int _counter = 45;
    private static int _counter = 1;
    private String _stringVal;
//    TextView qun;
    String path_ = "";
//    ImageView img_choose_folder;
    FragmentAddProductSheetDialogBinding binding = null;
    public AddProductSheetFragment() {
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
//        return inflater.inflate(R.layout.fragment_add_product_sheet_dialog, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_product_sheet_dialog, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.choosePhotoId.setOnClickListener(this);
        binding.linearClose.setOnClickListener(this);
        binding.qun.setText("1");
        binding.btnAdd.setOnClickListener(this);
        binding.addition.setOnClickListener(this);
        binding.subtraction.setOnClickListener(this);
    }
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        //String[] mimeTypes = {"image/jpeg", "image/png"};
//        String[] mimeTypes = {"image/jpeg", "image/png"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    path_ = data.getData().toString();
                    //SocketFactorySolved();
                    //doFileUpload(path_);
                    Uri selectedImage = data.getData();
                    binding.imgChooseFolder.setImageURI(selectedImage);
                    break;
            }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_photo_id:
                pickFromGallery();
                break;
            case R.id.linear_close:
                dismiss();
                break;
            case R.id.btn_add:
                String name = binding.editTextProductName1.getText().toString();
                String pprice = binding.editTextAddProductPrice.getText().toString();
                String qty = binding.qun.getText().toString();
                if (name.isEmpty()){
                    Query.ErrorDialog(getContext(), Constraints.EmptyProduct);
                    return;
                }
                if (pprice.isEmpty()){
                    Query.ErrorDialog(getContext(), Constraints.EmptyPrice);
                    return;
                }
                boolean numeric = true;

                try {
                    Double num = Double.parseDouble(pprice);
                } catch (NumberFormatException e) {
                    numeric = false;
                }

                if(!numeric) {
                    Query.ErrorDialog(getContext(), Constraints.ISNOTNUMBER);
                    return;
                }
//                //String chk_hide_img = Query.GetOptions(20);
//                BitmapDrawable drawable = null;
//                Bitmap bitmap = null;
//                if (img_choose_folder.getDrawable() == null){
//                    bitmap = null;
//                }else{
//                    drawable = (BitmapDrawable) img_choose_folder.getDrawable();
//                    bitmap = drawable.getBitmap();
//                }
                String str_img = "";
                if (path_.length() > 0) {
//                    str_img = Query.getImageUrl(getContext(), binding.imgChooseFolder);
                    str_img = String.valueOf(Uri.parse(path_));
                }
                String str_imgItemId = "";
                String str_imgUrl = "";
                String str_imgType = "";
                String str_imgfileName = "";
                String base64imgValue = "";

                Query.SavePLUQuick(name,"",pprice,"0",str_img,
                        str_imgItemId,str_imgUrl,str_imgType,str_imgfileName,base64imgValue,"1",
                        "1","0","0",getContext());

                MainActivity.str_quick_add_product = "1";

                ArrayList<String> sldNameArr = new ArrayList<String>();
                ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
                ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
                ArrayList<String> sltBillDisArr = new ArrayList<String>();
                ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
                ArrayList<String> sldIDArr = new ArrayList<String>();
                ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
                ArrayList<String> intTableNo = new ArrayList<String>();
                ArrayList<String> vchQueueNo = new ArrayList<String>();
                ArrayList<Integer> Modifier_ID = new ArrayList<Integer>();
                ArrayList<String> slddisID = new ArrayList<String>();
                ArrayList<String> slddisName = new ArrayList<String>();
                ArrayList<String> slddisTypeName = new ArrayList<String>();
                ArrayList<String> slddisType = new ArrayList<String>();
                ArrayList<String> slddisValue = new ArrayList<String>();
                Integer countSelectedArr = 1;
                String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
                String paymenttype = "Cash";
                String status = "SALES";
                String Itemstatus = "SALES";
                Double sub_total = 0.0;
                Double amount = 0.0;
                //Integer qty = 0;
                Integer totalItems = 0;
                sldNameArr.add(name);
                sldQtyArr.add(1);
                sltPriceTotalArr.add(pprice);
                sltBillDisArr.add("0");
                sltCategoryNameArr.add("0");
                //qty = 2;
                totalItems = 2;
                Integer id = Query.findLatestID("ID","PLU",true);
                sldIDArr.add(String.valueOf(id));
                sltCategoryIDArr.add("0");
                intTableNo.add("0");
                vchQueueNo.add("0");
                Modifier_ID.add(0);
                slddisID.add("0");
                slddisName.add("0");
                slddisTypeName.add("0");
                slddisType.add("0");
                slddisValue.add("0");

                for (int i = 0 ; i < Integer.parseInt(binding.qun.getText().toString()); i++) {
                    Integer billIDDD = Query.getBillID(MainActivity.strbillNo);
//                    CheckOutActivity.SaveBill(billIDDD,MainActivity.strbillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr,
//                            sltCategoryNameArr,
//                            Integer.parseInt(qty), totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                            sub_total, amount, paymenttype, Modifier_ID, intTableNo, vchQueueNo,Itemstatus,slddisID,slddisName,
//                            slddisTypeName,slddisType,slddisValue);

                    CheckOutActivity.saveDetailsBillProduct(billIDDD, MainActivity.strbillNo, name
                             ,pprice,
                            "0", "0",
                            1, String.valueOf(id), "0",
                            dateFormat3,   Modifier_ID,
                            MainActivity.inv_table, MainActivity.tbl_name,
                            status,Itemstatus, "0", "0", "0",
                            "0", "0",0,"0","");
                }

                Toast.makeText(getContext(), "Product Created Successfully!", Toast.LENGTH_LONG).show();
                MainActivity.St = "1";
//                MainActivity.St = "1";
                dismiss();
                //MainActivity.St = "1";
                ProductMainPageFragment.RunAuto();
                MainActivity.getBillByBillNo();

                break;
            case R.id.addition:
//                _counter++;
//                _stringVal = Integer.toString(_counter);
//                qun.setText(_stringVal);
                Integer c = Integer.parseInt(binding.qun.getText().toString());
                c++;
                _stringVal = Integer.toString(c);
                binding.qun.setText(_stringVal);
                break;
            case R.id.subtraction:
                Integer c_Sub = Integer.parseInt(binding.qun.getText().toString());
//                _counter--;
//                if (_counter > 0 ){
//                    _counter = 1;
//                }
//                _stringVal = Integer.toString(_counter);
                c_Sub--;
//                if (c_Sub > 0 ){
//                    c_Sub = 1;
//                }
                if (c_Sub < 0 ){
                    c_Sub = 1;
                }
                _stringVal = Integer.toString(c_Sub);
                binding.qun.setText(_stringVal);
                break;
        }
    }
}
