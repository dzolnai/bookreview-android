package hu.bme.aut.student.bookreview.api;

import hu.bme.aut.student.bookreview.model.entity.User;
import retrofit2.Call;
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
    Call<Void> userCheckGet(
            @Query("username") String username
    );

    @POST("user/register")
    Call<Void> registerUser(@Body User user);
}
