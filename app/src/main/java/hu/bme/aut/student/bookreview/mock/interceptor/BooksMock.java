package hu.bme.aut.student.bookreview.mock.interceptor;

import android.net.Uri;

import hu.bme.aut.student.bookreview.config.NetworkConfig;
import hu.bme.aut.student.bookreview.model.repository.MemoryRepository;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.student.bookreview.mock.MockHelper.makeResponse;

public class BooksMock {

    private Repository _repository;

    public BooksMock(Repository repository) {
        _repository = repository;
    }


    public Response process(Request request) {
		Uri uri = Uri.parse(request.url().toString());

		String responseString;
		int responseCode;
		Headers headers = request.headers();


		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "books") && request.method().equalsIgnoreCase("POST")) {
			responseString = "";
			responseCode = 200;
		}else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "books") && request.method().equalsIgnoreCase("GET")) {
			responseString = GsonHelper.getGson().toJson(_repository.getAllBooks());
			responseCode = 200;
		} else {
			responseString = "ERROR";
			responseCode = 503;
		}

		return makeResponse(request, headers, responseCode, responseString);
	}
}