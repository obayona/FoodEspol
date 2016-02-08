package ec.espol.food.foodespoladmin.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ec.espol.food.foodespoladmin.Observer;

/**
 * Created by oswaldoalejandro on 07/02/16.
 */
public class RequestNuevoPlato {
    private Context context;
    private final Observer observer;

    public RequestNuevoPlato(Context c, Observer observer){
        // Instantiate the RequestQueue.
        this.observer = observer;
        this.context=c;

    }

    public void guardarPlato(HashMap<String, String> plato) {

        JsonObjectRequest request;
        Constants cons = new Constants();
        String ip = cons.ip;
        String url = ip + new String("guardarPlato");

        Log.d("ip", url);


        request = new JsonObjectRequest( url ,new JSONObject(plato),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("ensaje", "*****estoy en el response puta madre");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }

}
