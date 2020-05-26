package com.hobbyland.version1.Profile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hobbyland.version1.R;

public class HobbyViewHolder extends RecyclerView.ViewHolder {

    TextView hobbyName;
    ImageView hobby;
    ImageView exp;
    ImageView knowledge;
    ImageView skill;

    public HobbyViewHolder(@NonNull View itemView) {
        super(itemView);
        hobby=itemView.findViewById(R.id.imv_hobby_item_hobby);
        exp=itemView.findViewById(R.id.imv_hobby_item_experience);
        knowledge=itemView.findViewById(R.id.imv_hobby_item_knowledge);
        skill=itemView.findViewById(R.id.imv_hobby_item_skill_level);
        hobbyName=itemView.findViewById(R.id.tv_hobby_item_name);
    }
}
