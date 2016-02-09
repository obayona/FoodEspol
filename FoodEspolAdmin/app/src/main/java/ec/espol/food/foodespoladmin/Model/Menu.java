package ec.espol.food.foodespoladmin.Model;

import java.io.Serializable;

/**
 * Created by jorge on 7/2/16.
 */
public class Menu implements Serializable {
    private String fecha;
    private int id;

    public Menu( int id,String fecha) {
        this.fecha = fecha;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public String getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }



}
