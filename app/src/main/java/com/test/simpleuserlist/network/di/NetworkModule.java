package com.test.simpleuserlist.network.di;

import com.test.simpleuserlist.network.RandomUserService;
import com.test.simpleuserlist.util.Constants;
import com.test.simpleuserlist.util.di.UtilsScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    private RandomUserService mRandomUserService;

    public NetworkModule(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.RANDOM_USER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRandomUserService = retrofit.create(RandomUserService.class);
    }

    @Provides
    @UtilsScope
    RandomUserService provideRandomUserService(){
        return mRandomUserService;
    }
}
