package com.example.a10117279_wisatabandung;

//Tanggal : 8 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DBDataSource extends AppCompatActivity {

    private SQLiteDatabase database;

    private DBHelper dbHelper;

    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAMA, DBHelper.COLUMN_DESKRIPSI,
            DBHelper.COLUMN_LATITUDE, DBHelper.COLUMN_LONGITUDE, DBHelper.COLUMN_IMAGE, DBHelper.COLUMN_STATUS};

    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private Model cursorToModel(Cursor cursor)
    {
        Model model = new Model();
        // debug LOGCAT
        Log.v("info", "The setLatLng "+cursor.getString(0)+","+cursor.getString(1));

        model.setId(cursor.getLong(0));
        model.setNama(cursor.getString(1));
        model.setDeskripsi(cursor.getString(2));
        model.setLatitude(cursor.getString(3));
        model.setLongitude(cursor.getString(4));
        model.setImage(cursor.getString(5));
        model.setStatus(cursor.getString(6));

        return model;
    }

    public ArrayList<Model> getAllData() {
        ArrayList<Model> listPlaces = new ArrayList<Model>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Model daftarTempat = cursorToModel(cursor);
            listPlaces.add(daftarTempat);
            cursor.moveToNext();
        }
        cursor.close();
        return listPlaces;
    }

    public void updateFav(Model b)
    {
        String strFilter = "id=" + b.getId();

        ContentValues args = new ContentValues();

        args.put(DBHelper.COLUMN_NAMA, b.getNama());
        args.put(DBHelper.COLUMN_DESKRIPSI, b.getDeskripsi());
        args.put(DBHelper.COLUMN_LATITUDE, b.getLatitude());
        args.put(DBHelper.COLUMN_LONGITUDE, b.getLongitude());
        args.put(DBHelper.COLUMN_IMAGE, b.getImage());
        args.put(DBHelper.COLUMN_STATUS, b.getStatus());

        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public ArrayList<Model> getFavData() {
        ArrayList<Model> listPlaces = new ArrayList<Model>();

        String whereClause = "status = ?";
        String[] whereArgs = new String[] {
                "1"
        };

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Model daftarTempat = cursorToModel(cursor);
            listPlaces.add(daftarTempat);
            cursor.moveToNext();
        }

        cursor.close();
        return listPlaces;
    }
}