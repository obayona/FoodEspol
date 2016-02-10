package ec.espol.food.foodespolcliente.Adapters;

/**
 * Created by oswaldoalejandro on 10/02/16.
 */


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
import ec.espol.food.foodespolcliente.Observer;
import ec.espol.food.foodespolcliente.PlatoView;
import ec.espol.food.foodespolcliente.R;
import ec.espol.food.foodespolcliente.Model.Plato;

/**
 * Created by jorge on 7/2/16.
 */
public class PlatosAdapter extends ArrayAdapter<Plato> {
    private ArrayList<Plato>platos;
    private Context context;
    private Observer observer;

    public PlatosAdapter(Context context, ArrayList<Plato> platos,Observer observer) {
        super(context,0,platos);
        this.platos = platos;
        this.context = context;
        this.observer=observer;
    }

    static class ViewHolder{
        public TextView nombre;
        public TextView precio;
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public PlatosAdapter setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
        return this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.plato_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nombre = (TextView)convertView.findViewById(R.id.txtNombrePlato);
            viewHolder.precio = (TextView)convertView.findViewById(R.id.txtprecioPlato);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        final Plato currentPlato = this.platos.get(position);
        holder.nombre.setText(currentPlato.getNombre());
        holder.precio.setText(Double.toString(currentPlato.getPrecio()));
        ImageButton informacion =(ImageButton)convertView.findViewById(R.id.btnInfoPlato);
        informacion.setFocusable(false);
        informacion.setClickable(false);
        observer=this.observer;
        informacion.setOnClickListener(new View.OnClickListener() {
            private Plato dataPlato = currentPlato;
            private Observer dataObserver = observer;

            @Override
            public void onClick(View v) {
                Log.i("Mensaje", "Se selecciona un plato");
                Intent intent = new Intent(context, PlatoView.class);

                intent.putExtra("id",currentPlato.getId());
                intent.putExtra("nombre",currentPlato.getNombre());
                intent.putExtra("foto",currentPlato.getPhotoPath());
                intent.putExtra("precio", currentPlato.getPrecio());
                intent.putExtra("path", currentPlato.getPhotoPath());
                intent.putExtra("catComidaRapida",currentPlato.catComidaRapida);
                intent.putExtra("catPiqueo",currentPlato.catPiqueo);
                intent.putExtra("catDesayuno",currentPlato.catDesayuno);
                intent.putExtra("catAlmuerzo",currentPlato.catAlmuerzo);
                intent.putExtra("idRestaurante", currentPlato.idRestaurante);

                context.startActivity(intent);

            }
        });

        return convertView;
    }

}


