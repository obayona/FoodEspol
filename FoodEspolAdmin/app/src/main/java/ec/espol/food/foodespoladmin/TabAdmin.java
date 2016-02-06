package ec.espol.food.foodespoladmin;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost;

public class TabAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_admin);
        TabHost tabH = (TabHost) findViewById(R.id.tabHost);

        //tab1
        tabH.setup();
        TabSpec ts1=tabH.newTabSpec("tab1");
        ts1.setIndicator("Menus");
        ts1.setContent(R.id.menus);
        tabH.addTab(ts1);
        //tab2
        tabH.setup();
        TabSpec ts2=tabH.newTabSpec("tab2");
        ts2.setIndicator("Platos");
        ts2.setContent(R.id.platos);
        tabH.addTab(ts2);
        //tab3
        tabH.setup();
        TabSpec ts3=tabH.newTabSpec("tab2");
        ts3.setIndicator("Restaurante");
        ts3.setContent(R.id.restaurante);
        tabH.addTab(ts3);


    }
}
