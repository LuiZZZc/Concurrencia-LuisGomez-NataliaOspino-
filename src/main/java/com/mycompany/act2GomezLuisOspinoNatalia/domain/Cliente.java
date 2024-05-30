package com.mycompany.act2GomezLuisOspinoNatalia.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private List<Producto> productos;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getNombre() {
        return nombre;
    }
}
