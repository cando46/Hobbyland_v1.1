package com.hobbyland.version1.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hobbyland.version1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileVisitorActivity extends AppCompatActivity implements View.OnClickListener {

    String UID;
    CircleImageView userProfile;
    TextView username;
    TextView age;
    TextView gender;
    TextView status;
    RecyclerView rv;
    ImageView addFriend;
    ImageView removeFriend;
    ImageView reportUser;
    ImageView sendMessage;
    int isFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_visitor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        UID = intent.getStringExtra("UID");
        isFriend=intent.getIntExtra("Friend",0);
        initView();
        initRecyclerView();

    }

    FirebaseRecyclerOptions<HobbyItem> options;
    FirebaseRecyclerAdapter<HobbyItem, HobbyViewHolder> adapter;
    DatabaseReference hobbyRef;

    private void initRecyclerView() {
        rv = findViewById(R.id.rv_profile_visitor_hobbies);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        hobbyRef = FirebaseDatabase.getInstance().getReference("HobbyUID").child(UID).child("Hobbies");

        options = new FirebaseRecyclerOptions.Builder<HobbyItem>()
                .setQuery(hobbyRef, HobbyItem.class).build();

        adapter = new FirebaseRecyclerAdapter<HobbyItem, HobbyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull HobbyViewHolder hobbyViewHolder, int i, @NonNull HobbyItem hobbyItem) {

                /*HOBBY ID'S
                 * FOOTBALL=1
                 * BASKETBALL=2
                 * VOLLEYBALL=3
                 * TENNIS=4
                 * TREKKING=5
                 * RUNNING=6
                 * TABLE_TENNIS=7
                 * SWIMMING=8
                 * */
                // SET HOBBY NAME AND PHOTO
                switch (hobbyItem.getHobbyName()) {
                    case "Football":
                        hobbyViewHolder.hobbyName.setText("Football");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.football);
                        break;
                    case "Basketball":
                        hobbyViewHolder.hobbyName.setText("Basketball");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.basketball);
                        break;
                    case "Volleyball":
                        hobbyViewHolder.hobbyName.setText("Volleyball");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.volleyball);
                        break;
                    case "Tennis":
                        hobbyViewHolder.hobbyName.setText("Tennis");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.tennis);
                        break;
                    case "Trekking":
                        hobbyViewHolder.hobbyName.setText("Trekking");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.trekking);
                        break;
                    case "Running":
                        hobbyViewHolder.hobbyName.setText("Running");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.runing);
                        break;
                    case "TableTennis":
                        hobbyViewHolder.hobbyName.setText("Table Tennis");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.table_tennis);
                        break;
                    case "Swimming":
                        hobbyViewHolder.hobbyName.setText("Swimming");
                        hobbyViewHolder.hobby.setImageResource(R.drawable.swimming);
                        break;

                }

                //SET EXPERIENCE
                switch (hobbyItem.getExperience()) {
                    case "1":
                        hobbyViewHolder.exp.setImageResource(R.drawable.one_year);
                        break;
                    case "2":
                        hobbyViewHolder.exp.setImageResource(R.drawable.two_years);
                        break;
                    case "3":
                        hobbyViewHolder.exp.setImageResource(R.drawable.three_years);
                        break;
                    case "4":
                        hobbyViewHolder.exp.setImageResource(R.drawable.four_years);
                        break;
                    case "5":
                        hobbyViewHolder.exp.setImageResource(R.drawable.five_years);
                        break;

                }

                //SET KNOWLEDGE
                switch (hobbyItem.getKnowledge()) {
                    case "1":
                        hobbyViewHolder.knowledge.setImageResource(R.drawable.knowledge1);
                        break;
                    case "2":
                        hobbyViewHolder.knowledge.setImageResource(R.drawable.knowledge2);
                        break;
                    case "3":
                        hobbyViewHolder.knowledge.setImageResource(R.drawable.knowledge3);
                        break;
                    case "4":
                        hobbyViewHolder.knowledge.setImageResource(R.drawable.knowledge4);
                        break;
                    case "5":
                        hobbyViewHolder.knowledge.setImageResource(R.drawable.knowledge5);
                        break;

                }

                //SET SKILL LEVEL

                switch (hobbyItem.getSkillLevel()) {
                    case "1":
                        hobbyViewHolder.skill.setImageResource(R.drawable.star_one);
                        break;
                    case "2":
                        hobbyViewHolder.skill.setImageResource(R.drawable.star_two);
                        break;
                    case "3":
                        hobbyViewHolder.skill.setImageResource(R.drawable.star_three);
                        break;
                    case "4":
                        hobbyViewHolder.skill.setImageResource(R.drawable.star_four);
                        break;
                    case "5":
                        hobbyViewHolder.skill.setImageResource(R.drawable.star_five);
                        break;

                }


            }

            @NonNull
            @Override
            public HobbyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hobby_item_layout, parent, false);

                return new HobbyViewHolder(view);
            }
        };

        adapter.startListening();
        rv.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }

    DatabaseReference mRef;

    private void initView() {
        username = findViewById(R.id.tv_profile_visitor_username);
        age = findViewById(R.id.tv_profile_visitor_age);
        gender = findViewById(R.id.tv_profile_visitor_gender);
        status = findViewById(R.id.tv_profile_visitor_status);
        userProfile = findViewById(R.id.cimv_profile_visitor_photo);
        addFriend = findViewById(R.id.imv_profile_visitor_add_friend);
        removeFriend = findViewById(R.id.imv_profile_visitor_delete_friend);
        reportUser = findViewById(R.id.imv_profile_visitor_report_user);
        sendMessage = findViewById(R.id.imv_profile_visitor_send_message);
        addFriend.setOnClickListener(this);
        removeFriend.setOnClickListener(this);
        reportUser.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
        if (isFriend==0){
            removeFriend.setVisibility(View.INVISIBLE);
            addFriend.setVisibility(View.INVISIBLE);
        }
        else {
            addFriend.setVisibility(View.INVISIBLE);
            removeFriend.setVisibility(View.VISIBLE);
        }


        mRef = FirebaseDatabase.getInstance().getReference("Users").child(UID);
        PullProfileInfo();

    }

    ProgressDialog progressDialog;

    private void PullProfileInfo() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String currentUserName = dataSnapshot.child("username").getValue(String.class);
                String currentUserAge = "  Age: " + dataSnapshot.child("age").getValue(String.class);
                String currentUserGender = "Gender: " + dataSnapshot.child("gender").getValue(String.class);
                String currentUserStatus = dataSnapshot.child("status").getValue(String.class);


                switch (currentUserGender) {
                    case "Gender: Male":
                        userProfile.setImageResource(R.drawable.man);
                        break;
                    case "Gender: Female":
                        userProfile.setImageResource(R.drawable.girl1);
                        break;
                }

                status.setText(currentUserStatus);
                age.setText(currentUserAge);
                gender.setText(currentUserGender);
                username.setText(currentUserName);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_profile_visitor_add_friend:
                onClickAddFriend();
                break;
            case R.id.imv_profile_visitor_delete_friend:
                onClickDeleteFriend();
                break;
            case R.id.imv_profile_visitor_report_user:
                onClickReportUser();
                break;
            case R.id.imv_profile_visitor_send_message:
                onClickSendMessage();
                break;
        }
    }

    private void onClickSendMessage() {

    }

    String complaint;
    long maxId = 0;
    DatabaseReference compRef;

    private void onClickReportUser() {
        compRef = FirebaseDatabase.getInstance().getReference("Complaints").child(UID);
        compRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(ProfileVisitorActivity.this);
        mDialog.setTitle("Write your complaint!");

        final EditText EtStatus = new EditText(ProfileVisitorActivity.this);
        EtStatus.setInputType(InputType.TYPE_CLASS_TEXT);
        mDialog.setView(EtStatus);

        EtStatus.setPadding(50, 50, 50, 50);


        mDialog.setPositiveButton("Send Report", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                complaint = EtStatus.getText().toString();
                compRef.child(String.valueOf(maxId+1)).setValue(complaint);
                Toast.makeText(getApplicationContext(), "Your complaint has been received. ", Toast.LENGTH_SHORT).show();
            }
        });

        mDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mDialog.show();
    }

    private void onClickDeleteFriend() {

    }

    private void onClickAddFriend() {

    }
}
