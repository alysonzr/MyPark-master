package com.example.mypark;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypark.DataBase.PesquisaBD;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class DetalhesPracasActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private Location location;

    private Double latitudeUsuario;
    private Double longitudeUsuario;
    private Double result;

    private GoogleApiClient mGoogleApiClient;
    TextView textView2;
    FirebaseFirestore fireStore;

   // ArrayList<String> pracass = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pracas);
        fireStore = FirebaseFirestore.getInstance();
        callConnection();

        ListView lista = (ListView) findViewById(R.id.listPracas);


        textView2 = findViewById(R.id.textView2);

        Intent i = getIntent();
        Boolean Skate = i.getBooleanExtra("skatee", false);
        Boolean areaCri = i.getBooleanExtra("AreaCrianca", false);
        Boolean corrida = i.getBooleanExtra("Corrida", false);
        Boolean areaDog = i.getBooleanExtra("areaDog", false);


        //ConverteLatitude(latitudeUsuario, longitudeUsuario, "-29.926859", "-51.039984");

        if (Skate) {
            ArrayList<Praca> pracas = adicionaPracas();
            ArrayAdapter adapter = new PracaAdapter(this, pracas);
            lista.setAdapter(adapter);


        }

        /*if (areaDog) {
            fireStore.collection("Parks").document("PracasGravatai").collection("AreaDog").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            pracass.add(document.getString("Nome"));
                        }

                    }
                }
            });
        }*/

    }

    private ArrayList<Praca> adicionaPracas() {
        final ArrayList<Praca> pracas = new ArrayList<Praca>();
        fireStore.collection("Parks").document("PracasGravatai").collection("Skate").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                     String nome = (document.getString("Nome"));
                     String facilidades = (document.getString("Facilidades"));

                     Praca p = new Praca(nome,facilidades);
                     pracas.add(p);
                    }

                }
            }
        });
        return  pracas;
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    // LISTENER
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", "onConnected(" + bundle + ")");

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else {
            Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (l != null) {
                latitudeUsuario = l.getLatitude();
                longitudeUsuario = l.getLongitude();
                Log.i("LOG", "latitude: " + l.getLatitude());
                Log.i("LOG", "longitude: " + l.getLongitude());
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed(" + connectionResult + ")");
    }

    public void ConverteLatitude(Double latitude1, Double logitude1, String latitude2, String logitude2) {

        final int R = 6371; // Radious of the earth
        Double lat1 = latitude1;
        Double lon1 = logitude1;
        Double lat2 = Double.parseDouble(latitude2);
        Double lon2 = Double.parseDouble(logitude2);
        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        result = distance;
        Log.i("LOG", "distancia: " + distance);
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }


    public Double getLatitudeUsuario() {
        return latitudeUsuario;
    }

    public void setLatitudeUsuario(Double latitudeUsuario) {
        this.latitudeUsuario = latitudeUsuario;
    }

    public Double getLongitudeUsuario() {
        return longitudeUsuario;
    }

    public void setLongitudeUsuario(Double longitudeUsuario) {
        this.longitudeUsuario = longitudeUsuario;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}