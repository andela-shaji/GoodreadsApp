package com.goodreadsapp;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

    private final BooksApplication app;

    public AppModule(BooksApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ApplicationContext Context provideApplicationContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    String providerTestString() {
        return "TEST2";
    }
}
