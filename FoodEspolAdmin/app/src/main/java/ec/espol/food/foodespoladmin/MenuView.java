package ec.espol.food.foodespoladmin;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Adapters.PlatoAdapter;
import ec.espol.food.foodespoladmin.Controllers.RequestMenu;
import ec.espol.food.foodespoladmin.Model.Menu;
import ec.espol.food.foodespoladmin.Model.Plato;

public class MenuView extends AppCompatActivity implements  Observer{
    private Menu menu;
    private ArrayList<Plato> platos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data=getIntent().getExtras();
        TextView titulo = (TextView) findViewById(R.id.txTitleMenu);
        platos= new ArrayList<Plato>();
        if(data!=null) {
            titulo.setText("Menu");
            menu = (Menu) data.getSerializable("menu");
            Log.i("Mensaje", "El Menu Seleccionado es " + menu.getId());
            TextView fecha = (TextView) findViewById(R.id.txtFechaMenu);
            fecha.setText(menu.getFecha());
            RequestMenu requestMenu = new RequestMenu(getBaseContext(),this);
            requestMenu.getPlatosMenu(menu.getId());
        }else {
            titulo.setText("Nuevo Menu");
        }
    }

    public void removePlato(int index){
        platos.remove(index);
        ListView elementsPlatos = (ListView) findViewById(R.id.listPlatoMenu);
        PlatoAdapter adapterPlatos = new PlatoAdapter(this, platos,this,menu.getId());
        elementsPlatos.setAdapter(adapterPlatos);

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
        Log.i("Mensaje", "los platos son "+platosRecibidos);
        int id;
        String nombre;
        double precio;
        String photoPath;
        Plato plato;
        ListView elementsPlatos = (ListView) findViewById(R.id.listPlatoMenu);
        PlatoAdapter adapterPlatos = new PlatoAdapter(this, platos,this,menu.getId());
        elementsPlatos.setAdapter(adapterPlatos);

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
}
