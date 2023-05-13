package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Product;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_products);
        Switch sell_buy = findViewById(R.id.sell_buy);
        EditText filter = findViewById(R.id.filter);
        ListView list = findViewById(R.id.list);
        RadioGroup filter_radio = findViewById(R.id.radio_filter);
        RadioButton radio1 = findViewById(R.id.radio1);
        RadioButton radio2 = findViewById(R.id.radio2);

        Intent UserProductsIntent = this.getIntent();
        int userId = UserProductsIntent.getIntExtra("userId", 0);
        boolean isShop = UserProductsIntent.getStringExtra("userType").equals("S");
        String email = UserProductsIntent.getStringExtra("userEmail");
        String type = UserProductsIntent.getStringExtra("type");
        CmandiniDatabase db = Room.databaseBuilder(getApplicationContext(), CmandiniDatabase.class,"cmandini").allowMainThreadQueries().build();
        if(isShop){
            sell_buy.setChecked(true);
            sell_buy.setEnabled(false);
        }
        if(type.equals("R")){
            sell_buy.setChecked(true);
            radio1.setChecked(true);
            radio2.setChecked(false);
        }
        else if(type.equals("C")){
            sell_buy.setChecked(true);
            radio1.setChecked(false);
            radio2.setChecked(true);
        }
        else if(type.equals("O")){
            sell_buy.setChecked(false);
            radio1.setChecked(true);
            radio2.setChecked(false);
        }
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        List<Product> shown;
        if(sell_buy.isChecked()) {
            radio1.setText("Sold products");
            radio2.setText("On sell products");
            if(filter_radio.getCheckedRadioButtonId() == R.id.radio1)
                shown = db.commandsDao().getSoldProductsOfUser(userId);
            else
                shown = db.commandsDao().getOnSellProductsOfUser(userId);
            for (int i = 0; i < shown.size(); i++) {
                HashMap<String, String> datamap = new HashMap<>();
                datamap.put("price", String.valueOf(shown.get(i).getPrice()));
                datamap.put("ref", String.valueOf(shown.get(i).getRef()));
                datamap.put("name", String.valueOf(shown.get(i).getName()));
                dataList.add(datamap);
            }
        }
        else {
            radio1.setText("Ordered products");
            radio2.setText("Purchased products");
            if(filter_radio.getCheckedRadioButtonId() == R.id.radio1)
                shown = db.productDao().getAllCommandsWithStatusForPerson(0, email);
            else
                shown = db.productDao().getAllCommandsWithStatusForPerson(1, email);
            for (int i = 0; i < shown.size(); i++) {
                HashMap<String, String> datamap = new HashMap<>();
                datamap.put("price", String.valueOf(shown.get(i).getPrice()));
                datamap.put("ref", String.valueOf(shown.get(i).getRef()));
                datamap.put("name", String.valueOf(shown.get(i).getName()));
                dataList.add(datamap);
            }
        }


        SimpleAdapter sadapter=new SimpleAdapter(
                this,
                dataList,
                R.layout.user_poduct_order_item,
                new String[] {"price", "ref", "name"},
                new int[] {R.id.listPrice, R.id.listRef, R.id.listName}
        );
        list.setAdapter(sadapter);
        sell_buy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Product> shown;
                if(isChecked) {
                    radio1.setText("Sold products");
                    radio2.setText("On sell products");
                    dataList.clear();
                    if(filter_radio.getCheckedRadioButtonId() == R.id.radio1)
                        shown = db.commandsDao().getSoldProductsOfUser(userId);
                    else
                        shown = db.commandsDao().getOnSellProductsOfUser(userId);
                    for (int i = 0; i < shown.size(); i++) {
                        HashMap<String, String> datamap = new HashMap<>();
                        datamap.put("price", String.valueOf(shown.get(i).getPrice()));
                        datamap.put("ref", String.valueOf(shown.get(i).getRef()));
                        datamap.put("name", String.valueOf(shown.get(i).getName()));
                        dataList.add(datamap);
                    }
                }
                else {
                    radio1.setText("Ordered products");
                    radio2.setText("Purchased products");
                    dataList.clear();
                    if(filter_radio.getCheckedRadioButtonId() == R.id.radio1)
                        shown = db.productDao().getAllCommandsWithStatusForPerson(0, email);
                    else
                        shown = db.productDao().getAllCommandsWithStatusForPerson(1, email);
                    for (int i = 0; i < shown.size(); i++) {
                        HashMap<String, String> datamap = new HashMap<>();
                        datamap.put("price", String.valueOf(shown.get(i).getPrice()));
                        datamap.put("ref", String.valueOf(shown.get(i).getRef()));
                        datamap.put("name", String.valueOf(shown.get(i).getName()));
                        dataList.add(datamap);
                    }
                }
                sadapter.notifyDataSetChanged();
            }
        });
        filter_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                List<Product> shown;
                if(sell_buy.isChecked()) {
                    dataList.clear();
                    if(checkedId == R.id.radio1)
                        shown = db.commandsDao().getSoldProductsOfUser(userId);
                    else
                        shown = db.commandsDao().getOnSellProductsOfUser(userId);
                    for (int i = 0; i < shown.size(); i++) {
                        HashMap<String, String> datamap = new HashMap<>();
                        datamap.put("price", String.valueOf(shown.get(i).getPrice()));
                        datamap.put("ref", String.valueOf(shown.get(i).getRef()));
                        datamap.put("name", String.valueOf(shown.get(i).getName()));
                        dataList.add(datamap);
                    }
                }
                else {
                    dataList.clear();
                    if(checkedId == R.id.radio1)
                        shown = db.productDao().getAllCommandsWithStatusForPerson(0, email);
                    else
                        shown = db.productDao().getAllCommandsWithStatusForPerson(1, email);
                    for (int i = 0; i < shown.size(); i++) {
                        HashMap<String, String> datamap = new HashMap<>();
                        datamap.put("price", String.valueOf(shown.get(i).getPrice()));
                        datamap.put("ref", String.valueOf(shown.get(i).getRef()));
                        datamap.put("name", String.valueOf(shown.get(i).getName()));
                        dataList.add(datamap);
                    }
                }
                sadapter.notifyDataSetChanged();

            }
        });

    }
}