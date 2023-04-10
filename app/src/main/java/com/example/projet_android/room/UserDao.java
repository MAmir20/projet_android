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

    @Transaction
    @Query("SELECT * FROM users")
    List<Sales> getSales();
}
