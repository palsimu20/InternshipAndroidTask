package com.example.instershipandroidtask.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instershipandroidtask.activitites.Activity_2;
import com.example.instershipandroidtask.R;
import com.example.instershipandroidtask.modelclass.Data;

import java.io.Serializable;
import java.util.List;

public class DataAdapterClass extends RecyclerView.Adapter<DataAdapterClass.ViewHolder> {
    List<Data> dataLists;
    Context context;

    public DataAdapterClass(List<Data> dataListsLists, Context context) {
        this.dataLists = dataListsLists;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.data,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Data dataList=dataLists.get(position);
        holder.tvname.setText(dataList.getName());
        holder.tvemail.setText(dataList.getEmail());
        holder.tvid.setText(dataList.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Activity_2.class);
                Bundle bundle=new Bundle();
                    bundle.putSerializable("data",  dataList);
                   intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvname,tvemail,tvid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.name);
            tvemail=itemView.findViewById(R.id.email);
            tvid=itemView.findViewById(R.id.id);
        }
    }
}
