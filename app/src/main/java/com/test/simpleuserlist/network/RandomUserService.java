package com.test.simpleuserlist.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {
    @GET("api/")
    Observable<String> loadUsers(@Query("results") int resultsCount);
}
