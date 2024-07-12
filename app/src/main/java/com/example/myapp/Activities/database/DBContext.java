package com.example.myapp.Activities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBContext {

    SQLiteHelper helper;
    Context context;
    SQLiteDatabase db;

    public DBContext(Context context){
        this.context = context;
    }

    public DBContext open() throws SQLException{
        helper = new SQLiteHelper(this.context);
        db = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    // Method to insert user information into system_user table
    public long insertUser(String username, String password, String firstName, String lastName,
                           String streetAddress, String city, String state, String zipcode,
                           String email, String phone, String nameOnCard, String ccType,
                           String ccNumber, String ccExpiry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.Username, username);
        contentValues.put(SQLiteHelper.Password, password);
        contentValues.put(SQLiteHelper.FirstName, firstName);
        contentValues.put(SQLiteHelper.LastName, lastName);
        contentValues.put(SQLiteHelper.StreetAddress, streetAddress);
        contentValues.put(SQLiteHelper.City, city);
        contentValues.put(SQLiteHelper.State, state);
        contentValues.put(SQLiteHelper.Zipcode, zipcode);
        contentValues.put(SQLiteHelper.Email, email);
        contentValues.put(SQLiteHelper.Phone, phone);
        contentValues.put(SQLiteHelper.NameOnCard, nameOnCard);
        contentValues.put(SQLiteHelper.Cctype, ccType);
        contentValues.put(SQLiteHelper.Ccnumber, ccNumber);
        contentValues.put(SQLiteHelper.Ccexpiry, ccExpiry);
        return db.insert(SQLiteHelper.System_users, null, contentValues);
    }

    // Method to check user login information
    public boolean checkLogin(String username, String password) {
        String[] columns = { "id" };
        String selection = SQLiteHelper.Username + " = ?" + " AND " + SQLiteHelper.Password + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(SQLiteHelper.System_users, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
