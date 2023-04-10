package com.example.projet_android.room;



import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.Date;

@Entity(tableName = "commands",
        primaryKeys = {"id_person", "id_product"},
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "id_person"),
                @ForeignKey(entity = Product.class,
                        parentColumns = "id",
                        childColumns = "id_product")
        })
public class Commands {
        @ColumnInfo(name = "id_person")
        private int idPerson;

        @ColumnInfo(name = "id_product")
        private int idProduct;
        @Nullable
        private double price;
        private int status = -1; // 0 refuse / -1 wait / 1 approve
        @Nullable
        private String date;

        public Commands(int idPerson, int idProduct, double price, int status, @Nullable String date) {
                this.idPerson = idPerson;
                this.idProduct = idProduct;
                this.price = price;
                this.status = status;
                this.date = date;
        }

        public int getIdPerson() {
                return idPerson;
        }

        public void setIdPerson(int idPerson) {
                this.idPerson = idPerson;
        }

        public int getIdProduct() {
                return idProduct;
        }

        public void setIdProduct(int idProduct) {
                this.idProduct = idProduct;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        @Nullable
        public String getDate() {
                return date;
        }

        public void setDate(@Nullable String date) {
                this.date = date;
        }
}
