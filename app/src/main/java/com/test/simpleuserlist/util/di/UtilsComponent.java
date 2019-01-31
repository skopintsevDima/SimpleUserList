package com.test.simpleuserlist.util.di;

import com.test.simpleuserlist.network.di.NetworkModule;
import com.test.simpleuserlist.ui.activity.main.screen.users.UsersViewModel;

import dagger.Component;

@Component(modules = {NetworkModule.class})
@UtilsScope
public interface UtilsComponent {
    void inject(UsersViewModel usersViewModel);
}
