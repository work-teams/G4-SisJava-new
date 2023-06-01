package Modelo;

import static Modelo.Utilidades.encriptarPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    /*
    MODIFICACIÓN
    @JorgeMarin
    - Agregar sentencia "finally" como buena práctica de programación
    - Cerrar la conexión con la base de datos
    - Liberar la memoria de los objetos rs, ps
    - Utiliza constructor de clase login
    
    MODIFICACIÓN
    @Carlos Tarmeño
    - Se verifica que la contraseña del label y la contraseña coincidan con el hash almacenado.
    - Si coinciden entonces se establece la contraseña hasheada para la autenticación.
     */
    public login log(String correo, String pass) {
        login l = null;
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try ( Connection con = cn.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("pass");
                    if (BCrypt.verifyer().verify(pass.toCharArray(), hashedPassword).verified) { // Verificar la contraseña encriptada
                        l = new login(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"), rs.getString("pass"), rs.getString("rol"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return l;
    }

    /*
    MODIFICACIÓN
    @Jorge Marin
    - Agregar sentencia "finally" como buena práctica de programación
    - Cerrar la conexión con la base de datos
    - Liberar la memoria de los objetos rs, ps.
    
    MODIFICACIÓN
    @Carlos Tarmeño
    - Al realizar el registro se llama al método estático 'encriptarPassword' que recibe la contraseña y retorna una contraseña hasheada.
    - La contraseña hasheada se inserta en la BD.
     */
    public boolean Registrar(login reg) {
        String sql = "INSERT INTO usuarios (nombre, correo, pass, rol) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            String hashedPassword = encriptarPassword(reg.getPass()); // Encriptar la contraseña
            ps.setString(3, hashedPassword);
            ps.setString(4, reg.getRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close(); // cerrar la conexión
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
  
    /*
    MODIFICACIÓN
    @Jorge Marin
    - Agregar sentencia "finally" como buena práctica de programación
    - Cerrar la conexión con la base de datos
    - Liberar la memoria de los objetos rs, ps.
     */
    public List<login> ListarUsuarios() {
        List<login> Lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                login lg = new login();
                lg.setId(rs.getInt("id"));
                lg.setNombre(rs.getString("nombre"));
                lg.setCorreo(rs.getString("correo"));
                lg.setRol(rs.getString("rol"));
                Lista.add(lg);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return Lista;
    }
   
    
    /*
    MODIFICACION
    @DanielSantos
    Objetivo: Implementacion de funcionalidad de edicion y eliminacion de usuarios
    */
    
    /*
    El sistema actual hace uso del LoginDAO para realizar el registro y 
    visualizacion de los usuarios registrados en la base de datos
    
    Para mejorar la funcionalidad se desea implementar las funciones de edicion 
    de usuario y elminacion de usuario
    
    La funcion edicion de usuario debe usar el query update y requiere el id del
    usuario que se desea editar y la nueva informacion del usuario, estos datos se conseguiran
    mediante el formulario de la pestaña Usuario de la vista Sistema
    
    La funcion eliminar usuario usara el query delete y requerira el id del usuario
    que se desea eliminar, este id se puede obtener de la tabla de usuarios en la 
    pestaña Usuarios de la vista Sistema
    
    */
    
        public boolean Modificar(login reg){
        String sql = "UPDATE usuarios SET nombre = ?, correo= ?, pass = ?, rol = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.setInt(5, reg.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
             try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean Eliminar(int id){
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
             try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
