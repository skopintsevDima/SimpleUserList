package com.test.simpleuserlist.ui.activity.main.screen.users;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.test.simpleuserlist.app.App;
import com.test.simpleuserlist.model.Gender;
import com.test.simpleuserlist.model.User;
import com.test.simpleuserlist.network.RandomUserService;
import com.test.simpleuserlist.util.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsersViewModel extends ViewModel {
    @Inject
    RandomUserService mRandomUserService;

    private boolean loading = false;
    private List<User> mUsers = new ArrayList<>();

    public UsersViewModel(){
        App.getUtilsComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    LiveData<List<User>> loadUsers() {
        MutableLiveData<List<User>> liveUsers = new MutableLiveData<>();
        if (!loading){
            loading = true;
            mRandomUserService.loadUsers(20)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .switchIfEmpty(Observable.just(""))
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) { }

                        @Override
                        public void onNext(String resultJsonStr) {
                            mUsers.addAll(parseUsers(resultJsonStr));
                            liveUsers.setValue(mUsers);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            loading = false;
                        }
                    });
        } else liveUsers.setValue(mUsers);
        return liveUsers;
    }

    private List<User> parseUsers(String resultJsonStr) {
        List<User> parsedUsers = new ArrayList<>();
        try {
            JSONObject resultJson = new JSONObject(resultJsonStr);
            JSONArray usersJsonArray = resultJson.getJSONArray("results");
            for (int i = 0; i < usersJsonArray.length(); i++){
                JSONObject userJson = usersJsonArray.getJSONObject(i);
                String firstName = userJson.getJSONObject("name").getString("first");
                String lastName = userJson.getJSONObject("name").getString("last");
                String email = userJson.getString("email");
                String photoUrl = userJson.getJSONObject("picture").getString("medium");
                String dobStr = userJson.getJSONObject("dob").getString("date");
                Long birthDate = DateUtils.parse(dobStr).getTime();
                List<String> phoneNumbers = new ArrayList<>();
                String phone = userJson.getString("phone");
                String cell = userJson.getString("cell");
                phoneNumbers.add(phone);
                phoneNumbers.add(cell);
                Gender gender = userJson.getString("gender").equals("male") ? Gender.MALE : Gender.FEMALE;

                User user = new User(firstName, lastName, email, photoUrl, birthDate, phoneNumbers, gender);
                parsedUsers.add(user);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return parsedUsers;
    }
}
