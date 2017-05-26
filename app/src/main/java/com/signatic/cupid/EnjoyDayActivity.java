package com.signatic.cupid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.Callback;
import com.signatic.utils.UserLogin;

import org.jetbrains.annotations.Nullable;

/**
 * Created by DefaultAccount on 10/3/2016.
 */
public class EnjoyDayActivity extends AppCompatActivity {
    Button mGetStart,mLimit;
    LinearLayout mLinearLayout;
    Canvas canvas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enjoyday);
        canvas=new Canvas();
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        TextView close= (TextView) toolbar.findViewById(R.id.close);
        mLinearLayout= (LinearLayout) findViewById(R.id.mau);
        OvalShape moi=new OvalShape();
        ShapeDrawable shapeDrawable=new ShapeDrawable(moi);
        shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorEnjoyHeader));
        mLinearLayout.setBackground(shapeDrawable);
        mGetStart= (Button) findViewById(R.id.getstart);
        mLimit= (Button) findViewById(R.id.limit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                UserServiceImpl userService = new UserServiceImpl();
                                userService.updateVip(7, UserLogin.getUserLogin().getID(),UserLogin.getUserLogin().getApiKey(), new Callback<Boolean>() {
                                    @Override
                                    public void next(Boolean result) {
                                        if (result) {
                                            Toast.makeText(getApplicationContext(), "Success! You have 7 days VIP ", Toast.LENGTH_LONG).show();
                                            mGetStart.setEnabled(false);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Success! You have 7 days VIP ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(EnjoyDayActivity.this);
                builder.setMessage("Are you sure use 7 days VIP free ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });
        mLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EnjoyDayActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }
}
