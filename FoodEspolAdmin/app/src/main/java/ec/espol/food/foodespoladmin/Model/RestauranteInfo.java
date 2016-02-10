package ec.espol.food.foodespoladmin.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ec.espol.food.foodespoladmin.Controllers.Constants;

/**
 * Created by oswaldoalejandro on 09/02/16.
 */
public class RestauranteInfo {
    public String propietario;
    public String nombre;
    public String capacidad;
    public double latitud;
    public double longitud;
    public String logo;
    public int bandPath;

    public RestauranteInfo(String propietario, String nombre, String capacidad, double latitud, double longitud, String logo  ){

        this.propietario = propietario;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.logo = logo;
        bandPath = 0; //bandera delo logo

    }

    public HashMap<String, List<String>> getHashMap(){

        HashMap<String, List<String>> map = new HashMap<>();

        //id Restaurante
        ArrayList<String> field1 = new ArrayList<String>();
        field1.add(Integer.toString(Constants.idRestaurante));
        map.put("idRestaurante", field1);

        /*
        //nombre del restaurante
        ArrayList<String> field2 = new ArrayList<String>();
        field2.add(nombre);
        map.put("nombre", field2);

        //nombre propietario
        ArrayList<String> field3 = new ArrayList<String>();
        field3.add(propietario);
        map.put("nombreProp", field3);


        //capacidad
        ArrayList<String> field4 = new ArrayList<String>();
        field4.add(capacidad);
        map.put("propietario", field4);

        //latitud
        ArrayList<String> field5 = new ArrayList<String>();
        field5.add(Double.toString(latitud));
        map.put("latitud", field5);

        //longitud
        ArrayList<String> field6 = new ArrayList<String>();
        field6.add(Double.toString(longitud));
        map.put("longitud", field6);

        //logo path
        ArrayList<String> field7 = new ArrayList<String>();
        field7.add(logo);
        map.put("logo", field7);

        //bandPath
        ArrayList<String> field8 = new ArrayList<String>();
        field8.add(Integer.toString(bandPath));
        map.put("bandPath", field8);*/

        return map;
    }


}



