package com.study.chang.googlemvpstudydemo.login;

import com.study.chang.googlemvpstudydemo.BasePreseter;
import com.study.chang.googlemvpstudydemo.BaseView;

/**
 *
 * @author 2018/6/12 14:49 / changliugang
 */
public interface LoginContract {

    interface Presenter extends BasePreseter {

        void login(final String username,final String password);

    }

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void loginFail();

        void showLoginedUsername(String username);

        void showMainUI();
    }

}
