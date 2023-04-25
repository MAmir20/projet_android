package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet_android.room.Category;
import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Commands;
import com.example.projet_android.room.Person;
import com.example.projet_android.room.Product;
import com.example.projet_android.room.Shop;
import com.example.projet_android.room.User;
import com.example.projet_android.room.UserDao;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private  CmandiniDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To delete database
        getApplicationContext().deleteDatabase("cmandini");

         db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class, "cmandini")
                 .allowMainThreadQueries()
                 .build();
         if(isDatabaseEmpty(db)){
             seedDatabase(db);
         }

        Button btn = findViewById(R.id.button);
        Button test = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            }
        });
    }
    public boolean isDatabaseEmpty(CmandiniDatabase db) {
        UserDao userDao = db.userDao();
        User count = userDao.getUserById(1); // replace getUsersCount() with your DAO method that returns the number of rows in your table
        return count == null;
    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }
    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    public void seedDatabase(CmandiniDatabase db){
        int nbUserRecords = 200;
        User u;
        for(int i=0;i<nbUserRecords/2;i++){
            Person p1 = new Person(generateRandomString(20), "24790319", "hassenakrout"+ i + "@enis.tn", "https://static.vecteezy.com/system/resources/thumbnails/004/607/791/small/man-face-emotive-icon-smiling-male-character-in-blue-shirt-flat-illustration-isolated-on-white-happy-human-psychological-portrait-positive-emotions-user-avatar-for-app-web-design-vector.jpg",generateRandomString(30),generateRandomString(10),generateRandomInt(16,80));
            Shop p2 = new Shop(generateRandomString(20), "24790319", "amirmezghani"+ i + "@enis.tn","https://static.vecteezy.com/system/resources/thumbnails/004/607/791/small/man-face-emotive-icon-smiling-male-character-in-blue-shirt-flat-illustration-isolated-on-white-happy-human-psychological-portrait-positive-emotions-user-avatar-for-app-web-design-vector.jpg",generateRandomString(30),generateRandomString(10),""+i);
            db.userDao().insert(p1);
            db.userDao().insert(p2);
            db.personDao().insert(p1);
            db.shopDao().insert(p2);
         }

        String[] names = {"Vehicle", "Real estate","Multimedia", "Home & garden","Clothes" ,"Hobbies", "Jobs", "Business", "Others" };
        for(int i=0; i< names.length;i++){
            Category c = new Category(names[i]);
            db.categoryDao().insert(c);
        }

        int nbProductRecords = 1000;
        String date;
        for(int i=0; i< nbProductRecords;i++) {
            date =  generateRandomInt(1, 31) + "/" + generateRandomInt(1, 12) + "/" + generateRandomInt(2000, 2023);
            Product pro = new Product(generateRandomString(10), generateRandomString(20),
                    generateRandomString(500), generateRandomInt(1, 100),
                    generateRandomInt(1, 100) * 1.5,"https://www.autoaubaine.com/actualite-automobile/images-automobiles/55420.jpg",
                    generateRandomString(20), date,
                    true, generateRandomInt(1, names.length), generateRandomInt(1, nbUserRecords));
            db.productDao().insert(pro);
        }
        int nbCommandRecords = 100,idPerson, idProduct,stat;

        for(int i=0; i<nbCommandRecords;i++) {

            boolean test = true;
            idPerson = generateRandomInt(1, nbUserRecords/2);
            idProduct = generateRandomInt(1, nbProductRecords);
            while(test) {
                List<Commands> commandsList = db.commandsDao().getAllCommandsForPerson(idPerson);
                test = false;
                for (Commands com : commandsList) {
                    if (com.getIdProduct() == idProduct) {
                        test = true;
                        break;
                    }
                }
                if(test){
                    idPerson = generateRandomInt(1, nbUserRecords/2);
                    idProduct = generateRandomInt(1, nbProductRecords);
                }
            }

            stat = generateRandomInt(-1, 1);
            date  = generateRandomInt(1, 31) + "/" + generateRandomInt(1, 12) + "/" + generateRandomInt(2000, 2023);

            Commands c = new Commands(idPerson, idProduct, db.productDao().getProductById(idProduct).getPrice(),stat, date );
            if(stat == 1){
                db.productDao().getProductById(idProduct).setVisibility(false);
                db.productDao().update(db.productDao().getProductById(idProduct));
            }
            db.commandsDao().insert(c);
        }
    }

}