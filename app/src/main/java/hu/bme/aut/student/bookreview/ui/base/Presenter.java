package hu.bme.aut.student.bookreview.ui.base;

import android.support.annotation.CallSuper;

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