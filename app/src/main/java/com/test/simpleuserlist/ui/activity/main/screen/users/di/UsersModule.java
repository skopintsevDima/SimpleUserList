package com.test.simpleuserlist.ui.activity.main.screen.users.di;

import android.arch.lifecycle.ViewModelProviders;

import com.test.simpleuserlist.ui.activity.main.screen.users.UsersFragment;
import com.test.simpleuserlist.ui.activity.main.screen.users.UsersViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {
    private UsersViewModel mUsersViewModel;

    public UsersModule(UsersFragment context){
        mUsersViewModel = ViewModelProviders.of(context).get(UsersViewModel.class);
    }

    @Provides
    @UsersScope
    UsersViewModel provideViewModel(){
        return mUsersViewModel;
    }
}
