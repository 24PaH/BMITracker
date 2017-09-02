package com.example.iuslab.bmitracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    String tips[] = {"Dont wish for a good body, work for it!",
            "Slow progress is better than no progress!",
            "Making excuses burns zero calories per hour!",
            "You don't get the ass you want by sitting on it!",
            "Stop wishing, start doing! "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        int randomTip = rand.nextInt(5);

        TextView textView = (TextView) findViewById(R.id.tips);
        textView.setText(tips[randomTip]);

        Button button = (Button) findViewById(R.id.button0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
