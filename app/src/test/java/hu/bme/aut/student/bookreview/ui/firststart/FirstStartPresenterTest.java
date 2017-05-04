package hu.bme.aut.student.bookreview.ui.firststart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.annotation.Config;

import hu.bme.aut.student.bookreview.BuildConfig;
import hu.bme.aut.student.bookreview.api.UsersApi;
import hu.bme.aut.student.bookreview.model.entity.User;
import hu.bme.aut.student.bookreview.model.service.SettingsService;
import hu.bme.aut.student.bookreview.test.RobolectricDaggerTestRunner;
import io.reactivex.Completable;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the first start presenter.
 * <p>
 * Created by Daniel Zolnai on 2017-05-04.
 */
public class FirstStartPresenterTest {
    private SettingsService _mockSettings;
    private FirstStartPresenter _presenter;
    private UsersApi _mockApi;

    @Before
    public void setup() throws Exception {
        _mockSettings = mock(SettingsService.class);
        _mockApi = mock(UsersApi.class);
        when(_mockApi.registerUser(any(User.class))).thenAnswer(new Answer<Completable>() {
            @Override
            public Completable answer(InvocationOnMock invocation) throws Throwable {
                return Completable.complete();
            }
        });
        when(_mockApi.userCheckGet(anyString())).thenAnswer(new Answer<Completable>() {
            @Override
            public Completable answer(InvocationOnMock invocation) throws Throwable {
                return Completable.complete();
            }
        });
        _presenter = new FirstStartPresenter(_mockSettings, _mockApi);
    }

    @Test
    public void testFirstStart() {
        FirstStartScreen screen = mock(FirstStartScreen.class);
        _presenter.attachScreen(screen);
        verify(_mockSettings, only()).getUsername();
        verify(screen, only()).promptUsernameInput();
    }

    @Test
    public void testCheckUser() {
        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        _presenter.checkUsername("abcdef");
        verify(_mockApi, only()).userCheckGet(usernameCaptor.capture());
        assertEquals(usernameCaptor.getAllValues().get(0), "abcdef");
    }

    @Test
    public void testRegister() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        _presenter.registerUsername("ghijklmno");
        verify(_mockApi, atLeastOnce()).registerUser(userCaptor.capture());
        assertEquals(userCaptor.getAllValues().get(0).getUsername(), "ghijklmno");
    }
}