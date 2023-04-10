package com.example.projet_android.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "shops")
public class Shop extends User {
    @ColumnInfo(name = "id_shop")
    private String idShop;
    private String address;

    public Shop(String name, String phone, String email, String password, String idShop, String address) {
        super(name, phone, email, password);
        this.idShop = idShop;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }
}
