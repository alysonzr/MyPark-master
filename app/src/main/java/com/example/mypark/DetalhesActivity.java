package com.example.mypark;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;



import java.util.ArrayList;

public class DetalhesActivity extends AppCompatActivity{


     TextView txtEndereco,txtNomePraca,nomeInstalacoes;
     ImageView iconInstalacoes;
     ViewPager viewPager;
     MapView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        txtEndereco =  findViewById(R.id.txtEndereco);
        txtNomePraca =  findViewById(R.id.txtNomePraca);
        viewPager = findViewById(R.id.view_pager);
        mapa = findViewById(R.id.mapView);
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
        String instalacoes = extras.getString("Instalacoes");


        ViewPagerAdapter adapterPager = new ViewPagerAdapter(this, imagem);
        ViewPagerAdapter adapterPager = new ViewPagerAdapter(this, imagem);
        
        PracaDetalhesAdapter pracaDetalhesAdapter = new PracaDetalhesAdapter(this,);

        viewPager.setAdapter(adapterPager);
        iconInstalacoes.setAdapter
        txtEndereco.setText(endereco);
        txtNomePraca.setText(nome);
    }




    /*@Override
    public void onMapReady(GoogleMap googleMap) {
       googleMap.setmar

    }*/
}
