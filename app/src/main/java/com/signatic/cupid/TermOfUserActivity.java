package com.signatic.cupid;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.signatic.utils.ToolsActivity;

/**
 * Created by NhutDu on 22/11/2016.
 */

public class TermOfUserActivity extends AppCompatActivity {

    //region Properties

    WebView webView;
    Button btnClose;

    //endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_user);
        ToolsActivity.setColorStatus(this,R.color.colorPrimaryDark);
        webView = (WebView) findViewById(R.id.webview);
        btnClose = (Button) findViewById(R.id.close_term_of_use);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/terms.html");

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
