package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityAddNewCategoryBinding;

public class AddNewCategoryActivityScreenSize {
    public AddNewCategoryActivityScreenSize(Context context, ActivityAddNewCategoryBinding binding){
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);

            LinearLayout.LayoutParams linearAddCat = new LinearLayout.LayoutParams(720,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearAddCat.leftMargin = 20;
            binding.btnAddCategory.setLayoutParams(linearAddCat);

            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                binding.btnAddCategory.setLayoutParams(new LinearLayout.LayoutParams(520, 90));
            }else if (terminalTypeVal.equals(Constraints.IMIN)){

                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            500);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams Layproduct = new LinearLayout.LayoutParams(720,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    Layproduct.leftMargin = 20;
                    Layproduct.bottomMargin = 20;
                    binding.layCatename.setLayoutParams(Layproduct);

                } else {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            600);
                    binding.layOverAll.setLayoutParams(linearOverAllParams);

                    LinearLayout.LayoutParams Layproduct = new LinearLayout.LayoutParams(650,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    Layproduct.leftMargin = 40;
                    Layproduct.bottomMargin = 20;
                    Layproduct.topMargin = 20;
                    binding.layCatename.setLayoutParams(Layproduct);

                    binding.btnAddCategory.setLayoutParams(new LinearLayout.LayoutParams(630, 90));
                }

            }else {
                binding.btnAddCategory.setLayoutParams(new LinearLayout.LayoutParams(650, 90));
            }
//            Resources r = getResources();
//            int px = Math.round(TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP, 300,r.getDisplayMetrics()));
//            btn_add_category.setWidth(px);
            binding.btnDeleteCategory.setVisibility(View.GONE);




            LinearLayout.LayoutParams linearbtnDeleteCategory = new LinearLayout.LayoutParams(320,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearbtnDeleteCategory.leftMargin = 30;
            binding.btnAddCategory.setLayoutParams(linearbtnDeleteCategory);
            binding.btnDeleteCategory.setLayoutParams(linearbtnDeleteCategory);

            String chkSubmitSalesOrNot = Query.GetOptions(22);
            if (chkSubmitSalesOrNot.equals("1")) {
                binding.editTextCategoryName.setEnabled(false);
                binding.editTextCategoryName.setInputType(InputType.TYPE_NULL);
                binding.btnAddCategory.setVisibility(View.GONE);
                binding.btnDeleteCategory.setVisibility(View.GONE);

            }
            if (terminalTypeVal.equals(Constraints.IMIN)){

                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            500);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);


                    LinearLayout.LayoutParams Layproduct = new LinearLayout.LayoutParams(720,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    Layproduct.leftMargin = 20;
                    Layproduct.bottomMargin = 20;

                    LinearLayout.LayoutParams linearBtn = new LinearLayout.LayoutParams(350,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    binding.btnAddCategory.setLayoutParams(linearBtn);
                    binding.btnDeleteCategory.setLayoutParams(linearBtn);
                } else {
                    LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            600);
                    linearOverAllParams.leftMargin = 10;
                    binding.layOverAll.setLayoutParams(linearOverAllParams);


                    LinearLayout.LayoutParams Layproduct = new LinearLayout.LayoutParams(660,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    Layproduct.leftMargin = 20;
                    Layproduct.bottomMargin = 20;

                    LinearLayout.LayoutParams linearBtn = new LinearLayout.LayoutParams(310,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearBtn.bottomMargin = 20;
                    linearBtn.leftMargin = 20;
                    binding.btnAddCategory.setLayoutParams(linearBtn);
                    binding.btnDeleteCategory.setLayoutParams(linearBtn);
                }

        }
    }
}
