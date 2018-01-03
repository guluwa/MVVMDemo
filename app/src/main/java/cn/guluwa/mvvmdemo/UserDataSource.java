package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.view.View;

/**
 * Created by guluwa on 2018/1/3.
 */

public interface UserDataSource {

    LiveData<ViewDataBean<UserBean>> queryByUsername(String name);
}
