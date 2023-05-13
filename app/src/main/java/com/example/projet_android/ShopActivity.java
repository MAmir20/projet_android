package com.example.projet_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projet_android.Adapter.CategoryAdapter;
import com.example.projet_android.Adapter.ProductAdapter;
import com.example.projet_android.room.Category;
import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopActivity extends AppCompatActivity {
    private CategoryAdapter adapter1;
    private ProductAdapter adapter2;
    private  RecyclerView recyclerViewCategory, recyclerViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        CmandiniDatabase db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class, "cmandini")
                .allowMainThreadQueries()
                .build();
        RecyclerViewCategory(db);
        RecyclerViewProduct(db);
        Intent myIntent = getIntent();
        String username = myIntent.getStringExtra("username");
        String mail = myIntent.getStringExtra("useremail");
        String photo = myIntent.getStringExtra("photo");

        CircleImageView photoView = findViewById(R.id.imageViewId);
        TextView name = findViewById(R.id.textView6);
        TextView emailView = findViewById(R.id.textView7);

        EditText search = findViewById(R.id.search);

        name.setText(username);
        emailView.setText(mail);
        if(photo.contains("http"))
            Glide.with(this)
                    .load(photo)
                    .into(photoView);
        else {
            File imgFile = new File(photo);
            Uri uri = Uri.fromFile(imgFile);
            // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //photo.setImageBitmap(myBitmap);
            photoView.setImageURI(uri);

        }
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    String query = search.getText().toString().trim();
                    performSearch(query,db);
                    return true;
                }
                return false;
            }
        });
        TextView CountProd = findViewById(R.id.CountProd);
        ConstraintLayout reset = findViewById(R.id.button);
       reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = db.productDao().getAllProducts();
                CountProd.setText(productList.size()+"");
                search.setText("");
                Toast.makeText(ShopActivity.this, "Reset" , Toast.LENGTH_SHORT).show();
                if(adapter2 != null)
                    adapter2.updateProductList(productList);

            }
        });

    }
    private void RecyclerViewCategory(CmandiniDatabase db){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory = findViewById(R.id.recycleView);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);

        List<Category> categoryList = db.categoryDao().getAllCategories();
        adapter1 = new CategoryAdapter(categoryList);
        TextView CountProd = findViewById(R.id.CountProd);
        CountProd.setText(db.productDao().getAllProducts().size()+"");
        //Set the SimpleAdapter as the adapter for the ListView
        recyclerViewCategory.setAdapter(adapter1);

        adapter1.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Category category = categoryList.get(position);
                Toast.makeText(ShopActivity.this, "Selected category: " + category.getName(), Toast.LENGTH_SHORT).show();
                // Retrieve the products for the clicked category from the database
                List<Product> productList = db.productDao().getProductsByCategoryId(category.getId());
                // Update the adapter for the product list with the new product list
                CountProd.setText(productList.size()+"");

                adapter2.updateProductList(productList);
            }
        });

    }
    private void RecyclerViewProduct(CmandiniDatabase db){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewProduct = findViewById(R.id.recycleView2);
        recyclerViewProduct.setLayoutManager(linearLayoutManager);
        List<Product> productList = db.productDao().getAllProducts();
        adapter2 = new ProductAdapter(productList, db);
        recyclerViewProduct.setAdapter(adapter2);

    }
    private void performSearch(String query, CmandiniDatabase db) {
        List<Product> productList = db.productDao().getProductsByName(query);
        TextView CountProd = findViewById(R.id.CountProd);
        CountProd.setText(productList.size()+"");
        adapter2.updateProductList(productList);


    }
}