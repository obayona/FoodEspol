package ec.espol.food.foodespoladmin;

import ec.espol.food.foodespoladmin.Controllers.RequestNuevoPlato;
import ec.espol.food.foodespoladmin.Model.CategoriaEnum;
import ec.espol.food.foodespoladmin.Model.Plato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.database.Cursor;
import android.widget.ImageView;
import android.widget.Switch;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


public class NuevoPlato extends AppCompatActivity {

    public Button btnGuardar;
    public Button btnPlatoGaleria;
    public Button btnPlatoCamara;
    public int RESULT_LOAD_IMAGE = 1;
    public int REQUEST_CAMERA = 2;
    public boolean selectCategory = false;
    public boolean selectedImage = false;
    public File imageFile;
    private RequestNuevoPlato upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGuardar = (Button)findViewById(R.id.btnGuardarPlato);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plato p = createPlato();
                if (p!=null) {
                    HashMap<String,List< String> > map = p.getHashMap();
                    Log.d("ensaje", "*****estoy mandando el plato");
                    upload = new RequestNuevoPlato(getApplicationContext(), datosPlato );

                    try {
                        Log.d("ensaje", "*****llamando a upload");
                        upload.uploadPlato(imageFile, map);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        btnPlatoGaleria = (Button)findViewById(R.id.btnPlatoGaleria);
        btnPlatoGaleria.setOnClickListener(eventLoadImage);

        btnPlatoCamara = (Button)findViewById(R.id.btnPlatoCamara);
        btnPlatoCamara.setOnClickListener(eventTakeImage);


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

    public Plato createPlato(){
        EditText nombreText = (EditText)findViewById(R.id.editNombrePlato);
        EditText precioText = (EditText)findViewById(R.id.editPrecioPlato);
        String nombre = nombreText.getText().toString();

        //validar datos
        double precio = 0;
        try {
            precio = Double.parseDouble(precioText.getText().toString());
        }
        catch( NumberFormatException e){
            Toast.makeText(this.getApplicationContext(), "error en el precio", Toast.LENGTH_LONG).show();
            return null;
        }

        if (nombre.equals("")){
            Toast.makeText(this.getApplicationContext(), "debe ingresar un nombre", Toast.LENGTH_LONG).show();
            return null;
        }

        //validar categorias:
        Switch switchPiqueo = (Switch)findViewById(R.id.switchPiqueo);
        Switch switchComidaRapida = (Switch)findViewById(R.id.switchComidaRapida);
        Switch switchDesayuno = (Switch)findViewById(R.id.switchDesayuno);
        Switch switchAlmuerzo = (Switch)findViewById(R.id.switchAlmuerzo);

        ArrayList<CategoriaEnum> categorias = new ArrayList<CategoriaEnum>();

        Plato p = new Plato(-1,nombre, precio, "fotoPath");

        if(switchComidaRapida.isChecked()){
            selectCategory = true;
            p.catComidaRapida =1;
        }
        if(switchPiqueo.isChecked()){
            selectCategory = true;
            p.catPiqueo = 1;
        }
        if(switchDesayuno.isChecked()){
            selectCategory = true;
            p.catDesayuno = 1;
        }
        if(switchAlmuerzo.isChecked()){
            selectCategory = true;
            p.catAlmuerzo = 1;
        }

        if(!selectCategory){
            Toast.makeText(this.getApplicationContext(), "debe elegir al menos una categoria", Toast.LENGTH_LONG).show();
            return null;
        }

        return p;

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView imageView = (ImageView) findViewById(R.id.imagePlato);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            imageFile = new File(picturePath); //copio el path

            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(picturePath);
            Bitmap resizeImage = getResizedBitmap(image, 100,200);

            imageView.setImageBitmap(resizeImage);

        }
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && null !=data ){

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
            Bitmap resizeImage = getResizedBitmap(imagen, 100,200);

            imageView.setImageBitmap(resizeImage);

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

    public View.OnClickListener eventLoadImage = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent i = new Intent(
                    Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }

    };

    public View.OnClickListener eventTakeImage = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);

        }
    };

    public Observer datosPlato = new Observer(){
        @Override
        public void update(Object object){

            finish();
        }
    };



}
