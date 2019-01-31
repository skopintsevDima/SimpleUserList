package com.test.simpleuserlist.ui.list.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.simpleuserlist.R;
import com.test.simpleuserlist.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> mItems;

    public UsersAdapter(List<User> items){
        mItems = items;
    }

    public void setItems(List<User> users) {
        mItems = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder viewHolder, int position) {
        User item = mItems.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
