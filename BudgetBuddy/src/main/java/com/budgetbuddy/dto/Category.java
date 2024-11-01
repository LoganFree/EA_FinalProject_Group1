package com.budgetbuddy.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data class Category {
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("category_names")
    private String category_names;

    public Category(int category_id, String category_names) {
        this.category_id = category_id;
        this.category_names = category_names;
    }

    public String toString()
    {
        return category_names;
    }

}
