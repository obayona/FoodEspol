package ec.espol.food.foodespolcliente;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;


import ec.espol.food.foodespolcliente.Controllers.Constants;
import ec.espol.food.foodespolcliente.Controllers.RequestRestaurante;
import ec.espol.food.foodespolcliente.Model.RestauranteInfo;
import ec.espol.food.foodespolcliente.MapsActivity;


public class RestauranteVista extends AppCompatActivity {
    private TextView nombreProp;
    private TextView nombre;
    private TextView capacidad;
    private TextView latitud;
    private TextView longitud;
    private ImageButton btnUbicacion;

    private RestauranteInfo restInfo;
    private RequestRestaurante request;
    private Constants cons;

    private int codeLocalization = 0;
    private  int idRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante_vista);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cons = new Constants();
        Intent intent = getIntent();
        idRestaurante = intent.getIntExtra("idRestaurante", 0);


        request = new RequestRestaurante(this, cargarDatos);
        request.getRestaurante(idRestaurante);

        btnUbicacion = (ImageButton) findViewById(R.id.btnUbicacion);
        btnUbicacion.setOnClickListener(eventUbicacion);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }


    public void cargarImagen(String pathImage){

        String ip = cons.ip;
        String url = ip  + pathImage;

        Log.d("*****url", url);

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ImageView imgView = (ImageView)findViewById(R.id.imageLogoRestaurante);
                        Bitmap resizeImage = getResizedBitmap(bitmap, 256, 256);
                        imgView.setImageBitmap(resizeImage);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        ImageView imgView = (ImageView)findViewById(R.id.imageLogoRestaurante);
                        imgView.setImageResource(R.drawable.food);
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }


    public View.OnClickListener eventUbicacion = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(RestauranteVista.this, MapsActivity.class);

            intent.putExtra("Latitud", restInfo.latitud);
            intent.putExtra("Longitud", restInfo.longitud);

            startActivity(intent);
        }

    };

    public Observer cargarDatos = new Observer() {
        @Override
        public void update(Object objeto) {

            restInfo = (RestauranteInfo)objeto;

            nombreProp = (TextView) findViewById(R.id.NombreProp);
            nombre = (TextView)findViewById(R.id.NombreRestaurante);
            capacidad = (TextView)findViewById(R.id.Capacidad);
            latitud = (TextView)findViewById(R.id.textLatitud);
            longitud = (TextView)findViewById(R.id.textLongitud);

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

}