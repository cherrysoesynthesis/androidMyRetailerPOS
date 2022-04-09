package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.dcs.myretailer.app.Category.CategoryModel;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.UUID;

public class AddCategorySheetFragment  extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText edit_text_product_name1,price;
    private static final int GALLERY_REQUEST_CODE = 001;
    //private static int _counter = 45;
    private static int _counter = 1;
    private String _stringVal;
    String path_ = "";
    ImageView img_choose_folder;
    public AddCategorySheetFragment() {
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
        return inflater.inflate(R.layout.fragment_add_category_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit_text_product_name1 = (EditText) view.findViewById(R.id.edit_text_product_name1);
        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
        LinearLayout choosePhoto = (LinearLayout) view.findViewById(R.id.choose_photo_id);
        img_choose_folder = (ImageView) view.findViewById(R.id.img_choose_folder);
        choosePhoto.setOnClickListener(this);

        String hideImage = Query.GetOptions(20);
        if (hideImage.equals("1")){
            img_choose_folder.setVisibility(View.GONE);
        }else {
            img_choose_folder.setVisibility(View.VISIBLE);
        }

        linear_close.setOnClickListener(this);
        Button btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
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
                    img_choose_folder.setImageURI(selectedImage);
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
                String name = edit_text_product_name1.getText().toString();
                if (name.isEmpty()){
                    new AlertDialog.Builder(getContext(), R.style.AlertDialogStyle)
                            .setMessage(Constraints.EmptyProduct)
                            .setCancelable(false)
                            .setNegativeButton(Constraints.OK, null)
                            .show();
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
                    //str_img = Query.getImageUrl(getContext(), img_choose_folder);
                    str_img = String.valueOf(Uri.parse(path_));
                }
//                Integer ID, String UUID, String name, String code, String image, Integer dateTime
                String catuuid = UUID.randomUUID().toString();
                CategoryModel ctM = new CategoryModel(0,catuuid,name,name,str_img,0);
                Query.saveCategory(ctM);
               // Query.saveCategory(name,str_img,getContext());
                Toast.makeText(getContext(), "Category Created Successfully!", Toast.LENGTH_LONG).show();
                MainActivity.St = "1";
                dismiss();

//                Intent i = new Intent(getContext(), MainActivity.class);
//                i.putExtra("name", "");
//                getContext().startActivity(i);
//                ((Activity)getContext()).finish();
                break;
        }
    }
}
