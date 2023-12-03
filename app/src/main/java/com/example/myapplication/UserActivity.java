package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragment.FavorFragment;
import com.example.myapplication.fragment.HomePageFragment;
import com.example.myapplication.fragment.UserCenterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {
    private HomePageFragment homePageFragment;
    private FavorFragment favorFragment;
    private UserCenterFragment userCenterFragment;
    private BottomNavigationView myBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        myBottomNavigationView = findViewById(R.id.user_bottomNavigationView);
        myBottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.homepage) {
                        selectedFragment(0);
                    } else if (item.getItemId() == R.id.favor) {
                        selectedFragment(1);
                    } else if (item.getItemId() == R.id.usercenter) {
                        selectedFragment(2);
                    }
                    return true;
                }
            });
        selectedFragment(0);
    }

    private void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if (position == 0) {
            if (homePageFragment == null) {
                homePageFragment = new HomePageFragment();
                fragmentTransaction.add(R.id.content,
                    homePageFragment);
            } else {
                fragmentTransaction.show(homePageFragment);
            }
        } else if (position == 1) {
            if (favorFragment == null) {
                favorFragment = new FavorFragment();
                fragmentTransaction.add(R.id.content,
                    favorFragment);
            } else {
                fragmentTransaction.show(favorFragment);
            }
        } else if (position == 2) {
            if (userCenterFragment == null) {
                userCenterFragment = new UserCenterFragment();
                fragmentTransaction.add(R.id.content,
                    userCenterFragment);
            } else {
                fragmentTransaction.show(userCenterFragment);
            }
        }
        fragmentTransaction.commit();
    }

    //隐藏非空布局
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homePageFragment != null) {
            fragmentTransaction.hide(homePageFragment);
        }
        if (favorFragment != null) {
            fragmentTransaction.hide(favorFragment);
        }
        if (userCenterFragment != null) {
            fragmentTransaction.hide(userCenterFragment);
        }
    }
}
