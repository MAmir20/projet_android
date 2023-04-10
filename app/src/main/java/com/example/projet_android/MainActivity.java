package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;

import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Person;
import com.example.projet_android.room.User;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //To delete database
       //getApplicationContext().deleteDatabase("cmandini");
        //To build the database
       CmandiniDatabase db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class,"cmandini").allowMainThreadQueries().build();

        Person p1 = new Person("Hassen Akrout", "24790319", "hassen.akrout@enis.tn","123456",23);
        //Insert p1 un table user and table person
        db.userDao().insert(p1);
        db.personDao().insert(p1);
        //get the list of all persons
        List<Person> personList= db.personDao().getAllPersons();

        TextView v1 = findViewById(R.id.textView);
            //get the first record in the database
            Person list = personList.get(0);
            String text = list.getName();
            v1.setText(text);


        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}