package com.example.projet_android.room;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
    @Query("Select * from users")
    List<User> getAllUsers();
    @Query("SELECT * FROM users WHERE id = :idUser")
    User getUserById(int idUser);
    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);
    @Query("SELECT * FROM persons WHERE email = :email")
    Person getPersonByEmail(String email);
    @Query("SELECT * FROM shops WHERE email = :email")
    Shop getShopByEmail(String email);
    @Transaction
    @Query("SELECT * FROM users")
    List<Sales> getSales();
}
