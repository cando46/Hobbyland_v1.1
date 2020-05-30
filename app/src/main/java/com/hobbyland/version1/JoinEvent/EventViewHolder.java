package com.hobbyland.version1.JoinEvent;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hobbyland.version1.R;

public class EventViewHolder extends RecyclerView.ViewHolder {
    ImageView hobbyPhoto;
    TextView title;
    TextView date;
    TextView quota;
    TextView location;
    Button join;
    Button details;
    ConstraintLayout expandable;



    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        hobbyPhoto = itemView.findViewById(R.id.imv_join_item_hobby);
        title = itemView.findViewById(R.id.tv_join_item_hobby_name);
        date = itemView.findViewById(R.id.tv_join_item_date);
        quota = itemView.findViewById(R.id.tv_join_item_quota);
        location = itemView.findViewById(R.id.tv_join_item_location);
        join = itemView.findViewById(R.id.btn_join_item_join);
        details = itemView.findViewById(R.id.btn_join_item_details);
        expandable=itemView.findViewById(R.id.cons_layout_join_expandable);

    }
}
