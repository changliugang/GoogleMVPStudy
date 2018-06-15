package com.study.chang.googlemvpstudydemo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.study.chang.googlemvpstudydemo.MainActivity;
import com.study.chang.googlemvpstudydemo.R;

/**
 * @author 2018/6/13 10:28 / changliugang
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private static final String USERNAME = "username";

    // View层持有Presenter层
    private LoginContract.Presenter mPresenter;

    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private Button mLoginBtn;

    public static LoginFragment getInstance(@Nullable String username) {
        Bundle arguments = new Bundle();
        arguments.putString(USERNAME, username);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(arguments);
        return loginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mUsernameEdit = rootView.findViewById(R.id.login_username);
        mPasswordEdit = rootView.findViewById(R.id.login_password);

        mLoginBtn = rootView.findViewById(R.id.login_login_btn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                mPresenter.login(username, password);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    /**
     * 这里赋值Presenter，Presenter构造函数里面调用的
     *
     * @param presenter
     */
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    ProgressDialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());//1.创建一个ProgressDialog的实例
        progressDialog.setMessage("正在加载中，请稍等......");//3.设置显示内容
        progressDialog.setCancelable(true);//4.设置可否用back键关闭对话框
        progressDialog.show();//5.将ProgessDialog显示出来
    }

    private void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void loginFail() {
        Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginedUsername(String username) {
        mUsernameEdit.setText(username);
    }

    @Override
    public void showMainUI() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
