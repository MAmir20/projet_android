package com.example.projet_android.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class Sales {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "id_user"
    )
    public List<Product> productList;
}
