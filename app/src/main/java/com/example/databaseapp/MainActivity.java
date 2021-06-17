package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonSave;
    private Button mButtonGet;
    private DBHelperClass mDBHelperClass;
    private String tableName;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableName ="users";
        SQLiteDatabase mydatabase = openOrCreateDatabase("usersDB", MODE_PRIVATE,null);
        mDBHelperClass = new DBHelperClass(mydatabase);
        mDBHelperClass.createTable(tableName, "first_name", "last_name");

        Button buttonSave = findViewById(R.id.buttonAddUser);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fname = findViewById(R.id.editTextFirstName);
                EditText lname = findViewById(R.id.editTextLastName);
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();

                mDBHelperClass.insertData(tableName, firstname, lastname);
                Toast.makeText(MainActivity.this, "Data is saved", Toast.LENGTH_LONG).show();

            }
        });

        Button buttonGetUser = findViewById(R.id.buttonGetUser);
        buttonGetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = mDBHelperClass.getData(tableName);
                cursor.moveToFirst();
                String user = "First name = " + cursor.getString(0)
                        + "last name = " + cursor.getString(1);
                Toast.makeText(MainActivity.this, user, Toast.LENGTH_LONG).show();
                TextView textView = findViewById(R.id.textView);
                textView.setText(user);
            }
        });

        Button buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cursor.moveToNext()){
                    String user = "First name = " + cursor.getString(0)
                            + "last name = " + cursor.getString(1);
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(user);
                }else{
                    Toast.makeText(MainActivity.this, "No more records", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}