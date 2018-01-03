package cn.guluwa.mvvmdemo;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by guluwa on 2018/1/3.
 */

public class DBHelper {

    private static final DBHelper helper = new DBHelper();
    private static final String DATABASE_NAME = "my_database";

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        return helper;
    }

    private MyDataBase myDataBase;

    public void initDataBase(Context context) {
        myDataBase =Room.databaseBuilder(context, MyDataBase.class, DATABASE_NAME).build();
    }

    public MyDataBase getMyDataBase() {
        return myDataBase;
    }
}
