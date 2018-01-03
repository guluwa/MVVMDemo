package cn.guluwa.mvvmdemo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/3.
 */

public class RetrofitFactory {

    private static OkHttpClient client;

    private static Retrofit retrofit;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(9, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return retrofit;
    }
}
