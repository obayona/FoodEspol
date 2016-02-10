package ec.espol.food.foodespolcliente.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.io.Serializable;

import java.util.ArrayList;

import ec.espol.food.foodespolcliente.Controllers.RequestPlatos;
import ec.espol.food.foodespolcliente.Model.RestauranteInfo;
import ec.espol.food.foodespolcliente.Observer;
import ec.espol.food.foodespolcliente.PlatoView;
import ec.espol.food.foodespolcliente.R;
import ec.espol.food.foodespolcliente.Model.Plato;
import ec.espol.food.foodespolcliente.Restaurantes;

/**
 * Created by jorge on 7/2/16.
 */
public class RestauranteAdapter extends ArrayAdapter<RestauranteInfo> {
    private ArrayList<RestauranteInfo>restaurantes;
    private Context context;
    private Observer observer;

    public RestauranteAdapter(Context context, ArrayList<RestauranteInfo> restaurantes,Observer observer) {
        super(context,0,restaurantes);
        this.restaurantes = restaurantes;
        this.context = context;
        this.observer=observer;
    }

    static class ViewHolder{
        public TextView nombre;
        public TextView capacidad;
        public TextView aproximado;

    }

    public ArrayList<RestauranteInfo> getRestaurantes() {
        return restaurantes;
    }

    public RestauranteAdapter setPlatos(ArrayList<RestauranteInfo> restaurantes) {
        this.restaurantes = restaurantes;
        return this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.restaurante_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nombre = (TextView)convertView.findViewById(R.id.txtNombreRestaurante);
            viewHolder.capacidad = (TextView)convertView.findViewById(R.id.txtCapacidad);
            viewHolder.aproximado = (TextView)convertView.findViewById(R.id.txtAproximado);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        final RestauranteInfo currentRestaurante = this.restaurantes.get(position);
        holder.nombre.setText(currentRestaurante.getNombre());
        holder.capacidad.setText(currentRestaurante.getCapacidad());
        holder.aproximado.setText(currentRestaurante.getAproximado());
        ImageButton informacion =(ImageButton)convertView.findViewById(R.id.btnInfoRestaurante);
        ImageButton menuRestaurante =(ImageButton)convertView.findViewById(R.id.btnMenuRestaurante);
        informacion.setFocusable(false);
        informacion.setClickable(false);
        menuRestaurante.setFocusable(false);
        menuRestaurante.setClickable(false);
        observer=this.observer;
        Log.i("Mensaje", "adaptador");
        informacion.setOnClickListener(new View.OnClickListener() {
            private RestauranteInfo dataRestaurante = currentRestaurante;
            private Observer dataObserver = observer;

            @Override
            public void onClick(View v) {
                Log.i("Mensaje", "Informacion Restaurante");

            }
        });

        menuRestaurante.setOnClickListener(new View.OnClickListener() {
            private RestauranteInfo dataRestaurante = currentRestaurante;
            private Observer dataObserver = observer;

            @Override
            public void onClick(View v) {
                Log.i("Mensaje", "Informacion Restaurante");

            }
        });
        return convertView;
    }

}



