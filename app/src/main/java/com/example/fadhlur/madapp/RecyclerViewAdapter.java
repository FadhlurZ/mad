package com.example.fadhlur.madapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<String> mDataset;

    // Contructor
    public RecyclerViewAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent ,false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mFullName.setText(mDataset.get(position));

        holder.mFullName.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {

             }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mFullName;

        public ViewHolder(View a) {
            super(a);

            mFullName = (TextView) a.findViewById(R.id.full_name);
        }
    }
}
