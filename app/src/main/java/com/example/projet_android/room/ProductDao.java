package com.example.projet_android.room;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);
    @Update
    void update(Product product);
    @Delete
    void delete(Product product);

    @Query("DELETE FROM products WHERE id_category = :idCategory")
    void deleteProductsByCategoryId(int idCategory);
    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM products WHERE id = :idProduct")
    Product getProductById(int idProduct);

    @Query("SELECT * FROM products WHERE id_category = :idCategory")
    List<Product> getProductsByCategoryId(int idCategory);

    @Query("SELECT * FROM categories INNER JOIN products ON categories.id = products.id_category WHERE products.id = :idProduct")
    Category getCategoryForProduct(int idProduct);
}
