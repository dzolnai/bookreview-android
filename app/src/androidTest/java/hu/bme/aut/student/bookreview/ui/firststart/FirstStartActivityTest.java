package hu.bme.aut.student.bookreview.ui.firststart;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hu.bme.aut.student.bookreview.R;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.ui.home.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Test for the first start activity.
 * <p>
 * Created by Daniel Zolnai on 2017-05-05.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FirstStartActivityTest {

    @Rule
    public IntentsTestRule<FirstStartActivity> _activityRule = new IntentsTestRule<FirstStartActivity>(FirstStartActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            Context context = InstrumentationRegistry.getTargetContext();
            SettingsService.reset(context);
        }
    };

    @Test
    public void testNameInput() {
        onView(withId(R.id.dialog_username_input_username))
                .perform(typeText("abcdef"), closeSoftKeyboard());
        onView(withId(R.id.dialog_username_button_start)).perform(click());
        intended(hasComponent(HomeActivity.class.getName()));
    }
}