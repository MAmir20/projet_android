package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone_number);
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
                String password_val=password.getText().toString();
                String confirm_password_val=confirm_password.getText().toString();
                String age_shopID_val=age_shopID.getText().toString();
                boolean isShop;

                // Verify entries
                boolean filled = !username_val.isEmpty() && !email_val.isEmpty() && !phone_val.isEmpty() && !password_val.isEmpty() && !confirm_password_val.isEmpty() && !age_shopID_val.isEmpty();
                if(!filled) {
                    Toast.makeText(getApplicationContext(), "Missing information !", Toast.LENGTH_LONG).show();
                    if(username_val.isEmpty())
                        username.setHintTextColor(getColor(R.color.error));
                    if(email_val.isEmpty())
                        email.setHintTextColor(getColor(R.color.error));
                    if(phone_val.isEmpty())
                        phone.setHintTextColor(getColor(R.color.error));
                    if(password_val.isEmpty())
                        password.setHintTextColor(getColor(R.color.error));
                    if(confirm_password_val.isEmpty())
                        confirm_password.setHintTextColor(getColor(R.color.error));
                    if(age_shopID_val.isEmpty())
                        age_shopID.setHintTextColor(getColor(R.color.error));
                }
                else
                {
                    if(password_val != confirm_password_val) {
                        Toast.makeText(getApplicationContext(),"Wrong password !", Toast.LENGTH_LONG).show();
                        password.setText("");
                        confirm_password.setText("");
                        confirm_password.setHintTextColor(getColor(R.color.error));
                        password.setHintTextColor(getColor(R.color.error));
                    }
                    else {
                        // Inserting User
                        isShop = shop_indiv.isChecked();

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