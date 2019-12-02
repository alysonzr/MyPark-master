package com.example.mypark;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.regex.Pattern;

public class DetalhesActivity extends AppCompatActivity  implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Number latitude;
    private Number longitude;


    TextView txtEndereco,txtNomePraca,nomeInstalacoes;
     ImageView iconInstalacoes;
     ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        txtEndereco =  findViewById(R.id.txtEndereco);
        txtNomePraca =  findViewById(R.id.txtNomePraca);
        viewPager = findViewById(R.id.view_pager);

        iconInstalacoes = findViewById(R.id.iconInstalacoes);
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
        Number latitude = extras.getInt("latitude");
        Number longitude = extras.getInt("longitude");
        setLatitude(latitude);
        setLongitude(longitude);

        ViewPagerAdapter adapterPager = new ViewPagerAdapter(this, imagem);
        String[] instala = instalacoes.split(Pattern.quote (","));



        ListView listView = (ListView)findViewById(R.id.lisv);

        PracaDetalhesAdapter adapter = new PracaDetalhesAdapter(this,  instala );
        listView.setAdapter(adapter);

        viewPager.setAdapter(adapterPager);
        txtEndereco.setText(endereco);
        txtNomePraca.setText(nome);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng gravatai = new LatLng(getLatitude().doubleValue(), getLongitude().doubleValue());
        mMap.addMarker(new MarkerOptions().position(gravatai).title("Praça Jetulio Vargas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gravatai));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gravatai,10),5000,null);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }
}
