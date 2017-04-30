package hu.bme.aut.student.bookreview.mock.interceptor;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import hu.bme.aut.student.bookreview.config.NetworkConfig;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.student.bookreview.mock.MockHelper.makeResponse;


public class MockInterceptor implements Interceptor {


    private BooksMock _booksMock;
    private ReviewsMock _reviewsMock;
    private UsersMock _usersMock;

    public MockInterceptor(Repository repository) {
        _reviewsMock = new ReviewsMock(repository);
        _usersMock = new UsersMock(repository);
        _booksMock = new BooksMock(repository);

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Mock HTTP client", "URL call: " + uri.toString());
        Headers headers = request.headers();

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "books") && uri.getPath().endsWith("/reviews")) {
            return _reviewsMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "books")) {
            return _booksMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "users")) {
            return _usersMock.process(request);
        }

        return makeResponse(request, headers, 404, "Unknown");

    }

}