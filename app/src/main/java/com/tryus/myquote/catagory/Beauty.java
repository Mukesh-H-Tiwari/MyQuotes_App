package com.tryus.myquote.catagory;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tryus.myquote.MyShayari;
import com.tryus.myquote.R;

import java.util.ArrayList;
import java.util.List;

public class Beauty extends AppCompatActivity implements View.OnClickListener{
    private AdView adView;
//    private AdView mAdView;
    List<String> shayari_list;
    DatabaseReference databaseReference;
    MyShayari myShayari;
    int position = 0;
    TextView shayari_txt,title;

    Button prev_btn, next_btn, copy_btn, share_btn;
    TextView count_txt;
    RelativeLayout relativeLayout;
    LinearLayout loading;
    String value,color;
    ScrollView scrollView;
    int count = 0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar().hide();
        setContentView( R.layout.common );
        AudienceNetworkAds.initialize(this);


        title=findViewById( R.id.title );

        shayari_txt = findViewById( R.id.shayari_txt );
        prev_btn = findViewById( R.id.previous_btn );
        copy_btn = findViewById( R.id.copy_btn );
        share_btn = findViewById( R.id.share_btn );
        next_btn = findViewById( R.id.next_btn );
        count_txt = findViewById( R.id.cont_txt );
        relativeLayout=findViewById( R.id.relativelayout );
        scrollView=findViewById(R.id.scroll);
        loading=findViewById( R.id.loading );
//        relativeLayout.setBackground( getDrawable( R.drawable.back ) );
        relativeLayout=findViewById( R.id.main );
//        relativeLayout.setBackground(getDrawable( R.drawable.allback) );

        prev_btn.setOnClickListener( this );
        copy_btn.setOnClickListener( this );
        share_btn.setOnClickListener( this );
        next_btn.setOnClickListener( this );

        count_txt.setVisibility( View.INVISIBLE );

        adView = new AdView(this, "719947425397492_720601998665368", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();

//        AdListener adListener = new AdListener() {
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                // Ad error callback
//                Toast.makeText(
//                        Beauty.this,
//                        "Error: " + adError.getErrorMessage(),
//                        Toast.LENGTH_LONG)
//                        .show();
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Ad loaded callback
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//            }
//        };
//
//        // Request an ad
//        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());



        if (shayari_txt.getText().toString().trim().equals("Please Wait !")) {
            next_btn.setEnabled( false );
            next_btn.setClickable( false );

        }

        if (shayari_txt.getText().toString().trim().equals("Please Wait !")){
            prev_btn.setEnabled(false );
            prev_btn.setClickable(false);
        }





        final String rQuote = getIntent().getStringExtra( "key" );

        Intent intent=getIntent();
        value=intent.getStringExtra( "btn" );
        if (value.equals("beauty")){
            scrollView.setBackgroundResource( R.drawable.beauty_btn );
        }
        if (value.equals("friendship")){
            scrollView.setBackgroundResource( R.drawable.friendship_btn );
        }
        if (value.equals("coffee")){
            scrollView.setBackgroundResource( R.drawable.coffee_btn );
        }
        if (value.equals("habits")){
            scrollView.setBackgroundResource( R.drawable.habits_btn );
        }
        if (value.equals("happiness")){
            scrollView.setBackgroundResource( R.drawable.happiness_btn );
        }
        if (value.equals("love")){
            scrollView.setBackgroundResource( R.drawable.love_btn );
        }
        if (value.equals("sadness")){
            scrollView.setBackgroundResource( R.drawable.sadness_btn );
        }
        if (value.equals("sport")){
            scrollView.setBackgroundResource( R.drawable.sport_btn );
        }
        if (value.equals("sucess")){
            scrollView.setBackgroundResource( R.drawable.success_btn);
        }
        if (value.equals("travel")){
            scrollView.setBackgroundResource( R.drawable.travel_btn );
        }
        if (value.equals("risk")){
            scrollView.setBackgroundResource( R.drawable.risk_btn );
        }
        if (value.equals("night")){
            scrollView.setBackgroundResource( R.drawable.night_btn );
        }
        if (value.equals("loneliness")){
            scrollView.setBackgroundResource( R.drawable.loneliness_btn );
        }
        if (value.equals("marriage")){
            scrollView.setBackgroundResource( R.drawable.marriage_btn );
        }



        title.setText( value );
        databaseReference = FirebaseDatabase.getInstance().getReference( ""+value+"" );
        myShayari = new MyShayari();
        shayari_list = new ArrayList<>();


        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    myShayari = ds.getValue( MyShayari.class );
                    if (myShayari != null) {
                        shayari_list.add( myShayari.getS() );
                    }
                }

                shayari_txt.setText( shayari_list.get( position ) );
                count_txt.setText( position+"/"+shayari_list.size() );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( getApplicationContext(), "error Occurred", Toast.LENGTH_SHORT ).show();
            }
        } );


//        AdView adView = new AdView(this);
//        adView.setAdSize( AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-7127654850920599/2608123568");
//
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
////        mAdView.loadAd(adRequest);
//        mAdView.loadAd(adRequest);

//        if (!"Plzz connect to internet !".equals( shayari_txt.getText().toString().trim()  )) {
//            prev_btn.setEnabled( true );
//            prev_btn.setClickable( true );
//        }
//
//        if (!"Plzz connect to internet !".equals( shayari_txt.getText().toString().trim()  )){
//            next_btn.setEnabled( true );
//            next_btn.setClickable(true);
//        }



        shayari_txt.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setEnabled( true );
                next_btn.setClickable(true);
                prev_btn.setEnabled(true );
                prev_btn.setClickable(true);
                loading.setVisibility( View.INVISIBLE );
                count_txt.setVisibility( View.VISIBLE );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );




    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.previous_btn:
                previous();
                break;


            case R.id.copy_btn:
                copy();
                break;


            case R.id.share_btn:
                share();
                break;


            case R.id.next_btn:
                next();
                break;
        }

    }

    private void previous() {


//        Toast.makeText(getApplicationContext(), "Internet Connection Is Required", Toast.LENGTH_LONG).show();


            if (position > 0) {
                position = (position - 1) % shayari_list.size();
                shayari_txt.setText( shayari_list.get( position ) );
                count_txt.setText( position + "/" + shayari_list.size() );
            }
        }




    private void next(){




            position = (position + 1) % shayari_list.size();
            shayari_txt.setText( shayari_list.get( position ) );
            count_txt.setText( position + "/" + shayari_list.size() );
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
