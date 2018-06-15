package com.study.chang.googlemvpstudydemo.login;

import android.os.Handler;

import com.study.chang.googlemvpstudydemo.entity.LoginBean;
import com.study.chang.googlemvpstudydemo.util.SharedPreferencesUtil;

/**
 * @author 2018/6/13 11:53 / changliugang
 */
public class LoginPresenter implements LoginContract.Presenter {

    /*
    Presenter通常是同时持有 View 和Model的
     */

    private final LoginContract.View mLoginView;

    private final LoginBean mLoginBean;

    private String token = "";

    /**
     * 注意初始化的时候Presenter的时候是传入了Model和View的模块，那么才可让Presenter作为内容控制者来统筹全局
     *
     * @param loginBean 这个是为了记住上次登录的用户名，其实没有Model的需求的界面，只传下面的View就行了
     * @param loginView 这里传入了View模块，在业务变动的时候，调用相应的方法来改变UI
     */
    public LoginPresenter(LoginBean loginBean, LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginBean = loginBean;
        // 初始化，这里就完成了View对Presenter的绑定，可以在View模块（Fragment）中调用Presenter的函数，来完成业务
        // 比如例子中的登录操作
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        // 将上次登录的用户名传给View模块
        if (mLoginBean != null) {
            mLoginView.showLoginedUsername(mLoginBean.getUsername());
        }
    }

    @Override
    public void login(final String username, final String password) {
        mLoginView.showLoading();

        // 假定输入username为fail为登录失败，测试一下登录失败的情况
        if (username.equals("fail")) {
            mLoginView.loginFail();
            mLoginView.hideLoading();
            return;
        }

        // 模拟了网络请求，为了看下loading效果，延迟了3秒
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                token = username + "_" + password;
                saveToken(token);
                saveLoginedInfo(username);
                // 请求成功loading view消失，跳转到主界面
                mLoginView.hideLoading();
                mLoginView.showMainUI();
            }
        }, 3000);//3秒后执行Runnable中的run方法

    }


    private void saveToken(String token) {
        // sp保存token
        SharedPreferencesUtil.getInstance().put("token", token);

    }

    private void saveLoginedInfo(String username) {
        SharedPreferencesUtil.getInstance().put("username", username);
    }


}
