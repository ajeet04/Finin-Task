package com.example.finintask.view;


import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.finintask.R;
import com.example.finintask.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
    public void replaceFragment(String fragmentName, Bundle bundle, boolean addToBackStack) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BaseFragment top = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (top != null) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_left, R.anim.enter_from_left, R.anim.exit_from_right);
        }
        fragmentTransaction.replace(R.id.container, Fragment.instantiate(this, fragmentName, bundle), fragmentName);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addFragment(String fragmentName, Bundle bundle, boolean addToBackStack) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BaseFragment top = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (top != null) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_left, R.anim.enter_from_left, R.anim.exit_from_right);
        }
        fragmentTransaction.add(R.id.container, Fragment.instantiate(this, fragmentName, bundle), fragmentName);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

}
