
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio3_EjecutorCategorizacionDepartamentos {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Random random = new Random();
        Empresa empresa = new Empresa("Editorial Mundial", "27677457890001", "24 de Mayo");

        String[] nombresDepartamentos = {"Programación", "Marketing", "Recursos Humanos", "Ventas", "Finanzas", "Diseño", "Logística"};

        char continuar = 's';

        while (continuar == 's') {
            // Datos aleatorios
            String nombreDep = nombresDepartamentos[random.nextInt(nombresDepartamentos.length)];
            int empleados = 5 + random.nextInt(61); // de 5 a 65 empleados
            double produccion = 100000 + (random.nextDouble() * 2000000); // entre 100 mil y 2 millones

            Departamento d = new Departamento(nombreDep, empleados, produccion);
            d.determinarCategoria();
            empresa.agregarDepartamento(d);

            System.out.println("Departamento añadido:");
            System.out.println(d);

            System.out.print("¿Desea agregar otro departamento? (s/n): ");
            continuar = sc.next().charAt(0);
        }

        System.out.println("\n--- Detalles Empresa ---");
        System.out.println(empresa);
    }
}

class Departamento {

    public String nombre;
    public int numeroEmpleados;
    public double produccionAnual;
    public char categoria;

    public Departamento(String nombre, int numeroEmpleados, double produccionAnual) {
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
        this.produccionAnual = produccionAnual;

    }

    public char determinarCategoria() {
        if (numeroEmpleados > 20 && produccionAnual > 1000000) {
            categoria = 'A';
            return categoria;
        } else if (numeroEmpleados == 20 && produccionAnual == 1000000) {
            categoria = 'B';
            return categoria;
        } else if (numeroEmpleados == 10 && produccionAnual == 500000) {
            categoria = 'C';
            return categoria;
        } else {
            categoria = 'C';
            return categoria;
        }
    }

    @Override
    public String toString() {
        return "Departamento{" + "nombre=" + nombre + ", numeroEmpleados=" + numeroEmpleados + ", produccionAnual=" + produccionAnual + ", categoria=" + categoria + '}';
    }

}

class Empresa {

    public String nombre;
    public String ruc;
    public String direccion;
    public ArrayList<Departamento> departamentos;

    public Empresa(String nombre, String ruc, String direccion) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.departamentos = new ArrayList();
    }

    public void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    @Override
    public String toString() {
        return "Empresa{" + "nombre=" + nombre + ", ruc=" + ruc + ", direccion=" + direccion + ", departamentos=" + departamentos + '}';
    }

}
