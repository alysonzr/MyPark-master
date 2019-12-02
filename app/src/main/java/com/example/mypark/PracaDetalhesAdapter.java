package com.example.mypark;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PracaDetalhesAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final String[] elementos;

        public PracaDetalhesAdapter(Context context, String[] elementos) {
            super(context,  R.layout.activity_lista_instalacoes_custom, elementos);
            this.context = (Context) context;
            this.elementos = elementos;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View lisitem = convertView;
            if(lisitem == null){
                lisitem = LayoutInflater.from(context).inflate(R.layout.activity_lista_instalacoes_custom,parent,false);

            }
        for (int i = 0; i<elementos.length; i++){
            TextView Instalacoes = ( TextView )lisitem.findViewById (R.id.nomeInstalacoes );
            //  ImageView imageView = (ImageView)convertView.findViewById(R.id.iconInstalacoes);
            Instalacoes.setText(i);
        }


        return lisitem;
    }
}
