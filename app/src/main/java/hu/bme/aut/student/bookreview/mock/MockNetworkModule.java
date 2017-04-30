package hu.bme.aut.student.bookreview.mock;


import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.student.bookreview.api.BooksApi;
import hu.bme.aut.student.bookreview.api.ReviewsApi;
import hu.bme.aut.student.bookreview.api.UsersApi;
import hu.bme.aut.student.bookreview.inject.NetworkModule;
import hu.bme.aut.student.bookreview.model.repository.Repository;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {

    private NetworkModule networkModule = new NetworkModule();

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return networkModule.provideOkHttpClientBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder, Repository repository) {
        MockHttpServer mockHttpServer = new MockHttpServer(repository);
        builder.interceptors().add(chain -> {
            Request request = chain.request();
            return mockHttpServer.call(request);
        });

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return networkModule.provideRetrofit(client);
    }

    @Provides
    @Singleton
    public BooksApi provideBooksApi(Retrofit retrofit) {
        return networkModule.provideBooksApi(retrofit);
    }

    @Provides
    @Singleton
    public ReviewsApi provideReviewsApi(Retrofit retrofit) {
        return networkModule.provideReviewsApi(retrofit);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi(Retrofit retrofit) {
        return networkModule.provideUsersApi(retrofit);
    }


}