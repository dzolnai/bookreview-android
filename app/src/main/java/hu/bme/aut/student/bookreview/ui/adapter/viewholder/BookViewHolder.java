package hu.bme.aut.student.bookreview.ui.adapter.viewholder;

import android.view.View;

import com.squareup.picasso.Picasso;

import hu.bme.aut.student.bookreview.databinding.ItemBookBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.util.ItemClickListener;
import hu.bme.aut.student.bookreview.util.PaletteTransformation;

/**
 * Book view holder
 * Created by Daniel Zolnai on 2017-04-07.
 */
public class BookViewHolder extends BaseViewHolder<ItemBookBinding> {

    public BookViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(final Book book, final ItemClickListener<Book> internalItemClickListener) {
        _binding.bookTitle.setText(book.getTitle());
        _binding.bookAuthor.setText(book.getAuthor());
        Picasso.with(_binding.bookCover.getContext())
                .load(book.getImageUrl())
                .transform(new PaletteTransformation(_binding.bookBackground))
                .into(_binding.bookCover);
        _binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalItemClickListener.onItemClicked(book);
            }
        });
    }


}
