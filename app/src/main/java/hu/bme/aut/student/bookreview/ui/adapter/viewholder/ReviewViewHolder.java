package hu.bme.aut.student.bookreview.ui.adapter.viewholder;

import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import hu.bme.aut.student.bookreview.databinding.ItemReviewBinding;
import hu.bme.aut.student.bookreview.model.entity.Review;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

/**
 * View holder for a review object.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class ReviewViewHolder extends BaseViewHolder<ItemReviewBinding> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("YYYY.MM.dd.", Locale.US);

    public ReviewViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(final Review review, final ItemClickListener<Review> internalItemClickListener) {
        String nameAndDate = review.getUsername() + " - " + DATE_FORMAT.format(review.getPublishedAt());
        _binding.nameAndDate.setText(nameAndDate);
        if (review.getComment() != null) {
            _binding.comment.setText(review.getComment());
        }
        _binding.rating.setRating(review.getRating());
        _binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalItemClickListener.onItemClicked(review);
            }
        });
    }
}
