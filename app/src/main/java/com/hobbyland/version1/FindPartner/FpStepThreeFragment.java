package com.hobbyland.version1.FindPartner;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hobbyland.version1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FpStepThreeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageView hobby;
    ImageView experience;
    ImageView knowledge;
    ImageView skill;
    TextView hobbyName;
    Spinner age;
    Spinner location;
    Button findPartner;

    private FragmentStepThreeListener fragmentStepThreeListener;

    public FpStepThreeFragment() {
        // Required empty public constructor
    }

    public static FpStepThreeFragment newInstance(int yearOfExperience, int knowledgeLevel, int skillLevel, int hobbyID) {

        FpStepThreeFragment myFragment = new FpStepThreeFragment();

        Bundle args = new Bundle();
        args.putInt("HOBBY_ID", hobbyID);
        args.putInt("YEAR_OF_EXP", yearOfExperience);
        args.putInt("KNOWLEDGE_LEVEL", knowledgeLevel);
        args.putInt("SKILL_LEVEL", skillLevel);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner_step3_age_range:
                onItemSelectedAgeRange(position);
                break;
            case R.id.spinner_step3_location_range:
                onItemSelectedLocationRange(position);
                break;
        }
    }

    private void onItemSelectedLocationRange(int position) {

    }

    private void onItemSelectedAgeRange(int position) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface FragmentStepThreeListener {

        void inputStepThreeSent(int hobbyID);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fp_step_three, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        hobby = view.findViewById(R.id.imv_step3_hobby);
        experience = view.findViewById(R.id.imv_step3_experience);
        knowledge = view.findViewById(R.id.imv_step3_knowledge);
        skill = view.findViewById(R.id.imv_step3_skill_level);
        hobbyName = view.findViewById(R.id.tv_step3_hobby_name);
        age = view.findViewById(R.id.spinner_step3_age_range);
        location = view.findViewById(R.id.spinner_step3_location_range);
        findPartner = view.findViewById(R.id.btn_step3_find_partner);

        findPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initHobbyImageAndName(getArguments().getInt("HOBBY_ID"));
        initExperienceImage(getArguments().getInt("YEAR_OF_EXP"));
        initKnowledgeImage(getArguments().getInt("KNOWLEDGE_LEVEL"));
        initSkillImage(getArguments().getInt("SKILL_LEVEL"));

        //ADAPTER FOR AGE RANGE SPINNER
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.age_ranges, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter);
        age.setOnItemSelectedListener(this);
        //ADAPTER FOR KNOWLEDGE SPINNER
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                R.array.location_range, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter2);
        location.setOnItemSelectedListener(this);


    }

    private void initSkillImage(int skill_level) {
        switch (skill_level) {
            case 1:
                skill.setImageResource(R.drawable.star_one);
                break;
            case 2:
                skill.setImageResource(R.drawable.star_two);
                break;
            case 3:
                skill.setImageResource(R.drawable.star_three);
                break;
            case 4:
                skill.setImageResource(R.drawable.star_four);
                break;
            case 5:
                skill.setImageResource(R.drawable.star_five);
                break;
        }


    }

    private void initKnowledgeImage(int knowledge_level) {
        switch (knowledge_level){

            case 1:
                knowledge.setImageResource(R.drawable.knowledge1);
                break;
            case 2:
                knowledge.setImageResource(R.drawable.knowledge2);
                break;
            case 3:
                knowledge.setImageResource(R.drawable.knowledge3);
                break;
            case 4:
                knowledge.setImageResource(R.drawable.knowledge4);
                break;
            case 5:
                knowledge.setImageResource(R.drawable.knowledge5);
                break;

        }

    }

    private void initExperienceImage(int year_of_exp) {

        switch (year_of_exp){

            case 1:
                experience.setImageResource(R.drawable.one_year);
                break;
            case 2:
                experience.setImageResource(R.drawable.two_years);
                break;
            case 3:
                experience.setImageResource(R.drawable.three_years);
                break;
            case 4:
                experience.setImageResource(R.drawable.four_years);
                break;
            case 5:
                experience.setImageResource(R.drawable.five_years);
                break;

        }

    }

    private void initHobbyImageAndName(int hobby_id) {

        switch (hobby_id) {
            case 1:
                hobbyName.setText("Football");
                hobby.setImageResource(R.drawable.football);
                break;
            case 2:
                hobbyName.setText("Basketball");
                hobby.setImageResource(R.drawable.basketball);
                break;
            case 3:
                hobbyName.setText("Voleyball");
                hobby.setImageResource(R.drawable.volleyball);
                break;
            case 4:
                hobbyName.setText("Tennis");
                hobby.setImageResource(R.drawable.tennis);
                break;
            case 5:
                hobbyName.setText("Trekking");
                hobby.setImageResource(R.drawable.trekking);
                break;
            case 6:
                hobbyName.setText("Running");
                hobby.setImageResource(R.drawable.runing);
                break;
            case 7:
                hobbyName.setText("Table Tennis");
                hobby.setImageResource(R.drawable.table_tennis);
                break;
            case 8:
                hobbyName.setText("Swimming");
                hobby.setImageResource(R.drawable.swimming);
                break;
            default:
                hobbyName.setText("No Hobby Selected");
                break;
        }

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentStepThreeListener) {
            fragmentStepThreeListener = (FragmentStepThreeListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement FragmentStepTwoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentStepThreeListener = null;
    }
}
