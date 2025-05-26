
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Problema4_EjecutorAppFiscalia {

    public static void main(String[] args) {
        CasoCorrupcion[] casos = new CasoCorrupcion[2];

        casos[0] = new CasoCorrupcion("Caso Petroecuador", new Date(), "Iniciado", 500000);
        casos[0].agregarPersona(new Persona("Juan Perez", 45, "Ex Ministro", "Acusado", 2, true));
        casos[0].agregarPersona(new Persona("Luis Gómez", 38, "Contratista", "Testigo", 0, false));

        casos[1] = new CasoCorrupcion("Caso Odebrecht", new Date(), "Iniciado", 1000000);
        casos[1].agregarPersona(new Persona("Carlos Ruiz", 47, "Funcionario Público", "Acusado", 0, true));
        casos[1].agregarPersona(new Persona("Julian Ruiz", 47, "Contratista", "Victima", 0, true));

        for (CasoCorrupcion caso : casos) {
            caso.actualizarEstado();
            System.out.println(caso);

            for (Persona p : caso.implicados) {
                if (p.nivelImplicacion.equalsIgnoreCase("Acusado")) {
                    System.out.println(p.nombre);
                    System.out.println("- ¿Reducción de pena?: " + p.reduccionDePena());
                    System.out.println("- ¿Puede pagar fianza?: " + p.pagarFianza(caso.danoEconomico) + "- Fianza total: " + p.fianza);
                }
            }
            System.out.println();
        }
    }
}

class CasoCorrupcion {

    public String nombreCaso;
    public Date fechaInicio;
    public String estado;
    public double danoEconomico;
    public Persona[] implicados = new Persona[2];
    public int cantidadImplicados = 0;

    public CasoCorrupcion(String nombreCaso, Date fechaInicio, String estado, double danoEconomico) {
        this.nombreCaso = nombreCaso;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.danoEconomico = danoEconomico;

    }

    public void agregarPersona(Persona persona) {
        if (cantidadImplicados < implicados.length) {
            implicados[cantidadImplicados++] = persona;
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
        return "CasoCorrupcion{" + "nombreCaso=" + nombreCaso + ", fechaInicio=" + fechaInicio + ", estado=" + estado + ", danoEconomico=" + danoEconomico + ", implicados=" + Arrays.toString(implicados) + ", cantidadImplicados=" + cantidadImplicados + '}';
    }
}

class Persona {

    public String nombre;
    public int edad;
    public String ocupacion;
    public String nivelImplicacion;
    public int sentencia;
    public boolean colabora;
    public double fianza;

    public Persona(String nombre, int edad, String ocupacion, String nivelImplicacion, int sentencia, boolean colabora) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.sentencia = sentencia;
        this.colabora = colabora;
    }

    public boolean reduccionDePena() {
        return "Acusado".equalsIgnoreCase(nivelImplicacion) && colabora;
    }

    public boolean pagarFianza(double danoEconomico) {
        boolean puedePagar = "Acusado".equalsIgnoreCase(nivelImplicacion)
                && sentencia < 1
                && colabora
                && (danoEconomico * 0.5 > 0);

        if (puedePagar) {
            this.fianza = danoEconomico * 0.5;
        } else {
            this.fianza = 0; 
        }

        return puedePagar;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", edad=" + edad + ", ocupacion=" + ocupacion + ", nivelImplicacion=" + nivelImplicacion + ", sentencia=" + sentencia + ", colabora=" + colabora + '}';
    }

}
