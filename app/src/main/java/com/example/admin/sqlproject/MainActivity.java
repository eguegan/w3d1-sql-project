package com.example.admin.sqlproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String EXAMPLE_NAME = "Edwin";
    private static final String EXAMPLE_AGE = "63";

    private static final String TAG = "MainActivityTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSql(){
        UsersDatabaseHelper usersDatabaseHelper = new UsersDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = usersDatabaseHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(UsersDatabaseHelper.KEY_USER_NAME, EXAMPLE_NAME);
            values.put(UsersDatabaseHelper.KEY_AGE, EXAMPLE_AGE);

            db.insertOrThrow(UsersDatabaseHelper.TABLE_USERS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }
}
