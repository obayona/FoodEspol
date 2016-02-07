package ec.espol.food.foodespoladmin;

import ec.espol.food.foodespoladmin.Model.RequestPlatos;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

public class LogIn extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);


    }

    private final Observer observer = new Observer(){
        public void update(Object object){
            changeActivity();
        }
    };


    @Override
    public void onClick(View v) {

        EditText user = (EditText) findViewById(R.id.txtUser);
        EditText password = (EditText) findViewById(R.id.txtPasword);

        String userString = user.getText().toString();
        String passwordString = password.getText().toString();

        if(!userString.equals("") && !passwordString.equals("")  ){
            Login(userString, passwordString);
        }
    }


    public void Login(String user, String password ){
        Log.i("mensaje", "estoy enviando");
        RequestPlatos controller = new RequestPlatos(this.getApplicationContext(), observer);
        controller.validateLogIn(user, password);
    }

    public void changeActivity(){
        Intent intent;
        intent = new Intent(LogIn.this,TabAdmin.class);
        startActivity(intent);

    }


}
