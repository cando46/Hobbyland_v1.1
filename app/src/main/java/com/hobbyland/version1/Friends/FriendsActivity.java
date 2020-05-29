package com.hobbyland.version1.Friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hobbyland.version1.Profile.ProfileVisitorActivity;
import com.hobbyland.version1.R;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerOptions<FriendItem> options;
    FirebaseRecyclerAdapter<FriendItem,FriendViewHolder> adapter;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initRecyclerview();
    }

    private void initRecyclerview() {
        recyclerView =findViewById(R.id.rv_friends_friend_list);
        mRef= FirebaseDatabase.getInstance().getReference("Friends").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        options=new FirebaseRecyclerOptions.Builder<FriendItem>().setQuery(mRef,FriendItem.class).build();

        adapter=new FirebaseRecyclerAdapter<FriendItem, FriendViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FriendViewHolder friendViewHolder, int i, @NonNull final FriendItem friendItem) {
                    friendViewHolder.username.setText(friendItem.getUsername());
                    friendViewHolder.status.setText(friendItem.getStatus());

                    friendViewHolder.username.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onClickUser(friendItem.getUID());
                        }
                    });

                    friendViewHolder.profile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onClickUser(friendItem.getUID());
                        }
                    });
                    friendViewHolder.status.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onClickUser(friendItem.getUID());
                        }
                    });

                String cUID = friendItem.getUID();
                DatabaseReference genderRef= FirebaseDatabase.getInstance().getReference("Users").child(cUID);
                genderRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String gender=dataSnapshot.child("gender").getValue(String.class);
                        switch (gender){
                            case "Male":
                                friendViewHolder.profile.setImageResource(R.drawable.man);
                                break;
                            case"Female":
                                friendViewHolder.profile.setImageResource(R.drawable.girl1);
                                break;
                            default:
                                friendViewHolder.profile.setImageResource(R.drawable.girl2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @NonNull
            @Override
            public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item_layout,parent,false);
                return new FriendViewHolder(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }

    private void onClickUser(String uid) {
        Intent intent = new Intent(getApplicationContext(), ProfileVisitorActivity.class);
        intent.putExtra("UID",uid);
        intent.putExtra("Friend",1);
        startActivity(intent);
    }
}
