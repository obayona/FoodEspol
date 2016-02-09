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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ec.espol.food.foodespoladmin.Observer;

/**
 * Created by jorge on 7/2/16.
 */
public class RequestMenus {
    private Context context;
    private final Observer observer;
    private Constants cons;

    public RequestMenus(Context c, Observer observer){
        this.observer = observer;
        this.context=c;
        this.cons = new Constants();
    }

    public void getMenus(){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + String.format("getMenus?idRestautante=%d", cons.idRestaurante);
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray menus=response.getJSONArray("menus");
                            observer.update(menus);
                        } catch (JSONException e) {
                            Toast.makeText(context,"Error en el servidor",Toast.LENGTH_LONG).show();
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
    public void eliminarMenu(int idMenu){

        JsonObjectRequest request;

        String ip = cons.ip;
        String url = ip + String.format("eliminarMenu?idRestautante=%d&idMenu=%d", cons.idRestaurante,idMenu);
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            boolean ban=response.getBoolean("ban");
                            if(ban)
                                getMenus();
                        } catch (JSONException e) {
                            Toast.makeText(context,"Error en el servidor",Toast.LENGTH_LONG).show();
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
