package hu.bme.aut.student.bookreview.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.model.entity.Review;
import hu.bme.aut.student.bookreview.ui.adapter.viewholder.ReviewViewHolder;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

/**
 * Adapter for the review items.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private LayoutInflater _layoutInflater;
    private List<Review> _items;
    private ItemClickListener<Review> _externalItemClickListener, _internalItemClickListener;

    public ReviewAdapter() {
        _internalItemClickListener = new ItemClickListener<Review>() {
            @Override
            public void onItemClicked(Review item) {
                if (_externalItemClickListener != null) {
                    _externalItemClickListener.onItemClicked(item);
                }
            }
        };
    }

    public void setData(List<Review> items) {
        _items = items;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener<Review> itemClickListener) {
        _externalItemClickListener = itemClickListener;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (_layoutInflater == null) {
            _layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = DataBindingUtil.inflate(_layoutInflater, R.layout.item_review, parent, false).getRoot();
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
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
