package com.example.fadhlur.madapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    addData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("Insert something a niffo");
                }
            }
        });

        // Start new activity after button click
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    // Inserts the data to the database. Shows a toast message depending on the outcome
    public void addData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted");
        } else {
            toastMessage("Something went wrong");
        }
    }

    // Displays a toast message
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
