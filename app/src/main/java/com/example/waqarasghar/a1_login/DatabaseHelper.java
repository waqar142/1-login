package com.example.waqarasghar.a1_login;

/**
 * Created by waqar asghar on 1/18/2017.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "contact.db";
    public static final String TABLE_NAME = "contact";
    public static final String COLUMN_UserName = "username";
    public static final String COLUMN_Password = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contact (username text," +
            "password text)";

    public DatabaseHelper(Context context)
    {
        super(context , DATABASE_NAME, null,DATABASE_VERSION);
    }


    String uname="waqar";
    String password="12345";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;


    }

    public  void insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(COLUMN_UserName , uname );
        CV.put(COLUMN_Password , password);
        db.insert(TABLE_NAME , null , CV);
        db.close();
    }
    public String matchAuthentication(String uname){
        SQLiteDatabase   db=this.getReadableDatabase();

        Cursor cur = db.rawQuery("select * from " + TABLE_NAME , null );

        String usrnam,b;
        b="Not Found";

        if (cur.moveToFirst()){
            do {
                usrnam=cur.getString(0);
                if(usrnam.equals(uname)){
                    b=cur.getString(1);
                    break;

                }
            }while (cur.moveToNext());

        }

        return b;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists " + TABLE_NAME);
        onCreate(db);

    }
}
