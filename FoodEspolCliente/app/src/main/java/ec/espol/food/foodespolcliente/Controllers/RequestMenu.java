package ec.espol.food.foodespolcliente.Controllers;

import android.content.Context;
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

import java.util.HashMap;
import java.util.Map;

import ec.espol.food.foodespolcliente.Model.Menu;
import ec.espol.food.foodespolcliente.Observer;

/**
 * Created by jorge on 9/2/16.
 */
public class RequestMenu {
    private Context context;
    private final Observer observer;
    private Constants cons;

    public RequestMenu(Context c, Observer observer){
        this.observer = observer;
        this.context=c;
        this.cons = new Constants();
    }

    public void getPlatosMenu(int idMenu){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + String.format("getPlatosMenu?idRestautante=%d&idMenu=%d", 1,idMenu);
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray platos=response.getJSONArray("platos");
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
