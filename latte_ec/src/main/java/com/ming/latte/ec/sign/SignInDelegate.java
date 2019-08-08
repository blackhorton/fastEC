package com.ming.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.ming.latte.delegates.LatteDelegate;
import com.ming.latte.ec.R;
import com.ming.latte.ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Hortons
 * on 2019/8/4
 */


public class SignInDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_sig_in_wechat)
    IconTextView iconSigInWechat;
    Unbinder unbinder;

    @OnClick(R2.id.btn_sign_in)
    public void onClickBtnSignIn() {
        if (checkForm()) {
            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.icon_sig_in_wechat)
    public void onClickWeChat() {

    }

    @OnClick(R2.id.tv_link_sign_up)
    public void onClick() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = editSignInEmail.getText().toString();
        final String password = editSignInPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请输入至少6位数密码");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
