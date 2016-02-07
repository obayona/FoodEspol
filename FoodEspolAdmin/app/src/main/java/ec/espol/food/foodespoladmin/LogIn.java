package ec.espol.food.foodespoladmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class LogIn extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        controllerRequests controller = new controllerRequests(this.getApplicationContext());
        Log.i("mensaje", "estoy enviando");
        controller.validateLogIn("jorenver","cualquiera");
        intent = new Intent(LogIn.this,TabAdmin.class);
        startActivity(intent);

    }
}
