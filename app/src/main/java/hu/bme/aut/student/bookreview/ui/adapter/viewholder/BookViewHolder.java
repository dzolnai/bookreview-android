package hu.bme.aut.student.bookreview.ui.adapter.viewholder;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import hu.bme.aut.student.bookreview.databinding.ItemBookBinding;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

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
        _binding.bookBackground.setBackgroundColor(Color.BLACK);
        if (book.getImageUrl() != null && book.getImageUrl().length() > 0) {
            Target target;
            Picasso.with(_binding.bookCover.getContext())
                    .load(book.getImageUrl())
                    .into(target = new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            _binding.bookCover.setImageBitmap(bitmap);
                            Palette.from(bitmap)
                                    .generate(new Palette.PaletteAsyncListener() {
                                        @Override
                                        public void onGenerated(Palette palette) {
                                            if (_binding.bookBackground == null) {
                                                return;
                                            }
                                            _binding.bookBackground.setBackgroundColor(palette.getDarkMutedColor(Color.BLACK));
                                        }
                                    });
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
            _binding.bookCover.setTag(target);
        }
        _binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalItemClickListener.onItemClicked(book);
            }
        });
    }


}
