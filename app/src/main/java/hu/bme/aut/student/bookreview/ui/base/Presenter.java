package hu.bme.aut.student.bookreview.ui.base;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v4.content.ContextCompat;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;
import hu.bme.aut.student.bookreview.util.ItemClickListener;

public abstract class Presenter<S> {

	protected S _screen;

	@CallSuper
	public void attachScreen(S screen) {
		_screen = screen;
	}

	@CallSuper
	public void detachScreen() {
		_screen = null;
	}

}