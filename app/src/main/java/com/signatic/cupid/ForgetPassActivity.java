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

import com.signatic.DataBase.DatabaseSqlite;

/**
 * Created by DefaultAccount on 9/9/2016.
 */
public class ForgetPassActivity  extends AppCompatActivity {
    private TextInputLayout mIpEmail;
    private EditText mEditEmail;
    private Button mNext;
    private TextView mBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpass);
        //region Properties
        mIpEmail= (TextInputLayout) findViewById(R.id.ipemail);
        mEditEmail= (EditText) findViewById(R.id.editemail);
        mNext=(Button)findViewById(R.id.btn_forget);
        mBack= (TextView) findViewById(R.id.back);
        //endregion Properties
        //region method for button and textview
        GetEvent();
        //endregion method for button and textview


    }
    public void GetEvent(){
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkUserEmpty()){
                    DatabaseSqlite data=new DatabaseSqlite(ForgetPassActivity.this);
                    if(data.checkEmail(mEditEmail.getText().toString())!=null) {
                        Intent i = new Intent(ForgetPassActivity.this,ResetPasswordActivity.class);
                        Bundle mBundle =new Bundle();
                        mBundle.putString("email",mEditEmail.getText().toString());
                        i.putExtra("Bundle",mBundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    }else{
                        mIpEmail.setError("User Name Not Exists");
                    }
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgetPassActivity.this,LoginEmailActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
    }
    public boolean checkUserEmpty(){
        if(mEditEmail.getText().toString().isEmpty()){
           mIpEmail.setError("Please Input User Name");
            requestFocus(mEditEmail);
            return true;
        }
        return false;
    }

    public void requestFocus(View view){
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
