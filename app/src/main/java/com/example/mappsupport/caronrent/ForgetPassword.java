package com.example.mappsupport.caronrent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.SignUpDataBase;

public class ForgetPassword extends AppCompatActivity {

    EditText et_email,et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        et_email=(EditText)findViewById(R.id.et_email);
        et_pass=(EditText)findViewById(R.id.et_pass);
    }

    public void resetPassword(View view) {
        String email=et_email.getText().toString().trim();
        String pass=et_pass.getText().toString().trim();
        SignUpDataBase signUpDataBase=new SignUpDataBase(this);
        SQLiteDatabase db=signUpDataBase.getWritableDatabase();
        String query="update userRegister set pass='"+pass+"' where email='"+email+"'";
        Cursor cursor=db.rawQuery(query,null);
        boolean res=cursor.moveToFirst();
        if(res){
            String passw=cursor.getString(2);
            Toast.makeText(this, "Password Reset Sucessfully"+passw, Toast.LENGTH_SHORT).show();

        }
    }
}
