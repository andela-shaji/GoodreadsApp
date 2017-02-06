package com.goodreadsapp.books;

import com.goodreadsapp.api.BooksApi;
import com.goodreadsapp.model.GoodreadsBook;
import com.goodreadsapp.model.GoodreadsResponse;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class BooksPresenterTest {

    private BooksPresenter presenter;

    @Mock BooksScreen screen;
    @Mock BooksApi booksApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new BooksPresenter(screen, booksApi);
    }

    @Test
    public void searchByTitleSuccessDisplaysBook() {
        //given
        GoodreadsResponse mockResponse = mock(GoodreadsResponse.class);
        Mockito.when(mockResponse.getFirstBook()).thenReturn(mock(GoodreadsBook.class));

        Mockito.when(booksApi.searchByTitle(anyString()))
            .thenReturn(Observable.just(mockResponse));

        //when
        presenter.searchByTitle("Harry Potter");

        //then
        Mockito.verify(screen, times(1)).displayBook(any(GoodreadsBook.class));
    }
}
