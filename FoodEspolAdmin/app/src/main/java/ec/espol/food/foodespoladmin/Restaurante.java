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
import android.widget.ImageButton;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

import ec.espol.food.foodespoladmin.Controllers.RequestRestaurante;
import ec.espol.food.foodespoladmin.Model.RestauranteInfo;


public class Restaurante extends Fragment {
    private View view;

    private EditText nombreProp;
    private EditText nombre;
    private EditText capacidad;
    private TextView latitud;
    private TextView longitud;
    private RequestRestaurante request;
    private ImageButton btnEditarUbicacion;
    private RestauranteInfo restInfo;

    private int codeLocalization = 0;
    private int codeGalery = 1;
    private int codeCamera = 2;

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

        btnEditarUbicacion = (ImageButton)view.findViewById(R.id.btnEditarUbicacion);
        btnEditarUbicacion.setOnClickListener(eventEditUbicacion);


        return view;

    }

    public Observer cargarDatos = new Observer() {
        @Override
        public void update(Object objeto) {

            restInfo = (RestauranteInfo)objeto;

            nombreProp = (EditText)view.findViewById(R.id.editNombreProp);
            nombre = (EditText)view.findViewById(R.id.editNombreRestaurante);
            capacidad = (EditText)view.findViewById(R.id.editCapacidad);
            latitud = (TextView)view.findViewById(R.id.textLatitud);
            longitud = (TextView)view.findViewById(R.id.textLongitud);

            nombreProp.setText(restInfo.propietario);
            nombre.setText(restInfo.nombre);
            capacidad.setText(restInfo.capacidad);

            String strLatitud = String.format("Latitud: %f",restInfo.latitud );
            String strLongitud = String.format("Longitud: %f",restInfo.longitud );

            latitud.setText(strLatitud);
            longitud.setText(strLongitud);

        }
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == codeLocalization &&  null != data){

            Log.d("Imprimo", "estoy dentro del update");

            restInfo.latitud = data.getDoubleExtra("Latitud", 0);
            restInfo.longitud = data.getDoubleExtra("Longitud", 0);

            String strLatitud = String.format("Latitud: %f",restInfo.latitud );
            String strLongitud = String.format("Longitud: %f",restInfo.longitud );

            latitud.setText(strLatitud);
            longitud.setText(strLongitud);
        }

    }



    public View.OnClickListener eventEditUbicacion = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(getContext(), MapsActivity.class);

            intent.putExtra("Latitud",restInfo.latitud);
            intent.putExtra("Longitud", restInfo.longitud);

            startActivityForResult(intent, codeLocalization);
        }

    };


}
