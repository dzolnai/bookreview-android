package hu.bme.aut.student.bookreview.api;

import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BooksApi {
  
  /**
   * Get a list of all books
   * The /books returns a list of all the books defined in the API.
   * @return Call<List<Book>>
   */
  
  @GET("books")
  Call<List<Book>> booksGet();
    

  
  /**
   * Add a new book
   * Adds a new book to the list of all books
   * @return Call<Void>
   */
  
  @POST("books")
  Call<Void> booksPost();
    

  
  /**
   * Get the info of a specific book
   * This endpoint returns the data of a single book, with the given ID.
   * @param id The ID of the book.
   * @return Call<Book>
   */
  
  @GET("books/{id}")
  Call<Book> booksIdGet(
          @Path("id") String id
  );

  
  /**
   * Update the data of a specific book.
   * This endpoint updates the book with the given ID with the new data.
   * @param id The ID of the book.
   * @param body The new data for the given book.
   * @return Call<Void>
   */
  
  @PUT("books/{id}")
  Call<Void> booksIdPut(
          @Path("id") String id, @Body Book body
  );

  
  /**
   * Delete a specific book.
   * This endpoint deletes the book with the given ID from the backend.
   * @param id The ID of the book.
   * @return Call<Void>
   */
  
  @DELETE("books/{id}")
  Call<Void> booksIdDelete(
          @Path("id") String id
  );

  
}
