package ec.espol.food.foodespoladmin.Controllers;

/**
 * Created by oswaldoalejandro on 09/02/16.
 */
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import ec.espol.food.foodespoladmin.Observer;
import ec.espol.food.foodespoladmin.Model.RestauranteInfo;

public class RequestRestaurante {
    private Context context;
    private final Observer observer;
    private Constants cons;

    public RequestRestaurante(Context c, Observer o){
        observer = o;
        context = c;
        cons = new Constants();
    }

    public void getRestaurante(){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + String.format("getRestaurante?idRestaurante=%d", cons.idRestaurante);

        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String propietario = response.getString("nombreProp");
                            String nombre = response.getString("nombre");
                            String capacidad = response.getString("capacidad");
                            double latitud = response.getDouble("latitud");
                            double longitud = response.getDouble("longitud");
                            String logo = response.getString("logo");
                            RestauranteInfo rest = new RestauranteInfo(propietario, nombre, capacidad,
                                                        latitud, longitud, logo);
                            observer.update(rest);

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
