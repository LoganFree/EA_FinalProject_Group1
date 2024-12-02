package com.budgetbuddy.dao.CategoryDAO;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is a utility class for creating and managing a singleton Retrofit instance.
 * <p>
 * This class provides a single Retrofit instance configured with a base URL and a Gson converter factory.
 * It ensures that only one instance of Retrofit is created throughout the application's lifecycle.
 * </p>
 *
 * <p>
 * The base URL for the Retrofit instance is set to {@value #BASE_URL}.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://api.categoryapi.com/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
