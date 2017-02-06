package com.goodreadsapp.books;

import com.goodreadsapp.api.BooksApi;
import com.goodreadsapp.model.GoodreadsResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

class BooksPresenter {

    private static final String TAG = "BooksPresenter";

    private BooksScreen screen;
    private final BooksApi api;

    BooksPresenter(BooksScreen screen, BooksApi api) {
        this.screen = screen;
        this.api = api;
    }

    void searchByTitle(String title) {
        getGoodreadsResponseObservable(title)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<GoodreadsResponse>() {
            @Override
            public void accept(GoodreadsResponse goodreadsResponse) throws Exception {
                screen.displayBook(goodreadsResponse.getFirstBook());
            }
        });
    }

    private Observable<GoodreadsResponse> getGoodreadsResponseObservable(String title) {
        return api.searchByTitle(title);
    }
}
