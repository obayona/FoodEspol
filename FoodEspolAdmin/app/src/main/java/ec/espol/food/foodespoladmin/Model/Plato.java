package ec.espol.food.foodespoladmin.Model;

import java.util.ArrayList;

/**
 * Created by jorge on 7/2/16.
 */
import java.util.HashMap;

public class Plato {
    private int id;
    private String nombre;
    private double precio;
    private ArrayList<CategoriaEnum> categorias;

    public Plato(int id, String nombre, double precio, String photoPath) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categorias = new ArrayList<CategoriaEnum>();
        this.photoPath = photoPath;
    }
    public void addCategotia(CategoriaEnum categoria){
        boolean ban=true;
        for(CategoriaEnum c : categorias) {
            if(c.equals(categoria))
                ban=false;
        }
        if(ban)
            categorias.add(categoria);
    }

    public HashMap<String, String> getHashMap(){

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("nombre", nombre);
        map.put("precio", String.valueOf(precio));

        return map;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public ArrayList<CategoriaEnum> getCategotias() {
        return categorias;
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

    public void setCategorias(ArrayList<CategoriaEnum> categotias) {
        this.categorias = categotias;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }



}
