
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class Ejercicio4_AppFiscalia {
    public static void main(String[] args) {
        CasoCorrupcion[] casos = new CasoCorrupcion[2];

        casos[0] = new CasoCorrupcion("Caso Petroecuador", new Date(), "Iniciado");
        casos[1] = new CasoCorrupcion("Caso Odebrecht", new Date(), "Iniciado");

        casos[0].agregarPersona(new Persona("Juan Perez", 45, "Ex Ministro", "Acusado", 2));
        casos[0].agregarPersona(new Persona("Luis Gómez", 38, "Contratista", "Testigo", 0));

        casos[1].agregarPersona(new Persona("Maria Lopez", 50, "Empresaria", "Víctima", 0));
        casos[1].agregarPersona(new Persona("Carlos Ruiz", 47, "Funcionario Público", "Acusado", 1));

        for (CasoCorrupcion caso : casos) {
            if (caso != null) {
                caso.actualizarEstado();
                System.out.println(caso);
            }
        }
    }
}

class CasoCorrupcion {
    public String nombreCaso;
    public Date fechaInicio;
    public String estado;
    public Persona[] implicados;
    public int cantidadPersonas;

    public CasoCorrupcion(String nombreCaso, Date fechaInicio, String estado) {
        this.nombreCaso = nombreCaso;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.implicados = new Persona[10]; 
        this.cantidadPersonas = 0;
    }

    public void agregarPersona(Persona persona) {
        if (cantidadPersonas < implicados.length) {
            implicados[cantidadPersonas++] = persona;
        } else {
            System.out.println("No se pueden agregar más personas al caso: " + nombreCaso);
        }
    }

    public void actualizarEstado() {
        long diasTranscurridos = TimeUnit.MILLISECONDS.toDays(new Date().getTime() - fechaInicio.getTime());
        if (diasTranscurridos > 14) {
            estado = "Urgente";
        } else if (diasTranscurridos > 7) {
            estado = "Alerta";
        }
    }

    @Override
    public String toString() {
        return "CasoCorrupcion{" +"nombreCaso='" + nombreCaso + '\'' +", fechaInicio=" + fechaInicio +", estado='" + estado + '\'' +
                ", implicados=" + Arrays.toString(Arrays.copyOf(implicados, cantidadPersonas)) +'}';
    }
}

class Persona {
    public String nombre;
    public int edad;
    public String ocupacion;
    public String nivelImplicacion;
    public int sentencia;

    public Persona(String nombre, int edad, String ocupacion, String nivelImplicacion, int sentencia) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.sentencia = sentencia;
    }

    public boolean ReduccionDePena() {
        return "Acusado".equalsIgnoreCase(nivelImplicacion);
    }

    public boolean PagarFianza(double dañoEconomico) {
        return "Acusado".equalsIgnoreCase(nivelImplicacion) && sentencia < 1 && dañoEconomico * 0.5 > 0;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ocupacion='" + ocupacion + '\'' +
                ", nivelImplicacion='" + nivelImplicacion + '\'' +
                ", sentencia=" + sentencia +
                '}';
    }
}
    

