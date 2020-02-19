package com.example.finallabassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper mDatabase;
    EditText firstName, lastName, address, phone;
    Button Add, btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.et_firstname);
        lastName = findViewById(R.id.et_lastname);
        address = findViewById(R.id.et_address);
        phone = findViewById(R.id.et_phone);
        Add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view);
        mDatabase = new DataBaseHelper(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UsersActivity.class);
                startActivity(intent);
            }
        });





    }
    private void addUser() {
       String fname = firstName.getText().toString().trim();
       String lname = lastName.getText().toString().trim();
       String addrs = address.getText().toString().trim();
       String phn = phone.getText().toString().trim();
       if(fname.isEmpty()) {
           firstName.setError("name is manadatory");
           firstName.requestFocus();
           return;
       }
       if(mDatabase.addUser(fname,lname,addrs,phn)){
           Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(this, "user not added", Toast.LENGTH_SHORT).show();
       }
    }

}
