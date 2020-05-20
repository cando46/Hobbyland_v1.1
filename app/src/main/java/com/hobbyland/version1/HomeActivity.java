package com.hobbyland.version1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class HomeActivity extends AppCompatActivity {

    ChipNavigationBar navigationBar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        navigationBar=findViewById(R.id.chip_nav);
        if(savedInstanceState==null){
            navigationBar.setItemSelected(R.id.bottom_home,true);
            fragmentManager=getSupportFragmentManager();
            HomeFragment homeFragment= new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,homeFragment).commit();
        }
        navigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener(){

            @Override
            public void onItemSelected(int id){
                Fragment fragment = null;
                switch (id){
                    case R.id.bottom_home:
                        fragment= new HomeFragment();
                        break;
                        case R.id.bottom_my_events:
                        fragment= new MyEventsFragment();
                        break;
                        case R.id.Messages:
                        fragment= new MessagesFragment();
                        break;
                }
                if(fragment!=null){
                    fragmentManager= getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
            }
        });
    }

}
