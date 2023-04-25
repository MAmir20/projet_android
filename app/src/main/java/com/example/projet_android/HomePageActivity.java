package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Person;
import com.example.projet_android.room.Shop;
import com.example.projet_android.room.User;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        LinearLayout shop = findViewById(R.id.layout_shop);
        LinearLayout orders = findViewById(R.id.layout_orders);
        LinearLayout products = findViewById(R.id.layout_products);
        LinearLayout requests = findViewById(R.id.layout_requests);
        LinearLayout account = findViewById(R.id.layout_account);
        LinearLayout logout = findViewById(R.id.layout_logout);
        LinearLayout parent = findViewById(R.id.ligne1);
        Intent authenticatedIntent = this.getIntent();
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.useremail);
        CircleImageView photo = findViewById(R.id.photo);

        int userId = authenticatedIntent.getIntExtra("userId", 0);
        String mail = authenticatedIntent.getStringExtra("userEmail");
        boolean isShop = authenticatedIntent.getStringExtra("userType").equals("S");
        CmandiniDatabase db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class,"cmandini")
                .allowMainThreadQueries()
                .build();
        Toast.makeText(this, userId+"", Toast.LENGTH_SHORT).show();
        User user;
        if(isShop){
            parent.removeView(orders);
             user =  db.shopDao().getShopById(userId);
        }else {
             user = db.personDao().getPersonById(userId);
        }
        name.setText(user.getName());
        email.setText(mail);
        String photoPath = user.getPhoto();
        if(photoPath.contains("http"))
            Glide.with(this)
                    .load(photoPath)
                    .into(photo);
        else {
           File imgFile = new File(photoPath);
            Uri uri = Uri.fromFile(imgFile);
           // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //photo.setImageBitmap(myBitmap);
           photo.setImageURI(uri);

        }

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(HomePageActivity.this, ShopActivity.class);
                intent.putExtra("username", user.getName());
                intent.putExtra("useremail", mail);
                intent.putExtra("photo", user.getPhoto());
                startActivity(intent);
            }
        });
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "requests_clicked", Toast.LENGTH_SHORT).show();
            }
        });
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "cart_clicked", Toast.LENGTH_SHORT).show();
            }
        });
        if(!isShop)
            orders.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomePageActivity.this, "orders_clicked", Toast.LENGTH_SHORT).show();
                }
            });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "account_clicked", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "logout_clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}