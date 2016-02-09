package ec.espol.food.foodespoladmin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Adapters.MenuAdapter;
import ec.espol.food.foodespoladmin.Controllers.RequestMenus;
import ec.espol.food.foodespoladmin.Model.Menu;
import java.io.Serializable;


public class Menus extends Fragment implements Observer {
    private View view;
    private ArrayList<Menu> menus=new ArrayList<Menu>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Mensaje", "Actividad Menus Creada");
        view=inflater.inflate(R.layout.activity_menus, container, false);
        Button btnNuevoMenu = (Button)view.findViewById(R.id.btnNuevoMenu);
        btnNuevoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MenuView.class);
                startActivity(intent);

            }
        });
        RequestMenus requestMenus= new RequestMenus(getContext(),this);
        requestMenus.getMenus();

        return view;

    }

    @Override
    public void update(Object objeto ){
        JSONArray menusRecibidos;
        menusRecibidos=(JSONArray) objeto;
        Log.i("Mensaje", "los menus son "+menusRecibidos);
        int id;
        String fecha;
        final ListView elements = (ListView) view.findViewById(R.id.listMenus);
        final MenuAdapter adapter = new MenuAdapter(getContext(), menus);
        elements.setAdapter(adapter);
        elements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Mensaje", "Se debe abrir MenuView");
                Intent intent = new Intent(getContext(), MenuView.class);
                //Bundle data = new Bundle();
                //MenuView menu = adapter.getMenus().get(position);
                //data.putSerializable("menu", menu);
                //intent.putExtras(data);
                startActivity(intent);
            }
        });
        this.menus.clear();
        Menu m;
        for (int i = 0; i < menusRecibidos.length(); i++) {
            try {
                JSONObject row = menusRecibidos.getJSONObject(i);
                id = row.getInt("id");
                fecha = row.getString("fecha");
                m=new Menu(id,fecha);
                this.menus.add(m);
            }catch (JSONException e){

            }

        }

    }
}
