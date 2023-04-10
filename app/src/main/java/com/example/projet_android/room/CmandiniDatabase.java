package com.example.projet_android.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Shop.class, Person.class,Category.class, Product.class, Commands.class}, version = 1, exportSchema = false)
    public abstract class CmandiniDatabase extends RoomDatabase {
       // private static final String DB_NAME = "cmandini";
        public abstract PersonDao personDao();
        public abstract ProductDao productDao();
        public abstract CommandsDao commandsDao();
        public abstract UserDao userDao();
        public abstract ShopDao shopDao();
        public abstract CategoryDao categoryDao();

       /* private static CmandiniDatabase instance;

       public static synchronized CmandiniDatabase getInstance(Context context) {
            if (instance == null) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.getApplicationContext(),
                                        CmandiniDatabase.class, DB_NAME)
                                .fallbackToDestructiveMigration()
                                .build();
                    }
            }
            return instance;
        }*/
    }

