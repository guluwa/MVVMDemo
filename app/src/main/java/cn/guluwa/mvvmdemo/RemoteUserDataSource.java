package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guluwa on 2018/1/3.
 */

public class RemoteUserDataSource implements UserDataSource {

    private static final RemoteUserDataSource instance = new RemoteUserDataSource();

    private RemoteUserDataSource() {
    }

    public static RemoteUserDataSource getInstance() {
        return instance;
    }

    @Override
    public LiveData<ViewDataBean<UserBean>> queryByUsername(String name) {
        final MutableLiveData<ViewDataBean<UserBean>> user = new MutableLiveData<>();
        RetrofitFactory.getInstance().create(ApiService.class)
                .queryUserByUsername(name)
                .enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        if (null == response.body()) {
                            user.setValue(ViewDataBean.<UserBean>empty());
                            return;
                        }
                        user.setValue(ViewDataBean.content(response.body()));
                        // update cache
                        LocalUserDataSource.getInstance().addUser(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserBean> call, Throwable t) {
                        t.printStackTrace();
                        user.setValue(ViewDataBean.<UserBean>error(t));
                    }
                });
        return user;
    }
}
