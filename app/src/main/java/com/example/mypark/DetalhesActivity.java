package com.example.mypark;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.loopj.android.image.SmartImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class DetalhesActivity extends AppCompatActivity {


    MapView mapView;

    String[] img = new String[]{"https://firebasestorage.googleapis.com/v0/b/mypark-master-9cad5.appspot.com/o/Parcao%20gravatai%2F02.png?alt=media&token=56005fa5-486f-4a87-8be7-99773a5b5165",
            "https://firebasestorage.googleapis.com/v0/b/mypark-master-9cad5.appspot.com/o/Parcao%20gravatai%2F05.png?alt=media&token=8ad6c8b4-d792-45d3-85b1-9b2bfa5ba425"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        ViewPager viewPager = findViewById(R.id.view_pager);


        ListView listaDetalhes = (ListView)findViewById(R.id.listDetalhes);
        MapView mapa = (MapView) findViewById(R.id.mapView);
        TextView txtEndereco = (TextView)findViewById(R.id.txtEndereco);
       // ViewPager imagemTelaDetalhes = (ViewPager) findViewById(R.id.view_pager);

        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("nome");
        String endereco = extras.getString("endereco");
        ArrayList imagem = ((ArrayList) extras.get("imagem"));

        ViewPagerAdapter adapterPager = new ViewPagerAdapter(this,img);
        viewPager.setAdapter(adapterPager);

   // Picasso.with(listaDetalhes.getContext()).load(imagem.toString()).into(imagemTelaDetalhes);















       /* Intent i = getIntent();
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
        }*/
//            mMap.addMarker(new MarkerOptions().position(praca1).title("Praça Dom Feliciano - Centro Histórico"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca1));
//
//            LatLng praca2 = new LatLng(-29.935992,  -51.021787);
//            mMap.addMarker(new MarkerOptions().position(praca2).title("Praça Largo da Quintino "));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(praca2));
        }

    public void CarregarImagem(View v) {

        /*
         *Picasso.with(Aqui vem o contexto).load(URL da imagem).into(ImageView responsável pelo render);
         *
         * */
    }


    //For mapView
   /* @Override
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
*/

}
