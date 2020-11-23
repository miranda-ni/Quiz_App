package kg.geektech.quiz_app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kg.geektech.quiz_app.ui.quiz.QuizQuestionFragment;
import kg.geektech.quiz_app.utils.MainPagerAdapter;
import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    ActivityMainBinding binding;
    TextView txtV;
    ViewPager vp;
    BottomNavigationView botNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance();
        }

        vp = binding.viewPager;
        txtV = binding.textViewForGuide;
        botNavView = binding.navView;
        attachVPtoBNV();

    }

    public void attachVPtoBNV() {

        vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        vp.setOffscreenPageLimit(2);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            class MapFragment {
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                txtV.setVisibility(View.VISIBLE);
                switch (item.getItemId()) {

                    case R.id.navigation_main:

                        // if (MainFragment.newInstance()!=null)
                        vp.setCurrentItem(0, false);
                        txtV.setText("Quiz");
                        break;
                    case R.id.navigation_history:
                        vp.setCurrentItem(1, false);
                        txtV.setText("History");

                        break;
                    case R.id.navigation_settings:
                        vp.setCurrentItem(2, false);
                        txtV.setText("Settings");


                }
                return true;
            }
        });
    }

    public BottomNavigationView getBotNavView() {
        return botNavView;
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            botNavView.setVisibility(View.VISIBLE);
            getSupportFragmentManager().popBackStack();
        }
    }
}









