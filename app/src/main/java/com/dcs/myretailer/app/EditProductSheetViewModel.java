package com.dcs.myretailer.app;

import android.database.Cursor;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dcs.myretailer.app.Query.Query;

import java.util.ArrayList;
import java.util.List;

public class EditProductSheetViewModel extends ViewModel {
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
        Cursor c = Query.GetPLU(0);
        if (c != null){
            lstProductData.clear();
            while (c.moveToNext()){

//                lstProductData = Query.GetLstBook(c, lstProductData);
                String title = c.getString(0);
                String price = c.getString(1);
                String category = c.getString(5);
                String description = "";
                String thumbnail = c.getString(3);
                String productID = c.getString(4);
                String productCategoryID = c.getString(5);
                String productCategoryName = c.getString(6);
                String UUID = c.getString(7);
                String openPrice = c.getString(8);
                String remarks = c.getString(9);
                if ( openPrice == null){
                    openPrice = "0";
                }
                if ( remarks == null){
                    remarks = "0";
                }

                if (c.getInt(4) > 0) {
                    lstProductData.add(
                            new ProductData(
                                    title, price, category, description, thumbnail,
                                    productID, productCategoryID, productCategoryName, UUID, openPrice,remarks));
                }
            }
            c.close();
        }

        fruitList.setValue(lstProductData);
//        }, 5000);

    }
}
