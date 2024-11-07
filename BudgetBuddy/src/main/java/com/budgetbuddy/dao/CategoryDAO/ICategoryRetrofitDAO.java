package com.budgetbuddy.dao.CategoryDAO;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

public interface ICategoryRetrofitDAO {
    @GET("prod/categories")
    Call<Map<String, List<String>>> getCategories();

}

