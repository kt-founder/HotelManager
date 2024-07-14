package com.example.myapp.Activities.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Name
    public static final String dbname = "HotelManagement.db";

    // System Users Table Details
    public static final String System_users = "system_user";
    public static final String Username = "username";
    public static final String Password = "password";
    public static final String LastName = "lastName";
    public static final String FirstName = "firstName";
    public static final String NameOnCard = "creditCardname";
    public static final String Ccnumber = "creditCardNumber";
    public static final String Ccexpiry = "creditCardExpiry";
    public static final String StreetAddress = "streetAddress";
    public static final String City = "city";
    public static final String State = "state";
    public static final String Zipcode = "zipcode";
    public static final String Email = "email";
    public static final String Phone = "phone";
    public static final String Cctype = "creditCardType";

    // Reservation Table Details
    public static final String Reservations = "reservations";
    public static final String Booking_id = "booking_id";
    public static final String Hotel_name = "hotel_name";
    public static final String Hotel_location = "hotel_location";
    public static final String Room_type = "room_type";
    public static final String Number_of_rooms = "number_of_rooms";
    public static final String Number_of_nights = "number_of_nights";
    public static final String Number_of_adults = "number_of_adults";
    public static final String Number_of_children = "number_of_children"; // Fixed typo
    public static final String Check_in_date = "check_in_date";
    public static final String Check_out_date = "check_out_date";
    public static final String Price_per_night = "price_per_night";
    public static final String Tax = "tax";
    public static final String Total_price = "total_price";
    public static final String Billed_price = "billed_price";
    public static final String Billing_address = "billing_address";
    public static final String First_name = "first_name";
    public static final String Last_name = "last_name";
    public static final String Reservation_date = "reservation_date";
    public static final String User = "Username";
    public static final String RN = "RoomNumber";
    public static final String Status = "status";

    // Hotel Table
    public static final String HotelDetails = "hotels";
    public static final String HName = "hotelName";
    public static final String HLoc = "hotelLocation";
    public static final String HManager = "hotelManager";
    public static final String RType = "roomType";
    public static final String NRooms = "numberOfRooms";
    public static final String RPNight = "pricePerNight";

    // Room Table
    public static final String RoomDetails = "rooms";
    public static final String RNUM = "RoomNumber";
    public static final String RBeds = "numberOfBeds";
    public static final String RFacilities = "roomFacilities";
    public static final String RStatus = "roomStatus";
    public static final String NRDate = "nextReservationDate";
    public static final String NREndDate = "nextReservationEndDate";

    public SQLiteHelper(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create System Users Table
        String qry = "CREATE TABLE IF NOT EXISTS " + System_users + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Username + " TEXT UNIQUE, " +
                Password + " TEXT, " +
                FirstName + " TEXT, " +
                LastName + " TEXT, " +
                StreetAddress + " TEXT, " +
                City + " TEXT, " +
                State + " TEXT, " +
                Zipcode + " TEXT, " +
                Email + " TEXT, " +
                Phone + " TEXT, " +
                NameOnCard + " TEXT, " +
                Cctype + " TEXT, " +
                Ccnumber + " TEXT, " +
                Ccexpiry + " TEXT)";
        db.execSQL(qry);

        // Add default Role column
        db.execSQL("ALTER TABLE " + System_users + " ADD COLUMN Role TEXT DEFAULT 'Guest'");

        // Create Reservations Table
        String qry1 = "CREATE TABLE IF NOT EXISTS " + Reservations + " (" +
                Booking_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Hotel_name + " TEXT, " +
                Hotel_location + " TEXT, " +
                Room_type + " TEXT, " +
                Number_of_rooms + " TEXT, " +
                Number_of_nights + " TEXT, " +
                Number_of_adults + " TEXT, " +
                Number_of_children + " TEXT, " + // Fixed typo
                Check_in_date + " TEXT, " +
                Check_out_date + " TEXT, " +
                Price_per_night + " TEXT, " +
                Tax + " TEXT, " +
                Total_price + " TEXT, " +
                Billed_price + " TEXT, " +
                Billing_address + " TEXT, " +
                First_name + " TEXT, " +
                Last_name + " TEXT, " +


                Reservation_date + " TEXT)";
        db.execSQL(qry1);

        // Add Username column to Reservations
        db.execSQL("ALTER TABLE " + Reservations + " ADD COLUMN Username TEXT");

        // Create Hotel Details Table
        String qry2 = "CREATE TABLE IF NOT EXISTS " + HotelDetails + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HName + " TEXT, " +
                HLoc + " TEXT, " +
                RType + " TEXT, " +
                HManager + " TEXT, " +
                NRooms + " TEXT, " +
                RPNight + " TEXT)";
        db.execSQL(qry2);

        // Create Room Details Table
        String qry3 = "CREATE TABLE IF NOT EXISTS " + RoomDetails + " (" +
                RNUM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HName + " TEXT, " +
                HLoc + " TEXT, " +
                RType + " TEXT, " +
                HManager + " TEXT, " +
                NRooms + " TEXT, " +
                RPNight + " TEXT, " +
                RBeds + " TEXT, " +
                RFacilities + " TEXT, " +
                RStatus + " TEXT, " +
                NRDate + " TEXT, " +
                NREndDate + " TEXT)";
        db.execSQL(qry3);
        addDefaultHotelsAndRooms(db);
    }

    private void addDefaultHotelsAndRooms(SQLiteDatabase db) {
        String[] hotelNames = new String[3];
        hotelNames[0] = "Arlington";
        hotelNames[1] = "Dallas";
        hotelNames[2] = "Irving";
        String[] hotelLocations = new String[3];
        hotelLocations[0] = "Ha Noi";
        hotelLocations[1] = "Da Nang";
        hotelLocations[2] = "Ho Chi Minh";
        String hotelManager = "Manager1";
        String[] roomTypes = new String[3];
        roomTypes[0] = "Standard";
        roomTypes[1] = "Deluxe";
        roomTypes[2] = "Suite";

        String[] roomFacilities = new String[3];
        roomFacilities[0] = "WiFi, TV, AC";
        roomFacilities[1] =   "WiFi, TV, AC, Minibar";
        roomFacilities[2] =  "WiFi, TV, Minibar";

        String roomStatus = "Available";
        int roomNumber = 1;

        for (int i = 0; i < hotelNames.length; i++) {
            String insertHotel = "INSERT INTO " + HotelDetails + " (" +
                    HName + ", " +
                    HLoc + ", " +
                    HManager + ") VALUES ('" +
                    hotelNames[i] + "', '" +
                    hotelLocations[i] + "', '" +
                    hotelManager + "')";
            db.execSQL(insertHotel);

            for (int j = 1; j <= 15; j++) {
                String insertRoom = "INSERT INTO " + RoomDetails + " (" +
                        RNUM + ", " +
                        HName + ", " +
                        HLoc + ", " +
                        RType + ", " +
                        HManager + ", " +
                        NRooms + ", " +
                        RPNight + ", " +
                        RBeds + ", " +
                        RFacilities + ", " +
                        RStatus + ") VALUES (" +
                        roomNumber + ", '" +
                        hotelNames[i] + "', '" +
                        hotelLocations[i] + "', '" +
                        roomTypes[j % roomTypes.length] + "', '" +
                        hotelManager + "', '1', '" +
                        (100 + j * 10) + "', '" +
                        (1 + j % 3) + "', '" +
                        roomFacilities[j % roomFacilities.length] + "', '" +
                        roomStatus + "')";
                db.execSQL(insertRoom);
                roomNumber++;
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + System_users);
        db.execSQL("DROP TABLE IF EXISTS " + Reservations);
        db.execSQL("DROP TABLE IF EXISTS " + HotelDetails);
        db.execSQL("DROP TABLE IF EXISTS " + RoomDetails);

        onCreate(db);
    }
}
