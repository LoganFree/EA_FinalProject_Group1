package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Category;
import retrofit2.Call;
import retrofit2.http.GET;

import java.io.IOException;
import java.util.List;

public interface ICategoryDAO {
    List<Category> getCategories() throws IOException;

}
