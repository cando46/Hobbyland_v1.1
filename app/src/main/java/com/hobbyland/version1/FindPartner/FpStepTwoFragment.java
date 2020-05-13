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
import android.widget.Toast;

import com.hobbyland.version1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FpStepTwoFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private FragmentStepTwoListener stepTwoListener;
    Button next;
    Spinner spinnerYearOfExperience;
    Spinner spinnerKnowledgeLevel;
    Spinner spinnerSkillLevel;
    ImageView hobby;
    TextView hobbyName;
    int yearOfExp, knowledge, skill;

    public FpStepTwoFragment() {
        // Required empty public constructor
    }

    public interface FragmentStepTwoListener {

        void inputStepTwoSent(int yearOfExperience, int knowledgeLevel, int skillLevel, int HobbyID);

    }

    //ON CLICK FOR NEXT BUTTON
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_step2_next) {
            if (yearOfExp == 0 || skill == 0 || knowledge == 0) {
                Toast.makeText(getContext(), "Complete The Missing Informations!", Toast.LENGTH_SHORT).show();
            } else {
                stepTwoListener.inputStepTwoSent(yearOfExp,knowledge,skill,getArguments().getInt("HOBBY_ID"));
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fp_step_two, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        hobby = view.findViewById(R.id.imv_step2_hobby);
        hobbyName = view.findViewById(R.id.tv_step2_hobbyName);

        spinnerYearOfExperience = view.findViewById(R.id.spinner_step2_year_of_exp);
        spinnerKnowledgeLevel = view.findViewById(R.id.spinner_step2_knowledge);
        spinnerSkillLevel = view.findViewById(R.id.spinner_step2_skill_level);

        //ADAPTER FOR YEAR OF EXPERIENCE SPINNER
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.year_of_exp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYearOfExperience.setAdapter(adapter);
        spinnerYearOfExperience.setOnItemSelectedListener(this);
        //ADAPTER FOR KNOWLEDGE SPINNER
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                R.array.knowledge, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKnowledgeLevel.setAdapter(adapter2);
        spinnerKnowledgeLevel.setOnItemSelectedListener(this);
        //ADAPTER FOR SKILL LEVEL SPINNER
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this.getContext(),
                R.array.skill_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSkillLevel.setAdapter(adapter3);
        spinnerSkillLevel.setOnItemSelectedListener(this);


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
        switch (getArguments().getInt("HOBBY_ID")) {
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

        next = view.findViewById(R.id.btn_step2_next);
        next.setOnClickListener(this);
    }

    public static FpStepTwoFragment newInstance(int hobbyId) {
        FpStepTwoFragment myFragment = new FpStepTwoFragment();

        Bundle args = new Bundle();
        args.putInt("HOBBY_ID", hobbyId);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_step2_year_of_exp:
                onSelectedYearOfExp(position);
                break;
            case R.id.spinner_step2_knowledge:
                onSelectedKnowledge(position);
                break;
            case R.id.spinner_step2_skill_level:
                onSelectedSkillLevel(position);
                break;
        }

    }


    private void onSelectedYearOfExp(int position) {
        this.yearOfExp = position;

    }

    private void onSelectedKnowledge(int position) {
        this.knowledge = position;

    }

    private void onSelectedSkillLevel(int position) {
        this.skill = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentStepTwoListener){
            stepTwoListener=(FragmentStepTwoListener)context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentStepTwoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        stepTwoListener=null;
    }
}
