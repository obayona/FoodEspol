package ec.espol.food.foodespoladmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.util.Log;

public class Platos extends AppCompatActivity {

    private Button btNuevoPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);
        Log.i(DISPLAY_SERVICE, "se llama el create");
        btNuevoPlato = (Button)findViewById(R.id.btnNuevoPlato);
        btNuevoPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(DISPLAY_SERVICE, "Activity is created");
                Intent intent = new Intent(Platos.this, NuevoPlato.class);
                startActivity(intent);
                Log.i(DISPLAY_SERVICE, "Activity is created");
            }
        });

    }


}
