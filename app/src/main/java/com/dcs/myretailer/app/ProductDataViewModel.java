package com.dcs.myretailer.app;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ProductDataViewModel extends ViewModel {
    private String TAG = ProductDataViewModel.class.getSimpleName();

    private MutableLiveData<Integer> totalCount;
    private MutableLiveData<List<String>> fruitList;

    LiveData<List<String>> getFruitList() {
        if (fruitList == null) {
            fruitList = new MutableLiveData<>();
            loadFruits();
        }
        return fruitList;
    }


//    public MutableLiveData<Boolean> status = new MutableLiveData<>();
//
//    public MutableLiveData<Boolean> getStatusCode(String activationCode) {
//        status.postValue(Boolean.valueOf(false));
//        return status;
//    }
//

    public void setTotalCount(Integer value) {
        totalCount.setValue(value);
    }

    public LiveData<Integer> getTotalCount() {
        if (totalCount == null) {
            totalCount = new MutableLiveData<>();
            loadTotalCount();
        }
        return totalCount;
    }

    private void loadTotalCount() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            Integer tot = 0;
            totalCount.setValue(tot);
        }, 1000);

    }

    private void loadFruits() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            List<String> fruitsStringList = new ArrayList<>();
            fruitsStringList.add("Mango");
            fruitsStringList.add("Apple");
            fruitsStringList.add("Orange");
            fruitsStringList.add("Banana");
            fruitsStringList.add("Grapes");
            long seed = System.nanoTime();
            Collections.shuffle(fruitsStringList, new Random(seed));

            fruitList.setValue(fruitsStringList);
        }, 5000);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

}
