package com.example.myapp.Activities.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.Activities.entities.Hotel;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.Activities.entities.Room;

import java.util.ArrayList;
import java.util.List;

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
                    profile.setCreditCardNumber(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.Ccnumber)));
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

    public boolean insertReservation(String hotelName, String hotelLocation, String roomType, String numberOfRooms,
                                     String numberOfNights, String numberOfAdults, String numberOfChildren,
                                     String checkInDate, String checkOutDate, String pricePerNight,
                                     String tax, String total_price, String billed_price, String billing_address,
                                     String firstName, String lastName, String reservation_date, String username, String status, String bookingId) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hotel_name", hotelName);
        contentValues.put("hotel_location", hotelLocation);
        contentValues.put("room_type", roomType);
        contentValues.put("number_of_rooms", numberOfRooms);
        contentValues.put("number_of_nights", numberOfNights);
        contentValues.put("number_of_adults", numberOfAdults);
        contentValues.put("number_of_children", numberOfChildren);
        contentValues.put("check_in_date", checkInDate);
        contentValues.put("check_out_date", checkOutDate);
        contentValues.put("price_per_night", pricePerNight);
        contentValues.put("tax", tax);
        contentValues.put("total_price", total_price);
        contentValues.put("billed_price", billed_price);
        contentValues.put("billing_address", billing_address);
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("reservation_date", reservation_date);
        contentValues.put("Username", username);
        contentValues.put("status",status);
        contentValues.put("booking_id",bookingId);
        long result = db.insert("reservations", null, contentValues);
        return result != -1;
    }

    @SuppressLint("Range")
    public ArrayList<Room> getRoomDetails(String location, String roomType, String checkIn, String checkOut, int NumberOfNight) {
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
//                room.setNumberOfNights(cursor.getString(cursor.getColumnIndex("numberOfNights")));
                room.setPricePerNight(cursor.getString(cursor.getColumnIndex("pricePerNight")));
                room.setNumberOfRooms(cursor.getString(cursor.getColumnIndex("numberOfRooms")));
                room.setNumberOfBeds(cursor.getString(cursor.getColumnIndex("numberOfBeds")));
                room.setRoomStatus(cursor.getString(cursor.getColumnIndex("roomStatus")));
                room.setRoomNumber(cursor.getString(cursor.getColumnIndex("RoomNumber")));
                room.setCheckInDate(checkIn);
                room.setCheckOutDate(checkOut);
                room.setNumberOfNights(String.valueOf(NumberOfNight));
//                room.setNumberOfAdults(cursor.getString(cursor.getColumnIndex("numberOfAdults")));
//                room.setNumberOfChildren(cursor.getString(cursor.getColumnIndex("numberOfChildren")));
//                room.setBookingId(cursor.getString(cursor.getColumnIndex("bookingId")));
//                room.setTotalPrice(cursor.getString(cursor.getColumnIndex("totalPrice")));
                rooms.add(room);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return rooms;
    }

    @SuppressLint("Range")
    public ArrayList<Reservation> getPendingReservations(String username) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        // Include the status check in your query
        Cursor cursor = db.rawQuery("SELECT * FROM reservations WHERE Username = ? AND status = 'pending'", new String[]{username});

        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setBooking_id(cursor.getString(cursor.getColumnIndex("booking_id")));
                reservation.setHotel_name(cursor.getString(cursor.getColumnIndex("hotel_name")));
                reservation.setHotel_location(cursor.getString(cursor.getColumnIndex("hotel_location")));
                reservation.setRoom_type(cursor.getString(cursor.getColumnIndex("room_type")));
                reservation.setCheck_in_date(cursor.getString(cursor.getColumnIndex("check_in_date")));
                reservation.setCheck_out_date(cursor.getString(cursor.getColumnIndex("check_out_date")));
                reservation.setNumber_of_nights(cursor.getString(cursor.getColumnIndex("number_of_nights")));
                reservation.setPrice_per_night(cursor.getString(cursor.getColumnIndex("price_per_night")));
                reservation.setTotal_price(cursor.getString(cursor.getColumnIndex("total_price")));

                reservations.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reservations;
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

    public boolean updateReservationStatus(String bookingId, String newStatus) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", newStatus);  // Only updating the status column

        // Update statement
        int updateStatus = db.update("reservations", contentValues, "booking_id = ?", new String[] {String.valueOf(bookingId)});
        db.close();

        return updateStatus > 0; // Return true if the update was successful
    }

    @SuppressLint("Range")
    public Reservation getReservationByBookingId(String bookingId) {
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.query("reservations",
                null, // all columns
                "booking_id LIKE ?", // conditions
                new String[] {String.valueOf(bookingId)}, // values for conditions
                null, // group by
                null, // having
                null); // order by

        if (cursor != null && cursor.moveToFirst()) {
            Reservation reservation = new Reservation();
            reservation.setBooking_id(cursor.getString(cursor.getColumnIndex("booking_id")));
            reservation.setHotel_name(cursor.getString(cursor.getColumnIndex("hotel_name")));
            reservation.setHotel_location(cursor.getString(cursor.getColumnIndex("hotel_location")));
            reservation.setRoom_type(cursor.getString(cursor.getColumnIndex("room_type")));
            reservation.setNumber_of_rooms(cursor.getString(cursor.getColumnIndex("number_of_rooms")));
            reservation.setNumber_of_nights(cursor.getString(cursor.getColumnIndex("number_of_nights")));
            reservation.setNumber_of_adults(cursor.getString(cursor.getColumnIndex("number_of_adults")));
            reservation.setNumber_of_children(cursor.getString(cursor.getColumnIndex("number_of_children")));
            reservation.setCheck_in_date(cursor.getString(cursor.getColumnIndex("check_in_date")));
            reservation.setCheck_out_date(cursor.getString(cursor.getColumnIndex("check_out_date")));
            reservation.setPrice_per_night(cursor.getString(cursor.getColumnIndex("price_per_night")));
            reservation.setTax(cursor.getString(cursor.getColumnIndex("tax")));
            reservation.setTotal_price(cursor.getString(cursor.getColumnIndex("total_price")));
            reservation.setBilled_price(cursor.getString(cursor.getColumnIndex("billed_price")));
            reservation.setBilling_address(cursor.getString(cursor.getColumnIndex("billing_address")));
            reservation.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
            reservation.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
            reservation.setReservation_date(cursor.getString(cursor.getColumnIndex("reservation_date")));
            reservation.setUsername(cursor.getString(cursor.getColumnIndex("Username")));
            reservation.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            cursor.close();
            db.close();
            return reservation;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null; // return null if reservation not found
    }

    public boolean updateProfile(String username, String password, String firstName, String lastName,
                                 String streetAddress, String city, String state, String zipcode,
                                 String email, String phone) {
        SQLiteDatabase db = this.helper.getWritableDatabase();  // Get the database for writing
        ContentValues contentValues = new ContentValues();

        // Put all values in the ContentValues object
        contentValues.put("password", password);   // Assuming column name is the same as the field name
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("streetAddress", streetAddress);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("zipcode", zipcode);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        // Perform the update on the database table
        int rowsAffected = db.update("system_user", contentValues, "username = ?", new String[] { username });

        db.close();  // Close the database connection
        return rowsAffected > 0;  // Return true if the update was successful
    }

    @SuppressLint("Range")
    public List<Reservation> getReservationsByDate(String date, String username) {
        List<Reservation> reservations = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();

        // Assuming 'reservations' is the table name
        String query = "SELECT * FROM reservations WHERE check_in_date = ? OR check_out_date = ?";
        Cursor cursor = db.rawQuery(query, new String[] {date, date});

        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setBooking_id(cursor.getString(cursor.getColumnIndex("booking_id")));
                reservation.setHotel_name(cursor.getString(cursor.getColumnIndex("hotel_name")));
                reservation.setHotel_location(cursor.getString(cursor.getColumnIndex("hotel_location")));
                reservation.setRoom_type(cursor.getString(cursor.getColumnIndex("room_type")));
                reservation.setNumber_of_rooms(cursor.getString(cursor.getColumnIndex("number_of_rooms")));
                reservation.setNumber_of_nights(cursor.getString(cursor.getColumnIndex("number_of_nights")));
                reservation.setNumber_of_adults(cursor.getString(cursor.getColumnIndex("number_of_adults")));
                reservation.setNumber_of_children(cursor.getString(cursor.getColumnIndex("number_of_children")));
                reservation.setCheck_in_date(cursor.getString(cursor.getColumnIndex("check_in_date")));
                reservation.setCheck_out_date(cursor.getString(cursor.getColumnIndex("check_out_date")));
                reservation.setPrice_per_night(cursor.getString(cursor.getColumnIndex("price_per_night")));
                reservation.setTax(cursor.getString(cursor.getColumnIndex("tax")));
                reservation.setTotal_price(cursor.getString(cursor.getColumnIndex("total_price")));
                reservation.setBilled_price(cursor.getString(cursor.getColumnIndex("billed_price")));
                reservation.setBilling_address(cursor.getString(cursor.getColumnIndex("billing_address")));
                reservation.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
                reservation.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                reservation.setReservation_date(cursor.getString(cursor.getColumnIndex("reservation_date")));
                reservation.setUsername(cursor.getString(cursor.getColumnIndex("Username")));
                reservation.setStatus(cursor.getString(cursor.getColumnIndex("status")));

                reservations.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reservations;
    }
    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        // Delete statement
        String selection = "username = ?";
        String[] selectionArgs = { username };
        int deletedRows = db.delete("system_user", selection, selectionArgs);
        db.close(); // Closing the database connection
        return deletedRows > 0; // Returns true if a row was deleted
    }
    @SuppressLint("Range")
    public List<Room> getAvailableRooms(String type) {
        List<Room> rooms = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor;

        if (type.equals("All")) {
            // Query that fetches all available rooms regardless of type
            cursor = db.query("rooms", null, "roomStatus = ?", new String[]{"Available"}, null, null, null);
        } else {
            // Query that fetches available rooms of a specific type
            String[] selectionArgs = new String[]{"Available", type};
            cursor = db.query("rooms", null, "roomStatus = ? AND roomType = ?", selectionArgs, null, null, null);
        }

        if (cursor.moveToFirst()) {
            do {
                Room room = new Room();
                room.setRoomNumber(cursor.getString(cursor.getColumnIndex("RoomNumber")));
                room.setRoomType(cursor.getString(cursor.getColumnIndex("roomType")));
                room.setPricePerNight(cursor.getString(cursor.getColumnIndex("pricePerNight")));
                rooms.add(room);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();  // Always close the database once done
        return rooms;
    }

    @SuppressLint("Range")
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase(); // Ensure you have a method to get a readable DB

        // Define a SQL query to select all reservations
        String selectQuery = "SELECT * FROM reservations";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setBooking_id(cursor.getString(cursor.getColumnIndex("booking_id")));
                reservation.setHotel_name(cursor.getString(cursor.getColumnIndex("hotel_name")));
                reservation.setHotel_location(cursor.getString(cursor.getColumnIndex("hotel_location")));
                reservation.setRoom_type(cursor.getString(cursor.getColumnIndex("room_type")));
                reservation.setNumber_of_rooms(cursor.getString(cursor.getColumnIndex("number_of_rooms")));
                reservation.setNumber_of_nights(cursor.getString(cursor.getColumnIndex("number_of_nights")));
                reservation.setNumber_of_adults(cursor.getString(cursor.getColumnIndex("number_of_adults")));
                reservation.setNumber_of_children(cursor.getString(cursor.getColumnIndex("number_of_children")));
                reservation.setCheck_in_date(cursor.getString(cursor.getColumnIndex("check_in_date")));
                reservation.setCheck_out_date(cursor.getString(cursor.getColumnIndex("check_out_date")));
                reservation.setPrice_per_night(cursor.getString(cursor.getColumnIndex("price_per_night")));
                reservation.setTax(cursor.getString(cursor.getColumnIndex("tax")));
                reservation.setTotal_price(cursor.getString(cursor.getColumnIndex("total_price")));
                reservation.setBilled_price(cursor.getString(cursor.getColumnIndex("billed_price")));
                reservation.setBilling_address(cursor.getString(cursor.getColumnIndex("billing_address")));
                reservation.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
                reservation.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                reservation.setReservation_date(cursor.getString(cursor.getColumnIndex("reservation_date")));
                reservation.setUsername(cursor.getString(cursor.getColumnIndex("Username")));
                reservation.setStatus(cursor.getString(cursor.getColumnIndex("status")));

                reservations.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reservations;
    }

    public boolean updateRoomStatus(String roomNumber, String newStatus) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", newStatus);  // Assuming 'status' is the column name in your room table

        // Perform the update and check the result
        int rowsAffected = db.update("rooms", contentValues, "roomNumber = ?", new String[]{roomNumber});
        db.close(); // Close the database to free up resources
        return rowsAffected > 0; // If one or more rows are updated, return true
    }
    public boolean updateRoomDetails(String roomNumber, double newPrice, String newStatus, String newType) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("roomType", newType);   // Assuming the column name is 'roomType'
        values.put("pricePerNight", newPrice);  // Assuming the column name is 'pricePerNight'
        values.put("status", newStatus);  // Assuming the column name is 'status'

        // Performing the update operation
        int rowsAffected = db.update("rooms", values, "roomNumber = ?", new String[] {roomNumber});
        return rowsAffected > 0;
    }


}
