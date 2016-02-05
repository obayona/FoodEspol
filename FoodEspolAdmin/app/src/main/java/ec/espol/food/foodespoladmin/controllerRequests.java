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
public class controllerRequests {
    private static final String url="http://127.0.0.1:9009/validarlogIn";
    private Context context;
    public controllerRequests(Context c){
        // Instantiate the RequestQueue.

        this.context=c;
    }

    public void validateLogIn(final String user, final String password){
        Log.d("Mensaje","request" );
        JsonObjectRequest request;
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Mensaje", "La respuesta es: " + response.toString());
                        Log.d("Mensaje", response.toString());
                        try {
                            boolean result = response.getBoolean("estado");
                        } catch (JSONException e) {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }
}
