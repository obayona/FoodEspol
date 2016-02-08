package ec.espol.food.foodespoladmin;

import ec.espol.food.foodespoladmin.Controllers.RequestNuevoPlato;
import ec.espol.food.foodespoladmin.Model.Plato;
import java.util.HashMap;
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
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
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
                HashMap<String, String> map = p.getHashMap();
                Log.d("ensaje", "*****estoy mandando el plato");
                RequestNuevoPlato request = new RequestNuevoPlato(getApplicationContext(), null);
                request.guardarPlato(map);
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
        double precio = Double.parseDouble(precioText.getText().toString());

        Plato p = new Plato(-1,nombre, precio, "fotoPath");
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
            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(picturePath);
            Bitmap resizeImage = getResizedBitmap(image, 100,200);

            imageView.setImageBitmap(resizeImage);

        }
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && null !=data ){

            Bitmap imagen = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
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



}
