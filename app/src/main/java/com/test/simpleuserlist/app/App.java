package com.test.simpleuserlist.app;

import android.app.Application;

import com.test.simpleuserlist.app.di.AppComponent;
import com.test.simpleuserlist.app.di.AppModule;
import com.test.simpleuserlist.app.di.DaggerAppComponent;
import com.test.simpleuserlist.network.di.NetworkModule;
import com.test.simpleuserlist.util.di.DaggerUtilsComponent;
import com.test.simpleuserlist.util.di.UtilsComponent;

public class App extends Application {
    private static AppComponent sAppComponent;
    private static UtilsComponent sUtilsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildAppComponent();
        buildUtilsComponent();
    }

    private void buildAppComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void buildUtilsComponent() {
        sUtilsComponent = DaggerUtilsComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }

    public static UtilsComponent getUtilsComponent(){
        return sUtilsComponent;
    }
}
