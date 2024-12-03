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
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

public class RetrofitClientInstance {
    // Retrofit instance
    private static Retrofit retrofit;

    // Base URL for the API
    private static String BASE_URL = "https://api.categoryapi.com/";

    // Method to get or create a Retrofit instance
    public static Retrofit getRetrofitInstance() {
        // Check if Retrofit instance is already initialized
        if (retrofit == null) {
            // Build the Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Set the base URL
                    .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter for JSON parsing
                    .build();
        }
        return retrofit; // Return the initialized Retrofit instance
    }
}