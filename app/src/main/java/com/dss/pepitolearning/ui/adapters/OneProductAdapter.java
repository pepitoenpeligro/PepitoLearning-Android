package com.dss.pepitolearning.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dss.pepitolearning.R;
import com.dss.pepitolearning.models.PlayList;

import java.util.List;

public class OneProductAdapter extends RecyclerView.Adapter<OneProductAdapter.CourseViewHolder> {

    Context context;
    List<PlayList> playLists;

    public OneProductAdapter(Context context, List<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_product_list_row_items, parent, false);

        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        int i=position+1;
        holder.contentNumber.setText("0"+i);
        holder.contentTime.setText(playLists.get(position).getTime());
        holder.contentName.setText(playLists.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }


    public static class CourseViewHolder extends RecyclerView.ViewHolder{


        TextView contentNumber, contentTime, contentName;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            contentName = itemView.findViewById(R.id.content_title);
            contentTime = itemView.findViewById(R.id.content_time);
            contentNumber = itemView.findViewById(R.id.content_number);

        }
    }

}
