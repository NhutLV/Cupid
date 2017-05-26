
package com.signatic.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NhutDu on 06/10/2016.
 */
public final class Configuration {

    //region Constructors

    private Configuration() {
    }

    //endregion

    public static final String UPLOAD_IMAGE_URL = "http://signatic.com/Cupid/";
    public static final String BASE_URL = "http://signatic.com/Cupid/webservice/v1/index.php/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClient(final String api) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Authorization", api);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientUpload() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(UPLOAD_IMAGE_URL)
                        .addConverterFactory(GsonConverterFactory.create(createGson()));
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit;

    }

    private static  Gson createGson() {
        return new GsonBuilder().setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

}
