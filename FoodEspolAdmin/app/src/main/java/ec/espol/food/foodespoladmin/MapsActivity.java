package ec.espol.food.foodespoladmin;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitud;
    private double longitud;
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        latitud = intent.getDoubleExtra("Latitud", 0);
        longitud = intent.getDoubleExtra("Longitud", 0);


        Button btnGuardarUbicacion = (Button)findViewById(R.id.btnGuardarUbicacion);
        btnGuardarUbicacion.setOnClickListener(clickGuardarUbicacion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ubicacion = new LatLng(latitud, longitud);

        marker =  mMap.addMarker(new MarkerOptions().position(ubicacion).title("El restaurante"));
        CameraUpdate zoom =CameraUpdateFactory.zoomTo(17);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        mMap.animateCamera(zoom);

        mMap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latlng) {
                // TODO Auto-generated method stub

                if (marker != null) {
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions().position(latlng).title("Nueva Ubicacion"));

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        Intent intent=new Intent();
        setResult(AppCompatActivity.RESULT_CANCELED, intent);
        finish();

        return super.onOptionsItemSelected(item);
    }

    public View.OnClickListener clickGuardarUbicacion = new View.OnClickListener(){

        @Override
        public void onClick(View view){
            LatLng latLng = marker.getPosition();
            Log.d("Imprimo", "estoy llamando al observae");


            latitud = latLng.latitude;
            longitud = latLng.longitude;


            Intent intent=new Intent();
            intent.putExtra("Latitud", latitud);
            intent.putExtra("Longitud", longitud);

            setResult(0, intent);
            finish();

        }

    };




}
