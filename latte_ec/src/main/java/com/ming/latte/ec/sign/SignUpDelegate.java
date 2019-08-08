package com.ming.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ming.latte.delegates.LatteDelegate;
import com.ming.latte.ec.R;
import com.ming.latte.ec.R2;
import com.ming.latte.net.RestClient;
import com.ming.latte.net.callback.ISuccess;
import com.ming.latte.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Hortons
 * on 2019/8/3
 */


public class SignUpDelegate extends LatteDelegate {

    private static final String TAG = SignUpDelegate.class.getSimpleName();


    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;
    Unbinder unbinder;

    @OnClick(R2.id.btn_sign_up)
    public void onClickSignUp() {
        if (checkForm()) {
            //向服务器提交
            RestClient.builder()
                    //服务器的url
                    .url("http://192.168.1.101/RestService/api/user_profile.php")
                    .params("name", editSignUpName.getText().toString())
                    .params("email", editSignUpEmail.getText().toString())
                    .params("phone", editSignUpPhone.getText().toString())
                    .params("password", editSignUpPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d(TAG, response);
//                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response);
                            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    public void onClick() {
        getSupportDelegate().start(new SignInDelegate());
    }


    private boolean checkForm() {
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String rePassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            editSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手机号码错误");
            isPass = false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword.setError("请输入至少6位数密码");
            isPass = false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            editSignUpRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            editSignUpRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
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



//    @OnClick({R2.id.edit_sign_up_name, R2.id.edit_sign_up_email, R2.id.edit_sign_up_phone, R2.id.edit_sign_up_password, R2.id.edit_sign_up_re_password, R2.id.btn_sign_up, R2.id.tv_link_sign_in})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.edit_sign_up_name:
//                break;
//            case R.id.edit_sign_up_email:
//                break;
//            case R.id.edit_sign_up_phone:
//                break;
//            case R.id.edit_sign_up_password:
//                break;
//            case R.id.edit_sign_up_re_password:
//                break;
//            case R.id.btn_sign_up:
//                break;
//            case R.id.tv_link_sign_in:
//                break;
//        }
//    }
}
