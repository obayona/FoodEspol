package ec.espol.food.foodespolcliente;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Dialog;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.DatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespolcliente.Adapters.PlatoAdapter;
import ec.espol.food.foodespolcliente.Controllers.Constants;
import ec.espol.food.foodespolcliente.Controllers.RequestMenu;
import ec.espol.food.foodespolcliente.Model.Menu;
import ec.espol.food.foodespolcliente.Model.Plato;

public class MenuView extends AppCompatActivity implements  Observer{
    private Menu menu;
    private ArrayList<Plato> platos=new ArrayList<Plato>() ;
    private TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data=getIntent().getExtras();
        RequestMenu requesMenu = new RequestMenu(this,this);
        requesMenu.getPlatosMenu(1);
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

    @Override
    public void update(Object objeto ){
        JSONArray platosRecibidos;
        platosRecibidos=(JSONArray) objeto;
        int id;
        String nombre;
        double precio;
        String photoPath;
        Plato plato;
        final ListView elementsPlatos = (ListView) findViewById(R.id.listPlatoMenu);
        final PlatoAdapter adapterPlatos = new PlatoAdapter(this, platos,this,1);
        elementsPlatos.setAdapter(adapterPlatos);

        this.platos.clear();
        for (int i = 0; i < platosRecibidos.length(); i++) {
            try {
                JSONObject row = platosRecibidos.getJSONObject(i);
                id = row.getInt("id");
                nombre = row.getString("nombre");
                precio=row.getDouble("precio");
                photoPath=row.getString("foto");
                plato=new Plato(id,nombre,precio,photoPath,1);
                this.platos.add(plato);
            }catch (JSONException e){

            }

        }

    }



}
