package com.budgetbuddy.dao.CategoryDAO;

import com.budgetbuddy.dto.Category;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.*;

@Repository
public class CategoryDAO implements ICategoryDAO {

    @Override
    public List<Category> getCategories() throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        ICategoryRetrofitDAO categoryRetrofitDAO = retrofitInstance.create(ICategoryRetrofitDAO.class);
        Call<Map<String, List<String>>> call = categoryRetrofitDAO.getCategories();
        Response<Map<String, List<String>>> response = call.execute();

        List<Category> categories = new ArrayList<>();
        Set<String> uniqueNames = new HashSet<>(); // Track unique category names

        if (response.body() != null) {
            for (Map.Entry<String, List<String>> entry : response.body().entrySet()) {
                int categoryId = Integer.parseInt(entry.getKey());
                List<String> names = entry.getValue();
                String categoryName = names.get(0);

                // Check if category name already exists
                if (uniqueNames.add(categoryName)) {
                    categories.add(new Category(categoryId,categoryName));
                }
            }
        }
        return categories;
    }

}
