package ec.espol.food.foodespoladmin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

import ec.espol.food.foodespoladmin.Controllers.RequestRestaurante;
import ec.espol.food.foodespoladmin.Model.RestauranteInfo;


public class Restaurante extends Fragment {
    private View view;

    private EditText nombreProp;
    private EditText nombre;
    private EditText capacidad;
    private RequestRestaurante request;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.i("Mensaje", "Actividad Restaurante Creada");
        view= inflater.inflate(R.layout.activity_restaurante, container, false);

        request = new RequestRestaurante(getContext(), cargarDatos);
        request.getRestaurante();


        return view;

    }

    Observer cargarDatos = new Observer() {
        @Override
        public void update(Object objeto) {

            RestauranteInfo rest = (RestauranteInfo)objeto;

            nombreProp = (EditText)view.findViewById(R.id.editNombreProp);
            nombre = (EditText)view.findViewById(R.id.editNombreRestaurante);
            capacidad = (EditText)view.findViewById(R.id.editCapacidad);

            nombreProp.setText(rest.propietario);
            nombre.setText(rest.nombre);
            capacidad.setText(rest.capacidad);

        }
    };


}
