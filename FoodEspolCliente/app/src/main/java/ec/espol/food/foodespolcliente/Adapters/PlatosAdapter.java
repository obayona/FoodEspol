package ec.espol.food.foodespolcliente.Adapters;

/**
 * Created by oswaldoalejandro on 10/02/16.
 */


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ec.espol.food.foodespolcliente.Controllers.RequestPlatos;
import ec.espol.food.foodespolcliente.Observer;
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

        observer=this.observer;

        return convertView;
    }

}


