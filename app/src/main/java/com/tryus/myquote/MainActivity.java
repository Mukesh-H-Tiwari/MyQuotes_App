package com.tryus.myquote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.tryus.myquote.catagory.Beauty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button beauty_btn,friendship_btn,coffee_btn,happiness_btn,habits_btn,love_btn,loneliness_btn,night_btn,marriage_btn,risk_btn,sadness_btn,sport_btn,success_btn,travel_btn,search_btn;
    String value="value",beauty="beauty";
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        AudienceNetworkAds.initialize(this);



        beauty_btn =findViewById( R.id.beauty_btn);
        friendship_btn=findViewById( R.id.friendship_btn );
        coffee_btn=findViewById( R.id.coffee_btn );
        happiness_btn=findViewById( R.id.happiness_btn );
        habits_btn=findViewById( R.id.habits_btn );
        love_btn=findViewById( R.id.love_btn );
        loneliness_btn=findViewById( R.id.loneliness_btn );
        marriage_btn=findViewById( R.id.marriage_btn );
        sadness_btn=findViewById( R.id.sadness_btn );
        risk_btn=findViewById( R.id.risk_btn );
        sport_btn=findViewById( R.id.sport_btn );
        night_btn=findViewById( R.id.night_btn );
        travel_btn=findViewById(R.id.travel_btn);
        success_btn=findViewById( R.id.success_btn );
        search_btn=findViewById( R.id.search_btn );




        beauty_btn.setOnClickListener(  this  );
        friendship_btn.setOnClickListener( this );
        coffee_btn.setOnClickListener( this );
        happiness_btn.setOnClickListener( this );
        habits_btn.setOnClickListener( this );
        love_btn.setOnClickListener( this );
        loneliness_btn.setOnClickListener( this );
        marriage_btn.setOnClickListener( this );
        sadness_btn.setOnClickListener( this );
        risk_btn.setOnClickListener( this );
        sport_btn.setOnClickListener( this );
        night_btn.setOnClickListener( this );
        travel_btn.setOnClickListener( this );
        success_btn.setOnClickListener( this );
        search_btn.setOnClickListener( this );

        adView = new AdView(this, "719947425397492_719952642063637", AdSize.BANNER_HEIGHT_50);

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
//                        MainActivity.this,
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



    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){


            case R.id.beauty_btn:

                            value="beauty";
                            Intent intent1=new Intent( MainActivity.this, Beauty.class );
                            intent1.putExtra( "btn",value );
                            startActivity( intent1 );

                break;




            case R.id.friendship_btn:

                    value="friendship";
                    Intent intent2=new Intent( MainActivity.this, Beauty.class );
                    intent2.putExtra( "btn",value );
                    startActivity( intent2 );

                break;




            case R.id.coffee_btn:

                    value="coffee";
                    Intent intent3=new Intent( MainActivity.this, Beauty.class );
                    intent3.putExtra( "btn",value );
                    startActivity( intent3 );

                break;



            case R.id.habits_btn:

                    value="habits";
                    Intent intent4=new Intent( MainActivity.this, Beauty.class );
                    intent4.putExtra( "btn",value );
                    startActivity( intent4 );

                break;




            case R.id.happiness_btn:

                    value="happiness";
                    Intent intent5=new Intent( MainActivity.this, Beauty.class );
                    intent5.putExtra( "btn",value );
                    startActivity( intent5 );

                break;




            case R.id.love_btn:

                    value="love";
                    Intent intent6=new Intent( MainActivity.this, Beauty.class );
                    intent6.putExtra( "btn",value );
                    startActivity( intent6 );

                break;





            case R.id.loneliness_btn:

                    value="loneliness";
                    Intent intent7=new Intent( MainActivity.this, Beauty.class );
                    intent7.putExtra( "btn",value );
                    startActivity( intent7 );

                break;





            case R.id.risk_btn:

                    value="risk";
                    Intent intent8=new Intent( MainActivity.this, Beauty.class );
                    intent8.putExtra( "btn",value );

                break;





            case R.id.marriage_btn:

                    value="marriage";
                    Intent intent9=new Intent( MainActivity.this, Beauty.class );
                    intent9.putExtra( "btn",value );
                    startActivity( intent9 );

                break;





            case R.id.sport_btn:

                    value="sport";
                    Intent intent10=new Intent( MainActivity.this, Beauty.class );
                    intent10.putExtra( "btn",value );
                    startActivity( intent10 );

                break;





            case R.id.night_btn:

                    value="night";
                    Intent intent11=new Intent( MainActivity.this, Beauty.class );
                    intent11.putExtra( "btn",value );
                    startActivity( intent11 );

                break;





            case R.id.travel_btn:

                    value="travel";
                    Intent intent12=new Intent( MainActivity.this, Beauty.class );
                    intent12.putExtra( "btn",value );
                    startActivity( intent12 );

                break;





            case R.id.sadness_btn:

                    value="sadness";
                    Intent intent13=new Intent( MainActivity.this, Beauty.class );
                    intent13.putExtra( "btn",value );
                    startActivity( intent13 );

                break;





            case R.id.success_btn:

                    value="success";
                    Intent intent14=new Intent( MainActivity.this, Beauty.class );
                    intent14.putExtra( "btn",value );
                    startActivity( intent14 );

                break;



            case R.id.search_btn:

                    Intent intent15=new Intent( MainActivity.this, Search.class );
                    startActivity( intent15 );

                break;



        }


    }
    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}