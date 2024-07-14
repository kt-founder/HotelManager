package com.example.myapp.Activities.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.Activities.entities.Hotel;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.Activities.entities.Room;

import java.util.ArrayList;

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
        String[] columns = {"id","role"};
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
    public boolean updateProfileAdmin(String username, String password, String firstName, String lastName,
                                      String streetAddress, String city, String state, String zipcode,
                                      String email, String phone, String creditCardType) {
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
        contentValues.put(SQLiteHelper.Cctype, creditCardType);

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
            Profile profile = new Profile();
                   profile.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Username)));
                   profile.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Password)));
                   profile.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.FirstName)));
                    profile.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.LastName)));
                    profile.setStreetAddress(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.StreetAddress)));
                    profile.setCity(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.City)));
                    profile.setState(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.State)));
                    profile.setZipCode(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Zipcode)));
                   profile.setEmail( cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Email)));
                   profile.setPhone( cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Phone)));
                    profile.setCreditCardName(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.NameOnCard)));
                    profile.setCreditCardType(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Cctype)));
                    profile.setCreditCardExp(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Ccnumber)));
                    profile.setCreditCardExp(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Ccexpiry)));

            cursor.close();
            return profile;
        } else {
            return null;
        }


    }

    public Room searchRoom(String roomNumber) {
        String[] columns = {
                SQLiteHelper.RNUM,
                SQLiteHelper.RType,
                SQLiteHelper.RPNight,
                SQLiteHelper.RStatus
        };

        Cursor cursor = db.query(SQLiteHelper.RoomDetails, columns, SQLiteHelper.RNUM + " = ?", new String[]{roomNumber}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Room room = new Room(
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.RNUM)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.RType)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.RPNight)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.RStatus))
            );
            cursor.close();
            return room;
        } else {
            return null;
        }
    }
    @SuppressLint("Range")
    public String getUserRole(String username, String password) {
        // Assume you have a SQLiteDatabase instance named db
        Cursor cursor = db.rawQuery("SELECT Role FROM system_user WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("Role"));
        }
        return null; // return null if user not found or password is incorrect
    }
    @SuppressLint("Range")
    public ArrayList<Profile> searchByLastName(String lastName) {
        ArrayList<Profile> profiles = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM system_user WHERE lastName LIKE ?", new String[]{ "%" + lastName + "%" });

        if (cursor.moveToFirst()) {
            do {
                Profile profile = new Profile();
                // Assuming Profile class has these fields
                profile.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                profile.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                profile.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
                // Add other fields as necessary

                profiles.add(profile);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return profiles;
    }

    @SuppressLint("Range")
    public ArrayList<Room> getRoomDetails(String location, String roomType) {
        ArrayList<Room> rooms = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();

        String query = "SELECT * FROM rooms WHERE hotelLocation = ? AND roomType = ?";
        Cursor cursor = db.rawQuery(query, new String[]{location, roomType});

        if (cursor.moveToFirst()) {
            do {
                 Room room = new Room();

                room.setHotelName(cursor.getString(cursor.getColumnIndex("hotelName")));
                room.setHotelLocation(cursor.getString(cursor.getColumnIndex("hotelLocation")));
                room.setRoomType(cursor.getString(cursor.getColumnIndex("roomType")));
                room.setNumberOfBeds(cursor.getString(cursor.getColumnIndex("numberOfBeds")));
                room.setRoomFacilities(cursor.getString(cursor.getColumnIndex("roomFacilities")));
                room.setNumberOfNights(cursor.getString(cursor.getColumnIndex("numberOfNights")));
                room.setPricePerNight(cursor.getString(cursor.getColumnIndex("pricePerNight")));
                room.setNumberOfRooms(cursor.getString(cursor.getColumnIndex("numberOfRooms")));
                room.setNumberOfAdults(cursor.getString(cursor.getColumnIndex("numberOfAdults")));
                room.setNumberOfChildren(cursor.getString(cursor.getColumnIndex("numberOfChildren")));
                room.setBookingId(cursor.getString(cursor.getColumnIndex("bookingId")));
                room.setTotalPrice(cursor.getString(cursor.getColumnIndex("totalPrice")));
                rooms.add(room);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return rooms;
    }

    public Room getRoom(String roomNumber) {
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM rooms WHERE RoomNumber = ?", new String[]{roomNumber});

        if (cursor != null && cursor.moveToFirst()) {
            Room room = new Room(
                    cursor.getString(cursor.getColumnIndexOrThrow("RoomNumber")),
                    cursor.getString(cursor.getColumnIndexOrThrow("roomType")),
                    cursor.getString(cursor.getColumnIndexOrThrow("pricePerNight")),
                    cursor.getString(cursor.getColumnIndexOrThrow("roomStatus"))
            );
            cursor.close();
            return room;
        } else {
            return null;
        }
    }


}
