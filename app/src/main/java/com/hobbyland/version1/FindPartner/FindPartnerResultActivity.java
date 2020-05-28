package com.hobbyland.version1.FindPartner;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hobbyland.version1.HelperClasses.ResultHelperClass;
import com.hobbyland.version1.Profile.ProfileVisitorActivity;
import com.hobbyland.version1.R;

public class FindPartnerResultActivity extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference mRef;
    FirebaseRecyclerOptions<ResultItem> options;
    FirebaseRecyclerAdapter<ResultItem, ResultViewHolder> adapter;
    TextView noResult;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_partner_result);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        int hobbyID = intent.getIntExtra("HOBBY_ID", 1);
        noResult=findViewById(R.id.tv_results_no_matches);
        initRecyclerView(hobbyID);
    }



    private void initRecyclerView(int hobbyId) {
        rv = findViewById(R.id.rv_find_partner_results_list);
        switch (hobbyId) {
            case 1:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Football");
                break;
            case 2:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Basketball");
                break;
            case 3:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Volleyball");
                break;
            case 4:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Tennis");
                break;
            case 5:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Trekking");
                break;
            case 6:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Running");
                break;
            case 7:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("TableTennis");
                break;
            case 8:
                mRef = FirebaseDatabase.getInstance().getReference("Hobby").child("Swimming");
                break;
        }

        options = new FirebaseRecyclerOptions.Builder<ResultItem>().setQuery(mRef, ResultItem.class).build();

        adapter = new FirebaseRecyclerAdapter<ResultItem, ResultViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ResultViewHolder resultViewHolder, final int i, @NonNull final ResultItem resultItem) {

                noResult.setVisibility(View.INVISIBLE);
                boolean isExpanded = resultItem.isExpanded();
                resultViewHolder.expandable.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

                resultViewHolder.username.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultItem.setExpanded(!resultItem.isExpanded());
                        notifyItemChanged(i);
                    }
                });
                resultViewHolder.addFriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                resultViewHolder.seeProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ProfileVisitorActivity.class);
                        intent.putExtra("UID",resultItem.getUID());
                        startActivity(intent);

                    }
                });

                String UID = resultItem.getUID();
                DatabaseReference genderRef= FirebaseDatabase.getInstance().getReference("Users").child(UID);
                genderRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String gender=dataSnapshot.child("gender").getValue(String.class);
                        switch (gender){
                            case "Male":
                                resultViewHolder.userProfile.setImageResource(R.drawable.man);
                                break;
                            case"Female":
                                resultViewHolder.userProfile.setImageResource(R.drawable.girl1);
                                break;
                            default:
                               resultViewHolder.userProfile.setImageResource(R.drawable.girl2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //Set Username User Profile User Age
                resultViewHolder.username.setText(resultItem.username);
                resultViewHolder.age.setText("Age: " + resultItem.age);

                //hobbyname and image
                switch (resultItem.getHobbyName()) {
                    case "Football":
                        resultViewHolder.hobbyName.setText("Football");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.football);
                        break;
                    case "Basketball":
                        resultViewHolder.hobbyName.setText("Basketball");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.basketball);
                        break;
                    case "Volleyball":
                        resultViewHolder.hobbyName.setText("Volleyball");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.volleyball);
                        break;
                    case "Tennis":
                        resultViewHolder.hobbyName.setText("Tennis");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.tennis);
                        break;
                    case "Trekking":
                        resultViewHolder.hobbyName.setText("Trekking");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.trekking);
                        break;
                    case "Running":
                        resultViewHolder.hobbyName.setText("Running");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.runing);
                        break;
                    case "TableTennis":
                        resultViewHolder.hobbyName.setText("Table Tennis");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.table_tennis);
                        break;
                    case "Swimming":
                        resultViewHolder.hobbyName.setText("Swimming");
                        resultViewHolder.hobbyImage.setImageResource(R.drawable.swimming);
                        break;

                }
                //Experience
                switch (resultItem.getExperience()) {
                    case "1":
                        resultViewHolder.experience.setImageResource(R.drawable.one_year);
                        break;
                    case "2":
                        resultViewHolder.experience.setImageResource(R.drawable.two_years);
                        break;
                    case "3":
                        resultViewHolder.experience.setImageResource(R.drawable.three_years);
                        break;
                    case "4":
                        resultViewHolder.experience.setImageResource(R.drawable.four_years);
                        break;
                    case "5":
                        resultViewHolder.experience.setImageResource(R.drawable.five_years);
                        break;

                }

                //Knowledge

                switch (resultItem.getKnowledge()) {
                    case "1":
                        resultViewHolder.knowledge.setImageResource(R.drawable.knowledge1);
                        break;
                    case "2":
                        resultViewHolder.knowledge.setImageResource(R.drawable.knowledge2);
                        break;
                    case "3":
                        resultViewHolder.knowledge.setImageResource(R.drawable.knowledge3);
                        break;
                    case "4":
                        resultViewHolder.knowledge.setImageResource(R.drawable.knowledge4);
                        break;
                    case "5":
                        resultViewHolder.knowledge.setImageResource(R.drawable.knowledge5);
                        break;

                }

                //SkillLevel
                switch (resultItem.getSkill()) {
                    case "1":
                        resultViewHolder.skillLevel.setImageResource(R.drawable.star_one);
                        break;
                    case "2":
                        resultViewHolder.skillLevel.setImageResource(R.drawable.star_two);
                        break;
                    case "3":
                        resultViewHolder.skillLevel.setImageResource(R.drawable.star_three);
                        break;
                    case "4":
                        resultViewHolder.skillLevel.setImageResource(R.drawable.star_four);
                        break;
                    case "5":
                        resultViewHolder.skillLevel.setImageResource(R.drawable.star_five);
                        break;

                }


            }

            @NonNull
            @Override
            public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_user_hobby_item, parent, false);
                return new ResultViewHolder(view);
            }


        };
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
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
}
