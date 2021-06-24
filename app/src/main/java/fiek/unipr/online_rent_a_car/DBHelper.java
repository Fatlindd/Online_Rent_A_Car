package fiek.unipr.online_rent_a_car;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //Momentin qe ndryshohet versioni Databazes ekzekutohet funksioni onUpgrade
    public DBHelper( Context context) {
        super(context, "LoginDB", null, 1);
    }
   //Tek onCreate krijohet tabela per databazen
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create Table Users(username Text,lastname Text,email Text," +
              "number Text,password Text)");
    }
  //Kur dojm me bon Upgrading Aplikacionin ose me Databazen , atehere perdorim Drop Table per ta fshi tabelen Users
  // e cila tabel permban perdorust, dhe nje databaze e re pastaj krijohet me ane te konstruktorit DBHelper.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP Table IF EXISTS Users");
    }

    public Boolean insertData(String username,String lastname ,String email, String number, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("username",username);
        contentValues.put("lastname",lastname);
        contentValues.put("email",email);
        contentValues.put("number",number);
        contentValues.put("password",password);
        long result=db.insert("Users",null,contentValues);

    //Nese deshton atehere kthen -1 dhe ruhet tek result nalt , -1 nenkupton kur ka deshtime

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Metoda radhes eshte per te kontrolluar nese perdoruesi ekziston ne DataBazen tone ose jo!
    //Dhe pranon nje parameter username per te kontrolluar
    public Boolean check_user(String username){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Users WHERE username = ?",new String[]{username});
         //Cursor ka akses per te lexu dhe shkruar nese query qe ekzekutohet kthen te dhena .
        //cursor pas kesaj metode permban disa te dhena prandaj duhet te shohim nese ka apo jo te dhena rreth perdoruesit!
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean check_password(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE password = ?",new String[]{password});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    //Metoda per passwordin e perdoresit
    public Boolean check_username_password(String username ,String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?",new String[]{username,password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
