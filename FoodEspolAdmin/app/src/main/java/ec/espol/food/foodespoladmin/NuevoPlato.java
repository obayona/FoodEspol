package ec.espol.food.foodespoladmin;

import ec.espol.food.foodespoladmin.Controllers.RequestNuevoPlato;
import ec.espol.food.foodespoladmin.Model.Plato;
import java.util.HashMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import 	android.support.v4.app.NavUtils;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class NuevoPlato extends AppCompatActivity {

    public Button btnGuardar;


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
            }
        });

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


}
