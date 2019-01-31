package com.test.simpleuserlist.ui.activity.main.screen.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.test.simpleuserlist.R;
import com.test.simpleuserlist.model.User;
import com.test.simpleuserlist.ui.activity.main.screen.user_details.UserDetailsFragment;
import com.test.simpleuserlist.ui.activity.main.screen.users.di.DaggerUsersComponent;
import com.test.simpleuserlist.ui.activity.main.screen.users.di.UsersModule;
import com.test.simpleuserlist.ui.base.BaseFragment;
import com.test.simpleuserlist.ui.list.user.UsersAdapter;

import java.util.List;

import javax.inject.Inject;

public class UsersFragment extends BaseFragment {
    private RecyclerView mUsersListView;
    private ProgressBar mLoadingView;

    private UsersAdapter mUsersAdapter;

    @Inject
    UsersViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);
        mUsersListView = rootView.findViewById(R.id.usersListView);
        mLoadingView = rootView.findViewById(R.id.loadingView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaggerUsersComponent.builder()
                .usersModule(new UsersModule(this))
                .build()
                .inject(this);
        parent.setTitle(R.string.home);
        initListView();
    }

    private void initListView() {
        LinearLayoutManager llm = new LinearLayoutManager(parent);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mUsersListView.setLayoutManager(llm);
        mUsersListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = llm.getChildCount();
                    int totalItemCount = llm.getItemCount();
                    int pastVisibleItems = llm.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        mViewModel.loadUsers().observe(UsersFragment.this, UsersFragment.this::updateList);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        List<User> users = mViewModel.getUsers();
        if (users.isEmpty()){
            mLoadingView.setVisibility(View.VISIBLE);
            mViewModel.loadUsers().observe(this, this::updateList);
        } else updateList(users);
    }

    private void updateList(List<User> users) {
        mLoadingView.setVisibility(View.GONE);
        if (mUsersAdapter == null){
            mUsersAdapter = new UsersAdapter(users, position -> {
                User user = mViewModel.getUser(position);
                parent.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, UserDetailsFragment.newInstance(user))
                        .addToBackStack(null)
                        .commit();
            });
            mUsersListView.setAdapter(mUsersAdapter);
        } else {
            mUsersAdapter.setItems(users);
        }
    }

    @Override
    public void onDestroyView() {
        mUsersAdapter = null;
        super.onDestroyView();
    }
}
