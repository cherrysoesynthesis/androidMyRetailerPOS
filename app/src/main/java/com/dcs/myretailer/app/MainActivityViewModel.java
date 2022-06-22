package com.dcs.myretailer.app;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private String TAG = MainActivityViewModel.class.getSimpleName();

    private MutableLiveData<Integer> totalItem;
    private MutableLiveData<Integer> totalAmount;


    public void setTotalItem(Integer val) {
        totalItem.setValue(val);
    }

    public LiveData<Integer> getTotalItem() {
        if (totalItem == null) {
            totalItem = new MutableLiveData<>();
            loadTotaltotalItem();
        }
        return totalItem;
    }

    private void loadTotaltotalItem() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            Integer tot = 0;
            totalItem.setValue(tot);
        }, 1000);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }
}
