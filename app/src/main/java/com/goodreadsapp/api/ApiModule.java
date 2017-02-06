package com.goodreadsapp.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class ApiModule {

    //TODO: @Suada provide this as a string with a qualifier @Goodreads Url
    private final static String GOODREADS_URL = "https://www.goodreads.com/";

    //Retrofit
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
            .client(client)
            .baseUrl(GOODREADS_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(GoodreadsApiInterceptor goodreadsApiInterceptor) {
        return new OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(goodreadsApiInterceptor)
            .build();
    }

    @Provides
    @Singleton
    GoodreadsApiInterceptor provideGoodreadsInterceptor() {
        return new GoodreadsApiInterceptor();
    }

    @Provides
    @Singleton
    BooksApi provideBooksApi(Retrofit retrofit) {
        return retrofit.create(BooksApi.class);
    }

}
