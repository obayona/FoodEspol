package ec.espol.food.foodespoladmin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.util.Log;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Model.Categotia;
import ec.espol.food.foodespoladmin.Model.Plato;

public class Platos extends Fragment {

    private View view;
    private ArrayList<Plato> platos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        platos=new ArrayList<Plato>();
        Plato p;
        p= new Plato(1, "Seco de Chivo", 1.25, null);
        p.addCategotia(Categotia.PIQUEO);
        platos.add(p);
        p= new Plato(2, "Seco de higuana", 2.25, null);
        p.addCategotia(Categotia.ALMUERZO);
        platos.add(p);
        p= new Plato(1, "Corvivhe", 1.00, null);
        p.addCategotia(Categotia.DESAYUNO);
        platos.add(p);
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

        return view;

    }


}
