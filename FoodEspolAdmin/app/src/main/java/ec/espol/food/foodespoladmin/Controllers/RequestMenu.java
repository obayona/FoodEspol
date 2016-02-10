package ec.espol.food.foodespoladmin.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

import ec.espol.food.foodespoladmin.Model.Menu;
import ec.espol.food.foodespoladmin.Observer;

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
        String url = ip + String.format("getPlatosMenu?idRestautante=%d&idMenu=%d", cons.idRestaurante,idMenu);
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

    public void postMenu(final Menu menu){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + String.format("postMenu");
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("fecha",menu.getFecha());
        parametros.put("idMenu", Integer.toString(menu.getId()));
        parametros.put("idRestautante",Integer.toString(cons.idRestaurante));
        request = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(parametros),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            boolean respuesta=response.getBoolean("ban");
                            if(respuesta){
                                Toast.makeText(context,"Menu Creado exitosamente",Toast.LENGTH_LONG).show();
                            }
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
