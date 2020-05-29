package com.hobbyland.version1.Friends;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hobbyland.version1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView status;
    CircleImageView profile;

    public FriendViewHolder(@NonNull View itemView) {
        super(itemView);
        username=itemView.findViewById(R.id.tv_friend_item_username);
        status=itemView.findViewById(R.id.tv_friend_item_status);
        profile=itemView.findViewById(R.id.cimv_friend_item_photo);
    }
}
