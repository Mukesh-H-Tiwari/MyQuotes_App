package com.tryus.myquote;



import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import android.text.Editable;

import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;

import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.util.Arrays;



public class Search extends AppCompatActivity {

    String[] items;

    ArrayList<String> listItems;

    ArrayAdapter<String> adapter;

    ListView listView;

    EditText editText;

    DatabaseReference databaseReference;

    MyShayari myshayari;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );

        setContentView( R.layout.search );

        listView = (ListView) findViewById( R.id.listView );

        editText = (EditText) findViewById( R.id.txtsearch );

//        initList();


        editText.addTextChangedListener( new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                adapter.getFilter().filter(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }


            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int
                    after) {


            }


            @Override

            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {

                adapter.getFilter().filter(s.toString());

                if (s.toString().equals( "" )) {

                    // reset listview

                    initList();

                } else {

                    // perform search

                    searchItem( s.toString() );

                }

            }


            @Override

            public void afterTextChanged(Editable s) {

            }

        } );



        initList();

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService( Context.CLIPBOARD_SERVICE );
                String p = listItems.get( position );
                ClipData clipData = ClipData.newPlainText( "text", p );
                clipboardManager.setPrimaryClip( clipData );
                Toast.makeText( getApplicationContext(), "copied", Toast.LENGTH_SHORT ).show();

            }
        } );


    }


    public void searchItem(String textToSearch) {

        for (String item : items) {

            if (!item.contains( textToSearch )) {

                listItems.remove( item );

            }

        }

        adapter.notifyDataSetChanged();

    }


    public void initList() {

        databaseReference = FirebaseDatabase.getInstance().getReference( "beauty" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "friendship" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "coffee" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "habits" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "happiness" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "love" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "sadness" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "sport" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "success" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "travel" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "risk" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "night" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "loneliness" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "marriage" );

//        items=new String[]{"Canada","China","Japan","USA"};
        myshayari = new MyShayari();
//        listItems=new ArrayList<>(Arrays.asList(items));

        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<String>( this, R.layout.item, R.id.txtitem, listItems );


        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//         listItems.clear();
//         story_list.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    myshayari = ds.getValue( MyShayari.class );
                    if (myshayari != null) {
                        listItems.add( myshayari.getS() );
                    }


                }


                listView.setAdapter( adapter );

            }

            public void onCancelled(@NonNull DatabaseError error) {

            }

        } );
    }
}








//
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.Window;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.tryus.myquote.catagory.Beauty;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Search extends AppCompatActivity {
//
//    ListView listView;
//    private InterstitialAd mInterstitialAd;
//    DatabaseReference databaseReference;
//    List<String> title_list, story_list;
//    ArrayAdapter<String> adapter;
//    MyShayari myshayari;
//    EditText editText;
//    Button copy_btn;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        requestWindowFeature( Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
////        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
////        WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
//        setContentView(R.layout.search);
//
//
//
//
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        listView = findViewById( R.id.listView );
//        editText=findViewById( R.id.txtsearch );
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("beauty");
//        databaseReference = FirebaseDatabase.getInstance().getReference("friendship");
//        databaseReference = FirebaseDatabase.getInstance().getReference("coffee");
//        databaseReference = FirebaseDatabase.getInstance().getReference("habits");
//        databaseReference = FirebaseDatabase.getInstance().getReference("happiness");
//        databaseReference = FirebaseDatabase.getInstance().getReference("love");
//        databaseReference = FirebaseDatabase.getInstance().getReference("sadness");
//        databaseReference = FirebaseDatabase.getInstance().getReference("sport");
//        databaseReference = FirebaseDatabase.getInstance().getReference("success");
//        databaseReference = FirebaseDatabase.getInstance().getReference("travel");
//        databaseReference = FirebaseDatabase.getInstance().getReference("risk");
//        databaseReference = FirebaseDatabase.getInstance().getReference("night");
//        databaseReference = FirebaseDatabase.getInstance().getReference("loneliness");
//        databaseReference = FirebaseDatabase.getInstance().getReference("marriage");
//
//
//
//        myshayari = new MyShayari();
//        title_list = new ArrayList<>();
//        story_list = new ArrayList<>();
//        adapter = new ArrayAdapter<>( this, R.layout.item, R.id.text,title_list );
//
//
//
//        databaseReference.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                title_list.clear();
//                story_list.clear();
//
//
//
//
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    myshayari = ds.getValue( MyShayari.class );
//                    if (myshayari != null) {
//                        title_list.add( myshayari.getS()) ;
//                    }
//                    if (myshayari != null) {
//                        story_list.add( myshayari.getS() );
//                    }
//
//                }
//
//                listView.setAdapter( adapter );
//
//                listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//
//                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService( Context.CLIPBOARD_SERVICE );
//                        String p = story_list.get( position );
//                        ClipData clipData = ClipData.newPlainText( "text", p );
//                        clipboardManager.setPrimaryClip( clipData );
//                        Toast.makeText( getApplicationContext(), "copied", Toast.LENGTH_SHORT ).show();
//
//
////                        if (mInterstitialAd.isLoaded()) {
////                            mInterstitialAd.show();
////                        } else {
////                            Intent intent = new Intent( Search.this, Next.class );
//////                            String p = story_list.get( position );
//////                            String text = p.getText().toString();
////                            intent.putExtra( "btn", p );
////                            startActivity( intent );
////                        }
////
////                        mInterstitialAd.setAdListener(new AdListener() {
////                            @Override
////                            public void onAdClosed() {
////                                // Load the next interstitial.
////                                Intent intent = new Intent( Search.this, Next.class );
////                                String p = story_list.get( position );
////                                intent.putExtra( "btn", p );
////                                startActivity( intent );
////                                mInterstitialAd.loadAd(new AdRequest.Builder().build());
////                            }
////
////                        });
//
//
//                    }
//                } );
//
//
//
//
//
//
//
//                editText.addTextChangedListener( new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        adapter.getFilter().filter(charSequence);
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//
//                    }
//                } );
//            }
//
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        } );
//
//
//    }
//
//
//}
