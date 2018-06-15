package com.study.chang.googlemvpstudydemo.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.study.chang.googlemvpstudydemo.R;
import com.study.chang.googlemvpstudydemo.entity.LoginBean;
import com.study.chang.googlemvpstudydemo.util.ActivityUtils;
import com.study.chang.googlemvpstudydemo.util.SharedPreferencesUtil;

/**
 * src目录的代码组织方式是按照功能来组织的，包中又分为Activity、Fragment、Contract、Presenter四种类文件
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);


        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frag_container);

        if (loginFragment == null) {
            loginFragment = LoginFragment.getInstance(getLoginBeanFromSP().getUsername());

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.frag_container);
        }

        // Activity做了最重要的一件事，创建了Presenter，并以关联了Presenter和View（Fragment）
        // 这里面传入的LoginBean是保存在SP中的登录账号，可以为空
        new LoginPresenter(getLoginBeanFromSP(), loginFragment);

    }

    private LoginBean getLoginBeanFromSP() {
        LoginBean loginBean = new LoginBean();
        String username = (String) SharedPreferencesUtil.getInstance().get("username","");
        loginBean.setUsername(username);
        return loginBean;
    }
}
