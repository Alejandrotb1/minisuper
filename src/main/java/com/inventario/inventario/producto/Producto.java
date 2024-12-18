package com.inventario.inventario.producto;
import com.inventario.inventario.categoria.Categoria;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*/

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false, unique = true)
    private String codigo;
    @Column(length = 150, nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal precioCompra;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal precioVenta;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    public Categoria categoria;

    @Column(nullable = false)
    public LocalDate fechaAdquisicion;

    public LocalDate fechaVencimiento;

    @Column(length = 255)
    public String imagen;

    @Column(length = 500)
    public String descripcion;

    @Transient
    public int cantidad;

    public Producto() {
    }

    public Producto(Integer id, String codigo, String nombre, BigDecimal precioCompra, BigDecimal precioVenta, Categoria categoria, LocalDate fechaAdquisicion, LocalDate fechaVencimiento, String imagen, String descripcion, int cantidad) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaVencimiento = fechaVencimiento;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Producto(String codigo, String nombre, BigDecimal precioCompra, BigDecimal precioVenta, Categoria categoria, LocalDate fechaAdquisicion, LocalDate fechaVencimiento, String imagen, String descripcion, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaVencimiento = fechaVencimiento;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
