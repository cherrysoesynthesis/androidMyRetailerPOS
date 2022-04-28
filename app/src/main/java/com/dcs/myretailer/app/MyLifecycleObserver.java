package com.dcs.myretailer.app;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLifecycleObserver implements LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartListener(LifecycleOwner owner){

    }
}