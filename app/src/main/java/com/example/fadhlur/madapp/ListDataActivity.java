package com.example.fadhlur.madapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    // Declare variables
    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDatabaseHelper = new DatabaseHelper(this);

        // Populate the views to show the data from the ArrayList
        populateListView();
        populateRecyclerView();
    }

    // Repopulates the views after resuming this intent
    @Override
    protected void onResume() {
        super.onResume();
        populateListView();
        populateRecyclerView();
    }

    private void populateRecyclerView() {
        // Get the data from the database
        Cursor data = mDatabaseHelper.getData();

        ArrayList<String> recyclerViewData = new ArrayList<>();

        // Add the received data to the ArrayList
        while (data.moveToNext()) {
            recyclerViewData.add(data.getString(1));
        }

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerViewAdapter(recyclerViewData);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying the data in the ListView");

        // Get the data from the database
        Cursor data = mDatabaseHelper.getData();

        // Add the received data to the ArrayList
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        // If data has been selected, start intent to edit or delete the data
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: id selected is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id", itemID);
                    editScreenIntent.putExtra("name" , name);
                    startActivity(editScreenIntent);
                }
                else {
                    toastMessage("No ID known with that name");
                }
            }
        });
    }

    // Displays a toast message
    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
