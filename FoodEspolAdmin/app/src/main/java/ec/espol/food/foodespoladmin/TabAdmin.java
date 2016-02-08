package ec.espol.food.foodespoladmin;


import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentTabHost;
import  android.widget.TabHost.OnTabChangeListener;

import ec.espol.food.foodespoladmin.Controllers.Constants;

public class TabAdmin extends FragmentActivity {
    private  Constants cons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_admin);
        this.cons = new Constants();
        Log.d("ensaje", "el id restaurante es: "+cons.idRestaurante);
        FragmentTabHost tabHost= (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(TabAdmin.this, getSupportFragmentManager(), R.id.tabContent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Menus"), Menus.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Platos"), Platos.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Restaurante"), Restaurante.class, null);
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
