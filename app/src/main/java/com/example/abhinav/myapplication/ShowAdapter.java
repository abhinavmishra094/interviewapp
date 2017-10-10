package com.example.abhinav.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abhinav on 10/10/17.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewHolder> {

    List<User> users;
    public ShowAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view =
                inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
          holder.name.setText(users.get(holder.getAdapterPosition()).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView name;
         MyViewHolder(View itemView) {
            super(itemView);
             name = itemView.findViewById(R.id.name);
        }
    }
}
