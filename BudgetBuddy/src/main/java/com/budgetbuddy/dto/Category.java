package com.budgetbuddy.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

public @Data class Category {
    @SerializedName("category_names")
    private String category_names;

    public Category(String category_names) {
        this.category_names = category_names;
    }

}
