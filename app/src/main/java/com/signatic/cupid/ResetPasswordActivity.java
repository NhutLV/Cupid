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

import com.signatic.DataBase.DatabaseSqlite;

/**
 * Created by DefaultAccount on 9/9/2016.
 */
public class ResetPasswordActivity extends AppCompatActivity {
    private TextInputLayout mIppassword;
    private EditText mEditPassword;
    private String mEmail;
    private Button mNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword);

        //region Bundle
        Intent i=getIntent();
        Bundle bundle=i.getBundleExtra("Bundle");
        mEmail=bundle.getString("email");
        //region Properties
        mIppassword= (TextInputLayout) findViewById(R.id.ippassword);
        mEditPassword= (EditText) findViewById(R.id.edit_password);
        mNext=(Button)findViewById(R.id.btn_restpass);
        //endregion Properties
        //region method for button and textview
        getEvent();
        //endregion method for button and textview


    }
    public void getEvent(){
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkPasswordEmpty()){
                    DatabaseSqlite data=new DatabaseSqlite(ResetPasswordActivity.this);
                        data.resetPass(mEmail,mEditPassword.getText().toString());
                        Intent i = new Intent(ResetPasswordActivity.this,LoginEmailActivity.class);
                        startActivity(i);
                         overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    }
                }

        });

    }
    public boolean checkPasswordEmpty(){
        if(mEditPassword.getText().toString().isEmpty()){
            mIppassword.setError("Please Input Password");
            requestFocus(mEditPassword);
            return true;
        }
        return false;
    }

    public void requestFocus(View view){
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
