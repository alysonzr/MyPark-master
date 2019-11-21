package com.example.mypark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalhesActivity extends AppCompatActivity {


    MapView mapView;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        mapView = (MapView) findViewById(R.id.map);

        Intent i = getIntent();
        Bundle p = i.getExtras();
        String facilits = p.getString("chave");
        if(facilits == "" ) {
           // LatLng praca1 = new LatLng(-30.029804, -51.222800);
            setContentView(R.layout.activity_detalhes);
            mapView = (MapView) findViewById(R.id.map);
            Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
            map.addMarker(new MarkerOptions()
                    .position(new LatLng( -30.029804, -51.222800))
                    .title("You are here"));
        }
//            mMap.addMarker(new MarkerOptions().position(praca1).title("Praça Dom Feliciano - Centro Histórico"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca1));
//
//            LatLng praca2 = new LatLng(-29.935992,  -51.021787);
//            mMap.addMarker(new MarkerOptions().position(praca2).title("Praça Largo da Quintino "));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca2));
        }


    //For mapView
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }


}
