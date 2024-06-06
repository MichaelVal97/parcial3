import java.io.*;
import java.util.*;

public class Principal {
    private static List<Aeropuerto> aeropuertos = new ArrayList<>();
    private static List<Vuelo> vuelos = new ArrayList<>();
    private static List<Compania> companias = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        cargarDatos("src/vuelos.txt");

        System.out.println("Vuelos de salida desde cada aeropuerto:");
        mostrarVuelosSalida();

        System.out.println("Vuelos de llegada a cada aeropuerto:");
        mostrarVuelosLlegada();
    }

    private static void cargarDatos(String filename) throws IOException {
        String absolutePath = new File(filename).getAbsolutePath();
        System.out.println("Cargando datos desde: " + absolutePath);

        BufferedReader br = new BufferedReader(new FileReader(absolutePath));
        String line;
        boolean leyendoAeropuertos = false;
        boolean leyendoVuelos = false;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            if (line.startsWith("#Aeropuertos")) {
                leyendoAeropuertos = true;
                leyendoVuelos = false;
                continue;
            }

            if (line.startsWith("#Vuelos")) {
                leyendoVuelos = true;
                leyendoAeropuertos = false;
                continue;
            }

            if (leyendoAeropuertos) {
                cargarAeropuerto(line);
            } else if (leyendoVuelos) {
                cargarVuelo(line);
            }
        }
        br.close();
    }

    private static void cargarAeropuerto(String line) {
        String[] data = line.split(";");
        if (data.length < 5) return;

        String codigo = data[0];
        String nombre = data[1];
        String poblacion = data[2];
        String pais = data[3];
        int gmt = Integer.parseInt(data[4].replace(".", ""));

        aeropuertos.add(new Aeropuerto(codigo, nombre, poblacion, pais, gmt));
    }

    private static void cargarVuelo(String line) {
        String[] data = line.split(";");
        if (data.length < 9) return;

        String tipo = data[0];
        String origen = data[1];
        String destino = data[2];
        String duracion = data[3];
        String aerolinea = data[4];
        String avion = data[5];
        int capacidad = Integer.parseInt(data[6]);
        String diasHorarios = data[7];
        String infoAdicional = data[8];

        vuelos.add(new Vuelo(tipo, origen, destino, duracion, aerolinea, avion, capacidad, diasHorarios, infoAdicional));

        if (companias.stream().noneMatch(c -> c.getNombre().equals(aerolinea))) {
            companias.add(new Compania(aerolinea));
        }
    }

    private static void mostrarVuelosSalida() {
        for (Aeropuerto aeropuerto : aeropuertos) {
            System.out.println("Aeropuerto: " + aeropuerto.getNombre());
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getOrigen().equals(aeropuerto.getCodigo())) {
                    System.out.println("  Vuelo a " + getNombreAeropuerto(vuelo.getDestino()) +
                            " con " + vuelo.getAerolinea() + " (" + vuelo.getTipo() + ")");
                }
            }
        }
    }

    private static void mostrarVuelosLlegada() {
        for (Aeropuerto aeropuerto : aeropuertos) {
            System.out.println("Aeropuerto: " + aeropuerto.getNombre());
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getDestino().equals(aeropuerto.getCodigo())) {
                    Aeropuerto origen = getAeropuertoPorCodigo(vuelo.getOrigen());
                    String nombreOrigen = origen != null ? origen.getNombre() : "un aeropuerto no encontrado";
                    System.out.println("  Vuelo desde " + nombreOrigen +
                            " con " + vuelo.getAerolinea() + " (" + vuelo.getTipo() + ")");
                }
            }
        }
    }

    private static Aeropuerto getAeropuertoPorCodigo(String codigo) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (aeropuerto.getCodigo().equals(codigo)) {
                return aeropuerto;
            }
        }
        return null;
    }

    private static String getNombreAeropuerto(String codigo) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (aeropuerto.getCodigo().equals(codigo)) {
                return aeropuerto.getNombre();
            }
        }
        return "un aeropuerto no encontrado";
    }
}

