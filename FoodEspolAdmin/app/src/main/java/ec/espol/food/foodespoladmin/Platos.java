package ec.espol.food.foodespoladmin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Adapters.PlatosAdapter;
import ec.espol.food.foodespoladmin.Model.CategoriaEnum;
import ec.espol.food.foodespoladmin.Model.Plato;

public class Platos extends Fragment {

    private View view;
    private ArrayList<Plato> platos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_platos, container, false);
        Log.i("Mensaje", "Actividad Platos Creada");
        Button btNuevoPlato = (Button)view.findViewById(R.id.btnNuevoPlato);
        btNuevoPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),NuevoPlato.class);
                startActivity(intent);

            }
        });
        platos=new ArrayList<Plato>();
        Plato p;
        p= new Plato(1, "Seco de Chivo", 1.25, null);
        //p.addCategotia(CategoriaEnum.PIQUEO);
        platos.add(p);
        p= new Plato(2, "Seco de iguana", 2.25, null);
        //p.addCategotia(CategoriaEnum.ALMUERZO);
        platos.add(p);
        p= new Plato(3, "Corviche", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(4, "Camaleon Frito", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(5, "Cangrejo Asado", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(6, "Caldo de vaca", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(7, "Sopa de Calamar", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(8, "Frutas", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(9, "Frutanga", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        p= new Plato(10, "lobo Asado", 1.00, null);
        //p.addCategotia(CategoriaEnum.DESAYUNO);
        platos.add(p);
        final ListView elements = (ListView) view.findViewById(R.id.listPlatos);
        final PlatosAdapter adapter = new PlatosAdapter(getContext(), platos);
        elements.setAdapter(adapter);
        return view;

    }


}
