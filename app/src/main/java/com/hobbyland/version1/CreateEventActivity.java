package com.hobbyland.version1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.hobbyland.version1.CreateEvent.CeStepOneFragment;
import com.hobbyland.version1.CreateEvent.CeStepTwoFragment;

public class CreateEventActivity extends AppCompatActivity implements CeStepOneFragment.CeStepOneListener, CeStepTwoFragment.CeStepTwoListener {

    FragmentManager fragmentManager;
    private CeStepOneFragment stepOneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        init();
    }

    private void init() {
        fragmentManager=getSupportFragmentManager();
        stepOneFragment= new CeStepOneFragment();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_to_left, R.anim.exit_right_to_left,
                        R.anim.slide_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragment_container_create_event,stepOneFragment)
                .commit();
    }



    @Override
    public void inputCeStepOneSent(int hobbyID) {
        CeStepTwoFragment ceStepTwoFragment=CeStepTwoFragment.newInstance(hobbyID);
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_to_left, R.anim.exit_right_to_left,
                        R.anim.slide_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragment_container_create_event,ceStepTwoFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void inputCeStepTwoSent(int hobbyID) {

    }
}
