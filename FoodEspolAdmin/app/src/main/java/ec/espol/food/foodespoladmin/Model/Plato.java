package ec.espol.food.foodespoladmin.Model;

import java.util.ArrayList;

/**
 * Created by jorge on 7/2/16.
 */
import java.util.HashMap;
import java.util.List;

import ec.espol.food.foodespoladmin.Controllers.Constants;

public class Plato {
    private int id;
    private String nombre;
    private double precio;
    public int catComidaRapida = 0;
    public int catPiqueo = 0;
    public int catDesayuno = 0;
    public int catAlmuerzo = 0;



    public Plato(int id, String nombre, double precio, String photoPath) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;

    }

    public HashMap<String, List< String> > getHashMap(){

        HashMap<String, List<String>> map = new HashMap<>();

        ArrayList<String> field1 = new ArrayList<String>();
        field1.add(Integer.toString(Constants.idRestaurante));
        map.put("idRestaurante", field1);

        ArrayList<String> field2 = new ArrayList<String>();
        field2.add(Double.toString(precio));
        map.put("precio", field2);

        ArrayList<String> field3 = new ArrayList<String>();
        field3.add(nombre);
        map.put("nombre", field3);

        //categorias
        ArrayList<String> field4 = new ArrayList<String>();
        field4.add(Integer.toString(catComidaRapida));
        map.put("catComidaRapida", field4);

        ArrayList<String> field5 = new ArrayList<String>();
        field5.add(Integer.toString(catPiqueo));
        map.put("catPiqueo", field5);

        ArrayList<String> field6 = new ArrayList<String>();
        field6.add(Integer.toString(catDesayuno));
        map.put("catDesayuno", field6);

        ArrayList<String> field7 = new ArrayList<String>();
        field7.add(Integer.toString(catAlmuerzo));
        map.put("catAlmuerzo", field7);

        return map;
    }

    public String getPhotoPath() {
        return photoPath;
    }


    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    private String photoPath;

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }



}
