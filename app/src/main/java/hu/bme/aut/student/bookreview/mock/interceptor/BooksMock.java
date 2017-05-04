package hu.bme.aut.student.bookreview.mock.interceptor;

import android.net.Uri;

import java.io.IOException;

import hu.bme.aut.student.bookreview.config.NetworkConfig;
import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import hu.bme.aut.student.bookreview.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

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

        try {
            if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "books") && request.method().equalsIgnoreCase("POST")) {
                final Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                String body = buffer.readUtf8();
                Book newBook = GsonHelper.getGson().fromJson(body, Book.class);
                _repository.saveBook(newBook);
                responseString = "";
                responseCode = 200;
            } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "books") && request.method().equalsIgnoreCase("GET")) {
                if (_repository.getAllBooks() == null || _repository.getAllBooks().size() == 0) {
                    _seedRepository();
                }
                responseString = GsonHelper.getGson().toJson(_repository.getAllBooks());
                responseCode = 200;
            } else {
                responseString = "ERROR";
                responseCode = 503;
            }
        } catch (IOException ex) {
            responseString = ex.toString();
            responseCode = 500;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }

    private void _seedRepository() {
        Book book1 = new Book.Builder()
                .setTitle("Metamorphosis")
                .setAuthor("Franz Kafka")
                .setPublishYear(1915)
                .setImageUrl("https://s-media-cache-ak0.pinimg.com/originals/b3/6b/86/b36b86a4b7d012039bc9b3de1a0f81da.jpg")
                .build();
        Book book2 = new Book.Builder()
                .setTitle("Of Mice and Men")
                .setAuthor("John Steinbeck")
                .setPublishYear(1937)
                .setImageUrl("http://files.campus.edublogs.org/blogs.yis.ac.jp/dist/8/325/files/2012/04/original_204690_h7KgDpcspkwwvtIcmszxsVZzf-1y16175.jpg")
                .build();
        Book book3 = new Book.Builder()
                .setTitle("The Book of Strange New Things")
                .setAuthor("Michel Faber")
                .setPublishYear(2014)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/2/2e/BookStrangeNewThings_cover.jpg/200px-BookStrangeNewThings_cover.jpg")
                .build();
        Book book4 = new Book.Builder()
                .setTitle("Go Set a Watchman")
                .setAuthor("Harper Lee")
                .setPublishYear(2015)
                .setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51CC2jfysbL._SX323_BO1,204,203,200_.jpg")
                .build();
        Book book5 = new Book.Builder()
                .setTitle("Inferno")
                .setAuthor("Dan Brown")
                .setPublishYear(2013)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bb/Inferno-cover.jpg")
                .build();
        Book book6 = new Book.Builder()
                .setTitle("When Breath Becomes Air")
                .setAuthor("Paul Kalanithi")
                .setPublishYear(2016)
                .setImageUrl("https://cdn.waterstones.com/bookjackets/large/9781/7847/9781784701994.jpg")
                .build();
        Book book7 = new Book.Builder()
                .setTitle("Apollo Expeditions to the Moon - The NASA History")
                .setAuthor("Edgar M. Cortright")
                .setPublishYear(1975)
                .setImageUrl("https://images-na.ssl-images-amazon.com/images/I/5131SAKKp7L._SX383_BO1,204,203,200_.jpg")
                .build();
        _repository.saveBook(book1);
        _repository.saveBook(book2);
        _repository.saveBook(book3);
        _repository.saveBook(book4);
        _repository.saveBook(book5);
        _repository.saveBook(book6);
        _repository.saveBook(book7);
    }
}