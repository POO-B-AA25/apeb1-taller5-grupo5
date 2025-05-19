
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio1_EjecutorSistemVentas {

    public static Scanner sc = new Scanner(System.in);
    public static Producto inventario[];
    
    public static void main(String[] args) {
        Random random = new Random();

        String productos[] = {
            "Memoria RAM", "Monitor 120 Hz", "Laptop", "Monitor 140 Hz", "Laptop diseño",
            "Tarjeta Gráfica", "Celular iPhone 15", "Teclado", "Televisor 40 pulgadas",
            "Televisor 50 pulgadas", "Tarjeta Madre", "VisionPro", "Computador Gamer"
        };

        inventario = new Producto[productos.length];
        for (int i = 0; i < inventario.length; i++) {
            String nombreP = productos[i];
            inventario[i] = new Producto(nombreP, 100 + random.nextInt(900), 1 + random.nextInt(10));
        }

        CarritoCompras carrito = new CarritoCompras();

        char anadir = 's';
        while (anadir == 's') {
            int indice = random.nextInt(productos.length);
            String productoElegido = productos[indice];
            int cantidadElegida = 1 + random.nextInt(3);

            carrito.agregarProducto(productoElegido, cantidadElegida, inventario);

            System.out.print("¿Desea añadir otro producto?: (s/n): ");
            anadir = sc.next().charAt(0);
        }

        carrito.mostrarDetalleCompra();
        carrito.calcularTotal();


        System.out.print("Ingrese el monto con el que desea pagar: ");
        double monto = sc.nextDouble();

        carrito.realizarPago(monto, inventario);
    }
}


class CarritoCompras {

    public ArrayList<Producto> producto = new ArrayList<>();
    public double totalCompra;
    public double montoPagado;
    public double descuento = 0.10;

    public void agregarProducto(String nombre, int cantidad, Producto[] inventario) {
        for (Producto p : inventario) {
            if (p.nombre.equals(nombre)) {
                if (p.cantidad >= cantidad) {
                    producto.add(new Producto(p.nombre, p.precio, cantidad));

                    System.out.println("Producto agregado al carrito: " + nombre + " x" + cantidad);

                } else {
                    System.out.println("No hay suficiente stock para: " + nombre);

                }
            } 
        }
    }

    public void calcularTotal() {
        totalCompra = 0;
        for (int i = 0; i < producto.size(); i++) {
            Producto p = producto.get(i);
            totalCompra = totalCompra + (p.precio * p.cantidad) ;

            
            if (totalCompra > 1000) {
                double descuentoAplicado = totalCompra * descuento;
                totalCompra -= descuentoAplicado;
                System.out.println("Descuento aplicado: $" + descuentoAplicado);
            }

            System.out.println("Total a pagar: $" + totalCompra);
        }
        
    }

    public void realizarPago(double monto, Producto[] inventario) {
        montoPagado = monto;
        if (monto >= totalCompra) {
            System.out.println("Pago aceptado. ¡Gracias por su compra!");

            for (int i = 0; i < producto.size(); i++) {
                Producto enCarrito = producto.get(i);
                for (Producto p : inventario) {
                    if (p.nombre.equals(enCarrito.nombre)) {
                        p.cantidad -= enCarrito.cantidad;
                    }
                }
            }
        } else {
            double faltante = totalCompra - monto;
            System.out.println("Pago insuficiente. Faltan: $" + faltante);
        }
        for (Producto enCarrito : producto) {
                for (Producto p : inventario) {
                    if (p.nombre.equals(enCarrito.nombre)) {
                        p.cantidad -= enCarrito.cantidad;
                        break;
                    }
                }
            }
    }

    public void mostrarDetalleCompra() {
        System.out.println("\n--- Detalle de la Compra ---");
        for (int i = 0; i < producto.size(); i++) {
            Producto p = producto.get(i);
            System.out.println("- " + p.nombre + " x" + p.cantidad + " ($" + p.precio + " c/u)");
        }
    }
}

class Producto {

    public String nombre;
    public double precio;
    public int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }
    
}
