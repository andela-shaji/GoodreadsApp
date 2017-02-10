package com.goodreadsapp.books;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.goodreadsapp.BooksApplication;
import com.goodreadsapp.R;
import com.goodreadsapp.api.BooksApi;
import com.goodreadsapp.model.GoodreadsBook;
import com.jakewharton.rxbinding2.widget.RxTextView;
import java.util.Arrays;
import javax.inject.Inject;

public class BooksView extends LinearLayout implements BooksScreen {

    private static final String TAG = "BooksView";

    BooksPresenter presenter;

    @BindView(R.id.search) EditText searchEt;
    @BindView(R.id.booksList) RecyclerView booksList;

    @Inject BooksApi api;
    private BooksAdapter booksAdapter;

    public BooksView(Context context) {
        this(context, null);
    }

    public BooksView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public BooksView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs);
    }

    public BooksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        ((BooksApplication) context.getApplicationContext()).getAppComponent().inject(this);

        final View view = LayoutInflater.from(context).inflate(R.layout.books_view, this, true);
        ButterKnife.bind(this, view);

        booksAdapter = new BooksAdapter();

        booksList.setLayoutManager(new LinearLayoutManager(getContext()));
        booksList.setAdapter(booksAdapter);

        presenter = new BooksPresenter(this, api);
        presenter.handleSearch(RxTextView.textChanges(searchEt).skipInitialValue());
    }

    @Override
    public void displayBook(GoodreadsBook book) {
        booksAdapter.setItems(Arrays.asList(book));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.unbind();
    }
}
