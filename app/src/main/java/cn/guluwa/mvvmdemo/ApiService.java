package cn.guluwa.mvvmdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface ApiService {

    @GET("/users/{username}")
    Call<UserBean> queryUserByUsername(@Path("username") String username);
}
