package com.dcs.myretailer.app;

import android.database.Cursor;
import android.os.Handler;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.Query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductMainPageViewModel extends ViewModel {
    private String TAG = ProductMainPageViewModel.class.getSimpleName();

    private MutableLiveData<List<ProductData>> fruitList;

    public LiveData<List<ProductData>> getProduct() {
        if (fruitList == null) {
            fruitList = new MutableLiveData<>();
            getProductAll();
        }
        return fruitList;
    }
    void getProductAll() {

        Handler myHandler = new Handler();
//        myHandler.postDelayed(() -> {

            List<ProductData> lstProductData = new ArrayList<>();
            Query.ClearRecyclerViewValues();
            Cursor c = null;

            if (ProductMainPageFragment.str_newText.length() > 0){
                c = Query.GetPLU(-1);
            }else {
                if (ProductMainPageFragment.product_ID > 0) {
                    c = Query.GetPLU(ProductMainPageFragment.product_ID);
                }else{
                    ProductMainPageFragment.binding.backLayoutId.setVisibility(View.GONE);
                    c = Query.GetPLU(0);
                }
            }
            if (c != null) {
                lstProductData.clear();
                while (c.moveToNext()) {
                    lstProductData = Query.GetBillDetailsProduct(c, lstProductData);
                }
                c.close();
            }

            fruitList.setValue(lstProductData);
//        }, 5000);

    }
}
