package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

/**
 * Created by guluwa on 2018/1/3.
 */

public class LocalUserDataSource implements UserDataSource {

    private static final LocalUserDataSource instance = new LocalUserDataSource();

    private LocalUserDataSource() {
    }

    public static LocalUserDataSource getInstance() {
        return instance;
    }

    private UserService userService = UserServiceImpl.getInstance();


    @Override
    public LiveData<UserBean> queryByUsername(String name) {
        return userService.queryByUsername(name);
    }

    public LiveData<Long> addUser(UserBean userBean) {
        return userService.add(userBean);
    }
}
