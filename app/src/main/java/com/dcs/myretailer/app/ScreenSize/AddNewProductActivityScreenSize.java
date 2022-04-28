package com.dcs.myretailer.app.ScreenSize;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.databinding.ActivityAddNewProductBinding;

public class AddNewProductActivityScreenSize {
    public AddNewProductActivityScreenSize(Context context,
                                           ActivityAddNewProductBinding binding,
                                           String ID){
        String device = Query.GetDeviceData(Constraints.DEVICE);
        String  terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        Log.i("sdf__device","device___"+device);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);

            LinearLayout.LayoutParams Layproduct = new LinearLayout.LayoutParams(720,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            Layproduct.leftMargin = 20;
            Layproduct.bottomMargin = 20;
            binding.layProductName.setLayoutParams(Layproduct);
            binding.layPrice.setLayoutParams(Layproduct);
            binding.layProductCode.setLayoutParams(Layproduct);
            binding.layProductCategory.setLayoutParams(Layproduct);
            binding.layTax.setLayoutParams(Layproduct);
            binding.layOnHandQty.setLayoutParams(Layproduct);
            binding.layopenprice.setLayoutParams(Layproduct);
            binding.layremarks.setLayoutParams(Layproduct);
            binding.layKitchenPrinter.setLayoutParams(Layproduct);

            binding.stockIn.layStockInOverAll.setLayoutParams(Layproduct);
            binding.stockAdj.layStockInOverAll.setLayoutParams(Layproduct);
            binding.stockAdj.layadj.setLayoutParams(Layproduct);
            LinearLayout.LayoutParams Layproductstockin = new LinearLayout.LayoutParams(320,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            Layproductstockin.leftMargin = 20;
            //Layproductstockin.bottomMargin = 20;
            binding.stockIn.layStockQty.setLayoutParams(Layproductstockin);
            binding.stockIn.LayStockInDate.setLayoutParams(Layproductstockin);

            LinearLayout.LayoutParams Layproductstockadj = new LinearLayout.LayoutParams(320,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.stockAdj.layStockQty.setLayoutParams(Layproductstockadj);

            LinearLayout.LayoutParams Layproductstockadjdate = new LinearLayout.LayoutParams(320,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            Layproductstockadjdate.leftMargin = 20;
            binding.stockAdj.LayStockInDate.setLayoutParams(Layproductstockadjdate);

            LinearLayout.LayoutParams btnStockk = new LinearLayout.LayoutParams(700,
                    50);
            btnStockk.leftMargin = 20;
            binding.stockIn.btnAddStock.setLayoutParams(btnStockk);
            LinearLayout.LayoutParams btnStockkadj = new LinearLayout.LayoutParams(700,
                    50);
            binding.stockAdj.btnAddAdjStock.setLayoutParams(btnStockkadj);

        }else {
            LinearLayout.LayoutParams Layproductstockinbtn = new LinearLayout.LayoutParams(650,
                    90);
            Layproductstockinbtn.leftMargin = 40;
            Layproductstockinbtn.bottomMargin = 20;
            binding.btnAddProduct.setLayoutParams(Layproductstockinbtn);


            LinearLayout.LayoutParams Layproduct = new LinearLayout.LayoutParams(640,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            Layproduct.leftMargin = 40;
            Layproduct.bottomMargin = 20;
            binding.layopenprice.setLayoutParams(Layproduct);
            binding.layremarks.setLayoutParams(Layproduct);
        }

        if (ID.equals("null")){

            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearOverAllParams.leftMargin = 10;
                binding.layOverAll.setLayoutParams(linearOverAllParams);

                binding.btnAddProduct.setLayoutParams(new LinearLayout.LayoutParams(520, 90));
            }else if (terminalTypeVal.equals(Constraints.IMIN)){
                if (device.equals("M2-Max")) {
                    LinearLayout.LayoutParams Layproductstockinbtn = new LinearLayout.LayoutParams(720,
                            90);
                    Layproductstockinbtn.leftMargin = 20;
                    binding.btnAddProduct.setLayoutParams(Layproductstockinbtn);
                } else {
                    LinearLayout.LayoutParams Layproductstockinbtn = new LinearLayout.LayoutParams(640,
                            90);
                    Layproductstockinbtn.leftMargin = 40;
                    binding.btnAddProduct.setLayoutParams(Layproductstockinbtn);
                }
            }else {
                LinearLayout.LayoutParams Layproductstockinbtn = new LinearLayout.LayoutParams(650,
                        90);
                Layproductstockinbtn.leftMargin = 40;
                binding.btnAddProduct.setLayoutParams(Layproductstockinbtn);
            }

        }else{

            LinearLayout.LayoutParams Layproductstockinbtn = new LinearLayout.LayoutParams(310,
                    90);
            Layproductstockinbtn.leftMargin = 40;
            Layproductstockinbtn.bottomMargin = 20;
            binding.btnAddProduct.setLayoutParams(Layproductstockinbtn);
            binding.btnDeleteProduct.setLayoutParams(Layproductstockinbtn);
        }

        binding.editTextProductCode.setInputType(InputType.TYPE_NULL);
    }
}
