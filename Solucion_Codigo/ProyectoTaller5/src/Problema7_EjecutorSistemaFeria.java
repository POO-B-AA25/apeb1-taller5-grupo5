
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Problema7_EjecutorSistemaFeria {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
 
        String[] nombres = {"Lucinda", "Jose", "Miguel", "Ana"};
        String[] empresas = {"PrisnesCompany", "FoodiesInc", "DeliciasSAS", "SaboresUnicos", "GourmetCo"};

        String[] ingredientes1 = {"Cebolla", "Pimiento", "Ajo", "Aliño"};
        String[] ingredientes2 = {"Res", "Pollo", "Pescado", "Longaniza"};
        String[] ingredientes3 = {"Arroz", "Mote", "Arbeja", "Lentejas"};

       
        ArrayList<Plato> listaPlatos = new ArrayList<>();
        ArrayList<Expositor> listaExpositores = new ArrayList<>();
        char opcionExpo = 's';
        while (opcionExpo == 'S' || opcionExpo == 's') {
            char opcionplato = 'S';
            while (opcionplato == 'S' || opcionplato == 's') {
                
                String ingrediente1 = ingredientes1[random.nextInt(ingredientes1.length)];
                String ingrediente2 = ingredientes2[random.nextInt(ingredientes2.length)];
                String ingrediente3 = ingredientes3[random.nextInt(ingredientes3.length)];

                List<String> ingredientes = new ArrayList<>();
                ingredientes.add(ingrediente1);
                ingredientes.add(ingrediente2);
                ingredientes.add(ingrediente3);

                String nombre = ingrediente2;
                double precio = 2 + (8 - 2) * random.nextDouble(); // Entre 2.0 y 8.0
                int disponibles = 5 + random.nextInt(16); // Entre 5 y 20

                Plato p = new Plato(nombre, ingredientes, precio, disponibles);

                System.out.println("\nPlato generado:");
                System.out.println("Nombre: " + p.nombre);
                System.out.println("Precio: $" + String.format("%.2f", p.precio));
                System.out.println("Disponibles: " + p.disponibles);

                do {
                    System.out.print("¿Cuántos desea comprar? ");
                    p.vendidos = sc.nextInt();
                    if (p.vendidos < 0 || p.vendidos > p.disponibles) {
                        System.out.println("Número inválido. Debe ser entre 0 y " + p.disponibles);
                    }
                } while (p.vendidos < 0 || p.vendidos > p.disponibles);

                p.vender();
                p.toString();
                listaPlatos.add(p);

                System.out.print("¿Desea ingresar otro plato? (S/N): ");
                opcionplato = sc.next().charAt(0);
                while (opcionplato != 'S' && opcionplato != 's' && opcionplato != 'N' && opcionplato != 'n') {
                    System.out.print("Opción inválida. ¿Desea ingresar otro plato? (S/N): ");
                    opcionplato = sc.next().charAt(0);
                }
            }

            Expositor expo = new Expositor(nombres[random.nextInt(nombres.length)], empresas[random.nextInt(empresas.length)], listaPlatos, listaPlatos.size());
            expo.calcularIngresos();
            expo.platoMasVendido();
            listaExpositores.add(expo);
            System.out.println("\n--- Reporte del Expositor ---");
            System.out.println(expo);
            System.out.print("Desea añadir otro Expositor(S/N):");
            opcionExpo = sc.next().charAt(0);
        }
        Expositor expositorConMasClientes = null;
        int maxClientes = -1;
        for (Expositor expo : listaExpositores) {
            expo.calcularIngresos();
            if (expo.clientes > maxClientes) {
                maxClientes = expo.clientes;
                expositorConMasClientes = expo;
            }
        }
        System.out.println("\n--- Expositor Con mas Clientes ---");
        System.out.println(expositorConMasClientes);
    }

}


class Plato {

    public String nombre;
    public List<String> ingredientes;
    public double precio;
    public int disponibles;
    public int vendidos;

    public Plato(String nombre, List<String> ingredientes, double precio, int disponibles) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.disponibles = disponibles;
    }

    public boolean estadoDisponible() {
        return disponibles > 0;
    }

    public void vender() {
        if (vendidos <= disponibles) {
            disponibles -= vendidos;
        } else {
            System.out.println("No hay suficientes platos disponibles.");
        }
    }

    @Override
    public String toString() {
        return String.format("Plato{nombre=%s, ingredientes=%s, precio=%.2f, disponibles=%d, vendidos=%d}",
                nombre, ingredientes, precio, disponibles, vendidos);
    }

}

class Expositor {

    public String nombre;
    public String responsable;
    public ArrayList<Plato> platos;
    public double ingresos;
    public Plato masVendido;
    public int clientes;

    public Expositor() {
    }

    public Expositor(String nombre, String responsable, ArrayList<Plato> platos, int clientes) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.platos = platos;
        this.clientes = clientes;
    }

    public void anadirPlato(Plato p) {
        platos.add(p);
        
    }

    public double calcularIngresos() {
        double total = 0;
        for (Plato p : platos) {
            total += p.vendidos * p.precio;
        }
        this.ingresos = total;
        return this.ingresos;
    }

    public Plato platoMasVendido() {
        int max = -1;
        for (Plato p : platos) {
            if (p.vendidos > max) {
                max = p.vendidos;
                masVendido = p;
            }
        }
        return masVendido;
    }

    @Override
    public String toString() {
        return "Expositor" + "\nnombre=" + nombre + "\nresponsable=" + responsable + "\nplatos=" + platos + "\ningresos=" + ingresos + "\nmasVendido=" + masVendido + "\nclientes=" + clientes;
    }   

}
