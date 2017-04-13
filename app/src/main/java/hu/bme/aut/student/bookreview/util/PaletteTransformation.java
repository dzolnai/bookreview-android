package hu.bme.aut.student.bookreview.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.view.View;

import com.squareup.picasso.Transformation;

/**
 * Created by Daniel Zolnai on 2017-04-13.
 */
public class PaletteTransformation implements Transformation {

    private View _backgroundView;

    public PaletteTransformation(View backgroundView) {
        _backgroundView = backgroundView;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Palette.from(source)
                .generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        if (_backgroundView == null) {
                            return;
                        }
                        _backgroundView.setBackgroundColor(palette.getDarkMutedColor(Color.BLACK));
                    }
                });
        return source;
    }

    @Override
    public String key() {
        return "";
    }
}
