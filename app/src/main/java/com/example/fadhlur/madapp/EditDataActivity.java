package com.example.fadhlur.madapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    // Declare variables
    private static final String TAG = "EditDataActivity";
    private Button btnSave, btnDelete;
    private EditText editable_item;
    private    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        mDatabaseHelper = new DatabaseHelper(this);

        // Get the intent from listDataActivity
        Intent receivedIntent = getIntent();

        // Get the itemID passed by the intent
        selectedID = receivedIntent.getIntExtra("id" , -1);

        // Get the value passed by the intent extra
        selectedName = receivedIntent.getStringExtra("name");

        // Set the value to the textfield
        editable_item.setText(selectedName);

        // If the button has been clicked, update the name in the database
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();

                if (!item.equals("")) {
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                    toastMessage("The item has been updated");

                } else {
                    toastMessage("Please fill something in");
                }
            }
        });

        // If the button has been pressed, delete the item from the database
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("The item has been removed");
                finish();
            }
        });
    }

    // Displays a toast message
    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
