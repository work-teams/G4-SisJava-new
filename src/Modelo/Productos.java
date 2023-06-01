//Cachique Falcon Williams Eduardo   -   19200272 

package Modelo;


/*
    Aqui tenemos la clase de Productos, donde se tiene el metodo constructor para la clase, ademas de los getters y setters 
    para poder utilizarlos desde los objetos que hereden estos metodos. 
*/

public class Productos {
    private int id;
    private String codigo;
    private String nombre;
    private int proveedor;
    private String proveedorPro;
    private int stock;
    private double precio;
    
    public Productos(){
        
    }

    public Productos(int id, String codigo, String nombre, int proveedor, String proveedorPro, int stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.proveedorPro = proveedorPro;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public String getProveedorPro() {
        return proveedorPro;
    }

    public void setProveedorPro(String proveedorPro) {
        this.proveedorPro = proveedorPro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

   /*
    Considero que aqui deberia ir un metodo que permita evaluar la cantidad minima de productos 
    y crear la vista ncesesaria para colacar un JPanel y se pueda visualizar
    */
}
