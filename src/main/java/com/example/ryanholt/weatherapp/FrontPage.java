package com.example.ryanholt.weatherapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity {

    public double mapLong;
    public double mapLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        Button getWeather = (Button) findViewById(R.id.getWeather);
        Button setLocation = (Button) findViewById(R.id.setLocation);
        final Button aboutButton = (Button) findViewById(R.id.aboutButton);

        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getWeather = new Intent(getApplicationContext(), WeatherDisplay.class);

                getWeather.putExtra("Longitude", mapLong);
                getWeather.putExtra("Latitude", mapLat);

                startActivity(getWeather);
            }
        });

        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setLocation = new Intent(getApplicationContext(), SetLocation.class);

                setLocation.putExtra("Longitude", mapLong);
                setLocation.putExtra("Latitude", mapLat);

                startActivityForResult(setLocation, 200);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog aboutDialog = new AlertDialog.Builder(FrontPage.this).create();
                aboutDialog.setTitle("About");
                aboutDialog.setMessage("This is a test application to explore Android Development.");
                aboutDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                aboutDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case (200) : {
                if (resultCode == Activity.RESULT_OK) {
                    mapLong = intent.getFloatExtra("Longitude", 0);
                    mapLong = intent.getFloatExtra("Latitude", 0);
                }
                break;
            }
        }
    }
}
