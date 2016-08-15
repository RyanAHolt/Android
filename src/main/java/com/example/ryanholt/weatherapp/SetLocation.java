package com.example.ryanholt.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SetLocation extends AppCompatActivity implements OnMapReadyCallback {

    public double mapLong;
    public double mapLat;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        mapLong = intent.getFloatExtra("Latitude", 0);
        mapLat = intent.getFloatExtra("Longitude", 0);

        Button backButton = (Button) findViewById(R.id.location_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Longitude", mapLong);
                resultIntent.putExtra("Latitude", mapLat);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        Button setLocation = (Button) findViewById(R.id.set_location_button);
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make map display location and set mapLong and mapLat to the correct geocode
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mapLat != 0 && mapLong != 0) {
            LatLng location = new LatLng(mapLat, mapLong);
            mMap.addMarker(new MarkerOptions().position(location).title("Your Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
}
