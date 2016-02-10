package ec.espol.food.foodespoladmin.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.MenuView;
import ec.espol.food.foodespoladmin.Model.Plato;
import ec.espol.food.foodespoladmin.Observer;
import ec.espol.food.foodespoladmin.R;

/**
 * Created by jorge on 9/2/16.
 */
public class PlatoMenuAdapter extends ArrayAdapter<Plato> {
    private ArrayList<Plato> platos;
    private Context context;
    private Observer observer;


    public PlatoMenuAdapter(Context context, ArrayList<Plato> platos,Observer observer) {
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

    public PlatoMenuAdapter setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
        return this;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.plato_menu_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nombre = (TextView)convertView.findViewById(R.id.txtNombrePlato);
            viewHolder.precio = (TextView)convertView.findViewById(R.id.txtprecioPlato);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        final Plato currentPlato = this.platos.get(position);
        holder.nombre.setText(currentPlato.getNombre());
        holder.precio.setText(Double.toString(currentPlato.getPrecio()));
        return convertView;
    }
}
