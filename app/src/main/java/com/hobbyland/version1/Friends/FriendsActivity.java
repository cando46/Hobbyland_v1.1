package com.hobbyland.version1.Friends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.hobbyland.version1.Friends.FriendAdapter;
import com.hobbyland.version1.Friends.FriendItem;
import com.hobbyland.version1.R;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init() {
        ArrayList<FriendItem> friendList=new ArrayList<>();
        friendList.add(new FriendItem(R.drawable.boy1,"Test User2"));
        friendList.add(new FriendItem(R.drawable.girl1,"Test User3"));
        friendList.add(new FriendItem(R.drawable.girl2,"Test User4"));
        friendList.add(new FriendItem(R.drawable.girl3,"Test User5"));

        recyclerView =findViewById(R.id.rv_friends_friend_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        adapter=new FriendAdapter(friendList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
