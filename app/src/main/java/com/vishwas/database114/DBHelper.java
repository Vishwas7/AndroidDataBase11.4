package com.vishwas.database114;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vishwas on 9/4/2017.
 * creating class DBHelper and extending with SQLiteOpenHelper..
 * and implementing onCreate and onUpgrade methods and one construction..
 */

public class DBHelper extends SQLiteOpenHelper {
    //data base name
    private static final String THE_NAME_OF_DATABASE = " Employee.db";
    //table name
    private static final String THE_NAME_OF_TABLE = "employee_table";
    //column
    private static final String column_1 = "id";
    private static final String column_2 = "firstname";
    private static final String column_3 = "lastname";


    public DBHelper(Context context) {
        super(context, THE_NAME_OF_DATABASE, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + THE_NAME_OF_TABLE + " ("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+column_2+ " TEXT, " + column_3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + THE_NAME_OF_TABLE);
        onCreate(db);
    }

    boolean insertData(String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_2, firstName);
        contentValues.put(column_3, lastName);

        long result = db.insert(THE_NAME_OF_TABLE, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + THE_NAME_OF_TABLE, null);
    }

    public boolean updateData(String id, String firstname, String lastname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_1, id);
        contentValues.put(column_2,firstname );
        contentValues.put(column_3, lastname);

        db.update(THE_NAME_OF_TABLE, contentValues, column_1 + " = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(THE_NAME_OF_TABLE, column_1 + " = ?", new String[]{id});
    }
}