package com.assignment.avonetproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class db_manager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Assignment.db";
    private static final int    DATABASE_VERSION = 1;

    db_manager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(db_tables.CREATE_EXPENSE_TABLE);


        } catch (Exception e) {
            Log.e(db_manager.class.getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + db_tables.TABLE_NAME_EXPENSE);
        onCreate(db);
    }

}
