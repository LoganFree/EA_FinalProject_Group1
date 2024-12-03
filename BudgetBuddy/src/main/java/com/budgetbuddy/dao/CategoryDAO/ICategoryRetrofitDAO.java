package com.budgetbuddy.dao.CategoryDAO;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

public interface ICategoryRetrofitDAO {
    // HTTP GET request to fetch categories from the specified endpoint
    @GET("prod/categories")
    // Defines a method that returns a Call object containing a Map of category data
    Call<Map<String, List<String>>> getCategories();
}