package com.goodreadsapp.books;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.goodreadsapp.BooksApplication;
import com.goodreadsapp.R;
import com.goodreadsapp.api.BooksApi;
import com.goodreadsapp.model.GoodreadsBook;
import javax.inject.Inject;

public class BooksView extends LinearLayout implements BooksScreen {

    private static final String TAG = "BooksView";

    BooksPresenter presenter;

    @Inject BooksApi api;

    public BooksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        ((BooksApplication) context.getApplicationContext()).getAppComponent().inject(this);

        LayoutInflater.from(context).inflate(R.layout.books_view, this, true);

        presenter = new BooksPresenter(this, api);
        presenter.searchByTitle("Harry potter");

        //RxTextView.textChanges().
        //Log.d(TAG, "FROM BooksView testString injected is = " + testString);
    }

    @Override
    public void displayBook(GoodreadsBook book) {

    }
}
