package ec.espol.food.foodespolcliente;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespolcliente.Model.Plato;
import ec.espol.food.foodespolcliente.Controllers.RequestPlatos;
import ec.espol.food.foodespolcliente.Adapters.PlatosAdapter;


public class Platos extends Fragment implements Observer {
    private View view;
    private ArrayList<Plato> platos=new ArrayList<Plato>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Mensaje", "Actividad Menus Creada");
        view=inflater.inflate(R.layout.fragment_platos, container, false);

        RequestPlatos requestPlatos =new RequestPlatos(getContext(),this);
        requestPlatos.getPlatos();

        return view;

    }

    @Override
    public void update(Object objeto){
        JSONArray platosRecibidos;
        platosRecibidos=(JSONArray)objeto;
        Log.i("Mensaje", "los platos son "+platosRecibidos);
        int id;
        String nombre;
        double precio;
        String photoPath;
        Plato plato;
        ListView elementsPlatos = (ListView) view.findViewById(R.id.listPlatos);
        PlatosAdapter adapterPlatos = new PlatosAdapter(getContext(), platos,this);
        elementsPlatos.setAdapter(adapterPlatos);


        this.platos.clear();
        for (int i = 0; i < platosRecibidos.length(); i++) {
            try {
                JSONObject row = platosRecibidos.getJSONObject(i);
                id = row.getInt("id");
                nombre = row.getString("nombre");
                precio=row.getDouble("precio");
                photoPath=row.getString("foto");
                plato=new Plato(id,nombre,precio,photoPath);
                this.platos.add(plato);
            }catch (JSONException e){

            }

        }

    }

}
