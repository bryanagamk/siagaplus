package bro.id.siagaplus.Activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import bro.id.siagaplus.R;

public class IsiArtikelActivity extends AppCompatActivity {

    WebView webView;
    String url = "file:///android_asset/";
    String namaArtikel, namaMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_artikel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = findViewById(R.id.tvTitle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        namaArtikel = getIntent().getStringExtra("namaArtikel");
        namaMenu = getIntent().getStringExtra("namaMenu");

        tvTitle.setText(namaMenu);

        webView = findViewById(R.id.webView1);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url+namaArtikel);
    }
}
