package com.goodreadsapp.api;

import com.goodreadsapp.BuildConfig;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class GoodreadsApiInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();

        final HttpUrl url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("key", BuildConfig.GOODREADS_API_KEY)
            .build();

        return chain.proceed(request.newBuilder().url(url).build());
    }

}
