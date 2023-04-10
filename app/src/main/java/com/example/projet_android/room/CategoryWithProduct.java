package com.example.projet_android.room;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithProduct {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "id_category"
    )
    public List<Product> productList;
}
