package com.test.simpleuserlist.ui.activity.main.screen.users.di;

import com.test.simpleuserlist.ui.activity.main.screen.users.UsersFragment;

import dagger.Component;

@Component(modules = UsersModule.class)
@UsersScope
public interface UsersComponent {
    void inject(UsersFragment usersFragment);
}
