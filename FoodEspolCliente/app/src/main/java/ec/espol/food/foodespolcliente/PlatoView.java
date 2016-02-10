package ec.espol.food.foodespolcliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.view.Gravity;

import android.util.Log;

import ec.espol.food.foodespolcliente.Controllers.Constants;

import com.android.volley.toolbox.ImageRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

public class PlatoView extends AppCompatActivity {

    ImageView imagePlato;
    int idPlato;
    Constants cons;
    String pathImage;

    int catComidaRapida;
    int catPiqueo;
    int catDesayuno;
    int catAlmuerzo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_view);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        Double precio = intent.getDoubleExtra("precio", 0);
        idPlato = intent.getIntExtra("id", 0);
        pathImage = intent.getStringExtra("path");
        catComidaRapida = intent.getIntExtra("catComidaRapida", 0);
        catPiqueo = intent.getIntExtra("catPiqueo", 0);
        catDesayuno = intent.getIntExtra("catDesayuno", 0);
        catAlmuerzo = intent.getIntExtra("catAlmuerzo",0);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cons = new Constants();
        TextView txtNombre =(TextView) findViewById(R.id.txtNombre);
        txtNombre.setText(nombre);
        TextView txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        txtPrecio.setText(new String("$") + Double.toString(precio) );
        imagePlato = (ImageView)findViewById(R.id.imagePlato);

        //cargar categorias
        LinearLayout contenedor = (LinearLayout)findViewById(R.id.contenedorPlato);

        if(catComidaRapida == 1){
            TextView text = new TextView(this);
            text.setText("Comida Rapida");
            text.setGravity(Gravity.CENTER);
            contenedor.addView(text);
        }
        if(catPiqueo == 1){
            TextView text = new TextView(this);
            text.setText("Piqueo");
            text.setGravity(Gravity.CENTER);
            contenedor.addView(text);
        }
        if(catDesayuno == 1){
            TextView text = new TextView(this);
            text.setText("Desayuno");
            text.setGravity(Gravity.CENTER);
            contenedor.addView(text);
        }
        if(catAlmuerzo == 1){
            TextView text = new TextView(this);
            text.setText("Almuerzo");
            text.setGravity(Gravity.CENTER);
            contenedor.addView(text);
        }

        cargarImagen();



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

    public void cargarImagen(){
        String ip = cons.ip;
        String url = ip + pathImage;

        Log.d("*****url", url);

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ImageView imgView = (ImageView)findViewById(R.id.imagePlato);
                        Bitmap resizeImage = getResizedBitmap(bitmap, 512, 512);
                        imgView.setImageBitmap(resizeImage);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        ImageView imgView = (ImageView)findViewById(R.id.imagePlato);
                        imgView.setImageResource(R.drawable.food);
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


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


}
