package ec.espol.food.foodespoladmin;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

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
    private ImageButton btnRestGaleria;
    private ImageButton btnRestCamara;
    private ImageButton btnRestGuardar;

    private RequestRestaurante request;
    private ImageButton btnEditarUbicacion;
    private RestauranteInfo restInfo;
    private Constants cons;

    private int codeLocalization = 0;
    private int codeGalery = 1;
    private int codeCamera = 2;
    public File imageFile = null;


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

        btnRestGaleria =  (ImageButton)view.findViewById(R.id.btnRestGaleria);
        btnRestGaleria.setOnClickListener(eventGaleria);

        btnRestCamara = (ImageButton)view.findViewById(R.id.btnRestCamara);
        btnRestCamara.setOnClickListener(eventCamera);

        btnRestGuardar = (ImageButton)view.findViewById(R.id.btnRestGuardar);
        btnRestGuardar.setOnClickListener(eventGuardar);

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
                        Bitmap resizeImage = getResizedBitmap(bitmap, 256, 256);
                        imgView.setImageBitmap(resizeImage);
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


        if (requestCode == codeGalery && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            imageFile = new File(picturePath); //copio el path

            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(picturePath);
            Bitmap resizeImage = getResizedBitmap(image, 256, 256);

            ImageView imgView = (ImageView)view.findViewById(R.id.imageLogoRestaurante);
            imgView.setImageBitmap(resizeImage);

        }

        if(requestCode == codeCamera && resultCode == Activity.RESULT_OK && null !=data ){

            Bitmap imagen = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            //String picturePath =
            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");

            imageFile = destination; //copio el path

            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap resizeImage = getResizedBitmap(imagen, 256,256);

            ImageView imgView = (ImageView)view.findViewById(R.id.imageLogoRestaurante);
            imgView.setImageBitmap(resizeImage);

        }


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


    public View.OnClickListener eventEditUbicacion = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(getContext(), MapsActivity.class);

            intent.putExtra("Latitud", restInfo.latitud);
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

    public View.OnClickListener eventGaleria = new View.OnClickListener(){
      @Override
        public void onClick(View view){
          Intent i = new Intent(
                  Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

          startActivityForResult(i, codeGalery);


      }
    };

    public View.OnClickListener eventCamera = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, codeCamera);
        }
    };

    public View.OnClickListener eventGuardar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            nombreProp = (EditText)view.findViewById(R.id.editNombreProp);
            nombre = (EditText)view.findViewById(R.id.editNombreRestaurante);
            capacidad = (EditText)view.findViewById(R.id.editCapacidad);

            //validar Datos
            String nr = nombre.getText().toString();
            String np = nombreProp.getText().toString();
            String capText = capacidad.getText().toString();

            int cap = 0;
            try {
                cap = Integer.parseInt(capText);
            }
            catch( NumberFormatException e){
                Toast.makeText(getContext(), "Error en la capacidad", Toast.LENGTH_LONG).show();
                return;
            }
            if(nr.equals("")) {
                Toast.makeText(getContext(), "Error el nombre del restaurante", Toast.LENGTH_LONG).show();
                return;
            }
            if(np.equals("")){
                Toast.makeText(getContext(), "Error en el propietario", Toast.LENGTH_LONG).show();
                return;
            }

            restInfo.capacidad = capText;
            restInfo.nombre = nr;
            restInfo.propietario = np;

            if(imageFile!=null){
                restInfo.bandPath = 1; //cambio el path del logo
            }

            nombreProp.setEnabled(false);
            nombre.setEnabled(false);
            capacidad.setEnabled(false);
            btnEditarRestaurante.setImageResource(R.drawable.ic_action_lock_closed);


            HashMap<String,String> map = restInfo.getHasJson();
            request.guardarRestaurante(map);

            Log.d("Mensaje", "****Voy a mandar los datos");



        }
    };


}
