package hu.bme.aut.student.bookreview.mock;

import hu.bme.aut.student.bookreview.mock.interceptor.MockInterceptor;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Daniel Zolnai on 2017-04-30.
 */
public class MockHttpServer {

    private Repository _repository;

    public MockHttpServer(Repository repository) {
        _repository = repository;
    }

    public Response call(Request request) {
        MockInterceptor mockInterceptor = new MockInterceptor(_repository);
        return mockInterceptor.process(request);
    }
}
