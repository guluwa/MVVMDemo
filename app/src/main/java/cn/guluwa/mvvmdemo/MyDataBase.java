package cn.guluwa.mvvmdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by guluwa on 2018/1/3.
 */

@Database(entities = {UserBean.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();
}
