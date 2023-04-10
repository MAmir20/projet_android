package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        //ToggleButton shop_indiv = findViewById(R.id.shop_indiv);
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
                    Toast.makeText(getApplicationContext(),"Missing information", Toast.LENGTH_LONG).show();
                    if(email_val.isEmpty())
                        email.setHintTextColor(getColor(R.color.error));
                    if(password_val.isEmpty())
                        password.setHintTextColor(getColor(R.color.error));
                }
                else {
                    // Verify user credentials

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