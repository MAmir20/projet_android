package com.example.projet_android.room;



import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Nullable
    private String ref;
    private String name;
    private String description;
    private int qty;
    private double price;
    private String photo;
    private String address;
    @ColumnInfo(name = "post_date")
    private String postDate;
    private boolean visibility;
    @ColumnInfo(name = "id_category")
    private int idCategory;
    @ColumnInfo(name = "id_user")
    private int idUser;

    public Product(int id, @Nullable String ref, String name, String description, int qty, double price, String photo, String address, String postDate, boolean visibility, int idCategory, int idUser) {
        this.id = id;
        this.ref = ref;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.photo = photo;
        this.address = address;
        this.postDate = postDate;
        this.visibility = visibility;
        this.idCategory = idCategory;
        this.idUser = idUser;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getRef() {
        return ref;
    }

    public void setRef(@Nullable String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
