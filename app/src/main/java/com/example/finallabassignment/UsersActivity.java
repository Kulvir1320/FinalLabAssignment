package com.example.finallabassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    ListView listView;
    DataBaseHelper mDatabase;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        listView = findViewById(R.id.listView);
        userList = new ArrayList<>();
        mDatabase = new DataBaseHelper(this);
        loadUsers();
    }

    private void loadUsers() {
        Cursor cursor = mDatabase.getAllUsers();
        if (cursor.moveToFirst()) {
            System.out.println("if is working");
            do {
                System.out.println("do is working");
                userList.add(new User(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)));
                System.out.println(cursor.getInt(0));
                System.out.println(cursor.getString(1));

            } while (cursor.moveToNext());
            cursor.close();
            System.out.println("");

            System.out.println("data loaded");

            //show items in a listview
            // we use a custom adapter to show employee
            UserAdapter userAdapter = new UserAdapter(this, R.layout.list_layout, userList, mDatabase);
            listView.setAdapter(userAdapter);
        }
    }
}
