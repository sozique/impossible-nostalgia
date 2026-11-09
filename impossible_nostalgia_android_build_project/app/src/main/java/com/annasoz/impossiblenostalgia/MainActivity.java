package com.annasoz.impossiblenostalgia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** Self-contained, offline screen for the Impossible Nostalgia installation. */
public class MainActivity extends Activity {
    private WebView screen;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        screen = new WebView(this);
        screen.setBackgroundColor(0xff0868da);
        screen.getSettings().setJavaScriptEnabled(true);
        screen.getSettings().setDomStorageEnabled(true);
        screen.getSettings().setAllowFileAccess(true);
        screen.getSettings().setAllowContentAccess(false);
        screen.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        // Installation audio begins on the ENABLE AUDIO button, but the native
        // WebView need not demand a second gesture for each successive clip.
        screen.getSettings().setMediaPlaybackRequiresUserGesture(false);
        screen.setWebChromeClient(new WebChromeClient());
        screen.setWebViewClient(new WebViewClient());
        setContentView(screen);
        screen.loadUrl("file:///android_asset/www/index.html");
    }

    @Override protected void onPause() {
        if (screen != null) screen.onPause();
        super.onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        if (screen != null) screen.onResume();
    }

    @Override protected void onDestroy() {
        if (screen != null) { screen.destroy(); screen = null; }
        super.onDestroy();
    }
}
