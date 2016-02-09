package ec.espol.food.foodespoladmin.Controllers;

/**
 * Created by oswaldoalejandro on 08/02/16.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.koushikdutta.ion.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import ec.espol.food.foodespoladmin.Platos;
import ec.espol.food.foodespoladmin.Observer;

public class RequestNuevoPlato {
    private Context context;
    private final Observer observer;


    public RequestNuevoPlato(Context c, Observer o){
        observer = o;
        context = c;

    }

    public void uploadPlato(File f,Map<String,List<String>> map) throws JSONException {
        //map tiene los campos del reporte y path la ruta de la foto en el sistema de archivos de android

        Log.d("ensaje", "*****estoy en uploadPlato");
        if(f==null){
            return;
        }

        Log.d("ensaje", "*****no soy null");

        Constants cons = new Constants();
        String ip = cons.ip;
        String url = ip + new String("guardarPlato");

        Future uploading = Ion.with(this.context)
                .load(url)
                .progressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        // callbacks on progress can happen on the UI thread
                        // via progressHandler. This is useful if you need to update a TextView.
                        // Updates to TextViews MUST happen on the UI thread.
                    }
                })
                .setMultipartParameters(map)
                .setMultipartFile("image", f)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        try {
                            JSONObject jobj = new JSONObject(result.getResult());
                            Toast.makeText(context, "Reporte enviado con exito", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            Intent intent = new Intent(context,Platos.class);
                            observer.update(null);

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }

}



