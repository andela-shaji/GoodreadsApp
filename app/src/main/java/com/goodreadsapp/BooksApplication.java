package com.goodreadsapp;

import android.app.Application;
import android.util.Log;
import javax.inject.Inject;

public class BooksApplication extends Application {

    private static final String TAG = "BooksApplication";

    private AppComponent appComponent;

    @Inject String testString;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = AppComponent.Initializer.init(this);
        appComponent.inject(this);

        Log.d(TAG, "Injected : " + testString);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
