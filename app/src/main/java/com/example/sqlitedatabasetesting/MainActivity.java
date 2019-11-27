package com.example.sqlitedatabasetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sqlitedatabasetesting.SQLite.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

    }
}
