package com.goodreadsapp.books;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.goodreadsapp.R;
import com.goodreadsapp.model.GoodreadsBook;
import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    ///implements Action1<List<GoodreadsBook>> {

  private List<GoodreadsBook> items = new ArrayList<>();
  private int itemLayoutId;

  public BooksAdapter() {
  }

  @Override public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new BookViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.book_item, parent, false));
  }

  @Override public void onBindViewHolder(final BookViewHolder holder, int position) {
    final GoodreadsBook book = items.get(position);
    holder.bindTo(book);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  void setItems(List<GoodreadsBook> goodreadsBooks) {
    items = goodreadsBooks;
  }

  /*
  @Override public void call(List<Book> books) {
    items = books;
    notifyDataSetChanged();
  }
  */

  final class BookViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.bookTv) TextView bookTv;

    BookViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bindTo(GoodreadsBook book) {
      bookTv.setText(book.getTitle());
    }
  }


}
