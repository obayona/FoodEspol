package ec.espol.food.foodespoladmin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ec.espol.food.foodespoladmin.R;
import ec.espol.food.foodespoladmin.Model.Menu;

/**
 * Created by jorge on 7/2/16.
 */
public class MenuAdapter extends ArrayAdapter<Menu> {
    private ArrayList<Menu>menus;
    private Context context;

    public MenuAdapter(Context context, ArrayList<Menu> menus) {
        super(context,0,menus);
        this.menus = menus;
        this.context = context;
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
        Menu currentMenu = this.menus.get(position);
        holder.fecha.setText(currentMenu.getFecha());
        return convertView;
    }

}