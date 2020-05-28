package com.hobbyland.version1.FindPartner;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hobbyland.version1.R;


public class ResultViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView age;
    TextView hobbyName;
    ImageView userProfile;
    ImageView hobbyImage;
    ImageView knowledge;
    ImageView experience;
    ImageView skillLevel;
    Button addFriend;
    Button seeProfile;
    ConstraintLayout expandable;



    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);
        username=itemView.findViewById(R.id.tv_fp_result_username);
        age=itemView.findViewById(R.id.tv_fp_result_age);
        hobbyName=itemView.findViewById(R.id.tv_fp_result_hobby_name);
        userProfile=itemView.findViewById(R.id.imv_fp_result_user_profile_photo);
        hobbyImage=itemView.findViewById(R.id.imv_fp_result_hobby_photo);
        knowledge=itemView.findViewById(R.id.imv_fp_result_knowledge);
        experience=itemView.findViewById(R.id.imv_fp_result_experience);
        skillLevel=itemView.findViewById(R.id.imv_fp_result_skill_level);
        addFriend=itemView.findViewById(R.id.btn_fp_result_add_friend);
        seeProfile=itemView.findViewById(R.id.btn_fp_result_see_profile);
        expandable=itemView.findViewById(R.id.cons_layout_fp_result_expandable);
    }
}
