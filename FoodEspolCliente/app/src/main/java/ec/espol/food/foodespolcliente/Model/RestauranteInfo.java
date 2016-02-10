package ec.espol.food.foodespolcliente.Model;

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

    public RestauranteInfo(String propietario, String nombre, String capacidad, double latitud, double longitud, String logo  ){

        this.propietario = propietario;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.logo = logo;

    }


}



