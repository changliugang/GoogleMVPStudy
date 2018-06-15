package com.study.chang.googlemvpstudydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.study.chang.googlemvpstudydemo.util.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView showToken =  findViewById(R.id.text_token);
        String token = (String) SharedPreferencesUtil.getInstance().get("token","nothing");
        showToken.setText(token);
    }
}
