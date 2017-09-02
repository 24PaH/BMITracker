package com.example.iuslab.bmitracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ResultsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        myDB = new DatabaseHelper(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView theBmi = (TextView) findViewById(R.id.the_bmi);
        TextView bmiInfo = (TextView) findViewById(R.id.bmi_info);

        String height =getIntent().getExtras().getString("height");
        String weight =getIntent().getExtras().getString("weight");
        String age=getIntent().getExtras().getString("age");

        if(height.length() != 0 && weight.length() != 0) {
            double h1 = Double.parseDouble(height);
            double w1 = Double.parseDouble(weight);
            double bmi = w1 / (h1 * h1);
            bmi = Math.round(bmi * 100.0) / 100.0;
            String bmiS = Double.toString(bmi);
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date());*/

            myDB.addData(bmiS);

            if(bmi<16) {
                theBmi.setTextColor(ContextCompat.getColor(this, R.color.bad));
                bmiInfo.setText(R.string.underweight);
            }
            else if(bmi<=18.5) {
                theBmi.setTextColor(ContextCompat.getColor(this, R.color.warning));
                bmiInfo.setText(R.string.underweight);
            }
            else if(bmi>18.5 && bmi<=24.9) {
                theBmi.setTextColor(ContextCompat.getColor(this, R.color.good));
                bmiInfo.setText(R.string.normal_weight);
            }
            else if(bmi>=25 && bmi<=29.9) {
                theBmi.setTextColor(ContextCompat.getColor(this, R.color.warning));
                bmiInfo.setText(R.string.overweight);
            }
            else if(bmi>=30) {
                theBmi.setTextColor(ContextCompat.getColor(this, R.color.bad));
                bmiInfo.setText(R.string.obese);
            }

            theBmi.setText(bmiS);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent;
        if(id==R.id.progress){
            intent = new Intent(ResultsActivity.this, ProgressActivity.class);
            startActivity(intent);
        }
        if(id==R.id.graph){
            intent = new Intent(ResultsActivity.this, GraphActivity.class);
            startActivity(intent);
        }
        if(id==R.id.hints_and_info){
            intent = new Intent(ResultsActivity.this, InfoActivity.class);
            startActivity(intent);
        }
        if(id==R.id.about){
            intent = new Intent(ResultsActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
