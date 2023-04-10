package com.example.projet_android.room;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);
    @Update
    void update(Category category);
    @Delete
    void delete(Category category);

    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();

    @Query("SELECT * FROM categories WHERE id = :idCategory")
    Category getCategoryById(int idCategory);

    @Query("SELECT * FROM products WHERE id_category = :idCategory")
    List<Product> getProductsForCategory(int idCategory);

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoryWithProduct> getCategoryWithProducts();
}
