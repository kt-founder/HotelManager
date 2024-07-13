package com.example.myapp.Activities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.Activities.entities.Profile;

public class DBContext {

    SQLiteHelper helper;
    Context context;
    SQLiteDatabase db;

    public DBContext(Context context) {
        this.context = context;
    }

    public DBContext open() throws SQLException {
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
        String[] columns = {"id"};
        String selection = SQLiteHelper.Username + " = ?" + " AND " + SQLiteHelper.Password + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(SQLiteHelper.System_users, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean updateProfile(String username, String password, String firstName, String lastName,
                                 String streetAddress, String city, String state, String zipcode,
                                 String email, String phone, String nameOnCard, String ccType,
                                 String ccNumber, String ccExpiry) {
        ContentValues contentValues = new ContentValues();
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

        int rows = db.update(SQLiteHelper.System_users, contentValues, SQLiteHelper.Username + " = ?", new String[]{username});
        return rows > 0;
    }

    public Profile getUser(String username) {
        String[] columns = {
                SQLiteHelper.Username,
                SQLiteHelper.Password,
                SQLiteHelper.FirstName,
                SQLiteHelper.LastName,
                SQLiteHelper.StreetAddress,
                SQLiteHelper.City,
                SQLiteHelper.State,
                SQLiteHelper.Zipcode,
                SQLiteHelper.Email,
                SQLiteHelper.Phone,
                SQLiteHelper.NameOnCard,
                SQLiteHelper.Cctype,
                SQLiteHelper.Ccnumber,
                SQLiteHelper.Ccexpiry
        };

        Cursor cursor = db.query(SQLiteHelper.System_users, columns, SQLiteHelper.Username + " = ?", new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Profile profile = new Profile(
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Username)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Password)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.FirstName)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.LastName)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.StreetAddress)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.City)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.State)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Zipcode)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Email)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Phone)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.NameOnCard)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Cctype)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Ccnumber)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Ccexpiry))
            );
            cursor.close();
            return profile;
        } else {
            return null;
        }
    }
}
