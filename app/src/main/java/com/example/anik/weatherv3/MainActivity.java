package com.example.anik.weatherv3;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private TextView city_name;
    private TextView main;
    private TextView description;
    private TextView temp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = (Button) findViewById(R.id.button2);
      ;

        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {



                Intent i = new Intent(MainActivity.this,ResultActivity.class);
                startActivity(i);
            }
        });
    }

}
