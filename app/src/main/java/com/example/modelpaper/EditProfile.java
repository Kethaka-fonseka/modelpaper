package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHelper;

public class EditProfile extends AppCompatActivity {
    EditText username,dob,password;
    Button search;
    RadioGroup gender;
    String uname,date,pwd,Gender;
    RadioButton male,female,radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        username=findViewById(R.id.editUsername);
        Intent intent1=getIntent();
        gender=findViewById(R.id.editRadioGroup);
        Gender=intent1.getStringExtra("gender");
        uname=intent1.getStringExtra("username");
        pwd=intent1.getStringExtra("password");
        date=intent1.getStringExtra("dob");
        dob=findViewById(R.id.editDob);
        male=findViewById(R.id.editMale);
        female=findViewById(R.id.editFemale);
        password=findViewById(R.id.editPassword);
        password.setText(pwd);
        dob.setText(date);
      if(Gender.equals("Male")){
          male.setChecked(true);
      }
      else{
          female.setChecked(true);
      }

        search=findViewById(R.id.searchBtn);
      search.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(getApplicationContext(),ProfileManagement.class);
              intent.putExtra("username",username.getText().toString());
              startActivity(intent);
          }
      });
    }


    public void deleteInfo(View view){
        DBHelper dbHelper=new DBHelper(this);
        long val=dbHelper.deleteInfor(uname);
        if(val>0){
            Toast.makeText(getApplicationContext(),"Delete success",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Delete unsuccess",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateInfo(View view){
        DBHelper dbHelper=new DBHelper(this);
        int id=gender.getCheckedRadioButtonId();
        radioButton=findViewById(id);
        long val= dbHelper.updatInfo(uname,dob.getText().toString(),password.getText().toString(),radioButton.getText().toString());
        if(val>0){
            Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Update unsuccess",Toast.LENGTH_SHORT).show();
        }
    }
}