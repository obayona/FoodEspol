package ec.espol.food.foodespoladmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Adapters.PlatoMenuAdapter;
import ec.espol.food.foodespoladmin.Controllers.Constants;
import ec.espol.food.foodespoladmin.Controllers.RequestPlatos;
import ec.espol.food.foodespoladmin.Model.Menu;
import ec.espol.food.foodespoladmin.Model.Plato;

public class escogerPlatosMenu extends AppCompatActivity implements Observer {
    private ArrayList<Plato> platos=new ArrayList<Plato>();
    private Constants c=new Constants();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_platos_menu);
        RequestPlatos requestPlatos = new RequestPlatos(this,this);
        requestPlatos.getPlatos();
        c.platosSlected=new ArrayList<Plato>();
        ImageButton botonAgregar =(ImageButton)findViewById(R.id.btnAgregarPatosMenu);
        botonAgregar.setOnClickListener( clickGuardar);

    }

    @Override
    public void update(Object objeto ){
        JSONArray platosRecibidos;
        platosRecibidos=(JSONArray) objeto;
        Log.i("Mensaje", "los platos son " + platosRecibidos);
        int id;
        String nombre;
        double precio;
        String photoPath;
        Plato plato;
        ListView elementsPlatos = (ListView) findViewById(R.id.listPlatos);
        final PlatoMenuAdapter adapterPlatoMenu = new PlatoMenuAdapter(this,platos,this);
        elementsPlatos.setAdapter(adapterPlatoMenu);
        elementsPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Mensaje", "Se selecciono un plato");
                TextView row =(TextView)view.findViewById(R.id.txtprueba);
                row.setText("Seleccionado");
                Plato p=adapterPlatoMenu.getPlatos().get(position);
                c.platosSlected.add(p);

            }
        });

        this.platos.clear();
        for (int i = 0; i < platosRecibidos.length(); i++) {
            try {
                JSONObject row = platosRecibidos.getJSONObject(i);
                id = row.getInt("id");
                nombre = row.getString("nombre");
                precio=row.getDouble("precio");
                photoPath=row.getString("foto");
                plato=new Plato(id,nombre,precio,photoPath);
                this.platos.add(plato);
            }catch (JSONException e){

            }

        }

    }

    public View.OnClickListener clickGuardar = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Log.d("Imprimo", "estoy llamando al observae");

            Intent intent = new Intent();
            //Bundle data = new Bundle();
            //data.putSerializable("Platos",platosSlected);
            //intent.putExtras(data);
            setResult(0, intent);
            finish();

        }
    };
}
