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
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDatabaseHelper = new DatabaseHelper(this);

        // Populate the views to show the data from the ArrayList
        populateRecyclerView();
    }

    // Repopulates the views after resuming this intent
    @Override
    protected void onResume() {
        super.onResume();
        populateRecyclerView();
    }

    private void populateRecyclerView() {
        // Get the data from the database
        Cursor data = mDatabaseHelper.getData();

        ArrayList<String> recyclerViewData = new ArrayList<>();
        ArrayList<Integer> recyclerViewInt = new ArrayList<>();

        // Add the received data to the ArrayList
        while (data.moveToNext()) {
            recyclerViewData.add(data.getString(1));
            recyclerViewInt.add(data.getInt(0));
            System.out.println(data.getInt(0));
        }

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerViewAdapter(recyclerViewData, recyclerViewInt);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Displays a toast message
    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
