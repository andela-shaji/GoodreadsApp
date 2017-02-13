package com.goodreadsapp.books;

import com.goodreadsapp.api.BooksApi;
import com.goodreadsapp.model.GoodreadsBook;
import com.goodreadsapp.model.GoodreadsResponse;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BooksPresenterTest {

    private BooksPresenter presenter;

    @Mock BooksScreen screen;
    @Mock BooksApi booksApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new BooksPresenter(screen, booksApi, Schedulers.computation(),
            Schedulers.computation());
    }

    @Test
    public void searchByTitleSuccessDisplaysBook() {
        //given
        GoodreadsResponse mockResponse = mock(GoodreadsResponse.class);
        when(mockResponse.getFirstBook()).thenReturn(mock(GoodreadsBook.class));

        when(booksApi.searchByTitle(anyString())).thenReturn(Observable.just(mockResponse));

        //when
        presenter.handleSearch(
            Observable.<CharSequence>fromArray("H", "A", "R", "R", "Y", " ", "P", "O"));

        //then
        verify(screen, times(1)).displayBook(any(GoodreadsBook.class));
    }

    @Test
    public void unbindDisposesObservables() {
        //give

        //when
        presenter.unbind();

        //then
    }
}
