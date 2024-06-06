public class Vuelo {
    private String tipo;
    private String origen;
    private String destino;
    private String duracion;
    private String aerolinea;
    private String avion;
    private int capacidad;
    private String diasHorarios;
    private String infoAdicional;

    public Vuelo(String tipo, String origen, String destino, String duracion, String aerolinea, String avion, int capacidad, String diasHorarios, String infoAdicional) {
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.aerolinea = aerolinea;
        this.avion = avion;
        this.capacidad = capacidad;
        this.diasHorarios = diasHorarios;
        this.infoAdicional = infoAdicional;
    }

    public String getTipo() {
        return tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public String getAvion() {
        return avion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getDiasHorarios() {
        return diasHorarios;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }
}
