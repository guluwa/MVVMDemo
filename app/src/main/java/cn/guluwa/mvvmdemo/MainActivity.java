package cn.guluwa.mvvmdemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<String> mUserName;

    public MutableLiveData<String> getUserName() {
        if (mUserName == null) {
            mUserName = new MutableLiveData<>();
        }
        return mUserName;
    }

    private TextView tvUserName, tvUserId;
    private MainViewModel mViewModel;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initFloatingButton();
        initData();
    }

    private void initData() {
        index = 0;
        tvUserName = findViewById(R.id.tvUserName);
        tvUserId = findViewById(R.id.tvUserId);
        //LiveData绑定
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvUserName.setText(s);
            }
        };
        getUserName().observe(this, observer);
        //ViewModel
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getUser("drakeet").observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                if (userBean == null) return;
                tvUserName.setText(userBean.getName());
                tvUserId.setText(String.valueOf(userBean.getId()));
            }
        });
    }

    private void initFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "change user", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                getUserName().setValue("guluwa" + (index++));
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
