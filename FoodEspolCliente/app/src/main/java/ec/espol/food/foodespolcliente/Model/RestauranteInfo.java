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
    public String aproximado;

    public void setAproximado(String aproximado) {
        this.aproximado = aproximado;
    }



    public String getAproximado() {
        return aproximado;
    }



    public String getPropietario() {
        return propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getLogo() {
        return logo;
    }



    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public RestauranteInfo(String propietario, String nombre, String capacidad, double latitud, double longitud, String logo,String aproximado  ){

        this.propietario = propietario;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.logo = logo;
        this.aproximado=aproximado;

    }


}



