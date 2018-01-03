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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<String> mUserName;

    public MutableLiveData<String> getUserName() {
        if (mUserName == null) {
            mUserName = new MutableLiveData<>();
        }
        return mUserName;
    }

    private EditText etUserName;
    private TextView tvStatus, tvSearch;
    private ImageView ivUserImg;
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
        etUserName = findViewById(R.id.etUserName);
        tvStatus = findViewById(R.id.tvStatus);
        tvSearch = findViewById(R.id.tvSearch);
        ivUserImg = findViewById(R.id.ivUserImg);

        //ViewModel
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getUser().observe(this, new Observer<ViewDataBean<UserBean>>() {
            @Override
            public void onChanged(@Nullable ViewDataBean<UserBean> userBean) {
                if (userBean == null) {
                    tvStatus.setText("数据为空");
                    return;
                }
                switch (userBean.status) {
                    case Content:
                        Glide.with(MainActivity.this).asBitmap()
                                .load(userBean.data.getAvatar_url())
                                .into(ivUserImg);
                        tvStatus.setText("展示数据");
                        break;
                    case Empty:
                        tvStatus.setText("数据为空");
                        ivUserImg.setImageBitmap(null);
                        break;
                    case Error:
                        tvStatus.setText("数据错误");
                        ivUserImg.setImageBitmap(null);
                        break;
                    case Loading:
                        tvStatus.setText("正在加载");
                        ivUserImg.setImageBitmap(null);
                        break;
                }
            }
        });

        //开始查询
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setUserName(etUserName.getText().toString().trim());
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
