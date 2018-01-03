package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

/**
 * Created by guluwa on 2018/1/3.
 */

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserDao userDao = DBHelper.getInstance().getMyDataBase().getUserDao();

    @Override
    public MutableLiveData<Long> add(final UserBean userBean) {
        final MutableLiveData<Long> index = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return userDao.add(userBean);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                index.setValue(aLong);
            }
        }.execute();
        return index;
    }

    @Override
    public LiveData<UserBean> queryByUsername(String name) {
        return userDao.queryByUsername(name);
    }
}
