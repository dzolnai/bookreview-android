package hu.bme.aut.student.bookreview.mock.interceptor;

import android.net.Uri;

import java.io.IOException;

import hu.bme.aut.student.bookreview.model.entity.Review;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

import static hu.bme.aut.student.bookreview.mock.MockHelper.makeResponse;

public class ReviewsMock {

    private Repository _repository;

    public ReviewsMock(Repository repository) {
        _repository = repository;
    }

    public Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        try {
            if (uri.getPath().endsWith("/reviews") && request.method().equalsIgnoreCase("GET")) {
                String bookId = uri.getPathSegments().get(uri.getPathSegments().size() - 2);
                responseString = GsonHelper.getGson().toJson(_repository.getReviewsForBook(bookId));
                responseCode = 200;
            } else if (uri.getPath().endsWith("/reviews") && request.method().equalsIgnoreCase("POST")) {
                final Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                String body = buffer.readUtf8();
                Review newReview = GsonHelper.getGson().fromJson(body, Review.class);
                _repository.addReview(newReview);
                responseString = "";
                responseCode = 200;
            } else {
                responseString = "ERROR";
                responseCode = 503;
            }
        } catch (IOException e) {
            responseString = e.toString();
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}