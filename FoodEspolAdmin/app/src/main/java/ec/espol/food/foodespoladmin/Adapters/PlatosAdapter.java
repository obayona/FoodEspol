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

import ec.espol.food.foodespoladmin.Controllers.RequestPlatos;
import ec.espol.food.foodespoladmin.Observer;
import ec.espol.food.foodespoladmin.R;
import ec.espol.food.foodespoladmin.Model.Plato;

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

    public PlatosAdapter setReminders(ArrayList<Plato> platos) {
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
        ImageButton delete =(ImageButton)convertView.findViewById(R.id.btnEliminarPlato);
        delete.setFocusable(false);
        delete.setClickable(false);
        observer=this.observer;
        delete.setOnClickListener(new View.OnClickListener() {
            private Plato dataPlato=currentPlato;
            private Observer dataObserver=observer;
            @Override
            public void onClick(View v) {
                Log.i("Mensaje", "Se quiere eliminar un plato");
                RequestPlatos requestPlatos = new RequestPlatos(getContext(),dataObserver);
                requestPlatos.eliminarPlato(dataPlato.getId());
            }
        });
        return convertView;
    }

}
