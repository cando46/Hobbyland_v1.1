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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hobbyland.version1.R;

public class ProfileOwnerActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvUsername;
    TextView tvAge;
    TextView tvGender;
    TextView tvStatus;
    RecyclerView rvHobby;
    Button editPhoto;
    Button addHobby;
    Button editStatus;
    DatabaseReference mRef;
    DatabaseReference hobbyRef;
    ProgressDialog progressDialog;
    String currentUserName;
    String currentUserAge;
    String currentUserStatus;
    String currentUserGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_owner);
        initViews();
        initRecyclerView();
    }


    FirebaseRecyclerOptions<HobbyItem> options;
    FirebaseRecyclerAdapter<HobbyItem, HobbyViewHolder> adapter;

    private void initRecyclerView() {
        rvHobby = findViewById(R.id.rv_profile_owner_hobbies);
        rvHobby.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvHobby.setLayoutManager(layoutManager);
        hobbyRef = FirebaseDatabase.getInstance().getReference("HobbyUID").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Hobbies");

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
        rvHobby.setAdapter(adapter);
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

    private void initViews() {
        tvUsername = findViewById(R.id.tv_profile_owner_username);
        tvAge = findViewById(R.id.tv_profile_owner_age);
        tvGender = findViewById(R.id.tv_profile_owner_gender);
        tvStatus = findViewById(R.id.tv_profile_owner_status);
        editPhoto = findViewById(R.id.btn_profile_owner_edit_photo);
        addHobby = findViewById(R.id.btn_profile_owner_add_hobby);
        editStatus = findViewById(R.id.btn_profile_owner_edit_status);
        editPhoto.setOnClickListener(this);
        addHobby.setOnClickListener(this);
        editStatus.setOnClickListener(this);

        mRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        PullProfileInfo();
    }

    private void PullProfileInfo() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUserName = dataSnapshot.child("username").getValue(String.class);
                currentUserAge = "  Age: " + dataSnapshot.child("age").getValue(String.class);
                currentUserGender = "Gender: " + dataSnapshot.child("gender").getValue(String.class);
                currentUserStatus = dataSnapshot.child("status").getValue(String.class);

                tvStatus.setText(currentUserStatus);
                tvAge.setText(currentUserAge);
                tvGender.setText(currentUserGender);
                tvUsername.setText(currentUserName);
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
            case R.id.btn_profile_owner_add_hobby:
                onClickAddHobby();
                break;
            case R.id.btn_profile_owner_edit_photo:
                onClickEditPhoto();
                break;
            case R.id.btn_profile_owner_edit_status:
                onClickEditStatus();
                break;
        }
    }


    String toBeSendStatus;

    private void onClickEditStatus() {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(ProfileOwnerActivity.this);
        mDialog.setTitle("Edit Status");

        final EditText EtStatus = new EditText(ProfileOwnerActivity.this);
        EtStatus.setInputType(InputType.TYPE_CLASS_TEXT);
        mDialog.setView(EtStatus);

        mDialog.setPositiveButton("Share", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toBeSendStatus = EtStatus.getText().toString();
                mRef.child("status").setValue(toBeSendStatus);
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

    private void onClickEditPhoto() {

    }

    private void onClickAddHobby() {
        Intent intent=new Intent(getApplicationContext(),AddHobbyActivity.class);
        startActivity(intent);

    }
}
