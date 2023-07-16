package com.example.finalexam_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context ctx;
    Vector<Data> data;
    LinearLayout linearLayout;

    public MainAdapter(Context context, Vector<Data> data){
        this.ctx = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        Data data1 = data.get(position);

        holder.userId.setText(String.valueOf(data1.userId));
        holder.id.setText(String.valueOf(data1.id));
        holder.title.setText(data1.title);
        holder.body.setText(data1.body);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, userId, body, title;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.data_item);
            userId = itemView.findViewById(R.id.data_uId);
            id = itemView.findViewById(R.id.data_id);
            body = itemView.findViewById(R.id.data_body);
            title = itemView.findViewById(R.id.data_title);
        }
    }
}
