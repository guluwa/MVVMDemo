package cn.guluwa.mvvmdemo;

import android.app.Application;

/**
 * Created by guluwa on 2018/1/3.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
    }

    private void initDataBase() {
        DBHelper.getInstance().initDataBase(this);
        UserRepository.getInstance().init(this);
    }
}
