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

import ec.espol.food.foodespoladmin.Adapters.PlatoAdapter;
import ec.espol.food.foodespoladmin.Controllers.Constants;
import ec.espol.food.foodespoladmin.Controllers.RequestMenu;
import ec.espol.food.foodespoladmin.Model.Menu;
import ec.espol.food.foodespoladmin.Model.Plato;

public class MenuView extends AppCompatActivity implements  Observer{
    private Menu menu;
    private ArrayList<Plato> platos;
    private int codeEscogerPlatos = 0;
    private int year, month, day;
    private Calendar calendar;
    private TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data=getIntent().getExtras();
        TextView titulo = (TextView) findViewById(R.id.txTitleMenu);
        platos= new ArrayList<Plato>();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        ImageButton agregarPlatos = (ImageButton) findViewById(R.id.btnAgregarPatosMenu);
        ImageButton escogerFecha = (ImageButton) findViewById(R.id.btnEscogerFechaMenu);
        ImageButton guardarMenu = (ImageButton) findViewById(R.id.btnGuardarMenu);
        fecha = (TextView) findViewById(R.id.txtFechaMenu);
        agregarPlatos.setOnClickListener(eventEscogerPlatos);
        escogerFecha.setOnClickListener(eventEscogerFecha);
        guardarMenu.setOnClickListener(eventGuardarMenu);

        if(data!=null) {
            titulo.setText("Menu");
            menu = (Menu) data.getSerializable("menu");
            Log.i("Mensaje", "El Menu Seleccionado es " + menu.getId());

            fecha.setText(menu.getFecha());
            RequestMenu requestMenu = new RequestMenu(getBaseContext(),this);
            requestMenu.getPlatosMenu(menu.getId());
        }else {
            titulo.setText("Nuevo Menu");
            menu=new Menu(-1,null);
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

    public View.OnClickListener eventEscogerPlatos = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(MenuView.this, escogerPlatosMenu.class);

            startActivityForResult(intent, codeEscogerPlatos);
        }

    };
    public View.OnClickListener eventEscogerFecha = new View.OnClickListener(){
        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View view){

            showDialog(999);
        }

    };

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }
    private String generateDate(int year, int month, int day) {
        return Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            //showDate(arg1, arg2+1, arg3);

            menu.setFecha(generateDate(arg1, arg2+1, arg3));
            fecha.setText(menu.getFecha());
        }
    };

    public View.OnClickListener eventGuardarMenu = new View.OnClickListener(){
        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View view){

            RequestMenu requestMenu =new RequestMenu(MenuView.this,MenuView.this);
            requestMenu.postMenu(menu);
        }

    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Constants c=new Constants();
        ArrayList<Plato> platosSelected = c.platosSlected;
        ListView elementsPlatos = (ListView) findViewById(R.id.listPlatoMenu);
        PlatoAdapter adapterPlatos = new PlatoAdapter(this, platos,this,menu.getId());
        elementsPlatos.setAdapter(adapterPlatos);
        for(Plato p: platosSelected){
            Log.i("Mensaje", "plato Agregado "+p);
            platos.add(p);

        }

    }

}
