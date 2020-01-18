package com.example.finintask.view.fragment;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        try{
        }
        catch (ClassCastException exp){

        }
        super.onAttach(context);
    }

}
