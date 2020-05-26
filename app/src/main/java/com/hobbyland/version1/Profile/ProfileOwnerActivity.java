package com.hobbyland.version1.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    private void initViews() {
        tvUsername = findViewById(R.id.tv_profile_owner_username);
        tvAge = findViewById(R.id.tv_profile_owner_age);
        tvGender = findViewById(R.id.tv_profile_owner_gender);
        tvStatus = findViewById(R.id.tv_profile_owner_status);
        rvHobby = findViewById(R.id.rv_profile_owner_hobbies);
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
                currentUserAge = "  Age: "+dataSnapshot.child("age").getValue(String.class);
                currentUserGender = "Gender: "+dataSnapshot.child("gender").getValue(String.class);
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
                toBeSendStatus= EtStatus.getText().toString();
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

    }
}
