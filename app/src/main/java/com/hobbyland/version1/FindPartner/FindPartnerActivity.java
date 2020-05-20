package com.hobbyland.version1.FindPartner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.hobbyland.version1.FindPartner.FpResultsFragment;
import com.hobbyland.version1.FindPartner.FpStepOneFragment;
import com.hobbyland.version1.FindPartner.FpStepThreeFragment;
import com.hobbyland.version1.FindPartner.FpStepTwoFragment;
import com.hobbyland.version1.R;

public class FindPartnerActivity extends AppCompatActivity implements FpStepOneFragment.FragmentStepOneListener, FpStepTwoFragment.FragmentStepTwoListener, FpStepThreeFragment.FragmentStepThreeListener {

    FragmentManager fragmentManager;
    private FpStepOneFragment stepOneFragment;
    private FpStepTwoFragment stepTwoFragment;
    private FpStepThreeFragment stepThreeFragment;
    private FpResultsFragment resultsFragment;
    private int selectedHobbyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_partner);
        init();


    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        stepOneFragment = new FpStepOneFragment();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_to_left, R.anim.exit_right_to_left,
                        R.anim.slide_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragment_container_find_partner, stepOneFragment)
                .commit();
    }

    //StepOne Fragment sent the data about selected hobby as HobbyID
    @Override
    public void inputStepOneSent(int hobbyID) {
        FpStepTwoFragment fpStepTwoFragment = FpStepTwoFragment.newInstance(hobbyID);
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_to_left, R.anim.exit_right_to_left,
                        R.anim.slide_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragment_container_find_partner, fpStepTwoFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void inputStepThreeSent(int hobbyID) {

    }

    @Override
    public void inputStepTwoSent(int yearOfExperience, int knowledgeLevel, int skillLevel, int HobbyID) {
        FpStepThreeFragment fpStepThreeFragment = FpStepThreeFragment.newInstance(yearOfExperience, knowledgeLevel, skillLevel, HobbyID);
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_to_left, R.anim.exit_right_to_left,
                        R.anim.slide_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragment_container_find_partner, fpStepThreeFragment)
                .addToBackStack(null)
                .commit();


    }
}
