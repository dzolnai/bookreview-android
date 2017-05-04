package hu.bme.aut.student.bookreview.test;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import hu.bme.aut.student.bookreview.BookReviewApplication;

/**
 * Helper for replacing the application component.
 * <p>
 * Created by Daniel Zolnai on 2017-05-04.
 */
public class TestHelper {
    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        BookReviewApplication application = (BookReviewApplication)RuntimeEnvironment.application;
        // -----------------------------------------
        // Explanation: originally I wanted to do the lines below. The application component would be replaced with the test component, which then would use
        // the test application module. However, the Dagger compiler creates DaggerTestApplicationComponent in the src/debug directory instead of src/test, so it can't
        // find the component and the module later on. So I ended up mocking the things provided from dagger instead.
        // -----------------------------------------
        //ApplicationComponent injector = DaggerTestApplicationComponent.builder().testApplicationModule(new TestApplicationModule(application.getApplicationContext())).build();
        //BookReviewApplication.setApplicationComponent(injector);
    }
}
