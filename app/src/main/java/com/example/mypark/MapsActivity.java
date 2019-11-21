package com.example.mypark;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String SEMF = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        Intent i = getIntent();
        Bundle p = i.getExtras();
        String facilits = p.getString("chave");
        if(facilits == "" ) {
            LatLng praca1 = new LatLng(-30.029804, -51.222800);
            mMap.addMarker(new MarkerOptions().position(praca1).title("Praça Dom Feliciano - Centro Histórico"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca1));

            LatLng praca2 = new LatLng(-29.935992,  -51.021787);
            mMap.addMarker(new MarkerOptions().position(praca2).title("Praça Largo da Quintino "));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca2));
        }

        if(facilits == "areaCrianca_pistaCorrida_academia_corrida_skate"){
            LatLng praca = new LatLng(-29.924860, -51.065388);
            mMap.addMarker(new MarkerOptions().position(praca).title("Praça Morada do vale 3"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca));

            LatLng praca2 = new LatLng(-29.919929, -51.063341);
            mMap.addMarker(new MarkerOptions().position(praca2).title("Praça velha morada do vale 1"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca2));


        }
    }
}
