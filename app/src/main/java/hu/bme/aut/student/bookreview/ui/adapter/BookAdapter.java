package hu.bme.aut.student.bookreview.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.adapter.viewholder.BookViewHolder;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

/**
 * Adapter for displaying books in a RecyclerView.
 * <p>
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private LayoutInflater _layoutInflater;
    private List<Book> _items;

    private ItemClickListener<Book> _externalItemClickListener, _internalItemClickListener;

    public BookAdapter() {
        _internalItemClickListener = new ItemClickListener<Book>() {
            @Override
            public void onItemClicked(Book item) {
                if (_externalItemClickListener != null) {
                    _externalItemClickListener.onItemClicked(item);
                }
            }
        };
    }

    public void setItemClickListener(ItemClickListener<Book> itemClickListener) {
        _externalItemClickListener = itemClickListener;
    }


    public void setData(final List<Book> items) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                if (_items == null) {
                    return 0;
                }
                return _items.size();
            }

            @Override
            public int getNewListSize() {
                return items.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return _items.get(oldItemPosition).getId().equals(items.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return _items.get(oldItemPosition).getId().equals(items.get(newItemPosition).getId());
            }
        });
        _items = items;
        diffResult.dispatchUpdatesTo(this);
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
        holder.bind(_items.get(position), _internalItemClickListener);
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
