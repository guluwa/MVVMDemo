package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

/**
 * Created by guluwa on 2018/1/3.
 */

public interface UserDataSource {

    LiveData<UserBean> queryByUsername(String name);
}
