package com.example.iuslab.bmitracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UserActivity extends AppCompatActivity {

    private EditText heightET;
    private EditText weightET;
    private EditText ageET;

    private String heightS;
    private String weightS;
    private String ageS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button button = (Button) findViewById(R.id.view_bmi);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightET = (EditText) findViewById(R.id.height);
                weightET = (EditText) findViewById(R.id.weight);
                ageET = (EditText) findViewById(R.id.age);

                heightS = heightET.getText().toString();
                weightS = weightET.getText().toString();
                ageS = ageET.getText().toString();

                Intent yourIntent = new Intent(UserActivity.this, ResultsActivity.class);

                yourIntent.putExtra("height", heightS);
                yourIntent.putExtra("weight", weightS);
                yourIntent.putExtra("age", ageS);

                startActivity(yourIntent);
            }
        });
    }
}
