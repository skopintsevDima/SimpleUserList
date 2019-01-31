package com.test.simpleuserlist.ui.list.user;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.test.simpleuserlist.R;
import com.test.simpleuserlist.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

class UserViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView mUserPhotoView;
    private AppCompatTextView mNameView;
    private AppCompatTextView mEmailView;

    UserViewHolder(@NonNull View itemView) {
        super(itemView);
        mUserPhotoView = itemView.findViewById(R.id.userPhotoView);
        mNameView = itemView.findViewById(R.id.nameView);
        mEmailView = itemView.findViewById(R.id.emailView);
    }

    void bind(User item) {
        Picasso.get().load(Uri.parse(item.photoUrl)).into(mUserPhotoView);
        mNameView.setText(String.format("%s %s", item.firstName, item.lastName));
        mEmailView.setText(item.email);
    }
}
