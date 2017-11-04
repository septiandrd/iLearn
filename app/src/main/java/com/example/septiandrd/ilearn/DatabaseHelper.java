package com.example.septiandrd.ilearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by septiandrd on 8/22/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ilearn.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_CREATE = "create table users (id integer primary key not null , " +
            "name text not null, phone text not null, email text not null, password text not null);";

    SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST "+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertUser(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query,null);

        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME , user.getName());
        values.put(COLUMN_PHONE , user.getPhone());
        values.put(COLUMN_EMAIL , user.getEmail());
        values.put(COLUMN_PASSWORD , user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public User searcUser(String email) {
        String email_db,name, phone, pass;
        User user = null;
        name = "";
        phone = "";
        pass = "";
        db = this.getReadableDatabase();
        String query = "select email,name,phone,password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                email_db = cursor.getString(0);
                if (email_db.equals(email)) {
                    name = cursor.getString(1);
                    phone = cursor.getString(2);
                    pass = cursor.getString(3);
                    user = new User(name,phone,email_db,pass);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return user;
    }

    public String searchPassword(String email) {
        String email_db,password;
        password = "";
        db = this.getReadableDatabase();
        String query = "select email, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                email_db = cursor.getString(0);
                if (email_db.equals(email)) {
                    password = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return password;
    }
}
