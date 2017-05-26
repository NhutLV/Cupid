package com.signatic.cupid;

import android.content.Intent;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.signatic.utils.ToolsActivity;

/**
 * Created by DefaultAccount on 9/6/2016.
 */
public class SignUpActivity extends AppCompatActivity{
    private TextView mTitleSignUp,mAndBook,mOr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        mTitleSignUp= (TextView) findViewById(R.id.signup);
        mTitleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,SignUpActivityDemo.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
        mAndBook=(TextView)findViewById(R.id.tv1);
        mAndBook.setText(Html.fromHtml(getString(R.string.info_sign_up)));
        mOr=(TextView)findViewById(R.id.tv2);
        mOr.setText(Html.fromHtml(getString(R.string.info_sign_up_bottom)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ToolsActivity.backPress(this);
    }
}
