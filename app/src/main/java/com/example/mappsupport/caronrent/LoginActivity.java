package com.example.mappsupport.caronrent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.SignUpDataBase;

public class LoginActivity extends AppCompatActivity {

    EditText et_email,et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email=(EditText)findViewById(R.id.et_email);
        et_pass=(EditText)findViewById(R.id.et_pass);
    }

    public void forgetPassword(View view) {
        startActivity(new Intent(this,ForgetPassword.class));
    }

    public void signUp(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
    }

    public void userLogin(View view)
    {
        String email=et_email.getText().toString().trim();
        String pass=et_pass.getText().toString().trim();
        if(email.equals("")){
            et_email.requestFocus();
            et_email.setError("Enter email");
            return;
        }if(pass.equals("")){
            et_pass.requestFocus();
            et_pass.setError("Enter password");
            return;
        }
        else{
        SignUpDataBase signUpDataBase=new SignUpDataBase(this);
        SQLiteDatabase db=signUpDataBase.getWritableDatabase();
        String query="select * from userRegister where email='"+email+"' and pass='"+pass+"'";
        Cursor cursor=db.rawQuery(query,null);
        boolean res=cursor.moveToFirst();
        if(res){
            String name=cursor.getString(0);
            Intent intent=new Intent(this,HomeActivity.class);
            intent.putExtra("user",name);
            startActivity(intent);
            Toast.makeText(this, "Sucessfull", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Enter Valid Email and Password", Toast.LENGTH_SHORT).show();
        }
        }
    }
}
