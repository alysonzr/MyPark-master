package com.example.mypark;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.lang.Math;
import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class OpcoesDePraca extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private Location location;
    protected  static  final int TIMER_RUNTIME = 50000;
    private Double latitudeUsuario;
    private Double longitudeUsuario;
    private Double result;

    private GoogleApiClient mGoogleApiClient;
    TextView textView2;
    FirebaseFirestore fireStore;

    protected  boolean mbActive;
    protected ProgressBar nproProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pracas);
        setTitle("MySquare");
        fireStore = FirebaseFirestore.getInstance();
        callConnection();
        final ListView lista = (ListView) findViewById(R.id.listPracas);


        Intent i = getIntent();
        Boolean Skate = i.getBooleanExtra("skatee", false);
        Boolean areaCri = i.getBooleanExtra("AreaCrianca", false);
        Boolean corrida = i.getBooleanExtra("Corrida", false);
        Boolean areaDog = i.getBooleanExtra("areaDog", false);

        //ConverteLatitude(latitudeUsuario, longitudeUsuario, "-29.926859", "-51.039984");

        if (Skate) {
            final ArrayList<Praca> pracas = new ArrayList<Praca>();
            fireStore.collection("Parks").document("PracasGravatai").collection("Skate").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String uid = (document.getId());
                            String nome = (document.getString("Nome"));
                            String instalacoes = (document.getString("Facilidades"));
                            String endereco = (document.getString("Endereco"));
                            ArrayList imagem = ((ArrayList) document.get("Imagens"));

                            Praca p = new Praca(uid,nome, instalacoes,endereco,imagem);
                            pracas.add(p);
                            ArrayAdapter adapter = new PracaAdapter(OpcoesDePraca.this,pracas);
                            lista.setAdapter(adapter);
                        }
                    }
                }
            });
        }
        if(areaCri){
            final ArrayList<Praca> pracas = new ArrayList<Praca>();
            fireStore.collection("Parks").document("PracasGravatai").collection("AreaCrianca").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String uid = (document.getId());
                            String nome = (document.getString("Nome"));
                            String instalacoes = (document.getString("Instalacoes"));
                            String endereco = (document.getString("Endereco"));;
                            //String imagem = (document.getString("Imagens"));
                            ArrayList imagem = ((ArrayList) document.get("Imagens"));
                            Praca p = new Praca(uid,nome, instalacoes,endereco,imagem);
                            pracas.add(p);
                            ArrayAdapter adapter = new PracaAdapter(OpcoesDePraca.this,pracas);
                            lista.setAdapter(adapter);
                        }
                    }
                }
            });
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                Praca  itemValue = (Praca) lista.getItemAtPosition(position);
                String uid = itemValue.getUid();
                String nome = itemValue.getNome();
                String Instalacoes = itemValue.getFacilidades();
                String endereco = itemValue.getEndereco();
                ArrayList imagem = itemValue.getImagem();
                Log.d("LOG", String.valueOf(imagem));

                Intent i = new Intent(OpcoesDePraca.this, DetalhesActivity.class);
                i.putExtra("uid",uid);
                i.putExtra("nome",nome);
                i.putExtra("Instalacoes",Instalacoes);
                i.putExtra("endereco",endereco);
                i.putExtra("imagem", imagem);

                 startActivity(i);
                Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  itemValue : " +uid , Toast.LENGTH_LONG).show();

            }
        });

    }







    public void updateProgress(final int timePassed){
            if(null != nproProgressBar){
                final int progress = nproProgressBar.getMax() * timePassed / TIMER_RUNTIME;
                nproProgressBar.setProgress(progress);
            }
    }
    public  void onContinue(){
            Log.d("MensagemFinal", "sua barra de logging terminou");
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