package com.goodreadsapp.books;

import com.goodreadsapp.api.BooksApi;
import com.goodreadsapp.model.GoodreadsResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

class BooksPresenter {

    private static final String TAG = "BooksPresenter";

    private BooksScreen screen;
    private final BooksApi api;
    private CompositeDisposable compositeDisposable;

    BooksPresenter(BooksScreen screen, BooksApi api) {
        this.screen = screen;
        this.api = api;
        compositeDisposable = new CompositeDisposable();
    }

    private Observable<GoodreadsResponse> searchByTitle(String title) {
        return api.searchByTitle(title);
    }

    void handleSearch(Observable<CharSequence> searchEtChanges) {
        compositeDisposable.add(searchEtChanges.debounce(500, TimeUnit.MILLISECONDS)
            .flatMap(new Function<CharSequence, Observable<GoodreadsResponse>>() {
                @Override
                public Observable<GoodreadsResponse> apply(CharSequence charSequence)
                    throws Exception {
                    return searchByTitle(charSequence.toString());
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<GoodreadsResponse>() {
                @Override
                public void accept(GoodreadsResponse goodreadsResponse) throws Exception {
                    screen.displayBook(goodreadsResponse.getFirstBook());
                }
            }));
    }

    void unbind() {
        compositeDisposable.dispose();
    }

}
