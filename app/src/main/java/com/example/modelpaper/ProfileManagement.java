package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.example.modelpaper.Database.DBHelper;

import java.util.ArrayList;

public class ProfileManagement extends AppCompatActivity {
EditText username,dob,password;
RadioGroup gender;
Button  update;
RadioButton radioButton,male,female;
String g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        final Intent intent=getIntent();
        final String uname=intent.getStringExtra("username");
        username=findViewById(R.id.proUsername);
        dob=findViewById(R.id.proDob);
        update=findViewById(R.id.btnUpdate);
        male=findViewById(R.id.proMale);
        female=findViewById(R.id.proFemale);
        password=findViewById(R.id.proPassword);
        gender=findViewById(R.id.proGenderGroup);
        username.setText(uname);
        DBHelper dbHelper=new DBHelper(this);
        ArrayList dobs=dbHelper.readAllInfor(uname,"dob");
        ArrayList passwords=dbHelper.readAllInfor(uname,"password");
        ArrayList genders=dbHelper.readAllInfor(uname,"gender");

        if(dobs.get(0)==null){
            dob.setText("===Empty===");

        }
        else{
            dob.setText(dobs.get(0).toString().trim());
        }

        if(genders.get(0)==null){
            male.setChecked(true);
        }
        else{
            if(genders.get(0).toString().equals("Male")){
                male.setChecked(true);
            }
            else{
           female.setChecked(true);
            }

        }

        password.setText(passwords.get(0).toString().trim());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=gender.getCheckedRadioButtonId();
                radioButton=findViewById(id);
                Intent intent1=new Intent(getApplicationContext(),EditProfile.class);
                intent1.putExtra("username",uname);
                intent1.putExtra("dob",dob.getText().toString());
                intent1.putExtra("password",password.getText().toString());
                intent1.putExtra("gender",radioButton.getText().toString());
                startActivity(intent1);
            }
        });
    }




}