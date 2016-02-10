package ec.espol.food.foodespolcliente.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ec.espol.food.foodespolcliente.Observer;

/**
 * Created by jorge on 8/2/16.
 */
public class RequestRestaurantes {
    private Context context;
    private final Observer observer;
    private Constants cons;

    public RequestRestaurantes(Context c, Observer observer){
        this.observer = observer;
        this.context=c;
        this.cons = new Constants();
    }

    public void getRestaurantes(){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + new String("getRestaurantes");
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray restaurates=response.getJSONArray("restaurantes");
                            Log.i("Mensaje", "los restaurantes llegaron " + restaurates);
                            observer.update(restaurates);
                        } catch (JSONException e) {
                            Toast.makeText(context, "Error en el servidor", Toast.LENGTH_LONG).show();
                        }
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