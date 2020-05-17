package com.hobbyland.version1.Friends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hobbyland.version1.R;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private ArrayList<FriendItem> mFriendList;

    public static class FriendViewHolder extends RecyclerView.ViewHolder {

        public ImageView profile;
        public TextView username;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            profile=itemView.findViewById(R.id.cimv_friend_item_photo);
            username=itemView.findViewById(R.id.tv_friend_item_username);
        }
    }


    public FriendAdapter(ArrayList<FriendItem> friendList){
        mFriendList=friendList;

    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item_layout,parent,false);
        FriendViewHolder friendViewHolder= new FriendViewHolder(view);
        return friendViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        FriendItem currentItem = mFriendList.get(position);
        holder.profile.setImageResource(currentItem.getProfileImgResource());
        holder.username.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }


}
