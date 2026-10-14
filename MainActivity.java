package com.javedstudystudio.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setSupportMultipleWindows(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setLoadWithOverviewMode(false);
        s.setUseWideViewPort(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri u = request.getUrl();
                String scheme = u.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    if (u.toString().startsWith("https://wa.me/") || u.toString().contains("whatsapp.com")) {
                        try { startActivity(new Intent(Intent.ACTION_VIEW, u)); } catch (Exception ignored) {}
                        return true;
                    }
                    view.loadUrl(u.toString());
                    return true;
                }
                try { startActivity(new Intent(Intent.ACTION_VIEW, u)); } catch (Exception ignored) {}
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, android.os.Message resultMsg) {
                WebView.HitTestResult hit = view.getHitTestResult();
                if (hit != null && hit.getExtra() != null) {
                    try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(hit.getExtra()))); } catch (Exception ignored) {}
                }
                return false;
            }
        });

        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override protected void onDestroy() {
        if (webView != null) { webView.loadUrl("about:blank"); webView.destroy(); }
        super.onDestroy();
    }
}
