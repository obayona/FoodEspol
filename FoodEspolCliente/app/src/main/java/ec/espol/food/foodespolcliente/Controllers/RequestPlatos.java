package ec.espol.food.foodespolcliente.Controllers;

/**
 * Created by oswaldoalejandro on 10/02/16.
 */

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
public class RequestPlatos {
    private Context context;
    private final Observer observer;
    private Constants cons;

    public RequestPlatos(Context c, Observer observer){
        this.observer = observer;
        this.context=c;
        this.cons = new Constants();
    }

    public void getPlatos(){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + new String("getAllPlatos");
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray platos=response.getJSONArray("platos");
                            Log.i("Mensaje", "los platos llegaron " + platos);
                            observer.update(platos);
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

