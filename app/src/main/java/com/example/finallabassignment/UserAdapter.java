package com.example.finallabassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter {
    Context mContext;
    int layoutResource;
    List<User> user;
    DataBaseHelper mDatabase;

    public UserAdapter(@NonNull  Context mContext, int layoutResource, List<User> user, DataBaseHelper mDatabase) {
        super(mContext, layoutResource);
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.user = user;
        this.mDatabase = mDatabase;
    }

//
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View v = layoutInflater.inflate(layoutResource,null);

        TextView tvfirstname = v.findViewById(R.id.tv_firstname);
        TextView tvlastname = v.findViewById(R.id.tv_lastname);
        TextView tvaddress = v.findViewById(R.id.tv_address);
        TextView tvphone = v.findViewById(R.id.tv_phone);

         final User users = user.get(position);
         tvfirstname.setText(users.getFirstname());
         tvlastname.setText(users.getLastName());
         tvaddress.setText(users.getAddress());
         tvphone.setText(users.getPhone());

         v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 deleteUser(users);
             }
         });
         v.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 updateUser(users);
             }
         });
        return  v;


    }
     private  void updateUser(final User user){


     }
     private  void deleteUser(final User user) {
         AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
         builder.setTitle("Are you sure");
         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                /*
                String sql = "DElETE FROM employees WHERE id =?";
                mDatabase.execSQL(sql,new Integer[] {employee.getId()});

                 */
                 if(mDatabase.deleteUser(user.getId()))
                     loadUser();
             }
         });
         builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

             }
         });
         AlertDialog alertDialog = builder.create();
         alertDialog.show();

     }
     private void loadUser() {
         Cursor cursor = mDatabase.getAllUsers();
         if (cursor.moveToFirst()){
             user.clear();
             do{
//
                 user.add(new User(cursor.getInt(0),cursor.getString(1),
                         cursor.getString(2),
                         cursor.getString(3),
                         cursor.getInt(4)));
                }
             while (cursor.moveToNext());
             cursor.close();
         }
//         notifyDataSetChanged();

     }

    }

