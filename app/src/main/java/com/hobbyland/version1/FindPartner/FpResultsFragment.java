package com.hobbyland.version1.FindPartner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hobbyland.version1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FpResultsFragment extends Fragment {

    public FpResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fp_results, container, false);
    }
}
