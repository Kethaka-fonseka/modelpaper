package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHelper;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username=findViewById(R.id.etUsername);
        password=findViewById(R.id.etPassword);


    }

    public void addInfor(View view){
        DBHelper dbHelper=new DBHelper(this);
        long val=dbHelper.addInfor(username.getText().toString(),password.getText().toString());
        if(val>0){
            Toast.makeText(getApplicationContext(),"Registration success",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,ProfileManagement.class);
            intent.putExtra("username",username.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
        }
    }

    public void loginUser(View view){
        DBHelper dbHelper=new DBHelper(this);
        ArrayList usernames=dbHelper.readAllInfor("username");
        ArrayList passwords=dbHelper.readAllInfor("password");

        if(usernames.indexOf(username.getText().toString())>=0){
            if(passwords.get(usernames.indexOf(username.getText().toString())).equals(password.getText().toString())){
                Toast.makeText(getApplicationContext(),"Login  Success",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,ProfileManagement.class);
                intent.putExtra("username",username.getText().toString());
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
        }
    }


}