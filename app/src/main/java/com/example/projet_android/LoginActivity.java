package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Person;
import com.example.projet_android.room.Shop;
import com.example.projet_android.room.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        SwitchCompat shop_indiv = findViewById(R.id.shop_indiv);
        Button login = findViewById(R.id.login);
        TextView signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_val=email.getText().toString();
                String password_val=password.getText().toString();

                // Verify entries
                if(email_val.isEmpty() || password_val.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Missing information !", Toast.LENGTH_LONG).show();
                    if(email_val.isEmpty())
                        email.setHintTextColor(getColor(R.color.error));
                    if(password_val.isEmpty())
                        password.setHintTextColor(getColor(R.color.error));
                }
                else {
                    // Verify user credentials
                    CmandiniDatabase db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class,"cmandini").allowMainThreadQueries().build();
                    User user;//= db.userDao().getUserByEmail(email_val);
                    if(shop_indiv.isChecked())
                        user = db.shopDao().getShopByEmail(email_val);
                    else
                        user = db.personDao().getPersonByEmail(email_val);
                    if(user == null) {
                        Toast.makeText(getApplicationContext(),"User not found !", Toast.LENGTH_LONG).show();
                        email.setText("");
                        password.setText("");
                    }
                    else {
                        if(user.getEmail().equals(email_val) && user.getPassword().equals(password_val)){
                            Toast.makeText(getApplicationContext(),"Authenticated successfully !", Toast.LENGTH_LONG).show();
                            // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Intent authenticatedIntent = new Intent(getApplicationContext(), UserProductsActivity.class);
                            User u = db.userDao().getUserByEmail(email_val);
                            authenticatedIntent.putExtra("userId", u.getId());
                            authenticatedIntent.putExtra("userEmail", email_val);
                            authenticatedIntent.putExtra("userType", shop_indiv.isChecked()?"S":"P");
                            startActivity(authenticatedIntent);
                        }
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });
    }
}