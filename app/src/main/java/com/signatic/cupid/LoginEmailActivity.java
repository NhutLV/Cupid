package com.signatic.cupid;

import android.content.Intent;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.signatic.model.User;

import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;

/**
 * Created by DefaultAccount on 9/6/2016.
 */
public class LoginEmailActivity extends AppCompatActivity{
    private TextInputLayout mIpEmail,mIpPassword;
    private EditText mEditEmail,mEditPassword;
    private Button mLogin;
    private TextView mForgetPass;
    private String mPrefName= "data_login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_with_email_new);
        final UserServiceImpl mUserService = new UserServiceImpl(this);
        //region Properties
        mIpEmail= (TextInputLayout) findViewById(R.id.ipemail);
        mIpPassword= (TextInputLayout) findViewById(R.id.ippassword);
        mEditEmail= (EditText) findViewById(R.id.edit_email);
        mEditPassword= (EditText) findViewById(R.id.edit_password);
        mLogin= (Button) findViewById(R.id.btn_login);
        mForgetPass= (TextView) findViewById(R.id.forget);

        //endregion Properties

        //region method for button and text view

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CheckEmailEmpty()&&!CheckPasswordEmpty()){
                    mUserService.login(mEditEmail.getText().toString(),mEditPassword.getText().toString());
//                    User userLogin=new User("hungpham","khongco","bi","anhkhongthich@gmail.com","12344",null);
//                    UserLogin.setmUserLogin(userLogin);
//                    Intent intent = new Intent(LoginEmailActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                }
            }
        });

        GetEvent();
        //endregion method for button and textview
    }

    //region Private methods

    private void GetEvent(){
        mForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginEmailActivity.this, ForgetPassActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
    }
    private boolean CheckEmailEmpty(){
        if( mEditEmail.getText().toString().isEmpty()){
            mIpEmail.setError("Please Input Email or User Name");
            RequestFocus(mIpEmail);
         return true;
        }
        return false;
    }
    private boolean CheckPasswordEmpty(){
        if(mEditPassword.getText().toString().isEmpty()){
            mIpPassword.setError("Please Input Password");
            RequestFocus(mEditPassword);
            return true;
        }
        return false;
    }
    private void RequestFocus(View view){
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    //endregion

    //region Override methods

    @Override
    protected void onPause() {
        super.onPause();
        //savingPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //restoringPreferences();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ToolsActivity.backPress(this);
    }

    //endregion

}

