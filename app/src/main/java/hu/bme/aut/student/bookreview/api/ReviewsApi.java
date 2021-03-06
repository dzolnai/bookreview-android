package hu.bme.aut.student.bookreview.api;

import java.util.List;

import hu.bme.aut.student.bookreview.model.entity.Book;
import hu.bme.aut.student.bookreview.model.entity.Review;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewsApi {
  
  /**
   * Get all the reviews for a single book.
   * This endpoint returns all the reviews for a given book.
   * @param id The ID of the book.
   * @return Call<List<Review>>
   */
  
  @GET("books/{id}/reviews")
  Single<List<Review>> booksIdReviewsGet(
          @Path("id") String id
  );

  
  /**
   * Post a new review.
   * Post a new review for the book with the given ID.
   * @param body The data for the new review.
   * @return Call<Void>
   */
  
  @POST("books/{id}/reviews")
  Completable booksIdReviewsPost(
          @Body Review body
  );

  
}
