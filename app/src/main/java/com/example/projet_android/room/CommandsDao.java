package com.example.projet_android.room;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommandsDao {
    @Insert
    void insert(Commands command);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWithDefaultStatus(Commands command);
    @Update
    void update(Commands command);
    @Delete
    void delete(Commands command);
    @Query("SELECT * FROM commands WHERE id_person = :idPerson")
    List<Commands> getAllCommandsForPerson(long idPerson);

    @Query("SELECT * FROM commands WHERE id_product = :idProduct")
    List<Commands> getAllCommandsForProduct(long idProduct);

    @Query("SELECT * FROM commands WHERE status = :status")
    List<Commands> getAllCommandsWithStatus(int status);
    @Query("SELECT * FROM products INNER JOIN commands ON products.id = commands.id_product WHERE commands.id_person = :idPerson")
    List<Product> getAllProductsForPerson(int idPerson);

    @Query("SELECT * FROM products INNER JOIN commands ON products.id = commands.id_product WHERE commands.id_person = :idPerson AND commands.status = :status")
    List<Product> getAllCommandsWithStatusForPerson(int status, int idPerson);
    @Query("SELECT * FROM persons INNER JOIN commands ON persons.id = commands.id_person WHERE commands.id_product = :idProduct")
    List<Person> getAllPersonsForProduct(int idProduct);
    @Query("SELECT * FROM products INNER JOIN commands ON products.id = commands.id_product WHERE products.id_user = :idUser AND commands.status = 1")
    List<Product> getSoldProductsOfUser(int idUser);
    @Query("SELECT * FROM products LEFT JOIN commands ON products.id = commands.id_product WHERE products.id_user = :idUser AND products.visibility = 1")
    List<Product> getOnSellProductsOfUser(int idUser);
}
