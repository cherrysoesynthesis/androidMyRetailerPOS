///*
// * = COPYRIGHT
// *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
// *   This software is supplied under the terms of a license agreement or nondisclosure
// *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
// *   disclosed except in accordance with the terms in that agreement.
// *     Copyright (C) 2019-? Computer Technology(Shenzhen) CO., LTD All rights reserved.
// * Description: // Detail description about the function of this module,
// *             // interfaces with the other modules, and dependencies.
// * Revision History:
// * Date	                 Author	                Action
// * 20191018 	        liujian                  Create
// */
//
//package com.pax.commonlib.utils.rx;
//
//import com.jakewharton.rxrelay2.PublishRelay;
//import com.jakewharton.rxrelay2.Relay;
//
//import io.reactivex.Observable;
//
//public class RxBus {
//    private static volatile RxBus instance;
//    private final Relay<Object> mBus;
//
//    public RxBus() {
//        this.mBus = PublishRelay.create().toSerialized();
//    }
//
//    public static RxBus getInstance() {
//        if (instance == null) {
//            synchronized (RxBus.class) {
//                if (instance == null) {
//                    instance = Holder.BUS;
//                }
//            }
//        }
//        return instance;
//    }
//
//    /**
//     * post a RxBusMessage.
//     */
//    public void post(Object obj) {
//        mBus.accept(obj);
//    }
//
//    public <T> Observable<T> toObservable(Class<T> tClass) {
//        return mBus.ofType(tClass);
//    }
//
//    /**
//     * observe RxBusMessage
//     */
//    public Observable<Object> toObservable() {
//        return mBus;
//    }
//
//    public boolean hasObservers() {
//        return mBus.hasObservers();
//    }
//
//    private static class Holder {
//        private static final RxBus BUS = new RxBus();
//
//        private Holder() {
//            //nothing
//        }
//    }
//}
