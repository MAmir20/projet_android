package com.example.projet_android.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "shops", indices = {@Index(value = "id_shop", unique = true)})
public class Shop extends User {
    @ColumnInfo(name = "id_shop")
    private String idShop;

    public Shop(String name, String phone, String email,String address, String password, String idShop) {
        super(name, phone, email, address, password);
        this.idShop = idShop;

    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }
}
