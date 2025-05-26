
import java.util.Random;
import java.util.Scanner;

public class Problema2_EjectuorSistemaCalificaciones {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Random random = new Random();
        String nombres[] = {"Juan", "Fernando", "Marco", "Felipe", "Orlando", "Ana"};
        String apellidos[] = {"Lopez", "Roman", "Perez", "Flores", "Rios", "Piedra"};
        String materias[] = {"Matematica", "Fisica", "Programacion", "Computacion"};
        Estudiante[] aprobados = new Estudiante[20];
        Estudiante[] reprobados = new Estudiante[20];
        int k = 0;
        int l = 0;
        char anadir = 's';

        while (anadir == 's') {
            if (k > 20 || l > 20) {
                System.out.println("Se ha llegad al limite de estudiantes");

            } else {
                double acd = Math.floor(Math.random() * 36) / 10;
                double ape = Math.floor(Math.random() * 36) / 10;
                double aa = Math.floor(Math.random() * 31) / 10;
                Materia materia = new Materia(materias[(int) Math.floor(Math.random() * materias.length)], acd, ape, aa);

                String nombreEstudiante = nombres[(int) Math.floor(Math.random() * nombres.length)] + " "
                        + apellidos[(int) Math.floor(Math.random() * apellidos.length)];
                Estudiante estudiante = new Estudiante(nombreEstudiante, 18 + random.nextInt(7), materia);
                System.out.println(estudiante.nombreE);
                System.out.print(materia);
                System.out.println("Nota Final: " + (materia.calcularNotaFinal()));
                if (!estudiante.comprobarAprobacion()) {
                    System.out.println("Deberá rendir un examen de recuperación sobre 3.5/10 pts. agregado al 60% acumulado de los componentes ACD, APE y AA.");
                }

                if (estudiante.comprobarAprobacion()) {
                    System.out.println("Aprobado");
                    aprobados[k] = estudiante;
                    k++;
                } else {
                    System.out.println("Reprobado");
                    reprobados[l] = estudiante;
                    l++;
                }
                System.out.print("Desea añadir otro estudiante? (s/n): ");
                anadir = sc.next().charAt(0);
            }

        }
        System.out.println("\nAprobados");
        for (int i = 0; i < k; i++) {
            System.out.println(aprobados[i]);
        }

        System.out.println("\nReprobados");
        for (int i = 0; i < l; i++) {
            System.out.println(reprobados[i]);
        }

    }

}

class Estudiante {

    public String nombreE;
    public int edad;
    public Materia materia;
    public boolean aprobado;

    public Estudiante(String nombreE, int edad, Materia materia) {
        this.nombreE = nombreE;
        this.edad = edad;
        this.materia = materia;
    }
    
    public boolean comprobarAprobacion() {
        double total = materia.calACD + materia.calAPE + materia.calAA;
        return aprobado = total >= 7;
    }
    

    @Override
    public String toString() {
        return "Estudiante{" + "nombreE=" + nombreE + ", edad=" + edad + ", materia=" + materia + ", aprobado=" + aprobado + '}';
    }

}

class Materia {

    public String nombreM;
    public double calACD;
    public double calAPE;
    public double calAA;
    public double notaFinal;

    public Materia(String nombreM, double calACD, double calAPE, double calAA) {
        this.nombreM = nombreM;
        this.calACD = calACD;
        this.calAPE = calAPE;
        this.calAA = calAA;
    }
    public double calcularNotaFinal() {
        this.notaFinal = calACD + calAPE + calAA;
        return this.notaFinal;
    }
    

    @Override
    public String toString() {
        return "Materia{" + "nombreM=" + nombreM + ", calACD=" + calACD + ", calAPE=" + calAPE + ", calAA=" + calAA + '}';
    }
    
    
    
}
