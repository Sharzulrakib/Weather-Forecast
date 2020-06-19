package com.example.anik.weatherv3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Anik on 12/3/2017.
 */

public class My_Database extends SQLiteOpenHelper{
    private static final String TABLE = "Weather_Table";
    private String KEY_Des="Description";
    private String KEY_Main="Weather";


    public My_Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("

                + KEY_Main + " text not null, "
                + KEY_Des + " text not null"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);

        // Create tables again
        onCreate(sqLiteDatabase);
    }


    public void storeNewData(final ArrayList<String[]> upData) {


        if (upData == null)
            return;

        AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {
            private ManagePanes managePanes = new ManagePanes();

            @Override
            protected String doInBackground(String... voids) {
                Log.d("", "");
                managePanes.setCursorToBeg();
                for (int i = upData.size() - 1; i >= 0; i--) {
                    publishProgress(upData.get(i));
                }

                return null;
            }
        };
    }
}
