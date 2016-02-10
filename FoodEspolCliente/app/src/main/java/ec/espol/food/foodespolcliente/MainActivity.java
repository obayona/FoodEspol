package ec.espol.food.foodespolcliente;



import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentTabHost;
import  android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;
        FragmentTabHost tabHost= (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.tabContent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Platos"), Platos.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Restaurantes"), Restaurantes.class, null);

        tabHost.setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                // TODO your actions go here
                Log.i("Mensaje", "cambiastes al tab: "+tabId);

            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
