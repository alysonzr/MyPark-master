package com.example.mypark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PracaAdapter extends ArrayAdapter<Praca> {

    private final Context context;
    private final ArrayList<Praca> elementos;

    public PracaAdapter(Context context, ArrayList<Praca> elementos){
        super(context, R.layout.activity_lista_custom,elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public View getView (int position, View convertView , ViewGroup parent ) {
        LayoutInflater inflater = ( LayoutInflater ) context . getSystemService ( Context. LAYOUT_INFLATER_SERVICE );
        View rowView = inflater.inflate ( R.layout.activity_lista_custom , parent , false );
        TextView nomePraca = ( TextView ) rowView.findViewById (R.id.nome );
        TextView instalacoes = ( TextView ) rowView.findViewById ( R.id.instalacoes);

        nomePraca.setText ("Nome: "+elementos.get(position).getNome ());
        instalacoes.setText ("Instações: "+elementos.get (position).getFacilidades ());
        //imagem.setImageResource ( elementos .get (position). getImagem ());

        return rowView ;
    }
}
