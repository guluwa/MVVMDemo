package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.view.View;

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

    public LiveData<ViewDataBean<UserBean>> getUser(String name) {
        if (Utils.isConnected(context)) {
            return getUserFromRemote(name);
        } else {
            return getUserFromLocal(name);
        }
    }

    private LiveData<ViewDataBean<UserBean>> getUserFromRemote(String name) {
        return getUserFromDataSource(remoteUserDataSource, name);
    }

    private LiveData<ViewDataBean<UserBean>> getUserFromLocal(String name) {
        return getUserFromDataSource(localUserDataSource, name);
    }

    private LiveData<ViewDataBean<UserBean>> getUserFromDataSource(UserDataSource userDataSource, String name) {
        final MutableLiveData<ViewDataBean<UserBean>> data = new MutableLiveData<>();
        data.setValue(ViewDataBean.<UserBean>loading());
        userDataSource.queryByUsername(name, new UserDataSource.Result<UserBean>() {
            @Override
            public void onSuccess(UserBean user) {
                if (user == null) {
                    data.setValue(ViewDataBean.<UserBean>empty());
                } else {
                    data.setValue(ViewDataBean.content(user));
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                data.setValue(ViewDataBean.<UserBean>error(throwable));
            }
        });
        return data;
    }
}
