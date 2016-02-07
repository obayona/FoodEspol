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

    private Button btNuevoPlato;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_platos, container, false);
        btNuevoPlato = (Button)view.findViewById(R.id.btnNuevoPlato);
        btNuevoPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Mensaje", "Activity is created");
                Intent intent = new Intent(getContext(),NuevoPlato.class);
                startActivity(intent);
                Log.i("Mensaje", "Activity is created");
            }
        });

        return view;

    }


}
