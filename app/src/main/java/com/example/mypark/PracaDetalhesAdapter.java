package com.example.mypark;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PracaDetalhesAdapter extends ArrayAdapter<Praca >{

    private final Context context;
    private final ArrayList<Praca> elementos;

    public PracaDetalhesAdapter(Context context, ArrayList<Praca> elementos) {
        super(context, R.layout.activity_detalhes,elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_detalhes,parent , false );

        //ImageView IconInstalacoes = ( ImageView )rowView.findViewById ( R.id.iconInstalacoes);
        TextView intalacao = (TextView)rowView.findViewById(R.id.nomeInstalacoes);

       // IconInstalacoes.setImageURI(Uri.parse(elementos.get (position).getIcon ()));
        intalacao.setText(elementos.get(position).getFacilidades());

        return rowView ;
    }
}
