package com.mycompany.act2GomezLuisOspinoNatalia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import com.mycompany.act2GomezLuisOspinoNatalia.domain.Producto;
import com.mycompany.act2GomezLuisOspinoNatalia.domain.Cajera;
import com.mycompany.act2GomezLuisOspinoNatalia.domain.Cliente;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ingresar el número de cajeras y clientes desde la consola
        System.out.println("Ingrese el numero de cajeras:");
        int numCajeras = sc.nextInt();
        sc.nextLine(); // limpiar el buffer
        System.out.println("Ingrese el numero de clientes:");
        int numClientes = sc.nextInt();
        sc.nextLine(); // limpiar el buffer

        // Crear cajeras
        List<Cajera> cajeras = new ArrayList<>();
        for (int i = 1; i <= numCajeras; i++) {
            System.out.println("Ingrese el nombre de la cajera " + i + ":");
            String nombreCajera = sc.nextLine();
            cajeras.add(new Cajera(nombreCajera));
        }

        // Crear clientes
        Queue<Cliente> colaClientes = new LinkedList<>();
        for (int i = 1; i <= numClientes; i++) {
            System.out.println("Ingrese el nombre del cliente " + i + ":");
            String nombreCliente = sc.nextLine();
            Cliente cliente = new Cliente(nombreCliente);

            System.out.println("Ingrese la cantidad de productos que desea llevar:");
            int numProductos = sc.nextInt();
            sc.nextLine(); // limpiar el buffer
            for (int j = 1; j <= numProductos; j++) {
                System.out.println("Ingrese el nombre del producto " + j + ":");
                String nombreProducto = sc.nextLine();
                System.out.println("Ingrese la cantidad del producto " + j + ":");
                int cantidadProducto = sc.nextInt();
                System.out.println("Ingrese el precio del producto " + j + ":");
                double precioProducto = sc.nextDouble();
                sc.nextLine(); // limpiar el buffer

                Producto producto = new Producto(nombreProducto, cantidadProducto, precioProducto);
                cliente.agregarProducto(producto);
            }

            colaClientes.add(cliente);
        }

        // Asignar clientes a las cajeras y procesar las compras
        for (Cajera cajera : cajeras) {
            cajera.setClientes(colaClientes);
            cajera.start();
        }

        // Esperar a que todos los hilos terminen
        try {
            for (Cajera cajera : cajeras) {
                cajera.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar tiempos de procesamiento
        for (Cajera cajera : cajeras) {
            System.out.println("Cajera " + cajera.getNombre() + " procesó en " + cajera.getTiempoProcesamiento() + " ms");
        }

        sc.close();
    }
}
