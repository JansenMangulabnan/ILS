package com.example.ils;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

  private Context context;
  private static final String D_NAME = "Orders.db";
  private static final int D_VER = 1;

  private static final String T_NAME = "orders";
  private static final String C_ID = "_id";
  private static final String C_NAME = "name";
  private static final String C_ADDRESS = "address";
  private static final String C_NUMBER = "number";
  private static final String C_FLAVOR = "flavor";
  private static final String C_SIZE = "size";

  public MyDataBaseHelper(@Nullable Context context) {
    super(context, D_NAME, null, D_VER);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String query = "CREATE TABLE " + T_NAME + " (" + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_NAME + " TEXT, " + C_ADDRESS + " TEXT, " + C_NUMBER + " TEXT, " + C_FLAVOR + " TEXT, " + C_SIZE + " TEXT);";
    db.execSQL(query);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + T_NAME);
    onCreate(db);
  }

  void addCustomer(String name, String address, String number, String flavor, String size) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    cv.put(C_NAME, name);
    cv.put(C_ADDRESS, address);
    cv.put(C_NUMBER, number);
    cv.put(C_FLAVOR, flavor);
    cv.put(C_SIZE, size);

    long res = db.insert(T_NAME, null, cv);
    if (res == -1) {
      msg("Fail");
    }
  }
  void msg(String message) {
    new AlertDialog.Builder(context).setTitle(message).setPositiveButton("OK", null).show();
  }
  Cursor readAllData() {
    String query = "SELECT * FROM " + T_NAME;
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = null;
    if (db != null) {
      cursor = db.rawQuery(query, null);
    }
    return cursor;
  }
}
