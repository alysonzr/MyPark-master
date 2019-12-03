package com.example.mypark;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.mypark.R.id.map;

public class DetalhesActivity extends AppCompatActivity  implements OnMapReadyCallback {


    TextView txtEndereco,txtNomePraca,nomeInstalacoes, txtDistancia;
    ViewPager viewPager;
    Double latitudeDevice;
    Double longitudeDevice;

    Double latitudePraca;
    Double longitudePraca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        txtEndereco =  findViewById(R.id.txtEndereco);
        txtNomePraca =  findViewById(R.id.txtNomePraca);
        viewPager = findViewById(R.id.view_pager);
        txtDistancia = findViewById(R.id.txtDistancia);
        nomeInstalacoes = findViewById(R.id.nomeInstalacoes);
        mostraDadosNaTela();

    }

    private void mostraDadosNaTela() {
        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("nome");
        String endereco = extras.getString("endereco");
        ArrayList imagem = (ArrayList) extras.get("imagem");
        String icon = extras.getString("icon");
        ArrayList instalaDetalhes = (ArrayList) extras.get("instalaDetalhes");
        String instalacoes = extras.getString("Instalacoes");
        Double latitude = extras.getDouble("latitude");
        Double longitude = extras.getDouble("longitude");

        Double latitudeDevice = extras.getDouble("latitudeDevice");
        Double longitudeDevice = extras.getDouble("longitudeDevice");
        setLatitudeDevice(latitudeDevice);
        setLongitudeDevice(longitudeDevice);


        ViewPagerAdapter adapterPager = new ViewPagerAdapter(this, imagem);
        String[] instala = instalacoes.split(Pattern.quote (","));
        ArrayList<String> lista1 = new ArrayList<String>();
		ArrayList<String> i = convertStringArrayToArraylist(instala);
		for ( String o : i) {
			lista1.add(o.toString());
		}

        ListView listView = (ListView)findViewById(R.id.lisv);
        PracaDetalhesAdapter adapter = new PracaDetalhesAdapter(this,  lista1 );
        listView.setAdapter(adapter);

        viewPager.setAdapter(adapterPager);
        txtEndereco.setText("Endereço: "+endereco);
        txtNomePraca.setText("Nome: "+nome);
        txtDistancia.setText("Distancia: ");
    }

    public static ArrayList<String> convertStringArrayToArraylist(String[] strArr){
        ArrayList<String> stringList = new ArrayList<String>();
        for (String s : strArr) {
            stringList.add(s);
        }
        return stringList;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap == null) {
            googleMap = (MapFragment) getFragmentManager().findFragmentById(map);

            Double latitude = getLatitudePraca();
            Double longitude = getLatitudeDevice();

            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(latitude, longitude)).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(this,
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public Double getLatitudeDevice() {
        return latitudeDevice;
    }

    public void setLatitudeDevice(Double latitudeDevice) {
        this.latitudeDevice = latitudeDevice;
    }

    public Double getLongitudeDevice() {
        return longitudeDevice;
    }

    public void setLongitudeDevice(Double longitudeDevice) {
        this.longitudeDevice = longitudeDevice;
    }

    public Double getLatitudePraca() {
        return latitudePraca;
    }

    public void setLatitudePraca(Double latitudePraca) {
        this.latitudePraca = latitudePraca;
    }

    public Double getLongitudePraca() {
        return longitudePraca;
    }

    public void setLongitudePraca(Double longitudePraca) {
        this.longitudePraca = longitudePraca;
    }
}
