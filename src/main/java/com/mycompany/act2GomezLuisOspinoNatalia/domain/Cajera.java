package com.mycompany.act2GomezLuisOspinoNatalia.domain;

import java.util.Queue;

public class Cajera extends Thread {
    private String nombre;
    private Queue<Cliente> clientes;
    private long tiempoProcesamiento; // Tiempo en milisegundos

    public Cajera(String nombre) {
        this.nombre = nombre;
        this.tiempoProcesamiento = 0;
    }

    public void setClientes(Queue<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public void run() {
        while (true) {
            Cliente cliente = null;
            synchronized (clientes) {
                if (!clientes.isEmpty()) {
                    cliente = clientes.poll();
                }
            }
            if (cliente == null) {
                break;
            }

            long inicio = System.currentTimeMillis();
            System.out.println("Cajera " + nombre + " está procesando al cliente " + cliente.getNombre());

            double totalCompra = 0;
            for (Producto producto : cliente.getProductos()) {
                totalCompra += producto.getPrecio() * producto.getCantidad();
                // Simula el tiempo de escaneo de cada producto
                try {
                    Thread.sleep(100); // 100 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("  Producto: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad() + ", Precio: " + producto.getPrecio());
            }

            long fin = System.currentTimeMillis();
            tiempoProcesamiento += (fin - inicio);

            System.out.println("Cajera " + nombre + " terminó con el cliente " + cliente.getNombre() + " en " + (fin - inicio) + " ms");
            System.out.println("Total de la compra del cliente " + cliente.getNombre() + ": $" + totalCompra);
            System.out.println("-------------------------------");
        }
    }

    public long getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    public String getNombre() {
        return nombre;
    }
}
