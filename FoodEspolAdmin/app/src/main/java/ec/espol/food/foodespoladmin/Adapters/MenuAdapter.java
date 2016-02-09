package ec.espol.food.foodespoladmin.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.Controllers.RequestMenus;
import ec.espol.food.foodespoladmin.Observer;
import ec.espol.food.foodespoladmin.R;
import ec.espol.food.foodespoladmin.Model.Menu;

/**
 * Created by jorge on 7/2/16.
 */
public class MenuAdapter extends ArrayAdapter<Menu> {
    private ArrayList<Menu>menus;
    private Context context;
    private Observer observer;

    public MenuAdapter(Context context, ArrayList<Menu> menus,Observer observer) {
        super(context,0,menus);
        this.menus = menus;
        this.context = context;
        this.observer=observer;
    }

    static class ViewHolder{
        public TextView fecha;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public MenuAdapter setReminders(ArrayList<Menu> menus) {
        this.menus = menus;
        return this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.fecha = (TextView)convertView.findViewById(R.id.txtFechaMenu);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        final Menu currentMenu = this.menus.get(position);
        holder.fecha.setText(currentMenu.getFecha());
        ImageButton delete =(ImageButton)convertView.findViewById(R.id.btnEliminarMenu);
        delete.setFocusable(false);
        delete.setClickable(false);
        observer=this.observer;
        delete.setOnClickListener(new View.OnClickListener() {
            private Menu dataMenu=currentMenu;
            private Observer dataObserver=observer;
            @Override
            public void onClick(View v) {
                Log.i("Mensaje", "Se quiere eliminar un Menu: "+dataMenu.id);
                RequestMenus requestMenus = new RequestMenus(getContext(),dataObserver);
                requestMenus.eliminarMenu(dataMenu.id);
            }
        });
        return convertView;
    }

}