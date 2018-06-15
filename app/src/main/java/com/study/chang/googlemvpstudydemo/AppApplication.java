package com.study.chang.googlemvpstudydemo;

import android.app.Application;

import com.study.chang.googlemvpstudydemo.util.SharedPreferencesUtil;

/**
 * @author 2018/6/14 14:43 / changliugang
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtil.getInstance().init(this);
    }
}
