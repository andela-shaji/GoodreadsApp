package com.goodreadsapp;

import com.goodreadsapp.api.ApiModule;
import com.goodreadsapp.books.BooksView;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class , ApiModule.class} )
public interface AppComponent {

    void inject(BooksApplication application);

    void inject(BooksView booksView);

    final class Initializer {
        private Initializer() {
        }

        static AppComponent init(BooksApplication app) {
            return DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .build();
        }
    }
}
