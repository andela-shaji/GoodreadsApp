package com.goodreadsapp.api;

import com.goodreadsapp.model.GoodreadsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksApi {

    @GET("search")
    Observable<GoodreadsResponse> searchByTitle(@Query("q") String title);

}
