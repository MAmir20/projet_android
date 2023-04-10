package com.example.projet_android.room;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShopDao {
    @Insert
    void insert(Shop shop);

    @Update
    void update(Shop shop);

    @Delete
    void delete(Shop shop);

    @Query("SELECT * FROM shops")
    List<Shop> getAllShops();

    @Query("SELECT * FROM shops WHERE id = :idShop")
    Shop getShopById(int idShop);
}
