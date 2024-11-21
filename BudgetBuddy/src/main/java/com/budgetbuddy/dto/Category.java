package com.budgetbuddy.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Represents a category in the Budget Buddy application.
 * <p>
 * This class is used to define a category, including its unique ID and its name.
 * It is typically used to categorize various expenses and earnings in the system.
 * </p>
 *
 * <p>
 * The class uses {@link lombok.Data} to automatically generate getters, setters, equals, hashCode,
 * and toString methods.
 * </p>
 *
 * <p>
 * The class also utilizes {@link com.google.gson.annotations.SerializedName} to map JSON keys to class fields
 * when working with JSON data.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
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
