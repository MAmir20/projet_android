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

import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Person;
import com.example.projet_android.room.Shop;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone_number);
        EditText address = findViewById(R.id.address);
        EditText password = findViewById(R.id.password);
        EditText confirm_password = findViewById(R.id.confirm_password);
        SwitchCompat shop_indiv = findViewById(R.id.shop_indiv);
        EditText age_shopID = findViewById(R.id.age_shopID);
        Button signup = findViewById(R.id.signup);
        TextView login = findViewById(R.id.login);


        shop_indiv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    age_shopID.setHint("Shop ID");
                else
                    age_shopID.setHint("Age");
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_val=username.getText().toString();
                String email_val=email.getText().toString();
                String phone_val=phone.getText().toString();
                String address_val = address.getText().toString();
                String password_val=password.getText().toString();
                String confirm_password_val=confirm_password.getText().toString();
                String age_shopID_val=age_shopID.getText().toString();
                String password_val_hashed = password_val;

                // Verify entries
                boolean filled = !username_val.isEmpty() && !email_val.isEmpty() && !phone_val.isEmpty() && !password_val.isEmpty() && !confirm_password_val.isEmpty() && !age_shopID_val.isEmpty();
                if(!filled) {
                    Toast.makeText(getApplicationContext(), "Missing information !", Toast.LENGTH_SHORT).show();
                    if(username_val.isEmpty())
                        username.setHintTextColor(getColor(R.color.error));
                    if(email_val.isEmpty())
                        email.setHintTextColor(getColor(R.color.error));
                    if(phone_val.isEmpty())
                        phone.setHintTextColor(getColor(R.color.error));
                    if(address_val.isEmpty())
                        address.setHintTextColor(getColor(R.color.error));
                    if(password_val.isEmpty())
                        password.setHintTextColor(getColor(R.color.error));
                    if(confirm_password_val.isEmpty())
                        confirm_password.setHintTextColor(getColor(R.color.error));
                    if(age_shopID_val.isEmpty())
                        age_shopID.setHintTextColor(getColor(R.color.error));
                }
                else
                {
                    if(!password_val.equals(confirm_password_val)) {
                        Toast.makeText(getApplicationContext(),"Wrong password !", Toast.LENGTH_SHORT).show();
                        password.setText("");
                        confirm_password.setText("");
                        confirm_password.setHintTextColor(getColor(R.color.error));
                        password.setHintTextColor(getColor(R.color.error));
                    }
                    else {
                        // Inserting User
                        CmandiniDatabase db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class,"cmandini").allowMainThreadQueries().build();
                        /*SecureRandom random = new SecureRandom();
                        byte[] salt = new byte[16];
                        random.nextBytes(salt);*/
                        if(db.userDao().getUserByEmail(email_val) == null) {
                            if (!shop_indiv.isChecked()) {
                                Person user = new Person(username_val, phone_val, email_val, address_val, password_val_hashed, Integer.parseInt(age_shopID_val));
                                db.userDao().insert(user);
                                db.personDao().insert(user);
                            }
                            else {
                                Shop user = new Shop(username_val, phone_val, email_val, address_val, password_val_hashed, age_shopID_val);
                                db.userDao().insert(user);
                                db.shopDao().insert(user);
                            }
                            Toast.makeText(getApplicationContext(),"Signed up successfully !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email exists in DB !", Toast.LENGTH_SHORT).show();
                            email.setText("");
                        }
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}