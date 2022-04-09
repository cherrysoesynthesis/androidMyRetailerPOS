package com.dcs.myretailer.app;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules=IngenicoModule.class)
public interface IngenicoComponent {
    //void inject(IngenicoModule ingenicoModule);
    void inject(RemarkMainActivity sampleActivity);
}
