package ec.espol.food.foodespoladmin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Adapters.MenuAdapter;
import ec.espol.food.foodespoladmin.Controllers.RequestMenus;
import ec.espol.food.foodespoladmin.Model.Menu;


public class Menus extends Fragment implements Observer {
    private View view;
    private ArrayList<Menu> menus;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Mensaje", "Actividad Menus Creada");
        view=inflater.inflate(R.layout.activity_menus, container, false);
        menus=new ArrayList<Menu>();
        Menu m;
        m= new Menu(1, "7/02/2016");
        menus.add(m);
        final ListView elements = (ListView) view.findViewById(R.id.listMenus);
        final MenuAdapter adapter = new MenuAdapter(getContext(), menus);
        elements.setAdapter(adapter);
        RequestMenus requestMenus= new RequestMenus(getContext(),this);
        requestMenus.getMenus();
        return view;

    }

    @Override
    public void update(Object objeto ){

    }
}
