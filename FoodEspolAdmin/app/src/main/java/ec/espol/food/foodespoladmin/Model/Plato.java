package ec.espol.food.foodespoladmin.Model;

import java.util.ArrayList;

/**
 * Created by jorge on 7/2/16.
 */
public class Plato {
    private int id;
    private String nombre;
    private double precio;
    private ArrayList<Categotia> categotias;

    public Plato(int id, String nombre, double precio, String photoPath) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categotias = new ArrayList<Categotia>();
        this.photoPath = photoPath;
    }
    public void addCategotia(Categotia categoria){
        boolean ban=true;
        for(Categotia c : categotias) {
            if(c.equals(categoria))
                ban=false;
        }
        if(ban)
            categotias.add(categoria);
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public ArrayList<Categotia> getCategotias() {
        return categotias;
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

    public void setCategotias(ArrayList<Categotia> categotias) {
        this.categotias = categotias;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }



}
