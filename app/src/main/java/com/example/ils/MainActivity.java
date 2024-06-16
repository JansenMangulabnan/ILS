package com.example.ils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ils.CustomAdapter;
import com.example.ils.MyDataBaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  ImageButton addList;
  ImageView empty_imageview;
  TextView no_data;

  MyDataBaseHelper myDB;
  ArrayList<String> order_id, order_flavor, order_size, order_name, order_address, order_number;
  CustomAdapter customAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    addList = findViewById(R.id.addList);

    addList.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, AddActivity.class);
      startActivity(intent);
    });

    myDB = new MyDataBaseHelper(MainActivity.this);
    recyclerView = findViewById(R.id.recyclerView);
    order_id = new ArrayList<>();
    order_name = new ArrayList<>();
    order_address = new ArrayList<>();
    order_number = new ArrayList<>();
    order_flavor = new ArrayList<>();
    order_size = new ArrayList<>();

    storeDataInArrays(); //might be

    customAdapter = new CustomAdapter(MainActivity.this, order_id, order_name, order_address, order_number, order_flavor, order_size);
    recyclerView.setAdapter(customAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == 1){
      recreate();
    }
  }

  void storeDataInArrays(){
    Cursor cursor = myDB.readAllData();
    if(cursor.getCount() == 0){
      msg("NO");
    }else{
      while (cursor.moveToNext()){
        order_id.add(cursor.getString(0));
        order_name.add(cursor.getString(1));
        order_address.add(cursor.getString(2));
        order_number.add(cursor.getString(3));
        order_flavor.add(cursor.getString(4));
        order_size.add(cursor.getString(5));
      }
    }
  }
  void msg(String title) {
    new AlertDialog.Builder(this).setTitle(title).setPositiveButton("OK", null).show();
  }
}