package hu.bme.aut.student.bookreview.inject;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.api.ReviewsApi;
import hu.bme.aut.student.bookreview.api.UsersApi;
import hu.bme.aut.student.bookreview.config.NetworkConfig;
import hu.bme.aut.student.bookreview.util.GsonHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public BooksApi provideBooksApi(Retrofit retrofit) {
        return retrofit.create(BooksApi.class);
    }

    @Provides
    @Singleton
    public ReviewsApi provideReviewsApi(Retrofit retrofit) {
        return retrofit.create(ReviewsApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi(Retrofit retrofit) {
        return retrofit.create(UsersApi.class);
    }

}
