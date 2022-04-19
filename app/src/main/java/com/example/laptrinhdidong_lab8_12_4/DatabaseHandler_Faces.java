package com.example.laptrinhdidong_lab8_12_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler_Faces extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FacesManager";
    private static final String TABLE_FACES = "Faces";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_HAPPY = "happy";
    private static final String KEY_SAD = "sad";
    private static final String KEY_NORMAL = "normal";

    public DatabaseHandler_Faces(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FACES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT" + KEY_HAPPY + "TEXT" + KEY_SAD + "TEXT" + KEY_NORMAL + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACES);
        onCreate(db);
    }
    void addContact(Faces faces) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, faces.getEmail());
        values.put(KEY_HAPPY, faces.getHappy());
        values.put(KEY_SAD, faces.getSad());
        values.put(KEY_NORMAL, faces.getNormal());
        db.insert(TABLE_FACES, null, values);
        db.close(); // Closing database connection
    }
    public List<Faces> getFaces() {
        List<Faces> list = new ArrayList<Faces>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FACES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Faces faces = new Faces();
                faces.setId(Integer.parseInt(cursor.getString(0)));
                faces.setEmail(cursor.getString(1));
                faces.setHappy(Integer.parseInt(cursor.getString(2)));
                faces.setSad(Integer.parseInt(cursor.getString(3)));
                faces.setNormal(Integer.parseInt(cursor.getString(4)));

                // Adding people to list
                list.add(faces);
            } while (cursor.moveToNext());
        }

        return list;
    }
}
