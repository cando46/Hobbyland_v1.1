package com.hobbyland.version1.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hobbyland.version1.R;

public class AddHobbyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spHobbyName;
    Spinner spExperience;
    Spinner spKnowledge;
    Spinner spSkillLevel;
    int hobbyName, exp, knowledge, skill;
    Button cancel;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);
        initSpinners();

        cancel = findViewById(R.id.btn_add_hobby_cancel);
        add = findViewById(R.id.btn_add_hobby_add);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    private void initSpinners() {
        spExperience = findViewById(R.id.spinner_add_hobby_experience);
        spHobbyName = findViewById(R.id.spinner_add_hobby_hobbyName);
        spKnowledge = findViewById(R.id.spinner_add_hobby_knowledge);
        spSkillLevel = findViewById(R.id.spinner_add_hobby_skill_level);


        //ADAPTER FOR HobbyName
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.add_hobby_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHobbyName.setAdapter(adapter);
        spHobbyName.setOnItemSelectedListener(this);
        //ADAPTER FOR Experience
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.add_hobby_exp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spExperience.setAdapter(adapter2);
        spExperience.setOnItemSelectedListener(this);
        //ADAPTER Knowledge
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.add_hobby_knowledge, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKnowledge.setAdapter(adapter3);
        spKnowledge.setOnItemSelectedListener(this);
        //ADAPTER Skill Level
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.add_hobby_skill, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSkillLevel.setAdapter(adapter4);
        spSkillLevel.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_add_hobby_hobbyName:
                onItemSelectedHobbyName(position);
                break;
            case R.id.spinner_add_hobby_experience:
                onItemSelectedExperience(position);
                break;
            case R.id.spinner_add_hobby_knowledge:
                onItemSelectedKnowledge(position);
                break;
            case R.id.spinner_add_hobby_skill_level:
                onItemSelectedSkill(position);
                break;

        }


    }

    private void onItemSelectedSkill(int position) {
        this.skill = position;
    }

    private void onItemSelectedKnowledge(int position) {
        this.knowledge = position;
    }

    private void onItemSelectedExperience(int position) {
        this.exp = position;
    }

    private void onItemSelectedHobbyName(int position) {
        this.hobbyName = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_hobby_cancel:
                onClickCancel();
                break;
            case R.id.btn_add_hobby_add:
                onClickAdd();
                break;
        }
    }

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("HobbyUID").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Hobbies");
    ;

    private void onClickAdd() {
        if (hobbyName == 0 || exp == 0 || knowledge == 0 || skill == 0) {
            Toast.makeText(getApplicationContext(), "Complete The Missing Informations!", Toast.LENGTH_SHORT).show();
        } else {

            switch (hobbyName){
                case 1:
                    HobbyItem hobbyItem= new HobbyItem("Football",
                        String.valueOf(exp),
                        String.valueOf(knowledge),
                        String.valueOf(skill));
                    mRef.child("Football").setValue(hobbyItem);
                    break;
                case 2:
                    HobbyItem hobbyItem2= new HobbyItem("Basketball",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("Basketball").setValue(hobbyItem2);
                    break;
                case 3:
                    HobbyItem hobbyItem3= new HobbyItem("Volleyball",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("Volleyball").setValue(hobbyItem3);
                    break;
                case 4:
                    HobbyItem hobbyItem4= new HobbyItem("Tennis",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("Tennis").setValue(hobbyItem4);
                    break;
                case 5:
                    HobbyItem hobbyItem5= new HobbyItem("Trekking",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("Trekking").setValue(hobbyItem5);
                    break;
                case 6:
                    HobbyItem hobbyItem6= new HobbyItem("Running",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("Running").setValue(hobbyItem6);
                    break;
                case 7:
                    HobbyItem hobbyItem7= new HobbyItem("TableTennis",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("TableTennis").setValue(hobbyItem7);
                    break;
                case 8:
                    HobbyItem hobbyItem8= new HobbyItem("Swimming",
                            String.valueOf(exp),
                            String.valueOf(knowledge),
                            String.valueOf(skill));
                    mRef.child("Swimming").setValue(hobbyItem8);
                    break;
            }

            Intent intent = new Intent(getApplicationContext(), ProfileOwnerActivity.class);
            startActivity(intent);
        }


    }

    private void onClickCancel() {
        Intent intent = new Intent(getApplicationContext(), ProfileOwnerActivity.class);
        startActivity(intent);
    }
}
