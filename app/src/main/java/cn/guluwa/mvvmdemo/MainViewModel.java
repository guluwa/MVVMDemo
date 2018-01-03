package cn.guluwa.mvvmdemo;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

/**
 * Created by guluwa on 2018/1/3.
 */

public class MainViewModel extends ViewModel {

    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<String> mUserName;
    private LiveData<UserBean> mUser;

    public LiveData<UserBean> getUser() {
        if (mUser == null) {
            mUserName = new MutableLiveData<>();
            mUser = Transformations.switchMap(mUserName, new Function<String, LiveData<UserBean>>() {
                @Override
                public LiveData<UserBean> apply(String input) {
                    return userRepository.getUser(input);
                }
            });

        }
        return mUser;
    }

    public void setUserName(String name) {
        mUserName.setValue(name);
    }
}
