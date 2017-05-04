package hu.bme.aut.student.bookreview.api;

import hu.bme.aut.student.bookreview.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsersApi {

    /**
     * Checks if a username is already in use
     * Sends the requested username to the backend to check if a username is already in use.
     *
     * @param username The requested username
     * @return Call<Void>
     */

    @GET("user/check")
    Completable userCheckGet(@Query("username") String username
    );

    @POST("user/register")
    Completable registerUser(@Body User user);
}
