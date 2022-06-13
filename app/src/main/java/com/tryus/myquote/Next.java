package com.tryus.myquote;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Next extends AppCompatActivity implements View.OnClickListener {


    List<String> shayari_list;
    DatabaseReference databaseReference;
    MyShayari myShayari;
    int position = 0;
    TextView shayari_txt, title;
    RelativeLayout relativeLayout1;
    Button prev_btn, next_btn, copy_btn, share_btn;
    TextView count_txt;
    RelativeLayout relativeLayout;
    String value;
    int count = 0;
    private AdView adView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar().hide();
        setContentView( R.layout.activity_next );
        AudienceNetworkAds.initialize(this);



        shayari_txt = findViewById( R.id.shayari_txt );

        copy_btn = findViewById( R.id.copy_btn );
        share_btn = findViewById( R.id.share_btn );



        relativeLayout = findViewById( R.id.relativelayout );
//        relativeLayout.setBackground( getDrawable( R.drawable.back ) );
        relativeLayout = findViewById( R.id.main );
//        relativeLayout.setBackground( getDrawable( R.drawable.allback ) );

        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Toast.makeText(
                        Next.this,
                        "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };

        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());



        Intent intent = getIntent();
        value = intent.getStringExtra( "btn");
        shayari_txt.setText( value );

        copy_btn.setOnClickListener( this );
        share_btn.setOnClickListener( this );

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.copy_btn:
                copy();
                break;


            case R.id.share_btn:
                share();
                break;
        }
    }


    private void copy()

    {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService( Context.CLIPBOARD_SERVICE );
        ClipData clipData = ClipData.newPlainText( "text", shayari_txt.getText() );
        clipboardManager.setPrimaryClip( clipData );
        Toast.makeText( getApplicationContext(), "copied", Toast.LENGTH_SHORT ).show();
    }


    private void share() {
        Intent intent = new Intent( Intent.ACTION_SEND );
        intent.setType( "text/plain" );
        intent.putExtra( Intent.EXTRA_TEXT, shayari_txt.getText() );
        startActivity( Intent.createChooser( intent, "share by" ) );
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}

