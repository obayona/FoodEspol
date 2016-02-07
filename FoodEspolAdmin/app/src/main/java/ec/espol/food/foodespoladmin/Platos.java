package ec.espol.food.foodespoladmin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.util.Log;

public class Platos extends Fragment {

    private View view;

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

        return view;

    }


}
