package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guluwa on 2018/1/3.
 */

public class UserRepository {

    private static final UserRepository instance = new UserRepository();
    private Context context;
    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<UserBean> getUser(String name) {
        if (Utils.isConnected(context)) {
            return remoteUserDataSource.queryByUsername(name);
        } else {
            return localUserDataSource.queryByUsername(name);
        }
    }
}
