package ec.espol.food.foodespoladmin.Controllers;

/**
 * Created by oswaldoalejandro on 07/02/16.
 */
import ec.espol.food.foodespoladmin.Observer;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import org.json.JSONException;
import android.util.Log;
import android.widget.Toast;



public class RequestLogIn {


    private Context context;
    private final Observer observer;
    private Constants cons;
    public RequestLogIn(Context c, Observer observer){
        // Instantiate the RequestQueue.
        this.observer = observer;
        this.context=c;
        this.cons = new Constants();
    }

    public void validateLogIn(final String user, final String password){

        JsonObjectRequest request;
        String ip = cons.ip;
        String url = ip + String.format("validarlogIn?user=%s&clave=%s", user, password);
        request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d("ensaje", "estoy en el try");
                            int idRest = response.getInt("idRestaurante");
                            if(idRest!= -1){
                                Integer idRestaurante = new Integer(idRest);
                                cons.idRestaurante=idRest;
                                observer.update(idRestaurante);
                            }
                            else{
                                Toast.makeText(context,"Usuario o contraseña incorrecta",Toast.LENGTH_LONG).show();
                            }

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

/*
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



    }*/
}
