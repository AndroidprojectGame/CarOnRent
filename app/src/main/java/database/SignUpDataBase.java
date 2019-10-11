package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by mappsupport on 11/17/2017.
 */

public class SignUpDataBase extends SQLiteOpenHelper
{
    Context context;
    public SignUpDataBase(Context context)
    {
        super(context,"userRegister",null,1);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table userRegister(name TEXT, email TEXT PRIMARY KEY, pass TEXT, phone TEXT)";
        db.execSQL(query);
        Toast.makeText(context, "Table created sucessfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
