package com.test.simpleuserlist.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    protected Activity parent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = getActivity();
    }
}
