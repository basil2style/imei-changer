package com.makeinfo.imeichanger;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class HelpActivity extends Activity {
    WebView help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        help = (WebView) findViewById(R.id.webView2);

        help.loadUrl("file:///android_asset/help.html");
    }

}
