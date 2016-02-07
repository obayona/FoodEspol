package ec.espol.food.foodespoladmin;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import android.util.Log;
import android.widget.Toast;
public class controllerRequests {
    private static final String url="http://192.168.1.2:9009/validarlogIn?user=eloy&clave=1234";
    private Context context;
    public controllerRequests(Context c){
        // Instantiate the RequestQueue.

        this.context=c;
    }

    public void validateLogIn(final String user, final String password){
        Log.d("Mensaje","****request" );
        JsonObjectRequest request;
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Mensaje", "La respuesta es: " + response.toString());
                        Log.d("Mensaje", response.toString());
                        try {
                            Log.d("ensaje", "estoy en el try");
                            boolean result = response.getBoolean("estado");

                        } catch (JSONException e) {
                            Log.d("ensaje", "estoy en el catch");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("mensaje", "****error");
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }


    public void getPlatos(final int idRestaurante){
        JsonObjectRequest request;

        String urlPlatos = String.format("http://127.0.0.1:9009/getPlatos?idRestaurante=%d",idRestaurante);

        request = new JsonObjectRequest(Request.Method.GET, urlPlatos,
                new Response.Listener<JSONObject>(){
                    @Override
                public void onResponse(JSONObject response){

                    }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);



    }
}
