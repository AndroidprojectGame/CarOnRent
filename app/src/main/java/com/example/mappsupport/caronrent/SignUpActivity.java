package com.example.mappsupport.caronrent;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.SignUpDataBase;

public class SignUpActivity extends AppCompatActivity{

    EditText et_name,et_email,et_pass,et_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findId();
        initateView();
    }

    public void findId(){
        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_pass=(EditText)findViewById(R.id.et_pass);
        et_phone=(EditText)findViewById(R.id.et_phone);
    }

    public void initateView(){

    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void login(View view) throws IOException {
        //startActivity(new Intent(this,LoginActivity.class));
//        int pid = android.os.Process.myPid();
//        android.os.Process.killProcess(pid);
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        startActivity(intent);
        ActivityCompat.finishAffinity(this);
        System.exit(0);
    }


    public void signUp(View view)
    {
        String name=et_name.getText().toString().trim();
        String email=et_email.getText().toString().trim();
        String pass=et_pass.getText().toString().trim();
        String phone=et_phone.getText().toString().trim();

        if(!name.matches("[a-zA-Z ]+")){
            et_name.requestFocus();
            et_name.setError("Enter Valid Name");
            return;
        }
        else if(!isValidEmail(email)){
            et_email.requestFocus();
            et_email.setError("Enter Valid Email");
            return;
        }
        else if(!isValidPassword(pass)){
            et_pass.requestFocus();
            et_pass.setError("Enter atleast 6 digit");
            return;
        }
        else if(!isValidPhone(phone)){
            et_phone.requestFocus();
            et_phone.setError("Enter Valid Contact Number");
            return;
        }
        else{
            try {
                SignUpDataBase signUpDataBase = new SignUpDataBase(this);
                SQLiteDatabase db = signUpDataBase.getWritableDatabase();
                String query = "insert into userRegister values('" + name + "','" + email + "','" + pass + "','" + phone + "')";
                db.execSQL(query);
                Toast.makeText(this, "Value inserted sucessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }catch (SQLiteConstraintException e){
                et_email.requestFocus();
                et_email.setError("Email already exit");
                Toast.makeText(this, "Email id already exist", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }


    private boolean isValidName(String name) {
        String NAME = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
        Pattern pattern = Pattern.compile(NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
    private boolean isValidPhone(String phone) {
        String NAME = "^[2-9]{2}[0-9]{8}$";
        Pattern pattern = Pattern.compile(NAME);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
