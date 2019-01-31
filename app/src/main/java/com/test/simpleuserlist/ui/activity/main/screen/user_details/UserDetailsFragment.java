package com.test.simpleuserlist.ui.activity.main.screen.user_details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.test.simpleuserlist.R;
import com.test.simpleuserlist.model.User;
import com.test.simpleuserlist.ui.base.BaseFragment;
import com.test.simpleuserlist.util.DateUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsFragment extends BaseFragment {
    private static final String KEY_USER = "key_user";

    public static UserDetailsFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER, user);
        UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private CircleImageView mUserPhotoView;
    private AppCompatTextView mNameView;
    private AppCompatTextView mDobView;
    private AppCompatTextView mCellView;
    private AppCompatTextView mPhoneView;
    private AppCompatTextView mEmailView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_details, container, false);
        mUserPhotoView = rootView.findViewById(R.id.userPhotoView);
        mNameView = rootView.findViewById(R.id.nameView);
        mDobView = rootView.findViewById(R.id.dobView);
        mCellView = rootView.findViewById(R.id.cellView);
        mPhoneView = rootView.findViewById(R.id.phoneView);
        mEmailView = rootView.findViewById(R.id.emailView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent.setTitle(R.string.profile);
        Bundle arguments = getArguments();
        if (arguments != null){
            User user = arguments.getParcelable(KEY_USER);
            initUI(user);
        }
    }

    private void initUI(User user) {
        Picasso.get().load(Uri.parse(user.photoUrl)).into(mUserPhotoView);
        mNameView.setText(String.format("%s %s", user.firstName, user.lastName));
        mDobView.setText(DateUtils.format(user.birthDate));
        mCellView.setText(user.phoneNumbers.get(1));
        mPhoneView.setText(user.phoneNumbers.get(0));
        mEmailView.setText(user.email);
        
        setNumberViewListenerFor(mCellView);
        setNumberViewListenerFor(mPhoneView);
    }

    private void setNumberViewListenerFor(AppCompatTextView numberView){
        numberView.setOnClickListener(view -> {
            String number = numberView.getText().toString().trim();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(String.format("tel:%s", number)));
            startActivity(intent);
        });
    }
}
