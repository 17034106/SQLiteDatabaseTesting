package com.example.sqlitedatabasetesting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedatabasetesting.SQLite.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB;

    EditText etName, etSurname, etMarks, etId;
    Button btnAddData, btnViewAll, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etMarks = findViewById(R.id.etMarks);
        etId = findViewById(R.id.etId);
        btnAddData = findViewById(R.id.button_add);
        btnViewAll = findViewById(R.id.button_viewAll);
        btnUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);

        btnAddData.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        myDB = new DatabaseHelper(this);



    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_add:
                AddData();
                break;


            case R.id.button_viewAll:
                viewData();
                break;

            case R.id.button_update:
                updateData();
                break;

            case R.id.button_delete:
                deleteData();
                break;
        }
    }


    public void AddData(){
        boolean isInserted = myDB.insertData(etName.getText().toString(), etSurname.getText().toString(), Integer.parseInt(etMarks.getText().toString()));

        if (isInserted){
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data Inserted Failed", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, etName.getText().toString()+" "+etSurname.getText().toString()+" "+etMarks.getText().toString(), Toast.LENGTH_SHORT).show();

    }


    public void viewData(){
        Cursor res = myDB.getAllData();

        if (res.getCount() == 0){
            Toast.makeText(this, "No Available Data", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer buffer = new StringBuffer();

            while (res.moveToNext()){
                buffer.append("ID: " + res.getString(0) + "\nName: "+ res.getString(1) + "\nSurname: " + res.getString(2) + "\nMarks: " + res.getString(3) + "\n\n");
            }

            showMessage("Data", buffer.toString());



        }
    }


    public void updateData(){

        boolean isUpdate = myDB.updateData(etId.getText().toString(), etName.getText().toString(), etSurname.getText().toString(), Integer.parseInt(etMarks.getText().toString()));

        if (isUpdate){
            showMessage("Update", "Updated Successfully");
        }
        else{
            showMessage("Update", "Updated Failed");
        }

    }

    public void deleteData(){
        Integer deletedRows = myDB.deleteData(etId.getText().toString());
        if (deletedRows>0){
            showMessage("Delete", "Deleted Successfully");
        }
        else{
            showMessage("Delete", "Deleted Failed");
        }
    }



    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}
