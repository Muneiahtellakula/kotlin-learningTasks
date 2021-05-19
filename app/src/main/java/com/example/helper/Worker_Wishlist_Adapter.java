package com.example.helper;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class Worker_Wishlist_Adapter extends RecyclerView.Adapter<Worker_Wishlist_Adapter.ViewHolder> {

    DatabaseReference databaseReference;
    private Context context;
    private List<Worker> list;
    Worker_Wishlist_Adapter(Context context, List<Worker> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_wishlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Worker worker=list.get(position);
        holder.na.setText("  "+worker.getName());
        holder.mo.setText("  "+worker.getMobile());
        holder.ag.setText("  "+worker.getAge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder  extends RecyclerView.ViewHolder{

        public TextView na;
        public TextView mo;
        public TextView ag;

        public ViewHolder(View view){
            super(view);
            na=(TextView)view.findViewById(R.id.na1);
            mo=(TextView)view.findViewById(R.id.mo1);
            ag=(TextView)view.findViewById(R.id.ge1);
        }
    }
}
