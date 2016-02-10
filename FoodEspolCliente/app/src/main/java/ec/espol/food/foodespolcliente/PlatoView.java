package ec.espol.food.foodespolcliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PlatoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_view);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String precio = intent.getStringExtra("precio");
        TextView txtNombre =(TextView) findViewById(R.id.txtNombre);
        txtNombre.setText(nombre);
        TextView txtPrecio =(TextView) findViewById(R.id.txtPrecio);
        txtPrecio.setText(precio);
    }
}
