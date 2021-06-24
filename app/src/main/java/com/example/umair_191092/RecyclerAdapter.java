package com.example.umair_191092;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {
    List<Model>sd;
    Context context;

    public RecyclerAdapter(List<Model> sd, Context context) {
        this.sd = sd;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.Viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerAdapter.Viewholder holder, int position) {
        holder.text0.setText(sd.get(position).getRecord());
    }

    @Override
    public int getItemCount() {
        return sd.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView text0;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            text0 = itemView.findViewById(R.id.text1);
        }
    }
}
