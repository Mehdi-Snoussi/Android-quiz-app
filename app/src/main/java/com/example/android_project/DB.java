package com.example.android_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuizManager.db";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_RATE = "Rate";
    private static final String KEY_ID = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_RATE_ID = "id";
    private static final String KEY_RATE = "rateUs";

    public DB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                +KEY_ID + " TEXT PRIMARY KEY," +KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_RATE_TABLE = "CREATE TABLE " + TABLE_RATE + "("
                +KEY_RATE_ID + " Integer PRIMARY KEY," +KEY_RATE + " TEXT" + ")";
        db.execSQL(CREATE_RATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete the old table
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_RATE);
        //create a new table
        onCreate(db);
    }

    public Boolean insertData(Users users){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", users.getUsername());
        contentValues.put("password", users.getPassword());
        long result = MyDB.insert(TABLE_USERS, null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.query(TABLE_USERS,new String[]{KEY_ID},
                KEY_ID+"=?", new String[]{username},null,null,null,null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(Users users){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",
                new String[] {users.getUsername(),users.getPassword()});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void addRate(RateUs rateUs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RATE, rateUs.getRateUs());
        // insert line
        db.insert(TABLE_RATE,null,values);
        //close db connexion
        db.close();
    }

    public List<RateUs> getAllRate(){
        List<RateUs> rateModel = new ArrayList<RateUs>();

        String selectQuery = "SELECT *FROM "+TABLE_RATE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                RateUs r = new RateUs();
                r.setId(Integer.parseInt(cursor.getString(0)));
                r.setRateUs(cursor.getString(1));

                rateModel.add(r);

            } while (cursor.moveToNext());
        }
        return rateModel;

    }
}
