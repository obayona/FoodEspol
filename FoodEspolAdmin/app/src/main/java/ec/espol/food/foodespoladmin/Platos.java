package ec.espol.food.foodespoladmin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Adapters.PlatosAdapter;
import ec.espol.food.foodespoladmin.Controllers.RequestPlatos;
import ec.espol.food.foodespoladmin.Model.*;

public class Platos extends Fragment implements Observer {

    private View view;
    private ArrayList<Plato> platos=new ArrayList<Plato>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_platos, container, false);
        Log.i("Mensaje", "Actividad Platos Creada");

        Button btNuevoPlato = (Button)view.findViewById(R.id.btnNuevoPlato);
        btNuevoPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NuevoPlato.class);
                startActivity(intent);

            }
        });
        RequestPlatos requestPlatos =new RequestPlatos(getContext(),this);
        requestPlatos.getPlatos();
        return view;

    }

    @Override
    public void update(Object objeto ){
        JSONArray platosRecibidos;
        platosRecibidos=(JSONArray) objeto;
        Log.i("Mensaje", "los platos son "+platosRecibidos);
        int id;
        String nombre;
        double precio;
        String photoPath;
        Plato plato;
        ListView elementsPlatos = (ListView) view.findViewById(R.id.listPlatos);
        PlatosAdapter adapterPlatos = new PlatosAdapter(getContext(), platos);
        elementsPlatos.setAdapter(adapterPlatos);
        elementsPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Mensaje", "Se debe abrir MenuView");
                Intent intent = new Intent(getContext(), NuevoPlato.class);
                //Bundle data = new Bundle();
                //MenuView menu = adapter.getMenus().get(position);
                //data.putSerializable("menu", menu);
                //intent.putExtras(data);
                startActivity(intent);
            }
        });

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
