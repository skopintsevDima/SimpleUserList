package com.test.simpleuserlist.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BaseFragment extends Fragment {
    protected AppCompatActivity parent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (AppCompatActivity) getActivity();
    }
}
