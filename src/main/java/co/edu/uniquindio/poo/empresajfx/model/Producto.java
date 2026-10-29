package co.edu.uniquindio.poo.empresajfx.model;

public class Producto {
    private String nombreProducto;
    private String precio;
    private String codigo;

    public Producto(String nombreProducto, String precio, String codigo) {
        this.nombreProducto = nombreProducto;
        this.precio=precio;
        this.codigo=codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
