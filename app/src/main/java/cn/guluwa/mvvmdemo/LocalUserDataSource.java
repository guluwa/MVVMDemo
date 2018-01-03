package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;

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
    public LiveData<ViewDataBean<UserBean>> queryByUsername(String name) {
        final MediatorLiveData<ViewDataBean<UserBean>> data = new MediatorLiveData<>();
        data.setValue(ViewDataBean.<UserBean>loading());
        data.addSource(userService.queryByUsername(name), new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                if (userBean == null) {
                    data.setValue(ViewDataBean.<UserBean>empty());
                } else {
                    data.setValue(ViewDataBean.content(userBean));
                }
            }
        });
        return data;
    }

    public LiveData<Long> addUser(UserBean userBean) {
        return userService.add(userBean);
    }
}
