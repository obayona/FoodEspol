package ec.espol.food.foodespoladmin;

import android.app.Activity;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

import ec.espol.food.foodespoladmin.Controllers.Constants;
import ec.espol.food.foodespoladmin.Controllers.RequestRestaurante;
import ec.espol.food.foodespoladmin.Model.RestauranteInfo;


public class Restaurante extends Fragment {
    private View view;

    private EditText nombreProp;
    private EditText nombre;
    private EditText capacidad;
    private TextView latitud;
    private TextView longitud;
    private ImageButton btnEditarRestaurante;
    private RequestRestaurante request;
    private ImageButton btnEditarUbicacion;
    private RestauranteInfo restInfo;
    private Constants cons;

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

        cons = new Constants();
        btnEditarUbicacion = (ImageButton)view.findViewById(R.id.btnEditarUbicacion);
        btnEditarUbicacion.setOnClickListener(eventEditUbicacion);

        ImageView imgView = (ImageView)view.findViewById(R.id.imageLogoRestaurante);

        btnEditarRestaurante = (ImageButton)view.findViewById(R.id.btnEditarRestaurante);
        btnEditarRestaurante.setOnClickListener(eventiEditTexto);

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


           cargarImagen(restInfo.logo);

        }
    };


    public void cargarImagen(String pathImage){

        String ip = cons.ip;
        String url = ip  + pathImage;

        Log.d("*****url", url);

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ImageView imgView = (ImageView)view.findViewById(R.id.imageLogoRestaurante);
                        imgView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        ImageView imgView = (ImageView)view.findViewById(R.id.imageLogoRestaurante);
                        imgView.setImageResource(R.drawable.food);
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }



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

    public View.OnClickListener eventiEditTexto = new View.OnClickListener(){
        @Override
        public void onClick(View view){

            nombreProp.setEnabled(true);
            nombre.setEnabled(true);
            capacidad.setEnabled(true);

            btnEditarRestaurante.setImageResource(R.drawable.ic_action_lock_open);

        }

    };


}
