package ec.espol.food.foodespolcliente;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespolcliente.Adapters.PlatosAdapter;
import ec.espol.food.foodespolcliente.Adapters.RestauranteAdapter;
import ec.espol.food.foodespolcliente.Controllers.RequestRestaurantes;
import ec.espol.food.foodespolcliente.Model.Plato;
import ec.espol.food.foodespolcliente.Model.RestauranteInfo;


public class Restaurantes extends Fragment implements Observer {
    private View view;
    private ArrayList<RestauranteInfo> restaurantes=new ArrayList<RestauranteInfo> ();;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Mensaje", "Actividad Menus Creada");
        view=inflater.inflate(R.layout.fragment_restaurantes, container, false);
        RequestRestaurantes requestRestaurantes = new RequestRestaurantes(getContext(),this);
        requestRestaurantes.getRestaurantes();
        return view;

    }

    @Override
    public void update(Object objeto){
        JSONArray restaurantesRecibidos;
        restaurantesRecibidos=(JSONArray)objeto;
        Log.i("Mensaje", "los restaurantes en la vista "+restaurantesRecibidos);
        final ListView elementsRestaurantes = (ListView) view.findViewById(R.id.listRestaurantes);
        final RestauranteAdapter adapter = new RestauranteAdapter(getContext(), restaurantes,this);
        elementsRestaurantes.setAdapter(adapter);
        restaurantes.clear();
        for (int i = 0; i < restaurantesRecibidos.length(); i++) {
            try {
                Log.i("Mensaje", "Agregador "+i);
                JSONObject row = restaurantesRecibidos.getJSONObject(i);
                String propietario=row.getJSONObject("administrador").getString("nombre");
                String nombre=row.getString("nombre");
                String capacidad=row.getString("capacidad");
                double latitud=row.getDouble("latitud");
                double longitud=row.getDouble("longitud");
                String logo=row.getString("logo");
                String aproximado=row.getString("numClientes");
                RestauranteInfo restauranteInfo=new RestauranteInfo(propietario,nombre,capacidad,latitud,longitud,logo,aproximado);
                restaurantes.add(restauranteInfo);
            }catch (JSONException e){

            }

        }

    }

}