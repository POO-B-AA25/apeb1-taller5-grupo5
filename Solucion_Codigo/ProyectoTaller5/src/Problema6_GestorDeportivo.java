
package gestordeportivo;

import java.util.ArrayList;
import java.util.Scanner;

public class Problema6_GestorDeportivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventoDeportivo evento = new EventoDeportivo();
        
        System.out.println("Bienvenido al Evento");

        while (true) {
            System.out.println("Menu de Gestion Deportiva");
            System.out.println("[1]Registrar Participante");
            System.out.println("[2]Registrar puntuaje ");
            System.out.println("[3] Generar reporte");
            System.out.println("[4] Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Identificación: ");
                    String id = scanner.nextLine();
                    System.out.print("Disciplina: ");
                    String disciplina = scanner.nextLine();
                    evento.agregarParticipante(new Participante(nombre, id, disciplina));
                    break;
                case 2:
                    System.out.print("Nombre del participante: ");
                    String idPuntaje = scanner.nextLine();
                    System.out.print("Puntaje obtenido: ");
                    int puntaje = scanner.nextInt();
                    evento.registrarPuntaje(idPuntaje, puntaje);
                    break;
                case 3:
                    evento.generarReporte();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    }
    
}



class Participante{
    public String nombre;
    public String cedula;
    public String disciplina;
    public ArrayList<Integer> puntajes;

    public Participante(String nombre, String identificacion, String disciplina) {
        this.nombre = nombre;
        this.cedula = identificacion;
        this.disciplina = disciplina;
        this.puntajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void agregarPuntaje(int puntaje) {
        puntajes.add(puntaje);
    }

    public double calcularPromedio() {
        if (puntajes.isEmpty()) return 0;
        int suma = 0;
        for (int p : puntajes) {
            suma += p;
        }
        return (double) suma / puntajes.size();
    }

    public int pruebasSuperadas(int umbral) {
        int superadas = 0;
        for (int p : puntajes) {
            if (p >= umbral) superadas++;
        }
        return superadas;
    }
}
class EventoDeportivo {
    public ArrayList<Participante> participantes;

    public EventoDeportivo() {
        this.participantes = new ArrayList<>();
    }

    public void agregarParticipante(Participante p) {
        participantes.add(p);
    }

    public void registrarPuntaje(String identificacion, int puntaje) {
        for (Participante p : participantes) {
            if (p.getNombre().equals(identificacion)) {
                p.agregarPuntaje(puntaje);
                System.out.println("Puntaje registrado para " + p.getNombre());
                return;
            }
        }
        System.out.println("Participante no encontrado.");
    }

    public void generarReporte() {
        System.out.println("\n--- Reporte de Desempeño ---");
        for (Participante p : participantes) {
            System.out.println("Participante: " + p.getNombre() + " | Disciplina: " + p.getDisciplina());
            System.out.println("Promedio de rendimiento: " + p.calcularPromedio());
            System.out.println("Pruebas superadas: " + p.pruebasSuperadas(60));
        }
    }
}




