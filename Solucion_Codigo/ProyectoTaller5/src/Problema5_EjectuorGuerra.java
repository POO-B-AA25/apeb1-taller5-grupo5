
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Problema5_EjectuorGuerra {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        char opcion = 'S';

        String[] nombresEventos = {"Batalla", "Tratado de paz", "Reunión Diplomática"};

        while (opcion == 'S') {

            ArrayList<Pais> paises1 = new ArrayList<>(Arrays.asList(
                    new Pais("Peru", false, 100, 10),
                    new Pais("Ecuador", false, 100, 10),
                    new Pais("Colombia", false, 100, 10)
            ));

            ArrayList<Pais> paises2 = new ArrayList<>(Arrays.asList(
                    new Pais("Rusia", true, 100, 60),
                    new Pais("Ucrania", false, 100, 10),
                    new Pais("China", true, 100, 10)
            ));

            
            ArrayList<Evento> eventos = new ArrayList<>(Arrays.asList(
                    new Evento(nombresEventos[rand.nextInt(nombresEventos.length)],
                            LocalDate.of(2025, 5, 15),
                            "Latinoamérica",
                            "Problemas de territorio",
                            false,
                            paises1),
                    new Evento(nombresEventos[rand.nextInt(nombresEventos.length)],
                            LocalDate.of(2024, 5, 15),
                            "Europa",
                            "Problemas políticos",
                            true,
                            paises2)
            ));

            
            Conflicto conflicto = new Conflicto("Reseteo", LocalDate.of(2025, 5, 15), 100, eventos);
            conflicto.actualizarEstado(); 
            System.out.println(conflicto); 
            System.out.print("SIMULAR MÁS: ");
            opcion = sc.next().charAt(0);
        }
    }
}


class Conflicto {

    public String nombre;
    public LocalDate fechaInicio;
    public String estadoActual;
    public int totalPaisesMundo;
    public ArrayList<Evento> eventos;

    public Conflicto(String nombre, LocalDate fechaInicio, int totalPaisesMundo, ArrayList<Evento> eventos) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.totalPaisesMundo = totalPaisesMundo;
        this.eventos = eventos;
    }


    public void actualizarEstado() {
        int numeroPaisesBatalla = 0;

       
        for (Evento evento : eventos) {
            if (evento.nombre.equals("Batalla")) {
                numeroPaisesBatalla++;
            }
        }

        
        if ((numeroPaisesBatalla / 100.0) * totalPaisesMundo > 50) {
            this.estadoActual = "GUERRA MUNDIAL";
        } else if ((numeroPaisesBatalla / 100.0) * totalPaisesMundo > 30) {
            this.estadoActual = "CONVOCAR A LA ONU";
        }

        
        for (Evento evento : eventos) {
            for (Pais pais : evento.paises) {
                if (pais.estadoDesarrollo && evento.usoArmasNucleares) {
                    this.estadoActual = "GUERRA MUNDIAL";
                    break;
                }
            }
        }

        
        for (Evento evento : eventos) {
            for (Pais pais : evento.paises) {
                if ((pais.numeroBajas / 100.0) * pais.totalHabitantes >= 50) {
                    this.estadoActual = "CONVOCAR A LA ONU";
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conflicto{\n");
        sb.append("  nombre='" + nombre + "',\n");
        sb.append("  fechaInicio=" + fechaInicio + ",\n");
        sb.append("  estadoActual=" + estadoActual + ",\n");
        sb.append("  totalPaisesMundo=" + totalPaisesMundo + ",\n");
        sb.append("  eventos=[\n");
        for (Evento e : eventos) {
            sb.append(e.toString()).append(",\n");
        }
        sb.append("  ]\n");
        sb.append("}");
        return sb.toString();
    }
}


class Evento {

    public String nombre;
    public LocalDate fecha;
    public String ubicacion;
    public String descripcion;
    public boolean usoArmasNucleares;
    public ArrayList<Pais> paises;

    public Evento(String nombre, LocalDate fecha, String ubicacion, String descripcion,
            boolean usoArmasNucleares, ArrayList<Pais> paises) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.usoArmasNucleares = usoArmasNucleares;
        this.paises = paises;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("  Evento{\n");
        sb.append("    nombre='" + nombre + "',\n");
        sb.append("    fecha=" + fecha + ",\n");
        sb.append("    ubicacion='" + ubicacion + "',\n");
        sb.append("    descripcion='" + descripcion + "',\n");
        sb.append("    usoArmasNucleares=" + usoArmasNucleares + ",\n");
        sb.append("    paises=[\n");
        for (Pais p : paises) {
            sb.append(p.toString()).append(",\n");
        }
        sb.append("    ]\n");
        sb.append("  }");
        return sb.toString();
    }
}


class Pais {

    public String nombre;
    public boolean estadoDesarrollo;
    public int totalHabitantes;
    public int numeroBajas;

    public Pais(String nombre, boolean estadoDesarrollo, int totalHabitantes, int numeroBajas) {
        this.nombre = nombre;
        this.estadoDesarrollo = estadoDesarrollo;
        this.totalHabitantes = totalHabitantes;
        this.numeroBajas = numeroBajas;
    }

    @Override
    public String toString() {
        return "    - Pais{\n"
                + "        nombre='" + nombre + "',\n"
                + "        estadoDesarrollo=" + estadoDesarrollo + ",\n"
                + "        totalHabitantes=" + totalHabitantes + ",\n"
                + "        numeroBajas=" + numeroBajas + "\n"
                + "    }";
    }
}
