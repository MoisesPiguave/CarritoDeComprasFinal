package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> listaProductos;

    public ProductoDAOMemoria() {
        listaProductos = new ArrayList<>();
    }

    @Override
    public void crear(Producto producto) {
        listaProductos.add(producto);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : listaProductos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public void actualizar(Producto producto) {
        Producto p = buscarPorCodigo(producto.getCodigo());
        if (p != null) {
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
        }
    }

    @Override
    public void eliminar(int codigo) {
        Producto p = buscarPorCodigo(codigo);
        if (p != null) {
            listaProductos.remove(p);
        }
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(listaProductos);
    }
}
