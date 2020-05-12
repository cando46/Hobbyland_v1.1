package com.hobbyland.version1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hobbyland.version1.FindPartner.FpResultsFragment;
import com.hobbyland.version1.FindPartner.FpStepOneFragment;
import com.hobbyland.version1.FindPartner.FpStepThreeFragment;
import com.hobbyland.version1.FindPartner.FpStepTwoFragment;

public class FindPartnerActivity extends AppCompatActivity implements FpStepOneFragment.FragmentStepOneListener {

    FragmentManager fragmentManager;
    private FpStepOneFragment stepOneFragment;
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
                .add(R.id.fragment_container_find_partner, stepOneFragment)
                .addToBackStack(null)
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
}
