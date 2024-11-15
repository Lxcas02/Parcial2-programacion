package com.sd.productosapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sd.productosapi.exception.ProductoNotFoundException;
import com.sd.productosapi.model.Producto;
import com.sd.productosapi.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));
    }

    // Crear un nuevo producto
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto updateProducto(Long id, Producto productoDetails) {
        Producto producto = getProductoById(id);  // Obtener el producto existente por id
        producto.setNombre(productoDetails.getNombre());  // Actualizar el nombre
        producto.setPrecio(productoDetails.getPrecio());  // Actualizar el precio
        producto.setDescripcion(productoDetails.getDescripcion());  // Actualizar la descripción
        return productoRepository.save(producto);  // Guardar el producto actualizado
    }


    // Eliminar un producto
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
