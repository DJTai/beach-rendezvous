package com.example.beachrendezvous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/*
---Details of the groups of sports selected---

 */


public class findsportsDetails extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView admin;
    TextView gameheader;
    TextView type;
    TextView date;
    TextView place;
    ListView mListView;
    TextView header;
    ImageView mImageView;
    Toolbar header1;
    ImageButton returnbutton;

    String[] type1={"Indoor", "Outdoor", "Indoor", "Outdoor"};
    String[] date1={"07/9", "08/10", "09/20", "07/5"};
    String[] place1 ={"Gym", "ASI", "Pyramid", "Place4"};
    String gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_details);
        header=(TextView)findViewById(R.id.gameheader);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
             gameName=mBundle.getString("gameName");
                   }
        header.setText("Lets find the best moment and best place to play "+ gameName+"!");
        mListView = (ListView) findViewById(R.id.listview1);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        sportsDetailsAdapter myAdapter = new sportsDetailsAdapter(findsportsDetails.this, type1, date1, place1);
        mListView.setAdapter(myAdapter);
        returnbutton=(ImageButton) findViewById(R.id.return2);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(findsportsDetails.this, MainActivity.class);
                startActivity(in);
            }
        });


           }
}
