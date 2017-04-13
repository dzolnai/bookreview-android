package hu.bme.aut.student.bookreview.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.adapter.viewholder.BookViewHolder;

/**
 * Adapter for displaying books in a RecyclerView.
 *
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private LayoutInflater _layoutInflater;
    private List<Book> _items;

    public void setData(List<Book> items) {
        _items = items;
        notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (_layoutInflater == null) {
            _layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = DataBindingUtil.inflate(_layoutInflater, R.layout.item_book, parent, false).getRoot();
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(_items.get(position));
    }

    @Override
    public int getItemCount() {
        if (_items == null) {
            return 0;
        } else {
            return _items.size();
        }
    }
}
